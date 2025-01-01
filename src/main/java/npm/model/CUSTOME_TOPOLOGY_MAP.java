package npm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CUSTOME_TOPOLOGY_MAP {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "ID", updatable = false, nullable = false)
//	@Column(name="ID")
	private int id;
	
	private String VIEW_NAME;
	private String ID_1;
	private String LINK_FROM;
	private String LINK_TO;
	private String INTERFACE_FROM;
	private String INTERFACE_TO;
	private String IPADDRESS_FROM;
	private String IPADDRESS_TO;
	private String GROUP_NAME;
	public int getId() {
		return id;
	}
	
	public String getID_1() {
		return ID_1;
	}

	public void setID_1(String iD_1) {
		ID_1 = iD_1;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getVIEW_NAME() {
		return VIEW_NAME;
	}
	public void setVIEW_NAME(String vIEW_NAME) {
		VIEW_NAME = vIEW_NAME;
	}
	
	public String getLINK_FROM() {
		return LINK_FROM;
	}
	public void setLINK_FROM(String lINK_FROM) {
		LINK_FROM = lINK_FROM;
	}
	public String getLINK_TO() {
		return LINK_TO;
	}
	public void setLINK_TO(String lINK_TO) {
		LINK_TO = lINK_TO;
	}
	public String getINTERFACE_FROM() {
		return INTERFACE_FROM;
	}
	public void setINTERFACE_FROM(String iNTERFACE_FROM) {
		INTERFACE_FROM = iNTERFACE_FROM;
	}
	public String getINTERFACE_TO() {
		return INTERFACE_TO;
	}
	public void setINTERFACE_TO(String iNTERFACE_TO) {
		INTERFACE_TO = iNTERFACE_TO;
	}
	public String getIPADDRESS_FROM() {
		return IPADDRESS_FROM;
	}
	public void setIPADDRESS_FROM(String iPADDRESS_FROM) {
		IPADDRESS_FROM = iPADDRESS_FROM;
	}
	public String getIPADDRESS_TO() {
		return IPADDRESS_TO;
	}
	public void setIPADDRESS_TO(String iPADDRESS_TO) {
		IPADDRESS_TO = iPADDRESS_TO;
	}
	public String getGROUP_NAME() {
		return GROUP_NAME;
	}
	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}
	
	
	
	
}
