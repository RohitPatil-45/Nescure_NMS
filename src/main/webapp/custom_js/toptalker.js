window.onload = function() {

	// Link Down Summary Listing

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/topTalkerLinkSummaryList";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			// alert(data);

			var table = $('#linkSummary').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true
			});

		}
	});

}

