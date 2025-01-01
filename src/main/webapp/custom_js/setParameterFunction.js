function addProcessMonitor() {

	console.log("add process Monitor");
	var dataArray = [];

	// Iterate over the table rows
	$('#exampleTable51 tbody tr').each(function(index, row) {
		// Get the value of the checkbox in the current row
		var checkboxValue = $(this).find('td:first-child input[type="checkbox"]').prop('checked');

		// Get the value of the "Process ID" column in the current row
		var processIdValue = $(this).find('td:nth-child(2)').text();

		// Create an object with "id" and "checkbox" properties
		var rowData = {
			id: processIdValue,
			checkbox: checkboxValue
		};

		// Push the object to the dataArray
		dataArray.push(rowData);
	});

	// Log the resulting array
	console.log(dataArray);



	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/processData`;

	$.ajax({
		url: serviceUrl,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(dataArray),
		success: function(data) {
			if (data == "success") {
				Swal.fire({
					position: "top",
					icon: "success",
					title: `Process Monitoring added Successfully`,
					showConfirmButton: true,
					timer: 3000,
				}).then(function() {
					location.reload();
				});
			}


		},
	});

}



$('#selectAll').click(function() {
	// Get the state of the "Select All" checkbox
	var isChecked = $(this).prop('checked');

	// Set the state of all checkboxes in the table based on the "Select All" checkbox
	$('#exampleTable51 tbody tr td input[type="checkbox"]').prop('checked', isChecked);
});






function viewServiceListing() {
	var ip=document.getElementById('ipaddress').value;
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/viewServiceListing`;
	$.ajax({
		type: "GET",
		url: serviceUrl,
		data: {
        ipAddress: ip
         },
		dataType: "json",
		success: function(data) {
			/*if ($.fn.DataTable.isDataTable('#exampleTable2')) {
				$('#exampleTable2').DataTable().clear().destroy();
			}
			$('#exampleTable2').DataTable({
				"data": data,
				"paging": false,
				"scrollY": "400px",
				"lengthChange": false,
				"autoWidth": false,
				"columnDefs": [
					{
						"targets": [0],
						"render": function(data, type, row, meta) {
							if (type === 'display') {
								return '<input type="checkbox" ' + (data ? 'checked' : '') + '>';
							}
							return data;
						}
					}
				]
			});*/
			 $('#exampleTable52').DataTable().clear().rows.add(data).draw();

		},
	});
}



function addServiceMonitor() {

	console.log("add Service Monitor");
	var dataArray = [];

	// Iterate over the table rows
	$('#exampleTable52 tbody tr').each(function(index, row) {
		// Get the value of the checkbox in the current row
		var checkboxValue = $(this).find('td:first-child input[type="checkbox"]').prop('checked');

		// Get the value of the "Process ID" column in the current row
		var processIdValue = $(this).find('td:nth-child(2)').text();

		// Create an object with "id" and "checkbox" properties
		var rowData = {
			id: processIdValue,
			checkbox: checkboxValue
		};

		// Push the object to the dataArray
		dataArray.push(rowData);
	});

	// Log the resulting array
	console.log(dataArray);



	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/serviceData`;

	$.ajax({
		type: "POST",
		url: serviceUrl,
		contentType: 'application/json',
		data: JSON.stringify(dataArray),
		success: function(data) {
			if (data == "success") {
				Swal.fire({
					position: "top",
					icon: "success",
					title: `Service Monitoring added Successfully`,
					showConfirmButton: true,
					timer: 3000,
				}).then(function() {
					location.reload();
				});
			}


		},
	});

}


$('#selectAllService').click(function() {
	// Get the state of the "Select All" checkbox
	var isChecked = $(this).prop('checked');

	// Set the state of all checkboxes in the table based on the "Select All" checkbox
	$('#exampleTable52 tbody tr td input[type="checkbox"]').prop('checked', isChecked);
});

