package npm.dashboard.daoImpl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.Criteria;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import npm.admin.beans.processList;
import npm.model.DRIVE_THRESHOLD_LOG;
import npm.model.Disk_Configuration;
import npm.model.Firewall_Rules;
import npm.model.Hardware_Inventory;
import npm.model.Hw_Printers;
import npm.model.Logicaldrive_Configuration;
import npm.model.Memory_Configuration;
import npm.model.Patch_details;
import npm.model.Process_Details;
import npm.model.Service_Details;
import npm.model.Sw_Inventory;

import npm.admin.beans.AgentInstalledDeviceBean;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;

import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.dao.MasterDao;
import npm.admin.service.MasterService;
import npm.configuration.AbstractDao;
import npm.dashboard.dao.DashboardDao;
import npm.model.GroupMasterModel;
import npm.model.InterfaceMonitoring;
import npm.model.LatencyHisotryModel;
import npm.model.MailTemplate;
import npm.model.NodeHealthMonitoring;
import npm.model.NodeMonitoringModel;
import npm.model.SyslogEvent;
import npm.model.UserManageScopeModel;

@Repository
@Transactional
public class DashboardDaoImpl extends AbstractDao<Integer, NodeMonitoringModel> implements DashboardDao {

	@Autowired
	private Environment environment;

	@Autowired
	MasterService service;

	@Autowired
	MasterDao dao;

	/* Get Device Summary Count */
	public JSONArray deviceSummaryCount(String userScopeData) {

		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {
			Query q = getSession().createQuery("SELECT NODEMON.NODE_STATUS, COUNT(*)\r\n"
					+ "FROM NodeMonitoringModel NODEMON, AddNodeModel add_node\r\n"
					+ "WHERE add_node.DEVICE_IP = NODEMON.NODE_IP AND add_node.MONITORING_PARAM = 'Yes'\r\n" + "AND "
					+ userScopeData + "\r\n" + "GROUP BY NODEMON.NODE_STATUS");

			List<?> dataList = q.list();
			array1 = new JSONArray();
			String up_count = "0";
			String down_count = "0";
			String warrning_count = "0";
			for (int i = 0; i < dataList.size(); i++) {
				// array = new JSONArray();

				Object[] row = (Object[]) dataList.get(i);

				if (row[0].equals("Up")) {
					up_count = row[1].toString();
				}
				if (row[0].equals("Down")) {
					down_count = row[1].toString();
				}
				if (row[0].equals("Warrning")) {
					warrning_count = row[1].toString();
				}
				// array.put("status:"+row[0]);
				// array.put("count:"+row[1]);
//				jsonObject.put("status", row[0]);
//				jsonObject.put("count", row[1]);
//
//				array1.put(jsonObject);
			}

			jsonObject = new JSONObject();
			jsonObject.put("status", "Up");
			jsonObject.put("count", up_count);
			array1.put(jsonObject);

			jsonObject = new JSONObject();
			jsonObject.put("status", "Down");
			jsonObject.put("count", down_count);
			array1.put(jsonObject);

			jsonObject = new JSONObject();
			jsonObject.put("status", "Warning");
			jsonObject.put("count", warrning_count);
			array1.put(jsonObject);

			System.out.println("DeviceCount Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Link Summary Count */
	public JSONArray linkSummaryCount(String userScopeData) {

		JSONArray array1 = null;
		JSONObject jsonObject = null;
		try {
			Query q = getSession()
					.createQuery("SELECT IFMON.OPER_STATUS, COUNT(*)\r\n" + "FROM InterfaceMonitoring AS IFMON\r\n"
							+ "JOIN AddNodeModel AS add_node ON IFMON.NODE_IP = add_node.DEVICE_IP\r\n"
							+ "WHERE IFMON.MONITORING_PARAM = 'Yes'\r\n" + "AND " + userScopeData + "\r\n"
							+ "GROUP BY IFMON.OPER_STATUS");

			List<?> dataList = q.list();
			array1 = new JSONArray();
			String up_link = "0";
			String down_link = "0";
			for (int i = 0; i < dataList.size(); i++) {
				// array = new JSONArray();

				Object[] row = (Object[]) dataList.get(i);
				if (row[0].toString().equalsIgnoreCase("Up")) {
					up_link = row[1].toString();
				}

				if (row[0].toString().equalsIgnoreCase("Down")) {
					down_link = row[1].toString();
				}

				// array.put("status:"+row[0]);
				// array.put("count:"+row[1]);

			}
			jsonObject = new JSONObject();
			jsonObject.put("status", "up");
			jsonObject.put("count", up_link);
			array1.put(jsonObject);

			jsonObject = new JSONObject();
			jsonObject.put("status", "down");
			jsonObject.put("count", down_link);
			array1.put(jsonObject);

			System.out.println("LinkCount Summary:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Down Devices Summary Listing */
	public JSONArray deviceSummaryList(String userScopeData) {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			String sql = "SELECT nodemon.NODE_IP, nodemon.STATUS_TIMESTAMP,add_node.DEVICE_NAME\r\n"
					+ "FROM node_monitoring nodemon, add_node add_node\r\n"
					+ "WHERE nodemon.NODE_STATUS='Down' AND nodemon.NODE_IP = add_node.DEVICE_IP AND " + userScopeData;

			String db_type = environment.getRequiredProperty("DATABASE_TYPE");
			if (db_type.equalsIgnoreCase("mysql")) {
				sql = "SELECT nodemon.NODE_IP, nodemon.STATUS_TIMESTAMP, add_node.LOCATION, add_node.COMPANY,add_node.DEVICE_NAME\r\n"
						+ "FROM node_monitoring nodemon, add_node add_node\r\n"
						+ "WHERE nodemon.NODE_STATUS = 'Down' AND nodemon.NODE_IP = add_node.DEVICE_IP AND "
						+ userScopeData;
			} else if (db_type.equalsIgnoreCase("mssql")) {

			} else if (db_type.equalsIgnoreCase("oracle")) {

			}

			SQLQuery query = getSession().createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			List results = query.list();
			array1 = new JSONArray();

			for (Object object : results) {
				Map row = (Map) object;

				// System.out.println("Node IP: " + row.get("NODE_IP"));
				// System.out.println("Branch: " + row.get("LOCATION"));
				// System.out.println("Customer: " + row.get("COMPANY"));
				// System.out.println("Event Timestamp: " + row.get("STATUS_TIMESTAMP"));
				String downDate = row.get("STATUS_TIMESTAMP").toString();

				String downSince = "NA";

				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
					Date parsedDate = dateFormat.parse(downDate);
					Timestamp dbtimestamp = new Timestamp(parsedDate.getTime());
					// System.out.println("DB TimeStamp:" + dbtimestamp);
					Timestamp currenttimestamp = new Timestamp(new Date().getTime());
					// System.out.println("Current Timestamp:" + currenttimestamp);
					long milliseconds = currenttimestamp.getTime() - dbtimestamp.getTime();

					long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
					long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
							- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
					long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
					long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

					downSince = days + " Days " + hours + " Hours " + minutes + " Minutes " + seconds + " Seconds";

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Exception in Parse Timestamp:" + e);
				}

				array = new JSONArray();
				array.put(row.get("NODE_IP"));
				array.put(row.get("LOCATION"));
				array.put(row.get("COMPANY"));
				array.put(row.get("DEVICE_NAME"));
				if (!downSince.equalsIgnoreCase("NA")) {
					array.put(downSince);
				} else {
					array.put(row.get("STATUS_TIMESTAMP"));
				}
				array1.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("device down summary data =" + array1);
		return array1;
	}

	/* Get Down Link Summary Listing */
	public JSONArray linkSummaryList(String userScopeData) {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			String sql = "SELECT intmon.NODE_IP, intmon.INTERFACE_NAME, intmon.STATUS_TIMESTAMP, add_node.LOCATION, add_node.COMPANY,add_node.DEVICE_NAME\r\n"
					+ "FROM interface_monitoring intmon, add_node add_node\r\n"
					+ "WHERE intmon.OPER_STATUS = 'down' AND intmon.NODE_IP = add_node.DEVICE_IP AND " + userScopeData;

			String db_type = environment.getRequiredProperty("DATABASE_TYPE");
			if (db_type.equalsIgnoreCase("mysql")) {
				sql = "SELECT intmon.NODE_IP, intmon.INTERFACE_NAME, intmon.STATUS_TIMESTAMP, add_node.LOCATION, add_node.COMPANY,add_node.DEVICE_NAME\r\n"
						+ "FROM interface_monitoring intmon\r\n"
						+ "JOIN add_node add_node ON intmon.NODE_IP = add_node.DEVICE_IP\r\n"
						+ "WHERE intmon.OPER_STATUS = 'down' AND intmon.MONITORING_PARAM = 'Yes' AND " + userScopeData;
			} else if (db_type.equalsIgnoreCase("mssql")) {

			} else if (db_type.equalsIgnoreCase("oracle")) {

			}

			SQLQuery query = getSession().createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			List results = query.list();
			array1 = new JSONArray();

			for (Object object : results) {
				Map row = (Map) object;

				// System.out.println("Node IP: " + row.get("NODE_IP"));
				// System.out.println("Interface Name: " + row.get("INTERFACE_NAME"));
				// System.out.println("Branch: " + row.get("LOCATION"));
				// System.out.println("Customer: " + row.get("COMPANY"));
				// System.out.println("Event Timestamp: " + row.get("STATUS_TIMESTAMP"));
				String downDate = row.get("STATUS_TIMESTAMP").toString();

				String downSince = "NA";

				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
					Date parsedDate = dateFormat.parse(downDate);
					Timestamp dbtimestamp = new Timestamp(parsedDate.getTime());
					// System.out.println("DB TimeStamp:" + dbtimestamp);
					Timestamp currenttimestamp = new Timestamp(new Date().getTime());
					// System.out.println("Current Timestamp:" + currenttimestamp);
					long milliseconds = currenttimestamp.getTime() - dbtimestamp.getTime();

					long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
					long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
							- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
					long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
					long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

					downSince = days + " Days " + hours + " Hours " + minutes + " Minutes " + seconds + " Seconds";

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Exception in Parse Timestamp:" + e);
				}

				array = new JSONArray();
				array.put(row.get("NODE_IP"));
				array.put(row.get("INTERFACE_NAME"));
				array.put(row.get("LOCATION"));
				array.put(row.get("DEVICE_NAME"));
				array.put(row.get("COMPANY"));
				if (!downSince.equalsIgnoreCase("NA")) {
					array.put(downSince);
				} else {
					array.put(row.get("STATUS_TIMESTAMP"));
				}
				array1.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Device Info */
	public JSONArray getDeviceInfo(String deviceInfo, String userScopeData) {

		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;
		int srno = 0;

		if (deviceInfo.equals("total")) {
			try {
//				Query q = getSession().createQuery(
//						"SELECT add_node.DEVICE_IP, add_node.DEVICE_NAME, add_node.LOCATION, add_node.COMPANY\r\n"
//								+ "FROM AddNodeModel add_node, NodeMonitoringModel NM\r\n"
//								+ "WHERE add_node.DEVICE_IP = NM.NODE_IP AND add_node.MONITORING_PARAM = 'Yes' AND "
//								+ userScopeData);
				Query q = getSession().createSQLQuery(
						"SELECT add_node.DEVICE_IP, add_node.DEVICE_NAME, add_node.LOCATION, add_node.COMPANY, CASE WHEN COUNT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN 1 END) = 0 THEN 'NA' ELSE GROUP_CONCAT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN CONCAT(im.INTERFACE_NAME, ' (', COALESCE(im.Interface_IP_ICMP_Status, 'NA'), ')') END SEPARATOR ', ') END AS INTERFACE_NAMES,NM.NODE_STATUS FROM add_node add_node JOIN node_monitoring NM ON add_node.DEVICE_IP = NM.NODE_IP LEFT JOIN interface_monitoring im ON im.NODE_IP = add_node.DEVICE_IP AND im.MONITORING_PARAM = 'Yes' WHERE add_node.MONITORING_PARAM = 'Yes' AND "
								+ userScopeData + " GROUP BY add_node.DEVICE_IP");
				List<?> dataList = q.list();
				array1 = new JSONArray();

				for (int i = 0; i < dataList.size(); i++) {
					// array = new JSONArray();
					jsonObject = new JSONObject();
					srno++;
					Object[] row = (Object[]) dataList.get(i);
					jsonObject.put("srno", srno);
					jsonObject.put("deviceip", row[0]);
					jsonObject.put("devicename", row[1]);
					jsonObject.put("location", row[2]);
					jsonObject.put("company", row[3]);
					String interfacenames = row[4].toString();
//					System.out.println("int name :" + interfacenames);
					String interfacetoput = "";
					if (!interfacenames.equalsIgnoreCase("NA")) {
//						int token = 0;
						String[] parts = interfacenames.split(",");
//						System.out.println("inside");

						// Trim whitespace and print each part
						for (String part : parts) {
//							token++;
//							System.out.println(part.trim());
							String intrname = part.trim();
//							System.out.println("inside " + intrname);

							Pattern pattern = Pattern.compile("\\((.*?)\\)");
							Matcher matcher = pattern.matcher(intrname);
							String bracketValue = "NA";
							if (matcher.find()) {

								bracketValue = matcher.group(1).trim();
								System.out.println("inside " + bracketValue);
								System.out.println("Value inside brackets: " + bracketValue);
								intrname = intrname.replaceAll("\\s*\\(.*?\\)", "");
							} else {
								System.out.println("No brackets found.");
							}

//							if (row[5].toString().equalsIgnoreCase("down")) {
//								interfacetoput += intrname
//										+ "&nbsp;<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
//
//							} else {

							if (bracketValue.equalsIgnoreCase("up")) {
								interfacetoput += intrname
										+ "&nbsp;<small class=\"text-success mr-1\"> <i class=\"fas fa-arrow-up\"></i> Up </small><br>";
							} else if (bracketValue.equalsIgnoreCase("down")) {
								interfacetoput += intrname
										+ "&nbsp;<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
							} else if (bracketValue.equalsIgnoreCase("warning")) {
								interfacetoput += intrname
										+ "&nbsp;<small class=\"text-danger mr-1\"> Warning </small><br>";
							} else {
								interfacetoput += intrname + "&nbsp;(IP Not Assigned)<br>";
							}

//							}

						}

					} else {
						interfacetoput = "NA";
					}

					jsonObject.put("InterfaceName", interfacetoput);

					array1.put(jsonObject);
				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		} else {

			try {
//				Query q = getSession().createQuery(
//						"SELECT add_node.DEVICE_IP, add_node.DEVICE_NAME , add_node.LOCATION, add_node.COMPANY\r\n"
//								+ "FROM AddNodeModel add_node, NodeMonitoringModel NM\r\n"
//								+ "WHERE add_node.DEVICE_IP = NM.NODE_IP AND NM.NODE_STATUS = :deviceStatus AND add_node.MONITORING_PARAM = 'Yes' AND "
//								+ userScopeData);
				Query q = getSession().createSQLQuery(
						"SELECT add_node.DEVICE_IP, add_node.DEVICE_NAME, add_node.LOCATION, add_node.COMPANY, CASE WHEN COUNT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN 1 END) = 0 THEN 'NA' ELSE GROUP_CONCAT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN CONCAT(im.INTERFACE_NAME, ' (', COALESCE(im.Interface_IP_ICMP_Status, 'NA'), ')') END SEPARATOR ', ') END AS INTERFACE_NAMES FROM add_node add_node JOIN node_monitoring NM ON add_node.DEVICE_IP = NM.NODE_IP LEFT JOIN interface_monitoring im ON im.NODE_IP = add_node.DEVICE_IP AND im.MONITORING_PARAM = 'Yes' WHERE add_node.MONITORING_PARAM = 'Yes' AND NM.NODE_STATUS = :deviceStatus AND "
								+ userScopeData + " GROUP BY add_node.DEVICE_IP");

				q.setParameter("deviceStatus", deviceInfo);
				List<?> dataList = q.list();
				array1 = new JSONArray();

				for (int i = 0; i < dataList.size(); i++) {
					// array = new JSONArray();
					jsonObject = new JSONObject();
					srno++;
					Object[] row = (Object[]) dataList.get(i);
					jsonObject.put("srno", srno);
					jsonObject.put("deviceip", row[0]);
					jsonObject.put("devicename", row[1]);
					jsonObject.put("location", row[2]);
					jsonObject.put("company", row[3]);

					String interfacenames = row[4].toString();
//					System.out.println("int name :" + interfacenames);
					String interfacetoput = "";
					if (!interfacenames.equalsIgnoreCase("NA")) {
//						int token = 0;
						String[] parts = interfacenames.split(",");
//						System.out.println("inside");

						// Trim whitespace and print each part
						for (String part : parts) {
//							token++;
//							System.out.println(part.trim());
							String intrname = part.trim();
//							System.out.println("inside " + intrname);

							Pattern pattern = Pattern.compile("\\((.*?)\\)");
							Matcher matcher = pattern.matcher(intrname);
							String bracketValue = "NA";
							if (matcher.find()) {

								bracketValue = matcher.group(1).trim();
								System.out.println("inside " + bracketValue);
								System.out.println("Value inside brackets: " + bracketValue);
								intrname = intrname.replaceAll("\\s*\\(.*?\\)", "");
							} else {
								System.out.println("No brackets found.");
							}

//							if (deviceInfo.trim().equalsIgnoreCase("down")) {
//
//								interfacetoput += intrname
//										+ "&nbsp;<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
//
//							} else {

							if (bracketValue.equalsIgnoreCase("up")) {
								interfacetoput += intrname
										+ "&nbsp;<small class=\"text-success mr-1\"> <i class=\"fas fa-arrow-up\"></i> Up </small><br>";
							} else if (bracketValue.equalsIgnoreCase("down")) {
								interfacetoput += intrname
										+ "&nbsp;<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
							} else if (bracketValue.equalsIgnoreCase("warning")) {
								interfacetoput += intrname
										+ "&nbsp;<small class=\"text-danger mr-1\"> Warning </small><br>";
							} else {
								interfacetoput += intrname + "&nbsp;(IP Not Assigned)<br>";
							}

//							}

						}

					} else {
						interfacetoput = "NA";
					}

					jsonObject.put("InterfaceName", interfacetoput);

					array1.put(jsonObject);
				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		System.out.println("Device Status Array:" + array1);
		return array1;
	}

	/* Get Link Info */
	public JSONArray getLinkInfo(String linkInfo, String userScopeData) {

		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;
		int srno = 0;

		if (linkInfo.equals("total")) {
			try {
				Query q = getSession().createQuery(
						"SELECT IFMON.NODE_IP, IFMON.INTERFACE_NAME, add_node.LOCATION, add_node.COMPANY\r\n"
								+ "FROM AddNodeModel add_node, InterfaceMonitoring IFMON\r\n"
								+ "WHERE add_node.DEVICE_IP = IFMON.NODE_IP AND IFMON.MONITORING_PARAM = 'Yes' AND "
								+ userScopeData);
				List<?> dataList = q.list();
				array1 = new JSONArray();

				for (int i = 0; i < dataList.size(); i++) {
					// array = new JSONArray();
					jsonObject = new JSONObject();
					srno++;
					Object[] row = (Object[]) dataList.get(i);
					jsonObject.put("srno", srno);
					jsonObject.put("nodeip", row[0]);
					jsonObject.put("interfacename", row[1]);
					jsonObject.put("location", row[2]);
					jsonObject.put("company", row[3]);

					array1.put(jsonObject);
				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		} else {

			try {
				Query q = getSession().createQuery(
						"SELECT IFMON.NODE_IP, IFMON.INTERFACE_NAME, add_node.LOCATION, add_node.COMPANY\r\n"
								+ "FROM AddNodeModel add_node, InterfaceMonitoring IFMON\r\n"
								+ "WHERE add_node.DEVICE_IP = IFMON.NODE_IP AND IFMON.OPER_STATUS = :linkStatus AND IFMON.MONITORING_PARAM = 'Yes' AND "
								+ userScopeData);
				q.setParameter("linkStatus", linkInfo);
				List<?> dataList = q.list();
				array1 = new JSONArray();

				for (int i = 0; i < dataList.size(); i++) {
					// array = new JSONArray();
					jsonObject = new JSONObject();
					srno++;
					Object[] row = (Object[]) dataList.get(i);
					jsonObject.put("srno", srno);
					jsonObject.put("nodeip", row[0]);
					jsonObject.put("interfacename", row[1]);
					jsonObject.put("location", row[2]);
					jsonObject.put("company", row[3]);

					array1.put(jsonObject);
				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		System.out.println("Link Status Array:" + array1.length());
		return array1;
	}

	/* Top CPU Summary Listing */
	public JSONArray topCPUSummary(String userScopeData) {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			String sql = "SELECT * FROM Customers LIMIT 3;";

			String db_type = environment.getRequiredProperty("DATABASE_TYPE");
			if (db_type.equalsIgnoreCase("mysql")) {
				sql = "SELECT intmon.NODE_IP, intmon.INTERFACE_NAME, intmon.STATUS_TIMESTAMP\r\n"
						+ "FROM interface_monitoring intmon, add_node add_node\r\n"
						+ "WHERE intmon.OPER_STATUS = 'down' AND intmon.NODE_IP = add_node.DEVICE_IP AND "
						+ userScopeData;
			} else if (db_type.equalsIgnoreCase("mssql")) {

			} else if (db_type.equalsIgnoreCase("oracle")) {

			}

			SQLQuery query = getSession().createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			List results = query.list();
			array1 = new JSONArray();

			for (Object object : results) {
				Map row = (Map) object;

				// System.out.println("Node IP: " + row.get("NODE_IP"));
				// System.out.println("Interface Name: " + row.get("INTERFACE_NAME"));
				// System.out.println("Branch: " + row.get("LOCATION"));
				// System.out.println("Customer: " + row.get("COMPANY"));
				// System.out.println("Event Timestamp: " + row.get("STATUS_TIMESTAMP"));
				String downDate = row.get("STATUS_TIMESTAMP").toString();

				String downSince = "NA";

				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
					Date parsedDate = dateFormat.parse(downDate);
					Timestamp dbtimestamp = new Timestamp(parsedDate.getTime());
					// System.out.println("DB TimeStamp:" + dbtimestamp);
					Timestamp currenttimestamp = new Timestamp(new Date().getTime());
					// System.out.println("Current Timestamp:" + currenttimestamp);
					long milliseconds = currenttimestamp.getTime() - dbtimestamp.getTime();

					long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
					long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
							- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
					long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
					long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

					downSince = days + " Days " + hours + " Hours " + minutes + " Minutes " + seconds + " Seconds";

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Exception in Parse Timestamp:" + e);
				}

				array = new JSONArray();
				array.put(row.get("NODE_IP"));
				array.put(row.get("INTERFACE_NAME"));
				array.put(row.get("LOCATION"));
				array.put(row.get("COMPANY"));
				if (!downSince.equalsIgnoreCase("NA")) {
					array.put(downSince);
				} else {
					array.put(row.get("STATUS_TIMESTAMP"));
				}
				array1.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Interface Info */
	public JSONArray interfaceInfo(String deviceIP, String intName, String userScopeData) {
		System.out.println("Method Called..");
		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {
			Query q = getSession().createQuery("SELECT \r\n"
					+ "  INFMON.NODE_IP, INFMON.INTERFACE_NAME, INFMON.ALIAS_NAME, INFMON.INTERFACE_TYPE,\r\n"
					+ "  INFMON.INTERFACE_ID, INFMON.ADMIN_STATUS, INFMON.OPER_STATUS, COALESCE(INFMON.Interface_IP_Assign,'NA'),\r\n"
					+ "  INFMON.MTU, INFMON.CRC_ERROR, INFMON.PROCURED_BANDWIDTH, INFMON.IN_TRAFFIC,\r\n"
					+ "  INFMON.OUT_TRAFFIC, INFMON.INTERFACE_MACADDRESS, INFMON.INTERFACE_INPUT_ERROR,\r\n"
					+ "  INFMON.INTERFACE_OUTPUT_ERROR, INFMON.DISCARD_INPUT, INFMON.DISCARD_OUTPUT,\r\n"
					+ "  add_node.LOCATION, add_node.COMPANY,COALESCE(INFMON.Interface_IP_ICMP_Status,'NA') "
					+ "FROM \r\n" + "  InterfaceMonitoring AS INFMON\r\n" + "JOIN \r\n"
					+ "  AddNodeModel AS add_node \r\n" + "ON \r\n" + "  INFMON.NODE_IP = add_node.DEVICE_IP\r\n"
					+ "WHERE \r\n" + "  INFMON.NODE_IP = :nodeIP AND INFMON.INTERFACE_NAME = :interfaceName AND "
					+ userScopeData);
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);
			List<?> dataList = q.list();
			array1 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {

				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("node_ip", row[0]);
				jsonObject.put("int_name", row[1]);
				jsonObject.put("alias_name", row[2]);
				jsonObject.put("int_type", row[3]);
				jsonObject.put("int_id", row[4]);
				jsonObject.put("admin_status", row[5]);
				jsonObject.put("oper_status", row[6]);
				jsonObject.put("int_ip", row[7]);
				jsonObject.put("mtu", row[8]);
				jsonObject.put("crc", row[9]);
				jsonObject.put("procure_bw", row[10]);
				jsonObject.put("in_bw", row[11]);
				jsonObject.put("out_bw", row[12]);
				jsonObject.put("int_mac", row[13]);
				jsonObject.put("input_error", row[14]);
				jsonObject.put("output_error", row[15]);
				jsonObject.put("discard_input", row[16]);
				jsonObject.put("discard_output", row[17]);
				jsonObject.put("ICMPStatus", row[20]);

				array1.put(jsonObject);
			}
			System.out.println("Interface Info Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Interface Status History */
	public JSONArray interfaceStatusHistory(String deviceIP, String intName, String userScopeData) {
		System.out.println("Method Called..");
		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {
			Query q = getSession().createQuery("SELECT \r\n"
					+ "INFMON.INTERFACE_NAME, INFMON.INTERFACE_STATUS, INFMON.INTERFACE_STATUS_TYPE, INFMON.EVENT_TIMESTAMP\r\n"
					+ "FROM InterfaceStatusHistoryModel INFMON, AddNodeModel add_node \r\n"
					+ "WHERE INFMON.NODE_IP = :nodeIP AND INFMON.INTERFACE_NAME = :interfaceName AND INFMON.NODE_IP = add_node.DEVICE_IP AND "
					+ userScopeData);
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);
			q.setMaxResults(5);
			List<?> dataList = q.list();
			array1 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {

				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("int_name", row[0]);
				jsonObject.put("int_status", row[1]);
				jsonObject.put("int_status_type", row[2]);
				jsonObject.put("event_timestamp", row[3]);
				array1.put(jsonObject);
			}
			System.out.println("Interface History Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Interface CRC Log */
	public JSONArray interfaceCRCLog(String deviceIP, String intName, String userScopeData) {
		System.out.println("Method Called..");
		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT INFMON.INTERFACE_NAME, INFMON.OLD_CRC_VAL, INFMON.NEW_CRC_VAL, INFMON.EVENT_TIMESTAMP\r\n"
							+ "FROM InterfaceCRCHistory INFMON, AddNodeModel add_node\r\n"
							+ "WHERE INFMON.NODE_IP = add_node.DEVICE_IP AND INFMON.NODE_IP =:nodeIP AND INFMON.INTERFACE_NAME =:interfaceName AND "
							+ userScopeData);
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);
			q.setMaxResults(5);
			List<?> dataList = q.list();
			array1 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {

				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("int_name", row[0]);
				jsonObject.put("old_crc", row[1]);
				jsonObject.put("new_crc", row[2]);
				jsonObject.put("event_timestamp", row[3]);
				array1.put(jsonObject);
			}
			System.out.println("Interface CRC Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Interface Uptime Log */
	public JSONArray interfaceUptimeLog(String deviceIP, String intName, String userScopeData) {
		System.out.println("Method Called..");

		JSONArray array1 = null;
		JSONArray array2 = null;
		JSONArray array3 = null;
		JSONArray array4 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT INFMON.UPTIME_PERCENT, INFMON.DOWNTIME_PERCENT, DATE(INFMON.EVENT_TIMESTAMP)\r\n"
							+ "FROM InterfaceAvailablity INFMON, AddNodeModel add_node\r\n"
							+ "WHERE INFMON.NODE_IP =:nodeIP \r\n" + "AND INFMON.INTERFACE_NAME =:interfaceName\r\n "
							+ "AND INFMON.NODE_IP = add_node.DEVICE_IP \r\n" + "AND " + userScopeData
							+ " ORDER BY INFMON.EVENT_TIMESTAMP DESC");
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);
			q.setMaxResults(5);

			List<?> dataList = q.list();
			array1 = new JSONArray();
			array2 = new JSONArray();
			array3 = new JSONArray();
			array4 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {

				Object[] row = (Object[]) dataList.get(i);
				array2.put(row[0]);
				array3.put(row[1]);
				array4.put(row[2]);
			}
			jsonObject = new JSONObject();
			jsonObject.put("uptime", array2);
			jsonObject.put("downtime", array3);
			jsonObject.put("datetime", array4);
			array1.put(jsonObject);
			System.out.println("Interface Uptime Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Interface Bandwidth */
	public JSONArray interfaceBandwidthUtil(String deviceIP, String intName, String userScopeData) {

		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession()
					.createQuery("SELECT INFMON.IN_TRAFFIC, INFMON.OUT_TRAFFIC, INFMON.PROCURED_BANDWIDTH\r\n"
							+ "FROM InterfaceMonitoring INFMON, AddNodeModel add_node\r\n"
							+ "WHERE INFMON.NODE_IP = :nodeIP AND INFMON.INTERFACE_NAME = :interfaceName AND "
							+ userScopeData);
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);

			List<?> dataList = q.list();
			array1 = new JSONArray();
			for (int i = 0; i < dataList.size(); i++) {

				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("in_traffic", row[0]);
				jsonObject.put("out_traffic", row[1]);
				jsonObject.put("procured_bw", row[2]);
				array1.put(jsonObject);
			}

			System.out.println("Interface bandwidth Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	// Link and Jitter Latency
	public JSONArray getInterfaceLinkLatency(String deviceIP, String intName, String userScopeData) {

		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createSQLQuery("SELECT INFMON.LINK_LATENCY, INFMON.LINK_JITTER\r\n"
					+ "FROM interface_monitoring  INFMON, add_node  add_node\r\n"
					+ "WHERE INFMON.NODE_IP = :nodeIP AND INFMON.INTERFACE_NAME = :interfaceName AND " + userScopeData);
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);

			List<?> dataList = q.list();
			array1 = new JSONArray();
			for (int i = 0; i < dataList.size(); i++) {

				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("link_latency", row[0]);
				jsonObject.put("link_jitter", row[1]);
				array1.put(jsonObject);
			}

			System.out.println("Link Jitter and Latency Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Toptalker Link Summary Listing */
	public JSONArray topTalkerLinkSummaryList(String userScopeData) {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			String sql = "SELECT intmon.NODE_IP,intmon.INTERFACE_NAME, add_node.LOCATION, add_node.COMPANY  FROM interface_monitoring intmon, add_node add_node WHERE intmon.MONITORING_PARAM='Yes' AND intmon.NODE_IP=add_node.DEVICE_IP AND "
					+ userScopeData;

			String db_type = environment.getRequiredProperty("DATABASE_TYPE");
			if (db_type.equalsIgnoreCase("mysql")) {
				sql = "SELECT intmon.NODE_IP,intmon.INTERFACE_NAME, add_node.LOCATION, add_node.COMPANY  FROM interface_monitoring intmon, add_node add_node WHERE intmon.MONITORING_PARAM='Yes' AND intmon.NODE_IP=add_node.DEVICE_IP AND "
						+ userScopeData;
			} else if (db_type.equalsIgnoreCase("mssql")) {

			} else if (db_type.equalsIgnoreCase("oracle")) {

			}

			SQLQuery query = getSession().createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			List results = query.list();
			array1 = new JSONArray();

			for (Object object : results) {
				Map row = (Map) object;
				array = new JSONArray();
				array.put(row.get("NODE_IP"));
				// array.put(row.get("INTERFACE_NAME"));
				array.put("<a href=\"toptalkerinfo\">" + row.get("INTERFACE_NAME") + "</a>");
				// array.put("<a href=\"/NPMWebConsole/dashboard/toptalkerinfo\">" +
				// row.get("INTERFACE_NAME") + "</a>");
				array.put(row.get("LOCATION"));
				array.put(row.get("COMPANY"));
				array1.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	// Export Node Latency
	public List<LatencyHistoryReportBean> exportNodeLatency(String from_date, String to_date, String ip_address,
			String userScopeData) {

		String query = "select report.ID,report.NODE_IP,report.MIN_LATENCY,report.MAX_LATENCY,report.AVG_LATENCY,report.PACKET_LOSS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join LATENCY_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_address + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date
				+ "' LIMIT 1000";
		Query q = getSession().createSQLQuery(query);
		List<LatencyHistoryReportBean> dataList = new ArrayList<LatencyHistoryReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				LatencyHistoryReportBean bean = new LatencyHistoryReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setMIN_LATENCY(Double.parseDouble(a[2].toString()));
				bean.setMAX_LATENCY(Double.parseDouble(a[3].toString()));
				bean.setAVG_LATENCY(Double.parseDouble(a[4].toString()));
				bean.setPACKET_LOSS(Double.parseDouble(a[5].toString()));
				bean.setEVENT_TIMESTAMP(a[6].toString());
				bean.setDEVICE_NAME(a[7].toString());
				bean.setLOCATION(a[8].toString());
				bean.setDISTRICT(a[9].toString());
				bean.setSTATE(a[10].toString());
				bean.setZONE(a[11].toString());
				bean.setGROUP_NAME(a[12].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	// Top Talker Source IP Wise Chart and Table

	public JSONArray getTopTalkerChartFromDateToDate(String toDate, String fromDate, String userScopeData) {
		JSONArray finalArray = new JSONArray();
		try {
			Query query = null;
			query = getSession().createSQLQuery("SELECT\r\n" + " SUM(tt.DESTINATION_OCTECT) AS sum,\r\n"
					+ "    tt.SOURCE_IP\r\n" + "FROM\r\n" + " top_talker_log tt\r\n" + "JOIN\r\n"
					+ "    add_node add_node ON tt.SOURCE_IP = add_node.DEVICE_IP\r\n" + "WHERE\r\n"
					+ "    tt.DATE_TIME >= :fromDate\r\n" + "    AND tt.DATE_TIME <= :toDate\r\n" + "    AND "
					+ userScopeData + "\r\n" + "GROUP BY\r\n" + "tt.SOURCE_IP\r\n" + "ORDER BY\r\n" + "    sum DESC");
			List<Object[]> dataList = query.list();
			int sr = 0;

			for (Object[] obj : dataList) {
				sr++;
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("name", obj[1]);
				jsonObj.put("y", obj[0]);

				String hrSize = null;
				String s = obj[0].toString();

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");
				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}
				jsonObj.put("z", hrSize);

				finalArray.put(jsonObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

	public JSONArray getTopTalkerChartAllData(String userScopeData) {
		JSONArray finalArray = new JSONArray();
		try {
			Query query = null;
			query = getSession().createSQLQuery("SELECT SUM(ttl.DESTINATION_OCTECT) AS sum, ttl.SOURCE_IP \r\n"
					+ "FROM top_talker_log ttl\r\n" + "JOIN add_node add_node ON ttl.SOURCE_IP = add_node.DEVICE_IP\r\n"
					+ "WHERE " + userScopeData + "\r\n" + "GROUP BY ttl.SOURCE_IP\r\n" + "ORDER BY sum DESC LIMIT 10");
			List<Object[]> dataList = query.list();
			int sr = 0;

			for (Object[] obj : dataList) {
				sr++;
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("name", obj[1]);
				jsonObj.put("y", obj[0]);

				String hrSize = null;
				String s = obj[0].toString();
//						 double size = Double.parseDouble((String) obj[0]);
//						double size = Double.parseDouble(s);
//						double b = size;
//						double k = size / 1024.0;
//						double m = ((size / 1024.0) / 1024.0);
//						double g = (((size / 1024.0) / 1024.0) / 1024.0);
//						double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);
				//
//						DecimalFormat dec = new DecimalFormat("0.00");
				//
//						if (t > 1) {
//							hrSize = dec.format(t).concat(" TB");
//						} else if (g > 1) {
//							hrSize = dec.format(g).concat(" GB");
//						} else if (m > 1) {
//							hrSize = dec.format(m).concat(" MB");
//						} else if (k > 1) {
//							hrSize = dec.format(k).concat(" KB");
//						} else {
//							hrSize = dec.format(b).concat(" Bytes");
//						}

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");
//						LongFormat long1 = new LongFormat(0);
//						if (t > 1) {
//							hrSize = dec.format(t).concat(" TB");
//						} else if (g > 1) {
//							hrSize = dec.format(g).concat(" GB");
//						} else if (m > 1) {
//							hrSize = dec.format(m).concat(" MB");
//						} else if (k > 1) {
//							hrSize = dec.format(k).concat(" KB");
//						} else {
//							hrSize = dec.format(b).concat(" Bytes");
//						}
				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}
				jsonObj.put("z", hrSize);

				finalArray.put(jsonObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

	public JSONArray getTopTalkerSumOfDeviceListAlldata(String userScopeData) {
		JSONArray finalArray = new JSONArray();
		try {
			Query query = null;

			query = getSession().createSQLQuery(
					"SELECT ttl.SOURCE_IP, ttl.DESTINATION_IP, SUM(ttl.DESTINATION_OCTECT) AS sum, add_node.location, add_node.company\r\n"
							+ "FROM top_talker_log ttl\r\n"
							+ "JOIN add_node add_node ON ttl.SOURCE_IP = add_node.DEVICE_IP\r\n" + "where "
							+ userScopeData + "\r\n"
							+ "GROUP BY ttl.SOURCE_IP, ttl.DESTINATION_IP, add_node.location, add_node.company\r\n"
							+ "ORDER BY sum DESC LIMIT 10");

			List<Object[]> dataList = query.list();
			int sr = 0;

			for (Object[] obj : dataList) {
				sr++;
				JSONArray tempArray = new JSONArray();

				tempArray.put(sr);
				tempArray.put((obj[0]) == null ? "NA" : obj[0]);
				tempArray.put((obj[1]) == null ? "NA" : obj[1]);

				String hrSize = null;
				String s = obj[2].toString();

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");

				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}
				tempArray.put(hrSize);

				finalArray.put(tempArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

	public JSONArray getTopTalkerSumOfDeviceListFromDateToDate(String toDate, String fromDate, String userScopeData) {
		JSONArray finalArray = new JSONArray();

		try {
			Query query = null;

			query = getSession().createSQLQuery(
					"SELECT ttl.SOURCE_IP, ttl.DESTINATION_IP, SUM(ttl.DESTINATION_OCTECT) AS sum, add_node.location, add_node.company\r\n"
							+ "FROM top_talker_log ttl\r\n"
							+ "JOIN add_node add_node ON ttl.SOURCE_IP = add_node.DEVICE_IP\r\n"
							+ "WHERE ttl.DATE_TIME >= :fromDate AND ttl.DATE_TIME <= :toDate\r\n" + "AND "
							+ userScopeData + "\r\n" + "GROUP BY ttl.SOURCE_IP, ttl.DESTINATION_IP\r\n"
							+ "ORDER BY sum DESC");

			List<Object[]> dataList = query.list();
			int sr = 0;

			for (Object[] obj : dataList) {
				sr++;
				JSONArray tempArray = new JSONArray();

				tempArray.put(sr);
				tempArray.put((obj[0]) == null ? "NA" : obj[0]);
				tempArray.put((obj[1]) == null ? "NA" : obj[1]);

				String hrSize = null;
				String s = obj[2].toString();
//					 double size = Double.parseDouble((String) obj[0]);
//					double size = Double.parseDouble(s);
				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");
//					LongFormat long1 = new LongFormat(0);
//					if (t > 1) {
//						hrSize = dec.format(t).concat(" TB");
//					} else if (g > 1) {
//						hrSize = dec.format(g).concat(" GB");
//					} else if (m > 1) {
//						hrSize = dec.format(m).concat(" MB");
//					} else if (k > 1) {
//						hrSize = dec.format(k).concat(" KB");
//					} else {
//						hrSize = dec.format(b).concat(" Bytes");
//					}
				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}

				tempArray.put(hrSize);
				tempArray.put(size);
				finalArray.put(tempArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

	// End Top Talker Source IP Wise Chart and Table

	// Top Talker Connection Wise Chart and Table
	public JSONArray getsumOfDeviceDetailsListFromDateToDate(String toDate, String fromDate, String userScopeData) {
		JSONArray finalArray = new JSONArray();
		HashMap<Integer, String> protocolmap = null;
		protocolmap = new HashMap<Integer, String>();
		protocolmap.put(6, "TCP");
		protocolmap.put(17, "UDP");
		protocolmap.put(1, "ICMP");
		protocolmap.put(2, "IGMP");

		try {
			Query query = null;
			query = getSession().createSQLQuery(
					"SELECT ttl.SOURCE_IP, ttl.DESTINATION_IP, ttl.SOURCE_PORT, ttl.DESTINATION_PORT, ttl.PROTOCOL, SUM(ttl.DESTINATION_OCTECT) AS sum, add_node.location, add_node.company\r\n"
							+ "FROM top_talker_log ttl\r\n"
							+ "JOIN add_node add_node ON ttl.SOURCE_IP = add_node.DEVICE_IP\r\n"
							+ "WHERE ttl.DATE_TIME >= :fromDate AND ttl.DATE_TIME <= :toDate\r\n" + "AND "
							+ userScopeData + "\r\n"
							+ "GROUP BY ttl.SOURCE_IP, ttl.DESTINATION_IP, ttl.SOURCE_PORT, ttl.DESTINATION_PORT, ttl.PROTOCOL, add_node.location, add_node.company\r\n"
							+ "ORDER BY sum DESC");

			List<Object[]> dataList = query.list();
			int sr = 0;

			for (Object[] obj : dataList) {
				sr++;
				JSONArray tempArray = new JSONArray();
				String protocol = "NA";
				String protobg = obj[4].toString();
				try {
					int port = Integer.parseInt(protobg);
					protocol = protocolmap.get(port);
					if (protocol == null) {
						protocol = obj[4].toString();
					}
				} catch (Exception e) {
					protocol = obj[4].toString();
				}

				tempArray.put(sr);
				tempArray.put((obj[0]) == null ? "NA" : obj[0]);
				tempArray.put((obj[1]) == null ? "NA" : obj[1]);
				tempArray.put((obj[2]) == null ? "NA" : obj[2]);
				tempArray.put((obj[3]) == null ? "NA" : obj[3]);
				tempArray.put(protocol == null ? "NA" : protocol);
				String hrSize = null;
				String s = obj[5].toString();
//					 double size = Double.parseDouble((String) obj[0]);
//					double size = Double.parseDouble(s);
//					double b = size;
//					double k = size / 1024.0;
//					double m = ((size / 1024.0) / 1024.0);
//					double g = (((size / 1024.0) / 1024.0) / 1024.0);
//					double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);
				//
//					DecimalFormat dec = new DecimalFormat("0.00");
				//
//					if (t > 1) {
//						hrSize = dec.format(t).concat(" TB");
//					} else if (g > 1) {
//						hrSize = dec.format(g).concat(" GB");
//					} else if (m > 1) {
//						hrSize = dec.format(m).concat(" MB");
//					} else if (k > 1) {
//						hrSize = dec.format(k).concat(" KB");
//					} else {
//						hrSize = dec.format(b).concat(" Bytes");
//					}

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");
//					LongFormat long1 = new LongFormat(0);
//					if (t > 1) {
//						hrSize = dec.format(t).concat(" TB");
//					} else if (g > 1) {
//						hrSize = dec.format(g).concat(" GB");
//					} else if (m > 1) {
//						hrSize = dec.format(m).concat(" MB");
//					} else if (k > 1) {
//						hrSize = dec.format(k).concat(" KB");
//					} else {
//						hrSize = dec.format(b).concat(" Bytes");
//					}
				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}

				tempArray.put(hrSize);
				tempArray.put(size);

				finalArray.put(tempArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

	public JSONArray getsumOfDeviceDetailsListAllData(String userScopeData) {
		JSONArray finalArray = new JSONArray();
		HashMap<Integer, String> protocolmap = null;
		protocolmap = new HashMap<Integer, String>();
		protocolmap.put(6, "TCP");
		protocolmap.put(17, "UDP");
		protocolmap.put(1, "ICMP");
		protocolmap.put(2, "IGMP");
		try {
			Query query = null;
			query = getSession().createSQLQuery(
					"SELECT ttl.SOURCE_IP, ttl.DESTINATION_IP, ttl.SOURCE_PORT, ttl.DESTINATION_PORT, ttl.PROTOCOL, SUM(ttl.DESTINATION_OCTECT) AS sum, add_node.location, add_node.company\r\n"
							+ "FROM top_talker_log ttl\r\n"
							+ "JOIN add_node add_node ON ttl.SOURCE_IP = add_node.DEVICE_IP\r\n" + "WHERE "
							+ userScopeData + "\r\n"
							+ "GROUP BY ttl.SOURCE_IP, ttl.DESTINATION_IP, ttl.SOURCE_PORT, ttl.DESTINATION_PORT, ttl.PROTOCOL, add_node.location, add_node.company\r\n"
							+ "ORDER BY sum DESC LIMIT 10");

			List<Object[]> dataList = query.list();
			int sr = 0;

			for (Object[] obj : dataList) {
				sr++;
				JSONArray tempArray = new JSONArray();
				String protocol = "NA";
				String protobg = obj[4].toString();
				try {
					int port = Integer.parseInt(protobg);
					protocol = protocolmap.get(port);
					if (protocol == null) {
						protocol = obj[4].toString();
					}
				} catch (Exception e) {
					protocol = obj[4].toString();
				}
				tempArray.put(sr);
				tempArray.put((obj[0]) == null ? "NA" : obj[0]);
				tempArray.put((obj[1]) == null ? "NA" : obj[1]);
				tempArray.put((obj[2]) == null ? "NA" : obj[2]);
				tempArray.put((obj[3]) == null ? "NA" : obj[3]);
				tempArray.put(protocol == null ? "NA" : protocol);
				String hrSize = null;
				String s = obj[5].toString();
//						 double size = Double.parseDouble((String) obj[0]);
//						double size = Double.parseDouble(s);
//						double b = size;
//						double k = size / 1024.0;
//						double m = ((size / 1024.0) / 1024.0);
//						double g = (((size / 1024.0) / 1024.0) / 1024.0);
//						double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);
				//
//						DecimalFormat dec = new DecimalFormat("0.00");
				//
//						if (t > 1) {
//							hrSize = dec.format(t).concat(" TB");
//						} else if (g > 1) {
//							hrSize = dec.format(g).concat(" GB");
//						} else if (m > 1) {
//							hrSize = dec.format(m).concat(" MB");
//						} else if (k > 1) {
//							hrSize = dec.format(k).concat(" KB");
//						} else {
//							hrSize = dec.format(b).concat(" Bytes");
//						}

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");
//						LongFormat long1 = new LongFormat(0);
//						if (t > 1) {
//							hrSize = dec.format(t).concat(" TB");
//						} else if (g > 1) {
//							hrSize = dec.format(g).concat(" GB");
//						} else if (m > 1) {
//							hrSize = dec.format(m).concat(" MB");
//						} else if (k > 1) {
//							hrSize = dec.format(k).concat(" KB");
//						} else {
//							hrSize = dec.format(b).concat(" Bytes");
//						}
				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}
				tempArray.put(hrSize);

//						tempArray.put((obj[5]) == null ? "NA" : obj[5]);

				finalArray.put(tempArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

	// End Top Talker Connection Wise Chart and Table

	// Top Talker Protocol Wise Chart and Table
	public JSONArray getTopProtocolChartAllData(String userScopeData) {
		JSONArray finalArray = new JSONArray();
		HashMap<Integer, String> protocolmap = null;
		try {

//					Query protocolQuery = getSession().createQuery("from PROTOCOL_LIST");
//					List<PROTOCOL_LIST> list = protocolQuery.list();
//					for(PROTOCOL_LIST protocol : list) {

			protocolmap = new HashMap<Integer, String>();
//						protocolmap.put(protocol.getPORT_NO(), protocol.getPORT_PROTOCOL());
			protocolmap.put(6, "TCP");
			protocolmap.put(17, "UDP");
//					}

			Query query = null;
			query = getSession().createSQLQuery(
					"SELECT SUM(ttl.DESTINATION_OCTECT) AS sum, ttl.protocol, add_node.location, add_node.company\r\n"
							+ "FROM top_talker_log ttl\r\n"
							+ "JOIN add_node add_node ON ttl.SOURCE_IP = add_node.DEVICE_IP\r\n" + "WHERE "
							+ userScopeData + "\r\n" + "GROUP BY ttl.protocol, add_node.location, add_node.company\r\n"
							+ "ORDER BY sum DESC LIMIT 10");
//							"select sum(CAST(t.DESTINATION_OCTECT AS BIGINT)),t.protocol,p.PORT_PROTOCOL from TOP_TALKER_LOG t left JOIN PROTOCOL_LIST p ON p.PORT_NO = t.PROTOCOL where t.DEVICE_IP='"
//									+ ipAddress + "' and t.DATE_TIME>='" + fromDate + "' and t.DATE_TIME<='" + toDate
//									+ "'  group by t.PROTOCOL,p.PORT_PROTOCOL");
//							"select sum(t.CAST(DESTINATION_OCTECT AS BIGINT)),t.protocol,p.PORT_PROTOCOL from TOP_TALKER_LOG t left JOIN PROTOCOL_LIST p ON p.PORT_NO = t.PROTOCOL  where t.DEVICE_IP='"
//									+ ipAddress + "' and t.DATE_TIME>='" + fromDate + "' and t.DATE_TIME<='" + toDate
//									+ "'  group by t.protocol,p.PORT_PROTOCOL");
			List<Object[]> dataList = query.list();
			int sr = 0;

			for (Object[] obj : dataList) {
				sr++;
				JSONObject jsonObj = new JSONObject();
//						jsonObj.put("name", obj[1]);
				jsonObj.put("y", obj[0]);

				String hrSize = null;
				String s = obj[0].toString();
//						 double size = Double.parseDouble((String) obj[0]);
//						double size = Double.parseDouble(s);
//						double b = size;
//						double k = size / 1024.0;
//						double m = ((size / 1024.0) / 1024.0);
//						double g = (((size / 1024.0) / 1024.0) / 1024.0);
//						double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);
				//
//						DecimalFormat dec = new DecimalFormat("0.00");
				//
//						if (t > 1) {
//							hrSize = dec.format(t).concat(" TB");
//						} else if (g > 1) {
//							hrSize = dec.format(g).concat(" GB");
//						} else if (m > 1) {
//							hrSize = dec.format(m).concat(" MB");
//						} else if (k > 1) {
//							hrSize = dec.format(k).concat(" KB");
//						} else {
//							hrSize = dec.format(b).concat(" Bytes");
//						}

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");
//						LongFormat long1 = new LongFormat(0);
//						if (t > 1) {
//							hrSize = dec.format(t).concat(" TB");
//						} else if (g > 1) {
//							hrSize = dec.format(g).concat(" GB");
//						} else if (m > 1) {
//							hrSize = dec.format(m).concat(" MB");
//						} else if (k > 1) {
//							hrSize = dec.format(k).concat(" KB");
//						} else {
//							hrSize = dec.format(b).concat(" Bytes");
//						}
				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}
				jsonObj.put("z", hrSize);
//						String protocolName = "";
////						protocolName = protocolmap.get(obj[1].toString());

				String protocol = "NA";
				String protobg = obj[1].toString();

				try {
					int port = Integer.parseInt(protobg);
//							int port = Integer.parseInt(protobg);
					protocol = protocolmap.get(port);
//							
					if (protocol == null) {
						protocol = obj[1].toString();
					}
					System.out.println("protocolCharttt : " + protocol);
				} catch (Exception e) {
					protocol = obj[1].toString();
					System.out.println("Exception:" + e);
				}

				jsonObj.put("name", protocol);
				finalArray.put(jsonObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

	public JSONArray getTopProtocolSumOfDeviceListAlldata(String userScopeData) {

		HashMap<Integer, String> protocolmap = null;
		protocolmap = new HashMap<Integer, String>();

//					protocolmap.put(protocol.getPORT_NO(), protocol.getPORT_PROTOCOL());
		protocolmap.put(6, "TCP");
		protocolmap.put(17, "UDP");
		protocolmap.put(1, "ICMP");
		protocolmap.put(2, "IGMP");

//				}

		JSONArray finalArray = new JSONArray();
		try {
			Query query = null;

			query = getSession().createSQLQuery(
					"SELECT SUM(ttl.DESTINATION_OCTECT) AS sum, ttl.protocol, add_node.location, add_node.company\r\n"
							+ "FROM top_talker_log ttl\r\n"
							+ "JOIN add_node add_node ON ttl.SOURCE_IP = add_node.DEVICE_IP\r\n" + "WHERE "
							+ userScopeData + "\r\n" + "GROUP BY ttl.protocol, add_node.location, add_node.company\r\n"
							+ "ORDER BY sum DESC LIMIT 10");

			List<Object[]> dataList = query.list();
			int sr = 0;

			for (Object[] obj : dataList) {
				sr++;
				JSONArray tempArray = new JSONArray();

				String protocol = "NA";
				String protobg = obj[1].toString();

				try {
					int port = Integer.parseInt(protobg);
//							int port = Integer.parseInt(protobg);
					protocol = protocolmap.get(port);
					if (protocol == null) {
						protocol = obj[1].toString();
					}
					// System.out.println("protocolSummary : " + protocol);
				} catch (Exception e) {
					protocol = obj[1].toString();
					System.out.println("Exception:" + e);
				}

				tempArray.put(sr);
				tempArray.put(protocol);

				// for converting
				String hrSize = null;
				String s = obj[0].toString();

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");

				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}
				tempArray.put(hrSize);
				finalArray.put(tempArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

	public JSONArray getTopProtocolChartFromDateToDate(String toDate, String fromDate, String userScopeData) {
		JSONArray finalArray = new JSONArray();
		HashMap<Integer, String> protocolmap = null;
		try {

//				Query protocolQuery = getSession().createQuery("from PROTOCOL_LIST");
//				List<PROTOCOL_LIST> list = protocolQuery.list();
//				for(PROTOCOL_LIST protocol : list) {

			protocolmap = new HashMap<Integer, String>();
//					protocolmap.put(protocol.getPORT_NO(), protocol.getPORT_PROTOCOL());
			protocolmap.put(6, "TCP");
			protocolmap.put(17, "UDP");
			protocolmap.put(1, "ICMP");
			protocolmap.put(2, "IGMP");
//				}                                               

			Query query = null;
			query = getSession().createSQLQuery(
					"SELECT SUM(ttl.DESTINATION_OCTECT) AS sum, ttl.protocol, add_node.location, add_node.company\r\n"
							+ "FROM top_talker_log ttl\r\n"
							+ "JOIN add_node add_node ON ttl.SOURCE_IP = add_node.DEVICE_IP\r\n"
							+ "WHERE ttl.DATE_TIME >= :fromDate AND ttl.DATE_TIME <= :toDate\r\n" + "AND "
							+ userScopeData + "\r\n" + "GROUP BY ttl.protocol, add_node.location, add_node.company\r\n"
							+ "ORDER BY sum DESC");
//						"select sum(CAST(t.DESTINATION_OCTECT AS BIGINT)),t.protocol,p.PORT_PROTOCOL from TOP_TALKER_LOG t left JOIN PROTOCOL_LIST p ON p.PORT_NO = t.PROTOCOL where t.DEVICE_IP='"
//								+ ipAddress + "' and t.DATE_TIME>='" + fromDate + "' and t.DATE_TIME<='" + toDate
//								+ "'  group by t.PROTOCOL,p.PORT_PROTOCOL");
//						"select sum(t.CAST(DESTINATION_OCTECT AS BIGINT)),t.protocol,p.PORT_PROTOCOL from TOP_TALKER_LOG t left JOIN PROTOCOL_LIST p ON p.PORT_NO = t.PROTOCOL  where t.DEVICE_IP='"
//								+ ipAddress + "' and t.DATE_TIME>='" + fromDate + "' and t.DATE_TIME<='" + toDate
//								+ "'  group by t.protocol,p.PORT_PROTOCOL");
			List<Object[]> dataList = query.list();
			int sr = 0;

			for (Object[] obj : dataList) {
				sr++;
				JSONObject jsonObj = new JSONObject();
//					jsonObj.put("name", obj[1]);
				jsonObj.put("y", obj[0]);

				String hrSize = null;
				String s = obj[0].toString();
//					 double size = Double.parseDouble((String) obj[0]);
//					double size = Double.parseDouble(s);
//					double b = size;
//					double k = size / 1024.0;
//					double m = ((size / 1024.0) / 1024.0);
//					double g = (((size / 1024.0) / 1024.0) / 1024.0);
//					double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);
				//
//					DecimalFormat dec = new DecimalFormat("0.00");
				//
//					if (t > 1) {
//						hrSize = dec.format(t).concat(" TB");
//					} else if (g > 1) {
//						hrSize = dec.format(g).concat(" GB");
//					} else if (m > 1) {
//						hrSize = dec.format(m).concat(" MB");
//					} else if (k > 1) {
//						hrSize = dec.format(k).concat(" KB");
//					} else {
//						hrSize = dec.format(b).concat(" Bytes");
//					}
				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");
//					LongFormat long1 = new LongFormat(0);
//					if (t > 1) {
//						hrSize = dec.format(t).concat(" TB");
//					} else if (g > 1) {
//						hrSize = dec.format(g).concat(" GB");
//					} else if (m > 1) {
//						hrSize = dec.format(m).concat(" MB");
//					} else if (k > 1) {
//						hrSize = dec.format(k).concat(" KB");
//					} else {
//						hrSize = dec.format(b).concat(" Bytes");
//					}
				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}
				jsonObj.put("z", hrSize);
//					String protocolName = "";
////					protocolName = protocolmap.get(obj[1].toString());

				String protocol = "NA";
				String protobg = obj[1].toString();

				try {
					int port = Integer.parseInt(protobg);
//						int port = Integer.parseInt(protobg);
					protocol = protocolmap.get(port);
//						
					if (protocol == null) {
						protocol = obj[1].toString();
					}
					System.out.println("protocolCharttt : " + protocol);
				} catch (Exception e) {
					protocol = obj[1].toString();
					System.out.println("Exception:" + e);
				}

				jsonObj.put("name", protocol);
				finalArray.put(jsonObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

	public JSONArray getTopProtocolSumOfDeviceListFromDateToDate(String toDate, String fromDate, String userScopeData) {

//			Query protocolQuery = getSession().createSQLQuery("from PROTOCOL_LIST");
//			List<PROTOCOL_LIST> list = protocolQuery.list();
//			for(PROTOCOL_LIST protocol : list) {
		HashMap<Integer, String> protocolmap = null;
		protocolmap = new HashMap<Integer, String>();

//				protocolmap.put(protocol.getPORT_NO(), protocol.getPORT_PROTOCOL());
		protocolmap.put(6, "TCP");
		protocolmap.put(17, "UDP");
		protocolmap.put(1, "ICMP");
		protocolmap.put(2, "IGMP");
//			}

		JSONArray finalArray = new JSONArray();
		try {
			Query query = null;

//				query = getSession().createSQLQuery(
//						"select sum(CAST(t.DESTINATION_OCTECT AS BIGINT)),t.protocol, from TOP_TALKER_LOG t left JOIN PROTOCOL_LIST p ON p.PORT_NO = t.PROTOCOL where t.DEVICE_IP='"
//								+ ipAddress + "' and t.DATE_TIME>='" + fromDate + "' and t.DATE_TIME<='" + toDate
//								+ "'  group by t.PROTOCOL,p.PORT_PROTOCOL");
			query = getSession().createSQLQuery(
					"SELECT SUM(ttl.DESTINATION_OCTECT) AS sum, ttl.protocol, add_node.location, add_node.company\r\n"
							+ "FROM top_talker_log ttl\r\n"
							+ "JOIN add_node add_node ON ttl.SOURCE_IP = add_node.DEVICE_IP\r\n"
							+ "WHERE ttl.DATE_TIME >= :fromDate AND ttl.DATE_TIME <= :toDate\r\n" + "AND "
							+ userScopeData + "\r\n" + "GROUP BY ttl.protocol, add_node.location, add_node.company\r\n"
							+ "ORDER BY sum DESC");

			List<Object[]> dataList = query.list();
			int sr = 0;

			for (Object[] obj : dataList) {
				sr++;
				JSONArray tempArray = new JSONArray();

				String protocol = "NA";
				String protobg = obj[1].toString();

				try {
					int port = Integer.parseInt(protobg);
//						int port = Integer.parseInt(protobg);
					protocol = protocolmap.get(port);
					if (protocol == null) {
						protocol = obj[1].toString();
					}
					System.out.println("protocolSummary : " + protocol);
				} catch (Exception e) {
					protocol = obj[1].toString();
					System.out.println("Exception:" + e);
				}

				tempArray.put(sr);
				tempArray.put(protocol);

				// for converting
				String hrSize = null;
				String s = obj[0].toString();

//					double size = Double.parseDouble(s);
//					double b = size;
//					double k = size / 1024.0;
//					double m = ((size / 1024.0) / 1024.0);
//					double g = (((size / 1024.0) / 1024.0) / 1024.0);
//					double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");
//					LongFormat long1 = new LongFormat(0);
//					if (t > 1) {
//						hrSize = dec.format(t).concat(" TB");
//					} else if (g > 1) {
//						hrSize = dec.format(g).concat(" GB");
//					} else if (m > 1) {
//						hrSize = dec.format(m).concat(" MB");
//					} else if (k > 1) {
//						hrSize = dec.format(k).concat(" KB");
//					} else {
//						hrSize = dec.format(b).concat(" Bytes");
//					}
				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}

				tempArray.put(hrSize);
				tempArray.put(size);
				finalArray.put(tempArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

	public long getAlertCount() {
		long alertCount = 0;
		try {
			long query1 = (Long) getSession()
					.createQuery("SELECT COUNT(*) FROM NodeMonitoringModel WHERE LATENCY_STATUS='High'").uniqueResult();
			long query2 = (Long) getSession()
					.createQuery("SELECT COUNT(*) FROM NodeHealthMonitoring WHERE CPU_STATUS='High'").uniqueResult();
			long query3 = (Long) getSession()
					.createQuery("SELECT COUNT(*) FROM InterfaceMonitoring WHERE BW_STATUS='High'").uniqueResult();
			long query4 = (Long) getSession()
					.createQuery("SELECT COUNT(*) FROM Auto_Topology_Pooling WHERE CHANGED_CONNECTIVITY='Yes'")
					.uniqueResult();
			long query5 = (Long) getSession()
					.createQuery("SELECT COUNT(*) FROM NodeHealthMonitoring WHERE MEMORY_STATUS='High'").uniqueResult();

			long query6 = (Long) getSession()
					.createQuery("SELECT COUNT(*) FROM DRIVE_THRESHOLD_LOG WHERE STATUS='High'").uniqueResult();
			alertCount = query1 + query2 + query3 + query4 + query5 + query6;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alertCount;
	}

	public JSONArray getAlertInfo() {
		JSONArray jsonArray = null;
		jsonArray = new JSONArray();
		JSONObject jsonRow = null;
		jsonRow = new JSONObject();
		int ip_id_value = 0;
		List<Object[]> data = new ArrayList<Object[]>();
		Query query = getSession()
				.createQuery("SELECT NODE_IP, LATENCY FROM NodeMonitoringModel WHERE LATENCY_STATUS='High'");
		data = query.list();

		if (data.isEmpty()) {

		} else {
			for (Object[] b : data) {
				ip_id_value = ip_id_value + 1;
				jsonRow = new JSONObject();
				System.out.println("b[0]=" + b[0]);
				System.out.println("b[1]=" + b[1]);
				jsonRow.put("id", b[0] + "~" + b[1]);
				jsonRow.put("parent", "Latency Threshold Crossed");
				jsonRow.put("text", b[0] + " : " + b[1] + "(ms)");
				jsonRow.put("icon", "../webtemplate/dist/img/red.png");

				jsonArray.put(jsonRow);
			}

		}
		if (ip_id_value == 0) {

		} else {
			jsonRow = null;
			jsonRow = new JSONObject();
			jsonRow.put("id", "Latency Threshold Crossed");
			jsonRow.put("parent", "#");
			jsonRow.put("text", "Latency Threshold Crossed" + " (" + ip_id_value + ")");
			jsonRow.put("icon", "../webtemplate/dist/img/red.png");
			jsonArray.put(jsonRow);
			ip_id_value = 0;
		}
		System.out.println("Latency threshold tree = " + jsonArray);
		return jsonArray;
	}

	public JSONArray getCpuAlertInfo() {
		JSONArray jsonArray = null;
		jsonArray = new JSONArray();
		JSONObject jsonRow = null;
		jsonRow = new JSONObject();
		int ip_id_value = 0;
		List<Object[]> data = new ArrayList<Object[]>();
		Query query = getSession()
				.createQuery("SELECT NODE_IP, CPU_UTILIZATION FROM NodeHealthMonitoring WHERE CPU_STATUS='High'");
		data = query.list();

		if (data.isEmpty()) {

		} else {
			for (Object[] b : data) {
				ip_id_value = ip_id_value + 1;
				jsonRow = new JSONObject();
				System.out.println("b[0]=" + b[0]);
				System.out.println("b[1]=" + b[1]);
				jsonRow.put("id", b[0] + "~" + b[1]);
				jsonRow.put("parent", "CPU Threshold Crossed");
				jsonRow.put("text", b[0] + " : " + b[1] + "(%)");
				jsonRow.put("icon", "../webtemplate/dist/img/red.png");

				jsonArray.put(jsonRow);
			}

		}
		if (ip_id_value == 0) {

		} else {
			jsonRow = null;
			jsonRow = new JSONObject();
			jsonRow.put("id", "CPU Threshold Crossed");
			jsonRow.put("parent", "#");
			jsonRow.put("text", "CPU Threshold Crossed" + " (" + ip_id_value + ")");
			jsonRow.put("icon", "../webtemplate/dist/img/red.png");
			jsonArray.put(jsonRow);
			ip_id_value = 0;
		}
		return jsonArray;
	}

	public JSONArray getMemoryAlertInfo() {
		JSONArray jsonArray = null;
		jsonArray = new JSONArray();
		JSONObject jsonRow = null;
		jsonRow = new JSONObject();
		int ip_id_value = 0;
		List<Object[]> data = new ArrayList<Object[]>();
		Query query = getSession()
				.createQuery("SELECT NODE_IP, MEMORY_UTILIZATION FROM NodeHealthMonitoring WHERE MEMORY_STATUS='High'");
		data = query.list();

		if (data.isEmpty()) {

		} else {
			for (Object[] b : data) {
				ip_id_value = ip_id_value + 1;
				jsonRow = new JSONObject();
				System.out.println("b[0]=" + b[0]);
				System.out.println("b[1]=" + b[1]);
				jsonRow.put("id", b[0] + "~" + b[1]);
				jsonRow.put("parent", "Memory Threshold Crossed");
				jsonRow.put("text", b[0] + " : " + b[1] + "(%)");
				jsonRow.put("icon", "../webtemplate/dist/img/red.png");

				jsonArray.put(jsonRow);
			}

		}
		if (ip_id_value == 0) {

		} else {
			jsonRow = null;
			jsonRow = new JSONObject();
			jsonRow.put("id", "Memory Threshold Crossed");
			jsonRow.put("parent", "#");
			jsonRow.put("text", "Memory Threshold Crossed" + " (" + ip_id_value + ")");
			jsonRow.put("icon", "../webtemplate/dist/img/red.png");
			jsonArray.put(jsonRow);
			ip_id_value = 0;
		}
		return jsonArray;
	}

	public JSONArray getDriveAlertInfo() {
		JSONArray jsonArray = null;
		jsonArray = new JSONArray();
		JSONObject jsonRow = null;
		jsonRow = new JSONObject();
		int ip_id_value = 0;
		List<Object[]> data = new ArrayList<Object[]>();
		Query query = getSession()
				.createQuery("SELECT DEVICE_IP, DRIVES_NAME FROM DRIVE_THRESHOLD_LOG WHERE STATUS='High'");
		data = query.list();

		if (data.isEmpty()) {

		} else {
			for (Object[] b : data) {
				ip_id_value = ip_id_value + 1;
				jsonRow = new JSONObject();
				System.out.println("b[0]=" + b[0]);
				System.out.println("b[1]=" + b[1]);
				jsonRow.put("id", b[0] + "~" + b[1]);
				jsonRow.put("parent", "Drive Threshold Crossed");
				jsonRow.put("text", b[0] + " : Drive(" + b[1] + ")");
				jsonRow.put("icon", "../webtemplate/dist/img/red.png");

				jsonArray.put(jsonRow);
			}

		}
		if (ip_id_value == 0) {

		} else {
			jsonRow = null;
			jsonRow = new JSONObject();
			jsonRow.put("id", "Drive Threshold Crossed");
			jsonRow.put("parent", "#");
			jsonRow.put("text", "Drive Threshold Crossed" + " (" + ip_id_value + ")");
			jsonRow.put("icon", "../webtemplate/dist/img/red.png");
			jsonArray.put(jsonRow);
			ip_id_value = 0;
		}
		System.out.println("Drive threshold tree = " + jsonArray);
		return jsonArray;
	}

	public JSONArray getBandwidthAlertInfo() {
		JSONArray jsonArray = null;
		jsonArray = new JSONArray();
		JSONObject jsonRow = null;
		jsonRow = new JSONObject();
		int ip_id_value = 0;
		List<Object[]> data = new ArrayList<Object[]>();
		Query query = getSession().createQuery(
				"SELECT NODE_IP, INTERFACE_NAME, OUT_TRAFFIC, IN_TRAFFIC FROM InterfaceMonitoring WHERE BW_STATUS='High'");
		data = query.list();

		if (data.isEmpty()) {

		} else {
			for (Object[] b : data) {
				ip_id_value = ip_id_value + 1;
				jsonRow = new JSONObject();
				System.out.println("b[0]=" + b[0]);
				System.out.println("b[1]=" + b[1]);
				jsonRow.put("id", b[0] + "~" + b[1]);
				jsonRow.put("parent", "Bandwidth Threshold Crossed");
				jsonRow.put("text", b[0] + " - " + b[1] + " - " + b[2] + "(Byte) - " + b[3] + "(Byte)");
				jsonRow.put("icon", "../webtemplate/dist/img/red.png");

				jsonArray.put(jsonRow);
			}

		}
		if (ip_id_value == 0) {

		} else {
			jsonRow = null;
			jsonRow = new JSONObject();
			jsonRow.put("id", "Bandwidth Threshold Crossed");
			jsonRow.put("parent", "#");
			jsonRow.put("text", "Bandwidth Threshold Crossed" + " (" + ip_id_value + ")");
			jsonRow.put("icon", "../webtemplate/dist/img/red.png");
			jsonArray.put(jsonRow);
			ip_id_value = 0;
		}
		return jsonArray;
	}

	public JSONArray getTopologyAlertInfo() {
		JSONArray jsonArray = null;
		jsonArray = new JSONArray();
		JSONObject jsonRow = null;
		jsonRow = new JSONObject();
		int ip_id_value = 0;
		List<Object[]> data = new ArrayList<Object[]>();
		List<String> queryText = getSession()
				.createQuery("SELECT POOLING_ID FROM Auto_Topology_Pooling WHERE CHANGED_CONNECTIVITY='Yes'").list();
		for (String poolingId : queryText) {
			Query query = getSession().createQuery(
					"select SRC_IP,SRC_PORT,PRV_DEST_PORT,CURRENT_DEST_PORT from Auto_Topology_Change_History where POOLING_ID='"
							+ poolingId + "'");
			data = query.list();
			if (data.isEmpty()) {

			} else {
				for (Object[] b : data) {
					ip_id_value = ip_id_value + 1;
					jsonRow = new JSONObject();
					System.out.println("b[0]=" + b[0]);
					System.out.println("b[1]=" + b[1]);
					jsonRow.put("id", b[0] + "~" + b[1]);
					jsonRow.put("parent", "Topology Changed for Pooling ID : " + poolingId);
					jsonRow.put("text", b[0] + " - " + b[1] + " - " + b[2] + " - " + b[3]);
					jsonRow.put("icon", "../webtemplate/dist/img/red.png");

					jsonArray.put(jsonRow);
				}

			}
			if (ip_id_value == 0) {

			} else {
				jsonRow = null;
				jsonRow = new JSONObject();
				jsonRow.put("id", "Topology Changed for Pooling ID : " + poolingId);
				jsonRow.put("parent", "#");
				jsonRow.put("text", "Topology Changed for Pooling ID : " + poolingId + " (" + ip_id_value + ")");
				jsonRow.put("icon", "../webtemplate/dist/img/red.png");
				jsonArray.put(jsonRow);
				ip_id_value = 0;
			}
		}
		return jsonArray;
	}

	public JSONArray getTopMemoryUtilization(String userScopeData) {
		JSONArray finalArray = new JSONArray();
		JSONObject jsonRow = null;
		int sr_no = 0;
		Query query = getSession()
				.createSQLQuery("select nhm.node_ip, nhm.node_name, nhm.memory_utilization, add_node.location\r\n"
						+ "from node_health_monitoring nhm, add_node add_node\r\n"
						+ "where nhm.NODE_IP = add_node.DEVICE_IP \r\n" + "AND " + userScopeData + "\r\n"
						+ "order by memory_utilization desc\r\n" + "LIMIT 10");
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			sr_no++;
			jsonRow = new JSONObject();
			jsonRow.put("sr_no", sr_no);
			jsonRow.put("pc_ip", obj[0]);
			jsonRow.put("pc_name", obj[1]);
			jsonRow.put("memory", obj[2]);
			jsonRow.put("pc_branch", obj[3]);
			finalArray.put(jsonRow);
		}

		return finalArray;
	}

	public JSONArray getTopCpuUtilization(String userScopeData) {
		JSONArray finalArray = new JSONArray();
		JSONObject jsonRow = null;
		int sr_no = 0;
		Query query = getSession().createSQLQuery(
				"SELECT nhm.NODE_IP, nhm.NODE_NAME, nhm.CPU_UTILIZATION\r\n" + "FROM node_health_monitoring nhm,\r\n"
						+ " add_node add_node WHERE nhm.NODE_IP = add_node.DEVICE_IP AND " + userScopeData + "\r\n"
						+ "ORDER BY nhm.CPU_UTILIZATION DESC\r\n" + "LIMIT 10;");
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			sr_no++;
			jsonRow = new JSONObject();
			jsonRow.put("sr_no", sr_no);
			jsonRow.put("pc_ip", obj[0]);
			jsonRow.put("pc_name", obj[1]);
			jsonRow.put("cpu", obj[2]);
			finalArray.put(jsonRow);
		}
		System.out.println("finalArray=" + finalArray + "hello");
		return finalArray;
	}

	public JSONObject getBwInUtilization(String userScopeData) {
		JSONArray finalArray = new JSONArray();
		JSONArray ipArray = null;
		ipArray = new JSONArray();
		JSONArray countArray = null;
		countArray = new JSONArray();
		JSONObject jsonRow1 = null;
		Query query = getSession().createSQLQuery("SELECT im.NODE_IP, im.INTERFACE_NAME, im.IN_TRAFFIC\r\n"
				+ "FROM interface_monitoring im, add_node add_node \r\n"
				+ "WHERE im.NODE_IP = add_node.DEVICE_IP AND im.MONITORING_PARAM = 'Yes'\r\n" + "AND " + userScopeData
				+ "\r\n" + "ORDER BY im.IN_TRAFFIC DESC\r\n" + "LIMIT 10;");
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			jsonRow1 = new JSONObject();
			ipArray.put(obj[0] + " [" + obj[1] + "]");
			countArray.put(obj[2]);
			jsonRow1.put("ip", ipArray);
			jsonRow1.put("count", countArray);
		}
		System.out.println(jsonRow1);
		return jsonRow1;
	}

	public JSONObject getBWOutUtilization(String userScopeData) {
		JSONArray finalArray = new JSONArray();
		JSONArray ipArray = null;
		ipArray = new JSONArray();
		JSONArray countArray = null;
		countArray = new JSONArray();
		JSONObject jsonRow1 = null;
		Query query = getSession().createSQLQuery("SELECT im.NODE_IP, im.INTERFACE_NAME, im.OUT_TRAFFIC\r\n"
				+ "FROM interface_monitoring im, add_node add_node \r\n"
				+ "WHERE im.NODE_IP = add_node.DEVICE_IP AND im.MONITORING_PARAM = 'Yes'\r\n" + "AND " + userScopeData
				+ "\r\n" + "ORDER BY im.OUT_TRAFFIC DESC\r\n" + "LIMIT 10;");
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			jsonRow1 = new JSONObject();
			ipArray.put(obj[0] + " [" + obj[1] + "]");
			countArray.put(obj[2]);
			jsonRow1.put("ip", ipArray);
			jsonRow1.put("count", countArray);
		}
		System.out.println(jsonRow1);
		return jsonRow1;
	}

	public JSONObject getTopLatencyUtilization(String userScopeData) {
		JSONArray finalArray = new JSONArray();
		JSONArray ipArray = null;
		ipArray = new JSONArray();
		JSONArray countArray = null;
		countArray = new JSONArray();
//		jsonRow = null;
		JSONObject jsonRow1 = null;
		Query query = getSession().createSQLQuery("SELECT nm.node_ip, nm.latency FROM node_monitoring  nm,\r\n"
				+ " add_node add_node WHERE nm.NODE_IP = add_node.DEVICE_IP AND " + userScopeData + "\r\n"
				+ "ORDER BY nm.latency DESC\r\n" + "LIMIT 10;");
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			jsonRow1 = new JSONObject();
			ipArray.put(obj[0]);
			countArray.put(obj[1]);
			jsonRow1.put("ip", ipArray);
			jsonRow1.put("count", countArray);
		}

		return jsonRow1;
	}

	public JSONArray getAlarmInfo() {
		JSONArray finalArray = new JSONArray();
		JSONArray array = new JSONArray();
		JSONArray array1 = new JSONArray();
		JSONArray array2 = new JSONArray();
		JSONArray array3 = new JSONArray();
		try {
			Query query = getSession().createQuery("FROM NodeMonitoringModel WHERE LATENCY_STATUS='High'");
			List<NodeMonitoringModel> dataList = query.list();
			for (NodeMonitoringModel viewData : dataList) {
				JSONObject jsonEntry = new JSONObject();
				jsonEntry.put("NodeMonitoringNodeIP", viewData.getNODE_IP());
				jsonEntry.put("LATENCY", viewData.getLATENCY());
				jsonEntry.put("LATENCY_STATUS", viewData.getLATENCY_STATUS());
				jsonEntry.put("NodeMonitoringTime", viewData.getSTATUS_TIMESTAMP());
				array.put(jsonEntry);
			}

			Query query1 = getSession()
					.createQuery("FROM NodeHealthMonitoring WHERE CPU_STATUS='High' OR MEMORY_STATUS='High'");
			List<NodeHealthMonitoring> dataList1 = query1.list();
			for (NodeHealthMonitoring viewData : dataList1) {
				JSONObject jsonEntry = new JSONObject();
				jsonEntry.put("nodeHelthNodeIP", viewData.getNODE_IP());
				jsonEntry.put("MEMORY_STATUS", viewData.getMEMORY_STATUS());
				jsonEntry.put("MEMORY_UTILIZATION", viewData.getMEMORY_UTILIZATION());
				jsonEntry.put("CPU_STATUS", viewData.getCPU_STATUS());
				jsonEntry.put("CPU_UTILIZATION", viewData.getCPU_UTILIZATION());
				array1.put(jsonEntry);
			}

			Query query3 = getSession().createQuery("FROM DRIVE_THRESHOLD_LOG WHERE STATUS='High'");
			List<DRIVE_THRESHOLD_LOG> dataList3 = query3.list();
			for (DRIVE_THRESHOLD_LOG viewData : dataList3) {
				JSONObject jsonEntry = new JSONObject();
				jsonEntry.put("deviceIP", viewData.getDEVICE_IP());
				jsonEntry.put("driveName", viewData.getDRIVES_NAME());
				jsonEntry.put("status", viewData.getSTATUS());
				jsonEntry.put("eventTime", viewData.getEVENT_TIME());
				array3.put(jsonEntry);
			}

			Query query2 = getSession().createQuery("FROM InterfaceMonitoring WHERE BW_STATUS='High'");
			List<InterfaceMonitoring> dataList2 = query2.list();
			for (InterfaceMonitoring viewData : dataList2) {
				JSONObject jsonEntry = new JSONObject();
				jsonEntry.put("interfaceMonitoringNodeIP", viewData.getNODE_IP());
				jsonEntry.put("INTERFACE_NAME", viewData.getINTERFACE_NAME());
				jsonEntry.put("IN_TRAFFIC", viewData.getIN_TRAFFIC());
				jsonEntry.put("OUT_TRAFFIC", viewData.getOUT_TRAFFIC());
				jsonEntry.put("BW_STATUS", viewData.getBW_STATUS());
				jsonEntry.put("BW_TIME", viewData.getSTATUS_TIMESTAMP());
				array2.put(jsonEntry);
			}
			finalArray.put(array);
			finalArray.put(array1);
			finalArray.put(array2);
			finalArray.put(array3);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("finalArray = " + finalArray);
		return finalArray;
	}

	public long getAlarmCount() {
		long alertCount = 0;
		try {
			long query1 = (Long) getSession()
					.createQuery("SELECT COUNT(*) FROM NodeMonitoringModel WHERE LATENCY_STATUS='High'").uniqueResult();
			long query2 = (Long) getSession()
					.createQuery("SELECT COUNT(*) FROM NodeHealthMonitoring WHERE CPU_STATUS='High'").uniqueResult();
			long query3 = (Long) getSession()
					.createQuery("SELECT COUNT(*) FROM InterfaceMonitoring WHERE BW_STATUS='High'").uniqueResult();
			long query4 = (Long) getSession()
					.createQuery("SELECT COUNT(*) FROM NodeHealthMonitoring WHERE MEMORY_STATUS='High'").uniqueResult();
			alertCount = query1 + query2 + query3 + query4;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alertCount;
	}

	public JSONArray eventCountDashboard(String fromDate, String toDate) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery("SELECT "
				+ "   (SELECT COUNT(id) FROM agent_installed_device) as count1, "
				+ "   (SELECT COUNT(id) FROM windows_event_log WHERE EVENT_TIME BETWEEN :fromDate AND :toDate) as count2, "
				+ "   (SELECT COUNT(id) FROM syslogs_data WHERE event_time BETWEEN :fromDate AND :toDate) as count3")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate);

		List<Object[]> data = query.list();
		Object[] counts = data.get(0);
		for (Object count : counts) {
			arrayData = new JSONArray();
			arrayData.put(count);
			arrayMain.put(arrayData);
		}
		int total = arrayMain.getJSONArray(1).getInt(0) + arrayMain.getJSONArray(2).getInt(0);
		arrayData = new JSONArray();
		arrayData.put(total);
		arrayMain.put(arrayData);

		System.out.println("arrayMain=" + arrayMain);
		return arrayMain;
	}

	public JSONArray windowsSeverityEvent(String fromDate, String toDate) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession()
				.createSQLQuery(
						"SELECT COUNT(EVENT_SEVERITY) AS event_count, EVENT_SEVERITY " + "FROM windows_event_log "
								+ "WHERE EVENT_TIME BETWEEN :fromDate AND :toDate " + "GROUP BY EVENT_SEVERITY")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate);
		List<Object[]> data = query.list();

		for (Object[] row : data) {
			BigInteger eventCount = (BigInteger) row[0];
			String eventType = (String) row[1];

			JSONObject eventData = new JSONObject();
			eventData.put("event_type", eventType);
			eventData.put("event_count", eventCount);

			arrayMain.put(eventData);
		}

		System.out.println(arrayMain.toString());
		return arrayMain;
	}

	public JSONArray windowsEventLogWise(String fromDate, String toDate) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession()
				.createSQLQuery("SELECT COUNT(EVENT_TYPE) AS event_count, EVENT_TYPE " + "FROM windows_event_log "
						+ "WHERE EVENT_TIME BETWEEN :fromDate AND :toDate " + "GROUP BY EVENT_TYPE")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate);
		List<Object[]> data = query.list();

		for (Object[] row : data) {
			BigInteger eventCount = (BigInteger) row[0];
			String eventType = (String) row[1];

			JSONObject eventData = new JSONObject();
			eventData.put("event_type", eventType);
			eventData.put("event_count", eventCount);

			arrayMain.put(eventData);
		}

		System.out.println(arrayMain.toString());
		return arrayMain;
	}

	public JSONArray windowsEventTrend(String fromDate, String toDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate currentfromDate = LocalDate.parse(fromDate, formatter);
		LocalDate currenttoDate = LocalDate.parse(toDate, formatter);

		// Calculate the difference in days, weeks, months, and years
		int daysDifference = Period.between(currentfromDate, currenttoDate).getDays();
		int weeksDifference = Period.between(currentfromDate, currenttoDate).getDays() / 7;
		int monthsDifference = Period.between(currentfromDate, currenttoDate).getMonths();
		int yearsDifference = Period.between(currentfromDate, currenttoDate).getYears();

		System.out.println("Difference in days: " + daysDifference + " days");
		System.out.println("Difference in weeks: " + weeksDifference + " weeks");
		System.out.println("Difference in months: " + monthsDifference + " months");
		System.out.println("Difference in years: " + yearsDifference + " years");

		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		/*
		 * Query query = getSession().createSQLQuery(
		 * "SELECT DATE(date_time) AS day, COUNT(*) AS event_name, MIN(date_time) AS min_timestamp, MAX(date_time) AS max_timestamp "
		 * + "FROM syslogs_data " + "GROUP BY day" );
		 */

		Query query;
		if (yearsDifference != 0) {
			String dynamicFromDate = fromDate;
			String dynamicToDate = toDate;
			query = getSession()
					.createSQLQuery("SELECT YEAR(EVENT_TIME) AS year, " + "COUNT(*) AS event_count, "
							+ "MIN(EVENT_TIME) AS min_timestamp, " + "MAX(EVENT_TIME) AS max_timestamp "
							+ "FROM windows_event_log "
							+ "WHERE EVENT_TIME BETWEEN :dynamicFromDate AND :dynamicToDate " + "GROUP BY year")
					.setParameter("dynamicFromDate", dynamicFromDate).setParameter("dynamicToDate", dynamicToDate);

			List<Object[]> data = query.list();

			for (Object[] row : data) {
				BigInteger eventCount = (BigInteger) row[1];
				/* String eventDate = (String) row[0]; */
				/* String eventHour = (String) row[0]; */
				Integer eventHour = ((Number) row[0]).intValue();

				JSONObject eventData = new JSONObject();
				eventData.put("event_type", eventHour);
				eventData.put("event_count", eventCount);

				arrayMain.put(eventData);
			}

		} else if (monthsDifference != 0) {

			String dynamicFromDate = fromDate;
			String dynamicToDate = toDate;
			query = getSession()
					.createSQLQuery("SELECT DATE_FORMAT(EVENT_TIME, '%Y-%m') AS month, " + "COUNT(*) AS event_count, "
							+ "MIN(EVENT_TIME) AS min_timestamp, " + "MAX(EVENT_TIME) AS max_timestamp "
							+ "FROM windows_event_log "
							+ "WHERE EVENT_TIME BETWEEN :dynamicFromDate AND :dynamicToDate " + "GROUP BY month")
					.setParameter("dynamicFromDate", dynamicFromDate).setParameter("dynamicToDate", dynamicToDate);

			List<Object[]> data = query.list();

			for (Object[] row : data) {
				BigInteger eventCount = (BigInteger) row[1];
				/* String eventDate = (String) row[0]; */
				String eventHour = (String) row[0];

				JSONObject eventData = new JSONObject();
				eventData.put("event_type", eventHour);
				eventData.put("event_count", eventCount);

				arrayMain.put(eventData);
			}
		} else if (weeksDifference != 0 || daysDifference != 0) {
			String dynamicFromDate = fromDate;
			String dynamicToDate = toDate;
			query = getSession()
					.createSQLQuery("SELECT DATE(EVENT_TIME) AS day, " + "COUNT(*) AS event_id, "
							+ "MIN(EVENT_TIME) AS min_timestamp, " + "MAX(EVENT_TIME) AS max_timestamp "
							+ "FROM windows_event_log "
							+ "WHERE EVENT_TIME BETWEEN :dynamicFromDate AND :dynamicToDate " + "GROUP BY day")
					.setParameter("dynamicFromDate", dynamicFromDate).setParameter("dynamicToDate", dynamicToDate);

			List<Object[]> data = query.list();

			for (Object[] row : data) {
				BigInteger eventCount = (BigInteger) row[1];
				/* String eventDate = (String) row[0]; */
				java.sql.Date eventDate = (java.sql.Date) row[0];

				JSONObject eventData = new JSONObject();
				eventData.put("event_type", eventDate);
				eventData.put("event_count", eventCount);

				arrayMain.put(eventData);
			}
		} else {
			String dynamicFromDate = fromDate;
			String dynamicToDate = toDate;
			query = getSession()
					.createSQLQuery("SELECT " + "   TIME_FORMAT(EVENT_TIME, '%H:00:00') AS hour, "
							+ "   COUNT(*) AS event_count, " + "   MIN(EVENT_TIME) AS from_date, "
							+ "   MAX(EVENT_TIME) AS to_date " + "FROM windows_event_log "
							+ "WHERE EVENT_TIME BETWEEN :dynamicFromDate AND :dynamicToDate " + "GROUP BY hour")
					.setParameter("dynamicFromDate", dynamicFromDate).setParameter("dynamicToDate", dynamicToDate);

			List<Object[]> data = query.list();

			for (Object[] row : data) {
				BigInteger eventCount = (BigInteger) row[1];
				/* String eventDate = (String) row[0]; */
				String eventHour = (String) row[0];

				JSONObject eventData = new JSONObject();
				eventData.put("event_type", eventHour);
				eventData.put("event_count", eventCount);

				arrayMain.put(eventData);
			}
		}
		/*
		 * List<Object[]> data = query.list();
		 * 
		 * for (Object[] row : data) { BigInteger eventCount = (BigInteger) row[1];
		 * java.sql.Date eventDate = (java.sql.Date) row[0];
		 * 
		 * JSONObject eventData = new JSONObject(); eventData.put("event_type",
		 * eventDate); eventData.put("event_count", eventCount);
		 * 
		 * arrayMain.put(eventData); }
		 */

		System.out.println(arrayMain.toString());
		return arrayMain;
	}

	public JSONArray syslogEventTrend(String fromDate, String toDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate currentfromDate = LocalDate.parse(fromDate, formatter);
		LocalDate currenttoDate = LocalDate.parse(toDate, formatter);

		// Calculate the difference in days, weeks, months, and years
		int daysDifference = Period.between(currentfromDate, currenttoDate).getDays();
		int weeksDifference = Period.between(currentfromDate, currenttoDate).getDays() / 7;
		int monthsDifference = Period.between(currentfromDate, currenttoDate).getMonths();
		int yearsDifference = Period.between(currentfromDate, currenttoDate).getYears();

		System.out.println("Difference in days: " + daysDifference + " days");
		System.out.println("Difference in weeks: " + weeksDifference + " weeks");
		System.out.println("Difference in months: " + monthsDifference + " months");
		System.out.println("Difference in years: " + yearsDifference + " years");

		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		/*
		 * Query query = getSession().createSQLQuery(
		 * "SELECT DATE(date_time) AS day, COUNT(*) AS event_name, MIN(date_time) AS min_timestamp, MAX(date_time) AS max_timestamp "
		 * + "FROM syslogs_data " + "GROUP BY day" );
		 */

		Query query;
		if (yearsDifference != 0) {
			String dynamicFromDate = fromDate;
			String dynamicToDate = toDate;
			query = getSession().createSQLQuery("SELECT YEAR(event_time) AS year, " + "COUNT(*) AS event_count, "
					+ "MIN(event_time) AS min_timestamp, " + "MAX(event_time) AS max_timestamp " + "FROM syslogs_data "
					+ "WHERE event_time BETWEEN :dynamicFromDate AND :dynamicToDate " + "GROUP BY year")
					.setParameter("dynamicFromDate", dynamicFromDate).setParameter("dynamicToDate", dynamicToDate);

			List<Object[]> data = query.list();

			for (Object[] row : data) {
				BigInteger eventCount = (BigInteger) row[1];
				/* String eventDate = (String) row[0]; */
				/* String eventHour = (String) row[0]; */
				Integer eventHour = ((Number) row[0]).intValue();

				JSONObject eventData = new JSONObject();
				eventData.put("event_type", eventHour);
				eventData.put("event_count", eventCount);

				arrayMain.put(eventData);
			}

		} else if (monthsDifference != 0) {

			String dynamicFromDate = fromDate;
			String dynamicToDate = toDate;
			query = getSession()
					.createSQLQuery("SELECT DATE_FORMAT(event_time, '%Y-%m') AS month, " + "COUNT(*) AS event_count, "
							+ "MIN(event_time) AS min_timestamp, " + "MAX(event_time) AS max_timestamp "
							+ "FROM syslogs_data " + "WHERE event_time BETWEEN :dynamicFromDate AND :dynamicToDate "
							+ "GROUP BY month")
					.setParameter("dynamicFromDate", dynamicFromDate).setParameter("dynamicToDate", dynamicToDate);

			List<Object[]> data = query.list();

			for (Object[] row : data) {
				BigInteger eventCount = (BigInteger) row[1];
				/* String eventDate = (String) row[0]; */
				String eventHour = (String) row[0];

				JSONObject eventData = new JSONObject();
				eventData.put("event_type", eventHour);
				eventData.put("event_count", eventCount);

				arrayMain.put(eventData);
			}
		} else if (weeksDifference != 0 || daysDifference != 0) {
			String dynamicFromDate = fromDate;
			String dynamicToDate = toDate;
			query = getSession().createSQLQuery("SELECT DATE(event_time) AS day, " + "COUNT(*) AS event_id, "
					+ "MIN(event_time) AS min_timestamp, " + "MAX(event_time) AS max_timestamp " + "FROM syslogs_data "
					+ "WHERE event_time BETWEEN :dynamicFromDate AND :dynamicToDate " + "GROUP BY day")
					.setParameter("dynamicFromDate", dynamicFromDate).setParameter("dynamicToDate", dynamicToDate);

			List<Object[]> data = query.list();

			for (Object[] row : data) {
				BigInteger eventCount = (BigInteger) row[1];
				/* String eventDate = (String) row[0]; */
				java.sql.Date eventDate = (java.sql.Date) row[0];

				JSONObject eventData = new JSONObject();
				eventData.put("event_type", eventDate);
				eventData.put("event_count", eventCount);

				arrayMain.put(eventData);
			}
		} else {
			String dynamicFromDate = fromDate;
			String dynamicToDate = toDate;
			query = getSession()
					.createSQLQuery("SELECT " + "   TIME_FORMAT(event_time, '%H:00:00') AS hour, "
							+ "   COUNT(*) AS event_count, " + "   MIN(event_time) AS from_date, "
							+ "   MAX(event_time) AS to_date " + "FROM syslogs_data "
							+ "WHERE event_time BETWEEN :dynamicFromDate AND :dynamicToDate " + "GROUP BY hour")
					.setParameter("dynamicFromDate", dynamicFromDate).setParameter("dynamicToDate", dynamicToDate);

			List<Object[]> data = query.list();

			for (Object[] row : data) {
				BigInteger eventCount = (BigInteger) row[1];
				/* String eventDate = (String) row[0]; */
				String eventHour = (String) row[0];

				JSONObject eventData = new JSONObject();
				eventData.put("event_type", eventHour);
				eventData.put("event_count", eventCount);

				arrayMain.put(eventData);
			}
		}
		/*
		 * List<Object[]> data = query.list();
		 * 
		 * for (Object[] row : data) { BigInteger eventCount = (BigInteger) row[1];
		 * java.sql.Date eventDate = (java.sql.Date) row[0];
		 * 
		 * JSONObject eventData = new JSONObject(); eventData.put("event_type",
		 * eventDate); eventData.put("event_count", eventCount);
		 * 
		 * arrayMain.put(eventData); }
		 */

		System.out.println(arrayMain.toString());
		return arrayMain;
	}

	public JSONArray windowsSeverityBarClick(String fromDate, String toDate, String label) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT sc.ID, sc.LOG_ID, sc.DEVICE_IP, sc.DEVICE_NAME, sc.EVENT_ID, sc.EVENT_TYPE, sc.EVENT_SEVERITY, sc.EVENT_NAME, sc.EVENT_CATEGORY, sc.EVENT_SOURCE, sc.EVENT_MESSAGE, sc.Event_Level, sc.EVENT_TIME "
						+ "FROM windows_event_log sc " + "WHERE sc.EVENT_TIME BETWEEN :fromDate AND :toDate "
						+ "AND sc.EVENT_SEVERITY = :eventType")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate).setParameter("eventType", label);
		List<Object[]> data = query.list();
		System.out.println("Size Data:" + data.size());
		for (Object[] a : data) {
			arrayData = new JSONArray();
//			arrayData.put(a[0]);
			arrayData.put(a[1]);
			arrayData.put(a[2]);
			arrayData.put(a[3]);
			arrayData.put(a[4]);
			arrayData.put(a[5]);
			arrayData.put(a[6]);
			arrayData.put(a[7]);
			arrayData.put(a[8]);
			arrayData.put(a[9]);
			arrayData.put(a[10]);
			arrayData.put(a[11]);
			arrayData.put(a[12]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray windowsLogBarClick(String fromDate, String toDate, String label) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT sc.ID, sc.LOG_ID, sc.DEVICE_IP, sc.DEVICE_NAME, sc.EVENT_ID, sc.EVENT_TYPE, sc.EVENT_SEVERITY, sc.EVENT_NAME, sc.EVENT_CATEGORY, sc.EVENT_SOURCE, sc.EVENT_MESSAGE, sc.Event_Level, sc.EVENT_TIME "
						+ "FROM windows_event_log sc " + "WHERE sc.EVENT_TIME BETWEEN :fromDate AND :toDate "
						+ "AND sc.EVENT_TYPE = :eventType")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate).setParameter("eventType", label);
		List<Object[]> data = query.list();
		System.out.println("Size Data:" + data.size());
		for (Object[] a : data) {
			arrayData = new JSONArray();
//			arrayData.put(a[0]);
			arrayData.put(a[1]);
			arrayData.put(a[2]);
			arrayData.put(a[3]);
			arrayData.put(a[4]);
			arrayData.put(a[5]);
			arrayData.put(a[6]);
			arrayData.put(a[7]);
			arrayData.put(a[8]);
			arrayData.put(a[9]);
			arrayData.put(a[10]);
			arrayData.put(a[11]);
			arrayData.put(a[12]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray eventWiseSummary() {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			String query = "SELECT COUNT(EVENT_NAME) AS event_count, EVENT_NAME " + "FROM windows_event_log "
					+ "GROUP BY EVENT_NAME";
			Query q = getSession().createSQLQuery(query);
			List<Object[]> data = q.list();

			System.out.println("Size Data:" + data.size());
			array1 = new JSONArray();

			for (Object[] groupData : data) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(groupData[1].toString());
				array.put("<span style='color:orange' onclick=\"eventNameSummaryDetails('" + groupData[1].toString()
						+ "')\">" + groupData[0].toString() + "</span>");
				/*
				 * array.put(groupData[1].toString()); array.put(groupData[2].toString());
				 * array.put(groupData[3].toString());
				 */
				System.out.println(groupData[0].toString());
				System.out.println(groupData[1].toString());
				array1.put(array);
				/*
				 * array.put("<span style='color:orange' onclick=\"gettotalNodeSummaryDetails('"
				 * + groupData[0].toString() + "')\">" + groupData[1].toString() + "</span>");
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray eventCountWiseSummary(String eventName) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		System.out.println(eventName);
		Query query = getSession().createSQLQuery(
				"SELECT sc.LOG_ID, sc.DEVICE_IP, sc.DEVICE_NAME, sc.EVENT_ID, sc.EVENT_TYPE, sc.EVENT_SEVERITY, sc.EVENT_NAME, sc.EVENT_CATEGORY, sc.EVENT_SOURCE, sc.EVENT_MESSAGE, sc.Event_Level, sc.EVENT_TIME "
						+ "FROM windows_event_log sc " + "WHERE sc.EVENT_NAME = :eventNames")
				.setParameter("eventNames", eventName);
		List<Object[]> data = query.list();
		int srno = 0;
		System.out.println("Size Data:" + data.size());
		for (Object[] a : data) {
			srno++;
			arrayData = new JSONArray();
			arrayData.put(srno);
			arrayData.put(a[0]);
			arrayData.put(a[1]);
			arrayData.put(a[2]);
			arrayData.put(a[3]);
			arrayData.put(a[4]);
			arrayData.put(a[5]);
			arrayData.put(a[6]);
			arrayData.put(a[7]);
			arrayData.put(a[8]);
			arrayData.put(a[9]);
			arrayData.put(a[10]);
			arrayData.put(a[11]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray sourceWiseSummary(String fromDate, String toDate) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession()
				.createSQLQuery("SELECT DISTINCT EVENT_SOURCE " + "FROM windows_event_log "
						+ "WHERE EVENT_TIME BETWEEN :fromDate AND :toDate")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate);
		List<Object[]> data = query.list();

		for (Object eventSource : data) {
			arrayData = new JSONArray();
			arrayData.put(eventSource.toString());
			System.out.println(eventSource.toString());

			Query Successquery = getSession()
					.createSQLQuery("SELECT COUNT(EVENT_SEVERITY) as count FROM windows_event_log "
							+ "WHERE EVENT_SOURCE = :eventSource " + "AND EVENT_SEVERITY = 'success' "
							+ "AND EVENT_TIME BETWEEN :fromDate AND :toDate")
					.setParameter("eventSource", eventSource.toString()).setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate);

			Query Errorquery = getSession()
					.createSQLQuery("SELECT COUNT(EVENT_SEVERITY) as count " + "FROM windows_event_log "
							+ "WHERE EVENT_SOURCE = :eventSource " + "AND EVENT_SEVERITY = 'error' "
							+ "AND EVENT_TIME BETWEEN :fromDate AND :toDate")
					.setParameter("eventSource", eventSource.toString()).setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate);

			Query Warningquery = getSession()
					.createSQLQuery("SELECT COUNT(EVENT_SEVERITY) as count " + "FROM windows_event_log "
							+ "WHERE EVENT_SOURCE = :eventSource " + "AND EVENT_SEVERITY = 'warning' "
							+ "AND EVENT_TIME BETWEEN :fromDate AND :toDate")
					.setParameter("eventSource", eventSource.toString()).setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate);

			Query Informationquery = getSession()
					.createSQLQuery("SELECT COUNT(EVENT_SEVERITY) as count " + "FROM windows_event_log "
							+ "WHERE EVENT_SOURCE = :eventSource " + "AND EVENT_SEVERITY = 'information' "
							+ "AND EVENT_TIME BETWEEN :fromDate AND :toDate")
					.setParameter("eventSource", eventSource.toString()).setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate);

			Query Failurequery = getSession()
					.createSQLQuery("SELECT COUNT(EVENT_SEVERITY) as count " + "FROM windows_event_log "
							+ "WHERE EVENT_SOURCE = :eventSource " + "AND EVENT_SEVERITY = 'failure' "
							+ "AND EVENT_TIME BETWEEN :fromDate AND :toDate")
					.setParameter("eventSource", eventSource.toString()).setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate);

			List<Object[]> Successdata = Successquery.list();
			List<Object[]> Errordata = Errorquery.list();
			List<Object[]> Warningdata = Warningquery.list();
			List<Object[]> Informationdata = Informationquery.list();
			List<Object[]> Failuredata = Failurequery.list();
			for (Object Successdatas : Successdata) {
				/* arrayData.put(Successdatas.toString()); */
				arrayData.put("<span style='color:green' onclick=\"souceSummaryCountClick('" + eventSource.toString()
						+ "', '" + "success" + "')\">" + Successdatas.toString() + "</span>");

				System.out.println(Successdatas.toString());
			}
			for (Object Errordatas : Errordata) {
				/* arrayData.put(Errordatas.toString()); */
				arrayData.put("<span style='color:red' onclick=\"souceSummaryCountClick('" + eventSource.toString()
						+ "', '" + "error" + "')\">" + Errordatas.toString() + "</span>");
				System.out.println(Errordatas.toString());
			}

			for (Object Warningdatas : Warningdata) {
				/* arrayData.put(Warningdatas.toString()); */
				arrayData.put("<span style='color:yellow' onclick=\"souceSummaryCountClick('" + eventSource.toString()
						+ "', '" + "warning" + "')\">" + Warningdatas.toString() + "</span>");
				System.out.println(Warningdatas.toString());
			}
			for (Object Informationdatas : Informationdata) {
				/* arrayData.put(Informationdatas.toString()); */
				arrayData.put("<span style='color:orange' onclick=\"souceSummaryCountClick('" + eventSource.toString()
						+ "', '" + "information" + "')\">" + Informationdatas.toString() + "</span>");
				System.out.println(Informationdatas.toString());
			}
			for (Object Failuredatas : Failuredata) {
				/* arrayData.put(Failuredatas.toString()); */
				arrayData.put("<span style='color:#a82e2e' onclick=\"souceSummaryCountClick('" + eventSource.toString()
						+ "', '" + "failure" + "')\">" + Failuredatas.toString() + "</span>");
				System.out.println(Failuredatas.toString());
			}

//			System.out.println("Size Data:" + Successdata.size());
//			System.out.println("Size Data:" + Errordata.size());
//			System.out.println("Size Data:" + Warningdata.size());
//			System.out.println("Size Data:" + Informationdata.size());
//			System.out.println("Size Data:" + Failuredata.size());
			System.out.println("*****************************************");
			arrayMain.put(arrayData);
		}
		System.out.println("Size Data:" + data.size());
		return arrayMain;
	}

	public JSONArray souceSummaryCountClick(String fromDate, String toDate, String sourceName, String severityType) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT sc.LOG_ID, sc.DEVICE_IP, sc.DEVICE_NAME, sc.EVENT_ID, sc.EVENT_TYPE, sc.EVENT_SEVERITY, sc.EVENT_NAME, sc.EVENT_CATEGORY, sc.EVENT_SOURCE, sc.EVENT_MESSAGE, sc.Event_Level, sc.EVENT_TIME "
						+ "FROM windows_event_log sc " + "WHERE sc.EVENT_TIME BETWEEN :fromDate AND :toDate "
						+ "AND sc.EVENT_SEVERITY = :severityType " + "AND sc.EVENT_SOURCE = :sourceName")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate)
				.setParameter("severityType", severityType).setParameter("sourceName", sourceName);
		List<Object[]> data = query.list();
		int srno = 0;
		System.out.println("Size Data:" + data.size());
		for (Object[] a : data) {
			srno++;
			arrayData = new JSONArray();
			arrayData.put(srno);
			arrayData.put(a[0]);
			arrayData.put(a[1]);
			arrayData.put(a[2]);
			arrayData.put(a[3]);
			arrayData.put(a[4]);
			arrayData.put(a[5]);
			arrayData.put(a[6]);
			arrayData.put(a[7]);
			arrayData.put(a[8]);
			arrayData.put(a[9]);
			arrayData.put(a[10]);
			arrayData.put(a[11]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray osWiseSummary() {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			String query = "SELECT HI.os_name, " + "COUNT(*) AS Total_Count, " + // Added comma here
					"SUM(CASE WHEN AID.DEVICE_STATUS = 'UP' THEN 1 ELSE 0 END) AS UP_Count, "
					+ "SUM(CASE WHEN AID.DEVICE_STATUS = 'DOWN' THEN 1 ELSE 0 END) AS DOWN_Count " + // Removed comma
																										// here
					"FROM hardware_inventory HI " + "JOIN agent_installed_device AID ON HI.ip_address = AID.DEVICE_IP "
					+ "WHERE AID.DEVICE_STATUS IN ('UP', 'DOWN') " + "GROUP BY HI.os_name";
			Query q = getSession().createSQLQuery(query);
			List<Object[]> data = q.list();

			System.out.println("Size Data:" + data.size());
			array1 = new JSONArray();

			for (Object[] groupData : data) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(groupData[0].toString());
				/*
				 * array.put(groupData[1].toString()); array.put(groupData[2].toString());
				 * array.put(groupData[3].toString());
				 */
				System.out.println(groupData[0].toString());
				System.out.println(groupData[1].toString());
				System.out.println(groupData[2].toString());
				System.out.println(groupData[3].toString());
				array.put("<span style='color:orange' onclick=\"gettotalNodeSummaryDetails('" + groupData[0].toString()
						+ "')\">" + groupData[1].toString() + "</span>");
				array.put("<span style='color:green' onclick=\"getupNodeSummaryDetails('" + groupData[0].toString()
						+ "')\">" + groupData[2].toString() + "</span>");
				array.put("<span style='color:red' onclick=\"getdownNodeSummaryDetails('" + groupData[0].toString()
						+ "')\">" + groupData[3].toString() + "</span>");
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray windowsSecurityEvent(String fromDate, String toDate) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT sc.ID, sc.LOG_ID, sc.DEVICE_IP, sc.DEVICE_NAME, sc.EVENT_ID, sc.EVENT_TYPE, sc.EVENT_SEVERITY, sc.EVENT_NAME, sc.EVENT_CATEGORY, sc.EVENT_SOURCE, sc.EVENT_MESSAGE, sc.Event_Level, sc.EVENT_TIME "
						+ "FROM windows_event_log sc " + "WHERE sc.EVENT_TIME BETWEEN :fromDate AND :toDate")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
//			arrayData.put(a[0]);
			arrayData.put(a[1]);
			arrayData.put(a[2]);
			arrayData.put(a[3]);
			arrayData.put(a[4]);
			arrayData.put(a[5]);
			arrayData.put(a[6]);
			arrayData.put(a[7]);
			arrayData.put(a[8]);
			arrayData.put(a[9]);
			arrayData.put(a[10]);
			arrayData.put(a[11]);
			arrayData.put(a[12]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray syslogEventList(String fromDate, String toDate) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession()
				.createSQLQuery("SELECT sc.device_ip, sc.syslog_severity, sc.syslog_facility, sc.syslog_type, "
						+ "sc.syslog_subtype, sc.syslog_source_ip, sc.syslog_source_port, sc.syslog_destination_ip, "
						+ "sc.syslog_destination_port, sc.syslog_protocol, sc.syslogs_datasource, sc.syslog_param, sc.syslog_value, "
						+ "sc.syslog_rawmsg, sc.syslog_msg, sc.syslog_threat, sc.syslog_time, sc.syslog_date, sc.event_time, "
						+ "sc.syslog_level, sc.device_name, sc.device_id " + "FROM syslogs_data sc "
						+ "WHERE sc.event_time BETWEEN :fromDate AND :toDate")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate);
		List<Object[]> data = query.list();
		int sr = 0;
		for (Object[] a : data) {
			sr++;
			arrayData = new JSONArray();
			arrayData.put(sr);
			arrayData.put(a[0]);
//			arrayData.put(a[1]);
//			arrayData.put(a[2]);
//			arrayData.put(a[3]);
//			arrayData.put(a[4]);
//			arrayData.put(a[5]);
//			arrayData.put(a[6]);
//			arrayData.put(a[7]);
//			arrayData.put(a[8]);
//			arrayData.put(a[9]);
//			arrayData.put(a[10]);
//			arrayData.put(a[11]);
//			arrayData.put(a[12]);
			arrayData.put(a[13]);
//			arrayData.put(a[14]);
//			arrayData.put(a[15]);
//			arrayData.put(a[16]);
//			arrayData.put(a[17]);
			arrayData.put(a[18]);
//			arrayData.put(a[19]);
//			arrayData.put(a[20]);
//			arrayData.put(a[21]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray allDevicesList() {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession()
				.createSQLQuery("SELECT sc.DEVICE_IP, sc.DEVICE_STATUS, sc.HOST_NAME FROM agent_installed_device sc");
		List<Object[]> data = query.list();
		int sr = 0;
		for (Object[] a : data) {
			sr++;
			arrayData = new JSONArray();
			arrayData.put(sr);
			arrayData.put(a[0]);
			arrayData.put(a[1]);
			arrayData.put(a[2]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getTotalNodeSummaryDetails(String group_name) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery("SELECT hi.os_name, aid.device_ip, aid.device_status "
				+ "FROM hardware_inventory hi " + "JOIN agent_installed_device aid ON hi.ip_address = aid.device_ip "
				+ "WHERE hi.os_name=:group_name");
		query.setParameter("group_name", group_name);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getupNodeSummaryDetails(String group_name) {
		System.out.println("hi i am rajesh " + group_name);
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery("SELECT DISTINCT hi.os_name, aid.device_ip, aid.device_status "
				+ "FROM hardware_inventory hi " + "JOIN agent_installed_device aid ON hi.ip_address = aid.device_ip "
				+ "WHERE hi.os_name=:group_name AND aid.device_status = 'Up'");
		query.setParameter("group_name", group_name);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getdownNodeSummaryDetails(String group_name) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery("SELECT hi.os_name, aid.device_ip, aid.device_status "
				+ "FROM hardware_inventory hi " + "JOIN agent_installed_device aid ON hi.ip_address = aid.device_ip "
				+ "WHERE hi.os_name=:group_name AND aid.device_status = 'Down'");
		query.setParameter("group_name", group_name);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray allSyslogSeverityList(String fromDate, String toDate) {
		JSONArray arrayMain = new JSONArray();

		// Use createQuery for HQL queries
		Query query = getSession()
				.createSQLQuery("select syslog_severity, count(syslog_severity) as count " + "from syslogs_data "
						+ "where event_time BETWEEN :fromDate AND :toDate " + "group by syslog_severity")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate);

		// The result list will contain Object[] where [0] is syslogSeverity and [1] is
		// count
		List<Object[]> result = query.list();

		for (Object[] row : result) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("syslogSeverity", row[0]);
			jsonObject.put("count", row[1]);
			arrayMain.put(jsonObject);
		}
		return arrayMain;
	}

	public JSONArray syslogSeverityListData(String fromDate, String toDate, String severity) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession()
				.createSQLQuery("SELECT sc.device_ip, sc.syslog_severity, sc.syslog_facility, sc.syslog_type, "
						+ "sc.syslog_subtype, sc.syslog_source_ip, sc.syslog_source_port, sc.syslog_destination_ip, "
						+ "sc.syslog_destination_port, sc.syslog_protocol, sc.syslogs_datasource, sc.syslog_param, sc.syslog_value, "
						+ "sc.syslog_rawmsg, sc.syslog_msg, sc.syslog_threat, sc.syslog_time, sc.syslog_date, sc.event_time, "
						+ "sc.syslog_level, sc.device_name, sc.device_id " + "FROM syslogs_data sc "
						+ "WHERE syslog_severity=:severity AND sc.event_time BETWEEN :fromDate AND :toDate")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate).setParameter("severity", severity);
		List<Object[]> data = query.list();
		int sr = 0;
		for (Object[] a : data) {
			sr++;
			arrayData = new JSONArray();
			arrayData.put(sr);
			arrayData.put(a[0]);
//			arrayData.put(a[1]);
//			arrayData.put(a[2]);
//			arrayData.put(a[3]);
//			arrayData.put(a[4]);
//			arrayData.put(a[5]);
//			arrayData.put(a[6]);
//			arrayData.put(a[7]);
//			arrayData.put(a[8]);
//			arrayData.put(a[9]);
//			arrayData.put(a[10]);
//			arrayData.put(a[11]);
//			arrayData.put(a[12]);
			arrayData.put(a[13]);
//			arrayData.put(a[14]);
//			arrayData.put(a[15]);
//			arrayData.put(a[16]);
//			arrayData.put(a[17]);
			arrayData.put(a[18]);
//			arrayData.put(a[19]);
//			arrayData.put(a[20]);
//			arrayData.put(a[21]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray allSyslogTypeList(String fromDate, String toDate) {
		JSONArray arrayMain = new JSONArray();

		// Use createQuery for HQL queries
		Query query = getSession()
				.createSQLQuery("select syslog_type, count(syslog_type) as count " + "from syslogs_data "
						+ "where event_time BETWEEN :fromDate AND :toDate " + "group by syslog_type")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate);

		// The result list will contain Object[] where [0] is syslogSeverity and [1] is
		// count
		List<Object[]> result = query.list();

		for (Object[] row : result) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("syslogType", row[0]);
			jsonObject.put("count", row[1]);
			arrayMain.put(jsonObject);
		}
		System.out.println("arrayMainDATA=" + arrayMain);
		return arrayMain;
	}

	public JSONArray syslogTypeListData(String fromDate, String toDate, String type) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession()
				.createSQLQuery("SELECT sc.id, sc.device_ip, sc.syslog_severity, sc.syslog_facility, sc.syslog_type, "
						+ "sc.syslog_subtype, sc.syslog_source_ip, sc.syslog_source_port, sc.syslog_destination_ip, "
						+ "sc.syslog_destination_port, sc.syslog_protocol, sc.syslogs_datasource, sc.syslog_param, sc.syslog_value, "
						+ "sc.syslog_rawmsg, sc.syslog_msg, sc.syslog_threat, sc.syslog_time, sc.syslog_date, sc.event_time, "
						+ "sc.syslog_level, sc.device_name, sc.device_id " + "FROM syslogs_data sc "
						+ "WHERE syslog_type=:type AND sc.event_time BETWEEN :fromDate AND :toDate")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate).setParameter("type", type);
		List<Object[]> data = query.list();
		int sr = 0;
		for (Object[] a : data) {
			sr++;
			arrayData = new JSONArray();
			arrayData.put(sr);
			arrayData.put(a[1]);
			arrayData.put(a[2]);
			arrayData.put(a[3]);
			arrayData.put(a[4]);
			arrayData.put(a[5]);

//			arrayData.put(a[6]);
//			arrayData.put(a[7]);
//			arrayData.put(a[8]);
//			arrayData.put(a[9]);
//			arrayData.put(a[10]);
//			arrayData.put(a[11]);
//			arrayData.put(a[12]);
//			arrayData.put(a[13]);

			arrayData.put(a[14]);
			arrayData.put(a[15]);
			arrayData.put(a[16]);
			arrayData.put(a[17]);
			arrayData.put(a[18]);
			arrayData.put(a[19]);
			arrayData.put(a[20]);

//			arrayData.put(a[21]);
//			arrayData.put(a[22]);
			arrayMain.put(arrayData);
		}
		System.out.println("arrayMainList=" + arrayMain);
		return arrayMain;
	}

	public JSONArray collectTreeGroup() {

		// String query = "SELECT DEVICE_IP, HOST_NAME ,GROUP_NAME,DEVICE_STATUS FROM
		// agent_installed_device";
		JSONArray array = new JSONArray();
		JSONObject obj = null;
		try {

			List<Object[]> nodeObj = getSession()
					.createSQLQuery("Select ad.DEVICE_IP, ad.DEVICE_NAME, ad.GROUP_NAME, nm.NODE_STATUS"
							+ " from add_node ad, node_monitoring nm where ad.DEVICE_IP = nm.NODE_IP and ad.DEVICE_TYPE = 'Server'")
					.list();
			// List<AgentInstalledDeviceBean> dataList = new
			// ArrayList<AgentInstalledDeviceBean>();

			for (Object[] a : nodeObj) {

				obj = new JSONObject();
				obj.put("deviceIP", a[0].toString());
				obj.put("deviceName", a[1].toString());
				obj.put("groupName", a[2].toString());
				obj.put("nodeStatus", a[3].toString());

				array.put(obj);

			}

		} catch (Exception e) {
			System.out.println("Error while getting data = " + e);
		}

		// List<Object[]> data = q.list();

		return array;
	}

	// Listing of Node Details

	public JSONArray listingDiskConfigDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Disk_Configuration.class);
			criteria.add(Restrictions.eq("device_ip", ipAddress));
			List<Disk_Configuration> dataList = criteria.list();
			array1 = new JSONArray();
			for (Disk_Configuration groupData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(groupData.getName());
				array.put(groupData.getSerialNumber());
				array.put(groupData.getModel());
				array.put(groupData.getMediaType());
				array.put(groupData.getInterfaceType());
				array.put(groupData.getManufacturer());
				array.put(groupData.getCapacity());

				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingHardwareInDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Hardware_Inventory.class);
			criteria.add(Restrictions.eq("device_ip", ipAddress));
			List<Hardware_Inventory> dataList = criteria.list();
			array1 = new JSONArray();
			for (Hardware_Inventory groupData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(groupData.getProcessorName());
				array.put(groupData.getProcessorManufacturer());
				array.put(groupData.getProcessorCount());

				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingMemoryModuleDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Memory_Configuration.class);
			criteria.add(Restrictions.eq("device_ip", ipAddress));
			List<Memory_Configuration> dataList = criteria.list();
			array1 = new JSONArray();
			for (Memory_Configuration groupData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(groupData.getSocket());
				array.put(groupData.getCapacity());
				array.put(groupData.getModuleTag());
				array.put(groupData.getBankLabel());
				array.put(groupData.getSerialNumber());
				array.put(groupData.getFrequency());

				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingLogicalDriveDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Logicaldrive_Configuration.class);
			criteria.add(Restrictions.eq("device_ip", ipAddress));
			List<Logicaldrive_Configuration> dataList = criteria.list();
			array1 = new JSONArray();
			for (Logicaldrive_Configuration groupData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(groupData.getDrive());
				array.put(groupData.getDriveType());
				array.put(groupData.getFileType());
				array.put(groupData.getSerialNumber());
				double capacityDouble = Double.parseDouble(groupData.getCapacity());
				String formattedCapacity = String.format("%.2f", capacityDouble);

				array.put(formattedCapacity);
				double FreeSpaceDouble = Double.parseDouble(groupData.getFreeSpace());
				String formattedFreeSpace = String.format("%.2f", FreeSpaceDouble);
				array.put(formattedFreeSpace);

				double DriveUsageDouble = Double.parseDouble(groupData.getDriveUsage());
				String formattedDriveUsage = String.format("%.2f", DriveUsageDouble);

				array.put(formattedDriveUsage);

				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingPrintalDtDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Hw_Printers.class);
			criteria.add(Restrictions.eq("device_ip", ipAddress));
			List<Hw_Printers> dataList = criteria.list();
			array1 = new JSONArray();
			for (Hw_Printers groupData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(groupData.getName());
				array.put(groupData.getServer());
				array.put(groupData.getModel());
				array.put(groupData.getType());
				array.put(groupData.getDriverName());
				array.put(groupData.getLocation());

				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingHwConfigDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Hardware_Inventory.class);
			criteria.add(Restrictions.eq("device_ip", ipAddress));
			List<Hardware_Inventory> dataList = criteria.list();
			array1 = new JSONArray();
			for (Hardware_Inventory groupData : dataList) {

				array = new JSONArray();

				array.put(groupData.getDevice_ip());
				array.put(groupData.getMacAddress());
				array.put(groupData.getManufacturer());
				array.put(groupData.getProcessorCount());
				array.put(groupData.getOsType());
				array.put(groupData.getDiskSpace());
				array.put(groupData.getProcessorName());

				array.put(groupData.getServicePack());
				array.put(groupData.getDomain());
				array.put(groupData.getOriginalSerialNo());
				array.put(groupData.getTotalMemory());
				array.put(groupData.getProcessorManufacturer());
				array.put(groupData.getBiosName());
				array.put(groupData.getBiosManufacturer());

				array.put(groupData.getBiosReleaseDate());
				array.put(groupData.getBiosVersion());
				array.put(groupData.getBuildNumber());
				array.put(groupData.getDevice_name());
				array.put(groupData.getOsName());
				array.put(groupData.getOsVersion());

				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingSwInventDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Sw_Inventory.class);
			criteria.add(Restrictions.eq("deviceIp", ipAddress));
			List<Sw_Inventory> dataList = criteria.list();
			array1 = new JSONArray();
			for (Sw_Inventory groupData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(groupData.getDeviceName());
				array.put(groupData.getDeviceIp());
				// array.put(groupData.getMacId());
				array.put(groupData.getApplicationName());
				array.put(groupData.getApplicationVersion());
				// array.put(groupData.getIsLicense());
				// array.put(groupData.getLicenseKey());
				// array.put(groupData.getProductId());
				array.put(groupData.getPublisher());
				array.put(groupData.getUninstallStr());

				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingSwInventforlinuxService(String ipAddress) {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Query criteria = getSession().createSQLQuery(
					" SELECT SR_NO, IP_ADDRESS, PC_NAME, APPLICATION_NAME, CDATE, CTIME FROM sw_inventory_linux WHERE IP_ADDRESS='"
							+ ipAddress.trim() + "'");

			List<Object[]> dataList = criteria.list();
			array1 = new JSONArray();

			for (Object[] strings : dataList) {
				array = new JSONArray();
				array.put(strings[0]);
				array.put(strings[1]);
				array.put(strings[2]);
				array.put(strings[3]);
				array.put(strings[4]);
				array.put(strings[5]);

				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;

	}

	public JSONArray listingHwConfigLinuxService(String ipAddress) {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Query criteria = getSession().createSQLQuery(
					" SELECT  IP_ADDRESS, PC_NAME, BIOS_INFO, GRAPHIC_CARD, HDD_DRIVE, MOTHERBOARD_NAME, OP_NAME, PROCESSOR_NAME, RAM_DETAILS, DISCOVER_TIME, CTIME FROM hardware_inventory_linux WHERE IP_ADDRESS='"
							+ ipAddress + "' ;");

			List<Object[]> dataList = criteria.list();
			array1 = new JSONArray();

			for (Object[] strings : dataList) {
				array = new JSONArray();
				array.put(strings[0]);
				array.put(strings[1]);
				array.put(strings[2]);
				array.put(strings[3]);
				array.put(strings[4]);
				array.put(strings[5]);
				array.put(strings[6]);
				array.put(strings[7]);
				array.put(strings[8]);
				array.put(strings[9] + " " + strings[10]);
//				array.put(strings[10]);

				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingMemoryModuleLinuxService(String ipAddress) {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Query criteria = getSession().createSQLQuery(
					"SELECT  NODE_IP,CPU_STATUS, CPU_UTILIZATION,MEMORY_STATUS, FREE_MEMORY , MEMORY_UTILIZATION, TOTAL_MEMORY, USED_MEMORY FROM node_health_monitoring WHERE NODE_IP='"
							+ ipAddress + "'");

			List<Object[]> dataList = criteria.list();
			array1 = new JSONArray();

			for (Object[] strings : dataList) {
				array = new JSONArray();
				array.put(strings[0]);
				array.put(strings[1]);
				String CPU_UTILIZATION = "0";
				if (strings[2].toString().trim().equalsIgnoreCase("0")) {
					CPU_UTILIZATION = "0";
				} else {
					CPU_UTILIZATION = strings[2].toString().trim() + " %";
				}
				array.put(CPU_UTILIZATION);
				array.put(strings[3]);

				String kbString = strings[4].toString().trim();
				double kbDouble = Double.parseDouble(kbString); // Parse the string to double
				long kb = (long) kbDouble;
				double gb = kb / (1024.0 * 1024.0);
				double roundedGb = Math.ceil(gb * 100) / 100;
				String gbString = String.format("%.2f", roundedGb);
				String FreeMemgbString = gbString + " GB";

				array.put(FreeMemgbString);

				String Memory_UTILIZATION = "0";
				if (strings[5].toString().trim().equalsIgnoreCase("0")) {
					Memory_UTILIZATION = "0";
				} else {
					Memory_UTILIZATION = strings[5].toString().trim() + " %";
				}

				array.put(Memory_UTILIZATION);

				kbString = strings[6].toString().trim();
				kbDouble = Double.parseDouble(kbString); // Parse the string to double
				kb = (long) kbDouble; // Convert double to long
				gb = kb / (1024.0 * 1024.0); // Conversion to gigabytes

				roundedGb = Math.ceil(gb * 100) / 100;
				gbString = String.format("%.2f", roundedGb);

				String TotalMemgbString = gbString + " GB";

				array.put(TotalMemgbString);

				kbString = strings[7].toString().trim();
				kbDouble = Double.parseDouble(kbString); // Parse the string to double
				kb = (long) kbDouble; // Convert double to long
				gb = kb / (1024.0 * 1024.0); // Conversion to gigabytes
				roundedGb = Math.ceil(gb * 100) / 100;
				gbString = String.format("%.2f", roundedGb);

				String UsedMemgbString = gbString + " GB";

				array.put(UsedMemgbString);
//				array.put(strings[8]);
//				array.put(strings[9]);
//				array.put(strings[10]);

				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("Error :: "+e);
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingPatchDataDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Patch_details.class);
			criteria.add(Restrictions.eq("deviceIP", ipAddress));
			List<Patch_details> dataList = criteria.list();
			array1 = new JSONArray();
			for (Patch_details groupData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(groupData.getBranchName());
				array.put(groupData.getDeviceName());
				array.put(groupData.getDeviceIP());
				array.put(groupData.getPatchId());
				array.put(groupData.getPatchType());
				array.put(groupData.getInstalledDate());

				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingProcessDataDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Process_Details.class);
			criteria.add(Restrictions.eq("ipAddress", ipAddress));
			List<Process_Details> dataList = criteria.list();
			array1 = new JSONArray();
			for (Process_Details groupData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(groupData.getpId());
				array.put(groupData.getAliasName());
				array.put(groupData.getProcessName());
				array.put(groupData.getCpu());
				array.put(groupData.getMemory());
				array.put(groupData.getDiscoverTime());

				array.put(groupData.getThreadCount());
				array.put(groupData.getHandleCount());
				array.put(groupData.getPeakWorkingSet());
				array.put(groupData.getPrivateWorkingSet());
				array.put(groupData.getPagedPool());
				array.put(groupData.getNpPool());

				array.put(groupData.getCommitSize());
				array.put(groupData.getCpuTime());
				array.put(groupData.getIoRead());
				array.put(groupData.getIoWrite());
				array.put(groupData.getIoOther());
				array.put(groupData.getIoReadBytes());

				array.put(groupData.getIoWriteBytes());
				array.put(groupData.getIoOtherBytes());

				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingServiceDataDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Service_Details.class);
			criteria.add(Restrictions.eq("ipAddress", ipAddress));
			List<Service_Details> dataList = criteria.list();
			array1 = new JSONArray();
			for (Service_Details groupData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(groupData.getAliasName());
				array.put(groupData.getServiceName());
				array.put(groupData.getDisplayName());
				array.put(groupData.getServiceMode());
				array.put(groupData.getServiceState());

				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray listingFirewallDataDao(String ipAddress) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(Firewall_Rules.class);
			criteria.add(Restrictions.eq("localIP", ipAddress));
			List<Firewall_Rules> dataList = criteria.list();
			array1 = new JSONArray();
			for (Firewall_Rules groupData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(groupData.getBranchName());
				array.put(groupData.getPcName());
				array.put(groupData.getAliasName());
				array.put(groupData.getRuleName());
				array.put(groupData.getEnabled());

				array.put(groupData.getDirection());
				array.put(groupData.getProfiles());
				array.put(groupData.getGrouping());
				array.put(groupData.getLocalIP());
				array.put(groupData.getRemoteIP());

				array.put(groupData.getProtocol());
				array.put(groupData.getLocalPort());
				array.put(groupData.getRemotePort());
				array.put(groupData.getEdgetraversal());
				array.put(groupData.getAction());
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray getDiscoverDriveDetails(String ipAddress) {
		JSONArray array = new JSONArray();
		JSONArray array1 = null;
		int srno = 0;
		try {

			List<Object[]> li = getSession()
					.createSQLQuery("SELECT DRIVE_NAME, TOTAL_SPACE, FREE_SPACE, USED_SPACE, "
							+ "DRIVE_UTILIZATION_PERCENTAGE FROM discover_drives WHERE DEVICE_IP = '" + ipAddress + "'")
					.list();
			for (Object[] data : li) {
				array1 = new JSONArray();
				array1.put(data[0]);
				array1.put(String.format("%.2f", (Double.valueOf(data[1].toString()) / 1024)));
				array1.put(String.format("%.2f", (Double.valueOf(data[2].toString()) / 1024)));
				array1.put(String.format("%.2f", (Double.valueOf(data[3].toString()) / 1024)));
				array1.put(data[4]);

				array.put(array1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	public JSONArray getDiscoverLinuxDriveDetails(String ipAddress) {

		JSONArray array = new JSONArray();
		JSONArray array1 = null;
		int srno = 0;
		try {

			List<Object[]> li = getSession()
					.createSQLQuery("SELECT DRIVE_NAME, TOTAL_SPACE, FREE_SPACE, USED_SPACE, "
							+ "DRIVE_UTILIZATION_PERCENTAGE FROM discover_drives WHERE DEVICE_IP = '" + ipAddress + "'")
					.list();
			for (Object[] data : li) {
				array1 = new JSONArray();
				array1.put(data[0]);

				String totalSpaceString = data[1].toString().trim();
				double totalSpaceKB = Double.parseDouble(totalSpaceString);
				double totalSpaceBytes = totalSpaceKB * 1024.0;
				double totalSpaceGB = totalSpaceBytes / (1024.0 * 1024.0 * 1024.0);
				double roundedTotalSpaceGB = Math.round(totalSpaceGB * 100.0) / 100.0;
				String roundedTotalSpaceGBString1 = String.format("%.2f", roundedTotalSpaceGB);

				totalSpaceString = data[2].toString().trim();
				totalSpaceKB = Double.parseDouble(totalSpaceString);
				totalSpaceBytes = totalSpaceKB * 1024.0;
				totalSpaceGB = totalSpaceBytes / (1024.0 * 1024.0 * 1024.0);
				roundedTotalSpaceGB = Math.round(totalSpaceGB * 100.0) / 100.0;
				String roundedTotalSpaceGBString2 = String.format("%.2f", roundedTotalSpaceGB);

				totalSpaceString = data[2].toString().trim();
				totalSpaceKB = Double.parseDouble(totalSpaceString);
				totalSpaceBytes = totalSpaceKB * 1024.0;
				totalSpaceGB = totalSpaceBytes / (1024.0 * 1024.0 * 1024.0);
				roundedTotalSpaceGB = Math.round(totalSpaceGB * 100.0) / 100.0;
				String roundedTotalSpaceGBString3 = String.format("%.2f", roundedTotalSpaceGB);

				array1.put(roundedTotalSpaceGBString1 + " GB");
				array1.put(roundedTotalSpaceGBString2 + " GB");
				array1.put(roundedTotalSpaceGBString3 + " GB");

				String stringValue = data[4].toString().trim();
				double value = Double.parseDouble(stringValue);
				String formattedValue = String.format("%.2f", value);

				array1.put(formattedValue + " %");

				array.put(array1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	public JSONArray viewSetParameterData(String ipAddress) {

		JSONArray array = null;
		JSONArray array1 = null;
		JSONArray counts = null;
		int count = 0;
		try {
			Criteria criteria = getSession().createCriteria(Process_Details.class);
			criteria.add(Restrictions.eq("ipAddress", ipAddress));
			List<Process_Details> dataList = criteria.list();

			array1 = new JSONArray();
			for (Process_Details groupData : dataList) {
				String checkboxHtml = "<input type='checkbox' ";

				// Set the checkbox state based on monitoringParam
				if (groupData.getMonitoringParam()) {
					checkboxHtml += "checked ";
				}

				checkboxHtml += "/>";
				array = new JSONArray();
				array.put(checkboxHtml);
				array.put(groupData.getId());
				array.put(groupData.getIpAddress());
				array.put(groupData.getAliasName());
				array.put(groupData.getProcessName());
				if (groupData.getMonitoringParam() == true) {
					count++;
				}
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public JSONArray basicInfoDetails(String ip_address) {
		System.out.println("basicInfoDetails Dao IMPL");
		JSONArray arrayJson = new JSONArray();
		JSONObject jsonObjData = new JSONObject();

		try {
			Query q1 = getSession().createQuery("from NodeHealthMonitoring where NODE_IP=:NODE_IP ");
			q1.setParameter("NODE_IP", ip_address);
			List<NodeHealthMonitoring> nodeHealthList = q1.list();
			for (NodeHealthMonitoring list : nodeHealthList) {
				jsonObjData.put("Version", list.getVERSION());
				jsonObjData.put("Model", list.getMAKE_AND_MODEL());
				jsonObjData.put("Uptime", list.getUPTIME());
				jsonObjData.put("Total_RAM", list.getTOTAL_MEMORY() / (1024 * 1024));
				jsonObjData.put("Used_RAM", list.getUSED_MEMORY() / (1024 * 1024));
				jsonObjData.put("Free_RAM", list.getFREE_MEMORY() / (1024 * 1024));
				jsonObjData.put("cpuUtilization", list.getCPU_UTILIZATION());
				jsonObjData.put("temperature", list.getTEMPERATURE());
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		try {
			arrayJson.put(jsonObjData);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		System.out.println(arrayJson);

		return arrayJson;
	}

	public JSONArray cpuAndMemoryUtilization(String ip_address) {
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {
			Query q = getSession().createQuery(
					"SELECT CPU_UTILIZATION, MEMORY_UTILIZATION  FROM DeviceHealth where device_ip=:nodeIP");
			q.setParameter("nodeIP", ip_address);

			List<?> dataList = q.list();
			System.out.println(dataList);
			System.out.println(dataList.size());
			array1 = new JSONArray();
			for (int i = 0; i < dataList.size(); i++) {
				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("cpuUtilization", row[0]);
				jsonObject.put("memoryUtilization", row[1]);
				array1.put(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return array1;
	}

	public String processPostData(List<processList> dataArray) {
		String result = null;
		try {

			for (processList item : dataArray) {
				System.out.println("ID: " + item.getId() + ", Checkbox: " + item.getCheckbox());
				Long id = Long.parseLong(item.getId());
				Query q = getSession()
						.createQuery("UPDATE Process_Details SET monitoring_param=:checkbox WHERE id=:ID");

				q.setParameter("ID", id);
				q.setParameter("checkbox", item.getCheckbox());
				int i = q.executeUpdate();
			}
			/*
			 * Query q = getSession().createQuery(
			 * "UPDATE process_details SET monitoring_param=:username WHERE ID=:ID");
			 * 
			 * q.setParameter("ID", id);
			 */

			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}
		return result;
	}

	public JSONArray viewServiceListing(String ipAddress) {

		JSONArray array = null;
		JSONArray array1 = null;
		JSONArray counts = null;
		int count = 0;
		try {
			Criteria criteria = getSession().createCriteria(Service_Details.class);
			criteria.add(Restrictions.eq("ipAddress", ipAddress));
			List<Service_Details> dataList = criteria.list();

			array1 = new JSONArray();
			for (Service_Details groupData : dataList) {

				String checkboxHtml = "<input type='checkbox' ";

				// Set the checkbox state based on monitoringParam
				if (groupData.getMonitoringParam()) {
					checkboxHtml += "checked ";
				}

				checkboxHtml += "/>";

				array = new JSONArray();
				array.put(checkboxHtml);
				/* array.put(groupData.getMonitoringParam()); */
				array.put(groupData.getId());
				array.put(groupData.getIpAddress());
				array.put(groupData.getAliasName());
				array.put(groupData.getServiceName());
				if (groupData.getMonitoringParam() == true) {
					count++;
				}
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;

	}

	public String servicePostData(List<processList> dataArray) {
		String result = null;
		try {

			for (processList item : dataArray) {
				System.out.println("ID: " + item.getId() + ", Checkbox: " + item.getCheckbox());
				Long id = Long.parseLong(item.getId());
				Query q = getSession()
						.createQuery("UPDATE Service_Details  SET monitoring_param=:checkbox WHERE id=:ID");

				q.setParameter("ID", id);
				q.setParameter("checkbox", item.getCheckbox());
				int i = q.executeUpdate();
			}

			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}
		return result;
	}

	public String getostype(String ipAddress) {
		String myreturn = "NA";
		try {
			Query q = getSession()
					.createSQLQuery("SELECT COALESCE(Os_type, 'NA') as Os_type FROM add_node WHERE DEVICE_IP='"
							+ ipAddress.trim() + "'");
			List<String> dataList = q.list();

			myreturn = dataList.get(0).toString();
			// array = new JSONArray();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return myreturn;
	}

}
