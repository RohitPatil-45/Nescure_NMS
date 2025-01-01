package npm.admin.beans;

public class ADConfigMasterBean {

	private long id;
	private String ldapServerName;
	private long serverPort;
	private String logonDomain;
	private String username;
	private String password;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLdapServerName() {
		return ldapServerName;
	}
	public void setLdapServerName(String ldapServerName) {
		this.ldapServerName = ldapServerName;
	}
	public long getServerPort() {
		return serverPort;
	}
	public void setServerPort(long serverPort) {
		this.serverPort = serverPort;
	}
	public String getLogonDomain() {
		return logonDomain;
	}
	public void setLogonDomain(String logonDomain) {
		this.logonDomain = logonDomain;
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
	
	
	@Override
	public String toString() {
		return "ADConfigMasterBean [id=" + id + ", ldapServerName=" + ldapServerName + ", serverPort=" + serverPort
				+ ", logonDomain=" + logonDomain + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
