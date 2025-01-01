package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_MASTER" )
public class UserMasterModel implements Serializable  {
	
	private static final long serialVersionUID = -2264642949863409860L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String USERNAME;
	@Column
	private String PASSWORD;
	@Column
	private String CONFIRM_PASSWORD;
	@Column
	private String EMAIL;
	@Column
	private String MOBILE_NO;
	@Column
	private String NAME;
	@Column
	private String ROLE;
	@Column
	private String DEPARTMENT;
	
	
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
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
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getMOBILE_NO() {
		return MOBILE_NO;
	}
	public void setMOBILE_NO(String mOBILE_NO) {
		MOBILE_NO = mOBILE_NO;
	}
	
	public String getCONFIRM_PASSWORD() {
		return CONFIRM_PASSWORD;
	}
	public void setCONFIRM_PASSWORD(String cONFIRM_PASSWORD) {
		CONFIRM_PASSWORD = cONFIRM_PASSWORD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getROLE() {
		return ROLE;
	}
	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}
	public String getDEPARTMENT() {
		return DEPARTMENT;
	}
	public void setDEPARTMENT(String dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}
	@Override
	public String toString() {
		return "UserMasterModel [ID=" + ID + ", USERNAME=" + USERNAME + ", PASSWORD=" + PASSWORD + ", CONFIRM_PASSWORD="
				+ CONFIRM_PASSWORD + ", EMAIL=" + EMAIL + ", MOBILE_NO=" + MOBILE_NO + ", NAME=" + NAME + ", ROLE="
				+ ROLE + ", DEPARTMENT=" + DEPARTMENT + "]";
	}

	
	
	
}
