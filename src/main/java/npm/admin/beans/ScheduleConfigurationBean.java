package npm.admin.beans;

import java.sql.Timestamp;

public class ScheduleConfigurationBean {
	private long ID;
    private String backuppath;
	private String backupSchedule;
	private String configType;
	private String datepicker;
	private String node_ip;
	private String stutus;
	private String timestamp;
	public String getStutus() {
		return stutus;
	}
	public void setStutus(String stutus) {
		this.stutus = stutus;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public String getDatepicker() {
		return datepicker;
	}
	public void setDatepicker(String string) {
		this.datepicker = string;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getBackuppath() {
		return backuppath;
	}
	public void setBackuppath(String backuppath) {
		this.backuppath = backuppath;
	}
	public String getBackupSchedule() {
		return backupSchedule;
	}
	public void setBackupSchedule(String backupSchedule) {
		this.backupSchedule = backupSchedule;
	}
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getNode_ip() {
		return node_ip;
	}
	public void setNode_ip(String node_ip) {
		this.node_ip = node_ip;
	}
}
