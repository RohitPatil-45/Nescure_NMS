function checkTraceRoute(){
	$('#traceRouteSpinner').show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/CheckTraceRoute";
	$.ajax({
		type : 'POST',
		url : serviceUrl,

		data : 'ip=' + $('#ip').val(),
	}).done(function(data22) {
		var html = data22;
		document.getElementById("result").innerHTML = html;

		 $('#trace_route_result').show();
		 $('#traceRouteSpinner').hide();
	});
}