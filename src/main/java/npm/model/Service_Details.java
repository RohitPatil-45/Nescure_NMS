package npm.model;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "discover_services")
public class Service_Details  implements Serializable{

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "SR_NO", updatable = false, nullable = false)
	    private Long id;

	    @Column(name = "DEVICE_NAME")
	    private String aliasName;

	    @Column(name = "SERVICE_NAME")
	    private String serviceName;

	    @Column(name = "DISPLAY_NAME")
	    private String displayName;

	    @Column(name = "SERVICE_MODE")
	    private String serviceMode;

	    @Column(name = "SERVICE_STATE")
	    private String serviceState;

	    @Column(name = "DEVICE_IP")
	    private String ipAddress;
	    
	    @Column(name = "DATE_TIME")
	    private Timestamp dateTime;
	    
	    @Column(name = "MONITORING_PARAM")
	    private Boolean monitoringParam;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getAliasName() {
			return aliasName;
		}

		public void setAliasName(String aliasName) {
			this.aliasName = aliasName;
		}

		public String getServiceName() {
			return serviceName;
		}

		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getServiceMode() {
			return serviceMode;
		}

		public void setServiceMode(String serviceMode) {
			this.serviceMode = serviceMode;
		}

		public String getServiceState() {
			return serviceState;
		}

		public void setServiceState(String serviceState) {
			this.serviceState = serviceState;
		}

		public String getIpAddress() {
			return ipAddress;
		}

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		public Timestamp getDateTime() {
			return dateTime;
		}

		public void setDateTime(Timestamp dateTime) {
			this.dateTime = dateTime;
		}

		public Boolean getMonitoringParam() {
			return monitoringParam;
		}

		public void setMonitoringParam(Boolean monitoringParam) {
			this.monitoringParam = monitoringParam;
		}
	    
	    
}
