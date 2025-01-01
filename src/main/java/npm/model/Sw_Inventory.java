package npm.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Sw_Inventory") 
public class Sw_Inventory implements Serializable{
	private static final long serialVersionUID = 123456789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


    @Column(name = "device_name", length = 191)
    private String deviceName;

    @Column(name = "device_ip", length = 191)
    private String deviceIp;

    @Column(name = "mac_id", length = 191)
    private String macId;

    @Column(name = "application_name", length = 191)
    private String applicationName;

    @Column(name = "application_version", length = 191)
    private String applicationVersion;

    @Column(name = "is_license", length = 191)
    private String isLicense;

    @Column(name = "license_key", length = 191)
    private String licenseKey;

    @Column(name = "product_id", length = 191)
    private String productId;

    @Column(name = "publisher", length = 191)
    private String publisher;

    @Column(name = "uninstall_str", length = 191)
    private String uninstallStr;

    @Column(name = "branch_name", length = 191)
    private String branchName;

    @Column(name = "install_date", length = 191)
    private String installDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationVersion() {
		return applicationVersion;
	}

	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}

	public String getIsLicense() {
		return isLicense;
	}

	public void setIsLicense(String isLicense) {
		this.isLicense = isLicense;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getUninstallStr() {
		return uninstallStr;
	}

	public void setUninstallStr(String uninstallStr) {
		this.uninstallStr = uninstallStr;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
    
    
}
