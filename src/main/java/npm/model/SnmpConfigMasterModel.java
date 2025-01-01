package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SNMP_CONFIG_MASTER")
public class SnmpConfigMasterModel implements Serializable {

	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Column
	private String NAME;
	@Column
	private String DESCRIPTION;
	@Column
	private String VERSION;
	@Column
	private String COMMUNITY;
	@Column
	private String USERNAME;
	@Column
	private String CONTEXT;
	@Column
	private String AUTH_PROTOCOL;
	@Column
	private String AUTH_PASS;
	@Column
	private String ENCRYPT_PROTOCOL;
	@Column
	private String ENCRYPT_PASS;
	@Column
	private Long PORT;
	@Column
	private Long TIMEOUT;
	@Column
	private Long RETRIES;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public String getVERSION() {
		return VERSION;
	}

	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}

	public String getCOMMUNITY() {
		return COMMUNITY;
	}

	public void setCOMMUNITY(String cOMMUNITY) {
		COMMUNITY = cOMMUNITY;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getCONTEXT() {
		return CONTEXT;
	}

	public void setCONTEXT(String cONTEXT) {
		CONTEXT = cONTEXT;
	}

	public String getAUTH_PROTOCOL() {
		return AUTH_PROTOCOL;
	}

	public void setAUTH_PROTOCOL(String aUTH_PROTOCOL) {
		AUTH_PROTOCOL = aUTH_PROTOCOL;
	}

	public String getAUTH_PASS() {
		return AUTH_PASS;
	}

	public void setAUTH_PASS(String aUTH_PASS) {
		AUTH_PASS = aUTH_PASS;
	}

	public String getENCRYPT_PROTOCOL() {
		return ENCRYPT_PROTOCOL;
	}

	public void setENCRYPT_PROTOCOL(String eNCRYPT_PROTOCOL) {
		ENCRYPT_PROTOCOL = eNCRYPT_PROTOCOL;
	}

	public String getENCRYPT_PASS() {
		return ENCRYPT_PASS;
	}

	public void setENCRYPT_PASS(String eNCRYPT_PASS) {
		ENCRYPT_PASS = eNCRYPT_PASS;
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

	public Long getRETRIES() {
		return RETRIES;
	}

	public void setRETRIES(Long rETRIES) {
		RETRIES = rETRIES;
	}

	@Override
	public String toString() {
		return "SnmpConfigMasterModel [ID=" + ID + ", NAME=" + NAME + ", DESCRIPTION=" + DESCRIPTION + ", VERSION="
				+ VERSION + ", COMMUNITY=" + COMMUNITY + ", USERNAME=" + USERNAME + ", CONTEXT=" + CONTEXT
				+ ", AUTH_PROTOCOL=" + AUTH_PROTOCOL + ", AUTH_PASS=" + AUTH_PASS + ", ENCRYPT_PROTOCOL="
				+ ENCRYPT_PROTOCOL + ", ENCRYPT_PASS=" + ENCRYPT_PASS + ", PORT=" + PORT + ", TIMEOUT=" + TIMEOUT
				+ ", RETRIES=" + RETRIES + "]";
	}

}
