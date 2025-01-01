package npm.report.serviceImpl;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.admin.beans.CpuThresholdHealthReportBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.beans.LatencyThresholdReportBean;
import npm.admin.beans.LinkAVGBandwidthReportBean;
import npm.admin.beans.LinkAvailabilityReportBean;
import npm.admin.beans.LinkBandwidthHistoryReporttBean;
import npm.admin.beans.LinkLatencyReportBean;
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
import npm.report.dao.NodeReportDao;
import npm.report.service.NodeReportService;

@Service
public class NodeReportServiceImpl implements NodeReportService {

	@Autowired
	NodeReportDao dao;

	public List<NodeStatusReportBean> NodeStatusReport(String from_date, String to_date, List<String> ip_list) {

		return dao.NodeStatusReport(from_date, to_date, ip_list);

	}

	public List<LatencyHistoryReportBean> latencyHistoryReport(String from_date, String to_date, List<String> ip_list) {

		return dao.latencyHistoryReport(from_date, to_date, ip_list);

	}

	public List<LatencyThresholdReportBean> latencyThresholdReport(String from_date, String to_date,
			List<String> ip_list) {

		return dao.latencyThresholdReport(from_date, to_date, ip_list);

	}

	public List<NodeAvailabilityBean> nodeAvailabilityReport(String from_date, String to_date, List<String> ip_list) {

		return dao.nodeAvailabilityReport(from_date, to_date, ip_list);

	}

	public List<NodeHealthHistoryReportBean> nodeHealthHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		return dao.nodeHealthHistoryReport(from_date, to_date, ip_list);

	}

	public List<CpuThresholdHealthReportBean> cpuThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		return dao.cpuThresholdHistoryReport(from_date, to_date, ip_list);

	}

	public List<MemoryThresholdReportBean> memoryThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		return dao.memoryThresholdHistoryReport(from_date, to_date, ip_list);

	}

	public List<NodeHealthMonitoring> nodeHealthMonitoringView() {

		return dao.nodeHealthMonitoringView();
	}

	public List<NodeMonitoringModel> nodeMonitoringView() {

		return dao.nodeMonitoringView();
	}

	public JSONArray interfaceMonitoringView(String userScopeData) {

		return dao.interfaceMonitoringView(userScopeData);
	}

	public JSONObject latencyHistoricalData(String from_date, String to_date, String ip_address) {

		return dao.latencyHistoricalData(from_date, to_date, ip_address);

	}

	public JSONObject plotLatencyGraph(String from_date, String to_date, String ip_address) {

		return dao.plotLatencyGraph(from_date, to_date, ip_address);

	}

	public JSONObject interfaceBWHistoryData(String from_date, String to_date, String ip_address,
			String interface_name) {

		return dao.interfaceBWHistoryData(from_date, to_date, ip_address, interface_name);

	}

	public JSONObject nodeHealthHistoryData(String from_date, String to_date, String ip_address, String userScopeData) {

		return dao.nodeHealthHistoryData(from_date, to_date, ip_address, userScopeData);

	}

	public JSONObject nodeAvailabilityData(String from_date, String to_date, String ip_address) {

		return dao.nodeAvailabilityData(from_date, to_date, ip_address);

	}

	public JSONObject interfaceAvailabilityData(String from_date, String to_date, String ip_address,
			String interface_name) {

		return dao.interfaceAvailabilityData(from_date, to_date, ip_address, interface_name);

	}

	public String getIPAddress() {
		// TODO Auto-generated method stub
		return dao.getIPAddress();
	}

	public String getInterfaceOnIPAddress(String ipAddress) {
		// TODO Auto-generated method stub
		return dao.getInterfaceOnIPAddress(ipAddress);
	}

	public String SaveNodePositions(String fromTo, String positions, String ipAddress, String username) {
		// TODO Auto-generated method stub
		return dao.SaveNodePositions(fromTo, positions, ipAddress, username);
	}

	public String NetworkTopologyEditMode(String grpName) {
		// TODO Auto-generated method stub
		return dao.NetworkTopologyEditMode(grpName);
	}

	public String NetworkTopologyInsertInterface(String fromIpAddress, String toIpAddress, String fromInterface,
			String toInterface) {
		// TODO Auto-generated method stub
		return dao.NetworkTopologyInsertInterface(fromIpAddress, toIpAddress, fromInterface, toInterface);
	}

	public List<DeviceAlertLog> viewDeviceAlertLog() {
		// TODO Auto-generated method stub
		return dao.viewDeviceAlertLog();
	}

	public JSONArray getSlaReport(String from_date, String to_date, String yearlyCost) {
		// TODO Auto-generated method stub
		return dao.getSlaReport(from_date, to_date, yearlyCost);
	}

	public JSONArray getAvgUptimeReport(String from_date, String to_date) {
		// TODO Auto-generated method stub
		return dao.getAvgUptimeReport(from_date, to_date);
	}

	public JSONArray getConnectedDevices() {
		// TODO Auto-gen.erated method stub
		return dao.getConnectedDevices();
	}

	public JSONArray connectedDevicesforIP(String ipAddress) {
		// TODO Auto-generated method stub
		return dao.connectedDevicesforIP(ipAddress);
	}

	public JSONArray getAverageAvailabilityReport(String from_date, String to_date) {
		// TODO Auto-generated method stub
		return dao.getAverageAvailabilityReport(from_date, to_date);
	}

	public JSONArray getAvailabilityReportDateWise(String from_date, String to_date) {
		// TODO Auto-generated method stub
		return dao.getAvailabilityReportDateWise(from_date, to_date);
	}

	public JSONArray avarageLatencyReport(String from_date, String to_date, List<String> list, String userScopeData) {
		return dao.avarageLatencyReport(from_date, to_date, list, userScopeData);
	}

	public JSONArray averageHealthReport(String from_date, String to_date, List<String> list, String userScopeData) {
		return dao.averageHealthReport(from_date, to_date, list, userScopeData);
	}

	public JSONArray nodeAvailabilityAverageGraph(String from_date, String to_date, List<String> ip_address) {
		return dao.nodeAvailabilityAverageGraph(from_date, to_date, ip_address);
	}

	public JSONArray interfaceAvailabilityAverageGraph(String from_date, String to_date, List<String> ip_address) {
		return dao.interfaceAvailabilityAverageGraph(from_date, to_date, ip_address);
	}

	public List<LatencyHistoryReportBean> showLatencyReport(String ip_address, String fromDate, String toDate) {
		return dao.showLatencyReport(ip_address, fromDate, toDate);
	}

	public List<NodeStatusReportBean> showNodeStatusReport(String fromDate, String toDate, String ip_address) {
		return dao.showNodeStatusReport(ip_address, fromDate, toDate);
	}

	public List<LatencyThresholdReportBean> showLatencyThreshold(String fromDate, String toDate, String ip_address) {
		return dao.showLatencyThreshold(ip_address, fromDate, toDate);
	}

	public List<NodeAvailabilityBean> showAvailabilityReport(String fromDate, String toDate, String ip_address) {
		return dao.showAvailabilityReport(ip_address, fromDate, toDate);
	}

	public List<CpuThresholdHealthReportBean> showCPUThreshold(String fromDate, String toDate, String ip_address) {
		return dao.showCPUThreshold(ip_address, fromDate, toDate);
	}

	public List<MemoryThresholdReportBean> showMemoryThreshold(String fromDate, String toDate, String ip_address) {
		return dao.showMemoryThreshold(ip_address, fromDate, toDate);
	}

	public JSONObject showAvailabilityGraph(String fromDate, String toDate, String ip_address) {
		return dao.showAvailabilityGraph(ip_address, fromDate, toDate);
	}

	public JSONObject showLatencyGraph(String fromDate, String toDate, String ip_address) {
		return dao.showLatencyGraph(ip_address, fromDate, toDate);
	}

	public List<NodeHealthHistoryReportBean> showCPUReport(String fromDate, String toDate, String ip_address) {
		return dao.showCPUReport(ip_address, fromDate, toDate);
	}

	public List<NodeHealthHistoryReportBean> showMemoryReport(String fromDate, String toDate, String ip_address) {
		return dao.showMemoryReport(ip_address, fromDate, toDate);
	}

	public JSONArray showMemoryGraph(String fromDate, String toDate, String ip_address) {
		return dao.showMemoryGraph(ip_address, fromDate, toDate);
	}

	public JSONArray showCPUGraph(String fromDate, String toDate, String ip_address) {
		return dao.showCPUGraph(ip_address, fromDate, toDate);
	}

	public JSONObject interfaceBandwidthGraph(String from_date, String to_date, String ip_address,
			String interfaceName) {
		return dao.interfaceBandwidthGraph(from_date, to_date, ip_address, interfaceName);
	}

	public JSONObject interfaceUptimeGraph(String from_date, String to_date, String ip_address, String interfaceName) {
		return dao.interfaceUptimeGraph(from_date, to_date, ip_address, interfaceName);
	}

	public JSONArray getWindowsEventReport(String from_date, String to_date, List<String> list) {
		// TODO Auto-generated method stub
		return dao.getWindowsEventReport(from_date, to_date, list);
	}

	public JSONArray getDriveThresholdReport(String from_date, String to_date, List<String> list) {
		// TODO Auto-generated method stub
		return dao.getDriveThresholdReport(from_date, to_date, list);
	}

	public JSONArray getServiceReport(String from_date, String to_date, List<String> list) {
		// TODO Auto-generated method stub
		return dao.getServiceReport(from_date, to_date, list);
	}

	public List<Hardware_Inventory> windowsHardwareInventory() {
		// TODO Auto-generated method stub
		return dao.windowsHardwareInventory();
	}

	public JSONArray linuxHardwareInventory() {
		// TODO Auto-generated method stub
		return dao.linuxHardwareInventory();
	}

	public JSONArray linuxSoftwareInventory() {
		// TODO Auto-generated method stub
		return dao.linuxSoftwareInventory();
	}

	public JSONArray linuxServices() {
		// TODO Auto-generated method stub
		return dao.linuxServices();
	}

	public JSONArray windowsServices() {
		// TODO Auto-generated method stub
		return dao.windowsServices();
	}

	public String interfaceipassignhere(String interface_ip, String ip_address, String interface_name) {
		// TODO Auto-generated method stub
		return dao.interfaceipassignhereDao(interface_ip, ip_address, interface_name);
	}

	public JSONArray getinterfaceassigndata() {
		// TODO Auto-generated method stub
		return dao.getinterfaceassigndata();
	}

	public List<UserLoginHistoryBean> userLogReportData(String from_date, String to_date) {
		// TODO Auto-generated method stub
		return dao.userLogReportData(from_date, to_date);
	}

	public Map<String, String> getNodeIP() {
		// TODO Auto-generated method stub
		return dao.getNodeIP();
	}

	public JSONArray getSyslogReport(String from_date, String to_date, String ip) {
		// TODO Auto-generated method stub
		return dao.getSyslogReport(from_date, to_date, ip);
	}

	public JSONArray latencyStatusHistoryReport(String from_date, String to_date, List<String> list) {
		// TODO Auto-generated method stub
		return dao.latencyStatusHistoryReport(from_date, to_date, list);
	}

	public JSONArray getWorkingHoursAvailabilityReport(String from_date, String to_date) {
		// TODO Auto-generated method stub
		return dao.getWorkingHoursAvailabilityReport(from_date, to_date);
	}

	public JSONArray slaReportData(String from_date, String to_date, String yearlyCost) {
		// TODO Auto-generated method stub
		return dao.slaReportData(from_date, to_date, yearlyCost);
	}

	public JSONArray avgLatencyStatusHistoryReport(String from_date, String to_date,String Status) {
		// TODO Auto-generated method stub
		return dao.avgLatencyStatusHistoryReport(from_date, to_date,Status);
	}

	public JSONArray LinkLatencystatusreport(String from_date, String to_date, List<String> list) {
		// TODO Auto-generated method stub
		return dao.LinkLatencystatusreport(from_date, to_date, list);
	}

	public JSONArray LinkLatencyReportView(String from_date, String to_date, List<String> list) {
		// TODO Auto-generated method stub
		return dao.LinkLatencyReportView(from_date, to_date, list);
	}

	public JSONArray LinkaverageLatencyReportView(String from_date, String to_date, List<String> list,String status) {
		// TODO Auto-generated method stub
		return dao.LinkaverageLatencyReportView(from_date, to_date, list,status);
	}

	public JSONArray LinkAvailabilityReportView(String from_date, String to_date, List<String> list) {
		// TODO Auto-generated method stub
		return dao.LinkAvailabilityReportView(from_date, to_date, list);
	}

	public JSONArray InterfaceSlaReportView(String from_date, String to_date, String yearlyCost) {
		// TODO Auto-generated method stub
		return dao.InterfaceSlaReportView(from_date, to_date, yearlyCost);
	}

	public JSONArray LinkBandwidthHistoryReportView(String from_date, String to_date, List<String> list) {
		// TODO Auto-generated method stub
		return dao.LinkBandwidthHistoryReportView(from_date, to_date, list);
	}

	public JSONArray LinkAVGBandwidthReportView(String from_date, String to_date, List<String> list) {
		// TODO Auto-generated method stub
		return dao.LinkAVGBandwidthReportView(from_date, to_date, list);
	}

	public JSONArray latencyStatusHistoryReportFilter(String from_date, String to_date, List<String> list,
			String Status) {
		// TODO Auto-generated method stub
		return dao.latencyStatusHistoryReportFilter(from_date, to_date, list, Status);
	}

	public JSONArray LinkLatencyReportViewFilter(String from_date, String to_date, List<String> list, String status) {
		// TODO Auto-generated method stub
		return dao.LinkLatencyReportViewFilter(from_date, to_date, list, status);
	}
}
