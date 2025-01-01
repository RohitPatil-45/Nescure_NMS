package npm.report.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.xdevapi.JsonArray;

import npm.admin.beans.CpuThresholdHealthReportBean;
import npm.admin.beans.InterfaceAvailabilityBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.beans.LatencyThresholdReportBean;
import npm.admin.beans.MemoryThresholdReportBean;
import npm.admin.beans.NodeAvailabilityBean;
import npm.admin.beans.NodeHealthHistoryReportBean;
import npm.admin.beans.NodeStatusReportBean;
import npm.admin.beans.NwIpScanBean;
import npm.admin.beans.UserLoginHistoryBean;
import npm.admin.beans.UserMasterBean;
import npm.admin.dao.MasterDao;
import npm.model.NodeHealthHistory;
import npm.model.NodeStatusHistory;
import npm.report.dao.NodeReportDao;
import npm.report.service.NodeReportService;

@Controller
@RequestMapping("/nodeReport")
public class NodeReportController {

	@Autowired
	MasterDao dao;

	@Autowired
	NodeReportDao NodeRdao;

	@Autowired
	NodeReportService service;

	@RequestMapping("/deviceStatus")
	public ModelAndView DeviceReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called deviceStatus report");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("DeviceStatusReport");
		return m;
	}

	@RequestMapping("/userReport")
	public ModelAndView userReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called userReport report");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
//		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("userHistoryReport");
		return m;
	}

	@RequestMapping("/latencyHistoryReportForm")
	public ModelAndView latencyHistoryReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called latencyHistoryReportForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LatencyHistoryReport");
		return m;
	}

	// Latency Status History report (working Hours Report)
	@RequestMapping("/latencyStatusHistoryReportForm")
	public ModelAndView latencyStatusHistoryReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called latencyStatusHistoryReportForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LatencyStatusHistoryReport");
		return m;
	}

	@RequestMapping("/latencyStatusHistoryReportFormFilter")
	public ModelAndView latencyStatusHistoryReportFormFilter(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called latencyStatusHistoryReportForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LatencyStatusHistoryReportFilter");
		return m;
	}

	// Average Latency Status History report (working Hours Report)
	@RequestMapping("/avgLatencyStatusHistoryReportForm")
	public ModelAndView avgLatencyStatusHistoryReportForm(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView m = new ModelAndView();
		m.setViewName("AverageLatencyStatusHistoryReport");
		return m;
	}

	@RequestMapping("/avgLatencyStatusHistoryReport")
	public ModelAndView avgLatencyStatusHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");

		String status = req.getParameter("satusselect");

		JSONArray avgData = service.avgLatencyStatusHistoryReport(from_date, to_date,status);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", avgData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("AverageLatencyStatusHistoryReportView");
		return m;

	}

	// End Average Latency Status History report (working Hours Report)

	@RequestMapping("/latencyThresholdReportForm")
	public ModelAndView latencyThresholdReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called latencyThresholdReportForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LatencyThresholdReport");
		return m;
	}

	@RequestMapping("/nodeAvailabilityForm")
	public ModelAndView nodeAvailabilityForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeAvailabilityForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("NodeAvailability");
		return m;
	}

	@RequestMapping("/nodeHealthHistoryForm")
	public ModelAndView nodeHealthHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeHealthHistoryForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("nodeHealthHistoryReport");
		return m;
	}

	@RequestMapping("/cpuThresholdHistoryForm")
	public ModelAndView cpuThresholdHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeHealthHistoryForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("CPUThresholdHistoryReport");
		return m;
	}

	@RequestMapping("/memoryThresholdHistoryForm")
	public ModelAndView memoryThresholdHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeHealthHistoryForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("MemoryThresholdHistoryReport");
		return m;
	}

	@RequestMapping("/getGroupDeviceLists")
	public void getGroupDeviceLists(HttpServletRequest req, HttpServletResponse res, HttpSession session,
			@RequestParam("groupName") String group_name) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller getGroupDeviceLists" + group_name);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(NodeRdao.getGroupDeviceLists(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping("/getInterFaceNameLists")
	public void getInterFaceNameLists(HttpServletRequest req, HttpServletResponse res, HttpSession session,
			@RequestParam("ip") String ip) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(NodeRdao.getInterFaceNameLists(ip, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping("/getGroupDeviceDetails")
	public void getGroupDeviceDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session,
			@RequestParam("groupName") String group_name) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller getGroupDeviceDetails" + group_name);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(NodeRdao.getGroupDeviceDetails(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/nodeStatusReport")
	public ModelAndView nodeStatusReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeStatusReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
//		String ip = "192.168.0.230";
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<NodeStatusReportBean> historyData = service.NodeStatusReport(from_date, to_date, list);
		System.out.println("Report Data:" + historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("DeviceStatusReportView");
		return m;

	}

	@RequestMapping("/userLogReportData")
	public ModelAndView userLogReportData(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller userLogReportData report");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
//		String[] ip_address = req.getParameterValues("ipAddressCheck");
//		String ip = "192.168.0.230";
//		List<String> list = Arrays.asList(ip_address);

//		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<UserLoginHistoryBean> userlogreportdata = service.userLogReportData(from_date, to_date);
		System.out.println("Report Data:" + userlogreportdata);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", userlogreportdata);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("userLogReportView");
		return m;

	}

	@RequestMapping("/latencyHistoryReport")
	public ModelAndView latencyHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeStatusReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<LatencyHistoryReportBean> historyData = service.latencyHistoryReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LatencyHistoryReportView");
		return m;

	}

	// Latency status history report data
	@RequestMapping("/latencyStatusHistoryReport")
	public ModelAndView latencyStatusHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyStatusHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		JSONArray historyData = service.latencyStatusHistoryReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LatencyStatusHistoryReportView");
		return m;

	}

	@RequestMapping("/latencyStatusHistoryReportFilter")
	public ModelAndView latencyStatusHistoryReportFilter(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyStatusHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String status = req.getParameter("satusselect");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		System.out.println("status:" + status);

		JSONArray historyData = service.latencyStatusHistoryReportFilter(from_date, to_date, list, status);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LatencyStatusHistoryReportView");
		return m;

	}

	@RequestMapping("/latencyThresholdReport")
	public ModelAndView latencyThresholdReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyThresholdReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<LatencyThresholdReportBean> historyData = service.latencyThresholdReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LatencyThresholdReportView");
		return m;

	}

	@RequestMapping("/nodeAvailabilityReport")
	public ModelAndView nodeAvailabilityReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyThresholdReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<NodeAvailabilityBean> historyData = service.nodeAvailabilityReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("NodeAvailabilityView");
		return m;

	}

	@RequestMapping("/nodeHealthHistoryReport")
	public ModelAndView nodeHealthHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeHealthHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<NodeHealthHistoryReportBean> historyData = service.nodeHealthHistoryReport(from_date, to_date, list);
		System.out.println("Report Data:" + historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("nodeHealthHistoryReportView");
		return m;

	}

	@RequestMapping("/cpuThresholdHistoryReport")
	public ModelAndView cpuThresholdHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller cpuThresholdHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<CpuThresholdHealthReportBean> historyData = service.cpuThresholdHistoryReport(from_date, to_date, list);
		System.out.println("Report Data:" + historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("CPUThresholdHistoryReportView");
		return m;

	}

	@RequestMapping("/memoryThresholdHistoryReport")
	public ModelAndView memoryThresholdHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller memoryThresholdHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<MemoryThresholdReportBean> historyData = service.memoryThresholdHistoryReport(from_date, to_date, list);
		System.out.println("Report Data:" + historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("MemoryThresholdHistoryReportView");
		return m;

	}

	@RequestMapping(value = "/nodeHealthMonitoringView", method = RequestMethod.GET)
	public ModelAndView nodeHealthMonitoringView() {
		ModelAndView m = new ModelAndView();
		m.addObject("node", service.nodeHealthMonitoringView());
		m.setViewName("NodeHealthMonitoringView");
		return m;
	}

	@RequestMapping(value = "/nodeMonitoringView", method = RequestMethod.GET)
	public ModelAndView nodeMonitoringView() {
		ModelAndView m = new ModelAndView();
		m.addObject("node", service.nodeMonitoringView());
		m.setViewName("NodeMonitoringView");
		return m;
	}

	@RequestMapping(value = "/interfaceMonitoringView", method = RequestMethod.GET)
	public ModelAndView interfaceMonitoringView(HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		ModelAndView m = new ModelAndView();
		m.addObject("node", service.interfaceMonitoringView(userScopeData));
		m.setViewName("InterfaceMonitoringView");
		return m;
	}

	@RequestMapping("/latencyHisotryGraphForm")
	public ModelAndView latencyHisotryGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called LatencyHistoryGraphForm");

		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LatencyHistoryGraphForm");
		return m;
	}

	@RequestMapping("/latencyGraphForm")
	public ModelAndView latencyGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called latencyGraphForm");

		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LatencyGraph");
		return m;
	}

	// Avergae Latency Graph
	@RequestMapping("/latencyHistoryGraph")
	public void latencyHistoryGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyHistoryGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.latencyHistoricalData(from_date, to_date, ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	// Latency Graph
	@RequestMapping("/plotLatencyGraph")
	public void plotLatencyGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyHistoryGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.plotLatencyGraph(from_date, to_date, ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	// Interface Bandwidth History graph

	@RequestMapping("/interfaceBandwidthHistoryGraphForm")
	public ModelAndView interfaceBandwidthHistoryGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceBandwidthHistoryGraphForm");

		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("InterfaceBandwidthHistoryGraphForm");
		return m;
	}

	@RequestMapping("/interfaceBandwidthHistoryGraph")
	public void interfaceBandwidthHistoryGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceBandwidthHistoryGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		String interface_name = req.getParameter("interface_name");
		System.out.println("interface name = " + interface_name);
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.interfaceBWHistoryData(from_date, to_date, ip_address, interface_name));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}

	}

	// Node Health History Graph

	@RequestMapping("/nodeHealthHistoryGraphForm")
	public ModelAndView nodeHealthHistoryGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeHealthHistoryGraphForm");

		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("NodeHealthHistoryGraphForm");
		return m;
	}

	@RequestMapping("/nodeHealthHistoryGraph")
	public void nodeHealthHistoryGraph(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller nodeHealthHistoryGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeHealthHistoryData(from_date, to_date, ip_address, userScopeData));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}

	}

	// Node Availability
	@RequestMapping("/nodeAvailabilityGraphForm")
	public ModelAndView nodeAvailabilityGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeAvailabilityGraphForm");

		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("NodeAvailabilityGraphForm");
		return m;
	}

	@RequestMapping("/nodeAvailabilityGraph")
	public void nodeAvailabilityGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeAvailabilityGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeAvailabilityData(from_date, to_date, ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}

	}

	// Interface Availability Graph

	@RequestMapping("/interfaceAvailabilityGraphForm")
	public ModelAndView interfaceAvailabilityGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called InterfaceAvailabilityGraphForm");

		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("InterfaceAvailabilityGraphForm");
		return m;
	}

	@RequestMapping("/InterfaceIpAssign")
	public ModelAndView InterfaceIpAssign(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called InterfaceAvailabilityGraphForm");

		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("InterfaceIpAssign");
		return m;
	}

	@RequestMapping("/interfaceAvailabilityGraph")
	public String interfaceAvailabilityGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeAvailabilityGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		String interface_name = req.getParameter("interface_name");
		System.out.println("interface name = " + interface_name);
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.interfaceAvailabilityData(from_date, to_date, ip_address, interface_name));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}

		return null;

	}

	@RequestMapping("/interfaceipassignhere")
	public String interfaceipassignhere(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceipassignhere");
		String Interface_ip = req.getParameter("Interface_ip");
		String ip_address = req.getParameter("ip_address");
		String interface_name = req.getParameter("interface_name");
		System.out.println("interface name = " + interface_name);
		System.out.println("ip_address :" + ip_address);
		System.out.println("Interface_ip :" + Interface_ip);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.interfaceipassignhere(Interface_ip, ip_address, interface_name));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}

		return null;

	}

	// Device Alert Log

	@RequestMapping("/deviceAlertLog")
	public ModelAndView viewDeviceAlertLog() {

		ModelAndView m = new ModelAndView();
		m.addObject("deviceALertLog", service.viewDeviceAlertLog());
		m.setViewName("viewDeviceAlertlog");

		return m;

	}

	// SLA Report
	@RequestMapping("/slaReportForm")
	public String slaReportForm() {

		return "SLAReportForm";
	}

	@RequestMapping("/slaReport")
	public ModelAndView slaReport(HttpServletRequest req) {
		ModelAndView m = new ModelAndView();
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String yearlyCost = req.getParameter("yearlyCost");
		m.addObject("slaReportData", service.getSlaReport(from_date, to_date, yearlyCost));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("SLAReportView");
		return m;

	}

	// Average Uptime Report
	@RequestMapping("/avgUptimeForm")
	public String avgUptimeForm() {

		return "avgUptimeReportForm";
	}

	@RequestMapping("/avgUptimeReport")
	public ModelAndView avgUptimeReport(HttpServletRequest req) {
		ModelAndView m = new ModelAndView();
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		m.addObject("avgUptimeReportData", service.getAvgUptimeReport(from_date, to_date));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("avgUptimeReport");
		return m;

	}

	// Node Connected Device
	@RequestMapping("/connectedDevices")
	public ModelAndView connectedDevices(HttpServletRequest req) {
		ModelAndView m = new ModelAndView();
		m.addObject("connectedDevices", service.getConnectedDevices());
		m.setViewName("nodeConnectedDevices");
		return m;
	}

	@RequestMapping("/connectedDevicesforIP")
	public void connectedDevicesforIP(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView m = new ModelAndView();
		String ip = req.getParameter("ip_address");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.connectedDevicesforIP(ip));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}

	}

	@RequestMapping("/avgAvailabilityReportForm")
	public String availabilityReport() {
		return "AvailabilityReport";
	}

	@RequestMapping(value = "/getAverageAvailabilityReport", method = RequestMethod.GET)
	public ModelAndView getAverageAvailabilityReport(HttpServletRequest req) {
		ModelAndView m = new ModelAndView();
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		m.addObject("avgAvailabilityReport", service.getAverageAvailabilityReport(from_date, to_date));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("AvailabilityReportData");
		return m;
	}

	@RequestMapping("/availabilityReportForm")
	public String availabilityReportForm() {
		return "AvailabilityReportDateWise";
	}

	@RequestMapping(value = "/getAvailabilityReportDateWise", method = RequestMethod.GET)
	public ModelAndView getAvailabilityReportDateWise(HttpServletRequest req) {
		ModelAndView m = new ModelAndView();
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		m.addObject("avgAvailabilityReport", service.getAvailabilityReportDateWise(from_date, to_date));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("AvailabilityReportDateWiseData");
		return m;
	}
	// End Working Hours Availability Report

	// Average latency report
	@RequestMapping("/avarageLatencyReportForm")
	public ModelAndView avarageLatencyReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called avarageLatencyReport");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("avarageLatencyReport");
		return m;
	}

	@RequestMapping("/avarageLatencyReport")
	public ModelAndView avarageLatencyReport(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller nodeStatusReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//		JSONArray historyData = service.avarageLatencyReport(from_date, to_date, list,userScopeData);
		ModelAndView m = new ModelAndView();
//		m.addObject("statusReportData", historyData);
		m.addObject("statusReportData", service.avarageLatencyReport(from_date, to_date, list, userScopeData));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("viewAvarageLatencyReport");
		return m;

	}
	/////////////////////////////////////////////////////////////////////////////////////

	// averageHealthReport report
	@RequestMapping("/averageHealthReportForm")
	public ModelAndView averageHealthReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called avarageLatencyReport");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("averageHealthReport");
		return m;
	}

	@RequestMapping("/averageHealthReport")
	public ModelAndView averageHealthReport(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller nodeStatusReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", service.averageHealthReport(from_date, to_date, list, userScopeData));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("viewAverageHealthReport");
		return m;

	}

	// Node Availability Average
	@RequestMapping("/nodeAvailabilityAverageGraphForm")
	public ModelAndView nodeAvailabilityAverageGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeAvailabilityAverageGraphForm");

		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("barGraphNodeAvailablity");
		return m;
	}

	@RequestMapping("/nodeAvailabilityAverageGraph")
	public void nodeAvailabilityAverageGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeAvailabilityAverageGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] list = req.getParameterValues("ipAddressCheck");
		List<String> ip_address = Arrays.asList(list);
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeAvailabilityAverageGraph(from_date, to_date, ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}

	}

	// Interface Availability Avereges Graph

	@RequestMapping("/interfaceAvailabilityAverageGraphForm")
	public ModelAndView interfaceAvailabilityAverageGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceAvailabilityAverageGraphForm");

		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("barGraphInterfaceAvailablity");
		return m;
	}

	@RequestMapping("/interfaceAvailabilityAverageGraph")
	public String interfaceAvailabilityAverageGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceAvailabilityAverageGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] list = req.getParameterValues("ipAddressCheck");
		List<String> ip_address = Arrays.asList(list);
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.interfaceAvailabilityAverageGraph(from_date, to_date, ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}

		return null;

	}

	@RequestMapping("/showLatencyReport")
	public ModelAndView showLatencyReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller showLatencyReport by ip");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);

		List<LatencyHistoryReportBean> historyData = service.showLatencyReport(ip_address, fromDate, toDate);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("LatencyHistoryReportView");
		return m;

	}

	@RequestMapping("/showNodeStatusReport")
	public ModelAndView showNodeStatusReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeStatusReport");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);

		List<NodeStatusReportBean> historyData = service.showNodeStatusReport(fromDate, toDate, ip_address);
		System.out.println("Report Data:" + historyData.size());
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("DeviceStatusReportView");
		return m;

	}

	@RequestMapping("/showLatencyThreshold")
	public ModelAndView showLatencyThreshold(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyThresholdReport");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);

		List<LatencyThresholdReportBean> historyData = service.showLatencyThreshold(fromDate, toDate, ip_address);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("LatencyThresholdReportView");
		return m;

	}

	@RequestMapping("/showAvailabilityReport")
	public ModelAndView showAvailabilityReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyThresholdReport");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);

		List<NodeAvailabilityBean> historyData = service.showAvailabilityReport(fromDate, toDate, ip_address);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("NodeAvailabilityView");
		return m;

	}

	@RequestMapping("/showCPUThreshold")
	public ModelAndView showCPUThreshold(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller cpuThresholdHistoryReport");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);

		List<CpuThresholdHealthReportBean> historyData = service.showCPUThreshold(fromDate, toDate, ip_address);
		System.out.println("Report Data:" + historyData.size());
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("CPUThresholdHistoryReportView");
		return m;

	}

	@RequestMapping("/showMemoryThreshold")
	public ModelAndView showMemoryThreshold(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller memoryThresholdHistoryReport");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);

		List<MemoryThresholdReportBean> historyData = service.showMemoryThreshold(fromDate, toDate, ip_address);
		System.out.println("Report Data:" + historyData.size());
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("MemoryThresholdHistoryReportView");
		return m;

	}

	@RequestMapping("/showCPUReport")
	public ModelAndView showCPUReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller showCPUReport");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);

		List<NodeHealthHistoryReportBean> historyData = service.showCPUReport(fromDate, toDate, ip_address);
		System.out.println("Report Data:" + historyData.size());
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("nodeHealthHistoryCpuReportView");
		return m;

	}

	@RequestMapping("/showMemoryReport")
	public ModelAndView showMemoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller showMemoryReport");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);

		List<NodeHealthHistoryReportBean> historyData = service.showMemoryReport(fromDate, toDate, ip_address);
		System.out.println("Report Data:" + historyData.size());
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("nodeHealthHistoryMemoryReportView");
		return m;

	}

	@RequestMapping("/showLatencyGraph")
	public ModelAndView showLatencyGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller showLatencyGraph");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);
		ModelAndView m = new ModelAndView();
		m.addObject("statusGraphData", service.showLatencyGraph(fromDate, toDate, ip_address));
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("showLatencyGraphData");
		return m;
	}

	@RequestMapping("/showAvailabilityGraph")
	public ModelAndView showAvailabilityGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller showAvailabilityGraph");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);
		ModelAndView m = new ModelAndView();
		m.addObject("availabilityGraphData", service.showAvailabilityGraph(fromDate, toDate, ip_address));
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("showAvailabilityGraphData");
		return m;
	}

	@RequestMapping("/showCPUGraph")
	public ModelAndView showCPUGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller showAvailabilityGraph");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);
		ModelAndView m = new ModelAndView();
		m.addObject("cpuGraphData", service.showCPUGraph(fromDate, toDate, ip_address));
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("showCPUGraphData");
		return m;
	}

	@RequestMapping("/showMemoryGraph")
	public ModelAndView showMemoryGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller showAvailabilityGraph");
		String fromDate = req.getParameter("from_date");
		String toDate = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + toDate);
		System.out.println("from date:" + fromDate);
		ModelAndView m = new ModelAndView();
		m.addObject("memoryGraphData", service.showMemoryGraph(fromDate, toDate, ip_address));
		m.addObject("fdate", fromDate);
		m.addObject("tdate", toDate);
		m.setViewName("showMemoryGraphData");
		return m;
	}

	@RequestMapping("/interfaceBandwidthGraph")
	public ModelAndView interfaceBandwidthGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller showAvailabilityGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");
		String interfaceName = req.getParameter("intName");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		System.out.println("Interface Name:" + interfaceName);
		ModelAndView m = new ModelAndView();
		m.addObject("interfaceBWGraph", service.interfaceBandwidthGraph(from_date, to_date, ip_address, interfaceName));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.addObject("ipData", ip_address);
		m.addObject("intName", interfaceName);
		m.setViewName("interfaceBandwidthGraphData");
		return m;
	}

	@RequestMapping("/interfaceUptimeGraph")
	public ModelAndView interfaceUptimeGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller showAvailabilityGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");
		String interfaceName = req.getParameter("intName");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		System.out.println("Interface Name:" + interfaceName);
		ModelAndView m = new ModelAndView();
		m.addObject("interfaceUPGraph", service.interfaceUptimeGraph(from_date, to_date, ip_address, interfaceName));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.addObject("ipData", ip_address);
		m.addObject("intName", interfaceName);
		m.setViewName("interfaceUptimeGraphData");
		return m;
	}

	// Windows Event Report
	@RequestMapping("/windowsEvent")
	public ModelAndView windowsEventReport(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("WindowsEventReport");
		return m;
	}

	@RequestMapping("/getWindowsEventReport")
	public void getWindowsEventReport(HttpServletRequest req, HttpServletResponse res) {
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ip_address");
		List<String> list = Arrays.asList(ip_address);

		JSONArray finalArray = service.getWindowsEventReport(from_date, to_date, list);
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
	// End Windows Event Report

	// Drive Threshold Report
	@RequestMapping("/driveThreshold")
	public ModelAndView driveThreshold(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("DriveThreshold");
		return m;
	}

	@RequestMapping("/getDriveThresholdReport")
	public void getDriveThresholdReport(HttpServletRequest req, HttpServletResponse res) {
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ip_address");
		List<String> list = Arrays.asList(ip_address);

		JSONArray finalArray = service.getDriveThresholdReport(from_date, to_date, list);
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
	// End Drive Threshold Report

	// Service Report
	@RequestMapping("/serviceReport")
	public ModelAndView serviceReport(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("ServiceReport");
		return m;
	}

	@RequestMapping("/getServiceReport")
	public void getServiceReport(HttpServletRequest req, HttpServletResponse res) {
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ip_address");
		List<String> list = Arrays.asList(ip_address);

		JSONArray finalArray = service.getServiceReport(from_date, to_date, list);
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
	// End Service Report

	// Windows Hardware Inventory
	@RequestMapping(value = "/windowsHardwareInventory", method = RequestMethod.GET)
	public ModelAndView windowsHardwareInventory() {
		ModelAndView m = new ModelAndView();
		m.addObject("winInventory", service.windowsHardwareInventory());
		m.setViewName("windowsHardwareInventory");
		return m;
	}
	// End Windows Hardware Inventory

	// Linux Hardware Inventory
	@RequestMapping(value = "/linuxHardwareInventory", method = RequestMethod.GET)
	public ModelAndView linuxHardwareInventory() {
		ModelAndView m = new ModelAndView();
		m.addObject("linuxInventory", service.linuxHardwareInventory());
		m.setViewName("LinuxHardwareInventory");
		return m;
	}
	// End Linux Hardware Inventory

	// Linux Software Inventory
	@RequestMapping(value = "/linuxSoftwareInventory", method = RequestMethod.GET)
	public ModelAndView linuxSoftwareInventory() {
		ModelAndView m = new ModelAndView();
		m.addObject("linuxInventory", service.linuxSoftwareInventory());
		m.setViewName("LinuxSoftwareInventory");
		return m;
	}
	// End Linux Software Inventory

	// Linux Services
	@RequestMapping(value = "/linuxServices", method = RequestMethod.GET)
	public ModelAndView linuxServices() {
		ModelAndView m = new ModelAndView();
		m.addObject("linuxServices", service.linuxServices());
		m.setViewName("LinuxServices");
		return m;
	}
	// End Linux Services

	// Windows Services
	@RequestMapping(value = "/windowsServices", method = RequestMethod.GET)
	public ModelAndView windowsServices() {
		ModelAndView m = new ModelAndView();
		m.addObject("windowsServices", service.windowsServices());
		m.setViewName("WindowsServices");
		return m;
	}
	// End Windows Services

	@RequestMapping(value = "/getinterfaceassigndata", method = RequestMethod.GET)
	public JSONArray getinterfaceassigndata(HttpServletResponse response) {
		System.out.println("inside getinterfaceassigndata");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.getinterfaceassigndata();
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	// syslog Report
	@RequestMapping("/syslogReportForm")
	public String syslogReportForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called syslogReportForm");

		m.addAttribute("ip", service.getNodeIP());

		return "SyslogReportForm";
	}

	@RequestMapping("/syslogReport")
	public ModelAndView syslogReport(HttpServletRequest req) {
		ModelAndView m = new ModelAndView();
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip = req.getParameter("ip");

		System.out.println(from_date + " = " + to_date + " = " + ip);
		m.addObject("syslogReportData", service.getSyslogReport(from_date, to_date, ip));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("SyslogReportData");
		return m;

	}

	// Working Hours Availability Report
	@RequestMapping("/workingHoursAvailabilityReportForm")
	public String workingHoursAvailabilityReportForm() {
		return "WorkingHoursAvailabilityReport";
	}

	@RequestMapping("/getWorkingHoursAvailabilityReport")
	public ModelAndView getWorkingHoursAvailabilityReport(HttpServletRequest req) {
		ModelAndView m = new ModelAndView();
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");

		System.out.println(from_date + " = " + to_date);
		m.addObject("availabilityReport", service.getWorkingHoursAvailabilityReport(from_date, to_date));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("WorkingHoursAvailabilityReportData");
		return m;

	}

	// SLA Report as per working hours
	@RequestMapping("/sla")
	public String sla() {

		return "workingHoursSLAReport";
	}

	@RequestMapping("/slaReportData")
	public ModelAndView slaReportData(HttpServletRequest req) {
		ModelAndView m = new ModelAndView();
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String yearlyCost = req.getParameter("yearlyCost");
		m.addObject("slaReportData", service.slaReportData(from_date, to_date, yearlyCost));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("workingHoursSLAReportData");
		return m;

	}

}
