

$(function() {
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/node/jsonInterfaceParameter`;
	$.ajax({
		type: "GET",
		url: serviceUrl,
		dataType: "json",
		success: function(data4) {
			// alert(data4)
			var table = $("#example1").DataTable(
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




function deleteInterfaceParameter(ID) {
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
			var serviceUrl = base_url + "/node/deleteInterfaceParameter";
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
						window.location.href = base_url + "/node/viewInterfaceParameter";
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