package npm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOPOLOGY_NODE_POSTIONS")
public class TOPOLOGY_NODE_POSTIONS {

	private static final long serialVersionUID = -2264642949863409860L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	
	private String NODE_ID;
	private String X_POS;
	private String Y_POS;
	private String VIEW_NAME;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getNODE_ID() {
		return NODE_ID;
	}
	public void setNODE_ID(String nODE_ID) {
		NODE_ID = nODE_ID;
	}
	public String getX_POS() {
		return X_POS;
	}
	public void setX_POS(String x_POS) {
		X_POS = x_POS;
	}
	public String getY_POS() {
		return Y_POS;
	}
	public void setY_POS(String y_POS) {
		Y_POS = y_POS;
	}
	public String getVIEW_NAME() {
		return VIEW_NAME;
	}
	public void setVIEW_NAME(String vIEW_NAME) {
		VIEW_NAME = vIEW_NAME;
	}
	
	
	
}
