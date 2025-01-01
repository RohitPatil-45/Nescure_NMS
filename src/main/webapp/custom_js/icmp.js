

$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
   //alert( "Form successful submitted!" );
    	addUpdate()	
      
    }
  });
  $('#addIcmpId').validate({
    rules: {
    	
    	packetSize:{
    		
    		required: true
    		
    	},
    	packetCount:{
    		required: true
    	},
    	
    	poolingTime:{
    		required: true
    		
    	},
    	
    	timeout: {
    		required: true,
    		
    	},
    	
    	ttl: {
    		required: true
    	},
    	
    	
    	
      customerName: {
        required: true,
        
      },
      location: {
        required: true,
       
      },
      

  	
      

    },
    messages: {
      packetSize: {
        required: "Please enter Packet Size",
        
      },
      packetCount: {
        required: "Please provide Packet Count",
      },
      
      timeout: {
            required: "Enter Session Timeout",
            
        },
      
      ttl: {
              required: "Please enter TTL",
              
        },
      
       customerName: {
                required: "Please enter Customer Name",
                
              },
              location: {
                  required: "Please enter Location",
                  
                },
                poolingTime: {
                    required: "Please enter Pooling Time",
                    
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

function editIcmp(id) {
	//alert("edit called")
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/editIcmp";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'id=' + id,
	}).done(function(data) {
		alert(data)
	});
}

function deleteIcmp(id) {

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
	var serviceUrl = base_url + "/master/deleteIcmp";
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
				  window.location.href =base_url+"/master/viewIcmp";

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
function addUpdate(){
	
	var dbid=$('#id').val()
	 //alert("DB ID"+dbid)
	
	if(dbid==0)
		{
		//alert("Add action call"+dbid)
		addData();
		
		}
	else
		{
		//alert("Update action call"+dbid)
		updateData();
		}
	
}





function updateData()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/updateIcmp";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'packetSize='+$('#packetSize').val()
	        + '&packetCount='+$('#packetCount').val()
	        +'&poolingTime='+$('#poolingTime').val()
	        +'&timeout='+$('#timeout').val()
	        +'&ttl='+$('#ttl').val()
	          +'&id='+$('#id').val()
	        +'&customerName='+$('#customerName').val()
	        +'&location='+$('#location').val(),
	    }).done(function (data) {
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Update Icmp Data Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/viewIcmp";

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

//ICMP config
function addData()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/addIcmp";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'packetSize='+$('#packetSize').val()
	        + '&packetCount='+$('#packetCount').val()
	        +'&poolingTime='+$('#poolingTime').val()
	        +'&timeout='+$('#timeout').val()
	        +'&ttl='+$('#ttl').val()
	        +'&customerName='+$('#customerName').val()
	        +'&location='+$('#location').val(),
	     

	        
		     

	    }).done(function (data) {
	    	
	    	
	    // alert(data)
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Add ICMP Successfull',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/addIcmpform";

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

