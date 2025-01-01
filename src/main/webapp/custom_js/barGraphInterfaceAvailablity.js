$(function() {
	$.validator.setDefaults({
		submitHandler: function() {
			// alert( "Form successful submitted!" +$('#ip_address').val());
			/*document.getElementById("graph_ip").innerHTML = $('#ip_address')
				.val();*/
			generateGraph();

		}
	});
	$('#reportForm').validate({
		rules: {
			from_date: {
				required: true
			},

			to_date: {
				required: true

			},

		},
		messages: {
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
	// window.location.reload();
	$("#print").show()
}

$(function() {
	$("#backForm").click(function() {
		$("#graphFormDiv").show();
		$("#graphView").hide();
	});

	$("#SavePngButton").click(function() {

		html2canvas($("#graphView"), {
			onrendered : function(canvas) {
				// alert("onrendered emthod: ")
				canvas.toBlob(function(blob) {
					// you can changename
					var name = "BandwidthGraph.pdf";
					var imgData = canvas.toDataURL(blob);
					var doc = new jsPDF('p', 'mm');
					doc.addImage(imgData, 'PNG', 8, 10, 198, 105);
					doc.save(name);
				});
			}
		});
	});
});

$(function() {

	$("#SaveJPGButton").click(function() {

		html2canvas($("#graphView"), {
			onrendered : function(canvas) {
				// alert("onrendered emthod: ")
				canvas.toBlob(function(blob) {
					// you can changename
					saveAs(blob, "BandwidthGraph.jpeg");
				});
			}
		});
	});
});

function myFunction() {
	var change = document.getElementById("toggle");
	if (change.innerHTML == "Select All") {
		$('.checkers').each(function() {
			this.checked = true;
		});
		change.innerHTML = "Un-Select All";
	} else {
		$('.checkers').each(function() {
			this.checked = false;
		});
		change.innerHTML = "Select All";
	}
}


// Date range as a button
$('#daterange-btn').daterangepicker(
	{
		timePicker: true,
		timePickerIncrement: 10,
		ranges: {
			'Today': [moment().hours(0).minutes(0).seconds(0), moment().hours(23).minutes(59).seconds(59)],
			'Yesterday': [moment().hours(0).minutes(0).seconds(0).subtract(1, 'days'),
			moment().hours(23).minutes(59).seconds(59).subtract(1, 'days')],
			'Last 7 Days': [moment().hours(0).minutes(0).seconds(0).subtract(6, 'days'), moment().hours(23).minutes(59).seconds(59)],
			'Last 30 Days': [moment().hours(0).minutes(0).seconds(0).subtract(29, 'days'), moment().hours(23).minutes(59).seconds(59)],
			'This Month': [moment().startOf('month'),
			moment().endOf('month')],
			'Last Month': [
				moment().subtract(1, 'month').startOf('month'),
				moment().subtract(1, 'month').endOf('month')],
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

function groupDevices(option) {
	var grpname = option.value;
	// alert(grpname);
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/nodeReport/getGroupDeviceDetails";
	$
		.ajax({
			type: 'get',
			dataType: 'json',
			url: serviceUrl,
			data: 'groupName=' + grpname

		})
		.done(
			function(data5) {
				$('#example').show();
				var table = $('#example').DataTable();
				table.destroy();
				$('#example thead tr').clone(true).appendTo(
					'#example thead').addClass('searchRow');
				$('.searchRow').css('display', 'none');
				$('#example thead tr:eq(1) th')
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
										'<input type="text" class="customSearch" placeholder="Search" />');

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

				var table = $('#example')
					.DataTable(
						{
							dom: '<"pull-left"Bl><"pull-right"f>rtip',
							orderCellsTop: true,
							// fixedHeader: true,
							scrollX: true,
							data: data5,
							paging: false,
							scrollY: '350px',
							orderCellsTop: true,
							fixedHeader: true,
							buttons: [
								{
									text: '<div id="toggle" onclick="myFunction()">Select All</div>',
									className: 'newButton',
									action: function(e,
										dt, node,
										config) {

									},

								},
								{
									text: 'Toggle Search',

									action: function() {
										var element = document
											.querySelector('.searchRow');
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

								},

							],

						});

				if ($("#utype").val() == "User") {
					$(".newButton").hide();
					// table.columns([0,1]).visible(false);
				}

			});
}

function generateGraph() {
	$("#spinnerTopConnChart").show()

	var val = [];
     $('.checkers:checked').each(function(i){
       val[i] = $(this).val();
     });
     
     var ipAddress = val.join(",")

	$.ajax({
		type: 'POST',
		url: 'interfaceAvailabilityAverageGraph',
		dataType: 'json',
		data: 'ipAddressCheck=' + ipAddress + '&from_date=' +
			$('#from_date').val() + '&to_date=' + $('#to_date').val(),
		success: function(jsondata) {
			$("#spinnerTopConnChart").hide()
			$("#graphFormDiv").hide();
			$("#graphView").show();
			var start = +new Date();

			Highcharts.setOptions({
				global: {
					useUTC: false
				}
			});

			var chartType = 'column'; // Change to 'bar' for a bar chart

			Highcharts.chart('containervlx', {
				chart: {
					type: chartType,
					events: {
						load: function() {
							this.setTitle( {
								text: 'Availability#' + (new Date() - start) + 'ms'
							});
						}
					},
					zoomType: 'x'
				},
				colors:['red', '#50B432'],
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
				xAxis: {
					type: 'category',
					categories: jsondata.map(item => item[0]), // IP addresses on x-axis
					title: {
						text: 'IP Address'
					}
				},
				yAxis: {
					title: {
						text: 'Percentage'
					}
				},
				plotOptions: {
					column: {
						stacking: 'normal'
					}
				},
				series: [{
					name: 'Down Time Percent',
					data: jsondata.map(item => item[1]), // Downtime Percent on y-axis
					showInNavigator: true,
					tooltip: {
						valueDecimals: 1,
						valueSuffix: '%'
					}
				}, {
					name: 'UP Time Percent',
					data: jsondata.map(item => item[2]), // Uptime Percent on y-axis
					showInNavigator: true,
					tooltip: {
						valueDecimals: 1,
						valueSuffix: '%'
					}
				}]
			});
		}
	});
}

$(function() {

	$('#SavePngButton').click(function() {
		var element = $('#containervlx')[0];

		html2pdf(element, {
			margin: 15,
			filename: 'AverageInterfaceAvailability.pdf',
			image: { type: 'jpeg', quality: 0.98 },
			html2canvas: { scale: 2 },
			jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
		});
	});

	$('#SaveJPGButton').click(function() {
		var element = $('#containervlx')[0];

		html2canvas(element, {
			scale: 2, // You can adjust the scale as needed
		}).then(function(canvas) {
			var link = document.createElement('a');
			link.href = canvas.toDataURL('image/png');
			link.download = 'AverageInterfaceAvailability.png';
			link.click();
		});
	});


	$("#backForm").click(function() {
		$("#graphFormDiv").show();
		$("#graphView").hide();
	});

});
