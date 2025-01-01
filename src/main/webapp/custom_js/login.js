$(document).on('keypress', function(e) {
	if (e.which == 13) {
		checkDBChreds();
	}
});

function checkDBChreds() {
	// alert("loginnn")
	var value = $("#username").val();
	// alert(value)
	var value2 = $("#password").val();

	if (value === '') {
		alert("Please Enter Username")
		$("#laoderforlogin").hide();

	} else if (value2 === '') {
		alert("Please Enter Password")
		$("#laoderforlogin").hide();
	} else {

		$("#laoderforlogin").show();

		var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/login/loginuser";
		$.ajax(
				{
					url : serviceUrl,
					type : 'POST',
					data : "username=" + $('#username').val() + "&password="
							+ $('#password').val(),

				}).done(
				function(data) {
					// alert("hie")
					// alert(data)
					if (data == "noScope") {
						swal.fire({
							position : 'top',
							icon : 'error',
							title : "Unsuccessful !",
							text : "Please Add Usertype Scope",
							type : "error",
							confirmButtonClass : "btn btn-success",
							confirmButtonText : "OK",

						})
						$("#laoderforlogin").hide();
					} else if (data == "success") {
						// alert("Login Successfully")

						var l = window.location;
						var base_url = l.protocol + "//" + l.host + "/"
								+ l.pathname.split('/')[1];
						// var serviceUrl23 = base_url;
						// alert(base_url)
						// alert(base_url + "/DLP")
						// var serviceUrl23 = base_url +
						// "/nodeDashboard/nodeDetails";
						var serviceUrl23 = base_url + "/login/Home";
						// alert(serviceUrl23)
						window.location.href = serviceUrl23;
						$("#laoderforlogin").hide();

					} else {
						Swal.fire({
							position : 'top',
							icon : 'error',
							title : 'Invalid Username or Password',
							showConfirmButton : false,
							timer : 3000
						})
						$("#laoderforlogin").hide();
					}

				});

	}
}

//
// $("#login-button").click(
// function() {
//
// var value = $("#username").val();
// var value2 = $("#password").val();
//
// if (value === '') {
// alert("Please Enter Username")
// // Swal.fire({
// // position : 'top',
// // icon : 'warning',
// // heightAuto : false,
// // title : 'Please Enter Username',
// // showConfirmButton : true
// //
// // });
// } else if(value2 === '') {
// alert("Please Enter Password")
// // Swal.fire({
// // position : 'top',
// // icon : 'warning',
// // heightAuto : false,
// // title : 'Please Enter Password',
// // showConfirmButton : true
// //
// // })
// }
// else {
// var l = window.location;
// var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
// var serviceUrl1 = base_url + "/login/loginuser";
// $.ajax({
// type : 'GET',
// url : serviceUrl1,
// data: 'username=' + $("#username").val()+
// '&password=' + $("#password").val(),
// // dataType : 'json',
// success : function(data) {
// alert("nodeStatusHistoryDetails")
// alert(data)
// if ((data == "error") || (data == "")
// || (data == " ") || data == "userntmtch") {
// alert("Invalid Username & Password")
// // Swal.fire({
// // position : 'top',
// // icon : 'warning',
// // heightAuto : false,
// // title : 'Invalid Password',
// // showConfirmButton : false,
// // timer : 3000
// // })
// //
// // } else if (data == "userntmtch") {
// // Swal.fire({
// // position : 'top',
// // icon : 'warning',
// // heightAuto : false,
// // title : 'Username does not exist',
// // showConfirmButton : false,
// // timer : 3000
// // })
//
// // alert("Username does not exist")
// }
//
// else {
//
// // Swal.fire({
// // position : 'top',
// // icon : 'warning',
// // heightAuto : false,
// // title : 'Login Sucessfully',
// // showConfirmButton : false,
// // timer : 3000
// // })
//
// alert("Login Sucessfull")
// var l = window.location;
// var base_url = l.protocol + "//" + l.host + "/"
// + l.pathname.split('/')[1];
// // var serviceUrl23 = base_url;
// // alert(base_url)
// // alert(base_url + "/DLP")
// // var serviceUrl23 = base_url + "/nodeDashboard/nodeDetails";
// var serviceUrl23 = base_url + "/login/Home";
// // alert(serviceUrl23)
// window.location.href = serviceUrl23;
// } ;
//
//
// }
// });
// // }
// }
// }).show();
