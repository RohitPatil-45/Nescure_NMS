//$("#checkPing").click(function() {
//alert("button on")
//	var l = window.location;
//	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
//	var serviceUrl = base_url + "/master/CheckPing";
//	$.ajax({
//		type : 'POST',
//		url : serviceUrl,
//
//		data : 'ip=' + $('#ip').val(),
//	}).done(function(data22) {
//		var html = data22;
//		document.getElementById("result").innerHTML = html;
//
//		// $('#ping_result').show();
//	});
//});

function checkPing(){
	$('#pingSpinner').show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/CheckPing";
	$.ajax({
		type : 'POST',
		url : serviceUrl,

		data : 'ip=' + $('#ip').val(),
	}).done(function(data22) {
		var html = data22;
		document.getElementById("result").innerHTML = html;

		 $('#ping_result').show();
		 $('#pingSpinner').hide();
	});
}
