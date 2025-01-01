package npm.admin.beans;

public class GroupMasterBean {
	
	private long id;
	private String groupName;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Override
	public String toString() {
		return "GroupMasterBean [id=" + id + ", groupName=" + groupName + "]";
	}
	
	

}
