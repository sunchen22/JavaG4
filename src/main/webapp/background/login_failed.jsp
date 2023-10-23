<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>樓頂揪樓咖後台管理</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet"
	href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/adminlte.min.css">
</head>
<body class="hold-transition login-page">
		<div class="login-box">
		<div class="login-logo">
			<img height="60px" src="../background/dist/img/Logo.png"
				alt="樓頂揪樓咖 Logo" class="brand-image img-circle elevation-3"
				style="opacity: .8"> <span
				class="brand-text font-weight-normal"
				style="text-align: start; font-size: 80%;">樓頂揪樓咖</span>
		</div>

		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg "
					style="font-weight: bold; padding-bottom: 10px;">後台管理平台</p>
				<div style="text-align: center; padding-bottom: 10px;">
					<i class="fa fa-exclamation-circle fa-3x" style="color: #DC143C;"></i>
				</div>
				<p class="login-box-msg ">登入失敗，請重新輸入帳號、密碼</p>

				 <form action="bglogin" method="post">
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="account" value="" placeholder="請輸入帳號">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" name="password" value="" placeholder="請輸入密碼">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-8">
<!-- 							<div class="icheck-primary"> -->
<!-- 								<input type="checkbox" id="remember"> <label -->
<!-- 									for="remember"> Remember Me </label> -->
<!-- 							</div> -->
						</div>
						<!-- /.col -->
				          <div class="col-4">
				          	<input type="submit" value="登入" class="btn btn-warning btn-block" style="font-weight:bold">
				          	<input type="hidden" name="action" value="login">
				          </div>
						<!-- /.col -->
					</div>
				</form>

				<!-- <div class="social-auth-links text-center mb-3"> -->
				<!-- <p>- OR -</p>
        <a href="#" class="btn btn-block btn-primary">
          <i class="fab fa-facebook mr-2"></i> Sign in using Facebook
        </a> -->
				<!-- <a href="#" class="btn btn-block btn-danger">
          <i class="fab fa-google-plus mr-2"></i> Sign in using Google+
        </a> -->
				<!-- </div> -->
				<!-- /.social-auth-links -->

				<!-- <p class="mb-1">
        <a href="forgot-password.html">忘記密碼</a>
      </p> -->
				<!-- <p class="mb-0">
        <a href="register.html" class="text-center">Register a new membership</a>
      </p> -->
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script src="plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/adminlte.min.js"></script>
</body>
</html>
