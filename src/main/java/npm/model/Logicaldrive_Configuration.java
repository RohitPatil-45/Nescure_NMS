package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Logicaldrive_Configuration")
public class Logicaldrive_Configuration implements Serializable{
	private static final long serialVersionUID = 123456789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "capacity", length = 191)
    private String capacity;

    @Column(name = "device_name", length = 191)
    private String deviceName;

    @Column(name = "ip_address", length = 191)
    private String ipAddress;
    
    @Column
    private String device_ip;

    @Column(name = "drive", length = 191)
    private String drive;

    @Column(name = "drive_type", length = 191)
    private String driveType;

    @Column(name = "drive_usage", length = 191)
    private String driveUsage;

    @Column(name = "file_type", length = 191)
    private String fileType;

    @Column(name = "free_space", length = 191)
    private String freeSpace;

    @Column(name = "serial_number", length = 191)
    private String serialNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDrive() {
		return drive;
	}

	public void setDrive(String drive) {
		this.drive = drive;
	}

	public String getDriveType() {
		return driveType;
	}

	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	public String getDriveUsage() {
		return driveUsage;
	}

	public void setDriveUsage(String driveUsage) {
		this.driveUsage = driveUsage;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(String freeSpace) {
		this.freeSpace = freeSpace;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}
	
	
    
}
