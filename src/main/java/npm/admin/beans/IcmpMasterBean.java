package npm.admin.beans;

public class IcmpMasterBean {
	
	
	private long id;
	private String packetSize;
	private String packetCount;
	private String poolingTime;
	private String timeout;
	private String ttl;
	private String customerName;
	private String location;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPacketSize() {
		return packetSize;
	}
	public void setPacketSize(String packetSize) {
		this.packetSize = packetSize;
	}
	public String getPacketCount() {
		return packetCount;
	}
	public void setPacketCount(String packetCount) {
		this.packetCount = packetCount;
	}
	public String getPoolingTime() {
		return poolingTime;
	}
	public void setPoolingTime(String poolingTime) {
		this.poolingTime = poolingTime;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public String getTtl() {
		return ttl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "IcmpMasterBean [packetSize=" + packetSize + ", packetCount=" + packetCount + ", poolingTime="
				+ poolingTime + ", timeout=" + timeout + ", ttl=" + ttl + ", customerName=" + customerName
				+ ", location=" + location + "]";
	}
	
	

}
