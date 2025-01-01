package npm.admin.beans;

public class NwIpScanBean {
	
	private long id;
	private String groupName;
	private String startIp;
	private String endIp;
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
	public String getEndIp() {
		return endIp;
	}
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	@Override
	public String toString() {
		return "NwIpScanBean [id=" + id + ", groupName=" + groupName + ", startIp=" + startIp + ", endIp=" + endIp
				+ "]";
	}
	
	

}
