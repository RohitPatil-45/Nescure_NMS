package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMAIL_CONFIG_MASTER")

public class EmailConfigMasterModel implements Serializable {

	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Column
	private String EMAIL_ID;
	@Column
	private String SMTP_SERVER;
	@Column
	private Long PORT;
	@Column
	private Long TIMEOUT;
	@Column
	private String IS_SSL_TLS;
	@Column
	private String SMTP_AUTH;
	@Column
	private String USERNAME;
	@Column
	private String PASSWORD;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getEMAIL_ID() {
		return EMAIL_ID;
	}
	public void setEMAIL_ID(String eMAIL_ID) {
		EMAIL_ID = eMAIL_ID;
	}
	public String getSMTP_SERVER() {
		return SMTP_SERVER;
	}
	public void setSMTP_SERVER(String sMTP_SERVER) {
		SMTP_SERVER = sMTP_SERVER;
	}
	public Long getPORT() {
		return PORT;
	}
	public void setPORT(Long pORT) {
		PORT = pORT;
	}
	public Long getTIMEOUT() {
		return TIMEOUT;
	}
	public void setTIMEOUT(Long tIMEOUT) {
		TIMEOUT = tIMEOUT;
	}
	public String getIS_SSL_TLS() {
		return IS_SSL_TLS;
	}
	public void setIS_SSL_TLS(String iS_SSL_TLS) {
		IS_SSL_TLS = iS_SSL_TLS;
	}
	public String getSMTP_AUTH() {
		return SMTP_AUTH;
	}
	public void setSMTP_AUTH(String sMTP_AUTH) {
		SMTP_AUTH = sMTP_AUTH;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	
	@Override
	public String toString() {
		return "EmailConfigMasterModel [ID=" + ID + ", EMAIL_ID=" + EMAIL_ID + ", SMTP_SERVER=" + SMTP_SERVER
				+ ", PORT=" + PORT + ", TIMEOUT=" + TIMEOUT + ", IS_SSL_TLS=" + IS_SSL_TLS + ", SMTP_AUTH=" + SMTP_AUTH
				+ ", USERNAME=" + USERNAME + ", PASSWORD=" + PASSWORD + "]";
	}




}
