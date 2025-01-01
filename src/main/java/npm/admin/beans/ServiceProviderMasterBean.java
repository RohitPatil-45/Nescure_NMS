package npm.admin.beans;

public class ServiceProviderMasterBean {

	private long id;
	private String serviceProviderName;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	@Override
	public String toString() {
		return "ServiceProviderMasterBean [id=" + id + ", serviceProviderName=" + serviceProviderName + "]";
	}
	
	
	
	
	
	
	
	
}
