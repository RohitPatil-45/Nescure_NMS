package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOCATION_MASTER" )
public class LocationMasterModel implements Serializable{
	
	private static final long serialVersionUID = -2264642949863409860L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String COMPANY_NAME;
	@Column
	private String LOCATION_NAME;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}
	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}
	public String getLOCATION_NAME() {
		return LOCATION_NAME;
	}
	public void setLOCATION_NAME(String lOCATION_NAME) {
		LOCATION_NAME = lOCATION_NAME;
	}
	@Override
	public String toString() {
		return "LocationMasterModel [ID=" + ID + ", COMPANY_NAME=" + COMPANY_NAME + ", LOCATION_NAME=" + LOCATION_NAME
				+ "]";
	}
	
	


}
