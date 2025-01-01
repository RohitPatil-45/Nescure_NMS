package npm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "SLA_Master")
public class SLAMasterModel {

	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Lob
	@Column
	private String days_data;
	@Column
	private String numeric_days_data;
	@Column
	private String from_time;
	@Column
	private String to_time;
	@Column
	private String between_data;

	public String getBetween_data() {
		return between_data;
	}

	public void setBetween_data(String between_data) {
		this.between_data = between_data;
	}

	public String getNumeric_days_data() {
		return numeric_days_data;
	}

	public void setNumeric_days_data(String numeric_days_data) {
		this.numeric_days_data = numeric_days_data;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getDays_data() {
		return days_data;
	}

	public void setDays_data(String days_data) {
		this.days_data = days_data;
	}

	public String getFrom_time() {
		return from_time;
	}

	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}

	public String getTo_time() {
		return to_time;
	}

	public void setTo_time(String to_time) {
		this.to_time = to_time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SLAMasterModel [ID=" + ID + ", days_data=" + days_data + ", numeric_days_data=" + numeric_days_data
				+ ", from_time=" + from_time + ", to_time=" + to_time + ", between_data=" + between_data + "]";
	}

}
