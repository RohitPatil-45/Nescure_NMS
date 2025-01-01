package npm.dashboard.serviceImpl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.admin.beans.processList;

import npm.admin.beans.AgentInstalledDeviceBean;

import npm.admin.beans.LatencyHistoryReportBean;
import npm.dashboard.dao.DashboardDao;
import npm.dashboard.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	DashboardDao dao;

	/* Get Device Summary Count */
	public JSONArray deviceSummaryCount(String userScopeData) {

		JSONArray jsonarray_dao_output = dao.deviceSummaryCount(userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Link Summary Count */
	public JSONArray linkSummaryCount(String userScopeData) {

		JSONArray jsonarray_dao_output = dao.linkSummaryCount(userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Down Devices Summary Listing */
	public JSONArray deviceSummaryList(String userScopeData) {

		JSONArray jsonarray_dao_output = dao.deviceSummaryList(userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Down Link Summary Listing */
	public JSONArray linkSummaryList(String userScopeData) {

		JSONArray jsonarray_dao_output = dao.linkSummaryList(userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Device Info */
	public JSONArray getDeviceInfo(String deviceInfo, String userScopeData) {

		JSONArray jsonarray_dao_output = dao.getDeviceInfo(deviceInfo, userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Device Info */
	public JSONArray getLinkInfo(String linkInfo, String userScopeData) {

		JSONArray jsonarray_dao_output = dao.getLinkInfo(linkInfo, userScopeData);

		return jsonarray_dao_output;
	}

	/* Top CPU Summary Listing */
	public JSONArray topCPUSummary(String userScopeData) {

		JSONArray jsonarray_dao_output = dao.topCPUSummary(userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Interface Info */
	public JSONArray interfaceInfo(String deviceIP, String intName, String userScopeData) {

		JSONArray jsonarray_dao_output = dao.interfaceInfo(deviceIP, intName, userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Interface Status History */
	public JSONArray interfaceStatusHistory(String deviceIP, String intName, String userScopeData) {

		JSONArray jsonarray_dao_output = dao.interfaceStatusHistory(deviceIP, intName, userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Interface CRC Log */
	public JSONArray interfaceCRCLog(String deviceIP, String intName, String userScopeData) {

		JSONArray jsonarray_dao_output = dao.interfaceCRCLog(deviceIP, intName, userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Interface Uptime Log */
	public JSONArray interfaceUptimeLog(String deviceIP, String intName, String userScopeData) {

		JSONArray jsonarray_dao_output = dao.interfaceUptimeLog(deviceIP, intName, userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Interface Bandwidth */
	public JSONArray interfaceBandwidthUtil(String deviceIP, String intName, String userScopeData) {

		JSONArray jsonarray_dao_output = dao.interfaceBandwidthUtil(deviceIP, intName, userScopeData);

		return jsonarray_dao_output;
	}

	/* Get Toptalker Link Summary Listing */
	public JSONArray topTalkerLinkSummaryList(String userScopeData) {

		JSONArray jsonarray_dao_output = dao.topTalkerLinkSummaryList(userScopeData);

		return jsonarray_dao_output;
	}

	// Export Node Latency
	public List<LatencyHistoryReportBean> exportNodeLatency(String from_date, String to_date, String ip_address,
			String userScopeData) {

		return dao.exportNodeLatency(from_date, to_date, ip_address, userScopeData);

	}

	// Top Talker Source IP
	public JSONArray getTopTalkerChartFromDateToDate(String toDate, String fromDate, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopTalkerChartFromDateToDate(toDate, fromDate, userScopeData);
	}

	public JSONArray getTopTalkerChartAllData(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopTalkerChartAllData(userScopeData);
	}

	public JSONArray getTopTalkerSumOfDeviceListAlldata(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopTalkerSumOfDeviceListAlldata(userScopeData);
	}

	public JSONArray getTopTalkerSumOfDeviceListFromDateToDate(String toDate, String fromDate, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopTalkerSumOfDeviceListFromDateToDate(toDate, fromDate, userScopeData);
	}
	// End Top Talker Source IP

	// Top Talker Connection Wise
	public JSONArray getsumOfDeviceDetailsListFromDateToDate(String toDate, String fromDate, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getsumOfDeviceDetailsListFromDateToDate(toDate, fromDate, userScopeData);
	}

	public JSONArray getsumOfDeviceDetailsListAllData(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getsumOfDeviceDetailsListAllData(userScopeData);
	}
	// End Top Talker Connection Wise

	// Top Talker Protocol Wise
	public JSONArray getTopProtocolChartAllData(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopProtocolChartAllData(userScopeData);
	}

	public JSONArray getTopProtocolSumOfDeviceListAlldata(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopProtocolSumOfDeviceListAlldata(userScopeData);
	}

	public JSONArray getTopProtocolChartFromDateToDate(String toDate, String fromDate, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopProtocolChartFromDateToDate(toDate, fromDate, userScopeData);
	}

	public JSONArray getTopProtocolSumOfDeviceListFromDateToDate(String toDate, String fromDate, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopProtocolSumOfDeviceListFromDateToDate(toDate, fromDate, userScopeData);
	}

	public JSONArray interfaceLinkLatency(String deviceIP, String intName, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getInterfaceLinkLatency(deviceIP, intName, userScopeData);
	}

	public long alertCount() {
		// TODO Auto-generated method stub
		return dao.getAlertCount();
	}

	public JSONArray getAlertInfo() {
		// TODO Auto-generated method stub
		return dao.getAlertInfo();
	}

	public JSONArray getCpuAlertInfo() {
		// TODO Auto-generated method stub
		return dao.getCpuAlertInfo();
	}

	public JSONArray getMemoryAlertInfo() {
		// TODO Auto-generated method stub
		return dao.getMemoryAlertInfo();
	}
	
	public JSONArray getDriveAlertInfo() {
		// TODO Auto-generated method stub
		return dao.getDriveAlertInfo();
	}

	public JSONArray getBandwidthAlertInfo() {
		// TODO Auto-generated method stub
		return dao.getBandwidthAlertInfo();
	}

	public JSONArray getTopologyAlertInfo() {
		// TODO Auto-generated method stub
		return dao.getTopologyAlertInfo();
	}

	public JSONArray getTopMemoryUtilization(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopMemoryUtilization(userScopeData);
	}

	public JSONArray getTopCpuUtilization(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopCpuUtilization(userScopeData);
	}

	public JSONObject getBwInUtilization(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getBwInUtilization(userScopeData);
	}

	public JSONObject getBWOutUtilization(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getBWOutUtilization(userScopeData);
	}

	public JSONObject getTopLatencyUtilization(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTopLatencyUtilization(userScopeData);
	}

	public JSONArray getAlarmInfo() {
		return dao.getAlarmInfo();
	}

	public long getAlarmCount() {
		return dao.getAlarmCount();
	}

	public JSONArray eventCountDashboard(String fromDate, String toDate) {
		return dao.eventCountDashboard(fromDate, toDate);
	}

	public JSONArray windowsSeverityEvent(String fromDate, String toDate) {
		return dao.windowsSeverityEvent(fromDate, toDate);
	}

	public JSONArray windowsEventLogWise(String fromDate, String toDate) {
		return dao.windowsEventLogWise(fromDate, toDate);
	}

	public JSONArray windowsEventTrend(String fromDate, String toDate) {
		return dao.windowsEventTrend(fromDate, toDate);
	}

	public JSONArray syslogEventTrend(String fromDate, String toDate) {
		return dao.syslogEventTrend(fromDate, toDate);
	}

	public JSONArray windowsSeverityBarClick(String fromDate, String toDate, String label) {
		return dao.windowsSeverityBarClick(fromDate, toDate, label);
	}

	public JSONArray windowsLogBarClick(String fromDate, String toDate, String label) {
		return dao.windowsLogBarClick(fromDate, toDate, label);
	}

	public JSONArray eventWiseSummary() {
		return dao.eventWiseSummary();
	}

	public JSONArray eventCountWiseSummary(String eventName) {
		return dao.eventCountWiseSummary(eventName);
	}

	public JSONArray sourceWiseSummary(String fromDate, String toDate) {
		return dao.sourceWiseSummary(fromDate, toDate);
	}

	public JSONArray souceSummaryCountClick(String fromDate, String toDate, String sourceName, String severityType) {
		return dao.souceSummaryCountClick(fromDate, toDate, sourceName, severityType);
	}

	public JSONArray osWiseSummary() {
		return dao.osWiseSummary();
	}

	public JSONArray windowsSecurityEvent(String fromDate, String toDate) {
		return dao.windowsSecurityEvent(fromDate, toDate);
	}

	public JSONArray syslogEventList(String fromDate, String toDate) {
		return dao.syslogEventList(fromDate, toDate);
	}

	public JSONArray allDevicesList() {
		return dao.allDevicesList();
	}

	public JSONArray getTotalNodeSummaryDetails(String group_name) {
		return dao.getTotalNodeSummaryDetails(group_name);
	}

	public JSONArray getupNodeSummaryDetails(String group_name) {
		return dao.getupNodeSummaryDetails(group_name);
	}

	public JSONArray getdownNodeSummaryDetails(String group_name) {
		return dao.getdownNodeSummaryDetails(group_name);
	}

	public JSONArray allSyslogSeverityList(String fromDate, String toDate) {
		return dao.allSyslogSeverityList(fromDate, toDate);
	}

	public JSONArray syslogSeverityListData(String fromDate, String toDate, String severity) {
		return dao.syslogSeverityListData(fromDate, toDate, severity);
	}

	public JSONArray allSyslogTypeList(String fromDate, String toDate) {
		return dao.allSyslogTypeList(fromDate, toDate);
	}

	public JSONArray syslogTypeListData(String fromDate, String toDate, String type) {
		return dao.syslogTypeListData(fromDate, toDate, type);
	}

	public JSONArray collectTreeGroup() {
		return dao.collectTreeGroup();
	}

	// Listing of Node Details
	public JSONArray listingDiskConfigService(String ipAddress) {
		return dao.listingDiskConfigDao(ipAddress);
	}

	public JSONArray listingHardwareInService(String ipAddress) {
		return dao.listingHardwareInDao(ipAddress);
	}

	public JSONArray listingMemoryModuleService(String ipAddress) {
		return dao.listingMemoryModuleDao(ipAddress);
	}

	public JSONArray listingLogicalDriveService(String ipAddress) {
		return dao.listingLogicalDriveDao(ipAddress);
	}

	public JSONArray listingPrintalDtService(String ipAddress) {
		return dao.listingPrintalDtDao(ipAddress);
	}

	public JSONArray listingHwConfigService(String ipAddress) {
		return dao.listingHwConfigDao(ipAddress);
	}

	public JSONArray listingSwInventService(String ipAddress) {
		return dao.listingSwInventDao(ipAddress);
	}

	public JSONArray listingPatchDataService(String ipAddress) {
		return dao.listingPatchDataDao(ipAddress);
	}

	public JSONArray listingProcessDataService(String ipAddress) {
		return dao.listingProcessDataDao(ipAddress);
	}

	public JSONArray listingServiceDataService(String ipAddress) {
		return dao.listingServiceDataDao(ipAddress);
	}

	public JSONArray listingFirewallDataService(String ipAddress) {
		return dao.listingFirewallDataDao(ipAddress);
	}
	
	public JSONArray getDiscoverDriveDetails(String ipAddress) {
		// TODO Auto-generated method stub
		return dao.getDiscoverDriveDetails(ipAddress);
	}
	
	public JSONArray viewSetParameterData(String ipAddress) {
		return dao.viewSetParameterData(ipAddress);
	}
	
	public JSONArray basicInfoDetails(String ip_address) {
		// TODO Auto-generated method stub
		return dao.basicInfoDetails(ip_address);
	}
	
	public JSONArray cpuAndMemoryUtilization(String ip_address) {
		// TODO Auto-generated method stub
		return dao.cpuAndMemoryUtilization(ip_address);
	}
	
	public String processPostData(List<processList> dataArray) {
		return dao.processPostData(dataArray);
	}
	
	public JSONArray viewServiceListing(String ipAddress) {
		return dao.viewServiceListing(ipAddress);
	}
	
	public String servicePostData(List<processList> dataArray) {
		return dao.servicePostData(dataArray);
	}

	public String getostype(String ipAddress) {
		// TODO Auto-generated method stub
		return dao.getostype(ipAddress);
	}

	public JSONArray listingSwInventforlinuxService(String ipAddress) {
		// TODO Auto-generated method stub
		return dao.listingSwInventforlinuxService(ipAddress);
	}

	public JSONArray listingHwConfigLinuxService(String ipAddress) {
		// TODO Auto-generated method stub
		return dao.listingHwConfigLinuxService(ipAddress);
	}

	public JSONArray listingMemoryModuleLinuxService(String ipAddress) {
		// TODO Auto-generated method stub
		return dao.listingMemoryModuleLinuxService(ipAddress);
	}

	public JSONArray getDiscoverLinuxDriveDetails(String ipAddress) {
		// TODO Auto-generated method stub
		return dao.getDiscoverLinuxDriveDetails(ipAddress);
	}

	
}
