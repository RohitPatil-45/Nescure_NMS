function getTotalInterfaceSummaryDetails(group_name){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getTotalInterfaceSummaryDetails";
	$.ajax({
		type : 'GET',
		data : 'group_name=' + group_name,
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
function getupInterfaceSummaryDetails(group_name){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getupInterfaceSummaryDetails";
	$.ajax({
		type : 'GET',
		data : 'group_name=' + group_name,
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
function getdownInterfaceSummaryDetails(group_name){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getdownInterfaceSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'group_name=' + group_name,
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
function getwarningInterfaceSummaryDetails(group_name){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getwarningInterfaceSummaryDetails";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : 'group_name=' + group_name,
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