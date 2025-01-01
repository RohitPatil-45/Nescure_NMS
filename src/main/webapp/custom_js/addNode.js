$("#importNodeIP").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/importNodeIP";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "nodeIP=" + nodeIP,
	}).done(function(data) {
		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "Node IP Imported Successfully",
				showConfirmButton: false,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/master/discoveredNetwork";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed to Import Node IP",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
});

$(function() {
	$.validator.setDefaults({
		submitHandler: function() {
			addUpdate();
		},
	});
	$("#addNodeId").validate({
		rules: {
			deviceIp: {
				required: true,
			},
			deviceName: {
				required: true,
			},

			company: {
				required: true,
			},

			zone: {
				required: true,
			},

			district: {
				required: true,
			},
			state: {
				required: true,
			},

			location: {
				required: true,
			},
		},
		messages: {
			deviceIp: {
				required: "Please provide Device IP",
			},
			deviceName: {
				required: "Please provide Device Name",
			},

			company: {
				required: "Enter Provide Company Name",
			},

			zone: {
				required: "Please enter ther Zone",
			},

			state: {
				required: "Please enter the State",
			},
			location: {
				required: "Please enter Location",
			},
			district: {
				required: "Please enter the District",
			},
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
});

function editAddNode(id) {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/editAddNode";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "id=" + id,
	}).done(function(data) {
	});
}

function deleteAddNode(id) {
	Swal.fire({
		position: "top",
		title: "Are you sure?",
		text: "You want to delete the record:" + id,
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "Yes, delete it!",
	}).then((result) => {
		if (result.value) {
			var l = window.location;
			var base_url =
				l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
			var serviceUrl = base_url + "/node/deleteAddNode";
			$.ajax({
				type: "POST",
				url: serviceUrl,
				data: "id=" + id,
			}).done(function(data) {
				if (data === "success") {
					Swal.fire({
						position: "top",
						icon: "success",
						title: "Delete Record Successfully",
						showConfirmButton: false,
						timer: 3000,
					}).then(() => {
						var l = window.location;
						var base_url =
							l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
						window.location.href = base_url + "/node/viewAddNode";
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
}
function addUpdate() {
	var dbid = $("#id").val();
	// alert("DB ID"+dbid)

	if (dbid == 0) {
		// alert("Add action call"+dbid)
		addData();
	} else {
		// alert("Update action call"+dbid)
		updateData();
	}
}

function updateData() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/updateAddNode";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data:
			"deviceIp=" +
			$("#deviceIp").val() +
			"&deviceName=" +
			$("#deviceName").val() +
			"&deviceType=" +
			$("#deviceType").val() +
			"&groupName=" +
			$("#groupName").val() +
			"&snmp=" +
			$("#snmp").val() +
			"&serviceProvider=" +
			$("#serviceProvider").val() +
			"&company=" +
			$("#company").val() +
			"&zone=" +
			$("#zone").val() +
			"&state=" +
			$("#state").val() +
			"&district=" +
			$("#district").val() +
			"&id=" +
			$("#id").val() +
			"&location=" +
			$("#location").val(),
	}).done(function(data) {
		console.log(data);

		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "Update add node Data Successfully",
				showConfirmButton: true,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/viewAddNode";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
}

// ICMP config
function addData() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/addNode";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data:
			"deviceIp=" +
			$("#deviceIp").val() +
			"&deviceName=" +
			$("#deviceName").val() +
			"&deviceType=" +
			$("#deviceType").val() +
			"&groupName=" +
			$("#groupName").val() +
			"&snmp=" +
			$("#snmp").val() +
			"&serviceProvider=" +
			$("#serviceProvider").val() +
			"&company=" +
			$("#company").val() +
			"&zone=" +
			$("#zone").val() +
			"&state=" +
			$("#state").val() +
			"&district=" +
			$("#district").val() +
			"&location=" +
			$("#location").val(),
	}).done(function(data) {
		// alert(data)
		// console.log(data);

		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "Add node Successfull",
				showConfirmButton: true,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/addNodeForm";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
}

function showLoadingIndicator() {
	// Show the loading overlay
	$("#loading-overlay").show();
}

function hideLoadingIndicator() {
	// Hide the loading overlay
	$("#loading-overlay").hide();
}

function testSNMPConfig(IP, SNMP) {
	showLoadingIndicator();

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/master/testSNMPConfig";

	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "deviceIp=" + IP + "&snmp=" + SNMP,
	}).done(function(data) {
		// Hide loading indicator
		setTimeout(function() {
			hideLoadingIndicator();
			if (data === "successfully") {
				// Show Swal message
				Swal.fire({
					position: "top",
					icon: "success",
					title: "Test " + " Done " + data,
					showConfirmButton: false,
					timer: 2000, // Adjust the timer value to 3000 milliseconds
					// (3 seconds) or any desired duration
				}).then(() => {
					// Redirect to the specified page
					var l = window.location;
					var base_url =
						l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
					window.location.href = base_url + "/node/viewAddNode";
				});
			}
		}, 3000);
	});
}

// button modal
$(document).ready(function() {
	// Function to open modals based on button clicks
	$(".dropdown-menu button").on("click", function() {
		var buttonId = $(this).attr("id");
		switch (buttonId) {
			case "addSnmp":
				$("#snmpModal").modal("show");
				break;
			case "addGroupName":
				$("#groupNameModal").modal("show");
				break;
			case "addServiceProvider":
				$("#serviceProviderModal").modal("show");
				break;
			case "addCompany":
				$("#companyModal").modal("show");
				break;
			case "addDeviceType":
				$("#deviceTypeModal").modal("show");
				break;
			case "addState":
				$("#stateModal").modal("show");
				break;
			case "addZone":
				$("#zoneModal").modal("show");
				break;
			case "addDistrict":
				$("#districtModal").modal("show");
				break;
			case "addlocation":
				$("#locationModal").modal("show");
				break;
			case "dataDelete":
				$("#deleteModal").modal("show");
				break;
			default:
				// Handle default case (if needed)
				break;
		}
	});
});
// snmp
$("#addsnmpModal").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/addsnmpModal";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "nodeIP=" + nodeIP + "&snmp=" + $("#snmp").val(),
	}).done(function(data) {
		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "SNMP Updated Successfully",
				showConfirmButton: false,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/viewAddNode";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed to Update SNMP",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
});
// Group Name
$("#addGroupNameModal").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/addGroupNameModal";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "nodeIP=" + nodeIP + "&groupName=" + $("#groupName").val(),
	}).done(function(data) {
		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "Group Name Updated Successfully",
				showConfirmButton: false,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/viewAddNode";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed to Update Group Name",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
});
//
$("#addserviceProviderModal").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/addserviceProviderModal";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data:
			"nodeIP=" + nodeIP + "&serviceProvider=" + $("#serviceProvider").val(),
	}).done(function(data) {
		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "Service Provider Updated Successfully",
				showConfirmButton: false,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/viewAddNode";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed to Update Service Provider",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
});
//
$("#addcompanyModal").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/addcompanyModal";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "nodeIP=" + nodeIP + "&company=" + $("#company").val(),
	}).done(function(data) {
		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "Company Updated Successfully",
				showConfirmButton: false,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/viewAddNode";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed to Update Company",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
});
//
$("#adddeviceTypeModal").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/adddeviceTypeModal";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "nodeIP=" + nodeIP + "&deviceType=" + $("#deviceType").val(),
	}).done(function(data) {
		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "Device Type Updated Successfully",
				showConfirmButton: false,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/viewAddNode";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed to Update Device Type",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
});
//
$("#addstateModal").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/addstateModal";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "nodeIP=" + nodeIP + "&state=" + $("#state").val(),
	}).done(function(data) {
		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "state Updated Successfully",
				showConfirmButton: false,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/viewAddNode";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed to Update state",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
});
//
$("#addzoneModal").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/addzoneModal";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "nodeIP=" + nodeIP + "&zone=" + $("#zone").val(),
	}).done(function(data) {
		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "zone Updated Successfully",
				showConfirmButton: false,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/viewAddNode";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed to Update zone",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
});
//
$("#adddistrictModal").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/adddistrictModal";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "nodeIP=" + nodeIP + "&district=" + $("#district").val(),
	}).done(function(data) {
		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "district Updated Successfully",
				showConfirmButton: false,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/viewAddNode";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed to Update district",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
});
//
$("#addlocationModal").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
	var serviceUrl = base_url + "/node/addlocationModal";
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data: "nodeIP=" + nodeIP + "&location=" + $("#location").val(),
	}).done(function(data) {
		if (data === "success") {
			Swal.fire({
				position: "top",
				icon: "success",
				title: "location Updated Successfully",
				showConfirmButton: false,
				timer: 3000,
			}).then(() => {
				var l = window.location;
				var base_url =
					l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
				window.location.href = base_url + "/node/viewAddNode";
			});
		} else {
			Swal.fire({
				position: "top",
				icon: "warning",
				title: "Failed to Update location",
				showConfirmButton: false,
				timer: 3000,
			});
		}
	});
});
//
$("#dataDelete").click(function() {
	var nodeIP = [];
	var table = $("#example1").DataTable();
	var s = table.column(0).checkboxes.selected();
	$.each(s, function(key, i) {
		nodeIP.push(i);
	});
	Swal.fire({
		position: "top",
		title: "Are you sure?",
		text: "You want to delete the record:" + nodeIP,
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "Yes, delete it!",
	}).then((result) => {
		if (result.value) {
			var l = window.location;
			var base_url =
				l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
			var serviceUrl = base_url + "/node/deleteBulkNode";
			$.ajax({
				type: "POST",
				url: serviceUrl,
				data: "nodeIP=" + nodeIP,
			}).done(function(data) {
				if (data === "success") {
					Swal.fire({
						position: "top",
						icon: "success",
						title: "Bulk Node deleted Successfully",
						showConfirmButton: false,
						timer: 3000,
					}).then(() => {
						var l = window.location;
						var base_url =
							l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
						window.location.href = base_url + "/node/viewAddNode";
					});
				} else {
					Swal.fire({
						position: "top",
						icon: "warning",
						title: "Failed to Bulk Node deleted",
						showConfirmButton: false,
						timer: 3000,
					});
				}
			});
		}
	});
});
