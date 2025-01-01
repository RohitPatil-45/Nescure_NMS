package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drive_threshold_log")
public class DRIVE_THRESHOLD_LOG {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	
	@Column(name = "DEVICE_NAME")
	private String DEVICE_NAME;
	
	@Column(name = "DEVICE_IP")
	private String DEVICE_IP;
	
	@Column(name = "USER_NAME")
	private String USER_NAME;
	
	@Column(name = "DRIVES_NAME")
	private String DRIVES_NAME;
	
	@Column(name = "STATUS")
	private String STATUS;
	
	@Column(name = "EVENT_TIME")
	private Timestamp EVENT_TIME;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getDEVICE_NAME() {
		return DEVICE_NAME;
	}

	public void setDEVICE_NAME(String dEVICE_NAME) {
		DEVICE_NAME = dEVICE_NAME;
	}

	public String getDEVICE_IP() {
		return DEVICE_IP;
	}

	public void setDEVICE_IP(String dEVICE_IP) {
		DEVICE_IP = dEVICE_IP;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}

	public String getDRIVES_NAME() {
		return DRIVES_NAME;
	}

	public void setDRIVES_NAME(String dRIVES_NAME) {
		DRIVES_NAME = dRIVES_NAME;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public Timestamp getEVENT_TIME() {
		return EVENT_TIME;
	}

	public void setEVENT_TIME(Timestamp eVENT_TIME) {
		EVENT_TIME = eVENT_TIME;
	}
	
	

}
