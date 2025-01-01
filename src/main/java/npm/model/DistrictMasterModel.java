package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="DISTRICT_MASTER" )
public class DistrictMasterModel implements Serializable{

	private static final long serialVersionUID = 6763083091643095585L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String COMPANY_NAME;
	@Column
	private String DISTRICT_NAME;
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
	public String getDISTRICT_NAME() {
		return DISTRICT_NAME;
	}
	public void setDISTRICT_NAME(String dISTRICT_NAME) {
		DISTRICT_NAME = dISTRICT_NAME;
	}
	@Override
	public String toString() {
		return "DistrictMasterModel [ID=" + ID + ", COMPANY_NAME=" + COMPANY_NAME + ", DISTRICT_NAME=" + DISTRICT_NAME
				+ "]";
	}
	
	
}
