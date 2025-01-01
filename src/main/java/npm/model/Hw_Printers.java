package npm.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Hw_Printers")
public class Hw_Printers implements Serializable {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "sr_no", updatable = false, nullable = false)
	    private Long srNo;

	    @Column(name = "device_name", length = 191)
	    private String deviceName;

	    @Column(name = "ip_address", length = 191)
	    private String ipAddress;
	    
	    @Column
	    private String device_ip;

	    @Column(name = "driver_name", length = 191)
	    private String driverName;

	    @Column(name = "location", length = 191)
	    private String location;

	    @Column(name = "model", length = 191)
	    private String model;

	    @Column(name = "name", length = 191)
	    private String name;

	    @Column(name = "server", length = 191)
	    private String server;

	    @Column(name = "type", length = 191)
	    private String type;

		public Long getSrNo() {
			return srNo;
		}

		public void setSrNo(Long srNo) {
			this.srNo = srNo;
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

		public String getDriverName() {
			return driverName;
		}

		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
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

		public String getServer() {
			return server;
		}

		public void setServer(String server) {
			this.server = server;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDevice_ip() {
			return device_ip;
		}

		public void setDevice_ip(String device_ip) {
			this.device_ip = device_ip;
		}
		
		
	    
	    
}
