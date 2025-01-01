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
@Table(name = "device_alert_log")
public class DeviceAlertLog implements Serializable{
	
	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SR_NO", updatable = false, nullable = false)
	private Long ID;

	private String NODE_IP;
	private String ALERT_ID;
	private String ALERT_MSG;
	private String ALERT_TIMESTAMP;
	private Timestamp EVENT_TIMESTAMP;
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
	public String getALERT_ID() {
		return ALERT_ID;
	}
	public void setALERT_ID(String aLERT_ID) {
		ALERT_ID = aLERT_ID;
	}
	public String getALERT_MSG() {
		return ALERT_MSG;
	}
	public void setALERT_MSG(String aLERT_MSG) {
		ALERT_MSG = aLERT_MSG;
	}
	public String getALERT_TIMESTAMP() {
		return ALERT_TIMESTAMP;
	}
	public void setALERT_TIMESTAMP(String aLERT_TIMESTAMP) {
		ALERT_TIMESTAMP = aLERT_TIMESTAMP;
	}
	public Timestamp getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(Timestamp eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DeviceAlertLog [ID=" + ID + ", NODE_IP=" + NODE_IP + ", ALERT_ID=" + ALERT_ID + ", ALERT_MSG="
				+ ALERT_MSG + ", ALERT_TIMESTAMP=" + ALERT_TIMESTAMP + ", EVENT_TIMESTAMP=" + EVENT_TIMESTAMP + "]";
	}
	
	

}
