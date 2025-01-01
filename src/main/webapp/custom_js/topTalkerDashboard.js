
window.onload = function(){
	$("#spinner1Onchange").hide();
	
	getTopTalkerChart();
	getTopTalkerSumOfDeviceDetailsList()
	
	getTopConnChart();
	getTopConnSumOfDeviceDetailsList();
	
	getTopProtocolChart();
	getTopProtocolSumOfDeviceDetailsList();
	
}

$("#ToDate").on('change', function(event) {
	$("#spinner1Onchange").show();
	$("#spinner1").show();
	event.preventDefault();

	var fromDate = $("#FromDate").val() + " 00:00:00.000";
	var toDate = $("#ToDate").val() + " 23:59:00.000";

	if ($("#FromDate").val() > $("#ToDate").val()) {
		
		swal({
			// position : 'top',
			// icon : 'warning',
			text : "Form date should be smaller than To date",
			width : 150,
			confirmButtonClass : "btn btn-success",
		// showConfirmButton : false,
		// timer : 1500
		})
		$("#FromDate").val("");
		$("#ToDate").val("");
	} else if ($("#FromDate").val() === null || $("#FromDate").val() === '') {
		swal({
			// position : 'top',
			// icon : 'warning',
			text : "Please Enter From Date",
			width : 150,
			confirmButtonClass : "btn btn-success",
		// showConfirmButton : false,
		// timer : 1500
		})
		$("#FromDate").val("");
	} else {
	//	alert("hii")
		getTopTalkerChart1(fromDate, toDate);
		getTopTalkerSumOfDeviceDetailsList1(fromDate, toDate);
		
		getTopConnChart1(fromDate, toDate);
		getTopConnSumOfDeviceDetailsList1(fromDate, toDate);
		
		getTopProtocolChart1(fromDate, toDate);
		getTopProtocolSumOfDeviceDetailsList1(fromDate, toDate);
		
	}

});

//Top Talker Source IP wise
function getTopTalkerChart1(fromDate, toDate) {
	$("#spinnerTopTalkChart").show()
//	var d = fromDate;
//	d = d.substr(0, 10).split("-");
//
//	d = d[2] + "-" + d[1] + "-" + d[0];
//
//	// d = d[2] + "/" + d[1] + "/" + d[0];
//	var e = toDate;
//	e = e.substr(0, 10).split("-");
//
//	e = e[2] + "-" + e[1] + "-" + e[0];
	// d = d[2] + "/" + d[1] + "/" + d[0];
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopTalkerChartFromDateToDate";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',
				data : 'ipAddress=' + $("#ipAddressHidden").val()
						+ '&fromDate=' + fromDate + '&toDate=' + toDate,
				success : function(data) {
					// alert(data)
					$("#spinnerTopTalkChart").hide()
					$("#spinner1Onchange").hide()
					spinner1Onchange
					// Data retrieved from https://netmarketshare.com/
					// Make monochrome colors
					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[0], i;

						for (i = 0; i < 10; i += 1) {
							// Start out with a darkened base color (negative
							// brighten), and end
							// up with a much brighter color
							colors.push(Highcharts.color(base).brighten(
									(i - 3) / 7).get());
						}
						return colors;
					}());

					// Build the chart
					Highcharts
							.chart(
									'topTalkercontainer',
									{
										chart : {
											plotBackgroundColor : null,
											plotBorderWidth : null,
											plotShadow : false,
											type : 'pie'
										},
										title : {

											text : '<span style="font-size: 16px;color:blue">Top Talker '
//													+ fromDate
//													+ " To "
//													+ toDate 
													+ '</span>',

											// text: 'Top Connection '+d+" To
											// "+e,
											align : 'left'
										},
										tooltip : {
											pointFormat : '<b>{point.z}</b>'
										},
									
										accessibility : {
											point : {
												valueSuffix : '%'
											}
										},
										colors : [ '#012a4a', '#013a63',
												'#01497c', '#014f86',
												'#2a6f97', '#2c7da0',
												'#468faf', '#61a5c2',
												'#293462', '#16213E', '#293462' ],
										// colors : [ '#50B432', '#ED561B',
										// '#DDDF00', '#24CBE5',
										// '#64E572', '#FF9655',
										// '#FFF263', '#6AF9C4' ],
										plotOptions : {
											pie : {
												allowPointSelect : true,
												cursor : 'pointer',
												colors : pieColors,
												dataLabels : {
													enabled : true,
													format : '<b>{point.name}</b><br>{point.percentage:.1f} %',
													distance : -50,
													filter : {
														property : 'percentage',
														operator : '>',
														value : 4
													}
												}
											}
										},
										series : [ {

											name : 'Share',
											data : data

										} ]
									});
				}
			})
}

function getTopTalkerChart() {
	$("#spinnerTopTalkChart").show()

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopTalkerChartAllData";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					// alert(data)
					$("#spinnerTopTalkChart").hide()
					// Data retrieved from https://netmarketshare.com/
					// Make monochrome colors
					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[2], i;

						for (i = 0; i < 10; i += 1) {
							// Start out with a darkened base color (negative
							// brighten), and end
							// up with a much brighter color
							colors.push(Highcharts.color(base).brighten(
									(i - 3) / 7).get());
						}
						return colors;
					}());

					// Build the chart
					Highcharts
							.chart(
									'topTalkercontainer',
									{
										chart : {
											plotBackgroundColor : null,
											plotBorderWidth : null,
											plotShadow : false,
											type : 'pie'
										},
										title : {

											text : '<span style="font-size: 16px;color:blue">Top Talker</span>',

											// text: 'Top Connection '+d+" To
											// "+e,
											align : 'left'
										},
										tooltip : {
											pointFormat : '<b>{point.z}</b>'
										},
										// tooltip : function () {
										// alert(this.y / 1024)
										// return (this.y / 1024)+ 'KB'
										// },
										accessibility : {
											point : {
												valueSuffix : '%'
											}
										},
										colors : [ '#50B432', '#ED561B',
												'#DDDF00', '#24CBE5',
												'#64E572', '#FF9655',
												'#FFF263', '#6AF9C4' ],
										plotOptions : {
											pie : {
												allowPointSelect : true,
												cursor : 'pointer',
												colors : pieColors,
												dataLabels : {
													enabled : true,
													format : '<b>{point.name}</b><br>{point.percentage:.1f} %',
													distance : -50,
													filter : {
														property : 'percentage',
														operator : '>',
														value : 4
													}
												}
											}
										},
										series : [ {

											name : 'Share',
											data : data
										// data: [
										// { name: 'Chrome', y: 74.03 },
										// { name: 'Edge', y: 12.66 },
										// { name: 'Firefox', y: 4.96 },
										// { name: 'Safari', y: 2.49 },
										// { name: 'Internet Explorer', y: 2.31
										// },
										// { name: 'Other', y: 3.398 }
										// ]
										} ]
									});

				}
			})

}

function getTopTalkerSumOfDeviceDetailsList() {
	$("#spinner1").show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopTalkerSumOfDeviceListAlldata";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',

				success : function(data) {
					// alert(data)
					$("#spinner1").hide();
//					$('#topTalkerSourceIp thead tr').clone(true)
//							.appendTo('#topTalkerSourceIp thead')
//							.addClass('searchRow');
//					$('#topTalkerSourceIp thead tr:eq(1) th')
//							.each(
//									function(i) {
//										var title = $(this).text();
//
//										if (title === "Select"
//												|| title === "Action") {
//											$(this)
//													.html(
//															'<div><label>&nbsp;</label></div>');
//
//										} else {
//
//											$(this)
//													.html(
//															'<input type="text" style="width:100px" class="customSearch" placeholder="Search" />');
//
//											$('input', this)
//													.on(
//															'keyup change',
//															function() {
//																if (table
//																		.column(
//																				i)
//																		.search() !== this.value) {
//																	table
//																			.column(
//																					i)
//																			.search(
//																					this.value)
//																			.draw();
//																}
//															});
//
//										}
//									});
//
//					$('#topTalkerSourceIp').DataTable().columns
//							.adjust().draw();
					var table = $('#topTalkerSourceIp')
							.DataTable(
									{
										lengthChange : false,
										// pageLength : 5,
										data : data,
										destroy : true,
										scrollX : true,
										scrollY : '250px',
										info : false,
										paging : false,
										searching : true,
										searching : true,
										"ordering" : true,
										"order" : [ [ 0, "asc" ] ],
										buttons : [
												{
													text : 'Toggle Search',

													action : function() {
														var element = document
																.querySelector('.searchRow');
														// alert(element)
														var style = getComputedStyle(element);
														var displaystyle = style.display;

														if (displaystyle === "none") {
															$('.searchRow')
																	.css(
																			'display',
																			'contents');
														} else {
															$('.searchRow')
																	.css(
																			'display',
																			'none');
														}

													},

												}, ]
									});

					table.buttons().container().appendTo(
							$('.col-sm-6:eq(0)', table.table().container()));

				}
			})
}

function getTopTalkerSumOfDeviceDetailsList1(fromDate, toDate) {
	// alert("getTopTalkerSumOfDeviceDetailsList")
	$("#spinner1").show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url
			+ "/dashboard/getTopTalkerSumOfDeviceListFromDateToDate";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',

				data : 'ToDate=' + toDate + '&FromDate=' + fromDate,
				success : function(data) {
					// alert(data)
					$("#spinner1").hide();
//					$('#topTalkerSourceIp thead tr').clone(true)
//							.appendTo('#topTalkerSourceIp thead')
//							.addClass('searchRow');
//					$('#topTalkerSourceIp thead tr:eq(1) th')
//							.each(
//									function(i) {
//										var title = $(this).text();
//
//										if (title === "Select"
//												|| title === "Action") {
//											$(this)
//													.html(
//															'<div><label>&nbsp;</label></div>');
//
//										} else {
//
//											$(this)
//													.html(
//															'<input type="text" style="width:100px" class="customSearch" placeholder="Search" />');
//
//											$('input', this)
//													.on(
//															'keyup change',
//															function() {
//																if (table
//																		.column(
//																				i)
//																		.search() !== this.value) {
//																	table
//																			.column(
//																					i)
//																			.search(
//																					this.value)
//																			.draw();
//																}
//															});
//
//										}
//									});
					var table = $('#topTalkerSourceIp')
							.DataTable(
									{
										lengthChange : false,
										// pageLength : 5,
										data : data,
										destroy : true,
										scrollX : true,
										scrollY : '250px',
										info : false,
										paging : false,
										searching : true,
										"ordering" : true,
										// "order" : [ [ 1, "asc" ] ],
										buttons : [
												{
													text : 'Toggle Search',

													action : function() {
														var element = document
																.querySelector('.searchRow');
														// alert(element)
														var style = getComputedStyle(element);
														var displaystyle = style.display;

														if (displaystyle === "none") {
															$('.searchRow')
																	.css(
																			'display',
																			'contents');
														} else {
															$('.searchRow')
																	.css(
																			'display',
																			'none');
														}

													},

												}, ]
									});

					table.buttons().container().appendTo(
							$('.col-sm-6:eq(0)', table.table().container()));

				}
			})
}
// End Top Talker Source IP wise


// Top Talker Connection wise
function getTopConnChart() {
	// alert("getTopConnChart")
	$("#spinnerTopConnChart").show()

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopTalkerChartAllData";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					// alert(data)
					$("#spinnerTopConnChart").hide()
					// Data retrieved from https://netmarketshare.com/
					// Make monochrome colors
					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[2], i;

						for (i = 0; i < 10; i += 1) {
							// Start out with a darkened base color (negative
							// brighten), and end
							// up with a much brighter color
							colors.push(Highcharts.color(base).brighten(
									(i - 3) / 7).get());
						}
						return colors;
					}());

					// Build the chart
					Highcharts
							.chart(
									'topConnectioncontainer',
									{
										chart : {
											plotBackgroundColor : null,
											plotBorderWidth : null,
											plotShadow : false,
											type : 'pie'
										},
										title : {

											text : '<span style="font-size: 16px;color:blue">Top Connection</span>',

											// text: 'Top Connection '+d+" To
											// "+e,
											align : 'left'
										},
										tooltip : {
											pointFormat : '<b>{point.z}</b>'
										},
										// tooltip : function () {
										// alert(this.y / 1024)
										// return (this.y / 1024)+ 'KB'
										// },
										accessibility : {
											point : {
												valueSuffix : '%'
											}
										},
										// colors :
										// ['#012a4a','#013a63','#01497c','#014f86','#2a6f97','#2c7da0','#468faf','#61a5c2','#89c2d9','#a9d6e5'],
										colors : [ '#50B432', '#ED561B',
												'#DDDF00', '#24CBE5',
												'#64E572', '#FF9655',
												'#FFF263', '#6AF9C4' ],
										plotOptions : {
											pie : {
												allowPointSelect : true,
												cursor : 'pointer',
												colors : pieColors,
												dataLabels : {
													enabled : true,
													format : '<b>{point.name}</b><br>{point.percentage:.1f} %',
													distance : -50,
													filter : {
														property : 'percentage',
														operator : '>',
														value : 4
													}
												}
											}
										},
										series : [ {

											name : 'Share',
											data : data
										// data: [
										// { name: 'Chrome', y: 74.03 },
										// { name: 'Edge', y: 12.66 },
										// { name: 'Firefox', y: 4.96 },
										// { name: 'Safari', y: 2.49 },
										// { name: 'Internet Explorer', y: 2.31
										// },
										// { name: 'Other', y: 3.398 }
										// ]
										} ]
									});

				}
			})

}

function getTopConnChart1(fromDate, toDate) {
//	alert("getTopConnChart")
	$("#spinnerTopConnChart").show()
//	var d = fromDate;
//	d = d.substr(0, 10).split("-");
//
//	d = d[2] + "-" + d[1] + "-" + d[0];
//
//	// d = d[2] + "/" + d[1] + "/" + d[0];
//	var e = toDate;
//	e = e.substr(0, 10).split("-");
//
//	e = e[2] + "-" + e[1] + "-" + e[0];
	// d = d[2] + "/" + d[1] + "/" + d[0];
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopTalkerChartFromDateToDate";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',
				data : 'fromDate=' + fromDate + '&toDate=' + toDate,
				success : function(data) {
					// alert(data)
					$("#spinnerTopConnChart").hide()
					// Data retrieved from https://netmarketshare.com/
					// Make monochrome colors
					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[0], i;

						for (i = 0; i < 10; i += 1) {
							// Start out with a darkened base color (negative
							// brighten), and end
							// up with a much brighter color
							colors.push(Highcharts.color(base).brighten(
									(i - 3) / 7).get());
						}
						return colors;
					}());

					// Build the chart
					Highcharts
							.chart(
									'topConnectioncontainer',
									{
										chart : {
											plotBackgroundColor : null,
											plotBorderWidth : null,
											plotShadow : false,
											type : 'pie'
										},
										title : {
											text : '<span style="font-size: 16px;color:blue">Top Connection '
													+ fromDate
													+ " To "
													+ toDate + '</span>',
											align : 'left'
										},
										tooltip : {
											pointFormat : '<b>{point.z}</b>'
										},
										accessibility : {
											point : {
												valueSuffix : '%'
											}
										},
										colors : [ '#50B432', '#ED561B',
												'#DDDF00', '#24CBE5',
												'#64E572', '#FF9655',
												'#FFF263', '#6AF9C4' ],
										plotOptions : {
											pie : {
												allowPointSelect : true,
												cursor : 'pointer',
												colors : pieColors,
												dataLabels : {
													enabled : true,
													format : '<b>{point.name}</b><br>{point.percentage:.1f} %',
													distance : -50,
													filter : {
														property : 'percentage',
														operator : '>',
														value : 4
													}
												}
											}
										},
										series : [ {

											name : 'Share',
											data : data

										} ]
									});

				}
			})

}



function getTopConnSumOfDeviceDetailsList1(fromDate, toDate) {
	$("#spinner1").show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getsumOfDeviceDetailsListFromDateToDate";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',

				data : 'ToDate=' + toDate + '&FromDate=' + fromDate,
				success : function(data) {
					// (data)
					$("#spinner1").hide();
					$("#spinner1Onchange").hide();
//					$('#topConnectionSumOfDeviceTable thead tr').clone(true)
//							.appendTo('#topConnectionSumOfDeviceTable thead')
//							.addClass('searchRow');
//					$('#topConnectionSumOfDeviceTable thead tr:eq(1) th')
//							.each(
//									function(i) {
//										var title = $(this).text();
//
//										if (title === "Select"
//												|| title === "Action") {
//											$(this)
//													.html(
//															'<div><label>&nbsp;</label></div>');
//
//										} else {
//
//											$(this)
//													.html(
//															'<input type="text" style="width:100px" class="customSearch" placeholder="Search" />');
//
//											$('input', this)
//													.on(
//															'keyup change',
//															function() {
//																if (table
//																		.column(
//																				i)
//																		.search() !== this.value) {
//																	table
//																			.column(
//																					i)
//																			.search(
//																					this.value)
//																			.draw();
//																}
//															});
//
//										}
//									});
					var table = $('#topConnectionSumOfDeviceTable')
							.DataTable(
									{
										lengthChange : false,
										// pageLength : 5,
										data : data,
										destroy : true,
										scrollX : true,
										scrollY : '250px',
										info : false,
										paging : false,
										searching : true,
										// "order" : [ [ 0, "asc" ] ],
										// "ordering": true,
										buttons : [
												{
													text : 'Toggle Search',

													action : function() {
														var element = document
																.querySelector('.searchRow');
														// alert(element)
														var style = getComputedStyle(element);
														var displaystyle = style.display;

														if (displaystyle === "none") {
															$('.searchRow')
																	.css(
																			'display',
																			'contents');
														} else {
															$('.searchRow')
																	.css(
																			'display',
																			'none');
														}

													},

												}, ]
									});

					table.buttons().container().appendTo(
							$('.col-sm-6:eq(0)', table.table().container()));

				}
			})
}

function getTopConnSumOfDeviceDetailsList() {
	$("#spinner1").show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getsumOfDeviceDetailsListAllData";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					// (data)
					$("#spinner1").hide();
//					$('#topConnectionSumOfDeviceTable thead tr').clone(true)
//							.appendTo('#topConnectionSumOfDeviceTable thead')
//							.addClass('searchRow');
//					$('#topConnectionSumOfDeviceTable thead tr:eq(1) th')
//							.each(
//									function(i) {
//										var title = $(this).text();
//
//										if (title === "Select"
//												|| title === "Action") {
//											$(this)
//													.html(
//															'<div><label>&nbsp;</label></div>');
//
//										} else {
//
//											$(this)
//													.html(
//															'<input type="text" style="width:100px" class="customSearch" placeholder="Search" />');
//
//											$('input', this)
//													.on(
//															'keyup change',
//															function() {
//																if (table
//																		.column(
//																				i)
//																		.search() !== this.value) {
//																	table
//																			.column(
//																					i)
//																			.search(
//																					this.value)
//																			.draw();
//																}
//															});
//
//										}
//									});
					var table = $('#topConnectionSumOfDeviceTable')
							.DataTable(
									{
										lengthChange : false,
										// pageLength : 5,
										data : data,
										destroy : true,
										scrollX : true,
										scrollY : '250px',
										info : false,
										paging : false,
										searching : true,
										searching : true,
										orderCellsTop : true,
										ordering : true,
										"order" : [ [ 0, "asc" ] ],
										buttons : [
												{
													text : 'Toggle Search',

													action : function() {
														var element = document
																.querySelector('.searchRow');
														// alert(element)
														var style = getComputedStyle(element);
														var displaystyle = style.display;

														if (displaystyle === "none") {
															$('.searchRow')
																	.css(
																			'display',
																			'contents');
														} else {
															$('.searchRow')
																	.css(
																			'display',
																			'none');
														}

													},

												}, ]
									});

					table.buttons().container().appendTo(
							$('.col-sm-6:eq(0)', table.table().container()));

				}
			})
}

//End Top Talker Connection Wise



// Top Talker Protocol Wise
function getTopProtocolChart() {
	$("#spinnerTopProtocolChart").show();

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopProtocolChartAllData";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',

				success : function(data) {
					// alert(data)
					$("#spinnerTopProtocolChart").hide();
					// Data retrieved from https://netmarketshare.com/
					// Make monochrome colors
					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[2], i;

						for (i = 0; i < 10; i += 1) {
							// Start out with a darkened base color (negative
							// brighten), and end
							// up with a much brighter color
							colors.push(Highcharts.color(base).brighten(
									(i - 3) / 7).get());
						}
						return colors;
					}());

					// Build the chart
					Highcharts
							.chart(
									'topProtocolcontainer',
									{
										chart : {
											plotBackgroundColor : null,
											plotBorderWidth : null,
											plotShadow : false,
											type : 'pie'
										},
										title : {

											text : '<span style="font-size: 16px;color:blue">Top Protocol </span>',

											// text: 'Top Connection '+d+" To
											// "+e,
											align : 'left'
										},
										tooltip : {
											pointFormat : '<b>{point.z}</b>'
										},
										accessibility : {
											point : {
												valueSuffix : '%'
											}
										},
										colors : [ '#50B432', '#ED561B',
												'#DDDF00', '#24CBE5',
												'#64E572', '#FF9655',
												'#FFF263', '#6AF9C4' ],
										plotOptions : {
											pie : {
												allowPointSelect : true,
												cursor : 'pointer',
												colors : pieColors,
												dataLabels : {
													enabled : true,
													format : '<b>{point.name}</b><br>{point.percentage:.1f} %',
													distance : -50,
													filter : {
														property : 'percentage',
														operator : '>',
														value : 4
													}
												}
											}
										},
										series : [ {

											name : 'Share',
											data : data

										} ]
									});

				}
			})

}

function getTopProtocolSumOfDeviceDetailsList() {
	$("#spinnerProtocoloList").show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopProtocolSumOfDeviceListAlldata";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',

				success : function(data) {
					// alert(data)
					$("#spinnerProtocoloList").hide();

//					$('#topProtocolSumOfDeviceTable thead tr').clone(true)
//							.appendTo('#topProtocolSumOfDeviceTable thead')
//							.addClass('searchRow');
//					$('#topProtocolSumOfDeviceTable thead tr:eq(1) th')
//							.each(
//									function(i) {
//										var title = $(this).text();
//
//										if (title === "Select"
//												|| title === "Action") {
//											$(this)
//													.html(
//															'<div><label>&nbsp;</label></div>');
//
//										} else {
//
//											$(this)
//													.html(
//															'<input type="text" style="width:100px" class="customSearch" placeholder="Search" />');
//
//											$('input', this)
//													.on(
//															'keyup change',
//															function() {
//																if (table
//																		.column(
//																				i)
//																		.search() !== this.value) {
//																	table
//																			.column(
//																					i)
//																			.search(
//																					this.value)
//																			.draw();
//																}
//															});
//
//										}
//									});
					var table = $('#topProtocolSumOfDeviceTable')
							.DataTable(
									{
										lengthChange : false,
										// pageLength : 5,
										data : data,
										destroy : true,
										//scrollX : true,
										scrollY : '265px',
										info : false,
										paging : false,
										searching : true,
										searching : true,
										orderCellsTop : true,
										ordering : true,
										"order" : [ [ 0, "asc" ] ],
										buttons : [
												{
													text : 'Toggle Search',

													action : function() {
														var element = document
																.querySelector('.searchRow');
														// alert(element)
														var style = getComputedStyle(element);
														var displaystyle = style.display;

														if (displaystyle === "none") {
															$('.searchRow')
																	.css(
																			'display',
																			'contents');
														} else {
															$('.searchRow')
																	.css(
																			'display',
																			'none');
														}

													},

												}, ]
									});

					table.buttons().container().appendTo(
							$('.col-sm-6:eq(0)', table.table().container()));

				}
			})
}

function getTopProtocolChart1(fromDate, toDate) {
	$("#spinnerTopProtocolChart").show();
//	var d = fromDate;
//	d = d.substr(0, 10).split("-");
//
//	d = d[2] + "-" + d[1] + "-" + d[0];
//
//	// d = d[2] + "/" + d[1] + "/" + d[0];
//	var e = toDate;
//	e = e.substr(0, 10).split("-");
//
//	e = e[2] + "-" + e[1] + "-" + e[0];
	// d = d[2] + "/" + d[1] + "/" + d[0];
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopProtocolChartFromDateToDate";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',
				data : 'ipAddress=' + $("#ipAddressHidden").val()
						+ '&fromDate=' + fromDate + '&toDate=' + toDate,
				success : function(data) {
					// alert(data)
					$("#spinnerTopProtocolChart").hide();
					// Data retrieved from https://netmarketshare.com/
					// Make monochrome colors
					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[0], i;

						for (i = 0; i < 10; i += 1) {
							// Start out with a darkened base color (negative
							// brighten), and end
							// up with a much brighter color
							colors.push(Highcharts.color(base).brighten(
									(i - 3) / 7).get());
						}
						return colors;
					}());

					// Build the chart
					Highcharts
							.chart(
									'topProtocolcontainer',
									{
										chart : {
											plotBackgroundColor : null,
											plotBorderWidth : null,
											plotShadow : false,
											type : 'pie'
										},
										title : {

											text : '<span style="font-size: 16px;color:blue">Top Protocol '
													+ fromDate
													+ " To "
													+ toDate + '</span>',

											// text: 'Top Connection '+d+" To
											// "+e,
											align : 'left'
										},
										tooltip : {
											pointFormat : '<b>{point.z}</b>'
										},
										accessibility : {
											point : {
												valueSuffix : '%'
											}
										},
										colors : [ '#50B432', '#ED561B',
												'#DDDF00', '#24CBE5',
												'#64E572', '#FF9655',
												'#FFF263', '#6AF9C4' ],
										plotOptions : {
											pie : {
												allowPointSelect : true,
												cursor : 'pointer',
												colors : pieColors,
												dataLabels : {
													enabled : true,
													format : '<b>{point.name}</b><br>{point.percentage:.1f} %',
													distance : -50,
													filter : {
														property : 'percentage',
														operator : '>',
														value : 4
													}
												}
											}
										},
										series : [ {

											name : 'Share',
											data : data

										} ]
									});

				}
			})

}

function getTopProtocolSumOfDeviceDetailsList1(fromDate, toDate) {
	// alert("inside getTopProtocolSumOfDeviceDetailsList")
	$("#spinnerProtocoloList").show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	// var serviceUrl = base_url + "/Node/getTopProtocolSumOfDeviceList";
	var serviceUrl = base_url
			+ "/dashboard/getTopProtocolSumOfDeviceListFromDateToDate";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',

				data : 'ToDate=' + toDate + '&FromDate=' + fromDate,
				success : function(data) {
					// alert(data)
					$("#spinnerProtocoloList").hide();

					$('#topProtocolSumOfDeviceTable thead tr').clone(true)
							.appendTo('#topProtocolSumOfDeviceTable thead')
							.addClass('searchRow');
					$('#topProtocolSumOfDeviceTable thead tr:eq(1) th')
							.each(
									function(i) {
										var title = $(this).text();

										if (title === "Select"
												|| title === "Action") {
											$(this)
													.html(
															'<div><label>&nbsp;</label></div>');

										} else {

											$(this)
													.html(
															'<input type="text" style="width:100px" class="customSearch" placeholder="Search" />');

											$('input', this)
													.on(
															'keyup change',
															function() {
																if (table
																		.column(
																				i)
																		.search() !== this.value) {
																	table
																			.column(
																					i)
																			.search(
																					this.value)
																			.draw();
																}
															});

										}
									});
					var table = $('#topProtocolSumOfDeviceTable')
							.DataTable(
									{
										lengthChange : false,
										// pageLength : 5,
										data : data,
										destroy : true,
										scrollX : true,
										scrollY : '150px',
										info : false,
										paging : false,
										searching : true,
										"ordering" : true,
										// "order" : [ [ 0, "asc" ] ],
										buttons : [
												{
													text : 'Toggle Search',

													action : function() {
														var element = document
																.querySelector('.searchRow');
														// alert(element)
														var style = getComputedStyle(element);
														var displaystyle = style.display;

														if (displaystyle === "none") {
															$('.searchRow')
																	.css(
																			'display',
																			'contents');
														} else {
															$('.searchRow')
																	.css(
																			'display',
																			'none');
														}

													},

												}, ]
									});

					table.buttons().container().appendTo(
							$('.col-sm-6:eq(0)', table.table().container()));

				}
			})
}


// End Top Talker Protocol Wise


