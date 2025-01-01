<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Interface Parameter</title>
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
</style>
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">

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

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">

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
		<div class="content-wrapper">


			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">

							<h1>Interface Parameter</h1>


						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>

								<li class="breadcrumb-item active">Interface Parameter</li>


							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<!-- left column -->
						<div class="col-md-12">
							<!-- jquery validation -->
							<div class="card card-primary">
								<div class="card-header">
									<h3 class="card-title">Interface Parameter</h3>
								</div>
								<!-- /.card-header -->
								<!-- form start -->
								<!--<form id="quickForm">-->

								<form method="post" id="setInterfaceParameter">
									<div class="card-body">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="exampleInputEmail1">Monitoring</label> <select
														id="monitoring" class="form-control" style="width: 100%;">

														<option value="Yes" label="Yes" />
														<option value="No" label="No" />

													</select>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">CRC History</label> <select
														id="crc" class="form-control" style="width: 100%;">

														<option value="Yes" label="Yes" />
														<option value="No" label="No" />

													</select>
												</div>

											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="exampleInputEmail1">BW History</label> <select
														id="bwHistory" class="form-control" style="width: 100%;">

														<option value="Yes" label="Yes" />
														<option value="No" label="No" />

													</select>
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">BW Threshold (%)</label>
													<input type="text" id="bwThreshold" class="form-control"
														placeholder="Enter BW Threshold" />
												</div>

											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="exampleInputEmail1">Mail Alert</label> <select
														id="mailAlert" class="form-control" style="width: 100%;">

														<option value="Yes" label="Yes" />
														<option value="No" label="No" />

													</select>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">SMS Alert</label> <select
														id="smsAlert" class="form-control" style="width: 100%;">

														<option value="Yes" label="Yes" />
														<option value="No" label="No" />

													</select>
												</div>

											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="exampleInputEmail1">Auto Ticketing</label> <select
														id="autoTicketing" class="form-control"
														style="width: 100%;">
														<option value="Yes" label="Yes" />
														<option value="No" label="No" />
													</select>
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Procure
														Bandwidth (Byte)</label> <input type="text" id="procureBW"
														class="form-control" placeholder="Enter Procure Bandwidth" />
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="exampleInputEmail1">Group Name</label>
													<form:select name="group_name" id="group_name" type="text"
														path="groupName" class="form-control" style="width: 100%;"
														items="${groupName}" onchange="groupDevices(this);">
													</form:select>
												</div>
											</div>

										</div>

										<br>
										<table name="example" id="example"
											class="display compact responsive nowrap stripe"
											style="width: 100%; display: none;">

											<thead>
												<tr>
													<th style="text-align: left">Select</th>
													<th style="text-align: left">Sr No</th>
													<th style="text-align: left">Interface Name</th>
													<th style="text-align: left">Interface IP</th>
													<th style="text-align: left">Alias Name</th>
													<th style="text-align: left">Interface Status</th>

													<th style="text-align: left">BW History Param</th>
													<th style="text-align: left">CRC History Param</th>
													<th style="text-align: left">Monitoring Param</th>
													<th style="text-align: left">BW Threshold</th>

													<th style="text-align: left">Node IP</th>
													<th style="text-align: left">Node Name</th>
													<th style="text-align: left">Group Name</th>
													<th style="text-align: left">Location</th>
													<th style="text-align: left">District</th>
													<th style="text-align: left">State</th>
													<th style="text-align: left">Zone</th>
												</tr>
											</thead>
											<tbody>

											</tbody>
										</table>
									</div>

									<div class="card-footer">
										<button type="button" class="btn btn-primary"
											onclick="addInterfaceParameter()">Submit</button>
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

	<!-- jQuery -->
	<%-- <script
		src="<%=request.getContextPath()%>/webtemplate/plugins/moment/moment.min.js"></script> --%>

	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- jquery-validation -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/additional-methods.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/datatablesJS/jquery-3.5.1.js"></script>
	<!-- Bootstrap 4 -->


	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->

	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>

	<!-- Select2 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/select2/js/select2.full.min.js"></script>

	<!-- DataTables -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net/js/jquery.dataTables.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net/js/dataTables.fixedHeader.js"></script>

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
		src="<%=request.getContextPath()%>/webtemplate/plugins/inputmask/jquery.inputmask.min.js"></script>

	<script>
		$(function() {

			$('[data-mask]').inputmask()
		})
	</script>

	<script
		src="<%=request.getContextPath()%>/custom_js/interfaceParameter.js"></script>


</body>
</html>
