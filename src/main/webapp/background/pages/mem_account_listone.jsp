<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- userinfo資料 -->
<%@ page import="com.userinfo.entity.*" %>

<% UserInfo userinfo = (UserInfo) session.getAttribute("userinfo");
System.out.println("我在JSP"+userinfo+"為何");
%>

<!-- 先取出BuildingInfo List以供常用大樓選單使用 -->
<%@ page import="com.buildinginfo.entity.*"%>
<%
BuildingInfo buildingInfo = userinfo.getBuildinginfo();
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

				<li class="nav-item"><a class="nav-link" href="../index3.jsp"
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
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->

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
			<!-- 以下預計放置搜尋出的畫面 -->
			<div class="card">
				<div class="card-body">
				<p style=" text-align: center">成功更新會員資料!</p>
						<table class="table table-bordered">
							<thead>
								<tr style="text-align: center;">
									<th>會員編號</th>
									<th>會員帳號</th>
									<th>會員姓名</th>
									<th>會員電話</th>
									<th>會員暱稱</th>
									<th>註冊時間</th>
									<th>會員生日</th>
									<th>常用大樓</th>
									<th>照片</th>
								</tr>
							</thead>
							
							<tbody>
									<tr>
										<td>${userinfo.userID}</td>
										<td>${userinfo.userAccount}</td>
										<td>${userinfo.userName}</td>
										<td>${userinfo.userPhone}</td>
										<td>${userinfo.userNickName}</td>
										<td>${userinfo.userRegisterTime}</td>
										<td>${userinfo.userBirthday}</td>
<%-- 										<td>${userinfo.buildinginfo}</td> --%>
										<td>${userinfo.buildinginfo.buildingID} ${userinfo.buildinginfo.buildingName}</td>
										<td><img width="100" src="UserImage?userID=${userinfo.userID}" ></td>
									</tr>
							</tbody>
						</table>
				</div>

<!-- ======= 底部頁數/頁碼 ======= -->
<div class="card-footer bg-transparent ">
	<ul class="pagination justify-content-center ">
	<a href="${pageContext.request.contextPath}/background/pages/mem_account.jsp">回會員資料查詢頁</a>
	</ul>
	
	
</div>

				</div>
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
	
 <!-- 引入selfjs -->
<%@ include  file="pagejs.file" %>

</body>

</html>