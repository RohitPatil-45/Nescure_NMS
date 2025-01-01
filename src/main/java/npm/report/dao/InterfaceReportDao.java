package npm.report.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;

import npm.admin.beans.BWThresholdHistoryBean;
import npm.admin.beans.InterfaceAvailabilityBean;
import npm.admin.beans.InterfaceBandwidthHistoryBean;
import npm.admin.beans.InterfaceCRCHistoryBean;
import npm.admin.beans.InterfaceStatusReportBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.model.InterfaceMonitoring;

public interface InterfaceReportDao {

	JSONArray getGroupInterfaceDetails(String group_name, String userScopeData);

	List<InterfaceStatusReportBean> InterfaceStatusReport(String from_date, String to_date, List<String> ip_list);

	JSONArray InterfaceBandwidthHistoryReport(String from_date, String to_date, List<String> ip_list);

	List<BWThresholdHistoryBean> bandwidthThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list);

	List<InterfaceCRCHistoryBean> interfaceCrcHistoryReport(String from_date, String to_date, List<String> ip_list);

	List<InterfaceAvailabilityBean> interfaceAvailabilityReport(String from_date, String to_date, List<String> ip_list);

	String getInterfaceNameByIP(String ip_address);

	JSONArray getGroupInterface(String group_name);

	JSONArray getPortSummary(String userScopeData);

	JSONArray getTotalPortSummaryDetails(String ip_address, String userScopeData);

	JSONArray getUsedPortSummaryDetails(String ip, String userScopeData);

	JSONArray getUnUsedPortSummaryDetails(String ip, String userScopeData);

	JSONArray getUpPortSummaryDetails(String ip, String userScopeData);

	JSONArray getDownPortSummaryDetails(String ip, String userScopeData);

	JSONArray unutilizedPortSummaryDetails(String ip, String userScopeData);

	JSONArray groupSummary(String userScopeData);

	JSONArray getupNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest req);

	JSONArray getdownNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest req);

	JSONArray getwarningNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest req);

	JSONArray getTotalNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest request);

	JSONArray interfaceSummary(String userScopeData);

	JSONArray interfaceSummaryGroupWise(String userScopeData);

	JSONArray getTotalInterfaceSummaryDetails(String group_name, String userScopeData, HttpServletRequest req);

	JSONArray getupInterfaceSummaryDetails(String group_name, String userScopeData);

	JSONArray getdownInterfaceSummaryDetails(String group_name, String userScopeData);

	JSONArray getwarningInterfaceSummaryDetails(String group_name, String userScopeData);

	JSONArray averageBandwidthReport(String from_date, String to_date, List<String> list, String userScopeData);

	JSONArray interfaceJitterReport(String from_date, String to_date, List<String> list);

	JSONArray interfaceBandwidth(String from_date, String to_date, String ip_address, String interfaceName);

	List<InterfaceStatusReportBean> interfaceStatusEvents(String from_date, String to_date, String ip_address,
			String interfaceName);

	List<InterfaceAvailabilityBean> interfaceUptime(String from_date, String to_date, String ip_address,
			String interfaceName);

	JSONArray deviceTypeSummary(String userScopeData);

	JSONArray getTotalDeviceTypeSummaryDetails(String group_name, String userScopeData);

	JSONArray getupDeviceTypeSummaryDetails(String group_name, String userScopeData);

	JSONArray getdownDeviceTypeSummaryDetails(String group_name, String userScopeData);

	JSONArray getwarningDeviceTypeSummaryDetails(String group_name, String userScopeData);

	JSONArray getInterfaceDetailsWan4g(String group_name, String status, HttpServletRequest req);

	JSONArray getGroupInterfaceIPDetails(String group_name, String userScopeData);
}
