<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

				<li class="nav-item"><a class="nav-link"  href="${pageContext.request.contextPath}/background/pages/index3.jsp" 
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
			<section class="content">
				<div class="container-fluid">
					<div class="card card-warning">
						<!-- <div class="card-header">
          	</div> -->

						<div class="card-body">
		<!-- 		=======複合查詢======= -->
										<form action="${pageContext.request.contextPath}/background/pages/user.do" method="post">
											<div class="row">
												<div class="col-md-10 ">
													<div class="row">

														<div class="col-5">
															<div class="form-group">
																<label>會員編號</label>
																<input type="text" class="form-control" name="userid" id="userIDInput" placeholder="以會員編號模糊查詢">
															</div>
														</div>
														<div class="col-5">
															<div class="form-group">
																<label>會員帳號</label>
																<input type="text" class="form-control" name="useremail" id="emailInput" placeholder="以會員帳號模糊查詢">
															</div>
														</div>

													</div>

													<div class="row align-items-center">
														<div class="col-4">
															<div class="form-group">
																<label>會員姓名</label>
																<input type="text" class="form-control" name="username"  id="nameInput" placeholder="以會員姓名模糊查詢">
															</div>
														</div>

														<div class="col-4">
															<div class="form-group">
																<label>會員電話</label>
																<input type="text" class="form-control" name="userphone"  id="phoneInput" placeholder="以會員電話模糊查詢">
															</div>
														</div>

														<div class="col-4">
															<label></label>
															<div class="form-group">
																<input type="submit" class="btn btn-warning" value="搜尋">
																<input type="hidden" name="action" value="compositeQuery">

															</div>
														</div>
													</div>
												</div>
											</div>
										</form>

									</div>
						<!-- card-body end-->
					</div>
					<!-- card end-->
				</div>
			</section>

			<!-- 以下預計放置搜尋出的畫面 -->
			<div class="card">
				<div class="card-body">
				<p>查詢結果：</p>
<!-- 						<ul class="pagination justify-content-left "> -->
<%-- 							<c:if test="${empPageQty > 0}"> --%>
<%-- 						  		<b><font >第${currentPage}/${empPageQty}頁</font></b> --%>
<%-- 							</c:if> --%>
<!-- 						</ul> -->
						<table class="table table-bordered">
							<thead>
								<tr style="text-align: center;">
									<th>會員編號</th>
									<th>會員帳號</th>
									<th>會員姓名</th>
									<th>會員電話</th>
									<th>修改</th>
<!-- 									<th>停權</th> -->
								</tr>
							</thead>
							
							<tbody>
								<c:forEach var="user" items="${userinfoList}">
									<tr>
										<td>${user.userID}</td>
										<td>${user.userAccount}</td>
										<td>${user.userName}</td>
										<td>${user.userPhone}</td>
										<td>
											<FORM METHOD="post"
												action="${pageContext.request.contextPath}/background/pages/user.do" style="margin-bottom: 0px;" id="modifybtn">
												<input type="submit" value="修改" class="btn btn-warning btn-sm"> 
													<input type="hidden" name="userID" value="${user.userID}" >
													<input type="hidden" name="action" value="getOne_For_Update" >
											</FORM>
										</td>
<!-- 										<td> -->
<!-- 											<FORM METHOD="post" style="margin-bottom: 0px;" -->
<!-- 												id="suspendbtn" > -->
<%-- 													<input type="submit" value="停權" class="btn btn-danger btn-sm"> <input type="hidden" name="empID" value="${user.userID}"> --%>
<!-- 											</FORM> -->
<!-- 										</td> -->
									</tr>
								</c:forEach>

							</tbody>
						</table>
				</div>

<!-- ======= 底部頁數/頁碼 ======= -->
<div class="card-footer bg-transparent ">
	<ul class="pagination justify-content-center ">

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

</body>

</html>