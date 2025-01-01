package npm.report.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.admin.beans.BWThresholdHistoryBean;
import npm.admin.beans.InterfaceAvailabilityBean;
import npm.admin.beans.InterfaceBandwidthHistoryBean;
import npm.admin.beans.InterfaceCRCHistoryBean;
import npm.admin.beans.InterfaceStatusReportBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.beans.NodeStatusReportBean;
import npm.model.InterfaceMonitoring;
import npm.report.dao.InterfaceReportDao;
import npm.report.dao.NodeReportDao;
import npm.report.service.InterfaceReportService;

@Service
public class InterfaceReportServiceImpl implements InterfaceReportService {

	@Autowired
	InterfaceReportDao dao;

	public JSONArray getGroupInterfaceDetails(String group_name, String userScopeData) {

		return dao.getGroupInterfaceDetails(group_name, userScopeData);

	}

	public List<InterfaceStatusReportBean> InterfaceStatusReport(String from_date, String to_date,
			List<String> ip_list) {

		return dao.InterfaceStatusReport(from_date, to_date, ip_list);

	}

	public JSONArray InterfaceBandwidthHistoryReport(String from_date, String to_date, List<String> ip_list) {

		return dao.InterfaceBandwidthHistoryReport(from_date, to_date, ip_list);

	}

	public List<BWThresholdHistoryBean> bandwidthThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list) {
		// TODO Auto-generated method stub
		return dao.bandwidthThresholdHistoryReport(from_date, to_date, ip_list);
	}

	public List<InterfaceCRCHistoryBean> interfaceCrcHistoryReport(String from_date, String to_date,
			List<String> ip_list) {
		// TODO Auto-generated method stub
		return dao.interfaceCrcHistoryReport(from_date, to_date, ip_list);
	}

	public List<InterfaceAvailabilityBean> interfaceAvailabilityReport(String from_date, String to_date,
			List<String> ip_list) {
		// TODO Auto-generated method stub
		return dao.interfaceAvailabilityReport(from_date, to_date, ip_list);
	}

	public String getInterfaceNameByIP(String ip_address) {
		// TODO Auto-generated method stub
		return dao.getInterfaceNameByIP(ip_address);
	}

	public JSONArray getGroupInterface(String group_name) {
		// TODO Auto-generated method stub
		return dao.getGroupInterface(group_name);
	}

	public JSONArray portSummary(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getPortSummary(userScopeData);
	}

	public JSONArray getTotalPortSummaryDetails(String ip_address, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getTotalPortSummaryDetails(ip_address, userScopeData);
	}

	public JSONArray getUsedPortSummaryDetails(String ip, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getUsedPortSummaryDetails(ip, userScopeData);
	}

	public JSONArray getUnUsedPortSummaryDetails(String ip, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getUnUsedPortSummaryDetails(ip, userScopeData);
	}

	public JSONArray getUpPortSummaryDetails(String ip, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getUpPortSummaryDetails(ip, userScopeData);
	}

	public JSONArray getDownPortSummaryDetails(String ip, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getDownPortSummaryDetails(ip, userScopeData);
	}

	public JSONArray unutilizedPortSummaryDetails(String ip, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.unutilizedPortSummaryDetails(ip, userScopeData);
	}

	public JSONArray groupSummary(String userScopeData) {
		return dao.groupSummary(userScopeData);
	}

	public JSONArray getupNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest req) {
		return dao.getupNodeSummaryDetails(group_name, userScopeData, req);
	}

	public JSONArray getdownNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest req) {
		return dao.getdownNodeSummaryDetails(group_name, userScopeData, req);
	}

	public JSONArray getwarningNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest req) {
		return dao.getwarningNodeSummaryDetails(group_name, userScopeData, req);
	}

	public JSONArray getTotalNodeSummaryDetails(String group_name, String userScopeData, HttpServletRequest request) {
		return dao.getTotalNodeSummaryDetails(group_name, userScopeData, request);
	}

	public JSONArray interfaceSummary(String userScopeData) {
		return dao.interfaceSummary(userScopeData);
	}

	public JSONArray interfaceSummaryGroupWise(String userScopeData) {
		return dao.interfaceSummaryGroupWise(userScopeData);
	}

	public JSONArray getTotalInterfaceSummaryDetails(String group_name, String userScopeData, HttpServletRequest req) {
		return dao.getTotalInterfaceSummaryDetails(group_name, userScopeData, req);
	}

	public JSONArray getupInterfaceSummaryDetails(String group_name, String userScopeData) {
		return dao.getupInterfaceSummaryDetails(group_name, userScopeData);
	}

	public JSONArray getdownInterfaceSummaryDetails(String group_name, String userScopeData) {
		return dao.getdownInterfaceSummaryDetails(group_name, userScopeData);
	}

	public JSONArray getwarningInterfaceSummaryDetails(String group_name, String userScopeData) {
		return dao.getwarningInterfaceSummaryDetails(group_name, userScopeData);
	}

	public JSONArray averageBandwidthReport(String from_date, String to_date, List<String> list, String userScopeData) {
		return dao.averageBandwidthReport(from_date, to_date, list, userScopeData);
	}

	public JSONArray interfaceJitterReport(String from_date, String to_date, List<String> list) {
		return dao.interfaceJitterReport(from_date, to_date, list);
	}

	public JSONArray interfaceBandwidth(String from_date, String to_date, String ip_address, String interfaceName) {
		return dao.interfaceBandwidth(from_date, to_date, ip_address, interfaceName);
	}

	public List<InterfaceStatusReportBean> interfaceStatusEvents(String from_date, String to_date, String ip_address,
			String interfaceName) {
		return dao.interfaceStatusEvents(from_date, to_date, ip_address, interfaceName);
	}

	public List<InterfaceAvailabilityBean> interfaceUptime(String from_date, String to_date, String ip_address,
			String interfaceName) {
		return dao.interfaceUptime(from_date, to_date, ip_address, interfaceName);
	}

	public JSONArray deviceTypeSummary(String userScopeData) {
		return dao.deviceTypeSummary(userScopeData);
	}

	public JSONArray getTotalDeviceTypeSummaryDetails(String group_name, String userScopeData) {
		return dao.getTotalDeviceTypeSummaryDetails(group_name, userScopeData);
	}

	public JSONArray getupDeviceTypeSummaryDetails(String group_name, String userScopeData) {
		return dao.getupDeviceTypeSummaryDetails(group_name, userScopeData);
	}

	public JSONArray getdownDeviceTypeSummaryDetails(String group_name, String userScopeData) {
		return dao.getdownDeviceTypeSummaryDetails(group_name, userScopeData);
	}

	public JSONArray getwarningDeviceTypeSummaryDetails(String group_name, String userScopeData) {
		return dao.getwarningDeviceTypeSummaryDetails(group_name, userScopeData);
	}

	public JSONArray getInterfaceDetailsWan4g(String group_name, String status, HttpServletRequest req) {
		// TODO Auto-generated method stub
		return dao.getInterfaceDetailsWan4g(group_name, status, req);
	}

	public JSONArray getGroupInterfaceIPDetails(String group_name, String userScopeData) {
		// TODO Auto-generated method stub
		return dao.getGroupInterfaceIPDetails(group_name, userScopeData);
	}

}
