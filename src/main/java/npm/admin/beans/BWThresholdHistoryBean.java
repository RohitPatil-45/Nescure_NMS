package npm.admin.beans;

import java.sql.Timestamp;

public class BWThresholdHistoryBean {

	private Long ID;	

	private String INTERFACE_NAME;	
	private double BW_THRESHOLD;	
	private String BW_VAL;
//	private double BW_VAL;	
	private String BW_STATUS;	
	private String BW_TYPE;
	private String IP_INTERFACE;
	private String EVENT_TIMESTAMP;

	private String NODE_IP;
	private String DEVICE_NAME;
	private String DEVICE_TYPE;
	private String GROUP_NAME;
	private String SNMP;
	private String SERVICE_PROVIDER;
	private String COMPANY;
	private String STATE;
	private String ZONE;
	private String DISTRICT;
	private String LOCATION;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getINTERFACE_NAME() {
		return INTERFACE_NAME;
	}
	public void setINTERFACE_NAME(String iNTERFACE_NAME) {
		INTERFACE_NAME = iNTERFACE_NAME;
	}
	public double getBW_THRESHOLD() {
		return BW_THRESHOLD;
	}
	public void setBW_THRESHOLD(double bW_THRESHOLD) {
		BW_THRESHOLD = bW_THRESHOLD;
	}
//	public double getBW_VAL() {
//		return BW_VAL;
//	}
//	public void setBW_VAL(double bW_VAL) {
//		BW_VAL = bW_VAL;
//	}
	
	// 04-11-2023
	public String getBW_VAL() {
		return BW_VAL;
	}
	public void setBW_VAL(String bW_VAL) {
		BW_VAL = bW_VAL;
	}
	// 04-11-2023
	public String getBW_STATUS() {
		return BW_STATUS;
	}
	public void setBW_STATUS(String bW_STATUS) {
		BW_STATUS = bW_STATUS;
	}
	public String getIP_INTERFACE() {
		return IP_INTERFACE;
	}
	public void setIP_INTERFACE(String iP_INTERFACE) {
		IP_INTERFACE = iP_INTERFACE;
	}
	public String getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(String eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	public String getNODE_IP() {
		return NODE_IP;
	}
	public void setNODE_IP(String nODE_IP) {
		NODE_IP = nODE_IP;
	}
	public String getDEVICE_NAME() {
		return DEVICE_NAME;
	}
	public void setDEVICE_NAME(String dEVICE_NAME) {
		DEVICE_NAME = dEVICE_NAME;
	}
	public String getDEVICE_TYPE() {
		return DEVICE_TYPE;
	}
	public void setDEVICE_TYPE(String dEVICE_TYPE) {
		DEVICE_TYPE = dEVICE_TYPE;
	}
	public String getGROUP_NAME() {
		return GROUP_NAME;
	}
	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}
	public String getSNMP() {
		return SNMP;
	}
	public void setSNMP(String sNMP) {
		SNMP = sNMP;
	}
	public String getSERVICE_PROVIDER() {
		return SERVICE_PROVIDER;
	}
	public void setSERVICE_PROVIDER(String sERVICE_PROVIDER) {
		SERVICE_PROVIDER = sERVICE_PROVIDER;
	}
	public String getCOMPANY() {
		return COMPANY;
	}
	public void setCOMPANY(String cOMPANY) {
		COMPANY = cOMPANY;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getZONE() {
		return ZONE;
	}
	public void setZONE(String zONE) {
		ZONE = zONE;
	}
	public String getDISTRICT() {
		return DISTRICT;
	}
	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	public String getBW_TYPE() {
		return BW_TYPE;
	}
	public void setBW_TYPE(String bW_TYPE) {
		BW_TYPE = bW_TYPE;
	}
	
	

}
