package npm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agent_installed_device")
public class AgentInstalledDevice {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "HOST_NAME")
    private String hostName;

    @Column(name = "BRANCH_NAME")
    private String branchName;

    @Column(name = "AGENT_START_TIME")
    private String agentStartTime;

    @Column(name = "DEVICE_STATUS")
    private String deviceStatus;

    @Column(name = "VERSION")
    private String version;

    @Column(name = "MAC_ADDRESS")
    private String macAddress;

    @Column(name = "DEVICE_IP")
    private String deviceIp;

    @Column(name = "DEVICE_MAC_LIST", columnDefinition = "TEXT")
    private String deviceMacList;

    @Column(name = "SW_LAST_DISCOVER", columnDefinition = "TEXT")
    private String swLastDiscover;

    @Column(name = "HW_LAST_DISCOVER", columnDefinition = "TEXT")
    private String hwLastDiscover;

    @Column(name = "HW_DISCOVER_STATUS", columnDefinition = "TEXT")
    private String hwDiscoverStatus;

    @Column(name = "HW_SCAN_TYPE", columnDefinition = "TEXT")
    private String hwScanType;

    @Column(name = "SERIALNUMBER")
    private String serialNumber;

    @Column(name = "SW_COUNT")
    private String swCount;

    @Column(name = "SW_SCAN_TYPE")
    private String swScanType;
    
    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

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

	public java.sql.Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.sql.Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public java.sql.Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(java.sql.Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "AgentInstalledDevice [id=" + id + ", ipAddress=" + ipAddress + ", hostName=" + hostName
				+ ", branchName=" + branchName + ", agentStartTime=" + agentStartTime + ", deviceStatus=" + deviceStatus
				+ ", version=" + version + ", macAddress=" + macAddress + ", deviceIp=" + deviceIp + ", deviceMacList="
				+ deviceMacList + ", swLastDiscover=" + swLastDiscover + ", hwLastDiscover=" + hwLastDiscover
				+ ", hwDiscoverStatus=" + hwDiscoverStatus + ", hwScanType=" + hwScanType + ", serialNumber="
				+ serialNumber + ", swCount=" + swCount + ", swScanType=" + swScanType + ", groupName=" + groupName
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
    
    
}
