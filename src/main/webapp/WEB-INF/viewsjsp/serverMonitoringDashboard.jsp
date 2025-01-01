<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NPM | Server Monitoring</title>

<!-- Admin pro css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/adminpro-custon-icon.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/meanmenu.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/jquery.mCustomScrollbar.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/animate.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/Lobibox.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/notifications.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/normalize.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/modals.css">
<%-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/c3.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/form/all-type-forms.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/responsive.css"> --%>



<!-- Google Font: Source Sans Pro -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/adminpro-custon-icon.css">

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

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.css">

<style>
</style>

</head>
<body
	class="hold-transition layout-top-nav layout-fixed layout-navbar-fixed layout-footer-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<%-- <div
			class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__wobble"
				src="<%=request.getContextPath()%>/webtemplate/dist/img/AdminLTELogo.png"
				alt="AdminLTELogo" height="60" width="60">
		</div> --%>


		<!-- Navbar -->
		<jsp:include page="header.jsp" />

		<!-- /.navbar -->

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper" style="background-color: #e2e2e2">

			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-3">

							<span style="font-size: 22px;"><b>Server Monitoring
									Dashboard &nbsp;&nbsp;<i class="fa fa-pie-chart"
									id="statusLogo" style="color: rgb(77, 134, 189);"></i>
							</b></span>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<!-- Top Talker Dashboard charts -->

					<div class="row" style="margin-top: 2%;">
						<div class="col-md-12">
							<div class="row">
								<!-- CPU Helth div -->
								<div class="col-md-3" id="topTalkerDiv1" style="">
									<div class="row">
										<!-- 										<div class="col-md-1"></div> -->
										<div class="col-md-12 boxStyle">
											<div id="spinnerTopTalkChart">
												<center>
													<i style='margin-left: 10px; display: none;' id=''
														class='fa fa-spinner fa-spin fa-2x '></i>
												</center>
											</div>
											<div>

												<div id="topTalkercontainer"></div>

											</div>

										</div>
									</div>

								</div>

								<!-- Memory Health Div -->

								<div class="col-md-3" id="topConnectionDiv">
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-12 boxStyle">
											<div id="spinnerTopConnChart">
												<center>
													<i style='margin-left: 10px;' id=''
														class='fa fa-spinner fa-spin fa-2x '></i>
												</center>
											</div>
											<div>

												<div id="topConnectioncontainer"></div>

											</div>

										</div>
									</div>
								</div>

								<!-- Memory Health Div -->

								<div class="col-md-3" id="deliveryOptimization">
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-12 boxStyle">
											<div id="deliveryOptimization">
												<center>
													<i style='margin-left: 10px;' id=''
														class='fa fa-spinner fa-spin fa-2x '></i>
												</center>
											</div>
											<div>

												<div id="deliveryOptimization"></div>

											</div>

										</div>
									</div>
								</div>

								<!-- Disk health div -->
								<div class="col-md-3" id="topProtocolDiv1">
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-12 boxStyle">
											<div id="spinnerTopProtocolChart">
												<center>
													<i style='margin-left: 10px;' id=''
														class='fa fa-spinner fa-spin fa-2x '></i>
												</center>
											</div>
											<div>

												<div id="topProtocolcontainer"></div>

											</div>

										</div>
									</div>

								</div>

							</div>

							<div class="row">
								<div class="card col-md-12"
									style="margin-top: 10px; margin-left: 6px;">
									<div class="card-body">
										<div class="card-body table-responsive p-0"
											style="height: 400px;">
											<table class="table table-striped table-valign-middle"
												name="memoryUtilization" id="memoryUtilization">
												<thead>
													<tr>
														<th>Sr No.</th>
														<th>Device Name</th>
														<th>Service Name</th>
														<th>Availability</th>

													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>
									<!-- /.card-body -->
								</div>
								<center>
									<i style='display: none; margin-left: 10px;' id='spinner1'
										class='fa fa-spinner fa-spin fa-2x '></i>
							</div>
						</div>
					</div>
					<br>

				</div>
		</div>

		<!-- /.End charts -->

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

	<!-- 	high Charts -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dataTable/highstock.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.3/moment-with-locales.min.js"
		integrity="sha512-vFABRuf5oGUaztndx4KoAEUVQnOvAIFs59y4tO0DILGWhQiFnFHiR+ZJfxLDyJlXgeut9Z07Svuvm+1Jv89w5g=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->

	<!-- Page specific script -->
	<script
		src="<%=request.getContextPath()%>/custom_js/serverMonitoringDashboard.js"></script>
	<script>
		$(function() {
			$('#FromDate').datepicker({
				autoclose : true,
				format : "yyyy-mm-dd",
				//			    viewMode: "months",
				//			    minViewMode: "months"
				todayHighlight : true
			});

			$('#ToDate').datepicker({
				autoclose : true,
				format : "yyyy-mm-dd",
				//			    viewMode: "months",
				//			    minViewMode: "months"
				todayHighlight : true
			});

		});
	</script>

</body>
</html>
