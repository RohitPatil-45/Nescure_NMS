package npm.admin.controller;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import npm.dashboard.service.NodeDetailsService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	NodeDetailsService service;

	@RequestMapping(value = { "/loginuser" }, method = RequestMethod.POST)
	public void checkCredentials(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		String controller_response = "";
		PrintWriter out = null;
		try {

			out = res.getWriter();

//			out.print("error");

			System.out.println("Controller Called loginuser");

//		String email=req.getParameter("email");
			String username = req.getParameter("username");
			String password = req.getParameter("password");

			// System.out.println("login password = "+password);
//	
//		
//		service.getDBVerification(username, password);
			String dao_output = service.getDBVerification(username, password);
			// System.out.println("From Service_IMPL_output response====" + dao_output);
			// System.out.println("password" + password);
			// String dao_output = login_dao.getDBVerification(username, password);
			// System.out.println("From Service_IMPL_output response " + dao_output);
			String[] arrOfStr = dao_output.split("~");
			// System.out.println("Processed Password: " + arrOfStr[0]);
			// System.out.println("Username: " + arrOfStr[1]);

			if (username.equals(arrOfStr[1])) {
				System.out.println("Username matched");
				if (password.equals(arrOfStr[0])) {
					System.out.println("Credentials Matched !!!!!!");
					session.setAttribute("username", username);
					String userName = String.valueOf(session.getAttribute("username"));
					String adminScope = service.getAdminScope(userName);
					String dashboardScope = service.getDashoardScope(userName);
					String reportScope = service.getReportScope(userName);
					String graphScope = service.getGraphScope(userName);
					String userScope = service.getUserScope(userName);

					session.setAttribute("adminScope", adminScope);
					session.setAttribute("dashboardScope", dashboardScope);
					session.setAttribute("reportScope", reportScope);
					session.setAttribute("graphScope", graphScope);
					session.setAttribute("userScope", userScope);

					System.out.println("User Scope = " + userScope);
					System.out.println("Admin Scope = " + adminScope);
					System.out.println("Dashboard Scope = " + dashboardScope);
					System.out.println("Report Scope = " + reportScope);
					System.out.println("Graph Scope = " + graphScope);
					if (adminScope.equalsIgnoreCase("") || adminScope.equals(null)) {
						controller_response = "noScope";
					} else {
						controller_response = "success";
					}
				} else {
					System.out.println("Password Not Matchedd");
					controller_response = "error";
				}

			} else {
				System.out.println("Username Not matched");
				controller_response = "userntmtch";
			}
			String clientIp = req.getRemoteAddr();
			System.out.println("clientip::"+clientIp);

			if ("0:0:0:0:0:0:0:1".equals(clientIp) || "::1".equals(clientIp) || "127.0.0.1".equals(clientIp)) {
				clientIp = "103.145.172.88"; // Replace with your custom IP
			}

			// Get server's hostname
			String serverHostname = "";
			try {
				// Perform reverse DNS lookup on the client's IP
				InetAddress inetAddress = InetAddress.getByName(clientIp);
				serverHostname = inetAddress.getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			String responsescope = "";
			if (controller_response.equals("noScope")) {
				responsescope = "logg IN failed (no scope)";
			} else if (controller_response.equals("success")) {
				responsescope = "Legged IN";
			} else if (controller_response.equals("error")) {
				responsescope = "log IN failed(Password Incorrect)";
			} else if (controller_response.equals("userntmtch")) {
				responsescope = "log IN fail (User not match)";
			}

			// Add details to the response map
			System.out.println("hostname : " + serverHostname);
			System.out.println("ipAddress : " + clientIp);
			System.out.println("login controller final response:" + controller_response);
			System.out.println("login controller final response:" + responsescope);

			service.saveuserloginhistory(username, serverHostname, clientIp, responsescope);

			out.print(controller_response);
		} catch (Exception e) {
			System.out.println("Exceptionn:" + e);
		} finally {

			out.close();
		}
		System.out.println("#############################");

		// return "landingPage";
		// return null;
	}

	@RequestMapping(value = { "/logoutstore" }, method = RequestMethod.POST)
	public void logoutstore(HttpServletRequest req, HttpServletResponse res, HttpSession session) {

		PrintWriter out = null;
		try {

			out = res.getWriter();

//			out.print("error");

			System.out.println("Controller Called loginuser");

//		String email=req.getParameter("email");
			String username = req.getParameter("username");

			String clientIp = req.getRemoteAddr();

			if ("0:0:0:0:0:0:0:1".equals(clientIp) || "::1".equals(clientIp) || "127.0.0.1".equals(clientIp)) {
				clientIp = "103.145.172.88"; // Replace with your custom IP
			}

			// Get server's hostname
			String serverHostname = "";
			try {
				// Perform reverse DNS lookup on the client's IP
				InetAddress inetAddress = InetAddress.getByName(clientIp);
				serverHostname = inetAddress.getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			String responsescope = "Logged Out";

			// Add details to the response map
			System.out.println("hostname : " + serverHostname);
			System.out.println("username : " + username);
			System.out.println("ipAddress : " + clientIp);
//			System.out.println("login controller final response:" + controller_response);
			System.out.println("logoutstore controller final response:" + responsescope);

			service.saveuserloginhistory(username, serverHostname, clientIp, responsescope);

			out.print("done");
		} catch (Exception e) {
			System.out.println("Exceptionn in logoutstore:" + e);
		} finally {

			out.close();
		}
		System.out.println("#############################");

		// return "landingPage";
		// return null;
	}

	@RequestMapping(value = { "/Home" }, method = RequestMethod.GET)
	public String viewDLP(ModelMap model) {

		// return "landingPage";
		return "summary";
	}

	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String index(ModelMap model) {

		return "index";
	}

}
