<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dinerinfo.entity.*"%>

<%
//此為輸入格式有錯誤時的dinerInfo物件
DinerInfo dinerInfo = (DinerInfo) request.getAttribute("dinerInfo");
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>樓頂揪樓咖 | 商家會員註冊</title>

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
<body class="hold-transition register-page">
	<div class="register-box w-100">
		<div class="register-logo">
			<a href="<%=request.getContextPath()%>/consumer/index.jsp"><b>樓頂揪樓咖</b>商家註冊</a>
		</div>

		<div class="card col-12">
			<div class="card-body register-card-body">
				<p class="login-box-msg">請留下您的資訊，我們將由專人與您聯繫</p>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<form METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/register/dinerInfo.do" name="form1">
					<div class="row">
						<!-- 商店名稱 -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerName" class="form-control" placeholder="商店名稱"
								value="<%=(dinerInfo != null) ? dinerInfo.getDinerName() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-store"></span>
								</div>
							</div>
						</div>

						<!-- 銀行帳戶 銀行代碼 -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerBank" class="form-control"
								placeholder="銀行帳戶 銀行代碼 " value="<%=(dinerInfo != null) ? dinerInfo.getDinerBank() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-user"></span>
								</div>
							</div>
						</div>

						<!-- 商店地址 -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerAddress" class="form-control"
								placeholder="商店地址" value="<%=(dinerInfo != null) ? dinerInfo.getDinerAddress() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-map-marker-alt"></span>

								</div>
							</div>
						</div>

						<!-- 銀行帳戶 帳戶號碼 -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerAccount" class="form-control"
								placeholder="銀行帳戶 帳戶號碼" value="<%=(dinerInfo != null) ? dinerInfo.getDinerAccount() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-university"></span>
								</div>
							</div>
						</div>

						<!-- 聯絡人姓名 -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerContact" class="form-control"
								placeholder="聯絡人姓名" value="<%=(dinerInfo != null) ? dinerInfo.getDinerContact() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-user"></span>
								</div>
							</div>
						</div>

						<!-- 銀行帳戶 戶名 -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerAccountName" class="form-control"
								placeholder="銀行帳戶 戶名" value="<%=(dinerInfo != null) ? dinerInfo.getDinerAccountName() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-money-check-alt"></span>

								</div>
							</div>
						</div>

						<!-- 聯絡電話  請留手機號碼-->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerPhone" class="form-control"
								placeholder="聯絡電話 (請填寫手機)" value="<%=(dinerInfo != null) ? dinerInfo.getDinerPhone() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-phone"></span>
								</div>
							</div>
						</div>

						<!-- 聯絡人email  -->
						<div class="input-group mb-3 col-6">
							<input type="email" name="dinerEmail" class="form-control"
								placeholder="聯絡人email" value="<%=(dinerInfo != null) ? dinerInfo.getDinerEmail() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-envelope"></span>
								</div>
							</div>
						</div>
						<!-- 統編  (將來會作為登入帳號使用) -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerTaxID" class="form-control"
								placeholder="統編 " value="<%=(dinerInfo != null) ? dinerInfo.getDinerTaxID() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-signature"></span>
								</div>
							</div>
						</div>

						<!-- 請問您販售的商品是甚麼類型? * -->
						<div class="form-group form-inline mb-3 col-6 row">
							<div class="col-4" style="text-align: right;">
								<label for="dinerType">您販售的商品類型</label>
							</div>
							<div class="col-8">
								<select name="dinerType" class="form-control w-100">
									<option value="M">單純餐點</option>
									<option value="D">單純飲料</option>
									<option value="X">複合餐飲</option>
								</select>
							</div>
						</div>



					</div>


					<div class="row">
						<div class="col-8">
							<div class="icheck-primary">
<!-- 								<input type="checkbox" id="agreeTerms" name="terms" -->
<!-- 									value="agree"> <label for="agreeTerms"> 我同意<a -->
<!-- 									href="#">使用者條款</a>及<a href="#">隱私權條款</a> -->
<!-- 								</label> -->
							</div>
						</div>
						<!-- /.col -->
						<div class="col-4">
							<input type="hidden" name="action" value="insert">
							<button type="submit" class="btn btn-primary btn-block">送出註冊申請</button>
						</div>
						<!-- /.col -->
					</div>
				</form>



				<a href="${pageContext.request.contextPath}/dinerbackground/pages/Team/login/login-form.jsp" class="text-center">已是商家會員?請前往登入</a>
			</div>
			<!-- /.form-box -->
		</div>
		<!-- /.card -->
	</div>
	<!-- /.register-box -->

	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/adminlte.min.js"></script>
</body>
</html>