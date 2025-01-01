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
	<c:when test="${userId==null}">
		<title>Add User</title>
	</c:when>
	<c:otherwise>
		<title>Edit User</title>
	</c:otherwise>
</c:choose>


<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome -->
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
									<h1>Add User</h1>
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
										<li class="breadcrumb-item active">Add User</li>
									</c:when>
									<c:otherwise>
										<li class="breadcrumb-item active">Edit User</li>
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
										<c:when test="${userId==null}">
											<h3 class="card-title">Add User</h3>
										</c:when>
										<c:otherwise>
											<h3 class="card-title">Edit User</h3>
										</c:otherwise>
									</c:choose>

								</div>
								<!-- /.card-header -->
								<!-- form start -->
								<!--<form id="quickForm">-->
								<form:form method="post" id="addUserId"
									modelAttribute="fetching">
									<div class="card-body">

										<div class="row">



											<div class="col-md-6">

												<div class="form-group">
													<label for="exampleInputEmail1">Name</label>
													<!--  <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">-->
													<form:input type="text" path="name" class="form-control"
														placeholder="Enter name" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Password</label>
													<!--  <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">-->
													<form:input type="password" path="password"
														class="form-control" placeholder="Password" />
												</div>
											</div>


											<div class="col-md-6">
												<div class="form-group">
													<label for="exampleInputPassword1">Username</label>
													<c:if test="${id==null}">
														<form:input type="text" path="username"
															class="form-control" placeholder="Username" />
													</c:if>
													<c:if test="${id!=null}">
														<form:input type="text" path="username" id="usernameId"
															class="form-control" placeholder="Username" />
													</c:if>
												</div>


												<div class="form-group">
													<label for="exampleInputPassword1">Confirm Password</label>

													<form:input type="password" path="confirmPassword"
														class="form-control" placeholder="Confirm Password" />
												</div>

											</div>

											<div class="col-md-6">

												<div class="form-group">
													<label for="exampleInputEmail1">Email address</label>
													<!--  <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">-->
													<form:input type="text" path="email" class="form-control"
														placeholder="Enter email" />
												</div>

												<div class="form-group">
													<label for="exampleInputEmail1">Role</label>
													<!--  <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">-->
													<form:select path="role" class="form-control select2"
														style="width: 100%;">

														<form:option value="user" label="User" />
														<form:option value="admin" label="Admin" />
														<form:option value="superadmin" label="Super Admin" />
													</form:select>
												</div>


											</div>

											<div class="col-md-6">

												<div class="form-group">
													<label for="exampleInputEmail1">Mobile No</label>
													<!--  <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">-->
													<form:input type="text" path="mobileNo"
														class="form-control" placeholder="Enter Mobile Number" />
												</div>

												<div class="form-group">
													<label for="exampleInputEmail1">Department</label>
													<!--  <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">-->
													<form:input type="text" path="department"
														class="form-control" placeholder="Enter Department" />
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
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- Sweet Alert -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>
	<!-- Page specific script -->
	<script src="<%=request.getContextPath()%>/custom_js/addUser.js"></script>

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
		$( "#usernameId" ).prop( "disabled", true );
	</script>

</body>
</html>
