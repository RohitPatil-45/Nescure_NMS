var devicechart = "";
var linkpie = "";

function logoutsaveonclick() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/login/logoutstore";
	$.ajax({
		url : serviceUrl,
		type : 'POST',
		data : "username=" + $('#sessionUserstore').text(),

	}).done(function(data) {

	});
	// var landingurl = $('#ContextPathId').val();
	// window.location.href = landingurl;

}

window.onload = function() {

	allCount();
	setInterval(allCount, 20000);

	// Node Composition

	// var pieCanvas = document.getElementById("devicepieChart");
	// pieCanvas.onclick = function(evt) {
	// var activePoints = devicechart.getElementsAtEvent(evt);
	// // alert(activePoints)
	// if (activePoints[0]) {
	// var chartData = activePoints[0]['_chart'].config.data;
	// var idx = activePoints[0]['_index'];
	//
	// var status = chartData.labels[idx];
	// var value = chartData.datasets[0].data[idx];
	// getNodeInfo(status)
	//
	// }
	// }

	// Link Composition
	// var linkpieChart = document.getElementById("linkpieChart");
	// linkpieChart.onclick = function(evt) {
	// var activePoints = linkpie.getElementsAtEvent(evt);
	// // alert(activePoints)
	// if (activePoints[0]) {
	// var chartData = activePoints[0]['_chart'].config.data;
	// var idx = activePoints[0]['_index'];
	//
	// var status = chartData.labels[idx];
	// var value = chartData.datasets[0].data[idx];
	// getLinkInfo(status)
	//
	// }
	// }

	// Device Down Summary Listing

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/deviceSummaryList";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			// alert(data);

			var table = $('#deviceSummary').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true,
				scrollY : '150px'
			});

		}
	});

	// Link Up Down Count

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/linkSummaryList";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			// alert(data);

			var table = $('#linkSummary').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 10,
				scrollX : true,
				scrollY : true,
				scrollY : '150px'
			});

		}
	});
}

// Get Node Details on Model
function getNodeInfo(NodeStatus) {
	// alert("hhii");
	// $('#deviceInfoTable td').remove();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getDeviceInfo";
	var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : {
			deviceInfo : NodeStatus
		},
		dataType : 'json',
		success : function(data) {

			var infoModal = $('#device-info-modal');
			infoModal.modal('show');

			// Destroy the existing DataTable instance if it exists
			if ($.fn.DataTable.isDataTable('#deviceInfoTable')) {
				$('#deviceInfoTable').DataTable().clear().destroy();
			}

			// Clear the table body
			$('#deviceInfoTable tbody').empty();

			var htmldata;
			$.each(data, function(i, item) {
				htmldata = $('<tr>').html(
						"<td>" + data[i].srno + "</td>" + "<td><a href="
								+ nodeDetailUrl + data[i].deviceip + ">"
								+ data[i].deviceip + "</a></td>" + "<td>"
								+ data[i].devicename + "</td>" + "<td>"
								+ data[i].InterfaceName + "</td>" + "<td>"
								+ data[i].location + "</td>" + "<td>"
								+ data[i].company + "</td>");
				$('#deviceInfoTable tbody').append(htmldata);
			});

			var dataTable2 = $('#deviceInfoTable').DataTable({
				autoWidth : false,
				// pageLength: 10,
				buttons : [ "copy", "csv", "excel", "print" ],
				deferRender : true, // Recommended for scroller for large
				// datasets
				"paging" : false,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 500, // Set the height of the scrollable area
				scroller : true
			// Enable Scroller extension
			});

			// Append the buttons container to the desired location
			dataTable2.buttons().container().appendTo(
					'#deviceInfoTable_wrapper .col-md-6:eq(0)');

			setTimeout(function() {

				dataTable2.columns.adjust().draw();
			}, 500);

		}

	});

}

function allCount() {
	deviceUpDownCount();

	// interfaceUpDownCount();

	// alertCount();

	groupWiseSummary();

	// interfaceSummary();

	interfaceSummaryGroupWise();

	// deviceTypeSummary();
}

// Get Link Details on Model
function getLinkInfo(LinkStatus) {

	$('#linkInfoTable td').remove();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getLinkInfo";
	var linkDetailUrl = base_url + "/dashboard/interfaceInfoPage?nodeIP=";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : {
			linkInfo : LinkStatus
		},
		dataType : 'json',
		success : function(data) {
			var infoModal = $('#link-info-modal');
			infoModal.modal('show');

			// Destroy the existing DataTable instance if it exists
			if ($.fn.DataTable.isDataTable('#linkInfoTable')) {
				$('#linkInfoTable').DataTable().clear().destroy();
			}

			// Clear the table body
			$('#linkInfoTable tbody').empty();

			var htmldata;
			$.each(data, function(i, item) {
				htmldata = $('<tr>').html(
						"<td>" + data[i].srno + "</td>" + "<td>"
								+ data[i].nodeip + "</td>" + "<td><a href="
								+ linkDetailUrl + data[i].nodeip + "&intName="
								+ encodeURIComponent(data[i].interfacename)
								+ ">" + data[i].interfacename + "</a></td>"
								+ "<td>" + data[i].location + "</td>" + "<td>"
								+ data[i].company + "</td>");
				$('#linkInfoTable tbody').append(htmldata);
			});

			var dataTable = $('#linkInfoTable').DataTable({
				autoWidth : false,
				pageLength : 10,
				buttons : [ "copy", "csv", "excel", "print" ],
			});

			// Append the buttons container to the desired location
			dataTable.buttons().container().appendTo(
					'#linkInfoTable_wrapper .col-md-6:eq(0)');

		}
	});
}

// Alert Info
function getAlertInfo() {

	// Latency threshold
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getAlertInfo";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			$('#up_tree').jstree({
				"core" : {
					'data' : data

				},
				"plugins" : [ "dnd", "search", "sort" ],
				"search" : {
					"case_sensitive" : false,
					"show_only_matches" : true,
				},
				"default" : {
					"valid_children" : [ "default", "file" ]
				}

			})
		}
	});
	// End Latency Threshold

	// CPU threshold crossed
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getCpuAlertInfo";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			$('#cpu_tree').jstree({
				"core" : {
					'data' : data

				},
				"plugins" : [ "dnd", "search", "sort" ],
				"search" : {
					"case_sensitive" : false,
					"show_only_matches" : true,
				},
				"default" : {
					"valid_children" : [ "default", "file" ]
				}

			})
		}
	});
	// End CPU threshold crossed

	// Memory Threshold crossed
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getMemoryAlertInfo";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			$('#memory_tree').jstree({
				"core" : {
					'data' : data

				},
				"plugins" : [ "dnd", "search", "sort" ],
				"search" : {
					"case_sensitive" : false,
					"show_only_matches" : true,
				},
				"default" : {
					"valid_children" : [ "default", "file" ]
				}

			})
		}
	});
	// End Memory Threshold Crossed

	// Drive Threshold crossed
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getDriveAlertInfo";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			$('#drive_tree').jstree({
				"core" : {
					'data' : data

				},
				"plugins" : [ "dnd", "search", "sort" ],
				"search" : {
					"case_sensitive" : false,
					"show_only_matches" : true,
				},
				"default" : {
					"valid_children" : [ "default", "file" ]
				}

			})
		}
	});
	// End Drive Threshold Crossed

	// Bandwidth Threshold Crossed
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getBandwidthAlertInfo";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			$('#bandwidth_tree').jstree({
				"core" : {
					'data' : data

				},
				"plugins" : [ "dnd", "search", "sort" ],
				"search" : {
					"case_sensitive" : false,
					"show_only_matches" : true,
				},
				"default" : {
					"valid_children" : [ "default", "file" ]
				}

			})
		}
	});
	// End Bandwidth Threshold Crossed

	// Topology Changed
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTopologyAlertInfo";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			$('#topology_tree').jstree({
				"core" : {
					'data' : data

				},
				"plugins" : [ "dnd", "search", "sort" ],
				"search" : {
					"case_sensitive" : false,
					"show_only_matches" : true,
				},
				"default" : {
					"valid_children" : [ "default", "file" ]
				}

			})
		}
	});
	// End Topology Changed

}
// Group wise And Interface Wise
function getTotalNodeSummaryDetails(group_name) {
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getTotalNodeSummaryDetails";
	var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";
	$.ajax({
		type : 'GET',
		data : 'group_name=' + group_name,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			$('#portSummaryTable tbody').empty();

			if ($.fn.DataTable.isDataTable('#portSummaryTable')) {
				$('#portSummaryTable').DataTable().clear().destroy();
			}

			// Clear the table body
			$('#portSummaryTable tbody').empty();

			var htmldata;
			$.each(data, function(i, item) {
				htmldata = $('<tr>').html(
						"<td><a href=" + nodeDetailUrl + data[i][0] + ">"
								+ data[i][0] + "</a></td>" + "<td>"
								+ data[i][1] + "</td>" + "<td>" + data[i][2]
								+ "</td>" + "<td>" + data[i][3] + "</td>"
								+ "<td>" + data[i][4] + "</td>" + "<td>"
								+ data[i][5] + "</td>" + "<td>" + data[i][6]
								+ "</td>");
				$('#portSummaryTable tbody').append(htmldata);
			});

			var table = $('#portSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				// data : data,
				"paging" : false,
				// "pageLength" : 5,
				// scrollX : true,
				// scrollY : true,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 500, // Set the height of the scrollable area
				"sScrollX" : "100%",
				"scrollCollapse" : true,
				scroller : true
			// Enable Scroller extension
			});

			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);

		}
	});
}
function getupNodeSummaryDetails(group_name) {
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getupNodeSummaryDetails";
	var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";
	$.ajax({
		type : 'GET',
		data : 'group_name=' + group_name,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {

			if ($.fn.DataTable.isDataTable('#portSummaryTable')) {
				$('#portSummaryTable').DataTable().clear().destroy();
			}

			// Clear the table body
			$('#portSummaryTable tbody').empty();

			var htmldata;
			$.each(data, function(i, item) {
				htmldata = $('<tr>').html(
						"<td><a href=" + nodeDetailUrl + data[i][0] + ">"
								+ data[i][0] + "</a></td>" + "<td>"
								+ data[i][1] + "</td>" + "<td>" + data[i][2]
								+ "</td>" + "<td>" + data[i][3] + "</td>"
								+ "<td>" + data[i][4] + "</td>" + "<td>"
								+ data[i][5] + "</td>" + "<td>" + data[i][6]
								+ "</td>");
				$('#portSummaryTable tbody').append(htmldata);
			});

			var table = $('#portSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				// data : data,
				// "pageLength" : 5,
				"paging" : false,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 500,// Set the height of the scrollable area
				scroller : true,
				"sScrollX" : "100%",
				"scrollCollapse" : true,
				destroy : true,
			});

			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);

		}
	});
}
function getdownNodeSummaryDetails(group_name) {
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getdownNodeSummaryDetails";
	var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'group_name=' + group_name,
		dataType : 'json',
		success : function(data) {

			if ($.fn.DataTable.isDataTable('#portSummaryTable')) {
				$('#portSummaryTable').DataTable().clear().destroy();
			}

			// Clear the table body
			$('#portSummaryTable tbody').empty();

			var htmldata;
			$.each(data, function(i, item) {
				htmldata = $('<tr>').html(
						"<td><a href=" + nodeDetailUrl + data[i][0] + ">"
								+ data[i][0] + "</a></td>" + "<td>"
								+ data[i][1] + "</td>" + "<td>" + data[i][2]
								+ "</td>" + "<td>" + data[i][3] + "</td>"
								+ "<td>" + data[i][4] + "</td>" + "<td>"
								+ data[i][5] + "</td>" + "<td>" + data[i][6]
								+ "</td>");
				$('#portSummaryTable tbody').append(htmldata);
			});

			var table = $('#portSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				// data : data,
				// "pageLength" : 5,
				"paging" : false,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 500, // Set the height of the scrollable area
				scroller : true,
				"sScrollX" : "100%",
				"scrollCollapse" : true,
				destroy : true,
			});

			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);

		}
	});
}
function getwarningNodeSummaryDetails(group_name) {
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getwarningNodeSummaryDetails";
	var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'group_name=' + group_name,
		dataType : 'json',
		success : function(data) {

			if ($.fn.DataTable.isDataTable('#portSummaryTable')) {
				$('#portSummaryTable').DataTable().clear().destroy();
			}

			// Clear the table body
			$('#portSummaryTable tbody').empty();

			var htmldata;
			$.each(data, function(i, item) {
				htmldata = $('<tr>').html(
						"<td><a href=" + nodeDetailUrl + data[i][0] + ">"
								+ data[i][0] + "</a></td>" + "<td>"
								+ data[i][1] + "</td>" + "<td>" + data[i][2]
								+ "</td>" + "<td>" + data[i][3] + "</td>"
								+ "<td>" + data[i][4] + "</td>" + "<td>"
								+ data[i][5] + "</td>" + "<td>" + data[i][6]
								+ "</td>");
				$('#portSummaryTable tbody').append(htmldata);
			});

			var table = $('#portSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				// data : data,
				// "pageLength" : 5,
				"paging" : false,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 500, // Set the height of the scrollable area
				scroller : true,
				destroy : true,
				"sScrollX" : "100%",
				"scrollCollapse" : true,
			});
			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);

		}
	});
}
// Interface
function getTotalInterfaceSummaryDetails(group_name) {
	$('#interface-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url
			+ "/interfaceReport/getTotalInterfaceSummaryDetails";
	$.ajax({
		type : 'GET',
		data : 'group_name=' + group_name,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			var table = $('#interfaceSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"paging" : false,
				// "pageLength" : 5,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets

				scrollY : 500, // Set the height of the scrollable area
				// SCROLLX : FALSE,
				// scrollCollapse : true,
				scroller : true
			});

			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);

		}
	});
}
function getupInterfaceSummaryDetails(group_name) {
	$('#interface-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getupInterfaceSummaryDetails";
	$.ajax({
		type : 'GET',
		data : 'group_name=' + group_name,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			var table = $('#interfaceSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"paging" : false,
				// "pageLength" : 5,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 500, // Set the height of the scrollable area
				scroller : true
			});

			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);

		}
	});
}
function getdownInterfaceSummaryDetails(group_name) {
	$('#interface-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url
			+ "/interfaceReport/getdownInterfaceSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'group_name=' + group_name,
		dataType : 'json',
		success : function(data) {
			var table = $('#interfaceSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"paging" : false,
				// "pageLength" : 5,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 500, // Set the height of the scrollable area
				scroller : true
			});

			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);

		}
	});
}
function getwarningInterfaceSummaryDetails(group_name) {
	$('#interface-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url
			+ "/interfaceReport/getwarningInterfaceSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'group_name=' + group_name,
		dataType : 'json',
		success : function(data) {
			var table = $('#interfaceSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"paging" : false,
				// "pageLength" : 5,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 500, // Set the height of the scrollable area
				scroller : true
			});

			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);

		}
	});
}

function getInterfaceDetailsWan4g(groupName, status) {
	$('#interface-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getInterfaceDetailsWan4g";
	var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";

	if ($.fn.DataTable.isDataTable('#interfaceSummaryTable')) {
		$('#interfaceSummaryTable').DataTable().clear().destroy();
	}

	$.ajax({
		type : 'GET',
		data : 'group_name=' + groupName + '&status=' + status,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {

			if ($.fn.DataTable.isDataTable('#interfaceSummaryTable')) {
				$('#interfaceSummaryTable').DataTable().clear().destroy();
			}

			// Clear the table body
			$('#interfaceSummaryTable tbody').empty();

			var htmldata;
			$.each(data, function(i, item) {
				htmldata = $('<tr>').html(
						"<td><a href=" + nodeDetailUrl + data[i][0] + ">"
								+ data[i][0] + "</a></td>" + "<td>"
								+ data[i][1] + "</td>" + "<td>" + data[i][2]
								+ "</td>" + "<td>" + data[i][3] + "</td>"
								+ "<td>" + data[i][4] + "</td>" + "<td>"
								+ data[i][5] + "</td>" + "<td>" + data[i][6]
								+ "</td>" + "<td>" + data[i][7] + "</td>"
								+ "<td>" + data[i][8] + "</td>" + "<td>"
								+ data[i][9] + "</td>");
				$('#interfaceSummaryTable tbody').append(htmldata);
			});

			// alert(data)
			var table = $('#interfaceSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				// data : data,
				"paging" : false,
				// "pageLength" : 5,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 500, // Set the height of the scrollable area
				"sScrollX" : "100%",
				"scrollCollapse" : true,
				scroller : true
			});
			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);

		}
	});
}

// Device Type
function getTotalDeviceTypeSummaryDetails(group_name) {
	$('#deviceTypeSummaryModal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url
			+ "/interfaceReport/getTotalDeviceTypeSummaryDetails";
	$.ajax({
		type : 'GET',
		data : 'group_name=' + group_name,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			$('#portSummaryTable tbody').empty();
			var table = $('#deviceTypeSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 500, // Set the height of the scrollable area
				scroller : true,
				destroy : true,
			});

		}
	});
}
function getupDeviceTypeSummaryDetails(group_name) {
	$('#deviceTypeSummaryModal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url
			+ "/interfaceReport/getupDeviceTypeSummaryDetails";
	$.ajax({
		type : 'GET',
		data : 'group_name=' + group_name,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			var table = $('#deviceTypeSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true,
				destroy : true,
			});

		}
	});
}
function getdownDeviceTypeSummaryDetails(group_name) {
	$('#deviceTypeSummaryModal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url
			+ "/interfaceReport/getdownDeviceTypeSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'group_name=' + group_name,
		dataType : 'json',
		success : function(data) {
			var table = $('#deviceTypeSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true,
				destroy : true,
			});

		}
	});
}
function getwarningDeviceTypeSummaryDetails(group_name) {
	$('#deviceTypeSummaryModal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url
			+ "/interfaceReport/getwarningDeviceTypeSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'group_name=' + group_name,
		dataType : 'json',
		success : function(data) {
			var table = $('#deviceTypeSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true,
				destroy : true,
			});

		}
	});
}
/*
 * $(function() { $("#searchDataModal").click(function() { if
 * ($('#searchDataOption').val() == 'ipAddressSearch') { alert("1"); } else if
 * ($('#searchDataOption').val() == 'nameSearch') { alert("2"); } else if
 * ($('#searchDataOption').val() == 'groupNameSearch') { alert("3"); } else if
 * ($('#searchDataOption').val() == 'deviceTypeSearch') { alert("4"); } });
 */

$("#searchDataModal").click(function() {
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;

	var searchByIp = `${base_url}/node/searchByIp`;
	var searchByName = `${base_url}/node/searchByName`;
	var searchByGroupName = `${base_url}/node/searchByGroupName`;
	var searchByDeviceType = `${base_url}/node/searchByDeviceType`;
	var targetUrl;
	var optionValue = $('#searchDataOption').val();

	switch (optionValue) {
	case 'ipAddressSearch':
		targetUrl = searchByIp;
		break;
	case 'nameSearch':
		targetUrl = searchByName;
		break;
	case 'groupNameSearch':
		targetUrl = searchByGroupName;
		break;
	case 'deviceTypeSearch':
		targetUrl = searchByDeviceType;
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
	document.getElementById('searchDataNode').action = targetUrl;
	document.getElementById('searchDataNode').submit();
});
function detailNodeData(data) {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";
	window.location.href = nodeDetailUrl + data;
}

// alarm chart
// $(document).ready(function() {

/*
 * var l = window.location; var base_url = l.protocol + "//" + l.host + "/" +
 * l.pathname.split('/')[1]; var dataUrl = base_url +
 * "/dashboard/getAlarmCount"; $.ajax({ type: 'GET', url: dataUrl, dataType:
 * 'json', success: function(data) { var donutChartCanvas =
 * document.getElementById('donutChart').getContext('2d'); var donutData = {
 * labels: ['Alarm'], datasets: [{ data: [data], backgroundColor: ['#f56954'], }] };
 * 
 * var donutOptions = { maintainAspectRatio: true, responsive: true,
 * cutoutPercentage: 70, circumference: 2 * Math.PI, rotation: -Math.PI, legend: {
 * display: false, }, tooltips: { enabled: true } };
 * 
 * var canvas = document.getElementById("donutChart"); var ctx =
 * canvas.getContext("2d"); var myNewChart = new Chart(donutChartCanvas, { type:
 * 'doughnut', data: donutData, options: donutOptions });
 * 
 * canvas.onclick = function(evt) { var activePoints =
 * myNewChart.getElementsAtEvent(evt); if (activePoints[0]) { var chartData =
 * activePoints[0]['_chart'].config.data; var idx = activePoints[0]['_index'];
 * 
 * var label = chartData.labels[idx]; var value =
 * chartData.datasets[0].data[idx];
 * 
 * $('#alarmInfoModal').modal('show'); } }; } });
 */

// Update the count
/*
 * var totalCount = donutData.datasets[0].data.reduce((sum, value) => sum +
 * value, 0); alarmCount.textContent = totalCount;
 */

/*
 * var l = window.location; var base_url = l.protocol + "//" + l.host + "/" +
 * l.pathname.split('/')[1]; var dataUrl = base_url + "/dashboard/getAlarmInfo";
 * $.ajax({ type: 'GET', url: dataUrl, dataType: 'json', success: function(data) {
 * var htmlContent = ''; $.each(data[0], function(index, entry) { htmlContent += '<div
 * class="callout callout-danger">'; htmlContent += '<p><strong>Device IP:</strong> ' +
 * entry.NodeMonitoringNodeIP + '</p>'; htmlContent += '<p><strong>Latency:</strong> ' +
 * entry.LATENCY + '</p>'; htmlContent += '<p><strong>Latency Status:</strong> ' +
 * entry.LATENCY_STATUS + '</p>'; htmlContent += '<p><strong>Time:</strong> ' +
 * entry.NodeMonitoringTime + '</p>'; htmlContent += '</div>'; });
 * $('#NodeMonitoringData').html(htmlContent);
 * 
 * var htmlContent = ''; $.each(data[1], function(index, entry) { htmlContent += '<div
 * class="callout callout-danger">'; htmlContent += '<p><strong>Device IP:</strong> ' +
 * entry.nodeHelthNodeIP + '</p>'; htmlContent += '<p><strong>Memory Status:</strong> ' +
 * entry.MEMORY_STATUS + '</p>'; htmlContent += '<p><strong>Memory
 * Threshould:</strong> ' + entry.MEMORY_UTILIZATION + '%</p>'; htmlContent += '</div>';
 * }); $('#NodeHelthMonitoringData').html(htmlContent);
 * 
 * var htmlContent = ''; $.each(data[1], function(index, entry) { htmlContent += '<div
 * class="callout callout-danger">'; htmlContent += '<p><strong>Device IP:</strong> ' +
 * entry.nodeHelthNodeIP + '</p>'; htmlContent += '<p><strong>CPU Status:</strong> ' +
 * entry.CPU_STATUS + '</p>'; htmlContent += '<p><strong>CPU Threshould:</strong> ' +
 * entry.CPU_UTILIZATION + '%</p>'; htmlContent += '</div>'; });
 * $('#NodeHelthCpuMonitoringData').html(htmlContent);
 * 
 * var htmlContent = ''; $.each(data[2], function(index, entry) { htmlContent += '<div
 * class="callout callout-danger">'; htmlContent += '<p><strong>Device IP:</strong> ' +
 * entry.interfaceMonitoringNodeIP + '</p>'; htmlContent += '<p><strong>Interface
 * Name:</strong> ' + entry.INTERFACE_NAME + '</p>'; htmlContent += '<p><strong>Bandwith
 * Threshould (IN):</strong> ' + entry.IN_TRAFFIC + '(Byte)</p>'; htmlContent += '<p><strong>Bandwith
 * Threshould (OUT):</strong> ' + entry.OUT_TRAFFIC + '(Byte)</p>';
 * htmlContent += '<p><strong>Bandwith Status:</strong> ' + entry.BW_STATUS + '</p>';
 * htmlContent += '<p><strong>Time:</strong> ' + entry.BW_TIME + '</p>';
 * htmlContent += '</div>'; });
 * $('#InterfaceMonitoringData').html(htmlContent);
 * 
 * var htmlContent = ''; $.each(data[3], function(index, entry) { htmlContent += '<div
 * class="callout callout-danger">'; htmlContent += '<p><strong>Device IP:</strong> ' +
 * entry.deviceIP + '</p>'; htmlContent += '<p><strong>Drive:</strong> ' +
 * entry.driveName + '</p>'; htmlContent += '<p><strong>Status:</strong> ' +
 * entry.status + '</p>'; htmlContent += '<p><strong>Event Time:</strong> ' +
 * entry.eventTime + '</p>'; htmlContent += '</div>'; });
 * $('#DriveMonitoring').html(htmlContent); } });
 */
// });
// Device Up Down Count
function deviceUpDownCount() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/deviceSummaryCount";
	$
			.ajax({
				type : 'GET',
				url : serviceUrl,
				dataType : 'json',
				success : function(data) {

					var upcount = 0;
					var downcount = 0;
					var warningcount = 0;

					for (var i = 0; i < data.length; i++) {

						if (data[i].status == "Up") {
							document.getElementById("upspanid").textContent = data[i].count;
							upcount = data[i].count;
						}
						if (data[i].status == "Down") {
							document.getElementById("downspanid").textContent = data[i].count;
							downcount = data[i].count;
						}
						if (data[i].status == "Warning") {
							document.getElementById("warningspanid").textContent = data[i].count;
							warningcount = data[i].count;
						}
					}

					var result = parseInt(upcount) + parseInt(downcount)
							+ parseInt(warningcount);
					document.getElementById("totalspanid").textContent = result;

					localStorage.setItem("upCountVar", upcount);
					localStorage.setItem("downCountVar", downcount);
					localStorage.setItem("warningCountVar", warningcount);

					// Device Summary PieChart
					/*
					 * var vUpCount = localStorage.getItem("upCountVar"); var
					 * vDownCount = localStorage.getItem("downCountVar"); var
					 * vWarningCount = localStorage.getItem("warningCountVar");
					 * 
					 * var upCountVal = Number(vUpCount); var downCountVal =
					 * Number(vDownCount); var warningCountVal =
					 * Number(vWarningCount);
					 * 
					 * var devicedonutData = { labels: ['Up', 'Down',
					 * 'Warning'], datasets: [{ data: [upCountVal, downCountVal,
					 * warningCountVal], backgroundColor: ['#00a65a', '#f56954',
					 * '#f39c12'], }] }
					 * 
					 * var devicepieChartCanvas =
					 * $('#devicepieChart').get(0).getContext('2d') var
					 * devicepieData = devicedonutData; var devicepieOptions = {
					 * maintainAspectRatio: false, responsive: true, }
					 */

					// Create pie or douhnut chart
					// You can switch between pie and douhnut using the method
					// below.
					/*
					 * devicechart = new Chart(devicepieChartCanvas, { type:
					 * 'pie', data: devicepieData, options: devicepieOptions })
					 */

				}

			});
}

// Interface Up down Count Summary
function interfaceUpDownCount() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/linkSummaryCount";
	$
			.ajax({
				type : 'GET',
				url : serviceUrl,
				dataType : 'json',
				success : function(data) {

					var linkupcount = 0;
					var linkdowncount = 0;

					for (var i = 0; i < data.length; i++) {

						if (data[i].status == "up") {
							document.getElementById("uplinkspanid").textContent = data[i].count;
							linkupcount = data[i].count;
						}
						if (data[i].status == "down") {
							document.getElementById("downlinkspanid").textContent = data[i].count;
							linkdowncount = data[i].count;
						}

					}

					var result = parseInt(linkupcount)
							+ parseInt(linkdowncount);
					document.getElementById("totallinkspanid").textContent = result;

					localStorage.setItem("upLinkCountVar", linkupcount);
					localStorage.setItem("downLinkCountVar", linkdowncount);

					var vUpLinkCount = localStorage.getItem("upLinkCountVar");
					var vDownLinkCount = localStorage
							.getItem("downLinkCountVar");

					// var upLinkCountVal = Number(vUpLinkCount);
					// var downLinkCountVal = Number(vDownLinkCount);

					/*
					 * var linkdonutData = { labels: ['Up', 'Down'], datasets: [{
					 * data: [upLinkCountVal, downLinkCountVal],
					 * backgroundColor: ['#00a65a', '#f56954'], }] }
					 * 
					 * var linkpieChartCanvas =
					 * $('#linkpieChart').get(0).getContext('2d') var
					 * linkpieData = linkdonutData; var linkpieOptions = {
					 * maintainAspectRatio: false, responsive: true, }
					 */
					// Create pie or douhnut chart
					// You can switch between pie and douhnut using the method
					// below.
					/*
					 * linkpie = new Chart(linkpieChartCanvas, { type: 'pie',
					 * data: linkpieData, options: linkpieOptions })
					 */
				}
			});

}

function alertCount() {
	// Alert Count
	var alarmCount = null;
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/alertCount";

	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			$('#alertspanid').text(data);
			$('#alarmCount').text(data);
		}
	});
}

function groupWiseSummary() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/groupSummary";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			var table = $('#groupSummary').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"paging" : false,
				// "pageLength" : 10,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 350, // Set the height of the scrollable area
				scroller : true
			});

			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);

		}
	});
}

function interfaceSummary() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var interfaceServiceUrl = base_url + "/interfaceReport/interfaceSummary";
	$.ajax({
		type : 'GET',
		url : interfaceServiceUrl,
		dataType : 'json',
		success : function(data) {
			var table = $('#interfaceSummary').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true,
				scrollY : '150px',
				destroy : true,
			});

		}
	});
}

function interfaceSummaryGroupWise() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var interfaceServiceUrl = base_url
			+ "/interfaceReport/interfaceSummaryGroupWise";
	$.ajax({
		type : 'GET',
		url : interfaceServiceUrl,
		dataType : 'json',
		success : function(data) {
			var table = $('#interfaceSummaryNew').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"paging" : false,
				// "pageLength" : 5,
				destroy : true,
				deferRender : true, // Recommended for scroller for large
				// datasets
				scrollY : 350, // Set the height of the scrollable area
				scroller : true
			});
			setTimeout(function() {

				table.columns.adjust().draw();
			}, 500);
		}
	});
}

function deviceTypeSummary() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/deviceTypeSummary";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			var table = $('#deviceTypeSummary').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true,
				scrollY : '150px',
				destroy : true,
			});

		}
	});
}