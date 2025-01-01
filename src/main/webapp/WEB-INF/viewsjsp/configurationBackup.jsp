<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Configuration Credential Master</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
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
								<c:when test="${userId==null}">
									<h1>Configuration Credential Master</h1>
								</c:when>
								<c:otherwise>
									<h1>Edit User</h1>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<c:choose>
									<c:when test="${userId==null}">
										<li class="breadcrumb-item active">Configuration
											Credential Master</li>
									</c:when>
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
										<c:when test="${userId==null}">
											<h3 class="card-title">Configuration Credential Master</h3>
										</c:when>
										<c:otherwise>
											<h3 class="card-title">Edit User</h3>
										</c:otherwise>
									</c:choose>

								</div>
								<!-- /.card-header -->
								<!-- form start -->
								<!--<form id="quickForm">-->
								<form:form method="post" modelAttribute="fetchConfigData"
									id="configBackupTemplate">
									<div class="card-body">
										<div class="row">
											<div class="col-md-3">
												<form:input type="hidden" path="ID" name="ID" />
												<%-- <div class="form-group">
													<label for="exampleInputPassword1">Device IP</label> <form:input
														type="text" path="deviceIp" name="deviceIp"
														class="form-control" placeholder="Enter Device Ip" />
												</div> --%>
												<div class="form-group">
													<label for="exampleInputPassword1">IP Address</label>
													<div class="input-group">
														<!-- <input type="text" id="ip" placeholder="Enter IP Address"
															class="form-control" data-inputmask="'alias': 'ip'"
															data-mask> -->
														<!-- <div id="multipleIP"> -->
														<form:select path="deviceIp" name="deviceIp" tabindex="1"
															class="form-control" style="width: 100%;"
															items="${configTemplates}" multiple="multiple">
														</form:select>
														<!-- </div> -->
													</div>

												</div>
												<c:choose>
													<c:when test="${ID==null }">
														<div class="form-group">
															<label for="exampleInputPassword1">Username</label>
															<form:input type="text" path="username" name="username"
																class="form-control" placeholder="Enter Username" />
														</div>
													</c:when>
												</c:choose>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label for="exampleInputPassword1">Device Mode</label>
													<form:select path="deviceMode" name="deviceMode"
														class="form-control select2">
														<form:option value="SSH">SSH</form:option>
														<form:option value="Telnet">Telnet</form:option>
													</form:select>
												</div>
												<c:choose>
													<c:when test="${ID==null }">
														<div class="form-group">
															<label for="exampleInputPassword1">Password</label>
															<form:input type="password" path="password"
																name="password" class="form-control"
																placeholder="Enter Password" />
														</div>
													</c:when>
												</c:choose>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label for="exampleInputPassword1">Full Output
														Command</label>
													<form:input path="foCommand" type="text" name="foCommand"
														class="form-control"
														placeholder="Enter Full Output Command" />
												</div>
												<c:choose>
													<c:when test="${ID==null }">
														<div class="form-group">
															<label for="exampleInputPassword1">Enable
																Password</label>
															<form:input type="password" path="enablePass"
																name="enablePass" class="form-control"
																placeholder="Enter Enable Password" />
														</div>
													</c:when>
												</c:choose>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label for="exampleInputPassword1">Configuration
														Command</label>
													<form:input path="confCommand" type="text"
														name="confCommand" class="form-control"
														placeholder="Enter Configuration Command" />
												</div>
											</div>
										</div>
									</div>
									<!-- /.card-body -->
									<div class="card-footer">
										<button type="button" id="addConfigurationBackup"
											class="btn btn-primary">Submit</button>
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
			<c:choose>
				<c:when test="${ID==null }">
					<section class="content">
						<div class="container-fluid">
							<div class="row">
								<div class="col-12">


									<div class="card">
										<div class="card-header">
											<h3 class="card-title">Configuration Credential Master
												Details</h3>
										</div>
										<!-- /.card-header -->
										<div class="card-body">


											<table id="viewConfigurationBackup"
												class="table table-bordered table-striped">
												<thead>
													<tr>
														<th>ID</th>
														<th>Device IP</th>
														<th>Device Mode</th>
														<th>Username</th>
														<th>Configuration Command</th>
														<th>Full Output Command</th>
														<th>Edit</th>
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
				</c:when>
			</c:choose>

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
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/select2/js/select2.full.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/inputmask/jquery.inputmask.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/custom_js/configurationBackup.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/multiselect/bootstrap-multiselect.min.js"></script>

	<!-- $(function() {

			$('[data-mask]').inputmask()
		}) -->
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
	</script>

</body>
</html>