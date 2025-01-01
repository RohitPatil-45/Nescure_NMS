<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Device Details</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/webtemplate/dist/img/AdminLogo.png">
<!-- Google Font: Source Sans Pro -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/OfflineCss/googleapifamily.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->

<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">
<!-- Select2 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/select2/css/select2.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bs-stepper/css/bs-stepper.min.css">
<!-- dropzonejs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/dropzone/min/dropzone.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">



<style>
ul, #myUL {
	list-style-type: none;
}

#myUL {
	margin: 0;
	padding: 0;
}

.caret {
	cursor: pointer;
	-webkit-user-select: none; /* Safari 3.1+ */
	-moz-user-select: none; /* Firefox 2+ */
	-ms-user-select: none; /* IE 10+ */
	user-select: none;
}

.caret::before {
	color: white;
	display: inline-block;
	margin-right: 6px;
}

.caret-down::before {
	-ms-transform: rotate(90deg); /* IE 9 */
	-webkit-transform: rotate(90deg); /* Safari */
	transform: rotate(90deg);
}

.nested {
	display: none;
}

.active {
	display: block;
}

.container {
	display: flex;
}

.context-menu {
	display: none;
	position: absolute;
	background-color: #7d7d7d;
	border: 1px solid #acb1a6;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	z-index: 1000;
}

.context-menu ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

.context-menu li {
	padding: 8px 12px;
	cursor: pointer;
}

.context-menu li:hover {
	background-color: #44444461;
}

.content {
	
}

.custom-datatable-container {
	/* 	max-width: 1000px; */
	overflow-x: auto;
}

.highcharts-background {
	fill: #343a40;
}

canvas {
	height: 300px;
}
</style>
</head>
<!-- class="dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed" -->
<body
	class="hold-transition layout-top-nav layout-fixed layout-navbar-fixed layout-footer-fixed"
	style="height: auto;">
	<div class="wrapper">
		<jsp:include page="header.jsp" />

		<!-- Content Header (Page header) -->
		<div class="content-wrapper">
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<c:choose>
								<c:when test="${userId==null}">
									<h1>Device Details</h1>
								</c:when>
								<c:otherwise>
									<h1>Edit Device Details</h1>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<c:choose>
									<c:when test="${userId==null}">
										<li class="breadcrumb-item active">Device Details</li>
									</c:when>
									<c:otherwise>
										<li class="breadcrumb-item active">Edit Device Details</li>
									</c:otherwise>
								</c:choose>

							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>
			<div class="container" style="margin-left: 10px; min-width: 100%;">
				<ul id="myUL"
					style="flex: 20%; background-color: #e2e2e2; border-radius: 10px; padding-left: 12px; padding-top: 5px; min-width: 300px; min-height: 500px;">
					<li style="font-size: 20px;"><span class="caret caret-down"><i
							class="fas fa-desktop"></i>&nbsp; Devices</span>
						<ul class="nested active">
							<c:forEach var="data" items="${uniqueGroup}">
								<li style="font-size: 20px;"><img
									src="<%=request.getContextPath()%>/webtemplate/dist/img/Green.png"
									alt="Icon" class="icon"><span class="caret caret-down">${data.group}</span>
									<ul class="nested active">
										<c:forEach var="subItem" items="${dataArray}">
											<c:if test="${subItem.group eq data.group}">

												<li type="button"
													onclick="callForOtherDetails('${subItem.ipAddress}','${subItem.device}')"
													oncontextmenu="showContextMenu(event, '${subItem.group}', '${subItem.ipAddress}', '${subItem.device}')">
													<c:choose>
														<c:when test="${subItem.deviceStatus eq 'Up'}">
															<img
																src="<%=request.getContextPath()%>/webtemplate/dist/img/Green.png"
																alt="Icon" class="icon">
														</c:when>
														<c:when test="${subItem.deviceStatus eq 'Down'}">
															<img
																src="<%=request.getContextPath()%>/webtemplate/dist/img/red.png"
																alt="Icon" class="icon">
														</c:when>
													</c:choose> <c:out value="${subItem.ipAddress}" />
												</li>

											</c:if>
										</c:forEach>
									</ul></li>
							</c:forEach>
						</ul></li>
				</ul>
				<section class="content" style="flex: 75%;">
					<jsp:include page="setParameter.jsp" />
					<div class="container-fluid deviceDetails" style="display: none;">
						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h4 class="card-title">Detail Page</h4>
									</div>
									<div class="card-body">
										<!-- Nav tabs -->
										<div class="default-tab">
											<ul class="nav nav-tabs" role="tablist">
												<li class="nav-item"><a class="nav-link active"
													data-toggle="tab" href="#home">HW Inventory </a></li>
												<li class="nav-item" id="HWConfigurationdefaultID"><a
													class="nav-link" data-toggle="tab" href="#profile">HW
														Configuration</a></li>
												<li class="nav-item"><a class="nav-link"
													data-toggle="tab" href="#contact">Software Inventory</a></li>
												<li class="nav-item" id="PatchDetailsdefaultID"><a
													class="nav-link" data-toggle="tab" href="#patch">Patch
														Details</a></li>
												<!-- 												<li class="nav-item"><a class="nav-link" -->
												<!-- 													data-toggle="tab" href="#processP">Process Details</a></li> -->
												<li class="nav-item"><a class="nav-link"
													data-toggle="tab" href="#serviceS">Service Details</a></li>
												<!-- 												<li class="nav-item"><a class="nav-link" -->
												<!-- 													data-toggle="tab" href="#firewall">Firewall Details</a></li> -->
												<!-- 												<li class="nav-item"><a class="nav-link" -->
												<!-- 													data-toggle="tab" href="#memory">Memory Utilization</a></li> -->
												<!-- 												<li class="nav-item"><a class="nav-link" -->
												<!-- 													data-toggle="tab" href="#cpu">CPU Utilization</a></li> -->
											</ul>
											<div class="tab-content">
												<div class="tab-pane fade show active" id="home"
													role="tabpanel">
													<div class="pt-4">
														<section class="content">
															<div class="container-fluid">
																<div class="row">
																	<div class="col-12">


																		<div class="card">
																			<div id="hwInventoryDefaultID">
																				<div class="card-header"
																					style="background-color: #948b8b;">
																					<h3 class="card-title">Hardware Inventory</h3>
																				</div>
																				<!-- /.card-header -->

																				<div class="card-body">


																					<table id="exampleTable1"
																						class="table table-bordered table-striped">
																						<thead>
																							<tr>
																								<th>Sr.No</th>
																								<th>Processor Name</th>
																								<th>Processor Manufacture</th>
																								<th>No. of Cores</th>
																							</tr>
																						</thead>
																						<tbody>

																						</tbody>

																					</table>


																				</div>
																			</div>

																			<div id="hwInventoryLinuxID" style="display: none;">

																				<div class="card-header"
																					style="background-color: #948b8b;">
																					<h3 class="card-title">HW Configuration</h3>
																					<!-- 																					<span>&nbsp;Discovered Time :&nbsp;</span><span -->
																					<!-- 																						id="discoverTimeLinux"></span><span -->
																					<!-- 																						id="ctimeLinux"></span> -->
																				</div>
																				<div class="tab-content" style="padding: 1%">
																					<form style="margin-top: 10px">
																						<div>
																							<div class="form-row">
																								<div class="form-group col-md-6">
																									<label for="ipAddressLinux">IP Address</label>
																									<input type="text" class="form-control"
																										readonly id="ipAddressLinux">
																								</div>
																								<div class="form-group col-md-6">
																									<label for="pcNameLinux">PC Name</label> <input
																										type="text" class="form-control" readonly
																										id="pcNameLinux">
																								</div>
																							</div>
																							<div class="form-row">
																								<div class="form-group col-md-6">
																									<label for="biosInfoLinux">BIOS Info</label> <input
																										type="text" class="form-control" readonly
																										id="biosInfoLinux">
																								</div>
																								<div class="form-group col-md-6">
																									<label for="graphicCardLinux">Graphic
																										Card</label> <input type="text" class="form-control"
																										readonly id="graphicCardLinux">
																								</div>
																							</div>
																							<div class="form-row">
																								<div class="form-group col-md-6">
																									<label for="hddDriveLinux">HDD Drive</label> <input
																										type="text" class="form-control" readonly
																										id="hddDriveLinux">
																								</div>
																								<div class="form-group col-md-6">
																									<label for="motherboardNameLinux">Motherboard
																										Name</label> <input type="text" class="form-control"
																										readonly id="motherboardNameLinux">
																								</div>
																							</div>
																							<div class="form-row">
																								<div class="form-group col-md-6">
																									<label for="opNameLinux">Operating
																										System Name</label> <input type="text"
																										class="form-control" readonly id="opNameLinux">
																								</div>
																								<div class="form-group col-md-6">
																									<label for="processorNameLinux">Processor
																										Name</label> <input type="text" class="form-control"
																										readonly id="processorNameLinux">
																								</div>
																							</div>
																							<div class="form-row">
																								<div class="form-group col-md-6">
																									<label for="ramDetailsLinux">RAM
																										Details</label> <input type="text"
																										class="form-control" readonly
																										id="ramDetailsLinux">
																								</div>
																								<div class="form-group col-md-6">
																									<label for="discoverTimeLinux">Discover
																										Time</label> <input type="text" class="form-control"
																										readonly id="discoverTimeLinux">
																								</div>
																							</div>
																							<!-- 																							<div class="form-row"> -->
																							<!-- 																								<div class="form-group col-md-6"> -->
																							<!-- 																									<label for="ctimeLinux">CTIME</label> <input -->
																							<!-- 																										type="text" class="form-control" readonly -->
																							<!-- 																										id="ctimeLinux"> -->
																							<!-- 																								</div> -->
																							<!-- 																							</div> -->
																					</form>
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
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-12">


								<div class="card" id="InstallMemoryModulesDefaultValue">
									<div class="card-header" style="background-color: #948b8b;">
										<h3 class="card-title">Install Memory Modules</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">


										<table id="exampleTable11"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr.No</th>
													<th>Socket</th>
													<th>Capacity</th>
													<th>Module Tag</th>
													<th>Bank Label</th>
													<th>Serial Number</th>
													<th>Frequency</th>
												</tr>
											</thead>
											<tbody>

											</tbody>

										</table>


									</div>
									<!-- /.card-body -->
								</div>

								<div class="card" id="InstallMemoryModulesLinuxValue">
									<div class="card-header" style="background-color: #948b8b;">
										<h3 class="card-title">RAM And CPU Details</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">


										<table id="exampleTableInstallMemoryModulesLinuxValue"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>NODE IP</th>
													<th>CPU STATUS</th>
													<th>CPU UTILIZATION</th>
													<th>MEMORY STATUS</th>
													<th>FREE MEMORY</th>
													<th>MEMORY UTILIZATION</th>
													<th>TOTAL MEMORY</th>
													<th>USED MEMORY</th>
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
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-12">


								<div class="card" id="HardDiskDefaultID">
									<div class="card-header" style="background-color: #948b8b;">
										<h3 class="card-title">Hard Disk</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">


										<table id="exampleTable12"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr.No</th>
													<th>Name</th>
													<th>Serial Number</th>
													<th>Model</th>
													<th>Media Type</th>
													<th>Interface Type</th>
													<th>Manufacture</th>
													<th>Capacity</th>
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
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-12">


								<div class="card" id="LogicalDriveDefaultID">
									<div class="card-header" style="background-color: #948b8b;">
										<h3 class="card-title">Logical Drive</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">


										<table id="exampleTable13"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr.No</th>
													<th>Drive</th>
													<th>Drive Type</th>
													<th>File Type</th>
													<th>Serial Number</th>
													<th>Capacity(GB)</th>
													<th>Free Space(GB)</th>
													<th>Driver Usage(GB)</th>
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
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-12">


								<div class="card">
									<div class="card-header" style="background-color: #948b8b;">
										<h3 class="card-title">Drive Details</h3>
									</div>
									<div class="card-body">
										<table id="exampleTable14"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Drive Name</th>
													<th>Total Space (GB)</th>
													<th>Free Space (GB)</th>
													<th>Used Space (GB)</th>
													<th>Drive utilization(%)</th>
												</tr>
											</thead>
											<tbody>

											</tbody>

										</table>


									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
		<div class="tab-pane fade" id="profile">
			<div class="pt-4">
				<section class="content" id="main">
					<div class="nav-tabs-custom">
						<div class="card-header" style="background-color: #948b8b;">
							<h3 class="card-title">HW Configuration</h3>
						</div>
						<div class="tab-content">
							<form style="margin-top: 10px">
								<div class="form-row" style="display: flex;">
									<div class="form-group col-md-6">
										<label for="ipAddress">IP Address</label> <input type="text"
											class="form-control" readonly id="ipAddress">
									</div>
									<div class="form-group col-md-6">
										<label for="macAddress">Mac Address</label> <input type="text"
											class="form-control" readonly id="macAddress">
									</div>
								</div>
								<div class="form-row" style="display: flex;">
									<div class="form-group col-md-6">
										<label for="manufacturer">Manufacture</label> <input
											type="text" id="manufacturer" class="form-control" readonly>
									</div>
									<div class="form-group col-md-6">
										<label for="processorCount">Processor Count</label> <input
											type="text" id="processorCount" class="form-control" readonly>
									</div>
								</div>
								<div class="form-row" style="display: flex;">
									<div class="form-group col-md-6">
										<label for="osType">OS Type</label> <input type="text"
											id="osType" class="form-control" readonly>
									</div>
									<div class="form-group col-md-6">
										<label for="diskSpace">Disk Space</label> <input type="text"
											id="diskSpace" class="form-control" readonly>
									</div>
								</div>
								<div class="form-row" style="display: flex;">
									<div class="form-group col-md-6">
										<label for="processorName">Processor Name</label> <input
											type="text" id="processorName" class="form-control" readonly>
									</div>
									<div class="form-group col-md-6">
										<label for="servicePack">Service Pack</label> <input
											type="text" id="servicePack" class="form-control" readonly>
									</div>
								</div>
								<div class="form-row" style="display: flex;">
									<div class="form-group col-md-6">
										<label for="domain">Domain</label> <input type="text"
											id="domain" class="form-control" readonly>
									</div>
									<div class="form-group col-md-6">
										<label for="originalSerialNumber">Original Serial
											Number</label> <input type="text" id="originalSerialNumber"
											class="form-control" readonly>
									</div>
								</div>
								<div class="form-row" style="display: flex;">
									<div class="form-group col-md-6">
										<label for="totalMemory">Total Memory</label> <input
											type="text" id="totalMemory" class="form-control" readonly>
									</div>
									<div class="form-group col-md-6">
										<label for="processorManufacturer">Processor
											Manufacture</label> <input type="text" id="processorManufacturer"
											class="form-control" readonly>
									</div>
								</div>
								<div class="form-row" style="display: flex;">
									<div class="form-group col-md-6">
										<label for="biosName">BIOS Name</label> <input type="text"
											id="biosName" class="form-control" readonly>
									</div>
									<div class="form-group col-md-6">
										<label for="biosManufacturer">BIOS Manufacture</label> <input
											type="text" id="biosManufacturer" class="form-control"
											readonly>
									</div>
								</div>
								<div class="form-row" style="display: flex;">
									<div class="form-group col-md-6">
										<label for="biosReleaseDate">BIOS Release Date</label> <input
											type="text" id="biosReleaseDate" class="form-control"
											readonly>
									</div>
									<div class="form-group col-md-6">
										<label for="biosVersion">BIOS Version</label> <input
											type="text" id="biosVersion" class="form-control" readonly>
									</div>
								</div>
								<div class="form-row" style="display: flex;">
									<div class="form-group col-md-6">
										<label for="buildNumber">Build Number</label> <input
											type="text" id="buildNumber" class="form-control" readonly>
									</div>
									<div class="form-group col-md-6">
										<label for="hostName">Host Name</label> <input type="text"
											id="hostName" class="form-control" readonly>
									</div>
								</div>
								<div class="form-row" style="display: flex;">
									<div class="form-group col-md-6">
										<label for="osName">OS Name</label> <input type="text"
											id="osName" class="form-control" readonly>
									</div>
									<div class="form-group col-md-6">
										<label for="osVersion">OS Version</label> <input type="text"
											id="osVersion" class="form-control" readonly>
									</div>
								</div>
							</form>
						</div>
					</div>
				</section>
			</div>
		</div>
		<div class="tab-pane fade" id="contact">
			<div class="pt-4">
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-12">


								<div class="card">
									<div class="card-header" style="background-color: #948b8b;">
										<h3 class="card-title">Software Inventory</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">
										<div id="SoftwareinventorDefaultable">

											<div class="table-responsive custom-datatable-container">
												<table id="exampleTable2"
													class="table table-bordered table-striped">
													<thead>
														<tr>
															<th>ID</th>
															<th>Device Name</th>
															<th>Device IP</th>
															<!-- <th>MAC ID</th> -->
															<th>Application Name</th>
															<th>Application Version</th>
															<!-- <th>Is License</th> -->
															<!-- <th>License Key</th> -->
															<!-- <th>Product ID</th> -->
															<th>Publisher</th>
															<th>Uninstall String</th>
														</tr>
													</thead>
													<tbody>

													</tbody>

												</table>
											</div>
										</div>

										<div id="SoftwareinventorLinuxtable" style="display: none;">

											<div class="table-responsive custom-datatable-container">
												<table id="exampleTableLinuxSw"
													class="table table-bordered table-striped">
													<thead>
														<tr>
															<th>ID</th>
															<th>Device IP</th>
															<th>Device Name</th>

															<th>APPLICATION NAME</th>
															<th>DATE</th>
															<th>TIME</th>
														</tr>
													</thead>
													<tbody>

													</tbody>

												</table>
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
			</div>
		</div>
		<div class="tab-pane fade" id="patch">
			<div class="pt-4">
				<section class="content">
					<div class="container-fluid" style="max-width: 1000px;">
						<div class="row">
							<div class="col-12">


								<div class="card">
									<div class="card-header" style="background-color: #948b8b;">
										<h3 class="card-title">Patch Details</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">

										<div class="table-responsive">
											<table id="exampleTable3"
												class="table table-bordered table-striped">
												<thead>
													<tr>
														<th>Sr No</th>
														<th>Branch Name</th>
														<th>Device Name</th>
														<th>Device IP</th>
														<th>Patch ID</th>
														<th>Patch Type</th>
														<th>Installed Date</th>
													</tr>
												</thead>
												<tbody>

												</tbody>

											</table>
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
			</div>
		</div>
		<div class="tab-pane fade" id="processP">
			<div class="pt-4">
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-12">


								<div class="card">
									<div class="card-header" style="background-color: #948b8b;">
										<h3 class="card-title">Process Details</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">

										<div class="table-responsive custom-datatable-container">
											<table id="exampleTable4"
												class="table table-bordered table-striped">
												<thead>
													<tr>
														<th>Sr No</th>
														<th>P ID</th>
														<th>Alias Name</th>
														<th>Process Name</th>
														<th>CPU</th>
														<th>Memory</th>
														<th>Discover Time</th>
														<th>Thread Count</th>
														<th>Handle Count</th>
														<th>Peak Working Set</th>
														<th>Private Working Set</th>
														<th>Paged Pool</th>
														<th>NP Pool</th>
														<th>Commit Size</th>
														<th>CPU Time</th>
														<th>I/O Read</th>
														<th>I/O Write</th>
														<th>I/O Other</th>
														<th>I/O Read Bytes</th>
														<th>I/O Write Bytes</th>
														<th>I/O Other Bytes</th>
													</tr>
												</thead>
												<tbody>

												</tbody>

											</table>

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
			</div>
		</div>
		<div class="tab-pane fade" id="serviceS">
			<div class="pt-4">
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-12">


								<div class="card">
									<div class="card-header" style="background-color: #948b8b;">
										<h3 class="card-title">Services Details</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">


										<table id="exampleTable5"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr.No</th>
													<th>Device Name</th>
													<th>Service Name</th>
													<th>Display Name</th>
													<th>Service Mode</th>
													<th>Service State</th>
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
			</div>
		</div>
		<div class="tab-pane fade" id="firewall">
			<div class="pt-4">
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-12">


								<div class="card">
									<div class="card-header" style="background-color: #948b8b;">
										<h3 class="card-title">Firewall Details</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">
										<div class="table-responsive custom-datatable-container">
											<table id="exampleTable6"
												class="table table-bordered table-striped">
												<thead>
													<tr>
														<th>Sr No</th>
														<th>Branch Name</th>
														<th>PC Name</th>
														<th>Alias Name</th>
														<th>Rule Name</th>
														<th>Enabled</th>
														<th>Direction</th>
														<th>Profiles</th>
														<th>Grouping</th>
														<th>Local IP</th>
														<th>Remote IP</th>
														<th>Protocol</th>
														<th>Local Port</th>
														<th>Remote Port</th>
														<th>Edgetraversal</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>

												</tbody>

											</table>
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
			</div>
		</div>
		<div class="tab-pane fade" id="memory">
			<div class="pt-4">
				<div class="col-md-12">
					<!-- Donut chart -->
					<div class="card card-primary card-outline">
						<div class="card-header">
							<h3 class="card-title">
								<i class="far fa-chart-bar"></i> Memory Utilization
							</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-minus"></i>
								</button>
								<button type="button" class="btn btn-tool"
									data-card-widget="remove">
									<i class="fas fa-times"></i>
								</button>
							</div>
						</div>
						<div class="card-body">
							<div id="donut-chart" style="height: 300px;"></div>
						</div>
						<!-- /.card-body-->
					</div>
					<!-- /.card -->
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="cpu">
			<div class="pt-4">
				<div class="col-md-12">
					<!-- interactive chart -->
					<div class="card card-primary card-outline">
						<div class="card-header">
							<h3 class="card-title">
								<i class="far fa-chart-bar"></i> CPU Utilization
							</h3>

							<div class="card-tools">
								Real time
								<div class="btn-group" id="realtime" data-toggle="btn-toggle">
									<button type="button" class="btn btn-default btn-sm active"
										data-toggle="on">On</button>
									<button type="button" class="btn btn-default btn-sm"
										data-toggle="off">Off</button>
								</div>
							</div>
						</div>
						<div class="card-body">
							<div id="interactive" style="height: 300px;"></div>
						</div>
						<div class="d-flex flex-row justify-content-end">
							<span class="mr-2"> <i class="fas fa-square text-primary"></i>
								CPU Utilization
							</span>
						</div>
						<!-- /.card-body-->
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
	<!-- /.col -->
	</div>
	<!-- /.row -->
	</div>
	</div>
	<!-- /.container-fluid -->
	</section>
	</div>
	</div>
	</div>
	<%@ include file="footer.jsp"%>
	<div id="contextMenu" class="context-menu" style="min-width: 100px;">
		<ul id="myUlElement" style="background: aliceblue;">

		</ul>
	</div>
	<input style="display: none;" type="text" id="group">
	<input style="display: none;" type="text" id="ipaddress">
	<input style="display: none;" type="text" id="device">
	<!-- jQuery -->


	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/jquery.flot.js"></script>
	<!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/plugins/jquery.flot.resize.js"></script>
	<!-- FLOT PIE PLUGIN - also used to draw donut charts -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/plugins/jquery.flot.pie.js"></script>

	<!-- date-range-picker -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/moment/moment.min.js"></script>
	<script src="<%=request.getContextPath()%>/webtemplate/js/highstock.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/chart.js/Chart.min.js"></script>
	<%-- <script src="<%=request.getContextPath()%>/custom_js/cpuUtility.js"></script>  --%>









	<!-- Bootstrap 4 -->


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

	<script src="<%=request.getContextPath()%>/custom_js/deviceDetails.js"></script>

	<script
		src="<%=request.getContextPath()%>/custom_js/setParameterFunction.js"></script>
	<script>
		var updateValueElement = document.getElementById("update_Value");
		var nameUpdates = "Update";
		function showSecurityDetails(update, ipAddress) {
			nameUpdates = update;
			updateValueElement.textContent = nameUpdates;

			var dataArray = [ {
				"ipAddress" : "192.168.1.5",
				"location" : "Pune",
				"device" : "Licifer",
				"group" : "Group A"
			}, {
				"ipAddress" : "192.168.1.8",
				"location" : "Pune",
				"device" : "NPM34",
				"group" : "Group A"
			}, {
				"ipAddress" : "192.168.1.7",
				"location" : "Mumbai",
				"device" : "HP Spector",
				"group" : "Group C"
			}, {
				"ipAddress" : "192.168.1.52",
				"location" : "Tamilnadu",
				"device" : "AK",
				"group" : "Group D"
			}, {
				"ipAddress" : "192.168.1.24",
				"location" : "Kerala",
				"device" : "Kl89",
				"group" : "Group E"
			} ];
			var tableBody = document.getElementById("tableBody");

			while (tableBody.firstChild) {
				tableBody.removeChild(tableBody.firstChild);
			}
			// Loop through the dataArray and create table rows

			dataArray.forEach(function(data) {
				// Create a new row
				if ((data.group === update || data.location === update)
						&& data.ipAddress == ipAddress) {
					var row = document.createElement("tr");

					// Create and append td elements for each property
					var groupTd = document.createElement("td");
					groupTd.textContent = data.group;
					row.appendChild(groupTd);

					var locationTd = document.createElement("td");
					locationTd.textContent = data.location;
					row.appendChild(locationTd);

					var deviceTd = document.createElement("td");
					deviceTd.textContent = data.device;
					row.appendChild(deviceTd);

					var ipAddressTd = document.createElement("td");
					ipAddressTd.textContent = data.ipAddress;
					ipAddressTd.id = "myBtn";
					row.appendChild(ipAddressTd);

					// Append the row to the table body
					tableBody.appendChild(row);
				}
			});
		}
	</script>
	<script>
		var toggler = document.getElementsByClassName("caret");
		var i;

		for (i = 0; i < toggler.length; i++) {
			toggler[i].addEventListener("click", function() {
				this.parentElement.querySelector(".nested").classList
						.toggle("active");
				this.classList.toggle("caret-down");
			});
		}
	</script>
	<script>
		/*
		 * Custom Label formatter
		 * ----------------------
		 */
		function labelFormatter(label, series) {
			return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
					+ label + '<br>' + Math.round(series.percent) + '%</div>'
		}
	</script>
	<script>
		/* $(document)
				.ready(
						function() {
							$('#exampleTable1,#exampleTable11,#exampleTable12,#exampleTable13,#exampleTable14')
									.DataTable({
										dom : 'tip',

									});
						}); */
	</script>


	<!-- <script>
	 document.addEventListener("DOMContentLoaded", function () {
	        // Original Donut Chart
	        var donutCtx = document.getElementById('donut-chart').getContext('2d');
	        var donutChart = new Chart(donutCtx, {
	            type: 'doughnut',
	            data: {
	                labels: ['Free%', 'Used%'],
	                datasets: [{
	                    data: [70, 80],
	                    backgroundColor: ['#28a745', '#dc3545'],
	                }],
	            },
	            options: {
	                legend: {
	                    display: true,
	                },
	            },
	        });

	        // CPU Doughnut Chart
	        var cpuData = {
	            used: 70,
	            free: 30
	        };

	        var cpuCtx = document.getElementById('cpu-doughnut-chart').getContext('2d');
	        var cpuDoughnutChart = new Chart(cpuCtx, {
	            type: 'doughnut',
	            data: {
	                labels: ['Used %', 'Free %'],
	                datasets: [{
	                    data: [cpuData.used, cpuData.free],
	                    backgroundColor: ['#DD1C1C', '#52ED15'],
	                }],
	            },
	            options: {
	                legend: {
	                    display: false
	                },
	                cutoutPercentage: 50 // Adjust this value based on your design preference
	            },
	        });

	        // Example of updating the CPU doughnut chart with new data
	        setInterval(function () {
	            cpuData.used = Math.random() * 100; // Replace this with actual CPU utilization data
	            cpuData.free = 100 - cpuData.used;

	            cpuDoughnutChart.data.datasets[0].data = [cpuData.used, cpuData.free];
	            cpuDoughnutChart.update();
	        }, 5000); // Update every 5 seconds (adjust the interval based on your requirements)
	    });
	</script> -->
	<script>
		$('#exampleTable6').DataTable({

			dom : 'frtip'
		});
	</script>


</body>
</html>