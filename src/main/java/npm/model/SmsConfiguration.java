package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SMS_CONFIGURATION")
public class SmsConfiguration implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long ID;
	@Column
	private String SMS_PROVIDER;
	@Column
	private String USERNAME;
	@Column
	private String SMS_URL;
	@Column
	private String PASSWORD;
	
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getSMS_PROVIDER() {
		return SMS_PROVIDER;
	}
	public void setSMS_PROVIDER(String sMS_PROVIDER) {
		SMS_PROVIDER = sMS_PROVIDER;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	
	public String getSMS_URL() {
		return SMS_URL;
	}
	public void setSMS_URL(String sMS_URL) {
		SMS_URL = sMS_URL;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	@Override
	public String toString() {
		return "SmsConfiguration [ID=" + ID + ", SMS_PROVIDER=" + SMS_PROVIDER + ", USERNAME=" + USERNAME + ", SMS_URL="
				+ SMS_URL + ", PASSWORD=" + PASSWORD + "]";
	}
}
