package npm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mail_template")
public class MailTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SR_NO", updatable = false ,nullable = false)
	private Long SR_NO;
	@Column
	private String ATTACHEMENT;
	@Column
	private String ATTACHEMENT_TYPE;
	@Column
	private String CC_MAIL_ID;
	@Column
	private String DAYS;
	@Column
	private String FUNCTION_NAME;
	@Column
	private String IP_ADDRESS_LIST;
	@Column
	private String IsActive;
	@Column
	private String MSG_BODY;
	@Column
	private String PERIODS;
	@Column
	private String SEND_TIME;
	@Column
	private String SUBJECT;
	@Column(unique = true)
	private String TEMPLET_NAME;
	@Column
	private String TO_MAIL;
	@Column
	private String PHONE;
	public Long getSR_NO() {
		return SR_NO;
	}
	public void setSR_NO(Long sR_NO) {
		SR_NO = sR_NO;
	}
	public String getATTACHEMENT() {
		return ATTACHEMENT;
	}
	public void setATTACHEMENT(String aTTACHEMENT) {
		ATTACHEMENT = aTTACHEMENT;
	}
	public String getATTACHEMENT_TYPE() {
		return ATTACHEMENT_TYPE;
	}
	public void setATTACHEMENT_TYPE(String aTTACHEMENT_TYPE) {
		ATTACHEMENT_TYPE = aTTACHEMENT_TYPE;
	}
	public String getCC_MAIL_ID() {
		return CC_MAIL_ID;
	}
	public void setCC_MAIL_ID(String cC_MAIL_ID) {
		CC_MAIL_ID = cC_MAIL_ID;
	}
	public String getDAYS() {
		return DAYS;
	}
	public void setDAYS(String dAYS) {
		DAYS = dAYS;
	}
	public String getFUNCTION_NAME() {
		return FUNCTION_NAME;
	}
	public void setFUNCTION_NAME(String fUNCTION_NAME) {
		FUNCTION_NAME = fUNCTION_NAME;
	}
	public String getIP_ADDRESS_LIST() {
		return IP_ADDRESS_LIST;
	}
	public void setIP_ADDRESS_LIST(String iP_ADDRESS_LIST) {
		IP_ADDRESS_LIST = iP_ADDRESS_LIST;
	}
	public String getIsActive() {
		return IsActive;
	}
	public void setIsActive(String isActive) {
		IsActive = isActive;
	}
	public String getMSG_BODY() {
		return MSG_BODY;
	}
	public void setMSG_BODY(String mSG_BODY) {
		MSG_BODY = mSG_BODY;
	}
	public String getPERIODS() {
		return PERIODS;
	}
	public void setPERIODS(String pERIODS) {
		PERIODS = pERIODS;
	}
	public String getSEND_TIME() {
		return SEND_TIME;
	}
	public void setSEND_TIME(String sEND_TIME) {
		SEND_TIME = sEND_TIME;
	}
	public String getSUBJECT() {
		return SUBJECT;
	}
	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}
	public String getTEMPLET_NAME() {
		return TEMPLET_NAME;
	}
	public void setTEMPLET_NAME(String tEMPLET_NAME) {
		TEMPLET_NAME = tEMPLET_NAME;
	}
	public String getTO_MAIL() {
		return TO_MAIL;
	}
	public void setTO_MAIL(String tO_MAIL) {
		TO_MAIL = tO_MAIL;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public MailTemplate(Long sR_NO, String aTTACHEMENT, String aTTACHEMENT_TYPE, String cC_MAIL_ID, String dAYS,
			String fUNCTION_NAME, String iP_ADDRESS_LIST, String isActive, String mSG_BODY, String pERIODS,
			String sEND_TIME, String sUBJECT, String tEMPLET_NAME, String tO_MAIL, String pHONE) {
		super();
		SR_NO = sR_NO;
		ATTACHEMENT = aTTACHEMENT;
		ATTACHEMENT_TYPE = aTTACHEMENT_TYPE;
		CC_MAIL_ID = cC_MAIL_ID;
		DAYS = dAYS;
		FUNCTION_NAME = fUNCTION_NAME;
		IP_ADDRESS_LIST = iP_ADDRESS_LIST;
		IsActive = isActive;
		MSG_BODY = mSG_BODY;
		PERIODS = pERIODS;
		SEND_TIME = sEND_TIME;
		SUBJECT = sUBJECT;
		TEMPLET_NAME = tEMPLET_NAME;
		TO_MAIL = tO_MAIL;
		PHONE = pHONE;
	}
	public MailTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MailTemplate [SR_NO=" + SR_NO + ", ATTACHEMENT=" + ATTACHEMENT + ", ATTACHEMENT_TYPE="
				+ ATTACHEMENT_TYPE + ", CC_MAIL_ID=" + CC_MAIL_ID + ", DAYS=" + DAYS + ", FUNCTION_NAME="
				+ FUNCTION_NAME + ", IP_ADDRESS_LIST=" + IP_ADDRESS_LIST + ", IsActive=" + IsActive + ", MSG_BODY="
				+ MSG_BODY + ", PERIODS=" + PERIODS + ", SEND_TIME=" + SEND_TIME + ", SUBJECT=" + SUBJECT
				+ ", TEMPLET_NAME=" + TEMPLET_NAME + ", TO_MAIL=" + TO_MAIL + ", PHONE=" + PHONE + "]";
	}

	

}
