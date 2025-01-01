<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- <meta http-equiv="refresh" content="30;url=index.jsp"> -->
<title>NPM | Header</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/webtemplate/dist/img/AdminLTELogo.png">
</head>
<body class="hold-transition layout-top-nav">
	<% String sessionUser = (String)session.getAttribute("username"); 
    String adminScope = (String)session.getAttribute("adminScope"); 
    String dashboardScope = (String)session.getAttribute("dashboardScope"); 
    String reportScope = (String)session.getAttribute("reportScope");
    String graphScope = (String)session.getAttribute("graphScope");
    
    
    //session.setMaxInactiveInterval(10); %>
	<div class="wrapper">
		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand-md navbar-light navbar-white">
			<div class="container">
				<a href="<%=request.getContextPath()%>/dashboard/summaryPage"
					class="navbar-brand"> <!--  <img
					src="<%=request.getContextPath()%>/webtemplate/dist/img/AdminLTELogo.png"
					alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
					style="opacity: .8"> --> <!-- 					<img height="128" width="128" -->
					<%-- 					src="<%=request.getContextPath()%>/webtemplate/dist/img/canaris.jpeg" --%>
					<!-- 					class="brand-image"">  --> <!-- <span class="brand-text font-weight-light">NPM</span> -->
					<!-- 					<span class="brand-text font-weight-bold">TARANG</span> -->
					<img width="120"
					src="<%=request.getContextPath()%>/webtemplate/dist/img/nesecure1.png"
					class="">
				</a>

				<button class="navbar-toggler order-1" type="button"
					data-toggle="collapse" data-target="#navbarCollapse"
					aria-controls="navbarCollapse" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse order-3" id="navbarCollapse">
					<!-- Left navbar links -->
					<ul class="navbar-nav">
						<li class="nav-item"><a
							href="<%=request.getContextPath()%>/dashboard/summaryPage"
							class="nav-link">Home</a></li>
						<li class="nav-item dropdown"><a
							id="dashboardDropdownSubMenu" href="#" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"
							class="nav-link dropdown-toggle">Dashboard</a>
							<ul aria-labelledby="dashboardDropdownSubMenu"
								class="dropdown-menu border-0 shadow">
								<%
									if (dashboardScope.contains("Home_Dashboard")) {
								%>
								<li><a
									href="<%=request.getContextPath()%>/dashboard/summaryPage"
									class="dropdown-item">Home Dashboard</a></li>
								<li class="dropdown-divider"></li>

								<li><a
									href="<%=request.getContextPath()%>/dashboard/sysLogEventDashboard"
									class="dropdown-item">Event Dashboard</a></li>


								<%
									}
									if (dashboardScope.contains("Event_Dashboard")) {
								%>

								<%
									}

									if (dashboardScope.contains("Device_Details")) {
								%>
								<li class="dropdown-divider"></li>
								<li><a
									href="<%=request.getContextPath()%>/dashboard/serverDetails"
									class="dropdown-item">Server Details</a></li>

								<%
									}
									if (dashboardScope.contains("Top_10")) {
								%>
								<!-- 								<li class="dropdown-divider"></li> -->
								<!-- 								<li><a -->
								<%-- 									href="<%=request.getContextPath()%>/dashboard/topActivityDashboard" --%>
								<!-- 									class="dropdown-item">Top 10</a></li> -->
								<%
									}
									if (dashboardScope.contains("Auto_Topology")) {
								%>
								<!-- 								<li class="dropdown-divider"></li> -->
								<!-- 								<li><a -->
								<%-- 									href="<%=request.getContextPath()%>/nodeDashboard/autoTopologyForm" --%>
								<!-- 									class="dropdown-item">Auto Topology</a></li> -->
								<!-- 								<li class="dropdown-divider"></li> -->
								<!-- 								<li><a tabindex="-1" -->
								<%-- 									href="<%=request.getContextPath()%>/master/viewManualTopology" --%>
								<!-- 									class="dropdown-item">Manual Topology</a></li> -->
								<%
									}
									if (dashboardScope.contains("Top_Talker")) {
								%>
								<!-- 								<li class="dropdown-divider"></li> -->

								<!-- 								<li><a -->
								<%-- 									href="<%=request.getContextPath()%>/dashboard/toptalker" --%>
								<!-- 									class="dropdown-item">Top Talker</a></li> -->

								<%
									}
								%>
								<!-- <li class="dropdown-divider"></li> -->

								<%-- <li><a tabindex="-1"
									href="<%=request.getContextPath()%>/interfaceReport/groupSummary"
									class="dropdown-item">Group Summary</a></li>
									<li class="dropdown-divider"></li>

								<li><a tabindex="-1"
									href="<%=request.getContextPath()%>/interfaceReport/interfaceSummary"
									class="dropdown-item">Interface Summary</a></li> --%>

								<%
									if (dashboardScope.contains("Port_Summary")) {
								%>
								<li class="dropdown-divider"></li>

								<li><a tabindex="-1"
									href="<%=request.getContextPath()%>/interfaceReport/portSummary"
									class="dropdown-item">Port Summary</a></li>
								<!-- 								<li class="dropdown-divider"></li> -->

								<!-- 								<li><a tabindex="-1" -->
								<%-- 									href="<%=request.getContextPath()%>/dashboard/mapTopology" --%>
								<!-- 									class="dropdown-item">Map Topology</a></li> -->
								<%
									}
								%>
							</ul></li>
						<li class="nav-item dropdown"><a id="adminDropdownSubMenu"
							href="#" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" class="nav-link dropdown-toggle">Admin</a>
							<ul aria-labelledby="adminDropdownSubMenu"
								class="dropdown-menu border-0 shadow" style="width: max-content">
								<!-- Level two dropdown-->
								<%
									if (adminScope.contains("Master")) {
								%>
								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Master</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addUserForm"
											class="dropdown-item">Register User</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewUsers"
											class="dropdown-item">View Users</a></li>
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/master/manualTopologyForm" --%>
										<!-- 											class="dropdown-item">Manual Topology</a></li> -->
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/master/addIcmpform" --%>
										<!-- 											class="dropdown-item">ICMP Configuration</a></li> -->
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/master/viewIcmp" --%>
										<!-- 											class="dropdown-item">View ICMP Configuration</a></li> -->
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/companyMasterform"
											class="dropdown-item">Company Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewCompanyMaster"
											class="dropdown-item">View Company</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/locationMasterform"
											class="dropdown-item">Location Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewLocationMaster"
											class="dropdown-item">View Location</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addGroupForm"
											class="dropdown-item">Group Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addServiceProviderForm"
											class="dropdown-item">Service Provider Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addMakeModelForm"
											class="dropdown-item">Make & Model Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addDeviceTypeForm"
											class="dropdown-item">Device Type Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addSNMPConfigForm"
											class="dropdown-item">SNMP Configuration</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/smsConfiguration"
											class="dropdown-item">SMS Configuration</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/mailTemplate"
											class="dropdown-item">Mail Template</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addEmailConfigForm"
											class="dropdown-item">Email Configuration</a></li>
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/master/addADConfigForm" --%>
										<!-- 											class="dropdown-item">AD Configuration</a></li> -->
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/master/slaMaster" --%>
										<!-- 											class="dropdown-item">SLA Master</a></li> -->

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/slaMainMaster"
											class="dropdown-item">SLA Master</a></li>


										<li><a tabindex="-1" style="display: none;"
											href="<%=request.getContextPath()%>/node/nodeSpeedTest"
											class="dropdown-item">Speed Test</a></li>
									</ul></li>
								<%
									}
									if (adminScope.contains("Node")) {
								%>

								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Node</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/addNodeForm"
											class="dropdown-item">Add Node</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/bulkUploadForm"
											class="dropdown-item">Bulk Upload</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/viewAddNode"
											class="dropdown-item">View Node</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/addNodeParameterForm"
											class="dropdown-item">Set Node Parameter</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/viewNodeParameter"
											class="dropdown-item">View Node Parameter</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/availabilityParameter"
											class="dropdown-item">Availability Parameter</a></li>
										<%--
                      <li>
                        <a
                          tabindex="-1"
                          href="<%=request.getContextPath()%>/node/configBackup"
                          class="dropdown-item"
                          >Configuration Backup</a
                        >
                      </li>
                      --%>
										<%--
                      <li>
                        <a
                          tabindex="-1"
                          href="<%=request.getContextPath()%>/node/viewconfigBackupdata"
                          class="dropdown-item"
                          >View Configuration Backup</a
                        >
                      </li>
                      --%>
										<%--
                      <li>
                        <a
                          tabindex="-1"
                          href="<%=request.getContextPath()%>/node/nodeSpeedTest"
                          class="dropdown-item"
                          >Speed Test</a
                        >
                      </li>
                      --%>
									</ul></li>
								<%
									}
									if (adminScope.contains("Network_Discovery")) {
								%>
								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Network
										Discovery</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/nwIpScanForm"
											class="dropdown-item">IP Scan</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewIpScan"
											class="dropdown-item">View IP Scan</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/subnetScan"
											class="dropdown-item">Subnet Scan</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewSubnetScan"
											class="dropdown-item">View Subnet Scan</a></li>
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/master/ScheduleScan" --%>
										<!-- 											class="dropdown-item">Schedule Scan</a></li> -->
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/discoveredNetwork"
											class="dropdown-item">View Discovered Network</a></li>
									</ul></li>

								<%
									}
									if (adminScope.contains("Interface")) {
								%>

								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Interface</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/interfaceParameter"
											class="dropdown-item">Interface Parameter</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/viewInterfaceParameter"
											class="dropdown-item">View Interface Parameter</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/InterfaceIpAssign"
											class="dropdown-item">Assign Interface IP</a></li>
									</ul></li>

								<%
									}
									if (adminScope.contains("Service_Provisioning")) {
								%>
								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Service
										Provisioning</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/ping"
											class="dropdown-item">Ping Tool</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/traceRoute"
											class="dropdown-item">Trace Route</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/SSH"
											class="dropdown-item">Read Configuration</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/pushSSH"
											class="dropdown-item">Push Configuration</a></li>
									</ul></li>
								<%
									}
									if (adminScope.contains("Configuration_Management")) {
								%>
								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Configuration
										Management</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/configurationBackup"
											class="dropdown-item">Configuration Credential Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/configBackup"
											class="dropdown-item">Schedule Configuration</a></li>
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/node/ScheduleConfigurationLog" --%>
										<!-- 											class="dropdown-item">Schedule Configuration Log</a></li> -->
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/configTemplate"
											class="dropdown-item">Configuration Template</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/pushConfiguration"
											class="dropdown-item">Push Configuration</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/pushConfigurationReport"
											class="dropdown-item">Push Configuration Log</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/ping"
											class="dropdown-item">Read</a></li>
									</ul></li>
								<%
									}
									if (adminScope.contains("Settings")) {
								%>
								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Settings</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/userManageScopeForm"
											class="dropdown-item">Manage User Scope</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewUserManageScope"
											class="dropdown-item">Manage UserScope view</a></li>
									</ul></li>

								<%
									}
								%>

								<!-- End Level two -->
							</ul></li>
						<%-- <li class="nav-item dropdown"><a id="adminDropdownSubMenu"
							href="#" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" class="nav-link dropdown-toggle">Server
								Monitoring</a>
							<ul aria-labelledby="dashboardDropdownSubMenu"
								class="dropdown-menu border-0 shadow">
								<li><a
									href="<%=request.getContextPath()%>/dashboard/serverMonitoringDashboard"
									class="dropdown-item">Dashboard</a></li>
									<li><a
									href="<%=request.getContextPath()%>/dashboard/serviceMonitoringDashboard"
									class="dropdown-item">Service Monitoring</a></li>
									<li><a
									href="<%=request.getContextPath()%>/dashboard/processMonitoringDashboard"
									class="dropdown-item">Prosess Monitoring</a></li>
									<li><a
									href="<%=request.getContextPath()%>/dashboard/driveMonitoringDashboard"
									class="dropdown-item">Drive Details</a></li>
							</ul></li> --%>

						<li class="nav-item dropdown"><a id="reportDropdownSubMenu"
							href="#" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" class="nav-link dropdown-toggle">Report</a>
							<ul aria-labelledby="adminDropdownSubMenu"
								class="dropdown-menu border-0 shadow" style="width: max-content">
								<%
									if (reportScope.contains("Node_Report")) {
								%>

								<!-- 								server dropdown -->
								<%-- 		<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Server
										Report</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/deviceStatus"
											class="dropdown-item">Node Status Report</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/windowsHardwareInventory"
											class="dropdown-item">Windows Hardware Inventory</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/windowsServices"
											class="dropdown-item">Windows Services</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/linuxHardwareInventory"
											class="dropdown-item">Linux Hardware Inventory</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/linuxSoftwareInventory"
											class="dropdown-item">Linux Software Inventory</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/linuxServices"
											class="dropdown-item">Linux Services Monitoring</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/averageHealthReportForm"
											class="dropdown-item">Average Health Report</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeAvailabilityForm"
											class="dropdown-item">Node Availability</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeHealthHistoryForm"
											class="dropdown-item">Node Health History</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/cpuThresholdHistoryForm"
											class="dropdown-item">CPU Threshold History</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/memoryThresholdHistoryForm"
											class="dropdown-item">Memory Threshold History</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/driveThreshold"
											class="dropdown-item">Drive Threshold</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeHealthMonitoringView"
											class="dropdown-item">Node Health Monitoring</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeMonitoringView"
											class="dropdown-item">Node Monitoring</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/serviceReport"
											class="dropdown-item">Service Report</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/avgAvailabilityReportForm"
											class="dropdown-item">Avg Availability Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/availabilityReportForm"
											class="dropdown-item">Availability Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/avgUptimeForm"
											class="dropdown-item">Average Uptime Report</a></li>


									</ul></li> --%>

								<!-- Level two dropdown-->
								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Node
										Report</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/userReport"
											class="dropdown-item">User Log Report</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/deviceStatus"
											class="dropdown-item">Node Status Report</a></li>
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/latencyHistoryReportForm" --%>
										<!-- 											class="dropdown-item">Latency History Report</a></li> -->
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/latencyThresholdReportForm"
											class="dropdown-item">Latency Threshold Report</a></li>
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/avarageLatencyReportForm" --%>
										<!-- 											class="dropdown-item">Average Latency Report</a></li> -->
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/averageHealthReportForm" --%>
										<!-- 											class="dropdown-item">Average Health Report</a></li> -->
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeAvailabilityForm"
											class="dropdown-item">Node Availability</a></li>
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/nodeHealthHistoryForm" --%>
										<!-- 											class="dropdown-item">Node Health History</a></li> -->
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/cpuThresholdHistoryForm" --%>
										<!-- 											class="dropdown-item">CPU Threshold History</a></li> -->
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/memoryThresholdHistoryForm" --%>
										<!-- 											class="dropdown-item">Memory Threshold History</a></li> -->
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/driveThreshold" --%>
										<!-- 											class="dropdown-item">Drive Threshold</a></li> -->
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/nodeHealthMonitoringView" --%>
										<!-- 											class="dropdown-item">Node Health Monitoring</a></li> -->
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeMonitoringView"
											class="dropdown-item">Node Monitoring</a></li>
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/serviceReport" --%>
										<!-- 											class="dropdown-item">Service Report</a></li> -->

										<%--
                      <li>
                        <a
                          tabindex="-1"
                          href="<%=request.getContextPath()%>/nodeReport/deviceAlertLog"
                          class="dropdown-item"
                          >Device Alert Log</a
                        >
                      </li>
                      --%>

										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/slaReportForm" --%>
										<!-- 											class="dropdown-item">SLA Report</a></li> -->

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/syslogReportForm"
											class="dropdown-item">Syslog Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/avgAvailabilityReportForm"
											class="dropdown-item">Avg Availability Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/availabilityReportForm"
											class="dropdown-item">Availability Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/avgUptimeForm"
											class="dropdown-item">Average Uptime Report</a></li>

										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/connectedDevices" --%>
										<!-- 											class="dropdown-item">Node Connected Devices</a></li> -->
									</ul></li>
								<%
									}
									if (reportScope.contains("Interface_Report")) {
								%>

								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Interface
										Report</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/interfaceStatusReportForm"
											class="dropdown-item">Interface Status Report</a></li>

										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/interfaceReport/latencyinterfaceReportForm" --%>
										<!-- 											class="dropdown-item">Interface Latency Report</a></li> -->
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/interfaceBandwidthHistoryForm"
											class="dropdown-item">Interface Bandwidth History</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/bandwidthThresholdHistoryForm"
											class="dropdown-item">Bandwidth Threshold History</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/averageBandwidthReportForm"
											class="dropdown-item">Average Bandwidth Report</a></li>
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/interfaceReport/interfaceCrcHistoryForm" --%>
										<!-- 											class="dropdown-item">Interface CRC History</a></li> -->
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/interfaceAvailabilityForm"
											class="dropdown-item">Interface Availability</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/interfaceMonitoringView"
											class="dropdown-item">Interface Monitoring</a></li>
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/interfaceReport/interfaceJitterReportForm" --%>
										<!-- 											class="dropdown-item">Interface Jitter Report</a></li> -->
									</ul></li>


								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Working
										Hour Report</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/latencyStatusHistoryReportForm"
											class="dropdown-item">Device Latency History Status
												Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/latencyStatusHistoryReportFormFilter"
											class="dropdown-item">Device Latency History Status
												(Filter) Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/avgLatencyStatusHistoryReportForm"
											class="dropdown-item">Device Average Latency Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/workingHoursAvailabilityReportForm"
											class="dropdown-item">Device Availability Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/sla"
											class="dropdown-item">Device SLA Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/LinkStatusreportForm"
											class="dropdown-item">Link Status Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/LinkLatencyReportForm"
											class="dropdown-item">Link Latency Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/LinkLatencyReportFormFilter"
											class="dropdown-item">Link Latency (Filter) Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/LinkaverageLatencyReportForm"
											class="dropdown-item">Link average Latency Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/LinkAvailabilityReportForm"
											class="dropdown-item">Link Availability Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/InterfaceSlaReportForm"
											class="dropdown-item">Link SLA report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/LinkBandwidthHistoryReportForm"
											class="dropdown-item">Link Bandwidth History Report</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/LinkAVGBandwidthReportForm"
											class="dropdown-item">Link AVG Bandwidth Report</a></li>

									</ul></li>




								<%
									}
									if (reportScope.contains("Top_Talker_Report")) {
								%>

								<%-- <li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Top
										Talker Report</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/toptTalkerReport/topTalkerSourceWiseForm"
											class="dropdown-item">Top Talker IP Wise</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/toptTalkerReport/topTalkerForm"
											class="dropdown-item">Top Talker</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/toptTalkerReport/topConnectionWiseForm"
											class="dropdown-item">Top Connection Wise</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/toptTalkerReport/topTalkerProtocolWiseForm"
											class="dropdown-item">Top Protocol Wise</a></li>
									</ul></li> --%>

								<%-- 	<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Event
										Report</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/windowsEvent"
											class="dropdown-item">Windows Events</a></li>
									</ul></li> --%>
								<%
									}
								%>

								<!-- End Level two -->
							</ul></li>

						<li class="nav-item dropdown"><a id="reportDropdownSubMenu"
							href="#" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" class="nav-link dropdown-toggle">Graph</a>
							<ul aria-labelledby="adminDropdownSubMenu"
								class="dropdown-menu border-0 shadow">
								<%
									if (graphScope.contains("Node_Graph")) {
								%>
								<!-- Level two dropdown-->
								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Node
										Graph</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<!-- 										<li><a tabindex="-1" -->
										<%-- 											href="<%=request.getContextPath()%>/nodeReport/nodeHealthHistoryGraphForm" --%>
										<!-- 											class="dropdown-item">Node Health History Graph</a></li> -->
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeAvailabilityGraphForm"
											class="dropdown-item">Node Availability Graph</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeAvailabilityAverageGraphForm"
											class="dropdown-item">Node Availability Average Graph</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/latencyHisotryGraphForm"
											class="dropdown-item">Average Latency Graph</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/latencyGraphForm"
											class="dropdown-item">Latency Graph</a></li>
									</ul></li>
								<%
									}
									if (graphScope.contains("Interface_Graph")) {
								%>

								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Interface
										Graph</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/interfaceBandwidthHistoryGraphForm"
											class="dropdown-item">Average Interface Bandwidth History
												Graph</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/interfaceAvailabilityGraphForm"
											class="dropdown-item">Interface Availability Graph</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/interfaceAvailabilityAverageGraphForm"
											class="dropdown-item">Interface Availability Average
												Graph</a></li>
									</ul></li>
								<%
									}
								%>

								<!-- End Level two -->
							</ul></li>
					</ul>

					<!-- SEARCH FORM -->
					<!-- <form class="form-inline ml-0 ml-md-3">
						<div class="input-group input-group-sm">
							<input class="form-control form-control-navbar" type="search"
								placeholder="Search" aria-label="Search">
							<div class="input-group-append">
								<button class="btn btn-navbar" type="submit">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</div>
					</form> -->
				</div>

				<!-- Right navbar links -->
				<ul class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
					<li class="nav-item"><a class="nav-link"
						data-widget="navbar-search" href="#" role="button"> <i
							class="fas fa-search"></i>
					</a>
						<div class="navbar-search-block">
							<form class="form-inline" method="post" id="searchDataNode">
								<div class="input-group input-group-sm">
									<select type="text" id='searchDataOption'
										name='searchDataOption' class="form-control col-4"
										style="background-color: #f5f5f5; border-color: #e8e8e8;">
										<option value="">Please Select</option>
										<option value="nameSearch">Device Name</option>
										<option value="ipAddressSearch">IP Address</option>
										<option value="groupNameSearch">Group Name</option>
										<option value="deviceTypeSearch">Device Type</option>
									</select> <input class="form-control form-control-navbar" type="search"
										id="searchNodeData" name="searchNodeData" placeholder="Search"
										aria-label="Search">
									<div class="input-group-append">
										<button class="btn btn-navbar" id="searchDataModal"
											type="submit">
											<i class="fas fa-search"></i>
										</button>
										<button class="btn btn-navbar" type="button"
											data-widget="navbar-search">
											<i class="fas fa-times"></i>
										</button>
									</div>
								</div>
							</form>
						</div></li>
					<!-- Messages Dropdown Menu -->

					<!-- Notifications Dropdown Menu -->
					<!--  	<li class="nav-item dropdown"><a class="nav-link"
						data-toggle="dropdown" href="#"> <i class="far fa-bell"></i> <span
							class="badge badge-warning navbar-badge">15</span>
					</a>
						<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
							<span class="dropdown-header">15 Notifications</span>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item"> <i
								class="fas fa-envelope mr-2"></i> 4 new messages <span
								class="float-right text-muted text-sm">3 mins</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item"> <i
								class="fas fa-users mr-2"></i> 8 friend requests <span
								class="float-right text-muted text-sm">12 hours</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item"> <i
								class="fas fa-file mr-2"></i> 3 new reports <span
								class="float-right text-muted text-sm">2 days</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item dropdown-footer">See All
								Notifications</a>
						</div></li>
						-->

					<!-- 					<li class="nav-item"><a class="nav-link" -->
					<!-- 						data-widget="control-sidebar" data-slide="true" href="#" -->
					<!-- 						role="button"> <i class="fas fa-th-large"></i> -->
					<!-- 					</a></li> -->
					<li class="nav-item dropdown"><a id="reportDropdownSubMenu"
						href="#" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false" class="nav-link dropdown-toggle"><i
							class="fa fa-user"></i>&nbsp;&nbsp;<%=sessionUser%></a>
						<ul aria-labelledby="dashboardDropdownSubMenu"
							class="dropdown-menu border-0 shadow">
							<li><a href="<%=request.getContextPath()%>"
								class="dropdown-item" onclick='logoutsaveonclick()'>Logout</a></li>
						</ul></li>
					<span id="sessionUserstore" style="display: none;"><%=sessionUser%></span>
					<%-- 					<span id="ContextPathId" style="display: none;"><%=request.getContextPath()%></span> --%>
				</ul>
			</div>
		</nav>
		<!-- /.navbar -->
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->
	<!-- 	<script -->
	<%-- 		src="<%=request.getContextPath()%>/custom_js/summaryDashboard.js"></script> --%>
	<script>
	


	
	
    // Check if the session variable is null or undefined
    if ("<%=sessionUser%>" === null || "<%=sessionUser%>
		" === undefined) {
			// Redirect to index.jsp
			window.location.href = "index.jsp";
		}
	</script>
</body>
</html>
