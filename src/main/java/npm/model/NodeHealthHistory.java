package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NODE_HEALTH_HISTORY" )
public class NodeHealthHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private double CPU_UTILIZATION;
	@Column
	private double MEMORY_UTILIZATION;
	@Column
	private double TEMPERATURE;
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
	public double getCPU_UTILIZATION() {
		return CPU_UTILIZATION;
	}
	public void setCPU_UTILIZATION(double cPU_UTILIZATION) {
		CPU_UTILIZATION = cPU_UTILIZATION;
	}
	public double getMEMORY_UTILIZATION() {
		return MEMORY_UTILIZATION;
	}
	public void setMEMORY_UTILIZATION(double mEMORY_UTILIZATION) {
		MEMORY_UTILIZATION = mEMORY_UTILIZATION;
	}
	public double getTEMPERATURE() {
		return TEMPERATURE;
	}
	public void setTEMPERATURE(double tEMPERATURE) {
		TEMPERATURE = tEMPERATURE;
	}
	public Timestamp getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(Timestamp eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	
	
	
}
