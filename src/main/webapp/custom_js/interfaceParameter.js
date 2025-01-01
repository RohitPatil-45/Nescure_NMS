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
//
// $(function() {
//
// $('[data-mask]').inputmask()
// // Bootstrap Duallistbox
//
// })
//

function groupDevices(option) {
	var grpname = option.value;
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getGroupInterface";
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


function addInterfaceParameter()
{
	var checkboxValues = [];
	$('input[name=ipAddressCheck]:checked').map(function() {
	            checkboxValues.push($(this).val());
	});
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/addInterfaceParameter";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'ids=' +checkboxValues
	        + '&monitoring='+$('#monitoring').val()
	        +'&bwHistory='+$('#bwHistory').val()
	        +'&crc='+$('#crc').val()
	        +'&bwThreshold='+$('#bwThreshold').val()
	        +'&mailAlert='+$('#mailAlert').val()
	        +'&smsAlert='+$('#smsAlert').val()
	        +'&autoTicketing='+$('#autoTicketing').val()
	        +'&procureBW='+$('#procureBW').val(),

	    }).done(function (data) {
		    	console.log(data);

			    if(data=== "success"){
	Swal.fire({
	position: 'top',
	icon: 'success',
	title: 'Interface Parameter Successfully set',
	showConfirmButton: true,
	timer: 3000
	})
	
	.then(() => {
		 var l = window.location;
		   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
		  window.location.href =base_url+"/master/interfaceParameter";

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
		    });
}




