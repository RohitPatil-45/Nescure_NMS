<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NMS | Log in</title>

<!-- Google Font: Source Sans Pro -->
<!-- <link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/css/fontAwesome.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/webtemplate/dist/img/AdminLTELogo.png">
</head>
<style>
.block_1:after {
	position: absolute;
	content: " ";
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 0;
	background-color: rgba(255, 0, 0, 0.5);
}
</style>
<body class="hold-transition login-page "
	style="background-image: url(<%=request.getContextPath()%>/webtemplate/dist/img/MainBGImage.jpg); background-size: cover; position: relative;">
	<div class="login-box">
		<!-- /.login-logo -->
		<div class="card"
			style="background: rgb(0 0 0/ 48%); border-radius: 16px; box-shadow: 2px 2px 13px 17px rgba(0, 0, 0, 0.1); backdrop-filter: blur(6.3px); -webkit-backdrop-filter: blur(6.3px); color: white;">
			<div class="card-header text-center">
				<img class="animation__wobble" width="300"
					src="<%=request.getContextPath()%>/webtemplate/dist/img/nesecure1.png">
			</div>
			<div class="card-body">
				<p class="login-box-msg">Sign in to start your session</p>

				<form class="pressEnter">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="User Name"
							id="username">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" placeholder="Password"
							id="password">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-8">
							<div class="icheck-primary">
								<input type="checkbox" id="ad"> <label for=ad>
									AD </label>
							</div>
						</div>
					</div>
					<div class="row">
						<!-- /.col -->
						<div class="col-4">
							<button type="button"
								style="margin-top: 10px; margin-left: 110px; background: #0074d9ba; border-radius: 9px; box-shadow: 0 4px 12px 11px rgb(0 0 0/ 5%); backdrop-filter: blur(6.3px); -webkit-backdrop-filter: blur(6.3px); color: white;"
								onclick="checkDBChreds()" class="btn  btn-block"
								id="login-button">
								Sign In&nbsp;<i style='margin-left: 10px; display: none;'
									id='laoderforlogin' class='fa fa-spinner fa-spin'></i>
							</button>

						</div>
						<!-- /.col -->
					</div>
				</form>

				<!--  <div class="social-auth-links text-center mt-2 mb-3">
					<a href="#" class="btn btn-block btn-primary"> <i
						class="fas fa-ad mr-2"></i> Sign in using Active Directory
					</a>-->
				<!--         <a href="#" class="btn btn-block btn-danger"> -->
				<!--           <i class="fab fa-google-plus mr-2"></i> Sign in using Google+ -->
				<!--         </a> -->
				<!--       </div> -->
				<!-- /.social-auth-links -->

				<!-- <p class="mb-1">
        <a href="forgot-password.html">I forgot my password</a>
      </p> -->
				<!--       <p class="mb-0"> -->
				<!--         <a href="register.html" class="text-center">Register a new membership</a> -->
				<!--       </p> -->
			</div>
			<!-- /.card-body -->
		</div>
		<!-- /.card -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/sweetalert2/sweetalert2.min.js"></script>
	<script src="<%=request.getContextPath()%>/custom_js/login.js"></script>
</body>
</html>
