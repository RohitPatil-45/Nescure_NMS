<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Network Topology updated</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/static/images/favicon.ico">
<link href="<%=request.getContextPath()%>/static/css/fontfamiliy.css"
	rel="stylesheet">

<link
	href="<%=request.getContextPath()%>/webtemplate/visjs/vis-network.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/adminpro-custon-icon.css">
<!-- 	<script -->
<%-- 		src="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/js/vendor/jquery-1.11.3.min.js"></script> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/multiselect/bootstrap-multiselect.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/meanmenu.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/jquery.mCustomScrollbar.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/animate.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/Lobibox.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/notifications.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/normalize.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/modals.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/c3.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/form/all-type-forms.css">


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/responsive.css">
<script
	src="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/js/vendor/modernizr-2.8.3.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/js/sweetalert2/sweetalert2.css" />
<script
	src="<%=request.getContextPath()%>/webtemplate/js/sweetalert2/sweetalert2.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/js/sweetalert2/sweetalert2.min.css" />
<script
	src="<%=request.getContextPath()%>/webtemplate/js/sweetalert2/sweetalert2.min.js"></script>
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getContextPath()%>/static/custom/dataTable/vis-network.min.css"> --%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/c3.min.css">
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
	width: 68%;
	height: 100%;
	margin: 00%;
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
	width: 1281px !important;
	height: 800px;
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
<body class="materialdesign">
	<div class="wrapper-pro"
		style="margin-left: -186px; margin-right: 28px;">
		<%-- 		<jsp:include page="/WEB-INF/views/dashboardsidebar.jsp" /> --%>
		<div class="content-inner-all">
			<!-- Header top area end -->

			<%-- <%
				String username = (String) session.getAttribute("username");
			%> --%>
			<br> <br>
			<div class="tree-viewer-area mg-b-15">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="sparkline13-list shadow-reset">
								<div class="sparkline13-hd">
									<div class="main-sparkline13-hd">
										<h5>
											<input type="box" readonly
												style="width: 10px; height: 10px; background-color: red">
											<span class="tooltiptext"
												style="overflow: auto; height: 350px; width: inherit;">Down</span>
											<input type="box" readonly
												style="width: 10px; height: 10px; background-color: #5FBF00">
											<span class="tooltiptext"
												style="overflow: auto; height: 350px; width: inherit;">Up</span>
											<div
												style="text-align: center; font-size: 20px; margin-top: -21px;">Auto
												Topology</div>

											</span>
										</h5>
									</div>
									<div class="box-tools pull-right">
										<button class="btn " onclick="abcccc()"
											style="float: left; margin-top: 5px; margin-left: 2%; color: black;">View
											History</button>

										<!-- 										<button type="button" class="btn btn-box-tool" -->
										<!-- 											data-widget="collapse"> -->
										<!-- 											<i class="fa fa-minus"></i> -->
										<!-- 										</button> -->

										<!-- 										<button type="button" class="btn btn-box-tool" -->
										<!-- 											data-widget="remove"> -->
										<!-- 											<i class="fa fa-times"></i> -->
										<!-- 										</button> -->
									</div>
								</div>
								<div class="admin-panel clearfix"
									style="background-color: white;">
									<div class="floating-box"
										style="width: 1281px !important; background-color: white">
										<div class="admin-panel clearfix">
											<div id="mynetwork"></div>
											<!-- 											<div id="forRighClick"> -->
											<!-- 												<span id="op"></span> -->

											<!-- 												<div id="cntnr"></div> -->

											<!-- 											</div> -->
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

				</div>
			</div>

		</div>
		<%-- <jsp:include page="/WEB-INF/views/FullFooter.jsp" /> --%>
	</div>

	<%-- <jsp:include page="/WEB-INF/views/allScriptingFiles.jsp" />
 --%>
	<%-- 	   <link href="<%=request.getContextPath()%>/static/visjs/vis-network.min.css" rel="stylesheet" type="text/css" /> --%>
	<!-- 	   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
	<!-- <script type="text/javascript" src="https://visjs.github.io/vis-network/standalone/umd/vis-network.min.js"></script> -->

	<script src="<%=request.getContextPath()%>/webtemplate/visjs/vis.js"></script>
	<%-- 	<script src="<%=request.getContextPath()%>/static/visjs/vis.js"></script> --%>
	<!-- 	<script -->
	<%-- 		src="<%=request.getContextPath()%>/static/custom/vis-network.minNew"></script> --%>

	<!-- 	<script -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>


	<script
		src="<%=request.getContextPath()%>/webtemplate/multiselect/bootstrap-multiselect.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/dataTable/custJsWidgit.js"></script>
	<script src="<%=request.getContextPath()%>/custom_js/AutoTopology.js"></script>
	<script>
		function abcccc() {

			var context = window.location.pathname.substring(0,
					window.location.pathname.indexOf("/", 2));
			var url = window.location.protocol + "//" + window.location.host
					+ context + "/Node/autoTopologyHistoryForm";
			open(url, "_blank");
		}
	</script>
</body>

</html>