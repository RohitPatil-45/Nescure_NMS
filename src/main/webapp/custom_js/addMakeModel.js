window.onload = function() {
	//alert("HiTT");
	//Add Group Listing 
	
	  var l = window.location;
	  	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  	var serviceUrl = base_url + "/master/makeModelListing";
	     $.ajax({
	         type: 'GET',
	         url: serviceUrl,
	         dataType: 'json',
	         success: function(data4) {
	        //	 alert(data4)
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
   //   alert( "Form successful submitted! in Group Js" );
    	MakeModelAddUpdate();
      
    }
  });
  $('#addMakeModelId').validate({
    rules: {
    	makeModelName:{
    		required: true
    	},
    },
    messages: {
    	makeModelName: {
    	  required: "Please Enter the Make & Model name"
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




function deleteMakeModel(id) {
//alert("DeleteGroup Called");
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
	var serviceUrl = base_url + "/master/deleteMakeModel";
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
				  window.location.href =base_url+"/master/addMakeModelForm";

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
function MakeModelAddUpdate(){
	
	var dbid=$('#id').val()
	//alert("DB ID"+dbid)
	
	if(dbid==0)
		{
		//alert("Add action call"+dbid)
		addMakeModel();
		
		}
	else
		{
	//	alert("Update action call"+dbid)
		
		}
	
}


function addMakeModel()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/addMakeModel";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'makeModelName='+$('#makeModelName').val(),
	    }).done(function (data) {
	    	
	    	
	    //	alert(data)
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Make & Model Added Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/addMakeModelForm";

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




