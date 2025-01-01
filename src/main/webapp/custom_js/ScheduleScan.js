$("#addScheduleScan").click(function() {
	if ($("#scheduleScanForm").valid()) {
		var l = window.location;
		var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
		var addServiceUrl = `${base_url}/master/addScheduleScan`;
		var updateUrl = `${base_url}/master/`;
		
     var selectedType = $("#configType option:selected");
       if (selectedType.length === 0) {
        Swal.fire({
            position: "top",
            icon: "warning",
            title: "Please select at least one ConfigType.",
            showConfirmButton: false,
            timer: 3000,
          });
         }
		$.ajax({
			type: "POST",
			/*url: serviceUrl,*/
			url: $("#ID").val() == 0 ? addServiceUrl : updateUrl,
			data: $("#scheduleScanForm").serialize(),
		}).done(function(data) {
			if (data =="added") {
				Swal.fire({
					position: "top",
					icon: "success",
					title: `Schedule Scan ${data} Successfully`,
					showConfirmButton: true,
					timer: 3000,
				}).then(() => {
					var l = window.location;
					var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
					window.location.href = `${base_url}/master/ScheduleScan`;
				});
			} else if (data =="already") {
				Swal.fire({
					position: "top",
					icon: "warning",
					title: `Schedule Scan ${data} exists`,
					showConfirmButton: true,
					timer: 3000,
				}).then(() => {
					var l = window.location;
					var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
					window.location.href = `${base_url}/master/ScheduleScan`;
				});
			}
			else if(data =="updated"){
				Swal.fire({
					position: "top",
					icon: "success",
					title: `Schedule Scan ${data} Successfully`,
					showConfirmButton: true,
					timer: 3000,
				}).then(() => {
					var l = window.location;
					var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
					window.location.href = `${base_url}/master/ScheduleScan`;
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
	var serviceUrl = `${base_url}/master/viewScheduleScan`;
	$.ajax({
		type: "GET",
		url: serviceUrl,
		dataType: "json",
		success: function(data4) {
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
		},
	});
});

function deleteScheduleScan(ID) {
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
			var serviceUrl = base_url + "/master/deleteScheduleScan";
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
						window.location.href = base_url + "/master/ScheduleScan";
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
once_time
$("#scheduleScanForm").validate({
	rules: {
		scheduleType: { required: true },
		startIp: { required: true },
		subnetIp: { required: true },
		once_time: { required: true },
	},
	messages: {
		scheduleType: { required: "Please provide schedule type" },
		startIp: { required: "Please provide Starting Ip" },
		subnetIp: { required: "Please provide Ending Ip" },
		once_time: { required: "Please provide Time" },
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

window.onload = function() {
			$("#configType").multiselect({
				enableFiltering : true,
				includeSelectAllOption : true,
				buttonWidth : "100%",
				maxHeight : 350,
			});
		}
