<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bulk Upload</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome -->
<%-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css"> --%>
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



<%-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css"> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/sweetalert2/sweetalert2.min.css">
<%-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/sweetalert2/sweetalert2.css"> --%>

<style type="text/css">
.downloadbtn {
	background-color: DodgerBlue;
	border: none;
	color: white;
	padding: 5px 30px;
	cursor: pointer;
	font-size: 11px;
	border-radius: 25px;
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
								<li class="breadcrumb-item active">Bulk Upload</li>

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
						<div class="col-md-3"></div>
						<div class="col-md-6">
							<!-- File Upload Details Div -->
							<div class="card card-primary" style="display: none;"
								id="fileUploadDetails">
								<div class="card-header">
									<h3 class="card-title">File Upload Details</h3>
								</div>
								<div class="card-body">
									<div class="row ">
										<div class="col-md-12">
											<br>
											<center>
												<h3 style="color: #000">
													<b>Uploaded Successfully</b>
												</h3>
											</center>
											<br>
											<center>
												<p id="insertshow">
													<b> Inserted Record = <span id="insertCount"></span></b>
												</p>
											</center>
											<br>
											<center>
												<p id="updateshow">
													<b>Updated Record = <span id="updateCount"></span></b>
												</p>
											</center>
											<br>

											<center>
												<p id="updateshow">
													<b>Delete Record = <span id="deleteCount"></span></b>
												</p>
											</center>
											<br>
											<center>
												<p id="failshow">
													<b>Failed Record = <span id="failCount"></span></b>
												</p>
											</center>
											<br>
											<center>
												<b>Failed Record Details:
													<button class="downloadbtn">
														<i class="fa fa-download"></i> Download
													</button>
												</b>

											</center>
											<br> <br>
											<center>
												<button class="btn btn-primary">
													<a href="javascript:void(0);" id="bulkupload_back"><font
														color="white">Back</font></a>
												</button>
											</center>
											<br> <br>
										</div>
									</div>
								</div>
							</div>
							<!-- End File Upload Details Div -->
							<!-- jquery validation -->
							<div class="card card-primary" id="uploadFile">
								<div class="card-header">
									<h3 class="card-title">Bulk Upload</h3>
									<%-- <c:choose>
										<c:when test="${userId==null}">
											<h3 class="card-title">Bulk Upload</h3>
										</c:when>
										<c:otherwise>
											<h3 class="card-title">Edit User</h3>
										</c:otherwise>
									</c:choose>
 --%>
								</div>
								<!-- /.card-header -->
								<!-- form start -->
								<!--<form id="quickForm">-->
								<form method="post" id="bulkUpload" action="uploadExcelFile"
									enctype="multipart/form-data">
									<div class="card-body">
										<!-- 	
									<div class="text-center">
													<div class="spinner-border" role="status">
														<span class="visually-hidden"></span>
													</div>
												</div> -->

										<div class="row">

											<div class="col-md-6">
												<div class="form-group">
													<label for="inputFile">File</label> <input type="file"
														id="bulkUploadFile" name="bulkUploadFile"
														class="form-control" accept=".xlsx" /> <span
														id="selectFile" style="color: red; display: none;">Please
														Select the File</span>

												</div>
											</div>

										</div>

									</div>


									<!-- /.card-body -->
									<div class="card-footer">
										<!-- <button type="button" class="btn btn-primary" id="submitBulk"">Submit</button> -->
										<button type="button" class="btn btn-primary" id="submitBulk">Submit</button>
										<center>
											<i style="margin-left: 10px; display: none;"
												id="uploadFileSpinner" class='fa fa-spinner fa-spin fa-4x '></i>
										</center>
									</div>
								</form>

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

	<!-- Sweet Alert -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>
	<!-- Page specific script -->
	<script src="<%=request.getContextPath()%>/custom_js/bulkUpload.js"></script>

	<!-- Select2 -->
	<%-- <script
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
	</script> --%>

</body>
</html>
