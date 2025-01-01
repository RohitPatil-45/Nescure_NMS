package npm.report.daoImpl;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import dynamicPassword.Encryption;
import npm.admin.beans.CpuThresholdHealthReportBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.beans.LatencyThresholdReportBean;
import npm.admin.beans.LinkAVGBandwidthReportBean;
import npm.admin.beans.LinkAvailabilityReportBean;
import npm.admin.beans.LinkBandwidthHistoryReporttBean;
import npm.admin.beans.LinkLatencyReportBean;
import npm.admin.beans.LinkLatencystatusreportBean;
import npm.admin.beans.MemoryThresholdReportBean;
import npm.admin.beans.NodeAvailabilityBean;
import npm.admin.beans.NodeHealthHistoryReportBean;
import npm.admin.beans.NodeStatusReportBean;
import npm.admin.beans.UserLoginHistoryBean;
import npm.configuration.AbstractDao;
import npm.model.AddNodeModel;
import npm.model.CUSTOME_TOPOLOGY_MAP;
import npm.model.DRIVE_THRESHOLD_LOG;
import npm.model.DeviceAlertLog;
import npm.model.Drive_Report;
import npm.model.GroupMasterModel;
import npm.model.Hardware_Inventory;
import npm.model.InterfaceAvailablity;
import npm.model.InterfaceBWHistory;
import npm.model.InterfaceMonitoring;
import npm.model.LatencyHisotryModel;
import npm.model.NodeAvailablity;
import npm.model.NodeHealthHistory;
import npm.model.NodeHealthMonitoring;
import npm.model.NodeMonitoringModel;
import npm.model.TOPOLOGY_NODE_POSTIONS;
import npm.model.Windows_Event_Log;
import npm.model.custome_topology_view;
import npm.report.dao.NodeReportDao;
import npm.admin.beans.LinkaverageLatencyReportBean;

@Repository
@Transactional
public class NodeReportDaoImpl extends AbstractDao<Integer, AddNodeModel> implements NodeReportDao {

	public List<NodeStatusReportBean> NodeStatusReport(String from_date, String to_date, List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.NODE_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join node_status_history report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		System.out.println("query :: " + query);
		Query q = getSession().createSQLQuery(query);
		List<NodeStatusReportBean> dataList = new ArrayList<NodeStatusReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			id++;
			NodeStatusReportBean bean = new NodeStatusReportBean();
			bean.setID(id);
			bean.setNODE_IP((a[1] == null) ? "-" : a[1].toString().equals("") ? "-" : a[1].toString());
			bean.setNODE_STATUS((a[2] == null) ? "-" : a[2].toString().equals("") ? "-" : a[2].toString());
			bean.setEVENT_TIMESTAMP((a[3] == null) ? "-" : a[3].toString().equals("") ? "-" : a[3].toString());
			bean.setDEVICE_NAME((a[4] == null) ? "-" : a[4].toString().equals("") ? "-" : a[4].toString());
			bean.setLOCATION((a[5] == null) ? "-" : a[5].toString().equals("") ? "-" : a[5].toString());
			bean.setDISTRICT((a[6] == null) ? "-" : a[6].toString().equals("") ? "-" : a[6].toString());
			bean.setSTATE((a[7] == null) ? "-" : a[7].toString().equals("") ? "-" : a[7].toString());
			bean.setZONE((a[8] == null) ? "-" : a[8].toString().equals("") ? "-" : a[8].toString());
			bean.setGROUP_NAME((a[9] == null) ? "-" : a[9].toString().equals("") ? "-" : a[9].toString());
			dataList.add(bean);
		}

		return dataList;
	}

	public List<LatencyHistoryReportBean> latencyHistoryReport(String from_date, String to_date, List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.MIN_LATENCY,report.MAX_LATENCY,report.AVG_LATENCY,report.PACKET_LOSS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join LATENCY_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
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

	public List<LatencyThresholdReportBean> latencyThresholdReport(String from_date, String to_date,
			List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.LATENCY_THRESHOLD,report.LATENCY_VAL,report.LATENCY_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join LATENCY_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<LatencyThresholdReportBean> dataList = new ArrayList<LatencyThresholdReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				LatencyThresholdReportBean bean = new LatencyThresholdReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setLATENCY_THRESHOLD(Integer.parseInt(a[2].toString()));
				bean.setLATENCY_VAL(Double.parseDouble(a[3].toString()));
				bean.setLATENCY_STATUS(a[4].toString());

				bean.setEVENT_TIMESTAMP(a[5].toString());
				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<NodeAvailabilityBean> nodeAvailabilityReport(String from_date, String to_date, List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.UPTIME_PERCENT,report.UPTIME_STR,report.DOWNTIME_PERCENT,report.DOWNTIME_STR,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join NODE_AVAILABILITY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<NodeAvailabilityBean> dataList = new ArrayList<NodeAvailabilityBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				String var_uptime = "NA";
//				 try {
//					 int up_second_day=(Integer) a[3];
//					 
//                     int day = (int) TimeUnit.SECONDS.toDays(up_second_day);
//                     long hours = TimeUnit.SECONDS.toHours(up_second_day) - (day * 24);
//                     long minute = TimeUnit.SECONDS.toMinutes(up_second_day) - (TimeUnit.SECONDS.toHours(up_second_day) * 60);
//                     long second = TimeUnit.SECONDS.toSeconds(up_second_day) - (TimeUnit.SECONDS.toMinutes(up_second_day) * 60);
//                     var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";
//
//                 } catch (Exception ex) {
//                     System.out.println("Exception Uptime cal:" + ex);
//                 }
				System.out.println(a[1].toString() + "var_uptime:" + var_uptime);

				String var_downtime = "NA";
//					 try {
//						 int up_second_day=(Integer) a[5];
//	                     int day = (int) TimeUnit.SECONDS.toDays(up_second_day);
//	                     long hours = TimeUnit.SECONDS.toHours(up_second_day) - (day * 24);
//	                     long minute = TimeUnit.SECONDS.toMinutes(up_second_day) - (TimeUnit.SECONDS.toHours(up_second_day) * 60);
//	                     long second = TimeUnit.SECONDS.toSeconds(up_second_day) - (TimeUnit.SECONDS.toMinutes(up_second_day) * 60);
//	                     var_downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";
//
//	                 } catch (Exception ex) {
//	                     System.out.println("excep 333 util:" + ex);
//	                 }

				id++;
				NodeAvailabilityBean bean = new NodeAvailabilityBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setUPTIME_PERCENT(Double.parseDouble(a[2].toString()));
				bean.setUPTIME(a[3].toString());
				bean.setDOWNTIME_PERCENT(Double.parseDouble(a[4].toString()));
				bean.setDOWNTIME(a[5].toString());

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

	public List<NodeHealthHistoryReportBean> nodeHealthHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.CPU_UTILIZATION,report.MEMORY_UTILIZATION,report.TEMPERATURE,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME, report.TOTAL_MEMORY, report.USED_MEMORY, report.FREE_MEMORY from add_node node join NODE_HEALTH_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<NodeHealthHistoryReportBean> dataList = new ArrayList<NodeHealthHistoryReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				NodeHealthHistoryReportBean bean = new NodeHealthHistoryReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setCPU_UTILIZATION(Double.parseDouble(a[2].toString()));
				bean.setMEMORY_UTILIZATION(Double.parseDouble(a[3].toString()));
				bean.setTOTAL_MEMORY(Double.parseDouble(a[12].toString()));
				bean.setUSED_MEMORY(Double.parseDouble(a[13].toString()));
				bean.setFREE_MEMORY(Double.parseDouble(a[14].toString()));

				bean.setTEMPERATURE(Double.parseDouble(a[4].toString()));

				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<CpuThresholdHealthReportBean> cpuThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.CPU_UTILIZATION,report.CPU_THRESHOLD,report.CPU_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join CPU_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<CpuThresholdHealthReportBean> dataList = new ArrayList<CpuThresholdHealthReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				CpuThresholdHealthReportBean bean = new CpuThresholdHealthReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setCPU_UTILIZATION(Double.parseDouble(a[2].toString()));
				bean.setCPU_THRESHOLD(Double.parseDouble(a[3].toString()));
				bean.setCPU_STATUS(a[4].toString());

				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<MemoryThresholdReportBean> memoryThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.MEMORY_UTILIZATION,report.MEMORY_THRESHOLD,report.MEMORY_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join MEMORY_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<MemoryThresholdReportBean> dataList = new ArrayList<MemoryThresholdReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				MemoryThresholdReportBean bean = new MemoryThresholdReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setMEMORY_UTILIZATION(Double.parseDouble(a[2].toString()));
				bean.setMEMORY_THRESHOLD(Double.parseDouble(a[3].toString()));
				bean.setMEMORY_STATUS(a[4].toString());

				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<NodeHealthMonitoring> nodeHealthMonitoringView() {

		Criteria criteria = getSession().createCriteria(NodeHealthMonitoring.class);
		return criteria.list();
	}

	public List<NodeMonitoringModel> nodeMonitoringView() {

		Criteria criteria = getSession().createCriteria(NodeMonitoringModel.class);
		return criteria.list();
	}

	public JSONArray interfaceMonitoringView(String userScopeData) {
		JSONArray finalArray = new JSONArray();
		JSONArray array = null;
		List<Object[]> q = getSession().createQuery(
				"SELECT ifm.NODE_IP, ifm.INTERFACE_NAME, ifm.INTERFACE_ID, ifm.INTERFACE_TYPE, ifm.ADMIN_STATUS, ifm.OPER_STATUS, "
						+ "ifm.PROCURED_BANDWIDTH, ifm.INTERFACE_MACADDRESS, ifm.INTERFACE_IP, ifm.ALIAS_NAME, ifm.CRC_ERROR, ifm.OUT_TRAFFIC, "
						+ "ifm.IN_TRAFFIC, ifm.DISCARD_INPUT, ifm.DISCARD_OUTPUT, ifm.INTERFACE_INPUT_ERROR, ifm.INTERFACE_OUTPUT_ERROR, ifm.INTERFACE_ERROR, "
						+ "ifm.MONITORING_PARAM, ifm.MAIL_ALERT, ifm.SMS_ALERT, ifm.AUTO_TICKETING, ifm.BW_THRESHOLD, ifm.BW_HISTORY_PARAM, ifm.CRC_HISTORY_PARAM "
						+ "FROM InterfaceMonitoring ifm, AddNodeModel add_node WHERE ifm.NODE_IP = add_node.DEVICE_IP AND "
						+ userScopeData)
				.list();

		for (Object[] objects : q) {
			array = new JSONArray();
			array.put(objects[0]);
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
			array.put(objects[13]);
			array.put(objects[14]);
			array.put(objects[15]);
			array.put(objects[16]);
			array.put(objects[17]);
			array.put(objects[18]);
			array.put(objects[19]);
			array.put(objects[20]);
			array.put(objects[21]);
			array.put(objects[22]);
			array.put(objects[23]);
			array.put(objects[24]);
			finalArray.put(array);
		}
		return finalArray;

//		Criteria criteria = getSession().createCriteria(InterfaceMonitoring.class);
//		return criteria.list();
	}

	public JSONArray getGroupDeviceDetails(String group_name, String userScopeData) {
		JSONArray array = null;
		JSONArray array1 = new JSONArray();
		try {
			System.out.println("Query:" + group_name);
			String queryText = "";
			Query q = null;
			if (group_name.equals("All")) {
				queryText = "from AddNodeModel add_node where " + userScopeData;
				q = getSession().createQuery(queryText);
			} else {
				queryText = "from AddNodeModel add_node where add_node.GROUP_NAME=:GROUP_NAME AND " + userScopeData;
				q = getSession().createQuery(queryText);
				q.setParameter("GROUP_NAME", group_name);
			}

			int srno = 0;
			List<AddNodeModel> dataList = q.list();
			for (AddNodeModel node : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put("<input type='checkbox' id=" + node.getDEVICE_IP()
						+ " name='ipAddressCheck' class='checkers' value='" + node.getDEVICE_IP() + "'/>");
				array.put(srno);
				array.put(node.getDEVICE_IP());
				array.put(node.getDEVICE_NAME());
				array.put(node.getGROUP_NAME());
				array.put(node.getLOCATION());
				array.put(node.getDISTRICT());
				array.put(node.getSTATE());
				array.put(node.getZONE());
				array1.put(array);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println(array1);
		return array1;
	}

	// Average latency Graph
	public JSONObject latencyHistoricalData(String from_date, String to_date, String ip_address) {

		System.out.println("latency history graph");
		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray latency_array = null;
		latency_array = new JSONArray();

		JSONArray packet_drop_array = null;
		packet_drop_array = new JSONArray();

		JSONArray latency_obj = null;
		JSONArray packet_drop_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from LatencyHisotryModel where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			int srno = 0;
			List<LatencyHisotryModel> dataList = q.list();
			for (LatencyHisotryModel data : dataList) {
				srno = srno + 1;

				double avg_latency = data.getAVG_LATENCY();
				double pkt_loss = data.getPACKET_LOSS();
				Timestamp time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				latency_obj = new JSONArray();
				latency_obj.put(datemili);
				latency_obj.put(avg_latency);
				latency_array.put(latency_obj);

				packet_drop_obj = new JSONArray();
				packet_drop_obj.put(datemili);
				packet_drop_obj.put(pkt_loss);
				packet_drop_array.put(packet_drop_obj);

				System.out.println(datemili + " - " + avg_latency + " - " + pkt_loss);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
//		System.out.println("latency Data:" + latency_array);
//		System.out.println("packet drop = " + packet_drop_array);
		jsonObj.put("latency", latency_array);
		jsonObj.put("packet_drop", packet_drop_array);
		return jsonObj;
	}
	// Average latency Graph

	// Latency graph
	public JSONObject plotLatencyGraph(String from_date, String to_date, String ip_address) {

		System.out.println("latency graph");
		JSONObject jsonFinalObj = new JSONObject();

		JSONArray latency_array = new JSONArray();
		JSONObject latency_obj = null;

		JSONArray packet_drop_array = new JSONArray();
		JSONObject packet_drop_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			SimpleDateFormat formatOfDateForChart = null;
			formatOfDateForChart = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
			Query q = getSession().createQuery(
					"from LatencyHisotryModel where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			int srno = 0;
			List<LatencyHisotryModel> dataList = q.list();
			for (LatencyHisotryModel data : dataList) {
				srno = srno + 1;

				double avg_latency = data.getAVG_LATENCY();
				double pkt_loss = data.getPACKET_LOSS();
				Timestamp time = data.getEVENT_TIMESTAMP();
				Date cDate = time;
				String s = cDate.toString();
				String[] arrOfStr = s.split(" ");
				String cTime = arrOfStr[1].substring(0, 8);
				Date newDate = null;
				newDate = formatOfDateForChart.parse(cDate + " " + cTime.replaceAll(" ", ""));
				System.out.println("formatedDate: " + newDate);
				long datemili = newDate.getTime();

				latency_obj = new JSONObject();
				latency_obj.put("x", datemili);
				latency_obj.put("y", avg_latency);
				latency_array.put(latency_obj);
				System.out.println("latency_obj=:" + latency_obj);

				packet_drop_obj = new JSONObject();
				packet_drop_obj.put("x", datemili);
				packet_drop_obj.put("y", pkt_loss);
				packet_drop_array.put(packet_drop_obj);
				System.out.println("Packet drop obj=:" + packet_drop_obj);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("size = " + latency_array.length());
		jsonFinalObj.put("latency", latency_array);
		jsonFinalObj.put("packet_drop", packet_drop_array);
		return jsonFinalObj;
	}
	// End Latency graph

	// Interface Bandwidth History Graph
//	public JSONObject interfaceBWHistoryData(String from_date, String to_date, String ip_address,
//			String interface_name) {
//
//		JSONObject jsonObj = null;
//		jsonObj = new JSONObject();
//
//		JSONArray inTraffic_array = null;
//		inTraffic_array = new JSONArray();
//
//		JSONArray outTraffic_array = null;
//		outTraffic_array = new JSONArray();
//
//		JSONArray inTraffic_obj = null;
//		JSONArray outTraffic_obj = null;
//
//		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
//		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);
//
//		try {
//			System.out.println("Query:" + ip_address);
//			Query q = getSession().createQuery(
//					"from InterfaceBWHistory where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
//			q.setParameter("NODE_IP", ip_address);
//			q.setParameter("fromDate", fromDateTimeStamp);
//			q.setParameter("toDate", toDateTimeStamp);
////			q.setParameter("INTERFACE_NAME", interface_name);
//
//			int srno = 0;
//			List<InterfaceBWHistory> dataList = q.list();
//
//			System.out.println("Data Size:" + dataList.size());
//			for (InterfaceBWHistory data : dataList) {
//				srno = srno + 1;
//
//				double in_traffic = data.getIN_TRAFFIC();
//				double out_traffic = data.getOUT_TRAFFIC();
//				Timestamp time = data.getEVENT_TIMESTAMP();
//				long datemili = time.getTime();
//
//				inTraffic_obj = new JSONArray();
//				inTraffic_obj.put(datemili);
//				inTraffic_obj.put(in_traffic);
//				inTraffic_array.put(inTraffic_obj);
//
//				outTraffic_obj = new JSONArray();
//				outTraffic_obj.put(datemili);
//				outTraffic_obj.put(out_traffic);
//				outTraffic_array.put(outTraffic_obj);
//
//			}
//		} catch (Exception e) {
//			System.out.println("Exception:" + e);
//		}
//		jsonObj.put("inTraffic", inTraffic_array);
//		jsonObj.put("outTraffic", outTraffic_array);
//		return jsonObj;
//	}
	public JSONObject interfaceBWHistoryData(String from_date, String to_date, String ip_address,
			String interface_name) {

		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray inTraffic_array = null;
		inTraffic_array = new JSONArray();

		JSONArray outTraffic_array = null;
		outTraffic_array = new JSONArray();

		JSONArray inTraffic_obj = null;
		JSONArray outTraffic_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		String ip_interface = ip_address + "~" + interface_name;

		try {

			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from InterfaceBWHistory where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
//			q.setParameter("INTERFACE_NAME", interface_name);

			int srno = 0;
			List<InterfaceBWHistory> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (InterfaceBWHistory data : dataList) {
				srno = srno + 1;

				double in_traffic = data.getIN_TRAFFIC();
				double out_traffic = data.getOUT_TRAFFIC();
				Timestamp time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				inTraffic_obj = new JSONArray();
				inTraffic_obj.put(datemili);
				inTraffic_obj.put(in_traffic);
				inTraffic_array.put(inTraffic_obj);

				outTraffic_obj = new JSONArray();
				outTraffic_obj.put(datemili);
				outTraffic_obj.put(out_traffic);
				outTraffic_array.put(outTraffic_obj);

			}

			// For Average, Max, Min - InTraffic and OutTraffic
			List<Object[]> list = getSession()
					.createSQLQuery("select max(IN_TRAFFIC), min(IN_TRAFFIC), avg(IN_TRAFFIC), \r\n"
							+ "max(OUT_TRAFFIC), min(OUT_TRAFFIC), avg(OUT_TRAFFIC)\r\n"
							+ "from interface_bw_history where IP_INTERFACE='" + ip_interface
							+ "'and EVENT_TIMESTAMP \r\n" + "BETWEEN '" + fromDateTimeStamp + "' and '"
							+ toDateTimeStamp + "'")
					.list();

			for (Object[] objects : list) {
				jsonObj.put("max_InTraffic", Math.round(Double.valueOf(objects[0].toString()) / 1024));
				jsonObj.put("min_InTraffic", Math.round(Double.valueOf(objects[1].toString()) / 1024));
				jsonObj.put("avg_InTraffic", Math.round(Double.valueOf(objects[2].toString()) / 1024));

				jsonObj.put("max_OutTraffic", Math.round(Double.valueOf(objects[3].toString()) / 1024));
				jsonObj.put("min_OutTraffic", Math.round(Double.valueOf(objects[4].toString()) / 1024));
				jsonObj.put("avg_OutTraffic", Math.round(Double.valueOf(objects[5].toString()) / 1024));
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		jsonObj.put("inTraffic", inTraffic_array);
		jsonObj.put("outTraffic", outTraffic_array);
		return jsonObj;
	}

	// Node Health History Graph

	public JSONObject nodeHealthHistoryData(String from_date, String to_date, String ip_address, String userScopeData) {

		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray cpu_utilization_array = null;
		cpu_utilization_array = new JSONArray();

		JSONArray memory_utilization_array = null;
		memory_utilization_array = new JSONArray();

		JSONArray memory_utilization_obj = null;
		JSONArray cpu_utilization_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from NodeHealthHistory where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			int srno = 0;
			List<NodeHealthHistory> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (NodeHealthHistory data : dataList) {
				srno = srno + 1;

				double cpu_utilization = data.getCPU_UTILIZATION();
				double memory_utilization = data.getMEMORY_UTILIZATION();
				Timestamp time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				cpu_utilization_obj = new JSONArray();
				cpu_utilization_obj.put(datemili);
				cpu_utilization_obj.put(cpu_utilization);
				cpu_utilization_array.put(cpu_utilization_obj);

				memory_utilization_obj = new JSONArray();
				memory_utilization_obj.put(datemili);
				memory_utilization_obj.put(memory_utilization);
				memory_utilization_array.put(memory_utilization_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("CPU Utilization Data:" + cpu_utilization_array);
		jsonObj.put("cpuUtilization", cpu_utilization_array);
		jsonObj.put("memoryUtilization", memory_utilization_array);
		return jsonObj;
	}

	// Node Availability Graph

	public JSONObject nodeAvailabilityData(String from_date, String to_date, String ip_address) {

		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray downtime_percent_array = null;
		downtime_percent_array = new JSONArray();

		JSONArray uptime_percent_array = null;
		uptime_percent_array = new JSONArray();

		JSONArray downtime_percent_obj = null;
		JSONArray uptime_percent_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from NodeAvailablity where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			int srno = 0;
			List<NodeAvailablity> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (NodeAvailablity data : dataList) {
				srno = srno + 1;

				double downtime_percent = data.getDOWNTIME_PERCENT();
				double uptime_percent = data.getUPTIME_PERCENT();
				Date time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				downtime_percent_obj = new JSONArray();
				downtime_percent_obj.put(datemili);
				downtime_percent_obj.put(downtime_percent);
				downtime_percent_array.put(downtime_percent_obj);

				uptime_percent_obj = new JSONArray();
				uptime_percent_obj.put(datemili);
				uptime_percent_obj.put(uptime_percent);
				uptime_percent_array.put(uptime_percent_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("Downtime Percent:" + downtime_percent_array);
		jsonObj.put("downtimePercent", downtime_percent_array);
		jsonObj.put("uptimePercent", uptime_percent_array);
		return jsonObj;
	}

	// Interface Availability Graph

	public JSONObject interfaceAvailabilityData(String from_date, String to_date, String ip_address,
			String interface_name) {

		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray downtime_percent_array = null;
		downtime_percent_array = new JSONArray();

		JSONArray uptime_percent_array = null;
		uptime_percent_array = new JSONArray();

		JSONArray downtime_percent_obj = null;
		JSONArray uptime_percent_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from InterfaceAvailablity where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			// q.setParameter("INTERFACE_NAME", interface_name);
			int srno = 0;
			List<InterfaceAvailablity> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (InterfaceAvailablity data : dataList) {
				srno = srno + 1;

				double downtime_percent = data.getDOWNTIME_PERCENT();
				double uptime_percent = data.getUPTIME_PERCENT();
				Date time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				downtime_percent_obj = new JSONArray();
				downtime_percent_obj.put(datemili);
				downtime_percent_obj.put(downtime_percent);
				downtime_percent_array.put(downtime_percent_obj);

				uptime_percent_obj = new JSONArray();
				uptime_percent_obj.put(datemili);
				uptime_percent_obj.put(uptime_percent);
				uptime_percent_array.put(uptime_percent_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("Downtime Percent:" + downtime_percent_array);
		jsonObj.put("downtimePercent", downtime_percent_array);
		jsonObj.put("uptimePercent", uptime_percent_array);
		return jsonObj;
	}

	public String getIPAddress() {

		String output = "<option value=''>Please Select</option>";
//		String output = "";
		try {

			Query queryBranch = getSession().createQuery("select distinct DEVICE_IP from AddNodeModel");
			List<String> branches = queryBranch.list();

			for (String branch : branches) {

				int i = branch.indexOf(" ", 0);

				output += "<option>" + branch + "</option>";

			}
			System.out.println("Branches: " + output);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return output;
	}

	public String getInterfaceOnIPAddress(String ipAddress) {

		String output = "<option value=''>Please Select</option>";
		try {
			Query queryBranch = getSession().createQuery("select distinct INTERFACE_IP from InterfaceMonitoring");
			List<String> branches = queryBranch.list();

			for (String branch : branches) {

				int i = branch.indexOf(" ", 0);

				output += "<option>" + branch + "</option>";

			}
			System.out.println("Branches: " + output);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return output;
	}

	public String SaveNodePositions(String fromTo, String positions, String ipAddress, String username) {

		String result = "";
		Query q = getSession().createQuery("from TOPOLOGY_NODE_POSTIONS where VIEW_NAME='" + username + "'");
//	Query query1 = getSession().createQuery("from COMPANY where COMPANY_NAME='" + companyName + "'");
		List<TOPOLOGY_NODE_POSTIONS> dataList = q.list();
		if (dataList.size() > 0) {
//			result = "nameExist";
			Query q1 = getSession()
					.createQuery("delete from TOPOLOGY_NODE_POSTIONS where VIEW_NAME='" + username + "'");
			int i = q1.executeUpdate();
		} else {
			try {

				JSONParser parser = new JSONParser();
				JSONArray json = (JSONArray) parser.parse(positions);
				JSONParser parser2 = new JSONParser();
				for (int e = 0; e < ((List) json).size(); e++) {

//	                System.out.println("values in jason " + json.get(e));
					JSONObject jo = (JSONObject) parser2.parse(json.get(e).toString());

					TOPOLOGY_NODE_POSTIONS topology = new TOPOLOGY_NODE_POSTIONS();
					topology.setNODE_ID(jo.get("id").toString());
					topology.setX_POS(jo.get("x").toString());
					topology.setY_POS(jo.get("y").toString());
					topology.setVIEW_NAME(jo.get("username").toString());

					getSession().save(topology);
					result = "success";
				}
			} catch (Exception e) {
				result = "error";
				e.printStackTrace();
			}
		}
		return result;
	}

	public String NetworkTopologyEditMode(String grpName) {
		String result = "";
		List<String> offRouter_list = new ArrayList<String>();
		List<String> offRouterSrList = new ArrayList<String>();
		List<String> offNodeSrList = new ArrayList<String>();
		try {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpSession session = requestAttributes.getRequest().getSession();
			String username = session.getAttribute("username").toString();

			Query q = getSession().createQuery("from InterfaceMonitoring where OPER_STATUS='down'");
//			Query query1 = getSession().createQuery("from COMPANY where COMPANY_NAME='" + companyName + "'");
			List<InterfaceMonitoring> dataList = q.list();

			for (InterfaceMonitoring node : dataList) {
				offRouter_list.add(node.getINTERFACE_IP());
				offRouterSrList.add(node.getINTERFACE_NAME());
			}

			for (String downSrNO : offRouter_list) {
				String ipAddressOfDown = downSrNO.substring(0, downSrNO.indexOf("~"));
				String interfaceOfDown = downSrNO.substring(downSrNO.indexOf("~") + 1);
//				String q2 = "from  where node_name='" + ipAddressOfDown + "'";

				Query q2 = getSession()
						.createQuery("from custome_topology_view where node_name='" + ipAddressOfDown + "'");

				List<custome_topology_view> dataList2 = q2.list();
//				for (InterfaceMonitoring node : dataList) {
//					offNodeSrList.add(node.getID());
//					
//				}

//	                    rs3 = stmt3.executeQuery(q2);

//	                    while (rs3.next()) {
//	                        offNodeSrList.add(rs3.getString(1));

//	                    }
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public String NetworkTopologyInsertInterface(String fromIpAddress, String toIpAddress, String fromInterface,
			String toInterface) {

		String result = "";
		// TODO Auto-generated method stub
		int fromIPID = 0;
		int toIPID = 0;
		try {
			Query q = getSession().createQuery("from custome_topology_view where node_name='" + fromIpAddress + "'");
			List<custome_topology_view> dataList = q.list();

			for (custome_topology_view node : dataList) {
				fromIPID = node.getId();
			}
			Query q1 = getSession()
					.createQuery("from custome_topology_view)" + " where node_name='" + toIpAddress + "'");
			List<custome_topology_view> dataList1 = q1.list();

			for (custome_topology_view node : dataList1) {
				toIPID = node.getId();
			}

			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpSession session = requestAttributes.getRequest().getSession();
//			String username = session.getAttribute("username").toString();
//			String grpName = session.getAttribute("groupname").toString();

			CUSTOME_TOPOLOGY_MAP map = new CUSTOME_TOPOLOGY_MAP();
//			map.setVIEW_NAME(username);
			map.setVIEW_NAME("User");
//			map.setId(id);
			map.setID_1(fromIpAddress + "~" + fromInterface);
			map.setLINK_FROM(String.valueOf(fromIPID));
			map.setLINK_TO(String.valueOf(toIPID));
			map.setINTERFACE_FROM(fromInterface);
			map.setINTERFACE_TO(toInterface);
			map.setIPADDRESS_FROM(fromIpAddress);
			map.setIPADDRESS_TO(toIpAddress);
//			map.setGROUP_NAME(grpName);
			map.setGROUP_NAME("grp");
			getSession().save(map);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}

	public List<DeviceAlertLog> viewDeviceAlertLog() {

		List<DeviceAlertLog> list = getSession().createQuery("from DeviceAlertLog order by ID desc").list();
		return list;

	}

	public JSONArray getSlaReport(String from_date, String to_date, String yearlyCost) {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int penalty_cost_percentage = 0;
		double penalty_cost = 0;
		int sr = 0;
		String daysData = "";
		String fromTime = "";
		String toTime = "";
		String numericDaysData = "";
		String betweenData = "";

		try {
			List<Object[]> resultList = getSession()
					.createSQLQuery(
							"SELECT ID, days_data, from_time, to_time, numeric_days_data, between_data FROM sla_master")
					.list();

			// Loop through the result list and store the values in variables
			for (Object[] data : resultList) {
//		            Long id = (Long) data[0];
				daysData = (String) data[1];
				fromTime = (String) data[2];
				toTime = (String) data[3];
				numericDaysData = (String) data[4];
				betweenData = (String) data[5];

				// Now, you can use these variables as needed
//		            System.out.println("ID: " + id);
				System.out.println("Days Data: " + daysData);
				System.out.println("From Time: " + fromTime);
				System.out.println("To Time: " + toTime);
				System.out.println("Numeric Days Data: " + numericDaysData);
				System.out.println("Between Data: " + betweenData);
			}

			// Close the session
//			getSession().close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(yearlyCost);
		System.out.println(from_date);
		System.out.println(to_date);

//		System.out.println(daysData);
		System.out.println(fromTime);
		System.out.println(toTime);

		System.out.println(numericDaysData);
		System.out.println(betweenData);

//		String myquerry = "SELECT \r\n" + "    NODE_IP,\r\n" + "   avg(PACKET_LOSS)\r\n" + "\r\n" + "FROM \r\n"
//				+ "    latency_history\r\n" + "WHERE \r\n" + "    TIME(EVENT_TIMESTAMP) " + betweenData.trim()
//				+ " -- Filter to data between 1 AM and 11 PM only\r\n" + "    AND WEEKDAY(EVENT_TIMESTAMP) "
//				+ numericDaysData.trim() + " -- Include only weekdays (Monday to Friday)\r\n"
//				+ "    AND EVENT_TIMESTAMP BETWEEN '" + from_date.trim() + "' AND '" + to_date.toString() + "'\r\n"
//				+ "GROUP BY \r\n" + "    NODE_IP";

//		String myquerry = "SELECT \r\n" + "    NODE_IP,\r\n" + "    DATE(EVENT_TIMESTAMP) AS event_date,\r\n"
//				+ "    \r\n" + "    -- Calculate total down time (in seconds) when PACKET_LOSS > 0\r\n" + "    SUM(\r\n"
//				+ "        CASE \r\n" + "            WHEN PACKET_LOSS > 0 THEN \r\n"
//				+ "                TIMESTAMPDIFF(SECOND, \r\n"
//				+ "                    GREATEST(EVENT_TIMESTAMP, TIMESTAMP(DATE(EVENT_TIMESTAMP), '" + fromTime.trim()
//				+ "')),\r\n"
//				+ "                    LEAST(TIMESTAMPADD(SECOND, 1, EVENT_TIMESTAMP), TIMESTAMP(DATE(EVENT_TIMESTAMP), '"
//				+ toTime.trim() + "'))\r\n" + "                )\r\n" + "            ELSE 0 \r\n" + "        END\r\n"
//				+ "    ) AS down_time_seconds,\r\n" + "    \r\n"
//				+ "    -- Calculate total up time (in seconds) when PACKET_LOSS = 0\r\n" + "    SUM(\r\n"
//				+ "        CASE \r\n" + "            WHEN PACKET_LOSS = 0 THEN \r\n"
//				+ "                TIMESTAMPDIFF(SECOND, \r\n"
//				+ "                    GREATEST(EVENT_TIMESTAMP, TIMESTAMP(DATE(EVENT_TIMESTAMP), '" + fromTime.trim()
//				+ "')),\r\n"
//				+ "                    LEAST(TIMESTAMPADD(SECOND, 1, EVENT_TIMESTAMP), TIMESTAMP(DATE(EVENT_TIMESTAMP), '"
//				+ toTime.trim() + "'))\r\n" + "                )\r\n" + "            ELSE 0 \r\n" + "        END\r\n"
//				+ "    ) AS up_time_seconds,\r\n" + "\r\n" + "    -- Calculate total seconds in the monitored period ("
//				+ fromTime.trim() + " to " + toTime.trim() + ")\r\n" + "    TIMESTAMPDIFF(\r\n" + "        SECOND, \r\n"
//				+ "        TIMESTAMP(DATE(EVENT_TIMESTAMP), '" + fromTime.trim() + "'), \r\n"
//				+ "        TIMESTAMP(DATE(EVENT_TIMESTAMP), '" + toTime.trim() + "')\r\n"
//				+ "    ) AS total_period_seconds,\r\n" + "\r\n" + "    -- Calculate uptime percentage\r\n"
//				+ "    (SUM(\r\n" + "        CASE \r\n" + "            WHEN PACKET_LOSS = 0 THEN \r\n"
//				+ "                TIMESTAMPDIFF(SECOND, \r\n"
//				+ "                    GREATEST(EVENT_TIMESTAMP, TIMESTAMP(DATE(EVENT_TIMESTAMP), '" + fromTime.trim()
//				+ "')),\r\n"
//				+ "                    LEAST(TIMESTAMPADD(SECOND, 1, EVENT_TIMESTAMP), TIMESTAMP(DATE(EVENT_TIMESTAMP), '"
//				+ toTime.trim() + "'))\r\n" + "                )\r\n" + "            ELSE 0 \r\n" + "        END\r\n"
//				+ "    ) / TIMESTAMPDIFF(\r\n" + "        SECOND, \r\n" + "        TIMESTAMP(DATE(EVENT_TIMESTAMP), '"
//				+ fromTime.trim() + "'), \r\n" + "        TIMESTAMP(DATE(EVENT_TIMESTAMP), '" + toTime.trim() + "')\r\n"
//				+ "    ) * 100) AS uptime_percent,\r\n" + "\r\n" + "    -- Calculate downtime percentage\r\n"
//				+ "    (SUM(\r\n" + "        CASE \r\n" + "            WHEN PACKET_LOSS > 0 THEN \r\n"
//				+ "                TIMESTAMPDIFF(SECOND, \r\n"
//				+ "                    GREATEST(EVENT_TIMESTAMP, TIMESTAMP(DATE(EVENT_TIMESTAMP), '" + fromTime.trim()
//				+ "')),\r\n"
//				+ "                    LEAST(TIMESTAMPADD(SECOND, 1, EVENT_TIMESTAMP), TIMESTAMP(DATE(EVENT_TIMESTAMP), '"
//				+ toTime.trim() + "'))\r\n" + "                )\r\n" + "            ELSE 0 \r\n" + "        END\r\n"
//				+ "    ) / TIMESTAMPDIFF(\r\n" + "        SECOND, \r\n" + "        TIMESTAMP(DATE(EVENT_TIMESTAMP), '"
//				+ fromTime.trim() + "'), \r\n" + "        TIMESTAMP(DATE(EVENT_TIMESTAMP), '" + toTime.trim() + "')\r\n"
//				+ "    ) * 100) AS downtime_percent\r\n" + "\r\n" + "FROM \r\n" + "    latency_history\r\n"
//				+ "WHERE \r\n" + "    TIME(EVENT_TIMESTAMP) BETWEEN '" + fromTime.trim() + "' AND '" + toTime.trim()
//				+ "' -- Filter to data between 1 AM and 11 PM only\r\n" + "    AND WEEKDAY(EVENT_TIMESTAMP) "
//				+ numericDaysData.trim() + " -- Include only weekdays (Monday to Friday)\r\n"
//				+ "    AND EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'\r\n" + "GROUP BY \r\n"
//				+ "    NODE_IP, \r\n" + "    DATE(EVENT_TIMESTAMP) -- Group by IP and day\r\n" + "HAVING \r\n"
//				+ "    down_time_seconds > 0 OR up_time_seconds > 0\r\n" + "ORDER BY \r\n"
//				+ "    event_date, NODE_IP;\r\n" + "";

		try {
			List<Object[]> li = getSession().createSQLQuery(
					"select NODE_IP, avg(UPTIME_PERCENT), avg(DOWNTIME_PERCENT) from node_availability group by NODE_IP")
					.list();
			for (Object[] data : li) {
				array = new JSONArray();
				sr++;
				array.put(sr);
				array.put(data[0]);
				array.put(data[1]);
				array.put(data[2]);
				if (Double.valueOf(data[1].toString()) >= 99) {
					penalty_cost_percentage = 0;
				} else if (Double.valueOf(data[1].toString()) >= 98.5 && Double.valueOf(data[1].toString()) < 99) {
					penalty_cost_percentage = 10;
				} else if (Double.valueOf(data[1].toString()) >= 97.5 && Double.valueOf(data[1].toString()) < 98.5) {
					penalty_cost_percentage = 20;
				} else if (Double.valueOf(data[1].toString()) >= 96.5 && Double.valueOf(data[1].toString()) < 97.5) {
					penalty_cost_percentage = 25;
				} else if (Double.valueOf(data[1].toString()) < 96.5) {
					penalty_cost_percentage = 30;
				} else if (Double.valueOf(data[1].toString()) < 96.5) {
					penalty_cost_percentage = -1;
				}

				penalty_cost = (Double.valueOf(yearlyCost) * penalty_cost_percentage) / 100;
				array.put(Double.valueOf(yearlyCost));
				array.put(penalty_cost_percentage);
				array.put(penalty_cost);
				array.put(Double.valueOf(yearlyCost) - penalty_cost);

				arrayList.put(array);
			}

//			System.out.println(myquerry);
//			List<Object[]> li = getSession().createSQLQuery(myquerry).list();
//			for (Object[] data : li) {
//				array = new JSONArray();
//				sr++;
//
//				String uptimepercent = data[1].toString();
//				double uptime = Double.parseDouble(uptimepercent.trim());
//				double downtime = 100 - uptime;
//				String downtimepercent = String.valueOf(downtime);
//
//				array.put(sr);
//				array.put(data[0]);
//				array.put(uptimepercent);
//				array.put(downtimepercent);
//
////				int totalupSeconds = Integer.parseInt(data[3].toString().trim());
////				// Input in seconds
////
////				int upphours = totalupSeconds / 3600;
////				int uppminutes = (totalupSeconds % 3600) / 60;
////				int uppseconds = totalupSeconds % 60;
//
////		        System.out.printf("Hours: %d, Minutes: %d, Seconds: %d%n", hours, minutes, seconds);
//
//				// Storing in variables
////				String timeUPFormatted = String.format("%02d:%02d:%02d", upphours, uppminutes, uppseconds);
//
////				System.out.println("Formatted Time: " + timeUPFormatted);
//
////				int totaldownnSeconds = Integer.parseInt(data[2].toString().trim());
////				// Input in seconds
////
////				int downnhours = totaldownnSeconds / 3600;
////				int downnminutes = (totaldownnSeconds % 3600) / 60;
////				int downnseconds = totaldownnSeconds % 60;
//
//				// Storing in variables
////				String timedownnFormatted = String.format("%02d:%02d:%02d", downnhours, downnminutes, downnseconds);
////				System.out.println(timeUPFormatted);
////				System.out.println(timedownnFormatted);
//
////				array.put("Hours: " + upphours + ", Minutes: " + uppminutes + ", Seconds: " + uppseconds + "");
////				array.put("Hours: " + downnhours + ", Minutes: " + downnminutes + ", Seconds: " + downnseconds + "");
//
//				if (Double.valueOf(uptimepercent.trim()) >= 99) {
//					penalty_cost_percentage = 0;
//				} else if (Double.valueOf(uptimepercent.trim()) >= 98.5 && Double.valueOf(uptimepercent.trim()) < 99) {
//					penalty_cost_percentage = 10;
//				} else if (Double.valueOf(uptimepercent.trim()) >= 97.5
//						&& Double.valueOf(uptimepercent.trim()) < 98.5) {
//					penalty_cost_percentage = 20;
//				} else if (Double.valueOf(uptimepercent.trim()) >= 96.5
//						&& Double.valueOf(uptimepercent.trim()) < 97.5) {
//					penalty_cost_percentage = 25;
//				} else if (Double.valueOf(uptimepercent.trim()) < 96.5) {
//					penalty_cost_percentage = 30;
//				}
//
////				else if (Double.valueOf(data[1].toString()) < 96.5) {
////					penalty_cost_percentage = -1;
////				}
//
//				penalty_cost = (Double.valueOf(yearlyCost) * penalty_cost_percentage) / 100;
//				array.put(Double.valueOf(yearlyCost));
//				array.put(penalty_cost_percentage);
//				array.put(penalty_cost);
//				array.put(Double.valueOf(yearlyCost) - penalty_cost);
//
//				arrayList.put(array);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList);
		return arrayList;
	}

	// Avg Uptime Report

	public JSONArray getAvgUptimeReport(String from_date, String to_date) {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		try {
			List<Object[]> li = getSession().createSQLQuery(
					"select NODE_IP, avg(UPTIME_PERCENT), avg(DOWNTIME_PERCENT) from node_availability where EVENT_TIMESTAMP between '"
							+ from_date + "' and '" + to_date + "' group by NODE_IP")
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
		return arrayList;
	}

	public JSONArray getConnectedDevices() {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		try {
			List<Object[]> li = getSession().createSQLQuery(
					"select DEVICE_IP, DEV_CONNECTED_IP, DEV_CONNECTED_MAC_ID, DEV_CONNECTED_INTERFACE_NAME, DEV_CONNECTED_CON_TYPE from node_connect_pc")
					.list();
			for (Object[] data : li) {
				sr++;
				array = new JSONArray();
				array.put(sr);
				array.put((data[0] == null) ? "NA" : data[0].equals("") ? "NA" : data[0]);
				array.put((data[1] == null) ? "NA" : data[1].equals("") ? "NA" : data[1]);
				array.put((data[2] == null) ? "NA" : data[2].equals("") ? "NA" : data[2]);
				array.put((data[3] == null) ? "NA" : data[3].equals("") ? "NA" : data[3]);
				array.put((data[4] == null) ? "NA" : data[4].equals("") ? "NA" : data[4]);
				arrayList.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public JSONArray connectedDevicesforIP(String ipAddress) {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		try {
			List<Object[]> li = getSession().createSQLQuery(
					"select DEV_CONNECTED_IP, DEV_CONNECTED_MAC_ID, DEV_CONNECTED_INTERFACE_NAME, DEV_CONNECTED_CON_TYPE from node_connect_pc where DEVICE_IP = '"
							+ ipAddress + "'")
					.list();
			for (Object[] data : li) {
				sr++;
				array = new JSONArray();
				array.put(sr);
				array.put((data[0] == null) ? "NA" : data[0].equals("") ? "NA" : data[0]);
				array.put((data[1] == null) ? "NA" : data[1].equals("") ? "NA" : data[1]);
				array.put((data[2] == null) ? "NA" : data[2].equals("") ? "NA" : data[2]);
				array.put((data[3] == null) ? "NA" : data[3].equals("") ? "NA" : data[3]);
				arrayList.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public JSONArray getAverageAvailabilityReport(String from_date, String to_date) {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		try {
			List<Object[]> li = getSession().createSQLQuery("SELECT NODE_IP,AVG(PACKET_LOSS)  \r\n"
					+ "FROM latency_history WHERE HOUR(EVENT_TIMESTAMP) >= 9 AND HOUR(EVENT_TIMESTAMP) <= 21\r\n"
					+ "and EVENT_TIMESTAMP between DATE('" + from_date + "') and DATE('" + to_date + "')\r\n"
					+ "GROUP BY NODE_IP").list();
			for (Object[] data : li) {
				sr++;
				array = new JSONArray();
				array.put(sr);
				array.put(data[0]);
				array.put(100 - Double.valueOf(data[1].toString()));
				array.put(data[1]);
				arrayList.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Working Hours Data = " + arrayList);
		return arrayList;
	}

	public JSONArray getAvailabilityReportDateWise(String from_date, String to_date) {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		try {
			List<Object[]> li = getSession()
					.createSQLQuery("SELECT  DATE(EVENT_TIMESTAMP),NODE_IP,AVG(PACKET_LOSS)  FROM latency_history \r\n"
							+ "WHERE HOUR(EVENT_TIMESTAMP) >= 9 AND HOUR(EVENT_TIMESTAMP) <= 21 and\r\n"
							+ "EVENT_TIMESTAMP between DATE('" + from_date + "') and DATE('" + to_date + "')\r\n"
							+ "GROUP BY NODE_IP, DATE(EVENT_TIMESTAMP)")
					.list();
			for (Object[] data : li) {
				sr++;
				array = new JSONArray();
				array.put(sr);
				array.put(data[0]);
				array.put(data[1]);
				array.put(100 - Double.valueOf(data[2].toString()));
				array.put(data[2]);
				arrayList.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Working Hours Data Date wise = " + arrayList);
		return arrayList;
	}

	public JSONArray getGroupDeviceLists(String group_name, String userScopeData) {
		JSONArray array = null;
		JSONArray array1 = new JSONArray();
		String options = "";
		try {
			System.out.println("Query:" + group_name);

			Query q = null;
			if (group_name.equalsIgnoreCase("all")) {
//				System.out.println("from AddNodeModel add_node where " + userScopeData);
				q = getSession().createQuery("from AddNodeModel add_node where " + userScopeData);
			} else {
				q = getSession().createQuery(
						"from AddNodeModel add_node where add_node.GROUP_NAME=:GROUP_NAME AND " + userScopeData);
				q.setParameter("GROUP_NAME", group_name);
			}
//			q.setParameter("GROUP_NAME", group_name);
			List<AddNodeModel> dataList = q.list();
//			array1.put("<option value=''>Please Select</option>");
			for (AddNodeModel node : dataList) {
				array = new JSONArray();
				array.put(node.getDEVICE_IP());
				array1.put(array);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return array1;
	}

	public JSONArray getInterFaceNameLists(String ip, String userScopeData) {
		JSONArray array = null;
		JSONArray array1 = new JSONArray();
		try {
			Query q = getSession()
					.createQuery("SELECT distinct INTERFACE_NAME from InterfaceMonitoring where NODE_IP=:ip");
			q.setParameter("ip", ip);
			List<String> dataList = q.list();
			for (String node : dataList) {
				array = new JSONArray();
				array.put(node);
				array1.put(array);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("array1=" + array1.length());
		return array1;
	}

	public JSONArray avarageLatencyReport(String from_date, String to_date, List<String> ip_list,
			String userScopeData) {
		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("Average IP List:" + ip_data + ":" + from_date + to_date);
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		try {
			List<Object[]> li = getSession().createSQLQuery(
					"select lh.NODE_IP, ROUND(avg(lh.AVG_LATENCY),2) from latency_history lh join add_node node on node.DEVICE_IP=lh.NODE_IP where lh.NODE_IP in ('"
							+ ip_data + "') and  lh.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date
							+ "' group by lh.NODE_IP")
					.list();
			for (Object[] data : li) {
				sr++;
				array = new JSONArray();
				array.put(sr);
				array.put(data[0]);
				array.put(data[1]);
				arrayList.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("arrayList =" + arrayList.length());
		return arrayList;
	}

//	
//	public List<LatencyHistoryReportBean> averageHealthReport(String from_date, String to_date, List<String> ip_list,
//			String userScopeData) {
//		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
//		System.out.println("Average IP List:" + ip_data + ":" + from_date + to_date);
//		String query = "SELECT report.NODE_IP, report.MIN_LATENCY, report.MAX_LATENCY,AVG(report.AVG_LATENCY) AS AVG_LATENCY,report.PACKET_LOSS,report.EVENT_TIMESTAMP,add_node.DEVICE_NAME,add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE,add_node.GROUP_NAME\r\n"
//				+ "FROM add_node JOIN LATENCY_HISTORY report WHERE add_node.DEVICE_IP = report.NODE_IP AND add_node.DEVICE_IP IN ('"+ ip_data +"') AND report.EVENT_TIMESTAMP BETWEEN '"+ from_date +"' AND '"+ to_date +"' "
//				+ "GROUP BY report.NODE_IP,report.MIN_LATENCY,report.MAX_LATENCY, report.PACKET_LOSS,report.EVENT_TIMESTAMP,add_node.DEVICE_NAME,add_node.LOCATION,add_node.DISTRICT,add_node.STATE,add_node.ZONE, add_node.GROUP_NAME;\r\n";
//		Query q = getSession().createSQLQuery(query);
//		List<LatencyHistoryReportBean> dataList = new ArrayList<LatencyHistoryReportBean>();
//
//		List<Object[]> data = q.list();
//		System.out.println("Size Data:" + data.size());
//		long id = 0;
//		for (Object[] a : data) {
//			try {
//
//				id++;
//				LatencyHistoryReportBean bean = new LatencyHistoryReportBean();
//				bean.setID(id);
//				bean.setNODE_IP(a[0].toString());
//				bean.setMIN_LATENCY(Double.parseDouble(a[1].toString()));
//				bean.setMAX_LATENCY(Double.parseDouble(a[2].toString()));
//				bean.setAVG_LATENCY(Double.parseDouble(a[3].toString()));
//				bean.setPACKET_LOSS(Double.parseDouble(a[4].toString()));
//				bean.setEVENT_TIMESTAMP(a[5].toString());
//				bean.setDEVICE_NAME(a[6].toString());
//				bean.setLOCATION(a[7].toString());
//				bean.setDISTRICT(a[8].toString());
//				bean.setSTATE(a[9].toString());
//				bean.setZONE(a[10].toString());
//				bean.setGROUP_NAME(a[11].toString());
//				dataList.add(bean);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//		return dataList;
//	}

	public JSONArray averageHealthReport(String from_date, String to_date, List<String> ip_list, String userScopeData) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("Average IP List:" + ip_data + ":" + from_date + to_date);
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		try {
			List<Object[]> li = getSession().createSQLQuery(
					"select nhm.NODE_IP, ROUND(avg(nhm.CPU_UTILIZATION),2),ROUND(avg(nhm.MEMORY_UTILIZATION),2) from node_health_history nhm join add_node node on node.DEVICE_IP=nhm.NODE_IP where nhm.NODE_IP in ('"
							+ ip_data + "') and  nhm.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date
							+ "' group by nhm.NODE_IP")
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

	public JSONArray nodeAvailabilityAverageGraph(String from_date, String to_date, List<String> ip_address) {
		String ip_data = ip_address.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		JSONArray arrayList = new JSONArray();

		try {
			List<Object[]> li = getSession().createSQLQuery(
					"SELECT NODE_IP, ROUND(avg(UPTIME_PERCENT),2), ROUND(avg(DOWNTIME_PERCENT),2) FROM node_availability "
							+ "WHERE NODE_IP IN ('" + ip_data
							+ "') AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate group by NODE_IP")
					.setParameter("fromDate", from_date).setParameter("toDate", to_date).list();
			for (Object[] data : li) {
				JSONArray array = new JSONArray();
				array.put(data[0]);
				array.put(data[1]);
				array.put(data[2]);
				arrayList.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// Log or handle the exception as needed
		}

		System.out.println("arrayList =" + arrayList.length());
		return arrayList;
	}

	public JSONArray interfaceAvailabilityAverageGraph(String from_date, String to_date, List<String> ip_address) {
		String ip_data = ip_address.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		JSONArray arrayList = new JSONArray();

		try {
			List<Object[]> li = getSession().createSQLQuery(
					"SELECT INTERFACE_NAME, ROUND(avg(UPTIME_PERCENT),2), ROUND(avg(DOWNTIME_PERCENT),2) FROM interface_availability "
							+ "WHERE NODE_IP IN ('" + ip_data
							+ "') AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate group by INTERFACE_NAME")
					.setParameter("fromDate", from_date).setParameter("toDate", to_date).list();
			System.out.println("list data= " + li);
			for (Object[] data : li) {
				JSONArray array = new JSONArray();
				array.put(data[0]);
				array.put(data[1]);
				array.put(data[2]);
				arrayList.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// Log or handle the exception as needed
		}

		System.out.println("arrayList =" + arrayList.length());
		return arrayList;
	}

	public List<LatencyHistoryReportBean> showLatencyReport(String ip_address, String fromDate, String toDate) {
		String query = "select report.ID,report.NODE_IP,report.MIN_LATENCY,report.MAX_LATENCY,report.AVG_LATENCY,report.PACKET_LOSS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join LATENCY_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP='"
				+ ip_address + "'  and  report.EVENT_TIMESTAMP BETWEEN '" + fromDate + "' AND '" + toDate + "'";
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

	public List<NodeStatusReportBean> showNodeStatusReport(String ip_address, String fromDate, String toDate) {
		String query = "select report.ID,report.NODE_IP,report.NODE_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join node_status_history report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP='"
				+ ip_address + "' and  report.EVENT_TIMESTAMP BETWEEN '" + fromDate + "' AND '" + toDate + "'";
		System.out.println("query :: " + query);
		Query q = getSession().createSQLQuery(query);
		List<NodeStatusReportBean> dataList = new ArrayList<NodeStatusReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			id++;
			NodeStatusReportBean bean = new NodeStatusReportBean();
			bean.setID(id);
			bean.setNODE_IP(a[1].toString());
			bean.setNODE_STATUS(a[2].toString());
			bean.setEVENT_TIMESTAMP(a[3].toString());
			bean.setDEVICE_NAME(a[4].toString());
			bean.setLOCATION(a[5].toString());
			bean.setDISTRICT(a[6].toString());
			bean.setSTATE(a[7].toString());
			bean.setZONE(a[8].toString());
			bean.setGROUP_NAME(a[9].toString());
			dataList.add(bean);
		}

		return dataList;
	}

	public List<LatencyThresholdReportBean> showLatencyThreshold(String ip_address, String fromDate, String toDate) {
		String query = "select report.ID,report.NODE_IP,report.LATENCY_THRESHOLD,report.LATENCY_VAL,report.LATENCY_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join LATENCY_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP='"
				+ ip_address + "' and  report.EVENT_TIMESTAMP BETWEEN '" + fromDate + "' AND '" + toDate + "'";
		Query q = getSession().createSQLQuery(query);
		List<LatencyThresholdReportBean> dataList = new ArrayList<LatencyThresholdReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				LatencyThresholdReportBean bean = new LatencyThresholdReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setLATENCY_THRESHOLD(Integer.parseInt(a[2].toString()));
				bean.setLATENCY_VAL(Double.parseDouble(a[3].toString()));
				bean.setLATENCY_STATUS(a[4].toString());

				bean.setEVENT_TIMESTAMP(a[5].toString());
				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<NodeAvailabilityBean> showAvailabilityReport(String ip_address, String fromDate, String toDate) {
		String query = "select report.ID,report.NODE_IP,report.UPTIME_PERCENT,report.UPTIME_STR,report.DOWNTIME_PERCENT,report.DOWNTIME_STR,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join NODE_AVAILABILITY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP='"
				+ ip_address + "' and  report.EVENT_TIMESTAMP BETWEEN '" + fromDate + "' AND '" + toDate + "'";
		Query q = getSession().createSQLQuery(query);
		List<NodeAvailabilityBean> dataList = new ArrayList<NodeAvailabilityBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				String var_uptime = "NA";
				System.out.println(a[1].toString() + "var_uptime:" + var_uptime);

				String var_downtime = "NA";

				id++;
				NodeAvailabilityBean bean = new NodeAvailabilityBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setUPTIME_PERCENT(Double.parseDouble(a[2].toString()));
				bean.setUPTIME(a[3].toString());
				bean.setDOWNTIME_PERCENT(Double.parseDouble(a[4].toString()));
				bean.setDOWNTIME(a[5].toString());

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

	public List<CpuThresholdHealthReportBean> showCPUThreshold(String ip_address, String fromDate, String toDate) {
		String query = "select report.ID,report.NODE_IP,report.CPU_UTILIZATION,report.CPU_THRESHOLD,report.CPU_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join CPU_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP='"
				+ ip_address + "' and  report.EVENT_TIMESTAMP BETWEEN '" + fromDate + "' AND '" + toDate + "'";
		Query q = getSession().createSQLQuery(query);
		List<CpuThresholdHealthReportBean> dataList = new ArrayList<CpuThresholdHealthReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				CpuThresholdHealthReportBean bean = new CpuThresholdHealthReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setCPU_UTILIZATION(Double.parseDouble(a[2].toString()));
				bean.setCPU_THRESHOLD(Double.parseDouble(a[3].toString()));
				bean.setCPU_STATUS(a[4].toString());

				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<MemoryThresholdReportBean> showMemoryThreshold(String ip_address, String fromDate, String toDate) {
		String query = "select report.ID,report.NODE_IP,report.MEMORY_UTILIZATION,report.MEMORY_THRESHOLD,report.MEMORY_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join MEMORY_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP='"
				+ ip_address + "' and  report.EVENT_TIMESTAMP BETWEEN '" + fromDate + "' AND '" + toDate + "'";
		Query q = getSession().createSQLQuery(query);
		List<MemoryThresholdReportBean> dataList = new ArrayList<MemoryThresholdReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				MemoryThresholdReportBean bean = new MemoryThresholdReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setMEMORY_UTILIZATION(Double.parseDouble(a[2].toString()));
				bean.setMEMORY_THRESHOLD(Double.parseDouble(a[3].toString()));
				bean.setMEMORY_STATUS(a[4].toString());

				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public JSONObject showAvailabilityGraph(String ip_address, String fromDate, String toDate) {
		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray downtime_percent_array = null;
		downtime_percent_array = new JSONArray();

		JSONArray uptime_percent_array = null;
		uptime_percent_array = new JSONArray();

		JSONArray downtime_percent_obj = null;
		JSONArray uptime_percent_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(fromDate);
		Timestamp toDateTimeStamp = Timestamp.valueOf(toDate);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from NodeAvailablity where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			int srno = 0;
			List<NodeAvailablity> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (NodeAvailablity data : dataList) {
				srno = srno + 1;

				double downtime_percent = data.getDOWNTIME_PERCENT();
				double uptime_percent = data.getUPTIME_PERCENT();
				Date time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				downtime_percent_obj = new JSONArray();
				downtime_percent_obj.put(datemili);
				downtime_percent_obj.put(downtime_percent);
				downtime_percent_array.put(downtime_percent_obj);

				uptime_percent_obj = new JSONArray();
				uptime_percent_obj.put(datemili);
				uptime_percent_obj.put(uptime_percent);
				uptime_percent_array.put(uptime_percent_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("Downtime Percent:" + downtime_percent_array);
		System.out.println("UpTime Percent:" + uptime_percent_array);
		jsonObj.put("downtimePercent", downtime_percent_array);
		jsonObj.put("uptimePercent", uptime_percent_array);
		return jsonObj;
	}

	public JSONObject showLatencyGraph(String ip_address, String fromDate, String toDate) {
		System.out.println("latency graph");
		JSONObject jsonFinalObj = new JSONObject();

		JSONArray latency_array = new JSONArray();
		JSONObject latency_obj = null;

		JSONArray packet_drop_array = new JSONArray();
		JSONObject packet_drop_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(fromDate);
		Timestamp toDateTimeStamp = Timestamp.valueOf(toDate);

		try {
			System.out.println("Query:" + ip_address);
			SimpleDateFormat formatOfDateForChart = null;
			formatOfDateForChart = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
			Query q = getSession().createQuery(
					"from LatencyHisotryModel where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			int srno = 0;
			List<LatencyHisotryModel> dataList = q.list();
			for (LatencyHisotryModel data : dataList) {
				srno = srno + 1;

				double avg_latency = data.getAVG_LATENCY();
				double pkt_loss = data.getPACKET_LOSS();
				Timestamp time = data.getEVENT_TIMESTAMP();
				Date cDate = time;
				String s = cDate.toString();
				String[] arrOfStr = s.split(" ");
				String cTime = arrOfStr[1].substring(0, 8);
				Date newDate = null;
				newDate = formatOfDateForChart.parse(cDate + " " + cTime.replaceAll(" ", ""));
				System.out.println("formatedDate: " + newDate);
				long datemili = newDate.getTime();

				latency_obj = new JSONObject();
				latency_obj.put("x", datemili);
				latency_obj.put("y", avg_latency);
				latency_array.put(latency_obj);
				System.out.println("latency_obj=:" + latency_obj);

				packet_drop_obj = new JSONObject();
				packet_drop_obj.put("x", datemili);
				packet_drop_obj.put("y", pkt_loss);
				packet_drop_array.put(packet_drop_obj);
				System.out.println("Packet drop obj=:" + packet_drop_obj);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("size = " + latency_array.length());
		jsonFinalObj.put("latency", latency_array);
		jsonFinalObj.put("packet_drop", packet_drop_array);
		return jsonFinalObj;
	}

	public List<NodeHealthHistoryReportBean> showCPUReport(String ip_address, String fromDate, String toDate) {
		String query = "select report.ID,report.NODE_IP,report.CPU_UTILIZATION,report.TEMPERATURE,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME, report.TOTAL_MEMORY, report.USED_MEMORY, report.FREE_MEMORY from add_node node join NODE_HEALTH_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP='"
				+ ip_address + "' and  report.EVENT_TIMESTAMP BETWEEN '" + fromDate + "' AND '" + toDate + "'";
		Query q = getSession().createSQLQuery(query);
		List<NodeHealthHistoryReportBean> dataList = new ArrayList<NodeHealthHistoryReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				NodeHealthHistoryReportBean bean = new NodeHealthHistoryReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setCPU_UTILIZATION(Double.parseDouble(a[2].toString()));
				bean.setTEMPERATURE(Double.parseDouble(a[3].toString()));

				bean.setEVENT_TIMESTAMP(a[4].toString());

				bean.setDEVICE_NAME(a[5].toString());
				bean.setLOCATION(a[6].toString());
				bean.setDISTRICT(a[7].toString());
				bean.setSTATE(a[8].toString());
				bean.setZONE(a[9].toString());
				bean.setGROUP_NAME(a[10].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<NodeHealthHistoryReportBean> showMemoryReport(String ip_address, String fromDate, String toDate) {
		String query = "select report.ID,report.NODE_IP,report.MEMORY_UTILIZATION,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME, report.TOTAL_MEMORY, report.USED_MEMORY, report.FREE_MEMORY from add_node node join NODE_HEALTH_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP='"
				+ ip_address + "' and  report.EVENT_TIMESTAMP BETWEEN '" + fromDate + "' AND '" + toDate + "'";
		Query q = getSession().createSQLQuery(query);
		List<NodeHealthHistoryReportBean> dataList = new ArrayList<NodeHealthHistoryReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				NodeHealthHistoryReportBean bean = new NodeHealthHistoryReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setMEMORY_UTILIZATION(Double.parseDouble(a[2].toString()));
				bean.setTOTAL_MEMORY(Double.parseDouble(a[10].toString()));
				bean.setUSED_MEMORY(Double.parseDouble(a[11].toString()));
				bean.setFREE_MEMORY(Double.parseDouble(a[12].toString()));

				bean.setEVENT_TIMESTAMP(a[3].toString());

				bean.setDEVICE_NAME(a[4].toString());
				bean.setLOCATION(a[5].toString());
				bean.setDISTRICT(a[6].toString());
				bean.setSTATE(a[7].toString());
				bean.setZONE(a[8].toString());
				bean.setGROUP_NAME(a[9].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public JSONArray showMemoryGraph(String ip_address, String fromDate, String toDate) {
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

	public JSONArray showCPUGraph(String ip_address, String fromDate, String toDate) {
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

	public JSONObject interfaceBandwidthGraph(String from_date, String to_date, String ip_address,
			String interfaceName) {
		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray inTraffic_array = null;
		inTraffic_array = new JSONArray();

		JSONArray outTraffic_array = null;
		outTraffic_array = new JSONArray();

		JSONArray inTraffic_obj = null;
		JSONArray outTraffic_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from InterfaceBWHistory where NODE_IP=:NODE_IP AND INTERFACE_NAME=:interfaceName AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			q.setParameter("interfaceName", interfaceName);
//			q.setParameter("INTERFACE_NAME", interface_name);

			int srno = 0;
			List<InterfaceBWHistory> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (InterfaceBWHistory data : dataList) {
				srno = srno + 1;

				double in_traffic = data.getIN_TRAFFIC();
				double out_traffic = data.getOUT_TRAFFIC();
				Timestamp time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				inTraffic_obj = new JSONArray();
				inTraffic_obj.put(datemili);
				inTraffic_obj.put(in_traffic);
				inTraffic_array.put(inTraffic_obj);

				outTraffic_obj = new JSONArray();
				outTraffic_obj.put(datemili);
				outTraffic_obj.put(out_traffic);
				outTraffic_array.put(outTraffic_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		jsonObj.put("inTraffic", inTraffic_array);
		jsonObj.put("outTraffic", outTraffic_array);
		return jsonObj;
	}

	public JSONObject interfaceUptimeGraph(String from_date, String to_date, String ip_address, String interfaceName) {
		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray downtime_percent_array = null;
		downtime_percent_array = new JSONArray();

		JSONArray uptime_percent_array = null;
		uptime_percent_array = new JSONArray();

		JSONArray downtime_percent_obj = null;
		JSONArray uptime_percent_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from InterfaceAvailablity where NODE_IP=:NODE_IP AND INTERFACE_NAME=:interfaceName AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			q.setParameter("interfaceName", interfaceName);
			// q.setParameter("INTERFACE_NAME", interface_name);
			int srno = 0;
			List<InterfaceAvailablity> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (InterfaceAvailablity data : dataList) {
				srno = srno + 1;

				double downtime_percent = data.getDOWNTIME_PERCENT();
				double uptime_percent = data.getUPTIME_PERCENT();
				Date time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				downtime_percent_obj = new JSONArray();
				downtime_percent_obj.put(datemili);
				downtime_percent_obj.put(downtime_percent);
				downtime_percent_array.put(downtime_percent_obj);

				uptime_percent_obj = new JSONArray();
				uptime_percent_obj.put(datemili);
				uptime_percent_obj.put(uptime_percent);
				uptime_percent_array.put(uptime_percent_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("Downtime Percent:" + downtime_percent_array);
		jsonObj.put("downtimePercent", downtime_percent_array);
		jsonObj.put("uptimePercent", uptime_percent_array);
		return jsonObj;
	}

	public JSONArray getWindowsEventReport(String from_date, String to_date, List<String> list) {
		String ip_data = list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		JSONArray array = new JSONArray();
		JSONArray obj = null;
		int sr = 0;
		try {

			List<Windows_Event_Log> eventList = getSession().createQuery("FROM Windows_Event_Log where eventTime >= '"
					+ from_date + "' " + "and eventTime <= '" + to_date + "' and deviceIp in ('" + ip_data + "')")
					.list();
			for (Windows_Event_Log object : eventList) {
				sr++;
				obj = new JSONArray();
				obj.put(sr);
				obj.put(object.getLogId());
				obj.put(object.getDeviceIp());
				obj.put(object.getDeviceName());
				obj.put(object.getEventId());
				obj.put(object.getEventType());
				obj.put(object.getEventSeverity());
				obj.put(object.getEventName());
				obj.put(object.getEventCategory());
				obj.put(object.getEventSource());
				obj.put(object.getEventMessage());
				obj.put(object.getEventLevel());

				array.put(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Windows Event Report = " + array);
		return array;
	}

	public JSONArray getDriveThresholdReport(String from_date, String to_date, List<String> list) {
		String ip_data = list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		JSONArray array = new JSONArray();
		JSONArray obj = null;
		int sr = 0;
		try {

			List<DRIVE_THRESHOLD_LOG> driveData = getSession()
					.createQuery("FROM DRIVE_THRESHOLD_LOG where EVENT_TIME >= '" + from_date + "' "
							+ "and EVENT_TIME <= '" + to_date + "' and DEVICE_IP in ('" + ip_data + "')")
					.list();
			for (DRIVE_THRESHOLD_LOG object : driveData) {
				sr++;
				obj = new JSONArray();
				obj.put(sr);
				obj.put(object.getDEVICE_IP());
				obj.put(object.getDEVICE_NAME());
				obj.put(object.getUSER_NAME());
				obj.put(object.getDRIVES_NAME());
				obj.put(object.getSTATUS());
				obj.put(object.getEVENT_TIME());

				array.put(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Drive Threshold Report = " + array);
		return array;
	}

	public JSONArray getServiceReport(String from_date, String to_date, List<String> list) {
		String ip_data = list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		JSONArray array = new JSONArray();
		JSONArray obj = null;
		int sr = 0;
		try {

			List<Object[]> driveData = getSession()
					.createSQLQuery("SELECT device_ip, device_name, service_name, status, event_time"
							+ " from service_status_log" + " where event_time >= '" + from_date + "' "
							+ "and event_time <= '" + to_date + "' and device_ip in ('" + ip_data + "')")
					.list();
			for (Object[] object : driveData) {
				sr++;
				obj = new JSONArray();
				obj.put(sr);
				obj.put(object[0]);
				obj.put(object[1]);
				obj.put(object[2]);
				obj.put(object[3]);
				obj.put(object[4]);

				array.put(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Service Report = " + array);
		return array;
	}

	public List<Hardware_Inventory> windowsHardwareInventory() {
		Criteria criteria = getSession().createCriteria(Hardware_Inventory.class);
		return criteria.list();
	}

	public JSONArray linuxHardwareInventory() {
		JSONArray array = new JSONArray();
		JSONArray obj = null;
		int sr = 0;
		try {

			List<Object[]> linuxInventory = getSession().createSQLQuery(
					"SELECT IP_ADDRESS, PC_NAME, MAC_ADDRESS, BIOS_INFO, GRAPHIC_CARD, HDD_DRIVE, MOTHERBOARD_NAME, OP_NAME, OS_NAME,\r\n"
							+ "VERSION, PROCESSOR_NAME, RAM_DETAILS, SERIAL_NO, CTIME, DISCOVER_TIME, ARCHITECTURE\r\n"
							+ "FROM hardware_inventory_linux;")
					.list();
			for (Object[] object : linuxInventory) {
				// sr++;
				obj = new JSONArray();
				// obj.put(sr);
				obj.put(object[0]);
				obj.put(object[1]);
				obj.put((object[2] == null) ? "NA" : object[2].equals("") ? "NA" : object[2]);
				obj.put(object[3]);
				obj.put(object[4]);
				obj.put(object[5]);
				obj.put(object[6]);
				obj.put(object[7]);
				obj.put(object[8]);
				obj.put(object[9]);
				obj.put(object[10]);
				obj.put(object[11]);
				obj.put(object[12]);
				obj.put(object[13]);
				obj.put(object[14]);
				obj.put(object[15]);

				array.put(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Linux Hardware Inventory Report = " + array);
		return array;
	}

	public JSONArray linuxSoftwareInventory() {
		JSONArray array = new JSONArray();
		JSONArray obj = null;
		int sr = 0;
		try {

			List<Object[]> linuxInventory = getSession()
					.createSQLQuery(
							"SELECT IP_ADDRESS, PC_NAME, APPLICATION_NAME, CDATE, CTIME FROM sw_inventory_linux")
					.list();
			for (Object[] object : linuxInventory) {
				// sr++;
				obj = new JSONArray();
				// obj.put(sr);
				obj.put(object[0]);
				obj.put(object[1]);
				obj.put(object[2]);
				obj.put(object[3]);
				obj.put(object[4]);

				array.put(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Linux Software Inventory Report = " + array);
		return array;
	}

	public JSONArray linuxServices() {
		JSONArray array = new JSONArray();
		JSONArray obj = null;
		int sr = 0;
		try {

			List<Object[]> linuxInventory = getSession().createSQLQuery(
					"SELECT IP_ADDRESS, PC_NAME, SERVICE_NAME, STATUS, LOAD_NAME, ACTIVE_STATUS, CDATE, CTIME FROM linux_service_monitoring")
					.list();
			for (Object[] object : linuxInventory) {
				// sr++;
				obj = new JSONArray();
				// obj.put(sr);
				obj.put(object[0]);
				obj.put(object[1]);
				obj.put(object[2]);
				obj.put(object[3]);
				obj.put(object[4]);
				obj.put(object[5]);
				obj.put(object[6]);
				obj.put(object[7]);
				array.put(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Linux Services = " + array);
		return array;
	}

	public JSONArray windowsServices() {
		JSONArray array = new JSONArray();
		JSONArray obj = null;
		int sr = 0;
		try {

			List<Object[]> windowsServices = getSession().createSQLQuery(
					"SELECT DEVICE_IP, DEVICE_NAME, SERVICE_NAME, DISPLAY_NAME, SERVICE_MODE, SERVICE_STATE FROM discover_services")
					.list();
			for (Object[] object : windowsServices) {
				// sr++;
				obj = new JSONArray();
				// obj.put(sr);
				obj.put(object[0]);
				obj.put(object[1]);
				obj.put(object[2]);
				obj.put(object[3]);
				obj.put(object[4]);
				obj.put(object[5]);
				array.put(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Windows Services = " + array);
		return array;
	}

	public String interfaceipassignhereDao(String interface_ip, String ip_address, String interface_name) {
		// TODO Auto-generated method stub
		System.out.println("inside dao interfaceipassignhereDao");
		System.out.println("interface name = " + interface_name);
		System.out.println("ip_address :" + ip_address);
		System.out.println("Interface_ip :" + interface_ip);
		String result = "fail";

		try {
			Encryption encrypt = new Encryption();
//			Query q = getSession().createQuery(
//					"Update UserMasterModel set email=:email,mobile_no=:mobileNo, name=:name, password=:password, confirm_password=:confirmPassword, role=:role, department=:department where id=:id");
			Query q = getSession().createSQLQuery(
					"UPDATE interface_monitoring SET Interface_IP_Assign =:Interface_IP_Assign, Interface_IP_ICMP_Status =:Interface_IP_ICMP_Status, Intreface_IP_Monitoring =:Intreface_IP_Monitoring WHERE NODE_IP =:NODE_IP AND INTERFACE_NAME =:INTERFACE_NAME"
							+ "");
			q.setParameter("Interface_IP_Assign", interface_ip);
			q.setParameter("Interface_IP_ICMP_Status", "Up");
			q.setParameter("Intreface_IP_Monitoring", "Yes");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("INTERFACE_NAME", interface_name);

			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public JSONArray getinterfaceassigndata() {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Query q = getSession().createQuery(
					"SELECT npm FROM InterfaceMonitoring npm WHERE Interface_IP_Assign IS NOT NULL AND Interface_IP_ICMP_Status IS NOT NULL AND Intreface_IP_Monitoring IS NOT NULL");
			List<InterfaceMonitoring> dataList = q.list();
			array1 = new JSONArray();
			for (InterfaceMonitoring configData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(configData.getNODE_IP());
				array.put(configData.getINTERFACE_NAME());
				array.put(configData.getInterface_IP_Assign());

//				array.put(
//						"<a href=\"editConfigurationBackup/" + configData.getID() + "\"><i class=\"fas fa-edit\"></a>");
//				array.put(
//						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteConfigurationBackup(\""
//								+ configData.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public List<UserLoginHistoryBean> userLogReportData(String from_date, String to_date) {

//		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println(" from_date:" + from_date + to_date);
		String query = "SELECT  history.activity, history.ipaddress, history.timestamp, history.username FROM user_login_history history WHERE history.timestamp BETWEEN '"
				+ from_date + "' AND '" + to_date + "'";
		System.out.println("query :: " + query);
		Query q = getSession().createSQLQuery(query);
		List<UserLoginHistoryBean> dataList = new ArrayList<UserLoginHistoryBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			id++;
			UserLoginHistoryBean bean = new UserLoginHistoryBean();
			bean.setID(id);
			bean.setActivity((a[0] == null) ? "-" : a[0].toString().equals("") ? "-" : a[0].toString());

			bean.setIpaddress((a[1] == null) ? "-" : a[1].toString().equals("") ? "-" : a[1].toString());
			bean.setTimestamp((a[2] == null) ? "-" : a[2].toString().equals("") ? "-" : a[2].toString());
			bean.setUsername((a[3] == null) ? "-" : a[3].toString().equals("") ? "-" : a[3].toString());

			dataList.add(bean);
		}

		return dataList;
	}

	public Map<String, String> getNodeIP() {

		Map<String, String> ipMap = new HashMap<String, String>();
		try {
			List<String> li = getSession().createSQLQuery("SELECT device_ip FROM syslogs_ips").list();

//			List<AddNodeModel> listdata = q.list();

			for (String ips : li) {
				ipMap.put(ips.trim(), ips.trim());
			}

//			for (AddNodeModel ip : listdata) {
//				ipMap.put(ip.getDEVICE_IP(), ip.getDEVICE_IP());
//			}
		} catch (Exception e) {
			System.out.println("Exception occured while fetching Node IP from Add Node " + e);
		}

		return ipMap;
	}

	public JSONArray getSyslogReport(String from_date, String to_date, String ip) {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		try {
			List<Object[]> li = getSession().createSQLQuery(
					"select device_ip, syslog_date, syslog_msg, syslog_rawmsg, syslog_type, syslog_severity from syslogs_data where event_time between '"
							+ from_date + "' and '" + to_date + "' and device_ip='" + ip + "'")
					.list();
			for (Object[] data : li) {
				sr++;
				array = new JSONArray();
				array.put(sr);
				array.put(data[0]);
				array.put(data[1]);
				array.put(data[2]);
				array.put(data[3]);
				array.put(data[4]);
				array.put(data[5]);
				arrayList.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public JSONArray latencyStatusHistoryReport(String from_date, String to_date, List<String> list) {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		String ip_data = "";
		Map<String, Map<String, String>> deviceMap = null;
		try {
			ip_data = list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
			System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			String query001 = "SELECT device_ip, device_name, group_name FROM add_node ";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];

				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName);
				deviceMap.put(deviceIp, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			String query = "select device_ip, status, timestamp, latency, packetdrop, working_hour_flag "
					+ "from device_status_latency_history where device_ip in ('" + ip_data
					+ "') and  timestamp_epoch BETWEEN UNIX_TIMESTAMP('" + from_date + "') \r\n"
					+ "                        AND UNIX_TIMESTAMP('" + to_date + "') " + " AND working_hour_flag=1";

			Query q = getSession().createSQLQuery(query);

			List<Object[]> li = q.list();
			for (Object[] data : li) {
				sr++;

				String ip = data[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(ip);
				String deviceName = "-";
				String groupName = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

				array = new JSONArray();
				array.put(sr);
				array.put(data[0]);
				array.put(deviceName);
				array.put(groupName);
				array.put(data[1]);
				array.put(data[2]);
				array.put(data[3]);
				array.put(data[4]);
//				array.put(data[5]);
				arrayList.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public JSONArray latencyStatusHistoryReportFilter(String from_date, String to_date, List<String> list,
			String status) {

		System.out.println("Inside Filter");
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		String ip_data = "";
		Map<String, Map<String, String>> deviceMap = null;
		try {
			ip_data = list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
			System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			String query001 = "SELECT device_ip, device_name, group_name FROM add_node ";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];

				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName);
				deviceMap.put(deviceIp, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			String query = "select device_ip, status, timestamp, latency, packetdrop, working_hour_flag "
					+ "from device_status_latency_history where device_ip in ('" + ip_data
					+ "') and  timestamp_epoch BETWEEN UNIX_TIMESTAMP('" + from_date + "') \r\n"
					+ "                        AND UNIX_TIMESTAMP('" + to_date + "') "
					+ " AND working_hour_flag=1 AND status='" + status.trim() + "'";

			Query q = getSession().createSQLQuery(query);

			List<Object[]> li = q.list();
			for (Object[] data : li) {
				sr++;

				String ip = data[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(ip);
				String deviceName = "-";
				String groupName = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

				array = new JSONArray();
				array.put(sr);
				array.put(data[0]);
				array.put(deviceName);
				array.put(groupName);
				array.put(data[1]);
				array.put(data[2]);
				array.put(data[3]);
				array.put(data[4]);
//				array.put(data[5]);
				arrayList.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public JSONArray avgLatencyStatusHistoryReport(String from_date, String to_date, String status) {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;
		Map<String, Map<String, String>> deviceMap = null;
		try {

			String query001 = "SELECT device_ip, device_name, group_name FROM add_node ";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];

				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName);
				deviceMap.put(deviceIp, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			String query = "select device_ip, ROUND(AVG(latency), 2), ROUND(AVG(packetdrop), 2) "
					+ "from device_status_latency_history where    timestamp_epoch BETWEEN UNIX_TIMESTAMP('" + from_date
					+ "') \r\n" + "  AND UNIX_TIMESTAMP('" + to_date + "')  " + " AND working_hour_flag=1 AND status='"
					+ status.trim() + "'  group by device_ip";

			Query q = getSession().createSQLQuery(query);

			List<Object[]> li = q.list();
			for (Object[] data : li) {
				String ip = data[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(ip);
				String deviceName = "-";
				String groupName = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}
				sr++;
				array = new JSONArray();
				array.put(sr);
				array.put(data[0]);
				array.put(deviceName);
				array.put(groupName);
				array.put(data[1]);
				array.put(data[2]);
				arrayList.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public JSONArray getWorkingHoursAvailabilityReport(String from_date, String to_date) {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int sr = 0;

		Map<String, Map<String, String>> deviceMap = null;
		try {

			String query001 = "SELECT device_ip, device_name, group_name FROM add_node ";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];

				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName);
				deviceMap.put(deviceIp, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

//			String query = " SELECT \r\n" + "    device_ip,\r\n" + "    AVG(latency) AS avg_latency,\r\n"
//					+ "    AVG(packetdrop) AS avg_packet_drop\r\n" + "FROM \r\n"
//					+ "    device_status_latency_history\r\n" + "WHERE \r\n"
//					+ "    timestamp_epoch BETWEEN UNIX_TIMESTAMP('" + from_date + "') \r\n"
//					+ "                        AND UNIX_TIMESTAMP('" + to_date + "')\r\n" + "GROUP BY \r\n"
//					+ "    device_ip";

			String query = " SELECT \r\n" + "    device_ip,\r\n"
					+ "     SUM(CASE WHEN status = 'Up' THEN total_duration_seconds ELSE 0 END) AS uptime_seconds,\r\n"
					+ "     SUM(CASE WHEN status = 'Down' THEN total_duration_seconds ELSE 0 END) AS downtime_seconds\r\n"
					+ "FROM (\r\n" + "    SELECT \r\n" + "        device_ip,\r\n" + "        status,\r\n"
					+ "COALESCE(\r\n" + "            LEAD(timestamp_epoch) OVER (\r\n"
					+ "                PARTITION BY device_ip, DATE(FROM_UNIXTIME(timestamp_epoch)) \r\n"
					+ "                ORDER BY timestamp_epoch\r\n" + "            ) - timestamp_epoch, \r\n"
					+ "            0\r\n" + "        ) AS total_duration_seconds"
					+ "    FROM device_status_latency_history\r\n"
					+ "    WHERE working_hour_flag=1 AND timestamp BETWEEN '" + from_date + "' AND '" + to_date
					+ "' \r\n" + ") AS durations\r\n" + "GROUP BY device_ip;\r\n" + "";

//			System.out.println("querrry ::::::: " + query);
			Query q = getSession().createSQLQuery(query);

			List<Object[]> li = q.list();
			for (Object[] data : li) {

				sr++;

				String ip = data[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(ip);
				String deviceName = "-";
				String groupName = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}
				array = new JSONArray();
				array.put(sr);
				String ipaddress = data[0].toString();
				double uptimeDouble = Double.parseDouble(data[1].toString().trim());

				double DowntimeDouble = Double.parseDouble(data[2].toString().trim());
//				
//				
//				
//				
//				
//				// Convert to long
				long uptime = (long) uptimeDouble;
//				Long uptime = Long.parseLong(data[1].toString().trim());
//
//				// Convert to long
				long downtime = (long) DowntimeDouble;
////				Long downtime = Long.parseLong(data[2].toString().trim());

//				Long uptime = null;
//				Long downtime = null;

//				if (data[1].toString().trim().matches("\\d+\\.\\d+")) { // Check if the string is a valid decimal number
//					double value = Double.parseDouble(data[1].toString().trim());
//					uptime = Math.round(value);
//				} else {
//					// Handle invalid input
//					uptime = Long.parseLong(data[1].toString().trim());
//
//				}
//				if (data[2].toString().trim().matches("\\d+\\.\\d+")) { // Check if the string is a valid decimal number
//					double value = Double.parseDouble(data[2].toString().trim());
//					downtime = Math.round(value);
//				} else {
//					// Handle invalid input
//
//					downtime = Long.parseLong(data[2].toString().trim());
//				}

				Long totalTime = uptime + downtime;

				// Calculate Uptime (%) and Downtime (%)
				double uptimePercentage = (uptime * 100.0) / totalTime;
				double downtimePercentage = (downtime * 100.0) / totalTime;

//				struig cal

				int day = (int) TimeUnit.SECONDS.toDays(uptime);
				long hours = TimeUnit.SECONDS.toHours(uptime) - (day * 24);
				long minute = TimeUnit.SECONDS.toMinutes(uptime) - (TimeUnit.SECONDS.toHours(uptime) * 60);
				long second = TimeUnit.SECONDS.toSeconds(uptime) - (TimeUnit.SECONDS.toMinutes(uptime) * 60);
				String var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

//				int day = (int) TimeUnit.HOURS.toDays((long) uptime); // Convert hours to days
//				long hours = (long) uptime % 24; // Remaining whole hours after extracting days
//
//				// Extract the fractional part of the hour and convert to minutes and seconds
//				double fractionalHour = uptime - (long) uptime; // Fractional part (e.g., 0.77)
//				long minute = (long) (fractionalHour * 60); // Convert fraction to minutes
//				long second = (long) ((fractionalHour * 60 - minute) * 60); // Remaining fraction to seconds
//
//				String var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

				day = (int) TimeUnit.SECONDS.toDays(downtime);
				hours = TimeUnit.SECONDS.toHours(downtime) - (day * 24);
				minute = TimeUnit.SECONDS.toMinutes(downtime) - (TimeUnit.SECONDS.toHours(downtime) * 60);
				second = TimeUnit.SECONDS.toSeconds(downtime) - (TimeUnit.SECONDS.toMinutes(downtime) * 60);
				String var_Downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second
						+ " Seconds";

//				day = (int) TimeUnit.HOURS.toDays((long) downtime); // Convert hours to days
//				hours = (long) downtime % 24; // Remaining whole hours after extracting days
//
//				// Extract the fractional part of the hour and convert to minutes and seconds
//				fractionalHour = downtime - (long) downtime; // Fractional part (e.g., 0.77)
//				minute = (long) (fractionalHour * 60); // Convert fraction to minutes
//				second = (long) ((fractionalHour * 60 - minute) * 60); // Remaining fraction to seconds
//
//				String var_Downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second
//						+ " Seconds";

				DecimalFormat df = new DecimalFormat("0.00");
				array.put(data[0]);
				array.put(deviceName);
				array.put(groupName);
				array.put(var_uptime);
				array.put(var_Downtime);
				array.put(df.format(uptimePercentage));
				array.put(df.format(downtimePercentage));
				arrayList.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public JSONArray slaReportData(String from_date, String to_date, String yearlyCost) {
		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int penalty_cost_percentage = 0;
		double penalty_cost = 0;
		int sr = 0;
		String daysData = "";
		String fromTime = "";
		String toTime = "";
		String numericDaysData = "";
		String betweenData = "";

		System.out.println(yearlyCost);
		System.out.println(from_date);
		System.out.println(to_date);

		System.out.println(fromTime);
		System.out.println(toTime);

		System.out.println(numericDaysData);
		System.out.println(betweenData);

		Map<String, Map<String, String>> deviceMap = null;

		try {

			String query001 = "SELECT device_ip, device_name, group_name FROM add_node ";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];

				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName);
				deviceMap.put(deviceIp, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String query = " SELECT \r\n" + "    device_ip,\r\n"
					+ "     SUM(CASE WHEN status = 'Up' THEN total_duration_seconds ELSE 0 END) AS uptime_hours,\r\n"
					+ "     SUM(CASE WHEN status = 'Down' THEN total_duration_seconds ELSE 0 END) AS downtime_hours\r\n"
					+ "FROM (\r\n" + "    SELECT \r\n" + "        device_ip,\r\n" + "        status,\r\n"
					+ " COALESCE(\r\n" + "            LEAD(timestamp_epoch) OVER (\r\n"
					+ "                PARTITION BY device_ip, DATE(FROM_UNIXTIME(timestamp_epoch)) \r\n"
					+ "                ORDER BY timestamp_epoch\r\n" + "            ) - timestamp_epoch, \r\n"
					+ "            0\r\n" + "        ) AS total_duration_seconds "
					+ "    FROM device_status_latency_history\r\n"
					+ "    WHERE working_hour_flag=1 AND timestamp BETWEEN '" + from_date + "' AND '" + to_date
					+ "' \r\n" + ") AS durations\r\n " + "GROUP BY device_ip";

//			String query = "SELECT \r\n" + "    device_ip,\r\n"
//					+ "    SUM(CASE WHEN status = 'Up' AND working_hour_flag = 1 THEN total_duration_seconds ELSE 0 END) AS uptime_seconds,\r\n"
//					+ "    SUM(CASE WHEN status = 'Down' AND working_hour_flag = 1 THEN total_duration_seconds ELSE 0 END) AS downtime_seconds\r\n"
//					+ "FROM (\r\n" + "    SELECT \r\n" + "        device_ip,\r\n" + "        status,\r\n"
//					+ "        working_hour_flag,\r\n" + "        COALESCE(\r\n"
//					+ "            LEAD(timestamp_epoch) OVER (PARTITION BY device_ip ORDER BY timestamp_epoch) - timestamp_epoch, \r\n"
//					+ "            0\r\n" + "        ) AS total_duration_seconds\r\n"
//					+ "    FROM device_status_latency_history\r\n"
//					+ "    WHERE working_hour_flag=1 AND timestamp BETWEEN '" + from_date + "' AND '" + to_date
//					+ "' \r\n" + ") AS durations\r\n" + "GROUP BY device_ip";

//			List<Object[]> li = getSession().createSQLQuery(
//					"select NODE_IP, avg(UPTIME_PERCENT), avg(DOWNTIME_PERCENT) from node_availability group by NODE_IP")
//					.list();
			List<Object[]> li = getSession().createSQLQuery(query).list();
			for (Object[] data : li) {
				array = new JSONArray();
				sr++;
				String ip = data[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(ip);
				String deviceName = "-";
				String groupName = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

				array.put(sr);
				String ipaddress = data[0].toString();
				Long uptime = Long.parseLong(data[1].toString().trim());
				Long downtime = Long.parseLong(data[2].toString().trim());

//				Long uptime = null;
//				Long downtime = null;

//				if (data[1].toString().trim().matches("\\d+\\.\\d+")) { // Check if the string is a valid decimal number
//					double value = Double.parseDouble(data[1].toString().trim());
//					uptime = Math.round(value);
//				} else {
//					// Handle invalid input
//					uptime = Long.parseLong(data[1].toString().trim());
//
//				}
//				if (data[2].toString().trim().matches("\\d+\\.\\d+")) { // Check if the string is a valid decimal number
//					double value = Double.parseDouble(data[2].toString().trim());
//					downtime = Math.round(value);
//				} else {
//					// Handle invalid input
//
//					downtime = Long.parseLong(data[2].toString().trim());
//				}

				Long totalTime = uptime + downtime;

				// Calculate Uptime (%) and Downtime (%)
				double uptimePercentage = (uptime * 100.0) / totalTime;
				double downtimePercentage = (downtime * 100.0) / totalTime;

//				struig cal

				int day = (int) TimeUnit.SECONDS.toDays(uptime);
				long hours = TimeUnit.SECONDS.toHours(uptime) - (day * 24);
				long minute = TimeUnit.SECONDS.toMinutes(uptime) - (TimeUnit.SECONDS.toHours(uptime) * 60);
				long second = TimeUnit.SECONDS.toSeconds(uptime) - (TimeUnit.SECONDS.toMinutes(uptime) * 60);
				String var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

//				int day = (int) TimeUnit.HOURS.toDays((long) uptime); // Convert hours to days
//				long hours = (long) uptime % 24; // Remaining whole hours after extracting days
//
//				// Extract the fractional part of the hour and convert to minutes and seconds
//				double fractionalHour = uptime - (long) uptime; // Fractional part (e.g., 0.77)
//				long minute = (long) (fractionalHour * 60); // Convert fraction to minutes
//				long second = (long) ((fractionalHour * 60 - minute) * 60); // Remaining fraction to seconds
//
//				String var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

				day = (int) TimeUnit.SECONDS.toDays(downtime);
				hours = TimeUnit.SECONDS.toHours(downtime) - (day * 24);
				minute = TimeUnit.SECONDS.toMinutes(downtime) - (TimeUnit.SECONDS.toHours(downtime) * 60);
				second = TimeUnit.SECONDS.toSeconds(downtime) - (TimeUnit.SECONDS.toMinutes(downtime) * 60);
				String var_Downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second
						+ " Seconds";

//				day = (int) TimeUnit.HOURS.toDays((long) downtime); // Convert hours to days
//				hours = (long) downtime % 24; // Remaining whole hours after extracting days
//
//				// Extract the fractional part of the hour and convert to minutes and seconds
//				fractionalHour = downtime - (long) downtime; // Fractional part (e.g., 0.77)
//				minute = (long) (fractionalHour * 60); // Convert fraction to minutes
//				second = (long) ((fractionalHour * 60 - minute) * 60); // Remaining fraction to seconds
//
//				String var_Downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second
//						+ " Seconds";

				double UptimeTotalhours = (double) uptime;
				double DowntimeTotalhours = (double) downtime;

				String formattedUptimehours = String.format("%.2f", UptimeTotalhours);
				String formattedDowntimehours = String.format("%.2f", DowntimeTotalhours);
				// Parse the formatted strings back to doubles for calculation
				double formattedUptime22 = Double.parseDouble(formattedUptimehours);
				double formattedDowntime22 = Double.parseDouble(formattedDowntimehours);

				// Calculate the total
				double totalHours = formattedUptime22 + formattedDowntime22;

				DecimalFormat df = new DecimalFormat("0.00");
				array.put(data[0]);
				array.put(deviceName);
				array.put(groupName);
				array.put(var_uptime);
				array.put(var_Downtime);
				array.put(formattedUptimehours);
				array.put(formattedDowntimehours);
				array.put(String.format("%.2f", totalHours));
				array.put(df.format(uptimePercentage));
				array.put(df.format(downtimePercentage));

				if (uptimePercentage >= 99) {
					penalty_cost_percentage = 0;
				} else if (uptimePercentage >= 98.5 && uptimePercentage < 99) {
					penalty_cost_percentage = 10;
				} else if (uptimePercentage >= 97.5 && uptimePercentage < 98.5) {
					penalty_cost_percentage = 20;
				} else if (uptimePercentage >= 96.5 && uptimePercentage < 97.5) {
					penalty_cost_percentage = 25;
				} else if (uptimePercentage < 96.5) {
					penalty_cost_percentage = 30;
				} else if (uptimePercentage < 96.5) {
					penalty_cost_percentage = -1;
				}

				penalty_cost = (Double.valueOf(yearlyCost) * penalty_cost_percentage) / 100;
				array.put(Double.valueOf(yearlyCost));
				array.put(penalty_cost_percentage);
				array.put(penalty_cost);
				array.put(Double.valueOf(yearlyCost) - penalty_cost);

				arrayList.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList);
		return arrayList;
	}

	public JSONArray LinkLatencystatusreport(String from_date, String to_date, List<String> ip_list) {

		Map<String, Map<String, String>> deviceMap = null;

		JSONArray array = null;
		JSONArray arrayList = new JSONArray();

		try {

			// Your updated SQL query with JOIN condition
			String query001 = "SELECT a.DEVICE_IP, a.DEVICE_NAME, a.GROUP_NAME, i.Interface_IP_Assign, i.INTERFACE_NAME "
					+ "FROM add_node a " + "JOIN interface_monitoring i ON a.DEVICE_IP = i.NODE_IP "
					+ "WHERE i.MONITORING_PARAM = 'Yes'";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];
				String interfaceIpAssign = (String) row[3];
				String interfaceName = (String) row[4];

				// Create an inner Map to store key-value pairs for deviceName and groupName
				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName); // Key as groupName and value as groupName
				innerMap.put("interfaceName", interfaceName); // Key as interfaceName and value as interfaceName
				innerMap.put("deviceip", deviceIp);

				// Store the innerMap in the main HashMap using Interface_IP_Assign as the key
				deviceMap.put(interfaceIpAssign, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "select NODE_IP,NODE_STATUS,EVENT_TIMESTAMP from interface_icmp_status_events  where NODE_IP in ('"
				+ ip_data + "') and  EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
//		List<LinkLatencystatusreportBean> dataList = new ArrayList<LinkLatencystatusreportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;

				String interfaceip = a[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(interfaceip);
				String deviceName = "-";
				String groupName = "-";
				String INTERFACE_NAME = "-";
				String DEVICE_IP = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");
					INTERFACE_NAME = deviceDetails.get("interfaceName");
					DEVICE_IP = deviceDetails.get("deviceip");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

//				LinkLatencystatusreportBean bean = new LinkLatencystatusreportBean();
//				bean.setID(id);
//				bean.setNODE_IP(a[0].toString());
//				bean.setNODE_STATUS(a[1].toString());
//				bean.setEVENT_TIMESTAMP(a[2].toString());
//				bean.setDEVICE_IP(DEVICE_IP);
//				bean.setDEVICE_NAME(deviceName);
//				bean.setGROUP_NAME(groupName);
//				bean.setINTERFACE_NAME(INTERFACE_NAME);
//
//				dataList.add(bean);

				array = new JSONArray();
				array.put(id);
				array.put(a[0].toString());
				array.put(INTERFACE_NAME);
				array.put(DEVICE_IP);
				array.put(deviceName);
				array.put(groupName);
				array.put(a[1].toString());
				array.put(a[2].toString());

				arrayList.put(array);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return arrayList;
	}

	public JSONArray LinkLatencyReportView(String from_date, String to_date, List<String> ip_list) {

		JSONArray array = null;
		JSONArray arrayList = new JSONArray();

		Map<String, Map<String, String>> deviceMap = null;

		try {

			// Your updated SQL query with JOIN condition
			String query001 = "SELECT a.DEVICE_IP, a.DEVICE_NAME, a.GROUP_NAME, i.Interface_IP_Assign, i.INTERFACE_NAME "
					+ "FROM add_node a " + "JOIN interface_monitoring i ON a.DEVICE_IP = i.NODE_IP "
					+ "WHERE i.MONITORING_PARAM = 'Yes'";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];
				String interfaceIpAssign = (String) row[3];
				String interfaceName = (String) row[4];

				// Create an inner Map to store key-value pairs for deviceName and groupName
				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName); // Key as groupName and value as groupName
				innerMap.put("interfaceName", interfaceName); // Key as interfaceName and value as interfaceName
				innerMap.put("deviceip", deviceIp);

				// Store the innerMap in the main HashMap using Interface_IP_Assign as the key
				deviceMap.put(interfaceIpAssign, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "SELECT  device_ip AS DEVICE_IP,\r\n" + "    status AS STATUS,\r\n"
				+ "    timestamp AS TIMESTAMP,\r\n" + "    timestamp_epoch AS TIMESTAMP_EPOCH,\r\n"
				+ "    latency AS LATENCY,\r\n" + "    packetdrop AS PACKETDROP,\r\n"
				+ "    working_hour_flag AS WORKING_HOUR_FLAG,\r\n" + "    jitter AS JITTER\r\n" + "FROM \r\n"
				+ "    interface_status_latency_history\r\n" + "WHERE \r\n" + "    device_ip IN ('" + ip_data
				+ "') \r\n" + "    AND timestamp BETWEEN '" + from_date + "' AND '" + to_date + "'"
				+ " AND working_hour_flag=1";
		Query q = getSession().createSQLQuery(query);
//		List<LinkLatencyReportBean> dataList = new ArrayList<LinkLatencyReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;

				String interfaceip = a[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(interfaceip);
				String deviceName = "-";
				String groupName = "-";
				String INTERFACE_NAME = "-";
				String DEVICE_IP = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");
					INTERFACE_NAME = deviceDetails.get("interfaceName");
					DEVICE_IP = deviceDetails.get("deviceip");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

//				LinkLatencyReportBean bean = new LinkLatencyReportBean();

				array = new JSONArray();
				array.put(id);
				array.put(a[0].toString());
				array.put(INTERFACE_NAME);
				array.put(DEVICE_IP);
				array.put(deviceName);
				array.put(groupName);
				array.put(a[1].toString());
				array.put(a[2].toString());
//				array.put(a[3].toString());
				array.put(a[4].toString());
				array.put(a[5].toString());
//				array.put(a[6].toString());
				array.put((a[7] != null && !a[7].toString().isEmpty()) ? a[7].toString() : "-");
				arrayList.put(array);
//				bean.setID(id);
//				bean.setDEVICE_IP(a[0].toString());
//
//				bean.setSTATUS(a[1].toString());
//				bean.setTIMESTAMP(a[2].toString());
//				bean.setTIMESTAMP_EPOCH(a[3].toString());
//				bean.setLATENCY(a[4].toString());
//				bean.setPACKETDROP(a[5].toString());
//				bean.setWORKING_HOUR_FLAG(a[6].toString());
//				bean.setJITTER((a[7] != null && !a[7].toString().isEmpty()) ? a[7].toString() : "-");
//
//				bean.setROUTER_IP(DEVICE_IP);
//				bean.setDEVICE_NAME(deviceName);
//				bean.setGROUP_NAME(groupName);
//				bean.setINTERFACE_NAME(INTERFACE_NAME);
//
//				dataList.add(bean);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return arrayList;
	}

	public JSONArray LinkLatencyReportViewFilter(String from_date, String to_date, List<String> ip_list,
			String status) {

		JSONArray array = null;
		JSONArray arrayList = new JSONArray();

		Map<String, Map<String, String>> deviceMap = null;

		try {

			// Your updated SQL query with JOIN condition
			String query001 = "SELECT a.DEVICE_IP, a.DEVICE_NAME, a.GROUP_NAME, i.Interface_IP_Assign, i.INTERFACE_NAME "
					+ "FROM add_node a " + "JOIN interface_monitoring i ON a.DEVICE_IP = i.NODE_IP "
					+ "WHERE i.MONITORING_PARAM = 'Yes'";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];
				String interfaceIpAssign = (String) row[3];
				String interfaceName = (String) row[4];

				// Create an inner Map to store key-value pairs for deviceName and groupName
				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName); // Key as groupName and value as groupName
				innerMap.put("interfaceName", interfaceName); // Key as interfaceName and value as interfaceName
				innerMap.put("deviceip", deviceIp);

				// Store the innerMap in the main HashMap using Interface_IP_Assign as the key
				deviceMap.put(interfaceIpAssign, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "SELECT  device_ip AS DEVICE_IP,\r\n" + "    status AS STATUS,\r\n"
				+ "    timestamp AS TIMESTAMP,\r\n" + "    timestamp_epoch AS TIMESTAMP_EPOCH,\r\n"
				+ "    latency AS LATENCY,\r\n" + "    packetdrop AS PACKETDROP,\r\n"
				+ "    working_hour_flag AS WORKING_HOUR_FLAG,\r\n" + "    jitter AS JITTER\r\n" + "FROM \r\n"
				+ "    interface_status_latency_history\r\n" + "WHERE \r\n" + "    device_ip IN ('" + ip_data
				+ "') \r\n" + "    AND timestamp BETWEEN '" + from_date + "' AND '" + to_date + "'"
				+ " AND working_hour_flag=1 AND status='" + status + "'";
		Query q = getSession().createSQLQuery(query);
//		List<LinkLatencyReportBean> dataList = new ArrayList<LinkLatencyReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;

				String interfaceip = a[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(interfaceip);
				String deviceName = "-";
				String groupName = "-";
				String INTERFACE_NAME = "-";
				String DEVICE_IP = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");
					INTERFACE_NAME = deviceDetails.get("interfaceName");
					DEVICE_IP = deviceDetails.get("deviceip");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

//				LinkLatencyReportBean bean = new LinkLatencyReportBean();

				array = new JSONArray();
				array.put(id);
				array.put(a[0].toString());
				array.put(INTERFACE_NAME);
				array.put(DEVICE_IP);
				array.put(deviceName);
				array.put(groupName);
				array.put(a[1].toString());
				array.put(a[2].toString());
//				array.put(a[3].toString());
				array.put(a[4].toString());
				array.put(a[5].toString());
//				array.put(a[6].toString());
				array.put((a[7] != null && !a[7].toString().isEmpty()) ? a[7].toString() : "-");
				arrayList.put(array);
//				bean.setID(id);
//				bean.setDEVICE_IP(a[0].toString());
//
//				bean.setSTATUS(a[1].toString());
//				bean.setTIMESTAMP(a[2].toString());
//				bean.setTIMESTAMP_EPOCH(a[3].toString());
//				bean.setLATENCY(a[4].toString());
//				bean.setPACKETDROP(a[5].toString());
//				bean.setWORKING_HOUR_FLAG(a[6].toString());
//				bean.setJITTER((a[7] != null && !a[7].toString().isEmpty()) ? a[7].toString() : "-");
//
//				bean.setROUTER_IP(DEVICE_IP);
//				bean.setDEVICE_NAME(deviceName);
//				bean.setGROUP_NAME(groupName);
//				bean.setINTERFACE_NAME(INTERFACE_NAME);
//
//				dataList.add(bean);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return arrayList;
	}

	public JSONArray LinkaverageLatencyReportView(String from_date, String to_date, List<String> ip_list,
			String Status) {

		JSONArray array = null;
		JSONArray arrayList = new JSONArray();

		Map<String, Map<String, String>> deviceMap = null;

		try {

			// Your updated SQL query with JOIN condition
			String query001 = "SELECT a.DEVICE_IP, a.DEVICE_NAME, a.GROUP_NAME, i.Interface_IP_Assign, i.INTERFACE_NAME "
					+ "FROM add_node a " + "JOIN interface_monitoring i ON a.DEVICE_IP = i.NODE_IP "
					+ "WHERE i.MONITORING_PARAM = 'Yes'";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];
				String interfaceIpAssign = (String) row[3];
				String interfaceName = (String) row[4];

				// Create an inner Map to store key-value pairs for deviceName and groupName
				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName); // Key as groupName and value as groupName
				innerMap.put("interfaceName", interfaceName); // Key as interfaceName and value as interfaceName
				innerMap.put("deviceip", deviceIp);

				// Store the innerMap in the main HashMap using Interface_IP_Assign as the key
				deviceMap.put(interfaceIpAssign, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "SELECT \r\n" + "    device_ip,\r\n" + "    AVG(latency) AS avg_latency,\r\n"
				+ "    AVG(packetdrop) AS avg_packetdrop\r\n" + ", AVG(jitter) AS avg_jitter " + "FROM \r\n"
				+ "    interface_status_latency_history\r\n" + "WHERE \r\n" + "    device_ip IN ('" + ip_data
				+ "') \r\n" + "    AND  timestamp_epoch BETWEEN UNIX_TIMESTAMP('" + from_date + "') \r\n"
				+ "                        AND UNIX_TIMESTAMP('" + to_date + "') "
				+ " AND working_hour_flag=1 AND status='" + Status + "'" + " GROUP BY device_ip ";
		Query q = getSession().createSQLQuery(query);
//		List<LinkaverageLatencyReportBean> dataList = new ArrayList<LinkaverageLatencyReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		for (Object[] a : data) {
			try {

				id++;

				String interfaceip = a[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(interfaceip);
				String deviceName = "-";
				String groupName = "-";
				String INTERFACE_NAME = "-";
				String DEVICE_IP = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");
					INTERFACE_NAME = deviceDetails.get("interfaceName");
					DEVICE_IP = deviceDetails.get("deviceip");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

//				LinkaverageLatencyReportBean bean = new LinkaverageLatencyReportBean();
//				bean.setID(id);
//				bean.setNODE_IP(a[0].toString());
//				bean.setAVG_LATENCY(df.format(a[1]));
//				bean.setAVG_PACKETDROP(df.format(a[2]));
//
//				bean.setROUTER_IP(DEVICE_IP);
//				bean.setDEVICE_NAME(deviceName);
//				bean.setGROUP_NAME(groupName);
//				bean.setINTERFACE_NAME(INTERFACE_NAME);
//				
//				
//				
//
//				dataList.add(bean);

				array = new JSONArray();
				array.put(id);
				array.put(a[0].toString());
				array.put(INTERFACE_NAME);
				array.put(DEVICE_IP);
				array.put(deviceName);
				array.put(groupName);

				array.put(df.format(a[1]));
				array.put(df.format(a[2]));
				array.put(df.format(a[3]));
				arrayList.put(array);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error ");
				e.printStackTrace();
			}
		}

		return arrayList;
	}

	public JSONArray LinkAvailabilityReportView(String from_date, String to_date, List<String> ip_list) {

		JSONArray array = null;
		JSONArray arrayList = new JSONArray();

		Map<String, Map<String, String>> deviceMap = null;

		try {

			// Your updated SQL query with JOIN condition
			String query001 = "SELECT a.DEVICE_IP, a.DEVICE_NAME, a.GROUP_NAME, i.Interface_IP_Assign, i.INTERFACE_NAME "
					+ "FROM add_node a " + "JOIN interface_monitoring i ON a.DEVICE_IP = i.NODE_IP "
					+ "WHERE i.MONITORING_PARAM = 'Yes'";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];
				String interfaceIpAssign = (String) row[3];
				String interfaceName = (String) row[4];

				// Create an inner Map to store key-value pairs for deviceName and groupName
				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName); // Key as groupName and value as groupName
				innerMap.put("interfaceName", interfaceName); // Key as interfaceName and value as interfaceName
				innerMap.put("deviceip", deviceIp);

				// Store the innerMap in the main HashMap using Interface_IP_Assign as the key
				deviceMap.put(interfaceIpAssign, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "SELECT \r\n" + "    device_ip,\r\n"
				+ "     SUM(CASE WHEN status = 'Up' THEN total_duration_seconds ELSE 0 END) AS uptime_hours,\r\n"
				+ "     SUM(CASE WHEN status = 'Down' THEN total_duration_seconds ELSE 0 END) AS downtime_hours\r\n"
				+ "FROM (\r\n" + "    SELECT \r\n" + "        device_ip,\r\n" + "        status,\r\n" + " COALESCE(\r\n"
				+ "            LEAD(timestamp_epoch) OVER (\r\n"
				+ "                PARTITION BY device_ip, DATE(FROM_UNIXTIME(timestamp_epoch)) \r\n"
				+ "                ORDER BY timestamp_epoch\r\n" + "            ) - timestamp_epoch, \r\n"
				+ "            0\r\n" + "        ) AS total_duration_seconds "
				+ "    FROM  interface_status_latency_history\r\n" + "    WHERE working_hour_flag=1 AND device_ip IN ('"
				+ ip_data + "') AND timestamp BETWEEN '" + from_date + "' AND '" + to_date + "'  "
				+ ") AS durations\r\n" + "GROUP BY device_ip";
		Query q = getSession().createSQLQuery(query);
//		List<LinkAvailabilityReportBean> dataList = new ArrayList<LinkAvailabilityReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
//				LinkAvailabilityReportBean bean = new LinkAvailabilityReportBean();

				String interfaceip = a[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(interfaceip);
				String deviceName = "-";
				String groupName = "-";
				String INTERFACE_NAME = "-";
				String DEVICE_IP = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");
					INTERFACE_NAME = deviceDetails.get("interfaceName");
					DEVICE_IP = deviceDetails.get("deviceip");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

				Long uptime = Long.parseLong(a[1].toString().trim());
				Long downtime = Long.parseLong(a[2].toString().trim());

//				Long uptime = null;
//				Long downtime = null;

//				if (a[1].toString().trim().matches("\\d+\\.\\d+")) { // Check if the string is a valid decimal number
//					double value = Double.parseDouble(a[1].toString().trim());
//					uptime = Math.round(value);
//				} else {
//					// Handle invalid input
//					uptime = Long.parseLong(a[1].toString().trim());
//
//				}
//				if (a[2].toString().trim().matches("\\d+\\.\\d+")) { // Check if the string is a valid decimal number
//					double value = Double.parseDouble(a[2].toString().trim());
//					downtime = Math.round(value);
//				} else {
//					// Handle invalid input
//
//					downtime = Long.parseLong(a[2].toString().trim());
//				}

				Long totalTime = uptime + downtime;

				// Calculate Uptime (%) and Downtime (%)
				double uptimePercentage = (uptime * 100.0) / totalTime;
				double downtimePercentage = (downtime * 100.0) / totalTime;

//				struig cal

				int day = (int) TimeUnit.SECONDS.toDays(uptime);
				long hours = TimeUnit.SECONDS.toHours(uptime) - (day * 24);
				long minute = TimeUnit.SECONDS.toMinutes(uptime) - (TimeUnit.SECONDS.toHours(uptime) * 60);
				long second = TimeUnit.SECONDS.toSeconds(uptime) - (TimeUnit.SECONDS.toMinutes(uptime) * 60);
				String var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

//				int day = (int) TimeUnit.HOURS.toDays((long) uptime); // Convert hours to days
//				long hours = (long) uptime % 24; // Remaining whole hours after extracting days
//
//				// Extract the fractional part of the hour and convert to minutes and seconds
//				double fractionalHour = uptime - (long) uptime; // Fractional part (e.g., 0.77)
//				long minute = (long) (fractionalHour * 60); // Convert fraction to minutes
//				long second = (long) ((fractionalHour * 60 - minute) * 60); // Remaining fraction to seconds
//
//				String var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

				day = (int) TimeUnit.SECONDS.toDays(downtime);
				hours = TimeUnit.SECONDS.toHours(downtime) - (day * 24);
				minute = TimeUnit.SECONDS.toMinutes(downtime) - (TimeUnit.SECONDS.toHours(downtime) * 60);
				second = TimeUnit.SECONDS.toSeconds(downtime) - (TimeUnit.SECONDS.toMinutes(downtime) * 60);
				String var_Downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second
						+ " Seconds";

//				day = (int) TimeUnit.HOURS.toDays((long) downtime); // Convert hours to days
//				hours = (long) downtime % 24; // Remaining whole hours after extracting days
//
//				// Extract the fractional part of the hour and convert to minutes and seconds
//				fractionalHour = downtime - (long) downtime; // Fractional part (e.g., 0.77)
//				minute = (long) (fractionalHour * 60); // Convert fraction to minutes
//				second = (long) ((fractionalHour * 60 - minute) * 60); // Remaining fraction to seconds
//
//				String var_Downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second
//						+ " Seconds";

				DecimalFormat df2 = new DecimalFormat("0.00");

//				bean.setID(id);
//				bean.setNODE_IP(a[0].toString());
//
//				bean.setDOWNTIME(var_Downtime);
//				bean.setUPTIME(var_uptime);
//				bean.setUPTIME_PERCENT(df2.format(uptimePercentage));
//				bean.setDOWNTIME_PERCENT(df2.format(downtimePercentage));
//
//				dataList.add(bean);

				array = new JSONArray();
				array.put(id);
				array.put(a[0].toString());

				array.put(INTERFACE_NAME);
				array.put(DEVICE_IP);
				array.put(deviceName);
				array.put(groupName);

				array.put(var_uptime);
				array.put(var_Downtime);
				array.put(df2.format(uptimePercentage));
				array.put(df2.format(downtimePercentage));
				arrayList.put(array);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error ");
				e.printStackTrace();
			}
		}

		return arrayList;
	}

	public JSONArray InterfaceSlaReportView(String from_date, String to_date, String yearlyCost) {

		Map<String, Map<String, String>> deviceMap = null;

		try {

			// Your updated SQL query with JOIN condition
			String query001 = "SELECT a.DEVICE_IP, a.DEVICE_NAME, a.GROUP_NAME, i.Interface_IP_Assign, i.INTERFACE_NAME "
					+ "FROM add_node a " + "JOIN interface_monitoring i ON a.DEVICE_IP = i.NODE_IP "
					+ "WHERE i.MONITORING_PARAM = 'Yes'";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];
				String interfaceIpAssign = (String) row[3];
				String interfaceName = (String) row[4];

				// Create an inner Map to store key-value pairs for deviceName and groupName
				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName); // Key as groupName and value as groupName
				innerMap.put("interfaceName", interfaceName); // Key as interfaceName and value as interfaceName
				innerMap.put("deviceip", deviceIp);

				// Store the innerMap in the main HashMap using Interface_IP_Assign as the key
				deviceMap.put(interfaceIpAssign, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONArray array = null;
		JSONArray arrayList = new JSONArray();
		int penalty_cost_percentage = 0;
		double penalty_cost = 0;
		int sr = 0;
		String daysData = "";
		String fromTime = "";
		String toTime = "";
		String numericDaysData = "";
		String betweenData = "";

		System.out.println(yearlyCost);
		System.out.println(from_date);
		System.out.println(to_date);

		System.out.println(fromTime);
		System.out.println(toTime);

		System.out.println(numericDaysData);
		System.out.println(betweenData);

		try {
//			String query = "SELECT \r\n" + "    device_ip,\r\n"
//					+ "     SUM(CASE WHEN status = 'Up' THEN total_duration_seconds ELSE 0 END) AS uptime_seconds,\r\n"
//					+ "     SUM(CASE WHEN status = 'Down' THEN total_duration_seconds ELSE 0 END) AS downtime_seconds\r\n"
//					+ "FROM (\r\n" + "    SELECT \r\n" + "        device_ip,\r\n" + "        status,\r\n"
//					+ "        COALESCE(\r\n"
//					+ "            LEAD(timestamp_epoch) OVER (PARTITION BY device_ip ORDER BY timestamp_epoch) - timestamp_epoch, \r\n"
//					+ "            0\r\n" + "        ) AS total_duration_seconds\r\n"
//					+ "    FROM  interface_status_latency_history\r\n"
//					+ "    WHERE working_hour_flag=1 AND timestamp BETWEEN '" + from_date + "' AND '" + to_date + "'  "
//					+ ") AS durations\r\n" + "GROUP BY device_ip";

			String query = "SELECT  \r\n" + "	device_ip,\r\n"
					+ "    SUM(CASE WHEN status = 'Up' THEN total_duration_seconds ELSE 0 END)  AS uptime_hours,\r\n"
					+ "    SUM(CASE WHEN status = 'Down' THEN total_duration_seconds ELSE 0 END)  AS downtime_hours\r\n"
					+ "FROM ( \r\n" + "    SELECT     \r\n" + "        device_ip,\r\n" + "        status,\r\n"
					+ "        COALESCE(\r\n" + "            LEAD(timestamp_epoch) OVER (\r\n"
					+ "                PARTITION BY device_ip, DATE(FROM_UNIXTIME(timestamp_epoch)) \r\n"
					+ "                ORDER BY timestamp_epoch\r\n" + "            ) - timestamp_epoch, \r\n"
					+ "            0\r\n" + "        ) AS total_duration_seconds\r\n"
					+ "    FROM interface_status_latency_history\r\n" + "    WHERE working_hour_flag = 1 \r\n"
					+ "        AND timestamp BETWEEN '" + from_date + "' AND '" + to_date + "'\r\n"
					+ ") AS durations\r\n" + "GROUP BY device_ip;";

			List<Object[]> li = getSession().createSQLQuery(query).list();
			for (Object[] data : li) {
				array = new JSONArray();
				sr++;

				String interfaceip = data[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(interfaceip);
				String deviceName = "-";
				String groupName = "-";
				String INTERFACE_NAME = "-";
				String DEVICE_IP = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");
					INTERFACE_NAME = deviceDetails.get("interfaceName");
					DEVICE_IP = deviceDetails.get("deviceip");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

				array.put(sr);
				String ipaddress = data[0].toString();
				Long uptime = Long.parseLong(data[1].toString().trim());
				Long downtime = Long.parseLong(data[2].toString().trim());
//				Long uptime = null;
//				Long downtime = null;

//				if (data[1].toString().trim().matches("\\d+\\.\\d+")) { // Check if the string is a valid decimal number
//					double value = Double.parseDouble(data[1].toString().trim());
//					uptime = Math.round(value);
//				} else {
//					// Handle invalid input
//					uptime = Long.parseLong(data[1].toString().trim());
//
//				}
//				if (data[2].toString().trim().matches("\\d+\\.\\d+")) { // Check if the string is a valid decimal number
//					double value = Double.parseDouble(data[2].toString().trim());
//					downtime = Math.round(value);
//				} else {
//					// Handle invalid input
//
//					downtime = Long.parseLong(data[2].toString().trim());
//				}

				Long totalTime = uptime + downtime;

				// Calculate Uptime (%) and Downtime (%)
				double uptimePercentage = (uptime * 100.0) / totalTime;
				double downtimePercentage = (downtime * 100.0) / totalTime;

//				struig cal

				int day = (int) TimeUnit.SECONDS.toDays(uptime);
				long hours = TimeUnit.SECONDS.toHours(uptime) - (day * 24);
				long minute = TimeUnit.SECONDS.toMinutes(uptime) - (TimeUnit.SECONDS.toHours(uptime) * 60);
				long second = TimeUnit.SECONDS.toSeconds(uptime) - (TimeUnit.SECONDS.toMinutes(uptime) * 60);
				String var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

//				int day = (int) TimeUnit.HOURS.toDays((long) uptime); // Convert hours to days
//				long hours = (long) uptime % 24; // Remaining whole hours after extracting days
//
//				// Extract the fractional part of the hour and convert to minutes and seconds
//				double fractionalHour = uptime - (long) uptime; // Fractional part (e.g., 0.77)
//				long minute = (long) (fractionalHour * 60); // Convert fraction to minutes
//				long second = (long) ((fractionalHour * 60 - minute) * 60); // Remaining fraction to seconds
//
//				String var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

				day = (int) TimeUnit.SECONDS.toDays(downtime);
				hours = TimeUnit.SECONDS.toHours(downtime) - (day * 24);
				minute = TimeUnit.SECONDS.toMinutes(downtime) - (TimeUnit.SECONDS.toHours(downtime) * 60);
				second = TimeUnit.SECONDS.toSeconds(downtime) - (TimeUnit.SECONDS.toMinutes(downtime) * 60);
				String var_Downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second
						+ " Seconds";

//				day = (int) TimeUnit.HOURS.toDays((long) downtime); // Convert hours to days
//				hours = (long) downtime % 24; // Remaining whole hours after extracting days
//
//				// Extract the fractional part of the hour and convert to minutes and seconds
//				fractionalHour = downtime - (long) downtime; // Fractional part (e.g., 0.77)
//				minute = (long) (fractionalHour * 60); // Convert fraction to minutes
//				second = (long) ((fractionalHour * 60 - minute) * 60); // Remaining fraction to seconds
//
//				String var_Downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second
//						+ " Seconds";

				DecimalFormat df = new DecimalFormat("0.00");

				double UptimeTotalhours = (double) uptime;
				double DowntimeTotalhours = (double) downtime;

				String formattedUptimehours = String.format("%.2f", UptimeTotalhours);
				String formattedDowntimehours = String.format("%.2f", DowntimeTotalhours);
				// Parse the formatted strings back to doubles for calculation
				double formattedUptime22 = Double.parseDouble(formattedUptimehours);
				double formattedDowntime22 = Double.parseDouble(formattedDowntimehours);

				// Calculate the total
				double totalHours = formattedUptime22 + formattedDowntime22;

				array.put(data[0]);

				array.put(INTERFACE_NAME);
				array.put(DEVICE_IP);
				array.put(deviceName);
				array.put(groupName);

				array.put(var_uptime);
				array.put(var_Downtime);

				array.put(formattedUptimehours);
				array.put(formattedDowntimehours);
				array.put(String.format("%.2f", totalHours));

				array.put(df.format(uptimePercentage));
				array.put(df.format(downtimePercentage));

				if (uptimePercentage >= 99) {
					penalty_cost_percentage = 0;
				} else if (uptimePercentage >= 98.5 && uptimePercentage < 99) {
					penalty_cost_percentage = 10;
				} else if (uptimePercentage >= 97.5 && uptimePercentage < 98.5) {
					penalty_cost_percentage = 20;
				} else if (uptimePercentage >= 96.5 && uptimePercentage < 97.5) {
					penalty_cost_percentage = 25;
				} else if (uptimePercentage < 96.5) {
					penalty_cost_percentage = 30;
				} else if (uptimePercentage < 96.5) {
					penalty_cost_percentage = -1;
				}

				penalty_cost = (Double.valueOf(yearlyCost) * penalty_cost_percentage) / 100;
				array.put(Double.valueOf(yearlyCost));
				array.put(penalty_cost_percentage);
				array.put(penalty_cost);
				array.put(Double.valueOf(yearlyCost) - penalty_cost);

				arrayList.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList);
		return arrayList;
	}

	public JSONArray LinkBandwidthHistoryReportView(String from_date, String to_date, List<String> ip_list) {

		JSONArray array = null;
		JSONArray arrayList = new JSONArray();

		Map<String, Map<String, String>> deviceMap = null;

		try {

			String query001 = "SELECT device_ip, device_name, group_name FROM add_node ";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];

				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName);
				deviceMap.put(deviceIp, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "SELECT \r\n" + "    id, \r\n" + "    device_ip, \r\n" + "    interface_name, \r\n"
				+ "    interface_ip, \r\n" + "    interface_status, \r\n" + "    in_traffic, \r\n"
				+ "    out_traffic, \r\n" + "    working_hour_flag, \r\n" + "    timestamp, \r\n"
				+ "    timestamp_epoch, \r\n" + "    ip_interface_name\r\n" + "FROM \r\n"
				+ "    interface_bw_history_status\r\n" + "WHERE \r\n" + "    interface_ip IN ('" + ip_data + "') \r\n"
				+ "    AND timestamp BETWEEN '" + from_date + "' AND '" + to_date + "'" + " AND working_hour_flag=1";
		Query q = getSession().createSQLQuery(query);
//		List<LinkBandwidthHistoryReporttBean> dataList = new ArrayList<LinkBandwidthHistoryReporttBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		for (Object[] a : data) {
			try {

				id++;
//				LinkBandwidthHistoryReporttBean bean = new LinkBandwidthHistoryReporttBean();
//				bean.setID(id);
//				bean.setDEVICE_IP(a[1].toString());
//				bean.setINTERFACE_NAME(a[2].toString());
//				bean.setINTERFACE_IP(a[3].toString());
//				bean.setINTERFACE_STATUS(a[4].toString());
//				bean.setIN_TRAFFIC(a[5].toString());
//				bean.setOUT_TRAFFIC(a[6].toString());
//				bean.setWORKING_HOUR_FLAG(a[7].toString());
//				bean.setTIMESTAMP(a[8].toString());
//				bean.setTIMESTAMP_EPOCH(a[9].toString());
//				bean.setIP_INTERFACE_NAME(a[10].toString());

				String ip = a[1].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(ip);
				String deviceName = "-";
				String groupName = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

				array = new JSONArray();
				array.put(id);
				array.put(a[3].toString());
				array.put(a[2].toString());
				array.put(a[1].toString());
				array.put(deviceName);
				array.put(groupName);

				array.put(a[4].toString());
				array.put(String.format("%.2f", (Double.valueOf(a[5].toString().trim()) / 1024)));
				array.put(String.format("%.2f", (Double.valueOf(a[6].toString().trim()) / 1024)));
//				array.put(a[7].toString());
				array.put(a[8].toString());
//				array.put(a[9].toString());
//				array.put(a[10].toString());
				arrayList.put(array);

//				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error ");
				e.printStackTrace();
			}
		}

		return arrayList;

	}

	public JSONArray LinkAVGBandwidthReportView(String from_date, String to_date, List<String> ip_list) {

		JSONArray array = null;
		JSONArray arrayList = new JSONArray();

		Map<String, Map<String, String>> deviceMap = null;

		try {

			// Your updated SQL query with JOIN condition
			String query001 = "SELECT a.DEVICE_IP, a.DEVICE_NAME, a.GROUP_NAME, i.Interface_IP_Assign, i.INTERFACE_NAME "
					+ "FROM add_node a " + "JOIN interface_monitoring i ON a.DEVICE_IP = i.NODE_IP "
					+ "WHERE i.MONITORING_PARAM = 'Yes'";

			System.out.println("Query: " + query001);

			// Execute the query.
			Query q001 = getSession().createSQLQuery(query001);

			// Fetch the results as a list of Object arrays.
			List<Object[]> results = q001.list();

			// Create a HashMap to store the results.
			deviceMap = new HashMap<String, Map<String, String>>();

			// Iterate through the results and populate the HashMap.
			for (Object[] row : results) {
				String deviceIp = (String) row[0];
				String deviceName = (String) row[1];
				String groupName = (String) row[2];
				String interfaceIpAssign = (String) row[3];
				String interfaceName = (String) row[4];

				// Create an inner Map to store key-value pairs for deviceName and groupName
				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("deviceName", deviceName); // Key as deviceName and value as deviceName
				innerMap.put("groupName", groupName); // Key as groupName and value as groupName
				innerMap.put("interfaceName", interfaceName); // Key as interfaceName and value as interfaceName
				innerMap.put("deviceip", deviceIp);

				// Store the innerMap in the main HashMap using Interface_IP_Assign as the key
				deviceMap.put(interfaceIpAssign, innerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "SELECT \r\n" + "    interface_ip,\r\n" + "    AVG(in_traffic) AS avg_in_traffic,\r\n"
				+ "    AVG(out_traffic) AS avg_out_traffic\r\n" + "FROM \r\n" + "  interface_bw_history_status\r\n"
				+ "WHERE \r\n" + "   interface_ip IN ('" + ip_data + "') \r\n"
				+ "    AND timestamp_epoch BETWEEN UNIX_TIMESTAMP('" + from_date + "') \r\n"
				+ "                        AND UNIX_TIMESTAMP('" + to_date + "') " + " AND working_hour_flag=1 "
				+ " GROUP BY  interface_ip ";
		Query q = getSession().createSQLQuery(query);
//		List<LinkAVGBandwidthReportBean> dataList = new ArrayList<LinkAVGBandwidthReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		for (Object[] a : data) {
			try {

				id++;
//				LinkAVGBandwidthReportBean bean = new LinkAVGBandwidthReportBean();
//				bean.setID(id);
//				bean.setNODE_IP(a[0].toString());
//				bean.setAVG_IN_TRAFIC(df.format(a[1]));
//				bean.setAVG_OUT_TRAFIC(df.format(a[2]));
//
//				dataList.add(bean);

				String interfaceip = a[0].toString().trim();
				Map<String, String> deviceDetails = deviceMap.get(interfaceip);
				String deviceName = "-";
				String groupName = "-";
				String INTERFACE_NAME = "-";
				String DEVICE_IP = "-";

				if (deviceDetails != null) {
					deviceName = deviceDetails.get("deviceName");
					groupName = deviceDetails.get("groupName");
					INTERFACE_NAME = deviceDetails.get("interfaceName");
					DEVICE_IP = deviceDetails.get("deviceip");

//		            // Print or process the retrieved details.
//		            System.out.println("IP: " + ip + ", Device Name: " + deviceName + ", Group Name: " + groupName);
				}

				array = new JSONArray();
				array.put(id);
				array.put(a[0].toString());
				array.put(INTERFACE_NAME);
				array.put(DEVICE_IP);
				array.put(deviceName);
				array.put(groupName);
				array.put(String.format("%.2f", (Double.valueOf(a[1].toString().trim()) / 1024)));
				array.put(String.format("%.2f", (Double.valueOf(a[2].toString().trim()) / 1024)));
//				array.put(df.format(a[1]));
//				array.put(df.format(a[2]));
				arrayList.put(array);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error ");
				e.printStackTrace();
			}
		}

		return arrayList;

	}

}
