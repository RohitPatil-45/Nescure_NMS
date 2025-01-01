$(function() {
	// Define the data with unique checkbox ids
	var data4 = [
			[
					"<input type='checkbox' id='day1' name='dayCheck' class='checkers' value='Monday'/>",
					"Monday" ],
			[
					"<input type='checkbox' id='day2' name='dayCheck' class='checkers' value='Tuesday'/>",
					"Tuesday" ],
			[
					"<input type='checkbox' id='day3' name='dayCheck' class='checkers' value='Wednesday'/>",
					"Wednesday" ],
			[
					"<input type='checkbox' id='day4' name='dayCheck' class='checkers' value='Thursday'/>",
					"Thursday" ],
			[
					"<input type='checkbox' id='day5' name='dayCheck' class='checkers' value='Friday'/>",
					"Friday" ],
			[
					"<input type='checkbox' id='day6' name='dayCheck' class='checkers' value='Saturday'/>",
					"Saturday" ],
			[
					"<input type='checkbox' id='day7' name='dayCheck' class='checkers' value='Sunday'/>",
					"Sunday" ] ];

	// Initialize DataTable
	var table = $("#daysselector").DataTable({
		lengthChange : false,
		autoWidth : false,
		data : data4,
		paging : false, // Disables pagination
		searching : false, // Removes the search bar
		info : false, // Hides pagination info
		scrollX : false, // Disables horizontal scrolling
		columns : [ {
			title : "Select",
			orderable : false
		}, // Disable sorting for the Select column
		{
			title : "Days",
			orderable : false
		} // Disable sorting for the Days column
		]
	});

	// Append table controls
	table.buttons().container().appendTo(
			"#daysselector_wrapper .col-sm-6:eq(0)");
	
	getdata();
});

$(function() {
	$.validator
			.setDefaults({
				submitHandler : function() {
					// alert( "Form successful submitted!"
					// +$('#ip_address').val());
					// document.getElementById("graph_ip").innerHTML =
					// $('#ip_address')
					// .val();
					var checkboxes = document
							.querySelectorAll('input[name="dayCheck"]:checked'), values = [];
					Array.prototype.forEach.call(checkboxes, function(el) {
						values.push(el.value);

					});
					if (!values.length > 0) {

						Swal.fire({
							position : 'top',
							icon : 'info',
							title : 'Please Select atleast one Day',
							showConfirmButton : false,
							timer : 3000
						})
					} else {
						var dayArray = new Array();
						dayArray = values.toString().split(",");
						datasave(dayArray);
					}

				}
			});
	$('#reportForm').validate({
		rules : {

			Starttime : {

				required : true

			},
			Endtime : {

				required : true

			}

		},
		messages : {
			Starttime : {
				required : "Please select time",

			},
			Endtime : {

				required : "Please select time",

			},

			terms : "Please accept our terms"
		},
		errorElement : 'span',
		errorPlacement : function(error, element) {
			error.addClass('invalid-feedback');
			element.closest('.form-group').append(error);
		},
		highlight : function(element, errorClass, validClass) {
			$(element).addClass('is-invalid');
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).removeClass('is-invalid');
		}
	});
});

function datasave(dayArray) {
	console.log(dayArray);
	console.log($('#Starttime').val());
	console.log($('#Endtime').val());
	
	  $('#spinnerTopConnChart').show();

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/saveSLADataMaster";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'dayArray=' + dayArray + '&from_date=' + $('#Starttime').val()
				+ '&to_date=' + $('#Endtime').val(),
	
		success : (function(eventData) {

			
			  if(eventData=== "success"){
					Swal.fire({
					position: 'top',
					icon: 'success',
					title: 'SLA Conditions Updated Successfully',
					showConfirmButton: true,
					timer: 3000
					})
					
					.then(() => {
						 var l = window.location;
						   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
						  window.location.href =base_url+"/master/slaMainMaster";

						})
							      
							    }else{
							    	 
							    	Swal.fire({
							    		  position: 'top',
							    		  icon: 'warning',
							    		  title: 'Failed',
							    		  showConfirmButton: false,
							    		  timer: 3000
							    		})
									     
								    }
			  
			  
			  $('#spinnerTopConnChart').hide();
		})
	})
}

function getdata(){
	// alert("inside get data");
		
		var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/getslamasterdata";

		$.ajax({
			url : serviceUrl,
			type : 'GET',
			dataType : 'json',
			

		}).done(
				function(data4) {
					
					  var table = $("#interfaIpAssignTable").DataTable({
					        lengthChange: false,
					        autoWidth: false,
					        data: data4,
					        pageLength: 5,
					        scrollX: true,
					        scrollY: true,
					      });
					      table.buttons().container().appendTo("#interfaIpAssignTable_wrapper .col-sm-6:eq(0)");
					
				});
		
		
	}