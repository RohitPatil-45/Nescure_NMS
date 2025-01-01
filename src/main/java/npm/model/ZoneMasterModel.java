package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ZONE_MASTER" )
public class ZoneMasterModel implements Serializable{

	private static final long serialVersionUID = -8540886058186749578L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String COMPANY_NAME;
	@Column
	private String ZONE_NAME;
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
	public String getZONE_NAME() {
		return ZONE_NAME;
	}
	public void setZONE_NAME(String zONE_NAME) {
		ZONE_NAME = zONE_NAME;
	}
	@Override
	public String toString() {
		return "ZoneMasterModel [ID=" + ID + ", COMPANY_NAME=" + COMPANY_NAME + ", ZONE_NAME=" + ZONE_NAME + "]";
	}
	
	
}
