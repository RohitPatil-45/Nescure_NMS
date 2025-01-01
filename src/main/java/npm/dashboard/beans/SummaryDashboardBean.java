package npm.dashboard.beans;

public class SummaryDashboardBean {
	
	private long upDeviceCount;
	private long downDeviceCount;
	private long warningDeviceCount;
	private long alertCount;
	
	
	public long getUpDeviceCount() {
		return upDeviceCount;
	}
	public void setUpDeviceCount(long upDeviceCount) {
		this.upDeviceCount = upDeviceCount;
	}
	public long getDownDeviceCount() {
		return downDeviceCount;
	}
	public void setDownDeviceCount(long downDeviceCount) {
		this.downDeviceCount = downDeviceCount;
	}
	public long getWarningDeviceCount() {
		return warningDeviceCount;
	}
	public void setWarningDeviceCount(long warningDeviceCount) {
		this.warningDeviceCount = warningDeviceCount;
	}
	public long getAlertCount() {
		return alertCount;
	}
	public void setAlertCount(long alertCount) {
		this.alertCount = alertCount;
	}
	
	
	@Override
	public String toString() {
		return "SummaryDashboardBean [upDeviceCount=" + upDeviceCount + ", downDeviceCount=" + downDeviceCount
				+ ", warningDeviceCount=" + warningDeviceCount + ", alertCount=" + alertCount + "]";
	}
	
	
	
	

}
