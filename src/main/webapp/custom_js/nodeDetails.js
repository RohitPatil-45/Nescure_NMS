window.onload = function() {

	var obj = document.getElementById("deviceIP");
	var ip = obj.value;
	// alert(ip)

	// Interface Summary
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/nodeDashboard/InterfaceStatusHistoryDetails";
	var linkDetailUrl = base_url + "/dashboard/interfaceInfoPage?nodeIP=";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'ip_address=' + ip,
		dataType : 'json',
		success : function(data) {
			var htmldata;
			$.each(data, function(i, item) {
				htmldata = $('<tr>').html(
						"<td>" + data[i].srno + "</td>" + "<td><a href="
								+ linkDetailUrl + ip + "&intName="
								+ encodeURIComponent(data[i].interfaceName)
								+ ">" + data[i].interfaceName + "</a></td>"
								+ "<td>" + data[i].interfaceIP + "</td>"
								+ "<td>" + data[i].ICMPStatus + "</td>"
								// + "<td>" + data[i].oper_status + "</td>"
								+ "<td>" + data[i].aliasName + "</td>")

				$('#interfaceStatusSummary').append(htmldata);

			});
			//
			// var table = $('#interfaceStatusSummary').DataTable({
			// lengthChange : false,
			// autoWidth : false,
			// searching : false,
			// paging : true,
			// info : false,
			// ordering : false,
			// data : htmldata,
			// "pageLength" : 5,
			// scrollX : true,
			// scrollY : true
			// });

		}
	});

	// Node Status Event

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/nodeDashboard/nodeStatusHistoryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'ip_address=' + ip,
		dataType : 'json',
		success : function(data) {
			// alert("nodeStatusHistoryDetails")
			// alert(data)
			var table = $('#nodeStatusSummary').DataTable({
				lengthChange : false,
				autoWidth : false,
				searching : false,
				paging : false,
				info : false,
				ordering : false,

				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true
			});

		}
	});

	// Drive Details
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getDiscoverDriveDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'ip_address=' + ip,
		dataType : 'json',
		success : function(data) {
			// alert("nodeStatusHistoryDetails")
			// alert(data)
			var table = $('#driveDetailsTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				searching : false,
				paging : false,
				info : false,
				ordering : false,

				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true
			});

		}
	});

	// Basic info & RAM Graph & CPU Utilization
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/nodeDashboard/basicInfoDetails";
	$
			.ajax({
				type : 'GET',
				url : serviceUrl,
				data : 'ip_address=' + ip,
				dataType : 'json',
				success : function(data) {
					// alert(data[0]['temperature'])

					document.getElementById('Node_ip').textContent = data[0]['Node_ip'];
					document.getElementById('NodeNAme').textContent = data[0]['NodeNAme'];
					document.getElementById('Location').textContent = data[0]['Location'];
					document.getElementById('Company').textContent = data[0]['Company'];
					document.getElementById('District').textContent = data[0]['District'];
					document.getElementById('status').textContent = data[0]['status'];

					document.getElementById('Version').textContent = data[0]['Version'];
					document.getElementById('Model').textContent = data[0]['Model'];
					// document.getElementById('Uptime').textContent =
					// data[0]['Uptime'];

					// used_ram = data[0]['Used_RAM'];
					// free_ram = data[0]['Free_RAM'];

					document.getElementById('Total_RAM').textContent = data[0]['Total_RAM'];
					document.getElementById('Used_RAM').textContent = data[0]['Used_RAM'];
					document.getElementById('Free_RAM').textContent = data[0]['Free_RAM'];
					const degrees = new Intl.NumberFormat('en-US', {
						style : 'unit',
						unit : 'celsius',
					});
					document.getElementById('deviceTemperature').textContent = degrees
							.format(data[0]['temperature']);

					//
					// // document.getElementById('Customer').textContent =
					// data[0]['Customer'];
					// // document.getElementById('State').textContent =
					// data[0]['State'];
					//					
					// intractiveChart(data[0]['cpuUtilization']);
					/*
					 * DONUT CHART -----------
					 */
					var donutData = [ {
						label : 'Used %',
						data : data[0]['Used_RAM'],
						color : '#DD1C1C'
					}, {
						label : 'Free %',
						data : data[0]['Free_RAM'],
						color : '#52ED15'
					} ]
					$.plot('#donut-chart', donutData, {
						series : {
							pie : {
								show : true,
								radius : 1,
								innerRadius : 0.5,
								label : {
									show : true,
									radius : 2 / 3,
									formatter : labelFormatter,
									threshold : 0.1
								}

							}
						},
						legend : {
							show : false
						}
					})
				}
			});

	// var ip = "192.168.0.230";
	// var used_ram;
	// var free_ram;
	// $.ajax({
	// type: 'POST',
	// url: 'nodeLatencyPacketDrop',
	// dataType: 'json',
	// data: 'ip_address=' + ip,
	// success: function (jsondata) {
	// alert("IP = "+ip+ "\nLatency = "+jsondata.latency+" , Packet Loss =
	// "+jsondata.packetLoss);
	// }
	//
	// });

	// $.ajax({
	// type: 'POST',
	// url: 'nodeAddNode',
	// dataType: 'json',
	// data: 'ip_address=' + ip,
	// success: function (jsondata) {
	// alert(jsondata.allData)
	// }
	//
	// });
	// 
	// $.ajax({
	// type: 'POST',
	// url: 'nodeAvailabilityDetails',
	// dataType: 'json',
	// data: 'ip_address=' + ip,
	// success: function (jsondata) {
	// alert("Uptime = "+jsondata.uptimePercent+ "\ndowntime =
	// "+jsondata.downtimePercent+ "\nevent timestamp =
	// "+jsondata.eventTimestamp)
	// }
	//
	// });

	$(function() {
		/*
		 * Flot Interactive Chart -----------------------
		 */
		// We use an inline data source in the example, usually data would
		// be fetched from a server
		/*
		 * LINE CHART ----------
		 */
		// LINE randomly generated data
		var sin = [], cos = []
		for (var i = 0; i < 14; i += 0.5) {
			sin.push([ i, Math.sin(i) ])
			cos.push([ i, Math.cos(i) ])
		}
		var line_data1 = {
			data : sin,
			color : '#3c8dbc'
		}
		var line_data2 = {
			data : cos,
			color : '#00c0ef'
		}
		$.plot('#line-chart', [ line_data1, line_data2 ], {
			grid : {
				hoverable : true,
				borderColor : '#f3f3f3',
				borderWidth : 1,
				tickColor : '#f3f3f3'
			},
			series : {
				shadowSize : 0,
				lines : {
					show : true
				},
				points : {
					show : true
				}
			},
			lines : {
				fill : false,
				color : [ '#3c8dbc', '#f56954' ]
			},
			yaxis : {
				show : true
			},
			xaxis : {
				show : true
			}
		})
		// Initialize tooltip on hover
		$('<div class="tooltip-inner" id="line-chart-tooltip"></div>').css({
			position : 'absolute',
			display : 'none',
			opacity : 0.8
		}).appendTo('body')
		$('#line-chart')
				.bind(
						'plothover',
						function(event, pos, item) {

							if (item) {
								var x = item.datapoint[0].toFixed(2), y = item.datapoint[1]
										.toFixed(2)

								$('#line-chart-tooltip').html(
										item.series.label + ' of ' + x + ' = '
												+ y).css({
									top : item.pageY + 5,
									left : item.pageX + 5
								}).fadeIn(200)
							} else {
								$('#line-chart-tooltip').hide()
							}

						})
		/* END LINE CHART */

		/*
		 * FULL WIDTH STATIC AREA CHART -----------------
		 */
		var areaData = [ [ 2, 88.0 ], [ 3, 93.3 ], [ 4, 102.0 ], [ 5, 108.5 ],
				[ 6, 115.7 ], [ 7, 115.6 ], [ 8, 124.6 ], [ 9, 130.3 ],
				[ 10, 134.3 ], [ 11, 141.4 ], [ 12, 146.5 ], [ 13, 151.7 ],
				[ 14, 159.9 ], [ 15, 165.4 ], [ 16, 167.8 ], [ 17, 168.7 ],
				[ 18, 169.5 ], [ 19, 168.0 ] ]
		$.plot('#area-chart', [ areaData ], {
			grid : {
				borderWidth : 0
			},
			series : {
				shadowSize : 0, // Drawing is faster without shadows
				color : '#00c0ef',
				lines : {
					fill : true
				// Converts the line chart to area chart
				},
			},
			yaxis : {
				show : false
			},
			xaxis : {
				show : false
			}
		})

		/* END AREA CHART */

		/*
		 * BAR CHART ---------
		 */

		var bar_data = {
			data : [ [ 1, 85 ], [ 2, 50 ], [ 3, 30 ], [ 4, 70 ], [ 5, 80 ],
					[ 6, 20 ] ],
			bars : {
				show : true
			}
		}
		$.plot('#bar-chart', [ bar_data ], {
			grid : {
				borderWidth : 1,
				borderColor : '#f3f3f3',
				tickColor : '#f3f3f3'
			},
			series : {
				bars : {
					show : true,
					barWidth : 0.5,
					align : 'center',
				},
			},
			colors : [ '#3c8dbc' ],
			xaxis : {
				ticks : [ [ 1, '18-03-22' ], [ 2, '17-03-22' ],
						[ 3, '16-03-22' ], [ 4, '15-03-22' ],
						[ 5, '14-03-22' ], [ 6, '13-03-22' ] ]
			}
		})
		/* END BAR CHART */

		/*
		 * DONUT CHART -----------
		 */

		var donutData = [ {
			label : 'Used %',
			data : used_ram,
			color : '#DD1C1C'
		},

		{
			label : 'Free %',
			data : free_ram,
			color : '#52ED15'
		} ]
		$.plot('#donut-chart', donutData, {
			series : {
				pie : {
					show : true,
					radius : 1,
					innerRadius : 0.5,
					label : {
						show : true,
						radius : 2 / 3,
						formatter : labelFormatter,
						threshold : 0.1
					}

				}
			},
			legend : {
				show : false
			}
		})
		/*
		 * END DONUT CHART
		 */

	})

	// $.ajax({
	// type : 'get',
	// dataType : 'json',
	// url: 'nodeStatusHistoryDetails',
	// data: 'ip_address=' + ip,
	// success: function (jsondata) {
	// alert("nodeStatusHistoryDetails")
	// alert(jsondata)
	// //alert("Node Status = "+jsondata.nodeStatus+ "\nEvent Timestamp =
	// "+jsondata.eventTimestamp)
	//    	 
	//    	 
	// var table = $('#nodeStatusSummary')
	// .DataTable(
	// {
	// data : jsondata
	//					
	//
	//
	// });
	//    	 
	// }
	//
	// });

	// $.ajax({
	// type: 'POST',
	// url: 'nodeHealthMonitoringDetails',
	// dataType: 'json',
	// data: 'ip_address=' + ip,
	// success: function (jsondata) {
	// alert("IP = "+ip+"\n"+jsondata.allData)
	// }
	//
	// });

	// Node Connected devices
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/nodeReport/connectedDevicesforIP";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'ip_address=' + ip,
		dataType : 'json',
		success : function(data) {
			var table = $('#ConnectedDevices').DataTable({
				lengthChange : false,
				autoWidth : false,
				paging : true,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true
			});

		}
	});

};

// Report Date range as a button
$('#daterange-btn').daterangepicker(
		// alert("in datepicker hiiii"),
		{
			timePicker : true,
			timePickerIncrement : 10,
			ranges : {
				'Today' : [ moment(), moment() ],
				'Yesterday' : [ moment().subtract(1, 'days'),
						moment().subtract(1, 'days') ],
				'Last 7 Days' : [ moment().subtract(6, 'days'), moment() ],
				'Last 30 Days' : [ moment().subtract(29, 'days'), moment() ],
				'This Month' : [ moment().startOf('month'),
						moment().endOf('month') ],
				'Last Month' : [
						moment().subtract(1, 'month').startOf('month'),
						moment().subtract(1, 'month').endOf('month') ]
			},
			startDate : moment().subtract(29, 'days'),
			endDate : moment()
		}, function(start, end) {
			var from_date = document.getElementById("from_date");
			from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
			var to_date = document.getElementById("to_date");
			to_date.value = end.format('YYYY-MM-DD HH:mm:ss');

		})

$('#reservationtime').daterangepicker({
	timePicker : true,
	timePickerIncrement : 10,
	locale : {
		format : 'MM/DD/YYYY hh:mm:ss'
	}

}, function(start, end) {

	var from_date = document.getElementById("from_date");
	from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
	var to_date = document.getElementById("to_date");
	to_date.value = end.format('YYYY-MM-DD HH:mm:ss');
});

$(function() {

	$('[data-mask]').inputmask()
	// Bootstrap Duallistbox

})

// CPU Utilization
function intractiveChart(value) {
	// alert(value)
	// var l = window.location;
	// var base_url = l.protocol + "//" + l.host + "/" +
	// l.pathname.split('/')[1];
	// var serviceUrl = base_url + "/nodeDashboard/nodeStatusHistoryDetails";
	// $
	// .ajax({
	// type : 'GET',
	// url : serviceUrl,
	// data: 'ip_address=' + ip,
	// dataType : 'json',
	// success : function(data) {
	// var data = [], totalPoints = value

	var data = [], totalPoints = value

	function getRandomData() {

		if (data.length > 0) {
			data = data.slice(1)
		}

		// Do a random walk
		while (data.length < totalPoints) {

			var prev = data.length > 0 ? data[data.length - 1] : 10, y = prev
					+ Math.random() * 10 - 5

			if (y < 0) {
				y = 0
			} else if (y > 100) {
				y = 100
			}

			data.push(y)
		}

		// Zip the generated y values with the x values
		var res = []
		for (var i = 0; i < data.length; ++i) {
			res.push([ i, data[i] ])
		}

		return res
	}

	var interactive_plot = $.plot('#interactive', [ {
		data : getRandomData(),
	} ], {
		grid : {
			borderColor : '#f3f3f3',
			borderWidth : 1,
			tickColor : '#f3f3f3'
		},
		series : {
			color : '#3c8dbc',
			lines : {
				lineWidth : 2,
				show : true,
				fill : true,
			},
		},
		yaxis : {
			min : 0,
			max : 100,
			show : true
		},
		xaxis : {
			show : true
		}
	})

	var updateInterval = 500 // Fetch data ever x milliseconds
	var realtime = 'on' // If == to on then fetch data every x seconds. else
	// stop fetching
	function update() {

		interactive_plot.setData([ getRandomData() ])

		// Since the axes don't change, we don't need to call plot.setupGrid()
		// interactive_plot.draw()
		if (realtime === 'on') {
			setTimeout(update, updateInterval)
		}
	}

	// INITIALIZE REALTIME DATA FETCHING
	if (realtime === 'on') {
		update()
	}
	// REALTIME TOGGLE
	$('#realtime .btn').click(function() {
		if ($(this).data('toggle') === 'on') {
			realtime = 'on'
		} else {
			realtime = 'off'
		}
		update()
	})
	/*
	 * END INTERACTIVE CHART
	 */
	// }
	// })
}

/*
 * $('#exportLatency').click(function(){ alert("Hello"); var l =
 * window.location; var base_url = l.protocol + "//" + l.host + "/" +
 * l.pathname.split('/')[1]; var serviceUrl = base_url +
 * "/nodeReport/exportNodeLatency"; $.ajax({ type : 'GET', url : serviceUrl,
 * data : 'ip_address=' + ip +'&fromDate='+$('#from_date').val()
 * +'&toDate='+$('#to_date').val(),
 * 
 * dataType : 'json', success : function(data) {
 * //alert("nodeStatusHistoryDetails") //alert(data) var table =
 * $('#exportNodeLatency').DataTable({ lengthChange : false, autoWidth : false,
 * searching : true, // paging : false, info : false, ordering : false,
 * 
 * data : data, "pageLength" : 5, scrollX : true, scrollY : true }); } });
 * 
 * 
 * 
 * });
 */
$(document).ready(function() {
	$("#generate").on("click", function() {
		$("#dateModal").modal("show");
	});
});

$(function() {
	$('#graph').hide();
	$('#graphButton').hide();
	$('#reportGraph').change(function() {
		if ($('#reportGraph').val() == 'graph') {
			$('#graph').show();
			$('#graphButton').show();
			$('#report').hide();
			$('#reportButton').hide();
		} else {
			$('#graph').hide();
			$('#graphButton').hide();
			$('#report').show();
			$('#reportButton').show();
		}
	});
});
$(document).ready(function() {
	$("#exportNodeReportGraph").validate({
		rules : {
			from_date : {
				required : true
			},
			to_date : {
				required : true
			},
		},
		messages : {
			from_date : {
				required : "Please provide From_date"
			},
			to_date : {
				required : "Please provide To_date"
			},
		},
		errorElement : "span",
		errorPlacement : function(error, element) {
			error.addClass("invalid-feedback");
			element.closest(".form-group").append(error);
		},
		highlight : function(element, errorClass, validClass) {
			$(element).addClass("is-invalid");
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).removeClass("is-invalid");
		},
	});
});
$("#exportReportLatency")
		.click(
				function() {
					if ($("#exportNodeReportGraph").valid()) {
						var obj = document.getElementById("deviceIP");
						var ip = obj.value;
						var l = window.location;
						var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;

						var showLatencyReport = `${base_url}/nodeReport/showLatencyReport`;
						var showCPUReport = `${base_url}/nodeReport/showCPUReport`;
						var showMemoryReport = `${base_url}/nodeReport/showMemoryReport`;
						var showNodeStatusReport = `${base_url}/nodeReport/showNodeStatusReport`;
						var showAvailabilityReport = `${base_url}/nodeReport/showAvailabilityReport`;
						var showCPUThreshold = `${base_url}/nodeReport/showCPUThreshold`;
						var showMemoryThreshold = `${base_url}/nodeReport/showMemoryThreshold`;
						var showLatencyThreshold = `${base_url}/nodeReport/showLatencyThreshold`;
						var targetUrl;
						var radioValue = $("input[name='report']:checked")
								.val();

						switch (radioValue) {
						case 'latency':
							targetUrl = showLatencyReport;
							break;
						case 'cpu':
							targetUrl = showCPUReport;
							break;
						case 'memory':
							targetUrl = showMemoryReport;
							break;
						case 'nodeStatus':
							targetUrl = showNodeStatusReport;
							break;
						case 'availability':
							targetUrl = showAvailabilityReport;
							break;
						case 'cpuThreshold':
							targetUrl = showCPUThreshold;
							break;
						case 'memoryThreshold':
							targetUrl = showMemoryThreshold;
							break;
						case 'latencyThreshold':
							targetUrl = showLatencyThreshold;
							break;
						default:
							Swal
									.fire({
										position : "top",
										icon : "warning",
										title : "Please select at least one Radio Button",
										showConfirmButton : false,
										timer : 3000,
									});
							return;
						}
						document.getElementById('exportNodeReportGraph').action = targetUrl;
						document.getElementById('exportNodeReportGraph')
								.submit();
					}
				});

$("#exportGraphLatency").click(function() {
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;

	var showLatencyGraph = `${base_url}/nodeReport/showLatencyGraph`;
	var showCPUGraph = `${base_url}/nodeReport/showCPUGraph`;
	var showMemoryGraph = `${base_url}/nodeReport/showMemoryGraph`;
	var showAvailabilityGraph = `${base_url}/nodeReport/showAvailabilityGraph`;
	var targetUrl;
	var radioValue = $("input[name='graph']:checked").val();

	switch (radioValue) {
	case 'latencyGraph':
		targetUrl = showLatencyGraph;
		break;
	case 'cpuGraph':
		targetUrl = showCPUGraph;
		break;
	case 'memoryGraph':
		targetUrl = showMemoryGraph;
		break;
	case 'availabilityGraph':
		targetUrl = showAvailabilityGraph;
		break;
	default:
		Swal.fire({
			position : "top",
			icon : "warning",
			title : "Please select at least one Radio Button",
			showConfirmButton : false,
			timer : 3000,
		});
		return;
	}
	document.getElementById('exportNodeReportGraph').action = targetUrl;
	document.getElementById('exportNodeReportGraph').submit();
});
/*-----------------------------------Start Graph----------------------------------*/

/*-----------------------------------End Graph------------------------------------*/
