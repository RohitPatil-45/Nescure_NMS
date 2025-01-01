package npm.admin.beans;

public class AddNodeBean {
	
	private long id;
	private String deviceIp;
	private String deviceName;
	private String deviceType;
	private String groupName;
	private String snmp;
	private String serviceProvider;
	private String company;
	private String zone;
	private String state;
	private String location;
	private String district;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSnmp() {
		return snmp;
	}
	public void setSnmp(String snmp) {
		this.snmp = snmp;
	}
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	@Override
	public String toString() {
		return "AddNodeBean [id=" + id + ", deviceIp=" + deviceIp + ", deviceName=" + deviceName + ", deviceType="
				+ deviceType + ", groupName=" + groupName + ", snmp=" + snmp + ", serviceProvider=" + serviceProvider
				+ ", company=" + company + ", zone=" + zone + ", state=" + state + ", location=" + location
				+ ", district=" + district + "]";
	}
	
	

}
