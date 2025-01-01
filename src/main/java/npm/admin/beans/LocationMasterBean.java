package npm.admin.beans;

public class LocationMasterBean {
	
	private long id;
	private String companyName;
	private String location;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "LocationMasterBean [id=" + id + ", companyName=" + companyName + ", location=" + location + "]";
	}
	
	

}
