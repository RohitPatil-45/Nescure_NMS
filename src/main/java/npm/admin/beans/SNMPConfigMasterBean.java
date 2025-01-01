package npm.admin.beans;

public class SNMPConfigMasterBean {

	private long id;
	private String name;
	private String description;
	private String version;
	private String community;
	private String username;
	private String context;
	private String authProtocol;
	private String authPassword;
	private String encryptProtocol;
	private String encryptPassword;
	private long port;
	private long timeout;
	private long retries;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getAuthProtocol() {
		return authProtocol;
	}
	public void setAuthProtocol(String authProtocol) {
		this.authProtocol = authProtocol;
	}
	public String getAuthPassword() {
		return authPassword;
	}
	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}
	public String getEncryptProtocol() {
		return encryptProtocol;
	}
	public void setEncryptProtocol(String encryptProtocol) {
		this.encryptProtocol = encryptProtocol;
	}
	public String getEncryptPassword() {
		return encryptPassword;
	}
	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
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
	public long getRetries() {
		return retries;
	}
	public void setRetries(long retries) {
		this.retries = retries;
	}
	
	
	@Override
	public String toString() {
		return "SNMPConfigMasterBean [id=" + id + ", name=" + name + ", description=" + description + ", version="
				+ version + ", community=" + community + ", username=" + username + ", context=" + context
				+ ", authProtocol=" + authProtocol + ", authPassword=" + authPassword + ", encryptProtocol="
				+ encryptProtocol + ", encryptPassword=" + encryptPassword + ", port=" + port + ", timeout=" + timeout
				+ ", retries=" + retries + "]";
	}
	
	
	
	
}
