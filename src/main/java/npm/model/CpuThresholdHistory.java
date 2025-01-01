package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CPU_THRESHOLD_HISTORY" )
public class CpuThresholdHistory {

	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private double CPU_UTILIZATION;
	@Column
	private double CPU_THRESHOLD;
	@Column
	private String CPU_STATUS;
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
	public double getCPU_THRESHOLD() {
		return CPU_THRESHOLD;
	}
	public void setCPU_THRESHOLD(double cPU_THRESHOLD) {
		CPU_THRESHOLD = cPU_THRESHOLD;
	}
	public String getCPU_STATUS() {
		return CPU_STATUS;
	}
	public void setCPU_STATUS(String cPU_STATUS) {
		CPU_STATUS = cPU_STATUS;
	}
	public Timestamp getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(Timestamp eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	
	
	
	
}
