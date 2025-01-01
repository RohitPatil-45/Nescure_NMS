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
// $('#daterange-btn').daterangepicker(
// {
// timePicker : true,
// timePickerIncrement : 10,
// ranges : {
// 'Today' : [ moment(), moment() ],
// 'Yesterday' : [ moment().subtract(1, 'days'),
// moment().subtract(1, 'days') ],
// 'Last 7 Days' : [ moment().subtract(6, 'days'), moment() ],
// 'Last 30 Days' : [ moment().subtract(29, 'days'), moment() ],
// 'This Month' : [ moment().startOf('month'),
// moment().endOf('month') ],
// 'Last Month' : [
// moment().subtract(1, 'month').startOf('month'),
// moment().subtract(1, 'month').endOf('month') ]
// },
// startDate : moment().subtract(29, 'days'),
// endDate : moment()
// }, function(start, end) {
// var from_date = document.getElementById("from_date");
// from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
// var to_date = document.getElementById("to_date");
// to_date.value = end.format('YYYY-MM-DD HH:mm:ss');
//
// })

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

function groupDevices(option) {
	var grpname = option.value;
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getGroupInterfaceDetails";
	$
			.ajax({
				type : 'get',
				dataType : 'json',
				url : serviceUrl,
				data : 'groupName=' + grpname
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
											dom : '<"pull-left"Bl><"pull-right"f>rtip',
											orderCellsTop : true,
											// fixedHeader: true,
											scrollX : true,
											data : data5,
											paging : false,
											scrollY : '350px',
											orderCellsTop : true,
											fixedHeader : true,
											buttons : [
													{
														text : '<div id="toggle" onclick="myFunction()">Select All</div>',
														className : 'newButton',
														action : function(e,
																dt, node,
																config) {

														},

													},
													{
														text : 'Toggle Search',

														action : function() {
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
