<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Dashboard 3</title>

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
</head>
<!--
`body` tag options:

  Apply one or more of the following classes to to the body tag
  to get the desired effect

  * sidebar-collapse
  * sidebar-mini
-->
<body class="hold-transition layout-top-nav">
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
							<h1 class="m-0">Interface Details - 192.168.0.1 ~
								GigabitEthernet0/0</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
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
									<div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-bars"></i>
										</a>
									</div>
								</div>
								<div class="card-body" style="height: 715px;">
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">IP Address</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">192.168.0.1</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Name</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">GigabitEthernet0/0</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Alias Name</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">Primary WAN</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Type</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">53</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Specific</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">29</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Admin Status</p>
										<p class="d-flex flex-column text-right">

											<span class="text-success mr-1"> <i
												class="fas fa-arrow-up"></i> up
											</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Operational Status</p>
										<p class="d-flex flex-column text-right">
											<span class="text-success mr-1"> <i
												class="fas fa-arrow-up"></i> up
											</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface IP Address</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">172.30.1.1</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">MTU</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">1500</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">CRC Count</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">20</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Bandwidth</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">1.86 Gbps(Transmit) 1.86
												Gbps(Receive)</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Current Throughput</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">8.0 Kbps(Transmit) 1.86
												Gbps(Receive)</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Input Error</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">8.0 Kbps(Transmit) 1.86
												Gbps(Receive)</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Output Error</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">8.0 Kbps(Transmit) 1.86
												Gbps(Receive)</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Discard Input</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">8.0 Kbps(Transmit) 1.86
												Gbps(Receive)</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Discard Output</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted">8.0 Kbps(Transmit) 1.86
												Gbps(Receive)</span>
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
										<h3 class="card-title">Real Time Bandwidth</h3>

									</div>
									<div id="container" style="height: 330px; margin: 0 auto"></div>
								</div>
							</div>
							<!-- /.card -->
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Interface Uptime</h3>

									</div>
								</div>
								<div class="card-body">
									<div class="d-flex">
										<p class="d-flex flex-column">
											<span>Uptime Over Time</span>
										</p>
										<p class="ml-auto d-flex flex-column text-right">
											<span class="text-muted">Month Wise (Avg %)</span>
										</p>
									</div>
									<!-- /.d-flex -->

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
							<!-- /.card -->

						</div>
						<!-- /.col-md-6 -->
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Last 5 Events of Interface</h3>
									<div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-bars"></i>
										</a>
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle">
										<thead>
											<tr>
												<th>Date</th>
												<th>Time</th>
												<th>Activity</th>
												<th>Interface Status</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>27/02/2020</td>
												<td>18:53:49</td>
												<td><span class="text-success mr-1"> <i
														class="fas fa-arrow-up"></i> up
												</span></td>
												<td>operational</td>
											</tr>
											<tr>
												<td>26/02/2020</td>
												<td>09:14:49</td>
												<td><span class="text-danger mr-1"> <i
														class="fas fa-arrow-down"></i> down
												</span></td>
												<td>operational</td>
											</tr>
											<tr>
												<td>25/02/2020</td>
												<td>12:54:49</td>
												<td><span class="text-success mr-1"> <i
														class="fas fa-arrow-up"></i> up
												</span></td>
												<td>operational</td>
											</tr>
											<tr>
												<td>24/02/2020</td>
												<td>14:03:49</td>
												<td><span class="text-danger mr-1"> <i
														class="fas fa-arrow-down"></i> down
												</span></td>
												<td>operational</td>
											</tr>
											<td>23/02/2020</td>
											<td>16:34:49</td>
											<td><span class="text-success mr-1"> <i
													class="fas fa-arrow-up"></i> up
											</span></td>
											<td>operational</td>
											</tr>

										</tbody>
									</table>
								</div>
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col-md-6 -->
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Last 5 Events of CRC Log</h3>
									<div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-bars"></i>
										</a>
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle">
										<thead>
											<tr>
												<th>Date</th>
												<th>Time</th>
												<th>CRC Before</th>
												<th>CRC Current</th>

											</tr>
										</thead>
										<tbody>
											<tr>
												<td>27/02/2020</td>
												<td>18:53:49</td>
												<td>45</td>
												<td>50</td>

											</tr>
											<tr>
												<td>26/02/2020</td>
												<td>09:14:49</td>
												<td>40</td>
												<td>45</td>

											</tr>
											<tr>
												<td>25/02/2020</td>
												<td>12:54:49</td>
												<td>35</td>
												<td>40</td>

											</tr>
											<tr>
												<td>24/02/2020</td>
												<td>14:03:49</td>
												<td>30</td>
												<td>35</td>

											</tr>
											<td>23/02/2020</td>
											<td>16:34:49</td>
											<td>25</td>
											<td>30</td>

											</tr>

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
	<!-- AdminLTE -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.js"></script>

	<!-- OPTIONAL SCRIPTS -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/chart.js/Chart.min.js"></script>
	<!-- Highchart Js -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/highcharts/highcharts.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/pages/dashboard4.js"></script>
	<script>
		Highcharts.chart('container', {
			chart : {
				type : 'spline',
				animation : Highcharts.svg, // don't animate in old IE
				marginRight : 10,
				events : {
					load : function() {
						var chart = this;
						setInterval(function() {
							chart.series.forEach(function(s) {
								for (var i = 0; i < 20; i++) {
									s.addPoint(Math.random(), false, true);
								}
							});
							chart.redraw();
						}, 2000);
					}
				}
			},
			series : [ {}, {} ].map(function(s) {
				s.data = [];
				for (var i = 0; i < 100; i++) {
					s.data.push(Math.random());
				}
				return s;
			})
		});
	</script>
</body>
</html>
