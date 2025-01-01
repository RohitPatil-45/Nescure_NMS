window.onload = function() {
	//	alert("onload")
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

var ii = 0;
$('#addbtnInclude')
		.click(
				function() {
					$('#customValueInclude').show()

					var value = $('#command').val().trim();

					if ($('#command').val() === "") {
						swal({
							title : "Error",
							text : "Please Enter the Keyword",
							type : "warning",
							width : 300,
							showCancelButton : !0,
							confirmButtonClass : "btn btn-danger",
							cancelButtonClass : "btn btn-danger m-l-10",
							confirmButtonText : "OK",
						})
					} else {
						ii++;
						$(
								"<div style='display: block;word-wrap: break-word;' id='dividkeyword"
										+ ii
										+ "' ><input type='checkbox' checked class='keywordApplication' value='"
										+ value
										+ "'>&nbsp;&nbsp;<span>"
										+ value
										+ "</span><span class='glyphicon glyphicon-remove' style='margin-left:20px;cursor:pointer;' onclick='removeDivKeyword("
										+ ii + ")'  ></span></div> ").appendTo(
								"#customValueDivInclude");
						$('#command').val("");
						// $('#customValueDiv').show();
					}

				})
				
function pushSSH(){
	var keyword = 0;
	var keywordArr = [];
	$('#pushSShSpinner').show();
	$('input[class="keywordApplication"]:checked').each(function() {
		
		keywordArr[keyword++] = this.value;
	});
	
	
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/"
			+ l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/checkPushSSH";
	$.ajax(
			{
				type : 'POST',
				url : serviceUrl,
				data : 'ipAddress=' + $('#ipAddress').val()
						+ '&username=' + $('#username').val()
						+ '&password=' + $('#password').val()
						+ '&command=' + keywordArr,

			}).done(function(data) {
		$('#pushSShSpinner').hide();
		var html = data;
		document.getElementById("result").innerHTML = html;

		$('#pushSSH_result').show();
	});
}
