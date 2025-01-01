package npm.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "syslogs_data")
public class SyslogEvent implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -4450454554313950150L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "device_ip")
	private String deviceIp;

	@Column(name = "syslog_severity")
	private String syslogSeverity;

	@Column(name = "syslog_facility")
	private String syslogFacility;

	@Column(name = "syslog_type")
	private String syslogType;

	@Column(name = "syslog_subtype")
	private String syslogSubtype;

	@Column(name = "syslog_source_ip")
	private String syslogSourceIp;

	@Column(name = "syslog_source_port")
	private String syslogSourcePort;

	@Column(name = "syslog_destination_ip")
	private String syslogDestinationIp;

	@Column(name = "syslog_destination_port")
	private String syslogDestinationPort;

	@Column(name = "syslog_protocol")
	private String syslogProtocol;

	@Column(name = "syslog_datasource")
	private String syslogDatasource;

	@Column(name = "syslog_param")
	private String syslogParam;

	@Lob
	@Column(name = "syslog_value")
	private String syslogValue;

	@Lob
	@Column(name = "syslog_rawmsg")
	private String syslogRawmsg;

	@Lob
	@Column(name = "syslog_msg")
	private String syslogMsg;

	@Column(name = "syslog_threat")
	private String syslogThreat;

	@Column(name = "syslog_time")
	private String syslogTime;

	@Column(name = "syslog_date")
	private String syslogDate;

	@Column(name = "event_time")
	private Timestamp eventTime;

	@Column(name = "syslog_level")
	private String syslogLevel;

	@Column(name = "device_name")
	private String deviceName;

	@Column(name = "device_id")
	private String deviceId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getSyslogSeverity() {
		return syslogSeverity;
	}

	public void setSyslogSeverity(String syslogSeverity) {
		this.syslogSeverity = syslogSeverity;
	}

	public String getSyslogFacility() {
		return syslogFacility;
	}

	public void setSyslogFacility(String syslogFacility) {
		this.syslogFacility = syslogFacility;
	}

	public String getSyslogType() {
		return syslogType;
	}

	public void setSyslogType(String syslogType) {
		this.syslogType = syslogType;
	}

	public String getSyslogSubtype() {
		return syslogSubtype;
	}

	public void setSyslogSubtype(String syslogSubtype) {
		this.syslogSubtype = syslogSubtype;
	}

	public String getSyslogSourceIp() {
		return syslogSourceIp;
	}

	public void setSyslogSourceIp(String syslogSourceIp) {
		this.syslogSourceIp = syslogSourceIp;
	}

	public String getSyslogSourcePort() {
		return syslogSourcePort;
	}

	public void setSyslogSourcePort(String syslogSourcePort) {
		this.syslogSourcePort = syslogSourcePort;
	}

	public String getSyslogDestinationIp() {
		return syslogDestinationIp;
	}

	public void setSyslogDestinationIp(String syslogDestinationIp) {
		this.syslogDestinationIp = syslogDestinationIp;
	}

	public String getSyslogDestinationPort() {
		return syslogDestinationPort;
	}

	public void setSyslogDestinationPort(String syslogDestinationPort) {
		this.syslogDestinationPort = syslogDestinationPort;
	}

	public String getSyslogProtocol() {
		return syslogProtocol;
	}

	public void setSyslogProtocol(String syslogProtocol) {
		this.syslogProtocol = syslogProtocol;
	}

	public String getSyslogDatasource() {
		return syslogDatasource;
	}

	public void setSyslogDatasource(String syslogDatasource) {
		this.syslogDatasource = syslogDatasource;
	}

	public String getSyslogParam() {
		return syslogParam;
	}

	public void setSyslogParam(String syslogParam) {
		this.syslogParam = syslogParam;
	}

	public String getSyslogValue() {
		return syslogValue;
	}

	public void setSyslogValue(String syslogValue) {
		this.syslogValue = syslogValue;
	}

	public String getSyslogRawmsg() {
		return syslogRawmsg;
	}

	public void setSyslogRawmsg(String syslogRawmsg) {
		this.syslogRawmsg = syslogRawmsg;
	}

	public String getSyslogMsg() {
		return syslogMsg;
	}

	public void setSyslogMsg(String syslogMsg) {
		this.syslogMsg = syslogMsg;
	}

	public String getSyslogThreat() {
		return syslogThreat;
	}

	public void setSyslogThreat(String syslogThreat) {
		this.syslogThreat = syslogThreat;
	}

	public String getSyslogTime() {
		return syslogTime;
	}

	public void setSyslogTime(String syslogTime) {
		this.syslogTime = syslogTime;
	}

	public String getSyslogDate() {
		return syslogDate;
	}

	public void setSyslogDate(String syslogDate) {
		this.syslogDate = syslogDate;
	}

	public Timestamp getEventTime() {
		return eventTime;
	}

	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
	}

	public String getSyslogLevel() {
		return syslogLevel;
	}

	public void setSyslogLevel(String syslogLevel) {
		this.syslogLevel = syslogLevel;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "SyslogEvent [id=" + id + ", deviceIp=" + deviceIp + ", syslogSeverity=" + syslogSeverity
				+ ", syslogFacility=" + syslogFacility + ", syslogType=" + syslogType + ", syslogSubtype="
				+ syslogSubtype + ", syslogSourceIp=" + syslogSourceIp + ", syslogSourcePort=" + syslogSourcePort
				+ ", syslogDestinationIp=" + syslogDestinationIp + ", syslogDestinationPort=" + syslogDestinationPort
				+ ", syslogProtocol=" + syslogProtocol + ", syslogDatasource=" + syslogDatasource + ", syslogParam="
				+ syslogParam + ", syslogValue=" + syslogValue + ", syslogRawmsg=" + syslogRawmsg + ", syslogMsg="
				+ syslogMsg + ", syslogThreat=" + syslogThreat + ", syslogTime=" + syslogTime + ", syslogDate="
				+ syslogDate + ", eventTime=" + eventTime + ", syslogLevel=" + syslogLevel + ", deviceName="
				+ deviceName + ", deviceId=" + deviceId + "]";
	}

}
