

$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
   //alert( "Form successful submitted!" );
    	addUpdate()	
      
    }
  });
  $('#nwIpScanId').validate({
    rules: {
    	
    	ipScan:{
    		
    		required: true
    		
    	},
    	endIpScan:{
    		required: true
    	},
    	    	  	
      

    },
    messages: {
    	ipScan: {
        required: "Please provide Start IP Address",
        
      },
      endIpScan: {
        required: "Please provide End IP Address",
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

function editIpScan(id) {
	//alert("edit called")
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/editIpScan";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'id=' + id,
	}).done(function(data) {
		alert(data)
	});
}

function deleteIpScan(id) {

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
	var serviceUrl = base_url + "/master/deleteIpScan";
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
				  window.location.href =base_url+"/master/viewIpScan";

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
		updateIpScan();
		}
	
}





function updateIpScan()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/updateIpScan";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'groupName='+$('#groupName').val()
	        + '&ipScan='+$('#ipScan').val()
	        +'&endIpScan='+$('#endIpScan').val()
	          +'&id='+$('#id').val(),
	          
	    }).done(function (data) {
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Update IP SCAN Data Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/viewIpScan";

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
		var serviceUrl = base_url + "/master/addIpScan";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'groupName='+$('#groupName').val()
	        + '&ipScan='+$('#ipScan').val()
	        +'&endIpScan='+$('#endIpScan').val(),       
		     

	    }).done(function (data) {
	    	
	    	
	    // alert(data)
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Add IP SCAN Successfull',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/nwIpScanForm";

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

