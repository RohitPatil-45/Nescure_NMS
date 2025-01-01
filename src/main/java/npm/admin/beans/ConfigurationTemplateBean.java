package npm.admin.beans;

public class ConfigurationTemplateBean {
	private long ID;
	private String templateName;
	private String command;
	@Override
	public String toString() {
		return "ConfigurationTemplateBean [ID=" + ID + ", templateName=" + templateName + ", command=" + command + "]";
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
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
}
