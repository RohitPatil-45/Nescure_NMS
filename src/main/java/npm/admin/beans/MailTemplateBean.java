package npm.admin.beans;

public class MailTemplateBean {
	private long SR_NO;
	private String templateName;
	private String subject;
	private String sendTime;
	private String ipAddressList;
	private String functionName;
	private String msgBody;
	private String attachment;
	private String toMail;
	private String periods;
	private String attachmentType;
	private String ccMail;
	private String days;
	private String isActive;
	private String phone;
	public long getSR_NO() {
		return SR_NO;
	}
	public void setSR_NO(long sR_NO) {
		SR_NO = sR_NO;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getIpAddressList() {
		return ipAddressList;
	}
	public void setIpAddressList(String ipAddressList) {
		this.ipAddressList = ipAddressList;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getToMail() {
		return toMail;
	}
	public void setToMail(String toMail) {
		this.toMail = toMail;
	}
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public String getCcMail() {
		return ccMail;
	}
	public void setCcMail(String ccMail) {
		this.ccMail = ccMail;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "MailTemplateBean [SR_NO=" + SR_NO + ", templateName=" + templateName + ", subject=" + subject
				+ ", sendTime=" + sendTime + ", ipAddressList=" + ipAddressList + ", functionName=" + functionName
				+ ", msgBody=" + msgBody + ", attachment=" + attachment + ", toMail=" + toMail + ", periods=" + periods
				+ ", attachmentType=" + attachmentType + ", ccMail=" + ccMail + ", days=" + days + ", isActive="
				+ isActive + ", phone=" + phone + "]";
	}
	
	
	
	
}
