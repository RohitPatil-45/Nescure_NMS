window.onload = function() {
	
	
	// Hide the Divs by default
	
	document.getElementById("usernameid").style.display = 'none';
	document.getElementById("passwordid").style.display = 'none';
	
	// end
	
	
	  var l = window.location;
	  	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  	var serviceUrl = base_url + "/master/emailConfigListing";
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
    	EmailAddUpdate();
      
    }
  });
  $('#addEmailConfigId').validate({
    rules: {    	
    	
    	emailId:{
    		
    		required: true,
    		email: true,
    		
    	},
    	
    	smtpServer:{
    		required: true
    	},
    	
    	port:{
    		required: true
    		
    	},
    	
    	timeout: {
        required: true
   
      },
      
      isSslTls: {
          required: false
     
        },
        
        isSmtpAuth: {
            required: false
       
          },
      
     
    	  username: {
              required:true 
                  
          },
          
    	 password:{
    		 required: true
              
        	  
       	  },
      
        

    },
    
    messages: {
    		emailId:{
    		
    			required: "Please enter a email address",
    	        email: "Please enter a vaild email address"
    		
    	},
    	
    	smtpServer:{
    		required: "Please enter SMTP server"
    	},
    	
    	port:{
    		required: "Please enter port"
    		
    	},
    	
    	timeout: {
        required: "Please enter timeout"
   
      },
     
      username: {
          required: "Please enter username"
     
        },
        
        password: {
            required: "Please enter password"
       
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


function editEmailConfig(id) {
	alert("edit called"+id)
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/editEmailConfig";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'id=' + id,
	}).done(function(data) {
		// alert(data)
	});
}

function deleteEmailConfig(id) {

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
	var serviceUrl = base_url + "/master/deleteEmailConfig";
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
				  window.location.href =base_url+"/master/addEmailConfigForm";

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
function EmailAddUpdate(){
	
	var dbid=$('#id').val()
// alert("DB ID"+dbid)
	
	if(dbid==0)
		{
 // alert("Add action call"+dbid)
		addEmailConfig();
		
		}
	else
		{
	// alert("Update action call"+dbid)
		 updateEmailConfig();
		}
	
}


function addEmailConfig()
{
	
	var div = document.getElementById("smtpcheckbox");
	var div1 = document.getElementById("sslcheckbox");
	
	
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/addEmailConfig";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'emailId='+$('#emailId').val()
	        + '&port='+$('#port').val()
	        +'&smtpServer='+$('#smtpServer').val()
	        +'&timeout='+$('#timeout').val()
	        +'&isSslTls='+div.checked
	        +'&username='+$('#username').val()
	        +'&isSmtpAuth='+div1.checked
	        +'&password='+$('#password').val(),
	        
	    }).done(function (data) {
	    	
	    	
	    // alert(data)
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Email Configuration Added Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/addEmailConfigForm";

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



function updateUser()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/updateUser";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'name='+$('#name').val()
	        + '&username='+$('#username').val()
	        +'&password='+$('#password').val()
	        +'&confirmPassword='+$('#confirmPassword').val()
	        +'&email='+$('#email').val()
	        +'&mobileNo='+$('#mobileNo').val()
	        +'&department='+$('#department').val()
	        +'&id='+$('#id').val()
	        +'&role='+$('#role').val(),
	    }).done(function (data) {
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Update User Data Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/viewUsers";

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
