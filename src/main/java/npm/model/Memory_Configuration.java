package npm.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Memory_Configuration") 
public class Memory_Configuration implements Serializable{
	 private static final long serialVersionUID = 123456789L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", updatable = false, nullable = false)
	    private Long id;


	    @Column(name = "device_name", length = 191)
	    private String deviceName;
	    
	    @Column
	    private String device_ip;

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

		public String getIpAddress() {
			return ipAddress;
		}
		
		

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		public String getBankLabel() {
			return bankLabel;
		}

		public void setBankLabel(String bankLabel) {
			this.bankLabel = bankLabel;
		}

		public String getCapacity() {
			return capacity;
		}

		public void setCapacity(String capacity) {
			this.capacity = capacity;
		}

		public String getFrequency() {
			return frequency;
		}

		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}

		public String getModuleTag() {
			return moduleTag;
		}

		public void setModuleTag(String moduleTag) {
			this.moduleTag = moduleTag;
		}

		public String getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}

		public String getSocket() {
			return socket;
		}

		public void setSocket(String socket) {
			this.socket = socket;
		}

		
		public String getDevice_ip() {
			return device_ip;
		}

		public void setDevice_ip(String device_ip) {
			this.device_ip = device_ip;
		}


		@Column(name = "ip_address", length = 191)
	    private String ipAddress;

	    @Column(name = "bank_label", length = 191)
	    private String bankLabel;

	    @Column(name = "capacity", length = 191)
	    private String capacity;

	    @Column(name = "frequency", length = 191)
	    private String frequency;

	    @Column(name = "module_tag", length = 191)
	    private String moduleTag;

	    @Column(name = "serial_number", length = 191)
	    private String serialNumber;

	    @Column(name = "socket", length = 191)
	    private String socket;
}
