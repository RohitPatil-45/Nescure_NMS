package npm.admin.beans;

import java.sql.Timestamp;

public class WindowsEventLogBean {

	private int id;
    private String logId;
    private String deviceIp;
    private String deviceName;
    private String eventId;
    private String eventType;
    private String eventSeverity;
    private String eventName;
    private String eventCategory;
    private String eventSource;
    private String eventMessage;
    private String eventLevel;
    private Timestamp eventTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
		return "WindowsEventLogBean [id=" + id + ", logId=" + logId + ", deviceIp=" + deviceIp + ", deviceName="
				+ deviceName + ", eventId=" + eventId + ", eventType=" + eventType + ", eventSeverity=" + eventSeverity
				+ ", eventName=" + eventName + ", eventCategory=" + eventCategory + ", eventSource=" + eventSource
				+ ", eventMessage=" + eventMessage + ", eventLevel=" + eventLevel + ", eventTime=" + eventTime + "]";
	}
    
    
}
