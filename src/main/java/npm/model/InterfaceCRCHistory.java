package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INTERFACE_CRC_HISTORY" )
public class InterfaceCRCHistory {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private String INTERFACE_NAME;
	@Column
	private long OLD_CRC_VAL;
	@Column
	private long NEW_CRC_VAL;
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
	public long getOLD_CRC_VAL() {
		return OLD_CRC_VAL;
	}
	public void setOLD_CRC_VAL(long oLD_CRC_VAL) {
		OLD_CRC_VAL = oLD_CRC_VAL;
	}
	public long getNEW_CRC_VAL() {
		return NEW_CRC_VAL;
	}
	public void setNEW_CRC_VAL(long nEW_CRC_VAL) {
		NEW_CRC_VAL = nEW_CRC_VAL;
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
	
	
}
