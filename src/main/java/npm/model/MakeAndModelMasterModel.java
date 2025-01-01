package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MAKE_MODEL_MASTER")

public class MakeAndModelMasterModel implements Serializable {

	private static final long serialVersionUID = -2264642949863409860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Column
	private String MAKE_MODEL_NAME;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getMAKE_MODEL_NAME() {
		return MAKE_MODEL_NAME;
	}

	public void setMAKE_MODEL_NAME(String mAKE_MODEL_NAME) {
		MAKE_MODEL_NAME = mAKE_MODEL_NAME;
	}

	@Override
	public String toString() {
		return "MakeAndModelMasterModel [ID=" + ID + ", MAKE_MODEL_NAME=" + MAKE_MODEL_NAME + "]";
	}

}
