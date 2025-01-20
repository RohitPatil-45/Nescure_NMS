package npm.admin.daoImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;

import dynamicPassword.Encryption;
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
import npm.admin.beans.VMCreationBean;
import npm.admin.dao.MasterDao;
import npm.configuration.AbstractDao;
import npm.model.ADConfigMasterModel;
import npm.model.CompanyMasterModel;
import npm.model.DeviceTypeMasterModel;
import npm.model.EmailConfigMasterModel;
import npm.model.GroupMasterModel;
import npm.model.IcmpConfigModel;
import npm.model.InterfaceMonitoring;
import npm.model.LocationMasterModel;
import npm.model.MailTemplate;
import npm.model.MakeAndModelMasterModel;
import npm.model.ManualTopology;
import npm.model.NWIPScanModel;
import npm.model.Network_Discovery;
import npm.model.NodeParameterModel;
import npm.model.PushConfigurationModel;
import npm.model.SLAMasterModel;
import npm.model.ScheduleScan;
import npm.model.ServiceProviderMasterModel;
import npm.model.SnmpConfigMasterModel;
import npm.model.SubnetScanModel;
import npm.model.UserManageScopeModel;
import npm.model.UserMasterModel;
import npm.model.UserScopeMaster;
import npm.model.Windows_Event_Log;

@Repository
@Transactional
public class MasterDaoImpl extends AbstractDao<Integer, UserMasterModel> implements MasterDao {

	public String addUser(String username, String password, String email, String mobileNo, String name,
			String confirmPassword, String role, String department) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("username:" + username);
		System.out.println("password:" + password);
		System.out.println("email:" + email);
		System.out.println("mobileNo:" + mobileNo);
		System.out.println("department:" + department);
		System.out.println("role:" + role);
		System.out.println("name:" + name);
		System.out.println("confirmPassword: " + confirmPassword);

		String result = null;
		try {
			Encryption encrypt = new Encryption();
			UserMasterModel user = new UserMasterModel();
			user.setUSERNAME(username);
			user.setPASSWORD(encrypt.encrypt(password));
			user.setEMAIL(email);
			user.setMOBILE_NO(mobileNo);
			user.setCONFIRM_PASSWORD(encrypt.encrypt(confirmPassword));
			user.setDEPARTMENT(department);
			user.setROLE(role);
			user.setNAME(name);
			getSession().save(user);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public String updateUser(long id, String password, String email, String mobileNo, String name,
			String confirmPassword, String role, String department) {
		String result = null;
		try {
			Encryption encrypt = new Encryption();
			Query q = getSession().createQuery(
					"Update UserMasterModel set email=:email,mobile_no=:mobileNo, name=:name, password=:password, confirm_password=:confirmPassword, role=:role, department=:department where id=:id");
			q.setParameter("email", email);
			q.setParameter("mobileNo", mobileNo);
			q.setParameter("id", id);
			q.setParameter("password", encrypt.encrypt(password));
			q.setParameter("confirmPassword", encrypt.encrypt(confirmPassword));
			q.setParameter("role", role);
			q.setParameter("department", department);
			q.setParameter("name", name);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<UserMasterModel> viewUsers() {
		Criteria criteria = getSession().createCriteria(UserMasterModel.class);
		return criteria.list();
	}

	public String deleteUser(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete user:" + id);

		String result = null;

		try {
			Query q = getSession().createQuery("Delete from UserMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}

	public UserMasterBean editUser(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit user:" + id);
		String result = null;
		UserMasterBean newObj = null;

		UserMasterBean user = new UserMasterBean();
		try {
			Query q = getSession().createQuery("from UserMasterModel where ID=:id");
			q.setParameter("id", id);
			// List<UserMasterModel> listData = q.list();
			List<UserMasterModel> listdata = q.list();
			user.setName(listdata.get(0).getNAME());
			user.setMobileNo(listdata.get(0).getMOBILE_NO());
			user.setEmail(listdata.get(0).getEMAIL());
			user.setDepartment(listdata.get(0).getDEPARTMENT());
			user.setRole(listdata.get(0).getROLE());
			user.setUsername(listdata.get(0).getUSERNAME());
			user.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return user;
	}

	// ICMP Configuration
	public String addIcmp(String packetSize, String packetCount, String poolingTime, String timeout, String ttl,
			String customerName, String location) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("packetSize:" + packetSize);
		System.out.println("packetCount:" + packetCount);
		System.out.println("poolingTime:" + poolingTime);
		System.out.println("timeout:" + timeout);
		System.out.println("ttl:" + ttl);
		System.out.println("customerName:" + customerName);
		System.out.println("location:" + location);

		String result = null;
		try {
			IcmpConfigModel icmp = new IcmpConfigModel();
			icmp.setPACKET_SIZE(packetSize);
			icmp.setPACKET_COUNT(packetCount);
			icmp.setPOOLING_TIME(poolingTime);
			icmp.setTIMEOUT(timeout);
			icmp.setTTL(ttl);
			icmp.setCUSTOMER_NAME(customerName);
			icmp.setLOCATION(location);

			getSession().save(icmp);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public Map<String, String> getCompanyMap() {
		Map<String, String> companyMap = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from CompanyMasterModel ");

			List<CompanyMasterModel> listdata = q.list();

			for (CompanyMasterModel company : listdata) {
				companyMap.put(company.getCOMPANY_NAME(), company.getCOMPANY_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return companyMap;
	}

	public List<IcmpConfigModel> viewIcmp() {

		Criteria criteria = getSession().createCriteria(IcmpConfigModel.class);
		return criteria.list();
	}

	public String deleteIcmp(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete user:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from IcmpConfigModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}

	public IcmpMasterBean editIcmp(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit Icmp:" + id);
		String result = null;
		IcmpMasterBean newObj = null;

		IcmpMasterBean icmp = new IcmpMasterBean();
		try {
			Query q = getSession().createQuery("from IcmpConfigModel where ID=:id");
			q.setParameter("id", id);
			// List<UserMasterModel> listData = q.list();
			List<IcmpConfigModel> listdata = q.list();
			icmp.setPacketSize(listdata.get(0).getPACKET_SIZE());
			icmp.setPacketCount(listdata.get(0).getPACKET_COUNT());
			icmp.setPoolingTime(listdata.get(0).getPOOLING_TIME());
			icmp.setTimeout(listdata.get(0).getTIMEOUT());
			icmp.setTtl(listdata.get(0).getTTL());
			icmp.setCustomerName(listdata.get(0).getCUSTOMER_NAME());
			icmp.setLocation(listdata.get(0).getLOCATION());
			icmp.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return icmp;
	}

	public String updateIcmp(long id, String packetSize, String packetCount, String poolingTime, String timeout,
			String ttl, String customerName, String location) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update IcmpConfigModel set packet_size=:packetSize,packet_count=:packetCount,pooling_time=:poolingTime, timeout=:timeout, ttl=:ttl, customer_name=:customerName, location=:location where id=:id");
			q.setParameter("packetSize", packetSize);
			q.setParameter("packetCount", packetCount);
			q.setParameter("poolingTime", poolingTime);
			q.setParameter("id", id);
			q.setParameter("timeout", timeout);
			q.setParameter("ttl", ttl);
			q.setParameter("customerName", customerName);
			q.setParameter("location", location);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	// Company Master
	public String addCompany(String companyName, String address, String email, String number) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("companyName:" + companyName);
		System.out.println("address:" + address);
		System.out.println("email:" + email);
		System.out.println("number:" + number);
		String result = null;
		try {
			CompanyMasterModel companymaster = new CompanyMasterModel();
			companymaster.setCOMPANY_NAME(companyName);
			companymaster.setADDRESS(address);
			companymaster.setEMAIL(email);
			companymaster.setNUMBER(number);

			getSession().save(companymaster);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<CompanyMasterModel> viewCompanyMaster() {

		Criteria criteria = getSession().createCriteria(CompanyMasterModel.class);
		return criteria.list();
	}

	public String deleteCompanyMaster(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete company:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from CompanyMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}

	public CompanyMasterBean editCompanyMaster(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit Company Master:" + id);
		String result = null;

		CompanyMasterBean companyMaster = new CompanyMasterBean();
		try {
			Query q = getSession().createQuery("from CompanyMasterModel where ID=:id");
			q.setParameter("id", id);
			// List<UserMasterModel> listData = q.list();
			List<CompanyMasterModel> listdata = q.list();

			companyMaster.setCompanyName(listdata.get(0).getCOMPANY_NAME());
			companyMaster.setAddress(listdata.get(0).getADDRESS());
			companyMaster.setEmail(listdata.get(0).getEMAIL());
			companyMaster.setContact(listdata.get(0).getNUMBER());
			companyMaster.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return companyMaster;
	}

	public String updateCompanyMaster(long id, String companyName, String address, String email, String number) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update CompanyMasterModel set company_name=:companyName,address=:address,email=:email, number=:number where id=:id");
			q.setParameter("companyName", companyName);
			q.setParameter("address", address);
			q.setParameter("email", email);
			q.setParameter("id", id);
			q.setParameter("number", number);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	// Location Master
	public String addlocationMaster(String companyName, String location) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("companyName:" + companyName);
		System.out.println("address:" + location);

		String result = null;
		try {
			LocationMasterModel lmm = new LocationMasterModel();
			lmm.setCOMPANY_NAME(companyName);
			lmm.setLOCATION_NAME(location);
			getSession().save(lmm);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<LocationMasterModel> viewLocationMaster() {

		Criteria criteria = getSession().createCriteria(LocationMasterModel.class);
		return criteria.list();
	}

	public String deleteLocationMaster(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete location:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from LocationMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}

	public LocationMasterBean editLocationMaster(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit Location Master:" + id);
		String result = null;

		LocationMasterBean locationMaster = new LocationMasterBean();
		try {
			Query q = getSession().createQuery("from LocationMasterModel where ID=:id");
			q.setParameter("id", id);
			// List<UserMasterModel> listData = q.list();
			List<LocationMasterModel> listdata = q.list();

			locationMaster.setCompanyName(listdata.get(0).getCOMPANY_NAME());
			locationMaster.setLocation(listdata.get(0).getLOCATION_NAME());
			locationMaster.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return locationMaster;
	}

	public String updateLocationMaster(String companyName, String location, long id) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update LocationMasterModel set company_name=:companyName,location_name=:location where id=:id");
			q.setParameter("companyName", companyName);
			q.setParameter("location", location);
			q.setParameter("id", id);

			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	// Network Discovery
	// IP SCAN
	public Map<String, String> getGroupMap() {
		Map<String, String> groupMap = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from GroupMasterModel ");

			List<GroupMasterModel> listdata = q.list();

			for (GroupMasterModel group : listdata) {
				groupMap.put("Please Select", "Please Select");
				groupMap.put("All", "All Groups");
				groupMap.put(group.getGROUP_NAME(), group.getGROUP_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return groupMap;
	}

	public Map<String, String> getGroupDeviceDetails() {
		Map<String, String> groupMap = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from GroupMasterModel ");

			List<GroupMasterModel> listdata = q.list();

			for (GroupMasterModel group : listdata) {
				groupMap.put(group.getGROUP_NAME(), group.getGROUP_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return groupMap;
	}

	public String addIpScan(String groupName, String ipScan, String endIpScan) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("groupName:" + groupName);
		System.out.println("ipScan:" + ipScan);

		String result = null;
		try {
			NWIPScanModel lmm = new NWIPScanModel();
			lmm.setGROUP_NAME(groupName);
			lmm.setSTART_IP(ipScan);
			lmm.setEND_IP(endIpScan);
			getSession().save(lmm);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<NWIPScanModel> viewIpScan() {

		Criteria criteria = getSession().createCriteria(NWIPScanModel.class);
		return criteria.list();
	}

	public String deleteIpScan(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete deleteIpScan:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from NWIPScanModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}

	public NwIpScanBean editIpScan(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit Location Master:" + id);
		String result = null;

		NwIpScanBean ipScan = new NwIpScanBean();
		try {
			Query q = getSession().createQuery("from NWIPScanModel where ID=:id");
			q.setParameter("id", id);
			// List<UserMasterModel> listData = q.list();
			List<NWIPScanModel> listdata = q.list();

			ipScan.setGroupName(listdata.get(0).getGROUP_NAME());
			ipScan.setStartIp(listdata.get(0).getSTART_IP());
			ipScan.setEndIp(listdata.get(0).getEND_IP());
			ipScan.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return ipScan;
	}

	public String updateIpScan(String groupName, String ipScan, String endIpScan, long id) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update NWIPScanModel set group_name=:groupName,start_ip=:ipScan, end_ip=:endIpScan where id=:id");
			q.setParameter("groupName", groupName);
			q.setParameter("ipScan", ipScan);
			q.setParameter("endIpScan", endIpScan);
			q.setParameter("id", id);

			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Group Master */
	public String addGroup(String groupName) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("groupName:" + groupName);

		String result = null;
		try {
			GroupMasterModel groupMasterModel = new GroupMasterModel();
			groupMasterModel.setGROUP_NAME(groupName);
			getSession().save(groupMasterModel);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Group Listing */
	public JSONArray groupListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(GroupMasterModel.class);

			List<GroupMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (GroupMasterModel groupmaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(groupmaster.getGROUP_NAME());
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteGroup(\""
						+ groupmaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete Group */

	public String deleteGroup(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Group:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from GroupMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete Group" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Service Provider Master */
	public String addServiceProvider(String serviceProviderName) {
		// TODO Auto-generated method stub
		// System.out.println("Dao called");
		// System.out.println("serviceProviderName:" + serviceProviderName);

		String result = null;
		try {
			ServiceProviderMasterModel serviceProviderMasterModel = new ServiceProviderMasterModel();
			serviceProviderMasterModel.setSERVICE_PROVIDER_NAME(serviceProviderName);
			getSession().save(serviceProviderMasterModel);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Service Provider Listing */
	public JSONArray serviceProviderListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(ServiceProviderMasterModel.class);

			List<ServiceProviderMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (ServiceProviderMasterModel serviceprovidermaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(serviceprovidermaster.getSERVICE_PROVIDER_NAME());
				array.put(
						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteServiceProvider(\""
								+ serviceprovidermaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete Service Provider */

	public String deleteServiceProvider(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Service Provider:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from ServiceProviderMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete Service Provider" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Make Model Master */
	public String addMakeModel(String makeModelName) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			MakeAndModelMasterModel makeAndModelMasterModel = new MakeAndModelMasterModel();
			makeAndModelMasterModel.setMAKE_MODEL_NAME(makeModelName);
			getSession().save(makeAndModelMasterModel);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Make Model Listing */
	public JSONArray makeModelListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(MakeAndModelMasterModel.class);

			List<MakeAndModelMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (MakeAndModelMasterModel makeModelmaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(makeModelmaster.getMAKE_MODEL_NAME());
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteMakeModel(\""
						+ makeModelmaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete Make & Model */

	public String deleteMakeModel(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Make & Model:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from MakeAndModelMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete MakeModel" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Device Type Master */
	public String addDeviceType(String deviceTypeName) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			DeviceTypeMasterModel deviceTypeMasterModel = new DeviceTypeMasterModel();
			deviceTypeMasterModel.setDEVICE_TYPE(deviceTypeName);
			getSession().save(deviceTypeMasterModel);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Device Type Listing */
	public JSONArray deviceTypeListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(DeviceTypeMasterModel.class);

			List<DeviceTypeMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (DeviceTypeMasterModel devicetypemaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(devicetypemaster.getDEVICE_TYPE());
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteDeviceType(\""
						+ devicetypemaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete Device Type */

	public String deleteDeviceType(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Device Type:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from DeviceTypeMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete Device Type" + e);
			result = "fail";
		}

		return result;
	}

	/* Add AD Configuration */

	public String addADConfig(String ldapServerName, String username, String logonDomain, String password,
			long serverPort) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("ldapServerName:" + ldapServerName);
		System.out.println("username:" + username);
		System.out.println("logonDomain:" + logonDomain);
		System.out.println("password:" + password);
		System.out.println("serverPort:" + serverPort);

		String result = null;
		try {
			ADConfigMasterModel adconfig = new ADConfigMasterModel();
			adconfig.setLDAP_SERVER(ldapServerName);
			adconfig.setUSERNAME(username);
			adconfig.setLOGON_DOMAIN(logonDomain);
			adconfig.setPASSWORD(password);
			adconfig.setSERVER_PORT(serverPort);
			getSession().save(adconfig);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add AD Configuration Listing */
	public JSONArray adConfigListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(ADConfigMasterModel.class);

			List<ADConfigMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (ADConfigMasterModel adconfigmaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(adconfigmaster.getLDAP_SERVER());
				array.put(adconfigmaster.getLOGON_DOMAIN());
				array.put(adconfigmaster.getUSERNAME());
				array.put(adconfigmaster.getSERVER_PORT());
				array.put("<a href=\"editADConfig/" + adconfigmaster.getID() + "\"><i class=\"fas fa-edit\"></a>");
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteADConfig(\""
						+ adconfigmaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete AD Configuration */

	public String deleteADConfig(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete AD Config:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from ADConfigMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete AD Config" + e);
			result = "fail";
		}

		return result;
	}

	/* Edit AD Configuration */

	public ADConfigMasterBean editADConfig(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit AD Config:" + id);
		String result = null;

		ADConfigMasterBean adConfig = new ADConfigMasterBean();
		try {
			Query q = getSession().createQuery("from ADConfigMasterModel where ID=:id");
			q.setParameter("id", id);
			List<ADConfigMasterModel> listdata = q.list();
			adConfig.setLdapServerName(listdata.get(0).getLDAP_SERVER());
			adConfig.setLogonDomain(listdata.get(0).getLOGON_DOMAIN());
			adConfig.setUsername(listdata.get(0).getUSERNAME());
			adConfig.setServerPort(listdata.get(0).getSERVER_PORT());
			adConfig.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return adConfig;
	}

	/* Update AD Configuration */

	public String updateADConfig(String ldapServerName, String username, String logonDomain, String password,
			long serverPort, long id) {
		// TODO Auto-generated method stub

		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update ADConfigMasterModel set LDAP_SERVER=:ldapServerName,USERNAME=:username,PASSWORD=:password, LOGON_DOMAIN=:logonDomain, SERVER_PORT=:serverPort where id=:id");
			q.setParameter("ldapServerName", ldapServerName);
			q.setParameter("username", username);
			q.setParameter("password", password);
			q.setParameter("logonDomain", logonDomain);
			q.setParameter("serverPort", serverPort);
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Email Configuration */

	public String addEmailConfig(String emailId, long port, String smtpServer, long timeout, String isSslTls,
			String username, String isSmtpAuth, String password) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");

		try {
			if (username.isEmpty()) {
				username = "NA";
			}
			if (password.isEmpty()) {
				password = "NA";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		String result = null;
		try {
			EmailConfigMasterModel emailconfig = new EmailConfigMasterModel();
			emailconfig.setEMAIL_ID(emailId);
			emailconfig.setPORT(port);
			emailconfig.setSMTP_SERVER(smtpServer);
			emailconfig.setTIMEOUT(timeout);
			emailconfig.setIS_SSL_TLS(isSslTls);
			emailconfig.setUSERNAME(username);
			emailconfig.setSMTP_AUTH(isSmtpAuth);
			emailconfig.setPASSWORD(password);
			getSession().save(emailconfig);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Email Configuration Listing */
	public JSONArray emailConfigListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(EmailConfigMasterModel.class);

			List<EmailConfigMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (EmailConfigMasterModel emailconfigmaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(emailconfigmaster.getEMAIL_ID());
				array.put(emailconfigmaster.getSMTP_SERVER());
				array.put(emailconfigmaster.getPORT());
				array.put(emailconfigmaster.getTIMEOUT());
				array.put(emailconfigmaster.getIS_SSL_TLS());
				array.put(emailconfigmaster.getSMTP_AUTH());
				array.put(emailconfigmaster.getUSERNAME());
				// array.put("<a href=\"editEmailConfig/" + emailconfigmaster.getID() + "\"><i
				// class=\"fas fa-edit\"></a>");
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteEmailConfig(\""
						+ emailconfigmaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete Email Configuration */

	public String deleteEmailConfig(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Email Config:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from EmailConfigMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete Email Config" + e);
			result = "fail";
		}

		return result;
	}

	/* Edit Email Configuration */

	public EmailConfigMasterBean editEmailConfig(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit Email Config:" + id);
		String result = null;

		EmailConfigMasterBean emailConfig = new EmailConfigMasterBean();
		try {
			Query q = getSession().createQuery("from EmailConfigMasterModel where ID=:id");
			q.setParameter("id", id);
			List<EmailConfigMasterModel> listdata = q.list();
			emailConfig.setEmailId(listdata.get(0).getEMAIL_ID());
			emailConfig.setSmtpServer(listdata.get(0).getSMTP_SERVER());
			emailConfig.setPort(listdata.get(0).getPORT());
			emailConfig.setTimeout(listdata.get(0).getTIMEOUT());
			emailConfig.setIsSslTls(String.valueOf(listdata.get(0).getIS_SSL_TLS()));
			emailConfig.setIsSmtpAuth(String.valueOf(listdata.get(0).getSMTP_AUTH()));
			emailConfig.setUsername(listdata.get(0).getUSERNAME());
			emailConfig.setPassword(listdata.get(0).getPASSWORD());
			emailConfig.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return emailConfig;
	}

	/* Add SNMP Configuration */

	public String addSNMPConfig(String name, String description, String version, String community, String username,
			String authProtocol, String context, String authPassword, String encryptProtocol, long port,
			String encryptPassword, long timeout, long retries) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");

		try {
			if (name.isEmpty()) {
				name = "NA";
			}
			if (description.isEmpty()) {
				description = "NA";
			}
			if (version.isEmpty()) {
				version = "NA";
			}
			if (community.isEmpty()) {
				community = "NA";
			}
			if (username.isEmpty()) {
				username = "NA";
			}
			if (authProtocol.isEmpty()) {
				authProtocol = "NA";
			}
			if (context.isEmpty()) {
				context = "NA";
			}
			if (authPassword.isEmpty()) {
				authPassword = "NA";
			}
			if (encryptProtocol.isEmpty()) {
				encryptProtocol = "NA";
			}
			if (port == 0) {
				port = 0;
			}
			if (encryptPassword.isEmpty()) {
				encryptPassword = "NA";
			}
			if (timeout == 0) {
				timeout = 0;
			}
			if (retries == 0) {
				retries = 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		String result = null;
		try {
			SnmpConfigMasterModel snmpconfig = new SnmpConfigMasterModel();
			snmpconfig.setNAME(name);
			snmpconfig.setDESCRIPTION(description);
			snmpconfig.setVERSION(version);
			snmpconfig.setCOMMUNITY(community);
			snmpconfig.setUSERNAME(username);
			snmpconfig.setAUTH_PROTOCOL(authProtocol);
			snmpconfig.setCONTEXT(context);
			snmpconfig.setAUTH_PASS(authPassword);
			snmpconfig.setENCRYPT_PROTOCOL(encryptProtocol);
			snmpconfig.setPORT(port);
			snmpconfig.setENCRYPT_PASS(encryptPassword);
			snmpconfig.setTIMEOUT(timeout);
			snmpconfig.setRETRIES(retries);
			getSession().save(snmpconfig);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add SNMP Configuration Listing */
	public JSONArray snmpConfigListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(SnmpConfigMasterModel.class);

			List<SnmpConfigMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (SnmpConfigMasterModel snmpconfig : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				/*
				 * array.
				 * put("<button class='btn btn-sm btn-warning' id='testing'  name='Doem' onclick='testSNMPConfig(\""
				 * + snmpconfig.getID() + "\" )'>Test <i class='fa fa-check'></i></button>");
				 */
				array.put(snmpconfig.getNAME());
				array.put(snmpconfig.getDESCRIPTION());
				array.put(snmpconfig.getVERSION());
				array.put(snmpconfig.getCOMMUNITY());
				array.put(snmpconfig.getUSERNAME());
				array.put(snmpconfig.getAUTH_PROTOCOL());
				array.put(snmpconfig.getCONTEXT());
				array.put(snmpconfig.getAUTH_PASS());
				array.put(snmpconfig.getENCRYPT_PROTOCOL());
				array.put(snmpconfig.getPORT());
				array.put(snmpconfig.getENCRYPT_PASS());
				array.put(snmpconfig.getTIMEOUT());
				array.put(snmpconfig.getRETRIES());
				// array.put("<a href=\"editSNMPConfig/" + emailconfig.getID() + "\"><i
				// class=\"fas fa-edit\"></a>");
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteSNMPConfig(\""
						+ snmpconfig.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete SNMP Configuration */

	public String deleteSNMPConfig(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete SNMP Config:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from SnmpConfigMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete SNMP Config" + e);
			result = "fail";
		}

		return result;
	}

	public List<Network_Discovery> discoveredNetwork() {
		Criteria criteria = getSession().createCriteria(Network_Discovery.class);
		return criteria.list();
	}

	public String addInterfaceParameter(String id, String monitoring, String bwHistory, String crc, String bwThreshold,
			String mailAlert, String smsAlert, String autoTicketing, String procureBW) {
		String result = "";
		try {
			Query query = getSession().createQuery("update InterfaceMonitoring set MONITORING_PARAM = '" + monitoring
					+ "', BW_HISTORY_PARAM = '" + bwHistory + "', CRC_HISTORY_PARAM = '" + crc + "', BW_THRESHOLD = '"
					+ bwThreshold + "'" + ", MAIL_ALERT = '" + mailAlert + "', SMS_ALERT = '" + smsAlert
					+ "', AUTO_TICKETING = '" + autoTicketing + "'" + ", PROCURED_BANDWIDTH = '" + procureBW
					+ "' where ID in ('" + id + "')");
			query.executeUpdate();
			result = "success";
		} catch (Exception e) {
			result = "fail";
		}
		return result;
	}

	public String getIpAddress() {
		String result = "<option value=''>Please Select</option>";
		try {

			Query query = getSession().createQuery("select DISTINCT DEVICE_IP from AddNodeModel");
			List<String> list = query.list();

			for (String ip : list) {
				result += "<option value='" + ip + "'>" + ip + "</option>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String userManageScopeServiceDao(String username, String groupname, String location, String admin,
			String dashboard, String report, String graph) {
		String result = null;
		int i = 0;
		Criteria criteria = getSession().createCriteria(UserManageScopeModel.class);
		List<UserManageScopeModel> dataList = criteria.list();
		for (UserManageScopeModel configData : dataList) {
			if (configData.getUSERNAME().equals(username)) {
				try {
					Query q = getSession().createQuery(
							"UPDATE UserManageScopeModel SET GROUPNAME=:groupname, LOCATION=:location , ADMIN=:admin ,  DASHBOARD=:dashboard  , REPORT=:report, GRAPH=:graph, USER_SCOPE=:userScope WHERE USERNAME=:username");
					q.setParameter("groupname", groupname);
					q.setParameter("location", location);
					q.setParameter("admin", admin);
					q.setParameter("dashboard", dashboard);
					q.setParameter("report", report);
					q.setParameter("graph", graph);

					String[] groupArray = groupname.split(",");
					String[] locationArray = location.split(",");

					String groupClause = String.join("','", groupArray);
					String locationClause = String.join("','", locationArray);

					String userScopeFilter = String.format("add_node.GROUP_NAME IN ('%s')", groupClause);

					q.setParameter("userScope", userScopeFilter);

					q.setParameter("username", username);
					q.executeUpdate();
					result = "success";
				} catch (Exception e) {
					System.out.println("Exception:" + e);
					result = "fail";
				}
				break;
			} else if (dataList.size() - 1 == i) {
				try {
					UserManageScopeModel userSData = new UserManageScopeModel();
					userSData.setUSERNAME(username);
					userSData.setGROUPNAME(groupname);
					userSData.setLOCATION(location);
					userSData.setADMIN(admin);
					userSData.setDASHBOARD(dashboard);
					userSData.setREPORT(report);
					userSData.setGRAPH(graph);

					String[] groupArray = groupname.split(",");
					String[] locationArray = location.split(",");

					String groupClause = String.join("','", groupArray);
					String locationClause = String.join("','", locationArray);

//					String userScopeFilter = String.format(
//							"add_node.GROUP_NAME IN ('%s') AND add_node.LOCATION IN ('%s')", groupClause,
//							locationClause);

					String userScopeFilter = String.format("add_node.GROUP_NAME IN ('%s')", groupClause);
					userSData.setUSER_SCOPE(userScopeFilter);
					getSession().save(userSData);
					result = "success";
				} catch (Exception e) {
					System.out.println("Exception:" + e);
					result = "fail";
				}
			}
			i++;
		}
		return result;
	}

	public List<UserManageScopeModel> viewUserManageScopeDao() {
		Criteria criteria = getSession().createCriteria(UserManageScopeModel.class);
		return criteria.list();
	}

	public String deleteUserManageScopeDao(long ID) {
		System.out.println("delete User Manage Scope:" + ID);

		String result = null;

		try {
			Query q = getSession().createQuery("Delete from UserManageScopeModel where ID=:id");
			q.setParameter("id", ID);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}

	public String mailTemplateDao(String templateName, String subject, String sendTime, String ipAddressList,
			String functionName, String msgBody, String attachment, String toMail, String periods,
			String attachmentType, String ccMail, String days, String isActive, String phone) {
		String result = null;
		try {
			if (isMailTemplateExists(templateName)) {
				result = "Template Name already exists";
			} else {
				MailTemplate mailData = new MailTemplate();
				mailData.setTEMPLET_NAME(templateName);
				mailData.setSUBJECT(subject);
				mailData.setSEND_TIME(sendTime);
				mailData.setIP_ADDRESS_LIST(ipAddressList);
				mailData.setFUNCTION_NAME(functionName);
				mailData.setMSG_BODY(msgBody);
				mailData.setATTACHEMENT(attachment);
				mailData.setTO_MAIL(toMail);
				mailData.setPERIODS(periods);
				mailData.setATTACHEMENT_TYPE(attachmentType);
				mailData.setCC_MAIL_ID(ccMail);
				mailData.setDAYS(days);
				mailData.setIsActive(isActive);
				mailData.setPHONE(phone);

				getSession().save(mailData);
				result = "success";
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}
		System.out.println("result = " + result);
		return result;
	}

	private boolean isMailTemplateExists(String templateName) {
		Query query = getSession().createQuery("SELECT COUNT(*) FROM MailTemplate WHERE TEMPLET_NAME = :templateName");
		query.setParameter("templateName", templateName);

		Long count = (Long) query.uniqueResult();
		return count > 0;
	}

	public JSONArray viewMailTemplateDao(String userScopeData) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Query query = getSession().createQuery("from MailTemplate");

			List<MailTemplate> dataList = query.list();
			array1 = new JSONArray();
			for (MailTemplate viewTemplate : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(viewTemplate.getATTACHEMENT());
				array.put(viewTemplate.getATTACHEMENT_TYPE());
				array.put(viewTemplate.getCC_MAIL_ID());
				array.put(viewTemplate.getDAYS());
				array.put(viewTemplate.getFUNCTION_NAME());
				array.put(viewTemplate.getIsActive());
				array.put(viewTemplate.getMSG_BODY());
				array.put(viewTemplate.getPERIODS());
				array.put(viewTemplate.getSEND_TIME());
				array.put(viewTemplate.getSUBJECT());
				array.put(viewTemplate.getTEMPLET_NAME());
				array.put(viewTemplate.getTO_MAIL());
				array.put(viewTemplate.getPHONE());
				array.put(viewTemplate.getIP_ADDRESS_LIST());
				array.put("<a href=\"editMailTemplate/" + viewTemplate.getSR_NO() + "\"><i class=\"fas fa-edit\"></a>");
				array.put(
						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteMailTemplate(\""
								+ viewTemplate.getSR_NO() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("array1 = " + array1);
		return array1;
	}

	public String deleteMailTemplateDao(Long SR_NO) {
		String result = null;
		try {
			Query q = getSession().createQuery("DELETE FROM MailTemplate WHERE SR_NO = :SR_NO");
			q.setParameter("SR_NO", SR_NO);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete Mail Template" + e);
			result = "fail";
		}

		return result;
	}

	public MailTemplateBean mailTemplateBeanDao(Long SR_NO) {
		System.out.println("edit user:" + SR_NO);
		String result = null;
		MailTemplateBean newObj = null;

		MailTemplateBean mailTemplateBean = new MailTemplateBean();
		try {
			Query q = getSession().createQuery("from MailTemplate where SR_NO=:SR_NO");
			q.setParameter("SR_NO", SR_NO);
			// List<UserMasterModel> listData = q.list();
			List<MailTemplate> listdata = q.list();
			mailTemplateBean.setTemplateName(listdata.get(0).getTEMPLET_NAME());
			mailTemplateBean.setFunctionName(listdata.get(0).getFUNCTION_NAME());
			mailTemplateBean.setToMail(listdata.get(0).getTO_MAIL());
			mailTemplateBean.setCcMail(listdata.get(0).getCC_MAIL_ID());
			mailTemplateBean.setSubject(listdata.get(0).getSUBJECT());
			mailTemplateBean.setMsgBody(listdata.get(0).getMSG_BODY());
			mailTemplateBean.setPeriods(listdata.get(0).getPERIODS());
			mailTemplateBean.setDays(listdata.get(0).getDAYS());
			mailTemplateBean.setSendTime(listdata.get(0).getSEND_TIME());
			mailTemplateBean.setAttachment(listdata.get(0).getATTACHEMENT());
			mailTemplateBean.setAttachmentType(listdata.get(0).getATTACHEMENT_TYPE());
			mailTemplateBean.setIsActive(listdata.get(0).getIsActive());
			mailTemplateBean.setIpAddressList(listdata.get(0).getIP_ADDRESS_LIST());
			mailTemplateBean.setPhone(listdata.get(0).getPHONE());

			mailTemplateBean.setSR_NO(SR_NO);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}
		return mailTemplateBean;

	}

	public String updateMailTemplateDao(String templateName, String subject, String sendTime, String ipAddressList,
			String functionName, String msgBody, String attachment, String toMail, String periods,
			String attachmentType, String ccMail, String days, String isActive, Long SR_NO, String phone) {

		String result = null;
		try {
			Query q = getSession().createQuery(
					"UPDATE MailTemplate SET TEMPLET_NAME=:templateName, SUBJECT=:subject, SEND_TIME=:sendTime, IP_ADDRESS_LIST=:ipAddressList, FUNCTION_NAME=:functionName, MSG_BODY=:msgBody, ATTACHEMENT=:attachment, TO_MAIL=:toMail, PERIODS=:periods, ATTACHEMENT_TYPE=:attachmentType, CC_MAIL_ID=:ccMail, DAYS=:days, IsActive=:isActive,PHONE=:phone WHERE SR_NO=:SR_NO");
			q.setParameter("templateName", templateName);
			q.setParameter("subject", subject);
			q.setParameter("sendTime", sendTime);
			q.setParameter("ipAddressList", ipAddressList);
			q.setParameter("functionName", functionName);
			q.setParameter("msgBody", msgBody);
			q.setParameter("attachment", attachment);
			q.setParameter("toMail", toMail);
			q.setParameter("periods", periods);
			q.setParameter("attachmentType", attachmentType);
			q.setParameter("ccMail", ccMail);
			q.setParameter("days", days);
			q.setParameter("isActive", isActive);
			q.setParameter("SR_NO", SR_NO);
			q.setParameter("phone", phone);

			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public Map<String, String> getDistinctNodes(String userScopeData) {
		Map<String, String> nodeIP = new HashMap<String, String>();
		try {

			Query query = getSession().createQuery(
					"SELECT DISTINCT add_node.DEVICE_IP FROM AddNodeModel add_node WHERE " + userScopeData);
			List<String> li = query.list();
			for (String node : li) {
				nodeIP.put(node, node);
			}
			// System.out.println("Distinct nodes = "+nodeIP);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeIP;
	}

	public String pushConfig(String ipAddress, String username, String password, String command) {
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

//		try {
//			jsch = new JSch();
//			session = jsch.getSession(username, ipAddress, 22);
//			session.setPassword(password);
//			session.setConfig("StrictHostKeyChecking", "no");
//			session.connect();
//			channel = session.openChannel("shell");
//			// Thread.sleep(10000);
//			OutputStream ops = channel.getOutputStream();
//			PrintStream ps = null;
//			ps = new PrintStream(ops, true);
//			channel.connect();
//			// Thread.sleep(10000);
//			input = channel.getInputStream();
//			// System.out.println("input = "+input.);
//			Thread.sleep(2000);
//			ps.println(command);
//			Thread.sleep(2000);
//			ps.println("exit");
//			ps.close();
//			int SIZE = 1024;
//			byte[] tmp = null;
//			tmp = new byte[SIZE];
//			// System.out.println("tmp = "+tmp.length);
//			while (true) {
//				System.out.println("in while size is = " + input.available());
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
//			builder.append(ee);
//
//		} 

		finally {
//			try {
//				com.jcraft.jsch.ChannelExec channel.disconnect();
//		           
//			} catch (Exception e) {
//				System.out.println("Exception:" + e);
//			}
//			try {
//				 session.disconnect();
//			} catch (Exception e) {
//				System.out.println("Exception:" + e);
//			}
			try {
				input.close();
			} catch (Exception e) {
				// System.out.println("Exception:" + e);
			}

		}

		return builder.toString();

	}

	public String addPushConfigurationDao(String templateName, String command, Timestamp datepicker, String status,
			String ip_address) {
		String result = "";
		String username = "";
		String password = "";
		String output = "";
		StringTokenizer ip = new StringTokenizer(ip_address, ",");

		try {
			Session session = getSession();

			while (ip.hasMoreTokens()) {
				String ipAddress = ip.nextToken();
				System.out.println("IP Address = " + ipAddress);
				try {
					List<Object[]> q = getSession().createSQLQuery(
							"SELECT USERNAME, PASSWORD FROM node_parameter" + " WHERE DEVICE_IP = '" + ipAddress + "'")
							.list();
					for (Object[] node : q) {
						username = node[0].toString();
						password = node[1].toString();
					}

					// Push configuration into Devices
					System.out.println("Username = " + username + "\nPassword = " + password);
					output = pushConfig(ipAddress, username, password, command);
					PushConfigurationModel pc = new PushConfigurationModel();
					pc.setTEMPLATE(templateName);
					pc.setCOMMAND(command);
					pc.setDATETIME(datepicker);
					pc.setSTATUS(status);
					pc.setIP_ADDRESS(ipAddress);
					pc.setOP(output);
					session.save(pc);

				} catch (Exception e) {
					System.out.println("Exception occured = " + e);
					// result = "fail";
				}
			}

		} catch (HibernateException e) {
			System.out.println("Hibernate Exception:" + e);
			// result = "fail";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			// result = "fail";
		} finally {
// Close the session if it's not managed elsewhere
// session.close();
		}
		result = "success";
		return result;
	}

	public String getTemplateName() {
		System.out.println("in template dao impl");
		String result = "<option value=''>Please Select</option>";
		try {

			Query query = getSession().createQuery("select TEMPLATE_NAME from CommandMaster");
			List<String> list = query.list();

			for (String templates : list) {
				result += "<option value='" + templates + "'>" + templates + "</option>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("template = " + result);
		return result;
	}

	public String fetchCommandDao(String option) {
		String configData = null;
		try {
			Query q = getSession().createQuery("select COMMAND from CommandMaster where TEMPLATE_NAME = :templateName");
			q.setParameter("templateName", option);

//		        configData = q.getSingleResult().toString();
			return (String) q.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
//		        System.out.println("Exception: " + e);
			return null;
		}
//		    return configData;
	}

	public List<PushConfigurationBean> pushConfiReport(String from_date, String to_date, List<String> ip_list) {
		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "SELECT pc.ID, pc.COMMAND, pc.DATETIME, pc.IP_ADDRESS,pc.STATUS,pc.TEMPLATE, pc.OP FROM push_configuration pc WHERE"
				+ " pc.IP_ADDRESS IN ('" + ip_data + "') AND pc.DATETIME BETWEEN '" + from_date + "' AND '" + to_date
				+ "'";
		Query q = getSession().createSQLQuery(query);
		List<PushConfigurationBean> dataList = new ArrayList<PushConfigurationBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				PushConfigurationBean bean = new PushConfigurationBean();
				bean.setId(id);
				bean.setTemplateName(a[5].toString());
				bean.setCommand(a[1].toString());
				bean.setIp_address(a[3].toString());
				bean.setStatus(a[4].toString());
				bean.setDatetime(a[2].toString());
				bean.setOp(a[6].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public String addSubnetScan(String groupName, String subnetScan, String endsubnetScan) {
		System.out.println("Dao called addSubnetScan");
		System.out.println("groupName:" + groupName);
		System.out.println("ipScan:" + subnetScan);

		String result = null;
		try {
			SubnetScanModel lmm = new SubnetScanModel();
			lmm.setGROUP_NAME(groupName);
			lmm.setSTART_IP(subnetScan);
			lmm.setSUBNET_MASK(endsubnetScan);
			getSession().save(lmm);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<SubnetScanModel> viewSubnetScan() {
		Criteria criteria = getSession().createCriteria(SubnetScanModel.class);
		return criteria.list();
	}

	public String deleteSubnetScan(long id) {
		System.out.println("delete deleteSubnetScan:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from SubnetScanModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}

	public SubnetScanBean editSubnetScan(long id) {
		System.out.println("edit editSubnetScan Master:" + id);
		String result = null;

		SubnetScanBean subnetScan = new SubnetScanBean();
		try {
			Query q = getSession().createQuery("from SubnetScanModel where ID=:id");
			q.setParameter("id", id);
			List<SubnetScanModel> listdata = q.list();

			subnetScan.setGroupName(listdata.get(0).getGROUP_NAME());
			subnetScan.setStartIp(listdata.get(0).getSTART_IP());
			subnetScan.setSubnetMask(listdata.get(0).getSUBNET_MASK());
			subnetScan.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return subnetScan;
	}

	public String updateSubnetScan(String groupName, String subnetScan, String endsubnetScan, long id) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update SubnetScanModel set group_name=:groupName,start_ip=:subnetScan, SUBNET_MASK=:endsubnetScan where id=:id");
			q.setParameter("groupName", groupName);
			q.setParameter("subnetScan", subnetScan);
			q.setParameter("endsubnetScan", endsubnetScan);
			q.setParameter("id", id);

			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<UserScopeMaster> getUserScopeMaster() {
		Criteria criteria = getSession().createCriteria(UserScopeMaster.class);
		return criteria.list();
	}

	public String addConfigurationBackup(String deviceIp, String enablePass, String deviceMode, String confCommand,
			String username, String foCommand, String password) {
		String result = "";
		Criteria criteria = getSession().createCriteria(NodeParameterModel.class);
		List<NodeParameterModel> dataList = criteria.list();
		List<String> ipList = Arrays.asList(deviceIp.split(","));

		for (String ipAddress : ipList) {
			if (!dataList.isEmpty()) {
				int dataconparision = 0;
				for (NodeParameterModel configData : dataList) {
					if (configData.getDEVICE_IP().equals(ipAddress)) {
						try {
							Query q = getSession().createQuery(
									"UPDATE NodeParameterModel SET DEVICE_MODE=:deviceMode, CURRENT_CONFIG=:confCommand , FULL_OUTPUT=:foCommand ,  USERNAME=:username  , PASSWORD=:password , ENABLE_PWD=:enablePass WHERE DEVICE_IP=:ipAddress");
							q.setParameter("ipAddress", ipAddress);
							q.setParameter("deviceMode", deviceMode);
							q.setParameter("confCommand", confCommand);
							q.setParameter("username", username);
							q.setParameter("foCommand", foCommand);
							q.setParameter("password", password);
							q.setParameter("enablePass", enablePass);
							int i = q.executeUpdate();
						} catch (Exception e) {
							System.out.println("Exception:" + e);
						}

						result = "updated";
						break;
					} else if (dataList.size() - 1 == dataconparision) {
						try {
							NodeParameterModel confData = new NodeParameterModel();
							confData.setDEVICE_IP(ipAddress);
							confData.setDEVICE_MODE(deviceMode);
							confData.setENABLE_PWD(enablePass);
							confData.setUSERNAME(username);
							confData.setPASSWORD(password);
							confData.setFULL_OUTPUT(foCommand);
							confData.setCURRENT_CONFIG(confCommand);
							getSession().saveOrUpdate(confData);
						} catch (Exception e) {
							System.out.println("Exception:" + e);

						}
						result = "added";
					}
					dataconparision++;
				}
			} else {
				try {
					NodeParameterModel confData = new NodeParameterModel();
					confData.setDEVICE_IP(ipAddress);
					confData.setDEVICE_MODE(deviceMode);
					confData.setENABLE_PWD(enablePass);
					confData.setUSERNAME(username);
					confData.setPASSWORD(password);
					confData.setFULL_OUTPUT(foCommand);
					confData.setCURRENT_CONFIG(confCommand);
					getSession().saveOrUpdate(confData);
				} catch (Exception e) {
					System.out.println("Exception:" + e);

				}
				result = "added";
			}
		}

		return result;
	}

	public JSONArray viewConfigurationBackup(String userScopeData) {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Query q = getSession().createQuery(
					"SELECT npm FROM NodeParameterModel npm, AddNodeModel add_node WHERE npm.DEVICE_IP=add_node.DEVICE_IP AND "
							+ userScopeData);
			List<NodeParameterModel> dataList = q.list();
			array1 = new JSONArray();
			for (NodeParameterModel configData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(configData.getDEVICE_IP());
				array.put(configData.getDEVICE_MODE());
				array.put(configData.getUSERNAME());
				array.put(configData.getFULL_OUTPUT());
				array.put(configData.getCURRENT_CONFIG());
				array.put(
						"<a href=\"editConfigurationBackup/" + configData.getID() + "\"><i class=\"fas fa-edit\"></a>");
				array.put(
						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteConfigurationBackup(\""
								+ configData.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public String deleteConfigurationBackup(long ID) {
		String result = null;
		try {
			Query q = getSession().createQuery("Delete from NodeParameterModel where ID=:ID");
			q.setParameter("ID", ID);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ConfigBackupBean ConfigBackupBeanDao(long ID) {
		System.out.println("edit user:" + ID);
		String result = null;
		ConfigBackupBean newObj = null;
		ConfigBackupBean configData = new ConfigBackupBean();
		try {
			Query q = getSession().createQuery("from NodeParameterModel where ID=:ID");
			q.setParameter("ID", ID);
			// List<NodeParameterModel> listData = q.list();
			List<NodeParameterModel> listdata = q.list();
			configData.setID(listdata.get(0).getID());
			/* configData.setUsername(listdata.get(0).getUSERNAME()); */
			configData.setDeviceIp(listdata.get(0).getDEVICE_IP());
			configData.setDeviceMode(listdata.get(0).getDEVICE_MODE());
			configData.setConfCommand(listdata.get(0).getCURRENT_CONFIG());
			configData.setFoCommand(listdata.get(0).getFULL_OUTPUT());
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}
		return configData;
	}

	public String updateConfigurationBackupDao(String deviceIp, String deviceMode, String confCommand, String foCommand,
			long ID) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"UPDATE NodeParameterModel SET DEVICE_IP=:deviceIp, DEVICE_MODE=:deviceMode, CURRENT_CONFIG=:confCommand , FULL_OUTPUT=:foCommand WHERE ID=:ID");
			q.setParameter("deviceIp", deviceIp);
			q.setParameter("deviceMode", deviceMode);
			q.setParameter("confCommand", confCommand);
			q.setParameter("foCommand", foCommand);
			q.setParameter("ID", ID);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}
		return result;
	}

	public String addScheduleScanDao(String scheduleType, String startIp, String subnetIp, String once_time) {
		String result = "added";
		Criteria criteria = getSession().createCriteria(ScheduleScan.class);
		List<ScheduleScan> dataList = criteria.list();
		if (!dataList.isEmpty()) {
			int dataconparision = 0;
			for (ScheduleScan confTempData : dataList) {
				if (dataList.size() - 1 == dataconparision) {
					try {
						ScheduleScan confTempDataUpdate = new ScheduleScan();
						confTempDataUpdate.setSCHEDULE_TYPE(scheduleType);
						confTempDataUpdate.setSTART_IP(startIp);
						confTempDataUpdate.setSUBNET_MASK(subnetIp);
						confTempDataUpdate.setONCE_TIME(once_time);
						getSession().save(confTempDataUpdate);
					} catch (Exception e) {
						System.out.println("Exception:" + e);

					}
					result = "added";
				}
				dataconparision++;
			}
		} else {
			ScheduleScan confTempDataUpdate = new ScheduleScan();
			confTempDataUpdate.setSCHEDULE_TYPE(scheduleType);
			confTempDataUpdate.setSTART_IP(startIp);
			confTempDataUpdate.setSUBNET_MASK(subnetIp);
			confTempDataUpdate.setONCE_TIME(once_time);
			getSession().save(confTempDataUpdate);
			result = "added";
		}

		return result;
	}

	public JSONArray viewScheduleScanDao() {
		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Criteria criteria = getSession().createCriteria(ScheduleScan.class);
			List<ScheduleScan> dataList = criteria.list();
			array1 = new JSONArray();
			for (ScheduleScan confTempData : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put(srno);
				array.put(confTempData.getSCHEDULE_TYPE());
				array.put(confTempData.getSTART_IP());
				array.put(confTempData.getSUBNET_MASK());
				array.put(confTempData.getONCE_TIME());
				array.put(
						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteScheduleScan(\""
								+ confTempData.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

	public String deleteScheduleScanDao(long ID) {
		String result = null;
		try {
			Query q = getSession().createQuery("Delete from ScheduleScan where ID=:ID");
			q.setParameter("ID", ID);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public SNMPConfigMasterBean fetchSMNPConfigDao(String deviceIp, String snmp) {

		System.out.println("edit user:" + snmp);
		String result = null;
		SNMPConfigMasterBean configData = new SNMPConfigMasterBean();
		try {
			Query q = getSession().createQuery("from SnmpConfigMasterModel where NAME=:snmp");
			q.setParameter("snmp", snmp);
			List<SnmpConfigMasterModel> listdata = q.list();
			configData.setId(listdata.get(0).getID());
			configData.setName(listdata.get(0).getNAME());
			configData.setDescription(listdata.get(0).getDESCRIPTION());
			configData.setVersion(listdata.get(0).getVERSION());
			configData.setCommunity(listdata.get(0).getCOMMUNITY());
			configData.setUsername(listdata.get(0).getUSERNAME());
			configData.setContext(listdata.get(0).getCONTEXT());
			configData.setAuthProtocol(listdata.get(0).getAUTH_PROTOCOL());
			configData.setAuthPassword(listdata.get(0).getAUTH_PASS());
			configData.setEncryptProtocol(listdata.get(0).getENCRYPT_PROTOCOL());
			configData.setEncryptPassword(listdata.get(0).getENCRYPT_PASS());
			configData.setPort(listdata.get(0).getPORT());
			configData.setTimeout(listdata.get(0).getTIMEOUT());
			configData.setRetries(listdata.get(0).getRETRIES());
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}
		return configData;

	}

	public String manualTopology(String sourceDeviceIp, String source_interface_name, String destinationDeviceIp,
			String destination_interface_name) {
		String result = null;
		String source_device_name_data = null;
		String source_device_mac_data = null;
		String destination_device_name_data = null;
		String destination_device_mac_data = null;
		try {
			List<Object[]> q = getSession()
					.createSQLQuery(
							"select DEVICE_NAME,DEVICE_MAC from add_node where DEVICE_IP='" + sourceDeviceIp + "'")
					.list();
			for (Object[] dataList : q) {
				source_device_name_data = dataList[0].toString();
				source_device_mac_data = ((dataList[1] == null) ? "NA"
						: dataList[1].equals("") ? "NA" : dataList[1].toString());
			}
			System.out.println("source_device_name_data=" + source_device_name_data + "=" + source_device_mac_data);
			List<Object[]> query = getSession()
					.createSQLQuery(
							"select DEVICE_NAME,DEVICE_MAC from add_node where DEVICE_IP='" + destinationDeviceIp + "'")
					.list();
			for (Object[] dataList : query) {
				destination_device_name_data = dataList[0].toString();
				destination_device_mac_data = ((dataList[1] == null) ? "NA"
						: dataList[1].equals("") ? "NA" : dataList[1].toString());
			}
			System.out.println(
					"destination_device_name_data=" + destination_device_name_data + "=" + destination_device_mac_data);
			ManualTopology manualTopology = new ManualTopology();
			manualTopology.setSource_device_ip(sourceDeviceIp);
			manualTopology.setSource_interface_name(source_interface_name);
			manualTopology.setDestination_device_ip(destinationDeviceIp);
			manualTopology.setDestination_interface_name(destination_interface_name);
			manualTopology.setDestination_device_name(destination_device_name_data);
			manualTopology.setSource_device_mac(source_device_mac_data);
			manualTopology.setDestination_device_mac(destination_device_mac_data);
			manualTopology.setSource_device_name(source_device_name_data);

			getSession().save(manualTopology);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public JSONArray getManualTopology(String string, String contextPath) {
		JSONArray finalJSONArray = null;
		JSONObject finalJSONObject = null;
//		JSONObject edgesJSON = null;

		JSONArray edgesArray = null;
		JSONArray nodesArray = null;

		// For Node Down List
		HashMap<String, String> NodeDownMap = new HashMap<String, String>();
		// Query NodeDownQuery = getSession().createQuery("select distinct
		// IP_ADDRESS,ROUTER_STATUS from Add_Interface");
		Query NodeDownQuery = getSession().createQuery(
				"select a.DEVICE_IP,n.NODE_STATUS  from AddNodeModel a JOIN NodeMonitoringModel n  on a.DEVICE_IP=n.NODE_IP");

		List<Object[]> NodeDownList = NodeDownQuery.list();
		for (Object[] obj : NodeDownList) {
			NodeDownMap.put(obj[0].toString(), obj[1].toString());
		}

		try {
			int i = 0;
			Set<String> set = new HashSet<String>();
			List<Object[]> objList = new ArrayList<Object[]>();
			HashMap<String, String> hashMap = new HashMap<String, String>();

			// For Device IP List
//			Query deviceQuery = getSession().createQuery("select distinct local_device_mac from auto_topology");
//			List<String> deviceIPList = deviceQuery.list();
//			set.addAll(deviceIPList);
			Query deviceQuery = getSession().createQuery("select distinct source_device_ip from ManualTopology");
			List<String> deviceIPList = deviceQuery.list();
			set.addAll(deviceIPList);

			// For Remote Device IP List
//			Query localdeviceQuery = getSession().createQuery("select distinct remote_device_mac from auto_topology");
//			List<String> localDeviceIPList = localdeviceQuery.list();
//			set.addAll(localdeviceQuery.list());
			Query localdeviceQuery = getSession()
					.createQuery("select distinct destination_device_ip from ManualTopology");
			List<String> localDeviceIPList = localdeviceQuery.list();
			set.addAll(localdeviceQuery.list());

			nodesArray = new JSONArray();

			nodesArray = new JSONArray();
			Iterator<String> setIterator = set.iterator();
			while (setIterator.hasNext()) {
				String mac = setIterator.next();
				Query Query = getSession().createQuery(
//						"select a.IP_ADDRESS,a.DEVICE_NAME,a.DEVICE_MAC,a.ROUTER_STATUS ,a.DEVICE_NAME from add_interface a where a.DEVICE_MAC ='"
//								+ mac + "'");
//						"select a.IP_ADDRESS,a.DEVICE_NAME,a.DEVICE_MAC,a.ROUTER_STATUS ,a.DEVICE_NAME from Add_Interface a where a.IP_ADDRESS ='"
//								+ mac + "'");

						"select a.DEVICE_IP,a.DEVICE_NAME,a.DEVICE_MAC,n.NODE_STATUS ,a.DEVICE_TYPE from AddNodeModel a  JOIN NodeMonitoringModel n  on a.DEVICE_IP=n.NODE_IP where a.DEVICE_IP ='"
								+ mac + "'");
				List<Object[]> objList1 = Query.list();
				if (objList1.isEmpty()) {
					JSONObject nodesJSON1 = null;
					nodesJSON1 = new JSONObject();
					nodesJSON1.put("id", mac);
					nodesJSON1.put("label", mac);
					nodesJSON1.put("shape", "image");
					nodesJSON1.put("title", mac);
//					nodesJSON1.put("title", obj[2].toString() + "(" + obj[1].toString() + ")");
					nodesJSON1.put("font", "12px arial white");
					nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/wifi-router-down.png");
					nodesArray.put(nodesJSON1);

				} else {
					for (Object[] obj : objList1) {

						JSONObject nodesJSON1 = null;
						nodesJSON1 = new JSONObject();
						nodesJSON1.put("id", mac);
						nodesJSON1.put("label", obj[0].toString() + '\n' + "(" + obj[4].toString() + ")");
						nodesJSON1.put("title", obj[2].toString() + "(" + obj[1].toString() + ")");
						nodesJSON1.put("shape", "image");
						nodesJSON1.put("font", "12px arial white");
						if (obj[4].toString().equals("Router")) {
							if (obj[3].toString().endsWith("Up")) {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/wifi-router-up.png");
							} else {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/wifi-router-down.png");
							}
						} else if (obj[4].toString().equals("Switch")) {
							if (obj[3].toString().endsWith("Up")) {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/network-switch-up.png");
							} else {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/network-switch-down.png");
							}
						} else if (obj[4].toString().equals("Firewall")) {
							if (obj[3].toString().endsWith("Up")) {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/firewall-up.png");
							} else {
								nodesJSON1.put("image", contextPath + "/webtemplate/visjs/img/firewall-down.png");
							}
						}
						nodesArray.put(nodesJSON1);
					}
				}

			}

			// end node json

			// interface json start

			// interface status map
			HashMap<String, String> foMap = new HashMap<String, String>();
			try {

				Query foQuery = getSession().createSQLQuery("select NODE_IP,INTERFACE_NAME from interface_monitoring");
				List<Object[]> objList2 = foQuery.list();
				for (Object[] obj : objList2) {
					foMap.put(obj[0].toString() + "~" + obj[1].toString(), "0");
				}
			} catch (Exception e) {

			}
			HashMap<String, String> LinkDownMap = new HashMap<String, String>();
			try {

				Query LinkDownQuery = getSession()
						.createSQLQuery("select distinct NODE_IP,INTERFACE_NAME,OPER_STATUS from interface_monitoring");
				List<Object[]> objList2 = LinkDownQuery.list();
				for (Object[] obj : objList2) {
					LinkDownMap.put(obj[0].toString() + "~" + obj[1].toString(), obj[2].toString());
				}
			} catch (Exception e) {

			}

			// For Remote DeviceIP List
			Query remoteDeviceIPNameQuery = getSession()
					.createQuery("select distinct destination_device_ip,destination_device_name from ManualTopology");
			List<Object[]> objList1 = remoteDeviceIPNameQuery.list();
			for (Object[] obj : objList1) {
				hashMap.put(obj[0].toString(), obj[1].toString());
			}

			// For Edges JSON
			edgesArray = new JSONArray();
			HashMap<String, Double> CurveSet = new HashMap<String, Double>();
			int k = 0;
			Query autotopologyQuery = getSession().createQuery("from ManualTopology");
			List<ManualTopology> autoTopologyList = autotopologyQuery.list();
			for (ManualTopology data : autoTopologyList) {
				k++;
				String local_mac = data.getSource_device_mac().toString();
				String remote_mac = data.getDestination_device_mac().toString();
				String local_port = data.getSource_interface_name().toString();
				String remote_port = data.getDestination_interface_name().toString();
				String local_ip = data.getSource_device_ip().toString();
				String remote_ip = data.getDestination_device_ip().toString();
				if (local_ip == null || local_ip.equals("-")) {
					local_ip = data.getSource_device_mac().toString();
				}
				if (remote_ip == null || remote_ip.equals("-")) {
					remote_ip = data.getDestination_device_mac().toString();
				}

				// for local interface status
				String local_interface_status = null;
				if (LinkDownMap.containsKey(local_ip + "~" + local_port)) {
					local_interface_status = LinkDownMap.get(local_ip + "~" + local_port);
				} else {
					local_interface_status = "down";
				}

				// for remote inbterface status
				String remote_interface_status = null;
				if (LinkDownMap.containsKey(remote_ip + "~" + remote_port)) {
					remote_interface_status = LinkDownMap.get(remote_ip + "~" + remote_port);
				} else {
					remote_interface_status = "down";
				}

				// for fo_value
				String remote_fo_value = null;
				if (foMap.containsKey(remote_ip + "~" + remote_port)) {
					// remote_fo_value = foMap.get(remote_ip + "~" + remote_port);
					remote_fo_value = "-";
				} else {

				}
				String fo_value = null;
				if (foMap.containsKey(local_ip + "~" + local_port)) {
					fo_value = foMap.get(local_ip + "~" + local_port);

					fo_value = "-";
//						fo_value = fo_value.substring(0, fo_value.indexOf(" "));

				} else {
					fo_value = "-";
				}

				JSONObject edgesJSON1 = new JSONObject();
//				edgesJSON1.put("from", local_mac);
//				edgesJSON1.put("to", remote_mac);
				edgesJSON1.put("from", local_ip);
				edgesJSON1.put("to", remote_ip);
//				edgesJSON1.put("arrows", "to");
				edgesJSON1.put("id", String.valueOf(k));
				edgesJSON1.put("title", local_ip + "(" + local_port + ")(" + fo_value + ") >>" + remote_ip + "("
						+ remote_port + ")" + "(" + remote_fo_value + ")");
				if (remote_interface_status.equalsIgnoreCase("down") || local_interface_status.equalsIgnoreCase("down"))
					edgesJSON1.put("color", "Red");
				else
					edgesJSON1.put("color", "Green");

				double curveValue = 0.0;

				try {

					if (CurveSet.containsKey(local_ip + "~" + remote_ip)) {
						double old_val = CurveSet.get(local_ip + "~" + remote_ip);
						curveValue = old_val + 0.2;
					} else if (CurveSet.containsKey(remote_ip + "~" + local_ip)) {
						double old_val = CurveSet.get(local_ip + "~" + remote_ip);
						curveValue = old_val + 0.2;
					} else {
						curveValue = 0.2;
					}

					CurveSet.put(local_ip + "~" + remote_ip, curveValue);
					CurveSet.put(remote_ip + "~" + local_ip, curveValue);

				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}

				JSONObject obj11 = new JSONObject();
				obj11.put("type", "curvedCCW");
				obj11.put("roundness", curveValue);

				edgesJSON1.put("smooth", obj11);

				edgesArray.put(edgesJSON1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(nodesArray);
		System.out.println(edgesArray);
		finalJSONArray = new JSONArray();

		finalJSONArray.put(nodesArray);
		finalJSONArray.put(edgesArray);
		return finalJSONArray;

	}

	public String GetSpeedTestDetails(String srcIP, String intname) {
		StringBuilder strb = new StringBuilder();
		StringBuilder strOtherB = new StringBuilder();
		boolean isSSHEnable = true;

		// TODO Auto-generated method stub
		String responce_data = "fail";
		String result = "";
		String username = "";
		String password = "";
		String output = "";
		String ipAddress = srcIP;

		System.out.println("IP Address = " + ipAddress);
		try {
			List<Object[]> q = getSession()
					.createSQLQuery(
							"SELECT USERNAME, PASSWORD FROM node_parameter" + " WHERE DEVICE_IP = '" + ipAddress + "'")
					.list();
			for (Object[] node : q) {
				username = node[0].toString();
				password = node[1].toString();
			}

			// Push configuration into Devices
			System.out.println("Username = " + username + "\nPassword = " + password);

		} catch (Exception e) {
			System.out.println("Exception occured = " + e);
			// result = "fail";
		}

		System.out.println("inside Read SSH");
		String line2 = "";
		String command = "";
//	 String command = "show system snmp sysinfo";
		if (intname.trim().equalsIgnoreCase("wan")) {
			command = "iperf3 -c 172.31.31.31 -p 5000";
		} else {
			command = "iperf3 -c 172.31.0.1 -p 5000";
		}
		Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
		String datetime = String.valueOf(timestampNow.getTime());
		System.out.println("Data:" + ipAddress + ":" + username + ":" + password + ":" + command + ":" + datetime);

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

			String line;
			// Set up BufferedWriter to write output to a file
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			// BufferedWriter writer = new BufferedWriter(new FileWriter(log9));
			boolean isLinePresent = false;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				strb.append(line);
				strb.append("\n");

				if (line.contains("- - - - - -")) {
					isLinePresent = true;
				}
				if (isLinePresent) {

					// strOtherB.append(line);
					if (line.contains("iperf")) {

					} else {
						strOtherB.append(line.replace(" ", "&nbsp;") + "<br>");
					}
					// strOtherB.append("\n");
				}

				// iperf Done.

				// writer.write(line);
				// if (!line.equalsIgnoreCase("iperf done.")) {
				// builder.append(line.replace(" ", "&nbsp;") + "<br>");
				// }
				// writer.newLine(); // Write the output to the file

			}

			// Close the channel and session
			// writer.close();
			reader.close();
			channel.disconnect();
			session.disconnect();

		} catch (Exception e) {
			System.out.println("Exception SSH:" + e);
			isSSHEnable = false;
			strb.append("Error:" + e.toString());
		}

		finally {
			try {
				input.close();
			} catch (Exception e) {
				// System.out.println("Exception:" + e);
			}

		}

		StringBuilder speedExactOP = new StringBuilder();

		if (isSSHEnable) {
			String speedoutput = strb.toString();
			// Regular expressions to find sender and receiver bitrate
			try {

				String senderRegex = "\\[\\s*\\d+\\]   0\\.00-10\\.00\\s+sec\\s+(\\d+\\.?\\d*\\s+[KMGT]Bytes)\\s+(\\d+\\.?\\d*\\s+[KMG]bits/sec)\\s+\\d+\\s+sender";
				// String receiverRegex = "\\[\\s*\\d+\\]
				// 0\\.00-10\\.\\d+\\s+sec\\s+(\\d+\\.?\\d*\\s+[KMGT]Bytes)\\s+(\\d+\\.?\\d*\\s+[KMG]bits/sec)\\s+receiver";
				String receiverRegex = "\\[\\s*\\d+\\]   \\d+\\.\\d+-\\d+\\.\\d+\\s+sec\\s+(\\d+\\.?\\d*\\s+[KMGT]Bytes)\\s+(\\d+\\.?\\d*\\s+[KMG]bits/sec)\\s+receiver";

				// Extract sender data
				Matcher senderMatcher = Pattern.compile(senderRegex).matcher(speedoutput);

				Matcher receiverMatcher = Pattern.compile(receiverRegex).matcher(speedoutput);

				// Extract receiver data
//		          Matcher receiverMatcher = Pattern.compile(receiverRegex).matcher(speedoutput);
//		          if (receiverMatcher.find()) {
//		        	  System.out.println("updated Download: " + receiverMatcher.group(2));
//		             // System.out.println("Receiver Transfer: " + receiverMatcher.group(1));
//		        	//  speedExactOP.append("\n");
//		        	  speedExactOP.append("Download : " + receiverMatcher.group(2));
//		              
//		          }

				if (senderMatcher.find()) {
					// System.out.println("Sender Transfer: " + senderMatcher.group(1));

					speedExactOP.append("Upload : " + senderMatcher.group(2));
					speedExactOP.append("<br>");

					System.out.println("updated Uploadd: " + senderMatcher.group(2));
				}

				if (receiverMatcher.find()) {
					// System.out.println("Receiver Transfer: " + receiverMatcher.group(1));
					// System.out.println("Receiver Bitrate: " + receiverMatcher.group(2));
					speedExactOP.append("Download : " + receiverMatcher.group(2));
				}
				speedExactOP.append("<br>");
				speedExactOP.append(strOtherB);

			} catch (Exception e) {
				System.out.println("Exceptionnn: " + e);
			}
			// return speedExactOP.toString();
		} else {
			speedExactOP = strb;

		}
		return speedExactOP.toString();

	}

	private static double extractSpeed(String output, String pattern) {
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(output);
		if (matcher.find()) {
			return Double.parseDouble(matcher.group(1));
		}
		return 0.0; // Return 0.0 if no match is found
	}

	public String saveSLADataMaster(String from_date, String to_date, List<String> dayslist) {

		String days_data = dayslist.toString().replace("[", "").replace("]", "").replace(" ", "");
		String result = "fail";

		String[] daysArrayw = days_data.split(",");
		StringBuilder numericDays = new StringBuilder("IN (");

		// Loop through each day and convert it to the corresponding number
		for (String day : daysArrayw) {
			day = day.trim(); // Remove any extra whitespace

			if (day.equalsIgnoreCase("Monday")) {
				numericDays.append("0");
			} else if (day.equalsIgnoreCase("Tuesday")) {
				numericDays.append("1");
			} else if (day.equalsIgnoreCase("Wednesday")) {
				numericDays.append("2");
			} else if (day.equalsIgnoreCase("Thursday")) {
				numericDays.append("3");
			} else if (day.equalsIgnoreCase("Friday")) {
				numericDays.append("4");
			} else if (day.equalsIgnoreCase("Saturday")) {
				numericDays.append("5");
			} else if (day.equalsIgnoreCase("Sunday")) {
				numericDays.append("6");
			}

			// Add a comma between numbers (but not after the last one)
			numericDays.append(",");
		}
		numericDays.setLength(numericDays.length() - 1);
		numericDays.append(")");

		try {
			String hql = "DELETE FROM SLAMasterModel"; // HQL to delete all records
			Query query = getSession().createQuery(hql);
			query.executeUpdate(); // Execute the deletion

			SLAMasterModel slainsert = new SLAMasterModel();
			slainsert.setDays_data(days_data);
			slainsert.setNumeric_days_data(numericDays.toString());
			slainsert.setFrom_time(from_date + ":00");
			slainsert.setTo_time(to_date + ":00");
			slainsert.setBetween_data(" BETWEEN '" + from_date + ":00" + "' AND '" + to_date + ":00" + "'");

			getSession().save(slainsert);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}
		return result;
	}

	public JSONArray getslamasterdata() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {
			Query q = getSession().createQuery("SELECT npm FROM SLAMasterModel npm");
			List<SLAMasterModel> dataList = q.list();
			array1 = new JSONArray();
			for (SLAMasterModel configData : dataList) {
//				srno = srno + 1;
				array = new JSONArray();
//				array.put(srno);
				array.put(configData.getDays_data());
				array.put(configData.getFrom_time());
				array.put(configData.getTo_time());

//				array.put(
//						"<a href=\"editConfigurationBackup/" + configData.getID() + "\"><i class=\"fas fa-edit\"></a>");
//				array.put(
//						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteConfigurationBackup(\""
//								+ configData.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return array1;
	}

}
