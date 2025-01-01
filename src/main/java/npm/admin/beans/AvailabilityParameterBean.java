package npm.admin.beans;

import java.time.LocalTime;

public class AvailabilityParameterBean {

	private long id;
	private LocalTime fromTime;
	private LocalTime toTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalTime getFromTime() {
		return fromTime;
	}
	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}
	public LocalTime getToTime() {
		return toTime;
	}
	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}
	@Override
	public String toString() {
		return "AvailabilityParameterBean [id=" + id + ", fromTime=" + fromTime + ", toTime=" + toTime + "]";
	}
	
	
	
	
}
