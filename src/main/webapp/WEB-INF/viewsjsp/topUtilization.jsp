<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NPM | Top 10</title>

<!-- Google Font: Source Sans Pro -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">

<!-- DataTables -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">


</head>
<body
	class="hold-transition layout-top-nav layout-fixed layout-navbar-fixed layout-footer-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<div
			class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__wobble"
				src="<%=request.getContextPath()%>/webtemplate/dist/img/AdminLTELogo.png"
				alt="AdminLTELogo" height="60" width="60">
		</div>


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
							<h1 class="m-0">Top 10</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Top 10</li>
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
			<section class="content">
				<div class="container-fluid">
					<!-- Info boxes -->
					<div class="row">

						<div class="col-md-6">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">CPU Utilization</h3>
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
								<div class="card-body table-responsive p-0"
									style="height: 200px;">
									<table id="cpuSummary"
										class="table table-head-fixed text-nowrap">
										<thead>
											<tr>
												<th>Device</th>
												<th>CPU</th>
												<th>Avg(%)</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>FGT30E5619025245</td>
												<td>Unknown Processor</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-danger" role="progressbar"
															aria-valuenow="90" aria-valuemin="0" aria-valuemax="100"
															style="width: 90%">90%</div>
													</div></td>

											</tr>
											<tr>
												<td>CISCO-MUM-CORE-RO-01</td>
												<td>Unknown Processor</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-danger" role="progressbar"
															aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"
															style="width: 85%">85%</div>
													</div></td>

											</tr>
											<tr>
												<td>ASR-THA-RO-01</td>
												<td>Unknown Processor</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-warning" role="progressbar"
															aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
															style="width: 80%">80%</div>
													</div></td>

											</tr>
											<tr>
												<td>ASR-MUM-RO-01</td>
												<td>Unknown Processor</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-warning" role="progressbar"
															aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"
															style="width: 75%">75%</div>
													</div></td>

											</tr>
											<tr>
												<td>ASR-THA-CORE-SWITCH-01</td>
												<td>Unknown Processor</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-success" role="progressbar"
															aria-valuenow="65" aria-valuemin="0" aria-valuemax="100"
															style="width: 65%">65%</div>
													</div></td>

											</tr>

										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>

						<div class="col-md-6">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Memory Utilization</h3>
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
								<div class="card-body table-responsive p-0"
									style="height: 200px;">
									<table class="table table-head-fixed text-nowrap">
										<thead>
											<tr>
												<th>Device</th>
												<th>Size(Kb)</th>
												<th>Avg(%)</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>FGT30E5619025245</td>
												<td>2048</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-danger" role="progressbar"
															aria-valuenow="90" aria-valuemin="0" aria-valuemax="100"
															style="width: 90%">90%</div>
													</div></td>

											</tr>
											<tr>
												<td>CISCO-MUM-CORE-RO-01</td>
												<td>2048</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-danger" role="progressbar"
															aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"
															style="width: 85%">85%</div>
													</div></td>

											</tr>
											<tr>
												<td>ASR-THA-RO-01</td>
												<td>2048</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-warning" role="progressbar"
															aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"
															style="width: 75%">75%</div>
													</div></td>

											</tr>
											<tr>
												<td>ASR-MUM-RO-01</td>
												<td>2048</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-warning" role="progressbar"
															aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
															style="width: 70%">70%</div>
													</div></td>

											</tr>
											<tr>
												<td>ASR-THA-CORE-SWITCH-01</td>
												<td>1024</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-success" role="progressbar"
															aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
															style="width: 60%">60%</div>
													</div></td>

											</tr>

										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
					</div>
					<!-- /.row -->

					<!-- Info boxes -->
					<div class="row">

						<div class="col-md-6">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Ping Response Time</h3>
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
								<div class="card-body table-responsive p-0"
									style="height: 200px;">
									<table class="table table-head-fixed text-nowrap">
										<thead>
											<tr>
												<th>Device</th>
												<th>IP Address</th>
												<th>Avg(ms)</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>FGT30E5619025245</td>
												<td>192.168.0.1</td>
												<td>120</td>
											</tr>
											<tr>
												<td>CISCO-MUM-CORE-RO-01</td>
												<td>10.10.34.67</td>
												<td>110</td>
											</tr>
											<tr>
												<td>ASR-THA-RO-01</td>
												<td>10.10.76.54</td>
												<td>80</td>
											</tr>
											<tr>
												<td>ASR-MUM-RO-01</td>
												<td>10.1.23.54</td>
												<td>80</td>
											</tr>
											<tr>
												<td>ASR-THA-CORE-SWITCH-01</td>
												<td>10.1.56.76</td>
												<td>80</td>
											</tr>


										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>

						<div class="col-md-6">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Ping Availability</h3>
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
								<div class="card-body table-responsive p-0"
									style="height: 200px;">
									<table class="table table-head-fixed text-nowrap">
										<thead>
											<tr>
												<th>Device</th>
												<th>IP Address</th>
												<th>Uptime(%)</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>FGT30E5619025245</td>
												<td>192.168.0.1</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-success" role="progressbar"
															aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
															style="width: 100%">100%</div>
													</div></td>
											</tr>
											<tr>
												<td>CISCO-MUM-CORE-RO-01</td>
												<td>10.10.34.67</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-success" role="progressbar"
															aria-valuenow="90" aria-valuemin="0" aria-valuemax="100"
															style="width: 90%">90%</div>
													</div></td>
											</tr>
											<tr>
												<td>ASR-THA-RO-01</td>
												<td>10.10.76.54</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-warning" role="progressbar"
															aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"
															style="width: 85%">85%</div>
													</div></td>
											</tr>
											<tr>
												<td>ASR-MUM-RO-01</td>
												<td>10.1.23.54</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-danger" role="progressbar"
															aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
															style="width: 80%">80%</div>
													</div></td>
											</tr>
											<tr>
												<td>ASR-THA-CORE-SWITCH-01</td>
												<td>10.1.56.76</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-danger" role="progressbar"
															aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"
															style="width: 75%">75%</div>
													</div></td>
											</tr>


										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
					</div>
					<!-- /.row -->

					<!-- Info boxes -->
					<div class="row">

						<div class="col-md-6">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Interface Utilization</h3>
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
								<div class="card-body table-responsive p-0"
									style="height: 200px;">
									<table class="table table-head-fixed text-nowrap">
										<thead>
											<tr>
												<th>Device</th>
												<th>Interface</th>
												<th>Tx Avg (%)</th>
												<th>Rx Avg (%)</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>FGT30E5619025245</td>
												<td>lan4</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-danger" role="progressbar"
															aria-valuenow="95" aria-valuemin="0" aria-valuemax="100"
															style="width: 95%">95%</div>
													</div></td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-danger" role="progressbar"
															aria-valuenow="90" aria-valuemin="0" aria-valuemax="100"
															style="width: 90%">90%</div>
													</div></td>
											</tr>
											<tr>
												<td>CISCO-MUM-CORE-RO-01</td>
												<td>GigabitEthernet0/0</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-danger" role="progressbar"
															aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"
															style="width: 85%">85%</div>
													</div></td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-danger" role="progressbar"
															aria-valuenow="82" aria-valuemin="0" aria-valuemax="100"
															style="width: 82%">82%</div>
													</div></td>
											</tr>
											<tr>
												<td>ASR-THA-RO-01</td>
												<td>GigabitEthernet0/1</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-warning" role="progressbar"
															aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
															style="width: 80%">80%</div>
													</div></td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-warning" role="progressbar"
															aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
															style="width: 80%">80%</div>
													</div></td>
											</tr>
											<tr>
												<td>ASR-MUM-RO-01</td>
												<td>Port-channel30</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-warning" role="progressbar"
															aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"
															style="width: 75%">75%</div>
													</div></td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-warning" role="progressbar"
															aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
															style="width: 70%">70%</div>
													</div></td>
											</tr>
											<tr>
												<td>ASR-THA-CORE-SWITCH-01</td>
												<td>Serial 0/1</td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-success" role="progressbar"
															aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
															style="width: 60%">60%</div>
													</div></td>
												<td><div class="progress mb-3">
														<div class="progress-bar bg-success" role="progressbar"
															aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
															style="width: 40%">40%</div>
													</div></td>
											</tr>

										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>

						<div class="col-md-6">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Interface Traffic</h3>
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
								<div class="card-body table-responsive p-0"
									style="height: 200px;">
									<table class="table table-head-fixed text-nowrap">
										<thead>
											<tr>
												<th>Device</th>
												<th>Interface</th>
												<th>Tx Avg (Kbps)</th>
												<th>Rx Avg (Kbps)</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>FGT30E5619025245</td>
												<td>lan4</td>
												<td>95.10 Mbps</td>
												<td>97.80 Mbps</td>
											</tr>
											<tr>
												<td>CISCO-MUM-CORE-RO-01</td>
												<td>GigabitEthernet0/0</td>
												<td>80 Mbps</td>
												<td>85 Mbps</td>
											</tr>
											<tr>
												<td>ASR-THA-RO-01</td>
												<td>GigabitEthernet0/1</td>
												<td>70 Mbps</td>
												<td>70 Mbps</td>
											</tr>
											<tr>
												<td>ASR-MUM-RO-01</td>
												<td>Port-channel30</td>
												<td>60.10 Mbps</td>
												<td>65.80 Mbps</td>
											</tr>
											<tr>
												<td>ASR-THA-CORE-SWITCH-01</td>
												<td>Serial 0/1</td>
												<td>50.10 Mbps</td>
												<td>55.80 Mbps</td>
											</tr>

										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
					</div>
					<!-- /.row -->
					<!-- Info boxes -->
					<div class="row">

						<div class="col-md-6">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Interface Errors</h3>
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
								<div class="card-body table-responsive p-0"
									style="height: 200px;">
									<table class="table table-head-fixed text-nowrap">
										<thead>
											<tr>
												<th>Device</th>
												<th>Interface</th>
												<th>Tx Errors</th>
												<th>Rx Errors</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>FGT30E5619025245</td>
												<td>lan4</td>
												<td>150</td>
												<td>130</td>
											</tr>
											<tr>
												<td>CISCO-MUM-CORE-RO-01</td>
												<td>GigabitEthernet0/0</td>
												<td>120</td>
												<td>110</td>
											</tr>
											<tr>
												<td>ASR-THA-RO-01</td>
												<td>GigabitEthernet0/1</td>
												<td>100</td>
												<td>105</td>
											</tr>
											<tr>
												<td>ASR-MUM-RO-01</td>
												<td>Port-channel30</td>
												<td>95</td>
												<td>90</td>
											</tr>
											<tr>
												<td>ASR-THA-CORE-SWITCH-01</td>
												<td>Serial 0/1</td>
												<td>85</td>
												<td>80</td>
											</tr>

										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>

						<div class="col-md-6">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Interface Discards</h3>
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
								<div class="card-body table-responsive p-0"
									style="height: 200px;">
									<table class="table table-head-fixed text-nowrap">
										<thead>
											<tr>
												<th>Device</th>
												<th>Interface</th>
												<th>Tx Discards</th>
												<th>Rx Discards</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>FGT30E5619025245</td>
												<td>lan4</td>
												<td>150</td>
												<td>130</td>
											</tr>
											<tr>
												<td>CISCO-MUM-CORE-RO-01</td>
												<td>GigabitEthernet0/0</td>
												<td>120</td>
												<td>110</td>
											</tr>
											<tr>
												<td>ASR-THA-RO-01</td>
												<td>GigabitEthernet0/1</td>
												<td>100</td>
												<td>105</td>
											</tr>
											<tr>
												<td>ASR-MUM-RO-01</td>
												<td>Port-channel30</td>
												<td>95</td>
												<td>90</td>
											</tr>
											<tr>
												<td>ASR-THA-CORE-SWITCH-01</td>
												<td>Serial 0/1</td>
												<td>85</td>
												<td>80</td>
											</tr>

										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
					</div>
					<!-- /.row -->

				</div>
				<!--/. container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

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
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- overlayScrollbars -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.js"></script>

	<!-- PAGE PLUGINS -->
	<!-- jQuery Mapael -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/raphael/raphael.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-mapael/jquery.mapael.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-mapael/maps/usa_states.min.js"></script>
	<!-- ChartJS -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/chart.js/Chart.min.js"></script>

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


	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->

	<!-- Page specific script -->
	<script src="<%=request.getContextPath()%>/custom_js/topUtilization.js"></script>
</body>
</html>
