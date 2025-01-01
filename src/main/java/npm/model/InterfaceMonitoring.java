package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INTERFACE_MONITORING")
public class InterfaceMonitoring {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private String INTERFACE_NAME;
	@Column
	private String INTERFACE_ID;
	@Column
	private String INTERFACE_TYPE;
	@Column
	private String ADMIN_STATUS;
	@Column
	private String OPER_STATUS;
	@Column
	private double PROCURED_BANDWIDTH;
	@Column
	private String INTERFACE_MACADDRESS;
	@Column
	private String INTERFACE_IP;
	@Column
	private String ALIAS_NAME;
	@Column
	private String CRC_ERROR;
	@Column
	private String INTERFACE_ERROR;
	@Column
	private String MTU;
	@Column
	private double OUT_TRAFFIC;
	@Column
	private double IN_TRAFFIC;
	@Column
	private String DISCARD_INPUT;

	@Column
	private String DISCARD_OUTPUT;
	@Column
	private String INTERFACE_INPUT_ERROR;
	@Column
	private String INTERFACE_OUTPUT_ERROR;
	@Column
	private String MONITORING_PARAM;
	@Column
	private String MAIL_ALERT;
	@Column
	private String SMS_ALERT;
	@Column
	private String AUTO_TICKETING;
	@Column
	private double BW_THRESHOLD;
	@Column
	private String BW_HISTORY_PARAM;
	@Column
	private String CRC_HISTORY_PARAM;
	@Column
	private Timestamp STATUS_TIMESTAMP;
	@Column
	private double OUT_BW_PERCENT;
	@Column
	private double IN_BW_PERCENT;
	@Column
	private String BW_STATUS;
	@Column
	private String BW_TYPE;
	@Column
	private Timestamp BW_TIMESTAMP;
	@Column
	private String Interface_IP_Assign;
	@Column
	private String Interface_IP_ICMP_Status;
	@Column
	private String Intreface_IP_Monitoring;
	@Column
	private Timestamp Intreface_IP_STATUS_TIME;

	public Timestamp getIntreface_IP_STATUS_TIME() {
		return Intreface_IP_STATUS_TIME;
	}

	public void setIntreface_IP_STATUS_TIME(Timestamp intreface_IP_STATUS_TIME) {
		Intreface_IP_STATUS_TIME = intreface_IP_STATUS_TIME;
	}

	public String getInterface_IP_Assign() {
		return Interface_IP_Assign;
	}

	public void setInterface_IP_Assign(String interface_IP_Assign) {
		Interface_IP_Assign = interface_IP_Assign;
	}

	public String getInterface_IP_ICMP_Status() {
		return Interface_IP_ICMP_Status;
	}

	public void setInterface_IP_ICMP_Status(String interface_IP_ICMP_Status) {
		Interface_IP_ICMP_Status = interface_IP_ICMP_Status;
	}

	public String getIntreface_IP_Monitoring() {
		return Intreface_IP_Monitoring;
	}

	public void setIntreface_IP_Monitoring(String intreface_IP_Monitoring) {
		Intreface_IP_Monitoring = intreface_IP_Monitoring;
	}

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

	public String getINTERFACE_NAME() {
		return INTERFACE_NAME;
	}

	public void setINTERFACE_NAME(String iNTERFACE_NAME) {
		INTERFACE_NAME = iNTERFACE_NAME;
	}

	public String getINTERFACE_ID() {
		return INTERFACE_ID;
	}

	public void setINTERFACE_ID(String iNTERFACE_ID) {
		INTERFACE_ID = iNTERFACE_ID;
	}

	public String getINTERFACE_TYPE() {
		return INTERFACE_TYPE;
	}

	public void setINTERFACE_TYPE(String iNTERFACE_TYPE) {
		INTERFACE_TYPE = iNTERFACE_TYPE;
	}

	public String getADMIN_STATUS() {
		return ADMIN_STATUS;
	}

	public void setADMIN_STATUS(String aDMIN_STATUS) {
		ADMIN_STATUS = aDMIN_STATUS;
	}

	public String getOPER_STATUS() {
		return OPER_STATUS;
	}

	public void setOPER_STATUS(String oPER_STATUS) {
		OPER_STATUS = oPER_STATUS;
	}

	public double getPROCURED_BANDWIDTH() {
		return PROCURED_BANDWIDTH;
	}

	public void setPROCURED_BANDWIDTH(double pROCURED_BANDWIDTH) {
		PROCURED_BANDWIDTH = pROCURED_BANDWIDTH;
	}

	public String getINTERFACE_MACADDRESS() {
		return INTERFACE_MACADDRESS;
	}

	public void setINTERFACE_MACADDRESS(String iNTERFACE_MACADDRESS) {
		INTERFACE_MACADDRESS = iNTERFACE_MACADDRESS;
	}

	public String getINTERFACE_IP() {
		return INTERFACE_IP;
	}

	public void setINTERFACE_IP(String iNTERFACE_IP) {
		INTERFACE_IP = iNTERFACE_IP;
	}

	public String getALIAS_NAME() {
		return ALIAS_NAME;
	}

	public void setALIAS_NAME(String aLIAS_NAME) {
		ALIAS_NAME = aLIAS_NAME;
	}

	public String getCRC_ERROR() {
		return CRC_ERROR;
	}

	public void setCRC_ERROR(String cRC_ERROR) {
		CRC_ERROR = cRC_ERROR;
	}

	public String getMTU() {
		return MTU;
	}

	public void setMTU(String mTU) {
		MTU = mTU;
	}

	public double getOUT_TRAFFIC() {
		return OUT_TRAFFIC;
	}

	public void setOUT_TRAFFIC(double oUT_TRAFFIC) {
		OUT_TRAFFIC = oUT_TRAFFIC;
	}

	public double getIN_TRAFFIC() {
		return IN_TRAFFIC;
	}

	public void setIN_TRAFFIC(double iN_TRAFFIC) {
		IN_TRAFFIC = iN_TRAFFIC;
	}

	public String getDISCARD_INPUT() {
		return DISCARD_INPUT;
	}

	public void setDISCARD_INPUT(String dISCARD_INPUT) {
		DISCARD_INPUT = dISCARD_INPUT;
	}

	public String getDISCARD_OUTPUT() {
		return DISCARD_OUTPUT;
	}

	public void setDISCARD_OUTPUT(String dISCARD_OUTPUT) {
		DISCARD_OUTPUT = dISCARD_OUTPUT;
	}

	public String getINTERFACE_INPUT_ERROR() {
		return INTERFACE_INPUT_ERROR;
	}

	public void setINTERFACE_INPUT_ERROR(String iNTERFACE_INPUT_ERROR) {
		INTERFACE_INPUT_ERROR = iNTERFACE_INPUT_ERROR;
	}

	public String getINTERFACE_OUTPUT_ERROR() {
		return INTERFACE_OUTPUT_ERROR;
	}

	public void setINTERFACE_OUTPUT_ERROR(String iNTERFACE_OUTPUT_ERROR) {
		INTERFACE_OUTPUT_ERROR = iNTERFACE_OUTPUT_ERROR;
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

	public double getBW_THRESHOLD() {
		return BW_THRESHOLD;
	}

	public void setBW_THRESHOLD(double bW_THRESHOLD) {
		BW_THRESHOLD = bW_THRESHOLD;
	}

	public String getBW_HISTORY_PARAM() {
		return BW_HISTORY_PARAM;
	}

	public void setBW_HISTORY_PARAM(String bW_HISTORY_PARAM) {
		BW_HISTORY_PARAM = bW_HISTORY_PARAM;
	}

	public String getCRC_HISTORY_PARAM() {
		return CRC_HISTORY_PARAM;
	}

	public void setCRC_HISTORY_PARAM(String cRC_HISTORY_PARAM) {
		CRC_HISTORY_PARAM = cRC_HISTORY_PARAM;
	}

	public Timestamp getSTATUS_TIMESTAMP() {
		return STATUS_TIMESTAMP;
	}

	public void setSTATUS_TIMESTAMP(Timestamp sTATUS_TIMESTAMP) {
		STATUS_TIMESTAMP = sTATUS_TIMESTAMP;
	}

	public double getOUT_BW_PERCENT() {
		return OUT_BW_PERCENT;
	}

	public void setOUT_BW_PERCENT(double oUT_BW_PERCENT) {
		OUT_BW_PERCENT = oUT_BW_PERCENT;
	}

	public double getIN_BW_PERCENT() {
		return IN_BW_PERCENT;
	}

	public void setIN_BW_PERCENT(double iN_BW_PERCENT) {
		IN_BW_PERCENT = iN_BW_PERCENT;
	}

	public String getBW_STATUS() {
		return BW_STATUS;
	}

	public void setBW_STATUS(String bW_STATUS) {
		BW_STATUS = bW_STATUS;
	}

	public String getBW_TYPE() {
		return BW_TYPE;
	}

	public void setBW_TYPE(String bW_TYPE) {
		BW_TYPE = bW_TYPE;
	}

	public Timestamp getBW_TIMESTAMP() {
		return BW_TIMESTAMP;
	}

	public void setBW_TIMESTAMP(Timestamp bW_TIMESTAMP) {
		BW_TIMESTAMP = bW_TIMESTAMP;
	}

	public String getINTERFACE_ERROR() {
		return INTERFACE_ERROR;
	}

	public void setINTERFACE_ERROR(String iNTERFACE_ERROR) {
		INTERFACE_ERROR = iNTERFACE_ERROR;
	}

	@Override
	public String toString() {
		return "InterfaceMonitoring [ID=" + ID + ", NODE_IP=" + NODE_IP + ", INTERFACE_NAME=" + INTERFACE_NAME
				+ ", INTERFACE_ID=" + INTERFACE_ID + ", INTERFACE_TYPE=" + INTERFACE_TYPE + ", ADMIN_STATUS="
				+ ADMIN_STATUS + ", OPER_STATUS=" + OPER_STATUS + ", PROCURED_BANDWIDTH=" + PROCURED_BANDWIDTH
				+ ", INTERFACE_MACADDRESS=" + INTERFACE_MACADDRESS + ", INTERFACE_IP=" + INTERFACE_IP + ", ALIAS_NAME="
				+ ALIAS_NAME + ", CRC_ERROR=" + CRC_ERROR + ", INTERFACE_ERROR=" + INTERFACE_ERROR + ", MTU=" + MTU
				+ ", OUT_TRAFFIC=" + OUT_TRAFFIC + ", IN_TRAFFIC=" + IN_TRAFFIC + ", DISCARD_INPUT=" + DISCARD_INPUT
				+ ", DISCARD_OUTPUT=" + DISCARD_OUTPUT + ", INTERFACE_INPUT_ERROR=" + INTERFACE_INPUT_ERROR
				+ ", INTERFACE_OUTPUT_ERROR=" + INTERFACE_OUTPUT_ERROR + ", MONITORING_PARAM=" + MONITORING_PARAM
				+ ", MAIL_ALERT=" + MAIL_ALERT + ", SMS_ALERT=" + SMS_ALERT + ", AUTO_TICKETING=" + AUTO_TICKETING
				+ ", BW_THRESHOLD=" + BW_THRESHOLD + ", BW_HISTORY_PARAM=" + BW_HISTORY_PARAM + ", CRC_HISTORY_PARAM="
				+ CRC_HISTORY_PARAM + ", STATUS_TIMESTAMP=" + STATUS_TIMESTAMP + ", OUT_BW_PERCENT=" + OUT_BW_PERCENT
				+ ", IN_BW_PERCENT=" + IN_BW_PERCENT + ", BW_STATUS=" + BW_STATUS + ", BW_TYPE=" + BW_TYPE
				+ ", BW_TIMESTAMP=" + BW_TIMESTAMP + ", Interface_IP_Assign=" + Interface_IP_Assign
				+ ", Interface_IP_ICMP_Status=" + Interface_IP_ICMP_Status + ", Intreface_IP_Monitoring="
				+ Intreface_IP_Monitoring + ", Intreface_IP_STATUS_TIME=" + Intreface_IP_STATUS_TIME + "]";
	}

}
