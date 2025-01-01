package npm.report.controller;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import npm.admin.beans.BWThresholdHistoryBean;
import npm.admin.beans.InterfaceAvailabilityBean;
import npm.admin.beans.InterfaceBandwidthHistoryBean;
import npm.admin.beans.InterfaceCRCHistoryBean;
import npm.admin.beans.InterfaceStatusReportBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.beans.LinkAVGBandwidthReportBean;
import npm.admin.beans.LinkAvailabilityReportBean;
import npm.admin.beans.LinkBandwidthHistoryReporttBean;
import npm.admin.beans.LinkLatencyReportBean;
import npm.admin.beans.LinkLatencystatusreportBean;
import npm.admin.beans.LinkaverageLatencyReportBean;
import npm.admin.beans.NodeStatusReportBean;
import npm.admin.dao.MasterDao;
import npm.model.InterfaceMonitoring;
import npm.report.service.InterfaceReportService;
import npm.report.service.NodeReportService;

@Controller
@RequestMapping("/interfaceReport")
public class InterfaceReportController {
	@Autowired
	MasterDao dao;

	@Autowired
	InterfaceReportService service;

	@Autowired
	NodeReportService servicein;

	@RequestMapping("/getGroupInterfaceDetails")
	public void getGroupDeviceDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session,
			@RequestParam("groupName") String group_name) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller getGroupInterfaceDetails" + group_name);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getGroupInterfaceDetails(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getGroupInterfaceIPDetails")
	public void getGroupInterfaceIPDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session,
			@RequestParam("groupName") String group_name) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller getGroupInterfaceDetails" + group_name);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getGroupInterfaceIPDetails(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getGroupInterface")
	public void getGroupInterface(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("groupName") String group_name) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getGroupInterface(group_name));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/interfaceStatusReportForm")
	public ModelAndView DeviceReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceStatusReport");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("InterfaceStatusReportForm");
		return m;
	}

	@RequestMapping("/interfaceStatusReport")
	public ModelAndView nodeStatusReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceStatusReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<InterfaceStatusReportBean> historyData = service.InterfaceStatusReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("InterfaceStatusReportView");
		return m;

	}

	// Interface Bandwidth History

	@RequestMapping("/interfaceBandwidthHistoryForm")
	public ModelAndView interfaceBandwidthHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceBandwidthHistoryForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("interfaceBandwidthHistoryForm");
		return m;
	}

	@RequestMapping("/interfaceBandwidthHistoryReport")
	public ModelAndView InterfaceBandwidthHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceBandwidthHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//		List<InterfaceBandwidthHistoryBean> historyData = service.InterfaceBandwidthHistoryReport(from_date, to_date,list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", service.InterfaceBandwidthHistoryReport(from_date, to_date, list));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("interfaceBandwidthHistoryView");
		return m;

	}

	// Bandwidth Threshold History

	@RequestMapping("/bandwidthThresholdHistoryForm")
	public ModelAndView bandwidthThresholdHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called bandwidthThresholdHistoryForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("BandwidthThresholdHistoryForm");
		return m;
	}

	@RequestMapping("/latencyinterfaceReportForm")
	public ModelAndView latencyinterfaceReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called latencyinterfaceReportForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("latencyinterfaceReportForm");
		return m;
	}

	@RequestMapping("/LinkStatusreportForm")
	public ModelAndView LinkStatusreportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called latencyinterfaceReportForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LinkStatusreportForm");
		return m;
	}

	@RequestMapping("/LinkLatencyReportForm")
	public ModelAndView LinkLatencyReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called LinkLatencyReportForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LinkLatencyReportForm");
		return m;
	}

	@RequestMapping("/LinkLatencyReportFormFilter")
	public ModelAndView LinkLatencyReportFormFilter(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called LinkLatencyReportFormFilter");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LinkLatencyReportFormFilter");
		return m;
	}

	@RequestMapping("/LinkaverageLatencyReportForm")
	public ModelAndView LinkaverageLatencyReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called LinkaverageLatencyReportForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LinkaverageLatencyReportForm");
		return m;
	}

	@RequestMapping("/LinkAvailabilityReportForm")
	public ModelAndView LinkAvailabilityReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called LinkaverageLatencyReportForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LinkAvailabilityReportForm");
		return m;
	}

	@RequestMapping("/LinkBandwidthHistoryReportForm")
	public ModelAndView LinkBandwidthHistoryReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called LinkBandwidthHistoryReportForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LinkBandwidthHistoryReportForm");
		return m;
	}

	@RequestMapping("/LinkAVGBandwidthReportForm")
	public ModelAndView LinkAVGBandwidthReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called LinkAVGBandwidthReportForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LinkAVGBandwidthReportForm");
		return m;
	}

	@RequestMapping("/InterfaceSlaReportForm")
	public String InterfaceSlaReportForm() {
		return "InterfaceSlaReportForm";
	}

	@RequestMapping("/bandwidthThresholdHistoryReport")
	public ModelAndView bandwidthThresholdHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller bandwidthThresholdHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<BWThresholdHistoryBean> historyData = service.bandwidthThresholdHistoryReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("BandwidthThresholdHistoryView");
		return m;

	}

	@RequestMapping("/InterfaceLatencyHistoryReport")
	public ModelAndView InterfaceLatencyHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller bandwidthThresholdHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//		List<BWThresholdHistoryBean> historyData = service.bandwidthThresholdHistoryReport(from_date, to_date, list);
//		ModelAndView m = new ModelAndView();
//		m.addObject("InterfaceReportData", historyData);
//		m.addObject("fdate", from_date);
//		m.addObject("tdate", to_date);
//		m.setViewName("BandwidthThresholdHistoryView");
		List<LatencyHistoryReportBean> historyData = servicein.latencyHistoryReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LatencyHistoryReportView");
		return m;

	}

	@RequestMapping("/LinkLatencystatusreport")
	public ModelAndView LinkLatencystatusreport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller bandwidthThresholdHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//		List<BWThresholdHistoryBean> historyData = service.bandwidthThresholdHistoryReport(from_date, to_date, list);
//		ModelAndView m = new ModelAndView();
//		m.addObject("InterfaceReportData", historyData);
//		m.addObject("fdate", from_date);
//		m.addObject("tdate", to_date);
//		m.setViewName("BandwidthThresholdHistoryView");
//		List<LinkLatencystatusreportBean> historyData = servicein.LinkLatencystatusreport(from_date, to_date, list);
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		System.out.println(historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", servicein.LinkLatencystatusreport(from_date, to_date, list));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LinkLatencystatusreportView");
		return m;

	}

	@RequestMapping("/LinkLatencyReportView")
	public ModelAndView LinkLatencyReportView(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller LinkLatencyReportView");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//		List<BWThresholdHistoryBean> historyData = service.bandwidthThresholdHistoryReport(from_date, to_date, list);
//		ModelAndView m = new ModelAndView();
//		m.addObject("InterfaceReportData", historyData);
//		m.addObject("fdate", from_date);
//		m.addObject("tdate", to_date);
//		m.setViewName("BandwidthThresholdHistoryView");
//		List<LinkLatencyReportBean> historyData = servicein.LinkLatencyReportView(from_date, to_date, list);
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!2");
//		System.out.println(historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", servicein.LinkLatencyReportView(from_date, to_date, list));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LinkLatencyReportView");
		return m;

	}

	@RequestMapping("/LinkLatencyReportViewFilter")
	public ModelAndView LinkLatencyReportViewFilter(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller LinkLatencyReportViewFilter");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String status = req.getParameter("satusselect");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		System.out.println("status:" + status);

//		List<BWThresholdHistoryBean> historyData = service.bandwidthThresholdHistoryReport(from_date, to_date, list);
//		ModelAndView m = new ModelAndView();
//		m.addObject("InterfaceReportData", historyData);
//		m.addObject("fdate", from_date);
//		m.addObject("tdate", to_date);
//		m.setViewName("BandwidthThresholdHistoryView");
//		List<LinkLatencyReportBean> historyData = servicein.LinkLatencyReportView(from_date, to_date, list);
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!2");
//		System.out.println(historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", servicein.LinkLatencyReportViewFilter(from_date, to_date, list,status));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LinkLatencyReportView");
		return m;

	}

	@RequestMapping("/LinkaverageLatencyReportView")
	public ModelAndView LinkaverageLatencyReportView(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller LinkLatencyReportView");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String status = req.getParameter("satusselect");
		
		
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//		List<LinkaverageLatencyReportBean> historyData = servicein.LinkaverageLatencyReportView(from_date, to_date,
//				list);
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!2");
//		System.out.println(historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", servicein.LinkaverageLatencyReportView(from_date, to_date, list,status));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LinkaverageLatencyReportView");
		return m;

	}

	@RequestMapping("/LinkAvailabilityReportView")
	public ModelAndView LinkAvailabilityReportView(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller LinkAvailabilityReportView");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//		List<LinkAvailabilityReportBean> historyData = servicein.LinkAvailabilityReportView(from_date, to_date, list);
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!2");
//		System.out.println(historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", servicein.LinkAvailabilityReportView(from_date, to_date, list));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LinkAvailabilityReportView");
		return m;

	}

	@RequestMapping("/LinkBandwidthHistoryReportView")
	public ModelAndView LinkBandwidthHistoryReportView(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller LinkBandwidthHistoryReportView");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//		List<LinkBandwidthHistoryReporttBean> historyData = servicein.LinkBandwidthHistoryReportView(from_date, to_date,
//				list);
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!2");
//		System.out.println(historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", servicein.LinkBandwidthHistoryReportView(from_date, to_date, list));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LinkBandwidthHistoryReportView");
		return m;

	}

	@RequestMapping("/LinkAVGBandwidthReportView")
	public ModelAndView LinkAVGBandwidthReportView(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller LinkAVGBandwidthReportView");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//		List<LinkAVGBandwidthReportBean> historyData = servicein.LinkAVGBandwidthReportView(from_date, to_date, list);
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!2");
//		System.out.println(historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", servicein.LinkAVGBandwidthReportView(from_date, to_date, list));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("LinkAVGBandwidthReportView");
		return m;

	}

	@RequestMapping("/InterfaceSlaReportView")
	public ModelAndView InterfaceSlaReportView(HttpServletRequest req) {
		ModelAndView m = new ModelAndView();
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String yearlyCost = req.getParameter("yearlyCost");
		m.addObject("slaReportData", servicein.InterfaceSlaReportView(from_date, to_date, yearlyCost));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("InterfaceSlaReportView");
		return m;

	}

	// Interface CRC Value

	@RequestMapping("/interfaceCrcHistoryForm")
	public ModelAndView interfaceCrcHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceCrcHistoryForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("InterfaceCRCHistoryForm");
		return m;
	}

	@RequestMapping("/interfaceCrcHistoryReport")
	public ModelAndView interfaceCrcHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceBandwidthHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<InterfaceCRCHistoryBean> historyData = service.interfaceCrcHistoryReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("InterfaceCRCHistoryView");
		return m;

	}

	// Interface Availability

	@RequestMapping("/interfaceAvailabilityForm")
	public ModelAndView interfaceAvailabilityForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceAvailabilityForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("InterfaceAvailabilityForm");
		return m;
	}

	@RequestMapping("/interfaceAvailabilityReport")
	public ModelAndView interfaceAvailabilityReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceAvailabilityReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<InterfaceAvailabilityBean> historyData = service.interfaceAvailabilityReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("InterfaceAvailabilityView");
		return m;

	}

	// Get interface name by IP

	@RequestMapping("/getInterfaceNameByIP")
	public void getInterfaceNameByIP(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller getInterfaceNameByIP");
		String ip_address = req.getParameter("ip_address");

		System.out.println("ip_address :" + ip_address);

		String interface_name = service.getInterfaceNameByIP(ip_address);
		System.out.println("interface Name = " + interface_name);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(interface_name);
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}
//		ModelAndView m = new ModelAndView();
//		m.addObject("InterfaceReportData", historyData);
//		m.setViewName("InterfaceAvailabilityView");
//		return m;
	}
	// Group Summary
//	@RequestMapping("/groupSummary")
//	public ModelAndView groupSummary(HttpSession session) {
//		String userScopeData = (String) session.getAttribute("userScope");
//		JSONArray finalArray = service.groupSummary(userScopeData);
//		ModelAndView m = new ModelAndView();
//		m.addObject("groupSummary", finalArray);
////		m.setViewName("groupSummary");
//		m.setViewName("summary");
//		return m;
//	}

	@RequestMapping("/groupSummary")
	public void groupSummary(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.groupSummary(userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getTotalNodeSummaryDetails")
	public void getTotalNodeSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;

		try {
			out = res.getWriter();
			out.print(service.getTotalNodeSummaryDetails(group_name, userScopeData, req));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getupNodeSummaryDetails")
	public void getupNodeSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getupNodeSummaryDetails(group_name, userScopeData, req));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getdownNodeSummaryDetails")
	public void getdownNodeSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getdownNodeSummaryDetails(group_name, userScopeData, req));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getwarningNodeSummaryDetails")
	public void getwarningNodeSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getwarningNodeSummaryDetails(group_name, userScopeData, req));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	// InterFace Summary

//		@RequestMapping("/interfaceSummary")
//		public ModelAndView interfaceSummary(HttpSession session) {
//			String userScopeData = (String) session.getAttribute("userScope");
//			JSONArray finalArray = service.interfaceSummary(userScopeData);
//			ModelAndView m = new ModelAndView();
//			m.addObject("interfaceSummary", finalArray);
//			m.setViewName("interfaceSummary");
//			return m;
//		}

	@RequestMapping("/interfaceSummary")
	public void interfaceSummary(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.interfaceSummary(userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/interfaceSummaryGroupWise")
	public void interfaceSummaryGroupWise(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.interfaceSummaryGroupWise(userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getTotalInterfaceSummaryDetails")
	public void getTotalInterfaceSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getTotalInterfaceSummaryDetails(group_name, userScopeData, req));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getupInterfaceSummaryDetails")
	public void getupInterfaceSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getupInterfaceSummaryDetails(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getdownInterfaceSummaryDetails")
	public void getdownInterfaceSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getdownInterfaceSummaryDetails(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getwarningInterfaceSummaryDetails")
	public void getwarningInterfaceSummaryDetails(HttpServletRequest req, HttpServletResponse res,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getwarningInterfaceSummaryDetails(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getInterfaceDetailsWan4g")
	public void getInterfaceDetailsWan4g(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String status = req.getParameter("status");
		String group_name = req.getParameter("group_name");
		System.out.println("Status = " + status + "\nGroup Name = " + group_name);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getInterfaceDetailsWan4g(group_name, status, req));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	// Port Summary
	@RequestMapping("/portSummary")
	public ModelAndView portSummary(HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		JSONArray finalArray = service.portSummary(userScopeData);
		ModelAndView m = new ModelAndView();
		m.addObject("portSummary", finalArray);
		m.setViewName("PortSummary");
		return m;
	}

	@RequestMapping("/totalPortSummaryDetails")
	public void totalPortSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String ip = req.getParameter("ip_address");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getTotalPortSummaryDetails(ip, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/usedPortSummaryDetails")
	public void usedPortSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String ip = req.getParameter("ip_address");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getUsedPortSummaryDetails(ip, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/unUsedPortSummaryDetails")
	public void unUsedPortSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String ip = req.getParameter("ip_address");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getUnUsedPortSummaryDetails(ip, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/upPortSummaryDetails")
	public void upPortSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String ip = req.getParameter("ip_address");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getUpPortSummaryDetails(ip, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/downPortSummaryDetails")
	public void downPortSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String ip = req.getParameter("ip_address");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getDownPortSummaryDetails(ip, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/unutilizedPortSummaryDetails")
	public void unutilizedPortSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String ip = req.getParameter("ip_address");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.unutilizedPortSummaryDetails(ip, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	// averageBandwidthReport report
	@RequestMapping("/averageBandwidthReportForm")
	public ModelAndView averageBandwidthReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called avarageLatencyReport");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("averageBandwidthReport");
		return m;
	}

	@RequestMapping("/averageBandwidthReport")
	public ModelAndView averageBandwidthReport(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller interfaceBandwidthHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//		List<InterfaceBandwidthHistoryBean> historyData = service.InterfaceBandwidthHistoryReport(from_date, to_date,list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", service.averageBandwidthReport(from_date, to_date, list, userScopeData));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("viewAverageBandwidthReport");
		return m;

	}

	// averageBandwidthReport report
	@RequestMapping("/interfaceJitterReportForm")
	public ModelAndView interfaceJitterReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called avarageLatencyReport");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("interfaceJitterReportForm");
		return m;
	}

	@RequestMapping("/interfaceJitterReport")
	public ModelAndView interfaceJitterReport(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		System.out.println("Controller interfaceBandwidthHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

//			List<InterfaceBandwidthHistoryBean> historyData = service.InterfaceBandwidthHistoryReport(from_date, to_date,list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", service.interfaceJitterReport(from_date, to_date, list));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("interfaceJitterReportView");
		return m;

	}

	@RequestMapping("/interfaceBandwidth")
	public ModelAndView interfaceBandwidth(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceBandwidthHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");
		String interfaceName = req.getParameter("intName");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		System.out.println("Interface Name:" + interfaceName);

//			List<InterfaceBandwidthHistoryBean> historyData = service.InterfaceBandwidthHistoryReport(from_date, to_date,list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", service.interfaceBandwidth(from_date, to_date, ip_address, interfaceName));
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("interfaceBandwidthHistoryView");
		return m;

	}

	@RequestMapping("/interfaceStatusEvents")
	public ModelAndView interfaceStatusEvents(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceStatusReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");
		String interfaceName = req.getParameter("intName");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		System.out.println("Interface Name:" + interfaceName);

		List<InterfaceStatusReportBean> historyData = service.interfaceStatusEvents(from_date, to_date, ip_address,
				interfaceName);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("InterfaceStatusReportView");
		return m;

	}

	@RequestMapping("/interfaceUptime")
	public ModelAndView interfaceUptime(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceAvailabilityReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("deviceIP");
		String interfaceName = req.getParameter("intName");

		System.out.println("ip_list :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		System.out.println("Interface Name:" + interfaceName);

		List<InterfaceAvailabilityBean> historyData = service.interfaceUptime(from_date, to_date, ip_address,
				interfaceName);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("InterfaceAvailabilityView");
		return m;

	}

	@RequestMapping("/deviceTypeSummary")
	public void deviceTypeSummary(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.deviceTypeSummary(userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getTotalDeviceTypeSummaryDetails")
	public void getTotalDeviceTypeSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getTotalDeviceTypeSummaryDetails(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getupDeviceTypeSummaryDetails")
	public void getupDeviceTypeSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getupDeviceTypeSummaryDetails(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getdownDeviceTypeSummaryDetails")
	public void getdownDeviceTypeSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getdownDeviceTypeSummaryDetails(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getwarningDeviceTypeSummaryDetails")
	public void getwarningDeviceTypeSummaryDetails(HttpServletRequest req, HttpServletResponse res,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getwarningDeviceTypeSummaryDetails(group_name, userScopeData));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}
}
