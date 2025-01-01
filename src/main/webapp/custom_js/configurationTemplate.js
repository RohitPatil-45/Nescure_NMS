$("#addConfigurationTemplates").click(function() {
	if ($("#configTemplateFrom").valid()) {
		var l = window.location;
		var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
		var addServiceUrl = `${base_url}/node/addConfigurationTemplate`;
		var updateUrl = `${base_url}/node/updateConfigurationTemplate`;
		$.ajax({
			type: "POST",
			/*url: serviceUrl,*/
			url: $("#ID").val() == 0 ? addServiceUrl : updateUrl,
			data: $("#configTemplateFrom").serialize(),
		}).done(function(data) {
			/*if (data!=null) {
				Swal.fire({
					position: "top",
					icon: "success",
					title: "Add Backup Configuration Successfully",
					title: $("#ID").val() == 0 ? `${data}` : "Template Configuration updated Successfully",
					showConfirmButton: true,
					timer: 3000,
				}).then(() => {
					var l = window.location;
					var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
					window.location.href = `${base_url}/node/configTemplate`;
				});
			} else {
				Swal.fire({
					position: "top",
					icon: "warning",
					title: "Failed",
					showConfirmButton: false,
					timer: 3000,
				});
			}*/

			if (data =="added") {
				Swal.fire({
					position: "top",
					icon: "success",
					title: `Template Configuration ${data} Successfully`,
					showConfirmButton: true,
					timer: 3000,
				}).then(() => {
					var l = window.location;
					var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
					window.location.href = `${base_url}/node/configTemplate`;
				});
			} else if (data =="already") {
				Swal.fire({
					position: "top",
					icon: "warning",
					title: `Template Configuration ${data} exists`,
					showConfirmButton: true,
					timer: 3000,
				}).then(() => {
					var l = window.location;
					var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
					window.location.href = `${base_url}/node/configTemplate`;
				});
			}
			else if(data =="updated"){
				Swal.fire({
					position: "top",
					icon: "success",
					title: `Template Configuration ${data} Successfully`,
					showConfirmButton: true,
					timer: 3000,
				}).then(() => {
					var l = window.location;
					var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
					window.location.href = `${base_url}/node/configTemplate`;
				});
			}
			 else {
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
});

$(function() {
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/node/viewConfigurationTemplate`;
	$.ajax({
		type: "GET",
		url: serviceUrl,
		dataType: "json",
		success: function(data4) {
			// alert(data4)
			var table =$("#example1").DataTable(
					{
						"responsive" : true,
						"lengthChange" : false,
						"autoWidth" : false,
						"data" : data4,
						"buttons" : [ "copy", "csv", "excel", "pdf", "print",
								"colvis" ]
					}).buttons().container().appendTo(
					'#example1_wrapper .col-md-6:eq(0)');
			$('#example2').DataTable({
				"paging" : true,
				"lengthChange" : false,
				"searching" : false,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false,
				"responsive" : true,
			});
			table.buttons().container().appendTo("#example1_wrapper .col-sm-6:eq(0)");
		},
	});
});

function deleteConfigurationTemplate(ID) {
	Swal.fire({
		position: "top",
		title: "Are you sure?",
		text: "You want to delete the record:" + ID,
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085D6",
		cancelButtonColor: "#d33",
		confirmButtonText: "Yes, delete it!",
	}).then((result) => {
		if (result.value) {
			var l = window.location;
			var base_url =
				l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
			var serviceUrl = base_url + "/node/deleteConfigurationTemplate";
			$.ajax({
				type: "POST",
				url: serviceUrl,
				data: "ID=" + ID,
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
						window.location.href = base_url + "/node/configTemplate";
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

$("#configTemplateFrom").validate({
	rules: {
		templateName: { required: true },
		command: { required: true },
	},
	messages: {
		templateName: { required: "Please provide Template Name" },
		command: { required: "Please provide Command" },
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