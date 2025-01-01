package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "COMMAND_MASTER")
public class CommandMaster implements Serializable {

	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Column(name = "TEMPLATE_NAME")
	private String TEMPLATE_NAME;
	
	@Column(name = "COMMAND")
	private String COMMAND;
	
	@Column(name = "BACKUP")
	private String BACKUP;
	
	@Column(name = "BACKUP_PATH")
	private String BACKUP_PATH;
	
	@Column(name = "RUNNING_CONFIG")
	private String RUNNING_CONFIG;
	
	@Column(name = "STARTUP_CONFIG")
	private String STARTUP_CONFIG;

	@Override
	public String toString() {
		return "CommandMaster [ID=" + ID + ", TEMPLATE_NAME=" + TEMPLATE_NAME + ", COMMAND=" + COMMAND + ", BACKUP="
				+ BACKUP + ", BACKUP_PATH=" + BACKUP_PATH + ", RUNNING_CONFIG=" + RUNNING_CONFIG + ", STARTUP_CONFIG="
				+ STARTUP_CONFIG + "]";
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getTEMPLATE_NAME() {
		return TEMPLATE_NAME;
	}

	public void setTEMPLATE_NAME(String tEMPLATE_NAME) {
		TEMPLATE_NAME = tEMPLATE_NAME;
	}

	public String getCOMMAND() {
		return COMMAND;
	}

	public void setCOMMAND(String cOMMAND) {
		COMMAND = cOMMAND;
	}

	

	public String getBACKUP() {
		return BACKUP;
	}

	public void setBACKUP(String bACKUP) {
		BACKUP = bACKUP;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
