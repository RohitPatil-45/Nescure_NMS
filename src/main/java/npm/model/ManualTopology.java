package npm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auto_topology_manual")
public class ManualTopology {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", updatable = false ,nullable = false)
	private Long Id;
	@Column
	private String source_device_ip;
	@Column
	private String source_interface_name;
	@Column
	private String destination_device_ip;
	@Column
	private String destination_interface_name;
	@Column
	private String source_device_name;
	@Column
	private String destination_device_name;
	@Column
	private String source_device_mac;
	@Column
	private String destination_device_mac;
	@Column
	private String POOLING_ID;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getSource_device_ip() {
		return source_device_ip;
	}
	public void setSource_device_ip(String source_device_ip) {
		this.source_device_ip = source_device_ip;
	}
	public String getSource_interface_name() {
		return source_interface_name;
	}
	public void setSource_interface_name(String source_interface_name) {
		this.source_interface_name = source_interface_name;
	}
	public String getDestination_device_ip() {
		return destination_device_ip;
	}
	public void setDestination_device_ip(String destination_device_ip) {
		this.destination_device_ip = destination_device_ip;
	}
	public String getDestination_interface_name() {
		return destination_interface_name;
	}
	public void setDestination_interface_name(String destination_interface_name) {
		this.destination_interface_name = destination_interface_name;
	}
	public String getSource_device_name() {
		return source_device_name;
	}
	public void setSource_device_name(String source_device_name) {
		this.source_device_name = source_device_name;
	}
	public String getDestination_device_name() {
		return destination_device_name;
	}
	public void setDestination_device_name(String destination_device_name) {
		this.destination_device_name = destination_device_name;
	}
	public String getSource_device_mac() {
		return source_device_mac;
	}
	public void setSource_device_mac(String source_device_mac) {
		this.source_device_mac = source_device_mac;
	}
	public String getDestination_device_mac() {
		return destination_device_mac;
	}
	public void setDestination_device_mac(String destination_device_mac) {
		this.destination_device_mac = destination_device_mac;
	}
	public String getPOOLING_ID() {
		return POOLING_ID;
	}
	public void setPOOLING_ID(String pOOLING_ID) {
		POOLING_ID = pOOLING_ID;
	}
	@Override
	public String toString() {
		return "ManualTopology [Id=" + Id + ", source_device_ip=" + source_device_ip + ", source_interface_name="
				+ source_interface_name + ", destination_device_ip=" + destination_device_ip
				+ ", destination_interface_name=" + destination_interface_name + ", source_device_name="
				+ source_device_name + ", destination_device_name=" + destination_device_name + ", source_device_mac="
				+ source_device_mac + ", destination_device_mac=" + destination_device_mac + ", POOLING_ID="
				+ POOLING_ID + "]";
	}
	
	
	

	
	
	
}
