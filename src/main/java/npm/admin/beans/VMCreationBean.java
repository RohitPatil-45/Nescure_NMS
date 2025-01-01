package npm.admin.beans;

import java.io.Serializable;

public class VMCreationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String activity;

	private String hostip;

	private String details;

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getHostip() {
		return hostip;
	}

	public void setHostip(String hostip) {
		this.hostip = hostip;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "VMCreationBean{" + "activity=" + activity + ", hostip=" + hostip + ", details=" + details + '}';
	}

}
