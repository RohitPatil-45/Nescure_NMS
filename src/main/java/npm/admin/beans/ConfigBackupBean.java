package npm.admin.beans;

public class ConfigBackupBean {
	private long ID;
	private String deviceIp;
	private String enablePass;
	private String deviceMode;
	private String confCommand;
	private String username;
	private String foCommand;
	private String password;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getEnablePass() {
		return enablePass;
	}
	public void setEnablePass(String enablePass) {
		this.enablePass = enablePass;
	}
	public String getDeviceMode() {
		return deviceMode;
	}
	public void setDeviceMode(String deviceMode) {
		this.deviceMode = deviceMode;
	}
	public String getConfCommand() {
		return confCommand;
	}
	public void setConfCommand(String confCommand) {
		this.confCommand = confCommand;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFoCommand() {
		return foCommand;
	}
	public void setFoCommand(String foCommand) {
		this.foCommand = foCommand;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
