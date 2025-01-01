package npm.admin.beans;

public class LinkaverageLatencyReportBean {

	private long ID;
	private String NODE_IP;
	private String AVG_LATENCY;
	private String AVG_PACKETDROP;
	private String DEVICE_NAME;
	private String GROUP_NAME;
	private String INTERFACE_NAME;
	private String ROUTER_IP;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNODE_IP() {
		return NODE_IP;
	}

	public void setNODE_IP(String nODE_IP) {
		NODE_IP = nODE_IP;
	}

	public String getAVG_LATENCY() {
		return AVG_LATENCY;
	}

	public void setAVG_LATENCY(String aVG_LATENCY) {
		AVG_LATENCY = aVG_LATENCY;
	}

	public String getAVG_PACKETDROP() {
		return AVG_PACKETDROP;
	}

	public void setAVG_PACKETDROP(String aVG_PACKETDROP) {
		AVG_PACKETDROP = aVG_PACKETDROP;
	}

	public String getDEVICE_NAME() {
		return DEVICE_NAME;
	}

	public void setDEVICE_NAME(String dEVICE_NAME) {
		DEVICE_NAME = dEVICE_NAME;
	}

	public String getGROUP_NAME() {
		return GROUP_NAME;
	}

	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}

	public String getINTERFACE_NAME() {
		return INTERFACE_NAME;
	}

	public void setINTERFACE_NAME(String iNTERFACE_NAME) {
		INTERFACE_NAME = iNTERFACE_NAME;
	}

	public String getROUTER_IP() {
		return ROUTER_IP;
	}

	public void setROUTER_IP(String rOUTER_IP) {
		ROUTER_IP = rOUTER_IP;
	}
	
	

}
