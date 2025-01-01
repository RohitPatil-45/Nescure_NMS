package npm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INTERFACE_STATUS_DIFF")
public class InterfaceStatusDiffModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Column
	private String NODE_IP;
	@Column
	private String INTERFACE_NAME;
	@Column
	private Timestamp DOWN_EVENT_TIMESTAMP;
	@Column
	private Timestamp UP_EVENT_TIMESTAMP;
	@Column
	private String TIME_DIFF;
	@Column
	private Long TOTAL_SEC;

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

	public String getINTERFACE_NAME() {
		return INTERFACE_NAME;
	}

	public void setINTERFACE_NAME(String iNTERFACE_NAME) {
		INTERFACE_NAME = iNTERFACE_NAME;
	}

	public Timestamp getDOWN_EVENT_TIMESTAMP() {
		return DOWN_EVENT_TIMESTAMP;
	}

	public void setDOWN_EVENT_TIMESTAMP(Timestamp dOWN_EVENT_TIMESTAMP) {
		DOWN_EVENT_TIMESTAMP = dOWN_EVENT_TIMESTAMP;
	}

	public Timestamp getUP_EVENT_TIMESTAMP() {
		return UP_EVENT_TIMESTAMP;
	}

	public void setUP_EVENT_TIMESTAMP(Timestamp uP_EVENT_TIMESTAMP) {
		UP_EVENT_TIMESTAMP = uP_EVENT_TIMESTAMP;
	}

	public String getTIME_DIFF() {
		return TIME_DIFF;
	}

	public void setTIME_DIFF(String tIME_DIFF) {
		TIME_DIFF = tIME_DIFF;
	}

	public Long getTOTAL_SEC() {
		return TOTAL_SEC;
	}

	public void setTOTAL_SEC(Long tOTAL_SEC) {
		TOTAL_SEC = tOTAL_SEC;
	}

	@Override
	public String toString() {
		return "InterfaceStatusDiffModel [ID=" + ID + ", NODE_IP=" + NODE_IP + ", INTERFACE_NAME=" + INTERFACE_NAME
				+ ", DOWN_EVENT_TIMESTAMP=" + DOWN_EVENT_TIMESTAMP + ", UP_EVENT_TIMESTAMP=" + UP_EVENT_TIMESTAMP
				+ ", TIME_DIFF=" + TIME_DIFF + ", TOTAL_SEC=" + TOTAL_SEC + "]";
	}

}
