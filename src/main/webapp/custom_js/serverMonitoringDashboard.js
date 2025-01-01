window.onload = function() {
	$("#spinner1Onchange").hide();

	getTopTalkerChart();
	getTopTalkerSumOfDeviceDetailsList()

	getTopConnChart();
	getTopConnSumOfDeviceDetailsList();

	getTopProtocolChart();
	getTopProtocolSumOfDeviceDetailsList();

	deliveryOptimizationChart();
	topMemoryUtilization();

}

function topMemoryUtilization() {
    // Sample data
    var sampleData = [
        { device_name: 'OPM-QA3', service_name: 'MySQL', availability: 85 },
        { device_name: 'OPM-QA3', service_name: 'PostgreSQL', availability: 70 },
        { device_name: 'ServerAlpha', service_name: 'Nginx ', availability: 60 },
        { device_name: 'DataCentral', service_name: 'Microsoft ', availability: 85 },
        { device_name: 'CloudMaster', service_name: 'Network Monitoring ', availability: 70 },
        { device_name: 'QuantumServer', service_name: 'Version Control Service ', availability: 60 },
        // Add more sample data points as needed
    ];

    var html = "<tbody>";
    for (var i = 0; i < sampleData.length; i++) {
        html += "<tr>";
        html += "<td>" + (i + 1) + "</td>"; // You can adjust the index if you want to start from 1
        html += "<td>" + sampleData[i].device_name + "</td>";
        html += "<td>" + sampleData[i].service_name + "</td>";

        var availability = sampleData[i].availability;
        if (availability >= 80) {
            html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-danger progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
                + availability
                + "%;color:black;\">"
                + availability
                + "%</div></div></b></td>";
        } else if (availability >= 70 && availability < 80) {
            html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-warning progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
                + availability
                + "%;color:black;\">"
                + availability
                + "%</div></div></b></td>";
        } else if (availability < 70) {
            html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-success progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
                + availability
                + "%;color:black;\">"
                + availability
                + "%</div></div></b></td>";
        }
        html += "</tr>";
    }
    html = html + "</tbody>";
    $('#memoryUtilization').append(html);
}



function getTopTalkerChart() {
	$("#spinnerTopTalkChart").show();

	// Static data
	var staticData = [ {
		name : 'On Maintenance',
		y : 20
	}, {
		name : 'Up',
		y : 30
	}, {
		name : 'Down',
		y : 15
	},
	// Add more data points as needed
	];

	// Rest of your code
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopTalkerChartAllData";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					$("#spinnerTopTalkChart").hide();

					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[2], i;

						for (i = 0; i < 10; i += 1) {
							colors.push(Highcharts.color(base).brighten(
									(i - 3) / 7).get());
						}
						return colors;
					}());

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
											text : '<span style="font-size: 16px;color:blue">Windows Service Availability(Credential Manager)</span>',
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
												colors : ['#bdb9b7','#64e572','#ed561b'],
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
											data : staticData
										} ]
									});
				}
			});
}

function getTopTalkerSumOfDeviceDetailsList() {
	$("#spinner1").show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopTalkerSumOfDeviceListAlldata";

	$.ajax({
		url : serviceUrl,
		type : 'POST',
		dataType : 'json',

		success : function(data) {
			// alert(data)
			$("#spinner1").hide();
			var table = $('#topTalkerSourceIp').DataTable({
				lengthChange : false,
				// pageLength : 5,
				data : data,
				destroy : true,
				scrollX : true,
				scrollY : '350px',
				info : false,
				paging : false,
				orderCellsTop : true,
				ordering : true,
				"order" : [ [ 0, "asc" ] ],
				buttons : [ {
					text : 'Toggle Search',

					action : function() {
						var element = document.querySelector('.searchRow');
						// alert(element)
						var style = getComputedStyle(element);
						var displaystyle = style.display;

						if (displaystyle === "none") {
							$('.searchRow').css('display', 'contents');
						} else {
							$('.searchRow').css('display', 'none');
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

	$.ajax({
		url : serviceUrl,
		type : 'POST',
		dataType : 'json',

		data : 'ToDate=' + toDate + '&FromDate=' + fromDate,
		success : function(data) {
			// alert(data)
			$("#spinner1").hide();
			var table = $('#topTalkerSourceIp').DataTable({
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
				buttons : [ {
					text : 'Toggle Search',

					action : function() {
						var element = document.querySelector('.searchRow');
						// alert(element)
						var style = getComputedStyle(element);
						var displaystyle = style.display;

						if (displaystyle === "none") {
							$('.searchRow').css('display', 'contents');
						} else {
							$('.searchRow').css('display', 'none');
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
	$("#spinnerTopConnChart").show();

	// Static data
	var staticData = [ {
		name : 'On Maintenance',
		y : 0
	}, {
		name : 'Up',
		y : 100
	}, {
		name : 'Down',
		y : 0
	},
	// Add more data points as needed
	];

	// Rest of your code
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopTalkerChartAllData";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					$("#spinnerTopConnChart").hide();

					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[2], i;

						for (i = 0; i < 10; i += 1) {
							colors.push(Highcharts.color(base).brighten(
									(i - 3) / 7).get());
						}
						return colors;
					}());

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
											text : '<span style="font-size: 16px;color:blue">Windows Service Availability(WMI)</span>',
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
												colors : ['#bdb9b7','#64e572','#ed561b'],
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
											data : staticData
										} ]
									});
				}
			});
}

function deliveryOptimizationChart() {
	$("#deliveryOptimization").show();

	// Static data
	var staticData = [ {
		name : 'On Maintenance',
		y : 20
	}, {
		name : 'Up',
		y : 10
	}, {
		name : 'Down',
		y : 60
	},
	// Add more data points as needed
	];

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopTalkerChartAllData";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					$("#spinnerTopConnChart").hide();
					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[2], i;

						for (i = 0; i < 10; i += 1) {
							colors.push(Highcharts.color(base).brighten(
									(i - 3) / 7).get());
						}
						return colors;
					}());

					Highcharts
							.chart(
									'deliveryOptimization',
									{
										chart : {
											plotBackgroundColor : null,
											plotBorderWidth : null,
											plotShadow : false,
											type : 'pie'
										},
										title : {
											text : '<span style="font-size: 16px;color:blue">Windows Service Availability(Delivery Optimization)</span>',
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
												colors : ['#bdb9b7','#64e572','#ed561b'],
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
											data : staticData
										} ]
									});
				}
			});
}

function getTopConnChart1(fromDate, toDate) {
	// alert("getTopConnChart")
	$("#spinnerTopConnChart").show()
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
					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[0], i;

						for (i = 0; i < 10; i += 1) {
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
	var serviceUrl = base_url
			+ "/dashboard/getsumOfDeviceDetailsListFromDateToDate";

	$.ajax({
		url : serviceUrl,
		type : 'POST',
		dataType : 'json',

		data : 'ToDate=' + toDate + '&FromDate=' + fromDate,
		success : function(data) {
			// (data)
			$("#spinner1").hide();
			$("#spinner1Onchange").hide();

			var table = $('#topConnectionSumOfDeviceTable').DataTable({
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
				buttons : [ {
					text : 'Toggle Search',

					action : function() {
						var element = document.querySelector('.searchRow');
						// alert(element)
						var style = getComputedStyle(element);
						var displaystyle = style.display;

						if (displaystyle === "none") {
							$('.searchRow').css('display', 'contents');
						} else {
							$('.searchRow').css('display', 'none');
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

	$.ajax({
		url : serviceUrl,
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			// (data)
			$("#spinner1").hide();
			var table = $('#topConnectionSumOfDeviceTable').DataTable({
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
				buttons : [ {
					text : 'Toggle Search',

					action : function() {
						var element = document.querySelector('.searchRow');
						// alert(element)
						var style = getComputedStyle(element);
						var displaystyle = style.display;

						if (displaystyle === "none") {
							$('.searchRow').css('display', 'contents');
						} else {
							$('.searchRow').css('display', 'none');
						}

					},

				}, ]
			});

			table.buttons().container().appendTo(
					$('.col-sm-6:eq(0)', table.table().container()));

		}
	})
}

// End Top Talker Connection Wise

// Top Talker Protocol Wise
function getTopProtocolChart() {
	$("#spinnerTopProtocolChart").show();

	// Static data
	var staticData = [ {
		name : 'On Maintenance',
		y : 20
	}, {
		name : 'Up',
		y : 80
	}, {
		name : 'Down',
		y : 0
	},
	// Add more data points as needed
	];
	/*
	 * var staticData = [ { name: 'Low Disk Space', y: 10, z: 'Disk Health: Low' }, {
	 * name: 'Moderate Disk Space', y: 30, z: 'Disk Health: Moderate' }, { name:
	 * 'High Disk Space', y: 60, z: 'Disk Health: High' }, // Add more disk
	 * health data points as needed ];
	 */

	// Rest of your code
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopProtocolChartAllData";

	$
			.ajax({
				url : serviceUrl,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					$("#spinnerTopProtocolChart").hide();

					var pieColors = (function() {
						var colors = [], base = Highcharts.getOptions().colors[2], i;

						for (i = 0; i < 10; i += 1) {
							colors.push(Highcharts.color(base).brighten(
									(i - 3) / 7).get());
						}
						return colors;
					}());

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
											text : '<span style="font-size: 16px;color:blue">Windows Service Availability(Windows Event Log)</span>',
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
												colors : ['#bdb9b7','#64e572','#ed561b'],
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
											data : staticData
										} ]
									});
				}
			});
}

function getTopProtocolSumOfDeviceDetailsList() {
	$("#spinnerProtocoloList").show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url
			+ "/dashboard/getTopProtocolSumOfDeviceListAlldata";

	$.ajax({
		url : serviceUrl,
		type : 'POST',
		dataType : 'json',

		success : function(data) {
			// alert(data)
			$("#spinnerProtocoloList").hide();
			var table = $('#topProtocolSumOfDeviceTable').DataTable({
				lengthChange : false,
				// pageLength : 5,
				data : data,
				destroy : true,
				// scrollX : true,
				// scrollY : '150px',
				info : false,
				paging : false,
				searching : true,
				searching : true,
				orderCellsTop : true,
				ordering : true,
				"order" : [ [ 0, "asc" ] ],
				buttons : [ {
					text : 'Toggle Search',

					action : function() {
						var element = document.querySelector('.searchRow');
						// alert(element)
						var style = getComputedStyle(element);
						var displaystyle = style.display;

						if (displaystyle === "none") {
							$('.searchRow').css('display', 'contents');
						} else {
							$('.searchRow').css('display', 'none');
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
	// var d = fromDate;
	// d = d.substr(0, 10).split("-");
	//
	// d = d[2] + "-" + d[1] + "-" + d[0];
	//
	// // d = d[2] + "/" + d[1] + "/" + d[0];
	// var e = toDate;
	// e = e.substr(0, 10).split("-");
	//
	// e = e[2] + "-" + e[1] + "-" + e[0];
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

