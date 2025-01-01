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
@Table(name = "windows_event_log")
public class Windows_Event_Log implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOG_ID")
    private String logId;

    @Column(name = "DEVICE_IP")
    private String deviceIp;

    @Column(name = "DEVICE_NAME")
    private String deviceName;

    @Column(name = "EVENT_ID")
    private String eventId;

    @Column(name = "EVENT_TYPE")
    private String eventType;

    @Column(name = "EVENT_SEVERITY")
    private String eventSeverity;

    @Column(name = "EVENT_NAME")
    private String eventName;

    @Column(name = "EVENT_CATEGORY")
    private String eventCategory;

    @Column(name = "EVENT_SOURCE")
    private String eventSource;

    @Column(name = "EVENT_MESSAGE")
    private String eventMessage;

    @Column(name = "Event_Level")
    private String eventLevel;

    @Column(name = "EVENT_TIME")
    private Timestamp eventTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventSeverity() {
		return eventSeverity;
	}

	public void setEventSeverity(String eventSeverity) {
		this.eventSeverity = eventSeverity;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}

	public String getEventSource() {
		return eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	public String getEventMessage() {
		return eventMessage;
	}

	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}

	public String getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}

	public Timestamp getEventTime() {
		return eventTime;
	}

	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
	}

	@Override
	public String toString() {
		return "Windows_Event_Log [id=" + id + ", logId=" + logId + ", deviceIp=" + deviceIp + ", deviceName="
				+ deviceName + ", eventId=" + eventId + ", eventType=" + eventType + ", eventSeverity=" + eventSeverity
				+ ", eventName=" + eventName + ", eventCategory=" + eventCategory + ", eventSource=" + eventSource
				+ ", eventMessage=" + eventMessage + ", eventLevel=" + eventLevel + ", eventTime=" + eventTime + "]";
	}
    
    
}
