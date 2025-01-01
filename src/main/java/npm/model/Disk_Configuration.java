package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Disk_Configuration")
public class Disk_Configuration implements Serializable {

	 private static final long serialVersionUID = 123456789L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", updatable = false, nullable = false)
	    private Long id;


	    @Column(name = "capacity", length = 191, nullable = true)
	    private String capacity;

	    @Column(name = "device_name", length = 191, nullable = true)
	    private String deviceName;

	    @Column(name = "ip_address", length = 191, nullable = true)
	    private String ipAddress;
	    
	    @Column
	    private String device_ip;

	    @Column(name = "interface_type", length = 191, nullable = true)
	    private String interfaceType;

	    @Column(name = "manufacturer", length = 191, nullable = true)
	    private String manufacturer;

	    @Column(name = "media_type", length = 191, nullable = true)
	    private String mediaType;

	    @Column(name = "model", length = 191, nullable = true)
	    private String model;

	    @Column(name = "name", length = 191, nullable = true)
	    private String name;

	    @Column(name = "serial_number", length = 191, nullable = true)
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

		public String getInterfaceType() {
			return interfaceType;
		}

		public void setInterfaceType(String interfaceType) {
			this.interfaceType = interfaceType;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}

		public String getMediaType() {
			return mediaType;
		}

		public void setMediaType(String mediaType) {
			this.mediaType = mediaType;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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
