

$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
  // alert( "Form successful submitted!" );
    	addUpdate()	
      
    }
  });
  $('#companyMasterId').validate({
    rules: {
    	
    	companyName:{
    		
    		required: true
    		
    	},
    	address:{
    		required: true
    	},
    	
    	email:{
    		required: true
    		
    	},
    	
    	number: {
    		required: true,
    		
    	},
    	  	
      

    },
    messages: {
    	companyName: {
        required: "Please enter Company Name",
        
      },
      address: {
        required: "Please provide address",
      },
      
      email: {
            required: "Please provide email",
            
        },
      
        number: {
              required: "Please provide Contact Number",
              
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

function editCompanyMaster(id) {
	//alert("edit called")
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/editCompanyMaster";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'id=' + id,
	}).done(function(data) {
		alert(data)
	});
}

function deleteCompanyMaster(id) {

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
	var serviceUrl = base_url + "/master/deleteCompanyMaster";
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
				  window.location.href =base_url+"/master/viewCompanyMaster";

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
			addCompanyMasterData();
		
		}
	else
		{
			//alert("Update action call"+dbid)
			updateCompanyMaster();
		}
	
}





function updateCompanyMaster()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/updateCompanyMaster";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'companyName='+$('#companyName').val()
	        + '&address='+$('#address').val()
	        +'&email='+$('#email').val()
	        +'&id=' +$('#id').val()
	        +'&number='+$('#number').val(),
	    }).done(function (data) {
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Update Company Master Data Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/viewCompanyMaster";

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
function addCompanyMasterData()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/addCompany";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'companyName='+$('#companyName').val()
	        + '&address='+$('#address').val()
	        +'&email='+$('#email').val()
	        +'&number='+$('#number').val(),
	     

	        
		     

	    }).done(function (data) {
	    	
	    	
	    // alert(data)
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Add Company Successfull',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/companyMasterform";

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

