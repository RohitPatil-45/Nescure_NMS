window.onload = function() {

	// Device Up Down Count

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/topCPUUtilization";
	$
			.ajax({
				type : 'GET',
				url : serviceUrl,
				dataType : 'json',
				success : function(data) {

					var table = $('#cpuSummary').DataTable({
		                lengthChange: false,
		                autoWidth: false,
		                data: data,
		                 "pageLength": 5,
		                 scrollX:true,
		                 scrollY:true
		            });
				}
			});

	// Device Down Summary Listing

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/deviceSummaryList";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			//alert(data);

			var table = $('#deviceSummary').DataTable({
                lengthChange: false,
                autoWidth: false,
                data: data,
                 "pageLength": 5,
                 scrollX:true,
                 scrollY:true
            });
			
		}
	});

	// Link Up Down Count

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/linkSummaryCount";
	$
			.ajax({
				type : 'GET',
				url : serviceUrl,
				dataType : 'json',
				success : function(data) {

					var linkupcount = 0;
					var linkdowncount = 0;

					for (var i = 0; i < data.length; i++) {

						if (data[i].status == "up") {
							document.getElementById("uplinkspanid").textContent = data[i].count;
							linkupcount = data[i].count;
						}
						if (data[i].status == "down") {
							document.getElementById("downlinkspanid").textContent = data[i].count;
							linkdowncount = data[i].count;
						}

					}

					document.getElementById("totallinkspanid").textContent = data.length;

					localStorage.setItem("upLinkCountVar", linkupcount);
					localStorage.setItem("downLinkCountVar", linkdowncount);
				}
			});
	
	
	// Link Down Summary Listing

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/linkSummaryList";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			//alert(data);

			var table = $('#linkSummary').DataTable({
                lengthChange: false,
                autoWidth: false,
                data: data,
                 "pageLength": 5,
                 scrollX:true,
                 scrollY:true
            });
			
		}
	});


}
