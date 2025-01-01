package npm.report.service;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import npm.admin.beans.CpuThresholdHealthReportBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.beans.LatencyThresholdReportBean;
import npm.admin.beans.LinkAVGBandwidthReportBean;
import npm.admin.beans.LinkLatencystatusreportBean;
import npm.admin.beans.LinkaverageLatencyReportBean;
import npm.admin.beans.MemoryThresholdReportBean;
import npm.admin.beans.NodeAvailabilityBean;
import npm.admin.beans.NodeHealthHistoryReportBean;
import npm.admin.beans.NodeStatusReportBean;
import npm.admin.beans.UserLoginHistoryBean;
import npm.model.DeviceAlertLog;
import npm.model.Hardware_Inventory;
import npm.model.InterfaceMonitoring;
import npm.model.NodeAvailablity;
import npm.model.NodeHealthHistory;
import npm.model.NodeHealthMonitoring;
import npm.model.NodeMonitoringModel;
import npm.model.NodeStatusHistory;
import npm.admin.beans.LinkAvailabilityReportBean;
import npm.admin.beans.LinkBandwidthHistoryReporttBean;
import npm.admin.beans.LinkLatencyReportBean;

public interface NodeReportService {

	List<NodeStatusReportBean> NodeStatusReport(String from_date, String to_date, List<String> ip_list);

	List<LatencyHistoryReportBean> latencyHistoryReport(String from_date, String to_date, List<String> ip_list);

	List<LatencyThresholdReportBean> latencyThresholdReport(String from_date, String to_date, List<String> ip_list);

	List<NodeAvailabilityBean> nodeAvailabilityReport(String from_date, String to_date, List<String> ip_list);

	List<NodeHealthHistoryReportBean> nodeHealthHistoryReport(String from_date, String to_date, List<String> ip_list);

	List<CpuThresholdHealthReportBean> cpuThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list);

	List<MemoryThresholdReportBean> memoryThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list);

	List<NodeHealthMonitoring> nodeHealthMonitoringView();

	List<NodeMonitoringModel> nodeMonitoringView();

	JSONArray interfaceMonitoringView(String userScopeData);

	JSONObject latencyHistoricalData(String from_date, String to_date, String ip_address);

	JSONObject plotLatencyGraph(String from_date, String to_date, String ip_address);

	JSONObject interfaceBWHistoryData(String from_date, String to_date, String ip_address, String interface_name);

	JSONObject nodeHealthHistoryData(String from_date, String to_date, String ip_address, String userScopeData);

	JSONObject nodeAvailabilityData(String from_date, String to_date, String ip_address);

	JSONObject interfaceAvailabilityData(String from_date, String to_date, String ip_address, String interface_name);

	String getIPAddress();

	String getInterfaceOnIPAddress(String ipAddress);

	String SaveNodePositions(String fromTo, String positions, String ipAddress, String username);

	String NetworkTopologyEditMode(String grpName);

	String NetworkTopologyInsertInterface(String grpName, String toIpAddress, String fromInterface, String toInterface);

	List<DeviceAlertLog> viewDeviceAlertLog();

	JSONArray getSlaReport(String from_date, String to_date, String yearlyCost);

	JSONArray getAvgUptimeReport(String from_date, String to_date);

	JSONArray getConnectedDevices();

	JSONArray connectedDevicesforIP(String ipAddress);

	JSONArray getAverageAvailabilityReport(String from_date, String to_date);

	JSONArray getAvailabilityReportDateWise(String from_date, String to_date);

	JSONArray avarageLatencyReport(String from_date, String to_date, List<String> list, String userScopeData);

	JSONArray averageHealthReport(String from_date, String to_date, List<String> list, String userScopeData);

	JSONArray nodeAvailabilityAverageGraph(String from_date, String to_date, List<String> ip_address);

	JSONArray interfaceAvailabilityAverageGraph(String from_date, String to_date, List<String> ip_address);

	List<LatencyHistoryReportBean> showLatencyReport(String ip_address, String fromDate, String toDate);

	List<NodeStatusReportBean> showNodeStatusReport(String fromDate, String toDate, String ip_address);

	List<LatencyThresholdReportBean> showLatencyThreshold(String fromDate, String toDate, String ip_address);

	List<NodeAvailabilityBean> showAvailabilityReport(String fromDate, String toDate, String ip_address);

	List<CpuThresholdHealthReportBean> showCPUThreshold(String fromDate, String toDate, String ip_address);

	List<MemoryThresholdReportBean> showMemoryThreshold(String fromDate, String toDate, String ip_address);

	JSONObject showAvailabilityGraph(String fromDate, String toDate, String ip_address);

	JSONObject showLatencyGraph(String fromDate, String toDate, String ip_address);

	List<NodeHealthHistoryReportBean> showCPUReport(String fromDate, String toDate, String ip_address);

	List<NodeHealthHistoryReportBean> showMemoryReport(String fromDate, String toDate, String ip_address);

	JSONArray showMemoryGraph(String fromDate, String toDate, String ip_address);

	JSONArray showCPUGraph(String fromDate, String toDate, String ip_address);

	JSONObject interfaceBandwidthGraph(String from_date, String to_date, String ip_address, String interfaceName);

	JSONObject interfaceUptimeGraph(String from_date, String to_date, String ip_address, String interfaceName);

	JSONArray getWindowsEventReport(String from_date, String to_date, List<String> list);

	JSONArray getDriveThresholdReport(String from_date, String to_date, List<String> list);

	JSONArray getServiceReport(String from_date, String to_date, List<String> list);

	List<Hardware_Inventory> windowsHardwareInventory();

	JSONArray linuxHardwareInventory();

	JSONArray linuxSoftwareInventory();

	JSONArray linuxServices();

	JSONArray windowsServices();

	String interfaceipassignhere(String interface_ip, String ip_address, String interface_name);

	JSONArray getinterfaceassigndata();

	List<UserLoginHistoryBean> userLogReportData(String from_date, String to_date);

	Map<String, String> getNodeIP();

	JSONArray getSyslogReport(String from_date, String to_date, String ip);

	JSONArray latencyStatusHistoryReport(String from_date, String to_date, List<String> list);

	JSONArray getWorkingHoursAvailabilityReport(String from_date, String to_date);

	JSONArray slaReportData(String from_date, String to_date, String yearlyCost);

	JSONArray avgLatencyStatusHistoryReport(String from_date, String to_date, String status);

	JSONArray LinkLatencystatusreport(String from_date, String to_date, List<String> list);

	JSONArray LinkLatencyReportView(String from_date, String to_date, List<String> list);

	JSONArray LinkaverageLatencyReportView(String from_date, String to_date, List<String> list, String status);

	JSONArray LinkAvailabilityReportView(String from_date, String to_date, List<String> list);

	JSONArray InterfaceSlaReportView(String from_date, String to_date, String yearlyCost);

	JSONArray LinkBandwidthHistoryReportView(String from_date, String to_date, List<String> list);

	JSONArray LinkAVGBandwidthReportView(String from_date, String to_date, List<String> list);

	JSONArray latencyStatusHistoryReportFilter(String from_date, String to_date, List<String> list, String status);

	JSONArray LinkLatencyReportViewFilter(String from_date, String to_date, List<String> list, String status);
}
