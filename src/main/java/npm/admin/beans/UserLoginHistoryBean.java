package npm.admin.beans;

import java.sql.Timestamp;

import javax.persistence.Column;

public class UserLoginHistoryBean {

	private Long ID;

	private String username;

	private String ipaddress;

	private String hostname;

	private String activity;

	private String timestamp;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "UserLoginHistoryBean [ID=" + ID + ", username=" + username + ", ipaddress=" + ipaddress + ", hostname="
				+ hostname + ", activity=" + activity + ", timestamp=" + timestamp + "]";
	}

	

}
