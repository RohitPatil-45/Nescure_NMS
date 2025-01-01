package npm.dashboard.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import npm.admin.beans.processList;

import npm.admin.beans.AgentInstalledDeviceBean;

import npm.admin.beans.LatencyHistoryReportBean;
import npm.dashboard.service.DashboardService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	DashboardService service;

	/* Summary Dashboard Page */

	@RequestMapping("/summaryPage")
	public String summaryDashboardPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called summaryPage");

		return "summary";
	}

	/* Get Device Summary Count */
	@RequestMapping(value = "/deviceSummaryCount", method = RequestMethod.GET)
	public JSONArray deviceSummaryCount(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.deviceSummaryCount(userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Link Summary Count */
	@RequestMapping(value = "/linkSummaryCount", method = RequestMethod.GET)
	public JSONArray linkSummaryCount(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.linkSummaryCount(userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	// Get Alert Count
	@RequestMapping(value = "/alertCount", method = RequestMethod.GET)
	public void alertCount(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(service.alertCount());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	// get Alert Info
	@RequestMapping(value = "/getAlertInfo", method = RequestMethod.GET)
	public void getAlertInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		System.out.println("in alert controller");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(service.getAlertInfo());
			System.out.println(service.getAlertInfo());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = "/getCpuAlertInfo", method = RequestMethod.GET)
	public void getCpuAlertInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(service.getCpuAlertInfo());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = "/getMemoryAlertInfo", method = RequestMethod.GET)
	public void getMemoryAlertInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(service.getMemoryAlertInfo());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = "/getDriveAlertInfo", method = RequestMethod.GET)
	public void getDriveAlertInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(service.getDriveAlertInfo());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = "/getBandwidthAlertInfo", method = RequestMethod.GET)
	public void getBandwidthAlertInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(service.getBandwidthAlertInfo());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = "/getTopologyAlertInfo", method = RequestMethod.GET)
	public void getTopologyAlertInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(service.getTopologyAlertInfo());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/* Get Down Devices Summary Listing */

	@RequestMapping(value = "/deviceSummaryList", method = RequestMethod.GET)
	public JSONArray deviceSummaryList(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.deviceSummaryList(userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Down Link Summary Listing */

	@RequestMapping(value = "/linkSummaryList", method = RequestMethod.GET)
	public JSONArray linkSummaryList(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.linkSummaryList(userScopeData);
			System.out.println("Down Link Summary = " + jsonresponse);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Device Info */
	@RequestMapping(value = "/getDeviceInfo", method = RequestMethod.GET)
	public JSONArray getDeviceInfo(@RequestParam("deviceInfo") String deviceInfo, HttpServletResponse response,
			HttpSession session) {

		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.getDeviceInfo(deviceInfo, userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Link Info */
	@RequestMapping(value = "/getLinkInfo", method = RequestMethod.GET)
	public JSONArray getLinkInfo(@RequestParam("linkInfo") String linkInfo, HttpServletResponse response,
			HttpSession session) {

		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.getLinkInfo(linkInfo, userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Top 10 Dashboard Page */

	@RequestMapping("/topUtilization")
	public String topUtilizationPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called topUtilization");

		return "topUtilization";
	}

	@RequestMapping("/topActivityDashboard")
	public String topActivityDashboard(HttpServletRequest req, HttpServletResponse res) {
		return "TopActivityDashboard";
	}

	@RequestMapping(value = { "/getTopMemoryUtilization" }, method = RequestMethod.POST)
	public void getTopMemoryUtilization(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.print(service.getTopMemoryUtilization(userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
			;
		} finally {
			out.close();
		}

	}

	@RequestMapping(value = { "/getTopCpuUtilization" }, method = RequestMethod.POST)
	public void getTopCPUUtilization(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(service.getTopCpuUtilization(userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
			;
		} finally {
			out.close();
		}

	}

	@RequestMapping(value = { "/getBwInUtilization" }, method = RequestMethod.POST)
	public void getBWINUtilization(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.print(service.getBwInUtilization(userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
			;
		} finally {
			out.close();
		}

	}

	@RequestMapping(value = { "/getBWOutUtilization" }, method = RequestMethod.POST)
	public void getBWOutUtilization(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;

		String userScopeData = (String) session.getAttribute("userScope");

		try {
			out = response.getWriter();
			out.print(service.getBWOutUtilization(userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
			;
		} finally {
			out.close();
		}

	}

	@RequestMapping(value = { "/getTopLatencyUtilization" }, method = RequestMethod.POST)
	public void getTopLatencyUtilization(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.print(service.getTopLatencyUtilization(userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
			;
		} finally {
			out.close();
		}

	}

	/* Top CPU Summary Listing */

	@RequestMapping(value = "/topCPUSummary", method = RequestMethod.GET)
	public JSONArray topCPUSummary(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.topCPUSummary(userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Interface Details Page */

	@RequestMapping("/interfaceDetailsPage")
	public String interfaceDetailsPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceDetailsPage");

		return "interfaceDetails";
	}

	/* Interface Details Page */

	@RequestMapping("/interfaceInfoPage")
	public String interfaceInfoPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceInfoPage");

		return "interfaceInfo";
	}

	/* Get Interface Info */
	@RequestMapping(value = "/interfaceInfo", method = RequestMethod.POST)
	public JSONArray interfaceInfo(@RequestParam("deviceIP") String deviceIP, @RequestParam("intName") String intName,
			HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceInfo(deviceIP, intName, userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Interface Status History */
	@RequestMapping(value = "/interfaceStatusHistory", method = RequestMethod.POST)
	public JSONArray interfaceStatusHistory(@RequestParam("deviceIP") String deviceIP,
			@RequestParam("intName") String intName, HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceStatusHistory(deviceIP, intName, userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Interface CRC Log */
	@RequestMapping(value = "/interfaceCRCLog", method = RequestMethod.POST)
	public JSONArray interfaceCRCLog(@RequestParam("deviceIP") String deviceIP, @RequestParam("intName") String intName,
			HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceCRCLog(deviceIP, intName, userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Interface Uptime Log */
	@RequestMapping(value = "/interfaceUptimeLog", method = RequestMethod.POST)
	public JSONArray interfaceUptimeLog(@RequestParam("deviceIP") String deviceIP,
			@RequestParam("intName") String intName, HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceUptimeLog(deviceIP, intName, userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Interface Bandwidth Utilization */

	@RequestMapping(value = "/interfaceBandwidthUtil", method = RequestMethod.POST)
	public JSONArray interfaceBandwidthUtil(@RequestParam("deviceIP") String deviceIP,
			@RequestParam("intName") String intName, HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceBandwidthUtil(deviceIP, intName, userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	// ink Latency and Jitter Latency
	@RequestMapping(value = "/interfaceLinkLatency", method = RequestMethod.POST)
	public JSONArray interfaceLinkLatency(@RequestParam("deviceIP") String deviceIP,
			@RequestParam("intName") String intName, HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceLinkLatency(deviceIP, intName, userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Node Details Page */

	@RequestMapping("/nodeDetailsPage")
	public String nodeDetailsPage(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		System.out.println("Controller Called nodeDetailsPage");

		return "nodeDetails";
	}

	// Export Node Latency
	@RequestMapping("/exportNodeLatency")
	public ModelAndView exportNodeLatency(@RequestParam("from_date") String from_date,
			@RequestParam("to_date") String to_date, @RequestParam("ip_address") String ip_address,
			HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

//			String from_date = req.getParameter("fromDate");
//			String to_date = req.getParameter("toDate");
//			String ip_address = req.getParameter("ipAddress");
		System.out.println("from_date=" + from_date + to_date + ip_address);

		List<LatencyHistoryReportBean> historyData = service.exportNodeLatency(from_date, to_date, ip_address,
				userScopeData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("exportNodeLatencyReport");
		return m;
//		ModelAndView m = new ModelAndView();
//		m.addObject("data", service.exportNodeLatency(from_date, to_date, ip_address, userScopeData));
//		m.setViewName("exportNodeLatencyReport");

//		return m;

	}

//	@RequestMapping(value = { "/nodeDetailsPage/{id}" }, method = RequestMethod.GET)
//	public String machineInfo(ModelMap model, @PathVariable String id) {
//		System.out.println("Controller Called nodeDetailsPage:"+id);
//
//		return "nodeDetails";
//	}
	/* Server Monitoring Dashboard Page */
	@RequestMapping("/serverMonitoringDashboard")
	public String serverMonitoringDashboard(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called serverMonitoringDashboard");

		return "serverMonitoringDashboard";
	}

	/* TopTalker Dashboard Page */

	@RequestMapping("/toptalker")
	public String toptalkerDashboardPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called ToptalkerPage");

		return "topTalkerDashboard";
	}

	/* Get Toptalker Link Summary Listing */

	@RequestMapping(value = "/topTalkerLinkSummaryList", method = RequestMethod.GET)
	public JSONArray topTalkerLinkSummaryList(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.topTalkerLinkSummaryList(userScopeData);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* TopTalker Dashboard Page */

	@RequestMapping("/toptalkerinfo")
	public String toptalkerInfoPage(HttpServletRequest req, HttpServletResponse res) {
		// System.out.println("Controller Called ToptalkerInfoPage");

		return "topTalkerInfo";
	}

	// Top Talker Source IP Chart

	@RequestMapping(value = { "/getTopTalkerChartAllData" }, method = RequestMethod.POST)
	public void getTopTalkerChartAllData(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {

			out = response.getWriter();
			System.out.println("HELLO TOPTALKER =" + service.getTopTalkerChartAllData(userScopeData));
			out.print(service.getTopTalkerChartAllData(userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = { "/getTopTalkerChartFromDateToDate" }, method = RequestMethod.POST)
	public void getTopTalkerChartFromDateToDate(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		System.out.println("in top talker source ip wise chart");
		try {

			String ToDate = request.getParameter("toDate");
			String FromDate = request.getParameter("fromDate");
			out = response.getWriter();
			System.out.println(service.getTopTalkerChartFromDateToDate(ToDate, FromDate, userScopeData));
			out.print(service.getTopTalkerChartFromDateToDate(ToDate, FromDate, userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = { "/getTopTalkerSumOfDeviceListAlldata" }, method = RequestMethod.POST)
	public void getTopTalkerSumOfDeviceListAlldata(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {

			out = response.getWriter();
			out.print(service.getTopTalkerSumOfDeviceListAlldata(userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = { "/getTopTalkerSumOfDeviceListFromDateToDate" }, method = RequestMethod.POST)
	public void getTopTalkerSumOfDeviceListFromDateToDate(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
//			String ipAddress = request.getParameter("ipAddress");
			String ToDate = request.getParameter("ToDate");
			String FromDate = request.getParameter("FromDate");
			out = response.getWriter();
			out.print(service.getTopTalkerSumOfDeviceListFromDateToDate(ToDate, FromDate, userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	// End Top Talker Source IP Chart

	// Top Talker Connection wise
	@RequestMapping(value = { "/getsumOfDeviceDetailsListAllData" }, method = RequestMethod.POST)
	public void getsumOfDeviceDetailsListAllData(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {

			out = response.getWriter();
			out.print(service.getsumOfDeviceDetailsListAllData(userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = { "/getsumOfDeviceDetailsListFromDateToDate" }, method = RequestMethod.POST)
	public void getsumOfDeviceDetailsListFromDateToDate(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
//			String ipAddress = request.getParameter("ipAddress");
			String ToDate = request.getParameter("ToDate");
			String FromDate = request.getParameter("FromDate");
			out = response.getWriter();
			out.print(service.getsumOfDeviceDetailsListFromDateToDate(ToDate, FromDate, userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	// End Top Talker Connection wise

	// Top Talker Protocol Wise
	@RequestMapping(value = { "/getTopProtocolChartAllData" }, method = RequestMethod.POST)
	public void getTopProtocolChartAllData(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {

			out = response.getWriter();
			out.print(service.getTopProtocolChartAllData(userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = { "/getTopProtocolSumOfDeviceListAlldata" }, method = RequestMethod.POST)
	public void getTopProtocolSumOfDeviceListAlldata(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
			String ipAddress = request.getParameter("ipAddress");
			String ToDate = request.getParameter("ToDate");
			String FromDate = request.getParameter("FromDate");
			out = response.getWriter();
			out.print(service.getTopProtocolSumOfDeviceListAlldata(userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = { "/getTopProtocolChartFromDateToDate" }, method = RequestMethod.POST)
	public void getTopProtocolChartFromDateToDate(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {

			String ToDate = request.getParameter("toDate");
			String FromDate = request.getParameter("fromDate");
			out = response.getWriter();
			out.print(service.getTopProtocolChartFromDateToDate(ToDate, FromDate, userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping(value = { "/getTopProtocolSumOfDeviceListFromDateToDate" }, method = RequestMethod.POST)
	public void getTopProtocolSumOfDeviceListFromDateToDate(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		PrintWriter out = null;
		try {
//			String ipAddress = request.getParameter("ipAddress");
			String ToDate = request.getParameter("ToDate");
			String FromDate = request.getParameter("FromDate");
			out = response.getWriter();
			out.print(service.getTopProtocolSumOfDeviceListFromDateToDate(ToDate, FromDate, userScopeData));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	// serviceMonitoringDashboard
//	@RequestMapping(value = "/serviceMonitoringDashboard", method = RequestMethod.GET)
//	public JSONArray serviceMonitoringDashboard(ModelMap model, HttpServletRequest request, HttpServletResponse response,
//			HttpSession session) {
//		String userScopeData = (String) session.getAttribute("userScope");
//
//		PrintWriter out = null;
//		try {
//			out = response.getWriter();
//			JSONArray jsonresponse = service.topTalkerLinkSummaryList(userScopeData);
//			out.print(jsonresponse);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			out.close();
//		}
//
//		return null;
//	}
	@RequestMapping("/serviceMonitoringDashboard")
	public String ScheduleScanForm() {
		return "serviceMonitoring";
	}

	@RequestMapping("/driveMonitoringDashboard")
	public String driveMonitoringDashboard() {
		return "driveMonitoring";
	}

	@RequestMapping("/processMonitoringDashboard")
	public String processMonitoringDashboard() {
		return "processMonitoring";
	}

	@RequestMapping("/sidebar")
	public String sidebar() {
		return "sideBar";
	}

	@RequestMapping("/mapTopology")
	public String mapTopology(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called mapTopology");

		return "mapTopology";
	}

	// get alert count
	@RequestMapping(value = "/getAlarmCount", method = RequestMethod.GET)
	public void getAlarmCount(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		System.out.println("in getAlarmInfo controller");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(service.getAlarmCount());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	// get Alert Info
	@RequestMapping(value = "/getAlarmInfo", method = RequestMethod.GET)
	public void getAlarmInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		System.out.println("in getAlarmInfo controller");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(service.getAlarmInfo());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping("/sysLogEventDashboard")
	public String sysLogEventDashboard(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		System.out.println("Controller Called sysLogEventDashboard");

		return "SysLogEventDashboard";
	}

	@RequestMapping(value = "/eventCountDashboard", method = RequestMethod.GET)
	public Map<String, Integer> getEventsData(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray eventData = service.eventCountDashboard(fromDate, toDate);
			System.out.println("eventDataCount =" + eventData);
			out.print(eventData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/windowsSeverityEvent", method = RequestMethod.GET)
	public JSONArray windowsSeverityEvent(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.windowsSeverityEvent(fromDate, toDate);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/windowsEventLogWise", method = RequestMethod.GET)
	public JSONArray windowsEventLogWise(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.windowsEventLogWise(fromDate, toDate);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/windowsEventTrend", method = RequestMethod.GET)
	public JSONArray windowsEventTrend(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.windowsEventTrend(fromDate, toDate);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/syslogEventTrend", method = RequestMethod.GET)
	public JSONArray syslogEventTrend(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		System.out.println(fromDate);
		System.out.println(toDate);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.syslogEventTrend(fromDate, toDate);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/windowsSeverityBarClick", method = RequestMethod.GET)
	public JSONArray windowsSeverityBarClick(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, @RequestParam(name = "label") String label,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.windowsSeverityBarClick(fromDate, toDate, label);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/windowsLogBarClick", method = RequestMethod.GET)
	public JSONArray windowsLogBarClick(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, @RequestParam(name = "label") String label,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.windowsLogBarClick(fromDate, toDate, label);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/eventWiseSummary", method = RequestMethod.GET)
	public JSONArray eventWiseSummary(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.eventWiseSummary();
			System.out.println("event name wise summery" + jsonresponse);
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/eventCountWiseSummary", method = RequestMethod.GET)
	public JSONArray eventCountWiseSummary(@RequestParam(name = "eventName") String eventName, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.eventCountWiseSummary(eventName);
			System.out.println("event count wise summery" + jsonresponse);
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/sourceWiseSummary", method = RequestMethod.GET)
	public JSONArray sourceWiseSummary(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.sourceWiseSummary(fromDate, toDate);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/souceSummaryCountClick", method = RequestMethod.GET)
	public JSONArray souceSummaryCountClick(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, @RequestParam(name = "sourceName") String sourceName,
			@RequestParam(name = "severityType") String severityType, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.souceSummaryCountClick(fromDate, toDate, sourceName, severityType);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/osWiseSummary", method = RequestMethod.GET)
	public JSONArray osWiseSummary(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.osWiseSummary();
			out.print(jsonresponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/windowsSecurityEvent", method = RequestMethod.GET)
	public JSONArray windowsSecurityEvent(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.windowsSecurityEvent(fromDate, toDate);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/syslogEventList", method = RequestMethod.GET)
	public JSONArray syslogEventList(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.syslogEventList(fromDate, toDate);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/allDevicesList", method = RequestMethod.GET)
	public JSONArray allDevicesList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.allDevicesList();
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping("/getTotalNodeSummaryDetails")
	public void getTotalNodeSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getTotalNodeSummaryDetails(group_name));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getupNodeSummaryDetails")
	public void getupNodeSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getupNodeSummaryDetails(group_name));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/getdownNodeSummaryDetails")
	public void getdownNodeSummaryDetails(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String group_name = req.getParameter("group_name");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getdownNodeSummaryDetails(group_name));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping(value = "/allSyslogSeverityList", method = RequestMethod.GET)
	public JSONArray allSyslogSeverityList(HttpServletRequest request,
			@RequestParam(name = "from_date") String fromDate, @RequestParam(name = "to_date") String toDate,
			HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.allSyslogSeverityList(fromDate, toDate);
			System.out.println("allSyslogSeverityList=" + jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/syslogSeverityListData", method = RequestMethod.GET)
	public JSONArray syslogSeverityList(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, @RequestParam(name = "severity") String severity,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.syslogSeverityListData(fromDate, toDate, severity);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/allSyslogTypeList", method = RequestMethod.GET)
	public JSONArray allSyslogTypeList(HttpServletRequest request, @RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.allSyslogTypeList(fromDate, toDate);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/syslogTypeListData", method = RequestMethod.GET)
	public JSONArray syslogTypeListData(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate, @RequestParam(name = "type") String type,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.syslogTypeListData(fromDate, toDate, type);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	// Server Details
	@RequestMapping("/serverDetails")
	public String deviceDetails(Model m) {

		JSONArray dataArray1 = service.collectTreeGroup();
//		System.out.println("server details ="+dataArray1);
		List<Map<String, Object>> dataArray = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < dataArray1.length(); i++) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			JSONObject jsonObject = dataArray1.getJSONObject(i);
			// Assuming you have getters in AgentInstalledDeviceBean
			dataMap.put("group", jsonObject.get("groupName"));
			dataMap.put("device", jsonObject.get("deviceName"));
			dataMap.put("ipAddress", jsonObject.get("deviceIP"));
			dataMap.put("deviceStatus", jsonObject.get("nodeStatus"));
			// Add other properties as needed
//    	    System.out.println("deviceIP = "+jsonObject.get("deviceIP"));
//    	    System.out.println("device Name = "+jsonObject.get("deviceName"));
//    	    System.out.println("group = "+jsonObject.get("groupName"));
//    	    System.out.println("status = "+jsonObject.get("nodeStatus"));
			dataArray.add(dataMap);
		}
		Set<String> uniqueGroups = new HashSet<String>();
		List<Map<String, Object>> uniqueGroupsArray = new ArrayList<Map<String, Object>>();

		for (Map<String, Object> data : dataArray) {
			String group = (String) data.get("group");

			// Check if the group is not already in the set
			if (uniqueGroups.add(group)) {
				// If it's a new group, add it to the unique list
				Map<String, Object> uniqueData = new HashMap<String, Object>(data);
				uniqueGroupsArray.add(uniqueData);
			}
		}

		m.addAttribute("dataArray", dataArray);
		m.addAttribute("uniqueGroup", uniqueGroupsArray);
		return "DeviceDetails";

	}

	@RequestMapping("/getDiscoverDriveDetails")
	public void nodeStatusHistoryDetails(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("getDiscoverDriveDetails controller called");
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getDiscoverDriveDetails(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = "/serverDetails", method = RequestMethod.POST)
	public void deviceDetails(@RequestParam("group") String group, @RequestParam("ipAddress") String ipAddress,
			@RequestParam("device_name") String device_name, HttpServletResponse res) {
		PrintWriter out = null;
		JSONArray diskConf = new JSONArray();
		JSONArray hardInvent = new JSONArray();
		JSONArray memoryModule = new JSONArray();
		JSONArray logicalDrive = new JSONArray();
		JSONArray printalDt = new JSONArray();
		JSONArray hwConfig = new JSONArray();
		JSONArray swInvent = new JSONArray();

		JSONArray patchData = new JSONArray();
		JSONArray processData = new JSONArray();
		JSONArray serviceData = new JSONArray();
		JSONArray firewallData = new JSONArray();

		JSONArray driveDetails = new JSONArray();
		try {
			res.setContentType("application/json");
			out = res.getWriter();
			String result = "your group: " + group + " and your address: " + ipAddress;

			String getostype = service.getostype(ipAddress);
			System.out.println("Os type for " + ipAddress + " is " + getostype);
			if (getostype.equalsIgnoreCase("Ubuntu")) {
				diskConf = service.listingDiskConfigService(ipAddress);
				hardInvent = service.listingHardwareInService(ipAddress);
				memoryModule = service.listingMemoryModuleLinuxService(ipAddress);
				logicalDrive = service.listingLogicalDriveService(ipAddress);
				printalDt = service.listingPrintalDtService(ipAddress);
				hwConfig = service.listingHwConfigLinuxService(ipAddress);

				swInvent = service.listingSwInventforlinuxService(ipAddress);

				patchData = service.listingPatchDataService(ipAddress);
				processData = service.listingProcessDataService(ipAddress);
				serviceData = service.listingServiceDataService(ipAddress);
				firewallData = service.listingFirewallDataService(ipAddress);

				driveDetails = service.getDiscoverLinuxDriveDetails(ipAddress);

			} else {
				diskConf = service.listingDiskConfigService(ipAddress);
				hardInvent = service.listingHardwareInService(ipAddress);
				memoryModule = service.listingMemoryModuleService(ipAddress);
				logicalDrive = service.listingLogicalDriveService(ipAddress);
				printalDt = service.listingPrintalDtService(ipAddress);
				hwConfig = service.listingHwConfigService(ipAddress);
				swInvent = service.listingSwInventService(ipAddress);

				patchData = service.listingPatchDataService(ipAddress);
				processData = service.listingProcessDataService(ipAddress);
				serviceData = service.listingServiceDataService(ipAddress);
				firewallData = service.listingFirewallDataService(ipAddress);

				driveDetails = service.getDiscoverDriveDetails(ipAddress);

			}

			JSONObject combinedData = new JSONObject();
			combinedData.put("diskConf", diskConf);
			combinedData.put("hardInvent", hardInvent);
			combinedData.put("memoryModule", memoryModule);
			combinedData.put("logicalDrive", logicalDrive);
			combinedData.put("driveDetails", driveDetails);
			combinedData.put("printalDt", printalDt);
			combinedData.put("hwConfig", hwConfig);
			combinedData.put("swInvent", swInvent);

			combinedData.put("patchData", patchData);

			combinedData.put("processData", processData);
			combinedData.put("serviceData", serviceData);
			combinedData.put("firewallData", firewallData);
			combinedData.put("OSType", getostype);
			System.out.println(patchData);
			System.out.println(firewallData);
			System.out.println(patchData);
			out.print(combinedData.toString());

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/viewSetParameterData", method = RequestMethod.GET)
	public JSONArray viewSetParameterData(@RequestParam String ipAddress, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.viewSetParameterData(ipAddress);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/basicInfoDetails", method = RequestMethod.GET)
	public void basicInfoDetails(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("basicInfoDetails controller called");
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
//		JSONObject getDetail = service.nodeLatencyPacketDrop(ip_address);
//		System.out.println("data = "+getDetail);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.basicInfoDetails(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = "/cpuAndMemoryUtilization", method = RequestMethod.POST)
	public void cpuAndMemoryUtilization(HttpServletRequest req, HttpServletResponse res) {
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.cpuAndMemoryUtilization(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = "/processData", method = RequestMethod.POST)
	public void processData(@RequestBody String dataArray, HttpServletResponse res) {

		System.out.println("setting parameter for service monitoring =" + dataArray);
		Gson gson = new Gson();
		List<processList> list = gson.fromJson(dataArray, new TypeToken<List<processList>>() {
		}.getType());
//		 try {
//		        for (processList item : dataArray) {
//		            System.out.println("ID: " + item.getId() + ", Checkbox: " + item.getCheckbox());
//		        }
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }

		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.processPostData(list));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping(value = "/viewServiceListing", method = RequestMethod.GET)
	public JSONArray viewServiceListing(@RequestParam String ipAddress, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse1 = service.viewServiceListing(ipAddress);
			System.out.println(jsonresponse1);
			out.print(jsonresponse1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/serviceData", method = RequestMethod.POST)
	public void serviceData(@RequestBody String dataArray, HttpServletResponse res) {

		System.out.println(dataArray);
		Gson gson = new Gson();
		List<processList> list = gson.fromJson(dataArray, new TypeToken<List<processList>>() {
		}.getType());
//		 try {
//		        for (processList item : dataArray) {
//		            System.out.println("ID: " + item.getId() + ", Checkbox: " + item.getCheckbox());
//		        }
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }

		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.servicePostData(list));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	
//	@RequestMapping(value = "/interfacespeedtest", method = RequestMethod.GET)
//	public ModelAndView interfacespeedtest(@RequestParam("deviceIP") String deviceIP, @RequestParam("intName") String intName,
//			HttpServletResponse response, HttpSession session,ModelAndView modelview) {
////		String userScopeData = (String) session.getAttribute("userScope");
//
//		
//		System.out.println("222222222222222222222222222222222222111111111111111111111111111111111**************");
//	    System.out.println(deviceIP);
//	    System.out.println(intName);
//	
//		
//	    modelview.setViewName("speedTest");
//		return modelview;
//	}

}
