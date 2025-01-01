window.onload = function() {
	
	
	  var l = window.location;
	  	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  	var serviceUrl = base_url + "/master/adConfigListing";
	     $.ajax({
	         type: 'GET',
	         url: serviceUrl,
	         dataType: 'json',
	         success: function(data4) {
	        // alert(data4)
	             var table = $('#example1').DataTable({
	                 lengthChange: false,
	                 autoWidth: false,
	                 data: data4,
	                  "pageLength": 5,
	                  scrollX:true,
	                  scrollY:true
	             });

	             table.buttons().container()
	                     .appendTo('#example1_wrapper .col-sm-6:eq(0)');

	         }

	     });
	
	
}




$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
   // alert( "Form successful submitted!" );
    	ADAddUpdate();
      
    }
  });
  $('#addADConfigId').validate({
    rules: {
    	
    	ldapServerName:{
    		
    		required: true
    		
    	},
    	username:{
    		required: true
    	},
    	
    	logonDomain:{
    		required: true
    		
    	},
    	
    	password: {
        required: true,
   
      },
      
      serverPort: {
          required: true,
     
        },
      

    },
    messages: {
    	ldapServerName: {
        required: "Please enter LDAP server name"
      },
      
      logonDomain: {
    	  required: "Please enter logon domain"
      },
      
      username: {
    	  required: "Please enter the username"
      },
      
      password: {
          required: "Please enter the password"
        },
      
        serverPort: {
    	  required: "Please enter the server port"
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


// function editADConfig(id) {
// alert("edit called"+id)
// var l = window.location;
// var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
// var serviceUrl = base_url + "/master/editADConfig";
// $.ajax({
// type : 'POST',
// url : serviceUrl,
// data : 'id=' + id,
// }).done(function(data) {
// //alert(data)
// });
// }

function deleteADConfig(id) {

	Swal.fire({
		position : 'top',
		  title: 'Are you sure?',
		  text: "You want to delete the record:"+id,
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes, delete it!'
		})
	.then((result) => {
		  if (result.value) {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/deleteADConfig";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'id=' + id,
	}).done(function(data) {

		if (data === "success") {

			Swal.fire({
				position : 'top',
				icon : 'success',
				title : 'Delete Record Successfully',
				showConfirmButton : false,
				timer : 3000
			}).then(() => {
				 var l = window.location;
				   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
				  window.location.href =base_url+"/master/addADConfigForm";

				})

		} else {

			Swal.fire({
				position : 'top',
				icon : 'warning',
				title : 'Record Not Deleted',
				showConfirmButton : false,
				timer : 3000
			})

		}
	});
		  }
	});
}
function ADAddUpdate(){
	
	var dbid=$('#id').val()
	// alert("DB ID"+dbid)
	
	if(dbid==0)
		{
	//	alert("Add action call"+dbid)
		addADConfig();
		
		}
	else
		{
		//alert("Update action call"+dbid)
		updateADConfig();
		}
	
}


function addADConfig()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/addADConfig";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'ldapServerName='+$('#ldapServerName').val()
	        + '&username='+$('#username').val()
	        +'&logonDomain='+$('#logonDomain').val()
	        +'&password='+$('#password').val()
	        +'&serverPort='+$('#serverPort').val(),
	        
	    }).done(function (data) {
	    	
	    	
	    // alert(data)
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'AD Configuration Added Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/addADConfigForm";

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



function updateADConfig()
{
//	alert("update call");
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/updateADConfig";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'ldapServerName='+$('#ldapServerName').val()
	        + '&username='+$('#username').val()
	        +'&logonDomain='+$('#logonDomain').val()
	        +'&password='+$('#password').val()
	        +'&serverPort='+$('#serverPort').val()
	        +'&id='+$('#id').val(),
	    }).done(function (data) {
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Update AD Data Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/addADConfigForm";

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
