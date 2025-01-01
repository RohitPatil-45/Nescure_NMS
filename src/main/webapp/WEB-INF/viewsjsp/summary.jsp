<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NPM | Home</title>

<!-- Google Font: Source Sans Pro -->
<!-- <link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
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
</head>
<style>
.card-header {
	background-color: dodgerblue;
}

.modal-header {
	background-color: dodgerblue;
}

#searchDataOption {
	text-align: center !important;
}

.scrollDiv {
	float: left;
	overflow-y: auto;
	height: 79%;
	width: 100% !important;
}

.headerAlertbody {
	background-color: #ececec;
	padding: 0.5em;
	margin: 4px;
}

.headerAlert {
	font-size: 18px;
	font-weight: bold;
}

.donutChart {
	min-height: 250px;
	height: 250px;
	max-height: 250px;
	max-width: 100%;
}
</style>
<body
	class="hold-transition layout-top-nav layout-fixed layout-navbar-fixed layout-footer-fixed">
	<!-- class="hold-transition dark-mode layout-top-nav layout-fixed layout-navbar-fixed layout-footer-fixed">  -->
	<div class="wrapper">

		<!-- Preloader -->
		<div
			class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__wobble"
				src="<%=request.getContextPath()%>/webtemplate/dist/img/AdminLTELogo.png"
				alt="AdminLTELogo" height="60" width="60">
		</div>

		<!-- Navbar -->
		<jsp:include page="header.jsp" />
		<!-- /.navbar -->

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Overview</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Overview</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<!-- Info boxes -->
					<div class="row">
						<div class="col-12 col-sm-6 col-md-3">
							<div class="info-box">
								<span class="info-box-icon bg-success elevation-1"><i
									class="fas fa-server"></i></span>

								<div class="info-box-content">
									<span class="info-box-text">Up Nodes</span> <span
										class="info-box-number" id="upspanid" data-toggle="modal"
										data-target="#device-info-modal" onclick="getNodeInfo('Up')">
										0</span>

								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->
						</div>
						<!-- /.col -->
						<div class="col-12 col-sm-6 col-md-3">
							<div class="info-box mb-3">
								<span class="info-box-icon bg-danger elevation-1"><i
									class="fas fa-server"></i></span>

								<div class="info-box-content">
									<span class="info-box-text">Down Nodes</span> <span
										class="info-box-number" id="downspanid" data-toggle="modal"
										data-target="#device-info-modal" onclick="getNodeInfo('Down')">0</span>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->
						</div>
						<!-- /.col -->

						<!-- fix for small devices only -->
						<div class="clearfix hidden-md-up"></div>

						<div class="col-12 col-sm-6 col-md-3">
							<div class="info-box mb-3">
								<span class="info-box-icon bg-warning elevation-1"><i
									class="fas fa-server"></i></span>

								<div class="info-box-content">
									<span class="info-box-text">Warning Nodes</span> <span
										class="info-box-number" id="warningspanid" data-toggle="modal"
										data-target="#device-info-modal"
										onclick="getNodeInfo('Warning')">0</span>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->
						</div>
						<!-- /.col -->
						<div class="col-12 col-sm-6 col-md-3">
							<div class="info-box mb-3">
								<span class="info-box-icon bg-info elevation-1"><i
									class="fas fa-server"></i></span>

								<div class="info-box-content">
									<span class="info-box-text">Total Nodes</span> <span
										class="info-box-number" id="totalspanid" data-toggle="modal"
										data-target="#device-info-modal"
										onclick="getNodeInfo('total')">0</span>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->

					<!-- Info boxes -->
					<div class="row" style="display: none;">
						<div class="col-12 col-sm-6 col-md-3">
							<div class="info-box">
								<span class="info-box-icon bg-success elevation-1"><i
									class="fas fa-signal"></i></span>

								<div class="info-box-content">
									<span class="info-box-text">Up Links</span> <span
										class="info-box-number" id="uplinkspanid" data-toggle="modal"
										data-target="#link-info-modal" onclick="getLinkInfo('up')">0</span>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->
						</div>
						<!-- /.col -->
						<div class="col-12 col-sm-6 col-md-3">
							<div class="info-box mb-3">
								<span class="info-box-icon bg-danger elevation-1"><i
									class="fas fa-signal"></i></span>

								<div class="info-box-content">
									<span class="info-box-text">Down Links</span> <span
										class="info-box-number" id="downlinkspanid"
										data-toggle="modal" data-target="#link-info-modal"
										onclick="getLinkInfo('down')">0</span>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->
						</div>
						<!-- /.col -->

						<!-- fix for small devices only -->
						<div class="clearfix hidden-md-up"></div>

						<div class="col-12 col-sm-6 col-md-3">
							<div class="info-box mb-3">
								<span class="info-box-icon bg-warning elevation-1"><i
									class="fas fa-signal"></i></span>

								<div class="info-box-content">
									<span class="info-box-text">Total Links</span> <span
										class="info-box-number" id="totallinkspanid"
										data-toggle="modal" data-target="#link-info-modal"
										onclick="getLinkInfo('total')">0</span>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->
						</div>
						<!-- /.col -->
						<div class="col-12 col-sm-6 col-md-3">
							<div class="info-box mb-3">
								<span class="info-box-icon bg-secondary elevation-1"><i
									class="fas fa-exclamation-circle"></i></span>

								<div class="info-box-content">
									<span class="info-box-text">Alerts</span> <span
										class="info-box-number" id="alertspanid" data-toggle="modal"
										data-target="#alert-info-modal" onclick="getAlertInfo()">0</span>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->



					<!-- Main row -->
				</div>

				<div class="row" style="display: none">
					<!-- Left col -->


					<div class="col-md-8">
						<!-- TABLE: LATEST ORDERS -->
						<div class="card">
							<div class="card-header border-transparent">
								<h3 class="card-title">Down Device Summary</h3>

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
							<!-- /.card-header -->
							<div class="card-body">


								<table id="deviceSummary"
									class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>IP Address</th>
											<th>Location</th>
											<th>Customer</th>
											<th>Device Name</th>
											<th>Down Since</th>
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

					<div class="col-md-4">
						<!-- PIE CHART -->
						<div class="card border-transparent">
							<div class="card-header">
								<h3 class="card-title">Node Composition</h3>

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
								<canvas id="devicepieChart"
									style="min-height: 290px; height: 290px; max-height: 290px; max-width: 100%;"></canvas>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>

					<!-- /.col -->

				</div>
				<!-- /.row -->

				<!-- Main row -->
				<div class="row" style="display: none">
					<!-- Left col -->


					<div class="col-md-8">
						<!-- TABLE: LATEST ORDERS -->
						<div class="card">
							<div class="card-header border-transparent">
								<h3 class="card-title">Down Link Summary</h3>

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
							<!-- /.card-header -->
							<div class="card-body">


								<table id="linkSummary"
									class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>IP Address</th>
											<th>Interface Name</th>
											<th>Location</th>
											<th>Device Name</th>
											<th>Customer</th>
											<th>Down Since</th>
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

					<div class="col-md-4">
						<!-- PIE CHART -->
						<div class="card border-transparent">
							<div class="card-header">
								<h3 class="card-title">Link Composition</h3>

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
								<canvas id="linkpieChart"
									style="min-height: 290px; height: 290px; max-height: 290px; max-width: 100%;"></canvas>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>

					<!-- /.col -->

				</div>
				<!-- /.row -->
				<!-- Main row -->
				<div class="row">
					<!-- Left col -->


					<div class="col-md-6">
						<!-- TABLE: LATEST ORDERS -->
						<div class="card">
							<div class="card-header border-transparent">
								<h3 class="card-title">Group Wise Summary</h3>

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
							<!-- /.card-header -->
							<div class="card-body">


								<table id="groupSummary"
									class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>Group Name</th>
											<th>Total Node</th>
											<th>Up Node</th>
											<th>Down Node</th>
											<th>Warning</th>
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
					<div class="modal fade" id="port-info-modal">
						<!-- 						style="height: 500px;" -->
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Group wise Summary</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body" id="portSummaryBody">
									<div class="card-body table-responsive p-0">
										<table class="table table-striped table-valign-middle"
											id="portSummaryTable">
											<thead>
												<tr>
												<tr>
													<th>IP Address</th>
													<th>Group Name</th>
													<th>Device Name</th>
													<th>WAN/3g-4g</th>
													<th>Location</th>
													<th>Status</th>
													<th>Status TimeStamp</th>

												</tr>
												</tr>
											</thead>
											<tbody>

											</tbody>
										</table>
									</div>
								</div>

							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>

					<!-- 				<div class="col-md-6">
						<div class="card">
							<div class="card-header border-transparent">
								<h3 class="card-title">Interface Wise Summary</h3>

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


								<table id="interfaceSummary"
									class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>Group Name</th>
											<th>Total Interface</th>
											<th>Up Interface</th>
											<th>Down Interface</th>
											<th>Warning</th>
										</tr>
									</thead>
									<tbody>

									</tbody>

								</table>


							</div>

						</div>

					</div>  -->

					<div class="col-md-6">
						<div class="card">
							<div class="card-header border-transparent">
								<h3 class="card-title">Interface Wise Summary</h3>

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


								<table id="interfaceSummaryNew"
									class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>Group Name</th>
											<th>Total Interface</th>
											<th>Up WAN Interface</th>
											<th>Up 4G Interface</th>
											<th>Down WAN Interface</th>
											<th>Down 4G Interface</th>
										</tr>
									</thead>
									<tbody>

									</tbody>

								</table>


							</div>

						</div>

					</div>

					<div class="modal fade" id="interface-info-modal">
						<!-- 						style="height: 500px;" -->
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Interface wise Summary</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body" id="portSummaryBody"
									style="overflow: scroll; width: 100%;">
									<div class="card-body table-responsive p-0">
										<table class="table table-striped table-valign-middle"
											id="interfaceSummaryTable">
											<thead>
												<tr>
													<th>IP Address</th>
													<th>Interface Name</th>
													<th>Group Name</th>
													<th>Device Name</th>
													<th>Location</th>
													<th>District</th>
													<th>State</th>
													<th>Zone</th>
													<th>ICMP Status</th>
													<th>Status TimeStamp</th>


												</tr>
											</thead>
											<tbody>

											</tbody>
										</table>
									</div>
								</div>

							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
				</div>
				<!--/. container-fluid -->
				<div class="row" style="display: none">
					<!-- Left col -->


					<div class="col-md-6">
						<!-- TABLE: LATEST ORDERS -->
						<div class="card" style="height: 100%;">
							<div class="card-header border-transparent">
								<h3 class="card-title">Recent Alarms</h3>

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
							<!-- /.card-header -->
							<div class="card-body">
								<canvas id="donutChart"></canvas>
								<div id="centerContent"
									style="position: absolute; top: 60%; left: 50%; transform: translate(-50%, -50%); text-align: center;">
									<img
										src="<%=request.getContextPath()%>/webtemplate/dist/img/alarm.png"
										alt="Bell Icon"
										style="width: 40px; height: 40px; margin-top: -20px;">
									<div id="alarmCount"
										style="font-size: 24px; font-weight: bold; margin-top: 10px;"></div>
									Alarm
								</div>
							</div>
							<!-- /.card-body -->

						</div>
						<!-- /.card -->
					</div>
					<div class="col-md-6">
						<!-- TABLE: LATEST ORDERS -->
						<div class="card" style="height: 100%;">
							<div class="card-header border-transparent">
								<h3 class="card-title">Device Type Summary</h3>

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
							<!-- /.card-header -->
							<div class="card-body">


								<table id="deviceTypeSummary"
									class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>Device Type</th>
											<th>Total Node</th>
											<th>Up Node</th>
											<th>Down Node</th>
											<th>Warning</th>
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
					<div class="modal fade" id="deviceTypeSummaryModal"
						style="height: 500px;">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Device Type wise Summary</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body" id="deviceTypeSummaryData">
									<div class="card-body table-responsive p-0">
										<table class="table table-striped table-valign-middle"
											id="deviceTypeSummaryTable">
											<thead>
												<tr>
												<tr>
													<th>IP Address</th>
													<th>Device Type</th>
													<th>Device Name</th>
													<th>Location</th>
													<th>Status</th>
													<th>Status TimeStamp</th>
												</tr>
												</tr>
											</thead>
											<tbody>

											</tbody>
										</table>
									</div>
								</div>

							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	<div class="modal fade" id="alarmInfoModal" style="height: 500px;">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Alarm Info</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="alertInfoBody">
					<div class="card-body table-responsive p-0" style="height: 250px">
						<div class="card-body headerAlertbody">
							<span class="headerAlert">Device Name</span>
						</div>
						<div class="card-body scrollDiv">
							<div id="NodeMonitoringData"></div>
							<div id="NodeHelthMonitoringData"></div>
							<div id="NodeHelthCpuMonitoringData"></div>
							<div id="DriveMonitoring"></div>
							<div id="InterfaceMonitoringData"></div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		</div>
	</div>

	<!-- Alert Modal  -->
	<div class="modal fade" id="alert-info-modal" style="height: 500px;">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Alert Info</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="alertInfoBody">
					<div class="card-body table-responsive p-0" style="height: 250px">
						<div class="row">
							<div id="up_tree" style="max-height: 500px; overflow-y: scroll;"></div>
						</div>
						<br>
						<div class="row">
							<div id="cpu_tree" style="max-height: 500px; overflow-y: scroll;"></div>
						</div>
						<br>
						<div class="row">
							<div id="memory_tree"
								style="max-height: 500px; overflow-y: scroll;"></div>
						</div>
						<br>
						<div class="row">
							<div id="drive_tree"
								style="max-height: 500px; overflow-y: scroll;"></div>
						</div>
						<br>
						<div class="row">
							<div id="bandwidth_tree"
								style="max-height: 500px; overflow-y: scroll;"></div>
						</div>
						<br>
						<div class="row">
							<div id="topology_tree"
								style="max-height: 500px; overflow-y: scroll;"></div>
						</div>

					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		</div>
	</div>
	<!--End Alert Modal  -->

	<!-- Sample Model -->
	<div class="modal fade" id="device-info-modal">
		<!-- 	style="height: 500px;" -->
		<div class="modal-dialog modal-lg">
			<div class="modal-content" style="width: fit-content;">
				<div class="modal-header">
					<h4 class="modal-title">Devices Info</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="deviceInfoBody">
					<div class="card-body table-responsive p-0">
						<table class="table table-striped table-valign-middle"
							id="deviceInfoTable">
							<thead>
								<tr>
									<th>Sr No.</th>
									<th>Node IP</th>
									<th>Node Name</th>
									<th>WAN/3g-4g</th>
									<th>Location</th>
									<th>Customer</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- Sample Model -->

	<div class="modal fade" id="link-info-modal" style="height: 500px;">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Link Info</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="linkInfoBody">
					<div class="card-body table-responsive p-0" style="height: 350px;">
						<div>
							<table class="table table-striped table-valign-middle"
								id="linkInfoTable">
								<thead>
									<tr>
										<th>Sr No.</th>
										<th>Node IP</th>
										<th>Interface Name</th>
										<th>Location</th>
										<th>Customer</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>

						</div>
					</div>

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

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

	<!-- REQUIRED SCRIPTS -->
	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- overlayScrollbars -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.js"></script>

	<!-- PAGE PLUGINS -->
	<!-- jQuery Mapael -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/raphael/raphael.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-mapael/jquery.mapael.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-mapael/maps/usa_states.min.js"></script>
	<!-- ChartJS -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/chart.js/Chart.min.js"></script>

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
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/AdminPro/js/tree-line/jstree.min.js"></script>

	<!-- 	<script src="https://code.highcharts.com/highcharts.js"></script> -->

	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/highcharts.js"></script>

	<!-- 	<script src="https://code.highcharts.com/modules/exporting.js"></script> -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/exporting.js"></script>
	<!-- 	<script src="https://code.highcharts.com/modules/accessibility.js"></script> -->

	<script
		src="<%=request.getContextPath()%>/webtemplate/OfflineJs/accessibility.js"></script>


	<!-- Page specific script -->
	<script
		src="<%=request.getContextPath()%>/custom_js/summaryDashboard.js"></script>

	<script>
		// Device Count
		/* var vUpCount = localStorage.getItem("upCountVar");
		var vDownCount = localStorage.getItem("downCountVar");
		var vWarningCount = localStorage.getItem("warningCountVar");

		var upCountVal = Number(vUpCount);
		var downCountVal = Number(vDownCount);
		var warningCountVal = Number(vWarningCount);

		var devicedonutData = {
			labels : [ 'Up', 'Down', 'Warning' ],
			datasets : [ {
				data : [ upCountVal, downCountVal, warningCountVal ],
				backgroundColor : [ '#00a65a', '#f56954', '#f39c12' ],
			} ]
		} */

		// Link Count
		/* var vUpLinkCount = localStorage.getItem("upLinkCountVar");
		var vDownLinkCount = localStorage.getItem("downLinkCountVar");

		var upLinkCountVal = Number(vUpLinkCount);
		var downLinkCountVal = Number(vDownLinkCount);

		var linkdonutData = {
			labels : [ 'Up', 'Down' ],
			datasets : [ {
				data : [ upLinkCountVal, downLinkCountVal ],
				backgroundColor : [ '#00a65a', '#f56954' ],
			} ]
		} */

		//-------------
		//- PIE CHART -
		//-------------
		// Get context with jQuery - using jQuery's .get() method.
		/* var devicepieChartCanvas = $('#devicepieChart').get(0).getContext('2d')
		var devicepieData = devicedonutData;
		var devicepieOptions = {
			maintainAspectRatio : false,
			responsive : true,
		}

		//Create pie or douhnut chart
		// You can switch between pie and douhnut using the method below.
		var devicechart = new Chart(devicepieChartCanvas, {
			type : 'pie',
			data : devicepieData,
			options : devicepieOptions
		}) */

		/* var linkpieChartCanvas = $('#linkpieChart').get(0).getContext('2d')
		var linkpieData = linkdonutData;
		var linkpieOptions = {
			maintainAspectRatio : false,
			responsive : true,
		}
		//Create pie or douhnut chart
		// You can switch between pie and douhnut using the method below.
		var linkpie = new Chart(linkpieChartCanvas, {
			type : 'pie',
			data : linkpieData,
			options : linkpieOptions
		}) */
	</script>
	<script>
		/* $(function() {
			$("#groupSummary").DataTable(
					{
						data : ${groupSummary},
						"responsive" : true,
						"lengthChange" : false,
						"autoWidth" : false,
						"buttons" : [ "copy", "csv", "excel", "pdf", "print",
								"colvis" ]
					}).buttons().container().appendTo(
					'#example1_wrapper .col-md-6:eq(0)');
		}); */
	</script>

</body>
</html>
