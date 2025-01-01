package npm.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import npm.admin.beans.ADConfigMasterBean;
import npm.admin.beans.AddNodeBean;
import npm.admin.beans.CompanyMasterBean;
import npm.admin.beans.ConfigBackupBean;
import npm.admin.beans.DeviceTypeMasterBean;
import npm.admin.beans.EmailConfigMasterBean;
import npm.admin.beans.GroupMasterBean;
import npm.admin.beans.IcmpMasterBean;
import npm.admin.beans.LocationMasterBean;
import npm.admin.beans.MailTemplateBean;
import npm.admin.beans.MakeModelMasterBean;
import npm.admin.beans.NwIpScanBean;
import npm.admin.beans.PushConfigurationBean;
import npm.admin.beans.SLAMasterBean;
import npm.admin.beans.SNMPConfigMasterBean;
import npm.admin.beans.ServiceProviderMasterBean;
import npm.admin.beans.SubnetScanBean;
import npm.admin.beans.UserManageScopeBean;
import npm.admin.beans.UserMasterBean;
import npm.admin.dao.MasterDao;
import npm.admin.service.MasterService;
import npm.admin.service.NodeService;
import npm.model.AddNodeModel;
import npm.model.LocationMasterModel;
import npm.model.UserMasterModel;
import npm.model.UserScopeMaster;

@Controller
@RequestMapping("/master")
public class MasterController {

	@Autowired
	MasterService service;

	@Autowired
	MasterDao dao;

	@Autowired
	NodeService nodeService;

	@RequestMapping("/addUserForm")
	public String addUserForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addUserForm");

		m.addAttribute("fetching", new UserMasterBean());

		return "addUser";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    public String submitForm( @Valid @ModelAttribute("emp") Employee e, BindingResult br)  
//    {  
	public void addUser(@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
			@RequestParam("email") String email, @RequestParam("role") String role,
			@RequestParam("department") String department, @RequestParam("mobileNo") String mobileNo,
			HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addUser");
			String result = service.addUser(username, password, email, mobileNo, name, confirmPassword, role,
					department);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

//	/@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)

	@RequestMapping(value = "/updateUser")
	public void updateUser(@RequestParam("name") String name, @RequestParam("id") long id,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
			@RequestParam("email") String email, @RequestParam("role") String role,
			@RequestParam("department") String department, @RequestParam("mobileNo") String mobileNo,
			HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called UpdateUser");
			String result = service.updateUser(id, password, email, mobileNo, name, confirmPassword, role, department);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/viewUsers", method = RequestMethod.GET)
	public ModelAndView viewUsers(HttpSession session) {
		ModelAndView m = new ModelAndView();
		m.addObject("userData", service.viewUsers());
		m.setViewName("viewUsers");
		return m;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void delete(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteUser(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/editUser/{ID}")
	public ModelAndView editUser(@PathVariable long ID, ModelAndView m) {

		System.out.println("Edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		UserMasterBean emp = service.editUser(ID);
		m.addObject("userId", ID);
		m.addObject("fetching", emp);
		long id = emp.getId();
		m.addObject("id", id);
		m.setViewName("addUser");
		return m;
	}

	// ICMP Configuration

	@RequestMapping(value = "/addIcmpform")
	public String icmpCall(Model m) {
		System.out.println("ICMP called");
		m.addAttribute("icmp", new IcmpMasterBean());
		return "ICMP";
	}

	@RequestMapping(value = "/addIcmp", method = RequestMethod.POST)
//  public String submitForm( @Valid @ModelAttribute("emp") Employee e, BindingResult br)  
//  {  
	public void addIcmp(@RequestParam("packetSize") String packetSize, @RequestParam("packetCount") String packetCount,
			@RequestParam("poolingTime") String poolingTime, @RequestParam("timeout") String timeout,
			@RequestParam("ttl") String ttl, @RequestParam("customerName") String customerName,
			@RequestParam("location") String location, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called icmpUser");
			String result = service.addIcmp(packetSize, packetCount, poolingTime, timeout, ttl, customerName, location);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/viewIcmp", method = RequestMethod.GET)
	public ModelAndView viewIcmp() {
		ModelAndView m = new ModelAndView();
		m.addObject("icmpConfig", service.viewIcmp());
		m.setViewName("viewIcmp");
		return m;
	}

	@RequestMapping(value = "/deleteIcmp", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteIcmp(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteIcmp(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/editIcmp/{ID}")
	public ModelAndView editIcmp(@PathVariable long ID, ModelAndView m) {

		System.out.println("Icmp edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		IcmpMasterBean emp = service.editIcmp(ID);
		m.addObject("icmpId", ID);
		m.addObject("icmp", emp);
		m.setViewName("ICMP");
		return m;
	}

	@RequestMapping(value = "/updateIcmp")
	public void updateIcmp(@RequestParam("packetSize") String packetSize,
			@RequestParam("packetCount") String packetCount, @RequestParam("id") long id,
			@RequestParam("poolingTime") String poolingTime, @RequestParam("timeout") String timeout,
			@RequestParam("ttl") String ttl, @RequestParam("customerName") String customerName,
			@RequestParam("location") String location, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called UpdateIcmp");
			String result = service.updateIcmp(id, packetSize, packetCount, poolingTime, timeout, ttl, customerName,
					location);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	// Company Master
	@RequestMapping(value = "/companyMasterform")
	public String companyMasterForm(Model m) {
		System.out.println("Company master  called");
		m.addAttribute("companyMaster", new CompanyMasterBean());
		return "companyMaster";
	}

	@RequestMapping(value = "/addCompany", method = RequestMethod.POST)
//  public String submitForm( @Valid @ModelAttribute("emp") Employee e, BindingResult br)  
//  {  
	public void addCompany(@RequestParam("companyName") String companyName, @RequestParam("address") String address,
			@RequestParam("email") String email, @RequestParam("number") String number, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called Company Master");
			String result = service.addCompany(companyName, address, email, number);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/viewCompanyMaster", method = RequestMethod.GET)
	public ModelAndView viewCompanyMaster() {
		ModelAndView m = new ModelAndView();
		m.addObject("viewCompanyMaster", service.viewCompanyMaster());
		m.setViewName("viewCompanyMaster");
		return m;
	}

	@RequestMapping(value = "/deleteCompanyMaster", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteCompanyMaster(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteCompanyMaster(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/editCompanyMaster/{ID}")
	public ModelAndView editCompanyMaster(@PathVariable long ID, ModelAndView m) {

		System.out.println("Company Master edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		CompanyMasterBean emp = service.editCompanyMaster(ID);
		m.addObject("companyMasterId", ID);
		m.addObject("companyMaster", emp);
		m.setViewName("companyMaster");
		return m;
	}

	@RequestMapping(value = "/updateCompanyMaster")
	public void updateCompanyMaster(@RequestParam("companyName") String companyName,
			@RequestParam("address") String address, @RequestParam("email") String email,
			@RequestParam("number") String number, @RequestParam("id") long id, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateCompanyMaster");
			String result = service.updateCompanyMaster(id, companyName, address, email, number);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	// location master
	@RequestMapping(value = "/locationMasterform")
	public ModelAndView localtionMasterForm(ModelAndView modelview, Model m) {
//		  Map<String, String> countryMap = new HashMap();
//		    countryMap.put("AR", "Argentina");
//		    countryMap.put("IN", "India");
//		    countryMap.put("JP", "Japan");
//		    countryMap.put("US", "United States");
//		    countryMap.put("SG", "Singapore");
		System.out.println("Location master  called");

		modelview.addObject("locationMaster", new LocationMasterBean());
		modelview.addObject("companyOptions", service.getCompanyMap());
		modelview.setViewName("locationMaster");
		return modelview;
	}

	@RequestMapping(value = "/addlocationMaster", method = RequestMethod.POST)
//  public String submitForm( @Valid @ModelAttribute("emp") Employee e, BindingResult br)  
//  {  
	public void addlocationMaster(@RequestParam("companyName") String companyName,
			@RequestParam("location") String location, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called location Master");
			String result = service.addlocationMaster(companyName, location);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/viewLocationMaster", method = RequestMethod.GET)
	public ModelAndView viewLocationMaster() {
		ModelAndView m = new ModelAndView();
		m.addObject("viewLocationMaster", service.viewLocationMaster());
		m.setViewName("viewLocationMaster");
		return m;
	}

	@RequestMapping(value = "/deleteLocationMaster", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteLocationMaster(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteLocationMaster(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/editLocationMaster/{ID}")
	public ModelAndView editLocationMaster(@PathVariable long ID, ModelAndView m) {

		System.out.println("Location Master edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		LocationMasterBean emp = service.editLocationMaster(ID);
		m.addObject("locationMasterId", ID);
		m.addObject("locationMaster", emp);
		m.addObject("companyOptions", service.getCompanyMap());
		m.setViewName("locationMaster");
		return m;
	}

	@RequestMapping(value = "/updateLocationMaster")
	public void updateCompanyMaster(@RequestParam("companyName") String companyName,
			@RequestParam("location") String location, @RequestParam("id") long id, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateCompanyMaster");
			String result = service.updateLocationMaster(companyName, location, id);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// NW IP SCAN
	@RequestMapping("/nwIpScanForm")
	public String nwIpScanForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called nwIpScanForm");

		m.addAttribute("nwIpScan", new NwIpScanBean());
		m.addAttribute("group", service.getGroupMap());

		return "nwIpScan";
	}

	@RequestMapping(value = "/addIpScan", method = RequestMethod.POST)
//  public String submitForm( @Valid @ModelAttribute("emp") Employee e, BindingResult br)  
//  {  
	public void addIpScan(@RequestParam("groupName") String groupName, @RequestParam("ipScan") String ipScan,
			@RequestParam("endIpScan") String endIpScan, HttpServletResponse res, HttpServletRequest req) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called IPSCAN Master");
//			String ipScan = req.getParameter("ipScan");
//			String endIpScan = req.getParameter("endIpScan");
			String result = service.addIpScan(groupName, ipScan, endIpScan);
			System.out.println("ipscan  = " + ipScan);
			System.out.println("endIP = " + endIpScan);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/viewIpScan", method = RequestMethod.GET)
	public ModelAndView viewIpScan() {
		ModelAndView m = new ModelAndView();
		m.addObject("viewIpScan", service.viewIpScan());
		m.setViewName("viewNwIpScan");
		return m;
	}

	@RequestMapping(value = "/deleteIpScan", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteIpScan(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteIpScan(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/editIpScan/{ID}")
	public ModelAndView editIpScan(@PathVariable long ID, ModelAndView m) {

		System.out.println("editIpScan Master edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		NwIpScanBean emp = service.editIpScan(ID);
		m.addObject("addIpScanId", ID);
		m.addObject("nwIpScan", emp);
		m.addObject("group", service.getGroupMap());
		m.setViewName("nwIpScan");
		return m;
	}

	@RequestMapping(value = "/updateIpScan")
	public void updateIpScan(@RequestParam("groupName") String groupName, @RequestParam("ipScan") String ipScan,
			@RequestParam("endIpScan") String endIpScan, @RequestParam("id") long id, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateIpScan");
			String result = service.updateIpScan(groupName, ipScan, endIpScan, id);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// Schedule Scan
	@RequestMapping("/ScheduleScan")
	public String ScheduleScanForm() {
		return "ScheduleScan";
	}
//	@RequestMapping(value = { "/UpdateCompany/{name}" }, method = RequestMethod.GET)
//	public ModelAndView UpdateCompany(HttpServletRequest req, HttpServletResponse res, @PathVariable String name) {
//		ModelAndView m = new ModelAndView("UpdateCompanyMaster");
//
//		m.addObject("company", service.CompanyList(name));
//
//		return m;
//
//	}

	/* Add Group Master Form */

	@RequestMapping("/addGroupForm")
	public String addGroupForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addGroupForm");

		m.addAttribute("fetchGroupInfo", new GroupMasterBean());

		return "addGroup";
	}

	/* Add Group Master Data */

	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public void addGroup(@RequestParam("groupName") String groupName, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addGroup");
			String result = service.addGroup(groupName);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	/* Add Group Listing */
	@RequestMapping(value = "/groupListing", method = RequestMethod.GET)
	public JSONArray groupListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.groupListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete Group */

	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
	public void deleteGroup(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteGroup(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add Service Provider Master Form */

	@RequestMapping("/addServiceProviderForm")
	public String addServiceProviderForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addServiceProviderForm");

		m.addAttribute("fetchSPInfo", new ServiceProviderMasterBean());

		return "addServiceProvider";
	}

	/* Add Service Provider Master Data */

	@RequestMapping(value = "/addServiceProvider", method = RequestMethod.POST)
	public void addServiceProvider(@RequestParam("serviceProviderName") String serviceProviderName,
			HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addServiceProvider");
			String result = service.addServiceProvider(serviceProviderName);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	/* Add Service Provider Listing */
	@RequestMapping(value = "/serviceProviderListing", method = RequestMethod.GET)
	public JSONArray serviceProviderListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.serviceProviderListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete Service Provider */

	@RequestMapping(value = "/deleteServiceProvider", method = RequestMethod.POST)
	public void deleteServiceProvider(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteServiceProvider(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add Make & Model Master Form */

	@RequestMapping("/addMakeModelForm")
	public String addMakeModelForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addMakeModelForm");

		m.addAttribute("fetchMakeModelInfo", new MakeModelMasterBean());

		return "addMakeModel";
	}

	/* Add Make and Model Master Data */

	@RequestMapping(value = "/addMakeModel", method = RequestMethod.POST)
	public void addMakeModel(@RequestParam("makeModelName") String makeModelName, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addMakeModel");
			String result = service.addMakeModel(makeModelName);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	/* Add Make & Model Listing */
	@RequestMapping(value = "/makeModelListing", method = RequestMethod.GET)
	public JSONArray makeModelListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.makeModelListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete Make & Model Provider */

	@RequestMapping(value = "/deleteMakeModel", method = RequestMethod.POST)
	public void deleteMakeModel(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteMakeModel(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add Device Type Form */

	@RequestMapping("/addDeviceTypeForm")
	public String addDeviceTypeForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addDeviceTypeForm");

		m.addAttribute("fetchDeviceTypeInfo", new DeviceTypeMasterBean());

		return "addDeviceType";
	}

	/* Add Device Type Master Data */

	@RequestMapping(value = "/addDeviceType", method = RequestMethod.POST)
	public void addDeviceType(@RequestParam("deviceTypeName") String deviceTypeName, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addDeviceType");
			String result = service.addDeviceType(deviceTypeName);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	/* Add Device Type Listing */
	@RequestMapping(value = "/deviceTypeListing", method = RequestMethod.GET)
	public JSONArray deviceTypeListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.deviceTypeListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete Device Type Provider */

	@RequestMapping(value = "/deleteDeviceType", method = RequestMethod.POST)
	public void deleteDeviceType(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteDeviceType(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add AD Configuration Form */

	@RequestMapping("/addADConfigForm")
	public String addADConfigForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addADConfigForm");

		m.addAttribute("fetchADConfigInfo", new ADConfigMasterBean());

		return "addADConfig";
	}

	/* Add AD Configuration */

	@RequestMapping(value = "/addADConfig", method = RequestMethod.POST)
	public void addADConfig(@RequestParam("ldapServerName") String ldapServerName,
			@RequestParam("username") String username, @RequestParam("logonDomain") String logonDomain,
			@RequestParam("password") String password, @RequestParam("serverPort") long serverPort,
			HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addADConfig");
			String result = service.addADConfig(ldapServerName, username, logonDomain, password, serverPort);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add AD Configuration Listing */
	@RequestMapping(value = "/adConfigListing", method = RequestMethod.GET)
	public JSONArray adConfigListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.adConfigListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete AD Configuration */

	@RequestMapping(value = "/deleteADConfig", method = RequestMethod.POST)
	public void deleteADConfig(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteADConfig(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Edit AD Configuration */

	@RequestMapping(value = "/editADConfig/{ID}")
	public ModelAndView editADConfig(@PathVariable long ID, ModelAndView m) {

		System.out.println("Edit controller called");
		ADConfigMasterBean adconfig = service.editADConfig(ID);
		System.out.println("response:" + adconfig.getId());
		m.addObject("adConfigId", ID);
		m.addObject("fetchADConfigInfo", adconfig);
		m.setViewName("addADConfig");
		return m;
	}

	/* Update AD Configuration */

	@RequestMapping(value = "/updateADConfig", method = RequestMethod.POST)
	public void updateADConfig(@RequestParam("ldapServerName") String ldapServerName,
			@RequestParam("username") String username, @RequestParam("logonDomain") String logonDomain,
			@RequestParam("password") String password, @RequestParam("serverPort") long serverPort,
			@RequestParam("id") long id, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called updateADConfig");
			String result = service.updateADConfig(ldapServerName, username, logonDomain, password, serverPort, id);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add Email Configuration Form */

	@RequestMapping("/addEmailConfigForm")
	public String addEmailConfigForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addEmailConfigForm");

		m.addAttribute("fetchEmailConfigInfo", new EmailConfigMasterBean());

		return "addEmailConfig";
	}

	/* Add Email Configuration */

	@RequestMapping(value = "/addEmailConfig", method = RequestMethod.POST)
	public void addEmailConfig(@RequestParam("emailId") String emailId, @RequestParam("port") long port,
			@RequestParam("smtpServer") String smtpServer, @RequestParam("timeout") long timeout,
			@RequestParam("isSslTls") String isSslTls, @RequestParam("username") String username,
			@RequestParam("isSmtpAuth") String isSmtpAuth, @RequestParam("password") String password,
			HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addEmailConfig" + isSmtpAuth + "//" + isSslTls);
			String result = service.addEmailConfig(emailId, port, smtpServer, timeout, isSslTls, username, isSmtpAuth,
					password);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add Email Configuration Listing */
	@RequestMapping(value = "/emailConfigListing", method = RequestMethod.GET)
	public JSONArray emailConfigListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.emailConfigListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete Email Configuration */

	@RequestMapping(value = "/deleteEmailConfig", method = RequestMethod.POST)
	public void deleteEmailConfig(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteEmailConfig(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Edit Email Configuration */

	@RequestMapping(value = "/editEmailConfig/{ID}")
	public ModelAndView editEmailConfig(@PathVariable long ID, ModelAndView m) {

		System.out.println("Edit controller called");
		EmailConfigMasterBean emailconfig = service.editEmailConfig(ID);
		System.out.println("response:" + emailconfig.getId());
		m.addObject("emailConfigId", ID);
		m.addObject("emailsslcheckbox", emailconfig.getIsSslTls());
		m.addObject("emailsmtpcheckbox", emailconfig.getIsSmtpAuth());
		m.addObject("fetchEmailConfigInfo", emailconfig);
		m.setViewName("addEmailConfig");
		return m;
	}

	/* Add SNMP Configuration Form */

	@RequestMapping("/addSNMPConfigForm")
	public String addSNMPConfigForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addSNMPConfigForm");

		m.addAttribute("fetchSNMPConfigInfo", new SNMPConfigMasterBean());

		return "addSnmpConfig";
	}

	/* Add SNMP Configuration */

	@RequestMapping(value = "/addSNMPConfig", method = RequestMethod.POST)
	public void addSNMPConfig(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("version") String version, @RequestParam("community") String community,
			@RequestParam("username") String username, @RequestParam("authProtocol") String authProtocol,
			@RequestParam("context") String context, @RequestParam("authPassword") String authPassword,
			@RequestParam("encryptProtocol") String encryptProtocol, @RequestParam("port") long port,
			@RequestParam("encryptPassword") String encryptPassword, @RequestParam("timeout") long timeout,
			@RequestParam("retries") long retries, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addSNMPConfig");
			String result = service.addSNMPConfig(name, description, version, community, username, authProtocol,
					context, authPassword, encryptProtocol, port, encryptPassword, timeout, retries);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping("/testSNMPConfig")
	public void testSNMPConfig(@RequestParam("deviceIp") String deviceIp, @RequestParam("snmp") String snmp,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			/*
			 * private long id; private String name; private String description; private
			 * String version; private String community; private String username; private
			 * String context; private String authProtocol; private String authPassword;
			 * private String encryptProtocol; private String encryptPassword; private long
			 * port; private long timeout; private long retries;
			 */
			SNMPConfigMasterBean snmpConfigData = service.fetchSMNPConfigService(deviceIp, snmp);
			System.out.println("id" + "=" + snmpConfigData.getId());
			System.out.println("description" + "=" + snmpConfigData.getName());
			System.out.println("version" + "=" + snmpConfigData.getVersion());
			System.out.println("community" + "=" + snmpConfigData.getCommunity());
			System.out.println("username" + "=" + snmpConfigData.getUsername());
			System.out.println("context" + "=" + snmpConfigData.getContext());
			System.out.println("authProtocol" + "=" + snmpConfigData.getAuthProtocol());
			System.out.println("authPassword" + "=" + snmpConfigData.getAuthPassword());
			System.out.println("encryptProtocol" + "=" + snmpConfigData.getEncryptProtocol());
			System.out.println("encryptPassword" + "=" + snmpConfigData.getEncryptPassword());
			System.out.println("port" + "=" + snmpConfigData.getPort());
			System.out.println("timeout" + "=" + snmpConfigData.getTimeout());
			System.out.println("retries" + "=" + snmpConfigData.getRetries());
			String data = "successfully";
			out = response.getWriter();
			out.print(data);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	/* Add Snmp Configuration Listing */
	@RequestMapping(value = "/snmpConfigListing", method = RequestMethod.GET)
	public JSONArray snmpConfigListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.snmpConfigListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete SNMP Configuration */

	@RequestMapping(value = "/deleteSNMPConfig", method = RequestMethod.POST)
	public void deleteSNMPConfig(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteSNMPConfig(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/discoveredNetwork", method = RequestMethod.GET)
	public ModelAndView viewAddNode() {
		ModelAndView m = new ModelAndView();
		m.addObject("discoveredNetwork", service.discoveredNetwork());
		m.setViewName("viewDiscoveredNetwork");
		return m;
	}

	// SLA Master - 14/10/2023
	@RequestMapping("/slaMaster")
	public String slaMaster(Model m) {
		m.addAttribute("slaMaster", new SLAMasterBean());
		return "SLAMaster";
	}

	@RequestMapping("/slaMainMaster")
	public ModelAndView slaMainMaster(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called slaMainMaster");

		ModelAndView m = new ModelAndView();
//		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("SLAMainMaster");
		return m;
	}

	
	@RequestMapping("/saveSLADataMaster")
	public void saveSLADataMaster(HttpServletRequest req, HttpServletResponse res) {
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] daydata = req.getParameterValues("dayArray");
		List<String> Dayslist = Arrays.asList(daydata);

		String finalArray = service.saveSLADataMaster(from_date, to_date, Dayslist);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(finalArray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	@RequestMapping(value = "/getslamasterdata", method = RequestMethod.GET)
	public JSONArray getslamasterdata(HttpServletResponse response) {
		System.out.println("inside getslamasterdata");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.getslamasterdata();
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}
	
	// Interface Monitoring
	@RequestMapping("/interfaceParameter")
	public String interfaceParameter(Model m) {
		// ModelAndView m = new ModelAndView();
		Map<String, String> groupName = dao.getGroupMap();
		groupName.put("All", "All");
		m.addAttribute("groupName", groupName);
		// m.addObject("groupName", dao.getGroupMap());
		// m.setViewName("InterfaceParameter");
		return "InterfaceParameter";
	}

	@RequestMapping(value = "/addInterfaceParameter", method = RequestMethod.POST)
	public void addInterfaceParameter(@RequestParam("ids") String id, @RequestParam("monitoring") String monitoring,
			@RequestParam("bwHistory") String bwHistory, @RequestParam("crc") String crc,
			@RequestParam("bwThreshold") String bwThreshold, @RequestParam("mailAlert") String mailAlert,
			@RequestParam("smsAlert") String smsAlert, @RequestParam("autoTicketing") String autoTicketing,
			@RequestParam("procureBW") String procureBW, HttpServletResponse res) {
		String ids = id.replace(",", "','");

		try {
			PrintWriter out = res.getWriter();
			String resp = service.addInterfaceParameter(ids, monitoring, bwHistory, crc, bwThreshold, mailAlert,
					smsAlert, autoTicketing, procureBW);
			System.out.println("interface parameter = " + resp);
			out.print(resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Mail Template
	@RequestMapping("/mailTemplate")
	public String mailTemplate(Model m, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		m.addAttribute("fetchMailTemplate", new MailTemplateBean());
		m.addAttribute("mailTemplates", service.getDistinctNodes(userScopeData));
		return "MailTemplate";
	}

	@RequestMapping(value = "/editMailTemplate/{SR_NO}")
	public ModelAndView editMailTemplate(@PathVariable long SR_NO, ModelAndView m, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		System.out.println("Edit controller called");
		MailTemplateBean editMailTemplate = service.MailTemplateBeanService(SR_NO);
		System.out.println("response:" + editMailTemplate.getSR_NO());
		m.addObject("SR_NO", SR_NO);
		m.addObject("fetchMailTemplate", editMailTemplate);
		m.addObject("mailTemplates", service.getDistinctNodes(userScopeData));
		m.setViewName("MailTemplate");
		return m;
	}

	/* Update AD Configuration */

	@RequestMapping(value = "/updateMailTemplate", method = RequestMethod.POST)
	public void updateMailTemplate(@RequestParam("templateName") String templateName,
			@RequestParam("subject") String subject, @RequestParam("sendTime") String sendTime,
			@RequestParam("ipAddressList") String ipAddressList, @RequestParam("functionName") String functionName,
			@RequestParam("msgBody") String msgBody, @RequestParam("attachment") String attachment,
			@RequestParam("toMail") String toMail, @RequestParam("periods") String periods,
			@RequestParam("attachmentType") String attachmentType, @RequestParam("ccMail") String ccMail,
			@RequestParam("days") String days, @RequestParam("isActive") String isActive,
			@RequestParam("SR_NO") Long SR_NO, @RequestParam("phone") String phone, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called update Mail Template");
			String result = service.updateMailTemplateService(templateName, subject, sendTime, ipAddressList,
					functionName, msgBody, attachment, toMail, periods, attachmentType, ccMail, days, isActive, SR_NO,
					phone);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/addMailTemplate", method = RequestMethod.POST)
	public void mailTemplate(@RequestParam("templateName") String templateName, @RequestParam("subject") String subject,
			@RequestParam("sendTime") String sendTime, @RequestParam("ipAddressList") String ipAddressList,
			@RequestParam("functionName") String functionName, @RequestParam("msgBody") String msgBody,
			@RequestParam("attachment") String attachment, @RequestParam("toMail") String toMail,
			@RequestParam("periods") String periods, @RequestParam("attachmentType") String attachmentType,
			@RequestParam("ccMail") String ccMail, @RequestParam("days") String days,
			@RequestParam("isActive") String isActive, @RequestParam("phone") String phone, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			String result = service.mailTemplateService(templateName, subject, sendTime, ipAddressList, functionName,
					msgBody, attachment, toMail, periods, attachmentType, ccMail, days, isActive, phone);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/viewMailTemplate", method = RequestMethod.GET)
	public JSONArray viewMailTemplate(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.viewMailTemplate(userScopeData);
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	// deleted data
	@RequestMapping(value = "/deleteMailTemplate", method = RequestMethod.POST)
	public void deleteMailTemplate(@RequestParam("SR_NO") long SR_NO, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + SR_NO);
			String result = service.deleteMailTemplateService(SR_NO);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	// Ping Tool
	@RequestMapping("/ping")
	public String ping() {
		return "Ping";
	}

	@RequestMapping(value = { "/CheckPing" }, method = RequestMethod.POST)
	public void CheckPing(HttpServletRequest req, HttpServletResponse res) {

		String ip = req.getParameter("ip");

		if (ip.contains(" -t")) {
			ip = ip.replace(" -t", "");
		}
		if (ip.contains("ping")) {
			ip = ip.replace("ping", "");
		}
		System.out.println("ip address " + ip);
		PrintWriter out = null;
		String result = "";
		try {
			out = res.getWriter();
			result = service.checkPing(ip);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
	// End Ping Tool

	// Trace Route
	@RequestMapping("/traceRoute")
	public String traceRoute() {
		return "TraceRoute";
	}

	@RequestMapping(value = { "/CheckTraceRoute" }, method = RequestMethod.POST)
	public void checkTraceRoute(HttpServletRequest req, HttpServletResponse res) {

		String ip = req.getParameter("ip");

		if (ip.contains(" -t")) {
			ip = ip.replace(" -t", "");
		}
		if (ip.contains("ping")) {
			ip = ip.replace("ping", "");
		}
		System.out.println("ip address " + ip);
		PrintWriter out = null;
		String result = "";
		try {
			out = res.getWriter();
			result = service.checkTraceRoute(ip);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	// End Trace Route

	// SSH
	@RequestMapping("/SSH")
	public String SSH(Model m) {
		return "SSH";
	}

	@RequestMapping(value = { "/getIPAddress" }, method = RequestMethod.GET)
	public void getIPAddress(HttpServletRequest req, HttpServletResponse res) {

		PrintWriter out = null;
		String result = "";
		try {
			out = res.getWriter();
			out.print(service.getIpAddress());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping(value = { "/checkSSH" }, method = RequestMethod.POST)
	public void checkSSH(HttpServletRequest req, HttpServletResponse res) {

		String ipAddress = req.getParameter("ipAddress");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String command = req.getParameter("command");

		PrintWriter out = null;
		String result = "";
		try {
			out = res.getWriter();
			result = service.checkSSH(ipAddress, username, password, command);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
	// End SSH

	// Push SSH
	@RequestMapping("/pushSSH")
	public String pushSSH(Model m) {
		return "PushSSH";
	}

	@RequestMapping(value = { "/checkPushSSH" }, method = RequestMethod.POST)
	public void checkPushSSH(HttpServletRequest req, HttpServletResponse res) {

		String ipAddress = req.getParameter("ipAddress");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String command = req.getParameter("command");

		PrintWriter out = null;
		String result = "";
		try {
			out = res.getWriter();
			result = service.checkPushSSH(ipAddress, username, password, command);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
	// End Push SSH

	// Manage user scope
	@RequestMapping("/userManageScopeForm")
	public String userManageScopeForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called userManageScopeForm");
		m.addAttribute("userScopeFetching", new UserManageScopeBean());

		List<UserMasterModel> userList = service.viewUsers();
		Set<String> uniqueUsernames = new HashSet();

		for (UserMasterModel user : userList) {
			uniqueUsernames.add(user.getUSERNAME());
		}

		List<String> usernames = new ArrayList(uniqueUsernames);
		m.addAttribute("userNameData", usernames);
		// Group Name
		m.addAttribute("groupNameData", service.getGroupMap());
		// Location
		List<LocationMasterModel> locationList = service.viewLocationMaster();
		List<String> location = new ArrayList();
		for (LocationMasterModel user : locationList) {
			location.add(user.getLOCATION_NAME());
		}
		m.addAttribute("locationData", location);
		// Admin Name

		List<UserScopeMaster> dataList = service.getUserScopeMaster();
		List<String> dashboardName = new ArrayList<String>();
		List<String> adminName = new ArrayList<String>();
		List<String> reportName = new ArrayList<String>();
		List<String> graphName = new ArrayList<String>();
		for (UserScopeMaster data : dataList) {
			adminName.add(data.getAdmin());
		}
		m.addAttribute("adminData", adminName);

		if (dataList != null) {
			for (UserScopeMaster data : dataList) {
				String dashboard = data.getDashboard();
				if (dashboard != null) {
					dashboardName.add(dashboard);
				}
			}
		}
		m.addAttribute("dashboardData", dashboardName);

		if (dataList != null) {
			for (UserScopeMaster data : dataList) {
				String report = data.getReport();
				if (report != null) {
					reportName.add(report);
				}
			}
		}
		m.addAttribute("reportData", reportName);

		if (dataList != null) {
			for (UserScopeMaster data : dataList) {
				String graph = data.getGraph();
				if (graph != null) {
					graphName.add(graph);
				}
			}
		}
		m.addAttribute("graphData", graphName);

		return "userManageScope";
	}

	// add manage user scope
	@RequestMapping(value = "/addUserManageScope", method = RequestMethod.POST)
	public void addUserManageScope(@RequestParam("username") String username,
			@RequestParam("groupname") String groupname, @RequestParam("location") String location,
			@RequestParam("admin") String admin, @RequestParam("dashboard") String dashboard,
			@RequestParam("report") String report, @RequestParam("graph") String graph, HttpServletResponse res) {
		System.out.println("Called addUserManageScope");

		try {
			PrintWriter out = res.getWriter();
			String result = service.userManageScopeService(username, groupname, location, admin, dashboard, report,
					graph);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// view manage user scope
	@RequestMapping(value = "/viewUserManageScope", method = RequestMethod.GET)
	public ModelAndView viewUserManageScope() {
		ModelAndView m = new ModelAndView();
		m.addObject("userData", service.viewUserManageScope());
		m.setViewName("viewUserManageScope");
		return m;
	}

	// delete user manage user scope
	@RequestMapping(value = "/deleteUserManageScope", method = RequestMethod.POST)
	public void deleteUserManageScope(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteUserManageScope(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	// Push Configuration

	@RequestMapping("/pushConfiguration")
	public ModelAndView DeviceReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called pushConfiguration");

		ModelAndView m = new ModelAndView();
		// m.addObject("templateName", dao.getTemplateName());
		// m.addObject("pushConifgBean", new PushConfigurationBean());
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("pushConfiguration");
		return m;
	}

	@RequestMapping(value = "/getTemplateName", method = RequestMethod.GET)
	public void getTemplateName(HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getTemplateName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping(value = "/addPushConfiguration", method = RequestMethod.POST)
	public void addPushConfiguration(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller addPushConfiguration");

		PrintWriter out = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		try {
			out = res.getWriter();

			String templateName = req.getParameter("templateName");
			String command = req.getParameter("command");
			String datepickerString = req.getParameter("datepicker");

			// Check if datepicker is not empty before parsing
			if (datepickerString != null && !datepickerString.trim().isEmpty()) {
				// Correct date parsing
				Timestamp datepicker = new Timestamp(formatter.parse(datepickerString).getTime());

				String status = req.getParameter("status");
				String ip_address = req.getParameter("ipAddressCheck");

				// Assuming you have a service method like this
				String result = service.addPushConfiguration(templateName, command, datepicker, status, ip_address);

				System.out.println("final response = " + result);
				out.print(result);
			} else {
				out.print("Date is empty");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			out.print("Error parsing date");
		} catch (IOException e) {
			e.printStackTrace();
			// Handle other IO exceptions
		} catch (Exception e) {
			e.printStackTrace();
			// Handle other exceptions
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@RequestMapping(value = "/getCommandsOnTemplateName", method = RequestMethod.GET)
	public void getCommandsOnTemplateName(@RequestParam("templateName") String templateName, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.fetchCommand(templateName));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	// Push Configuration Report
	@RequestMapping("/pushConfigurationReport")
	public ModelAndView pushConfigurationReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called pushConfigurationReport");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("pushConfigurationReport");
		return m;
	}

	@RequestMapping("/viewPushConfiguration")
	public String viewPushConfiguration(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called subnetScan");

		return "viewPushConfiguration";
	}

//			@RequestMapping("/pushConfiReport" )
	@RequestMapping(value = "/pushConfiReport", method = RequestMethod.POST)
	public ModelAndView pushConfiReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller pushConfiReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<PushConfigurationBean> pushData = service.pushConfiReport(from_date, to_date, list);
		System.out.println("Report Data:" + pushData);
		ModelAndView m = new ModelAndView();
		m.addObject("pushConfigurationData", pushData);
		m.setViewName("viewPushConfiguration");
		return m;

	}

	// Subnet Scan
	@RequestMapping("/subnetScan")
	public String subnetScan(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called subnetScan");

		m.addAttribute("nwSubnetScan", new SubnetScanBean());
		m.addAttribute("groupName", service.getGroupMap());

		return "subnetScan";
	}

	@RequestMapping(value = "/addSubnetScan", method = RequestMethod.POST)
	public void addSubnetScan(@RequestParam("groupName") String groupName,
			@RequestParam("subnetScan") String subnetScan, @RequestParam("endsubnetScan") String endsubnetScan,
			HttpServletResponse res, HttpServletRequest req) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called add subnetScan Master");
			// String ipScan = req.getParameter("ipScan");
			// String endIpScan = req.getParameter("endIpScan");
			String result = service.addSubnetScan(groupName, subnetScan, endsubnetScan);
			System.out.println("subnetScan  = " + subnetScan);
			System.out.println("endsubnetScan = " + endsubnetScan);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/viewSubnetScan", method = RequestMethod.GET)
	public ModelAndView viewSubnetScan() {
		ModelAndView m = new ModelAndView();
		m.addObject("viewSubnetScan", service.viewSubnetScan());
		m.setViewName("viewSubnetScan");
		return m;
	}

	@RequestMapping(value = "/deleteSubnetScan", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteSubnetScan(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteSubnetScan(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/editSubnetScan/{ID}")
	public ModelAndView editSubnetScan(@PathVariable long ID, ModelAndView m) {

		System.out.println("editSubnetScan Master edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		SubnetScanBean emp = service.editSubnetScan(ID);
		m.addObject("id", ID);
		m.addObject("nwSubnetScan", emp);
		m.addObject("groupName", service.getGroupMap());
		m.setViewName("subnetScan");
		System.out.println(emp);
		return m;
	}

	@RequestMapping(value = "/updateSubnetScan")
	public void updateSubnetScan(@RequestParam("groupName") String groupName,
			@RequestParam("subnetScan") String subnetScan, @RequestParam("endsubnetScan") String endsubnetScan,
			@RequestParam("id") long id, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateIpScan");
			String result = service.updateSubnetScan(groupName, subnetScan, endsubnetScan, id);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// Configuration backup templates
	@RequestMapping("/configurationBackup")
	public String configurationBackup(Model m, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		m.addAttribute("fetchConfigData", new ConfigBackupBean());
		m.addAttribute("configTemplates", service.getDistinctNodes(userScopeData));
		return "configurationBackup";
	}

	@RequestMapping(value = "/viewConfigurationBackup", method = RequestMethod.GET)
	public JSONArray viewConfigurationBackup(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.viewConfigurationBackup(userScopeData);
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/addConfigurationBackup", method = RequestMethod.POST)
	public void addConfigurationBackup(@RequestParam("deviceIp") String deviceIp,
			@RequestParam("enablePass") String enablePass, @RequestParam("deviceMode") String deviceMode,
			@RequestParam("confCommand") String confCommand, @RequestParam("username") String username,
			@RequestParam("foCommand") String foCommand, @RequestParam("password") String password,
			HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String result = service.addConfigurationBackup(deviceIp, enablePass, deviceMode, confCommand, username,
					foCommand, password);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/deleteConfigurationBackup", method = RequestMethod.POST)
	public void deleteConfigurationBackup(@RequestParam("ID") long ID, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String result = service.deleteConfigurationBackup(ID);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/editConfigurationBackup/{ID}")
	public ModelAndView editConfigurationBackup(@PathVariable Long ID, ModelAndView m, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Edit controller called");
		ConfigBackupBean configData = service.ConfigBackupBeanService(ID);
		System.out.println("response:" + configData.getID());
		m.addObject("ID", ID);
		m.addObject("fetchConfigData", configData);
		m.addObject("configTemplates", service.getDistinctNodes(userScopeData));
		m.setViewName("configurationBackup");
		return m;
	}

	/* Update AD Configuration */
	@RequestMapping(value = "/updateConfigurationBackup", method = RequestMethod.POST)
	public void updateConfigurationBackup(@RequestParam("deviceIp") String deviceIp, @RequestParam("ID") Long ID,
			@RequestParam("deviceMode") String deviceMode, @RequestParam("confCommand") String confCommand,
			@RequestParam("foCommand") String foCommand, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called update ConfigurationBackup Template");
			String result = service.updateConfigurationBackupService(deviceIp, deviceMode, confCommand, foCommand, ID);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/addScheduleScan", method = RequestMethod.POST)
	public void addScheduleScan(@RequestParam("scheduleType") String scheduleType,
			@RequestParam("startIp") String startIp, @RequestParam("subnetIp") String subnetIp,
			@RequestParam("once_time") String once_time, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String result = service.addScheduleScanService(scheduleType, startIp, subnetIp, once_time);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/viewScheduleScan", method = RequestMethod.GET)
	public JSONArray viewScheduleScan(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.viewScheduleScanService();
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/deleteScheduleScan", method = RequestMethod.POST)
	public void deleteScheduleScan(@RequestParam("ID") long ID, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String result = service.deleteScheduleScanService(ID);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/manualTopologyForm")
	public ModelAndView manualTopologyForm(HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		ModelAndView m = new ModelAndView();
		m.addObject("addNodeModal", new AddNodeBean());
		List<AddNodeModel> ipList = nodeService.viewAddNode(userScopeData);
		List<String> ipData = new ArrayList<String>();
		ipData.add("Please Select");
		for (AddNodeModel user : ipList) {
			ipData.add(user.getDEVICE_IP());
		}
		m.addObject("nodeIpList", ipData);
		m.setViewName("manualTopologyForm");
		return m;
	}

	@RequestMapping("/manualTopology")
	public void manualTopology(@RequestParam("sourceDeviceIp") String sourceDeviceIp,
			@RequestParam("source_interface_name") String source_interface_name,
			@RequestParam("destinationDeviceIp") String destinationDeviceIp,
			@RequestParam("destination_interface_name") String destination_interface_name, HttpServletResponse res) {
		System.out.println("manualTopology Controller");
		try {
			PrintWriter out = res.getWriter();
			String result = service.manualTopology(sourceDeviceIp, source_interface_name, destinationDeviceIp,
					destination_interface_name);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = { "/viewManualTopology" }, method = RequestMethod.GET)
	public String viewManualTopology(ModelMap model) {

		return "viewManualTopology";
	}

	@RequestMapping(value = { "/getManualTopology" }, method = RequestMethod.POST)
	public void getManualTopology(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			String contextPath = request.getContextPath();
			out.print(service.getManualTopology("username", contextPath));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = "/GetSpeedTestDetails")
	public void GetSpeedTestDetails(@RequestParam("srcIP") String srcIP, @RequestParam("infname") String infname,
			HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called GetSpeedTestDetails");
			
			
			String result = service.GetSpeedTestDetails(srcIP, infname);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
}
