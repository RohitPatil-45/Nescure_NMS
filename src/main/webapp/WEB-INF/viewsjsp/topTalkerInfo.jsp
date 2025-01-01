<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NPM | Home</title>

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
	<!-- class="hold-transition dark-mode layout-top-nav layout-fixed layout-navbar-fixed layout-footer-fixed">  -->
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
							<h1 class="m-0">Top Talker Info</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
								<li class="breadcrumb-item active">Top Talker Info</li>
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


					<!-- Main row -->
					<div class="row">
						<!-- Left col -->


						<div class="col-md-6">
							<!-- PIE CHART -->
							<div class="card border-transparent">
								<div class="card-header">
									<h3 class="card-title">Top 5 Applications - Last 1 Hours</h3>

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
									<canvas id="topApplication"
										style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>

						<div class="col-md-6">
							<!-- PIE CHART -->
							<div class="card border-transparent">
								<div class="card-header">
									<h3 class="card-title">Top 5 Senders - Last 1 Hours</h3>

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
									<canvas id="topSender"
										style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>

						<!-- /.col -->

					</div>
					<!-- /.row -->


					<!-- Main row -->
					<div class="row">
						<!-- Left col -->


						<div class="col-md-6">
							<!-- PIE CHART -->
							<div class="card border-transparent">
								<div class="card-header">
									<h3 class="card-title">Top 5 receivers - Last 1 Hours</h3>

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
									<canvas id="topReceiver"
										style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>

						<div class="col-md-6">
							<!-- PIE CHART -->
							<div class="card border-transparent">
								<div class="card-header">
									<h3 class="card-title">Top 5 Conversations - Last 1 Hours</h3>

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
									<canvas id="TopSenderReciever"
										style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>

						<!-- /.col -->

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


	<script>
		var topApplicationData = {
			labels : [ 'HTTPS(443)', 'HTTP(80)', 'MySQL(3306)', 'Oracle(1521)',
					'SMTP(25)', ],
			datasets : [ {
				data : [ 84.72, 8.39, 3.03, 0.75, 0.57 ],
				backgroundColor : [ '#f56954', '#00a65a', '#f39c12', '#00c0ef',
						'#3c8dbc' ],
			} ]
		}

		var topSenderData = {
			labels : [ '10.1.24.172', '10.1.23.54', '10.1.34.34', '10.1.129.2',
					'10.2.23.4', ],
			datasets : [ {
				data : [ 50, 15, 20, 10, 5 ],
				backgroundColor : [ '#f56954', '#00a65a', '#f39c12', '#00c0ef',
						'#3c8dbc' ],
			} ]
		}

		var topReceiverData = {
			labels : [ '10.1.129.3', '10.1.134.5', '142.250.183.35',
					'172.217.167.174', '31.13.79.53', ],
			datasets : [ {
				data : [ 40, 30, 20, 7, 3 ],
				backgroundColor : [ '#f56954', '#00a65a', '#f39c12', '#00c0ef',
						'#3c8dbc' ],
			} ]
		}

		var topSenderReceiverData = {
			labels : [ 'S 10.1.24.172 - R 10.1.129.3',
					'S 10.1.23.54 - R 10.1.134.5',
					'S 10.1.24.172 - R 10.1.129.3',
					'S 10.1.24.172 - R 142.250.183.35',
					'S 10.1.24.172 - R 31.13.79.53', ],
			datasets : [ {
				data : [ 40, 30, 20, 7, 3 ],
				backgroundColor : [ '#f56954', '#00a65a', '#f39c12', '#00c0ef',
						'#3c8dbc' ],
			} ]
		}

		//-------------
		//- PIE CHART -
		//-------------
		// Get context with jQuery - using jQuery's .get() method.
		var devicepieChartCanvas = $('#topApplication').get(0).getContext('2d')
		var devicepieData = topApplicationData;
		var devicepieOptions = {
			maintainAspectRatio : false,
			responsive : true,
		}
		//Create pie or douhnut chart
		// You can switch between pie and douhnut using the method below.
		new Chart(devicepieChartCanvas, {
			type : 'pie',
			data : devicepieData,
			options : devicepieOptions
		})

		var linkpieChartCanvas = $('#topSender').get(0).getContext('2d')
		var linkpieData = topSenderData;
		var linkpieOptions = {
			maintainAspectRatio : false,
			responsive : true,
		}
		//Create pie or douhnut chart
		// You can switch between pie and douhnut using the method below.
		new Chart(linkpieChartCanvas, {
			type : 'pie',
			data : linkpieData,
			options : linkpieOptions
		})

		var topReceiverCanvas = $('#topReceiver').get(0).getContext('2d')
		var receiverdata = topReceiverData;
		var receiverpieOptions = {
			maintainAspectRatio : false,
			responsive : true,
		}
		//Create pie or douhnut chart
		// You can switch between pie and douhnut using the method below.
		new Chart(topReceiverCanvas, {
			type : 'pie',
			data : receiverdata,
			options : receiverpieOptions
		})

		var TopSenderRecieverCanvas = $('#TopSenderReciever').get(0)
				.getContext('2d')
		var senderreceiverdata = topSenderReceiverData;
		var senderreceiverpieOptions = {
			maintainAspectRatio : false,
			responsive : true,
		}
		//Create pie or douhnut chart
		// You can switch between pie and douhnut using the method below.
		new Chart(TopSenderRecieverCanvas, {
			type : 'pie',
			data : senderreceiverdata,
			options : senderreceiverpieOptions
		})
	</script>
</body>
</html>
