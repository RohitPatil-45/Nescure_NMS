package npm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Auto_Topology_Change_History {
	
	@Id
	@Column
	private int ID;
	private String DATETIME;
	private String SRC_IP;
	private String SRC_PORT;
	private String PRV_DEST_IP;
	private String PRV_DEST_PORT;
	private String CURRENT_DEST_IP;
	private String CURRENT_DEST_PORT;
	private String SRC_MAC;
	private String PRV_DEST_MAC;
	private String CURRENT_DEST_MAC;
	private String POOLING_ID;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDATETIME() {
		return DATETIME;
	}
	public void setDATETIME(String dATETIME) {
		DATETIME = dATETIME;
	}
	public String getSRC_IP() {
		return SRC_IP;
	}
	public void setSRC_IP(String sRC_IP) {
		SRC_IP = sRC_IP;
	}
	public String getSRC_PORT() {
		return SRC_PORT;
	}
	public void setSRC_PORT(String sRC_PORT) {
		SRC_PORT = sRC_PORT;
	}
	public String getPRV_DEST_IP() {
		return PRV_DEST_IP;
	}
	public void setPRV_DEST_IP(String pRV_DEST_IP) {
		PRV_DEST_IP = pRV_DEST_IP;
	}
	public String getPRV_DEST_PORT() {
		return PRV_DEST_PORT;
	}
	public void setPRV_DEST_PORT(String pRV_DEST_PORT) {
		PRV_DEST_PORT = pRV_DEST_PORT;
	}
	public String getCURRENT_DEST_IP() {
		return CURRENT_DEST_IP;
	}
	public void setCURRENT_DEST_IP(String cURRENT_DEST_IP) {
		CURRENT_DEST_IP = cURRENT_DEST_IP;
	}
	public String getCURRENT_DEST_PORT() {
		return CURRENT_DEST_PORT;
	}
	public void setCURRENT_DEST_PORT(String cURRENT_DEST_PORT) {
		CURRENT_DEST_PORT = cURRENT_DEST_PORT;
	}
	public String getSRC_MAC() {
		return SRC_MAC;
	}
	public void setSRC_MAC(String sRC_MAC) {
		SRC_MAC = sRC_MAC;
	}
	public String getPRV_DEST_MAC() {
		return PRV_DEST_MAC;
	}
	public void setPRV_DEST_MAC(String pRV_DEST_MAC) {
		PRV_DEST_MAC = pRV_DEST_MAC;
	}
	public String getCURRENT_DEST_MAC() {
		return CURRENT_DEST_MAC;
	}
	public void setCURRENT_DEST_MAC(String cURRENT_DEST_MAC) {
		CURRENT_DEST_MAC = cURRENT_DEST_MAC;
	}
	public String getPOOLING_ID() {
		return POOLING_ID;
	}
	public void setPOOLING_ID(String pOOLING_ID) {
		POOLING_ID = pOOLING_ID;
	}
	

}
