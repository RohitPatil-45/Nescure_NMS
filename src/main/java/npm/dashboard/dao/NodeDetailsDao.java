package npm.dashboard.dao;

import org.json.JSONArray;
import org.json.JSONObject;

public interface NodeDetailsDao {
	JSONObject nodeLatencyPacketDrop(String ip_address);
	
	JSONObject nodeAddNode(String ip_address);
	
	JSONObject nodeAvailabilityDetails(String ip_address);
	
	JSONArray nodeStatusHistoryDetails(String ip_address);
	
	JSONObject nodeHealthMonitoringDetails(String ip_address);

	JSONArray InterfaceStatusHistoryDetails(String ip_address);

	JSONArray basicInfoDetails(String ip_address);

	JSONArray nodeUpTimeStatusDetails(String ip_address);

	JSONArray nodeLatencyStatusDetails(String ip_address);

	String getDBVerification(String username, String Password);
	
	public JSONArray getAutoNetworkTopology(String username, String contextPath);

	JSONArray autoTopologyHistoryData(String from_date, String to_date);

	JSONArray viewAutoTopologyHistoryOnID(String poolingID);
	
	JSONArray cpuAndMemoryUtilization(String ip_address);

	//Manage User Scope Functionality By Rohit
	String getAdminScope(String username);
	//Manage User Scope Functionality

	String getDashoardScope(String userName);

	String getReportScope(String userName);

	String getGraphScope(String userName);

	String getUserScope(String userName);

	String saveuserloginhistory(String username, String serverHostname, String clientIp, String controller_response);
	
	

}
