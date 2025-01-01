package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INTERFACE_STATUS_HISTORY" )
public class InterfaceStatusHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private String INTERFACE_NAME;
	@Column
	private String INTERFACE_STATUS_TYPE;
	@Column
	private String INTERFACE_STATUS;
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
	public String getINTERFACE_STATUS_TYPE() {
		return INTERFACE_STATUS_TYPE;
	}
	public void setINTERFACE_STATUS_TYPE(String iNTERFACE_STATUS_TYPE) {
		INTERFACE_STATUS_TYPE = iNTERFACE_STATUS_TYPE;
	}
	public String getINTERFACE_STATUS() {
		return INTERFACE_STATUS;
	}
	public void setINTERFACE_STATUS(String iNTERFACE_STATUS) {
		INTERFACE_STATUS = iNTERFACE_STATUS;
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
		return "InterfaceStatusHistory [ID=" + ID + ", NODE_IP=" + NODE_IP + ", INTERFACE_NAME=" + INTERFACE_NAME
				+ ", INTERFACE_STATUS_TYPE=" + INTERFACE_STATUS_TYPE + ", INTERFACE_STATUS=" + INTERFACE_STATUS
				+ ", IP_INTERFACE=" + IP_INTERFACE + ", EVENT_TIMESTAMP=" + EVENT_TIMESTAMP + "]";
	}
	
	
	
}
