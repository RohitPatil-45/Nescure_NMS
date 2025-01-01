<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:choose>
	<c:when test="${adConfigId==null}">
		<title>Add SNMP Configuration</title>
	</c:when>
	<c:otherwise>
		<title>Edit SNMP Configuration</title>
	</c:otherwise>
</c:choose>


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
	href="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">


<script>
	function showhide() {

		var div = document.getElementById("versionid");
		//	alert(div.value)
		if (div.value == "version1" || div.value == "version2") {
			document.getElementById("communityid").style.display = 'block';
			document.getElementById("usernameid").style.display = 'none';
			document.getElementById("contextid").style.display = 'none';
			document.getElementById("authid").style.display = 'none';
			document.getElementById("authpwdid").style.display = 'none';
			document.getElementById("encryptid").style.display = 'none';
			document.getElementById("encryptpwdid").style.display = 'none';
		} else if (div.value == "version3") {
			document.getElementById("communityid").style.display = 'none';
			document.getElementById("usernameid").style.display = 'block';
			document.getElementById("contextid").style.display = 'block';
			document.getElementById("authid").style.display = 'block';
			document.getElementById("authpwdid").style.display = 'block';
			document.getElementById("encryptid").style.display = 'block';
			document.getElementById("encryptpwdid").style.display = 'block';

			var authdiv = document.getElementById("authProtocol");
			if (authdiv.value == "none") {
				$("#authPassword").prop('disabled', true);
				document.getElementById("encryptProtocol").disabled = true;
				$("#encryptPassword").prop('disabled', true);
			} else {
				$("#authPassword").prop('disabled', false);
				document.getElementById("encryptProtocol").disabled = false;
				$("#encryptPassword").prop('disabled', false);
			}

		}

	}

	function showhideDiv() {

		var authdiv = document.getElementById("authProtocol");
		var encrypdiv = document.getElementById("encryptid");

		if (authdiv.value == "none") {
			$("#authPassword").prop('disabled', true);
			document.getElementById("encryptProtocol").value = 'none';
			document.getElementById("encryptProtocol").disabled = true;
			$("#encryptPassword").prop('disabled', true);
		} else {
			$("#authPassword").prop('disabled', false);
			document.getElementById("encryptProtocol").disabled = false;
			$("#encryptPassword").prop('disabled', false);

			var encryptdiv = document.getElementById("encryptProtocol");
			if (encryptdiv.value == "none") {
				$("#encryptPassword").prop('disabled', true);
			} else {
				$("#encryptPassword").prop('disabled', false);
			}
		}
	}

	function showhideEncryptDiv() {

		var encryptdiv = document.getElementById("encryptProtocol");
		if (encryptdiv.value == "none") {
			$("#encryptPassword").prop('disabled', true);
		} else {
			$("#encryptPassword").prop('disabled', false);
		}
	}
</script>

<style>
#loading-overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100vw;
	height: 100vh;
	background: rgba(255, 255, 255, 0.8);
	display: flex;
	justify-content: center;
	align-items: center;
	z-index: 9999;
	display: none;
}

#loading-spinner {
	border: 8px solid #f3f3f3;
	border-top: 8px solid #3498db;
	border-radius: 50%;
	width: 50px;
	height: 50px;
	animation: spin 1s linear infinite;
	position: absolute;
	top: 50%;
	left: 50%;
	right: 50%;
}

@
keyframes spin { 0% {
	transform: rotate(0deg);
}
100%
{
transform
:
 
rotate
(360deg);
 
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
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<c:choose>
								<c:when test="${adConfigId==null}">
									<h1>Add SNMP Configuration</h1>
								</c:when>
								<c:otherwise>
									<h1>Edit SNMP Configuration</h1>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<c:choose>
									<c:when test="${adConfigId==null}">
										<li class="breadcrumb-item active">Add SNMP Configuration</li>
									</c:when>
									<c:otherwise>
										<li class="breadcrumb-item active">Edit SNMP
											Configuration</li>
									</c:otherwise>
								</c:choose>

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
									<c:choose>
										<c:when test="${adConfigId==null}">
											<h3 class="card-title">Add SNMP Configuration</h3>
										</c:when>
										<c:otherwise>
											<h3 class="card-title">Edit SNMP Configuration</h3>
										</c:otherwise>
									</c:choose>

								</div>
								<!-- /.card-header -->
								<!-- form start -->
								<!--<form id="quickForm">-->
								<form:form method="post" id="addSNMPConfigId"
									modelAttribute="fetchSNMPConfigInfo">
									<div class="card-body">

										<div class="row">



											<div class="col-md-6">

												<div class="form-group">
													<label for="exampleInputEmail1">Name</label>
													<form:input type="text" path="name" class="form-control"
														placeholder="Enter name" />
												</div>


												<div class="form-group">
													<label>Version</label> <select class="form-control"
														path="version" id="versionid" onchange="showhide()">
														<option value="version1">Version 1</option>
														<option value="version2">Version 2</option>
														<option value="version3">Version 3</option>
													</select>
												</div>
											</div>


											<div class="col-md-6">
												<div class="form-group">
													<label for="exampleInputPassword1">Description</label>

													<form:input type="text" path="description"
														class="form-control" placeholder="Enter description" />
												</div>

												<div class="form-group" id="communityid">
													<label for="exampleInputPassword1">Community</label>

													<form:input type="text" path="community"
														class="form-control" placeholder="Enter community name" />
												</div>

											</div>

											<div class="col-md-6">

												<div class="form-group" id="usernameid">
													<label for="exampleInputEmail1">Username</label>
													<form:input type="text" path="username"
														class="form-control" placeholder="Enter username" />
												</div>



												<div class="form-group" id="authid">
													<label for="exampleInputEmail1">Authentication
														Protocol</label> <select class="form-control" id="authProtocol"
														path="authProtocol" onchange="showhideDiv()">
														<option value="none">None</option>
														<option value="md5">MD5</option>
														<option value="sha1">SHA-1</option>
														<option value="sha256">SHA-256</option>
													</select>
												</div>

											</div>

											<div class="col-md-6">

												<div class="form-group" id="contextid">
													<label for="exampleInputEmail1">Context</label>
													<form:input type="text" path="context" class="form-control"
														placeholder="Enter context" />
												</div>

												<div class="form-group" id="authpwdid">
													<label for="exampleInputEmail1">Authentication
														Password</label>
													<form:input type="password" path="authPassword"
														class="form-control"
														placeholder="Enter authentication password" />
												</div>

											</div>

											<div class="col-md-6">

												<div class="form-group" id="encryptid">
													<label for="exampleInputEmail1">Encryption Protocol</label>

													<select class="form-control" id="encryptProtocol"
														path="encryptProtocol" onchange="showhideEncryptDiv()">
														<option value="none">None</option>
														<option value="des56">DES56</option>
														<option value="aes128">AES128</option>
														<option value="aes192">AES192</option>
														<option value="aes256">AES256</option>
													</select>
												</div>


												<div class="form-group" id="portid">
													<label for="exampleInputEmail1">Port</label>
													<form:input type="text" path="port" class="form-control"
														placeholder="Enter port" />
												</div>

											</div>

											<div class="col-md-6">

												<div class="form-group" id="encryptpwdid">
													<label for="exampleInputEmail1">Encryption Password</label>
													<form:input type="password" path="encryptPassword"
														class="form-control"
														placeholder="Enter encryption password" />
												</div>

												<div class="form-group" id="timeoutid">
													<label for="exampleInputEmail1">Timeout (sec)</label>
													<form:input type="text" path="timeout" class="form-control"
														placeholder="Enter timeout" />
												</div>

											</div>

											<div class="col-md-6">

												<div class="form-group" id="retriesid">
													<label for="exampleInputEmail1">Retries</label>
													<form:input type="text" path="retries" class="form-control"
														placeholder="Enter retries" />
												</div>


											</div>

											<form:input type="hidden" path="id" />

										</div>

									</div>
									<!-- /.card-body -->
									<div class="card-footer">
										<button type="submit" class="btn btn-primary">Submit</button>
									</div>
								</form:form>
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

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">


							<div class="card">
								<div class="card-header">
									<h3 class="card-title">SNMP Configuration Details</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">


									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>Sr. No</th>
												<!-- <th>Testing</th> -->
												<th>Name</th>
												<th>Description</th>
												<th>Version</th>
												<th>Community</th>
												<th>Username</th>
												<th>Auth. Protocol</th>
												<th>Context</th>
												<th>Auth. Password</th>
												<th>Encrypt. Protocol</th>
												<th>Port</th>
												<th>Encrypt. Password</th>
												<th>Timeout (Sec)</th>
												<th>Retries</th>
												<!-- <th>Edit</th> -->
												<th>Delete</th>

											</tr>
										</thead>
										<tbody>

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
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- jquery-validation -->
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
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- Sweet Alert -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>
	<!-- Page specific script -->
	<script src="<%=request.getContextPath()%>/custom_js/addSNMPConfig.js"></script>

	<!-- Select2 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/select2/js/select2.full.min.js"></script>

	<script>
		$(function() {
			//Initialize Select2 Elements
			$('.select2').select2()

			//Initialize Select2 Elements
			$('.select2bs4').select2({
				theme : 'bootstrap4'
			})

		});
	</script>
	<div id="loading-overlay">
		<div id="loading-spinner"></div>
	</div>
</body>
</html>
