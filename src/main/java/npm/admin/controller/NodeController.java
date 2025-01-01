package npm.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import npm.admin.beans.AddNodeBean;
import npm.admin.beans.AddNodeParameterBean;
import npm.admin.beans.AvailabilityParameterBean;
import npm.admin.beans.CompanyMasterBean;
import npm.admin.beans.ConfigurationTemplateBean;
import npm.admin.beans.DeviceTypeMasterBean;
import npm.admin.beans.GroupMasterBean;
import npm.admin.beans.LocationMasterBean;
import npm.admin.beans.SNMPConfigMasterBean;
import npm.admin.beans.ScheduleConfigurationBean;
import npm.admin.beans.ServiceProviderMasterBean;
import npm.admin.beans.SpeedTestBean;
import npm.admin.dao.MasterDao;
import npm.admin.service.MasterService;
import npm.admin.service.NodeService;
import npm.model.LocationMasterModel;

@Controller
@RequestMapping("/node")
public class NodeController {

	@Autowired
	MasterDao dao;

	@Autowired
	NodeService service;

	@Autowired
	MasterService services;

	// Add Node
	@RequestMapping("/addNodeForm")
	public String addUserForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addNodeForm");

		m.addAttribute("addNode", new AddNodeBean());
		m.addAttribute("deviceType", new DeviceTypeMasterBean());
		m.addAttribute("deviceType", service.getdeviceType());

		m.addAttribute("groupName", new GroupMasterBean());
		m.addAttribute("groupName", service.getgroupName());

		m.addAttribute("serviceProvider", new ServiceProviderMasterBean());
		m.addAttribute("serviceProvider", service.getserviceProvider());

		m.addAttribute("snmp", new SNMPConfigMasterBean());
		m.addAttribute("snmp", service.getSnmp());

		return "addNode";
	}

	@RequestMapping(value = "/addNode", method = RequestMethod.POST)
	public void addIcmp(@RequestParam("deviceIp") String deviceIp, @RequestParam("deviceName") String deviceName,
			@RequestParam("deviceType") String deviceType, @RequestParam("groupName") String groupName,
			@RequestParam("snmp") String snmp, @RequestParam("serviceProvider") String serviceProvider,
			@RequestParam("company") String company, @RequestParam("state") String state,
			@RequestParam("zone") String zone, @RequestParam("location") String location,
			@RequestParam("district") String district, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addNode");
			String result = service.addNode(deviceIp, deviceName, deviceType, groupName, snmp, serviceProvider, company,
					state, zone, location, district);
			System.out.println("Status:" + result);
			System.out.println("ip = " + deviceIp);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	// Bulk Upload
	@RequestMapping("/bulkUploadForm")
	public String bulkUploadForm() {
		return "BulkUpload";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(@RequestParam("file") MultipartFile file, HttpServletResponse res) {
		String fileName = file.getOriginalFilename();
		System.out.println("in bulk upload controller");
		try {
			if (file.isEmpty()) {
				System.out.println("file empty");
			} else {
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String destPath = "C:" + File.separator + "NMS_Data/";
					File dir = new File(destPath);
					// System.out.println(rootPath);
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(dir + File.separator + fileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					String response = service.uploadFile(fileName, destPath);
					System.out.println("response for bulk upload = " + response);
					PrintWriter out = res.getWriter();
					out.print(response);

				} catch (Exception e) {
				}
			}
//			String response = service.uploadFile(fileName);
//			System.out.println("response for bulk upload = "+response);
//			PrintWriter out = res.getWriter();
//			out.print(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	@RequestMapping(value = "/uploadExcelFile", method = RequestMethod.POST)
//	public void uploadExcelFile(@RequestParam("bulkUploadFile") MultipartFile file, HttpServletResponse res) {
//		System.out.println("In mutlipart controller");
//		System.out.println(file.getOriginalFilename());
//		 if (!file.isEmpty()) {
//	            try {
//	                byte[] bytes = file.getBytes();
//
//	                // Creating the directory to store file
////	                String rootPath = System.getProperty("catalina.home");
////	                File dir = new File(rootPath + File.separator + "tmpFiles");
////	                if (!dir.exists())
////	                    dir.mkdirs();
//
//	                // Create the file on server
//	                File serverFile = new File("C:/NMS_Data/bulkuploadFile.xlsx");
//	                BufferedOutputStream stream = new BufferedOutputStream(
//	                        new FileOutputStream(serverFile));
//	                stream.write(bytes);
//	                stream.close();
//
//	               
//
//	            } catch (Exception e) {
//	            }
//	        } 
//		 else {
//			 System.out.println("fail");
//	        }
//	}

	@RequestMapping(value = "/viewAddNode", method = RequestMethod.GET)
	public ModelAndView viewAddNode(HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		ModelAndView m = new ModelAndView();
		m.addObject("viewAddNode", service.viewAddNode(userScopeData));

		m.addObject("addNode", new AddNodeBean());
		m.addObject("deviceType", new DeviceTypeMasterBean());
		m.addObject("deviceType", service.getdeviceType());

		m.addObject("groupName", new GroupMasterBean());
		m.addObject("groupName", service.getgroupName());

		m.addObject("serviceProvider", new ServiceProviderMasterBean());
		m.addObject("serviceProvider", service.getserviceProvider());

		m.addObject("snmp", new SNMPConfigMasterBean());
		m.addObject("snmp", service.getSnmp());

		m.addObject("company", new SNMPConfigMasterBean());
		m.addObject("company", service.getCompanyName());

		m.addObject("state", service.getStateName());
		m.addObject("zone", service.getZoneName());
		m.addObject("district", service.getDistrictName());

		List<LocationMasterModel> locationList = services.viewLocationMaster();
		List<String> location = new ArrayList<String>();
		for (LocationMasterModel user : locationList) {
			location.add(user.getLOCATION_NAME());
		}
		m.addObject("locationData", location);

		m.setViewName("viewAddNode");
		return m;
	}

	@RequestMapping(value = "/deleteAddNode", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteAddNode(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteAddNode(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/editAddNode/{ID}")
	public ModelAndView editNodeAdd(@PathVariable long ID, ModelAndView m) {

		System.out.println("editNodeAdd edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		AddNodeBean emp = service.editAddNode(ID);
		m.addObject("addNodeId", ID);
		m.addObject("addNode", emp);
		m.addObject("deviceType", service.getdeviceType());
		m.addObject("groupName", service.getgroupName());
		m.addObject("serviceProvider", service.getserviceProvider());
		m.addObject("snmp", service.getSnmp());
		m.setViewName("addNode");
		return m;
	}

	@RequestMapping(value = "/updateAddNode")
	public void updateCompanyMaster(@RequestParam("deviceIp") String deviceIp,
			@RequestParam("deviceName") String deviceName, @RequestParam("deviceType") String deviceType,
			@RequestParam("groupName") String groupName, @RequestParam("snmp") String snmp,
			@RequestParam("serviceProvider") String serviceProvider, @RequestParam("company") String company,
			@RequestParam("state") String state, @RequestParam("zone") String zone,
			@RequestParam("location") String location, @RequestParam("district") String district,
			HttpServletResponse res, @RequestParam("id") long id) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateAddNode");
			String result = service.updateAddNode(deviceIp, deviceName, deviceType, groupName, snmp, serviceProvider,
					company, state, zone, location, district, id);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// Update Bulk node
	@RequestMapping(value = "/addsnmpModal", method = RequestMethod.POST)
	public void addsnmpModal(@RequestParam("nodeIP") String nodeIP, @RequestParam("snmp") String snmp,
			HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = " + nodeIP);
			String result = service.addsnmpModal(nodeIP, snmp);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// groupName
	@RequestMapping(value = "/addGroupNameModal", method = RequestMethod.POST)
	public void addGroupNameModal(@RequestParam("nodeIP") String nodeIP, @RequestParam("groupName") String groupName,
			HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = " + nodeIP);
			String result = service.addGroupNameModal(nodeIP, groupName);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// serviceProvider
	@RequestMapping(value = "/addserviceProviderModal", method = RequestMethod.POST)
	public void addserviceProviderModal(@RequestParam("nodeIP") String nodeIP,
			@RequestParam("serviceProvider") String serviceProvider, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = " + nodeIP);
			String result = service.addserviceProviderModal(nodeIP, serviceProvider);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	//
	@RequestMapping(value = "/addcompanyModal", method = RequestMethod.POST)
	public void addcompanyModal(@RequestParam("nodeIP") String nodeIP, @RequestParam("company") String company,
			HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = " + nodeIP);
			String result = service.addcompanyModal(nodeIP, company);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	//
	@RequestMapping(value = "/adddeviceTypeModal", method = RequestMethod.POST)
	public void adddeviceTypeModal(@RequestParam("nodeIP") String nodeIP, @RequestParam("deviceType") String deviceType,
			HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = " + nodeIP);
			String result = service.adddeviceTypeModal(nodeIP, deviceType);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	//
	@RequestMapping(value = "/addstateModal", method = RequestMethod.POST)
	public void addstateModal(@RequestParam("nodeIP") String nodeIP, @RequestParam("state") String state,
			HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = " + nodeIP);
			String result = service.addstateModal(nodeIP, state);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	//
	@RequestMapping(value = "/addzoneModal", method = RequestMethod.POST)
	public void addzoneModal(@RequestParam("nodeIP") String nodeIP, @RequestParam("zone") String zone,
			HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = " + nodeIP);
			String result = service.addzoneModal(nodeIP, zone);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	//
	@RequestMapping(value = "/adddistrictModal", method = RequestMethod.POST)
	public void adddistrictModal(@RequestParam("nodeIP") String nodeIP, @RequestParam("district") String district,
			HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = " + nodeIP);
			String result = service.adddistrictModal(nodeIP, district);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	//
	@RequestMapping(value = "/addlocationModal", method = RequestMethod.POST)
	public void addlocationModal(@RequestParam("nodeIP") String nodeIP, @RequestParam("location") String location,
			HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = " + nodeIP);
			String result = service.addlocationModal(nodeIP, location);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	//
	@RequestMapping(value = "/deleteBulkNode", method = RequestMethod.POST)
	public void deleteBulkNode(@RequestParam("nodeIP") String nodeIP, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller delete Called nodeIP = " + nodeIP);
			String result = service.deleteBulkNode(nodeIP);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// Add Node Parameter Form

//	@RequestMapping("/getDistinctNode")
//	public ModelAndView getDistinctNode() {
//		ModelAndView m = new ModelAndView();
//		m.addObject("nodes", dao.getDistinctNode());
//		m.setViewName("nodeParameter");
//		return m;
//	}

	@RequestMapping("/addNodeParameterForm")
	public String addNodeParameterForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addNodeParameterForm");

		m.addAttribute("addNodeParameter", new AddNodeParameterBean());
		m.addAttribute("nodes", service.getDistinctNodes());

		return "nodeParameter";
	}

	@RequestMapping(value = "/addNodeParameter", method = RequestMethod.POST)
	public void addIcmp(@RequestParam("ip") String ip, @RequestParam("memoryThreshold") int memoryThreshold,
			@RequestParam("cpuThreshold") int cpuThreshold, @RequestParam("latencyThreshold") int latencyThreshold,
			@RequestParam("monitoring") String monitoring, @RequestParam("cpuHistory") String cpuHistory,
			@RequestParam("memoryHistory") String memoryHistory, @RequestParam("latencyHistory") String latencyHistory,
			@RequestParam("snmpDiscovery") String snmpDiscovery, @RequestParam("mailAlert") String mailAlert,
			@RequestParam("smsAlert") String smsAlert, @RequestParam("autoTicketing") String autoTicketing,
			HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addNodeParameter = " + ip);
			String result = service.addNodeParameter(ip, memoryThreshold, cpuThreshold, latencyThreshold, monitoring,
					cpuHistory, memoryHistory, latencyHistory, snmpDiscovery, mailAlert, autoTicketing, smsAlert);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/viewNodeParameter", method = RequestMethod.GET)
	public ModelAndView viewNodeParameter(HttpSession session, HttpServletResponse response) {
		String userScopeData = (String) session.getAttribute("userScope");
		ModelAndView m = new ModelAndView();
		m.addObject("viewNodeParameter", service.viewNodeParameter(userScopeData));
		System.out.println("view node parameter data = " + service.viewNodeParameter(userScopeData));

		m.setViewName("viewNodeParameter");
		return m;
	}

	@RequestMapping(value = "/deleteNodeParameter", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteNodeParameter(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteNodeParameter(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/editNodeParameter/{ID}")
	public ModelAndView editNodeParameter(@PathVariable long ID, ModelAndView m) {

		System.out.println("node parameter edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		AddNodeParameterBean emp = service.editNodeParameter(ID);
		m.addObject("addNodeParameterId", ID);
		m.addObject("addNodeParameter", emp);
		long id = emp.getId();
		m.addObject("id", id);
		m.setViewName("nodeParameter");
		return m;
	}

	@RequestMapping(value = "/updateNodeParameter")
	public void updateCompanyMaster(@RequestParam("ip") String ip, @RequestParam("memoryThreshold") int memoryThreshold,
			@RequestParam("cpuThreshold") int cpuThreshold, @RequestParam("latencyThreshold") int latencyThreshold,
			@RequestParam("monitoring") String monitoring, @RequestParam("cpuHistory") String cpuHistory,
			@RequestParam("memoryHistory") String memoryHistory, @RequestParam("latencyHistory") String latencyHistory,
			@RequestParam("snmpDiscovery") String snmpDiscovery, @RequestParam("mailAlert") String mailAlert,
			@RequestParam("smsAlert") String smsAlert, @RequestParam("autoTicketing") String autoTicketing,
			HttpServletResponse res, @RequestParam("id") long id) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateNodeParameter");
			String result = service.updateNodeParameter(ip, memoryThreshold, cpuThreshold, latencyThreshold, monitoring,
					cpuHistory, memoryHistory, latencyHistory, id, snmpDiscovery, mailAlert, autoTicketing, smsAlert);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// Config Backup

	@RequestMapping("/configBackup")
	public String DeviceReportForm(Model m) {
//		System.out.println("Controller configBackup");
//		ScheduleConfigurationBean scheduledata=new ScheduleConfigurationBean();
//		ModelAndView m = new ModelAndView();
//		m.addObject("fetchscheduleConfig", scheduledata);
//		m.addObject("groupName", dao.getGroupMap());
//		m.setViewName("ConfigBackup");
//		return m;
		m.addAttribute("fetchscheduleConfig", new ScheduleConfigurationBean());
		m.addAttribute("groupName", dao.getGroupMap());
		return "ConfigBackup";
	}

	@RequestMapping("/addConfigBackup")
	public void addConfigBackup(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller addConfigBackup");

		PrintWriter out = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			String datepickerString = req.getParameter("datepicker");
			String backupPath = req.getParameter("backuppath");
			String runConfig = req.getParameter("runConfig");
			String startupConfig = req.getParameter("startupConfig");
			String backup = req.getParameter("backup");
//			String timepicker = req.getParameter("timepicker");
			String group_name = req.getParameter("group_name");
			String ip_address = req.getParameter("ipAddressCheck");
			String configType = req.getParameter("configType");
			String backupSchedule = req.getParameter("backupSchedule");
			/* String datepicker = req.getParameter("datepicker"); */
			Timestamp datepicker1 = new Timestamp(formatter.parse(datepickerString).getTime());
			String datepicker = datepicker1.toString();

			String result = service.addConfigBackup(backupPath, runConfig, startupConfig, backup, group_name,
					ip_address, configType, backupSchedule, datepicker);
			System.out.println("final response = " + result);
			out = res.getWriter();
			out.print(result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping(value = "/viewconfigBackupdata", method = RequestMethod.GET)
	public ModelAndView viewconfigBackup(HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		ModelAndView m = new ModelAndView();
		m.addObject("configBackup", service.viewconfigBackup(userScopeData));
		m.setViewName("viewConfigBackup");
		return m;
	}

	@RequestMapping(value = "/deleteConfigBackup", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteConfigBackup(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteConfigBackup(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	// 19-12-2022
	@RequestMapping(value = "/importNodeIP", method = RequestMethod.POST)
	public void importNodeIP(@RequestParam("nodeIP") String nodeIP, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = " + nodeIP);
			String result = service.importNodeIP(nodeIP);
			// System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// Node Speed Test
	@RequestMapping("/nodeSpeedTest")
	public ModelAndView speedTest(ModelAndView modelview) {

//		modelview.addObject("speedtest", new SpeedTestBean());
//		modelview.addObject("iplisting", service.getspeedTestIps());
//		modelview.addObject("deviceslisting", service.getspeedTestDeviceslisting());
//		modelview.addObject("companyOptions", service.getCompanyMap());
		modelview.setViewName("speedTest");
		return modelview;
	}

	@RequestMapping(value = "/editSpeedTestDeviceMaster/{ID}")
	public ModelAndView editLocationMaster(@PathVariable long ID, ModelAndView m) {

		System.out.println("speed test edit Master edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		SpeedTestBean emp = service.editSpeedTestDeviceMaster(ID);
		m.addObject("speedTestMasterId", ID);
		m.addObject("speedTestMaster", emp);
//		m.addObject("companyOptions", service.getCompanyMap());
		m.setViewName("SpeedTestEditMaster");
		return m;
	}

	@RequestMapping(value = "/updateSpeedTestDeviceMaster")
	public void updateSpeedTestDeviceMaster(@RequestParam("device_IP") String device_IP,
			@RequestParam("discription") String discription, @RequestParam("id") long id, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateSpeedTestDeviceMaster " + device_IP + discription + id);
			String result = service.updateSpeedTestDeviceMaster(device_IP, discription, id);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	// Syslog Monitoring
	@RequestMapping("/syslogMonitoring")
	public String syslogMonitoring() {
		return "Syslog";
	}

	// Availability Parameter
	@RequestMapping("/availabilityParameter")
	public String addEmailConfigForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called availabilityParameter");

		m.addAttribute("availabilityParameter", new AvailabilityParameterBean());

		return "availabilityParameter";
	}

	@RequestMapping(value = "/addAvailabilityParameter", method = RequestMethod.POST)
	public void addAvailabilityParameter(
			@RequestParam("fromTime") @DateTimeFormat(pattern = "HH:mm") LocalTime fromTime,
			@RequestParam("toTime") @DateTimeFormat(pattern = "HH:mm") LocalTime toTime, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			String result = service.addAvailabilityParameter(fromTime, toTime);
			out.print(result);
		} catch (IOException e) {
			System.out.println("IOException:" + e);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/viewAvailabilityParameter", method = RequestMethod.GET)
	public JSONArray viewAvailabilityParameter(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.viewAvailabilityParameter();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	@RequestMapping("/configTemplate")
	public String configurationTemplate(Model m) {
		m.addAttribute("fetchConfigTemplateData", new ConfigurationTemplateBean());
		return "ConfigurationTemplate";
	}

	@RequestMapping(value = "/addConfigurationTemplate", method = RequestMethod.POST)
	public void addConfigurationTemplate(@RequestParam("templateName") String templateName,
			@RequestParam("command") String command, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String result = service.addConfigurationTemplateService(templateName, command);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/viewConfigurationTemplate", method = RequestMethod.GET)
	public JSONArray viewConfigurationTemplate(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.viewConfigurationTemplateService();
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/deleteConfigurationTemplate", method = RequestMethod.POST)
	public void deleteConfigurationTemplate(@RequestParam("ID") long ID, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String result = service.deleteConfigurationTemplateService(ID);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/editConfigurationTemplate/{ID}")
	public ModelAndView editConfigurationTemplate(@PathVariable Long ID, ModelAndView m) {
		System.out.println("Edit controller called");
		ConfigurationTemplateBean configData = service.ConfigurationTemplateBeanService(ID);
		System.out.println("response:" + configData.getID());
		m.addObject("ID", ID);
		m.addObject("fetchConfigTemplateData", configData);
		m.setViewName("ConfigurationTemplate");
		return m;
	}

	@RequestMapping(value = "/updateConfigurationTemplate", method = RequestMethod.POST)
	public void updateConfigurationTemplate(@RequestParam("ID") Long ID,
			@RequestParam("templateName") String templateName, @RequestParam("command") String command,
			HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called update Configuration Template");
			String result = service.updateConfigurationTemplateService(ID, templateName, command);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/viewSheduleConfiguration", method = RequestMethod.GET)
	public JSONArray viewSheduleConfiguration(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.viewSheduleConfigurationSevice();
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/deleteSheduleConfiguration", method = RequestMethod.POST)
	public void deleteSheduleConfiguration(@RequestParam("ID") long ID, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String result = service.deleteSheduleConfigurationService(ID);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/updateScheduleConfiguration", method = RequestMethod.POST)
	public void updateScheduleConfiguration(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller addConfigBackup");

		PrintWriter out = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			String datepickerString = req.getParameter("datepicker");
			String backupPath = req.getParameter("backuppath");
			String runConfig = req.getParameter("runConfig");
			String startupConfig = req.getParameter("startupConfig");
			String backup = req.getParameter("backup");
			String ID1 = req.getParameter("ID");
			long ID = Long.parseLong(ID1);
			String group_name = req.getParameter("group_name");
			String ip_address = req.getParameter("ipAddressCheck");
			String configType = req.getParameter("configType");
			String backupSchedule = req.getParameter("backupSchedule");
			/* String datepicker = req.getParameter("datepicker"); */
			Timestamp datepicker1 = new Timestamp(formatter.parse(datepickerString).getTime());
			String datepicker = datepicker1.toString();
			String result = service.updateScheduleConfigurationService(ID, backupPath, runConfig, startupConfig, backup,
					group_name, ip_address, configType, backupSchedule, datepicker);
			System.out.println("final response = " + result);
			out = res.getWriter();
			out.print(result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping(value = "/editScheduleConfiguration/{ID}")
	public String editScheduleConfiguration(Model m, @PathVariable Long ID) {
//		System.out.println("Edit controller called");
		ScheduleConfigurationBean configData = service.editScheduleConfigurationService(ID);
//		System.out.println("response:" + configData.getID());
//		m.addObject("fetchscheduleConfig", configData);
////		m.addObject("ID", ID);
//		m.addObject("groupName", dao.getGroupMap());
//		m.add
//		m.setViewName("ConfigBackup");
//		return m;
//		
////		 m.addAttribute("groupName", dao.getGroupMap());
////		return "ScheduleConfigurationLog";
		m.addAttribute("fetchscheduleConfig", configData);
		m.addAttribute("groupName", dao.getGroupMap());
		return "ConfigBackup";
	}

	@RequestMapping("/ScheduleConfigurationLog")
	public String ScheduleConfigurationLog(Model m) {
		m.addAttribute("groupName", dao.getGroupMap());
		return "ScheduleConfigurationLog";
	}

	@RequestMapping(value = "/scheduleConfigReport", method = RequestMethod.POST)
	public ModelAndView scheduleconfigReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller pushConfiReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_addresss = req.getParameterValues("ipAddressCheck");
		List<String> ip_address = Arrays.asList(ip_addresss);

		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<ScheduleConfigurationBean> pushData = service.scheduleconfigReport(from_date, to_date, ip_address);
		System.out.println("Report Data:" + pushData);
		ModelAndView m = new ModelAndView();
		m.addObject("scheduleFetchData", pushData);
		m.setViewName("scheduleConfigurationLogView");
		return m;

	}

	@RequestMapping("/viewInterfaceParameter")
	public String viewInterfaceParameter(Model m) {
		return "viewInterfaceParameter";
	}

	@RequestMapping(value = "/jsonInterfaceParameter", method = RequestMethod.GET)
	public JSONArray jsonInterfaceParameter(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.jsonInterfaceParameterService(userScopeData);
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/deleteInterfaceParameter", method = RequestMethod.POST)
	public void deleteInterfaceParameter(@RequestParam("ID") long ID, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String result = service.deleteInterfaceParameterService(ID);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping("/smsConfiguration")
	public String smsConfiguration(Model m) {
		return "smsConfiguration";
	}

	@RequestMapping(value = "/addSMSConfiguration", method = RequestMethod.POST)
	public void addSMSConfiguration(@RequestParam("smsProvider") String smsProvider,
			@RequestParam("smsUrl") String smsUrl, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String result = service.addSMSConfigurationService(smsProvider, smsUrl, username, password);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/viewSMSConfiguration", method = RequestMethod.GET)
	public JSONArray viewSMSConfiguration(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.viewSMSConfigurationService();
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/deleteSMSConfiguration", method = RequestMethod.POST)
	public void deleteSMSConfiguration(@RequestParam("ID") long ID, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String result = service.deleteSMSConfigurationService(ID);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@RequestMapping(value = "/searchByIp")
	public ModelAndView searchByIp(HttpSession session, HttpServletRequest req) {
		String ip = req.getParameter("searchNodeData");
		System.out.println("ip_list :" + ip);
		ModelAndView m = new ModelAndView();
		m.addObject("viewAddNode", service.searchByIp(ip));
		m.setViewName("viewNodeBySearch");
		return m;
	}

	@RequestMapping(value = "/searchByName")
	public ModelAndView searchByName(HttpSession session, HttpServletRequest req) {
		String name = req.getParameter("searchNodeData");
		ModelAndView m = new ModelAndView();
		m.addObject("viewAddNode", service.searchByName(name));

		m.setViewName("viewNodeBySearch");
		return m;
	}

	@RequestMapping(value = "/searchByGroupName")
	public ModelAndView searchByGroupName(HttpSession session, HttpServletRequest req) {
		String groupName = req.getParameter("searchNodeData");
		ModelAndView m = new ModelAndView();
		m.addObject("viewAddNode", service.searchByGroupName(groupName));

		m.setViewName("viewNodeBySearch");
		return m;
	}

	@RequestMapping(value = "/searchByDeviceType")
	public ModelAndView searchByDeviceType(HttpSession session, HttpServletRequest req) {
		String deviceType = req.getParameter("searchNodeData");
		ModelAndView m = new ModelAndView();
		m.addObject("viewAddNode", service.searchByDeviceType(deviceType));

		m.setViewName("viewNodeBySearch");
		return m;
	}

	@RequestMapping(value = "/mapTopologyData", method = RequestMethod.GET)
	public JSONArray mapTopologyData(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.mapTopologyData();
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

}
