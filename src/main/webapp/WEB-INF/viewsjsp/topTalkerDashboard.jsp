<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NPM | Top Talker</title>

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
		<div class="content-wrapper" style="background-color: #e2e2e2">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<!-- <div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Top Talker Dashboard</h1>
						</div>
						/.col
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Top Talker Dashboard</li>
							</ol>
						</div>
						/.col
					</div> -->
					<div class="row">
						<div class="col-md-3">

							<span style="font-size: 22px;"><b>Top Talker Dashboard
									&nbsp;&nbsp;<i class="fa fa-pie-chart" id="statusLogo"
									style="color: rgb(77, 134, 189);"></i>
							</b></span>
						</div>
						<div class="col-md-3">

							<div class="input-group date" style="width: 100%;">

								<!-- <div class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</div> -->
								<input type="text" readonly="true" class="form-control"
									style="background: #fff; cursor: pointer; padding: 5px 10px; width: 100%"
									name="FromDate" id="FromDate" data-toggle="tooltip"
									placeholder="Please Select From Date">

							</div>
						</div>
						<div class="col-md-4">

							<div class="input-group date" style="width: 67%;">

								<!-- <div class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</div> -->
								<input type="text" readonly="true" class="form-control"
									style="background: #fff; cursor: pointer; padding: 5px 10px; width: 100%"
									name="ToDate" id="ToDate" data-toggle="tooltip"
									placeholder="Please Select To Date">

							</div>
							<i style='display: none; margin-left: 10px;'
								id='spinner1Onchange' class='fa fa-spinner fa-spin'></i>
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



								<!-- Top Talker div -->
								<div class="col-md-4" id="topTalkerDiv1" style="">
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
									<div class="row">
										<!-- 										<div class="col-md-1"></div> -->
										<div class="card"
											style="margin-top: 10px; margin-left: 6px; width: 100%; height: 26em;">
											<!-- /.card-header -->
											<div class="card-body">
												<table id="topTalkerSourceIp" style="width: 100%;"
													class="table table-bordered table-striped">
													<thead>
														<tr>
															<th>SR No</th>
															<th>SOURCE IP</th>
															<th>DESTINATION IP</th>
															<th>DESTINATION OCTET</th>
														</tr>
													</thead>
													<tbody>

													</tbody>
													<!-- 	<tfoot>
														<tr>
															<th>SR No</th>
															<th>SOURCE IP</th>
															<th>DESTINATION IP</th>
															<th>DESTINATION OCTET</th>
														</tr>
													</tfoot> -->

												</table>
											</div>
											<!-- /.card-body -->
										</div>
										<center>
											<i style='display: none; margin-left: 10px;' id='spinner1'
												class='fa fa-spinner fa-spin fa-2x '></i>
										</center>
									</div>

								</div>

								<!-- Top Connection Div -->

								<div class="col-md-4" id="topConnectionDiv">
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
									<div class="row">
										<!-- 										<div class="col-md-1"></div> -->
										<div class="card"
											style="margin-top: 10px; margin-left: 6px; width: 100%; height: 26em;">
											<%-- <div class="card-header">
												<h3 class="card-title">Top Connection Wise Report</h3>
												&nbsp; &nbsp; &nbsp;<b>From Date :</b> ${fdate} <b>&nbsp;
													&nbsp; &nbsp;To Date :</b> ${tdate}
											</div> --%>
											<!-- /.card-header -->
											<div class="card-body">
												<table id="topConnectionSumOfDeviceTable"
													class="table table-bordered table-striped">
													<thead>
														<tr>
															<th>Sr No</th>
															<th>Source IP</th>
															<th>Destination IP</th>
															<th>Source Port</th>
															<th>Destination Port</th>
															<th>Protocol</th>
															<th>Destination Octect</th>
														</tr>
													</thead>
													<tbody>

													</tbody>
													<!-- <tfoot>
												<tr>
													<th>Sr No</th>
													<th>Source IP</th>
													<th>Destination IP</th>
													<th>Source Port</th>
													<th>Destination Port</th>
													<th>Protocol</th>
													<th>Destination Octect</th>
												</tr>
											</tfoot> -->

												</table>
											</div>
											<!-- /.card-body -->
										</div>
										<center>
											<i style='display: none; margin-left: 10px;' id='spinner1'
												class='fa fa-spinner fa-spin fa-2x '></i>
										</center>
									</div>

									<div class="row" style="padding-top: 1%; display: none">
										<div class="col-md-1"></div>
										<div class="col-md-12 boxStyle">
											<p></p>
											<div class="datatable-dashv1-list custom-datatable-overright"
												style="margin-top: 1%; margin-bottom: 5%;">

												<table class="table table-striped"
													id="topConnectionLogTable" style="width: 300%">
													<thead>
														<tr>
															<th>Sr No</th>
															<th>Source IP</th>
															<th>Destination IP</th>
															<th>Source Port</th>
															<th>Destination Port</th>
															<th>Protocol</th>
															<th>Destination Octate</th>
															<th>DateTime</th>
														</tr>
													</thead>
													<tbody>

													</tbody>
												</table>
												<center>
													<i style='display: none; margin-left: 10px;' id='spinner2'
														class='fa fa-spinner fa-spin fa-2x '></i>
												</center>
											</div>




										</div>
									</div>
								</div>

								<!-- Top Protocol div -->
								<div class="col-md-4" id="topProtocolDiv1">
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
									<div class="row">
										<!-- 										<div class="col-md-1"></div> -->
										<div class="card"
											style="margin-top: 10px; margin-left: 6px; width: 100%; height: 26em;">
											<%-- <div class="card-header">
												<h3 class="card-title">Top Connection Wise Report</h3>
												&nbsp; &nbsp; &nbsp;<b>From Date :</b> ${fdate} <b>&nbsp;
													&nbsp; &nbsp;To Date :</b> ${tdate}
											</div> --%>
											<!-- /.card-header -->
											<div class="card-body">
												<table id="topProtocolSumOfDeviceTable"
													class="table table-bordered table-striped">
													<thead>
														<tr>
															<th>Sr No</th>
															<th>Protocol</th>
															<th>Destination Octect</th>
														</tr>
													</thead>
													<tbody>

													</tbody>
													<!-- <tfoot>
												<tr>
													<th>Sr No</th>
													<th>Source IP</th>
													<th>Destination IP</th>
													<th>Source Port</th>
													<th>Destination Port</th>
													<th>Protocol</th>
													<th>Destination Octect</th>
												</tr>
											</tfoot> -->

												</table>
											</div>
											<!-- /.card-body -->
										</div>
										<center>
											<i style='display: none; margin-left: 10px;' id='spinner1'
												class='fa fa-spinner fa-spin fa-2x '></i>
										</center>
									</div>

								</div>

							</div>

						</div>

						<!-- Top Connection Div -->

						<%-- <div class="col-md-4" id="topConnectionDiv">
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
							<div class="row">
								<!-- 										<div class="col-md-1"></div> -->
								<div class="card"
									style="margin-top: 10px; margin-left: 6px; width: 484px;">
									<div class="card-header">
												<h3 class="card-title">Top Connection Wise Report</h3>
												&nbsp; &nbsp; &nbsp;<b>From Date :</b> ${fdate} <b>&nbsp;
													&nbsp; &nbsp;To Date :</b> ${tdate}
											</div>
									<!-- /.card-header -->
									<div class="card-body">
										<table id="topConnectionSumOfDeviceTable"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr No</th>
													<th>Source IP</th>
													<th>Destination IP</th>
													<th>Source Port</th>
													<th>Destination Port</th>
													<th>Protocol</th>
													<th>Destination Octect</th>
												</tr>
											</thead>
											<tbody>

											</tbody>
											<tfoot>
												<tr>
													<th>Sr No</th>
													<th>Source IP</th>
													<th>Destination IP</th>
													<th>Source Port</th>
													<th>Destination Port</th>
													<th>Protocol</th>
													<th>Destination Octect</th>
												</tr>
											</tfoot>

										</table>
									</div>
									<!-- /.card-body -->
								</div>
								<center>
									<i style='display: none; margin-left: 10px;' id='spinner1'
										class='fa fa-spinner fa-spin fa-2x '></i>
								</center>
							</div>

							<div class="row" style="padding-top: 1%; display: none">
								<div class="col-md-1"></div>
								<div class="col-md-12 boxStyle">
									<p></p>
									<div class="datatable-dashv1-list custom-datatable-overright"
										style="margin-top: 1%; margin-bottom: 5%;">

										<table class="table table-striped" id="topConnectionLogTable"
											style="width: 300%">
											<thead>
												<tr>
													<th>Sr No</th>
													<th>Source IP</th>
													<th>Destination IP</th>
													<th>Source Port</th>
													<th>Destination Port</th>
													<th>Protocol</th>
													<th>Destination Octate</th>
													<th>DateTime</th>
												</tr>
											</thead>
											<tbody>

											</tbody>
										</table>
										<center>
											<i style='display: none; margin-left: 10px;' id='spinner2'
												class='fa fa-spinner fa-spin fa-2x '></i>
										</center>
									</div>




								</div>
							</div>
						</div>
 --%>

						<!-- Top Protocol div -->
						<!-- 						<div class="col-md-4" id="topProtocolDiv1" style="">
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
							<div class="row">
								<div class="col-md-1"></div>
								<div class="col-md-12 boxStyle" style="margin-top: 1%;">

									<div class="datatable-dashv1-list custom-datatable-overright"
										style="margin-top: 1%; margin-bottom: 2%;">

										<table class="table table-striped"
											id="topProtocolSumOfDeviceTable" style="width: 100%;">
											<thead>
												<tr>
													<th>Sr No</th>

													<th>Protocol</th>

													<th>Destination Octect</th>

												</tr>
											</thead>
											<tbody>

											</tbody>
										</table>
										<center>
											<i style='display: none; margin-left: 10px;'
												id='spinnerProtocoloList'
												class='fa fa-spinner fa-spin fa-2x '></i>
										</center>
									</div>




								</div>
							</div>

						</div> -->
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

	<!-- 	<script -->
	<!-- 		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.3/moment-with-locales.min.js" -->
	<!-- 		integrity="sha512-vFABRuf5oGUaztndx4KoAEUVQnOvAIFs59y4tO0DILGWhQiFnFHiR+ZJfxLDyJlXgeut9Z07Svuvm+1Jv89w5g==" -->
	<!-- 		crossorigin="anonymous" referrerpolicy="no-referrer"></script> -->

	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/moment-with-locales.min.js"
		integrity="sha512-vFABRuf5oGUaztndx4KoAEUVQnOvAIFs59y4tO0DILGWhQiFnFHiR+ZJfxLDyJlXgeut9Z07Svuvm+1Jv89w5g=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->

	<!-- Page specific script -->
	<script
		src="<%=request.getContextPath()%>/custom_js/topTalkerDashboard.js"></script>
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
