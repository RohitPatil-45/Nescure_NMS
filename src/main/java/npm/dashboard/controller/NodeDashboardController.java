package npm.dashboard.controller;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import npm.admin.beans.NodeStatusReportBean;
import npm.dashboard.dao.NodeDetailsDao;
import npm.dashboard.service.NodeDetailsService;
import npm.report.service.NodeReportService;

@Controller
@RequestMapping("/nodeDashboard")
public class NodeDashboardController {

	@Autowired
	NodeDetailsService service;

	@Autowired
	NodeDetailsDao dao;

	@RequestMapping("/nodeDetails")
	public String nodeDetails(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeDetails");

		return "nodeDetails";
	}

	@RequestMapping("/dynamicNetworkTopology")
	public String dynamicNetworkTopology(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller dynamicNetworkTopology");

		return "DynamicNetworkTopologyTest";
	}

	@RequestMapping("/dynamicNetworkTopologyEdit")
	public String dynamicNetworkTopologyEdit(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller dynamicNetworkTopologyEdit");

		return "dynamicNetworkTopologyEdit";
	}

	@RequestMapping("/nodeLatencyPacketDrop")
	public void nodeLatencyPacketDrop(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("nodeLatencyPacketDrop controller called");
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
//		JSONObject getDetail = service.nodeLatencyPacketDrop(ip_address);
//		System.out.println("data = "+getDetail);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeLatencyPacketDrop(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/nodeAddNode")
	public void nodeAddNode(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("nodeAddNode controller called");
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
//		JSONObject getDetail = service.nodeLatencyPacketDrop(ip_address);
//		System.out.println("data = "+getDetail);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeAddNode(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/nodeAvailabilityDetails")
	public void nodeAvailabilityDetails(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("nodeAvailabilityDetails controller called");
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
//		JSONObject getDetail = service.nodeLatencyPacketDrop(ip_address);
//		System.out.println("data = "+getDetail);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeAvailabilityDetails(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/nodeStatusHistoryDetails")
	public void nodeStatusHistoryDetails(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("nodeStatusHistoryDetails controller called");
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
//		JSONObject getDetail = service.nodeLatencyPacketDrop(ip_address);
//		System.out.println("data = "+getDetail);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeStatusHistoryDetails(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/InterfaceStatusHistoryDetails")
	public void InterfaceStatusHistoryDetails(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("InterfaceStatusHistoryDetails controller called");
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
//		JSONObject getDetail = service.nodeLatencyPacketDrop(ip_address);
//		System.out.println("data = "+getDetail);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.InterfaceStatusHistoryDetails(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/basicInfoDetails")
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

	@RequestMapping("/nodeHealthMonitoringDetails")
	public void nodeHealthMonitoringDetails(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("nodeHealthMonitoringDetails controller called");
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
//		JSONObject getDetail = service.nodeLatencyPacketDrop(ip_address);
//		System.out.println("data = "+getDetail);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeHealthMonitoringDetails(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/nodeUpTimeStatusDetails")
	public void nodeUpTimeStatusDetails(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("nodeUpTimeStatusDetails controller called");
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
//		JSONObject getDetail = service.nodeLatencyPacketDrop(ip_address);
//		System.out.println("data = "+getDetail);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeUpTimeStatusDetails(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/nodeLatencyStatusDetails")
	public void nodeLatencyStatusDetails(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("nodeLatencyStatusDetails controller called");
		String ip_address = req.getParameter("ip_address");

		System.out.println(ip_address);
//		JSONObject getDetail = service.nodeLatencyPacketDrop(ip_address);
//		System.out.println("data = "+getDetail);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeLatencyStatusDetails(ip_address));
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			out.close();
		}
	}
	
	//02-11-2023 CPU AND MEMORY UTILIZATION
	@RequestMapping("/cpuAndMemoryUtilization")
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

	@Autowired
	NodeReportService serviceNode;

	@RequestMapping("/nodeStatusReport")
	public ModelAndView nodeStatusReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeStatusReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
//		String[] ip_address = req.getParameterValues("ipAddressCheck");
		String ip = "192.168.0.230";
		List<String> list = Arrays.asList(ip);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<NodeStatusReportBean> historyData = serviceNode.NodeStatusReport(from_date, to_date, list);
		System.out.println("Report Data:" + historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("DeviceStatusReportView");
		return m;

	}

	@RequestMapping(value = { "/getIPAddress" }, method = RequestMethod.GET)
	public void getBranchName(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside getIPAddress method");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(serviceNode.getIPAddress());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in ApplicationUserController viewApplicationUser Method: " + e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	@RequestMapping(value = { "/getInterfaceOnIPAddress" }, method = RequestMethod.POST)
	public void getInterfaceOnIPAddress(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside getInterfaceOnIPAddress method");

		PrintWriter out = null;
		try {
			String ipAddress = request.getParameter("ipAddress");
			out = response.getWriter();
			out.print(serviceNode.getInterfaceOnIPAddress(ipAddress));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in ApplicationUserController viewApplicationUser Method: " + e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	@RequestMapping(value = { "/SaveNodePositions" }, method = RequestMethod.POST)
	public void SaveNodePositions(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside getInterfaceOnIPAddress method");

		PrintWriter out = null;
		try {
			String fromTo = request.getParameter("fromTo");
			String positions = request.getParameter("positions");
			String ipAddress = request.getParameter("ipAddress");
//			String username = request.getParameter("username");
			String username = "NA";
			out = response.getWriter();
			out.print(serviceNode.SaveNodePositions(fromTo, positions, ipAddress, username));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in ApplicationUserController viewApplicationUser Method: " + e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

//	@RequestMapping(value = { "/NetworkTopologyEditMode" }, method = RequestMethod.POST)
//	public void NetworkTopologyEditMode(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("Inside NetworkTopologyEditMode method");
//
//		PrintWriter out = null;
//		try {
//			String grpName = "NA";
//		
//			out = response.getWriter();
//			out.print(serviceNode.NetworkTopologyEditMode(grpName));
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Exception in ApplicationUserController viewApplicationUser Method: " + e);
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
//
//	}
	@RequestMapping(value = { "/NetworkTopologyEditMode" }, method = RequestMethod.POST)
	public void NetworkTopologyEditMode(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside NetworkTopologyEditMode method");

		PrintWriter out = null;
		try {
			String grpName = "NA";

			out = response.getWriter();

			String tempData = "[{\"image\":\"..\\/webtemplate\\/visjs\\/img\\/routerUp.png\",\"shape\":\"image\",\"x\":3063,\"y\":998,\"id\":2,\"label\":\"10.252.10.21\",\"nodeType\":\"parentNode\"},{\"image\":\"..\\/webtemplate\\/visjs\\/img\\/routerUp.png\",\"shape\":\"image\",\"x\":3226,\"y\":2698,\"id\":11,\"label\":\"10.193.255.1\",\"nodeType\":\"parentNode\"},{\"image\":\"..\\/webtemplate\\/visjs\\/img\\/routerUp.png\",\"shape\":\"image\",\"x\":1915,\"y\":2375,\"id\":13,\"label\":\"10.192.254.2\",\"nodeType\":\"parentNode\"},{\"image\":\"..\\/webtemplate\\/visjs\\/img\\/routerDown.png\",\"shape\":\"image\",\"x\":3034,\"y\":2125,\"id\":14,\"label\":\"10.15.255.2\",\"nodeType\":\"parentNode\"}]~[{\"labelTo\":\"Gi0\\/3\",\"color\":\"red\",\"from\":\"36\",\"to\":\"5\",\"label\":\"Gi0\\/1\\/3 <---> Gi0\\/3\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/1\\/3\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/1\\/2\",\"color\":\"green\",\"from\":\"10\",\"to\":\"4\",\"label\":\"Gi0\\/1 <---> Gi0\\/1\\/2\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/1\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/0\\/0.101\",\"color\":\"red\",\"from\":\"29\",\"to\":\"1\",\"label\":\"Gi0\\/1\\/2 <---> Gi0\\/0\\/0.101\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/1\\/2\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/1\\/1\",\"color\":\"red\",\"from\":\"11\",\"to\":\"29\",\"label\":\"Gi0\\/0\\/2 <---> Gi0\\/1\\/1\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/0\\/2\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/1.1\",\"color\":\"red\",\"from\":\"26\",\"to\":\"9\",\"label\":\"Gi0\\/1\\/2 <---> Gi0\\/1.1\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/1\\/2\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/1\\/3\",\"color\":\"red\",\"from\":\"19\",\"to\":\"17\",\"label\":\"Gi2\\/0\\/5 <---> Gi0\\/1\\/3\",\"selectionWidth\":3,\"labelFrom\":\"Gi2\\/0\\/5\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/0\\/0\",\"color\":\"red\",\"from\":\"36\",\"to\":\"19\",\"label\":\"Gi0\\/1\\/1 <---> Gi0\\/0\\/0\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/1\\/1\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/1\\/1\",\"color\":\"green\",\"from\":\"9\",\"to\":\"22\",\"label\":\"Gi0\\/1.201 <---> Gi0\\/1\\/1\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/1.201\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/0\\/2\",\"color\":\"red\",\"from\":\"20\",\"to\":\"1\",\"label\":\"Gi0\\/1\\/1 <---> Gi0\\/0\\/2\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/1\\/1\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/0\\/2\",\"color\":\"green\",\"from\":\"2\",\"to\":\"13\",\"label\":\"Gi0\\/1 <---> Gi0\\/0\\/2\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/1\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/1\\/1\",\"color\":\"green\",\"from\":\"2\",\"to\":\"26\",\"label\":\"Lo10 <---> Gi0\\/1\\/1\",\"selectionWidth\":3,\"labelFrom\":\"Lo10\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/1\",\"color\":\"red\",\"from\":\"44\",\"to\":\"10\",\"label\":\"Gi0\\/1\\/2 <---> Gi0\\/1\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/1\\/2\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/1\\/1\",\"color\":\"red\",\"from\":\"14\",\"to\":\"44\",\"label\":\"Gi0\\/2 <---> Gi0\\/1\\/1\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/2\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/1\\/1\",\"color\":\"green\",\"from\":\"13\",\"to\":\"36\",\"label\":\"Tu2 <---> Gi0\\/1\\/1\",\"selectionWidth\":3,\"labelFrom\":\"Tu2\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/0\\/1.20\",\"color\":\"green\",\"from\":\"13\",\"to\":\"11\",\"label\":\"Gi0\\/0\\/0.100 <---> Gi0\\/0\\/1.20\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/0\\/0.100\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/2\",\"color\":\"red\",\"from\":\"2\",\"to\":\"14\",\"label\":\"Gi0\\/5 <---> Gi0\\/2\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/5\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/0\",\"color\":\"red\",\"from\":\"13\",\"to\":\"14\",\"label\":\"Gi0\\/1\\/0 <---> Gi0\\/0\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/1\\/0\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/0\\/2\",\"color\":\"green\",\"from\":\"2\",\"to\":\"11\",\"label\":\"Gi0\\/4 <---> Gi0\\/0\\/2\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/4\",\"hoverWidth\":2},{\"labelTo\":\"Gi0\\/0\",\"color\":\"green\",\"from\":\"11\",\"to\":\"14\",\"label\":\"Gi0\\/0\\/1.20 <---> Gi0\\/0\",\"selectionWidth\":3,\"labelFrom\":\"Gi0\\/0\\/1.20\",\"hoverWidth\":2}]~[]";

			out.print(tempData);

			out.print(serviceNode.NetworkTopologyEditMode(grpName));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in ApplicationUserController viewApplicationUser Method: " + e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	@RequestMapping(value = { "/NetworkTopologyInsertInterface" }, method = RequestMethod.GET)
	public void NetworkTopologyInsertInterface(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Inside NetworkTopologyInsertInterface method");

		PrintWriter out = null;
		try {
			String fromIpAddress = request.getParameter("fromIpAddress");
			String toIpAddress = request.getParameter("toIpAddress");
			String fromInterface = request.getParameter("fromInterface");
			String toInterface = request.getParameter("toInterface");

			out = response.getWriter();
			out.print(
					serviceNode.NetworkTopologyInsertInterface(fromIpAddress, toIpAddress, fromInterface, toInterface));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in NetworkTopologyInsertInterface viewApplicationUser Method: " + e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}
	
	
	//Auto Topology
	@RequestMapping(value = { "/autoTopologyForm" }, method = RequestMethod.GET)
	public String getAutoTopologyPage(ModelMap model) {

		return "/AutoNetworkTopology";
	}
	
	@RequestMapping(value = { "/getAutoNetworkTopology" }, method = RequestMethod.POST)
	public void getAutoNetworkTopology(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			String contextPath = request.getContextPath();
			out.print(service.getAutoNetworkTopology("username", contextPath));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	//Auto Topology History Data
	
	@RequestMapping("/autoTopologyHistoryForm")
	public String autoTopologyHistoryForm()
	{
		
		return "AutoTopologyHistoryForm";
		
	}
	
	@RequestMapping(value = { "/autoTopologyHistoryData" }, method = RequestMethod.POST)
	public ModelAndView autoTopologyHistoryData(HttpServletRequest request, HttpServletResponse response) {
		
	
		String from_date = request.getParameter("from_date");
		String to_date = request.getParameter("to_date");
		
		JSONArray jsonArray = service.autoTopologyHistoryData(from_date, to_date);
		ModelAndView m = new ModelAndView();
		m.addObject("autoTopologyHistoryData", jsonArray);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("AutoTopologyHistory");
		return m;

	}
	
	@RequestMapping(value = { "/viewAutoTopologyHistoryOnID/{id}" }, method = RequestMethod.GET)
	public ModelAndView viewAutoTopologyHistoryOnID(ModelMap model, @PathVariable("id") String poolingID) {

		System.out.println(poolingID);
		ModelAndView m = new ModelAndView();
		JSONArray jsonArray = service.viewAutoTopologyHistoryOnID(poolingID);
		System.out.println(jsonArray);
		m.addObject("autoTopologyHistoryOnID", jsonArray);
		m.setViewName("/viewAutoTopologyHistoryOnID");
		return m;
	}

}
