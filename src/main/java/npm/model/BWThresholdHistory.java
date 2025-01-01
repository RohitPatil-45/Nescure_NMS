package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BW_THRESHOLD_HISTORY" )
public class BWThresholdHistory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private String INTERFACE_NAME;
	@Column
	private double BW_THRESHOLD;
	@Column
	private double BW_VAL;
	@Column
	private String BW_STATUS;
	@Column
	private String BW_TYPE;
	@Column
	private String IP_INTERFACE;
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
	public String getINTERFACE_NAME() {
		return INTERFACE_NAME;
	}
	public void setINTERFACE_NAME(String iNTERFACE_NAME) {
		INTERFACE_NAME = iNTERFACE_NAME;
	}
	public double getBW_THRESHOLD() {
		return BW_THRESHOLD;
	}
	public void setBW_THRESHOLD(double bW_THRESHOLD) {
		BW_THRESHOLD = bW_THRESHOLD;
	}
	public double getBW_VAL() {
		return BW_VAL;
	}
	public void setBW_VAL(double bW_VAL) {
		BW_VAL = bW_VAL;
	}
	public String getBW_STATUS() {
		return BW_STATUS;
	}
	public void setBW_STATUS(String bW_STATUS) {
		BW_STATUS = bW_STATUS;
	}
	public String getIP_INTERFACE() {
		return IP_INTERFACE;
	}
	public void setIP_INTERFACE(String iP_INTERFACE) {
		IP_INTERFACE = iP_INTERFACE;
	}
	public Timestamp getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(Timestamp eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	@Override
	public String toString() {
		return "BWThresholdHistory [ID=" + ID + ", NODE_IP=" + NODE_IP + ", INTERFACE_NAME=" + INTERFACE_NAME
				+ ", BW_THRESHOLD=" + BW_THRESHOLD + ", BW_VAL=" + BW_VAL + ", BW_STATUS=" + BW_STATUS
				+ ", IP_INTERFACE=" + IP_INTERFACE + ", EVENT_TIMESTAMP=" + EVENT_TIMESTAMP + "]";
	}
	
	

}
