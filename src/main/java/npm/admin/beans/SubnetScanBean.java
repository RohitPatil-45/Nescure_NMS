package npm.admin.beans;

public class SubnetScanBean {

	private long id;
	private String groupName;
	private String startIp;
	private String subnetMask;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getStartIp() {
		return startIp;
	}
	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}
	public String getSubnetMask() {
		return subnetMask;
	}
	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}
	@Override
	public String toString() {
		return "SubnetScanBean [id=" + id + ", groupName=" + groupName + ", startIp=" + startIp + ", subnetMask="
				+ subnetMask + "]";
	}
	
	
}
