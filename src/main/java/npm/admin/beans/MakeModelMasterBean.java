package npm.admin.beans;

public class MakeModelMasterBean {
	
	private long id;
	private String makeModelName;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMakeModelName() {
		return makeModelName;
	}
	public void setMakeModelName(String makeModelName) {
		this.makeModelName = makeModelName;
	}
	
	
	@Override
	public String toString() {
		return "MakeModelMasterBean [id=" + id + ", makeModelName=" + makeModelName + "]";
	}
	
	
	

}
