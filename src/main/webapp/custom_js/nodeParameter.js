window.onload = function() {
	$('#ip_data').multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		buttonWidth: '100%',
		maxHeight: 350,

	});
}

$(function() {
	$.validator.setDefaults({
		submitHandler: function() {
			addUpdate()

		}
	});
	$('#addNodeParameterId').validate({
		rules: {

			ip: {

				required: true

			},
			cpuThreshold: {
				required: true
			},

			memoryThreshold: {
				required: true

			},

			latencyThreshold: {
				required: true,

			},






		},
		messages: {
			ip: {
				required: "Please enter ip",

			},
			cpuThreshold: {
				required: "Please provide CPU Threshold",
			},

			memoryThreshold: {
				required: "Enter Memory Threshold",

			},

			latencyThreshold: {
				required: "Please enter Latency Threshold",

			},


		},
		errorElement: 'span',
		errorPlacement: function(error, element) {
			error.addClass('invalid-feedback');
			element.closest('.form-group').append(error);
		},
		highlight: function(element, errorClass, validClass) {
			$(element).addClass('is-invalid');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass('is-invalid');
		}
	});
});

function editNodeParameter(id) {

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/node/editNodeParameter";
	$.ajax({
		type: 'POST',
		url: serviceUrl,
		data: 'id=' + id,
	}).done(function(data) {
		alert(data)
	});
}

function deleteNodeParameter(id) {

	Swal.fire({
		position: 'top',
		title: 'Are you sure?',
		text: "You want to delete the record:" + id,
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
				var serviceUrl = base_url + "/node/deleteNodeParameter";
				$.ajax({
					type: 'POST',
					url: serviceUrl,
					data: 'id=' + id,
				}).done(function(data) {

					if (data === "success") {

						Swal.fire({
							position: 'top',
							icon: 'success',
							title: 'Delete Record Successfully',
							showConfirmButton: false,
							timer: 3000
						}).then(() => {
							var l = window.location;
							var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
							window.location.href = base_url + "/node/viewNodeParameter";

						})

					} else {

						Swal.fire({
							position: 'top',
							icon: 'warning',
							title: 'Record Not Deleted',
							showConfirmButton: false,
							timer: 3000
						})

					}
				});
			}
		});
}
function addUpdate() {

	var dbid = $('#id').val()
	// alert("DB ID"+dbid)

	if (dbid == 0) {

		addNodeParameterForm();

	}
	else {

		updateData();
	}

}





function updateData() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/"
		+ l.pathname.split('/')[1];
	var serviceUrl = base_url + "/node/updateNodeParameter";
	$.ajax({
		type: 'POST',
		url: serviceUrl,
		data: 'ip=' + $('#ipedit').val()
			+ '&cpuThreshold=' + $('#cpuThreshold').val()
			+ '&memoryThreshold=' + $('#memoryThreshold').val()
			+ '&latencyThreshold=' + $('#latencyThreshold').val()
			+ '&monitoring=' + $('#monitoring').val()
			+ '&id=' + $('#id').val()
			+ '&cpuHistory=' + $('#cpuHistory').val()
			+ '&memoryHistory=' + $('#memoryHistory').val()
			+ '&latencyHistory=' + $('#latencyHistory').val()
			+ '&snmpDiscovery=' + $('#snmpDiscovery').val()
			+ '&mailAlert=' + $('#mailAlert').val()
			+ '&smsAlert=' + $('#smsAlert').val()
			+ '&autoTicketing=' + $('#autoTicketing').val(),

	}).done(function(data) {
		console.log(data);

		if (data === "success") {

			Swal.fire({
				position: 'top',
				icon: 'success',
				title: 'Update Icmp Data Successfully',
				showConfirmButton: true,
				timer: 3000
			}).then(() => {
				var l = window.location;
				var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
				window.location.href = base_url + "/node/viewNodeParameter";

			})

		} else {

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
function addNodeParameterForm() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/"
		+ l.pathname.split('/')[1];
	var serviceUrl = base_url + "/node/addNodeParameter";
	$.ajax({
		type: 'POST',
		url: serviceUrl,
		data: 'ip=' + $('#ip_data').val()
			+ '&cpuThreshold=' + $('#cpuThreshold').val()
			+ '&memoryThreshold=' + $('#memoryThreshold').val()
			+ '&latencyThreshold=' + $('#latencyThreshold').val()
			+ '&monitoring=' + $('#monitoring').val()
			+ '&latencyHistory=' + $('#latencyHistory').val()
			+ '&cpuHistory=' + $('#cpuHistory').val()
			+ '&memoryHistory=' + $('#memoryHistory').val()
			+ '&snmpDiscovery=' + $('#snmpDiscovery').val()
			+ '&mailAlert=' + $('#mailAlert').val()
			+ '&smsAlert=' + $('#smsAlert').val()
			+ '&autoTicketing=' + $('#autoTicketing').val(),



	}).done(function(data) {


		// alert(data)
		console.log(data);

		if (data === "success") {

			Swal.fire({
				position: 'top',
				icon: 'success',
				title: 'Node Parameter Added Successfull',
				showConfirmButton: true,
				timer: 3000
			}).then(() => {
				var l = window.location;
				var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
				window.location.href = base_url + "/node/addNodeParameterForm";

			})

		} else if (data == "update") {

			Swal.fire({
				position: 'top',
				icon: 'success',
				title: 'Updated Successfully',
				showConfirmButton: true,
				timer: 3000
			}).then(() => {
				var l = window.location;
				var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
				window.location.href = base_url + "/node/addNodeParameterForm";

			})

		}
		else {
			Swal.fire({
				position: 'top',
				icon: 'warning',
				title: 'Failed',
				showConfirmButton: true,
				timer: 3000
			})

		}
	});
}

