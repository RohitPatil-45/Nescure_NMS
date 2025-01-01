package npm.model;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SCHEDULE_CONFIGURATION")
public class ScheduleConfiguration implements Serializable {
	
	private static final long serialVersionUID = -2264642949863409860L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	
	private String BACKUP_PATH;
	private String CONFIGURATION_TYPE;
	private String BACKUP_SCHEDULE;
	private String TIME_PICKER;
	private String STATUS;
	private String NODE_IP;
	private String TIMESTAMP;
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
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getBACKUP_PATH() {
		return BACKUP_PATH;
	}
	public void setBACKUP_PATH(String bACKUP_PATH) {
		BACKUP_PATH = bACKUP_PATH;
	}
	public String getCONFIGURATION_TYPE() {
		return CONFIGURATION_TYPE;
	}
	public void setCONFIGURATION_TYPE(String cONFIGURATION_TYPE) {
		CONFIGURATION_TYPE = cONFIGURATION_TYPE;
	}
	public String getBACKUP_SCHEDULE() {
		return BACKUP_SCHEDULE;
	}
	public void setBACKUP_SCHEDULE(String bACKUP_SCHEDULE) {
		BACKUP_SCHEDULE = bACKUP_SCHEDULE;
	}
	public String getTIME_PICKER() {
		return TIME_PICKER;
	}
	public void setTIME_PICKER(String datepicker) {
		TIME_PICKER = datepicker;
	}
	public String getNODE_IP() {
		return NODE_IP;
	}
	public void setNODE_IP(String nODE_IP) {
		NODE_IP = nODE_IP;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ScheduleConfiguration [ID=" + ID + ", BACKUP_PATH=" + BACKUP_PATH + ", CONFIGURATION_TYPE="
				+ CONFIGURATION_TYPE + ", BACKUP_SCHEDULE=" + BACKUP_SCHEDULE + ", TIME_PICKER=" + TIME_PICKER
				+ ", NODE_IP=" + NODE_IP + "]";
	}
}
