package npm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="custome_topology_view")
public class custome_topology_view {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	  @Column(name = "ID", updatable = false, nullable = false)
//	@Column(name="ID")
	private int id;
	
	private String fromIp;
	
	private String node_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFromIp() {
		return fromIp;
	}

	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}
	
//	privaCte String id;
	
	
	
	
}
