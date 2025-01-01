<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Mail Template</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<%-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/css/bootstrap.min.css"> --%>
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
<!-- Multi Select -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/multiselect/bootstrap-multiselect.css">
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
								<c:when test="${SR_NO==null}">
									<h1>Mail Template</h1>
								</c:when>
								<c:otherwise>
									<h1>Edit Mail Template</h1>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<c:choose>
									<c:when test="${SR_NO==null}">
										<li class="breadcrumb-item active">Mail Template</li>
									</c:when>
									<c:otherwise>
										<li class="breadcrumb-item active">Edit Mail Template</li>
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
										<c:when test="${SR_NO==null}">
											<h3 class="card-title">Mail Template</h3>
										</c:when>
										<c:otherwise>
											<h3 class="card-title">Edit Mail Template</h3>
										</c:otherwise>
									</c:choose>

								</div>
								<!-- /.card-header -->
								<!-- form start -->
								<!--<form id="quickForm">-->
								<form:form method="post" id="mailTemplate"
									modelAttribute="fetchMailTemplate">
									<div class="card-body">
										<form:input type="hidden" path="SR_NO" name="SR_NO" />
										<div class="row">
											<div class="col-md-3">
												<div class="form-group">
													<label for="exampleInputPassword1">Template Name</label>
													<form:input type="text" path="templateName"
														name="templateName" class="form-control"
														placeholder="Enter Template Name" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Subject</label>
													<form:input type="text" path="subject" name="subject"
														class="form-control" placeholder="Enter Subject" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Send Time</label>
													<form:input type="text" path="sendTime" name="sendTime"
														class="form-control" placeholder="Enter Send Time" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">IP Address List</label>
													<form:select path="ipAddressList" tabindex="1"
														class="form-control" style="width: 100%;"
														items="${mailTemplates}" multiple="multiple">
													</form:select>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label for="exampleInputPassword1">Function Name</label>
													<form:input type="text" path="functionName"
														name="functionName" class="form-control"
														placeholder="Enter Function Name" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">MSG Body</label>
													<form:input type="text" path="msgBody" name="msgBody"
														class="form-control" placeholder="Enter MSG Body" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Attachment</label>
													<form:input type="text" path="attachment" name="attachment"
														class="form-control" placeholder="Enter Attachment" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Mobile Number</label>
													<form:input type="text" path="phone" name="phone"
														class="form-control" placeholder="Enter Mobile Number" />
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label for="exampleInputPassword1">To Mail</label>
													<form:input type="text" path="toMail" name="toMail"
														class="form-control" placeholder="Enter To Mail" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Periods</label>
													<form:input type="text" path="periods" name="periods"
														class="form-control" placeholder="Enter Periods" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Attachment Type</label>
													<form:input type="text" path="attachmentType"
														name="attachmentType" class="form-control"
														placeholder="Enter Attachment Type" />
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label for="exampleInputPassword1">CC Mail</label>
													<form:input type="text" path="ccMail" name="ccMail"
														class="form-control" placeholder="Enter CC Mail" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Days</label>
													<form:input type="text" path="days" name="days"
														class="form-control" placeholder="Enter Days" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Is Active</label>
													<form:select path="isActive" name="isActive"
														class="form-control">
														<form:option value="yes" label="Yes" />
														<form:option value="no" label="NO" />
													</form:select>
												</div>
											</div>
										</div>
									</div>
									<!-- /.card-body -->
									<div class="card-footer">
										<button type="button" id="mailSubmit" class="btn btn-primary">Submit</button>
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
				<c:when test="${SR_NO==null}">
					<section class="content">
						<div class="container-fluid">
							<div class="row">
								<div class="col-12">


									<div class="card">
										<div class="card-header">
											<h3 class="card-title">Mail Template Details</h3>
										</div>
										<!-- /.card-header -->
										<div class="card-body">


											<table id="example1"
												class="table table-bordered table-striped">
												<thead>
													<tr>
														<th>Sr. No</th>
														<th>ATTACHEMENT</th>
														<th>ATTACHEMENT_TYPE</th>
														<th>CC_MAIL_ID</th>
														<th>DAYS</th>
														<th>FUNCTION_NAME</th>
														<th>IsActive</th>
														<th>MSG_BODY</th>
														<th>PERIODS</th>
														<th>SEND_TIME</th>
														<th>SUBJECT</th>
														<th>TEMPLET_NAME</th>
														<th>TO_MAIL</th>
														<th>MOBILE NO.</th>
														<th>IP_ADDRESS_LIST</th>
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
				</c:when>
			</c:choose>
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
		src="<%=request.getContextPath()%>/webtemplate/multiselect/bootstrap-multiselect.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/multiselect/bootstrap-multi.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/multiselect/bootstrap-multiselect.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/multiselect/bootstrap-multiselect.min.js.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/additional-methods.min.js"></script>

	<!-- DataTables  & Plugins -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dataTable/jquery.dataTables.min.js"></script>
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
	<script src="<%=request.getContextPath()%>/custom_js/mailTemplate.js"></script>
	<script>
		$(function() {

			$('[data-mask]').inputmask()
		})
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