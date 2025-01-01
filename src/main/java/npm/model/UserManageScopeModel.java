package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_scope" )
public class UserManageScopeModel implements Serializable{

	private static final long serialVersionUID = 3120525041277155081L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String USERNAME;
	@Column
	private String GROUPNAME;
	@Column
	private String LOCATION;
	@Column
	private String ADMIN;
	@Column
	private String DASHBOARD;
	@Column
	private String REPORT;
	@Column
	private String GRAPH;
	@Column
	private String USER_SCOPE;
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
	public String getGROUPNAME() {
		return GROUPNAME;
	}
	public void setGROUPNAME(String gROUPNAME) {
		GROUPNAME = gROUPNAME;
	}
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	public String getADMIN() {
		return ADMIN;
	}
	public void setADMIN(String aDMIN) {
		ADMIN = aDMIN;
	}
	public String getDASHBOARD() {
		return DASHBOARD;
	}
	public void setDASHBOARD(String dASHBOARD) {
		DASHBOARD = dASHBOARD;
	}
	public String getREPORT() {
		return REPORT;
	}
	public void setREPORT(String rEPORT) {
		REPORT = rEPORT;
	}
	public String getGRAPH() {
		return GRAPH;
	}
	public void setGRAPH(String gRAPH) {
		GRAPH = gRAPH;
	}
	public String getUSER_SCOPE() {
		return USER_SCOPE;
	}
	public void setUSER_SCOPE(String uSER_SCOPE) {
		USER_SCOPE = uSER_SCOPE;
	}
	@Override
	public String toString() {
		return "UserManageScopeModel [ID=" + ID + ", USERNAME=" + USERNAME + ", GROUPNAME=" + GROUPNAME + ", LOCATION="
				+ LOCATION + ", ADMIN=" + ADMIN + ", DASHBOARD=" + DASHBOARD + ", REPORT=" + REPORT + ", GRAPH=" + GRAPH
				+ ", USER_SCOPE=" + USER_SCOPE + "]";
	}
	
	
	
	
	

}
