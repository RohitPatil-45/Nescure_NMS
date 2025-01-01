package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIG_BACKUP")
public class ConfigBackup implements Serializable{
	
	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	
	private String BACKUP_PATH;
	private String RUNNING_CONFIG;
	private String STARTUP_CONFIG;
	private String BACKUP;
	private String TIME;
	private String GROUP_NAME;
	private String NODE_IP;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getBACKUP_PATH() {
		return BACKUP_PATH;
	}
	public void setBACKUP_PATH(String bACKUP_PATH) {
		BACKUP_PATH = bACKUP_PATH;
	}
	public String getRUNNING_CONFIG() {
		return RUNNING_CONFIG;
	}
	public void setRUNNING_CONFIG(String rUNNING_CONFIG) {
		RUNNING_CONFIG = rUNNING_CONFIG;
	}
	public String getSTARTUP_CONFIG() {
		return STARTUP_CONFIG;
	}
	public void setSTARTUP_CONFIG(String sTARTUP_CONFIG) {
		STARTUP_CONFIG = sTARTUP_CONFIG;
	}
	public String getBACKUP() {
		return BACKUP;
	}
	public void setBACKUP(String bACKUP) {
		BACKUP = bACKUP;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public String getGROUP_NAME() {
		return GROUP_NAME;
	}
	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}
	
	public String getNODE_IP() {
		return NODE_IP;
	}
	public void setNODE_IP(String nODE_IP) {
		NODE_IP = nODE_IP;
	}
	@Override
	public String toString() {
		return "ConfigBackup [ID=" + ID + ", BACKUP_PATH=" + BACKUP_PATH + ", RUNNING_CONFIG=" + RUNNING_CONFIG
				+ ", STARTUP_CONFIG=" + STARTUP_CONFIG + ", BACKUP=" + BACKUP + ", TIME=" + TIME + ", GROUP_NAME="
				+ GROUP_NAME + ", NODE_IP=" + NODE_IP + "]";
	}

	
	
	
	

}
