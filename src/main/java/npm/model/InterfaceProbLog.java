package npm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interface_prob_log")
public class InterfaceProbLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Column
	private String DEVICE_IP;
	@Column
	private String INTERFACE_NAME;
	@Column
	private String DATE_TIME;
	@Column
	private String JITTER;
	@Column
	private String LATENCY;
	@Column
	private String IP_INTERFACE;
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
	public String getINTERFACE_NAME() {
		return INTERFACE_NAME;
	}
	public void setINTERFACE_NAME(String iNTERFACE_NAME) {
		INTERFACE_NAME = iNTERFACE_NAME;
	}
	public String getDATE_TIME() {
		return DATE_TIME;
	}
	public void setDATE_TIME(String dATE_TIME) {
		DATE_TIME = dATE_TIME;
	}
	public String getJITTER() {
		return JITTER;
	}
	public void setJITTER(String jITTER) {
		JITTER = jITTER;
	}
	public String getLATENCY() {
		return LATENCY;
	}
	public void setLATENCY(String lATENCY) {
		LATENCY = lATENCY;
	}
	public String getIP_INTERFACE() {
		return IP_INTERFACE;
	}
	public void setIP_INTERFACE(String iP_INTERFACE) {
		IP_INTERFACE = iP_INTERFACE;
	}
	@Override
	public String toString() {
		return "InterfaceProbLog [ID=" + ID + ", DEVICE_IP=" + DEVICE_IP + ", INTERFACE_NAME=" + INTERFACE_NAME
				+ ", DATE_TIME=" + DATE_TIME + ", JITTER=" + JITTER + ", LATENCY=" + LATENCY + ", IP_INTERFACE="
				+ IP_INTERFACE + "]";
	}
	
	
	
}
