<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Map Topology</title>

<!-- Google Font: Source Sans Pro -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">

</head>
<body
	class="hold-transition layout-top-nav layout-fixed layout-navbar-fixed layout-footer-fixed">

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
							<h1>Indian Map Topology</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Map Topology</li>
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


							<div class="card card-primary">
								<div class="card-header">
									<h3 class="card-title">Map Topology</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<div id="indiaMap" style="height: 500px; width: 100%;"></div>
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

		<!--End Unutilized Ports  -->
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
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>

	<%-- <script src="<%=request.getContextPath()%>/webtemplate/map/highcharts.js"></script>
	<script src="<%=request.getContextPath()%>/webtemplate/map/map.js"></script>
	<script src="<%=request.getContextPath()%>/webtemplate/map/in-all.js"></script>
	<script src="<%=request.getContextPath()%>/webtemplate/map/accessibility.js"></script> --%>
	<!-- 	<script src="https://code.highcharts.com/highcharts.js"></script> -->
	<!-- 	<script src="https://code.highcharts.com/maps/modules/exporting.js"></script> -->
	<!-- 	<script src="https://code.highcharts.com/maps/modules/map.js"></script> -->


	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/highcharts.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/map.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/exporting.js"></script>
	<!-- 	<script -->
	<!-- 		src="https://code.highcharts.com/mapdata/countries/in/in-all.js"></script> -->
	<!-- 	<script src="https://code.highcharts.com/maps/modules/accessibility.js"></script> -->

	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/in-all.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/accessibility.js"></script>
	<!-- 	<script src="https://code.highcharts.com/modules/drilldown.js"></script> -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/drilldown.js"></script>

	<script src="<%=request.getContextPath()%>/custom_js/mapTopology.js"></script>
	<!-- Page specific script -->

</body>
</html>
