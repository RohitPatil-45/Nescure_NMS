package npm.dashboard.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import npm.admin.beans.AgentInstalledDeviceBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.beans.processList;

public interface DashboardService {

	/* Get Device Count Summary */
	public JSONArray deviceSummaryCount(String userScopeData);

	/* Get Link Count Summary */
	public JSONArray linkSummaryCount(String userScopeData);

	/* Get Down Devices Summary Listing */
	public JSONArray deviceSummaryList(String userScopeData);

	/* Get Down Link Summary Listing */
	public JSONArray linkSummaryList(String userScopeData);

	/* Get Device Info */
	public JSONArray getDeviceInfo(String deviceInfo, String userScopeData);

	/* Get Link Info */
	public JSONArray getLinkInfo(String linkInfo, String userScopeData);

	/* Top CPU Summary Listing */
	public JSONArray topCPUSummary(String userScopeData);

	/* Get Interface Info */
	public JSONArray interfaceInfo(String deviceIP, String intName, String userScopeData);

	/* Get Interface Status History */
	public JSONArray interfaceStatusHistory(String deviceIP, String intName, String userScopeData);

	/* Get Interface CRC Log */
	public JSONArray interfaceCRCLog(String deviceIP, String intName, String userScopeData);

	/* Get Interface Uptime Log */
	public JSONArray interfaceUptimeLog(String deviceIP, String intName, String userScopeData);

	/* Get Interface Bandwidth */
	public JSONArray interfaceBandwidthUtil(String deviceIP, String intName, String userScopeData);

	/* Get Toptalker Link Summary Listing */
	public JSONArray topTalkerLinkSummaryList(String userScopeData);

	// Export Node Latency
	List<LatencyHistoryReportBean> exportNodeLatency(String from_date, String to_date, String ip_address,
			String userScopeData);

	public JSONArray getTopTalkerChartFromDateToDate(String toDate, String fromDate, String userScopeData);

	public JSONArray getTopTalkerChartAllData(String userScopeData);

	public JSONArray getTopTalkerSumOfDeviceListAlldata(String userScopeData);

	public JSONArray getTopTalkerSumOfDeviceListFromDateToDate(String toDate, String fromDate, String userScopeData);

	public JSONArray getsumOfDeviceDetailsListFromDateToDate(String toDate, String fromDate, String userScopeData);

	public JSONArray getsumOfDeviceDetailsListAllData(String userScopeData);

	public JSONArray getTopProtocolChartAllData(String userScopeData);

	public JSONArray getTopProtocolSumOfDeviceListAlldata(String userScopeData);

	public JSONArray getTopProtocolChartFromDateToDate(String toDate, String fromDate, String userScopeData);

	public JSONArray getTopProtocolSumOfDeviceListFromDateToDate(String toDate, String fromDate, String userScopeData);

	public JSONArray interfaceLinkLatency(String deviceIP, String intName, String userScopeData);

	public long alertCount();

	public JSONArray getAlertInfo();

	public JSONArray getCpuAlertInfo();

	public JSONArray getMemoryAlertInfo();

	public JSONArray getDriveAlertInfo();

	public JSONArray getBandwidthAlertInfo();

	public JSONArray getTopologyAlertInfo();

	public JSONArray getTopMemoryUtilization(String userScopeData);

	public JSONArray getTopCpuUtilization(String userScopeData);

	public JSONObject getBwInUtilization(String userScopeData);

	public JSONObject getBWOutUtilization(String userScopeData);

	public JSONObject getTopLatencyUtilization(String userScopeData);

	public JSONArray getAlarmInfo();

	public long getAlarmCount();

	public JSONArray eventCountDashboard(String fromDate, String toDate);

	public JSONArray windowsSeverityEvent(String fromDate, String toDate);

	public JSONArray windowsEventLogWise(String fromDate, String toDate);

	public JSONArray windowsEventTrend(String fromDate, String toDate);

	public JSONArray syslogEventTrend(String fromDate, String toDate);

	public JSONArray windowsSeverityBarClick(String fromDate, String toDate, String label);

	public JSONArray windowsLogBarClick(String fromDate, String toDate, String label);

	public JSONArray eventWiseSummary();

	public JSONArray eventCountWiseSummary(String eventName);

	public JSONArray sourceWiseSummary(String fromDate, String toDate);

	public JSONArray souceSummaryCountClick(String fromDate, String toDate, String sourceName, String severityType);

	public JSONArray osWiseSummary();

	public JSONArray windowsSecurityEvent(String fromDate, String toDate);

	public JSONArray syslogEventList(String fromDate, String toDate);

	public JSONArray allDevicesList();

	public JSONArray getTotalNodeSummaryDetails(String group_name);

	public JSONArray getupNodeSummaryDetails(String group_name);

	public JSONArray getdownNodeSummaryDetails(String group_name);

	public JSONArray allSyslogSeverityList(String fromDate, String toDate);

	public JSONArray syslogSeverityListData(String fromDate, String toDate, String severity);

	public JSONArray allSyslogTypeList(String fromDate, String toDate);

	public JSONArray syslogTypeListData(String fromDate, String toDate, String type);

	public JSONArray collectTreeGroup();

	// Listing of Node Details
	JSONArray listingDiskConfigService(String ipAddress);

	JSONArray listingHardwareInService(String ipAddress);

	JSONArray listingMemoryModuleService(String ipAddress);

	JSONArray listingLogicalDriveService(String ipAddress);

	JSONArray listingPrintalDtService(String ipAddress);

	JSONArray listingHwConfigService(String ipAddress);

	JSONArray listingSwInventService(String ipAddress);

	JSONArray listingPatchDataService(String ipAddress);

	JSONArray listingProcessDataService(String ipAddress);

	JSONArray listingServiceDataService(String ipAddress);

	JSONArray listingFirewallDataService(String ipAddress);

	JSONArray getDiscoverDriveDetails(String ipAddress);

	JSONArray viewSetParameterData(String ipAddress);

	JSONArray basicInfoDetails(String ip_address);

	JSONArray cpuAndMemoryUtilization(String ip_address);

	String processPostData(List<processList> dataArray);

	public JSONArray viewServiceListing(String ipAddress);

	String servicePostData(List<processList> dataArray);

	public String getostype(String ipAddress);

	public JSONArray listingSwInventforlinuxService(String ipAddress);

	public JSONArray listingHwConfigLinuxService(String ipAddress);

	public JSONArray listingMemoryModuleLinuxService(String ipAddress);

	public JSONArray getDiscoverLinuxDriveDetails(String ipAddress);

}
