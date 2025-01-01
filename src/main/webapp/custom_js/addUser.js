

$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
   //   alert( "Form successful submitted!" );
    	userAddUpdate();
      
    }
  });
  $('#addUserId').validate({
    rules: {
    	
    	name:{
    		
    		required: true
    		
    	},
    	username:{
    		required: true
    	},
    	
    	department:{
    		required: true
    		
    	},
    	
    	mobileNo: {
    		required: true,
    		minlength: 10, 
    		maxlength: 10
    	},
    	
    	role: {
    		required: true
    	},
    	
      email: {
        required: true,
        email: true,
      },
      password: {
        required: true,
        minlength: 5
        
      },
      

    },
    messages: {
      email: {
        required: "Please enter a email address",
        email: "Please enter a vaild email address"
      },
      password: {
        required: "Please provide a password",
        minlength: "Your password must be at least 5 characters long"
      },
      department:{
    	  required: "Please provide the Department name"
      },
      username: {
    	  required: "Please Provide the Username"
      },
      
      mobileNo: {
  		required: "Please provide Contact Number",
  		minlength: "10 digits Required", 
  		maxlength: "cannot exceed 10 digit"
      },
      
      role: {
    	  required: "Provide the Role"
      },
      
      
      
      terms: "Please accept our terms"
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

//function editUser(id) {
//	alert("edit called")
//	var l = window.location;
//	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
//	var serviceUrl = base_url + "/master/editUser";
//	$.ajax({
//		type : 'POST',
//		url : serviceUrl,
//		data : 'id=' + id,
//	}).done(function(data) {
//		alert(data)
//	});
//}

function deleteUser(id) {

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
	var serviceUrl = base_url + "/master/deleteUser";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'id=' + id,
	}).done(function(data) {

		if (data === "success") {

			Swal.fire({
				position : 'top',
				icon : 'success',
				title : 'Record Deleted Successfully',
				showConfirmButton : false,
				timer : 3000
			}).then(() => {
				 var l = window.location;
				   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
				  window.location.href =base_url+"/master/viewUsers";

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
function userAddUpdate(){
	
	var dbid=$('#id').val()
	//alert("DB ID"+dbid)
	
	if(dbid==0)
		{
		//alert("Add action call"+dbid)
		addUser();
		
		}
	else
		{
		//alert("Update action call"+dbid)
		updateUser();
		}
	
}


function addUser()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/addUser";
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
	        +'&role='+$('#role').val(),

	        
		     

	    }).done(function (data) {
	    	
	    	
	    //	alert(data)
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'User Added Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/addUserForm";

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
title: 'User Data Updated Successfully',
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
