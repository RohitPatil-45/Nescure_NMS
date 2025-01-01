package npm.admin.beans;

public class SLAMasterBean {
	
	private String DEVICE_IP;
	private String PERCENTAGE;
	private String COST;
	private String NOTE;
	public String getDEVICE_IP() {
		return DEVICE_IP;
	}
	public void setDEVICE_IP(String dEVICE_IP) {
		DEVICE_IP = dEVICE_IP;
	}
	public String getPERCENTAGE() {
		return PERCENTAGE;
	}
	public void setPERCENTAGE(String pERCENTAGE) {
		PERCENTAGE = pERCENTAGE;
	}
	public String getCOST() {
		return COST;
	}
	public void setCOST(String cOST) {
		COST = cOST;
	}
	public String getNOTE() {
		return NOTE;
	}
	public void setNOTE(String nOTE) {
		NOTE = nOTE;
	}
	@Override
	public String toString() {
		return "SLAMasterBean [DEVICE_IP=" + DEVICE_IP + ", PERCENTAGE=" + PERCENTAGE + ", COST=" + COST + ", NOTE="
				+ NOTE + "]";
	}
	
	
	

}
