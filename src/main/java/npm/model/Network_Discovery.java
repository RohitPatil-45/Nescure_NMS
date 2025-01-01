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
@Table(name = "network_discovery")
public class Network_Discovery implements Serializable{
	
	
	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	
	private String GROUP_NAME;
	private Timestamp EVENT_TIMESTAMP;
	private String MAC_ID;
	private String NODE_IP;
	private String NODE_NAME;
	private String OEM;
	private String OS_TYPE;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getGROUP_NAME() {
		return GROUP_NAME;
	}
	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}
	public Timestamp getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(Timestamp eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	public String getMAC_ID() {
		return MAC_ID;
	}
	public void setMAC_ID(String mAC_ID) {
		MAC_ID = mAC_ID;
	}
	public String getNODE_IP() {
		return NODE_IP;
	}
	public void setNODE_IP(String nODE_IP) {
		NODE_IP = nODE_IP;
	}
	public String getNODE_NAME() {
		return NODE_NAME;
	}
	public void setNODE_NAME(String nODE_NAME) {
		NODE_NAME = nODE_NAME;
	}
	public String getOEM() {
		return OEM;
	}
	public void setOEM(String oEM) {
		OEM = oEM;
	}
	public String getOS_TYPE() {
		return OS_TYPE;
	}
	public void setOS_TYPE(String oS_TYPE) {
		OS_TYPE = oS_TYPE;
	}
	@Override
	public String toString() {
		return "Network_Discovery [ID=" + ID + ", GROUP_NAME=" + GROUP_NAME + ", EVENT_TIMESTAMP=" + EVENT_TIMESTAMP
				+ ", MAC_ID=" + MAC_ID + ", NODE_IP=" + NODE_IP + ", NODE_NAME=" + NODE_NAME + ", OEM=" + OEM
				+ ", OS_TYPE=" + OS_TYPE + "]";
	}
	
	
}
