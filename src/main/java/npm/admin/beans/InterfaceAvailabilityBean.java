package npm.admin.beans;

import java.sql.Timestamp;

public class InterfaceAvailabilityBean {

	private Long ID;
	
	private String INTERFACE_NAME;
	
	private double UPTIME_PERCENT;
	
	private long UPTIME_MILISECONDS;
	
	private double DOWNTIME_PERCENT;
	
	private long DOWNTIME_MILISECONDS;
	
	private String IP_INTERFACE;
	
	private String EVENT_TIMESTAMP;
	
	private String UPTIME;
	private String DOWNTIME;
	

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
	public double getUPTIME_PERCENT() {
		return UPTIME_PERCENT;
	}
	public void setUPTIME_PERCENT(double uPTIME_PERCENT) {
		UPTIME_PERCENT = uPTIME_PERCENT;
	}
	public long getUPTIME_MILISECONDS() {
		return UPTIME_MILISECONDS;
	}
	public void setUPTIME_MILISECONDS(long uPTIME_MILISECONDS) {
		UPTIME_MILISECONDS = uPTIME_MILISECONDS;
	}
	public double getDOWNTIME_PERCENT() {
		return DOWNTIME_PERCENT;
	}
	public void setDOWNTIME_PERCENT(double dOWNTIME_PERCENT) {
		DOWNTIME_PERCENT = dOWNTIME_PERCENT;
	}
	public long getDOWNTIME_MILISECONDS() {
		return DOWNTIME_MILISECONDS;
	}
	public void setDOWNTIME_MILISECONDS(long dOWNTIME_MILISECONDS) {
		DOWNTIME_MILISECONDS = dOWNTIME_MILISECONDS;
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
	
	

}
