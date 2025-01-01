package npm.admin.beans;

public class CompanyMasterBean {
	
	private long id;
	private String companyName;
	private String address;
	private String email;
	private String number;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setContact(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "CompanyMasterBean [id=" + id + ", companyName=" + companyName + ", address=" + address + ", email="
				+ email + ", number=" + number + "]";
	}
	
	

}
