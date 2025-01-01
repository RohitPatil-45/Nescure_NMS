<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Top Talker Report</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

.searchRow {
	display: none;
}

.sparkline11-graph {
	padding-bottom: 0
}

.btn-circle.btn-sm {
	width: 40px;
	height: 40px;
	padding: 9px 0px;
	border-radius: 20px;
	font-size: 15px;
	text-align: center;
}

.btn-circle.btn-sm:focus {
	outline: none !important;
}

.selectionOptions {
	padding: 5% 0%;
}

.radioOptions, .radioOptions1 {
	padding-right: 10px;
	cursor: pointer;
}
</style>
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/adminpro-custon-icon.css">

<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Select2 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bs-stepper/css/bs-stepper.min.css">
<!-- dropzonejs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/dropzone/min/dropzone.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">

<!-- daterange picker -->
<%-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.css"> --%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.css">


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">

<!-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatablesCSS/jquery.dataTables.css">-->

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

</head>




<body class="hold-transition layout-top-nav">


	<div class="wrapper">

		<!-- Navbar -->
		<jsp:include page="header.jsp" />
		<!-- /.navbar -->

		<!-- Content Wrapper. Contains page content -->
		<!-- <div class="content-wrapper"> -->

		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>

								<li class="breadcrumb-item active">Top Talker Report</li>


							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<div class="row" id="selection">
				<div class="col-lg-2 text-center"></div>
				<div class="col-lg-8 text-center">
					<div class="row">

						<div class="col-md-4">
							<button type="button"
								class="btn btn-success btn-circle btn-sm selectedDiv"
								id="ipWiseClick" value="ipWiseClick">1</button>
							<p>
								<small>Top Connection Wise</small>
							</p>
						</div>
						<div class="col-md-4">
							<button type="button" class="btn btn-default btn-circle btn-sm"
								id="SourceWiseClick" value="SourceWiseClick">2</button>
							<p>
								<small>Top Talker Wise</small>
							</p>
						</div>
						<div class="col-md-4">
							<button type="button" class="btn btn-default btn-circle btn-sm"
								id="ProtocolWiseClick" value="ProtocolWiseClick">3</button>
							<p>
								<small>Top Protocol Wise</small>
							</p>
						</div>

					</div>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<!-- /.content -->
			<!-- </div> -->


			<!-- // Ip wise Start -->
			<section class="content">
				<div class="container-fluid" style="width: 65%">
					<div class="row">
						<!-- left column -->
						<div class="col-md-12">
							<!-- jquery validation -->
							<div class="card card-primary">
								<div class="card-header">
									<h3 class="card-title">Top Talker Report</h3>
								</div>
								<form action="" id="date-wise-form">
									<div class="box box-primary">
										<br>
										<div class="box-body">
											<div class="row">
												<div class="col-md-2"></div>
												<div class="col-md-4">
													<div class="form-group">
														<label>From Date:</label>

														<div class="input-group date">
															<div class="input-group-addon">
																<i class="fa fa-calendar"></i>
															</div>
															<input type="text" class="form-control pull-right"
																placeholder="Select From Date" readonly="true"
																name="from_date" id="from_date">
														</div>
														<!-- /.input group -->
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label>To Date:</label>

														<div class="input-group date">
															<div class="input-group-addon">
																<i class="fa fa-calendar"></i>
															</div>
															<input type="text" class="form-control pull-right"
																placeholder="Select To Date" readonly="true"
																name="to_date" id="to_date">
														</div>
														<!-- /.input group -->
													</div>
												</div>
												<div class="col-md-2"></div>
											</div>

											<!-- /.form group -->
										</div>
										<br>
										<div class="row">
											<div class="col-md-4"></div>
											<div class="col-md-4">
												<div class="modal-footer">
													<button type="button" id="submit_btn"
														class="btn btn-primary">Generate Report</button>
													<button type="reset" id="resetDate2"
														class="btn btn-danger pull-right reset-btn"
														data-dismiss="modal">
														<span class="fa fa-remove"></span> Reset
													</button>

												</div>
											</div>
											<div class="col-md-4"></div>
										</div>

									</div>


								</form>
							</div>
							<!-- /.card -->
						</div>
						<!--/.col (left) -->
						<!-- right column -->
						<div class="col-md-6"></div>
						<!--/.col (right) -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- // End Ip wise Start -->


			<!-- // Ip Wise report div Start -->
			<div class="container-fluid" style="width: 65%">
				<div class="row datatable-buttons" style="display: none"
					id="listingdiv">
					<div class="col-md-12">
						<div class="panel panel-color panel-primary">
							<div class="panel-heading">
								<div id="tableheader" class="card-primary"></div>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<div class="table-responsive" style="width: 100%">
											<table id="datatable-buttons"
												class="table table-striped  table-hover">
												<thead style="background: silver">
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
											</table>
										</div>

									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- //Ip Wise report  div End -->


			<!-- // Source and Destination report div Start -->
			<div class="container-fluid" style="width: 65%">
				<div class="row datatable-buttons" style="display: none"
					id="listingdiv1">
					<div class="col-md-12">
						<div class="panel panel-color panel-primary">
							<div class="panel-heading">
								<div id="tableheader1" class="card-secondary"></div>
								<!--                                        <h5 class="card-header mt-0" id="h_6" style="display: none" ><span class="counter text-white"><span id="panelHeading1"></span >&nbsp;&nbsp;<span class="counter text-white pull-center "><b>Date:</b><span id="fromdate1"></span></span>&nbsp;&nbsp;<span class="counter text-white pull-center "><b>From Time:</b><span id="todate1"></span></span>&nbsp;&nbsp;<span class="counter text-white pull-center "><b>To Time:</b><span id="todate2"></span>&nbsp;&nbsp;<span class="counter text-white pull-center "><b>IP Address:</b><span id="group"></span></span>&nbsp;&nbsp;<span class="counter text-white pull-right "><a href="javascript:void(0);" id="show-latency-report1" ><i class="fa fa-backward text-white" ></i></a></span></h5>-->

								<!--<h3 class="panel-title"><span id="panelHeading"></span><span class="pull-center">From Date:<h4 id=""></h4></span></h3>-->
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<div class="table-responsive">
											<table id="datatable-buttons_Source"
												class="table table-striped  table-hover">
												<thead style="background: silver">
													<tr>
														<th>Sr No</th>
														<th>Source IP</th>
														<th>Destination IP</th>
														<th>Sum</th>


													</tr>
												</thead>


												<tbody>


												</tbody>
											</table>
										</div>

									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- // Source and Destination report div End -->



			<!-- // Protocol div Start -->
			<div class="container-fluid" style="width: 65%">
				<div class="row datatable-buttons" style="display: none"
					id="listingdiv2">
					<div class="col-md-12">
						<div class="panel panel-color panel-primary">
							<div class="panel-heading">
								<div id="tableheader2" class="card-primary"></div>
								<!--                                        <h5 class="card-header mt-0" id="h_6" style="display: none" ><span class="counter text-white"><span id="panelHeading1"></span >&nbsp;&nbsp;<span class="counter text-white pull-center "><b>Date:</b><span id="fromdate1"></span></span>&nbsp;&nbsp;<span class="counter text-white pull-center "><b>From Time:</b><span id="todate1"></span></span>&nbsp;&nbsp;<span class="counter text-white pull-center "><b>To Time:</b><span id="todate2"></span>&nbsp;&nbsp;<span class="counter text-white pull-center "><b>IP Address:</b><span id="group"></span></span>&nbsp;&nbsp;<span class="counter text-white pull-right "><a href="javascript:void(0);" id="show-latency-report1" ><i class="fa fa-backward text-white" ></i></a></span></h5>-->

								<!--<h3 class="panel-title"><span id="panelHeading"></span><span class="pull-center">From Date:<h4 id=""></h4></span></h3>-->
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<div class="table-responsive">
											<table id="datatable-buttons_protocol"
												class="table table-striped  table-hover">
												<thead style="background: silver">
													<tr>
														<th>Sr No</th>
														<th>Protocol</th>
														<th>Destination Octect</th>

													</tr>
												</thead>

												<tbody>
												</tbody>
											</table>
										</div>

									</div>

								</div>
							</div>
						</div>
					</div>
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

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/moment/moment.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatablesJS/jquery-3.5.1.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- jquery-validation -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/additional-methods.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->

	<!-- Sweet Alert -->

	<!-- date-range-picker -->
	<%-- <script
		src="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.js"></script> --%>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>
	<!-- Page specific script -->
	<script src="<%=request.getContextPath()%>/custom_js/companyMaster.js"></script>

	<!-- Select2 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/select2/js/select2.full.min.js"></script>

	<!-- <script
src="<%=request.getContextPath()%>/webtemplate/datatablesJS/jquery.dataTables.js"></script>-->


	<!-- DataTables -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net/js/jquery.dataTables.min.js"></script>
	<%-- <script src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/dataTables.bootstrap.min.js"></script> --%>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net/js/dataTables.fixedHeader.js"></script>

	<%-- <script src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/dataTables.editor.min.js"></script> --%>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/dataTables.select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/dataTables.colReorder.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/dataTables.buttons.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/jszip.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/vfs_fonts.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/buttons.html5.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/buttons.print.min.js"></script>


	<script
		src="<%=request.getContextPath()%>/custom_js/topTalkerReport.js"></script>

	<script>
		$(function() {
			$('#from_date').datepicker({
				autoclose : true,
				format : "yyyy-mm-dd",
				//			    viewMode: "months",
				//			    minViewMode: "months"
				todayHighlight : true
			});

			$('#to_date').datepicker({
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
