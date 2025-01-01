<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Interface Monitoring Report | DataTables</title>

<!-- Google Font: Source Sans Pro -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<!-- DataTables -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">



<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">
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
							<h1>Node Report</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">InterfaceMonitoringView</li>
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
						<div class="col-12">


							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Interface Monitoring View</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">


									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>Node IP</th>
												<th>Interface Name</th>
												<th>Interface ID</th>
												<th>Interface Type</th>
												<th>Admin Status</th>
												<th>Operator Status</th>
												<th>Procedure Bandwidth</th>
												<th>Interface Mac Address</th>
												<th>Interface IP</th>
												<th>Alias Name</th>
												<th>CRC Error</th>
												<th>Out Traffic</th>
												<th>In Traffic</th>
												<th>Discard Input</th>
												<th>Discard Output</th>
												<th>Interface Error</th>
												<th>Interface Input Error</th>
												<th>Interface Output Error</th>
												<th>Monitoring Param</th>
												<th>Mail Alert</th>
												<th>SMS Alert</th>
												<th>Auto Ticketing</th>
												<th>Bandwidth Threshold</th>
												<th>Bandwidth History Param</th>
												<th>CRC Error Param</th>



											</tr>
										</thead>
										<tbody>
											<%-- <c:forEach var="emp" items="${node}">
												<tr>

													<td>${emp.NODE_IP}</td>
													<td>${emp.INTERFACE_NAME}</td>
													<td>${emp.INTERFACE_ID}</td>
													<td>${emp.INTERFACE_TYPE}</td>
													<td>${emp.ADMIN_STATUS}</td>
													<td>${emp.OPER_STATUS}</td>
													<td>${emp.PROCURED_BANDWIDTH}</td>
													<td>${emp.INTERFACE_MACADDRESS}</td>
													<td>${emp.INTERFACE_IP}</td>
													<td>${emp.ALIAS_NAME}</td>
													<td>${emp.CRC_ERROR}</td>
													<td>${emp.OUT_TRAFFIC}</td>
													<td>${emp.IN_TRAFFIC}</td>
													<td>${emp.DISCARD_INPUT}</td>
													<td>${emp.DISCARD_OUTPUT}</td>
													<td>${emp.INTERFACE_INPUT_ERROR}</td>
													<td>${emp.INTERFACE_OUTPUT_ERROR}</td>
													<td>${emp.INTERFACE_ERROR}</td>
													<td>${emp.MONITORING_PARAM}</td>
													<td>${emp.MAIL_ALERT}</td>
													<td>${emp.SMS_ALERT}</td>
													<td>${emp.AUTO_TICKETING}</td>
													<td>${emp.BW_THRESHOLD}</td>
													<td>${emp.BW_HISTORY_PARAM}</td>
													<td>${emp.CRC_HISTORY_PARAM}</td>
													

												</tr>
											</c:forEach> --%>
										</tbody>
										<tfoot>
											<tr>
												<th>Node IP</th>
												<th>Interface Name</th>
												<th>Interface ID</th>
												<th>Interface Type</th>
												<th>Admin Status</th>
												<th>Operator Status</th>
												<th>Procedure Bandwidth</th>
												<th>Interface Mac Address</th>
												<th>Interface IP</th>
												<th>Alias Name</th>
												<th>CRC Error</th>
												<th>Out Traffic</th>
												<th>In Traffic</th>
												<th>Discard Input</th>
												<th>Discard Output</th>
												<th>Interface Error</th>
												<th>Interface Input Error</th>
												<th>Interface Output Error</th>
												<th>Monitoring Param</th>
												<th>Mail Alert</th>
												<th>SMS Alert</th>
												<th>Auto Ticketing</th>
												<th>Bandwidth Threshold</th>
												<th>Bandwidth History Param</th>
												<th>CRC Error Param</th>

											</tr>
										</tfoot>

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
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
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

	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
	<script src="<%=request.getContextPath()%>/custom_js/addNode.js"></script>

	<!-- Page specific script -->
	<script>
		$(function() {
			let data = [];
			for (let i = 0; i < 50000; i++) {
			    data.push([i, i, i, i, i]);
			}
			$("#example1").DataTable(
					{
						"scrollX" : true,
						"data":${node},
						/* "responsive" : true, */
						"lengthChange" : false,
						"autoWidth" : true,
						"buttons" : [ "copy", "csv", "excel", "pdf", "print",
								"colvis" ]
					}).buttons().container().appendTo(
					'#example1_wrapper .col-md-6:eq(0)');
		});
	</script>
</body>
</html>
