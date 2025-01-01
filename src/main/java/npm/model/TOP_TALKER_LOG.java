package npm.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TOP_TALKER_LOG" )
public class TOP_TALKER_LOG implements Serializable {

        
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int SR_NO;
        private String DEVICE_IP;
        private String SOURCE_IP;
        private String DESTINATION_IP;
        private String SOURCE_PORT;
        private String DESTINATION_PORT;
        private int DESTINATION_OCTECT;
        private String PROTOCOL;        
        private Date DATE_TIME;
        private String SOURCE_INTERFACE;
        private String DESTINATION_INTERFACE;
        private int DESTINATION_PACKETS;
        private String NEXT_HOP;
		public int getSR_NO() {
			return SR_NO;
		}
		public void setSR_NO(int sR_NO) {
			SR_NO = sR_NO;
		}
		public String getDEVICE_IP() {
			return DEVICE_IP;
		}
		public void setDEVICE_IP(String dEVICE_IP) {
			DEVICE_IP = dEVICE_IP;
		}
		public String getSOURCE_IP() {
			return SOURCE_IP;
		}
		public void setSOURCE_IP(String sOURCE_IP) {
			SOURCE_IP = sOURCE_IP;
		}
		public String getDESTINATION_IP() {
			return DESTINATION_IP;
		}
		public void setDESTINATION_IP(String dESTINATION_IP) {
			DESTINATION_IP = dESTINATION_IP;
		}
		public String getSOURCE_PORT() {
			return SOURCE_PORT;
		}
		public void setSOURCE_PORT(String sOURCE_PORT) {
			SOURCE_PORT = sOURCE_PORT;
		}
		public String getDESTINATION_PORT() {
			return DESTINATION_PORT;
		}
		public void setDESTINATION_PORT(String dESTINATION_PORT) {
			DESTINATION_PORT = dESTINATION_PORT;
		}
		public int getDESTINATION_OCTECT() {
			return DESTINATION_OCTECT;
		}
		public void setDESTINATION_OCTECT(int dESTINATION_OCTECT) {
			DESTINATION_OCTECT = dESTINATION_OCTECT;
		}
		public String getPROTOCOL() {
			return PROTOCOL;
		}
		public void setPROTOCOL(String pROTOCOL) {
			PROTOCOL = pROTOCOL;
		}
		public Date getDATE_TIME() {
			return DATE_TIME;
		}
		public void setDATE_TIME(Date dATE_TIME) {
			DATE_TIME = dATE_TIME;
		}
		public String getSOURCE_INTERFACE() {
			return SOURCE_INTERFACE;
		}
		public void setSOURCE_INTERFACE(String sOURCE_INTERFACE) {
			SOURCE_INTERFACE = sOURCE_INTERFACE;
		}
		public String getDESTINATION_INTERFACE() {
			return DESTINATION_INTERFACE;
		}
		public void setDESTINATION_INTERFACE(String dESTINATION_INTERFACE) {
			DESTINATION_INTERFACE = dESTINATION_INTERFACE;
		}
		public int getDESTINATION_PACKETS() {
			return DESTINATION_PACKETS;
		}
		public void setDESTINATION_PACKETS(int dESTINATION_PACKETS) {
			DESTINATION_PACKETS = dESTINATION_PACKETS;
		}
		public String getNEXT_HOP() {
			return NEXT_HOP;
		}
		public void setNEXT_HOP(String nEXT_HOP) {
			NEXT_HOP = nEXT_HOP;
		}
		@Override
		public String toString() {
			return "TOP_TALKER_LOG [SR_NO=" + SR_NO + ", DEVICE_IP=" + DEVICE_IP + ", SOURCE_IP=" + SOURCE_IP
					+ ", DESTINATION_IP=" + DESTINATION_IP + ", SOURCE_PORT=" + SOURCE_PORT + ", DESTINATION_PORT="
					+ DESTINATION_PORT + ", DESTINATION_OCTECT=" + DESTINATION_OCTECT + ", PROTOCOL=" + PROTOCOL
					+ ", DATE_TIME=" + DATE_TIME + ", SOURCE_INTERFACE=" + SOURCE_INTERFACE + ", DESTINATION_INTERFACE="
					+ DESTINATION_INTERFACE + ", DESTINATION_PACKETS=" + DESTINATION_PACKETS + ", NEXT_HOP=" + NEXT_HOP
					+ "]";
		}
        
        
        
        

        
        
        

        
}

