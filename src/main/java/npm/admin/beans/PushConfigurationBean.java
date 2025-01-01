package npm.admin.beans;

public class PushConfigurationBean {
	
	private long id;
	private String templateName;
	private String command;
	private String datetime;
	private String ip_address;
	private String status;
	private String op;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	@Override
	public String toString() {
		return "PushConfigurationBean [id=" + id + ", templateName=" + templateName + ", command=" + command
				+ ", datetime=" + datetime + ", ip_address=" + ip_address + ", status=" + status + "]";
	}
	
	
	
	
}
