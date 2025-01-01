package npm.admin.beans;

public class SMSConfigurationBean {

	private String smsProvider;
	private String smsUrl;
	private String username;
	private String password;
	public String getSmsProvider() {
		return smsProvider;
	}
	public void setSmsProvider(String smsProvider) {
		this.smsProvider = smsProvider;
	}
	public String getSmsUrl() {
		return smsUrl;
	}
	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
