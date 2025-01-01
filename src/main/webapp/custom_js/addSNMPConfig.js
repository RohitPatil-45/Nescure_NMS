window.onload = function() {
	
	
	// Hide the Divs by default
	document.getElementById("communityid").style.display = 'block';
	document.getElementById("usernameid").style.display = 'none';
	document.getElementById("contextid").style.display = 'none';
	document.getElementById("authid").style.display = 'none';
	document.getElementById("authpwdid").style.display = 'none';
	document.getElementById("encryptid").style.display = 'none';
	document.getElementById("encryptpwdid").style.display = 'none';
	
	
	// end
	
	
	  var l = window.location;
	  	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  	var serviceUrl = base_url + "/master/snmpConfigListing";
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
    	SNMPAddUpdate();
      
    }
  });
  $('#addSNMPConfigId').validate({
    rules: {    	
    	
    	name:{
    		
    		required: true
    	},
    	
    	version:{
    		required: true
    	},
    	
    	description:{
    		required: true
    		
    	},
    	
    	community: {
        required: true
   
      },
      
      username: {
          required: true
     
        },
        
        authProtocol: {
            required: true
       
          },
      
     context: {
              required:true 
                  
          },
          authPassword:{
    		 required: true
              
        	  
       	  },
       	  
       	encryptProtocol:{
   		 required: true
      	  },
      	  
      	port:{
      		 required: true
         	  },
         	  
         	 encryptPassword:{
           		 required: true
              	  },
              	  
              	  
              	timeout:{
              		 required: true
                 	  },
                 	  
                 	 retries:{
                   		 required: true
                      	  },

    },
    
    messages: {
    		
name:{
    		
    		required: "Please enter name"
    	},
    	
    	version:{
    		required: "Please select version"
    	},
    	
    	description:{
    		required: "Please enter description"
    		
    	},
    	
    	community: {
        required: "Please enter community"
   
      },
      
      username: {
          required: "Please enter username"
     
        },
        
        authProtocol: {
            required: "Please select authetication protocol"
       
          },
      
     context: {
              required:"Please enter context" 
                  
          },
          authPassword:{
    		 required: "Please enter authetication password"
              
        	  
       	  },
       	  
       	encryptProtocol:{
   		 required: "Please select encryption protocol"
      	  },
      	  
      	port:{
      		 required: "Please enter port"
         	  },
         	  
         	 encryptPassword:{
           		 required: "Please enter encryption password"
              	  },
              	  
              	  
              	timeout:{
              		 required: "Please enter timeout"
                 	  },
                 	  
                 	 retries:{
                   		 required: "Please enter retries"
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


function editSNMPConfig(id) {
	//alert("edit called"+id)
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/editSNMPConfig";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'id=' + id,
	}).done(function(data) {
		// alert(data)
	});
}

function deleteSNMPConfig(id) {

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
	var serviceUrl = base_url + "/master/deleteSNMPConfig";
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
				  window.location.href =base_url+"/master/addSNMPConfigForm";

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
function SNMPAddUpdate(){
	
	var dbid=$('#id').val()
//	 alert("DB ID"+dbid)
	
	if(dbid==0)
		{
 //alert("Add action call"+dbid)
		addSNMPConfig();
		
		}
	else
		{
	// alert("Update action call"+dbid)
		// updateUser();
		}
	
}


function addSNMPConfig()
{
	
	
	var version = document.getElementById("versionid");
	
	
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/addSNMPConfig";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'name='+$('#name').val()
	        + '&description='+$('#description').val()
	        +'&version='+version.value
	        +'&community='+$('#community').val()
	        +'&username='+$('#username').val()
	        +'&authProtocol='+$('#authProtocol').val()
	        +'&context='+$('#context').val()
	        +'&authPassword='+$('#authPassword').val()
	        +'&encryptProtocol='+$('#encryptProtocol').val()
	        +'&port='+$('#port').val()
	        +'&encryptPassword='+$('#encryptPassword').val()
	        +'&timeout='+$('#timeout').val()
	        +'&retries='+$('#retries').val(),
	        
	    }).done(function (data) {
	    	
	    	
	    // alert(data)
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'SNMP Configuration Added Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/addSNMPConfigForm";

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


































 



/*function testSNMPConfig(ID) {
    var l = window.location;
    var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
    var serviceUrl = base_url + "/master/testSNMPConfig/" + ID;

    $.ajax({
        type: "POST",
        url: serviceUrl,
        data: "ID=" + ID,
    }).done(function(data) {
      if (data === "success") {
            Swal.fire({
                position: "top",
                icon: "success",
                title:"Delete " + data + " Successfully",
                showConfirmButton: false,
                timer: 3000, // Adjust the timer value to 3000 milliseconds (3 seconds) or any desired duration
            }).then(() => {
                var l = window.location;
                var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
                window.location.href = base_url + "/master/addSNMPConfigForm";
            });
        }
    });
}*/


function showLoadingIndicator() {
        // Show the loading overlay
        $('#loading-overlay').show();
    }

    function hideLoadingIndicator() {
        // Hide the loading overlay
        $('#loading-overlay').hide();
    }
    
    
function testSNMPConfig(ID) {
    showLoadingIndicator();

    var l = window.location;
    var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
    var serviceUrl = base_url + "/master/testSNMPConfig/" + ID;

    $.ajax({
        type: "POST",
        url: serviceUrl,
        data: "ID=" + ID,
    }).done(function(data) {
        // Hide loading indicator
        setTimeout(function () {
            hideLoadingIndicator();
            if (data === "successfully") {
            // Show Swal message
            Swal.fire({
                position: "top",
                icon: "success",
                title: "Test " + " Done "+ data ,
                showConfirmButton: false,
                timer: 2000, // Adjust the timer value to 3000 milliseconds (3 seconds) or any desired duration
            }).then(() => {
                // Redirect to the specified page
                var l = window.location;
                var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
                window.location.href = base_url + "/master/addSNMPConfigForm";
            });
        }
        }, 3000);

        
    })
}





