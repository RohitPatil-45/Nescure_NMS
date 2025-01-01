window.onload = function() {
	topMemoryUtilization();
	topCpuUtilization();
	topBWUtilizationIN();
	getBWOutUtilization();
	topDiskUtilization();
	topLatency();
}

function topMemoryUtilization() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopMemoryUtilization";
	var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";

	$
		.ajax({
			url: serviceUrl,
			type: 'post',
			dataType: 'json',
			success: function(data) {
				// alert(data)
				var html = "";
				html += "<tbody>";
				for (var i = 0; i < data.length; i++) {
					html += "<tr>"
					html += "<td >" + data[i].sr_no + "</td>"
					html += "<td ><a href="
						+ nodeDetailUrl + data[i].pc_ip + ">"
						+ data[i].pc_ip + "</a></td>"
					html += "<td >" + data[i].pc_name + "</td>"

					if (data[i].memory >= 80) {
						html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-danger progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
							+ data[i].memory
							+ "%\;color:black;\">"
							+ data[i].memory + "%</div></div></b></td>"
					} else if (data[i].memory >= 70 && data[i].memory < 80) {
						html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-warning progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
							+ data[i].memory
							+ "%\;color:black;\">"
							+ data[i].memory + "%</div></div></b></td>"
					} else if (data[i].memory < 70) {
						html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-success progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
							+ data[i].memory
							+ "%\;color:black;\">"
							+ data[i].memory + "%</div></div></b></td>"
					}
					/* html += "<td >" + data[i].pc_branch + "</td>" */
					html += "</tr>";
					tr = $("<tr></tr>");
					tr.append("<td>" + data[i].pc_name + "</td>");
					tr.append("<td>" + data[i].pc_ip + "</td>");
					/* tr.append("<td>" + data[i].pc_branch + "</td>"); */

				}
				html = html + "</tbody>"
				$('#memoryUtilization').append(html);
			}
		});
}

function topCpuUtilization() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopCpuUtilization";
	var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";

	$
		.ajax({
			url: serviceUrl,
			type: 'post',
			dataType: 'json',
			success: function(data) {
				// alert(data)
				var html = "";
				html += "<tbody>";
				for (var i = 0; i < data.length; i++) {
					html += "<tr>"
					html += "<td >" + data[i].sr_no + "</td>"
					html += "<td ><a href="
						+ nodeDetailUrl + data[i].pc_ip + ">"
						+ data[i].pc_ip + "</a></td>"
					html += "<td >" + data[i].pc_name + "</td>"

					if (data[i].cpu >= 80) {
						html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-danger progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
							+ data[i].cpu
							+ "%\;color:black;\">"
							+ data[i].cpu + "%</div></div></b></td>"
					} else if (data[i].cpu >= 70 && data[i].cpu < 80) {
						html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-warning progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
							+ data[i].cpu
							+ "%\;color:black;\">"
							+ data[i].cpu + "%</div></div></b></td>"
					} else if (data[i].cpu < 70) {
						html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-success progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
							+ data[i].cpu
							+ "%\;color:black;\">"
							+ data[i].cpu + "%</div></div></b></td>"
					}
					/* html += "<td >" + data[i].pc_branch + "</td>" */
					html += "</tr>";
					tr = $("<tr></tr>");
					tr.append("<td>" + data[i].pc_name + "</td>");
					tr.append("<td>" + data[i].pc_ip + "</td>");
					/* tr.append("<td>" + data[i].pc_branch + "</td>"); */

				}
				html = html + "</tbody>"
				$('#cpuUtilization').append(html);
			}
		});
}

function topBWUtilizationIN() {
	// //alert("topBWUtilizationIN ")
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getBwInUtilization";

	$.ajax({
		url: serviceUrl,
		type: 'post',
		dataType: 'json',
		success: function(data) {
			var count = new Array();

			count = data.count;
			// alert(count)
			var numberArray = count.map(Number)
			// alert(numberArray)
			var chart = new Highcharts.Chart({
				chart: {
					renderTo: 'bwInContainer',
					type: 'column',
					options3d: {
						enabled: true,
						alpha: 0,
						beta: -12,
						depth: 100,
					}
				},
				title: {
					text: ''
				},
				subtitle: {
					text: ''
				},
				plotOptions: {
					column: {
						depth: 45
					},
					series: {
						cursor: 'pointer',
						point: {
							events: {
								click: function() {
									topBWINModal(this.category);
								}
							}
						}
					}
				},
				colors: ['#44a9a8'],
				xAxis: {
					categories: data.ip

					// categories: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6,
					// 148.5, 216.4, 194.1, 95.6, 54.4]
					// data.ip
					// categories: ip
				},
				yAxis: {
					title: {
						text: 'bytes',
					}
				},
				series: [{
					name: 'BW',
					showInLegend: false,
					data: numberArray
				}]
			});

		}
	});
}

function getBWOutUtilization() {
	// //alert("topBWUtilizationIN ")
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getBWOutUtilization";

	$.ajax({
		url: serviceUrl,
		type: 'post',
		dataType: 'json',
		success: function(data) {
			var count = new Array();

			count = data.count;
			// alert(count)
			var numberArray = count.map(Number)
			// alert(numberArray)
			var chart = new Highcharts.Chart({
				chart: {
					renderTo: 'bwOutContainer',
					type: 'column',
					options3d: {
						enabled: true,
						alpha: 0,
						beta: -12,
						depth: 100,
					}
				},
				title: {
					text: ''
				},
				subtitle: {
					text: ''
				},
				plotOptions: {
					column: {
						depth: 45
					},
					series: {
						cursor: 'pointer',
						point: {
							events: {
								click: function() {
									topBWINModal(this.category);
								}
							}
						}
					}
				},
				colors: ['#44a9a8'],
				xAxis: {
					categories: data.ip

					// categories: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6,
					// 148.5, 216.4, 194.1, 95.6, 54.4]
					// data.ip
					// categories: ip
				},
				yAxis: {
					title: {
						text: 'bytes',
					}
				},
				series: [{
					name: 'BW',
					showInLegend: false,
					data: numberArray
				}]
			});

		}
	});
}

function topLatency() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopLatencyUtilization";

	$.ajax({
		url: serviceUrl,
		type: 'post',
		dataType: 'json',
		success: function(data) {
			var count = new Array();

			count = data.count;
			// //alert(count)
			var numberArray = count.map(Number)
			// //alert(numberArray)
			var chart = new Highcharts.Chart({
				chart: {
					renderTo: 'latencyContainer',
					type: 'column',
					options3d: {
						enabled: true,
						alpha: 0,
						beta: -12,
						depth: 100,
					}
				},
				title: {
					text: ''
				},
				subtitle: {
					text: ''
				},
				plotOptions: {
					column: {
						depth: 45
					},
					series: {
						cursor: 'pointer',
						point: {
							events: {
								click: function() {
									topLatencyModal(this.category);
								}
							}
						}
					}
				},
				// colors: ['#5c5c61'],
				xAxis: {
					categories: data.ip

				},
				yAxis: {
					title: {
						text: 'ms',
					}
				},
				series: [{
					name: 'Latency',
					showInLegend: false,
					data: numberArray,
					colorByPoint: true
				}]
			});

		}
	});
}

function topDiskUtilization() {
	var staticData = [{
		device_name: 'ServerAlpha',
		volume: 'C:',
		utilization: 85
	}, {
		device_name: 'ServerAlpha',
		volume: 'D:',
		utilization: 75
	}, {
		device_name: 'ServerAlpha',
		volume: 'E:',
		utilization: 60
	}, {
		device_name: 'DataCentral',
		volume: 'C:',
		utilization: 85
	}, {
		device_name: 'DataCentral',
		volume: 'D:',
		utilization: 75
	}, {
		device_name: 'DataCentral',
		volume: 'E:',
		utilization: 60
	}, {
		device_name: 'HyperCore',
		volume: 'C:',
		utilization: 85
	}, {
		device_name: 'HyperCore',
		volume: 'D:',
		utilization: 75
	}, {
		device_name: 'HyperCore',
		volume: 'E:',
		utilization: 60
	}, {
		device_name: 'NebulaNode',
		volume: 'C:',
		utilization: 85
	}, {
		device_name: 'NebulaNode',
		volume: 'D:',
		utilization: 75
	}, {
		device_name: 'NebulaNode',
		volume: 'E:',
		utilization: 60
	},
		// Add more static data points as needed
	];

	var html = "<tbody>";
	for (var i = 0; i < staticData.length; i++) {
		html += "<tr>";
		html += "<td>" + (i + 1) + "</td>"; // You can adjust the index if you
		// want to start from 1
		html += "<td>" + staticData[i].device_name + "</td>";
		html += "<td>" + staticData[i].volume + "</td>";

		var utilization = staticData[i].utilization;
		if (utilization >= 80) {
			html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-danger progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
				+ utilization
				+ "%;color:black;\">"
				+ utilization
				+ "%</div></div></b></td>";
		} else if (utilization >= 70 && utilization < 80) {
			html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-warning progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
				+ utilization
				+ "%;color:black;\">"
				+ utilization
				+ "%</div></div></b></td>";
		} else if (utilization < 70) {
			html += "<td> <b><div class=\"progress\"> <div class=\"progress-bar progress-bar-success progress-bar-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
				+ utilization
				+ "%;color:black;\">"
				+ utilization
				+ "%</div></div></b></td>";
		}
		html += "</tr>";
	}
	html = html + "</tbody>";
	$('#diskUtilization').append(html);
}
