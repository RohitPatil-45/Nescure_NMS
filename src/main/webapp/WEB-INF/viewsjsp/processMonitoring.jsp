<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Syslog</title>
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
									<h3 class="card-title">Process Details</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">


									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>Sr. No</th>
												<th>PID</th>
												<th>Alias Name</th>
												<th>Process Name</th>
												<th>CPU</th>
												<th>Memory</th>
												<th>Discover Time</th>
												<th>Thread Count</th>
												<th>Handle Count</th>
												<th>Peak Working Set</th>
												<th>Paged Pool</th>

											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>4740</td>
												<td>GTK-Reception</td>
												<td>jhi_service</td>
												<td>0.00012730578281523</td>
												<td>5732K</td>
												<td>2022-04-06 12:52:36</td>
												<td>136</td>
												<td>5732K</td>
												<td>188K</td>
												<td>82K</td>
											</tr>
											<tr>
												<td>2</td>
												<td>5678</td>
												<td>Sample-Alias-2</td>
												<td>process_2</td>
												<td>0.000256</td>
												<td>8192K</td>
												<td>2022-04-06 13:15:00</td>
												<td>150</td>
												<td>8192K</td>
												<td>256K</td>
												<td>100K</td>
											</tr>
											<tr>
												<td>3</td>
												<td>9101</td>
												<td>Another-Alias-3</td>
												<td>process_3</td>
												<td>0.000389</td>
												<td>10240K</td>
												<td>2022-04-06 14:30:45</td>
												<td>180</td>
												<td>10240K</td>
												<td>320K</td>
												<td>150K</td>
											</tr>
											<tr>
												<td>4</td>
												<td>1122</td>
												<td>Alias-4</td>
												<td>process_4</td>
												<td>0.000512</td>
												<td>4096K</td>
												<td>2022-04-06 15:45:20</td>
												<td>120</td>
												<td>4096K</td>
												<td>128K</td>
												<td>60K</td>
											</tr>
											<tr>
												<td>5</td>
												<td>2233</td>
												<td>Sample-Alias-5</td>
												<td>process_5</td>
												<td>0.000678</td>
												<td>6144K</td>
												<td>2022-04-06 16:10:55</td>
												<td>90</td>
												<td>6144K</td>
												<td>192K</td>
												<td>80K</td>
											</tr>
											<tr>
												<td>20</td>
												<td>9988</td>
												<td>Last-Alias-20</td>
												<td>process_20</td>
												<td>0.001024</td>
												<td>8192K</td>
												<td>2022-04-06 17:30:45</td>
												<td>200</td>
												<td>8192K</td>
												<td>256K</td>
												<td>120K</td>
											</tr>
											<tr>
												<td>2</td>
												<td>1234</td>
												<td>Sample-Alias</td>
												<td>sample_process_1</td>
												<td>0.001</td>
												<td>8192K</td>
												<td>2022-04-06 13:30:00</td>
												<td>150</td>
												<td>8192K</td>
												<td>256K</td>
												<td>100K</td>
											</tr>
											<tr>
												<td>3</td>
												<td>5678</td>
												<td>Another-Alias</td>
												<td>sample_process_2</td>
												<td>0.002</td>
												<td>10240K</td>
												<td>2022-04-06 14:15:45</td>
												<td>180</td>
												<td>10240K</td>
												<td>320K</td>
												<td>150K</td>
											</tr>
											<tr>
												<td>21</td>
												<td>1111</td>
												<td>New-Alias-21</td>
												<td>process_21</td>
												<td>0.001152</td>
												<td>10240K</td>
												<td>2022-04-06 18:15:30</td>
												<td>160</td>
												<td>10240K</td>
												<td>288K</td>
												<td>110K</td>
											</tr>
											<tr>
												<td>22</td>
												<td>2222</td>
												<td>Latest-Alias-22</td>
												<td>process_22</td>
												<td>0.001280</td>
												<td>12288K</td>
												<td>2022-04-06 18:45:00</td>
												<td>220</td>
												<td>12288K</td>
												<td>352K</td>
												<td>150K</td>
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