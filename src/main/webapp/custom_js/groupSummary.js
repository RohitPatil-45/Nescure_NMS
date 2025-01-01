function getTotalNodeSummaryDetails(group_name){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getTotalNodeSummaryDetails";
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
function getupNodeSummaryDetails(group_name){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getupNodeSummaryDetails";
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
function getdownNodeSummaryDetails(group_name){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getdownNodeSummaryDetails";
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
function getwarningNodeSummaryDetails(group_name){
	$('#port-info-modal').modal('show')
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getwarningNodeSummaryDetails";
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