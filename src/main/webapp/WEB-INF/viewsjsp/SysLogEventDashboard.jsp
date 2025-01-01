<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Event Dashboard | Home</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/webtemplate/dist/img/AdminLogo.png">
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">
<!-- Select2 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/select2/css/select2.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bs-stepper/css/bs-stepper.min.css">
<!-- dropzonejs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/dropzone/min/dropzone.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
<!-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatablesCSS/jquery.dataTables.css">-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.css">
<!-- DataTables -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<style>
.daterangepicker.ltr.show-ranges.opensright.show-calendar {
	color: initial;
}

.highcharts-figure, .highcharts-data-table table {
	min-width: 100%;
	max-width: 800px;
	margin: 0em auto;
}

/* #container1 {
	height: 300px;
}

#container2 {
	height: 300px;
}

#windowsEventTrend {
	height: 300px;
}

#syslogEventTrend {
	height: 300px;
} */
.highcharts-credits {
	display: none;
}

.highcharts-data-table table {
	font-family: Verdana, sans-serif;
	border-collapse: collapse;
	border: 1px solid #ebebeb;
	margin: 10px auto;
	text-align: center;
	width: 100%;
	max-width: 500px;
}

.highcharts-data-table caption {
	padding: 1em 0;
	font-size: 1.2em;
	color: #555;
}

.highcharts-data-table th {
	font-weight: 600;
	padding: 0.5em;
}

.highcharts-data-table td, .highcharts-data-table th,
	.highcharts-data-table caption {
	padding: 0.5em;
}

.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even)
	{
	background: #f8f8f8;
}

.highcharts-data-table tr:hover {
	background: #f1f7ff;
}

.highcharts-title {
	display: none;
}

.row {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
}

.info-box-content {
	flex-basis: 40%; /* Adjust the width as needed */
	margin-right: 10px; /* Add margin between elements */
	padding: 10px; /* Add padding for spacing */
}

canvas {
	flex-basis: 60%;
	max-width: 50%;
}

.ranges {
	color: black;
}

.flex-container {
	display: flex;
	flex-direction: row; /* Default value, but included for clarity */
	list-style: none;
	flex-wrap: wrap;
	padding: 0;
}

.flex-container ul {
	display: flex;
	flex-direction: row;
	padding: 0;
	flex-wrap: wrap;
}

.flex-container li {
	margin-right: 11px;
	margin-left: 15px;
	margin-top: 19px;
	box-sizing: border-box;
}

.card-header {
	background-color: dodgerblue;
}
</style>
</head>
<body
	class="hold-transition layout-top-nav layout-fixed layout-navbar-fixed layout-footer-fixed"
	style="height: auto;">

	<div class="wrapper">
		<jsp:include page="header.jsp" />
		<div class="content-wrapper">


			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<c:choose>
								<c:when test="${userId==null}">
									<h1>Overview</h1>
								</c:when>
								<c:otherwise>
									<h1>Edit User</h1>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<!-- <li class="breadcrumb-item"><a href="#">Home</a></li> -->
								<c:choose>
									<c:when test="${userId==null}">
										<div class="form-group">
											<div class="input-group">
												<button type="button" class="btn btn-default float-right"
													id="daterange-btn" name="daterange-btn">
													<i class="far fa-calendar-alt"></i> Date range picker <i
														class="fas fa-caret-down"></i>
												</button>
												<input type="text" id='from_date' name='from_date'
													class="form-control" readonly /> <input type="text"
													id='to_date' name='to_date' class="form-control" readonly />
												<button type="button" class="btn btn-default float-right"
													onclick="dashboardFilter()">
													<i class="fas fa-sync-alt" aria-hidden="false"></i>
												</button>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<li class="breadcrumb-item active">Edit User</li>
									</c:otherwise>
								</c:choose>

							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12 col-sm-6">
							<div class="info-box">
								<!-- 	<span class="info-box-icon bg-success elevation-1"><i
									class="fa fa-windows" style="color: white;"></i></span> -->

								<div class="info-box-content" style="min-height: 148px;">
									<span class="info-box-text">All Events</span> <span
										class="info-box-number" id="allEventsCount"></span>

								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->
						</div>
						<div class="col-12 col-sm-6">
							<div class="info-box mb-3">
								<!-- <span class="info-box-icon bg-warning elevation-1"><i
									class="fas fa-server"></i></span> -->

								<div class="info-box-content" style="min-height: 148px;">
									<span class="info-box-text">Syslog Event</span> <span
										class="info-box-number" id="syslogEventsCount"
										data-toggle="modal" data-target="#syslog-event-modal"
										onclick="syslogEventList()"></span>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->
						</div>
						<!-- /.col -->
						<!-- 						<div class="col-12 col-sm-6 col-md-6"> -->
						<!-- 							<div class="info-box mb-3" style="min-height: 164px;"> -->
						<!-- 								<span class="info-box-icon bg-danger elevation-1"><i
<!-- 									class="fa fa-windows"></i></span> -->

						<!-- 								<div style="display: flex; flex-direction: column; width: 100%;"> -->
						<!-- 									<div class="row" style="width: 100%;"> -->
						<!-- 										Info Box -->
						<!-- 																				<div class="info-box-content"> -->
						<!-- 																					<span class="info-box-text">Windows Event</span> <span -->
						<!-- 																						class="info-box-number" id="windowsEventsCount" -->
						<!-- 																						onclick="windowsSecurityEvent()"></span> -->
						<!-- 																				</div> -->

						<!-- 										Canvas for the pie chart -->
						<%-- 										<canvas id="linkpieChart1" --%>
						<%-- 											style="min-height: 90px; height: 70px; max-height: 70px;"></canvas> --%>
						<!-- 									</div> -->
						<!-- 									<div class="flex-container" -->
						<!-- 										style="width: 100%; padding-left: 2px;"> -->
						<!-- 										<ul id="myList"> -->
						<!-- 										</ul> -->
						<!-- 									</div> -->
						<!-- 								</div> -->
						<!-- 								/.info-box-content -->
						<!-- 							</div> -->
						<!-- 							/.info-box -->
						<!-- 						</div> -->
						<!-- /.col -->

						<!-- fix for small devices only -->
						<div class="clearfix hidden-md-up"></div>


						<!-- /.col -->
						<!-- 						<div class="col-12 col-sm-6 col-md-2"> -->
						<!-- 							<div class="info-box mb-3"> -->
						<!-- 								<span class="info-box-icon bg-info elevation-1"><i
<!-- 									class="fas fa-server"></i></span> -->
						-->

						<!-- 																<div class="info-box-content" style="min-height: 148px;"> -->
						<!-- 																	<span class="info-box-text">All Devices</span> <span -->
						<!-- 																		class="info-box-number" id="allDevicesCount" -->
						<!-- 																		onclick="allDevicesList()"></span> -->
						<!-- 																</div> -->
						<!-- 								/.info-box-content -->
						<!-- 							</div> -->
						<!-- 							/.info-box -->
						<!-- 						</div> -->
						<!-- /.col -->
					</div>
				</div>
				<div class="modal fade" id="windows-event-modal"
					style="height: 500px;">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Windows Events</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body" id="portSummaryBody">
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										id="windowsEventTable">
										<thead>
											<tr>
												<!-- 		<th>Event Data</th> -->
												<th>LOG ID</th>
												<th>DEVICE IP</th>
												<th>DEVICE NAME</th>
												<th>EVENT ID</th>
												<th>EVENT TYPE</th>
												<th>EVENT SEVERITY</th>
												<th>EVENT NAME</th>
												<th>EVENT CATEGORY</th>
												<th>EVENT SOURCE</th>
												<th>EVENT MESSAGE</th>
												<th>Event Level</th>
												<th>EVENT TIME</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>

						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>

				<div class="modal fade" id="devices-list-modal"
					style="height: 500px;">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">All Devices</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body" id="portSummaryBody">
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										id="devicesListTable">
										<thead>
											<tr>
												<th>Sr. No</th>
												<th>Device IP</th>
												<th>Device Status</th>
												<th>Host Name</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>

						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>


				<div class="modal fade" id="syslog-event-modal"
					style="height: 500px;">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Syslog Event</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body" id="portSummaryBody">
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										id="syslogEventTable">
										<thead>
											<tr>
												<th>Sr. No</th>
												<th>device_ip</th>
												<!-- <th>syslog_severity</th>
												<th>syslog_facility</th>
												<th>syslog_type</th>
												<th>syslog_subtype</th>
												<th>syslog_source_ip</th>
												<th>syslog_source_port</th>
												<th>syslog_destination_ip</th>
												<th>syslog_destination_port</th>
												<th>syslog_protocol</th>
												<th>syslog_datasource</th>
												<th>syslog_param</th>
												<th>syslog_value</th> -->
												<th>syslog_rawmsg</th>
												<!-- <th>syslog_msg</th>
												<th>syslog_threat</th> -->
												<!-- 	<th>syslog_time</th>
												<th>syslog_date</th> -->
												<th>event_time</th>
												<!-- <th>syslog_level</th> -->
												<!-- <th>device_name</th>
												<th>device_id</th> -->
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>

						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>

				<div class="modal fade" id="syslogSeverityModal"
					style="height: 500px;">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Syslog Event</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body" id="portSummaryBody">
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										id="syslogSeverityTable">
										<thead>
											<tr>
												<th>Sr. No</th>
												<th>device_ip</th>
												<!-- <th>syslog_severity</th>
												<th>syslog_facility</th>
												<th>syslog_type</th>
												<th>syslog_subtype</th>
												<th>syslog_source_ip</th>
												<th>syslog_source_port</th>
												<th>syslog_destination_ip</th>
												<th>syslog_destination_port</th>
												<th>syslog_protocol</th>
												<th>syslog_datasource</th>
												<th>syslog_param</th>
												<th>syslog_value</th> -->
												<th>syslog_rawmsg</th>
												<!-- <th>syslog_msg</th>
												<th>syslog_threat</th> -->
												<!-- 	<th>syslog_time</th>
												<th>syslog_date</th> -->
												<th>event_time</th>
												<!-- <th>syslog_level</th> -->
												<!-- <th>device_name</th>
												<th>device_id</th> -->
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>

						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<div class="modal fade" id="syslogTypeModal" style="height: 500px;">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Syslog Event</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body" id="portSummaryBody">
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										id="syslogTypeTable" style="width: fit-content;">
										<thead>
											<tr>
												<th>Sr. No</th>
												<th>device_ip</th>
												<th>syslog_severity</th>
												<th>syslog_facility</th>
												<th>syslog_type</th>
												<th>syslog_subtype</th>


												<!-- 												<th>syslog_source_ip</th> -->
												<!-- 												<th>syslog_source_port</th> -->
												<!-- 												<th>syslog_destination_ip</th> -->
												<!-- 												<th>syslog_destination_port</th> -->
												<!-- 												<th>syslog_protocol</th> -->
												<!-- 												<th>syslog_datasource</th> -->
												<!-- 												<th>syslog_param</th> -->
												<!-- 												<th>syslog_value</th> -->


												<th>syslog_rawmsg</th>
												<th>syslog_msg</th>
												<th>syslog_threat</th>
												<th>syslog_time</th>
												<th>syslog_date</th>
												<th>event_time</th>
												<th>syslog_level</th>


												<!-- 												<th>device_name</th> -->
												<!-- 												<th>device_id</th> -->
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>

						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
			</section>
			<!-- 			<section class="content"> -->
			<!-- 				<div class="container-fluid"> -->
			<!-- 					<div class="row"> -->
			<!-- 						<div class="col-md-6"> -->
			<!-- 							PIE CHART -->
			<!-- 							<div class="card border-transparent"> -->
			<!-- 								<div class="card-header"> -->
			<!-- 									<h3 class="card-title">Windows Severity Graph</h3> -->

			<!-- 									<div class="card-tools"> -->
			<!-- 										<button type="button" class="btn btn-tool" -->
			<!-- 											data-card-widget="collapse"> -->
			<!-- 											<i class="fas fa-minus"></i> -->
			<!-- 										</button> -->
			<!-- 										<button type="button" class="btn btn-tool" -->
			<!-- 											data-card-widget="remove"> -->
			<!-- 											<i class="fas fa-times"></i> -->
			<!-- 										</button> -->
			<!-- 									</div> -->
			<!-- 								</div> -->
			<!-- 								<div class="card-body"> -->
			<!-- 									<figure class="highcharts-figure"> -->
			<!-- 										<div id="container1" -->
			<!-- 											style="min-height: 290px; height: 290px; max-height: 290px; max-width: 100%;"></div> -->
			<!-- 									</figure> -->
			<!-- 								</div> -->
			<!-- 								/.card-body -->
			<!-- 							</div> -->
			<!-- 							/.card -->
			<!-- 						</div> -->
			<!-- 						<div class="col-md-6"> -->
			<!-- 							PIE CHART -->
			<!-- 							<div class="card border-transparent"> -->
			<!-- 								<div class="card-header"> -->
			<!-- 									<h3 class="card-title">Windows Log Type Wise Bar Chart</h3> -->

			<!-- 									<div class="card-tools"> -->
			<!-- 										<button type="button" class="btn btn-tool" -->
			<!-- 											data-card-widget="collapse"> -->
			<!-- 											<i class="fas fa-minus"></i> -->
			<!-- 										</button> -->
			<!-- 										<button type="button" class="btn btn-tool" -->
			<!-- 											data-card-widget="remove"> -->
			<!-- 											<i class="fas fa-times"></i> -->
			<!-- 										</button> -->
			<!-- 									</div> -->
			<!-- 								</div> -->
			<!-- 								<div class="card-body"> -->
			<!-- 									<figure class="highcharts-figure"> -->
			<!-- 										<div id="container2" -->
			<!-- 											style="min-height: 290px; height: 290px; max-height: 290px; max-width: 100%;"></div> -->
			<!-- 									</figure> -->
			<!-- 								</div> -->
			<!-- 								/.card-body -->
			<!-- 							</div> -->
			<!-- 							/.card -->
			<!-- 						</div> -->
			<!-- 					</div> -->
			<!-- 				</div> -->
			<!-- 			</section> -->


			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<!-- 						<div class="col-md-6"> -->
						<!-- 							PIE CHART -->
						<!-- 							<div class="card border-transparent"> -->
						<!-- 								<div class="card-header"> -->
						<!-- 									<h3 class="card-title">Windows Event Trends</h3> -->

						<!-- 									<div class="card-tools"> -->
						<!-- 										<button type="button" class="btn btn-tool" -->
						<!-- 											data-card-widget="collapse"> -->
						<!-- 											<i class="fas fa-minus"></i> -->
						<!-- 										</button> -->
						<!-- 										<button type="button" class="btn btn-tool" -->
						<!-- 											data-card-widget="remove"> -->
						<!-- 											<i class="fas fa-times"></i> -->
						<!-- 										</button> -->
						<!-- 									</div> -->
						<!-- 								</div> -->
						<!-- 								<div class="card-body"> -->
						<!-- 									<figure class="highcharts-figure"> -->
						<!-- 										<div id="windowsEventTrend" -->
						<!-- 											style="min-height: 290px; height: 290px; max-height: 290px; max-width: 100%;"></div> -->
						<!-- 									</figure> -->
						<!-- 								</div> -->
						<!-- 								/.card-body -->
						<!-- 							</div> -->
						<!-- 							/.card -->
						<!-- 						</div> -->

						<div class="col-md-6">
							<!-- PIE CHART -->
							<div class="card border-transparent">
								<div class="card-header">
									<h3 class="card-title">Syslog Event Trends</h3>

									<div class="card-tools">
										<button type="button" class="btn btn-tool"
											data-card-widget="collapse">
											<i class="fas fa-minus"></i>
										</button>
										<button type="button" class="btn btn-tool"
											data-card-widget="remove">
											<i class="fas fa-times"></i>
										</button>
									</div>
								</div>
								<div class="card-body">
									<figure class="highcharts-figure">
										<div id="syslogEventTrend"
											style="min-height: 290px; height: 290px; max-height: 290px; max-width: 100%;"></div>
									</figure>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>

						<div class="col-md-6">
							<!-- TABLE: LATEST ORDERS -->
							<div class="card" style="min-height: 380px;">
								<div class="card-header border-transparent">
									<h3 class="card-title">Syslog Severity</h3>

									<div class="card-tools">
										<button type="button" class="btn btn-tool"
											data-card-widget="collapse">
											<i class="fas fa-minus"></i>
										</button>
										<button type="button" class="btn btn-tool"
											data-card-widget="remove">
											<i class="fas fa-times"></i>
										</button>
									</div>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<div id="syslogSeverity"></div>
									<%-- <canvas id="syslogSeverity"
										style="min-height: 290px; height: 290px; max-height: 290px; max-width: 100%;"></canvas> --%>
								</div>
								<!-- /.card-body -->

							</div>
							<!-- /.card -->
						</div>
					</div>
				</div>
			</section>
















			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<!-- 						<div class="col-md-6"> -->
						<!-- 							TABLE: LATEST ORDERS -->
						<!-- 							<div class="card" style="min-height: 380px;"> -->
						<!-- 								<div class="card-header border-transparent"> -->
						<!-- 									<h3 class="card-title">OS Wise Summary</h3> -->

						<!-- 									<div class="card-tools"> -->
						<!-- 										<button type="button" class="btn btn-tool" -->
						<!-- 											data-card-widget="collapse"> -->
						<!-- 											<i class="fas fa-minus"></i> -->
						<!-- 										</button> -->
						<!-- 										<button type="button" class="btn btn-tool" -->
						<!-- 											data-card-widget="remove"> -->
						<!-- 											<i class="fas fa-times"></i> -->
						<!-- 										</button> -->
						<!-- 									</div> -->
						<!-- 								</div> -->
						<!-- 								/.card-header -->
						<!-- 								<div class="card-body"> -->


						<!-- 									<table id="linkSummary" -->
						<!-- 										class="table table-bordered table-striped"> -->
						<!-- 										<thead> -->
						<!-- 											<tr> -->
						<!-- 												<th>OS Name</th> -->
						<!-- 												<th>Total</th> -->
						<!-- 												<th>Up</th> -->
						<!-- 												<th>Down</th> -->
						<!-- 											</tr> -->
						<!-- 										</thead> -->
						<!-- 										<tbody> -->

						<!-- 										</tbody> -->

						<!-- 									</table> -->


						<!-- 								</div> -->
						<!-- 								/.card-body -->

						<!-- 							</div> -->
						<!-- 							/.card -->
						<!-- 						</div> -->
						<div class="modal fade" id="port-info-modal"
							style="height: 500px;">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">Os wise Summary</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body" id="portSummaryBody">
										<div class="card-body table-responsive p-0">
											<table class="table table-striped table-valign-middle"
												id="portSummaryTable">
												<thead>
													<tr>
														<th>OS Name</th>
														<th>Device Ip</th>
														<th>Status</th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>

								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>

						<!-- 						<div class="col-md-6"> -->
						<!-- 							TABLE: LATEST ORDERS -->
						<!-- 							<div class="card" style="min-height: 380px;"> -->
						<!-- 								<div class="card-header border-transparent"> -->
						<!-- 									<h3 class="card-title">Event Name Wise Summary</h3> -->

						<!-- 									<div class="card-tools"> -->
						<!-- 										<button type="button" class="btn btn-tool" -->
						<!-- 											data-card-widget="collapse"> -->
						<!-- 											<i class="fas fa-minus"></i> -->
						<!-- 										</button> -->
						<!-- 										<button type="button" class="btn btn-tool" -->
						<!-- 											data-card-widget="remove"> -->
						<!-- 											<i class="fas fa-times"></i> -->
						<!-- 										</button> -->
						<!-- 									</div> -->
						<!-- 								</div> -->
						<!-- 								/.card-header -->
						<!-- 								<div class="card-body"> -->


						<!-- 									<table id="eventNameSummary" -->
						<!-- 										class="table table-bordered table-striped"> -->
						<!-- 										<thead> -->
						<!-- 											<tr> -->
						<!-- 												<th>Event Name</th> -->
						<!-- 												<th>Count</th> -->
						<!-- 											</tr> -->
						<!-- 										</thead> -->
						<!-- 										<tbody> -->

						<!-- 										</tbody> -->

						<!-- 									</table> -->


						<!-- 								</div> -->
						<!-- 								/.card-body -->

						<!-- 							</div> -->
						<!-- 							/.card -->
						<!-- 						</div> -->
						<div class="modal fade" id="eventName-info-modal"
							style="height: 500px;">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">Event Name wise Summary</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body" id="eventSummaryBody">
										<div class="card-body table-responsive p-0">
											<table class="table table-striped table-valign-middle"
												id="eventSummaryTable">
												<thead>
													<tr>
														<th>ID</th>
														<th>LOG ID</th>
														<th>DEVICE IP</th>
														<th>DEVICE NAME</th>
														<th>EVENT ID</th>
														<th>EVENT TYPE</th>
														<th>EVENT SEVERITY</th>
														<th>EVENT NAME</th>
														<th>EVENT CATEGORY</th>
														<th>EVENT SOURCE</th>
														<th>EVENT MESSAGE</th>
														<th>Event Level</th>
														<th>EVENT TIME</th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>

								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
					</div>
				</div>
			</section>
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<!-- 						<div class="col-md-6"> -->
						<!-- 							TABLE: LATEST ORDERS -->
						<!-- 							<div class="card" style="min-height: 380px;"> -->
						<!-- 								<div class="card-header border-transparent"> -->
						<!-- 									<h3 class="card-title">Source Wise Summary</h3> -->

						<!-- 									<div class="card-tools"> -->
						<!-- 										<button type="button" class="btn btn-tool" -->
						<!-- 											data-card-widget="collapse"> -->
						<!-- 											<i class="fas fa-minus"></i> -->
						<!-- 										</button> -->
						<!-- 										<button type="button" class="btn btn-tool" -->
						<!-- 											data-card-widget="remove"> -->
						<!-- 											<i class="fas fa-times"></i> -->
						<!-- 										</button> -->
						<!-- 									</div> -->
						<!-- 								</div> -->
						<!-- 								/.card-header -->
						<!-- 								<div class="card-body"> -->


						<!-- 									<table id="sourceWiseSummary" -->
						<!-- 										class="table table-bordered table-striped"> -->
						<!-- 										<thead> -->
						<!-- 											<tr> -->
						<!-- 												<th>Source</th> -->
						<!-- 												<th>Success</th> -->
						<!-- 												<th>Error</th> -->
						<!-- 												<th>Warning</th> -->
						<!-- 												<th>Information</th> -->
						<!-- 												<th>Failure</th> -->
						<!-- 											</tr> -->
						<!-- 										</thead> -->
						<!-- 										<tbody> -->

						<!-- 										</tbody> -->

						<!-- 									</table> -->


						<!-- 								</div> -->
						<!-- 								/.card-body -->

						<!-- 							</div> -->
						<!-- 							/.card -->
						<!-- 						</div> -->

						<div class="modal fade" id="source-info-modal"
							style="height: 500px;">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">Source wise Summary</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body" id="eventSummaryBody">
										<div class="card-body table-responsive p-0">
											<table class="table table-striped table-valign-middle"
												id="sourceSummaryTable">
												<thead>
													<tr>
														<th>ID</th>
														<th>LOG ID</th>
														<th>DEVICE IP</th>
														<th>DEVICE NAME</th>
														<th>EVENT ID</th>
														<th>EVENT TYPE</th>
														<th>EVENT SEVERITY</th>
														<th>EVENT NAME</th>
														<th>EVENT CATEGORY</th>
														<th>EVENT SOURCE</th>
														<th>EVENT MESSAGE</th>
														<th>Event Level</th>
														<th>EVENT TIME</th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>

								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<!-- TABLE: LATEST ORDERS -->
							<div class="card" style="min-height: 380px;">
								<div class="card-header border-transparent">
									<h3 class="card-title">Syslog Type</h3>

									<div class="card-tools">
										<button type="button" class="btn btn-tool"
											data-card-widget="collapse">
											<i class="fas fa-minus"></i>
										</button>
										<button type="button" class="btn btn-tool"
											data-card-widget="remove">
											<i class="fas fa-times"></i>
										</button>
									</div>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<div id="syslogType"></div>
								</div>
								<!-- /.card-body -->

							</div>
							<!-- /.card -->
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/moment/moment.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- jquery-validation -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/additional-methods.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/dataTable/jquery.dataTables.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jszip/jszip.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/pdfmake/pdfmake.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/pdfmake/vfs_fonts.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.js"></script>

	<!-- Sweet Alert -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/highcharts/highcharts.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/chart.js/Chart.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/jquery.flot.js"></script>
	<!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/plugins/jquery.flot.resize.js"></script>
	<!-- FLOT PIE PLUGIN - also used to draw donut charts -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/plugins/jquery.flot.pie.js"></script>
	<!-- Page specific script -->
	<!-- 	<script src="https://code.highcharts.com/highcharts.js"></script> -->

	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/highcharts.js"></script>
	<!-- 	<script src="https://code.highcharts.com/modules/exporting.js"></script> -->
	<!-- 	<script src="https://code.highcharts.com/modules/export-data.js"></script> -->
	<!-- 	<script src="https://code.highcharts.com/modules/accessibility.js"></script> -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/export-data.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/accessibility.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/exporting.js"></script>
	<script
		src="<%=request.getContextPath()%>/custom_js/SysLogEventDashboard.js"></script>

	<script>
		function labelFormatter(label, series) {
			return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
					+ label + '<br>' + Math.round(series.percent) + '%</div>'
		}
	</script>

</body>
</html>
