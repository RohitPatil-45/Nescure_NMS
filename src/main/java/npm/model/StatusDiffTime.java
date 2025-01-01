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
@Table(name = "STATUS_DIFF_TIME")
public class StatusDiffTime implements Serializable{
	
	private static final long serialVersionUID = -2264642949863409860L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	private String DEVICE_IP;
	private Timestamp DOWN_EVENT_TIME;
	private Timestamp UP_EVENT_TIME;
	private String EVENT_DIFFERENCE;
	private Long EVENT_DIFFERENCE_SECOND;
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
	public Timestamp getDOWN_EVENT_TIME() {
		return DOWN_EVENT_TIME;
	}
	public void setDOWN_EVENT_TIME(Timestamp dOWN_EVENT_TIME) {
		DOWN_EVENT_TIME = dOWN_EVENT_TIME;
	}
	public Timestamp getUP_EVENT_TIME() {
		return UP_EVENT_TIME;
	}
	public void setUP_EVENT_TIME(Timestamp uP_EVENT_TIME) {
		UP_EVENT_TIME = uP_EVENT_TIME;
	}
	public String getEVENT_DIFFERENCE() {
		return EVENT_DIFFERENCE;
	}
	public void setEVENT_DIFFERENCE(String eVENT_DIFFERENCE) {
		EVENT_DIFFERENCE = eVENT_DIFFERENCE;
	}
	public Long getEVENT_DIFFERENCE_SECOND() {
		return EVENT_DIFFERENCE_SECOND;
	}
	public void setEVENT_DIFFERENCE_SECOND(Long eVENT_DIFFERENCE_SECOND) {
		EVENT_DIFFERENCE_SECOND = eVENT_DIFFERENCE_SECOND;
	}


}
