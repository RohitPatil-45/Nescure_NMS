package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADD_NODE")
public class AddNodeModel implements Serializable {

	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;

	private String DEVICE_IP;
	private String DEVICE_NAME;
	private String DEVICE_TYPE;
	private String GROUP_NAME;
	private String OEM;
	private String SNMP;
	private String SNMP_STATUS;
	private String SERVICE_PROVIDER;
	private String COMPANY;
	private String STATE;
	private String ZONE;
	private String DISTRICT;
	private String LOCATION;
	private int CPU_THRESHOLD;
	private int MEMORY_THRESHOLD;
	private int LATENCY_THRESHOLD;
	private String MONITORING_PARAM;
	private String MAIL_ALERT;
	private String SMS_ALERT;
	private String AUTO_TICKETING;
	private String DEVICE_MAC;
	
	
	public String getOEM() {
		return OEM;
	}
	public void setOEM(String oEM) {
		OEM = oEM;
	}
	public String getSNMP_STATUS() {
		return SNMP_STATUS;
	}
	public void setSNMP_STATUS(String sNMP_STATUS) {
		SNMP_STATUS = sNMP_STATUS;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getDEVICE_IP() {
		return DEVICE_IP;
	}
	public void setDEVICE_IP(String dEVICE_IP) {
		DEVICE_IP = dEVICE_IP;
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
	public int getCPU_THRESHOLD() {
		return CPU_THRESHOLD;
	}
	public void setCPU_THRESHOLD(int cPU_THRESHOLD) {
		CPU_THRESHOLD = cPU_THRESHOLD;
	}
	public int getMEMORY_THRESHOLD() {
		return MEMORY_THRESHOLD;
	}
	public void setMEMORY_THRESHOLD(int mEMORY_THRESHOLD) {
		MEMORY_THRESHOLD = mEMORY_THRESHOLD;
	}
	public int getLATENCY_THRESHOLD() {
		return LATENCY_THRESHOLD;
	}
	public void setLATENCY_THRESHOLD(int lATENCY_THRESHOLD) {
		LATENCY_THRESHOLD = lATENCY_THRESHOLD;
	}
	public String getMONITORING_PARAM() {
		return MONITORING_PARAM;
	}
	public void setMONITORING_PARAM(String mONITORING_PARAM) {
		MONITORING_PARAM = mONITORING_PARAM;
	}
	public String getMAIL_ALERT() {
		return MAIL_ALERT;
	}
	public void setMAIL_ALERT(String mAIL_ALERT) {
		MAIL_ALERT = mAIL_ALERT;
	}
	public String getSMS_ALERT() {
		return SMS_ALERT;
	}
	public void setSMS_ALERT(String sMS_ALERT) {
		SMS_ALERT = sMS_ALERT;
	}
	public String getAUTO_TICKETING() {
		return AUTO_TICKETING;
	}
	public void setAUTO_TICKETING(String aUTO_TICKETING) {
		AUTO_TICKETING = aUTO_TICKETING;
	}
	
	
	public String getDEVICE_MAC() {
		return DEVICE_MAC;
	}
	public void setDEVICE_MAC(String dEVICE_MAC) {
		DEVICE_MAC = dEVICE_MAC;
	}
	@Override
	public String toString() {
		return "AddNodeModel [ID=" + ID + ", DEVICE_IP=" + DEVICE_IP + ", DEVICE_NAME=" + DEVICE_NAME + ", DEVICE_TYPE="
				+ DEVICE_TYPE + ", GROUP_NAME=" + GROUP_NAME + ", OEM=" + OEM + ", SNMP=" + SNMP + ", SNMP_STATUS="
				+ SNMP_STATUS + ", SERVICE_PROVIDER=" + SERVICE_PROVIDER + ", COMPANY=" + COMPANY + ", STATE=" + STATE
				+ ", ZONE=" + ZONE + ", DISTRICT=" + DISTRICT + ", LOCATION=" + LOCATION + ", CPU_THRESHOLD="
				+ CPU_THRESHOLD + ", MEMORY_THRESHOLD=" + MEMORY_THRESHOLD + ", LATENCY_THRESHOLD=" + LATENCY_THRESHOLD
				+ ", MONITORING_PARAM=" + MONITORING_PARAM + ", MAIL_ALERT=" + MAIL_ALERT + ", SMS_ALERT=" + SMS_ALERT
				+ ", AUTO_TICKETING=" + AUTO_TICKETING + ", DEVICE_MAC=" + DEVICE_MAC + "]";
	}
	
	

	
	
	
}
