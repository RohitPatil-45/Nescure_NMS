package npm.admin.beans;

public class LinkBandwidthHistoryReporttBean {

	private long ID;
	private String DEVICE_IP;
	private String INTERFACE_NAME;
	private String INTERFACE_IP;
	private String INTERFACE_STATUS;
	private String IN_TRAFFIC;
	private String OUT_TRAFFIC;
	private String WORKING_HOUR_FLAG;
	private String TIMESTAMP;
	private String TIMESTAMP_EPOCH;
	private String IP_INTERFACE_NAME;

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

	public String getINTERFACE_NAME() {
		return INTERFACE_NAME;
	}

	public void setINTERFACE_NAME(String iNTERFACE_NAME) {
		INTERFACE_NAME = iNTERFACE_NAME;
	}

	public String getINTERFACE_IP() {
		return INTERFACE_IP;
	}

	public void setINTERFACE_IP(String iNTERFACE_IP) {
		INTERFACE_IP = iNTERFACE_IP;
	}

	public String getINTERFACE_STATUS() {
		return INTERFACE_STATUS;
	}

	public void setINTERFACE_STATUS(String iNTERFACE_STATUS) {
		INTERFACE_STATUS = iNTERFACE_STATUS;
	}

	public String getIN_TRAFFIC() {
		return IN_TRAFFIC;
	}

	public void setIN_TRAFFIC(String iN_TRAFFIC) {
		IN_TRAFFIC = iN_TRAFFIC;
	}

	public String getOUT_TRAFFIC() {
		return OUT_TRAFFIC;
	}

	public void setOUT_TRAFFIC(String oUT_TRAFFIC) {
		OUT_TRAFFIC = oUT_TRAFFIC;
	}

	public String getWORKING_HOUR_FLAG() {
		return WORKING_HOUR_FLAG;
	}

	public void setWORKING_HOUR_FLAG(String wORKING_HOUR_FLAG) {
		WORKING_HOUR_FLAG = wORKING_HOUR_FLAG;
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

	public String getIP_INTERFACE_NAME() {
		return IP_INTERFACE_NAME;
	}

	public void setIP_INTERFACE_NAME(String iP_INTERFACE_NAME) {
		IP_INTERFACE_NAME = iP_INTERFACE_NAME;
	}

}
