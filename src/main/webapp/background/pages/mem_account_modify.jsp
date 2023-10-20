<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 先取出BuildingInfo List以供常用大樓選單使用 -->
<%@ page import="com.buildinginfo.entity.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.buildinginfo.dao.*"%>
<%
BuildingInfoDAO buildingInfoDAO = new BuildingInfoDAOHibernateImpl();
List<BuildingInfo> buildingList = buildingInfoDAO.getAll();
pageContext.setAttribute("buildingList", buildingList);
%>

<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>樓頂揪樓咖後台管理</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="../plugins/fontawesome-free/css/all.min.css">
<!-- IonIcons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../dist/css/adminlte.min.css">

<style>
#preview {
	border: 1px solid lightgray;
	display: inline-block;
	width: 100px;
	min-height: 100px;
	position: relative;
}

#preview span.text {
	position: absolute;
	display: inline-block;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: -1;
	color: lightgray;
}

#preview img.preview_img {
	width: 100%;
}
</style>

</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button" onclick="toggleLogoutButton()"><i
						class="fas fa-bars"></i></a></li>

				<li class="nav-item d-none d-sm-inline-block "><a
					href="mem_account.jsp" class="nav-link">會員資料查詢</a></li>
			</ul>

			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">

				<li class="nav-item"><a class="nav-link" href="../index3.html"
					role="button"> <i class="fas fa-home"></i>
				</a></li>

				<li class="nav-item"><a class="nav-link"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>

			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<!-- 有修改顏色 原本sidebar-dark-primary -->
		<aside class="main-sidebar sidebar-light-warning elevation-4">
			<!-- Brand Logo -->
			<a href="../index3.html" class="brand-link"> <img
				src="../dist/img/Logo.png" alt="樓頂揪樓咖 Logo"
				class="brand-image img-circle elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-normal">後台管理平台</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<img src="../dist/empimg/emp02.png" class="img-circle elevation-2"
							alt="emp01">
					</div>
					<div class="info">
						<a href="#" class="d-block">小丸子</a>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">

<!-- 引入側邊欄 -->
<%@ include  file="pageaside.file" %>

							<ul class="nav nav-pills nav-sidebar " data-accordion="false"
								style="justify-content: flex-end">
								<li class="col-sm-5">
									<button id="logoutButton" type="button"
										class="btn btn-block btn-outline-warning btn-sm">
										<i class="fa fa-sign-out-alt  nav-icon"></i>登出
									</button>
								</li>
							</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1></h1>
						</div>

					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<!-- general form elements -->
			<section class="content">
				<div class="col-md-10">
					<div class="card card-warning">
						<div class="card-header">
							<h3 class="card-title">
								<i class="fa fa-id-card"></i>&nbsp;修改會員資料
							</h3>
						</div>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
						<!-- /.card-header -->
						<FORM METHOD="post" ACTION="user.do" name="form1" enctype="multipart/form-data">
							<div class="card-body" style="padding-bottom: 5px;">
								<div class="row">
									<div class="form-group col-4">
										<label>會員編號：</label>
										 <input class="form-control " readonly="readonly" name="userID" value="${userinfo.userID}">
									</div>

									<div class="form-group col-4">
										<label>會員帳號：</label> 
										<input type="text" class="form-control" name="userAccount" value="${userinfo.userAccount}">
									</div>
								
									<div class="form-group col-4">
										<label>會員姓名：</label> <input type="text" class="form-control" name="userName" value="${userinfo.userName}">
									</div>
								</div>	
								<div class="row">
									<div class="form-group col-4">
										<label>會員暱稱：</label> 
										<input type="text" class="form-control" name="userNickName"  value="${userinfo.userNickName}">
									</div>
								
									<div class="form-group col-4">
										<label>密碼：</label>
										<input type="password" class="form-control" name="userPassword" value="${userinfo.userPassword}">
									</div>
									
									<div class="form-group col-4">
										<label>會員電話：</label> <input type="text" class="form-control" name="userPhone" value="${userinfo.userPhone}">
									</div>
								</div>
								
								<div class="row">
									<div class="form-group col-4">
										<label>註冊日期：</label>
										<div class="input-group date">
											<input class="form-control" name="userRegisterTime"  readonly="readonly" value="${userinfo.userRegisterTime}">
										</div>
									</div>
								
									<div class="form-group col-3">
										<label>會員生日：</label>
										<div class="input-group date">
											<input class="form-control" name="userBirthday"  readonly="readonly" value="${userinfo.userBirthday}">
										</div>
									</div>
									
								</div>
								<div class="row">
									<div class="form-group col-6">
										<label>會員常用大樓：</label>
											<select class="form-control" name="buildingID">
														<c:forEach var="building" items="${buildingList}">
															<option value="${building.buildingID}" ${(userinfo.buildinginfo.buildingID == building.buildingID)? 'selected':''} >${building.buildingName}</option>
														</c:forEach>
											</select>
									</div>
									<div class="form-group col-4">
											<label>會員大頭照：</label> <br> 
											<input type="file"  name="userBlob" id="p_file" >
											<div id="preview">
												<img width="100" src="UserImage?userID=${userinfo.userID}" >
<%-- 			需要動態路徑嗎									<img width="100" src=" ${pageContext.request.contextPath}/background/pages/UserImage?userID=${userinfo.userID}" > --%>
											</div>
									</div>
									
								</div>
							</div>
							<!-- /.card-body -->
							<div class="card-footer" style="text-align: center; padding-top: 5px; display : inline-block">
								<input type="hidden" name="action" value="update"> 
								<input type="hidden" name="userID" value="${userinfo.userID}">
								<input type="submit" value="送出修改" class="btn btn-warning">
<%-- 								<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> --%>
							</div>
							<div style="display : inline-block; justify-content:center">
								<button type="submit" class="btn btn-warning">取消</button>
							</div>
						</FORM>
					
					
				</div>
				<!-- /.card -->
		</div>
		</section>




	</div>
	<!-- /.content-wrapper -->


	<!-- Control Sidebar -->
	<aside class="control-sidebar control-sidebar-warning">
		<!-- Control sidebar content goes here -->
	</aside>
	<!-- /.control-sidebar -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<strong>Copyright &copy; 2023</strong> 樓頂揪樓咖團隊 All rights reserved.
			<div class="float-right d-none d-sm-inline-block">
				<b>Version</b> 1.1.0
			</div>
		</footer>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->
	<script src="../plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE -->
	<script src="../dist/js/adminlte.js"></script>
	<!-- OPTIONAL SCRIPTS -->
	<script src="../plugins/chart.js/Chart.min.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<!-- <script src="../dist/js/pages/dashboard3.js"></script> -->
	<!-- 縮小時,登出按鈕消失 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<!-- 縮小時,登出按鈕消失 -->
	<script>
		function toggleLogoutButton() {
			var logoutButton = document.getElementById("logoutButton");
			if (logoutButton.style.display === "none") {
				logoutButton.style.display = "block";
			} else {
				logoutButton.style.display = "none";
			}
		}
	</script>
	<!-- 點擊側邊的會員查詢時發動 -->
	<script>
        // 當按鈕被點擊時觸發此
        document.getElementById("memBtn").addEventListener("click", function(event) {
            event.preventDefault(); 
            var url = "${pageContext.request.contextPath}/background/pages/user.do?action=getAll";
            window.location.href = url;
        });
    </script>
    
    
    <!-- 員工圖片預覽圖 -->
	<script>
	var preview_el = document.getElementById("preview");
	var p_file_el = document.getElementById("p_file");

		var preview_img = function(file) {
			var reader = new FileReader(); // 用來讀取檔案
			reader.readAsDataURL(file); // 讀取檔案
			reader.addEventListener("load",function() {
			let img_str = '<img src="' + reader.result + '" class="preview_img">';
			preview_el.innerHTML = img_str;
			});
		};

		p_file_el.addEventListener("change", function(e) {
			if (this.files.length > 0) {
				preview_img(this.files[0]);
			} else {
				preview_el.innerHTML = 'span class="text">預覽圖</span>';
			}
		});
	</script>
	
</body>

</html>