<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Push SSH</title>
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
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/font-awesome.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/adminpro-custon-icon.css">
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
	href="<%=request.getContextPath()%>/webtemplate/sweetalert2/sweetalert2.min.css">
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
							<%-- 	<c:choose>
								<c:when test="${userId==null}">
									<!-- <h1>Bulk Upload</h1> -->
								</c:when>
								<c:otherwise>
									<h1>Edit User</h1>
								</c:otherwise>
							</c:choose> --%>

						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="<%=request.getContextPath()%>/dashboard/summaryPage">Home</a></li>
								<%-- <c:choose>
									<c:when test="${userId==null}">
										<li class="breadcrumb-item active">Bulk Upload</li>
									</c:when>
									<c:otherwise>
										<li class="breadcrumb-item active">Edit Company</li>
									</c:otherwise>
								</c:choose> --%>
								<li class="breadcrumb-item active">Push SSH</li>

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
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<!-- File Upload Details Div -->
							<div class="card card-primary" style="display: none;"
								id="checkPing">
								<div class="card-header">
									<h3 class="card-title">Push SSH</h3>
								</div>
								<div class="card-body"></div>
							</div>
							<!-- End File Upload Details Div -->
							<!-- jquery validation -->
							<div class="card card-primary" id="uploadFile">
								<div class="card-header">
									<h3 class="card-title">Push SSH</h3>

								</div>
								<div class="card-body">
									<div class="row">

										<div class="col-md-6">
											<div class="form-group">
												<label>Enter IP Address:</label>

												<div class="input-group">
													<span id="ipAddress22" 
														style="width: 100%;"></span>
												</div>
												<!-- /.input group -->
												<span id="checkSrcIP" style="display: none; color: red">Please
													Enter IP Address</span>
											</div>
											<div class="form-group">
												<label for="exampleInputPassword1">Password</label>
												<!--  <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">-->
												<input type="password" id="password" class="form-control"
													placeholder="Password" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label for="exampleInputPassword1">Username</label> <input
													type="text" id="username" class="form-control"
													placeholder="Username" />
											</div>
											<div class="form-group">
												<label>Command</label><br>
												<textarea type="text" id="command" class="form-control"
													tabindex="3" placeholder="Enter Command"
													style="width: 80%;"></textarea>
												<div class="btn btn-info" id="addbtnInclude"
													style="margin-left: 80%; margin-top: -13%; height: 60px; border: none; position: absolute; background-color: hsl(208, 81%, 30%);">
													<button class="btn btn-primary"></button>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6"></div>
												<div class="col-md-6">
													<div class="row" id="customValueInclude"
														style="display: none;">
														<div class="col-md-8">
															<div id="customValueDivInclude"></div>
														</div>

													</div>
												</div>
											</div>
										</div>

									</div>

								</div>


								<!-- /.card-body -->
								<div class="card-footer">
									<button type="button" class="btn btn-primary"
										onclick="pushSSH()">
										Submit<i style='display: none; margin-left: 10px;'
											id='pushSShSpinner' class='fa fa-spinner fa-spin'></i>
									</button>
								</div>

							</div>
							<!-- /.card -->
						</div>
						<!--/.col (left) -->
						<!-- right column -->
						<div class="col-md-6"></div>
						<!--/.col (right) -->
					</div>
					<!-- /.row -->
					<div class="row" id="pushSSH_result" style="display: none">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<div class="card card-primary">
								<div class="card-header">
									<h3 class="card-title">SSH Results</h3>

								</div>
								<div class="card-body">
									<center>
										<textarea id="result" rows="12" cols="90"></textarea>
									</center>
								</div>
							</div>
						</div>

					</div>
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

	<!-- Sweet Alert -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>
	<!-- Page specific script -->
	<script src="<%=request.getContextPath()%>/custom_js/pushSSH.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/inputmask/jquery.inputmask.min.js"></script>
	<script>
		$(function() {

			$('[data-mask]').inputmask()
		})
	</script>

</body>
</html>
