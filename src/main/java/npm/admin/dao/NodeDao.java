package npm.admin.dao;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import npm.admin.beans.AddNodeBean;
import npm.admin.beans.AddNodeParameterBean;
import npm.admin.beans.ConfigurationTemplateBean;
import npm.admin.beans.ScheduleConfigurationBean;
import npm.admin.beans.SpeedTestBean;
import npm.model.AddNodeModel;
import npm.model.ConfigBackup;
import npm.model.NodeParameterModel;
import npm.model.SpeedTestModel;

public interface NodeDao {

	// Add Node
	Map<String, String> getdeviceType();

	Map<String, String> getgroupName();

	Map<String, String> getserviceProvider();

	Map<String, String> getSnmp();

	String addNode(String deviceIp, String deviceType, String deviceName, String groupName, String snmp,
			String serviceProvider, String company, String state, String zone, String location, String district);

	List<AddNodeModel> viewAddNode(String userScopeData);

	String deleteAddNode(long id);

	AddNodeBean editAddNode(long id);

	String updateAddNode(String deviceip, String deviceType, String deviceName, String groupName, String snmp,
			String serviceProvider, String company, String state, String zone, String location, String district,
			long id);

	// Node Parameter
	String addNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold, String monitoring,
			String cpuHistory, String latencyHistory, String memoryHistory, String snmpDiscovery, String mailAlert,
			String smsAlert, String autoTicketing);

	JSONArray viewNodeParameter(String userScopeData);

	String deleteNodeParameter(long id);

	AddNodeParameterBean editNodeParameter(long id);

	String updateNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold,
			String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, long id,
			String snmpDiscovery, String mailAlert, String smsAlert, String autoTicketing);

	String addConfigBackup(String backupPath, String runConfig, String startupConfig, String backup, String group_name,
			String ip_address, String configType, String backupSchedule, String datepicker);

	List<ConfigBackup> viewconfigBackup(String userScopeData);

	String deleteConfigBackup(long id);

	// 19-12-2022
	String importNodeIP(String nodeIP);

	public Map<String, String> getDistinctNodes();

	public String uploadFile(String fileName, String destPath);

	String addAvailabilityParameter(LocalTime fromTime, LocalTime toTime);

	JSONArray viewAvailabilityParameter();

	String addConfigurationTemplateDao(String templateName, String command);

	JSONArray viewConfigurationTemplateDao();

	String deleteConfigurationTemplateDao(long ID);

	ConfigurationTemplateBean ConfigurationTemplateBeanDao(long ID);

	String updateConfigurationTemplateDao(long ID, String templateName, String command);

	JSONArray viewSheduleConfigurationDao();

	String deleteSheduleConfigurationDao(long ID);

	ScheduleConfigurationBean editScheduleConfigurationDao(long ID);

	String updateScheduleConfigurationDao(long ID, String backupPath, String runConfig, String startupConfig,
			String backup, String group_name, String ip_address, String configType, String backupSchedule,
			String datepicker);

	List<ScheduleConfigurationBean> scheduleconfigReport(String from_date, String to_date, List<String> ip_address);

	JSONArray jsonInterfaceParameterDao(String userScopeData);

	String deleteInterfaceParameterDao(long ID);

	String addSMSConfigurationDao(String smsProvider, String smsUrl, String username, String password);

	JSONArray viewSMSConfigurationDao();

	String deleteSMSConfigurationDao(long ID);

	Map<String, String> getCompanyName();

	Map<String, String> getStateName();

	Map<String, String> getZoneName();

	Map<String, String> getDistrictName();

	String addsnmpModal(String nodeIP, String snmp);

	String addGroupNameModal(String nodeIP, String groupName);

	String addserviceProviderModal(String nodeIP, String serviceProvider);

	String addcompanyModal(String nodeIP, String company);

	String adddeviceTypeModal(String nodeIP, String deviceType);

	String addstateModal(String nodeIP, String state);

	String adddistrictModal(String nodeIP, String district);

	String addlocationModal(String nodeIP, String location);

	String deleteBulkNode(String nodeIP);

	String addzoneModal(String nodeIP, String zone);

	List<AddNodeModel> searchByIp(String ip);

	List<AddNodeModel> searchByName(String name);

	List<AddNodeModel> searchByGroupName(String groupName);

	List<AddNodeModel> searchByDeviceType(String deviceType);

	JSONArray mapTopologyData();

	Map<String, String> getspeedTestIps();

	List<SpeedTestModel> getspeedTestDeviceslisting();

	SpeedTestBean editSpeedTestDeviceMaster(long iD);

	String updateSpeedTestDeviceMaster(String device_IP, String discription, long id);

}
