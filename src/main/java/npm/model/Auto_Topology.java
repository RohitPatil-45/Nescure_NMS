package npm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auto_topology" )
public class Auto_Topology {
	

	@Id
	private int sr_no ;
	private String device_ip ; 
	private String device_name ; 
	private String remote_device_id ; 
	private String remote_chassis_type ; 
	private String remote_device_mac ; 
	private String remote_port_subtype ; 
	private String remote_port_subtype_value ; 
	private String remote_device_name ; 
	private String remote_device_desc ; 
	private String local_device_mac ; 
	private String local_device_name ; 
	private String local_device_desc ; 
	private String remote_port_name ; 
	private String local_interface_id ; 
	private String local_interface_name ;
	private String remote_device_ip ; 
	
	
	
	public String getRemote_device_ip() {
		return remote_device_ip;
	}
	public void setRemote_device_ip(String remote_device_ip) {
		this.remote_device_ip = remote_device_ip;
	}
	public int getSr_no() {
		return sr_no;
	}
	public void setSr_no(int sr_no) {
		this.sr_no = sr_no;
	}
	public String getDevice_ip() {
		return device_ip;
	}
	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getRemote_device_id() {
		return remote_device_id;
	}
	public void setRemote_device_id(String remote_device_id) {
		this.remote_device_id = remote_device_id;
	}
	public String getRemote_chassis_type() {
		return remote_chassis_type;
	}
	public void setRemote_chassis_type(String remote_chassis_type) {
		this.remote_chassis_type = remote_chassis_type;
	}
	public String getRemote_device_mac() {
		return remote_device_mac;
	}
	public void setRemote_device_mac(String remote_device_mac) {
		this.remote_device_mac = remote_device_mac;
	}
	public String getRemote_port_subtype() {
		return remote_port_subtype;
	}
	public void setRemote_port_subtype(String remote_port_subtype) {
		this.remote_port_subtype = remote_port_subtype;
	}
	public String getRemote_port_subtype_value() {
		return remote_port_subtype_value;
	}
	public void setRemote_port_subtype_value(String remote_port_subtype_value) {
		this.remote_port_subtype_value = remote_port_subtype_value;
	}
	public String getRemote_device_name() {
		return remote_device_name;
	}
	public void setRemote_device_name(String remote_device_name) {
		this.remote_device_name = remote_device_name;
	}
	public String getRemote_device_desc() {
		return remote_device_desc;
	}
	public void setRemote_device_desc(String remote_device_desc) {
		this.remote_device_desc = remote_device_desc;
	}
	public String getLocal_device_mac() {
		return local_device_mac;
	}
	public void setLocal_device_mac(String local_device_mac) {
		this.local_device_mac = local_device_mac;
	}
	public String getLocal_device_name() {
		return local_device_name;
	}
	public void setLocal_device_name(String local_device_name) {
		this.local_device_name = local_device_name;
	}
	public String getLocal_device_desc() {
		return local_device_desc;
	}
	public void setLocal_device_desc(String local_device_desc) {
		this.local_device_desc = local_device_desc;
	}
	public String getRemote_port_name() {
		return remote_port_name;
	}
	public void setRemote_port_name(String remote_port_name) {
		this.remote_port_name = remote_port_name;
	}
	public String getLocal_interface_id() {
		return local_interface_id;
	}
	public void setLocal_interface_id(String local_interface_id) {
		this.local_interface_id = local_interface_id;
	}
	public String getLocal_interface_name() {
		return local_interface_name;
	}
	public void setLocal_interface_name(String local_interface_name) {
		this.local_interface_name = local_interface_name;
	} 

}
