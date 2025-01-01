package npm.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="push_configuration" )
public class PushConfigurationModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7540291947530682774L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String TEMPLATE;
	@Column
	private String COMMAND;
	@Column
	private Timestamp DATETIME;
	@Column
	private String IP_ADDRESS;
	@Column
	private String STATUS;	
	@Column
	private String OP;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getTEMPLATE() {
		return TEMPLATE;
	}
	public void setTEMPLATE(String tEMPLATE) {
		TEMPLATE = tEMPLATE;
	}
	public String getCOMMAND() {
		return COMMAND;
	}
	public void setCOMMAND(String cOMMAND) {
		COMMAND = cOMMAND;
	}
	public Timestamp getDATETIME() {
		return DATETIME;
	}
	public void setDATETIME(Timestamp dATETIME) {
		DATETIME = dATETIME;
	}
	public String getIP_ADDRESS() {
		return IP_ADDRESS;
	}
	public void setIP_ADDRESS(String iP_ADDRESS) {
		IP_ADDRESS = iP_ADDRESS;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getOP() {
		return OP;
	}
	public void setOP(String oP) {
		OP = oP;
	}
	@Override
	public String toString() {
		return "PushConfigurationModel [ID=" + ID + ", TEMPLATE=" + TEMPLATE + ", COMMAND=" + COMMAND + ", DATETIME="
				+ DATETIME + ", IP_ADDRESS=" + IP_ADDRESS + ", STATUS=" + STATUS + ", OP=" + OP + "]";
	}
	
	
}
