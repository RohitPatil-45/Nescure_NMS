function getTotalPortSummaryDetails(ip){
	//$('#portSummaryTable td').remove();
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/totalPortSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'ip_address=' + ip,
		dataType : 'json',
		success : function(data) {
			;
			var table = $('#portSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				destroy : true,
				//scrollY : true
			});

		}
	});
}
function getUsedPortSummaryDetails(ip){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/usedPortSummaryDetails";
	$.ajax({
		type : 'GET',
		data : 'ip_address=' + ip,
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			var table = $('#portSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				destroy : true,
			});

		}
	});
}
function getUnUsedPortSummaryDetails(ip){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/unUsedPortSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'ip_address=' + ip,
		dataType : 'json',
		success : function(data) {
			var table = $('#portSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				destroy : true,
			});

		}
	});
}
function getUpPortSummaryDetails(ip){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/upPortSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'ip_address=' + ip,
		dataType : 'json',
		success : function(data) {
			var table = $('#portSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				destroy : true,
			});

		}
	});
}
function getDownPortSummaryDetails(ip){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/downPortSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'ip_address=' + ip,
		dataType : 'json',
		success : function(data) {
			var table = $('#portSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				destroy : true,
			});

		}
	});
}

function getunUtlilizedPortSummaryDetails(ip){
	$('#unutilized-port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/unutilizedPortSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'ip_address=' + ip,
		dataType : 'json',
		success : function(data) {
			var table = $('#unutilizedPortSummaryTable').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				destroy : true,
			});

		}
	});
}