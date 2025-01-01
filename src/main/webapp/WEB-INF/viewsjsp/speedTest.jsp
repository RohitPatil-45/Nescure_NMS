<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Node Speed Test</title>
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
	href="<%=request.getContextPath()%>/webtemplate/css/fontAwesome.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
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
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/tree-viewer/tree-viewer.css">




<!-- DataTables -->
<%-- <link rel="stylesheet"
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
 --%>

<style type="text/css">
.speedbox {
	margin: 5em;
	height: 200px;
	width: 200px;
	display: flex;
	display: -webkit-flex;
	flex-direction: column;
	-webkit-flex-direction: column;
	align-items: center;
	-webkit-align-items: center;
	position: relative; & __groove { height : 100px;
	width: 200px;
	background: transparent;
	border-top-left-radius: 100px;
	border-top-right-radius: 100px;
	border: 20px solid #eee;
	border-bottom: 0;
	box-sizing: border-box;
	position: absolute;
	left: 0;
	top: 0;
}

}
.speedbox__score {
	position: absolute;
	left: 0;
	top: 0;
	transform: rotate(-45deg);
	-webkit-transform: rotate(100deg);
	height: 200px;
	width: 200px;
	background: transparent;
	border-radius: 50%;
	border: 20px solid #5c6f7b;
	border-color: #d41717 transparent #5c6f7b #0cdfcf;
	box-sizing: border-box;
	cursor: pointer;
	z-index: 1;
	transition: transform .3s ease;
}

.speedbox__base {
	width: 240px;
	height: 100px;
	background: white;
	position: relative;
	top: 100px;
	z-index: 20; &: before { content : '';
	width: 240px;
	position: absolute;
	top: 0;
	border-bottom: 1px solid #eee;
	box-shadow: 1px 3px 15px rgba(0, 0, 0, 0.5);
}

}
.speedbox__odo {
	text-align: center;
	position: absolute;
	color: #5c6f7b;
	bottom: 100px;
	left: 50%;
	transform: translateX(-50%); i { font-size : 13px;
	opacity: .6;
}

}
/* 
div {
	margin-bottom: 0;
}

span {
	font-size: .7em;
} */

/* 

__ping {
	font-size: 13px;
}

__up {
	font-size: 13px;
	line-height: 1.5em;
}

__down {
	font-size: 25px;
	text-shadow: 0 0 3px rgba(0, 0, 0, 0.2);
	line-height: 1.2em;
}
} */
.loader {
	display: block;
	position: relative;
	height: 32px;
	width: 200px;
	background: #fff;
	border: 2px solid black;
	color: red;
	overflow: hidden;
}

.loader::before {
	content: '';
	background: red;
	position: absolute;
	left: 0;
	top: 0;
	width: 0;
	height: 100%;
	animation: loading 10s linear infinite;
}

.loader:after {
	content: '';
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	text-align: center;
	font-size: 24px;
	line-height: 32px;
	color: rgb(0, 255, 255);
	mix-blend-mode: difference;
	animation: percentage 10s linear infinite;
}

@
keyframes loading { 0% {
	width: 0
}

100%
{
width








































































































































































:




















































































 




















































































100%
}
}
@
keyframes percentage { 0% {
	content: "0%"
}
5%
{
content








































































































































































:




















































































 




















































































"5%"
}
10%
{
content








































































































































































:




















































































 




















































































"10%"
}
20%
{
content








































































































































































:




















































































 




















































































"20%"
}
30%
{
content








































































































































































:




















































































 




















































































"30%"
}
40%
{
content








































































































































































:




















































































 




















































































"40%"
}
50%
{
content








































































































































































:




















































































 




















































































"50%"
}
60%
{
content








































































































































































:




















































































 




















































































"60%"
}
70%
{
content








































































































































































:




















































































 




















































































"70%"
}
80%
{
content








































































































































































:




















































































 




















































































"80%"
}
90%
{
content








































































































































































:




















































































 




















































































"90%"
}
95%
{
content








































































































































































:




















































































 




















































































"95%"
}
96%
{
content








































































































































































:




















































































 




















































































"96%"
}
97%
{
content








































































































































































:




















































































 




















































































"97%"
}
98%
{
content








































































































































































:




















































































 




















































































"98%"
}
99%
{
content








































































































































































:




















































































 




















































































"99%"
}
100%
{
content








































































































































































:




















































































 




















































































"100%"
}
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
<!-- 			<section class="content-header"> -->
<!-- 				<div class="container-fluid"> -->
<!-- 					<div class="row mb-2"> -->
<!-- 						<div class="col-sm-6"> -->

<!-- 							<h1 id="headerCard">Speed Test</h1> -->


<!-- 						</div> -->
<!-- 						<div class="col-sm-6"> -->
<!-- 							<ol class="breadcrumb float-sm-right"> -->
<!-- 								<li class="breadcrumb-item"><a href="#">Home</a></li> -->

<!-- 								<li class="breadcrumb-item active">Speed Test</li> -->


<!-- 							</ol> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				/.container-fluid -->
<!-- 			</section> -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
<!-- 					<div class="row"> -->
<!-- 						left column -->
<!-- 						<div class="col-md-12"> -->
<!-- 							jquery validation -->
<!-- 							<div class="card card-primary" id="speedTestForm"> -->
<!-- 								<div class="card-header"> -->
<!-- 									<h3 class="card-title">Speed Test</h3> -->
<!-- 								</div> -->
<!-- 								/.card-header -->
<!-- 								form start -->
<!-- 								<form id="quickForm"> -->

<%-- 								<form:form method="post" id="checkNodeSpeed" --%>
<%-- 									modelAttribute="speedtest"> --%>
<!-- 									<div class="card-body"> -->
<!-- 										<div class="row"> -->
<!-- 											<div class="col-md-6"> -->
<!-- 												IP mask -->
<!-- 																								<div class="form-group"> -->
<!-- 																									<label>Source IP:</label> -->

<!-- 																								<div class="input-group"> -->
<!-- 																									<div class="input-group-prepend"> -->
<!-- 																										<span class="input-group-text"><i -->
<!-- 																											class="fas fa-laptop"></i></span> -->
<!-- 																									</div> -->
<!-- 																									<input type="text" name="srcIP" id="srcIP" -->
<!-- 																										class="form-control" data-inputmask="'alias': 'ip'" -->
<!-- 																										data-mask /> -->
<!-- 																								</div> -->
<!-- 												<div class="form-group"> -->
<!-- 													<label>Source IP:</label> -->
<!-- 																									<div class="input-group-prepend"> -->
<!-- 																										<span class="input-group-text"> <i -->
<!-- 																											class="fas fa-laptop"></i> -->
<!-- 																										</span> -->
<!-- 																									</div> -->
<%-- 													<form:select type="text" path="device_IP" --%>
<%-- 														class="form-control select2" style="width: 100%;" --%>
<%-- 														placeholder="Select IP" name="srcIP" id="srcIP" --%>
<%-- 														items="${iplisting}" /> --%>
<!-- 													/.input group -->
<!-- 													<span id="checkSrcIP" style="display: none; color: red">Please -->
<!-- 														Select Source IP</span> -->
<!-- 												</div> -->

<!-- 																								<div class="form-group"> -->
<!-- 																									<label for="exampleInputPassword1">Source Interface</label> -->
<!-- 																									<input type="text" id="srcInterface" class="form-control" -->
<!-- 																										placeholder="Enter Source Interface Name" /> <span -->
<!-- 																										id="checkSrcName" style="display: none; color: red">Please -->
<!-- 																										Enter Source Interface Name</span> -->
<!-- 																								</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-md-6"> -->
<!-- 												<div class="form-group"> -->
<!-- 													<label>Destination IP:</label> -->

<!-- 																									<div class="input-group"> -->
<!-- 																										<div class="input-group-prepend"> -->
<!-- 																											<span class="input-group-text"><i -->
<!-- 																												class="fas fa-laptop"></i></span> -->
<!-- 																										</div> -->
<!-- 																										<input type="text" name="desIP" id="desIP" -->
<!-- 																											class="form-control" data-inputmask="'alias': 'ip'" -->
<!-- 																											data-mask /> -->
<!-- 																									</div> -->
<%-- 													<form:select type="text" path="device_IP" --%>
<%-- 														class="form-control select2" style="width: 100%;" --%>
<%-- 														placeholder="Select IP" name="desIP" id="desIP" --%>
<%-- 														items="${iplisting}" /> --%>
<!-- 													/.input group -->
<!-- 													<span id="checkDesIP" style="display: none; color: red">Please -->
<!-- 														Select Destination IP</span> -->
<!-- 												</div> -->
<!-- 																								<div class="form-group"> -->
<!-- 																									<label for="exampleInputPassword1">Destination -->
<!-- 																										Interface</label> <input type="text" id="desInterface" -->
<!-- 																										class="form-control" -->
<!-- 																										placeholder="Enter Destination Interface Name" /> <span -->
<!-- 																										id="checkDesName" style="display: none; color: red">Please -->
<!-- 																										Enter Destination Interface Name</span> -->
<!-- 																								</div> -->

<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="card-footer"> -->
<!-- 										<button type="button" class="btn btn-primary" -->
<!-- 											onclick="speedTest()">Submit</button> -->
<%-- 										<center>
<%-- 											<span class="loader" id="speedLoader"></span> --%>
<%-- 										</center> --%> 

<!-- 									</div> -->


<%-- 								</form:form> --%>
<!-- 							</div> -->
<!-- 							/.card -->


<!-- 							<div class="card" id="listingcardID"> -->
<!-- 								<div class="card-header"> -->
<!-- 									<h3 class="card-title">Speed Test List</h3> -->
<!-- 								</div> -->
<!-- 								/.card-header -->
<!-- 								<div class="card-body"> -->


<!-- 									<table id="example1" class="table table-bordered table-striped"> -->
<!-- 										<thead> -->
<!-- 											<tr> -->
<!-- 												<th>Device IP</th> -->
<!-- 												<th>Description</th> -->

<!-- 												<th>Discovery Time</th> -->
<!-- 												<th>Edit</th> -->

<!-- 											</tr> -->
<!-- 										</thead> -->
<!-- 										<tbody> -->
<%-- 											<c:forEach var="emp" items="${deviceslisting}"> --%>
<!-- 												<tr> -->

<%-- 													<td>${emp.device_IP}</td> --%>
<%-- 													<td>${emp.discription}</td> --%>
<%-- 													<td>${emp.timestamp}</td> --%>
<%-- 													<td><a href="editSpeedTestDeviceMaster/${emp.ID}">Edit</a></td> --%>

<!-- 												</tr> -->
<%-- 											</c:forEach> --%>
<!-- 										</tbody> -->
<!-- 										<tfoot> -->
<!-- 											<tr> -->
<!-- 												<th>Device IP</th> -->
<!-- 												<th>Description</th> -->

<!-- 												<th>Discovery Time</th> -->
<!-- 												<th>Edit</th> -->

<!-- 											</tr> -->
<!-- 										</tfoot> -->

<!-- 									</table> -->


<!-- 								</div> -->
<!-- 								/.card-body -->
<!-- 							</div> -->

<!-- 						</div> -->
<!-- 						/.col (left) -->
<!-- 						right column -->
<!-- 						<div class="col-md-6"></div> -->
<!-- 						/.col (right) -->
<!-- 					</div> -->

					<!-- /.row -->

					<div class="row">
						<div class="col-md-3"></div>
						<div class="col-md-6">
							<div class="card card-primary" id="showSpeedTest">
								<div class="row mb-2">
									<div class="col-sm-6">
										<h4 class="m-0">
											Speed Test of <span id="nodeipdetailsstore"><%=request.getParameter("nodeIP")%></span>
											~ <span id="interfacenamedetailsstroe"><%=request.getParameter("intName")%></span>
										</h4>
<!-- 										<button type="button" class="btn btn-primary" -->
<!-- 											onclick="speedtestinterfaceonclick()">Speed Test</button> -->
									</div>
								</div>
								<div class="card-header">

									<h3 class="card-title">Speed Test</h3>
								</div>
								<div class="form-group">
<!-- 									<div class="card-header"> -->
<!-- 										<h3 class="card-title"></h3> -->
<!-- 										<b>Source IP :</b> <span id="setSrcIP"></span> -->
<!-- 																				 &nbsp; &nbsp; -->
<!-- 																				&nbsp;<b>Source Interface :</b> <span id="setSrcInterface"></span> -->
<!-- 										<br> <br> <b>Destination IP :</b> <span -->
<!-- 											id="setDesIP"></span> -->
<!-- 																				&nbsp; &nbsp; &nbsp;<b>Destination Interface :</b> <span -->
<!-- 																					id="setDesInterface"></span> -->

<!-- 									</div> -->
									<div class="row" style="padding: 10px;">

										<i class="fa fa-spinner fa-spin" style="font-size: 24px"
											id="speedLoader"></i>
										<div class="card" id="resultping" rows="12" cols="90"
											style="width: 100%; background-color: black; color: white; padding: 2%;"></div>
									</div>
									<div class="row" style="padding: 10px; display: none;">
										<div class="col-md-5">
											<div class="speedbox" style="margin-left: 40px;">
												<div class="speedbox__score" id="speedbox-score"></div>
												<div class="speedbox__groove"></div>
												<div class="speedbox__odo">
													<div class="speedbox__ping">
														<i class="fa fa-clock-o"></i> 28<span>ms</span>
													</div>
													<div class="speedbox__up">
														<i class="fa fa-arrow-circle-up"></i> 30.33<span>mb/s</span>
													</div>
													<div class="speedbox__down">
														<i class="fa fa-arrow-circle-down"></i> 31.30<span>mb/s</span>
													</div>
												</div>
												<div class="speedbox__base"></div>

											</div>
										</div>
										<div class="col-md-7">
											<div class="info-box mb-3" style="background-color: #e2e2e2">
												<span class="info-box-icon"><i
													class="fas fa-cloud-download-alt" style="color: blue"></i></span>
												<!-- <i class="fas fa-cloud-download-alt"></i> -->
												<div class="info-box-content">
													<div class="row">
														<div class="col-md-4">
															<span class="info-box-text">DOWNLOAD</span> <span
																class="info-box-number">31.30 Mbps</span>
														</div>
														<div class="col-md-6">
															<img
																src="<%=request.getContextPath()%>/webtemplate/dist/img/graph.png"
																style="margin-top: -16px;">
														</div>
													</div>

												</div>

											</div>
											<div class="info-box mb-3" style="background-color: #e2e2e2">
												<span class="info-box-icon"><i
													class="fas fa-cloud-upload-alt" style="color: blue"></i></span>
												<div class="info-box-content">
													<div class="row">
														<div class="col-md-4">
															<span class="info-box-text">UPLOAD</span> <span
																class="info-box-number">30.33 Mbps</span>
														</div>
														<div class="col-md-6">
															<img
																src="<%=request.getContextPath()%>/webtemplate/dist/img/graph.png"
																style="margin-top: -16px;">
														</div>
													</div>

												</div>

											</div>
											<div class="info-box mb-3" style="background-color: #e2e2e2">
												<span class="info-box-icon"><i
													class="fa-solid fa-arrow-rotate-right"></i></span>
												<div class="info-box-content">
													<span class="info-box-text">PING</span> <span
														class="info-box-number">28 ms</span>
												</div>

											</div>
										</div>

									</div>
									<center>
										<button class="btn btn-primary" onclick="checkAgain()">back</button>
									</center>
								</div>
							</div>
						</div>
					</div>
					<!-- real time monitoring -->
					<!-- 		<div class="row">
						<div class="col-md-12">
							<div class="card card-primary" id="showNetworkSpeed" style="display: none;">
								<div class="card-header">
									<h3 class="card-title">Speed Test</h3>
								</div>
								<div class="form-group">
									<div class="card-header">
										<h3 class="card-title"></h3>
										&nbsp; &nbsp; &nbsp;<b>Source IP :</b> <span id="setSrcIP"></span>
										<b>&nbsp; &nbsp; &nbsp;Destination IP :</b> <span
											id="setDesIP"></span>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div id="networkChart"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div> -->
					<!-- real time monitoring -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->



		<!-- Footer  -->
		<%-- 		<jsp:include page="footer.jsp" /> --%>
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

	<%-- <script
		src="<%=request.getContextPath()%>/webtemplate/datatablesJS/jquery-3.5.1.js"></script> --%>
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
	<%-- 	<script
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
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/buttons.html5.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/buttons.print.min.js"></script>
 --%>
	<!-- 	<script src="https://code.highcharts.com/highcharts.js"></script> -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/vfs_fonts.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/highcharts.js"></script>
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
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/inputmask/jquery.inputmask.min.js"></script>
	<script>
		$(function() {

			$('[data-mask]').inputmask()
		})
	</script>

	<script src="<%=request.getContextPath()%>/custom_js/speedTest.js"></script>
	<script>
		$(function() {
			$("#example1").DataTable(
					{
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

