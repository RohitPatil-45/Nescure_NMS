<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Drive Details</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.css">
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
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">



</head>
<body class="hold-transition layout-top-nav">




	<div class="wrapper">

		<!-- Navbar -->

		<jsp:include page="header.jsp" />

		<!-- /.navbar -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<!--<c:choose>
				<c:when test="${ID==null }">-->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">


							<div class="card" style="margin-top: 20px;">
								<div class="card-header"
									style="background-color: black; color: white;">
									<h3 class="card-title">Drive Details</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">


									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>Sr NO</th>
												<th>Alias Name</th>
												<th>Device Name</th>
												<th>Branch Name</th>
												<th>Status</th>
												<th>Drive Name</th>
												<th>Drive Percentage</th>

											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>Canaris-01</td>
												<td>Computer A</td>
												<td>Branch 1</td>
												<td>Active</td>
												<td>C:</td>
												<td><b>
														<div class="progress">
															<div
																class="progress-bar progress-bar-danger progress-bar-striped active"
																role="" progressbar style="width: 85%; color: black;">85%</div>
														</div>
												</b></td>
											</tr>
											<tr>
												<td>2</td>
												<td>Canaris-02</td>
												<td>Computer B</td>
												<td>Branch 2</td>
												<td>Inactive</td>
												<td>D:</td>
												<td><b>
														<div class="progress">
															<div
																class="progress-bar progress-bar-danger progress-bar-striped active"
																role="" progressbar style="width: 75%; color: black;">85%</div>
														</div>
												</b></td>
											</tr>
											<tr>
												<td>3</td>
												<td>Canaris-03</td>
												<td>Computer C</td>
												<td>Branch 3</td>
												<td>Active</td>
												<td>E:</td>
												<td><b>
														<div class="progress">
															<div
																class="progress-bar progress-bar-danger progress-bar-striped active"
																role="" progressbar style="width: 30%; color: black;">85%</div>
														</div>
												</b></td>
											</tr>
											<tr>
												<td>4</td>
												<td>Canaris-04</td>
												<td>Computer D</td>
												<td>Branch 4</td>
												<td>Active</td>
												<td>F:</td>
												<td><b>
														<div class="progress">
															<div
																class="progress-bar progress-bar-danger progress-bar-striped active"
																role="" progressbar style="width: 90%; color: black;">85%</div>
														</div>
												</b></td>
											</tr>
											<tr>
												<td>5</td>
												<td>Canaris-05</td>
												<td>Computer E</td>
												<td>Branch 5</td>
												<td>Inactive</td>
												<td>G:</td>
												<td><b>
														<div class="progress">
															<div
																class="progress-bar progress-bar-danger progress-bar-striped active"
																role="" progressbar style="width: 40%; color: black;">85%</div>
														</div>
												</b></td>
											</tr>
											<tr>
												<td>6</td>
												<td>Canaris-06</td>
												<td>Computer F</td>
												<td>Branch 6</td>
												<td>Active</td>
												<td>H:</td>
												<td><b>
														<div class="progress">
															<div
																class="progress-bar progress-bar-danger progress-bar-striped active"
																role="" progressbar style="width: 60%; color: black;">85%</div>
														</div>
												</b></td>
											</tr>
											<tr>
												<td>7</td>
												<td>Canaris-07</td>
												<td>Computer G</td>
												<td>Branch 7</td>
												<td>Inactive</td>
												<td>I:</td>
												<td><b>
														<div class="progress">
															<div
																class="progress-bar progress-bar-danger progress-bar-striped active"
																role="" progressbar style="width: 20%; color: black;">85%</div>
														</div>
												</b></td>
											</tr>
											<tr>
												<td>8</td>
												<td>Canaris-08</td>
												<td>Computer H</td>
												<td>Branch 8</td>
												<td>Active</td>
												<td>J:</td>
												<td><b>
														<div class="progress">
															<div
																class="progress-bar progress-bar-danger progress-bar-striped active"
																role="" progressbar style="width: 85%; color: black;">85%</div>
														</div>
												</b></td>
											</tr>
											<tr>
												<td>9</td>
												<td>Canaris-09</td>
												<td>Computer I</td>
												<td>Branch 9</td>
												<td>Active</td>
												<td>K:</td>
												<td><b>
														<div class="progress">
															<div
																class="progress-bar progress-bar-danger progress-bar-striped active"
																role="" progressbar style="width: 55%; color: black;">85%</div>
														</div>
												</b></td>
											</tr>
											<tr>
												<td>10</td>
												<td>Canaris-010</td>
												<td>Computer J</td>
												<td>Branch 10</td>
												<td>Inactive</td>
												<td>L:</td>
												<td><b>
														<div class="progress">
															<div
																class="progress-bar progress-bar-danger progress-bar-striped active"
																role="" progressbar style="width: 70%; color: black;">85%</div>
														</div>
												</b></td>
											</tr>
										</tbody>

									</table>


								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!--</c:when>
			</c:choose>-->
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<jsp:include page="footer.jsp" />

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
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
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- Sweet Alert -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/select2/js/select2.full.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/inputmask/jquery.inputmask.min.js"></script>
	<%-- <script
		src="<%=request.getContextPath()%>/custom_js/configurationTemplate.js"></script> --%>
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
		/
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
	<script>
		$(function() {

			$('[data-mask]').inputmask()
		})
	</script>
	<script>
		$(function() {
			//Initialize Select2 Elements
			$('.select2').select2()

			//Initialize Select2 Elements
			$('.select2bs4').select2({
				theme : 'bootstrap4'
			})

		});

		var table = $("#example1").DataTable({
			lengthChange : false,
			autoWidth : false,
			pageLength : 10,
			scrollX : true,
			scrollY : true,
			buttons : [ "copy", "csv", "excel", "pdf", "print", "colvis" ]
		}).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
	</script>

</body>
</html>