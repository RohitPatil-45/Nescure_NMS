package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MEMORY_THRESHOLD_HISTORY" )
public class MemoryThresholdHistory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private double MEMORY_UTILIZATION;
	@Column
	private double MEMORY_THRESHOLD;
	@Column
	private String MEMORY_STATUS;
	@Column
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
	public double getMEMORY_UTILIZATION() {
		return MEMORY_UTILIZATION;
	}
	public void setMEMORY_UTILIZATION(double mEMORY_UTILIZATION) {
		MEMORY_UTILIZATION = mEMORY_UTILIZATION;
	}
	public double getMEMORY_THRESHOLD() {
		return MEMORY_THRESHOLD;
	}
	public void setMEMORY_THRESHOLD(double mEMORY_THRESHOLD) {
		MEMORY_THRESHOLD = mEMORY_THRESHOLD;
	}
	public String getMEMORY_STATUS() {
		return MEMORY_STATUS;
	}
	public void setMEMORY_STATUS(String mEMORY_STATUS) {
		MEMORY_STATUS = mEMORY_STATUS;
	}
	public Timestamp getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(Timestamp eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	
	
	
}
