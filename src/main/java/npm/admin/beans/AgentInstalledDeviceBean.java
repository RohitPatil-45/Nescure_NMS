package npm.admin.beans;

import java.sql.Timestamp;

public class AgentInstalledDeviceBean {

	
	private Long id;
    private String ipAddress;
    private String hostName;
    private String branchName;
    private String agentStartTime;
    private String deviceStatus;
    private String version;
    private String macAddress;
    private String deviceIp;
    private String deviceMacList;
    private String swLastDiscover;
    private String hwLastDiscover;
    private String hwDiscoverStatus;
    private String hwScanType;
    private String serialNumber;
    private String swCount;
    private String swScanType;
    private String groupName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAgentStartTime() {
		return agentStartTime;
	}
	public void setAgentStartTime(String agentStartTime) {
		this.agentStartTime = agentStartTime;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getDeviceMacList() {
		return deviceMacList;
	}
	public void setDeviceMacList(String deviceMacList) {
		this.deviceMacList = deviceMacList;
	}
	public String getSwLastDiscover() {
		return swLastDiscover;
	}
	public void setSwLastDiscover(String swLastDiscover) {
		this.swLastDiscover = swLastDiscover;
	}
	public String getHwLastDiscover() {
		return hwLastDiscover;
	}
	public void setHwLastDiscover(String hwLastDiscover) {
		this.hwLastDiscover = hwLastDiscover;
	}
	public String getHwDiscoverStatus() {
		return hwDiscoverStatus;
	}
	public void setHwDiscoverStatus(String hwDiscoverStatus) {
		this.hwDiscoverStatus = hwDiscoverStatus;
	}
	public String getHwScanType() {
		return hwScanType;
	}
	public void setHwScanType(String hwScanType) {
		this.hwScanType = hwScanType;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSwCount() {
		return swCount;
	}
	public void setSwCount(String swCount) {
		this.swCount = swCount;
	}
	public String getSwScanType() {
		return swScanType;
	}
	public void setSwScanType(String swScanType) {
		this.swScanType = swScanType;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
}
