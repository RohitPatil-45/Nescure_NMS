package npm.admin.beans;

public class UserManageScopeBean {
	
	private long id;
	private String username;
	private String groupname;
	private String location;
	private String admin;
	private String dashboard;
	private String report;
	private String graph;
	private String user_scope;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getDashboard() {
		return dashboard;
	}
	public void setDashboard(String dashboard) {
		this.dashboard = dashboard;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getGraph() {
		return graph;
	}
	public void setGraph(String graph) {
		this.graph = graph;
	}
	public String getUser_scope() {
		return user_scope;
	}
	public void setUser_scope(String user_scope) {
		this.user_scope = user_scope;
	}
	@Override
	public String toString() {
		return "UserManageScopeBean [id=" + id + ", username=" + username + ", groupname=" + groupname + ", location="
				+ location + ", admin=" + admin + ", dashboard=" + dashboard + ", report=" + report + ", graph=" + graph
				+ ", user_scope=" + user_scope + "]";
	}
	
	
	

}
