<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.usernews.service.*"%>
<%@ page import="com.usernews.entity.*"%>

<%
UserNews usernews = (UserNews) request.getAttribute("usernews");
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
div.task_list_parent ul.task_list>li:first-child button.btn_up {
	background-color: lightgray !important;
	cursor: not-allowed !important;
	color: gray;
}

div.task_list_parent ul.task_list>li:last-child button.btn_down {
	background-color: lightgray !important;
	cursor: not-allowed !important;
	color: gray;
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
					href="mem_news.html" class="nav-link">會員最新消息</a></li>
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
						<img src="../dist/img/emp02.png" class="img-circle elevation-2"
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
						<%@ include file="pageaside.file"%>
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

			<section class="content">
				<div class="container-fluid">

					<div class="card">
						<div class="card-header">
							<div class="card-title" style="align-items: center;">修改會員最新消息</div>

						</div>

						<div class="card-body">

							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font color='red'>請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<div class="card-body" style="padding-bottom: 5px;">
								<div class="row">
									<div class="form-group col-4">
										<label>廣告編號：</label>
											<input class="form-control " readonly="readonly" name="userNewsID" value="${usernews.userNewsID}">
									</div>

										<%
										String userNewsContent;
										%>
									<div class="form-group col-4">
										<label>最新消息：</label>
										<input type="text" class="form-control" name="userNewsContent"  value="${usernews.userNewsContent}">
<%-- 									<input type="text" class="form-control" name="userNewsContent" value="<%=usernews.getUserNewsContent()%>"> --%>
									</div>

									<div class="form-group col-4">
										<label>狀態：</label> <select class="form-control" name="userNewsStatus">
											<option value="1" ${(usernews.userNewsStatus == '1') ? 'selected' : ''}>上架</option>
											<option value="0" ${(usernews.userNewsStatus == '0') ? 'selected' : ''}>下架</option>
										</select>
									</div>
								</div>

							</div>
						</div>
						<!-- /.card-body -->
						<div class="form-inlinecard-footer"
							style="text-align: center; padding-top: 5px; display: flex; justify-content: center; ">
							<FORM METHOD="post" ACTION="usernews.do" name="form1"
								enctype="multipart/form-data">

								<div style="text-align: center;  background: transparent ; margin:5px">
									<input type="hidden" name="action" value="update">
									<input type="hidden" name="userNewsID" value="${usernews.userNewsID}">
									<input type="submit" value="送出修改" class="btn btn-warning">
								</div>
							</FORM>

							<FORM METHOD="post" ACTION="usernews.do">
								<div style="text-align: center;  background: transparent ; margin:5px">
									<input type="hidden" name="action" value="cancel"> <input
										type="hidden" name="userNewsID" value="${usernews.userNewsID}">
									<input type="submit" value="取消" class="btn btn-warning">

								</div>
							</FORM>
						</div>

					</div>
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

	<!-- 引入selfjs -->
	<%@ include file="pagejs.file"%>


</body>

</html>