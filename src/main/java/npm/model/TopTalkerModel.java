package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOP_TALKER")
public class TopTalkerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private String INTERFACE_NAME;
	@Column
	private String SOURCE_IP;
	@Column
	private String DESTINATION_IP;
	@Column
	private String NEXT_HOP;
	@Column
	private String SOURCE_INTERFACE;
	@Column
	private String DESTINATION_INTERFACE;
	@Column
	private double DESTINATION_PACKETS;
	@Column
	private double DESTINATION_OCTECT;
	@Column
	private String SOURCE_PORT;
	@Column
	private String DESTINATION_PORT;
	@Column
	private String PROTOCOL;
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
	public String getSOURCE_IP() {
		return SOURCE_IP;
	}
	public void setSOURCE_IP(String sOURCE_IP) {
		SOURCE_IP = sOURCE_IP;
	}
	public String getDESTINATION_IP() {
		return DESTINATION_IP;
	}
	public void setDESTINATION_IP(String dESTINATION_IP) {
		DESTINATION_IP = dESTINATION_IP;
	}
	public String getNEXT_HOP() {
		return NEXT_HOP;
	}
	public void setNEXT_HOP(String nEXT_HOP) {
		NEXT_HOP = nEXT_HOP;
	}
	public String getSOURCE_INTERFACE() {
		return SOURCE_INTERFACE;
	}
	public void setSOURCE_INTERFACE(String sOURCE_INTERFACE) {
		SOURCE_INTERFACE = sOURCE_INTERFACE;
	}
	public String getDESTINATION_INTERFACE() {
		return DESTINATION_INTERFACE;
	}
	public void setDESTINATION_INTERFACE(String dESTINATION_INTERFACE) {
		DESTINATION_INTERFACE = dESTINATION_INTERFACE;
	}
	public double getDESTINATION_PACKETS() {
		return DESTINATION_PACKETS;
	}
	public void setDESTINATION_PACKETS(double dESTINATION_PACKETS) {
		DESTINATION_PACKETS = dESTINATION_PACKETS;
	}
	public double getDESTINATION_OCTECT() {
		return DESTINATION_OCTECT;
	}
	public void setDESTINATION_OCTECT(double dESTINATION_OCTECT) {
		DESTINATION_OCTECT = dESTINATION_OCTECT;
	}
	public String getSOURCE_PORT() {
		return SOURCE_PORT;
	}
	public void setSOURCE_PORT(String sOURCE_PORT) {
		SOURCE_PORT = sOURCE_PORT;
	}
	public String getDESTINATION_PORT() {
		return DESTINATION_PORT;
	}
	public void setDESTINATION_PORT(String dESTINATION_PORT) {
		DESTINATION_PORT = dESTINATION_PORT;
	}
	public String getPROTOCOL() {
		return PROTOCOL;
	}
	public void setPROTOCOL(String pROTOCOL) {
		PROTOCOL = pROTOCOL;
	}
	public Timestamp getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(Timestamp eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	
	@Override
	public String toString() {
		return "TopTalkerModel [ID=" + ID + ", NODE_IP=" + NODE_IP + ", INTERFACE_NAME=" + INTERFACE_NAME
				+ ", SOURCE_IP=" + SOURCE_IP + ", DESTINATION_IP=" + DESTINATION_IP + ", NEXT_HOP=" + NEXT_HOP
				+ ", SOURCE_INTERFACE=" + SOURCE_INTERFACE + ", DESTINATION_INTERFACE=" + DESTINATION_INTERFACE
				+ ", DESTINATION_PACKETS=" + DESTINATION_PACKETS + ", DESTINATION_OCTECT=" + DESTINATION_OCTECT
				+ ", SOURCE_PORT=" + SOURCE_PORT + ", DESTINATION_PORT=" + DESTINATION_PORT + ", PROTOCOL=" + PROTOCOL
				+ ", EVENT_TIMESTAMP=" + EVENT_TIMESTAMP + "]";
	}

	

}
