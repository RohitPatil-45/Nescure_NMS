package npm.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "NODE_STATUS_HISTORY")
public class NodeStatusHistoryModel implements Serializable {

	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;

	@Column
	private String NODE_IP;

	@Column
	private String NODE_STATUS;

	@UpdateTimestamp
	private LocalDateTime EVENT_TIMESTAMP;

	
	
	
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

	public LocalDateTime getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}

	public void setEVENT_TIMESTAMP(LocalDateTime eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}

	
	
	@Override
	public String toString() {
		return "NodeStatusHistoryModel [ID=" + ID + ", NODE_IP=" + NODE_IP + ", NODE_STATUS=" + NODE_STATUS
				+ ", EVENT_TIMESTAMP=" + EVENT_TIMESTAMP + "]";
	}

	
	
	
	
	
	
	
}
