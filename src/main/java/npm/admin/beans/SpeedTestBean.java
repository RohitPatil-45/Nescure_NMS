package npm.admin.beans;

import java.sql.Timestamp;

public class SpeedTestBean {

	private Long ID;

	private String device_IP;

	private String hostName;

	private String discription;

	private Timestamp timestamp;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getDevice_IP() {
		return device_IP;
	}

	public void setDevice_IP(String device_IP) {
		this.device_IP = device_IP;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public String toString() {
		return "SpeedTestBean [ID=" + ID + ", device_IP=" + device_IP + ", hostName=" + hostName + ", discription="
				+ discription + ", timestamp=" + timestamp + "]";
	}

}
