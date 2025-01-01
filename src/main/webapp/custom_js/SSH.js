window.onload = function() {
	// alert("onload")
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/getIPAddress";
	$
			.ajax({
				type : 'GET',
				url : serviceUrl,
				success : function(data) {
					var html = " <select class='form-control'  id='ipAddress' name='ipAddress'>";
					html += data + "</select>";
					// $("#branch").val(data);
					document.getElementById("ipAddress22").innerHTML = html;

				}
			});
}

function checkSSH() {
	$('#sshSpinner').show();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/checkSSH";
	$.ajax(
			{
				type : 'POST',
				url : serviceUrl,
				data : 'ipAddress=' + $('#ipAddress').val() + '&username='
						+ $('#username').val() + '&password='
						+ $('#password').val() + '&command='
						+ $('#command').val(),

			}).done(function(data) {
		$('#sshSpinner').hide();
		var html = data;
		document.getElementById("result").innerHTML = html;

		$('#ssh_result').show();
	});
}