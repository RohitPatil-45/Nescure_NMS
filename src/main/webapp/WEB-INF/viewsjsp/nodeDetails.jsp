<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Device Details</title>

<!-- Google Font: Source Sans Pro -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<!-- IonIcons -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">
<!-- date range picker -->


<!-- <script type="text/javascript" -->
<!-- 	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" /> -->


<!-- DataTables -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/dataTables.bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/fixedHeader.dataTables.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/jquery.dataTables.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/select.dataTables.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/buttons.dataTables.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/colReorder.dataTables.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.css">

<style>
.modal-dialog-slideout {
	min-height: 100%;
	margin: 0 0 0 auto;
	background: #fff;
}

.modal.fade .modal-dialog.modal-dialog-slideout {
	-webkit-transform: translate(100%, 0) scale(1);
	transform: translate(100%, 0) scale(1);
}

.modal.fade.show .modal-dialog.modal-dialog-slideout {
	-webkit-transform: translate(0, 0);
	transform: translate(0, 0);
	display: flex;
	align-items: stretch;
	-webkit-box-align: stretch;
	height: 100%;
}

.modal.fade.show .modal-dialog.modal-dialog-slideout .modal-body {
	overflow-y: auto;
	overflow-x: hidden;
}

.modal-dialog-slideout .modal-content {
	border: 0;
}

.modal-dialog-slideout .modal-header, .modal-dialog-slideout .modal-footer
	{
	height: 69px;
	display: block;
}

.modal-dialog-slideout .modal-header h5 {
	float: left;
}

.modal-dialog .modal-footer .exportLatency {
	float: right;
}

.dropbtn {
	/* 	background-color: #3498DB; */
	color: #3498DB;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
	/* 	background-color: #2980B9; */
	color: red;
}

.dropdown {
	float: right !important;
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	overflow: auto;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown a:hover {
	background-color: #ddd;
	background-color: #3498DB;
}

.show {
	display: block;
}

.card-header {
	background-color: dodgerblue;
}
</style>
</head>
<!--
`body` tag options:

  Apply one or more of the following classes to to the body tag
  to get the desired effect

  * sidebar-collapse
  * sidebar-mini
-->
<body class="hold-transition layout-top-nav">

	<input type="hidden" name="deviceIP" id="deviceIP"
		value='<%=request.getParameter("nodeIP")%>'>

	<div class="wrapper">

		<jsp:include page="header.jsp" />
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">
								Node Details
								<%=request.getParameter("nodeIP")%></h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<!-- <li class="breadcrumb-item mr-4"><button
										class="btn btn-block btn-default" type="button"
										style="background-color: dodgerblue; border-radius: 2em; width: 8em;"
										id="generate">
										Generate</i>
									</button></li> -->
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Node Details</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<div class="content">
				<div class="container-fluid">

					<div class="row">
						<div class="col-md-6">
							<!-- Line chart -->

							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Basic Information</h3>
									<!-- <div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-bars"></i>
										</a>
									</div> -->
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle">
										<thead>
											<tr>
												<th>Label</th>
												<th>Information</th>

											</tr>
										</thead>

										<tbody>
											<tr>
												<td>Node IP</td>
												<!-- 												<td>192.168.0.1</td> -->
												<td><span id="Node_ip"></span></td>
											</tr>
											<tr>
												<td>Node Name</td>
												<!-- 												<td>VSHRouter</td> -->
												<td><span id="NodeNAme"></span></td>
											</tr>
											<tr>
												<td>Location</td>
												<!-- 												<td>Vashi</td> -->
												<td><span id="Location"></span></td>
											</tr>
											<tr>
												<td>Company Name</td>
												<!-- 												<td>Cisco IOS Software</td> -->
												<td><span id="Company"></span></td>
											</tr>

											<tr style="display: none;">
												<td>Version</td>
												<!-- 												<td>Version Version 15.4(3)M2</td> -->
												<td><span id="Version"></span></td>
											</tr>

											<tr style="display: none;">
												<td>Model</td>
												<!-- 												<td>C3900 Software (C3900-UNIVERSALK9-M)</td> -->
												<td><span id="Model"></span></td>
											</tr>
											<tr>
												<td>District</td>
												<!-- 												<td>Thane</td> -->
												<td><span id="District"></span></td>
											</tr>
											<tr>
												<td>Node Status</td>
												<td><span id="status"></span></td>
											</tr>


										</tbody>
									</table>
								</div>
							</div>
							<div class="row" style="display: none;">
								<div class="col-md-12">
									<div class="card">
										<div class="card-header border-0">
											<h3 class="card-title">Device Temperature</h3>
											<div class="card-tools">
												<a href="#" class="btn btn-tool btn-sm"> <i
													class="fas fa-download"></i>
												</a> <a href="#" class="btn btn-tool btn-sm"> <i
													class="fas fa-bars"></i>
												</a>
											</div>
										</div>
										<div class="card-body table-responsive p-0">
											<div class="card-body">
												<center>
													<p style="font-size: x-large;">
														<b>Your Device Temperature is</b>
													</p>
													<span style="font-size: x-large;" id="deviceTemperature"></span>&nbsp;
													&nbsp; &nbsp; <img style="width: 50px" alt=""
														src="<%=request.getContextPath()%>/webtemplate/dist/img/temperature.png">
												</center>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
						<!-- /.col -->
						<div class="col-md-6">
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Node Latency</h3>

									</div>
									<!-- <div id="dropdown" style="float: right; margin-top: -5%;">
										<p onclick="myFunction()" class="dropbtn"
											style="float: right; margin-top: -6%; color: black">View
											Report</p>
										<div id="myDropdown" class="dropdown-content"
											style="margin-top: 6%; margin-left: -5%;">
											<a href="#" data-toggle="modal" data-target="#dateModal">Export</a>
											<a href="#graph">Graph</a>

										</div>
									</div> -->

								</div>
								<div class="card-body">
									<div class="d-flex"></div>
									<!-- /.d-flex -->

									<div class="position-relative mb-4">
										<div id="container_latency"
											style="height: 400px; min-width: 310px"></div>
										<%-- 										<canvas id="visitors-chart" height="200"></canvas> --%>
									</div>

									<div class="d-flex flex-row justify-content-end">
										<span class="mr-2"> <i
											class="fas fa-square text-primary"></i> Latency
										</span> <span> <i class="fas fa-square text-gray"></i> Packet
											Drop
										</span>
									</div>
								</div>
							</div>
							<!-- /.card -->

							<!-- /.card -->
						</div>

						<!-- /.col -->
					</div>



					<!-- First Row Complete -->

					<div class="row">
						<div class="col-lg-6" style="display: none;">
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Node Availablity</h3>
										<!-- 										<a href="javascript:void(0);">View Report</a> -->
									</div>
									<div id="dropdown" style="float: right; margin-top: -5%;">
										<!-- <p onclick="NodeAvailabilityDropdown()" class="dropbtn"
											style="float: right; margin-top: -6%;">View Report</p>-->
										<!-- 											<div id="myDropdown" class="dropdown-content" style="margin-left: 71%;margin-top: 47px;"> -->
										<div id="NodeAvailability" class="dropdown-content"
											style="margin-top: 6%; margin-left: -5%;">
											<a href="#" data-toggle="modal" data-target="#dateNodeModal">Export</a>
											<a href="#graph">Graph</a>

										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="d-flex">
										<p class="d-flex flex-column">
											<span class="text-bold text-lg"></span> <span>Uptime
												Over Time</span>
										</p>
										<p class="ml-auto d-flex flex-column text-right">


											<span class="text-muted">Date Wise (Avg %)</span>
										</p>
									</div>


									<div class="position-relative mb-4">
										<canvas id="sales-chart" height="200"></canvas>
									</div>

									<div class="d-flex flex-row justify-content-end">
										<span class="mr-2"> <i
											class="fas fa-square text-primary"></i> Uptime
										</span> <span> <i class="fas fa-square text-gray"></i>
											Downtime
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Node Status Events</h3>
									<!-- <div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-bars"></i>
										</a>
									</div> -->
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										name="nodeStatusSummary" id="nodeStatusSummary">
										<thead>
											<tr>
												<th>Sr No.</th>
												<th>Node IP</th>
												<th>Status</th>
												<th>Date</th>

											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Interface Summary</h3>
									<!-- <div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-bars"></i>
										</a>
									</div> -->
								</div>
								<div class="card-body table-responsive p-0"
									style="height: 335px;">
									<table class="table table-striped table-valign-middle"
										name="interfaceStatusSummary" id="interfaceStatusSummary">
										<thead>
											<tr>
												<th>Sr No.</th>
												<th>Interface Name</th>
												<th>Loop back IP</th>
												<th>ICMP Status</th>
												<th>Alias Name</th>
												<!-- 												<th></th> -->
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>

					<!-- Second Row Complete -->

					<div class="row" style="display: none;">

						<div class="col-md-6">

							<!-- Donut chart -->
							<div class="card card-primary card-outline">
								<div class="card-header">
									<h3 class="card-title">
										<i class="far fa-chart-bar"></i> Memory Utilization
									</h3>

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
									<div id="donut-chart" style="height: 300px;"></div>
								</div>
								<!-- /.card-body-->
							</div>
							<!-- /.card -->
						</div>




						<!-- /.col-md-6 -->
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">RAM</h3>
									<!-- <div class="card-tools">
										<a href="#" class="btn btn-sm btn-tool"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-sm btn-tool"> <i
											class="fas fa-bars"></i>
										</a>
									</div> -->
								</div>
								<div class="card-body">
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-success text-xl">
											<i class="ion ion-ios-refresh-empty"></i>
										</p>
										<p class="d-flex flex-column text-right">
											<span class="font-weight-bold"> <i
												class="ion ion-android-arrow-up text-success"></i><span
												id="Total_RAM"></span>
											</span> <span class="text-muted">Total RAM(MB)</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-warning text-xl">
											<i class="ion ion-ios-cart-outline"></i>
										</p>
										<p class="d-flex flex-column text-right">
											<span class="font-weight-bold"> <i
												class="ion ion-android-arrow-up text-warning"></i><span
												id="Used_RAM"></span>
											</span> <span class="text-muted">Used RAM(MB)</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center mb-0">
										<p class="text-danger text-xl">
											<i class="ion ion-ios-people-outline"></i>
										</p>
										<p class="d-flex flex-column text-right">
											<span class="font-weight-bold"> <i
												class="ion ion-android-arrow-down text-danger"></i><span
												id="Free_RAM"></span>
											</span> <span class="text-muted">Free RAM (MB)</span>
										</p>
									</div>
									<!-- /.d-flex -->
								</div>
							</div>
						</div>
						<!-- /.col-md-6 -->

					</div>



					<div class="row">
						<div class="col-6" style="display: none;">
							<!-- interactive chart -->
							<div class="card card-primary card-outline">
								<div class="card-header">
									<h3 class="card-title">
										<i class="far fa-chart-bar"></i> CPU Utilization
									</h3>

									<!-- <div class="card-tools">
										Real time
										<div class="btn-group" id="realtime" data-toggle="btn-toggle">
											<button type="button" class="btn btn-default btn-sm active"
												data-toggle="on">On</button>
											<button type="button" class="btn btn-default btn-sm"
												data-toggle="off">Off</button>
										</div>
									</div> -->
								</div>
								<div class="card-body">
									<div id="interactive" style="height: 300px;"></div>
								</div>
								<div class="d-flex flex-row justify-content-end">
									<span class="mr-2"> <i
										class="fas fa-square text-primary"></i> CPU Utilization
									</span>
									<!-- <span> <i class="fas fa-square text-gray"></i> Memory Utilization
											Drop
										</span> -->
								</div>
								<!-- /.card-body-->
							</div>
							<!-- /.card -->

						</div>

						<div class="col-lg-6" style="display: none;">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Drive Details</h3>
									<!-- <div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-bars"></i>
										</a>
									</div> -->
								</div>
								<div class="card-body table-responsive p-0"
									style="height: 335px;">
									<table class="table table-striped table-valign-middle"
										id="driveDetailsTable">
										<thead>
											<tr>
												<th>Drive Name</th>
												<th>Total Space (GB)</th>
												<th>Free Space (GB)</th>
												<th>Used Space (GB)</th>
												<th>Drive utilization(%)</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- Node Connected Device  -->
						<div class="col-lg-6" style="display: none;">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Node Connected Devices</h3>
									<!-- <div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-bars"></i>
										</a>
									</div> -->
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										name="ConnectedDevices" id="ConnectedDevices">
										<thead>
											<tr>
												<th>SR_NO</th>
												<th>DEV_CONNECTED_IP</th>
												<th>DEV_CONNECTED_MAC_ID</th>
												<th>DEV_CONNECTED_INTERFACE_NAME</th>
												<th>DEV_CONNECTED_CON_TYPE</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- End Node Connected Device -->



						<!-- /.col -->
					</div>



					<!-- /.row -->



					<div class="card-body" style="display: none">
						<div id="area-chart" style="height: 338px;"
							class="full-width-chart"></div>
						<div id="line-chart" style="height: 300px;"></div>
					</div>

				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->



		<!-- Modal -->
		<!-- Modal -->
		<div class="modal modal-right fade bd-example-modal-lg" id="dateModal"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-slideout modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Generate
							Report And Graph</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form method="post" id="exportNodeReportGraph">
						<div class="modal-body row">

							<div class="form-group col-6">
								<label>Report View:</label>
								<div class="input-group">
									<select type="text" id='reportGraph' name='reportGraph'
										class="form-control">
										<option value="report">Report</option>
										<option value="graph">Graph</option>
									</select>
								</div>
							</div>
							<div class="form-group col-6">
								<label>Report Date range:</label>
								<div class="input-group">
									<input type="hidden" name="deviceIP" id="deviceIP"
										value='<%=request.getParameter("nodeIP")%>'>
									<button type="button" class="btn btn-default float-right"
										id="daterange-btn1" name="daterange-btn1">
										<i class="far fa-calendar-alt"></i> Date range picker <i
											class="fas fa-caret-down"></i>
									</button>
									<input type="text" id='from_date' name='from_date'
										class="form-control" readonly required /> <input type="text"
										id='to_date' name='to_date' class="form-control" readonly
										required />
								</div>
							</div>
						</div>
						<hr />
						<div class="modal-body row" id="report">
							<div class="form-group col-12">
								<table id="example1" class="table table-bordered table-striped"
									style="width: 100%">
									<tbody>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="report"
														value="latency" id="latency" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="latency">
														Latency Report </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="report"
														value="cpu" id="cpu" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="cpu"> CPU
														Report </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="report"
														value="memory" id="memory" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="memory">
														Memory Report </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="report"
														value="nodeStatus" id="nodeStatus" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="nodeStatus">
														Node Status Report </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="report"
														value="availability" id="availability" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="availability">
														Availability Report </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="report"
														value="cpuThreshold" id="cpuThreshold" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="cpuThreshold">
														CPU Threshold </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="report"
														value="memoryThreshold" id="memoryThreshold" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="memoryThreshold">
														Memory Threshold </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="report"
														value="latencyThreshold" id="latencyThreshold" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="latencyThreshold">
														Latency Threshold </label>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="modal-body row" id="graph">
							<div class="form-group col-12">
								<table id="example1" class="table table-bordered table-striped">
									<tbody>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="graph"
														value="latencyGraph" id="latencyGraph" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="latencyGraph">
														Latency Graph </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="graph"
														value="cpuGraph" id="cpuGraph" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="cpuGraph"> CPU
														Graph </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="graph"
														value="memoryGraph" id="memoryGraph" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="memoryGraph">
														Memory Graph </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="graph"
														value="availabilityGraph" id="availabilityGraph" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="availabilityGraph">
														Availability Graph </label>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="modal-footer" id="reportButton">
							<button type="submit" class="btn btn-primary exportLatency"
								id="exportReportLatency"
								style="background-color: dodgerblue; border-radius: 2em; width: 9em;">Generate
								report</button>
						</div>
						<div class="modal-footer" id="graphButton">
							<button type="submit" class="btn btn-primary exportLatency"
								id="exportGraphLatency"
								style="background-color: dodgerblue; border-radius: 2em; width: 9em;">Generate
								Graph</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="modal fade bd-example-modal-lg" id="dateNodeModal"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Node
							Availability Report</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form method="post" action="nodeAvailabilityReport">
						<div class="modal-body">

							<div class="form-group" id="date_div">
								<label>Report Date range:</label>
								<!-- 								<div class="input-group"> -->
								<!-- 									<button type="button" class="btn btn-default float-right" -->
								<!-- 										id="daterange-btnNode" name="daterange-btnNode"> -->
								<!-- 										<i class="far fa-calendar-alt"></i> Date range picker <i -->
								<!-- 											class="fas fa-caret-down"></i> -->
								<!-- 									</button> -->
								<!-- 									<input type="text" id='from_dateNode' name='from_dateNode' -->
								<!-- 										class="form-control" readonly /> <input type="text" -->
								<!-- 										id='to_dateNode' name='to_dateNode' class="form-control" readonly /> -->
								<!-- 								</div> -->

							</div>
						</div>
						<div class="modal-footer">
							<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
							<center>
								<button type="submit" class="btn btn-primary">Submit</button>
							</center>
						</div>
					</form>
				</div>
			</div>
		</div>

		<!-- Footer  -->
		<jsp:include page="footer.jsp" />
		<!-- /. Footer -->

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>


	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/moment/moment.min.js"></script>
	<!-- jquery-validation -->

	<!-- end -->


	<script
		src="<%=request.getContextPath()%>/webtemplate/datatablesJS/jquery-3.5.1.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.js"></script>

	<!-- OPTIONAL SCRIPTS -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/chart.js/Chart.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/pages/dashboard3.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/additional-methods.min.js"></script>



	<!-- DataTables  & Plugins -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables/jquery.dataTables.min.js"></script>
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

	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/vfs_fonts.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/buttons.html5.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/buttons.print.min.js"></script>






	<!-- jQuery -->

	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
	<!-- FLOT CHARTS -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/jquery.flot.js"></script>
	<!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/plugins/jquery.flot.resize.js"></script>
	<!-- FLOT PIE PLUGIN - also used to draw donut charts -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/plugins/jquery.flot.pie.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<script src="<%=request.getContextPath()%>/webtemplate/js/highstock.js"></script>

	<%-- 	<script src="<%=request.getContextPath()%>/custom_js/exportReports.js"></script> --%>

	<!-- date-range-picker -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/moment/moment.min.js"></script>
	<script src="<%=request.getContextPath()%>/custom_js/nodeDetails.js"></script>



	<%-- <script src="<%=request.getContextPath()%>/custom_js/nodeReport.js"></script> --%>

	<!-- <script type="text/javascript">
		$(function() {

 			var start = moment().subtract(29, 'days');
 			var end = moment();

 			function cb(start, end) {
 				$('#daterange-btn span').html(
 						start.format('MMMM D, YYYY') + ' , '
 								+ end.format('MMMM D, YYYY'));
 				 							start.format('MMMM D, YYYY') + ' - '
 				 									+ end.format('MMMM D, YYYY'));
 			}

 			$('#daterange-btn').daterangepicker(
 					{

 						startDate : start,
 						locale : {
 							format : 'YYYY/MM/DD' // --------Here
 						},
 						endDate : end,

 						ranges : {
 							'Today' : [ moment(), moment() ],
 							'Yesterday' : [ moment().subtract(1, 'days'),
 									moment().subtract(1, 'days') ],
 							'Last 7 Days' : [ moment().subtract(6, 'days'),
 									moment() ],
 							'Last 30 Days' : [ moment().subtract(29, 'days'),
 									moment() ],
 							'This Month' : [ moment().startOf('month'),
 									moment().endOf('month') ],
 							'Last Month' : [
 									moment().subtract(1, 'month').startOf(
 											'month'),
 									moment().subtract(1, 'month')
 											.endOf('month') ]
 						}
					}, cb);

 			cb(start, end);

 		});
	</script> -->
	<!-- Page specific script -->
	<script>
		/*
		 * Custom Label formatter
		 * ----------------------
		 */
		function labelFormatter(label, series) {
			return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
					+ label + '<br>' + Math.round(series.percent) + '%</div>'
		}
	</script>

	<script>
		/* When the user clicks on the button, 
		 toggle between hiding and showing the dropdown content */
		function myFunction() {

			document.getElementById("myDropdown").classList.toggle("show");
		}
		function NodeAvailabilityDropdown() {
			document.getElementById("NodeAvailability").classList
					.toggle("show");
		}

		// Close the dropdown if the user clicks outside of it
		window.onclick = function(event) {
			if (!event.target.matches('.dropbtn')) {
				var dropdowns = document
						.getElementsByClassName("dropdown-content");
				var i;
				for (i = 0; i < dropdowns.length; i++) {
					var openDropdown = dropdowns[i];
					if (openDropdown.classList.contains('show')) {
						openDropdown.classList.remove('show');
					}
				}
			}
		}
	</script>

	<script>
		$('#daterange-btn1').daterangepicker(
				{
					timePicker : true,
					timePickerIncrement : 10,
					ranges : {
						'Today' : [ moment().hours(0).minutes(0).seconds(0),
								moment().hours(23).minutes(59).seconds(59) ],
						'Yesterday' : [
								moment().hours(0).minutes(0).seconds(0)
										.subtract(1, 'days'),
								moment().hours(23).minutes(59).seconds(59)
										.subtract(1, 'days') ],
						'Last 7 Days' : [
								moment().hours(0).minutes(0).seconds(0)
										.subtract(6, 'days'),
								moment().hours(23).minutes(59).seconds(59) ],
						'Last 30 Days' : [
								moment().hours(0).minutes(0).seconds(0)
										.subtract(29, 'days'),
								moment().hours(23).minutes(59).seconds(59) ],
						'This Month' : [ moment().startOf('month'),
								moment().endOf('month') ],
						'Last Month' : [
								moment().subtract(1, 'month').startOf('month'),
								moment().subtract(1, 'month').endOf('month') ]
					},
					startDate : moment().subtract(29, 'days'),
					endDate : moment()
				}, function(start, end) {
					var from_date = document.getElementById("from_date");
					from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
					var to_date = document.getElementById("to_date");
					to_date.value = end.format('YYYY-MM-DD HH:mm:ss');

				});

		$('#reservationtime').daterangepicker({
			timePicker : true,
			timePickerIncrement : 10,
			locale : {
				format : 'MM/DD/YYYY hh:mm:ss'
			}

		}, function(start, end) {

			var from_date = document.getElementById("from_date");
			from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
			var to_date = document.getElementById("to_date");
			to_date.value = end.format('YYYY-MM-DD HH:mm:ss');
		});
		/* 
		function ShowDateDiv() {
			$("#datetime_div").hide();	
			$("#date_div").show();	
		}

		function ShowTimeDiv() {
			$("#datetime_div").show();	
			$("#date_div").hide();	
		}
		 */
		$(function() {

			$('[data-mask]').inputmask()
			// Bootstrap Duallistbox

		})

		$(function() {
			var table = $("#example1").DataTable({
				"responsive" : true,
				"lengthChange" : false,
				"autoWidth" : false,
				"scrollY" : "200px",
				"scrollCollapse" : true,
			}

			).buttons().container().appendTo(
					'#example1_wrapper .col-md-6:eq(0)');

		});
	</script>


</body>
</html>
