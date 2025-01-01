<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NPM | Interface Info</title>

<!-- Google Font: Source Sans Pro -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<!-- IonIcons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">

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
<body class="hold-transition layout-top-nav">

	<input type="hidden" name="deviceIP" id="deviceIP"
		value='<%=request.getParameter("nodeIP")%>'>
	<input type="hidden" name="intName" id="intName"
		value='<%=request.getParameter("intName")%>'>

	<div class="wrapper">

		<!-- Navbar -->
		<jsp:include page="header.jsp" />
		<!-- /.navbar -->



		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h4 class="m-0">
								Interface Details of <span id="nodeipdetailsstore"><%=request.getParameter("nodeIP")%></span>
								~ <span id="interfacenamedetailsstroe"><%=request.getParameter("intName")%></span>
							</h4>
							<button type="button" class="btn btn-primary"
								onclick="speedtestinterfaceonclick()">Speed Test</button>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
<!-- 								<li class="breadcrumb-item mr-4"><button -->
<!-- 										class="btn btn-block btn-default" type="button" -->
<!-- 										style="background-color: dodgerblue; border-radius: 2em; width: 8em;" -->
<!-- 										id="generate"> -->
<!-- 										Generate</i> -->
<!-- 									</button></li> -->
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Interface Details</li>
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
						<!-- .col-md-12 -->

						<div class="col-lg-6">


							<div class="card"">
								<div class="card-header border-0">
									<h3 class="card-title">Interface Statistics</h3>
								</div>
								<div class="card-body" style="height: 100%;">
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">IP Address</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="nodeIPID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Name</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="intNameID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Alias Name</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="aliasID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Type</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="intTypeID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Specific</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="intSpcID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3"
										id="admindivid">
										<p class="text-normal text-medium">Admin Status</p>
										<p class="d-flex flex-column text-right">

											<span class="text-success mr-1" id="adminStatusID"> <i
												class="fas fa-arrow-up"></i> NA
											</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3"
										id="operdivid">
										<p class="text-normal text-medium">Operational Status</p>
										<p class="d-flex flex-column text-right">
											<span class="text-success mr-1" id="operStatusID"> <i
												class="fas fa-arrow-up"></i> NA
											</span>
										</p>
									</div>

									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3"
										id="operdivid">
										<p class="text-normal text-medium">ICMP Status</p>
										<p class="d-flex flex-column text-right">
											<span class="text mr-1" id="ICMPStatusID"> <i
												class="fas fa-arrow-up"></i> NA
											</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface IP Address</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="intIPID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface MAC Address</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="intMACID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">MTU</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="mtuID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">CRC Count</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="crcID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Procure Bandwidth</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="procBwID">NA</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Current Throughput</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="throughputID">NA</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Input Error</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="interfaceInputError">8.0
												Kbps(Transmit) 1.86 Gbps(Receive)</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Output Error</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="interfaceOutputError">8.0
												Kbps(Transmit) 1.86 Gbps(Receive)</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Discard Input</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="discardInput">8.0
												Kbps(Transmit) 1.86 Gbps(Receive)</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Discard Output</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="discardOutput">8.0
												Kbps(Transmit) 1.86 Gbps(Receive)</span>
										</p>
									</div>

								</div>
							</div>
						</div>

						<!-- /.col-md-12 -->
						<div class="col-lg-6">
							<!-- interactive chart -->
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Real Time Bandwidth (Kbps)</h3>
										<div class="card-tools">
											<a href="#" class="btn btn-tool btn-sm"> <i
												class="fas fa-download"></i>
											</a>
										</div>
									</div>


								</div>
								<div class="card-body">


									<div class="position-relative mb-4">
										<div id="container_bandwidth"
											style="height: 400px; min-width: 310px"></div>
									</div>

									<div class="d-flex flex-row justify-content-end">
										<span class="mr-2"> <i
											class="fas fa-square text-primary"></i> In
										</span> <span> <i class="fas fa-square text-gray"></i> Out
										</span>
									</div>
								</div>
							</div>
							<!-- /.card -->



							<div class="card" style="display: none;">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Interface Uptime</h3>
										<div class="card-tools">
											<a href="#" class="btn btn-tool btn-sm"> <i
												class="fas fa-download"></i>
											</a>
										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="d-flex">
										<p class="d-flex flex-column">
											<span>Uptime Over Time</span>
										</p>
										<p class="ml-auto d-flex flex-column text-right">
											<span class="text-muted">Date Wise (Avg %)</span>
										</p>
									</div>
									<!-- /.d-flex -->

									<div class="position-relative mb-4">
										<canvas id="sales-chart" height="200"></canvas>
									</div>

									<div class="d-flex flex-row justify-content-end">
										<span class="mr-2"> <i class="fas fa-square text-gray"></i>
											Uptime
										</span> <span class="mr-2"> <i
											class="fas fa-square text-primary"></i> Downtime
										</span>
									</div>
								</div>
							</div>
							<!-- /.card -->

						</div>
						<div class="col-lg-6">
							<!--Latency of link  -->
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Current Latency of link</h3>
										<div class="card-tools">
											<a href="#" class="btn btn-tool btn-sm"> <i
												class="fas fa-download"></i>
											</a>
										</div>
									</div>


								</div>
								<div class="card-body">


									<div class="position-relative mb-4">
										<div id="link_latency_container"
											style="height: 400px; min-width: 310px"></div>
									</div>

									<div class="d-flex flex-row justify-content-end">
										<span class="mr-2"> <i
											class="fas fa-square text-primary"></i> Link Latency 
									</div>
								</div>
							</div>
							<!--End Latency of Link  -->
						</div>
						<!-- /.col-md-6 -->
						<div class="col-lg-6">
							<!--Latency of link  -->
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Current Jitter of link</h3>
										<div class="card-tools">
											<a href="#" class="btn btn-tool btn-sm"> <i
												class="fas fa-download"></i>
											</a>
										</div>
									</div>


								</div>
								<div class="card-body">


									<div class="position-relative mb-4">
										<div id="link_Jitter_container"
											style="height: 400px; min-width: 310px"></div>
									</div>

									<div class="d-flex flex-row justify-content-end">

										<span class="mr-2"> <i
											class="fas fa-square text-primary"></i> Average jitter
										</span>


									</div>

								</div>
							</div>
							<!--End Latency of Link  -->
						</div>
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Last 5 Events of Interface</h3>
									<div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a>
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										id="interfaceSummary">
										<thead>
											<tr>
												<th>Date & Time</th>
												<th>Interface Name</th>
												<th>Activity</th>
												<th>Interface Status</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col-md-6 -->
						<div class="col-lg-6" style="display: none;">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Last 5 Events of CRC Log</h3>
									<div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a>
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										id="crcLogID">
										<thead>
											<tr>
												<th>Date & Time</th>
												<th>Interface Name</th>
												<th>CRC Before</th>
												<th>CRC Current</th>

											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- /.col-md-6 -->


					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<div class="modal modal-right fade bd-example-modal-lg" id="dateModal"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-slideout modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Generate
							Interface Report And Graph</h5>
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
										value='<%=request.getParameter("nodeIP")%>'> <input
										type="hidden" name="intName" id="intName"
										value='<%=request.getParameter("intName")%>'>
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
														value="interfaceBandwidth" id="interfaceBandwidth"
														required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="interfaceBandwidth">
														Interface Bandwidth </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="report"
														value="interfaceUptime" id="interfaceUptime" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="interfaceUptime">
														Interface Uptime </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="report"
														value="interfaceStatusEvents" id="interfaceStatusEvents"
														required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="interfaceStatusEvents">
														Interface Status Events </label>
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
														value="interfaceBandwidthGraph"
														id="interfaceBandwidthGraph" required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label"
														for="interfaceBandwidthGraph"> Interface Bandwidth
														Graph </label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-check">
													<input class="form-check-input" type="radio" name="graph"
														value="interfaceUptimeGraph" id="interfaceUptimeGraph"
														required>
												</div>
											</td>
											<td>
												<div class="form-check">
													<label class="form-check-label" for="interfaceUptimeGraph">
														Interface Uptime Graph </label>
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

		<!-- Footer  -->
		<jsp:include page="footer.jsp" />
		<!-- /. Footer -->

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->

	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->
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
	<!-- Page specific script -->
	<script src="<%=request.getContextPath()%>/custom_js/interfaceInfo.js"></script>
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
