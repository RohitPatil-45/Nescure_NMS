package npm.admin.beans;

public class LinkAvailabilityReportBean {

	private long ID;
	private String NODE_IP;
	private String UPTIME;
	private String DOWNTIME;
	private String UPTIME_PERCENT;
	private String DOWNTIME_PERCENT;

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

	public String getUPTIME() {
		return UPTIME;
	}

	public void setUPTIME(String uPTIME) {
		UPTIME = uPTIME;
	}

	public String getDOWNTIME() {
		return DOWNTIME;
	}

	public void setDOWNTIME(String dOWNTIME) {
		DOWNTIME = dOWNTIME;
	}

	public String getUPTIME_PERCENT() {
		return UPTIME_PERCENT;
	}

	public void setUPTIME_PERCENT(String uPTIME_PERCENT) {
		UPTIME_PERCENT = uPTIME_PERCENT;
	}

	public String getDOWNTIME_PERCENT() {
		return DOWNTIME_PERCENT;
	}

	public void setDOWNTIME_PERCENT(String dOWNTIME_PERCENT) {
		DOWNTIME_PERCENT = dOWNTIME_PERCENT;
	}

}
