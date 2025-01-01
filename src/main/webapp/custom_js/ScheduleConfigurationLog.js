
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
				'Today' : [ moment().hours(0).minutes(0).seconds(0), moment().hours(23).minutes(59).seconds(59) ],
				'Yesterday' : [ moment().hours(0).minutes(0).seconds(0).subtract(1, 'days'),
						moment().hours(23).minutes(59).seconds(59).subtract(1, 'days') ],
				'Last 7 Days' : [ moment().hours(0).minutes(0).seconds(0).subtract(6, 'days'), moment().hours(23).minutes(59).seconds(59)],
				'Last 30 Days' : [ moment().hours(0).minutes(0).seconds(0).subtract(29, 'days'), moment().hours(23).minutes(59).seconds(59)],
				'This Month' : [ moment().startOf('month'),
						moment().endOf('month') ],
				'Last Month' : [
						moment().subtract(1, 'month').startOf('month'),
						moment().subtract(1, 'month').endOf('month') ],
//				'Last 3 Month' : [
//							moment().subtract(4, 'month').startOf('month'),
//							moment().subtract(1, 'month').endOf('month') ],
//				'Last 6 Month' : [
//								moment().subtract(7, 'month').startOf('month'),
//								moment().subtract(1, 'month').endOf('month') ],
//				'Last Year' : [
//								moment().subtract(21, 'month').startOf('month'),
//								moment().subtract(10, 'month').endOf('month') ],
			},
			startDate : moment().subtract(29, 'days'),
			endDate : moment()
		}, function(start, end) {
			var from_date = document.getElementById("from_date");
			from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
			var to_date = document.getElementById("to_date");
			to_date.value = end.format('YYYY-MM-DD HH:mm:ss');

		})
//
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


function groupDevices(option) {
	var grpname = option.value;
	// alert(grpname);
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/nodeReport/getGroupDeviceDetails";
	// var serviceUrl = base_url + "/master/viewCompanyMaster";

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

// $(function() {
// // Initialize Select2 Elements
// $('.select2').select2()
//
// // Initialize Select2 Elements
// $('.select2bs4').select2({
// theme : 'bootstrap4'
// })
//
// });
$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
      scheduleconfigReport();
    },
  });
$('#scheduleconfigReportForm').validate({
    rules: {
    	
    	from_date:{
    		
    		required: true
    		
    	},
    	to_date:{
    		required: true
    	},
    	group_name:{
    		required: true
    	},
      

    },
    messages: {
      from_date: {
        required: "Please enter From Date and End Date",
        
      },
      to_date: {
        required: "",
        
      },
      group_name: {
        required: "Please enter Group name",
        
      },
      
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
      error.addClass('invalid-feedback');
      element.closest('.form-group').append(error);
    },
    highlight: function (element, errorClass, validClass) {
      $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
      $(element).removeClass('is-invalid');
    }
  });
 });

function scheduleConfigReport() {
var val = [];
     $('.checkers:checked').each(function(i){
       val[i] = $(this).val();
     });
     

     var ipAddress = val.join(",")

  if (!ipAddress.length > 0) {
      Swal.fire({
      position: "top",
      icon: "info",
      title: "Please Select atleast one checkbox",
      showConfirmButton: false,
      timer: 3000,
    });
  } 
  else {
    // Assuming you want to use temp instead of ip_address
    var l = window.location;
    var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
    var serviceUrl = `${base_url}/node/scheduleconfigReport`;

    $.ajax({
      type: "POST",
      url: serviceUrl,
      data: 'from_date='+$('#from_date').val()
	      /*  + '&runConfig='+runConfig
	        +'&startupConfig='+startupConfig
	        +'&backup='+backup*/
	        +'&to_date='+$('#to_date').val()
	        +'&group_name='+$('#group_name').val()
	        +'&ipAddressCheck='+ipAddress, // Join the array into a comma-separated string
    }).done(function (data) {
      sessionStorage.setItem("Device_IP", JSON.stringify(data));
    });
  }
}




