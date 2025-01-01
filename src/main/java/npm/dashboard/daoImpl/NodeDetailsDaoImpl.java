package npm.dashboard.daoImpl;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dynamicPassword.Decryption;
import npm.configuration.AbstractDao;
import npm.dashboard.dao.NodeDetailsDao;
import npm.model.AddNodeModel;
import npm.model.Auto_Topology;
import npm.model.Auto_Topology_Change_History;
import npm.model.Auto_Topology_Pooling;
import npm.model.InterfaceMonitoring;
import npm.model.NodeAvailablity;
import npm.model.NodeHealthMonitoring;
import npm.model.NodeMonitoringModel;
import npm.model.NodeStatusHistory;
import npm.model.UserLoginHistory;
import npm.model.UserManageScopeModel;
import npm.model.UserMasterModel;

@Repository
@Transactional
public class NodeDetailsDaoImpl extends AbstractDao<Integer, AddNodeModel> implements NodeDetailsDao {

	public JSONObject nodeLatencyPacketDrop(String ip_address) {

		JSONObject jsonObj = new JSONObject();
		JSONArray latency_obj = null;
		JSONArray packetLoss_obj = null;
		try {
			System.out.println("ip address = " + ip_address);
			Query q = getSession().createQuery("from NodeMonitoringModel where NODE_IP =:NODE_IP");
			q.setParameter("NODE_IP", ip_address);
			List<NodeMonitoringModel> list = q.list();

			for (NodeMonitoringModel data : list) {
				float latency = data.getLATENCY();
				float packetLoss = data.getPACKET_LOSS();

				latency_obj = new JSONArray();
				latency_obj.put(latency);

				packetLoss_obj = new JSONArray();
				packetLoss_obj.put(packetLoss);
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		jsonObj.put("latency", latency_obj);
		jsonObj.put("packetLoss", packetLoss_obj);
		System.out.println("data = " + jsonObj);
		return jsonObj;
	}

	public JSONObject nodeAddNode(String ip_address) {

		JSONObject jsonObj = new JSONObject();
		JSONArray dataObj = null;

		try {
			System.out.println("ip address = " + ip_address);
			Query q = getSession().createQuery("from AddNodeModel where DEVICE_IP =:DEVICE_IP");
			q.setParameter("DEVICE_IP", ip_address);
			List<AddNodeModel> list = q.list();

			for (AddNodeModel data : list) {

				dataObj = new JSONArray();

				dataObj.put(data.getCOMPANY());
				dataObj.put(data.getDEVICE_NAME());
				dataObj.put(data.getDEVICE_TYPE());
				dataObj.put(data.getGROUP_NAME());
				dataObj.put(data.getDISTRICT());
				dataObj.put(data.getLOCATION());
				dataObj.put(data.getSERVICE_PROVIDER());
				dataObj.put(data.getSNMP());
				dataObj.put(data.getSTATE());
				dataObj.put(data.getZONE());

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println(dataObj);
		jsonObj.put("allData", dataObj);
		return jsonObj;
	}

	public JSONObject nodeAvailabilityDetails(String ip_address) {

		JSONObject jsonObj = new JSONObject();

		JSONArray downtime_percent = new JSONArray();
		JSONArray uptime_percent = new JSONArray();
		JSONArray event_timestamp = new JSONArray();

		JSONArray downtime_percent_obj = null;
		JSONArray uptime_percent_obj = null;
		JSONArray event_timestamp_obj = null;

		try {
			System.out.println("ip address = " + ip_address);
			Query q = getSession().createQuery("from NodeAvailablity where NODE_IP =:NODE_IP");
			q.setParameter("NODE_IP", ip_address);
			List<NodeAvailablity> list = q.list();

			for (NodeAvailablity data : list) {

				double downtimePercent = data.getDOWNTIME_PERCENT();
				double uptimePercent = data.getUPTIME_PERCENT();
				Date eventTimestamp = data.getEVENT_TIMESTAMP();

				downtime_percent_obj = new JSONArray();
				downtime_percent_obj.put(downtimePercent);
				downtime_percent.put(downtime_percent_obj);

				uptime_percent_obj = new JSONArray();
				uptime_percent_obj.put(uptimePercent);
				uptime_percent.put(uptime_percent_obj);

				event_timestamp_obj = new JSONArray();
				event_timestamp_obj.put(eventTimestamp);
				event_timestamp.put(event_timestamp_obj);

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		jsonObj.put("uptimePercent", uptime_percent);
		jsonObj.put("downtimePercent", downtime_percent);
		jsonObj.put("eventTimestamp", event_timestamp);
		return jsonObj;
	}

	public JSONArray nodeStatusHistoryDetails(String ip_address) {

		JSONArray jsonData = null;
		JSONArray jsonMain = new JSONArray();
		long srno = 0;
		try {

			System.out.println("IP = " + ip_address);
			Query q = getSession().createQuery("from NodeStatusHistory where NODE_IP=:NODE_IP ORDER BY ID DESC");
			q.setParameter("NODE_IP", ip_address);
			q.setMaxResults(5);

//			Query q = getSession().createQuery(
//					"SELECT INFMON.INTERFACE_NAME,INFMON.INTERFACE_STATUS,INFMON.INTERFACE_STATUS_TYPE,INFMON.EVENT_TIMESTAMP FROM InterfaceStatusHistoryModel AS INFMON where INFMON.NODE_IP=:nodeIP AND INFMON.INTERFACE_NAME=:interfaceName");
//			q.setParameter("nodeIP", deviceIP);
//			q.setParameter("interfaceName", intName);
//			q.setMaxResults(5);
//			

			List<NodeStatusHistory> list = q.list();

			for (NodeStatusHistory data : list) {
				jsonData = new JSONArray();
				srno++;
				jsonData.put(srno);
				jsonData.put(data.getNODE_IP());
//				jsonData.put(data.getNODE_STATUS());
				if (data.getNODE_STATUS().equals("Up")) {
					jsonData.put("<small class='text-success mr-1'> <i class='fas fa-arrow-up'></i> Up </small>");
				} else {
					jsonData.put("<small class='text-danger mr-1'> <i class='fas fa-arrow-down'></i> Down </small>");
				}
				jsonData.put(data.getEVENT_TIMESTAMP());
				jsonMain.put(jsonData);

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
//		jsonObj.put("nodeStatus", node_status_array);
//		jsonObj.put("eventTimestamp", event_timestamp_array);
		return jsonMain;
	}

	public JSONObject nodeHealthMonitoringDetails(String ip_address) {

		JSONObject jsonObj = new JSONObject();
		JSONArray dataObj = null;

		try {
			System.out.println("ip address = " + ip_address);
			Query q = getSession().createQuery("from NodeHealthMonitoring where NODE_IP=:NODE_IP");
			q.setParameter("NODE_IP", ip_address);
			List<NodeHealthMonitoring> list = q.list();

			for (NodeHealthMonitoring data : list) {

				dataObj = new JSONArray();

				dataObj.put(data.getCPU_STATUS());
				dataObj.put(data.getCPU_UTILIZATION());
				dataObj.put(data.getFREE_MEMORY());
				dataObj.put(data.getMAKE_AND_MODEL());
				dataObj.put(data.getMEMORY_STATUS());
				dataObj.put(data.getMEMORY_UTILIZATION());
				dataObj.put(data.getNODE_NAME());
				dataObj.put(data.getSERIAL_NO());
				dataObj.put(data.getTEMPERATURE());
				dataObj.put(data.getTOTAL_MEMORY());
				dataObj.put(data.getUPTIME());
				dataObj.put(data.getUSED_MEMORY());
				dataObj.put(data.getVERSION());

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println(dataObj);
		jsonObj.put("allData", dataObj);
		return jsonObj;
	}

	public JSONArray InterfaceStatusHistoryDetails(String ip_address) {

		JSONArray jsonData = null;
		JSONArray jsonMain = new JSONArray();
		JSONObject jsonObject = null;
		long srno = 0;
		try {

			System.out.println("IP = " + ip_address);
			Query q = getSession().createQuery(
					"from InterfaceMonitoring where NODE_IP=:NODE_IP and MONITORING_PARAM=:MONITORING_PARAM ");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("MONITORING_PARAM", "Yes");
			List<InterfaceMonitoring> list = q.list();

			for (InterfaceMonitoring data : list) {
//				jsonData = new JSONArray();
				srno++;
				jsonObject = new JSONObject();
//				jsonData.put("<a href=http://localhost:8083/NPMWebConsole/dashboard/interfaceInfoPage?nodeIP="+data.getNODE_IP()+"&intName="+URLEncoder.encode(data.getINTERFACE_NAME(), "UTF-8")+">"+ data.getINTERFACE_NAME() + "</a>");
				jsonObject.put("srno", srno);
				jsonObject.put("interfaceName", data.getINTERFACE_NAME());
//				jsonData.put(data.getOPER_STATUS());
				jsonObject.put("interfaceIP", (data.getInterface_IP_Assign() == null) ? "NA"
						: data.getInterface_IP_Assign().equals("") ? "NA" : data.getInterface_IP_Assign());
				if (data.getOPER_STATUS().equals("up")) {
					jsonObject.put("oper_status",
							"<small class='text-success mr-1'> <i class='fas fa-arrow-up'></i> Up </small>");
				} else {
					jsonObject.put("oper_status",
							"<small class='text-danger mr-1'> <i class='fas fa-arrow-down'></i> Down </small>");
				}

				jsonObject.put("aliasName", (data.getALIAS_NAME() == null) ? "NA"
						: data.getALIAS_NAME().equals("") ? "NA" : data.getALIAS_NAME());
				jsonObject.put("ICMPStatus", (data.getInterface_IP_ICMP_Status() == null) ? "NA"
						: data.getInterface_IP_ICMP_Status().equals("") ? "NA"
								: data.getInterface_IP_ICMP_Status().equals("Up")
										? "<small class='text-success mr-1'> <i class='fas fa-arrow-up'></i> Up </small>"
										: data.getInterface_IP_ICMP_Status().equals("Down")
												? "<small class='text-danger mr-1'> <i class='fas fa-arrow-down'></i> Down </small>"
												: data.getInterface_IP_ICMP_Status());
				jsonMain.put(jsonObject);

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
//		jsonObj.put("nodeStatus", node_status_array);
//		jsonObj.put("eventTimestamp", event_timestamp_array);
		System.out.println("interface details ==== " + jsonMain);
		return jsonMain;
	}

	public JSONArray basicInfoDetails(String ip_address) {
		System.out.println("basicInfoDetails Dao IMPL");
		JSONArray arrayJson = new JSONArray();
		JSONObject jsonObjData = new JSONObject();
		try {
			Query q = getSession().createQuery("from AddNodeModel where DEVICE_IP=:NODE_IP ");
			q.setParameter("NODE_IP", ip_address);
			List<AddNodeModel> object = q.list();
			for (AddNodeModel list : object) {

				jsonObjData.put("Node_ip", list.getDEVICE_IP());
				jsonObjData.put("NodeNAme", list.getDEVICE_NAME());
				jsonObjData.put("Location", list.getLOCATION());
				jsonObjData.put("Company", list.getCOMPANY());
//				jsonObj.put("State", list.getSTATE());
				jsonObjData.put("District", list.getDISTRICT());
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

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
			Query q1 = getSession().createQuery("from NodeMonitoringModel where NODE_IP=:NODE_IP ");
			q1.setParameter("NODE_IP", ip_address);
			List<NodeMonitoringModel> nodeHealthList = q1.list();
			for (NodeMonitoringModel list : nodeHealthList) {
				jsonObjData.put("status", list.getNODE_STATUS());
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

	public JSONArray nodeUpTimeStatusDetails(String ip_address) {

		JSONArray jsonData = null;

		JSONArray upArray = null;
		JSONArray downArray = null;
		JSONArray catArray = null;

		try {

			System.out.println("IP = " + ip_address);
//			Query q = getSession().createQuery("FROM NodeAvailablity where EVENT_TIMESTAMP BETWEEN CURDATE()-7 AND CURDATE() AND NODE_IP=:NODE_IP ");
			Query q = getSession()
					.createQuery("FROM NodeAvailablity where NODE_IP=:NODE_IP ORDER BY EVENT_TIMESTAMP DESC");

			q.setParameter("NODE_IP", ip_address);
//			q.setFirstResult(start);
			q.setMaxResults(5);
			List<NodeAvailablity> list = q.list();

			String category = "";
			List<String> catList = new ArrayList();
			List<Double> upList = new ArrayList();
			List<Double> downList = new ArrayList();
			double uptime = 0;
			double downtime = 0;
			for (NodeAvailablity data : list) {

				downArray = new JSONArray();
				catArray = new JSONArray();
				upArray = new JSONArray();
				category = data.getEVENT_TIMESTAMP().toString();
				catList.add(category);

				uptime = data.getUPTIME_PERCENT();
				upList.add(uptime);

				downtime = data.getDOWNTIME_PERCENT();
				downList.add(downtime);

			}
			upArray = new JSONArray(upList);
			downArray = new JSONArray(downList);
			catArray = new JSONArray(catList);

			JSONObject jsonMain = new JSONObject();
			jsonMain.put("upArray", upArray);
			jsonMain.put("downArray", downArray);
			jsonMain.put("catArray", catArray);
			jsonData = new JSONArray();
			jsonData.put(jsonMain);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
//		jsonObj.put("nodeStatus", node_status_array);
//		jsonObj.put("eventTimestamp", event_timestamp_array);
		return jsonData;
	}

	public JSONArray nodeLatencyStatusDetails(String ip_address) {
		System.out.println("Latency Packet drop live graph ip:" + ip_address);
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession()
					.createQuery("SELECT LATENCY,PACKET_LOSS  FROM NodeMonitoringModel where NODE_IP=:nodeIP");
			q.setParameter("nodeIP", ip_address);

			List<?> dataList = q.list();
			array1 = new JSONArray();
			for (int i = 0; i < dataList.size(); i++) {
				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("latency", row[0]);
				jsonObject.put("packetLoss", row[1]);
				array1.put(jsonObject);
			}

			System.out.println("Latency Packet drop Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;

//		JSONArray jsonData = null;
//
//		JSONArray upArray = null;
//		JSONArray downArray = null;
//		JSONArray catArray = null;
//
//		try {
//
//			System.out.println("IP = " + ip_address);
//			Date date = new Date();
//			String currentDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
//			currentDate = currentDate+"%";
//			Query q = getSession().createQuery("FROM NodeMonitoringModel where NODE_IP=:NODE_IP");
////			
////			Query q = getSession().createQuery("FROM NodeMonitoringModel where NODE_IP=:NODE_IP AND LATENCY_TIMESTAMP LIKE '"+currentDate+"'");
////			Query q = getSession().createQuery("FROM NodeMonitoringModel where NODE_IP=:NODE_IP");
//
//			q.setParameter("NODE_IP", ip_address);
////			q.setFirstResult(start);
////			q.setMaxResults(5);
//			List<NodeMonitoringModel> list = q.list();
//
//			String category = "";
//			List<String> catList = new ArrayList();
//			List<Double> upList = new ArrayList();
//			List<Double> downList = new ArrayList();
//			double uptime = 0;
//			double downtime = 0;
//			for (NodeMonitoringModel data : list) {
//
//				downArray = new JSONArray();
//				catArray = new JSONArray();
//				upArray = new JSONArray();
////				category = data.getLATENCY().toString();
////				catList.add(category);
//
//				uptime = data.getPACKET_LOSS();
//				upList.add(uptime);
//
//				downtime = data.getLATENCY();
//				downList.add(downtime);
//
//			}
//			upArray = new JSONArray(upList);
//			downArray = new JSONArray(downList);
////			catArray = new JSONArray(catList);
//
//			JSONObject jsonMain = new JSONObject();
//			jsonMain.put("packetLoss", upArray);
//			jsonMain.put("latency", downArray);
////			jsonMain.put("catArray", catArray);
//			jsonData = new JSONArray();
//			jsonData.put(jsonMain);
//		} catch (Exception e) {
//			System.out.println("Exception:" + e);
//		}
//		
//		System.out.println("LatencyPacket Drop:" + jsonData);
//		
////		jsonObj.put("nodeStatus", node_status_array);
////		jsonObj.put("eventTimestamp", event_timestamp_array);
//		return jsonData;
	}

	// CPU AND MEMORY UTILIZATION
	public JSONArray cpuAndMemoryUtilization(String ip_address) {
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT CPU_UTILIZATION,MEMORY_UTILIZATION  FROM NodeHealthMonitoring where NODE_IP=:nodeIP");
			q.setParameter("nodeIP", ip_address);

			List<?> dataList = q.list();
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

//	@Override
	public String getDBVerification(String username, String Password) {
		System.out.println("Inside LoginDaoImpl getDBVerification method");
		String db_password = null;
		String db_username = null;
//		byte[] buffer = new byte[2000];
//		String usrname = "NA";
		try {
			System.out.println("Before Projection List ");
			Session s = getSession();
			System.out.println("s =" + s);
			Query query = getSession().createQuery("from UserMasterModel where USERNAME=:u");
			query.setParameter("u", username);
			List<UserMasterModel> dataList = query.list();
			for (UserMasterModel newObj : dataList) {
				db_username = newObj.getUSERNAME();
				Decryption decrypt = new Decryption();
				db_password = new String(decrypt.decrypt(newObj.getPASSWORD()));
//				db_password=newObj.getPASS();

			}
		} catch (

		Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("db_username = " + db_username + "\ndb_password = " + db_password);
		return db_password + "~" + db_username;
	}

	// Auto Topology
	public JSONArray getAutoNetworkTopology(String username, String contextPath) {
		JSONArray finalJSONArray = null;
		JSONObject finalJSONObject = null;
//		JSONObject edgesJSON = null;

		JSONArray edgesArray = null;
		JSONArray nodesArray = null;

		// For Node Down List
		HashMap<String, String> NodeDownMap = new HashMap<String, String>();
		// Query NodeDownQuery = getSession().createQuery("select distinct
		// IP_ADDRESS,ROUTER_STATUS from Add_Interface");
		Query NodeDownQuery = getSession().createQuery(
				"select a.DEVICE_IP,n.NODE_STATUS  from AddNodeModel a JOIN NodeMonitoringModel n  on a.DEVICE_IP=n.NODE_IP");

		List<Object[]> NodeDownList = NodeDownQuery.list();
		for (Object[] obj : NodeDownList) {
			NodeDownMap.put(obj[0].toString(), obj[1].toString());
		}

		try {
			int i = 0;
			Set<String> set = new HashSet<String>();
			List<Object[]> objList = new ArrayList<Object[]>();
			HashMap<String, String> hashMap = new HashMap<String, String>();

			// For Device IP List
//			Query deviceQuery = getSession().createQuery("select distinct local_device_mac from auto_topology");
//			List<String> deviceIPList = deviceQuery.list();
//			set.addAll(deviceIPList);
			Query deviceQuery = getSession().createQuery("select distinct device_ip from Auto_Topology");
			List<String> deviceIPList = deviceQuery.list();
			set.addAll(deviceIPList);

			// For Remote Device IP List
//			Query localdeviceQuery = getSession().createQuery("select distinct remote_device_mac from auto_topology");
//			List<String> localDeviceIPList = localdeviceQuery.list();
//			set.addAll(localdeviceQuery.list());
			Query localdeviceQuery = getSession().createQuery("select distinct remote_device_ip from Auto_Topology");
			List<String> localDeviceIPList = localdeviceQuery.list();
			set.addAll(localdeviceQuery.list());

			nodesArray = new JSONArray();

			nodesArray = new JSONArray();
			Iterator<String> setIterator = set.iterator();
			while (setIterator.hasNext()) {
				String mac = setIterator.next();
				Query Query = getSession().createQuery(
//						"select a.IP_ADDRESS,a.DEVICE_NAME,a.DEVICE_MAC,a.ROUTER_STATUS ,a.DEVICE_NAME from add_interface a where a.DEVICE_MAC ='"
//								+ mac + "'");
//						"select a.IP_ADDRESS,a.DEVICE_NAME,a.DEVICE_MAC,a.ROUTER_STATUS ,a.DEVICE_NAME from Add_Interface a where a.IP_ADDRESS ='"
//								+ mac + "'");

						"select a.DEVICE_IP,a.DEVICE_NAME,a.DEVICE_MAC,n.NODE_STATUS ,a.DEVICE_TYPE from AddNodeModel a  JOIN NodeMonitoringModel n  on a.DEVICE_IP=n.NODE_IP where a.DEVICE_IP ='"
								+ mac + "'");
				List<Object[]> objList1 = Query.list();
				if (objList1.isEmpty()) {
					JSONObject nodesJSON1 = null;
					nodesJSON1 = new JSONObject();
					nodesJSON1.put("id", mac);
					nodesJSON1.put("label", mac);
					nodesJSON1.put("shape", "image");
					nodesJSON1.put("title", mac);
//					nodesJSON1.put("title", obj[2].toString() + "(" + obj[1].toString() + ")");
					nodesJSON1.put("font", "12px arial white");
					nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/wifi-router-down.png");
					nodesArray.put(nodesJSON1);

				} else {
					for (Object[] obj : objList1) {

						JSONObject nodesJSON1 = null;
						nodesJSON1 = new JSONObject();
						nodesJSON1.put("id", mac);
						nodesJSON1.put("label", obj[0].toString() + '\n' + "(" + obj[4].toString() + ")");
						nodesJSON1.put("title", obj[2].toString() + "(" + obj[1].toString() + ")");
						nodesJSON1.put("shape", "image");
						nodesJSON1.put("font", "12px arial white");
						if (obj[4].toString().equals("Router")) {
							if (obj[3].toString().endsWith("Up")) {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/wifi-router-up.png");
							} else {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/wifi-router-down.png");
							}
						} else if (obj[4].toString().equals("Switch")) {
							if (obj[3].toString().endsWith("Up")) {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/network-switch-up.png");
							} else {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/network-switch-down.png");
							}
						} else if (obj[4].toString().equals("Firewall")) {
							if (obj[3].toString().endsWith("Up")) {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/firewall-up.png");
							} else {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/firewall-down.png");
							}
						}
						nodesArray.put(nodesJSON1);
					}
				}

			}

			// end node json

			// interface json start

			// interface status map
			HashMap<String, String> foMap = new HashMap<String, String>();
			try {

				Query foQuery = getSession().createSQLQuery("select NODE_IP,INTERFACE_NAME from interface_monitoring");
				List<Object[]> objList2 = foQuery.list();
				for (Object[] obj : objList2) {
					foMap.put(obj[0].toString() + "~" + obj[1].toString(), "0");
				}
			} catch (Exception e) {

			}
			HashMap<String, String> LinkDownMap = new HashMap<String, String>();
			try {

				Query LinkDownQuery = getSession()
						.createSQLQuery("select distinct NODE_IP,INTERFACE_NAME,OPER_STATUS from interface_monitoring");
				List<Object[]> objList2 = LinkDownQuery.list();
				for (Object[] obj : objList2) {
					LinkDownMap.put(obj[0].toString() + "~" + obj[1].toString(), obj[2].toString());
				}
			} catch (Exception e) {

			}

			// For Remote DeviceIP List
			Query remoteDeviceIPNameQuery = getSession()
					.createQuery("select distinct remote_device_ip,remote_device_name from Auto_Topology");
			List<Object[]> objList1 = remoteDeviceIPNameQuery.list();
			for (Object[] obj : objList1) {
				hashMap.put(obj[0].toString(), obj[1].toString());
			}

			// For Edges JSON
			edgesArray = new JSONArray();
			HashMap<String, Double> CurveSet = new HashMap<String, Double>();
			int k = 0;
			Query autotopologyQuery = getSession().createQuery("from Auto_Topology");
			List<Auto_Topology> autoTopologyList = autotopologyQuery.list();
			for (Auto_Topology data : autoTopologyList) {
				k++;
				String local_mac = data.getLocal_device_mac().toString();
				String remote_mac = data.getRemote_device_mac().toString();
				String local_port = data.getLocal_interface_name().toString();
				String remote_port = data.getRemote_port_name().toString();
				String local_ip = data.getDevice_ip().toString();
				String remote_ip = data.getRemote_device_ip().toString();
				if (local_ip == null || local_ip.equals("-")) {
					local_ip = data.getLocal_device_mac().toString();
				}
				if (remote_ip == null || remote_ip.equals("-")) {
					remote_ip = data.getRemote_device_mac().toString();
				}

				// for local interface status
				String local_interface_status = null;
				if (LinkDownMap.containsKey(local_ip + "~" + local_port)) {
					local_interface_status = LinkDownMap.get(local_ip + "~" + local_port);
				} else {
					local_interface_status = "down";
				}

				// for remote inbterface status
				String remote_interface_status = null;
				if (LinkDownMap.containsKey(remote_ip + "~" + remote_port)) {
					remote_interface_status = LinkDownMap.get(remote_ip + "~" + remote_port);
				} else {
					remote_interface_status = "down";
				}

				// for fo_value
				String remote_fo_value = null;
				if (foMap.containsKey(remote_ip + "~" + remote_port)) {
					// remote_fo_value = foMap.get(remote_ip + "~" + remote_port);
					remote_fo_value = "-";
				} else {

				}
				String fo_value = null;
				if (foMap.containsKey(local_ip + "~" + local_port)) {
					fo_value = foMap.get(local_ip + "~" + local_port);

					fo_value = "-";
//						fo_value = fo_value.substring(0, fo_value.indexOf(" "));

				} else {
					fo_value = "-";
				}

				JSONObject edgesJSON1 = new JSONObject();
//				edgesJSON1.put("from", local_mac);
//				edgesJSON1.put("to", remote_mac);
				edgesJSON1.put("from", local_ip);
				edgesJSON1.put("to", remote_ip);
//				edgesJSON1.put("arrows", "to");
				edgesJSON1.put("id", String.valueOf(k));
				edgesJSON1.put("title", local_ip + "(" + local_port + ")(" + fo_value + ") >>" + remote_ip + "("
						+ remote_port + ")" + "(" + remote_fo_value + ")");
				if (remote_interface_status.equalsIgnoreCase("down") || local_interface_status.equalsIgnoreCase("down"))
					edgesJSON1.put("color", "Red");
				else
					edgesJSON1.put("color", "Green");

				double curveValue = 0.0;

				try {

					if (CurveSet.containsKey(local_ip + "~" + remote_ip)) {
						double old_val = CurveSet.get(local_ip + "~" + remote_ip);
						curveValue = old_val + 0.2;
					} else if (CurveSet.containsKey(remote_ip + "~" + local_ip)) {
						double old_val = CurveSet.get(local_ip + "~" + remote_ip);
						curveValue = old_val + 0.2;
					} else {
						curveValue = 0.2;
					}

					CurveSet.put(local_ip + "~" + remote_ip, curveValue);
					CurveSet.put(remote_ip + "~" + local_ip, curveValue);

				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}

				JSONObject obj11 = new JSONObject();
				obj11.put("type", "curvedCCW");
				obj11.put("roundness", curveValue);

				edgesJSON1.put("smooth", obj11);

				edgesArray.put(edgesJSON1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(nodesArray);
		System.out.println(edgesArray);
		finalJSONArray = new JSONArray();

		finalJSONArray.put(nodesArray);
		finalJSONArray.put(edgesArray);
		return finalJSONArray;

	}

	// Auto Topology History Data
	public JSONArray autoTopologyHistoryData(String from_date, String to_date) {
		// TODO Auto-generated method stub
		System.out.println("in auto topology history report");
		JSONArray jsonArray = null;
		jsonArray = new JSONArray();
		int sr_no = 0;
		try {

			Query q = getSession().createQuery(
					"from Auto_Topology_Pooling where DATETIME between '" + from_date + "' and '" + to_date + "'");

			List<Auto_Topology_Pooling> datalist = q.list();

			System.out.println("list=" + datalist);

			for (Auto_Topology_Pooling temp : datalist) {
				System.out.println("in for loop");

				JSONArray tempArray = new JSONArray();

				sr_no++;

				tempArray.put(sr_no);
				tempArray.put(temp.getDATETIME());
				tempArray.put(temp.getPOOLING_ID());
				tempArray.put(temp.getCHANGED_CONNECTIVITY());
				tempArray.put("<button class='btn btn-warning' style='color:black' onclick=viewHistory('"
						+ temp.getPOOLING_ID() + "')>View Changes</button>");
//				tempArray.put("<button class='btn btn-warning' style='color:black' onclick=viewTopology('"
//						+ temp.getPOOLING_ID() + "')>Topology View</button>");
//				tempArray.put("<button class='btn btn-warning' style='color:black' onclick=viewTopologyList('"
//						+ temp.getPOOLING_ID() + "')>Topology List</button>");

				jsonArray.put(tempArray);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonArray;

	}

	public JSONArray viewAutoTopologyHistoryOnID(String poolingID) {

		JSONArray jsonArray = null;
		jsonArray = new JSONArray();
		int sr_no = 0;
		try {

			Query q = getSession()
					.createQuery("from Auto_Topology_Change_History where POOLING_ID = '" + poolingID + "'");

			List<Auto_Topology_Change_History> datalist = q.list();

			for (Auto_Topology_Change_History temp : datalist) {

				JSONArray tempArray = new JSONArray();

				sr_no++;

				tempArray.put(sr_no);
				tempArray.put(temp.getDATETIME());
				tempArray.put(temp.getPOOLING_ID());
				tempArray.put(temp.getSRC_IP());
				tempArray.put(temp.getSRC_PORT());
				tempArray.put(temp.getPRV_DEST_IP());
				tempArray.put(temp.getPRV_DEST_PORT());
				tempArray.put(temp.getCURRENT_DEST_IP());
				tempArray.put(temp.getCURRENT_DEST_PORT());
				tempArray.put(temp.getSRC_MAC());
				tempArray.put(temp.getPRV_DEST_MAC());
				tempArray.put(temp.getCURRENT_DEST_MAC());

				jsonArray.put(tempArray);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonArray;

	}

	// Manage User Scope By Rohit
	public String getAdminScope(String username) {
		String adminScope = "";
		try {

			List<UserManageScopeModel> li = getSession()
					.createQuery("from UserManageScopeModel where USERNAME = '" + username + "'").list();
			for (UserManageScopeModel data : li) {
				adminScope += data.getADMIN();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminScope;
	}
	// End Manage User Scope

	public String getDashoardScope(String userName) {
		String dashboardScope = "";
		try {

			List<UserManageScopeModel> li = getSession()
					.createQuery("from UserManageScopeModel where USERNAME = '" + userName + "'").list();
			for (UserManageScopeModel data : li) {
				dashboardScope += data.getDASHBOARD();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dashboardScope;
	}

	public String getReportScope(String userName) {
		String reportScope = "";
		try {

			List<UserManageScopeModel> li = getSession()
					.createQuery("from UserManageScopeModel where USERNAME = '" + userName + "'").list();
			for (UserManageScopeModel data : li) {
				reportScope += data.getREPORT();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportScope;
	}

	public String getGraphScope(String userName) {
		String graphScope = "";
		try {

			List<UserManageScopeModel> li = getSession()
					.createQuery("from UserManageScopeModel where USERNAME = '" + userName + "'").list();
			for (UserManageScopeModel data : li) {
				graphScope += data.getGRAPH();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graphScope;
	}

	public String getUserScope(String userName) {
		String userScope = "";
		try {

			List<UserManageScopeModel> li = getSession()
					.createQuery("from UserManageScopeModel where USERNAME = '" + userName + "'").list();
			for (UserManageScopeModel data : li) {
				userScope += data.getUSER_SCOPE();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userScope;
	}

	public String saveuserloginhistory(String username, String serverHostname, String clientIp,
			String controller_response) {
		try {

//				String ipAdd = ip.nextToken();
			UserLoginHistory cb = new UserLoginHistory();
			cb.setActivity(controller_response);
			cb.setHostname(serverHostname);
			cb.setIpaddress(clientIp);
			cb.setTimestamp(Timestamp.from(Instant.now()));
			cb.setUsername(username);
			getSession().save(cb);

		} catch (Exception e) {
			System.out.println("Exception in saveuserloginhistory :" + e);

		}
		return null;
	}
}
