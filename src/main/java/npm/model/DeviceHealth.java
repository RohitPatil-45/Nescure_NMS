package npm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device_health")
public class DeviceHealth {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_ip")
    private String DEVICE_IP;

    @Column(name = "device_name")
    private String DEVICE_NAME;

    @Column(name = "cpu_utilization")
    private double CPU_UTILIZATION;

    @Column(name = "memory_utilization")
    private double MEMORY_UTILIZATION;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDEVICE_IP() {
		return DEVICE_IP;
	}

	public void setDEVICE_IP(String dEVICE_IP) {
		DEVICE_IP = dEVICE_IP;
	}

	public String getDEVICE_NAME() {
		return DEVICE_NAME;
	}

	public void setDEVICE_NAME(String dEVICE_NAME) {
		DEVICE_NAME = dEVICE_NAME;
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
    
    
}
