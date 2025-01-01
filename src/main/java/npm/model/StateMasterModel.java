package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STATE_MASTER" )
public class StateMasterModel implements Serializable{

	private static final long serialVersionUID = -114640916105495025L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String COMPANY_NAME;
	@Column
	private String STATE_NAME;
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
	public String getSTATE_NAME() {
		return STATE_NAME;
	}
	public void setSTATE_NAME(String sTATE_NAME) {
		STATE_NAME = sTATE_NAME;
	}
	@Override
	public String toString() {
		return "StateMasterModel [ID=" + ID + ", COMPANY_NAME=" + COMPANY_NAME + ", STATE_NAME=" + STATE_NAME + "]";
	}
	
	
	
}
