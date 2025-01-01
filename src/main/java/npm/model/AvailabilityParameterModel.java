package npm.model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="availability_parameter" )
public class AvailabilityParameterModel implements Serializable{

	private static final long serialVersionUID = -3659985529856705635L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	@Column
	private LocalTime FROM_TIME;
	@Column
	private LocalTime TO_TIME;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public LocalTime getFROM_TIME() {
		return FROM_TIME;
	}
	public void setFROM_TIME(LocalTime fROM_TIME) {
		FROM_TIME = fROM_TIME;
	}
	public LocalTime getTO_TIME() {
		return TO_TIME;
	}
	public void setTO_TIME(LocalTime tO_TIME) {
		TO_TIME = tO_TIME;
	}
	@Override
	public String toString() {
		return "AvailabilityParameterModel [ID=" + ID + ", FROM_TIME=" + FROM_TIME + ", TO_TIME=" + TO_TIME + "]";
	}
	
	
	
	

}
