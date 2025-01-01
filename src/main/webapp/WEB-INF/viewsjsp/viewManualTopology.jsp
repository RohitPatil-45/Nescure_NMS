<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Manual Topology</title>

<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/webtemplate/dist/img/AdminLTELogo.png">
<link href="<%=request.getContextPath()%>/static/css/fontfamiliy.css"
	rel="stylesheet">
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

<style>
/*  ul { */
/*             list-style-type: none; */
/*         } */
.swal2-container.swal2-shown {
	background-color: rgba(0, 0, 0, .4);
	/*     width: 20%; */
	/* height:20%; */
}

#items {
	list-style: none;
	margin: 0px;
	margin-top: 4px;
	padding-left: 10px;
	padding-right: 10px;
	padding-bottom: 3px;
	font-size: 17px;
	color: #333333;
}
/*         hr { width: 85%; */
/*              background-color:#E4E4E4; */
/*              border-color:#E4E4E4; */
/*              color:#E4E4E4; */
/*         } */
#cntnr {
	display: none;
	position: fixed;
	border: 1px solid #B2B2B2;
	/*                          width:150px;   */
	background: #F9F9F9;
	box-shadow: 2px 2px 2px #E9E9E9;
	border-radius: 4px;
}

li {
	padding: 3px;
	padding-left: 10px;
	cursor: default;
}

/*         li:hover{ */
/*             background: #0DB0E7; */
/*         } */
select {
	cursor: pointer;
}

input [button] {
	cursor: pointer;
}

.slidebar {
	width: 188px;
	min-height: 00px;
	float: left;
	margin: 0 0 0 -180px;
	border-right: 1px solid rgb(235, 235, 235);
	background-color: rgb(247, 247, 247);
	background: none repeat scroll 0 0 #eee;
}

.floating-box {
	float: left;
	width: 100% !important;
	height: 100%;
	margin: 00%;
	margin-top: 1em !important;
	/*border: 3px solid #73AD21;*/
}

.floating-box1 {
	float: left;
	width: 16%;
	height: 100%;
	margin: 00%;
	/*border: 3px solid #73AD21;*/
}

.after-box1 {
	clear: left;
	border-left: 2px solid #00CCFF;
	border-right: 2px solid #00CCFF;
	border-top: 2px solid #00CCFF;
	/*border-bottom: 2px solid brown;*/
	/*border: 3px solid red;*/
}

.after-box {
	clear: left;

	/*border-bottom: 2px solid brown;*/
	/*border: 3px solid red;*/
}

.after-box2 {
	clear: left;

	/*border-bottom: 2px solid brown;*/
	/*border: 3px solid red;*/
}

#mynetwork {
	/* 	width: 1232px; */
	/* 	height: 720px; */
	/* 	border: 2px solid cyan; */
	/* 	/*             background-color:#333333; */ */
	/* 	background-color: black; */
	/* 	margin-left: 3%; */
	/* 	margin-top: 10px; */
	width: 100% !important;
	height: 700px;
	border: 1px solid #444444;
	background-color: #000000;
}

.btn-group {
	margin-bottom: 5%;
	width: 180px;
}

.multiselect-container {
	max-height: 200px;
	overflow: auto;
	padding-right: 5px;
}

.fixed-header-top {
	left: 0px;
	transition: all 0.3s;
}

.sparkline13-hd .btn {
	color: #fff
}

.sparkline13-hd:before, .box-body:before, .box-footer:before,
	.sparkline13-hd:after, .box-body:after, .box-footer:after {
	content: " ";
	display: table
}

.sparkline13-hd:after, .box-body:after, .box-footer:after {
	clear: both
}

.sparkline13-hd {
	display: block;
	padding: 10px;
	position: relative
}

.sparkline13-list>.sparkline13-hd>.box-tools .btn {
	border: 0;
	box-shadow: none
}

.sparkline13-hd.with-border {
	border-bottom: 1px solid #f4f4f4
}

.collapsed-box .sparkline13-hd.with-border {
	border-bottom: none
}

.sparkline13-hd>.fa, .sparkline13-hd>.glyphicon, .sparkline13-hd>.ion,
	.sparkline13-hd .box-title {
	display: inline-block;
	font-size: 18px;
	margin: 0;
	line-height: 1
}

.sparkline13-hd>.fa, .sparkline13-hd>.glyphicon, .sparkline13-hd>.ion {
	margin-right: 5px
}

.sparkline13-hd>.box-tools {
	position: absolute;
	right: 10px;
	top: 5px
}

.sparkline13-hd>.box-tools [data-toggle="tooltip"] {
	position: relative
}

.sparkline13-hd>.box-tools.pull-right .dropdown-menu {
	right: 0;
	left: auto
}

.sparkline13-hd>.box-tools .dropdown-menu>li>a {
	color: #444 !important
}

.btn-box-tool {
	padding: 10px 5px;
	font-size: 15px;
	background: transparent;
	color: #97a0b3
}

.open .btn-box-tool, .btn-box-tool:hover {
	color: #606c84
}

.btn-box-tool.btn:active {
	box-shadow: none
}

.btn-group-vertical .btn.btn-flat:first-of-type, .btn-group-vertical .btn.btn-flat:last-of-type
	{
	border-radius: 0
}

.dropdown-menu:hover {
	cursor: pointer;
}

.sparkline13-hd {
	padding: 15px 20px;
}

.dropdown-menu>.divider {
	background-color: #eee;
}

.clickableLinks {
	cursor: pointer;
}
</style>
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
							<h1>Manual Topology</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Manual Topology</li>
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
									<h3 class="card-title">Manual Topology</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<button class="btn btn-primary" onclick="abcccc()"
										style="float: right;">View History</button>
									<div class="floating-box">
										<div class="admin-panel clearfix">
											<div id="mynetwork"></div>
										</div>
									</div>
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
	<script src="<%=request.getContextPath()%>/webtemplate/visjs/vis.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/multiselect/bootstrap-multiselect.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/dataTable/custJsWidgit.js"></script>
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
	<!-- 	<script src="https://code.highcharts.com/highcharts.js"></script> -->
	<!-- 	<script src="https://code.highcharts.com/maps/modules/map.js"></script> -->
	<!-- 	<script -->
	<!-- 		src="https://code.highcharts.com/mapdata/countries/in/in-all.js"></script> -->
	<!-- 	<script src="https://code.highcharts.com/maps/modules/accessibility.js"></script> -->


	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/highcharts.js"></script>	
		
<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/map.js"></script>	

<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/in-all.js"></script>

<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/accessibility.js"></script>	

	<script
		src="<%=request.getContextPath()%>/custom_js/viewManualTopology.js"></script>
	<!-- Page specific script -->
	<script>
		function abcccc() {

			var context = window.location.pathname.substring(0,
					window.location.pathname.indexOf("/", 2));
			var url = window.location.protocol + "//" + window.location.host
					+ context + "/nodeDashboard/autoTopologyHistoryForm";
			open(url, "_blank");
		}
	</script>
</body>
</html>
