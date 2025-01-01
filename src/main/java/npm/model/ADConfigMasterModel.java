package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AD_CONFIG_MASTER")

public class ADConfigMasterModel implements Serializable {

	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Column
	private String LDAP_SERVER;
	@Column
	private Long SERVER_PORT;
	@Column
	private String LOGON_DOMAIN;
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

	public String getLDAP_SERVER() {
		return LDAP_SERVER;
	}

	public void setLDAP_SERVER(String lDAP_SERVER) {
		LDAP_SERVER = lDAP_SERVER;
	}

	public Long getSERVER_PORT() {
		return SERVER_PORT;
	}

	public void setSERVER_PORT(Long sERVER_PORT) {
		SERVER_PORT = sERVER_PORT;
	}

	public String getLOGON_DOMAIN() {
		return LOGON_DOMAIN;
	}

	public void setLOGON_DOMAIN(String lOGON_DOMAIN) {
		LOGON_DOMAIN = lOGON_DOMAIN;
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
		return "ADConfigMasterModel [ID=" + ID + ", LDAP_SERVER=" + LDAP_SERVER + ", SERVER_PORT=" + SERVER_PORT
				+ ", LOGON_DOMAIN=" + LOGON_DOMAIN + ", USERNAME=" + USERNAME + ", PASSWORD=" + PASSWORD + "]";
	}

}
