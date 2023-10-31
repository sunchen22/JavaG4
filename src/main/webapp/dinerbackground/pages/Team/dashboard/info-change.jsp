<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%@ page import="com.dinerinfo.service.*"%>

<%
// 	DinerInfoServiceImpl dinerSvc = new DinerInfoServiceImpl();
// 	DinerInfo account = dinerSvc.getDinerInfoByDinerID(1);
//測試用，之後順利連結頁面後要改以下這種

DinerInfo account = (DinerInfo) session.getAttribute("account");
// String dinerStatus = account.getDinerStatus();
//這是login之後傳進來的account，代表已登錄後才能看到的資料
%>

<html lang="zh-Hant">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>樓頂揪樓咖 商家資料管理</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<!-- <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css"> -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
	integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">
</head>

<body
	class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
	<div class="wrapper">


		<%@ include file="/dinerbackground/pages/Team/dinerNav.file"%>



		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">公司資料變更</h1>
						</div>
						<div class="card col-12">
							<div class="card-body register-card-body">
								<p class="login-box-msg">送出需要修改的資訊後，將有專人與您聯絡</p>
								<p class="login-box-msg">確認資訊無誤後，將為您更新資訊</p>

								<%-- 					不可重複申請 訊息 --%>
								<c:if test="${not empty alreadyApplyMsg}">
									<font style="color: yellow">${alreadyApplyMsg}</font>
								</c:if>

								<c:if test="${account.dinerStatus == 'changed'}">
									<div class="alert alert-warning">您已申請過變更資料，請靜待審核</div>
								</c:if>

								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
								
								<%-- 申請成功、沒有變更 訊息 --%>
								<c:if test="${not empty successMsg}">
									<font style="color: green">${successMsg}</font>
								</c:if>


								<form
									action="<%=request.getContextPath()%>/dinerbackground/pages/Team/dashboard/dinerInfo.do"
									method="post">
									

									<div class="row">
										<!-- 商店名稱 -->
										<div class="input-group mb-3 col-6">
											<input type="text" name="dinerName" class="form-control"
												placeholder="商店名稱"
												value="${empty modifiedData ? account.dinerName : modifiedData.dinerName}"
												${account.dinerStatus == 'changed' ? 'disabled' : ''}>

											<!--                       <input type="text" name="dinerName" class="form-control" placeholder="商店名稱" -->
											<%--                       		value="<%=account.getDinerName()%>"> --%>

											<div class="input-group-append">
												<div class="input-group-text">
													<span class="fas fa-store"></span>
												</div>
											</div>
										</div>

										<!-- 銀行帳戶 銀行代碼 -->
										<div class="input-group mb-3 col-6">
											<input type="text" name="dinerBank" class="form-control"
												placeholder="銀行帳戶 銀行代碼 "
												value="${empty modifiedData ? account.dinerBank : modifiedData.dinerBank}"
												${account.dinerStatus == 'changed' ? 'disabled' : ''}>
											<!--                       <input type="text" name="dinerBank" class="form-control" placeholder="銀行帳戶 銀行代碼 "  -->
											<%--                       		value="<%=account.getDinerBank()%>"> --%>
											<div class="input-group-append">
												<div class="input-group-text">
													<span class="fas fa-user"></span>
												</div>
											</div>
										</div>

										<!-- 商店地址 -->
										<div class="input-group mb-3 col-6">
											<input type="text" name="dinerAddress" class="form-control"
												placeholder="商店地址"
												value="${empty modifiedData ? account.dinerAddress : modifiedData.dinerAddress}"
												${account.dinerStatus == 'changed' ? 'disabled' : ''}>
											<!--                       <input type="text" name="dinerAddress" class="form-control" placeholder="商店地址"  -->
											<%--                       		value="<%=account.getDinerAddress()%>"> --%>
											<div class="input-group-append">
												<div class="input-group-text">
													<span class="fas fa-map-marker-alt"></span>

												</div>
											</div>
										</div>

										<!-- 銀行帳戶 帳戶號碼 -->
										<div class="input-group mb-3 col-6">
											<input type="text" name="dinerAccount" class="form-control"
												placeholder="銀行帳戶 帳戶號碼"
												value="${empty modifiedData ? account.dinerAccount : modifiedData.dinerAccount}"
												${account.dinerStatus == 'changed' ? 'disabled' : ''}>
											<!--                       <input type="text" name="dinerAccount" class="form-control" placeholder="銀行帳戶 帳戶號碼"  -->
											<%--                       		value="<%=account.getDinerAccount()%>"> --%>
											<div class="input-group-append">
												<div class="input-group-text">
													<span class="fas fa-university"></span>
												</div>
											</div>
										</div>

										<!-- 聯絡人姓名 -->
										<div class="input-group mb-3 col-6">
											<input type="text" name="dinerContact" class="form-control"
												placeholder="聯絡人姓名"
												value="${empty modifiedData ? account.dinerContact : modifiedData.dinerContact}"
												${account.dinerStatus == 'changed' ? 'disabled' : ''}>
											<!--                       <input type="text" name="dinerContact" class="form-control" placeholder="聯絡人姓名" -->
											<%--                       		 value="<%=account.getDinerContact()%>"> --%>
											<div class="input-group-append">
												<div class="input-group-text">
													<span class="fas fa-user"></span>
												</div>
											</div>
										</div>

										<!-- 銀行帳戶 戶名 -->
										<div class="input-group mb-3 col-6">
											<input type="text" class="form-control"
												name="dinerAccountName" placeholder="銀行帳戶 戶名"
												value="${empty modifiedData ? account.dinerAccountName : modifiedData.dinerAccountName}"
												${account.dinerStatus == 'changed' ? 'disabled' : ''}>
											<!--                       <input type="text" class="form-control" name="dinerAccountName" placeholder="銀行帳戶 戶名"  -->
											<%--                       		value="<%=account.getDinerAccountName()%>"> --%>
											<div class="input-group-append">
												<div class="input-group-text">
													<span class="fas fa-money-check-alt"></span>

												</div>
											</div>
										</div>

										<!-- 聯絡電話  請留手機號碼-->
										<div class="input-group mb-3 col-6">
											<input type="text" name="dinerPhone" class="form-control"
												placeholder="聯絡電話 (請填寫手機)"
												value="${empty modifiedData ? account.dinerPhone : modifiedData.dinerPhone}"
												${account.dinerStatus == 'changed' ? 'disabled' : ''}>
											<!--                       <input type="text" name="dinerPhone" class="form-control" placeholder="聯絡電話 (請填寫手機)" -->
											<%--                       		value="<%=account.getDinerPhone()%>"> --%>
											<div class="input-group-append">
												<div class="input-group-text">
													<span class="fas fa-phone"></span>
												</div>
											</div>
										</div>

										<!-- 聯絡人email  -->
										<div class="input-group mb-3 col-6">
											<input type="email" name="dinerEmail" class="form-control"
												placeholder="聯絡人email"
												value="${empty modifiedData ? account.dinerEmail : modifiedData.dinerEmail}"
												${account.dinerStatus == 'changed' ? 'disabled' : ''}>
											<!--                       <input type="email" name="dinerEmail" class="form-control" placeholder="聯絡人email" -->
											<%--                       		value="<%=account.getDinerEmail()%>"> --%>
											<div class="input-group-append">
												<div class="input-group-text">
													<span class="fas fa-envelope"></span>
												</div>
											</div>
										</div>

										<!-- 統編  (將來會作為登入帳號使用) -->
										<div class="input-group mb-3 col-6">
											<input type="text" name="dinerTaxID" class="form-control"
												placeholder="統編"
												value="${empty modifiedData ? account.dinerTaxID : modifiedData.dinerTaxID}"
												${account.dinerStatus == 'changed' ? 'disabled' : ''}>
											<!--                       <input type="text" name="dinerTaxID" class="form-control" placeholder="統編" -->
											<%--                       		value="<%=account.getDinerTaxID()%>"> --%>
											<div class="input-group-append ">
												<div class="input-group-text ">
													<span class="fas fa-signature"></span>
												</div>
											</div>
										</div>


										<!-- 請問您販售的商品是甚麼類型? * -->
										<div class="form-group form-inline mb-3 col row">
											<div class="col-4" style="text-align: right;">
												<label for="dinerType">您販售的商品類型</label>
											</div>
											<div class="col">
												<select name="dinerType" class="form-control w-100"
													${account.dinerStatus == 'changed' ? 'disabled' : ''}>
													<option value="M"
														${empty modifiedData ? (account.dinerType == 'M' ? 'selected' : '') : (modifiedData.dinerType == 'M' ? 'selected' : '')}>單純餐點</option>
													<option value="D"
														${empty modifiedData ? (account.dinerType == 'D' ? 'selected' : '') : (modifiedData.dinerType == 'D' ? 'selected' : '')}>單純飲料</option>
													<option value="X"
														${empty modifiedData ? (account.dinerType == 'X' ? 'selected' : '') : (modifiedData.dinerType == 'X' ? 'selected' : '')}>複合餐飲</option>
												</select>
												<%--                           <option value="M" <%= (account.getDinerType() != null && account.getDinerType().equals("M")) ? "selected" : "" %> >單純餐點</option> --%>
												<%--     					  <option value="D" <%= (account.getDinerType() != null && account.getDinerType().equals("D")) ? "selected" : "" %> >單純飲料</option> --%>
												<%--    						  <option value="X" <%= (account.getDinerType() != null && account.getDinerType().equals("X")) ? "selected" : "" %> >複合餐飲</option> --%>
											</div>
										</div>
									</div>

									<!-- 密碼 -->
									<div class="row">
										<div class="input-group mb-3 col-6">

											<input type="text" name="dinerPassword" class="form-control"
												value="${empty modifiedData ? account.dinerPassword : modifiedData.dinerPassword}"
												${account.dinerStatus == 'changed' ? 'disabled' : ''}>
											<!-- 											<input type="text" name="dinerPassword" class="form-control" -->
											<%-- 												value="<%=account.getDinerPassword()%>"> --%>
											<div class="input-group-append">
												<div class="input-group-text">
													<span class="fas fa-lock"></span>
												</div>
											</div>
										</div>
										<!-- /.col -->
										<div class="col-4 mr-1">

											<input type="hidden" name="dinerID"
												value="${account.dinerID}">
											<!-- 											<input type="hidden" -->
											<%-- 												name="dinerUpdate" value="${account.dinerUpdate}">  --%>
											<input type="hidden" name="action" value="dinerInfoChange">
											<!--這個隱藏格是為了再送出整個表單時有個錨定點 -->
											<button type="submit" class="btn btn-primary btn-block"
												id="submitButton">確認修改</button>
											<!--                       <button type="submit" class="btn btn-primary btn-block" onclick="submitForm()">確認修改</button> -->

										</div>
										<!-- /.col -->
									</div>
								</form>
							</div>
							<!-- /.row -->
						</div>
						<!-- /.container-fluid -->
					</div>
					<!-- /.content-header -->

					<!-- Main content -->
					<section class="content">
						<div class="container-fluid"></div>
						<!--/. container-fluid -->
					</section>
					<!-- /.content -->
				</div>
				<!-- /.content-wrapper -->

				<!-- Control Sidebar -->
				<aside class="control-sidebar control-sidebar-dark">
					<!-- Control sidebar content goes here -->
				</aside>
				<!-- /.control-sidebar -->


			</div>
			<!-- ./wrapper -->

			<!-- REQUIRED SCRIPTS -->
			<!-- jQuery -->
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery/jquery.min.js"></script>
			<!-- Bootstrap -->
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
			<!-- overlayScrollbars -->
			<script
				src="<%=request.getContextPath()%>/dinerbackground/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
			<!-- AdminLTE App -->
			<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/adminlte.js"></script>

			<!-- PAGE PLUGINS -->
			<!-- jQuery Mapael -->
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/raphael/raphael.min.js"></script>
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery-mapael/jquery.mapael.min.js"></script>
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery-mapael/maps/usa_states.min.js"></script>
			<!-- ChartJS -->
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/chart.js/Chart.min.js"></script>

			<!-- AdminLTE for demo purposes -->
			<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/demo.js"></script>
			<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
			<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/pages/dashboard2.js"></script>

			<script>
			document.getElementById('submitButton').addEventListener('click', function(event) {
			    var dinerStatus = '<%=account.getDinerStatus()%>';
							if (dinerStatus == 'changed') {
								event.preventDefault(); // 阻止表單提交
								alert('管理員已在審核過程中，請勿二次提交');
							}
						});
			</script>
</body>

</html>