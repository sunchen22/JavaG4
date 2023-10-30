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

<!-- userinfo資料 -->
<%@ page import="com.userinfo.entity.*" %>
<%
UserInfo userinfo = (UserInfo) request.getAttribute("userinfo"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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
	href="${pageContext.request.contextPath}/background/plugins/fontawesome-free/css/all.min.css">
<!-- IonIcons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/background/dist/css/adminlte.min.css">

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

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/background/pages/index3.jsp" 
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
		
 <!-- 引入側邊欄 -->
<%@ include  file="pageaside.file" %>

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
						<!-- /.card-header -->
						
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
						<FORM METHOD="post" ACTION="user.do" name="form1" enctype="multipart/form-data">
							<div class="card-body" style="padding-bottom: 5px;">
								<div class="row">
									<div class="form-group col-4">
										<label>會員編號：</label>
										 <input class="form-control " readonly="readonly" name="userID" value="${userinfo.userID}">
									</div>

									<div class="form-group col-4">
										<label>會員帳號：</label> 
										<input type="text" class="form-control" readonly="readonly"  name="userAccount" value="${userinfo.userAccount}">
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
							
							<div style="text-align: center;  background: transparent ; margin:5px">
								<input type="hidden" name="action" value="update"> 
								<input type="hidden" name="userID" value="${userinfo.userID}">
								<input type="submit" value="送出修改" class="btn btn-warning">
							</div>
							</FORM>
							<FORM METHOD="post" ACTION="user.do" >
							<div style="text-align: center;">
								<input type="hidden" name="action" value="cancel"> 
								<input type="hidden" name="userID" value="${userinfo.userID}">
								<input type="submit" value="取消" class="btn btn-warning">
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
	<script src="${pageContext.request.contextPath}/background/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/background/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE -->
	<script src="${pageContext.request.contextPath}/background/dist/js/adminlte.js"></script>
	<!-- OPTIONAL SCRIPTS -->
	<script src="${pageContext.request.contextPath}/background/plugins/chart.js/Chart.min.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<!-- <script src="../dist/js/pages/dashboard3.js"></script> -->
	
 <!-- 引入selfjs -->
<%@ include  file="pagejs.file" %>
    
    
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