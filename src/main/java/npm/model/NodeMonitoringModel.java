package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NODE_MONITORING")
public class NodeMonitoringModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;

	@Column
	private String NODE_IP;

	@Column
	private String NODE_STATUS;

	@Column
	private Timestamp STATUS_TIMESTAMP;

	@Column
	private Float LATENCY;

	@Column
	private String LATENCY_STATUS;

	@Column
	private String LATENCY_THRESHOLD;

	@Column
	private Timestamp LATENCY_TIMESTAMP;

	@Column
	private Float PACKET_LOSS;

	@Column
	private int MAX_RESPONSE;

	@Column
	private int MIN_RESPONSE;

	public Float getLATENCY() {
		return LATENCY;
	}

	public void setLATENCY(Float lATENCY) {
		LATENCY = lATENCY;
	}

	public Float getPACKET_LOSS() {
		return PACKET_LOSS;
	}

	public void setPACKET_LOSS(Float pACKET_LOSS) {
		PACKET_LOSS = pACKET_LOSS;
	}

	public int getMAX_RESPONSE() {
		return MAX_RESPONSE;
	}

	public void setMAX_RESPONSE(int mAX_RESPONSE) {
		MAX_RESPONSE = mAX_RESPONSE;
	}

	public int getMIN_RESPONSE() {
		return MIN_RESPONSE;
	}

	public void setMIN_RESPONSE(int mIN_RESPONSE) {
		MIN_RESPONSE = mIN_RESPONSE;
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

	public String getNODE_STATUS() {
		return NODE_STATUS;
	}

	public void setNODE_STATUS(String nODE_STATUS) {
		NODE_STATUS = nODE_STATUS;
	}

	public Timestamp getSTATUS_TIMESTAMP() {
		return STATUS_TIMESTAMP;
	}

	public void setSTATUS_TIMESTAMP(Timestamp sTATUS_TIMESTAMP) {
		STATUS_TIMESTAMP = sTATUS_TIMESTAMP;
	}

	public String getLATENCY_STATUS() {
		return LATENCY_STATUS;
	}

	public void setLATENCY_STATUS(String lATENCY_STATUS) {
		LATENCY_STATUS = lATENCY_STATUS;
	}

	public String getLATENCY_THRESHOLD() {
		return LATENCY_THRESHOLD;
	}

	public void setLATENCY_THRESHOLD(String lATENCY_THRESHOLD) {
		LATENCY_THRESHOLD = lATENCY_THRESHOLD;
	}

	public Timestamp getLATENCY_TIMESTAMP() {
		return LATENCY_TIMESTAMP;
	}

	public void setLATENCY_TIMESTAMP(Timestamp lATENCY_TIMESTAMP) {
		LATENCY_TIMESTAMP = lATENCY_TIMESTAMP;
	}

	@Override
	public String toString() {
		return "NodeMonitoringModel [ID=" + ID + ", NODE_IP=" + NODE_IP + ", NODE_STATUS=" + NODE_STATUS
				+ ", STATUS_TIMESTAMP=" + STATUS_TIMESTAMP + ", LATENCY=" + LATENCY + ", LATENCY_STATUS="
				+ LATENCY_STATUS + ", LATENCY_THRESHOLD=" + LATENCY_THRESHOLD + ", LATENCY_TIMESTAMP="
				+ LATENCY_TIMESTAMP + ", PACKET_LOSS=" + PACKET_LOSS + ", MAX_RESPONSE=" + MAX_RESPONSE
				+ ", MIN_RESPONSE=" + MIN_RESPONSE + "]";
	}

//	@Override
//	public String toString() {
//		return "NodeMonitoringModel [ID=" + ID + ", NODE_IP=" + NODE_IP + ", NODE_STATUS=" + NODE_STATUS
//				+ ", STATUS_TIMESTAMP=" + STATUS_TIMESTAMP + ", LATENCY=" + LATENCY + ", LATENCY_STATUS="
//				+ LATENCY_STATUS + ", LATENCY_THRESHOLD=" + LATENCY_THRESHOLD + ", LATENCY_TIMESTAMP="
//				+ LATENCY_TIMESTAMP + ", PACKET_LOSS=" + PACKET_LOSS + "]";
//	}

}
