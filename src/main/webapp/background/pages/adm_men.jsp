<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.webempadmin.model.*"%>

<%
WebempadminService empSvc = new WebempadminService();
List<WebempadminVO> list = empSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html lang="en">

<head>
<!--   <meta charset="utf-8"> -->
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


<!-- 管理員表格頭像的設定 -->
<style>
.emp_table_img {
	height: 40px;
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
					href="adm_men.jsp" class="nav-link">管理者帳號管理</a></li>
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
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->

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
					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

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
			<!-- <section class="content">從這裡開始 -->

			<div class="col-12" style="padding-left: 20px;">
				<div class="card">
					<div class="card-header">
						<div class="card-title" style="align-items: center;">管理者帳號</div>
						<div style="display: flex; justify-content: right;">
							<a href="adm_men_add.jsp" class="btn btn-warning "><i
								class="fa fa-plus-circle"></i>增加管理者</a>
							<!-- <button class="btn btn-warning "><i class="fa fa-plus"></i>增加管理者</button> -->
						</div>
					</div>

					<div class="card-body">
						<div class="table-responsive">
							<table class="table card-table table-vcenter text-nowrap">
								<thead>
									<tr>
										<th>員工編號</th>
										<th>姓名</th>
										<th>大頭照</th>
										<th>管理權限</th>
										<th>入職日期</th>
										<th>修改</th>
										<th>停權</th>
									</tr>
								</thead>

								<tbody id="table-body">
									<%@ include file="adm_men1.file"%>
									<c:forEach var="empIDVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">
										<tr>
											<td>${empIDVO.empID}</td>
											<td>${empIDVO.empName}</td>
											<td><img
												src="<%= request.getContextPath()%>/pages/emp.photo?empID=${empIDVO.empID}"
												class="img-circle elevation-1 emp_table_img"></td>
											<td>${empIDVO.empAdminAuthorization}</td>
											<td>${empIDVO.empArriveDate}</td>
											<td>
												<div >
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/background/pages/emp.do"
													ENCTYPE="multipart/form-data" style="margin-bottom: 0px;" id="modifybtn">
													<input type="submit" value="修改" class="btn btn-warning btn-sm" id="modifybtn1"> 
														<input type="hidden" name="empID" value="${empIDVO.empID}" id="modifybtn2">
														<input type="hidden" name="action" value="getOne_For_Update" id="modifybtn3">															
												</FORM>
												<div>
											</td>
											<td>
<%-- 刪除	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/background/pages/emp.do" style="margin-bottom: 0px;"> --%>
												<FORM METHOD="post" style="margin-bottom: 0px;" id="suspendbtn">
													<input type="submit" value="已離職" class="btn btn-danger btn-sm" > 
													<input type="hidden" name="empID" value="${empIDVO.empID}" >
<!-- 刪除	 <input type="hidden" name="action" value="delete"> -->

												</FORM>
											</td>
										</tr>

									</c:forEach>
								</tbody>
							</table>
							<%@ include file="adm_men2.file"%>
						</div>
					</div>
				</div>
				<!-- card end -->
			</div>
			<!-- class="col-12 -->
		</div>
		<!-- content-wrappe end -->



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
	<script src="../dist/js/pages/dashboard3.js"></script>
	<!-- AdminLTE App -->
	<!-- <script src="../dist/js/adminlte.min.js"></script> -->
	
 <!-- 引入selfjs -->
<%@ include  file="pagejs.file" %>
	
<!-- 停權 -->
	<script>
   
    $("#suspendbtn").click(function(){
    	console.log(123);
    	$("#modifybtn").attr("disabled",true);
    	$("#modifybtn2").attr("disabled",true);
    	$("#modifybtn3").attr("disabled",true);
    })
   
</script>




</body>

</html>