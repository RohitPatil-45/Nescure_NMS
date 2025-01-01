package npm.admin.beans;

public class DeviceTypeMasterBean {
	
	private long id;
	private String deviceTypeName;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDeviceTypeName() {
		return deviceTypeName;
	}
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
	
	
	@Override
	public String toString() {
		return "DeviceTypeMasterBean [id=" + id + ", deviceTypeName=" + deviceTypeName + "]";
	}
	
	
	
	
	
	

}
