package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SCHEDULE_SCAN" )
public class ScheduleScan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6327768723776169689L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String SCHEDULE_TYPE;
	@Column
	private String START_IP;
	@Column
	private String SUBNET_MASK;
	@Column
	private String CONFIG_TYPE;
	public String getCONFIG_TYPE() {
		return CONFIG_TYPE;
	}
	public void setCONFIG_TYPE(String cONFIG_TYPE) {
		CONFIG_TYPE = cONFIG_TYPE;
	}
	public String getONCE_TIME() {
		return ONCE_TIME;
	}
	public void setONCE_TIME(String oNCE_TIME) {
		ONCE_TIME = oNCE_TIME;
	}
	@Column
	private String ONCE_TIME;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getSCHEDULE_TYPE() {
		return SCHEDULE_TYPE;
	}
	public void setSCHEDULE_TYPE(String sCHEDULE_TYPE) {
		SCHEDULE_TYPE = sCHEDULE_TYPE;
	}
	public String getSTART_IP() {
		return START_IP;
	}
	public void setSTART_IP(String sTART_IP) {
		START_IP = sTART_IP;
	}
	public String getSUBNET_MASK() {
		return SUBNET_MASK;
	}
	public void setSUBNET_MASK(String sUBNET_MASK) {
		SUBNET_MASK = sUBNET_MASK;
	}
	
	
	
}