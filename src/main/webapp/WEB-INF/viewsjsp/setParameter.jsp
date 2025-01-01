
			<section class="content setParameter" style="flex: 75%; display:none;">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Set Parameter</h4>
								</div>
								<div class="card-body">
									<!-- Nav tabs -->
									<div class="default-tab">
										<ul class="nav nav-tabs" role="tablist">
											<li class="nav-item"><a class="nav-link active"
												data-toggle="tab" href="#process">Process Monitoring </a></li>
											<li class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#service" onclick="viewServiceListing()">Service Monitoring</a></li>
										<!-- 	<li class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#files">Files Monitoring</a></li> -->
										</ul>
										<div class="tab-content">
											<div class="tab-pane fade show active" id="process"
												role="tabpanel">
												<div class="pt-4">
													<section class="content">
														<div class="container-fluid">
															<div class="row">
																<div class="col-12">


																	<div class="card">
																		<div class="card-header"
																			style="background-color: #948b8b;">
																			<h3 class="card-title">Process Monitoring</h3>
																		</div>
																		<!-- /.card-header -->
																		<div class="card-body">

																			<table id="exampleTable51"
																				class="table table-bordered table-striped">
																				<thead>
																					<tr>
																						<th><input type="checkbox" id="selectAll"></th>
																						<th>Process ID</th>
																						<th>Device IP</th>
																						<th>Device Name</th>
																						<th>Process Name</th>
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
													<div class="container-fluid">
														<div class="row">
															<div class="col-12">
																<div class="float-right mb-4">
																	<button type="button" class="btn btn-danger"
																		style="width: 100px;" onclick="cancelAction()">Cancel</button>
																	<button type="button" class="btn btn-success"
																		style="width: 100px;" onclick="addProcessMonitor()">Add</button>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="tab-pane fade" id="service">
												<div class="pt-4">
													<section class="content">
														<div class="container-fluid">
															<div class="row">
																<div class="col-12">


																	<div class="card">
																		<div class="card-header"
																			style="background-color: #948b8b;">
																			<h3 class="card-title">Service Monitoring</h3>
																		</div>
																		<!-- /.card-header -->
																		<div class="card-body">


																			<table id="exampleTable52"
																				class="table table-bordered table-striped">
																				<thead>
																					<tr>
																						<th><input type="checkbox" id="selectAllService"></th>
																						<th>Service ID</th>
																						<th>Device IP</th>
																						<th>Device Name</th>
																						<th>Service Name</th>
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
													<div class="container-fluid">
														<div class="row">
															<div class="col-12">
																<div class="float-right mb-4">
																	<button type="button" class="btn btn-danger"
																		style="width: 100px;" onclick="cancelAction()">Cancel</button>
																	<button type="button" class="btn btn-success"
																		style="width: 100px;" onclick="addServiceMonitor()">Add</button>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="tab-pane fade" id="files" role="tabpanel">
												<div class="pt-4">
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
					<!-- /.container-fluid -->
			</section>
		