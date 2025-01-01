package npm.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INTERFACE_AVAILABILITY" )
public class InterfaceAvailablity {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private String INTERFACE_NAME;
	@Column
	private double UPTIME_PERCENT;
	@Column
	private int UPTIME_MILISECONDS;
	@Column
	private double DOWNTIME_PERCENT;
	@Column
	private int DOWNTIME_MILISECONDS;
	@Column
	private String IP_INTERFACE;
	@Column
	private Date EVENT_TIMESTAMP;
	
	
	
	
	public int getUPTIME_MILISECONDS() {
		return UPTIME_MILISECONDS;
	}
	public void setUPTIME_MILISECONDS(int uPTIME_MILISECONDS) {
		UPTIME_MILISECONDS = uPTIME_MILISECONDS;
	}
	public int getDOWNTIME_MILISECONDS() {
		return DOWNTIME_MILISECONDS;
	}
	public void setDOWNTIME_MILISECONDS(int dOWNTIME_MILISECONDS) {
		DOWNTIME_MILISECONDS = dOWNTIME_MILISECONDS;
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
	public String getINTERFACE_NAME() {
		return INTERFACE_NAME;
	}
	public void setINTERFACE_NAME(String iNTERFACE_NAME) {
		INTERFACE_NAME = iNTERFACE_NAME;
	}
	public double getUPTIME_PERCENT() {
		return UPTIME_PERCENT;
	}
	public void setUPTIME_PERCENT(double uPTIME_PERCENT) {
		UPTIME_PERCENT = uPTIME_PERCENT;
	}
	
	public double getDOWNTIME_PERCENT() {
		return DOWNTIME_PERCENT;
	}
	public void setDOWNTIME_PERCENT(double dOWNTIME_PERCENT) {
		DOWNTIME_PERCENT = dOWNTIME_PERCENT;
	}

	public String getIP_INTERFACE() {
		return IP_INTERFACE;
	}
	public void setIP_INTERFACE(String iP_INTERFACE) {
		IP_INTERFACE = iP_INTERFACE;
	}
	
	
	
	public Date getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(Date eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	@Override
	public String toString() {
		return "InterfaceAvailablity [ID=" + ID + ", NODE_IP=" + NODE_IP + ", INTERFACE_NAME=" + INTERFACE_NAME
				+ ", UPTIME_PERCENT=" + UPTIME_PERCENT + ", UPTIME_MILISECONDS=" + UPTIME_MILISECONDS
				+ ", DOWNTIME_PERCENT=" + DOWNTIME_PERCENT + ", DOWNTIME_MILISECONDS=" + DOWNTIME_MILISECONDS
				+ ", IP_INTERFACE=" + IP_INTERFACE + ", EVENT_TIMESTAMP=" + EVENT_TIMESTAMP + "]";
	}
	
	

}
