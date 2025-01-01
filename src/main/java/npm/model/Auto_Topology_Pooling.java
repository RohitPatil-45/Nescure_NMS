package npm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Auto_Topology_Pooling {
	
	@Id
	@Column
	private int SR_NO;
	private Date DATETIME;
	private  String POOLING_ID;
	private String CHANGED_CONNECTIVITY;
	public int getSR_NO() {
		return SR_NO;
	}
	public void setSR_NO(int sR_NO) {
		SR_NO = sR_NO;
	}
	public Date getDATETIME() {
		return DATETIME;
	}
	public void setDATETIME(Date dATETIME) {
		DATETIME = dATETIME;
	}
	public String getPOOLING_ID() {
		return POOLING_ID;
	}
	public void setPOOLING_ID(String pOOLING_ID) {
		POOLING_ID = pOOLING_ID;
	}
	public String getCHANGED_CONNECTIVITY() {
		return CHANGED_CONNECTIVITY;
	}
	public void setCHANGED_CONNECTIVITY(String cHANGED_CONNECTIVITY) {
		CHANGED_CONNECTIVITY = cHANGED_CONNECTIVITY;
	}
	@Override
	public String toString() {
		return "auto_topology_pooling [SR_NO=" + SR_NO + ", DATETIME=" + DATETIME + ", POOLING_ID=" + POOLING_ID
				+ ", CHANGED_CONNECTIVITY=" + CHANGED_CONNECTIVITY + "]";
	}

}
