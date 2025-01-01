package npm.admin.beans;

public class AddNodeParameterBean {
	
	private long id;
	private String ip;
	private int memoryThreshold;
	private int cpuThreshold;
	private int latencyThreshold;
	private String monitoring;
	private String latencyHistory;
	private String cpuHistory;
	private String memoryHistory;
	private String snmpDiscovery;
	private String mailAlert;
	private String smsAlert;
	private String autoTicketing;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getMemoryThreshold() {
		return memoryThreshold;
	}
	public void setMemoryThreshold(int memoryThreshold) {
		this.memoryThreshold = memoryThreshold;
	}
	public int getCpuThreshold() {
		return cpuThreshold;
	}
	public void setCpuThreshold(int cpuThreshold) {
		this.cpuThreshold = cpuThreshold;
	}
	public int getLatencyThreshold() {
		return latencyThreshold;
	}
	public void setLatencyThreshold(int latencyThreshold) {
		this.latencyThreshold = latencyThreshold;
	}
	public String getMonitoring() {
		return monitoring;
	}
	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}
	public String getLatencyHistory() {
		return latencyHistory;
	}
	public void setLatencyHistory(String latencyHistory) {
		this.latencyHistory = latencyHistory;
	}
	public String getCpuHistory() {
		return cpuHistory;
	}
	public void setCpuHistory(String cpuHistory) {
		this.cpuHistory = cpuHistory;
	}
	public String getMemoryHistory() {
		return memoryHistory;
	}
	public void setMemoryHistory(String memoryHistory) {
		this.memoryHistory = memoryHistory;
	}
	
	
	
	public String getSnmpDiscovery() {
		return snmpDiscovery;
	}
	public void setSnmpDiscovery(String snmpDiscovery) {
		this.snmpDiscovery = snmpDiscovery;
	}
	
	
	public String getMailAlert() {
		return mailAlert;
	}
	public void setMailAlert(String mailAlert) {
		this.mailAlert = mailAlert;
	}
	
	
	public String getSmsAlert() {
		return smsAlert;
	}
	public void setSmsAlert(String smsAlert) {
		this.smsAlert = smsAlert;
	}
	public String getAutoTicketing() {
		return autoTicketing;
	}
	public void setAutoTicketing(String autoTicketing) {
		this.autoTicketing = autoTicketing;
	}
	@Override
	public String toString() {
		return "AddNodeParameter [id=" + id + ", ip=" + ip + ", memoryThreshold=" + memoryThreshold + ", cpuThreshold="
				+ cpuThreshold + ", latencyThreshold=" + latencyThreshold + ", monitoring=" + monitoring
				+ ", latencyHistory=" + latencyHistory + ", cpuHistory=" + cpuHistory + ", memoryHistory="
				+ memoryHistory + "]";
	}
	
	

}
