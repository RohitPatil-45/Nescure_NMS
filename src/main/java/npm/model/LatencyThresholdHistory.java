package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LATENCY_THRESHOLD_HISTORY" )
public class LatencyThresholdHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private int LATENCY_THRESHOLD;
	@Column
	private double LATENCY_VAL;
	@Column
	private String LATENCY_STATUS;
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
	public int getLATENCY_THRESHOLD() {
		return LATENCY_THRESHOLD;
	}
	public void setLATENCY_THRESHOLD(int lATENCY_THRESHOLD) {
		LATENCY_THRESHOLD = lATENCY_THRESHOLD;
	}
	public double getLATENCY_VAL() {
		return LATENCY_VAL;
	}
	public void setLATENCY_VAL(double lATENCY_VAL) {
		LATENCY_VAL = lATENCY_VAL;
	}
	public String getLATENCY_STATUS() {
		return LATENCY_STATUS;
	}
	public void setLATENCY_STATUS(String lATENCY_STATUS) {
		LATENCY_STATUS = lATENCY_STATUS;
	}
	public Timestamp getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(Timestamp eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	@Override
	public String toString() {
		return "LatencyThresholdHistory [ID=" + ID + ", NODE_IP=" + NODE_IP + ", LATENCY_THRESHOLD=" + LATENCY_THRESHOLD
				+ ", LATENCY_VAL=" + LATENCY_VAL + ", LATENCY_STATUS=" + LATENCY_STATUS + ", EVENT_TIMESTAMP="
				+ EVENT_TIMESTAMP + "]";
	}
	
		
	
	
}
