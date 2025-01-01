

$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
   // alert( "Form successful submitted!" );
    	addUpdate()	
      
    }
  });
  $('#speedTestMasterId').validate({
    rules: {
    	
    	
    	discription:{
    		required: true
    	},
    	 	
      

    },
    messages: {
    	
    	discription: {
        required: "Please provide description",
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

function editLocationMaster(id) {
	// alert("edit called")
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/editLocationMaster";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'id=' + id,
	}).done(function(data) {
		alert(data)
	});
}

// function deleteLocationMaster(id) {
//
// Swal.fire({
// position : 'top',
// title: 'Are you sure?',
// text: "You want to delete the record:"+id,
// icon: 'warning',
// showCancelButton: true,
// confirmButtonColor: '#3085d6',
// cancelButtonColor: '#d33',
// confirmButtonText: 'Yes, delete it!'
// })
// .then((result) => {
// if (result.value) {
// var l = window.location;
// var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
// var serviceUrl = base_url + "/master/deleteLocationMaster";
// $.ajax({
// type : 'POST',
// url : serviceUrl,
// data : 'id=' + id,
// }).done(function(data) {
//
// if (data === "success") {
//
// Swal.fire({
// position : 'top',
// icon : 'success',
// title : 'Delete Record Successfully',
// showConfirmButton : false,
// timer : 3000
// }).then(() => {
// var l = window.location;
// var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
// window.location.href =base_url+"/master/viewLocationMaster";
//
// })
//
// } else {
//
// Swal.fire({
// position : 'top',
// icon : 'warning',
// title : 'Record Not Deleted',
// showConfirmButton : false,
// timer : 3000
// })
//
// }
// });
// }
// });
// }
function addUpdate(){
	
	var dbid=$('#ID').val()
	 // alert("DB ID"+dbid)
	
// if(dbid==0)
// {
// // alert("Add action call"+dbid)
// addlocationMasterData();
//		
// }
// else
// {
			// alert("Update action call"+dbid)
			updateLocationMaster();
// }
	
}





function updateLocationMaster()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/node/updateSpeedTestDeviceMaster";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'device_IP='+$('#device_IP').val()
	        + '&discription='+$('#discription').val()
	        +'&id=' +$('#ID').val(),
	    }).done(function (data) {
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Update Location Master Data Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/node/nodeSpeedTest";

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

// ICMP config
function addlocationMasterData()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/master/addlocationMaster";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'companyName='+$('#companyName').val()
	        + '&location='+$('#location').val(),
	   
		     

	    }).done(function (data) {
	    	
	    	
	    // alert(data)
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Add location master Successfull',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/master/locationMasterform";

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

