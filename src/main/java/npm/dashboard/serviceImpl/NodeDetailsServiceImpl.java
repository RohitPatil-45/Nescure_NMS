package npm.dashboard.serviceImpl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.dashboard.dao.NodeDetailsDao;
import npm.dashboard.service.NodeDetailsService;

@Service
public class NodeDetailsServiceImpl implements NodeDetailsService {

	@Autowired
	NodeDetailsDao dao;

	public JSONObject nodeLatencyPacketDrop(String ip_address) {

		return dao.nodeLatencyPacketDrop(ip_address);
	}

	public JSONObject nodeAddNode(String ip_address) {

		return dao.nodeAddNode(ip_address);
	}

	public JSONObject nodeAvailabilityDetails(String ip_address) {

		return dao.nodeAvailabilityDetails(ip_address);
	}

	public JSONArray nodeStatusHistoryDetails(String ip_address) {

		return dao.nodeStatusHistoryDetails(ip_address);
	}

	public JSONObject nodeHealthMonitoringDetails(String ip_address) {

		return dao.nodeHealthMonitoringDetails(ip_address);
	}

	public JSONArray InterfaceStatusHistoryDetails(String ip_address) {
		// TODO Auto-generated method stub
		return dao.InterfaceStatusHistoryDetails(ip_address);
	}

	public JSONArray basicInfoDetails(String ip_address) {
		// TODO Auto-generated method stub
		return dao.basicInfoDetails(ip_address);
	}

	public JSONArray nodeUpTimeStatusDetails(String ip_address) {
		// TODO Auto-generated method stub
		return dao.nodeUpTimeStatusDetails(ip_address);
	}

	public JSONArray nodeLatencyStatusDetails(String ip_address) {
		// TODO Auto-generated method stub
		return dao.nodeLatencyStatusDetails(ip_address);
	}

	// CPU AND MEMORY UTILIZATION
	public JSONArray cpuAndMemoryUtilization(String ip_address) {
		// TODO Auto-generated method stub
		return dao.cpuAndMemoryUtilization(ip_address);
	}

	public String getDBVerification(String username, String Password) {
		// TODO Auto-generated method stub
		return dao.getDBVerification(username, Password);

	}

	// Auto Topology
	public JSONArray getAutoNetworkTopology(String username, String contextPath) {
		// TODO Auto-generated method stub
		return dao.getAutoNetworkTopology(username, contextPath);
	}

	// Auto Topology History Data
	public JSONArray autoTopologyHistoryData(String from_date, String to_date) {
		// TODO Auto-generated method stub
		return dao.autoTopologyHistoryData(from_date, to_date);
	}

	public JSONArray viewAutoTopologyHistoryOnID(String poolingID) {
		// TODO Auto-generated method stub
		return dao.viewAutoTopologyHistoryOnID(poolingID);

	}

	public String getAdminScope(String username) {
		// TODO Auto-generated method stub
		return dao.getAdminScope(username);
	}

	public String getDashoardScope(String userName) {
		return dao.getDashoardScope(userName);
	}

	public String getReportScope(String userName) {
		return dao.getReportScope(userName);
	}

	public String getGraphScope(String userName) {
		return dao.getGraphScope(userName);
	}

	public String getUserScope(String userName) {
		return dao.getUserScope(userName);
	}

	public String saveuserloginhistory(String username, String serverHostname, String clientIp,
			String controller_response) {
		return dao.saveuserloginhistory(username, serverHostname, clientIp, controller_response);
		
	}

}
