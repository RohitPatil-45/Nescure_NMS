window.onload = function() {

	
var deviceIPP = document.getElementById("deviceIP");
var intNamee=document.getElementById("intName");
	
var deviceIP = deviceIPP.value;
var intName=intNamee.value;
	
	// Interface Info

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceInfo";
	$
			.ajax({
				type : 'POST',
				url : serviceUrl,
				data : {
					deviceIP : deviceIP,
					intName : intName
				},
				dataType : 'json',
				success : function(data) {

					for (var i = 0; i < data.length; i++) {
						document.getElementById("nodeIPID").textContent = data[i].node_ip;
					document.getElementById("intNameID").textContent = data[i].int_name;
					document.getElementById("aliasID").textContent = data[i].alias_name;
					document.getElementById("intTypeID").textContent = data[i].int_type;
					document.getElementById("intSpcID").textContent = data[i].int_id;
					document.getElementById("adminStatusID").textContent = data[i].admin_status;
					document.getElementById("operStatusID").textContent = data[i].oper_status;
					document.getElementById("intIPID").textContent = data[i].int_ip;
					document.getElementById("intMACID").textContent = data[i].int_mac;
					document.getElementById("mtuID").textContent = data[i].mtu;
					document.getElementById("interfaceInputError").textContent = data[i].input_error;
					document.getElementById("interfaceOutputError").textContent = data[i].output_error;
					document.getElementById("discardInput").textContent = data[i].discard_input;
					document.getElementById("discardOutput").textContent = data[i].discard_output;
					document.getElementById("ICMPStatusID").textContent = data[i].ICMPStatus;
						if(data[i].crc=="noSuchObject"){
							document.getElementById("crcID").textContent = "NA";
						}else{
							document.getElementById("crcID").textContent = data[i].crc;
						}
						
						document.getElementById("procBwID").textContent = Math.round(data[i].procure_bw/1024 * 100)/100
								+ " Kbps";
						document.getElementById("throughputID").textContent = Math.round(data[i].out_bw/1024 * 100)/100
								+ " Kbps(Transmit) "
								+ Math.round(data[i].in_bw/1024 * 100)/100
								+ " Kbps(Receive)";

					}

				}
			});

	// Interface Status History

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceStatusHistory";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			deviceIP : deviceIP,
			intName : intName
		},
		dataType : 'json',
		success : function(data) {
			// alert(data);

			$.each(data, function(i, item) {
				$('<tr>').html(
						"<td>" + data[i].event_timestamp + "</td><td>"
								+ data[i].int_name + "</td><td>"
								+ data[i].int_status + "</td><td>"
								+ data[i].int_status_type + "</td>").appendTo(
						'#interfaceSummary');
			});
		}
	});

	// Interface CRC Log

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceCRCLog";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			deviceIP : deviceIP,
			intName : intName
		},
		dataType : 'json',
		success : function(data) {
			// alert(data);

			$.each(data, function(i, item) {
				$('<tr>').html(
						"<td>" + data[i].event_timestamp + "</td><td>"
								+ data[i].int_name + "</td><td>"
								+ data[i].old_crc + "</td><td>"
								+ data[i].new_crc + "</td>").appendTo(
						'#crcLogID');
			});
		}
	});

	// -------------
	// - UPTIME BAR CHART -
	// -------------

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceUptimeLog";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			deviceIP : deviceIP,
			intName : intName
		},
		dataType : 'json',
		success : function(data) {

		
				var areaChartData = {
					labels : data[0].datetime,
					datasets : [ {
						label : 'Downtime',
						backgroundColor : 'rgba(60,141,188,0.9)',
						borderColor : 'rgba(60,141,188,0.8)',
						pointRadius : false,
						pointColor : '#3b8bba',
						pointStrokeColor : 'rgba(60,141,188,1)',
						pointHighlightFill : '#fff',
						pointHighlightStroke : 'rgba(60,141,188,1)',
						data : data[0].downtime,
					}, {
						label : 'Uptime',
						backgroundColor : 'rgba(210, 214, 222, 1)',
						borderColor : 'rgba(210, 214, 222, 1)',
						pointRadius : false,
						pointColor : 'rgba(210, 214, 222, 1)',
						pointStrokeColor : '#c1c7d1',
						pointHighlightFill : '#fff',
						pointHighlightStroke : 'rgba(220,220,220,1)',
						data : data[0].uptime,
					}, ]
				}

				var barChartCanvas = $('#sales-chart').get(0).getContext('2d')
				var barChartData = $.extend(true, {}, areaChartData)
//				var temp0 = areaChartData.datasets[0]
//				var temp1 = areaChartData.datasets[1]

//				barChartData.datasets[0] = temp1
//				barChartData.datasets[1] = temp0

				var barChartOptions = {
					responsive : true,
					maintainAspectRatio : false,
					datasetFill : false
				}

				new Chart(barChartCanvas, {
					type : 'bar',
					data : areaChartData,
					options : barChartOptions
				})

			

		}

	});

	/* Real time Bandwidth Utilization */

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceBandwidthUtil";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			deviceIP : deviceIP,
			intName : intName
		},
		dataType : 'json',
		success : function(jsondata) {
			// alert(jsondata);

			
			Highcharts.stockChart('container_bandwidth', {
			    chart: {
			        events: {
			            load: function () {
			            	
			                // set up the updating of the chart each second
			                var series = this.series[0];
			                var series2 = this.series[1];
			                
			                
			                setInterval(function () {
			          
			                	var l = window.location;
			                	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
			                	var serviceUrl = base_url + "/dashboard/interfaceBandwidthUtil";
			                	$.ajax({
			                		type : 'POST',
			                		url : serviceUrl,
			                		data : {
			                			deviceIP : deviceIP,
			                			intName : intName
			                		},
			                		dataType : 'json',
			                		success : function(jsondata1) {
			                	
			                			var x = (new Date()).getTime(), // current
																		// time
				                    		y=Math.round(jsondata1[0].in_traffic/1024),
				                    		z=Math.round(jsondata1[0].out_traffic/1024);
				                    
				                    series.addPoint([x, y], true, true);
				                    series2.addPoint([x, z], true, true);
			                			
			                		}
			                		
			                	});
			              
			                }, 2000);
			               
			            }
			        }
			    },

			    accessibility: {
			        enabled: false
			    },

			    time: {
			        useUTC: false
			    },

			    rangeSelector: {
			        buttons: [{
			            count: 1,
			            type: 'minute',
			            text: '1M'
			        }, {
			            count: 5,
			            type: 'minute',
			            text: '5M'
			        }, {
			            type: 'all',
			            text: 'All'
			        }],
			        inputEnabled: false,
			        selected: 0
			    },

			    
			    exporting: {
			        enabled: false
			    },

			    
			    series: [{
			        name: 'In Traffic',
			        data: (function () {
			            // generate an array of random data
			            var data = [],
			                time = (new Date()).getTime(),
			                i;

			            for (i = -999; i <= 0; i += 1) {
			                data.push([
			                    time + i * 1000,
			                    Math.round(jsondata[0].in_traffic/1024)
			                ]);
			            }
			            return data;
			        }())
			    },
			    {
			        name: 'Out Traffic',
			        data: (function () {
			            // generate an array of random data
			            var data = [],
			                time = (new Date()).getTime(),
			                i;

			            for (i = -999; i <= 0; i += 1) {
			                data.push([
			                    time + i * 1000,
			                    Math.round(jsondata[0].out_traffic/1024)
			                ]);
			            }
			            return data;
			        }())
			    }]
			});
			

		}
	});
	
	
	// Link Latency and Jitter Latency
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceLinkLatency";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			deviceIP : deviceIP,
			intName : intName
		},
		dataType : 'json',
		success : function(jsondata) {
			
			Highcharts.stockChart('link_latency_container', {
			    chart: {
			        events: {
			            load: function () {
			            	
			                // set up the updating of the chart each second
			                var series = this.series[0];
			                var series2 = this.series[1];
			                
			                
			                setInterval(function () {
			          
			                	var l = window.location;
			                	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
			                	var serviceUrl = base_url + "/dashboard/interfaceLinkLatency";
			                	$.ajax({
			                		type : 'POST',
			                		url : serviceUrl,
			                		data : {
			                			deviceIP : deviceIP,
			                			intName : intName
			                		},
			                		dataType : 'json',
			                		success : function(jsondata1) {
			                	
			                			var x = (new Date()).getTime(), // current
																		// time
//				                    		y=Math.round(jsondata1[0].link_latency/1024);
//				                    		z=Math.round(jsondata1[0].link_jitter/1024);
			                			y = jsondata1[0].link_latency;
			                			z = jsondata1[0].link_jitter;
				                    
				                    series.addPoint([x, y], true, true);
				                    series2.addPoint([x, z], true, true);
			                			
			                		}
			                		
			                	});
			              
			                }, 2000);
			               
			            }
			        }
			    },

			    accessibility: {
			        enabled: false
			    },

			    time: {
			        useUTC: false
			    },

			    rangeSelector: {
			        buttons: [{
			            count: 1,
			            type: 'minute',
			            text: '1M'
			        }, {
			            count: 5,
			            type: 'minute',
			            text: '5M'
			        }, {
			            type: 'all',
			            text: 'All'
			        }],
			        inputEnabled: false,
			        selected: 0
			    },

			    
			    exporting: {
			        enabled: false
			    },

			    
			    series: [{
			        name: 'Link Latency',
			        data: (function () {
			            // generate an array of random data
			            var data = [],
			                time = (new Date()).getTime(),
			                i;

			            for (i = -999; i <= 0; i += 1) {
			                data.push([
			                    time + i * 1000,
			                    jsondata[0].link_latency
			                ]);
			            }
			            return data;
			        }())
			    },
//			    {
//			        name: 'Out Traffic',
//			        data: (function () {
//			            // generate an array of random data
//			            var data = [],
//			                time = (new Date()).getTime(),
//			                i;
//
//			            for (i = -999; i <= 0; i += 1) {
//			                data.push([
//			                    time + i * 1000,
//			                    Math.round(jsondata[0].out_traffic/1024)
//			                ]);
//			            }
//			            return data;
//			        }())
//			    }
			    ]
			});
			
			//Link Jitter
			Highcharts.stockChart('link_Jitter_container', {
			    chart: {
			        events: {
			            load: function () {
			            	
			                // set up the updating of the chart each second
			                var series = this.series[0];
			                var series2 = this.series[1];
			                
			                
			                setInterval(function () {
			          
			                	var l = window.location;
			                	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
			                	var serviceUrl = base_url + "/dashboard/interfaceLinkLatency";
			                	$.ajax({
			                		type : 'POST',
			                		url : serviceUrl,
			                		data : {
			                			deviceIP : deviceIP,
			                			intName : intName
			                		},
			                		dataType : 'json',
			                		success : function(jsondata1) {
			                	
			                			var x = (new Date()).getTime(), // current
																		// time
//				                    		y=Math.round(jsondata1[0].link_latency/1024);
//				                    		z=Math.round(jsondata1[0].link_jitter/1024);
			                			y = jsondata1[0].link_jitter;
			                			z = jsondata1[0].link_latency;
				                    
				                    series.addPoint([x, y], true, true);
				                    series2.addPoint([x, z], true, true);
			                			
			                		}
			                		
			                	});
			              
			                }, 2000);
			               
			            }
			        }
			    },

			    accessibility: {
			        enabled: false
			    },

			    time: {
			        useUTC: false
			    },

			    rangeSelector: {
			        buttons: [{
			            count: 1,
			            type: 'minute',
			            text: '1M'
			        }, {
			            count: 5,
			            type: 'minute',
			            text: '5M'
			        }, {
			            type: 'all',
			            text: 'All'
			        }],
			        inputEnabled: false,
			        selected: 0
			    },

			    
			    exporting: {
			        enabled: false
			    },

			    
			    series: [{
			        name: 'Average Jitter',
			        data: (function () {
			            // generate an array of random data
			            var data = [],
			                time = (new Date()).getTime(),
			                i;

			            for (i = -999; i <= 0; i += 1) {
			                data.push([
			                    time + i * 1000,
			                    jsondata[0].link_jitter
			                ]);
			            }
			            return data;
			        }())
			    },
//			    {
//			        name: 'Out Traffic',
//			        data: (function () {
//			            // generate an array of random data
//			            var data = [],
//			                time = (new Date()).getTime(),
//			                i;
//
//			            for (i = -999; i <= 0; i += 1) {
//			                data.push([
//			                    time + i * 1000,
//			                    Math.round(jsondata[0].out_traffic/1024)
//			                ]);
//			            }
//			            return data;
//			        }())
//			    }
			    ]
			});
			

		}
	});
}
// Report Date range as a button
$('#daterange-btn').daterangepicker(
	//		alert("in datepicker hiiii"),
	{
		timePicker: true,
		timePickerIncrement: 10,
		ranges: {
			'Today': [moment(), moment()],
			'Yesterday': [moment().subtract(1, 'days'),
			moment().subtract(1, 'days')],
			'Last 7 Days': [moment().subtract(6, 'days'), moment()],
			'Last 30 Days': [moment().subtract(29, 'days'), moment()],
			'This Month': [moment().startOf('month'),
			moment().endOf('month')],
			'Last Month': [
				moment().subtract(1, 'month').startOf('month'),
				moment().subtract(1, 'month').endOf('month')]
		},
		startDate: moment().subtract(29, 'days'),
		endDate: moment()
	}, function(start, end) {
		var from_date = document.getElementById("from_date");
		from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
		var to_date = document.getElementById("to_date");
		to_date.value = end.format('YYYY-MM-DD HH:mm:ss');

	})

$('#reservationtime').daterangepicker({
	timePicker: true,
	timePickerIncrement: 10,
	locale: {
		format: 'MM/DD/YYYY hh:mm:ss'
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
		rules: {
			from_date: { required: true },
			to_date: { required: true },
		},
		messages: {
			from_date: { required: "Please provide From_date" },
			to_date: { required: "Please provide To_date" },
		},
		errorElement: "span",
		errorPlacement: function(error, element) {
			error.addClass("invalid-feedback");
			element.closest(".form-group").append(error);
		},
		highlight: function(element, errorClass, validClass) {
			$(element).addClass("is-invalid");
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass("is-invalid");
		},
	});
});
$("#exportReportLatency").click(function() {
	if ($("#exportNodeReportGraph").valid()) {
		var obj = document.getElementById("deviceIP");
		var ip = obj.value;
		var l = window.location;
		var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;

		var interfaceBandwidth = `${base_url}/interfaceReport/interfaceBandwidth`;
		var interfaceUptime = `${base_url}/interfaceReport/interfaceUptime`;
		var interfaceStatusEvents = `${base_url}/interfaceReport/interfaceStatusEvents`;
		var targetUrl;
		var radioValue = $("input[name='report']:checked").val();
		switch (radioValue) {
			case 'interfaceBandwidth':
				targetUrl = interfaceBandwidth;
				break;
			case 'interfaceUptime':
				targetUrl = interfaceUptime;
				break;
			case 'interfaceStatusEvents':
				targetUrl = interfaceStatusEvents;
				break;
			default:
				Swal.fire({
					position: "top",
					icon: "warning",
					title: "Please select at least one Radio Button",
					showConfirmButton: false,
					timer: 3000,
				});
				return;
		}
		document.getElementById('exportNodeReportGraph').action = targetUrl;
		document.getElementById('exportNodeReportGraph').submit();
	}
});

$("#exportGraphLatency").click(function() {
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;

	var interfaceBandwidthGraph = `${base_url}/nodeReport/interfaceBandwidthGraph`;
	var interfaceUptimeGraph = `${base_url}/nodeReport/interfaceUptimeGraph`;
	var targetUrl;
	var radioValue = $("input[name='graph']:checked").val();

	switch (radioValue) {
		case 'interfaceBandwidthGraph':
			targetUrl = interfaceBandwidthGraph;
			break;
		case 'interfaceUptimeGraph':
			targetUrl = interfaceUptimeGraph;
			break;
		default:
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Please select at least one Radio Button",
				showConfirmButton: false,
				timer: 3000,
			});
			return;
	}
	document.getElementById('exportNodeReportGraph').action = targetUrl;
	document.getElementById('exportNodeReportGraph').submit();
});


function speedtestinterfaceonclick(){
	
	
	var ip= $('#nodeipdetailsstore').text();
	var infname= $('#interfacenamedetailsstroe').text();
	
//	alert(ip);
//	alert(infname);
	
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/node/nodeSpeedTest?nodeIP="+ip+"&intName="+infname+"";
	window.location.href = serviceUrl;
	
}