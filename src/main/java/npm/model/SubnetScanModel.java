package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUBNET_SCAN" )
public class SubnetScanModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6327768723776169689L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String GROUP_NAME;
	@Column
	private String START_IP;
	@Column
	private String SUBNET_MASK;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getGROUP_NAME() {
		return GROUP_NAME;
	}
	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
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
