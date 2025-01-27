package npm.report.daoImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.json.JSONArray;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import npm.admin.beans.BWThresholdHistoryBean;
import npm.admin.beans.InterfaceAvailabilityBean;
import npm.admin.beans.InterfaceCRCHistoryBean;
import npm.admin.beans.InterfaceStatusReportBean;
import npm.configuration.AbstractDao;
import npm.model.AddNodeModel;
import npm.report.dao.InterfaceReportDao;

@Repository
@Transactional
public class InterfaceReportDaoImpl extends AbstractDao<Integer, AddNodeModel> implements InterfaceReportDao {

	public JSONArray getGroupInterfaceDetails(String group_name, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		int srno = 0;
		String query = "";
		if (group_name.trim().equalsIgnoreCase("all")) {
			query = "SELECT \r\n" + "    interface.INTERFACE_NAME,\r\n" + "    interface.INTERFACE_IP,\r\n"
					+ "    interface.ALIAS_NAME,\r\n" + "    interface.OPER_STATUS,\r\n" + "    interface.NODE_IP,\r\n"
					+ "    add_node.DEVICE_NAME,\r\n" + "    add_node.LOCATION,\r\n" + "    add_node.DISTRICT,\r\n"
					+ "    add_node.STATE,\r\n" + "    add_node.ZONE,\r\n" + "    add_node.GROUP_NAME\r\n"
					+ "FROM  \r\n" + "    interface_monitoring interface \r\n" + "LEFT JOIN \r\n"
					+ "    add_node add_node ON add_node.DEVICE_IP = interface.NODE_IP\r\n" + "WHERE \r\n"
					+ "    interface.MONITORING_PARAM = 'Yes' \r\n" + "AND " + userScopeData + "\r\n" + "GROUP BY \r\n"
					+ "    interface.NODE_IP, interface.INTERFACE_NAME, \r\n"
					+ "    interface.INTERFACE_IP, interface.ALIAS_NAME, interface.OPER_STATUS,\r\n"
					+ "    add_node.DEVICE_NAME, add_node.LOCATION, add_node.DISTRICT,\r\n"
					+ "    add_node.STATE, add_node.ZONE, add_node.GROUP_NAME";
		} else {

			query = "SELECT \r\n" + "    interface.INTERFACE_NAME,\r\n" + "    interface.INTERFACE_IP,\r\n"
					+ "    interface.ALIAS_NAME,\r\n" + "    interface.OPER_STATUS,\r\n" + "    interface.NODE_IP,\r\n"
					+ "    add_node.DEVICE_NAME,\r\n" + "    add_node.LOCATION,\r\n" + "    add_node.DISTRICT,\r\n"
					+ "    add_node.STATE,\r\n" + "    add_node.ZONE,\r\n" + "    add_node.GROUP_NAME\r\n"
					+ "FROM  \r\n" + "    interface_monitoring interface \r\n" + "LEFT JOIN \r\n"
					+ "    add_node add_node ON add_node.DEVICE_IP = interface.NODE_IP\r\n" + "WHERE \r\n"
					+ "    add_node.GROUP_NAME = '" + group_name + "' \r\n"
					+ "    AND interface.MONITORING_PARAM = 'Yes' \r\n" + "AND " + userScopeData + "\r\n"
					+ "GROUP BY \r\n" + "    interface.NODE_IP, interface.INTERFACE_NAME, \r\n"
					+ "    interface.INTERFACE_IP, interface.ALIAS_NAME, interface.OPER_STATUS,\r\n"
					+ "    add_node.DEVICE_NAME, add_node.LOCATION, add_node.DISTRICT,\r\n"
					+ "    add_node.STATE, add_node.ZONE, add_node.GROUP_NAME";
		}

		Query q = getSession().createSQLQuery(query);
		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());

		for (Object[] a : data) {
			srno = srno + 1;
			arrayData = new JSONArray();
			arrayData.put("<input type='checkbox' id=" + a[4] + "~" + a[0]
					+ " name='ipAddressCheck' class='checkers' value='" + a[4] + "~" + a[0] + "'/>");
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
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getGroupInterfaceIPDetails(String group_name, String userScopeData) {

		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		int srno = 0;
		String query = "";
		if (group_name.trim().equalsIgnoreCase("all")) {
			query = "SELECT \r\n" + "    interface.INTERFACE_NAME,\r\n" + "    interface.Interface_IP_Assign,\r\n"
					+ "    interface.ALIAS_NAME,\r\n" + "    interface.OPER_STATUS,\r\n" + "    interface.NODE_IP,\r\n"
					+ "    add_node.DEVICE_NAME,\r\n" + "    add_node.LOCATION,\r\n" + "    add_node.DISTRICT,\r\n"
					+ "    add_node.STATE,\r\n" + "    add_node.ZONE,\r\n" + "    add_node.GROUP_NAME\r\n"
					+ "FROM  \r\n" + "    interface_monitoring interface \r\n" + "LEFT JOIN \r\n"
					+ "    add_node add_node ON add_node.DEVICE_IP = interface.NODE_IP\r\n" + "WHERE \r\n"
					+ "    interface.MONITORING_PARAM = 'Yes' \r\n" + "AND " + userScopeData + "\r\n" + "GROUP BY \r\n"
					+ "    interface.NODE_IP, interface.INTERFACE_NAME, \r\n"
					+ "    interface.Interface_IP_Assign, interface.ALIAS_NAME, interface.OPER_STATUS,\r\n"
					+ "    add_node.DEVICE_NAME, add_node.LOCATION, add_node.DISTRICT,\r\n"
					+ "    add_node.STATE, add_node.ZONE, add_node.GROUP_NAME";
		} else {

			query = "SELECT \r\n" + "    interface.INTERFACE_NAME,\r\n" + "    interface.Interface_IP_Assign,\r\n"
					+ "    interface.ALIAS_NAME,\r\n" + "    interface.OPER_STATUS,\r\n" + "    interface.NODE_IP,\r\n"
					+ "    add_node.DEVICE_NAME,\r\n" + "    add_node.LOCATION,\r\n" + "    add_node.DISTRICT,\r\n"
					+ "    add_node.STATE,\r\n" + "    add_node.ZONE,\r\n" + "    add_node.GROUP_NAME\r\n"
					+ "FROM  \r\n" + "    interface_monitoring interface \r\n" + "LEFT JOIN \r\n"
					+ "    add_node add_node ON add_node.DEVICE_IP = interface.NODE_IP\r\n" + "WHERE \r\n"
					+ "    add_node.GROUP_NAME = '" + group_name + "' \r\n"
					+ "    AND interface.MONITORING_PARAM = 'Yes' \r\n" + "AND " + userScopeData + "\r\n"
					+ "GROUP BY \r\n" + "    interface.NODE_IP, interface.INTERFACE_NAME, \r\n"
					+ "    interface.Interface_IP_Assign, interface.ALIAS_NAME, interface.OPER_STATUS,\r\n"
					+ "    add_node.DEVICE_NAME, add_node.LOCATION, add_node.DISTRICT,\r\n"
					+ "    add_node.STATE, add_node.ZONE, add_node.GROUP_NAME";
		}

		Query q = getSession().createSQLQuery(query);
		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());

		for (Object[] a : data) {
			srno = srno + 1;
			arrayData = new JSONArray();
			arrayData.put("<input type='checkbox' id=" + a[1] + " name='ipAddressCheck' class='checkers' value='" + a[1]
					+ "'/>");
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
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public List<InterfaceStatusReportBean> InterfaceStatusReport(String from_date, String to_date,
			List<String> ip_interface_list) {

		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "select report.ID,report.INTERFACE_NAME,report.INTERFACE_STATUS,report.INTERFACE_STATUS_TYPE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join interface_status_history report on node.DEVICE_IP=report.NODE_IP where report.IP_INTERFACE in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<InterfaceStatusReportBean> dataList = new ArrayList<InterfaceStatusReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				InterfaceStatusReportBean bean = new InterfaceStatusReportBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setINTERFACE_STATUS(a[2].toString());
				bean.setINTERFACE_STATUS_TYPE(a[3].toString());
				bean.setEVENT_TIMESTAMP(a[4].toString());

				bean.setNODE_IP(a[5].toString());
				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}

	// Interface Bandwidth History

	public JSONArray InterfaceBandwidthHistoryReport(String from_date, String to_date, List<String> ip_interface_list) {

		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");

		String query = "select report.ID,report.INTERFACE_NAME,report.OUT_TRAFFIC,report.IN_TRAFFIC,report.IP_INTERFACE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join interface_bw_history report on node.DEVICE_IP=report.NODE_IP where report.IP_INTERFACE in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";

		Query q = getSession().createSQLQuery(query);

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());

		int sr = 0;
		JSONArray finalArray = new JSONArray();
		JSONArray array = null;
		for (Object[] a : data) {
			try {
				array = new JSONArray();
				sr++;
				array.put(sr);
				array.put(a[1]);
				array.put(a[2]);
				array.put(a[3]);
				array.put(String.format("%.2f", (Double.valueOf(a[2].toString()) / 1024)));
				array.put(String.format("%.2f", (Double.valueOf(a[3].toString()) / 1024)));
				array.put(a[4]);
				array.put(a[5]);
				array.put(a[6]);
				array.put(a[7]);
				array.put(a[8]);
				array.put(a[9]);
				array.put(a[10]);
				array.put(a[11]);
				array.put(a[12]);

				finalArray.put(array);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return finalArray;
	}

	// Bandwidth Threshold History

	public List<BWThresholdHistoryBean> bandwidthThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_interface_list) {

		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");
		System.out.println(ip_interface_list);
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "select report.ID,report.INTERFACE_NAME,report.BW_THRESHOLD,report.BW_VAL,report.BW_STATUS,report.IP_INTERFACE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME, report.BW_TYPE from add_node node join BW_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where report.IP_INTERFACE in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<BWThresholdHistoryBean> dataList = new ArrayList<BWThresholdHistoryBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				BWThresholdHistoryBean bean = new BWThresholdHistoryBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setBW_THRESHOLD(Double.parseDouble(a[2].toString()));
				// bean.setBW_VAL(Double.parseDouble(a[3].toString()));
				long longValue = new BigDecimal(a[3].toString()).longValue();
				bean.setBW_VAL(String.valueOf(longValue));
				bean.setBW_STATUS(a[4].toString());
				bean.setBW_TYPE(a[14].toString());
				bean.setIP_INTERFACE(a[5].toString());
				bean.setEVENT_TIMESTAMP(a[6].toString());

				bean.setNODE_IP(a[7].toString());
				bean.setDEVICE_NAME(a[8].toString());
				bean.setLOCATION(a[9].toString());
				bean.setDISTRICT(a[10].toString());
				bean.setSTATE(a[11].toString());
				bean.setZONE(a[12].toString());
				bean.setGROUP_NAME(a[13].toString());
				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}

	// Interface CRC History

	public List<InterfaceCRCHistoryBean> interfaceCrcHistoryReport(String from_date, String to_date,
			List<String> ip_interface_list) {

		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "select report.ID,report.INTERFACE_NAME,report.OLD_CRC_VAL,report.NEW_CRC_VAL,report.IP_INTERFACE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join INTERFACE_CRC_HISTORY report on node.DEVICE_IP=report.NODE_IP where report.IP_INTERFACE in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<InterfaceCRCHistoryBean> dataList = new ArrayList<InterfaceCRCHistoryBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				InterfaceCRCHistoryBean bean = new InterfaceCRCHistoryBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setOLD_CRC_VAL(Long.parseLong(a[2].toString()));
				bean.setNEW_CRC_VAL(Long.parseLong(a[3].toString()));
				bean.setIP_INTERFACE(a[4].toString());
				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setNODE_IP(a[6].toString());
				bean.setDEVICE_NAME(a[7].toString());
				bean.setLOCATION(a[8].toString());
				bean.setDISTRICT(a[9].toString());
				bean.setSTATE(a[10].toString());
				bean.setZONE(a[11].toString());
				bean.setGROUP_NAME(a[12].toString());
				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}

	// Interface Availability

	public List<InterfaceAvailabilityBean> interfaceAvailabilityReport(String from_date, String to_date,
			List<String> ip_interface_list) {

		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "select report.ID,report.INTERFACE_NAME,report.UPTIME_PERCENT,report.UPTIME_MILISECONDS,report.DOWNTIME_PERCENT,report.DOWNTIME_MILISECONDS,report.IP_INTERFACE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join INTERFACE_AVAILABILITY report on node.DEVICE_IP=report.NODE_IP where report.IP_INTERFACE in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<InterfaceAvailabilityBean> dataList = new ArrayList<InterfaceAvailabilityBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				String var_uptime = "NA";
				try {
					int up_second_day = (Integer) a[3];

					int day = (int) TimeUnit.SECONDS.toDays(up_second_day);
					long hours = TimeUnit.SECONDS.toHours(up_second_day) - (day * 24);
					long minute = TimeUnit.SECONDS.toMinutes(up_second_day)
							- (TimeUnit.SECONDS.toHours(up_second_day) * 60);
					long second = TimeUnit.SECONDS.toSeconds(up_second_day)
							- (TimeUnit.SECONDS.toMinutes(up_second_day) * 60);
					var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

				} catch (Exception ex) {
					System.out.println("Exception Uptime cal:" + ex);
				}
				System.out.println(a[1].toString() + "var_uptime:" + var_uptime);

				String var_downtime = "NA";
				try {
					int up_second_day = (Integer) a[5];
					int day = (int) TimeUnit.SECONDS.toDays(up_second_day);
					long hours = TimeUnit.SECONDS.toHours(up_second_day) - (day * 24);
					long minute = TimeUnit.SECONDS.toMinutes(up_second_day)
							- (TimeUnit.SECONDS.toHours(up_second_day) * 60);
					long second = TimeUnit.SECONDS.toSeconds(up_second_day)
							- (TimeUnit.SECONDS.toMinutes(up_second_day) * 60);
					var_downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

				} catch (Exception ex) {
					System.out.println("excep 333 util:" + ex);
				}

				InterfaceAvailabilityBean bean = new InterfaceAvailabilityBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setUPTIME_PERCENT(Double.parseDouble(a[2].toString()));
				bean.setUPTIME(var_uptime);
				bean.setDOWNTIME_PERCENT(Double.parseDouble(a[4].toString()));
				bean.setDOWNTIME(var_downtime);
				bean.setIP_INTERFACE(a[6].toString());
				bean.setEVENT_TIMESTAMP(a[7].toString());

				bean.setNODE_IP(a[8].toString());
				bean.setDEVICE_NAME(a[9].toString());
				bean.setLOCATION(a[10].toString());
				bean.setDISTRICT(a[11].toString());
				bean.setSTATE(a[12].toString());
				bean.setZONE(a[13].toString());
				bean.setGROUP_NAME(a[14].toString());

				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}

	// Get Interface Name By IP
	public String getInterfaceNameByIP(String ip_address) {

		String all_interface = "";
		System.out.println("IP = " + ip_address);
		String query = "select interface_name from INTERFACE_MONITORING where node_ip =:ip_address";
		Query q = getSession().createSQLQuery(query);
		q.setParameter("ip_address", ip_address);
		// List<InterfaceMonitoring> list = q.list();

		List<String> data = q.list();
		for (String a : data) {
			String interface_name = a.toString();
			all_interface += interface_name + "~";
		}
		System.out.println("all interface:" + all_interface);
		return all_interface;
	}

	public JSONArray getGroupInterface(String group_name) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		int srno = 0;

		if (group_name.equals("All")) {
			try {

				String query = "select  interface.INTERFACE_NAME,interface.INTERFACE_IP,interface.ALIAS_NAME,interface.OPER_STATUS,interface.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME, interface.ID, interface.BW_HISTORY_PARAM, interface.CRC_HISTORY_PARAM, interface.MONITORING_PARAM, interface.BW_THRESHOLD from  interface_monitoring interface LEFT JOIN add_node node  on node.DEVICE_IP=interface.NODE_IP "
						+ " GROUP BY "
						+ "interface.INTERFACE_NAME,interface.INTERFACE_IP,interface.ALIAS_NAME,interface.OPER_STATUS,interface.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME, interface.ID, interface.BW_HISTORY_PARAM, interface.CRC_HISTORY_PARAM, interface.MONITORING_PARAM, interface.BW_THRESHOLD";
				Query q = getSession().createSQLQuery(query);
				List<Object[]> data = q.list();
				System.out.println("Size Data:" + data.size());

				for (Object[] a : data) {
					srno = srno + 1;
					arrayData = new JSONArray();
					arrayData.put(
							"<input type='checkbox' name='ipAddressCheck' class='checkers' value='" + a[11] + "'/>");
					arrayData.put(srno);
					arrayData.put(a[0]);
					arrayData.put(a[1]);
					arrayData.put(a[2]);
					arrayData.put(a[3]);

					arrayData.put(a[12]);
					arrayData.put(a[13]);
					arrayData.put(a[14]);
					arrayData.put(a[15]);

					arrayData.put(a[4]);
					arrayData.put(a[5]);
					arrayData.put(a[10]);
					arrayData.put(a[6]);
					arrayData.put(a[7]);
					arrayData.put(a[8]);
					arrayData.put(a[9]);

					arrayMain.put(arrayData);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {

				String query = "select  interface.INTERFACE_NAME,interface.INTERFACE_IP,interface.ALIAS_NAME,interface.OPER_STATUS,interface.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME, interface.ID, interface.BW_HISTORY_PARAM, interface.CRC_HISTORY_PARAM, interface.MONITORING_PARAM, interface.BW_THRESHOLD from  interface_monitoring interface LEFT JOIN add_node node  on node.DEVICE_IP=interface.NODE_IP where node.GROUP_NAME='"
						+ group_name + "'  GROUP BY "
						+ "interface.INTERFACE_NAME,interface.INTERFACE_IP,interface.ALIAS_NAME,interface.OPER_STATUS,interface.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME, interface.ID, interface.BW_HISTORY_PARAM, interface.CRC_HISTORY_PARAM, interface.MONITORING_PARAM, interface.BW_THRESHOLD";
				Query q = getSession().createSQLQuery(query);
				List<Object[]> data = q.list();
				System.out.println("Size Data:" + data.size());

				for (Object[] a : data) {
					srno = srno + 1;
					arrayData = new JSONArray();
					arrayData.put(
							"<input type='checkbox' name='ipAddressCheck' class='checkers' value='" + a[11] + "'/>");
					arrayData.put(srno);
					arrayData.put(a[0]);
					arrayData.put(a[1]);
					arrayData.put(a[2]);
					arrayData.put(a[3]);

					arrayData.put(a[12]);
					arrayData.put(a[13]);
					arrayData.put(a[14]);
					arrayData.put(a[15]);

					arrayData.put(a[4]);
					arrayData.put(a[5]);
					arrayData.put(a[10]);
					arrayData.put(a[6]);
					arrayData.put(a[7]);
					arrayData.put(a[8]);
					arrayData.put(a[9]);

					arrayMain.put(arrayData);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return arrayMain;

	}

	public JSONArray getPortSummary(String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery("SELECT im.NODE_IP, COUNT(*), add_node.location\r\n"
				+ "FROM interface_monitoring im\r\n" + "JOIN add_node add_node ON im.NODE_IP=add_node.DEVICE_IP\r\n"
				+ "WHERE " + userScopeData + "\r\n" + "GROUP BY im.NODE_IP");
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put(a[0]);
//			arrayData.put(a[1]);
			arrayData.put("<span style='color:blue' onclick=\"getTotalPortSummaryDetails('" + a[0] + "')\">" + a[1]
					+ "</span>");
//			arrayData.put("<p onclick='getPortSummaryDetails('total')'>"+a[1]+"</p>");

			BigInteger usedCount = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM interface_monitoring im JOIN add_node add_node ON im.NODE_IP=add_node.DEVICE_IP WHERE im.ADMIN_STATUS='up' AND im.NODE_IP='"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();
			BigInteger unUsedCount = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM interface_monitoring im JOIN add_node add_node ON im.NODE_IP=add_node.DEVICE_IP WHERE im.ADMIN_STATUS='down' AND im.NODE_IP='"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();
			BigInteger upPorts = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM   interface_monitoring im JOIN add_node add_node ON im.NODE_IP=add_node.DEVICE_IP WHERE im.OPER_STATUS='up' AND im.NODE_IP='"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();
			BigInteger downPorts = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM   interface_monitoring im JOIN add_node add_node ON im.NODE_IP=add_node.DEVICE_IP WHERE im.OPER_STATUS='down' AND im.NODE_IP='"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();
//			BigInteger unUtilizedPorts = (BigInteger) getSession().createSQLQuery("SELECT COUNT(*) FROM (\r\n"
//					+ "    SELECT ibh.NODE_IP, ibh.INTERFACE_NAME, SUM(ibh.OUT_TRAFFIC) AS total_traffic\r\n"
//					+ "    FROM interface_bw_history ibh\r\n"
//					+ "    JOIN add_node add_node ON ibh.NODE_IP = add_node.DEVICE_IP\r\n" + "    WHERE ibh.NODE_IP = '"
//					+ a[0] + "'\r\n" + "AND " + userScopeData + "\r\n"
//					+ "    GROUP BY ibh.NODE_IP, ibh.INTERFACE_NAME\r\n" + "    HAVING total_traffic = 0\r\n"
//					+ ") AS SUBQUERY").getSingleResult();

			BigInteger unUtilizedPorts = (BigInteger) getSession().createSQLQuery("SELECT\r\n" + "        COUNT(*) \r\n"
					+ "    FROM\r\n" + "        (      SELECT\r\n" + "            ibh.NODE_IP,\r\n"
					+ "            ibh.INTERFACE_NAME,\r\n" + "            ibh.OUT_TRAFFIC AS total_traffic      \r\n"
					+ "        FROM\r\n" + "            interface_monitoring ibh      \r\n" + "        JOIN\r\n"
					+ "            add_node add_node \r\n"
					+ "                ON ibh.NODE_IP = add_node.DEVICE_IP      \r\n" + "        WHERE\r\n"
					+ "            ibh.NODE_IP = '" + a[0] + "'  \r\n" + "            AND " + userScopeData
					+ "        GROUP BY\r\n" + "            ibh.NODE_IP,\r\n" + "            ibh.INTERFACE_NAME,\r\n"
					+ "             ibh.OUT_TRAFFIC\r\n" + "        HAVING\r\n"
					+ "            total_traffic = 0  ) AS SUBQUERY").getSingleResult();

			arrayData.put("<span style='color:blue' onclick=\"getUsedPortSummaryDetails('" + a[0] + "')\">" + usedCount
					+ "</span>");
			arrayData.put("<span style='color:blue' onclick=\"getUnUsedPortSummaryDetails('" + a[0] + "')\">"
					+ unUsedCount + "</span>");
			arrayData.put("<span style='color:blue' onclick=\"getUpPortSummaryDetails('" + a[0] + "')\">" + upPorts
					+ "</span>");
			arrayData.put("<span style='color:blue' onclick=\"getDownPortSummaryDetails('" + a[0] + "')\">" + downPorts
					+ "</span>");
			arrayData.put("<span style='color:blue' onclick=\"getunUtlilizedPortSummaryDetails('" + a[0] + "')\">"
					+ unUtilizedPorts + "</span>");
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getTotalPortSummaryDetails(String ip, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		int sr = 0;
		Query query = getSession().createSQLQuery(
				"SELECT im.INTERFACE_NAME, im.INTERFACE_MACADDRESS, im.ALIAS_NAME, im.INTERFACE_IP, im.ADMIN_STATUS, im.OPER_STATUS FROM  interface_monitoring im JOIN add_node add_node ON im.NODE_IP=add_node.DEVICE_IP where im.NODE_IP = '"
						+ ip + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			sr++;
			arrayData = new JSONArray();
			arrayData.put(sr);
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getUsedPortSummaryDetails(String ip, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		int sr = 0;
		Query query = getSession().createSQLQuery(
				"SELECT im.INTERFACE_NAME, im.INTERFACE_MACADDRESS, im.ALIAS_NAME, im.INTERFACE_IP, im.ADMIN_STATUS, im.OPER_STATUS FROM  interface_monitoring im JOIN add_node add_node ON im.NODE_IP=add_node.DEVICE_IP where im.ADMIN_STATUS='up' AND im.NODE_IP = '"
						+ ip + "'");
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			sr++;
			arrayData = new JSONArray();
			arrayData.put(sr);
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getUnUsedPortSummaryDetails(String ip, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		int sr = 0;
		Query query = getSession().createSQLQuery(
				"SELECT im.INTERFACE_NAME, im.INTERFACE_MACADDRESS, im.ALIAS_NAME, im.INTERFACE_IP, im.ADMIN_STATUS, im.OPER_STATUS FROM  interface_monitoring im JOIN add_node add_node ON im.NODE_IP=add_node.DEVICE_IP where im.ADMIN_STATUS='down' AND im.NODE_IP = '"
						+ ip + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			sr++;
			arrayData = new JSONArray();
			arrayData.put(sr);
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getUpPortSummaryDetails(String ip, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		int sr = 0;
		Query query = getSession().createSQLQuery(
				"SELECT im.INTERFACE_NAME, im.INTERFACE_MACADDRESS, im.ALIAS_NAME, im.INTERFACE_IP, im.ADMIN_STATUS, im.OPER_STATUS FROM  interface_monitoring im JOIN add_node add_node ON im.NODE_IP=add_node.DEVICE_IP where im.OPER_STATUS='up' AND im.NODE_IP = '"
						+ ip + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			sr++;
			arrayData = new JSONArray();
			arrayData.put(sr);
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getDownPortSummaryDetails(String ip, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		int sr = 0;
		Query query = getSession().createSQLQuery(
				"SELECT im.INTERFACE_NAME, im.INTERFACE_MACADDRESS, im.ALIAS_NAME, im.INTERFACE_IP, im.ADMIN_STATUS, im.OPER_STATUS FROM  interface_monitoring im JOIN add_node add_node ON im.NODE_IP=add_node.DEVICE_IP where im.OPER_STATUS='down' AND im.NODE_IP = '"
						+ ip + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			sr++;
			arrayData = new JSONArray();
			arrayData.put(sr);
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray unutilizedPortSummaryDetails(String ip, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		int sr = 0;
		Query query = getSession()
				.createSQLQuery("SELECT ibh.NODE_IP, ibh.INTERFACE_NAME, SUM(ibh.OUT_TRAFFIC) AS total_traffic\r\n"
						+ "FROM interface_bw_history ibh\r\n"
						+ "JOIN add_node add_node ON ibh.NODE_IP = add_node.DEVICE_IP\r\n" + "WHERE ibh.NODE_IP = '"
						+ ip + "'\r\n" + "GROUP BY ibh.NODE_IP, ibh.INTERFACE_NAME\r\n" + "HAVING total_traffic = 0");
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			sr++;
			arrayData = new JSONArray();
			arrayData.put(sr);
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray groupSummary(String userScopeData) {
		JSONArray arrayMain = new JSONArray();

		// Single query to fetch all required data at once
		Query query = getSession().createSQLQuery("SELECT add_node.GROUP_NAME, " + "       COUNT(*) AS totalNodes, "
				+ "       SUM(CASE WHEN nm.NODE_STATUS = 'Up' THEN 1 ELSE 0 END) AS upNodes, "
				+ "       SUM(CASE WHEN nm.NODE_STATUS = 'Down' THEN 1 ELSE 0 END) AS downNodes, "
				+ "       SUM(CASE WHEN nm.NODE_STATUS = 'Warning' THEN 1 ELSE 0 END) AS warningNodes "
				+ "FROM add_node add_node " + "JOIN node_monitoring nm ON add_node.DEVICE_IP = nm.NODE_IP "
				+ "WHERE add_node.MONITORING_PARAM = 'Yes' AND " + userScopeData + " GROUP BY add_node.GROUP_NAME");

		List<Object[]> data = query.list();

		// Process the data in a single loop
		for (Object[] row : data) {
			JSONArray arrayData = new JSONArray();
			String groupName = (String) row[0];

			Long totalNodes = ((Number) row[1]).longValue();
			Long upNodes = ((Number) row[2]).longValue();
			Long downNodes = ((Number) row[3]).longValue();
			Long warningNodes = ((Number) row[4]).longValue();

			arrayData.put(groupName);
			arrayData.put("<span style='color:blue' onclick=\"getTotalNodeSummaryDetails('" + groupName + "')\">"
					+ totalNodes + "</span>");
			arrayData.put("<span style='color:lightgreen' onclick=\"getupNodeSummaryDetails('" + groupName + "')\">"
					+ upNodes + "</span>");
			arrayData.put("<span style='color:red' onclick=\"getdownNodeSummaryDetails('" + groupName + "')\">"
					+ downNodes + "</span>");
			arrayData.put("<span style='color:orange' onclick=\"getwarningNodeSummaryDetails('" + groupName + "')\">"
					+ warningNodes + "</span>");

			arrayMain.put(arrayData);
		}

		return arrayMain;
	}

	public JSONArray getupNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest request) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
//		Query query = getSession().createSQLQuery(
//				"SELECT add_node.DEVICE_IP,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,nm.NODE_STATUS,nm.STATUS_TIMESTAMP FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND nm.NODE_STATUS = 'Up' AND add_node.GROUP_NAME = '"
//						+ group_name + "' AND " + userScopeData);
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,NM.NODE_STATUS,NM.STATUS_TIMESTAMP, CASE WHEN COUNT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN 1 END) = 0 THEN 'NA' ELSE GROUP_CONCAT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN CONCAT(im.INTERFACE_NAME, ' (', COALESCE(im.Interface_IP_ICMP_Status, 'NA'), ')') END SEPARATOR ', ') END AS INTERFACE_NAMES FROM add_node add_node JOIN node_monitoring NM ON add_node.DEVICE_IP = NM.NODE_IP LEFT JOIN interface_monitoring im ON im.NODE_IP = add_node.DEVICE_IP AND im.MONITORING_PARAM = 'Yes' WHERE "
						+ " add_node.GROUP_NAME = '" + group_name + "' AND NM.NODE_STATUS = 'Up' AND " + userScopeData
						+ " GROUP BY add_node.DEVICE_IP");
		List<Object[]> data = query.list();

		String protocol = request.getScheme();
		// Get the host (domain and port)
		String host = request.getServerName();
		int port = request.getServerPort();
		if (port != 80 && port != 443) {
			host += ":" + port;
		}

		// Get the context path (first path after the domain)
		String contextPath = request.getContextPath();

		// Build the base URL
		String baseUrl = protocol + "://" + host + contextPath;

		// Define node detail URL
		String nodeDetailUrl = baseUrl + "/dashboard/nodeDetailsPage?nodeIP=";

		for (Object[] a : data) {
			arrayData = new JSONArray();
//			arrayData.put((a[0] == null) ? "NA"
//					: a[0].equals("") ? "NA" : "<a href=\"" + nodeDetailUrl + a[0] + "\">" + a[0] + "</a>");
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);

			String interfacenames = a[6].toString().trim();
//			System.out.println("int name :" + interfacenames);
			String interfacetoput = "";
			if (!interfacenames.equalsIgnoreCase("NA")) {
//				int token = 0;
				String[] parts = interfacenames.split(",");
//				System.out.println("inside");

				// Trim whitespace and print each part
				for (String part : parts) {
//					token++;
//					System.out.println(part.trim());
					String intrname = part.trim();
//					System.out.println("inside " + intrname);

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

					if (bracketValue.equalsIgnoreCase("up")) {
						interfacetoput += intrname
								+ "&nbsp;<small class=\"text-success mr-1\"> <i class=\"fas fa-arrow-up\"></i> Up </small><br>";
					} else if (bracketValue.equalsIgnoreCase("down")) {
						interfacetoput += intrname
								+ "&nbsp;<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
					} else if (bracketValue.equalsIgnoreCase("warning")) {
						interfacetoput += intrname + "&nbsp;<small class=\"text-danger mr-1\"> warning </small><br>";
					} else {
						interfacetoput += intrname + "&nbsp;(Ip Not Assigned)<br>";
					}

				}

			} else {
				interfacetoput = "NA";
			}

			arrayData.put(interfacetoput);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getdownNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest request) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
//		Query query = getSession().createSQLQuery(
//				"SELECT add_node.DEVICE_IP,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,nm.NODE_STATUS,nm.STATUS_TIMESTAMP FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND nm.NODE_STATUS = 'Down' AND add_node.GROUP_NAME = '"
//						+ group_name + "' AND " + userScopeData);
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,NM.NODE_STATUS,NM.STATUS_TIMESTAMP, CASE WHEN COUNT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN 1 END) = 0 THEN 'NA' ELSE GROUP_CONCAT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN CONCAT(im.INTERFACE_NAME, ' (', COALESCE(im.Interface_IP_ICMP_Status, 'NA'), ')') END SEPARATOR ', ') END AS INTERFACE_NAMES FROM add_node add_node JOIN node_monitoring NM ON add_node.DEVICE_IP = NM.NODE_IP LEFT JOIN interface_monitoring im ON im.NODE_IP = add_node.DEVICE_IP AND im.MONITORING_PARAM = 'Yes' WHERE "
						+ " add_node.GROUP_NAME = '" + group_name + "' AND NM.NODE_STATUS = 'Down' AND " + userScopeData
						+ " GROUP BY add_node.DEVICE_IP");

		List<Object[]> data = query.list();

		String protocol = request.getScheme();
		// Get the host (domain and port)
		String host = request.getServerName();
		int port = request.getServerPort();
		if (port != 80 && port != 443) {
			host += ":" + port;
		}

		// Get the context path (first path after the domain)
		String contextPath = request.getContextPath();

		// Build the base URL
		String baseUrl = protocol + "://" + host + contextPath;

		// Define node detail URL
		String nodeDetailUrl = baseUrl + "/dashboard/nodeDetailsPage?nodeIP=";

		for (Object[] a : data) {
			arrayData = new JSONArray();
//			arrayData.put((a[0] == null) ? "NA"
//					: a[0].equals("") ? "NA" : "<a href=\"" + nodeDetailUrl + a[0] + "\">" + a[0] + "</a>");
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);

			String interfacenames = a[6].toString().trim();
//			System.out.println("int name :" + interfacenames);
			String interfacetoput = "";
			if (!interfacenames.equalsIgnoreCase("NA")) {
//				int token = 0;
				String[] parts = interfacenames.split(",");
//				System.out.println("inside");

				// Trim whitespace and print each part
				for (String part : parts) {
//					token++;
//					System.out.println(part.trim());
					String intrname = part.trim();
//					System.out.println("inside " + intrname);

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

//					if (a[4].toString().equalsIgnoreCase("down")) {
//						interfacetoput += intrname
//								+ "&nbsp;<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
//
//					} else {
					if (bracketValue.equalsIgnoreCase("up")) {
						interfacetoput += intrname
								+ "&nbsp;<small class=\"text-success mr-1\"> <i class=\"fas fa-arrow-up\"></i> Up </small><br>";
					} else if (bracketValue.equalsIgnoreCase("down")) {
						interfacetoput += intrname
								+ "&nbsp;<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
					} else if (bracketValue.equalsIgnoreCase("warning")) {
						interfacetoput += intrname + "&nbsp;<small class=\"text-danger mr-1\"> warning </small><br>";
					} else {
						interfacetoput += intrname + "&nbsp;(Ip Not Assigned)<br>";
					}

//					}

				}

			} else {
				interfacetoput = "NA";
			}
			arrayData.put(interfacetoput);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getwarningNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest request) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
//		Query query = getSession().createSQLQuery(
//				"SELECT add_node.DEVICE_IP,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,nm.NODE_STATUS,nm.STATUS_TIMESTAMP FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND nm.NODE_STATUS = 'Warning' AND add_node.GROUP_NAME = '"
//						+ group_name + "' AND " + userScopeData);
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,NM.NODE_STATUS,NM.STATUS_TIMESTAMP, CASE WHEN COUNT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN 1 END) = 0 THEN 'NA' ELSE GROUP_CONCAT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN CONCAT(im.INTERFACE_NAME, ' (', COALESCE(im.Interface_IP_ICMP_Status, 'NA'), ')') END SEPARATOR ', ') END AS INTERFACE_NAMES FROM add_node add_node JOIN node_monitoring NM ON add_node.DEVICE_IP = NM.NODE_IP LEFT JOIN interface_monitoring im ON im.NODE_IP = add_node.DEVICE_IP AND im.MONITORING_PARAM = 'Yes' WHERE "
						+ " add_node.GROUP_NAME = '" + group_name + "' AND NM.NODE_STATUS = 'Warning' AND "
						+ userScopeData + " GROUP BY add_node.DEVICE_IP");
		List<Object[]> data = query.list();

		String protocol = request.getScheme();
		// Get the host (domain and port)
		String host = request.getServerName();
		int port = request.getServerPort();
		if (port != 80 && port != 443) {
			host += ":" + port;
		}

		// Get the context path (first path after the domain)
		String contextPath = request.getContextPath();

		// Build the base URL
		String baseUrl = protocol + "://" + host + contextPath;

		// Define node detail URL
		String nodeDetailUrl = baseUrl + "/dashboard/nodeDetailsPage?nodeIP=";

		for (Object[] a : data) {
			arrayData = new JSONArray();
//			arrayData.put((a[0] == null) ? "NA"
//					: a[0].equals("") ? "NA" : "<a href=\"" + nodeDetailUrl + a[0] + "\">" + a[0] + "</a>");
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);

			String interfacenames = a[6].toString().trim();
//			System.out.println("int name :" + interfacenames);
			String interfacetoput = "";
			if (!interfacenames.equalsIgnoreCase("NA")) {
//				int token = 0;
				String[] parts = interfacenames.split(",");
//				System.out.println("inside");

				// Trim whitespace and print each part
				for (String part : parts) {
//					token++;
//					System.out.println(part.trim());
					String intrname = part.trim();
//					System.out.println("inside " + intrname);

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

					if (bracketValue.equalsIgnoreCase("up")) {
						interfacetoput += intrname
								+ "&nbsp;<small class=\"text-success mr-1\"> <i class=\"fas fa-arrow-up\"></i> Up </small><br>";
					} else if (bracketValue.equalsIgnoreCase("down")) {
						interfacetoput += intrname
								+ "&nbsp;<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
					} else if (bracketValue.equalsIgnoreCase("warning")) {
						interfacetoput += intrname + "&nbsp;<small class=\"text-danger mr-1\"> warning </small><br>";
					} else {
						interfacetoput += intrname + "&nbsp;(Ip Not Assigned)<br>";
					}

				}

			} else {
				interfacetoput = "NA";
			}

			arrayData.put(interfacetoput);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getTotalNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest request) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
//		Query query = getSession().createSQLQuery(
//				"SELECT add_node.DEVICE_IP,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,nm.NODE_STATUS,nm.STATUS_TIMESTAMP FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND add_node.GROUP_NAME = '"
//						+ group_name + "' AND " + userScopeData);
//		System.out.println(
//				"SELECT add_node.DEVICE_IP,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,NM.NODE_STATUS,NM.STATUS_TIMESTAMP, CASE WHEN COUNT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN 1 END) = 0 THEN 'NA' ELSE GROUP_CONCAT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN CONCAT(im.INTERFACE_NAME, ' (', COALESCE(im.Interface_IP_ICMP_Status, 'NA'), ')') END SEPARATOR ', ') END AS INTERFACE_NAMES FROM add_node add_node JOIN node_monitoring NM ON add_node.DEVICE_IP = NM.NODE_IP LEFT JOIN interface_monitoring im ON im.NODE_IP = add_node.DEVICE_IP AND im.MONITORING_PARAM = 'Yes' WHERE add_node.MONITORING_PARAM = 'Yes' AND"
//						+ " add_node.GROUP_NAME = '" + group_name + "' AND " + userScopeData
//						+ " GROUP BY add_node.DEVICE_IP");
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,NM.NODE_STATUS,NM.STATUS_TIMESTAMP, CASE WHEN COUNT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN 1 END) = 0 THEN 'NA' ELSE GROUP_CONCAT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN CONCAT(im.INTERFACE_NAME, ' (', COALESCE(im.Interface_IP_ICMP_Status, 'NA'), ')') END SEPARATOR ', ') END AS INTERFACE_NAMES FROM add_node add_node JOIN node_monitoring NM ON add_node.DEVICE_IP = NM.NODE_IP LEFT JOIN interface_monitoring im ON im.NODE_IP = add_node.DEVICE_IP AND im.MONITORING_PARAM = 'Yes' WHERE "
						+ " add_node.GROUP_NAME = '" + group_name + "' AND " + userScopeData
						+ " GROUP BY add_node.DEVICE_IP");

		List<Object[]> data = query.list();
		String protocol = request.getScheme();

		// Get the host (domain and port)
		String host = request.getServerName();
		int port = request.getServerPort();

		host += ":" + port;

		// Get the context path (first path after the domain)
		String contextPath = request.getContextPath();

		// Build the base URL
		String baseUrl = protocol + "://" + host + contextPath;

		// Define node detail URL
		String nodeDetailUrl = baseUrl + "/dashboard/nodeDetailsPage?nodeIP=";
		for (Object[] a : data) {
			arrayData = new JSONArray();
//			arrayData.put((a[0] == null) ? "NA"
//					: a[0].equals("") ? "NA" : "<a href=\"" + nodeDetailUrl + a[0] + "\">" + a[0] + "</a>");
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);

			String interfacenames = a[6].toString().trim();
//			System.out.println("int name :" + interfacenames);
			String interfacetoput = "";
			if (!interfacenames.equalsIgnoreCase("NA")) {
//				int token = 0;
				String[] parts = interfacenames.split(",");
//				System.out.println("inside");

				// Trim whitespace and print each part
				for (String part : parts) {
//					token++;
//					System.out.println(part.trim());
					String intrname = part.trim();
//					System.out.println("inside " + intrname);

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

//					if (a[4].toString().equalsIgnoreCase("down")) {
//						interfacetoput += intrname
//								+ "&nbsp;<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
//
//					} else {

					if (bracketValue.equalsIgnoreCase("up")) {
						interfacetoput += intrname
								+ "&nbsp;<small class=\"text-success mr-1\"> <i class=\"fas fa-arrow-up\"></i> Up </small><br>";
					} else if (bracketValue.equalsIgnoreCase("down")) {
						interfacetoput += intrname
								+ "&nbsp;<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
					} else if (bracketValue.equalsIgnoreCase("warning")) {
						interfacetoput += intrname + "&nbsp;<small class=\"text-danger mr-1\"> warning </small><br>";
					} else {
						interfacetoput += intrname + "&nbsp;(IP Not Assigned)<br>";
					}

//					}

				}

			} else {
				interfacetoput = "NA";
			}

			arrayData.put(interfacetoput);

//			arrayData.put((a[6] == null) ? "NA" : a[6].equals("") ? "NA" : a[6]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);

			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray interfaceSummary(String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery("SELECT add_node.GROUP_NAME, COUNT(*) "
				+ "FROM add_node add_node, interface_monitoring im " + "WHERE add_node.DEVICE_IP = im.NODE_IP " + "AND "
				+ userScopeData + "\r\n" + "AND im.OPER_STATUS = 'up' " + "GROUP BY add_node.GROUP_NAME");
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put(a[0]);

			BigInteger TotalNode = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM  add_node add_node, interface_monitoring im WHERE im.NODE_IP = add_node.DEVICE_IP AND add_node.GROUP_NAME = '"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();
			BigInteger upNode = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM  add_node add_node, interface_monitoring im WHERE im.NODE_IP = add_node.DEVICE_IP AND im.OPER_STATUS = 'up' AND add_node.GROUP_NAME = '"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();
			BigInteger downNode = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM  add_node add_node, interface_monitoring im WHERE im.NODE_IP = add_node.DEVICE_IP AND im.OPER_STATUS = 'down' AND add_node.GROUP_NAME = '"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();
			BigInteger warningNode = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM  add_node add_node, interface_monitoring im WHERE im.NODE_IP = add_node.DEVICE_IP AND im.OPER_STATUS = 'warning' AND add_node.GROUP_NAME = '"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();

			arrayData.put("<span style='color:blue' onclick=\"getTotalInterfaceSummaryDetails('" + a[0] + "')\">"
					+ TotalNode + "</span>");
			arrayData.put("<span style='color:lightgreen' onclick=\"getupInterfaceSummaryDetails('" + a[0] + "')\">"
					+ upNode + "</span>");
			arrayData.put("<span style='color:red' onclick=\"getdownInterfaceSummaryDetails('" + a[0] + "')\">"
					+ downNode + "</span>");
			arrayData.put("<span style='color:orange' onclick=\"getwarningInterfaceSummaryDetails('" + a[0] + "')\">"
					+ warningNode + "</span>");
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray interfaceSummaryGroupWise(String userScopeData) {
		JSONArray arrayMain = new JSONArray();

		// Single consolidated query to fetch all required data with the additional
		// condition
		Query query = getSession().createSQLQuery("SELECT add_node.GROUP_NAME, "
				+ "       COUNT(*) AS totalInterfaces, "
				+ "       SUM(CASE WHEN im.INTERFACE_NAME = 'wan' AND im.Interface_IP_ICMP_Status = 'UP' THEN 1 ELSE 0 END) AS upWanInterfaces, "
				+ "       SUM(CASE WHEN im.INTERFACE_NAME = 'wan' AND im.Interface_IP_ICMP_Status = 'Down' THEN 1 ELSE 0 END) AS downWanInterfaces, "
				+ "       SUM(CASE WHEN im.INTERFACE_NAME = '3g-4g' AND im.Interface_IP_ICMP_Status = 'Up' THEN 1 ELSE 0 END) AS up4gInterfaces, "
				+ "       SUM(CASE WHEN im.INTERFACE_NAME = '3g-4g' AND im.Interface_IP_ICMP_Status = 'Down' THEN 1 ELSE 0 END) AS down4gInterfaces "
				+ "FROM add_node add_node " + "JOIN interface_monitoring im ON add_node.DEVICE_IP = im.NODE_IP "
				+ "WHERE " + userScopeData + "  "
				+ " AND im.INTERFACE_NAME IN ('wan', '3g-4g') " + " AND im.Intreface_IP_Monitoring = 'Yes' "
				+ "GROUP BY add_node.GROUP_NAME");

		List<Object[]> data = query.list();

		// Process the data in a single loop
		for (Object[] row : data) {
			JSONArray arrayData = new JSONArray();
			String groupName = (String) row[0];
			Long totalInterfaces = ((Number) row[1]).longValue();
			Long upWanInterfaces = ((Number) row[2]).longValue();
			Long downWanInterfaces = ((Number) row[3]).longValue();
			Long up4gInterfaces = ((Number) row[4]).longValue();
			Long down4gInterfaces = ((Number) row[5]).longValue();

			arrayData.put(groupName);
			arrayData.put("<span style='color:blue' onclick=\"getInterfaceDetailsWan4g('" + groupName + "', 'total')\">"
					+ totalInterfaces + "</span>");
			arrayData.put("<span style='color:green' onclick=\"getInterfaceDetailsWan4g('" + groupName
					+ "', 'upWan')\">" + upWanInterfaces + "</span>");
			arrayData.put("<span style='color:green' onclick=\"getInterfaceDetailsWan4g('" + groupName + "', 'up4g')\">"
					+ up4gInterfaces + "</span>");
			arrayData.put("<span style='color:red' onclick=\"getInterfaceDetailsWan4g('" + groupName
					+ "', 'downWan')\">" + downWanInterfaces + "</span>");
			arrayData.put("<span style='color:red' onclick=\"getInterfaceDetailsWan4g('" + groupName + "', 'down4g')\">"
					+ down4gInterfaces + "</span>");

			arrayMain.put(arrayData);
		}

		return arrayMain;
	}

	public JSONArray getTotalInterfaceSummaryDetails(String group_name, String userScopeData,
			HttpServletRequest request) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,im.INTERFACE_NAME,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,im.OPER_STATUS,im.STATUS_TIMESTAMP FROM  add_node add_node, interface_monitoring im WHERE im.NODE_IP = add_node.DEVICE_IP AND add_node.GROUP_NAME = '"
						+ group_name + "' AND " + userScopeData);
		List<Object[]> data = query.list();

		String protocol = request.getScheme();
		// Get the host (domain and port)
		String host = request.getServerName();
		int port = request.getServerPort();

		host += ":" + port;

		// Get the context path (first path after the domain)
		String contextPath = request.getContextPath();

		// Build the base URL
		String baseUrl = protocol + "://" + host + contextPath;

		// Define node detail URL
		String nodeDetailUrl = baseUrl + "/dashboard/nodeDetailsPage?nodeIP=";

		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA"
					: a[0].equals("") ? "NA" : "<a href=\"" + nodeDetailUrl + a[0] + "\">" + a[0] + "</a>");
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayData.put((a[6] == null) ? "NA" : a[6].equals("") ? "NA" : a[6]);
			arrayData.put((a[7] == null) ? "NA" : a[7].equals("") ? "NA" : a[7]);
			arrayData.put((a[8] == null) ? "NA" : a[8].equals("") ? "NA" : a[8]);
			arrayData.put((a[9] == null) ? "NA" : a[9].equals("") ? "NA" : a[9]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getupInterfaceSummaryDetails(String group_name, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,im.INTERFACE_NAME,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,im.OPER_STATUS,im.STATUS_TIMESTAMP FROM  add_node add_node, interface_monitoring im WHERE im.NODE_IP = add_node.DEVICE_IP AND im.OPER_STATUS = 'up' AND add_node.GROUP_NAME = '"
						+ group_name + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayData.put((a[6] == null) ? "NA" : a[6].equals("") ? "NA" : a[6]);
			arrayData.put((a[7] == null) ? "NA" : a[7].equals("") ? "NA" : a[7]);
			arrayData.put((a[8] == null) ? "NA" : a[8].equals("") ? "NA" : a[8]);
			arrayData.put((a[9] == null) ? "NA" : a[9].equals("") ? "NA" : a[9]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getdownInterfaceSummaryDetails(String group_name, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,im.INTERFACE_NAME,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,im.OPER_STATUS,im.STATUS_TIMESTAMP FROM  add_node add_node, interface_monitoring im WHERE im.NODE_IP = add_node.DEVICE_IP AND im.OPER_STATUS = 'down' AND add_node.GROUP_NAME = '"
						+ group_name + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayData.put((a[6] == null) ? "NA" : a[6].equals("") ? "NA" : a[6]);
			arrayData.put((a[7] == null) ? "NA" : a[7].equals("") ? "NA" : a[7]);
			arrayData.put((a[8] == null) ? "NA" : a[8].equals("") ? "NA" : a[8]);
			arrayData.put((a[9] == null) ? "NA" : a[9].equals("") ? "NA" : a[9]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getwarningInterfaceSummaryDetails(String group_name, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,im.INTERFACE_NAME,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,im.OPER_STATUS,im.STATUS_TIMESTAMP FROM  add_node add_node, interface_monitoring im WHERE im.NODE_IP = add_node.DEVICE_IP AND im.OPER_STATUS = 'warning' AND add_node.GROUP_NAME = '"
						+ group_name + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayData.put((a[6] == null) ? "NA" : a[6].equals("") ? "NA" : a[6]);
			arrayData.put((a[7] == null) ? "NA" : a[7].equals("") ? "NA" : a[7]);
			arrayData.put((a[8] == null) ? "NA" : a[8].equals("") ? "NA" : a[8]);
			arrayData.put((a[9] == null) ? "NA" : a[9].equals("") ? "NA" : a[9]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray averageBandwidthReport(String from_date, String to_date, List<String> ip_interface_list,
			String userScopeData) {
		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");
		System.out.println("Average IP List:" + ip_data + ":" + from_date + to_date);
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		try {
			List<Object[]> li = getSession().createSQLQuery(
					"select ibh.NODE_IP, ROUND(avg(ibh.IN_TRAFFIC),2),ROUND(avg(ibh.OUT_TRAFFIC),2) from interface_bw_history ibh join add_node node on node.DEVICE_IP=ibh.NODE_IP where ibh.IP_INTERFACE in ('"
							+ ip_data + "') and  ibh.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date
							+ "' group by ibh.NODE_IP")
					.list();
			for (Object[] data : li) {
				sr++;
				array = new JSONArray();
				array.put(sr);
				array.put(data[0]);
				array.put(data[1]);
				array.put(data[2]);
				arrayList.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("arrayList =" + arrayList.length());
		return arrayList;
	}

	public JSONArray interfaceJitterReport(String from_date, String to_date, List<String> ip_interface_list) {
		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");

		String query = "select report.ID,report.INTERFACE_NAME,report.DEVICE_IP,report.JITTER,report.LATENCY,report.DATE_TIME,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join interface_prob_log report on node.DEVICE_IP=report.DEVICE_IP where report.DEVICE_IP in ('"
				+ ip_data + "') and  report.DATE_TIME BETWEEN '" + from_date + "' AND '" + to_date + "'";

		Query q = getSession().createSQLQuery(query);

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());

		JSONArray finalArray = new JSONArray();
		JSONArray array = null;
		for (Object[] a : data) {
			try {
				array = new JSONArray();
				array.put(a[0]);
				array.put(a[1]);
				array.put(a[2]);
				array.put(a[3]);
				array.put(a[4]);
				array.put(a[5]);
				array.put(a[6]);
				array.put(a[7]);
				array.put(a[8]);
				array.put(a[9]);
				array.put(a[10]);
				array.put(a[11]);
				array.put(a[12]);

				finalArray.put(array);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return finalArray;
	}

	public JSONArray interfaceBandwidth(String from_date, String to_date, String ip_address, String interfaceName) {
		String query = "select report.ID,report.INTERFACE_NAME,report.OUT_TRAFFIC,report.IN_TRAFFIC,report.IP_INTERFACE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join interface_bw_history report on node.DEVICE_IP=report.NODE_IP where report.NODE_IP='"
				+ ip_address + "' and report.INTERFACE_NAME='" + interfaceName
				+ "' AND  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";

		Query q = getSession().createSQLQuery(query);

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());

		JSONArray finalArray = new JSONArray();
		JSONArray array = null;
		for (Object[] a : data) {
			try {
				array = new JSONArray();
				array.put(a[0]);
				array.put(a[1]);
				array.put(a[2]);
				array.put(a[3]);
				array.put(a[4]);
				array.put(a[5]);
				array.put(a[6]);
				array.put(a[7]);
				array.put(a[8]);
				array.put(a[9]);
				array.put(a[10]);
				array.put(a[11]);
				array.put(a[12]);

				finalArray.put(array);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return finalArray;
	}

	public List<InterfaceStatusReportBean> interfaceStatusEvents(String from_date, String to_date, String ip_address,
			String interfaceName) {
		String query = "select report.ID,report.INTERFACE_NAME,report.INTERFACE_STATUS,report.INTERFACE_STATUS_TYPE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join interface_status_history report on node.DEVICE_IP=report.NODE_IP where report.NODE_IP='"
				+ ip_address + "' and report.INTERFACE_NAME='" + interfaceName
				+ "' AND report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<InterfaceStatusReportBean> dataList = new ArrayList<InterfaceStatusReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				InterfaceStatusReportBean bean = new InterfaceStatusReportBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setINTERFACE_STATUS(a[2].toString());
				bean.setINTERFACE_STATUS_TYPE(a[3].toString());
				bean.setEVENT_TIMESTAMP(a[4].toString());

				bean.setNODE_IP(a[5].toString());
				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}

	public List<InterfaceAvailabilityBean> interfaceUptime(String from_date, String to_date, String ip_address,
			String interfaceName) {
		String query = "select report.ID,report.INTERFACE_NAME,report.UPTIME_PERCENT,report.UPTIME_MILISECONDS,report.DOWNTIME_PERCENT,report.DOWNTIME_MILISECONDS,report.IP_INTERFACE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join INTERFACE_AVAILABILITY report on node.DEVICE_IP=report.NODE_IP where report.NODE_IP='"
				+ ip_address + "' and report.INTERFACE_NAME='" + interfaceName
				+ "' AND  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<InterfaceAvailabilityBean> dataList = new ArrayList<InterfaceAvailabilityBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				String var_uptime = "NA";
				try {
					int up_second_day = (Integer) a[3];

					int day = (int) TimeUnit.SECONDS.toDays(up_second_day);
					long hours = TimeUnit.SECONDS.toHours(up_second_day) - (day * 24);
					long minute = TimeUnit.SECONDS.toMinutes(up_second_day)
							- (TimeUnit.SECONDS.toHours(up_second_day) * 60);
					long second = TimeUnit.SECONDS.toSeconds(up_second_day)
							- (TimeUnit.SECONDS.toMinutes(up_second_day) * 60);
					var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

				} catch (Exception ex) {
					System.out.println("Exception Uptime cal:" + ex);
				}
				System.out.println(a[1].toString() + "var_uptime:" + var_uptime);

				String var_downtime = "NA";
				try {
					int up_second_day = (Integer) a[5];
					int day = (int) TimeUnit.SECONDS.toDays(up_second_day);
					long hours = TimeUnit.SECONDS.toHours(up_second_day) - (day * 24);
					long minute = TimeUnit.SECONDS.toMinutes(up_second_day)
							- (TimeUnit.SECONDS.toHours(up_second_day) * 60);
					long second = TimeUnit.SECONDS.toSeconds(up_second_day)
							- (TimeUnit.SECONDS.toMinutes(up_second_day) * 60);
					var_downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

				} catch (Exception ex) {
					System.out.println("excep 333 util:" + ex);
				}

				InterfaceAvailabilityBean bean = new InterfaceAvailabilityBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setUPTIME_PERCENT(Double.parseDouble(a[2].toString()));
				bean.setUPTIME(var_uptime);
				bean.setDOWNTIME_PERCENT(Double.parseDouble(a[4].toString()));
				bean.setDOWNTIME(var_downtime);
				bean.setIP_INTERFACE(a[6].toString());
				bean.setEVENT_TIMESTAMP(a[7].toString());

				bean.setNODE_IP(a[8].toString());
				bean.setDEVICE_NAME(a[9].toString());
				bean.setLOCATION(a[10].toString());
				bean.setDISTRICT(a[11].toString());
				bean.setSTATE(a[12].toString());
				bean.setZONE(a[13].toString());
				bean.setGROUP_NAME(a[14].toString());

				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}

	public JSONArray deviceTypeSummary(String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery("SELECT add_node.DEVICE_TYPE, COUNT(*) "
				+ "FROM add_node add_node, node_monitoring nm " + "WHERE add_node.DEVICE_IP = nm.NODE_IP " + "AND "
				+ userScopeData + "\r\n" + "AND nm.NODE_STATUS = 'Up' " + "GROUP BY add_node.DEVICE_TYPE");
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put(a[0]);

			BigInteger TotalNode = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND add_node.DEVICE_TYPE = '"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();
			BigInteger upNode = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND nm.NODE_STATUS = 'Up' AND add_node.DEVICE_TYPE = '"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();
			BigInteger downNode = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND nm.NODE_STATUS = 'Down' AND add_node.DEVICE_TYPE = '"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();
			BigInteger warningNode = (BigInteger) getSession().createSQLQuery(
					"SELECT COUNT(*) FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND nm.NODE_STATUS = 'Warning' AND add_node.DEVICE_TYPE = '"
							+ a[0] + "' AND " + userScopeData)
					.getSingleResult();

			arrayData.put("<span style='color:blue' onclick=\"getTotalDeviceTypeSummaryDetails('" + a[0] + "')\">"
					+ TotalNode + "</span>");
			arrayData.put("<span style='color:lightgreen' onclick=\"getupDeviceTypeSummaryDetails('" + a[0] + "')\">"
					+ upNode + "</span>");
			arrayData.put("<span style='color:red' onclick=\"getdownDeviceTypeSummaryDetails('" + a[0] + "')\">"
					+ downNode + "</span>");
			arrayData.put("<span style='color:orange' onclick=\"getwarningDeviceTypeSummaryDetails('" + a[0] + "')\">"
					+ warningNode + "</span>");
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getTotalDeviceTypeSummaryDetails(String DEVICE_TYPE, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,add_node.DEVICE_TYPE,add_node.DEVICE_NAME,add_node.LOCATION,nm.NODE_STATUS,nm.STATUS_TIMESTAMP FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND add_node.DEVICE_TYPE = '"
						+ DEVICE_TYPE + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getupDeviceTypeSummaryDetails(String DEVICE_TYPE, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,add_node.DEVICE_TYPE,add_node.DEVICE_NAME,add_node.LOCATION,nm.NODE_STATUS,nm.STATUS_TIMESTAMP FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND nm.NODE_STATUS = 'Up' AND add_node.DEVICE_TYPE = '"
						+ DEVICE_TYPE + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getdownDeviceTypeSummaryDetails(String DEVICE_TYPE, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,add_node.DEVICE_TYPE,add_node.DEVICE_NAME,add_node.LOCATION,nm.NODE_STATUS,nm.STATUS_TIMESTAMP FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND nm.NODE_STATUS = 'Down' AND add_node.DEVICE_TYPE = '"
						+ DEVICE_TYPE + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getwarningDeviceTypeSummaryDetails(String DEVICE_TYPE, String userScopeData) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		Query query = getSession().createSQLQuery(
				"SELECT add_node.DEVICE_IP,add_node.DEVICE_TYPE,add_node.DEVICE_NAME,add_node.LOCATION,nm.NODE_STATUS,nm.STATUS_TIMESTAMP FROM  add_node add_node, node_monitoring nm WHERE nm.NODE_IP = add_node.DEVICE_IP AND nm.NODE_STATUS = 'Warning' AND add_node.DEVICE_TYPE = '"
						+ DEVICE_TYPE + "' AND " + userScopeData);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public JSONArray getInterfaceDetailsWan4g(String group_name, String status, HttpServletRequest request) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		String queryText = "";
		System.out.println("in getInterfaceDetailsWan4g");

		String protocol = request.getScheme();
		// Get the host (domain and port)
		String host = request.getServerName();
		int port = request.getServerPort();
		if (port != 80 && port != 443) {
			host += ":" + port;
		}

		// Get the context path (first path after the domain)
		String contextPath = request.getContextPath();

		// Build the base URL
		String baseUrl = protocol + "://" + host + contextPath;

		// Define node detail URL
		String nodeDetailUrl = baseUrl + "/dashboard/nodeDetailsPage?nodeIP=";

		if (status.equalsIgnoreCase("total")) {
			queryText = "SELECT add_node.DEVICE_IP,im.INTERFACE_NAME,add_node.GROUP_NAME,add_node.DEVICE_NAME,"
					+ "add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,im.OPER_STATUS,im.STATUS_TIMESTAMP,COALESCE(im.Interface_IP_ICMP_Status,'NA'),nm.NODE_STATUS "
					+ "FROM  add_node add_node, interface_monitoring im, node_monitoring nm WHERE im.NODE_IP = add_node.DEVICE_IP  AND nm.NODE_IP= add_node.DEVICE_IP "
					+ "AND add_node.GROUP_NAME = '" + group_name
					+ "' AND im.INTERFACE_NAME in ('wan','3g-4g') AND im.Intreface_IP_Monitoring='Yes'";
//			queryText = "SELECT add_node.DEVICE_IP,im.INTERFACE_NAME,add_node.GROUP_NAME,add_node.DEVICE_NAME,add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,im.OPER_STATUS,im.STATUS_TIMESTAMP, CASE WHEN COUNT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN 1 END) = 0 THEN 'NA' ELSE GROUP_CONCAT(CASE WHEN LOWER(im.INTERFACE_NAME) LIKE '%wan%' OR LOWER(im.INTERFACE_NAME) LIKE '%3g%' OR LOWER(im.INTERFACE_NAME) LIKE '%4g%' THEN CONCAT(im.INTERFACE_NAME, ' (', COALESCE(im.Interface_IP_ICMP_Status, 'NA'), ')') END SEPARATOR ', ') END AS INTERFACE_NAMES FROM add_node add_node JOIN node_monitoring NM ON add_node.DEVICE_IP = NM.NODE_IP LEFT JOIN interface_monitoring im ON im.NODE_IP = add_node.DEVICE_IP AND im.MONITORING_PARAM = 'Yes' WHERE "
//					+ " add_node.GROUP_NAME = '" + group_name
//					+ "' AND  im.INTERFACE_NAME in ('wan','3g-4g') GROUP BY add_node.DEVICE_IP";
		}

		else if (status.equalsIgnoreCase("upWan")) {
			queryText = "SELECT add_node.DEVICE_IP,im.INTERFACE_NAME,add_node.GROUP_NAME,add_node.DEVICE_NAME,"
					+ "add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,im.OPER_STATUS,im.STATUS_TIMESTAMP,COALESCE(im.Interface_IP_ICMP_Status,'NA'),nm.NODE_STATUS "
					+ "FROM add_node add_node, interface_monitoring im, node_monitoring nm WHERE im.NODE_IP = add_node.DEVICE_IP  AND nm.NODE_IP= add_node.DEVICE_IP AND "
					+ "  add_node.GROUP_NAME = '" + group_name
					+ "' AND im.INTERFACE_NAME = 'wan' AND im.Intreface_IP_Monitoring='Yes' AND im.Interface_IP_ICMP_Status='Up'";
		}

		else if (status.equalsIgnoreCase("downWan")) {
			queryText = "SELECT add_node.DEVICE_IP,im.INTERFACE_NAME,add_node.GROUP_NAME,add_node.DEVICE_NAME,"
					+ "add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,im.OPER_STATUS,im.STATUS_TIMESTAMP,COALESCE(im.Interface_IP_ICMP_Status,'NA'),nm.NODE_STATUS "
					+ "FROM add_node add_node, interface_monitoring im, node_monitoring nm WHERE im.NODE_IP = add_node.DEVICE_IP  AND nm.NODE_IP= add_node.DEVICE_IP AND "
					+ " " + " add_node.GROUP_NAME = '" + group_name
					+ "' AND im.INTERFACE_NAME = 'wan' AND im.Intreface_IP_Monitoring='Yes' AND (im.Interface_IP_ICMP_Status='Down')";
		} else if (status.equalsIgnoreCase("up4g")) {
			queryText = "SELECT add_node.DEVICE_IP,im.INTERFACE_NAME,add_node.GROUP_NAME,add_node.DEVICE_NAME,"
					+ "add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,im.OPER_STATUS,im.STATUS_TIMESTAMP,COALESCE(im.Interface_IP_ICMP_Status,'NA'),nm.NODE_STATUS "
					+ "FROM add_node add_node, interface_monitoring im, node_monitoring nm WHERE im.NODE_IP = add_node.DEVICE_IP  AND nm.NODE_IP= add_node.DEVICE_IP AND "
					+ " " + " add_node.GROUP_NAME = '" + group_name
					+ "' AND im.INTERFACE_NAME = '3g-4g' AND im.Intreface_IP_Monitoring='Yes' AND im.Interface_IP_ICMP_Status='Up'";
		}

		else {
			queryText = "SELECT add_node.DEVICE_IP,im.INTERFACE_NAME,add_node.GROUP_NAME,add_node.DEVICE_NAME,"
					+ "add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,im.OPER_STATUS,im.STATUS_TIMESTAMP,COALESCE(im.Interface_IP_ICMP_Status,'NA'),nm.NODE_STATUS "
					+ "FROM add_node add_node, interface_monitoring im, node_monitoring nm WHERE im.NODE_IP = add_node.DEVICE_IP  AND nm.NODE_IP= add_node.DEVICE_IP AND "
					+ " " + " add_node.GROUP_NAME = '" + group_name
					+ "' AND im.INTERFACE_NAME = '3g-4g' AND im.Intreface_IP_Monitoring='Yes' AND (im.Interface_IP_ICMP_Status='Down')";
		}

		Query query = getSession().createSQLQuery(queryText);
		List<Object[]> data = query.list();
		for (Object[] a : data) {
			arrayData = new JSONArray();
//			arrayData.put((a[0] == null) ? "NA"
//					: a[0].equals("") ? "NA" : "<a href=\"" + nodeDetailUrl + a[0] + "\">" + a[0] + "</a>");
			arrayData.put((a[0] == null) ? "NA" : a[0].equals("") ? "NA" : a[0]);
			arrayData.put((a[1] == null) ? "NA" : a[1].equals("") ? "NA" : a[1]);
			arrayData.put((a[2] == null) ? "NA" : a[2].equals("") ? "NA" : a[2]);
			arrayData.put((a[3] == null) ? "NA" : a[3].equals("") ? "NA" : a[3]);
			arrayData.put((a[4] == null) ? "NA" : a[4].equals("") ? "NA" : a[4]);
			arrayData.put((a[5] == null) ? "NA" : a[5].equals("") ? "NA" : a[5]);
			arrayData.put((a[6] == null) ? "NA" : a[6].equals("") ? "NA" : a[6]);
			arrayData.put((a[7] == null) ? "NA" : a[7].equals("") ? "NA" : a[7]);
//			arrayData.put((a[8] == null) ? "NA" : a[8].equals("") ? "NA" : a[8]);
			String interfacetoput = "";
			if (a[10] == null || a[10].equals("")) {

			} else {
//				if (a[11].toString().trim().equalsIgnoreCase("down")) {
//					interfacetoput = "<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
//				} else {
				if (a[10].toString().trim().equalsIgnoreCase("up")) {
					interfacetoput = "<small class=\"text-success mr-1\"> <i class=\"fas fa-arrow-up\"></i> Up </small><br>";
				} else if (a[10].toString().trim().equalsIgnoreCase("down")) {
					interfacetoput = "<small class=\"text-danger mr-1\"> <i class=\"fas fa-arrow-down\"></i> Down </small><br>";
				} else if (a[10].toString().trim().equalsIgnoreCase("warning")) {
					interfacetoput = "<small class=\"text-danger mr-1\"> warning </small><br>";
				} else {
					interfacetoput = "NA";
				}

//				}

			}

			arrayData.put((a[10] == null) ? "NA" : a[10].equals("") ? "NA" : interfacetoput);
			arrayData.put((a[9] == null) ? "NA" : a[9].equals("") ? "NA" : a[9]);

			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

}
