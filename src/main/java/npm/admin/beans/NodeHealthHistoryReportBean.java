package npm.admin.beans;

import java.sql.Timestamp;

public class NodeHealthHistoryReportBean {
	
	private Long ID;
	private String NODE_IP;	
	private double CPU_UTILIZATION;	
	private double MEMORY_UTILIZATION;	
	
	private double TOTAL_MEMORY;
	private double USED_MEMORY;
	private double FREE_MEMORY;
	
	private double TEMPERATURE;
	
	private String EVENT_TIMESTAMP;
	
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
	public String getNODE_IP() {
		return NODE_IP;
	}
	public void setNODE_IP(String nODE_IP) {
		NODE_IP = nODE_IP;
	}
	public double getCPU_UTILIZATION() {
		return CPU_UTILIZATION;
	}
	public void setCPU_UTILIZATION(double cPU_UTILIZATION) {
		CPU_UTILIZATION = cPU_UTILIZATION;
	}
	public double getMEMORY_UTILIZATION() {
		return MEMORY_UTILIZATION;
	}
	public void setMEMORY_UTILIZATION(double mEMORY_UTILIZATION) {
		MEMORY_UTILIZATION = mEMORY_UTILIZATION;
	}
	public double getTEMPERATURE() {
		return TEMPERATURE;
	}
	public void setTEMPERATURE(double tEMPERATURE) {
		TEMPERATURE = tEMPERATURE;
	}
	public String getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(String eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
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
	public double getTOTAL_MEMORY() {
		return TOTAL_MEMORY;
	}
	public void setTOTAL_MEMORY(double tOTAL_MEMORY) {
		TOTAL_MEMORY = tOTAL_MEMORY;
	}
	public double getUSED_MEMORY() {
		return USED_MEMORY;
	}
	public void setUSED_MEMORY(double uSED_MEMORY) {
		USED_MEMORY = uSED_MEMORY;
	}
	public double getFREE_MEMORY() {
		return FREE_MEMORY;
	}
	public void setFREE_MEMORY(double fREE_MEMORY) {
		FREE_MEMORY = fREE_MEMORY;
	}
	
	
	

}
