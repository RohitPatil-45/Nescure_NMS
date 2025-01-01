package npm.admin.beans;

public class LinkLatencyReportBean {

	private long ID;
	private String DEVICE_IP;
	private String STATUS;
	private String TIMESTAMP;
	private String TIMESTAMP_EPOCH;
	private String LATENCY;
	private String PACKETDROP;
	private String WORKING_HOUR_FLAG;
	private String JITTER;
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

	public String getDEVICE_IP() {
		return DEVICE_IP;
	}

	public void setDEVICE_IP(String dEVICE_IP) {
		DEVICE_IP = dEVICE_IP;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getTIMESTAMP() {
		return TIMESTAMP;
	}

	public void setTIMESTAMP(String tIMESTAMP) {
		TIMESTAMP = tIMESTAMP;
	}

	public String getTIMESTAMP_EPOCH() {
		return TIMESTAMP_EPOCH;
	}

	public void setTIMESTAMP_EPOCH(String tIMESTAMP_EPOCH) {
		TIMESTAMP_EPOCH = tIMESTAMP_EPOCH;
	}

	public String getLATENCY() {
		return LATENCY;
	}

	public void setLATENCY(String lATENCY) {
		LATENCY = lATENCY;
	}

	public String getPACKETDROP() {
		return PACKETDROP;
	}

	public void setPACKETDROP(String pACKETDROP) {
		PACKETDROP = pACKETDROP;
	}

	public String getWORKING_HOUR_FLAG() {
		return WORKING_HOUR_FLAG;
	}

	public void setWORKING_HOUR_FLAG(String wORKING_HOUR_FLAG) {
		WORKING_HOUR_FLAG = wORKING_HOUR_FLAG;
	}

	public String getJITTER() {
		return JITTER;
	}

	public void setJITTER(String jITTER) {
		JITTER = jITTER;
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
