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
				<p class="login-box-msg ">您已登出嘍～</p>
				<div id="timeBox" style="text-align:center"></div>

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

	<!-- 設定跳轉秒數 -->
	<script>
		var count = 3;
		function countDown() {
			var str = '<span>秒後跳轉至登入頁面</span>';
			document.getElementById("timeBox").innerHTML = count+str;
			count -= 1;
			if (count == 0) {
				location.href = "<%=request.getContextPath()%>/background/login.jsp";
			}
			setTimeout("countDown()", 1000); // 設定每秒執行1次
		}
		countDown();
	</script>
</body>
</html>
