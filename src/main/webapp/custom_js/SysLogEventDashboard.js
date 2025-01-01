$(function() {

	var today = new Date();
	var formattedDate = today.toISOString().split('T')[0];
	/* var fromDate = '2024-01-18 22:00:00'; */
	var fromDate = formattedDate + ' 00:00:00';
	var toDate = formattedDate + ' 23:59:59';

	$('#from_date').val(fromDate);
	$('#to_date').val(toDate);

	// var l = window.location;
	// var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	// var serviceUrl = `${base_url}/dashboard/osWiseSummary`;
	// $.ajax({
	// type : "GET",
	// url : serviceUrl,
	// dataType : "json",
	// success : function(data4) {
	// var table = $("#linkSummary").DataTable(
	// {
	// "responsive" : true,
	// "lengthChange" : false,
	// "autoWidth" : false,
	// "data" : data4,
	// "buttons" : [ "copy", "csv", "excel", "pdf", "print",
	// "colvis" ]
	// }).buttons().container().appendTo(
	// '#example1_wrapper .col-md-6:eq(0)');
	//
	// },
	// });

	// var l = window.location;
	// var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	// var serviceUrl = `${base_url}/dashboard/eventWiseSummary`;
	// $.ajax({
	// type : "GET",
	// url : serviceUrl,
	// dataType : "json",
	// success : function(data4) {
	// // alert(data4)
	// var table = $("#eventNameSummary").DataTable({
	// "responsive" : true,
	// "lengthChange" : false,
	// "autoWidth" : false,
	// "data" : data4,
	// });
	//
	// },
	// });
	/*
	 * var donutData = [ { label : 'Used %', data : 50, color : '#DD1C1C' }, {
	 * label : 'Free %', data : 40, color : '#52ED15' } ]
	 * $.plot('#linkpieChart', donutData, { series : { pie : { show : true,
	 * radius : 1, innerRadius : 0.5, label : { show : true, radius : 2 / 3,
	 * formatter : labelFormatter, threshold : 0.1 } } }, legend : { show :
	 * false } })
	 */

	/*
	 * var l = window.location; var base_url =
	 * `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`; var serviceUrl =
	 * `${base_url}/admin/statusWiseSummary`; $.ajax({ type: "GET", url:
	 * serviceUrl, dataType: "json", success: function(data5) { // alert(data4)
	 * var upCount = data5[0][0]; // Replace with your actual UP count var
	 * downCount = data5[0][1]; // Replace with your actual DOWN count
	 * 
	 * console.log(upCount); console.log(downCount);
	 * 
	 * var ctx = document.getElementById('linkpieChart').getContext('2d'); var
	 * linkPieChart = new Chart(ctx, { type: 'pie', data: { labels: ['Up',
	 * 'Down'], datasets: [{ data: [upCount, downCount], backgroundColor:
	 * ['#28a745', '#dc3545'], // Green for UP, Red for DOWN }] }, options: {
	 * responsive: true, maintainAspectRatio: false, legend: { position: 'top', },
	 * onClick: function(event, elements) { if (elements.length > 0) { var
	 * clickedIndex = elements[0]._index; var label =
	 * linkPieChart.data.labels[clickedIndex]; alert('You clicked on ' + label);
	 * getupNodeStatusDetails(label) if (label == "Up") {
	 * getupNodeStatusDetails(label); } else { getdownNodeStatusDetails(label); } } }, }
	 * }); }, });
	 */

	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/eventCountDashboard`;
	$.ajax({
		url : serviceUrl,
		type : 'GET',
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val()
		},
		dataType : 'json',
		success : function(data) {

			$('#allEventsCount').text(data[3][0]);
			// $('#windowsEventsCount').text(data[1][0]);
			$('#syslogEventsCount').text(data[2][0]);
			// $('#allDevicesCount').text(data[0][0]);
		},
		error : function(xhr, status, error) {
			console.error('Error fetching data:', error);
		}
	});

	// below function for the windows Severity Event

	// var l = window.location;
	// var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	// var serviceUrl = `${base_url}/dashboard/windowsSeverityEvent`;
	// $
	// .ajax({
	// type : "GET",
	// url : serviceUrl,
	// data : {
	// from_date : $('#from_date').val(),
	// to_date : $('#to_date').val()
	// },
	// dataType : "json",
	// success : function(data4) {
	// var dynamicData = data4.map(function(item) {
	// return {
	// name : item.event_type,
	// y : item.event_count
	// };
	// });
	// var dataColors = []
	// data4.forEach(function(item) {
	// if (item.event_type === "success") {
	// dataColors.push("#008000");
	// } else if (item.event_type === "warning") {
	// dataColors.push("#FFFF00");
	// } else if (item.event_type === "error") {
	// dataColors.push("red");
	// } else if (item.event_type === "information") {
	// dataColors.push("#FFA500");
	// }
	// });
	// Highcharts
	// .chart(
	// 'container1',
	// {
	// chart : {
	// type : 'column'
	// },
	// title : {
	// align : 'left',
	// text : 'Windows Severity Event'
	// },
	// accessibility : {
	// announceNewData : {
	// enabled : true
	// }
	// },
	// xAxis : {
	// type : 'category'
	// },
	// yAxis : {
	// title : {
	// text : 'Event Count'
	// }
	// },
	// legend : {
	// enabled : false
	// },
	// plotOptions : {
	// series : {
	// borderWidth : 0,
	// dataLabels : {
	// enabled : true,
	// format : '{point.y}'
	// },
	// point : {
	// events : {
	// click : function() {
	// windowsSeverityBarClick(this.name);
	// }
	// }
	// }
	// }
	// },
	// tooltip : {
	// headerFormat : '<span style="font-size:11px">{series.name}</span><br>',
	// pointFormat : '<span style="color:{point.color}">{point.name}</span>:
	// <b>{point.y}</b> of total<br/>'
	// },
	// series : [ {
	// name : 'Windows Event',
	// colorByPoint : true,
	// data : dynamicData,
	// colors : dataColors
	// } ]
	// });
	//
	// var eventTypeArray = data4.map(function(item) {
	// return item.event_type;
	// });
	//
	// var eventCountArray = data4.map(function(item) {
	// return item.event_count;
	// });
	//
	// /* console.log(eventTypeArray); */
	//
	// var data = {
	// labels : eventTypeArray,
	// datasets : [ {
	// data : eventCountArray,
	// backgroundColor : dataColors,
	// hoverBackgroundColor : dataColors
	// } ]
	// };
	//
	// // Get the canvas element
	// var ctx = document.getElementById('linkpieChart1')
	// .getContext('2d');
	//
	// // Create a pie chart
	// var linkPieChart = new Chart(
	// ctx,
	// {
	// type : 'pie',
	// data : data,
	// options : {
	// legend : {
	// display : false
	// },
	// onClick : function(event, elements) {
	// if (elements.length > 0) {
	// // The user clicked on a pie chart
	// // segment
	// var segmentIndex = elements[0]._index;
	// var segmentValue = data.datasets[0].data[segmentIndex];
	// var segmentLabel = data.labels[segmentIndex];
	// windowsSeverityBarClick(segmentLabel);
	// }
	// }
	// }
	// });
	//
	// var ulElement = document.getElementById('myList');
	// /* var colors = ['#FF6384', '#36A2EB', '#FFCE56', 'red']; */
	// var colors = dataColors;
	//
	// ulElement.innerHTML = '';
	// // Loop through the array and create <li> elements
	// // dynamically
	// data4.forEach(function(item, index) {
	// // Create a new <li> element
	// var liElement = document.createElement('li');
	//
	// // Set the text content of the <li> element with the
	// // desired format
	// liElement.textContent = item.event_type + ' '
	// + item.event_count;
	// liElement.style.color = colors[index % colors.length];
	// liElement.onclick = function() {
	// // Call the windowsSeverityBarClick function with
	// // the corresponding event_type
	// windowsSeverityBarClick(item.event_type);
	// };
	// // Append the <li> element to the <ul> element
	// ulElement.appendChild(liElement);
	// });
	//
	// },
	// });

	// var l = window.location;
	// var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	// var serviceUrl = `${base_url}/dashboard/windowsEventLogWise`;
	// $
	// .ajax({
	// type : "GET",
	// url : serviceUrl,
	// data : {
	// from_date : $('#from_date').val(),
	// to_date : $('#to_date').val()
	// },
	// dataType : "json",
	// success : function(data4) {
	// var dynamicData = data4.map(function(item) {
	// return {
	// name : item.event_type,
	// y : item.event_count
	// };
	// });
	//
	// Highcharts
	// .chart(
	// 'container2',
	// {
	// chart : {
	// type : 'column'
	// },
	// title : {
	// align : 'left',
	// text : 'Windows Severity Event'
	// },
	// accessibility : {
	// announceNewData : {
	// enabled : true
	// }
	// },
	// xAxis : {
	// type : 'category'
	// },
	// yAxis : {
	// title : {
	// text : 'Event Count'
	// }
	// },
	// legend : {
	// enabled : false
	// },
	// plotOptions : {
	// series : {
	// borderWidth : 0,
	// dataLabels : {
	// enabled : true,
	// format : '{point.y}'
	// },
	// point : {
	// events : {
	// click : function() {
	// // Your onclick
	// // function logic
	// // here
	// windowsLogBarClick(this.name);
	// }
	// }
	// }
	// }
	// },
	// tooltip : {
	// headerFormat : '<span style="font-size:11px">{series.name}</span><br>',
	// pointFormat : '<span style="color:{point.color}">{point.name}</span>:
	// <b>{point.y}</b> of total<br/>'
	// },
	// series : [ {
	// name : 'Windows Event',
	// colorByPoint : true,
	// data : dynamicData
	// } ]
	// });
	//
	// },
	// });

	// var l = window.location;
	// var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	// var serviceUrl = `${base_url}/dashboard/windowsEventTrend`;
	// $.ajax({
	// type : "GET",
	// url : serviceUrl,
	// data : {
	// from_date : $('#from_date').val(),
	// to_date : $('#to_date').val()
	// },
	// dataType : "json",
	// success : function(data4) {
	//
	// var formattedData = data4.map(function(item) {
	// return {
	// name : item.event_type,
	// y : item.event_count
	// };
	// });
	//
	// var eventDates = data4.map(function(item) {
	// return item.event_type;
	// });
	//
	// // Create the Highcharts chart
	// Highcharts.chart('windowsEventTrend', {
	// chart : {
	// type : 'area'
	// },
	// xAxis : {
	// categories : eventDates,
	//
	// },
	// yAxis : {
	// title : {
	// useHTML : true,
	// text : 'Event Count'
	// }
	// },
	// tooltip : {
	// shared : true,
	// },
	// plotOptions : {
	// area : {
	// stacking : 'normal',
	// lineColor : '#666666',
	// lineWidth : 1,
	// marker : {
	// lineWidth : 1,
	// lineColor : '#666666'
	// }
	// }
	// },
	// series : [ {
	// name : 'Event Count',
	// data : formattedData
	// } ]
	// });
	//
	// }
	// });

	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/syslogEventTrend`;
	$.ajax({
		type : "GET",
		url : serviceUrl,
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val()
		},
		dataType : "json",
		success : function(data4) {

			var formattedData = data4.map(function(item) {
				return {
					name : item.event_type,
					y : item.event_count
				};
			});

			var eventDates = data4.map(function(item) {
				return item.event_type;
			});

			// Create the Highcharts chart
			Highcharts.chart('syslogEventTrend', {
				chart : {
					type : 'area'
				},
				xAxis : {
					categories : eventDates,

				},
				yAxis : {
					title : {
						useHTML : true,
						text : 'Event Count'
					}
				},
				tooltip : {
					shared : true,
				},
				plotOptions : {
					area : {
						stacking : 'normal',
						lineColor : '#666666',
						lineWidth : 1,
						marker : {
							lineWidth : 1,
							lineColor : '#666666'
						}
					}
				},
				series : [ {
					name : 'Event Count',
					data : formattedData
				} ]
			});

		}
	});

	/*
	 * var l = window.location; var base_url = l.protocol + "//" + l.host + "/" +
	 * l.pathname.split('/')[1]; var serviceUrl = base_url +
	 * "/dashboard/allSyslogSeverityList"; $.ajax({ type: 'GET', url:
	 * serviceUrl, data: { from_date: $('#from_date').val(), to_date:
	 * $('#to_date').val() }, dataType: 'json', success: function(data) {
	 * //alert(data); var table = $('#syslogEventTable').DataTable({
	 * lengthChange: true, reponsive: false, data: data, "pageLength": 5,
	 * scrollX: true, scrollY: true, destroy: true, }); } });
	 */

	// var l = window.location;
	// var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	// var serviceUrl = `${base_url}/dashboard/sourceWiseSummary`;
	// $.ajax({
	// url : serviceUrl,
	// type : 'GET',
	// data : {
	// from_date : $('#from_date').val(),
	// to_date : $('#to_date').val()
	// },
	// dataType : 'json',
	// success : function(data) {
	// $('#sourceWiseSummary').DataTable().clear().rows.add(data).draw();
	//
	// },
	// error : function(xhr, status, error) {
	// console.error('Error fetching data:', error);
	// }
	// });
});

// Group wise And Interface Wise
function gettotalNodeSummaryDetails(group_name) {
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getTotalNodeSummaryDetails";
	$.ajax({
		type : 'GET',
		data : 'group_name=' + group_name,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			/*
			 * $('#portSummaryTable tbody').empty(); var table =
			 * $('#portSummaryTable').DataTable({ lengthChange : true, autoWidth :
			 * false, data : data, "pageLength" : 5, scrollX : true, scrollY :
			 * true, destroy: true, });
			 */

			$('#portSummaryTable').DataTable().clear().rows.add(data).draw();

		}
	})

	console.log(group_name);
}
function getupNodeSummaryDetails(group_name) {
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getupNodeSummaryDetails";
	$.ajax({
		type : 'GET',
		data : 'group_name=' + group_name,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			/*
			 * var table = $('#portSummaryTable').DataTable({ lengthChange :
			 * true, reponsive : false, data : data, "pageLength" : 5, scrollX :
			 * true, scrollY : true, destroy: true, });
			 */
			$('#portSummaryTable').DataTable().clear().rows.add(data).draw();
		}
	});
}
function getdownNodeSummaryDetails(group_name) {
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getdownNodeSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'group_name=' + group_name,
		dataType : 'json',
		success : function(data) {
			/*
			 * var table = $('#portSummaryTable').DataTable({ lengthChange :
			 * false, autoWidth : false, data : data, "pageLength" : 5, scrollX :
			 * true, scrollY : true, destroy: true, });
			 */

			$('#portSummaryTable').DataTable().clear().rows.add(data).draw();

		}
	});
}

function getupNodeStatusDetails(label) {
	$('#port-status-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getupNodeStatusDetails";
	$.ajax({
		type : 'GET',
		data : 'label=' + label,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			/*
			 * var table = $('#portStatusTable').DataTable({ lengthChange :
			 * true, reponsive : false, data : data, "pageLength" : 5, scrollX :
			 * true, scrollY : true, destroy: true, });
			 */
			$('#portStatusTable').DataTable().clear().rows.add(data).draw();
		}
	});
}
function getdownNodeStatusDetails(label) {
	$('#port-status-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getdownNodeStatusDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'label=' + label,
		dataType : 'json',
		success : function(data) {
			/*
			 * var table = $('#portStatusTable').DataTable({ lengthChange :
			 * false, autoWidth : false, data : data, "pageLength" : 5, scrollX :
			 * true, scrollY : true, destroy: true, });
			 */

			$('#portStatusTable').DataTable().clear().rows.add(data).draw();

		}
	});
}

function deviceDetailsIP(ip_address, device_name) {
	console.log(ip_address);
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/deviceDetailsIP/" + ip_address
			+ "/" + device_name + "/";
	window.location.href = serviceUrl;

}

function windowsSecurityEvent() {
	$('#windows-event-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/windowsSecurityEvent";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val()
		},
		dataType : 'json',
		success : function(data) {
			/*
			 * var table = $('#portStatusTable').DataTable({ lengthChange :
			 * true, reponsive : false, data : data, "pageLength" : 5, scrollX :
			 * true, scrollY : true, destroy: true, });
			 */
			$('#windowsEventTable').DataTable().clear().rows.add(data).draw();
		}
	});
}

function allDevicesList() {
	$('#devices-list-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/allDevicesList";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			/*
			 * var table = $('#portStatusTable').DataTable({ lengthChange :
			 * true, reponsive : false, data : data, "pageLength" : 5, scrollX :
			 * true, scrollY : true, destroy: true, });
			 */
			$('#devicesListTable').DataTable().clear().rows.add(data).draw();
		}
	});
}

function syslogEventList() {
	$('#syslog-event-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/syslogEventList";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val()
		},
		dataType : 'json',
		success : function(data) {
			var table = $('#syslogEventTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true,
				destroy : true,
			});

			setTimeout(function() {
//				alert("in")

				table.columns.adjust().draw();
			}, 1500);
			/* $('#syslogEventTable').DataTable().clear().rows.add(data).draw(); */
		}
	});
}

function syslogSeverityListData(data) {
	$('#syslogSeverityModal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/syslogSeverityListData";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val(),
			severity : data
		},
		dataType : 'json',
		success : function(data) {
			var table = $('#syslogSeverityTable').DataTable({
				lengthChange : true,
				reponsive : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true,
				destroy : true,
			});
			/* $('#syslogEventTable').DataTable().clear().rows.add(data).draw(); */
			setTimeout(function() {
//				alert("in")

				table.columns.adjust().draw();
			}, 1500);

		}
	});
}

function syslogTypeListData(data) {
	$('#syslogTypeModal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/syslogTypeListData";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val(),
			type : data
		},
		dataType : 'json',
		success : function(data) {
			var table = $('#syslogTypeTable').DataTable({
				lengthChange : true,
				reponsive : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true,
				destroy : true,
			});
			/* $('#syslogEventTable').DataTable().clear().rows.add(data).draw(); */

			setTimeout(function() {
				// alert("in")

				table.columns.adjust().draw();
//				table.columns.adjust().draw();
//				table.columns.adjust().draw();
			}, 1500);
		}
	});
}

$('#daterange-btn').daterangepicker(
		{
			timePicker : true,
			timePickerIncrement : 10,
			ranges : {
				'Today' : [ moment().hours(0).minutes(0).seconds(0),
						moment().hours(23).minutes(59).seconds(59) ],
				'Yesterday' : [
						moment().hours(0).minutes(0).seconds(0).subtract(1,
								'days'),
						moment().hours(23).minutes(59).seconds(59).subtract(1,
								'days') ],
				'Last 7 Days' : [
						moment().hours(0).minutes(0).seconds(0).subtract(6,
								'days'),
						moment().hours(23).minutes(59).seconds(59) ],
				'Last 30 Days' : [
						moment().hours(0).minutes(0).seconds(0).subtract(29,
								'days'),
						moment().hours(23).minutes(59).seconds(59) ],
				'This Month' : [ moment().startOf('month'),
						moment().endOf('month') ],
				'Last Month' : [
						moment().subtract(1, 'month').startOf('month'),
						moment().subtract(1, 'month').endOf('month') ],
			// 'Last 3 Month' : [
			// moment().subtract(4, 'month').startOf('month'),
			// moment().subtract(1, 'month').endOf('month') ],
			// 'Last 6 Month' : [
			// moment().subtract(7, 'month').startOf('month'),
			// moment().subtract(1, 'month').endOf('month') ],
			// 'Last Year' : [
			// moment().subtract(21, 'month').startOf('month'),
			// moment().subtract(10, 'month').endOf('month') ],
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

function dashboardFilter() {

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/allSyslogSeverityList";

	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val()
		},
		dataType : 'json',
		success : function(data) {
			var colors = {
				"warning" : '#FFD700', // Yellow
				"error" : '#FF0000', // Red
				"alert" : '#00FF00' // Green
			};

			// Extracting data for the pie chart
			var chartData = data.map(function(item) {
				return {
					name : item.syslogSeverity,
					y : item.count,
					color : colors[item.syslogSeverity]
				};
			});

			// Create the pie chart
			Highcharts.chart('syslogSeverity', {
				chart : {
					type : 'pie'
				},
				title : {
					text : 'Syslog Severity ListData'
				},
				series : [ {
					name : 'Count',
					data : chartData,
					events : {
						click : function(event) {
							var point = event.point;
							// Redirect to a specific URL with data
							/*
							 * var url = base_url +
							 * '/dashboard/syslogSeverityList/?type=' +
							 * point.name; window.location.href = url;
							 */
							syslogSeverityListData(point.name);
						}
					}
				} ],
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : false
						},
						showInLegend : true
					}
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle'
				},
				colors : Object.values(colors)
			});
		}
	});

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/allSyslogTypeList";

	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val()
		},
		dataType : 'json',
		success : function(data) {
			var colors = {
				"traffic" : '#FFD700', // Yellow
				"utm" : '#FF0000', // Red
				"event" : '#00FF00' // Green
			};

			// Extracting data for the pie chart
			var chartData = data.map(function(item) {
				return {
					name : item.syslogType,
					y : item.count,
					color : colors[item.syslogType]
				};
			});

			// Create the pie chart
			Highcharts.chart('syslogType', {
				chart : {
					type : 'pie'
				},
				title : {
					text : 'Sys Log Types'
				},
				series : [ {
					name : 'Count',
					data : chartData,
					events : {
						click : function(event) {
							var point = event.point;
							syslogTypeListData(point.name);
						}
					}
				} ],
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : false
						},
						showInLegend : true
					}
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle'
				},
				colors : Object.values(colors)
			});
		}
	});

	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/eventCountDashboard`;
	$.ajax({
		url : serviceUrl,
		type : 'GET',
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val()
		},
		dataType : 'json',
		success : function(data) {

			$('#allEventsCount').text(data[3][0]);
			// $('#windowsEventsCount').text(data[1][0]);
			$('#syslogEventsCount').text(data[2][0]);
			$('#allDevicesCount').text(data[0][0]);
		},
		error : function(xhr, status, error) {
			console.error('Error fetching data:', error);
		}
	});

	// below function for the windows Severity Event

	// var l = window.location;
	// var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	// var serviceUrl = `${base_url}/dashboard/windowsSeverityEvent`;
	// $
	// .ajax({
	// type : "GET",
	// url : serviceUrl,
	// data : {
	// from_date : $('#from_date').val(),
	// to_date : $('#to_date').val()
	// },
	// dataType : "json",
	// success : function(data4) {
	// var dynamicData = data4.map(function(item) {
	// return {
	// name : item.event_type,
	// y : item.event_count
	// };
	// });
	//
	// var dataColors = []
	// data4.forEach(function(item) {
	// if (item.event_type === "success") {
	// dataColors.push("#008000");
	// } else if (item.event_type === "warning") {
	// dataColors.push("#FFFF00");
	// } else if (item.event_type === "error") {
	// dataColors.push("red");
	// } else if (item.event_type === "information") {
	// dataColors.push("#FFA500");
	// }
	// });
	//
	// Highcharts
	// .chart(
	// 'container1',
	// {
	// chart : {
	// type : 'column'
	// },
	// title : {
	// align : 'left',
	// text : 'Windows Severity Event'
	// },
	// accessibility : {
	// announceNewData : {
	// enabled : true
	// }
	// },
	// xAxis : {
	// type : 'category'
	// },
	// yAxis : {
	// title : {
	// text : 'Event Count'
	// }
	// },
	// legend : {
	// enabled : false
	// },
	// plotOptions : {
	// series : {
	// borderWidth : 0,
	// dataLabels : {
	// enabled : true,
	// format : '{point.y}'
	// },
	// point : {
	// events : {
	// click : function() {
	// // Your onclick
	// // function logic
	// // here
	// windowsSeverityBarClick(this.name);
	// }
	// }
	// }
	// }
	// },
	// tooltip : {
	// headerFormat : '<span style="font-size:11px">{series.name}</span><br>',
	// pointFormat : '<span style="color:{point.color}">{point.name}</span>:
	// <b>{point.y}</b> of total<br/>'
	// },
	// series : [ {
	// name : 'Windows Event',
	// colorByPoint : true,
	// data : dynamicData,
	// colors : dataColors
	// } ]
	// });
	//
	// var eventTypeArray = data4.map(function(item) {
	// return item.event_type;
	// });
	//
	// var eventCountArray = data4.map(function(item) {
	// return item.event_count;
	// });
	//
	// /* console.log(eventTypeArray); */
	//
	// var data = {
	// labels : eventTypeArray,
	// datasets : [ {
	// data : eventCountArray,
	// backgroundColor : dataColors,
	// hoverBackgroundColor : dataColors
	// } ]
	// };
	//
	// // Get the canvas element
	// var ctx = document.getElementById('linkpieChart1')
	// .getContext('2d');
	//
	// // Create a pie chart
	// var linkPieChart = new Chart(ctx, {
	// type : 'pie',
	// data : data,
	// options : {
	// legend : {
	// display : false
	// }
	// }
	// });
	//
	// var ulElement = document.getElementById('myList');
	// /* var colors = ['#FF6384', '#36A2EB', '#FFCE56', 'red']; */
	// var colors = dataColors;
	// ulElement.innerHTML = '';
	// // Loop through the array and create <li> elements
	// // dynamically
	// data4.forEach(function(item, index) {
	// // Create a new <li> element
	// var liElement = document.createElement('li');
	//
	// // Set the text content of the <li> element with the
	// // desired format
	// liElement.textContent = item.event_type + ' '
	// + item.event_count;
	// liElement.style.color = colors[index % colors.length];
	// // Append the <li> element to the <ul> element
	// liElement.onclick = function() {
	// // Call the windowsSeverityBarClick function with
	// // the corresponding event_type
	// windowsSeverityBarClick(item.event_type);
	// };
	// ulElement.appendChild(liElement);
	// });
	//
	// },
	// });

	// var l = window.location;
	// var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	// var serviceUrl = `${base_url}/dashboard/windowsEventLogWise`;
	// $
	// .ajax({
	// type : "GET",
	// url : serviceUrl,
	// data : {
	// from_date : $('#from_date').val(),
	// to_date : $('#to_date').val()
	// },
	// dataType : "json",
	// success : function(data4) {
	// var dynamicData = data4.map(function(item) {
	// return {
	// name : item.event_type,
	// y : item.event_count
	// };
	// });
	//
	// Highcharts
	// .chart(
	// 'container2',
	// {
	// chart : {
	// type : 'column'
	// },
	// title : {
	// align : 'left',
	// text : 'Windows Severity Event'
	// },
	// accessibility : {
	// announceNewData : {
	// enabled : true
	// }
	// },
	// xAxis : {
	// type : 'category'
	// },
	// yAxis : {
	// title : {
	// text : 'Event Count'
	// }
	// },
	// legend : {
	// enabled : false
	// },
	// plotOptions : {
	// series : {
	// borderWidth : 0,
	// dataLabels : {
	// enabled : true,
	// format : '{point.y}'
	// },
	// point : {
	// events : {
	// click : function() {
	// // Your onclick
	// // function logic
	// // here
	// windowsLogBarClick(this.name);
	// }
	// }
	// }
	// }
	// },
	// tooltip : {
	// headerFormat : '<span style="font-size:11px">{series.name}</span><br>',
	// pointFormat : '<span style="color:{point.color}">{point.name}</span>:
	// <b>{point.y}</b> of total<br/>'
	// },
	// series : [ {
	// name : 'Windows Event',
	// colorByPoint : true,
	// data : dynamicData
	// } ]
	// });
	//
	// },
	// });

	// var l = window.location;
	// var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	// var serviceUrl = `${base_url}/dashboard/windowsEventTrend`;
	// $.ajax({
	// type : "GET",
	// url : serviceUrl,
	// data : {
	// from_date : $('#from_date').val(),
	// to_date : $('#to_date').val()
	// },
	// dataType : "json",
	// success : function(data4) {
	//
	// var formattedData = data4.map(function(item) {
	// return {
	// name : item.event_type,
	// y : item.event_count
	// };
	// });
	//
	// var eventDates = data4.map(function(item) {
	// return item.event_type;
	// });
	//
	// // Create the Highcharts chart
	// Highcharts.chart('windowsEventTrend', {
	// chart : {
	// type : 'area'
	// },
	// xAxis : {
	// categories : eventDates,
	//
	// },
	// yAxis : {
	// title : {
	// useHTML : true,
	// text : 'Event Count'
	// }
	// },
	// tooltip : {
	// shared : true,
	// },
	// plotOptions : {
	// area : {
	// stacking : 'normal',
	// lineColor : '#666666',
	// lineWidth : 1,
	// marker : {
	// lineWidth : 1,
	// lineColor : '#666666'
	// }
	// }
	// },
	// series : [ {
	// name : 'Event Count',
	// data : formattedData
	// } ]
	// });
	//
	// }
	// });

	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/syslogEventTrend`;
	$.ajax({
		type : "GET",
		url : serviceUrl,
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val()
		},
		dataType : "json",
		success : function(data4) {

			var formattedData = data4.map(function(item) {
				return {
					name : item.event_type,
					y : item.event_count
				};
			});

			var eventDates = data4.map(function(item) {
				return item.event_type;
			});

			// Create the Highcharts chart
			Highcharts.chart('syslogEventTrend', {
				chart : {
					type : 'area'
				},
				xAxis : {
					categories : eventDates,

				},
				yAxis : {
					title : {
						useHTML : true,
						text : 'Event Count'
					}
				},
				tooltip : {
					shared : true,
				},
				plotOptions : {
					area : {
						stacking : 'normal',
						lineColor : '#666666',
						lineWidth : 1,
						marker : {
							lineWidth : 1,
							lineColor : '#666666'
						}
					}
				},
				series : [ {
					name : 'Event Count',
					data : formattedData
				} ]
			});

		}
	});

	// var l = window.location;
	// var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	// var serviceUrl = `${base_url}/dashboard/sourceWiseSummary`;
	// $.ajax({
	// url : serviceUrl,
	// type : 'GET',
	// data : {
	// from_date : $('#from_date').val(),
	// to_date : $('#to_date').val()
	// },
	// dataType : 'json',
	// success : function(data) {
	// $('#sourceWiseSummary').DataTable().clear().rows.add(data).draw();
	//
	// },
	// error : function(xhr, status, error) {
	// console.error('Error fetching data:', error);
	// }
	// });

}

function windowsSeverityBarClick(label) {
	console.log(label);
	$('#windows-event-modal').modal('show')
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/windowsSeverityBarClick`;
	$.ajax({
		type : "GET",
		url : serviceUrl,
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val(),
			label : label
		},
		dataType : "json",
		success : function(data4) {
			$('#windowsEventTable').DataTable().clear().rows.add(data4).draw();
		}
	})
}

function windowsLogBarClick(label) {
	console.log(label);
	$('#windows-event-modal').modal('show')
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/windowsLogBarClick`;
	$.ajax({
		type : "GET",
		url : serviceUrl,
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val(),
			label : label
		},
		dataType : "json",
		success : function(data4) {
			$('#windowsEventTable').DataTable().clear().rows.add(data4).draw();
		}
	})
}

function eventNameSummaryDetails(eventName) {
	$('#eventName-info-modal').modal('show')
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/eventCountWiseSummary`;
	$.ajax({
		type : "GET",
		url : serviceUrl,
		data : {
			/*
			 * from_date: $('#from_date').val(), to_date: $('#to_date').val(),
			 */
			eventName : eventName
		},
		dataType : "json",
		success : function(data4) {
			$('#eventSummaryTable').DataTable().clear().rows.add(data4).draw();
		}
	})

}

function souceSummaryCountClick(sourceName, severityType) {

	$('#source-info-modal').modal('show')
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/souceSummaryCountClick`;
	$.ajax({
		url : serviceUrl,
		type : 'GET',
		data : {
			from_date : $('#from_date').val(),
			to_date : $('#to_date').val(),
			sourceName : sourceName,
			severityType : severityType
		},
		dataType : 'json',
		success : function(data) {
			$('#sourceSummaryTable').DataTable().clear().rows.add(data).draw();

		},
		error : function(xhr, status, error) {
			console.error('Error fetching data:', error);
		}
	});
}
