package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LATENCY_HISTORY" )
public class LatencyHisotryModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private double MIN_LATENCY;
	@Column
	private double MAX_LATENCY;
	@Column
	private double AVG_LATENCY;
	@Column
	private double PACKET_LOSS;
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
	public double getMIN_LATENCY() {
		return MIN_LATENCY;
	}
	public void setMIN_LATENCY(double mIN_LATENCY) {
		MIN_LATENCY = mIN_LATENCY;
	}
	public double getMAX_LATENCY() {
		return MAX_LATENCY;
	}
	public void setMAX_LATENCY(double mAX_LATENCY) {
		MAX_LATENCY = mAX_LATENCY;
	}
	public double getAVG_LATENCY() {
		return AVG_LATENCY;
	}
	public void setAVG_LATENCY(double aVG_LATENCY) {
		AVG_LATENCY = aVG_LATENCY;
	}
	public double getPACKET_LOSS() {
		return PACKET_LOSS;
	}
	public void setPACKET_LOSS(double pACKET_LOSS) {
		PACKET_LOSS = pACKET_LOSS;
	}
	public Timestamp getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(Timestamp eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	
	
	
	

}
