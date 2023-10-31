<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page import="com.dinerinfo.entity.*"%>

<%
DinerInfo dinerInfo = (DinerInfo) request.getAttribute("dinerInfo");
%>

<html lang="zh-Hant">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>樓頂揪樓咖 | 商家後台登入</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/fontawesome-free/css/all.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">
</head>

<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="<%=request.getContextPath()%>/consumer/index.jsp"><b>樓頂揪樓咖</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">歡迎回來~商業夥伴</p>

				<form
					action="<%=request.getContextPath()%>/dinerbackground/pages/Team/login/dinerInfo.do"
					method="post">



					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="請輸入帳號"
							name="dinerTaxID"
							value="<%=(dinerInfo != null) ? dinerInfo.getDinerTaxID() : ""%>">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" placeholder="請輸入密碼"
							name="dinerPassword"
							value="<%=(dinerInfo != null) ? dinerInfo.getDinerPassword() : ""%>">
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
<!-- 									for="remember"> 記住我 </label> -->
<!-- 							</div> -->
						</div>
						<!-- /.col -->
						<div class="col-4">
							<input type="hidden" name="action" value="dinerLogin">
							<!--這個隱藏格是為了再送出整個表單時有個錨定點 -->
							<button type="submit" class="btn btn-primary btn-block">登入</button>
						</div>
						<!-- /.col -->
					</div>

					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

				</form>


				<!-- /.social-auth-links -->

				<p class="mb-1">
					<a href="${pageContext.request.contextPath}/dinerbackground/pages/Team/login/forget-password.jsp">忘記密碼</a>
				</p>
				<p class="mb-0">
					<a href="${pageContext.request.contextPath}/dinerbackground/pages/Team/register/register-form.jsp" class="text-center">還沒有帳戶?立即註冊~</a>
				</p>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/adminlte.min.js"></script>
</body>

</html>