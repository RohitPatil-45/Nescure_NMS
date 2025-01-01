package npm.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import npm.report.service.TopTalkerReportService;
@Controller
@RequestMapping("/toptTalkerReport")
public class TopTalkerReportController {
	
	@Autowired
	TopTalkerReportService service;
	
	
	@RequestMapping("/topConnectionWiseForm")
	public String viewTalker() {
//		System.out.println("in controller top ");
//		ModelAndView m = new ModelAndView();
//		m.setViewName("topTalkerListing");
		return "topConnectionWiseReportForm";
	}
	
	@RequestMapping("/topTalkerSourceWiseForm")
	public String topTalkerSourceWiseForm() {

		return "topTalkerSourceWiseForm";
	}
	
	@RequestMapping("/topTalkerForm")
	public String topTalkerForm() {

		return "TopTalker";
	}
	
	@RequestMapping("/topTalkerProtocolWiseForm")
	public String topTalkerProtocolWiseForm() {

		return "TopTalkerProtocolWiseForm";
	}
	
	@RequestMapping("/topTalkerReport")
	public ModelAndView topTalkerReport(HttpServletRequest req, HttpServletResponse res,HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		JSONArray topTalker = service.topTalkerReport(from_date, to_date,userScopeData);
		ModelAndView m = new ModelAndView();
		m.addObject("topTalkerData", topTalker);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("TopTalkerReport");
		return m;

	}
	
	// Top Talker IP Wise Report
	@RequestMapping("/topTalkerIpWise")
	public ModelAndView topTalkerIpWise(HttpServletRequest req, HttpServletResponse res,HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller topTalkerIpWise");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		 String ipWiseClick = "ipWiseClick";
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		JSONArray topTalker = service.topTalkerIpWise(from_date, to_date, ipWiseClick,userScopeData);
		System.out.println(topTalker);
		ModelAndView m = new ModelAndView();
		m.addObject("topConnection", topTalker);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("topConnectionWiseReport");
		return m;

	}
	
	
	//Top Talker Source Wise Report
	@RequestMapping("/topTalkerSourceWise")
	public ModelAndView topTalkerSourceWise(HttpServletRequest req, HttpServletResponse res,HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller topTalkerSourceWise");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		 String sourceWiseClick = "SourceWiseClick";
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		JSONArray topTalker = service.topTalkerSourceWise(from_date, to_date, sourceWiseClick,userScopeData);
		System.out.println(topTalker);
		ModelAndView m = new ModelAndView();
		m.addObject("topSourceWise", topTalker);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("topTalkerSourceWiseReport");
		return m;

	}
	
	//Top Talker Protocol Wise Report
	@RequestMapping("/topTalkerProtocolWise")
	public ModelAndView topTalkerProtocolWise(HttpServletRequest req, HttpServletResponse res,HttpSession session) {
		String userScopeData = (String) session.getAttribute("userScope");
		System.out.println("Controller topTalkerSourceWise");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		 String protocolWiseClick = "ProtocolWiseClick";
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		JSONArray topTalker = service.topTalkerProtocolWise(from_date, to_date, protocolWiseClick,userScopeData);
		System.out.println(topTalker);
		ModelAndView m = new ModelAndView();
		m.addObject("topProtocolWise", topTalker);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("TopTalkerProtocolWiseReport");
		return m;

	}

}
