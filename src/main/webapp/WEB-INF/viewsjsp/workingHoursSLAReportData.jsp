<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Device SLA Report '${fdate}' - '${tdate}'</title>

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
							<h1>Device SLA Report - Working Hours Report</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="<%=request.getContextPath()%>/nodeReport/slaReportForm">Device
										SLA Report</a></li>
								<li class="breadcrumb-item active">View Report</li>


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
									<h3 class="card-title">Device SLA Report</h3>
									&nbsp; &nbsp; &nbsp;<b>From Date :</b> ${fdate} <b>&nbsp;
										&nbsp; &nbsp;To Date :</b> ${tdate}

								</div>
								<!-- /.card-header -->
								<div class="card-body">


									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>Sr No</th>
												<th>NODE IP</th>
												<th>Device Name</th>
												<th>Group Name</th>
												<th>UPTIME</th>
												<th>DOWN</th>
												<th>UPTIME Hours</th>
												<th>DOWN Hours</th>
												<th>Total Hours</th>
												<th>UPTIME(%)</th>
												<th>DOWNTIME(%)</th>
												<th>YEARLY COST</th>
												<th>PENALTY(%)</th>
												<th>PENALTY COST(Rs)</th>
												<th>NEED TO PAY COST(Rs)</th>


											</tr>
										</thead>
										<tbody>

										</tbody>
										<tfoot>
											<tr>
												<th>Sr No</th>
												<th>NODE IP</th>
												<th>Device Name</th>
												<th>Group Name</th>
												<th>UPTIME</th>
												<th>DOWN</th>
												<th>UPTIME Hours</th>
												<th>DOWN Hours</th>
												<th>Total Hours</th>
												<th>UPTIME(%)</th>
												<th>DOWNTIME(%)</th>
												<th>YEARLY COST</th>
												<th>PENALTY(%)</th>
												<th>PENALTY COST(Rs)</th>
												<th>NEED TO PAY COST(Rs)</th>
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

	<!-- Page specific script -->
	<script>
		$(function() {
			$("#example1").DataTable(
					{
						data : ${slaReportData},
						"responsive" : true,
						"lengthChange" : false,
						"autoWidth" : false,
						"buttons" : [ "copy", "csv", "excel", "pdf", "print",
								"colvis" ]
					}).buttons().container().appendTo(
					'#example1_wrapper .col-md-6:eq(0)');
		}); 
	</script>
</body>
</html>
