<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.usernews.service.*" %>
<%@ page import="com.usernews.entity.*" %>
<%
	UserNewsServiceImpl usernewsSvc= new UserNewsServiceImpl();
	List<UserNews> usernewsList = usernewsSvc.getAllUserNews();
	pageContext.setAttribute("usernewsList",usernewsList);
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
  <link rel="stylesheet" href="../plugins/fontawesome-free/css/all.min.css">
  <!-- IonIcons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
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
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
      <!-- Left navbar links -->
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" data-widget="pushmenu" href="#" role="button" onclick="toggleLogoutButton()"><i
              class="fas fa-bars"></i></a>

        </li>

        <li class="nav-item d-none d-sm-inline-block ">
          <a href="mem_news.html" class="nav-link">會員最新消息</a>
        </li>
      </ul>

      <!-- Right navbar links -->
      <ul class="navbar-nav ml-auto">

        <li class="nav-item">
          <a class="nav-link" href="../index3.html" role="button">
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
    <!-- 有修改顏色 原本sidebar-dark-primary -->
    <aside class="main-sidebar sidebar-light-warning elevation-4">
      <!-- Brand Logo -->
      <a href="../index3.html" class="brand-link">
        <img src="../dist/img/Logo.png" alt="樓頂揪樓咖 Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-normal">後台管理平台</span>
      </a>

      <!-- Sidebar -->
      <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
          <div class="image">
            <img src="../dist/empimg/emp02.png" class="img-circle elevation-2" alt="emp01">
          </div>
          <div class="info">
            <a href="#" class="d-block">小丸子</a>
          </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
          <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
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

      <section class="content">
        <div class="container-fluid">

          <div class="card">
          			<div class="card-header">
						<div class="card-title" style="align-items: center;">會員最新消息</div>
						<div style="display: flex; justify-content: right;">
							<a href="mem_news_add.jsp" class="btn btn-warning ">
							<i class="fa fa-plus-circle"></i>增加最新消息</a>
						</div>
					</div>
          
            <div class="card-body">
            
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

              <table class="table table-bordered">
                <thead>
                  <tr style="text-align: center;">
                    <th>廣告編號</th>
                    <th>會員最新消息</th>
                    <th>最新修改時間</th>
                    <th>上稿人</th>
                    <th>狀態</th>
                    <th>修改</th>
                    <th>下架</th>
                    <th>刪除</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="usernews" items="${usernewsList}">
                  <tr>
                    <td>${usernews.userNewsID}</td>
                    <td>${usernews.userNewsContent}</td>
                    <td>${usernews.userNewsReviseTime}</td>
                    <td>${usernews.webempadmin.empID} </td>
                    <td>${usernews.userNewsStatus} </td>
                    <td>
                    <FORM METHOD="post"
												action="${pageContext.request.contextPath}/background/pages/usernews.do" style="margin-bottom: 0px;">
												<input type="submit" value="修改" class="btn btn-warning btn-sm edit-button"> 
												<input type="hidden" name="userNewsID" value="${usernews.userNewsID}" >
												<input type="hidden" name="action" value="getOne_For_Update" >
<%-- 												<input type="hidden" name="account" value="${sessionScope.account}" > --%>
					</FORM>
					</td>
					<td>
					<FORM METHOD="post"  ACTION="<%=request.getContextPath()%>/background/pages/usernews.do" style="margin-bottom: 0px;" >
													<input type="submit" value="下架" class="btn btn-warning btn-sm edit-button"> 
													<input type="hidden" name="userNewsID" value="${usernews.userNewsID}" >
													<input type="hidden" name="action" value="suspend">
					</FORM>
                    </td>
                    <td>
					<FORM METHOD="post"  ACTION="<%=request.getContextPath()%>/background/pages/usernews.do" style="margin-bottom: 0px;" >
													<input type="submit" value="刪除" class="btn btn-danger btn-sm delete-button" > 
													<input type="hidden" name="userNewsID" value="${usernews.userNewsID}" >
													<input type="hidden" name="action" value="delete">
					</FORM>
                    </td>
                  </tr>
				</c:forEach>
                </tbody>
              </table>
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
  <script src="../plugins/jquery/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- AdminLTE -->
  <script src="../dist/js/adminlte.js"></script>
  <!-- OPTIONAL SCRIPTS -->
  <script src="../plugins/chart.js/Chart.min.js"></script>

 <!-- 引入selfjs -->
<%@ include  file="pagejs.file" %>
	
    
</body>

</html>