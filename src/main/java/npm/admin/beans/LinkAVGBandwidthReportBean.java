package npm.admin.beans;

public class LinkAVGBandwidthReportBean {

	private long ID;
	private String NODE_IP;
	private String AVG_IN_TRAFIC;
	private String AVG_OUT_TRAFIC;

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

	public String getAVG_IN_TRAFIC() {
		return AVG_IN_TRAFIC;
	}

	public void setAVG_IN_TRAFIC(String aVG_IN_TRAFIC) {
		AVG_IN_TRAFIC = aVG_IN_TRAFIC;
	}

	public String getAVG_OUT_TRAFIC() {
		return AVG_OUT_TRAFIC;
	}

	public void setAVG_OUT_TRAFIC(String aVG_OUT_TRAFIC) {
		AVG_OUT_TRAFIC = aVG_OUT_TRAFIC;
	}

}
