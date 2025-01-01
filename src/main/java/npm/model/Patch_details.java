package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "installed_patch")
public class Patch_details implements Serializable{

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO", updatable = false, nullable = false)
    private Long id;

    @Column(name = "BRANCH_NAME")
    private String branchName;

    @Column(name = "DEVICE_NAME")
    private String deviceName;

    @Column(name = "DEVICE_IP")
    private String deviceIP;

    @Column(name = "PATCH_ID")
    private String patchId;

    @Column(name = "PATCH_TYPE")
    private String patchType;

    @Column(name = "INSTALLED_DATE")
    private String installedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	

	public String getDeviceIP() {
		return deviceIP;
	}

	public void setDeviceIP(String deviceIP) {
		this.deviceIP = deviceIP;
	}

	public String getPatchId() {
		return patchId;
	}

	public void setPatchId(String patchId) {
		this.patchId = patchId;
	}

	public String getPatchType() {
		return patchType;
	}

	public void setPatchType(String patchType) {
		this.patchType = patchType;
	}

	public String getInstalledDate() {
		return installedDate;
	}

	public void setInstalledDate(String installedDate) {
		this.installedDate = installedDate;
	}
    
    
}
