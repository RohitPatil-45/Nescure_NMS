/**
 * 
 */
$("#sourceDeviceIp").change(function() {
	var ip = $('#sourceDeviceIp').val();
	var l = window.location;
	var interFaceName = null;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/nodeReport/getInterFaceNameLists";
	$.ajax({
		type: "get",
		dataType: "json",
		url: serviceUrl,
		data: "ip=" + ip,
	}).done(function(data5) {
		interFaceName = data5;
		var optionsString = '';
		$.each(interFaceName, function(index, value) {
			optionsString += '<option value="' + value + '">' + value + '</option>';
		});

		$('#source_interface_name').html(optionsString).select2();
		$('#source_interface_name').val(interFaceName[0]).trigger('change');
	});
});

$("#destinationDeviceIp").change(function() {
	var ip = $('#destinationDeviceIp').val();
	var l = window.location;
	var interFaceName = null;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/nodeReport/getInterFaceNameLists";
	$.ajax({
		type: "get",
		dataType: "json",
		url: serviceUrl,
		data: "ip=" + ip,
	}).done(function(data5) {
		interFaceName = data5;
		var optionsString = '';
		$.each(interFaceName, function(index, value) {
			optionsString += '<option value="' + value + '">' + value + '</option>';
		});

		$('#destination_interface_name').html(optionsString).select2();
		$('#destination_interface_name').val(interFaceName[0]).trigger('change');
	});
});


$(document).ready(function() {
	$("#manualTopology").validate({
		rules: {
			source_interface_name: { required: true },
			destination_interface_name: { required: true },
		},
		messages: {
			source_interface_name: { required: "Please provide Source Interface Name" },
			destination_interface_name: { required: "Please provide Destination Interface Name" },
		},
		errorElement: "span",
		errorPlacement: function(error, element) {
			error.addClass("invalid-feedback");
			element.closest(".form-group").append(error);
		},
		highlight: function(element, errorClass, validClass) {
			$(element).addClass("is-invalid");
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass("is-invalid");
		},
	});
	$("#manualTopologyId").click(function() {
		if ($("#manualTopology").valid()) {
			var l = window.location;
			var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;

			var addServiceUrl = `${base_url}/master/manualTopology`;

			$.ajax({
				type: "POST",
				url: addServiceUrl,
				data: 'sourceDeviceIp=' + $('#sourceDeviceIp').val()
					+ '&source_interface_name=' + $('#source_interface_name').val()
					+ '&destinationDeviceIp=' + $('#destinationDeviceIp').val()
					+ '&destination_interface_name=' + $('#destination_interface_name').val(),
			}).done(function(data) {
				if (data === "success") {
					Swal.fire({
						position: "top",
						icon: "success",
						title: "Manual Topology added Successfully",
						showConfirmButton: false,
						timer: 3000,
					}).then(() => {
						var l = window.location;
						var base_url =
							l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
						window.location.href = base_url + "/master/manualTopologyForm";
					});
				} else {
					Swal.fire({
						position: "top",
						icon: "warning",
						title: "Record Not Deleted",
						showConfirmButton: false,
						timer: 3000,
					});
				}
			});
		}
	});
});
