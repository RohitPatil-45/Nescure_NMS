package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NODE_STATUS_HISTORY" )
public class NodeStatusHistory {



	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private String NODE_STATUS;
	
	@Column
	private Timestamp EVENT_TIMESTAMP;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getNODE_IP() {
		return NODE_IP;
	}

	public void setNODE_IP(String nODE_IP) {
		NODE_IP = nODE_IP;
	}

	public String getNODE_STATUS() {
		return NODE_STATUS;
	}

	public void setNODE_STATUS(String nODE_STATUS) {
		NODE_STATUS = nODE_STATUS;
	}

	public Timestamp getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}

	public void setEVENT_TIMESTAMP(Timestamp eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}

	@Override
	public String toString() {
		return "NodeStatusHistory [ID=" + ID + ", NODE_IP=" + NODE_IP + ", NODE_STATUS=" + NODE_STATUS
				+ ", EVENT_TIMESTAMP=" + EVENT_TIMESTAMP + "]";
	}
	
	
	
	
}
