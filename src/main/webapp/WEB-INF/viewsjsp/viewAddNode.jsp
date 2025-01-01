<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>View Add Node | DataTables</title>

<!-- Google Font: Source Sans Pro -->
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

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">



<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">
<style>
.dropdown-menu {
	padding: 5px;
	min-width: 16rem !important;
}

#dropdownMenuButton {
	min-width: 1em;
	color: #fff;
}

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
100
%
{
transform






























:






























rotate




























(
360
deg






























)
;
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
							<h1>View Add Node</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">viewAddNode</li>
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


							<div class="card">
								<div class="card-header">
									<h3 class="card-title">View Add Node</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">

									<div
										class="dropdown btn btn-secondary buttons-copy buttons-html5 mb-3"
										id="actionButton">
										<button class="btn btn-sm dropdown-toggle" type="button"
											id="dropdownMenuButton" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false">Action</button>
										<div class="dropdown-menu"
											aria-labelledby="dropdownMenuButton">
											<button class="btn btn-block btn-default" type="button"
												id="addSnmp">
												Add SNMP</i>
											</button>
											<button class="btn btn-block btn-default" id="addGroupName">
												Add Group Name</i>
											</button>
											<button class="btn btn-block btn-default"
												id="addServiceProvider">
												Add Service Provider</i>
											</button>
											<button class="btn btn-block btn-default" id="addCompany">
												Add Company</i>
											</button>
											<button class="btn btn-block btn-default" id="addDeviceType">
												Add Device Type</i>
											</button>
											<button class="btn btn-block btn-default" id="addState">
												Add State</i>
											</button>
											<button class="btn btn-block btn-default" id="addZone">
												Add Zone</i>
											</button>
											<button class="btn btn-block btn-default" id="addDistrict">
												Add District</i>
											</button>
											<button class="btn btn-block btn-default" id="addlocation">
												Add Location</i>
											</button>
											<button class="btn btn-block btn-default" id="dataDelete">
												Delete</i>
											</button>
										</div>
									</div>
									<!-- SNMP -->
									<div class="modal fade" id="snmpModal">
										<div class="modal-dialog">
											<div class="modal-content">
												<form:form method="post" id="addNodeId"
													modelAttribute="addNode">
													<div class="modal-header bg-primary">
														<h4 class="modal-title">Add SNMP</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body m-2">
														<div class="row form-group">
															<div class="col-4">
																<label for="exampleInputEmail1" class="ml-3 mt-1">SNMP
																	:</label>
															</div>
															<div class="col-8">
																<form:select type="text" path="snmp"
																	class="form-control select2" style="width: 100%;"
																	items="${snmp}" />
															</div>
														</div>
													</div>
													<div class="modal-footer justify-content-between">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<button type="button" id="addsnmpModal"
															class="btn btn-primary">submit</button>
													</div>
												</form:form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- SNMP -->
									<!-- groupNameModal -->
									<div class="modal fade" id="groupNameModal">
										<div class="modal-dialog">
											<div class="modal-content">
												<form:form method="post" id="addNodeId"
													modelAttribute="addNode">
													<div class="modal-header bg-primary">
														<h4 class="modal-title">Add Group Name</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body m-2">
														<div class="row form-group">
															<div class="col-4">
																<label for="exampleInputEmail1" class="ml-3 mt-1">Group
																	Name :</label>
															</div>
															<div class="col-8">
																<form:select type="text" path="groupName"
																	class="form-control select2" style="width: 100%;"
																	items="${groupName}" />
															</div>
														</div>
													</div>
													<div class="modal-footer justify-content-between">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<button type="button" id="addGroupNameModal"
															class="btn btn-primary">submit</button>
													</div>
												</form:form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- groupNameModal -->
									<!-- serviceProviderModal -->
									<div class="modal fade" id="serviceProviderModal">
										<div class="modal-dialog">
											<div class="modal-content">
												<form:form method="post" id="addNodeId"
													modelAttribute="addNode">
													<div class="modal-header bg-primary">
														<h4 class="modal-title">Add Service Provider</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body m-2">
														<div class="row form-group">
															<div class="col-4">
																<label for="exampleInputEmail1" class="ml-3 mt-1">Service
																	Provider :</label>
															</div>
															<div class="col-8">
																<form:select type="text" path="serviceProvider"
																	class="form-control select2" style="width: 100%;"
																	items="${serviceProvider}" />
															</div>
														</div>
													</div>
													<div class="modal-footer justify-content-between">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<button type="button" id="addserviceProviderModal"
															class="btn btn-primary">submit</button>
													</div>
												</form:form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- serviceProviderModal -->
									<!-- companyModal -->
									<div class="modal fade" id="companyModal">
										<div class="modal-dialog">
											<div class="modal-content">
												<form:form method="post" id="addNodeId"
													modelAttribute="addNode">
													<div class="modal-header bg-primary">
														<h4 class="modal-title">Add Company</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body m-2">
														<div class="row form-group">
															<div class="col-4">
																<label for="exampleInputEmail1" class="ml-3 mt-1">Company
																	Name :</label>
															</div>
															<div class="col-8">
																<form:select type="text" path="company"
																	class="form-control select2" style="width: 100%;"
																	items="${company}" />
															</div>
														</div>
													</div>
													<div class="modal-footer justify-content-between">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<button type="button" id="addcompanyModal"
															class="btn btn-primary">submit</button>
													</div>
												</form:form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- companyModal -->
									<!-- deviceTypeModal -->
									<div class="modal fade" id="deviceTypeModal">
										<div class="modal-dialog">
											<div class="modal-content">
												<form:form method="post" id="addNodeId"
													modelAttribute="addNode">
													<div class="modal-header bg-primary">
														<h4 class="modal-title">Add Device Type</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body m-2">
														<div class="row form-group">
															<div class="col-4">
																<label for="exampleInputEmail1" class="ml-3 mt-1">Device
																	Type :</label>
															</div>
															<div class="col-8">
																<form:select type="text" path="deviceType"
																	class="form-control select2" style="width: 100%;"
																	items="${deviceType}" />
															</div>
														</div>
													</div>
													<div class="modal-footer justify-content-between">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<button type="button" id="adddeviceTypeModal"
															class="btn btn-primary">submit</button>
													</div>
												</form:form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- deviceTypeModal -->
									<!-- stateModal -->
									<div class="modal fade" id="stateModal">
										<div class="modal-dialog">
											<div class="modal-content">
												<form:form method="post" id="addNodeId"
													modelAttribute="addNode">
													<div class="modal-header bg-primary">
														<h4 class="modal-title">Add State</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body m-2">
														<div class="row form-group">
															<div class="col-4">
																<label for="exampleInputEmail1" class="ml-3 mt-1">States
																	:</label>
															</div>
															<div class="col-8">
																<form:select type="text" path="state"
																	class="form-control select2" style="width: 100%;"
																	items="${state}" />
															</div>
														</div>
													</div>
													<div class="modal-footer justify-content-between">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<button type="button" id="addstateModal"
															class="btn btn-primary">submit</button>
													</div>
												</form:form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- stateModal -->
									<!-- zoneModal -->
									<div class="modal fade" id="zoneModal">
										<div class="modal-dialog">
											<div class="modal-content">
												<form:form method="post" id="addNodeId"
													modelAttribute="addNode">
													<div class="modal-header bg-primary">
														<h4 class="modal-title">Add Zone</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body m-2">
														<div class="row form-group">
															<div class="col-3">
																<label for="exampleInputEmail1" class="ml-3 mt-1">Zone
																	:</label>
															</div>
															<div class="col-9">
																<form:select type="text" path="zone"
																	class="form-control select2" style="width: 100%;"
																	items="${zone}" />
															</div>
														</div>
													</div>
													<div class="modal-footer justify-content-between">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<button type="button" id="addzoneModal"
															class="btn btn-primary">submit</button>
													</div>
												</form:form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- zoneModal -->
									<!-- districtModal -->
									<div class="modal fade" id="districtModal">
										<div class="modal-dialog">
											<div class="modal-content">
												<form:form method="post" id="addNodeId"
													modelAttribute="addNode">
													<div class="modal-header bg-primary">
														<h4 class="modal-title">Add District</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body m-2">
														<div class="row form-group">
															<div class="col-3">
																<label for="exampleInputEmail1" class="ml-3 mt-1">District
																	:</label>
															</div>
															<div class="col-9">
																<form:select type="text" path="district"
																	class="form-control select2" style="width: 100%;"
																	items="${district}" />
															</div>
														</div>
													</div>
													<div class="modal-footer justify-content-between">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<button type="button" id="adddistrictModal"
															class="btn btn-primary">submit</button>
													</div>
												</form:form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- districtModal -->
									<!-- locationModal -->
									<div class="modal fade" id="locationModal">
										<div class="modal-dialog">
											<div class="modal-content">
												<form:form method="post" id="addNodeId"
													modelAttribute="addNode">
													<div class="modal-header bg-primary">
														<h4 class="modal-title">Add Location</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body m-2">
														<div class="row form-group">
															<div class="col-3">
																<label for="exampleInputEmail1" class="ml-3 mt-1">Location
																	:</label>
															</div>
															<div class="col-9">
																<form:select type="text" path="location"
																	class="form-control select2" style="width: 100%;"
																	items="${locationData}" />
															</div>
														</div>
													</div>
													<div class="modal-footer justify-content-between">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<button type="button" id="addlocationModal"
															class="btn btn-primary">submit</button>
													</div>
												</form:form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- locationModal -->

									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th></th>
												<th>Device IP</th>
												<th>Device Name</th>
												<th>Device Type</th>
												<th>Group Name</th>
												<th>OEM</th>
												<th>SNMP</th>
												<th>SNMP_STATUS</th>
												<th>Service Provider</th>
												<th>Company</th>
												<th>State</th>
												<th>Zone</th>
												<th>Location</th>
												<th>District</th>
												<th>Test SNMP</th>
												<th>Edit</th>
												<th>Delete(s)</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach var="emp" items="${viewAddNode}">
												<tr>
													<td>${emp.DEVICE_IP}</td>
													<td>${emp.DEVICE_IP}</td>
													<td>${emp.DEVICE_NAME}</td>
													<td>${emp.DEVICE_TYPE}</td>
													<td>${emp.GROUP_NAME}</td>
													<td>${emp.OEM}</td>
													<td>${emp.SNMP}</td>
													<td>${emp.SNMP_STATUS}</td>
													<td>${emp.SERVICE_PROVIDER}</td>
													<td>${emp.COMPANY}</td>
													<td>${emp.STATE}</td>
													<td>${emp.ZONE}</td>
													<td>${emp.LOCATION}</td>
													<td>${emp.DISTRICT}</td>
													<td>
														<button class='btn btn-sm btn-warning' id='testing'
															name='Doem'
															onclick='testSNMPConfig("${emp.DEVICE_IP}", "${emp.SNMP}")'>
															Test <i class='fa fa-check'></i>
														</button>
													</td>
													<td><a href="editAddNode/${emp.ID}">Edit</a></td>
													<td><a href="javascript:deleteAddNode(${emp.ID})">Delete</a></td>
												</tr>
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<th></th>
												<th>Device IP</th>
												<th>Device Name</th>
												<th>Device Type</th>
												<th>Group Name</th>
												<th>OEM</th>
												<th>SNMP</th>
												<th>SNMP_STATUS</th>
												<th>Service Provider</th>
												<th>Company</th>
												<th>State</th>
												<th>Zone</th>
												<th>Location</th>
												<th>District</th>
												<th>Test SNMP</th>
												<th>Edit</th>
												<th>Delete(s)</th>


											</tr>
										</tfoot>

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
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
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
	<script type="text/javascript"
		src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.12/js/dataTables.checkboxes.min.js"></script>

	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
	<script src="<%=request.getContextPath()%>/custom_js/addNode.js"></script>

	<!-- Page specific script -->
	<script>
		$(function() {
			var table = $("#example1").DataTable(
					{
						"columnDefs" : [ {
							'targets' : 0,
							'checkboxes' : {
								'selectRow' : true
							}
						} ],
						"select" : {
							'style' : 'multi'
						},
						"order" : [ [ 1, 'asc' ] ],
						"responsive" : true,
						"lengthChange" : false,
						"autoWidth" : false,
						"scrollX" : true,
						"buttons" : [ "copy", "csv", "excel", "pdf", "print",
								"colvis" ]
					}

			).buttons().container().appendTo(
					'#example1_wrapper .col-md-6:eq(0)');

		});
	</script>
	<div id="loading-overlay">
		<div id="loading-spinner"></div>
	</div>
</body>
</html>
