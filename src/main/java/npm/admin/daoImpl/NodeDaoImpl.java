package npm.admin.daoImpl;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import npm.admin.beans.AddNodeBean;
import npm.admin.beans.AddNodeParameterBean;
import npm.admin.beans.ConfigurationTemplateBean;
import npm.admin.beans.LocationMasterBean;
import npm.admin.beans.ScheduleConfigurationBean;
import npm.admin.beans.SpeedTestBean;
import npm.admin.dao.NodeDao;
import npm.configuration.AbstractDao;
import npm.model.AddNodeModel;
import npm.model.AvailabilityParameterModel;
import npm.model.CommandMaster;
import npm.model.CompanyMasterModel;
import npm.model.ConfigBackup;
import npm.model.DeviceTypeMasterModel;
import npm.model.DistrictMasterModel;
import npm.model.GroupMasterModel;
import npm.model.InterfaceMonitoring;
import npm.model.LocationMasterModel;
import npm.model.Network_Discovery;
import npm.model.NodeMonitoringModel;
import npm.model.NodeParameterModel;
import npm.model.ScheduleConfiguration;
import npm.model.ServiceProviderMasterModel;
import npm.model.SmsConfiguration;
import npm.model.SnmpConfigMasterModel;
import npm.model.SpeedTestModel;
import npm.model.StateMasterModel;
import npm.model.ZoneMasterModel;

@Repository
@Transactional
public class NodeDaoImpl extends AbstractDao<Integer, NodeParameterModel> implements NodeDao {

	// Add Node

	public Map<String, String> getdeviceType() {
		Map<String, String> deviceType = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from DeviceTypeMasterModel ");

			List<DeviceTypeMasterModel> listdata = q.list();

			for (DeviceTypeMasterModel devicetype : listdata) {
				deviceType.put(devicetype.getDEVICE_TYPE(), devicetype.getDEVICE_TYPE());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return deviceType;
	}

	public Map<String, String> getgroupName() {
		Map<String, String> groupName = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from GroupMasterModel ");

			List<GroupMasterModel> listdata = q.list();

			for (GroupMasterModel groupname : listdata) {
				groupName.put(groupname.getGROUP_NAME(), groupname.getGROUP_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return groupName;
	}

	public Map<String, String> getserviceProvider() {
		Map<String, String> serviceProvider = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from ServiceProviderMasterModel ");

			List<ServiceProviderMasterModel> listdata = q.list();

			for (ServiceProviderMasterModel sp : listdata) {
				serviceProvider.put(sp.getSERVICE_PROVIDER_NAME(), sp.getSERVICE_PROVIDER_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return serviceProvider;
	}

	public Map<String, String> getSnmp() {
		Map<String, String> snmp = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from SnmpConfigMasterModel ");

			List<SnmpConfigMasterModel> listdata = q.list();

			for (SnmpConfigMasterModel sp : listdata) {
				snmp.put(sp.getNAME(), sp.getNAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return snmp;
	}

	public String addNode(String deviceIp, String deviceType, String deviceName, String groupName, String snmp,
			String serviceProvider, String company, String state, String zone, String location, String district) {
		String nodeStatus = "Up";
		String latencyThreshold = "100";
		String result = null;
		try {

			NodeMonitoringModel nm = new NodeMonitoringModel();
			nm.setNODE_STATUS(nodeStatus);
			nm.setLATENCY_THRESHOLD(latencyThreshold);
			nm.setNODE_IP(deviceIp);

			AddNodeModel addNode = new AddNodeModel();
			addNode.setDEVICE_IP(deviceIp);
			;
			addNode.setDEVICE_NAME(deviceName);
			addNode.setDEVICE_TYPE(deviceType);
			addNode.setGROUP_NAME(groupName);
			addNode.setSNMP(snmp);
			addNode.setSERVICE_PROVIDER(serviceProvider);
			addNode.setMONITORING_PARAM("Yes");
			addNode.setCOMPANY(company);
			addNode.setSTATE(state);
			addNode.setDISTRICT(district);
			addNode.setZONE(zone);
			addNode.setLOCATION(location);

			getSession().save(addNode);
			getSession().save(nm);
			result = "success";

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<AddNodeModel> viewAddNode(String userScopeData) {

		try {
			Query query = getSession().createQuery("FROM AddNodeModel add_node WHERE " + userScopeData);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions appropriately
			return Collections.emptyList(); // or return null, throw an exception, etc.
		}
	}

	public String deleteAddNode(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Node Parameter:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from AddNodeModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception e***" + e);
			result = "fail";
		}

		return result;
	}

	public AddNodeBean editAddNode(long id) {
		// TODO Auto-generated method stub
		System.out.println(" editAddNode :" + id);
		String result = null;

		AddNodeBean addNode = new AddNodeBean();
		try {
			Query q = getSession().createQuery("from AddNodeModel where ID=:id");
			q.setParameter("id", id);
			// List<UserMasterModel> listData = q.list();
			List<AddNodeModel> listdata = q.list();

			addNode.setDeviceIp(listdata.get(0).getDEVICE_IP());
			addNode.setDeviceName(listdata.get(0).getDEVICE_NAME());
			addNode.setDeviceType(listdata.get(0).getDEVICE_TYPE());
			addNode.setGroupName(listdata.get(0).getGROUP_NAME());
			addNode.setServiceProvider(listdata.get(0).getSERVICE_PROVIDER());
			addNode.setSnmp(listdata.get(0).getSNMP());
			addNode.setCompany(listdata.get(0).getCOMPANY());
			addNode.setState(listdata.get(0).getSTATE());
			addNode.setZone(listdata.get(0).getZONE());
			addNode.setDistrict(listdata.get(0).getDISTRICT());
			addNode.setLocation(listdata.get(0).getLOCATION());
			addNode.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return addNode;
	}

	public String updateAddNode(String deviceIp, String deviceType, String deviceName, String groupName, String snmp,
			String serviceProvider, String company, String state, String zone, String location, String district,
			long id) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update AddNodeModel set device_ip=:deviceIp,device_type=:deviceType,device_name=:deviceName,group_name=:groupName,snmp=:snmp,"
							+ "service_provider=:serviceProvider,company=:company, state=:state, zone=:zone, location=:location, district=:district where id=:id");
			q.setParameter("deviceIp", deviceIp);
			q.setParameter("deviceType", deviceType);
			q.setParameter("deviceName", deviceName);
			q.setParameter("groupName", groupName);
			q.setParameter("snmp", snmp);
			q.setParameter("serviceProvider", serviceProvider);
			q.setParameter("company", company);
			q.setParameter("state", state);
			q.setParameter("zone", zone);
			q.setParameter("location", location);
			q.setParameter("district", district);
			q.setParameter("id", id);

			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	// Node Parameter

	public String addNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold,
			String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, String snmpDiscovery,
			String mailAlert, String smsAlert, String autoTicketing) {
		// TODO Auto-generated method stub
//		System.out.println("Dao called");
//		System.out.println("ip:" + ip);
//		System.out.println("memoryThreshold:" + memoryThreshold);
//		System.out.println("cpuThreshold:" + cpuThreshold);
//		System.out.println("latencyThreshold:" + latencyThreshold);
//		System.out.println("monitoring:" + monitoring);
//		System.out.println("cpuHistory:" + cpuHistory);
//		System.out.println("latencyHistory:" + latencyHistory);
//		System.out.println("memoryHistory: " + memoryHistory);

		String result = null;
		String nodeIP = null;
		// NodeParameterModel nodeParameter = new NodeParameterModel();

		// System.out.println("incoming ip = "+st.nextToken());

		// System.out.println("ip == "+(query));

		try {
			StringTokenizer st = new StringTokenizer(ip, ",");
			while (st.hasMoreTokens()) {
				NodeParameterModel nodeParameter = new NodeParameterModel();
				nodeIP = st.nextToken();
				System.out.println("incoming ip = " + nodeIP);
				List query = getSession()
						.createQuery("Select DEVICE_IP from NodeParameterModel where DEVICE_IP='" + nodeIP + "'")
						.list();
				if (!query.isEmpty()) {
					System.out.println("check for IP in update = " + nodeIP);

					Query q = getSession().createQuery(
							"Update NodeParameterModel set cpu_threshold=:cpuThreshold,memory_threshold=:memoryThreshold,latency_threshold=:latencyThreshold,monitoring=:monitoring,"
									+ "cpu_history=:cpuHistory,memory_history=:memoryHistory, latency_history=:latencyHistory, SNMP_DISCOVERY_PARAM=:snmpDiscovery,"
									+ "SMS_ALERT=:smsAlert, MAIL_ALERT=:mailAlert, AUTO_TICKETING=:autoTicketing where DEVICE_IP='"
									+ nodeIP + "' ");

					q.setParameter("cpuThreshold", cpuThreshold);
					q.setParameter("memoryThreshold", memoryThreshold);
					q.setParameter("latencyThreshold", latencyThreshold);
					q.setParameter("monitoring", monitoring);
					q.setParameter("cpuHistory", cpuHistory);
					q.setParameter("memoryHistory", memoryHistory);
					q.setParameter("latencyHistory", latencyHistory);
					q.setParameter("snmpDiscovery", snmpDiscovery);
					q.setParameter("smsAlert", smsAlert);
					q.setParameter("mailAlert", mailAlert);
					q.setParameter("autoTicketing", autoTicketing);
					q.executeUpdate();
					result = "update";

				} else {
					System.out.println("check for IP in add = " + nodeIP);

					nodeParameter.setDEVICE_IP(nodeIP);
					nodeParameter.setMEMORY_THRESHOLD(memoryThreshold);
					nodeParameter.setCPU_THRESHOLD(cpuThreshold);
					nodeParameter.setLATENCY_THRESHOLD(latencyThreshold);
					nodeParameter.setCPU_HISTORY(cpuHistory);
					nodeParameter.setLATENCY_HISTORY(latencyHistory);
					nodeParameter.setMEMORY_HISTORY(memoryHistory);
					nodeParameter.setMONITORING(monitoring);
					nodeParameter.setSNMP_DISCOVERY_PARAM(snmpDiscovery);
					nodeParameter.setSMS_ALERT(smsAlert);
					nodeParameter.setMAIL_ALERT(mailAlert);
					nodeParameter.setAUTO_TICKETING(autoTicketing);
					getSession().save(nodeParameter);
					result = "success";
				}

//				for add node

				List query22 = getSession().createSQLQuery("SELECT DEVICE_IP FROM add_node WHERE DEVICE_IP = :deviceIP")
						.setParameter("deviceIP", nodeIP).list();

				if (!query22.isEmpty()) {
					System.out.println("Updating MONITORING_PARAM for DEVICE_IP = " + nodeIP);

					Query updateQuery22 = getSession().createSQLQuery(
							"UPDATE add_node SET MONITORING_PARAM = :monitoringParam WHERE DEVICE_IP = :deviceIP");
					updateQuery22.setParameter("monitoringParam", monitoring);
					updateQuery22.setParameter("deviceIP", nodeIP);
					updateQuery22.executeUpdate();

					result = "update";
				}

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public JSONArray viewNodeParameter(String userScopeData) {

		JSONArray finalArray = new JSONArray();
		JSONArray array = null;
		List<Object[]> q = getSession().createQuery(
				"SELECT npm.ID, npm.DEVICE_IP,npm.CPU_THRESHOLD,npm.MEMORY_THRESHOLD,npm.LATENCY_THRESHOLD,npm.MONITORING,"
						+ "npm.CPU_HISTORY,npm.MEMORY_HISTORY,npm.LATENCY_HISTORY,npm.MAIL_ALERT,npm.SMS_ALERT,npm.AUTO_TICKETING,npm.SNMP_DISCOVERY_PARAM"
						+ " from NodeParameterModel npm, AddNodeModel add_node Where npm.DEVICE_IP=add_node.DEVICE_IP AND "
						+ userScopeData)
				.list();

		for (Object[] objects : q) {
			array = new JSONArray();
			array.put(objects[1]);
			array.put(objects[2]);
			array.put(objects[3]);
			array.put(objects[4]);
			array.put(objects[5]);
			array.put(objects[6]);
			array.put(objects[7]);
			array.put(objects[8]);
			array.put(objects[9]);
			array.put(objects[10]);
			array.put(objects[11]);
			array.put(objects[12]);
			array.put("<a href='editNodeParameter/" + objects[0] + "'>Edit</a>");
			array.put("<a href=\"javascript:deleteNodeParameter('" + objects[0] + "')\">Delete</a>");

			finalArray.put(array);
		}
		return finalArray;

//		Criteria criteria = getSession().createCriteria(NodeParameterModel.class);
//		return criteria.list();
	}

	public String deleteNodeParameter(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Node Parameter:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from NodeParameterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}

	public AddNodeParameterBean editNodeParameter(long id) {
		// TODO Auto-generated method stub
		System.out.println(" editNodeParameter :" + id);
		String result = null;

		AddNodeParameterBean nodeParameter = new AddNodeParameterBean();
		try {
			Query q = getSession().createQuery("from NodeParameterModel where ID=:id");
			q.setParameter("id", id);
			// List<UserMasterModel> listData = q.list();
			List<NodeParameterModel> listdata = q.list();

			nodeParameter.setIp(listdata.get(0).getDEVICE_IP());
			nodeParameter.setCpuThreshold(listdata.get(0).getCPU_THRESHOLD());
			nodeParameter.setMemoryThreshold(listdata.get(0).getMEMORY_THRESHOLD());
			nodeParameter.setLatencyThreshold(listdata.get(0).getLATENCY_THRESHOLD());
			nodeParameter.setCpuHistory(listdata.get(0).getCPU_HISTORY());
			nodeParameter.setMemoryHistory(listdata.get(0).getMEMORY_HISTORY());
			nodeParameter.setLatencyHistory(listdata.get(0).getLATENCY_HISTORY());
			nodeParameter.setMonitoring(listdata.get(0).getMONITORING());
			nodeParameter.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return nodeParameter;
	}

	public String updateNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold,
			String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, long id,
			String snmpDiscovery, String mailAlert, String smsAlert, String autoTicketing) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update NodeParameterModel set DEVICE_IP=:ip,cpu_threshold=:cpuThreshold,memory_threshold=:memoryThreshold,latency_threshold=:latencyThreshold,monitoring=:monitoring,"
							+ "cpu_history=:cpuHistory,memory_history=:memoryHistory, latency_history=:latencyHistory, SNMP_DISCOVERY_PARAM=:snmpDiscovery, "
							+ "SMS_ALERT=:smsAlert, MAIL_ALERT=:mailAlert, AUTO_TICKETING=:autoTicketing where id=:id");
			q.setParameter("ip", ip);
			q.setParameter("cpuThreshold", cpuThreshold);
			q.setParameter("memoryThreshold", memoryThreshold);
			q.setParameter("latencyThreshold", latencyThreshold);
			q.setParameter("monitoring", monitoring);
			q.setParameter("cpuHistory", cpuHistory);
			q.setParameter("memoryHistory", memoryHistory);
			q.setParameter("latencyHistory", latencyHistory);
			q.setParameter("snmpDiscovery", snmpDiscovery);
			q.setParameter("smsAlert", smsAlert);
			q.setParameter("mailAlert", mailAlert);
			q.setParameter("autoTicketing", autoTicketing);
			q.setParameter("id", id);

			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	// config backup
	public String addConfigBackup(String backupPath, String runConfig, String startupConfig, String backup,
			String group_name, String ip_address, String configType, String backupSchedule, String datepicker) {

		String result = "";
		StringTokenizer ip = new StringTokenizer(ip_address, ",");

		try {

			while (ip.hasMoreTokens()) {
				String ipAdd = ip.nextToken();
				ScheduleConfiguration cb = new ScheduleConfiguration();
				cb.setBACKUP_PATH(backupPath);
//				cb.setRUNNING_CONFIG(runConfig);
//				cb.setSTARTUP_CONFIG(startupConfig);
//				cb.setBACKUP(backup);
				cb.setBACKUP_SCHEDULE(backupSchedule);
				cb.setCONFIGURATION_TYPE(configType);
				cb.setTIME_PICKER(datepicker);
				cb.setNODE_IP(ipAdd);
				getSession().save(cb);

			}
			result = "success";

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<ConfigBackup> viewconfigBackup(String userScopeData) {
		try {
			Query query = getSession()
					.createQuery("FROM ConfigBackup cb, AddNodeModel add_node WHERE cb.NODE_IP=add_node.DEVICE_IP AND "
							+ userScopeData);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions appropriately
			return Collections.emptyList(); // or return null, throw an exception, etc.
		}

//		Criteria criteria = getSession().createCriteria(ConfigBackup.class);
//		return criteria.list();
	}

	public String deleteConfigBackup(long id) {
		// TODO Auto-generated method stub
		System.out.println("id:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from ConfigBackup where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}

	public String importNodeIP(String nodeIP) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		NodeMonitoringModel nm = null;
		NodeParameterModel np = null;

		try {

			while (ip.hasMoreTokens()) {

				String importNodeIP = ip.nextToken();
				// System.out.println("node ip:" + importNodeIP);

				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + importNodeIP + "'")
						.list();
				List query2 = getSession()
						.createQuery("from NodeParameterModel where DEVICE_IP = '" + importNodeIP + "'").list();
				List query3 = getSession()
						.createQuery("from NodeMonitoringModel where NODE_IP = '" + importNodeIP + "'").list();

				if (query1.isEmpty()) {

					ad = new AddNodeModel();
					ad.setDEVICE_IP(importNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("GroupA");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query q = getSession().createQuery("from Network_Discovery where NODE_IP='" + importNodeIP + "'");

					List<Network_Discovery> listdata = q.list();
					for (Network_Discovery sp : listdata) {
						Query updateQuery1 = getSession()
								.createQuery("update AddNodeModel set device_type='router',device_name='"
										+ sp.getNODE_NAME() + "', group_name='" + sp.getGROUP_NAME() + "', DEVICE_MAC='"
										+ sp.getMAC_ID() + "', OEM='" + sp.getOEM()
										+ "',snmp='Switch SNMP', SERVICE_PROVIDER='NA',company='NA', state='NA', zone='NA', location='NA', district='NA' where device_ip='"
										+ importNodeIP + "'");
						updateQuery1.executeUpdate();
					}
				}

				if (query3.isEmpty()) {
					nm = new NodeMonitoringModel();
					nm.setNODE_IP(importNodeIP);
					nm.setNODE_STATUS("Up");
					nm.setLATENCY_THRESHOLD("10");
					getSession().save(nm);
				} else {
					Query updateQuery3 = getSession().createQuery(
							"update NodeMonitoringModel set node_status='Up', latency_threshold='10' where node_ip='"
									+ importNodeIP + "'");
					updateQuery3.executeUpdate();
				}

				if (query2.isEmpty()) {

					np = new NodeParameterModel();
					np.setDEVICE_IP(importNodeIP);
					np.setCPU_HISTORY("Yes");
					np.setCPU_THRESHOLD(50);
					np.setLATENCY_HISTORY("Yes");
					np.setLATENCY_THRESHOLD(50);
					np.setMEMORY_HISTORY("Yes");
					np.setMEMORY_THRESHOLD(50);
					np.setMONITORING("NA");
					np.setSNMP_DISCOVERY_PARAM("No");
					getSession().save(np);
				} else {
					Query updateQuery3 = getSession().createQuery(
							"update NodeParameterModel set cpu_history='Yes', cpu_threshold='50', latency_history='Yes', latency_threshold='50', "
									+ "memory_history='Yes', memory_threshold='50', monitoring='NA', snmp_discovery_param='Yes' where device_ip='"
									+ importNodeIP + "'");
					updateQuery3.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public Map<String, String> getDistinctNodes() {
		Map<String, String> nodeIP = new HashMap<String, String>();
		try {

			Query query = getSession().createQuery("SELECT DISTINCT DEVICE_IP FROM AddNodeModel");
			List<String> li = query.list();
			for (String node : li) {
				nodeIP.put(node, node);
			}
			// System.out.println("Distinct nodes = "+nodeIP);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeIP;
	}

	// Bulk Upload

	public String uploadFile(String fileName, String destPath) {

		HashSet<String> ips = new HashSet<String>();
		Query query = getSession().createQuery("select DEVICE_IP from AddNodeModel");
		List<String> li = query.list();
		for (String ip : li) {
			ips.add(ip);
		}

		int insertCount = 0;
		int failCount = 0;
		int updateCount = 0;
		int deleteCount = 0;

		String response = "";
		// String path = "C:/NMS_Data/";
		fileName = destPath.concat(fileName);
		System.out.println("file name = " + fileName);
		try {
			FileInputStream file = new FileInputStream(new File(fileName));
			// FileInputStream file = new FileInputStream(new
			// File("C:/NMS_Data/BulkUploadUpdate.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Row row;

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				try {
					row = (Row) sheet.getRow(i);

					String device_IP, group_name, device_name, device_type, snmp, service_provider, company, state,
							zone, location, district, key_param;
					if (row.getCell(0) == null) {
						device_IP = "NA";
					} else
						device_IP = row.getCell(0).toString();

					if (row.getCell(1) == null) {
						device_name = "NA";
					} else
						device_name = row.getCell(1).toString();

					if (row.getCell(2) == null) {
						device_type = "NA";
					} else
						device_type = row.getCell(2).toString();

					if (row.getCell(3) == null) {
						group_name = "NA";
					} else
						group_name = row.getCell(3).toString();

					if (row.getCell(4) == null) {
						snmp = "NA";
					} else
						snmp = row.getCell(4).toString();

					if (row.getCell(5) == null) {
						service_provider = "NA";
					} else
						service_provider = row.getCell(5).toString();

					if (row.getCell(6) == null) {
						company = "NA";
					} else
						company = row.getCell(6).toString();

					if (row.getCell(7) == null) {
						state = "NA";
					} else
						state = row.getCell(7).toString();

					if (row.getCell(8) == null) {
						zone = "NA";
					} else
						zone = row.getCell(8).toString();

					if (row.getCell(9) == null) {
						location = "NA";
					} else
						location = row.getCell(9).toString();

					if (row.getCell(10) == null) {
						district = "NA";
					} else
						district = row.getCell(10).toString();

					if (row.getCell(11) == null) {
						key_param = "NA";
					} else
						key_param = row.getCell(11).toString();

					// System.out.println("key param = " + key_param);
					if (key_param.equals("1") || key_param.equals("1.0")) {
						if (ips.contains(device_IP)) {
							failCount++;
						} else {
							ips.add(row.getCell(0).toString());

							try {
								try {

									AddNodeModel node = new AddNodeModel();
									node.setDEVICE_IP(device_IP);
									node.setDEVICE_TYPE(device_type);
									node.setDEVICE_NAME(device_name);
									node.setGROUP_NAME(group_name);
									node.setSNMP(snmp);
									node.setSERVICE_PROVIDER(service_provider);
									node.setCOMPANY(company);
									node.setSTATE(state);
									node.setZONE(zone);
									node.setLOCATION(location);
									node.setDISTRICT(district);
									getSession().save(node);
								} catch (Exception e) {
									// TODO: handle exception
								}
								try {

									NodeMonitoringModel nm = new NodeMonitoringModel();
									nm.setNODE_IP(device_IP);
									nm.setNODE_STATUS("Up");
									getSession().save(nm);
								} catch (Exception e) {
									// TODO: handle exception
								}
								insertCount++;
								response = "success";

							} catch (Exception e) {
								e.printStackTrace();
								// System.out.println("in add fail count");
								response = "fail";
								// failCount++;
							}
						}

					} else if (key_param.equals("2") || key_param.equals("2.0")) {
						try {
							Query queryUpdate = getSession().createQuery("update AddNodeModel set DEVICE_NAME='"
									+ device_name + "', DEVICE_TYPE='" + device_type + "', GROUP_NAME='" + group_name
									+ "', SNMP='" + snmp + "', SERVICE_PROVIDER='" + service_provider + "', COMPANY='"
									+ company + "', STATE='" + state + "', ZONE='" + zone + "', LOCATION='" + location
									+ "', DISTRICT='" + district + "' where DEVICE_IP='" + device_IP + "'");
							queryUpdate.executeUpdate();
							updateCount++;
						} catch (Exception e) {
							e.printStackTrace();
							response = "fail";
						}

					} else if (key_param.equals("3") || key_param.equals("3.0")) {
						try {
							Query addNodeModelQuery = getSession()
									.createQuery("delete from AddNodeModel where DEVICE_IP = '" + device_IP + "'");
							addNodeModelQuery.executeUpdate();

							Query nodeMonitoringModelQuery = getSession()
									.createQuery("delete from NodeMonitoringModel where NODE_IP = '" + device_IP + "'");
							nodeMonitoringModelQuery.executeUpdate();

							deleteCount++;
						} catch (Exception e) {
							e.printStackTrace();
							response = "fail";
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
					response = "fail";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			response = "fail";
		}
		return response + "," + insertCount + "," + updateCount + "," + deleteCount + "," + failCount;
	}

	public String addAvailabilityParameter(LocalTime fromTime, LocalTime toTime) {
		System.out.println("addAvailabilityParameter called");
		String result = null;
		try {
			AvailabilityParameterModel avParameter = new AvailabilityParameterModel();
			avParameter.setFROM_TIME(fromTime);
			avParameter.setTO_TIME(toTime);
			getSession().save(avParameter);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public JSONArray viewAvailabilityParameter() {
		JSONArray array = null;
		JSONArray array1 = null;
		int id = 0;
		try {

			Criteria criteria = getSession().createCriteria(AvailabilityParameterModel.class);

			List<AvailabilityParameterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (AvailabilityParameterModel viewAp : dataList) {
				id = id + 1;
				array = new JSONArray();

				array.put(id);
				array.put(viewAp.getFROM_TIME());
				array.put(viewAp.getTO_TIME());
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	public String addConfigurationTemplateDao(String templateName, String command) {
		String result = null;
		Criteria criteria = getSession().createCriteria(CommandMaster.class);
		List<CommandMaster> dataList = criteria.list();
		if (!dataList.isEmpty()) {
			int dataconparision = 0;
			for (CommandMaster confTempData : dataList) {
				if (confTempData.getTEMPLATE_NAME().equals(templateName)) {
//					try {
//						Query q = getSession().createQuery(
//								"UPDATE CommandMaster SET COMMAND=:command  WHERE TEMPLATE_NAME=:templateName");
//						q.setParameter("templateName", templateName);
//						q.setParameter("command", command);
//						int i = q.executeUpdate();
//					} catch (Exception e) {
//						System.out.println("Exception:" + e);
//					}
					result = "already";
					break;
				} else if (dataList.size() - 1 == dataconparision) {
					try {
						CommandMaster confTempDataUpdate = new CommandMaster();
						confTempDataUpdate.setTEMPLATE_NAME(templateName);
						confTempDataUpdate.setCOMMAND(command);
						getSession().save(confTempDataUpdate);
					} catch (Exception e) {
						System.out.println("Exception:" + e);

					}
					result = "added";
				}
				dataconparision++;
			}
		} else {
			CommandMaster confTempDataUpdate = new CommandMaster();
			confTempDataUpdate.setTEMPLATE_NAME(templateName);
			confTempDataUpdate.setCOMMAND(command);
			getSession().save(confTempDataUpdate);
			result = "added";
		}

		return result;
	}

	public JSONArray viewConfigurationTemplateDao() {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(CommandMaster.class);
			List<CommandMaster> dataList = criteria.list();
			array1 = new JSONArray();
			for (CommandMaster confTempData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(confTempData.getTEMPLATE_NAME());
				array.put(confTempData.getCOMMAND());
				array.put("<a href=\"editConfigurationTemplate/" + confTempData.getID()
						+ "\"><i class=\"fas fa-edit\"></a>");
				array.put(
						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteConfigurationTemplate(\""
								+ confTempData.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public String deleteConfigurationTemplateDao(long ID) {
		String result = null;
		try {
			Query q = getSession().createQuery("Delete from CommandMaster where ID=:ID");
			q.setParameter("ID", ID);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ConfigurationTemplateBean ConfigurationTemplateBeanDao(long ID) {
		System.out.println("edit user:" + ID);
		String result = null;
		ConfigurationTemplateBean newObj = null;
		ConfigurationTemplateBean configData = new ConfigurationTemplateBean();
		try {
			Query q = getSession().createQuery("from CommandMaster where ID=:ID");
			q.setParameter("ID", ID);
			// List<CommandMaster> listData = q.list();
			List<CommandMaster> listdata = q.list();
			configData.setID(listdata.get(0).getID());
			configData.setTemplateName(listdata.get(0).getTEMPLATE_NAME());
			configData.setCommand(listdata.get(0).getCOMMAND());
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}
		return configData;
	}

	public String updateConfigurationTemplateDao(long ID, String templateName, String command) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"UPDATE CommandMaster SET TEMPLATE_NAME=:templateName, COMMAND=:command  WHERE ID=:ID");
			q.setParameter("templateName", templateName);
			q.setParameter("command", command);
			q.setParameter("ID", ID);
			int i = q.executeUpdate();
			result = "updated";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}
		return result;
	}

	public JSONArray viewSheduleConfigurationDao() {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(ScheduleConfiguration.class);
			List<ScheduleConfiguration> dataList = criteria.list();
			array1 = new JSONArray();
			for (ScheduleConfiguration confTempData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(confTempData.getBACKUP_PATH());
				array.put(confTempData.getBACKUP_SCHEDULE());
				array.put(confTempData.getCONFIGURATION_TYPE());
				array.put(confTempData.getNODE_IP());
				array.put(confTempData.getTIME_PICKER());
				array.put("<a href=\"editScheduleConfiguration/" + confTempData.getID()
						+ "\"><i class=\"fas fa-edit\"></a>");
				array.put(
						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteSheduleConfiguration(\""
								+ confTempData.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public String deleteSheduleConfigurationDao(long ID) {
		String result = null;
		try {
			Query q = getSession().createQuery("Delete from ScheduleConfiguration where ID=:ID");
			q.setParameter("ID", ID);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String updateScheduleConfigurationDao(long ID, String backupPath, String runConfig, String startupConfig,
			String backup, String group_name, String ip_address, String configType, String backupSchedule,
			String datepicker) {

		String result = "";
		StringTokenizer ip = new StringTokenizer(ip_address, ",");
		result = "success";
		try {

//			while (ip.hasMoreTokens()) {
//				String ipAdd = ip.nextToken();
			Query q = getSession().createQuery(
					"UPDATE ScheduleConfiguration SET BACKUP_PATH=:backupPath, CONFIGURATION_TYPE=:configType , BACKUP_SCHEDULE=:backupSchedule, TIME_PICKER=:datepicker,NODE_IP=:ip_address  WHERE ID=:ID");
			q.setParameter("ID", ID);
			q.setParameter("backupPath", backupPath);
			q.setParameter("configType", configType);
			q.setParameter("backupSchedule", backupSchedule);
			q.setParameter("datepicker", datepicker);
			q.setParameter("ip_address", ip_address);

			int i = q.executeUpdate();
//			}

			result = "success";

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public ScheduleConfigurationBean editScheduleConfigurationDao(long ID) {
		System.out.println("edit user:" + ID);
		String result = null;
		ScheduleConfigurationBean newObj = null;
		ScheduleConfigurationBean configData = new ScheduleConfigurationBean();
		try {
			Query q = getSession().createQuery("from ScheduleConfiguration where ID=:ID");
			q.setParameter("ID", ID);
			// List<ScheduleConfiguration> listData = q.list();
			List<ScheduleConfiguration> listdata = q.list();
			configData.setID(listdata.get(0).getID());
			configData.setBackuppath(listdata.get(0).getBACKUP_PATH());
			configData.setBackupSchedule(listdata.get(0).getBACKUP_SCHEDULE());
			configData.setNode_ip(listdata.get(0).getNODE_IP());
			configData.setDatepicker(listdata.get(0).getTIME_PICKER());
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}
		return configData;
	}

	public List<ScheduleConfigurationBean> scheduleconfigReport(String from_date, String to_date,
			List<String> ip_address) {

		String ip_data = ip_address.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "SELECT sc.ID, sc.BACKUP_PATH, sc.BACKUP_SCHEDULE, sc.CONFIGURATION_TYPE,sc.NODE_IP,sc.TIME_PICKER FROM schedule_configuration sc WHERE"
				+ " sc.NODE_IP IN ('" + ip_data + "') AND sc.TIME_PICKER BETWEEN '" + from_date + "' AND '" + to_date
				+ "'";
		Query q = getSession().createSQLQuery(query);
		List<ScheduleConfigurationBean> dataList = new ArrayList<ScheduleConfigurationBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				ScheduleConfigurationBean bean = new ScheduleConfigurationBean();
				bean.setID(id);
				bean.setBackuppath(a[1].toString());
				bean.setBackupSchedule(a[2].toString());
				bean.setConfigType(a[3].toString());
				bean.setNode_ip(a[4].toString());
				bean.setDatepicker(a[5].toString());
				bean.setStutus("");
				bean.setTimestamp("");
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public JSONArray jsonInterfaceParameterDao(String userScopeData) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Query q = getSession().createQuery(
					"SELECT ifm FROM InterfaceMonitoring ifm, AddNodeModel add_node WHERE ifm.NODE_IP=add_node.DEVICE_IP AND "
							+ userScopeData);
			List<InterfaceMonitoring> dataList = q.list();
			array1 = new JSONArray();
			for (InterfaceMonitoring confTempData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);

				array.put(confTempData.getPROCURED_BANDWIDTH());
				array.put(confTempData.getINTERFACE_NAME());
				array.put(confTempData.getNODE_IP());
				array.put(confTempData.getMONITORING_PARAM());
				array.put(confTempData.getBW_HISTORY_PARAM());
				array.put(confTempData.getCRC_HISTORY_PARAM());
				array.put(confTempData.getBW_THRESHOLD());
				array.put(confTempData.getMAIL_ALERT());
				array.put(confTempData.getSMS_ALERT());
				array.put(confTempData.getAUTO_TICKETING());
				array.put("<a href=\"editSchedule/" + confTempData.getID() + "\"><i class=\"fas fa-edit\"></a>");
				array.put(
						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteInterfaceParameter(\""
								+ confTempData.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public String deleteInterfaceParameterDao(long ID) {
		String result = null;
		try {
			Query q = getSession().createQuery("Delete from InterfaceMonitoring where ID=:ID");
			q.setParameter("ID", ID);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String addSMSConfigurationDao(String smsProvider, String smsUrl, String username, String password) {
		String result = null;
		Criteria criteria = getSession().createCriteria(SmsConfiguration.class);
		List<SmsConfiguration> dataList = criteria.list();
		if (!dataList.isEmpty()) {
			int dataconparision = 0;
			for (SmsConfiguration confTempData : dataList) {
				if (confTempData.getSMS_PROVIDER().equals(smsProvider)) {
//					try {
//						Query q = getSession().createQuery(
//								"UPDATE CommandMaster SET COMMAND=:command  WHERE TEMPLATE_NAME=:templateName");
//						q.setParameter("templateName", templateName);
//						q.setParameter("command", command);
//						int i = q.executeUpdate();
//					} catch (Exception e) {
//						System.out.println("Exception:" + e);
//					}
					result = "already";
					break;
				} else if (dataList.size() - 1 == dataconparision) {
					try {
						SmsConfiguration confTempDataUpdate = new SmsConfiguration();
						confTempDataUpdate.setSMS_PROVIDER(smsProvider);
						confTempDataUpdate.setSMS_URL(smsUrl);
						confTempDataUpdate.setUSERNAME(username);
						confTempDataUpdate.setPASSWORD(password);
						getSession().save(confTempDataUpdate);
					} catch (Exception e) {
						System.out.println("Exception:" + e);

					}
					result = "added";
				}
				dataconparision++;
			}
		} else {
			SmsConfiguration confTempDataUpdate = new SmsConfiguration();
			confTempDataUpdate.setSMS_PROVIDER(smsProvider);
			confTempDataUpdate.setSMS_URL(smsUrl);
			confTempDataUpdate.setUSERNAME(username);
			confTempDataUpdate.setPASSWORD(password);
			getSession().save(confTempDataUpdate);
			result = "added";
		}

		return result;
	}

	public JSONArray viewSMSConfigurationDao() {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(SmsConfiguration.class);
			List<SmsConfiguration> dataList = criteria.list();
			array1 = new JSONArray();
			for (SmsConfiguration confTempData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(confTempData.getSMS_PROVIDER());
				array.put(confTempData.getSMS_URL());
				array.put(confTempData.getUSERNAME());
				array.put(
						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteSMSConfiguration(\""
								+ confTempData.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public String deleteSMSConfigurationDao(long ID) {
		String result = null;
		try {
			Query q = getSession().createQuery("Delete from SmsConfiguration where ID=:ID");
			q.setParameter("ID", ID);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, String> getCompanyName() {
		Map<String, String> company = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from CompanyMasterModel");

			List<CompanyMasterModel> listdata = q.list();

			for (CompanyMasterModel companyName : listdata) {
				company.put(companyName.getCOMPANY_NAME(), companyName.getCOMPANY_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return company;
	}

	public Map<String, String> getStateName() {
		Map<String, String> state = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from StateMasterModel");

			List<StateMasterModel> listdata = q.list();

			for (StateMasterModel stateName : listdata) {
				state.put(stateName.getSTATE_NAME(), stateName.getSTATE_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return state;
	}

	public Map<String, String> getZoneName() {
		Map<String, String> zone = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from ZoneMasterModel");

			List<ZoneMasterModel> listdata = q.list();

			for (ZoneMasterModel zoneName : listdata) {
				zone.put(zoneName.getZONE_NAME(), zoneName.getZONE_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return zone;
	}

	public Map<String, String> getDistrictName() {
		Map<String, String> district = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from DistrictMasterModel");

			List<DistrictMasterModel> listdata = q.list();

			for (DistrictMasterModel districtName : listdata) {
				district.put(districtName.getDISTRICT_NAME(), districtName.getDISTRICT_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return district;
	}

	public String addsnmpModal(String nodeIP, String snmp) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		try {

			while (ip.hasMoreTokens()) {
				String snmpNodeIP = ip.nextToken();
				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + snmpNodeIP + "'")
						.list();

				if (query1.isEmpty()) {
					ad = new AddNodeModel();
					ad.setDEVICE_IP(snmpNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("A");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query updateQuery1 = getSession().createQuery(
							"update AddNodeModel set snmp='" + snmp + "' where device_ip='" + snmpNodeIP + "'");
					updateQuery1.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public String addGroupNameModal(String nodeIP, String groupName) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		try {

			while (ip.hasMoreTokens()) {
				String snmpNodeIP = ip.nextToken();
				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + snmpNodeIP + "'")
						.list();

				if (query1.isEmpty()) {
					ad = new AddNodeModel();
					ad.setDEVICE_IP(snmpNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("A");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query updateQuery1 = getSession().createQuery("update AddNodeModel set GROUP_NAME='" + groupName
							+ "' where device_ip='" + snmpNodeIP + "'");
					updateQuery1.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public String addserviceProviderModal(String nodeIP, String serviceProvider) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		try {

			while (ip.hasMoreTokens()) {
				String snmpNodeIP = ip.nextToken();
				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + snmpNodeIP + "'")
						.list();

				if (query1.isEmpty()) {
					ad = new AddNodeModel();
					ad.setDEVICE_IP(snmpNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("A");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query updateQuery1 = getSession().createQuery("update AddNodeModel set SERVICE_PROVIDER='"
							+ serviceProvider + "' where device_ip='" + snmpNodeIP + "'");
					updateQuery1.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public String addcompanyModal(String nodeIP, String company) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		try {

			while (ip.hasMoreTokens()) {
				String snmpNodeIP = ip.nextToken();
				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + snmpNodeIP + "'")
						.list();

				if (query1.isEmpty()) {
					ad = new AddNodeModel();
					ad.setDEVICE_IP(snmpNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("A");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query updateQuery1 = getSession().createQuery(
							"update AddNodeModel set COMPANY='" + company + "' where device_ip='" + snmpNodeIP + "'");
					updateQuery1.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public String adddeviceTypeModal(String nodeIP, String deviceType) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		try {

			while (ip.hasMoreTokens()) {
				String snmpNodeIP = ip.nextToken();
				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + snmpNodeIP + "'")
						.list();

				if (query1.isEmpty()) {
					ad = new AddNodeModel();
					ad.setDEVICE_IP(snmpNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("A");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query updateQuery1 = getSession().createQuery("update AddNodeModel set DEVICE_TYPE='" + deviceType
							+ "' where device_ip='" + snmpNodeIP + "'");
					updateQuery1.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public String addstateModal(String nodeIP, String state) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		try {

			while (ip.hasMoreTokens()) {
				String snmpNodeIP = ip.nextToken();
				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + snmpNodeIP + "'")
						.list();

				if (query1.isEmpty()) {
					ad = new AddNodeModel();
					ad.setDEVICE_IP(snmpNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("A");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query updateQuery1 = getSession().createQuery(
							"update AddNodeModel set STATE='" + state + "' where device_ip='" + snmpNodeIP + "'");
					updateQuery1.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public String adddistrictModal(String nodeIP, String district) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		try {

			while (ip.hasMoreTokens()) {
				String snmpNodeIP = ip.nextToken();
				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + snmpNodeIP + "'")
						.list();

				if (query1.isEmpty()) {
					ad = new AddNodeModel();
					ad.setDEVICE_IP(snmpNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("A");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query updateQuery1 = getSession().createQuery(
							"update AddNodeModel set DISTRICT='" + district + "' where device_ip='" + snmpNodeIP + "'");
					updateQuery1.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public String addlocationModal(String nodeIP, String location) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		try {

			while (ip.hasMoreTokens()) {
				String snmpNodeIP = ip.nextToken();
				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + snmpNodeIP + "'")
						.list();

				if (query1.isEmpty()) {
					ad = new AddNodeModel();
					ad.setDEVICE_IP(snmpNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("A");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query updateQuery1 = getSession().createQuery(
							"update AddNodeModel set LOCATION='" + location + "' where device_ip='" + snmpNodeIP + "'");
					updateQuery1.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public String deleteBulkNode(String nodeIP) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		try {

			while (ip.hasMoreTokens()) {
				String snmpNodeIP = ip.nextToken();

				Query deleteQuery = getSession()
						.createQuery("delete from AddNodeModel where device_ip='" + snmpNodeIP + "'");
				deleteQuery.executeUpdate();
				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public String addzoneModal(String nodeIP, String zone) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		try {

			while (ip.hasMoreTokens()) {
				String snmpNodeIP = ip.nextToken();
				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + snmpNodeIP + "'")
						.list();

				if (query1.isEmpty()) {
					ad = new AddNodeModel();
					ad.setDEVICE_IP(snmpNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("A");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query updateQuery1 = getSession().createQuery(
							"update AddNodeModel set ZONE='" + zone + "' where device_ip='" + snmpNodeIP + "'");
					updateQuery1.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<AddNodeModel> searchByIp(String ip) {
		try {
			Query query = getSession().createQuery("FROM AddNodeModel WHERE DEVICE_IP='" + ip + "'");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions appropriately
			return Collections.emptyList(); // or return null, throw an exception, etc.
		}
	}

	public List<AddNodeModel> searchByName(String name) {
		try {
			Query query = getSession().createQuery("FROM AddNodeModel WHERE DEVICE_NAME='" + name + "'");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions appropriately
			return Collections.emptyList(); // or return null, throw an exception, etc.
		}
	}

	public List<AddNodeModel> searchByGroupName(String groupName) {
		try {
			Query query = getSession().createQuery("FROM AddNodeModel WHERE GROUP_NAME='" + groupName + "'");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions appropriately
			return Collections.emptyList(); // or return null, throw an exception, etc.
		}
	}

	public List<AddNodeModel> searchByDeviceType(String deviceType) {
		try {
			Query query = getSession().createQuery("FROM AddNodeModel WHERE DEVICE_TYPE='" + deviceType + "'");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions appropriately
			return Collections.emptyList(); // or return null, throw an exception, etc.
		}
	}

	public JSONArray mapTopologyData() {
		JSONArray mapArray = new JSONArray();
		JSONObject mapObj = null;
		try {
			List<Object[]> listObj = getSession()
					.createSQLQuery("select ad.location, nm.NODE_IP, ad.LATITUDE, ad.LONGITUDE, nm.Node_status \r\n"
							+ "from add_node ad, node_monitoring nm where ad.DEVICE_IP = nm.NODE_IP;")
					.list();
			for (Object[] obj : listObj) {
				mapObj = new JSONObject();
				mapObj.put("location", obj[0]);
				mapObj.put("deviceIP", obj[1]);
				mapObj.put("latitude", obj[2]);
				mapObj.put("longitude", obj[3]);
				mapObj.put("status", obj[4]);
				mapArray.put(mapObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapArray;
	}

	public Map<String, String> getspeedTestIps() {

		Map<String, String> companyMap = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from SpeedTestModel ");

			List<SpeedTestModel> listdata = q.list();

			for (SpeedTestModel company : listdata) {
				String description = "";
				if (company.getDiscription() == null || company.getDiscription().trim().isEmpty()) {
					description = "";
				} else {
					description = "(" + company.getDiscription() + ")";
				}

				companyMap.put(company.getDevice_IP(), company.getDevice_IP() + description);
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return companyMap;
	}

	public List<SpeedTestModel> getspeedTestDeviceslisting() {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(SpeedTestModel.class);
		return criteria.list();
	}

	public SpeedTestBean editSpeedTestDeviceMaster(long iD) {

		// TODO Auto-generated method stub
		System.out.println("edit speed test Master:" + iD);
		String result = null;

		SpeedTestBean speedtestMaster = new SpeedTestBean();
		try {
			Query q = getSession().createQuery("from SpeedTestModel where ID=:id");
			q.setParameter("id", iD);
			// List<UserMasterModel> listData = q.list();
			List<SpeedTestModel> listdata = q.list();

			speedtestMaster.setDevice_IP(listdata.get(0).getDevice_IP());
			speedtestMaster.setDiscription(listdata.get(0).getDiscription());
			speedtestMaster.setID(iD);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return speedtestMaster;
	}

	public String updateSpeedTestDeviceMaster(String device_IP, String discription, long id) {

		String result = null;
		try {
			Query q = getSession().createQuery("Update SpeedTestModel set discription=:discription where ID=:id");
			q.setParameter("discription", discription);

			q.setParameter("id", id);

			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}
}
