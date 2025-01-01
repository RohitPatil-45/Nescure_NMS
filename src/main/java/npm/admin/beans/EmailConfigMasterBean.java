package npm.admin.beans;

public class EmailConfigMasterBean {
	
	private long id;
	private String emailId;
	private String smtpServer;
	private long port;
	private long timeout;
	private String isSslTls;
	private String isSmtpAuth;
	private String username;
	private String password;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSmtpServer() {
		return smtpServer;
	}
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}
	public long getPort() {
		return port;
	}
	public void setPort(long port) {
		this.port = port;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public String getIsSslTls() {
		return isSslTls;
	}
	public void setIsSslTls(String isSslTls) {
		this.isSslTls = isSslTls;
	}
	public String getIsSmtpAuth() {
		return isSmtpAuth;
	}
	public void setIsSmtpAuth(String isSmtpAuth) {
		this.isSmtpAuth = isSmtpAuth;
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
		return "EmailConfigMasterBean [id=" + id + ", emailId=" + emailId + ", smtpServer=" + smtpServer + ", port="
				+ port + ", timeout=" + timeout + ", isSslTls=" + isSslTls + ", isSmtpAuth=" + isSmtpAuth
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
	
	
	
	

}
