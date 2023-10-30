<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.usernews.service.*" %>
<%@ page import="com.usernews.entity.*" %>

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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/background/plugins/fontawesome-free/css/all.min.css">
  <!-- IonIcons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/background/dist/css/adminlte.min.css">

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
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
      <!-- Left navbar links -->
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" data-widget="pushmenu" href="#" role="button" onclick="toggleLogoutButton()"><i
              class="fas fa-bars"></i></a>

        </li>

        <li class="nav-item d-none d-sm-inline-block ">
          <a href="mem_news.jsp" class="nav-link">會員最新消息</a>
        </li>
      </ul>

      <!-- Right navbar links -->
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link"  href="${pageContext.request.contextPath}/background/pages/index3.jsp"  role="button">
            <i class="fas fa-home"></i>
          </a>
        </li>

        <li class="nav-item">
          <a class="nav-link" data-widget="fullscreen" href="#" role="button">
            <i class="fas fa-expand-arrows-alt"></i>
          </a>
        </li>

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
              <h1> </h1>
            </div>

          </div>
        </div><!-- /.container-fluid -->
      </section>

      <!-- Main content -->
			<!-- 以下預計放置搜尋出的畫面 -->
			<div class="card">
				<div class="card-body">
				<p style=" text-align: center">成功更新會員最新消息!</p>
						<table class="table table-bordered">
							<thead>
								<tr style="text-align: center;">
									<th>編號</th>
									<th>最新消息內容</th>
									<th>最新修改時間</th>
									<th>狀態</th>
									<th>編修人</th>
								</tr>
							</thead>
							
							<tbody>
									<tr>
										<td>${usernews.userNewsID}</td>
										<td>${usernews.userNewsContent}</td>
										<td>${usernews.userNewsReviseTime}</td>
										<td>${usernews.userNewsStatus}</td>
										<td>${usernews.webempadmin.empID} ${userNews.webempadmin.empName}</td>
									</tr>
							</tbody>
						</table>
				</div>
     
<!-- ======= 底部連結 ======= -->
<div class="card-footer bg-transparent ">
	<ul class="pagination justify-content-center ">
	<a href="${pageContext.request.contextPath}/background/pages/mem_news.jsp">回會員最新消息查詢頁</a>
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
      <strong>Copyright &copy; 2023</strong>
      樓頂揪樓咖團隊 All rights reserved.
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

 <!-- 引入selfjs -->
<%@ include  file="pagejs.file" %>
	

</body>

</html>