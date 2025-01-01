<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Windows Hardware Inventory</title>

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

<style>
#spinnerTopConnChart {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(255, 255, 255, 0.9);
	z-index: 1000;
}
</style>
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
							<h1>Report</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Hardware Inventory</li>
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
									<h3 class="card-title">Windows Hardware Inventory</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<div id="spinnerTopConnChart">
										<center>
											<i style='margin-left: 10px; margin-top: 30%; color: blue'
												id='' class='fa fa-spinner fa-spin fa-2x'></i>
									</div>


									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>Device IP</th>
												<th>Device Name</th>
												<th>Mac Address</th>
												<th>Bios Name</th>
												<th>Bios Manufacturer</th>
												<th>Bios Version</th>
												<th>Bios Release Date</th>
												<th>Build Number</th>
												<th>Original Serial No</th>
												<th>OS Name</th>
												<th>OS Type</th>
												<th>OS Version</th>
												<th>Processor Name</th>
												<th>Processor Manufacturer</th>
												<th>Processor Count</th>
												<th>Domain</th>
												<th>Manufacturer</th>
												<th>Total Memory</th>
												<th>Disk Space</th>
												<th>Product ID</th>
												<th>Service Pack</th>
												<th>System Model</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach var="emp" items="${winInventory}">
												<tr>

													<td>${emp.device_ip}</td>
													<td>${emp.device_name}</td>
													<td>${emp.macAddress}</td>
													<td>${emp.biosName}</td>
													<td>${emp.biosManufacturer}</td>
													<td>${emp.biosVersion}</td>
													<td>${emp.biosReleaseDate}</td>
													<td>${emp.buildNumber}</td>
													<td>${emp.originalSerialNo}</td>
													<td>${emp.osName}</td>
													<td>${emp.osType}</td>
													<td>${emp.osVersion}</td>
													<td>${emp.processorName}</td>
													<td>${emp.processorManufacturer}</td>
													<td>${emp.processorCount}</td>
													<td>${emp.domain}</td>
													<td>${emp.manufacturer}</td>
													<td>${emp.totalMemory}</td>
													<td>${emp.diskSpace}</td>
													<td>${emp.productId}</td>
													<td>${emp.servicePack}</td>
													<td>${emp.systemModel}</td>

												</tr>
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<th>Device IP</th>
												<th>Device Name</th>
												<th>Mac Address</th>
												<th>Bios Name</th>
												<th>Bios Manufacturer</th>
												<th>Bios Version</th>
												<th>Bios Release Date</th>
												<th>Build Number</th>
												<th>Original Serial No</th>
												<th>OS Name</th>
												<th>OS Type</th>
												<th>OS Version</th>
												<th>Processor Name</th>
												<th>Processor Manufacturer</th>
												<th>Processor Count</th>
												<th>Domain</th>
												<th>Manufacturer</th>
												<th>Total Memory</th>
												<th>Disk Space</th>
												<th>Product ID</th>
												<th>Service Pack</th>
												<th>System Model</th>

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
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<script src="<%=request.getContextPath()%>/custom_js/addNode.js"></script>

	<!-- Page specific script -->
	<script>
		$(function() {
			$('#example1').DataTable({
				lengthChange : false,
				autoWidth : false,
				"pageLength" : 10,
				scrollX : true,
				scrollY : true,
				"buttons": ["copy", "csv", "excel", "pdf", "print",
					"colvis"],
				"initComplete" : function(settings, json) {
					// Hide loader once the DataTable is initialized
					$('#spinnerTopConnChart').hide();
				},
				"drawCallback" : function(settings) {
					// Hide loader after each draw (page change, etc.)
					$('#spinnerTopConnChart').hide();
				}
			}).buttons().container().appendTo(
			'#example1_wrapper .col-md-6:eq(0)');
		});
	</script>
</body>
</html>
