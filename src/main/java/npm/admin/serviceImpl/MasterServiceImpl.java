package npm.admin.serviceImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;

//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.Channel;

import npm.admin.beans.ADConfigMasterBean;
import npm.admin.beans.CompanyMasterBean;
import npm.admin.beans.ConfigBackupBean;
import npm.admin.beans.EmailConfigMasterBean;
import npm.admin.beans.IcmpMasterBean;
import npm.admin.beans.LocationMasterBean;
import npm.admin.beans.MailTemplateBean;
import npm.admin.beans.NwIpScanBean;
import npm.admin.beans.PushConfigurationBean;
import npm.admin.beans.SNMPConfigMasterBean;
import npm.admin.beans.SubnetScanBean;
import npm.admin.beans.UserMasterBean;
import npm.admin.dao.MasterDao;
import npm.admin.service.MasterService;
import npm.model.CompanyMasterModel;
import npm.model.IcmpConfigModel;
import npm.model.LocationMasterModel;
import npm.model.NWIPScanModel;
import npm.model.Network_Discovery;
import npm.model.SnmpConfigMasterModel;
import npm.model.SubnetScanModel;
import npm.model.UserManageScopeModel;
import npm.model.UserMasterModel;
import npm.model.UserScopeMaster;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	MasterDao dao;

	public String addUser(String username, String password, String email, String mobileNo, String name,
			String confirmPassword, String role, String department) {
		// TODO Auto-generated method stub

		return dao.addUser(username, password, email, mobileNo, name, confirmPassword, role, department);

	}

	public String updateUser(long id, String password, String email, String mobileNo, String name,
			String confirmPassword, String role, String department) {
		// TODO Auto-generated method stub

		return dao.updateUser(id, password, email, mobileNo, name, confirmPassword, role, department);

	}

	public List<UserMasterModel> viewUsers() {
		// TODO Auto-generated method stub
		return dao.viewUsers();
	}

	public String deleteUser(long id) {
		// TODO Auto-generated method stub

		return dao.deleteUser(id);

	}

	public UserMasterBean editUser(long id) {
		// TODO Auto-generated method stub
		return dao.editUser(id);
	}

	// ICMP Configuration
	public String addIcmp(String packetSize, String packetCount, String poolingTime, String timeout, String ttl,
			String customerName, String location) {
		// TODO Auto-generated method stub

		return dao.addIcmp(packetSize, packetCount, poolingTime, timeout, ttl, customerName, location);

	}

	public List<IcmpConfigModel> viewIcmp() {
		// TODO Auto-generated method stub
		return dao.viewIcmp();
	}

	public String deleteIcmp(long id) {
		// TODO Auto-generated method stub

		return dao.deleteIcmp(id);

	}

	public IcmpMasterBean editIcmp(long id) {
		// TODO Auto-generated method stub
		return dao.editIcmp(id);
	}

	public String updateIcmp(long id, String packetSize, String packetCount, String poolingTime, String timeout,
			String ttl, String customerName, String location) {
		// TODO Auto-generated method stub

		return dao.updateIcmp(id, packetSize, packetCount, poolingTime, timeout, ttl, customerName, location);

	}

	// Company Master

	public String addCompany(String companyName, String address, String email, String number) {
		// TODO Auto-generated method stub

		return dao.addCompany(companyName, address, address, number);

	}

	public List<CompanyMasterModel> viewCompanyMaster() {
		// TODO Auto-generated method stub
		return dao.viewCompanyMaster();
	}

	public String deleteCompanyMaster(long id) {
		// TODO Auto-generated method stub

		return dao.deleteCompanyMaster(id);

	}

	public CompanyMasterBean editCompanyMaster(long id) {
		// TODO Auto-generated method stub
		return dao.editCompanyMaster(id);
	}

	public String updateCompanyMaster(long id, String companyName, String address, String email, String number) {
		// TODO Auto-generated method stub

		return dao.updateCompanyMaster(id, companyName, address, address, number);

	}

	// Location Master
	public String addlocationMaster(String companyName, String location) {
		// TODO Auto-generated method stub

		return dao.addlocationMaster(companyName, location);

	}

	public List<LocationMasterModel> viewLocationMaster() {
		// TODO Auto-generated method stub
		return dao.viewLocationMaster();
	}

	public String deleteLocationMaster(long id) {
		// TODO Auto-generated method stub

		return dao.deleteLocationMaster(id);

	}

	public LocationMasterBean editLocationMaster(long id) {
		// TODO Auto-generated method stub
		return dao.editLocationMaster(id);
	}

	public String updateLocationMaster(String companyName, String location, long id) {
		// TODO Auto-generated method stub

		return dao.updateLocationMaster(companyName, location, id);

	}

	public Map<String, String> getCompanyMap() {
		return dao.getCompanyMap();

	}

	// Network Discovery
	// IP SCAN
	public Map<String, String> getGroupMap() {
		return dao.getGroupMap();
	}

	public String addIpScan(String groupName, String ipScan, String endIpScan) {
		// TODO Auto-generated method stub

		return dao.addIpScan(groupName, ipScan, endIpScan);

	}

	public List<NWIPScanModel> viewIpScan() {
		// TODO Auto-generated method stub
		return dao.viewIpScan();
	}

	public String deleteIpScan(long id) {
		// TODO Auto-generated method stub

		return dao.deleteIpScan(id);

	}

	public NwIpScanBean editIpScan(long id) {
		// TODO Auto-generated method stub
		return dao.editIpScan(id);
	}

	public String updateIpScan(String groupName, String ipScan, String endIpScan, long id) {
		// TODO Auto-generated method stub

		return dao.updateIpScan(groupName, ipScan, endIpScan, id);

	}

	/* Add Group */
	public String addGroup(String groupName) {
		// TODO Auto-generated method stub
		return dao.addGroup(groupName);
	}

	/* Add Group Listing */
	public JSONArray groupListing() {

		JSONArray jsonarray_dao_output = dao.groupListing();

		return jsonarray_dao_output;
	}

	/* Delete Group */
	public String deleteGroup(long id) {
		// TODO Auto-generated method stub
		return dao.deleteGroup(id);
	}

	/* Add ServiceProvider */
	public String addServiceProvider(String serviceProviderName) {
		// TODO Auto-generated method stub
		return dao.addServiceProvider(serviceProviderName);
	}

	/* Add Service Provider Listing */
	public JSONArray serviceProviderListing() {

		JSONArray jsonarray_dao_output = dao.serviceProviderListing();

		return jsonarray_dao_output;
	}

	/* Delete Service Provider */
	public String deleteServiceProvider(long id) {
		// TODO Auto-generated method stub
		return dao.deleteServiceProvider(id);
	}

	/* Add MakeModel */
	public String addMakeModel(String makeModelName) {
		// TODO Auto-generated method stub
		return dao.addMakeModel(makeModelName);
	}

	/* Add MakeModel Listing */
	public JSONArray makeModelListing() {

		JSONArray jsonarray_dao_output = dao.makeModelListing();

		return jsonarray_dao_output;
	}

	/* Delete Make & Model */
	public String deleteMakeModel(long id) {
		// TODO Auto-generated method stub
		return dao.deleteMakeModel(id);
	}

	/* Add MakeModel */
	public String addDeviceType(String deviceTypeName) {
		// TODO Auto-generated method stub
		return dao.addDeviceType(deviceTypeName);
	}

	/* Add Device Type Listing */
	public JSONArray deviceTypeListing() {

		JSONArray jsonarray_dao_output = dao.deviceTypeListing();

		return jsonarray_dao_output;
	}

	/* Delete Device Type */
	public String deleteDeviceType(long id) {
		// TODO Auto-generated method stub
		return dao.deleteDeviceType(id);
	}

	/* Add AD Configuration */
	public String addADConfig(String ldapServerName, String username, String logonDomain, String password,
			long serverPort) {
		// TODO Auto-generated method stub

		return dao.addADConfig(ldapServerName, username, logonDomain, password, serverPort);

	}

	/* Add AD Configuration Listing */
	public JSONArray adConfigListing() {

		JSONArray jsonarray_dao_output = dao.adConfigListing();

		return jsonarray_dao_output;
	}

	/* Delete AD Configuration */
	public String deleteADConfig(long id) {
		// TODO Auto-generated method stub
		return dao.deleteADConfig(id);
	}

	/* Edit AD Configuration */
	public ADConfigMasterBean editADConfig(long id) {
		// TODO Auto-generated method stub
		return dao.editADConfig(id);
	}

	/* Update AD Configuration */
	public String updateADConfig(String ldapServerName, String username, String logonDomain, String password,
			long serverPort, long id) {
		// TODO Auto-generated method stub

		return dao.updateADConfig(ldapServerName, username, logonDomain, password, serverPort, id);

	}

	/* Add Email Configuration */
	public String addEmailConfig(String emailId, long port, String smtpServer, long timeout, String isSslTls,
			String username, String isSmtpAuth, String password) {
		// TODO Auto-generated method stub

		return dao.addEmailConfig(emailId, port, smtpServer, timeout, isSslTls, username, isSmtpAuth, password);

	}

	/* Add Email Configuration Listing */
	public JSONArray emailConfigListing() {

		JSONArray jsonarray_dao_output = dao.emailConfigListing();

		return jsonarray_dao_output;
	}

	/* Delete Email Configuration */
	public String deleteEmailConfig(long id) {
		// TODO Auto-generated method stub
		return dao.deleteEmailConfig(id);
	}

	/* Edit Email Configuration */
	public EmailConfigMasterBean editEmailConfig(long id) {
		// TODO Auto-generated method stub
		return dao.editEmailConfig(id);
	}

	/* Add SNMP Configuration */
	public String addSNMPConfig(String name, String description, String version, String community, String username,
			String authProtocol, String context, String authPassword, String encryptProtocol, long port,
			String encryptPassword, long timeout, long retries) {
		// TODO Auto-generated method stub

		return dao.addSNMPConfig(name, description, version, community, username, authProtocol, context, authPassword,
				encryptProtocol, port, encryptPassword, timeout, retries);

	}

	/* Add SNMP Configuration Listing */
	public JSONArray snmpConfigListing() {

		JSONArray jsonarray_dao_output = dao.snmpConfigListing();

		return jsonarray_dao_output;
	}

	/* Delete SNMP Configuration */
	public String deleteSNMPConfig(long id) {
		// TODO Auto-generated method stub
		return dao.deleteSNMPConfig(id);
	}

	public List<Network_Discovery> discoveredNetwork() {
		// TODO Auto-generated method stub
		return dao.discoveredNetwork();
	}

	public String addInterfaceParameter(String id, String monitoring, String bwHistory, String crc, String bwThreshold,
			String mailAlert, String smsAlert, String autoTicketing, String procureBW) {
		// TODO Auto-generated method stub
		return dao.addInterfaceParameter(id, monitoring, bwHistory, crc, bwThreshold, mailAlert, smsAlert,
				autoTicketing, procureBW);
	}

	public String checkPing(String ip) {
		String line2 = "";
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("cmd.exe", "/c", "ping " + ip);
		try {
			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {

				line2 += line + "&#13;";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in ping check");
		}
		return line2;

	}

	public String checkTraceRoute(String ip) {
		String line2 = "";
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("cmd.exe", "/c", "tracert -d " + ip);
		try {
			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {

				line2 += line + "&#13;";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in trace route check");
		}
		return line2;

	}

	public String getIpAddress() {
		// TODO Auto-generated method stub
		return dao.getIpAddress();
	}

	public String checkSSH(String ipAddress, String username, String password, String command) {
		System.out.println("inside Read SSH");
		String line2 = "";
//	 String command = "show system snmp sysinfo";

		Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
		String datetime = String.valueOf(timestampNow.getTime());
		System.out.println("Data:" + ipAddress + ":" + username + ":" + password + ":" + command + ":" + datetime);

		// Maintain Log
		File sampleFile = null;
		try {
			sampleFile = new File("C:\\ServiceProvisioning\\" + ipAddress + "_" + datetime + ".txt");
			if (sampleFile.exists()) {
				if (sampleFile.delete()) {
					System.out.println(sampleFile.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
			} else {
				sampleFile.createNewFile();
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		StringBuilder builder = new StringBuilder("Device OP:");

		// Start SSH Command
//		JSch jsch = null;
//		InputStream input = null;
//		Channel channel = null;
//		com.jcraft.jsch.Session session = null;
//		try {
//			jsch = new JSch();
//			session = jsch.getSession(username, ipAddress, 22);
//			session.setPassword(password);
//			session.setConfig("StrictHostKeyChecking", "no");
//			session.connect();
//			channel = session.openChannel("shell");
//			OutputStream ops = channel.getOutputStream();
//			PrintStream ps = null;
//			ps = new PrintStream(ops, true);
//			channel.connect();
//			input = channel.getInputStream();
//			ps.println(command);
//			ps.println("exit");
//			ps.close();
//			int SIZE = 1024;
//			byte[] tmp = null;
//			tmp = new byte[SIZE];
//			while (true) {
//				while (input.available() > 0) {
//					int i = input.read(tmp, 0, 1024);
//					if (i < 0) {
//						break;
//					}
//
//					String a = null;
//					a = new String(tmp, 0, i);
//					System.out.print("OP: " + a + ":End");
//					builder.append(a);
//					try {
//						PrintWriter out = null;
//						out = new PrintWriter(new FileWriter(sampleFile, true));
//						out.append(a);
//
//						out.close();
//					} catch (IOException e) {
//						System.out.println("COULD NOT LOG!!");
//					}
//
//				}
//				if (channel.isClosed()) {
//					System.out.println("exit-status: " + channel.getExitStatus());
//					break;
//				}
//				try {
//					Thread.sleep(1000);
//				} catch (Exception ee) {
//				}
//
//			}
//
//		} catch (Exception ee) {
//			System.out.println("errrrrrrrooorrr " + ee);
//		} finally {
//			try {
//				channel.disconnect();
//			} catch (Exception e) {
//				System.out.println("Exception:" + e);
//			}
//			try {
//				session.disconnect();
//			} catch (Exception e) {
//				System.out.println("Exception:" + e);
//			}
//			try {
//				input.close();
//			} catch (Exception e) {
//				// System.out.println("Exception:" + e);
//			}
//
//		}
		InputStream input = null;
		try {
			// Initialize JSch session
			JSch jsch = new JSch();
			com.jcraft.jsch.Session session = jsch.getSession(username, ipAddress, 22);
			session.setPassword(password);

			// Set properties to support SHA-256 algorithms
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			config.put("kex", "diffie-hellman-group14-sha256,diffie-hellman-group-exchange-sha256");
			config.put("cipher.s2c", "aes256-ctr,aes128-ctr");
			config.put("cipher.c2s", "aes256-ctr,aes128-ctr");
			config.put("mac.s2c", "hmac-sha2-256,hmac-sha1");
			config.put("mac.c2s", "hmac-sha2-256,hmac-sha1");

			session.setConfig(config);
			session.connect();

			// Open a channel and execute the command
			com.jcraft.jsch.ChannelExec channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(command);
			channel.setErrStream(System.err);

			// Retrieve and print the command output
			InputStream in = channel.getInputStream();
			channel.connect();

			File log9 = null;
			log9 = new File("C:\\ServiceProvisioning\\" + ipAddress + "_" + datetime + ".txt");
			try {
				if (log9.exists() == false) {
					System.out.println("We had to make a new file.");
					log9.createNewFile();
				}
			} catch (IOException e9) {
				System.out.println("COULD NOT LOG!!");
			}
			String line;
			// Set up BufferedWriter to write output to a file
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			BufferedWriter writer = new BufferedWriter(new FileWriter(log9));

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				writer.write(line);
				builder.append(line);
				writer.newLine(); // Write the output to the file

			}

			// Close the channel and session
			writer.close();
			reader.close();
			channel.disconnect();
			session.disconnect();

		} catch (Exception e) {
			System.out.println("Exception SSH:" + e);
			File log9 = null;
			log9 = new File("C:\\ServiceProvisioning\\" + ipAddress + "_" + datetime + "_Failed.txt");
			try {
				if (log9.exists() == false) {
					log9.createNewFile();
				}
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(log9));
					writer.write(e.toString());
					writer.close();
					// Write the output to the file
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("COULD NOT LOG!! " + e2);
				}
			} catch (IOException e9) {
				System.out.println("COULD NOT LOG!!");
			}

		}

		finally {
//			
			try {
				input.close();
			} catch (Exception e) {
				// System.out.println("Exception:" + e);
			}

		}

		return builder.toString();

	}

	public String checkPushSSH(String ipAddress, String username, String password, String commandlist) {
		System.out.println("inside impl");
		String line2 = "";
//		 String command = "show system snmp sysinfo";

		Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
		String datetime = String.valueOf(timestampNow.getTime());
		System.out.println("multiple commandData:" + ipAddress + ":" + username + ":" + password + ":" + commandlist
				+ ":" + datetime);

		// Maintain Log
		File sampleFile = null;
		try {
			sampleFile = new File("C:\\ServiceProvisioning\\" + ipAddress + "_" + datetime + ".txt");
			if (sampleFile.exists()) {
				if (sampleFile.delete()) {
					System.out.println(sampleFile.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
			} else {
				sampleFile.createNewFile();
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		StringBuilder builder = new StringBuilder("Device OP:");

		// Start SSH Command
//		JSch jsch = null;
//		InputStream input = null;
//		Channel channel = null;
//		com.jcraft.jsch.Session session = null;
//		try {
//			jsch = new JSch();
//			session = jsch.getSession(username, ipAddress, 22);
//			session.setPassword(password);
//			session.setConfig("StrictHostKeyChecking", "no");
//			session.connect();
//			channel = session.openChannel("shell");
//			OutputStream ops = channel.getOutputStream();
//			PrintStream ps = null;
//			ps = new PrintStream(ops, true);
//			channel.connect();
//			input = channel.getInputStream();
//			StringTokenizer st = new StringTokenizer(commandlist, ",");
//			while (st.hasMoreTokens()) {
//
//				String command = st.nextToken().toString();
//				ps.println(command);
//			}
//
//			ps.println("exit");
//			ps.close();
//			int SIZE = 1024;
//			byte[] tmp = null;
//			tmp = new byte[SIZE];
//			while (true) {
//				while (input.available() > 0) {
//					int i = input.read(tmp, 0, 1024);
//					if (i < 0) {
//						break;
//					}
//
//					String a = null;
//					a = new String(tmp, 0, i);
//					// System.out.print("OP: " + a + ":End");
//					builder.append(a);
//					try {
//						PrintWriter out = null;
//						out = new PrintWriter(new FileWriter(sampleFile, true));
//						out.append(a);
//
//						out.close();
//					} catch (IOException e) {
//						System.out.println("COULD NOT LOG!!");
//					}
//
//				}
//				if (channel.isClosed()) {
//					System.out.println("exit-status: " + channel.getExitStatus());
//					break;
//				}
//				try {
//					Thread.sleep(1000);
//				} catch (Exception ee) {
//				}
//
//			}
//
//		} catch (Exception ee) {
//			System.out.println("errrrrrrrooorrr " + ee);
//		} finally {
//			try {
//				channel.disconnect();
//			} catch (Exception e) {
//				System.out.println("Exception:" + e);
//			}
//			try {
//				session.disconnect();
//			} catch (Exception e) {
//				System.out.println("Exception:" + e);
//			}
//			try {
//				input.close();
//			} catch (Exception e) {
//				// System.out.println("Exception:" + e);
//			}
//
//		}

		InputStream input = null;
//		Channel channel = null;
//		com.jcraft.jsch.Session session = null;

		try {
			// Initialize JSch session
			JSch jsch = new JSch();
			com.jcraft.jsch.Session session = jsch.getSession(username, ipAddress, 22);
			session.setPassword(password);

			// Set properties to support SHA-256 algorithms
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			config.put("kex", "diffie-hellman-group14-sha256,diffie-hellman-group-exchange-sha256");
			config.put("cipher.s2c", "aes256-ctr,aes128-ctr");
			config.put("cipher.c2s", "aes256-ctr,aes128-ctr");
			config.put("mac.s2c", "hmac-sha2-256,hmac-sha1");
			config.put("mac.c2s", "hmac-sha2-256,hmac-sha1");

			session.setConfig(config);
			session.connect();

			// Open a channel and execute the command
			com.jcraft.jsch.ChannelExec channel = (ChannelExec) session.openChannel("exec");
			StringTokenizer st = new StringTokenizer(commandlist, ",");
			while (st.hasMoreTokens()) {

				String command22 = st.nextToken().toString();
				channel.setCommand(command22);
			}

			channel.setErrStream(System.err);

			// Retrieve and print the command output
			InputStream in = channel.getInputStream();
			channel.connect();

			File log9 = null;
			log9 = new File("C:\\ServiceProvisioning\\" + ipAddress + "_" + datetime + ".txt");
			try {
				if (log9.exists() == false) {
					System.out.println("We had to make a new file.");
					log9.createNewFile();
				}
			} catch (IOException e9) {
				System.out.println("COULD NOT LOG!!");
			}
			String line;
			// Set up BufferedWriter to write output to a file
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			BufferedWriter writer = new BufferedWriter(new FileWriter(log9));

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				writer.write(line);
				builder.append(line);
				writer.newLine(); // Write the output to the file

			}

			// Close the channel and session
			writer.close();
			reader.close();
			channel.disconnect();
			session.disconnect();

		} catch (Exception e) {
			System.out.println("Exception SSH:" + e);
			File log9 = null;
			log9 = new File("C:\\ServiceProvisioning\\" + ipAddress + "_" + datetime + "_Failed.txt");
			try {
				if (log9.exists() == false) {
					log9.createNewFile();
				}
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(log9));
					writer.write(e.toString());
					writer.close();
					// Write the output to the file
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("COULD NOT LOG!! " + e2);
				}
			} catch (IOException e9) {
				System.out.println("COULD NOT LOG!!");
			}

		}

		finally {
//			
			try {
				input.close();
			} catch (Exception e) {
				// System.out.println("Exception:" + e);
			}

		}

		return builder.toString();

	}

	public String userManageScopeService(String username, String groupname, String location, String admin,
			String dashboard, String report, String graph) {
		return dao.userManageScopeServiceDao(username, groupname, location, admin, dashboard, report, graph);
	}

	public List<UserManageScopeModel> viewUserManageScope() {
		return dao.viewUserManageScopeDao();
	}

	public String deleteUserManageScope(long ID) {
		return dao.deleteUserManageScopeDao(ID);
	}

	public String mailTemplateService(String templateName, String subject, String sendTime, String ipAddressList,
			String functionName, String msgBody, String attachment, String toMail, String periods,
			String attachmentType, String ccMail, String days, String isActive, String phone) {
		return dao.mailTemplateDao(templateName, subject, sendTime, ipAddressList, functionName, msgBody, attachment,
				toMail, periods, attachmentType, ccMail, days, isActive, phone);
	}

	public String deleteMailTemplateService(Long SR_NO) {
		return dao.deleteMailTemplateDao(SR_NO);
	}

	public JSONArray viewMailTemplate(String userScopeData) {
		return dao.viewMailTemplateDao(userScopeData);
	}

	public MailTemplateBean MailTemplateBeanService(Long SR_NO) {
		return dao.mailTemplateBeanDao(SR_NO);
	}

	public String updateMailTemplateService(String templateName, String subject, String sendTime, String ipAddressList,
			String functionName, String msgBody, String attachment, String toMail, String periods,
			String attachmentType, String ccMail, String days, String isActive, Long SR_NO, String phone) {
		return dao.updateMailTemplateDao(templateName, subject, sendTime, ipAddressList, functionName, msgBody,
				attachment, toMail, periods, attachmentType, ccMail, days, isActive, SR_NO, phone);
	}

	public Map<String, String> getDistinctNodes(String userScopeData) {
		return dao.getDistinctNodes(userScopeData);
	}

	public String addPushConfiguration(String templateName, String command, Timestamp datepicker, String status,
			String ip_address) {
		return dao.addPushConfigurationDao(templateName, command, datepicker, status, ip_address);
	}

//	public List<PushConfigurationModel> viewPushConfiguration() {
//		return dao.viewPushConfigurationDao();
//	}

	public String getTemplateName() {
		return dao.getTemplateName();
	}

	public String fetchCommand(String option) {
		return dao.fetchCommandDao(option);
	}

	public List<PushConfigurationBean> pushConfiReport(String from_date, String to_date, List<String> ip_list) {
		return dao.pushConfiReport(from_date, to_date, ip_list);
	}

	public String addSubnetScan(String groupName, String subnetScan, String endsubnetScan) {
		return dao.addSubnetScan(groupName, subnetScan, endsubnetScan);
	}

	public List<SubnetScanModel> viewSubnetScan() {
		return dao.viewSubnetScan();
	}

	public String deleteSubnetScan(long id) {
		return dao.deleteSubnetScan(id);
	}

	public SubnetScanBean editSubnetScan(long id) {
		return dao.editSubnetScan(id);
	}

	public String updateSubnetScan(String groupName, String subnetScan, String endsubnetScan, long id) {
		return dao.updateSubnetScan(groupName, subnetScan, endsubnetScan, id);
	}

	public List<UserScopeMaster> getUserScopeMaster() {
		return dao.getUserScopeMaster();
	}

	public String addConfigurationBackup(String deviceIp, String enablePass, String deviceMode, String confCommand,
			String username, String foCommand, String password) {
		return dao.addConfigurationBackup(deviceIp, enablePass, deviceMode, confCommand, username, foCommand, password);
	}

	public String deleteConfigurationBackup(long ID) {
		return dao.deleteConfigurationBackup(ID);
	}

	public JSONArray viewConfigurationBackup(String userScopeData) {
		return dao.viewConfigurationBackup(userScopeData);
	}

	public ConfigBackupBean ConfigBackupBeanService(long ID) {
		return dao.ConfigBackupBeanDao(ID);
	}

	public String updateConfigurationBackupService(String deviceIp, String deviceMode, String confCommand,
			String foCommand, long ID) {
		return dao.updateConfigurationBackupDao(deviceIp, deviceMode, confCommand, foCommand, ID);
	}

	public String addScheduleScanService(String scheduleType, String startIp, String subnetIp, String once_time) {
		return dao.addScheduleScanDao(scheduleType, startIp, subnetIp, once_time);
	}

	public JSONArray viewScheduleScanService() {
		return dao.viewScheduleScanDao();
	}

	public String deleteScheduleScanService(long ID) {
		return dao.deleteScheduleScanDao(ID);
	}

	public SNMPConfigMasterBean fetchSMNPConfigService(String deviceIp, String snmp) {
		return dao.fetchSMNPConfigDao(deviceIp, snmp);
	}

	public String manualTopology(String sourceDeviceIp, String source_interface_name, String destinationDeviceIp,
			String destination_interface_name) {
		return dao.manualTopology(sourceDeviceIp, source_interface_name, destinationDeviceIp,
				destination_interface_name);
	}

	public JSONArray getManualTopology(String string, String contextPath) {
		return dao.getManualTopology(string, contextPath);
	}

	public String GetSpeedTestDetails(String srcIP, String desIP) {
		// TODO Auto-generated method stub
		return dao.GetSpeedTestDetails(srcIP, desIP);
	}

	public String saveSLADataMaster(String from_date, String to_date, List<String> dayslist) {
		// TODO Auto-generated method stub
		return dao.saveSLADataMaster(from_date, to_date, dayslist);
	}

	public JSONArray getslamasterdata() {
		// TODO Auto-generated method stub
		return dao.getslamasterdata();
	}
}
