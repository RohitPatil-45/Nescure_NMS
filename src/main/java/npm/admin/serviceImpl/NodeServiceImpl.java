package npm.admin.serviceImpl;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.admin.beans.AddNodeBean;
import npm.admin.beans.AddNodeParameterBean;
import npm.admin.beans.ConfigurationTemplateBean;
import npm.admin.beans.ScheduleConfigurationBean;
import npm.admin.beans.SpeedTestBean;
import npm.admin.dao.NodeDao;
import npm.admin.service.NodeService;
import npm.model.AddNodeModel;
import npm.model.ConfigBackup;
import npm.model.NodeParameterModel;
import npm.model.SpeedTestModel;

@Service
public class NodeServiceImpl implements NodeService {

	@Autowired
	NodeDao dao;

	// Add Node
	public Map<String, String> getdeviceType() {
		// TODO Auto-generated method stub
		return dao.getdeviceType();
	}

	public Map<String, String> getgroupName() {
		// TODO Auto-generated method stub
		return dao.getgroupName();
	}

	public Map<String, String> getserviceProvider() {
		// TODO Auto-generated method stub
		return dao.getserviceProvider();
	}

	public Map<String, String> getSnmp() {
		// TODO Auto-generated method stub
		return dao.getSnmp();
	}

	public String addNode(String deviceIp, String deviceType, String deviceName, String groupName, String snmp,
			String serviceProvider, String company, String state, String zone, String location, String district) {
		// TODO Auto-generated method stub

		return dao.addNode(deviceIp, deviceName, deviceType, groupName, snmp, serviceProvider, company, state, zone,
				location, district);

	}

	public List<AddNodeModel> viewAddNode(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.viewAddNode(userScopeData);
	}

	public String deleteAddNode(long id) {
		// TODO Auto-generated method stub

		return dao.deleteAddNode(id);

	}

	public AddNodeBean editAddNode(long id) {
		// TODO Auto-generated method stub
		return dao.editAddNode(id);
	}

	public String updateAddNode(String deviceIp, String deviceType, String deviceName, String groupName, String snmp,
			String serviceProvider, String company, String state, String zone, String location, String district,
			long id) {
		// TODO Auto-generated method stub

		return dao.updateAddNode(deviceIp, deviceName, deviceType, groupName, snmp, serviceProvider, company, state,
				zone, location, district, id);

	}

	// Node Parameter
	public String addNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold,
			String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, String snmpDiscovery,
			String mailAlert, String smsAlert, String autoTicketing) {
		// TODO Auto-generated method stub

		return dao.addNodeParameter(ip, memoryThreshold, cpuThreshold, latencyThreshold, monitoring, cpuHistory,
				memoryHistory, latencyHistory, snmpDiscovery, mailAlert, autoTicketing, smsAlert);

	}

	public JSONArray viewNodeParameter(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.viewNodeParameter(userScopeData);
	}

	public String deleteNodeParameter(long id) {
		// TODO Auto-generated method stub

		return dao.deleteNodeParameter(id);

	}

	public AddNodeParameterBean editNodeParameter(long id) {
		// TODO Auto-generated method stub
		return dao.editNodeParameter(id);
	}

	public String updateNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold,
			String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, long id,
			String snmpDiscovery, String mailAlert, String smsAlert, String autoTicketing) {
		// TODO Auto-generated method stub

		return dao.updateNodeParameter(ip, memoryThreshold, cpuThreshold, latencyThreshold, monitoring, cpuHistory,
				memoryHistory, latencyHistory, id, snmpDiscovery, mailAlert, autoTicketing, smsAlert);

	}

	public String addConfigBackup(String backupPath, String runConfig, String startupConfig, String backup,
			String group_name, String ip_address, String configType, String backupSchedule, String datepicker) {
		// TODO Auto-generated method stub
		return dao.addConfigBackup(backupPath, runConfig, startupConfig, backup, group_name, ip_address, configType,
				backupSchedule, datepicker);
	}

	public List<ConfigBackup> viewconfigBackup(String userScopeData) {
		// TODO Auto-generated method stub
		return dao.viewconfigBackup(userScopeData);
	}

	public String deleteConfigBackup(long id) {
		// TODO Auto-generated method stub
		return dao.deleteConfigBackup(id);
	}

	// 19-12-2022
	public String importNodeIP(String nodeIP) {
		// TODO Auto-generated method stub
		return dao.importNodeIP(nodeIP);
	}

	public Map<String, String> getDistinctNodes() {
		// TODO Auto-generated method stub
		return dao.getDistinctNodes();
	}

	// Bulk Upload

	public String uploadFile(String fileName, String destPath) {
		// TODO Auto-generated method stub
		return dao.uploadFile(fileName, destPath);
	}

	public String addAvailabilityParameter(LocalTime fromTime, LocalTime toTime) {
		return dao.addAvailabilityParameter(fromTime, toTime);
	}

	public JSONArray viewAvailabilityParameter() {
		return dao.viewAvailabilityParameter();
	}

	public String addConfigurationTemplateService(String templateName, String command) {
		return dao.addConfigurationTemplateDao(templateName, command);
	}

	public JSONArray viewConfigurationTemplateService() {
		return dao.viewConfigurationTemplateDao();
	}

	public String deleteConfigurationTemplateService(long ID) {
		return dao.deleteConfigurationTemplateDao(ID);
	}

	public ConfigurationTemplateBean ConfigurationTemplateBeanService(long ID) {
		return dao.ConfigurationTemplateBeanDao(ID);
	}

	public String updateConfigurationTemplateService(long ID, String templateName, String command) {
		return dao.updateConfigurationTemplateDao(ID, templateName, command);
	}

	public JSONArray viewSheduleConfigurationSevice() {
		return dao.viewSheduleConfigurationDao();
	}

	public String deleteSheduleConfigurationService(long ID) {
		return dao.deleteSheduleConfigurationDao(ID);
	}

	public ScheduleConfigurationBean editScheduleConfigurationService(long ID) {
		return dao.editScheduleConfigurationDao(ID);
	}

	public String updateScheduleConfigurationService(long ID, String backupPath, String runConfig, String startupConfig,
			String backup, String group_name, String ip_address, String configType, String backupSchedule,
			String datepicker) {
		// TODO Auto-generated method stub
		return dao.updateScheduleConfigurationDao(ID, backupPath, runConfig, startupConfig, backup, group_name,
				ip_address, configType, backupSchedule, datepicker);
	}

	public List<ScheduleConfigurationBean> scheduleconfigReport(String from_date, String to_date,
			List<String> ip_address) {
		return dao.scheduleconfigReport(from_date, to_date, ip_address);

	}

	public JSONArray jsonInterfaceParameterService(String userScopeData) {
		return dao.jsonInterfaceParameterDao(userScopeData);
	}

	public String deleteInterfaceParameterService(long ID) {
		return dao.deleteInterfaceParameterDao(ID);
	}

	public String addSMSConfigurationService(String smsProvider, String smsUrl, String username, String password) {
		return dao.addSMSConfigurationDao(smsProvider, smsUrl, username, password);
	}

	public JSONArray viewSMSConfigurationService() {
		return dao.viewSMSConfigurationDao();
	}

	public String deleteSMSConfigurationService(long ID) {
		return dao.deleteSMSConfigurationDao(ID);
	}

	public Map<String, String> getCompanyName() {
		return dao.getCompanyName();
	}

	public Map<String, String> getStateName() {
		return dao.getStateName();
	}

	public Map<String, String> getZoneName() {
		return dao.getZoneName();
	}

	public Map<String, String> getDistrictName() {
		return dao.getDistrictName();
	}

	public String addsnmpModal(String nodeIP, String snmp) {
		return dao.addsnmpModal(nodeIP, snmp);
	}

	public String addGroupNameModal(String nodeIP, String groupName) {
		return dao.addGroupNameModal(nodeIP, groupName);
	}

	public String addserviceProviderModal(String nodeIP, String serviceProvider) {
		return dao.addserviceProviderModal(nodeIP, serviceProvider);
	}

	public String addcompanyModal(String nodeIP, String company) {
		return dao.addcompanyModal(nodeIP, company);
	}

	public String adddeviceTypeModal(String nodeIP, String deviceType) {
		return dao.adddeviceTypeModal(nodeIP, deviceType);
	}

	public String addstateModal(String nodeIP, String state) {
		return dao.addstateModal(nodeIP, state);
	}

	public String adddistrictModal(String nodeIP, String district) {
		return dao.adddistrictModal(nodeIP, district);
	}

	public String addlocationModal(String nodeIP, String location) {
		return dao.addlocationModal(nodeIP, location);
	}

	public String deleteBulkNode(String nodeIP) {
		return dao.deleteBulkNode(nodeIP);
	}

	public String addzoneModal(String nodeIP, String zone) {
		return dao.addzoneModal(nodeIP, zone);
	}

	public List<AddNodeModel> searchByIp(String ip) {
		return dao.searchByIp(ip);
	}

	public List<AddNodeModel> searchByName(String name) {
		return dao.searchByName(name);
	}

	public List<AddNodeModel> searchByGroupName(String groupName) {
		return dao.searchByGroupName(groupName);
	}

	public List<AddNodeModel> searchByDeviceType(String deviceType) {
		return dao.searchByDeviceType(deviceType);
	}

	public JSONArray mapTopologyData() {
		return dao.mapTopologyData();
	}

	public Map<String, String> getspeedTestIps() {
		// TODO Auto-generated method stub
		return dao.getspeedTestIps();
	}

	public List<SpeedTestModel> getspeedTestDeviceslisting() {
		// TODO Auto-generated method stub
		return dao.getspeedTestDeviceslisting();
	}

	public SpeedTestBean editSpeedTestDeviceMaster(long iD) {
		// TODO Auto-generated method stub
		return dao.editSpeedTestDeviceMaster(iD);
	}

	public String updateSpeedTestDeviceMaster(String device_IP, String discription, long id) {
		// TODO Auto-generated method stub
		return dao.updateSpeedTestDeviceMaster(device_IP, discription, id);
	}

}
