var fileName = "";
$(document).ready(function() {

	$('input[type="file"]').change(function(e) {
		//	alert($('#bulkUploadFile').val());
		fileName = e.target.files[0].name;
		//        alert(fileName);
		$('#selectFile').hide()
	});
});

$('#submitBulk').click(function() {
	if (fileName == "") {
		//alert("Select the File")
		$('#selectFile').show()
	} else {
		uploadFile();
	}

	//$('#bulkSpinner').show();
});

$('#bulkupload_back').click(function() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	window.location.href = base_url + "/node/bulkUploadForm";
})

function uploadFile() {
//	alert(fileName)
	$('#uploadFileSpinner').show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/node/uploadFile";
	var formData = new FormData();
    formData.append('file', $('#bulkUploadFile')[0].files[0]);
    
	$.ajax({
		type : 'POST',
		enctype : 'multipart/form-data',
		url : serviceUrl,
		data : formData,
		processData : false,
		contentType : false,
		cache : false,
	}).done(function(data) {
		//$('#bulkSpinner').hide();
		$('#fileUploadDetails').show();
		$('#uploadFile').hide();
		$('#uploadFileSpinner').hide();
		const response = data.split(",");
		$('#insertCount').text(response[1]);
		$('#updateCount').text(response[2]);
		$('#deleteCount').text(response[3]);
		$('#failCount').text(response[4]);
		//						if(response[0] == 'success'){
		//						Swal.fire({
		//							position: 'top',
		//							icon: 'success',
		//							title: 'Nodes added Successfull',
		//							showConfirmButton: true,
		//							timer: 5000
		//							})
		////							.then(() => {
		////								 var l = window.location;
		////								   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
		////								  window.location.href =base_url+"/node/bulkUploadForm";
		////
		////								})
		//					}
		//						else{
		//					    	 
		//					    	Swal.fire({
		//					    		  position: 'top',
		//					    		  icon: 'warning',
		//					    		  title: 'Failed to Add Nodes',
		//					    		  showConfirmButton: false,
		//					    		  timer: 3000
		//					    		})
		//							     
		//						    }
	})
}
