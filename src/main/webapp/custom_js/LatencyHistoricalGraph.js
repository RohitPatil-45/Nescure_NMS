$(function() {
	$.validator.setDefaults({
		submitHandler: function() {
			//alert( "Form successful submitted!" +$('#ip_address').val());

			document.getElementById("graph_ip").innerHTML = $('#ip_address')
				.val();
			generateGraph();

		}
	});
	$('#latencyGraphForm').validate({
		rules: {

			ip_address: {

				required: true

			},
			from_date: {
				required: true
			},

			to_date: {
				required: true

			},

		},
		messages: {
			ip_address: {
				required: "Please enter a IP address",

			},
			from_date: {
				required: "Please select ",

			},
			to_date: {
				required: "date range picker",
			},

			terms: "Please accept our terms"
		},
		errorElement: 'span',
		errorPlacement: function(error, element) {
			error.addClass('invalid-feedback');
			element.closest('.form-group').append(error);
		},
		highlight: function(element, errorClass, validClass) {
			$(element).addClass('is-invalid');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass('is-invalid');
		}
	});
});

function printReport() {
	alert("Print");
	$("#print").hide()
	window.print();
	//   window.location.reload();
	$("#print").show()
}
// Date range as a button
$('#daterange-btn').daterangepicker(
	{
		timePicker: true,
		timePickerIncrement: 10,
		ranges: {
			'Today': [moment().hours(0).minutes(0).seconds(0),
			moment().hours(23).minutes(59).seconds(59)],
			'Yesterday': [
				moment().hours(0).minutes(0).seconds(0).subtract(1,
					'days'),
				moment().hours(23).minutes(59).seconds(59).subtract(1,
					'days')],
			'Last 7 Days': [
				moment().hours(0).minutes(0).seconds(0).subtract(6,
					'days'),
				moment().hours(23).minutes(59).seconds(59)],
			'Last 30 Days': [
				moment().hours(0).minutes(0).seconds(0).subtract(29,
					'days'),
				moment().hours(23).minutes(59).seconds(59)],
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

//$('#reservationtime').daterangepicker({
//	timePicker : true,
//	timePickerIncrement : 10,
//	locale : {
//		format : 'MM/DD/YYYY hh:mm:ss'
//	}
//
//}, function(start, end) {
//
//	var from_date = document.getElementById("from_date");
//	from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
//	var to_date = document.getElementById("to_date");
//	to_date.value = end.format('YYYY-MM-DD HH:mm:ss');
//});

//function ShowDateDiv() {
//	 alert("Date pickerr");
//	$("#datetime_div").hide();
//	$("#date_div").show();
//}
//
//function ShowTimeDiv() {
//	 alert("Time pickerr");
//	$("#datetime_div").show();
//	$("#date_div").hide();
//}

$(function() {

	$('[data-mask]').inputmask()
	// Bootstrap Duallistbox

})

function groupDevices(option) {
	var grpname = option.value;
	var l = window.location;
	var ipMask = null;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/nodeReport/getGroupDeviceLists";
	$.ajax({
		type: "get",
		dataType: "json",
		url: serviceUrl,
		data: "groupName=" + grpname,
	}).done(function(data5) {
		ipMask = data5;

		var optionsString = '';
		$.each(ipMask, function(index, value) {
			optionsString += '<option value="' + value + '">' + value + '</option>';
		});

		$('#ip_address').html(optionsString).select2();
		$('#ip_address').val(ipMask[0]).trigger('change');
	});
}
var chart;
function generateGraph() {

	//alert("generate Graph")
	$("#spinnerTopConnChart").show()

	$.ajax({
		type: 'POST',
		url: 'plotLatencyGraph',
		dataType: 'json',
		data: 'ip_address=' + $('#ip_address').val() + '&from_date='
			+ $('#from_date').val() + '&to_date=' + $('#to_date').val(),
		success: function(jsondata_1) {
			$("#spinnerTopConnChart").hide()
			$("#graphFormDiv").hide();
			$("#graphView").show();
			var start = +new Date();
			Highcharts.setOptions({
				global: {
					useUTC: false
				}
			});

			chart = Highcharts.stockChart('latencyGraphContainer', {
				chart: {
					events: {
						load: function() {
							this.setTitle(null, {
								text: 'Latency & Packet Drop#' //+ (new Date() - start) + 'ms'

							});

						}
					},
					zoomType: 'x',
					panning: true,
					boost: {
						enabled: true, // Enable the boost module
						useGPUTranslations: true
					}
				},
				rangeSelector: {
					buttons: [{
						type: 'day',
						count: 3,
						text: '3d'
					}, {
						type: 'week',
						count: 1,
						text: '1w'
					}, {
						type: 'month',
						count: 1,
						text: '1m'
					}, {
						type: 'month',
						count: 6,
						text: '6m'
					}, {
						type: 'year',
						count: 1,
						text: '1y'
					}, {
						type: 'all',
						text: 'All'
					}],
					selected: 3
				},
				yAxis: {
					title: {
						text: 'Latency & Packet Loss'
					}
				},
				subtitle: {
					text: 'Latency & Packet Loss '
				},
				plotOptions: {
					series: {
						turboThreshold: 0
						// Comment out this code to display error
					}
				},
				series: [{
					name: 'Latency',
					data: jsondata_1.latency,
					showInNavigator: true,
					tooltip: {
						valueDecimals: 1,
						valueSuffix: 'ms'
					}

				}, {
					name: 'Packet Loss',
					data: jsondata_1.packet_drop,
					showInNavigator: true,
					tooltip: {
						valueDecimals: 1,
						valueSuffix: '%'
					}
				}],
				exporting: {
					filename: 'Latency Graph'
				}

			});

		}

	});

}

$(function() {

	$('#SavePngButton').click(function() {
		var element = $('#latencyGraphContainer')[0];

		html2pdf(element, {
			margin: 15,
			filename: 'BandwidthGraph.pdf',
			image: { type: 'jpeg', quality: 0.98 },
			html2canvas: { scale: 2 },
			jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
		});
	});

	$('#SaveJPGButton').click(function() {
		var element = $('#latencyGraphContainer')[0];

		html2canvas(element, {
			scale: 2, // You can adjust the scale as needed
		}).then(function(canvas) {
			var link = document.createElement('a');
			link.href = canvas.toDataURL('image/png');
			link.download = 'BandwidthGraph.png';
			link.click();
		});
	});


	$("#backForm").click(function() {
		$("#graphFormDiv").show();
		$("#graphView").hide();
	});

});

/*$(function() {

	$("#SaveJPGButton").click(function() {

		html2canvas($("#graphView"), {
			onrendered : function(canvas) {
				//alert("onrendered emthod: ")
				canvas.toBlob(function(blob) {
					//you can changename
					saveAs(blob, "BandwidthGraph.jpeg");
				});
			}
		});
	});
});*/
