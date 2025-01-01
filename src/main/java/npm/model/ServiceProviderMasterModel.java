package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SERVICE_PROVIDER_MASTER" )
public class ServiceProviderMasterModel implements Serializable{
	
	private static final long serialVersionUID = -2264642949863409860L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String SERVICE_PROVIDER_NAME;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getSERVICE_PROVIDER_NAME() {
		return SERVICE_PROVIDER_NAME;
	}
	public void setSERVICE_PROVIDER_NAME(String sERVICE_PROVIDER_NAME) {
		SERVICE_PROVIDER_NAME = sERVICE_PROVIDER_NAME;
	}
	
	

}
