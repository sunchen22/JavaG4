<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>樓頂揪樓咖後台管理</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- IonIcons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">


</head>
<!--
`body` tag options:

  Apply one or more of the following classes to to the body tag
  to get the desired effect

  * sidebar-collapse
  * sidebar-mini
-->

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
          <a href="index3.jsp" class="nav-link">管理者管理首頁</a>
        </li>
      </ul>

      <!-- Right navbar links -->
      <ul class="navbar-nav ml-auto">

        <li class="nav-item">
          <a class="nav-link" href="index3.html" role="button">
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
    <aside class="main-sidebar sidebar-light-warning elevation-4">
      <a href="index3.jsp" class="brand-link">
        <img src="<%= request.getContextPath()%>/background/dist/img/Logo.png" alt="樓頂揪樓咖 Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-normal">後台管理平台</span>
      </a>

      <!-- Sidebar -->
      <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
          <div class="image">
            <img src="dist/emgimg/emp02.png" class="img-circle elevation-2" alt="emp01">
          </div>
          <div class="info">
            <a href="#" class="d-block">小丸子</a>
          </div>
        </div>

        <!-- SidebarSearch Form -->
        <!-- 可省略/側邊搜尋bar -->
        <!-- <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div> -->

        <!-- Sidebar Menu -->
        <nav class="mt-2">
          <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
            <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->

            <!-- 側邊欄主標要打開用class="nav-item menu-open" -->
            <li class="nav-item">
              <!-- 若要自動打開設定<a href="#" class="nav-link active"></a> -->
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-book"></i>
                <p>
                  管理者管理
                  <i class="right fas fa-angle-left"></i>
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="./pages/adm_sales.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>銷售狀況</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./adm_people.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>線上會員人數</p>
                  </a>
                </li>
                <!-- 側邊欄次標要打開用class="nav-link active" -->
                <li class="nav-item">
                  <a href="pages/adm_men.jsp" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>管理者帳號管理</p>
                  </a>
                </li>
              </ul>
            </li>

            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-user"></i>
                <p>
                  會員管理
                  <i class="fas fa-angle-left right"></i>
                  <!-- 可省略/側邊欄的綠色標示提醒 -->
                  <!-- <span class="badge badge-info right">6</span> -->
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="./pages/mem_account.jsp" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>會員資料查詢</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/mem_profile.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>會員資料與權限變更</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/mem_news.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>會員最新消息</p>
                  </a>
                </li>

              </ul>
            </li>

            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-briefcase"></i>
                <p>
                  商家管理
                  <i class="fas fa-angle-left right"></i>
                </p>
              </a>

              <ul class="nav nav-treeview">
                <!-- <li class="nav-header">商家列表查詢</li> -->
                <li class="nav-item">
                  <a href="./pages/mer_list.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>商家列表查詢</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/mer_application.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>商家申請審核</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/mer_details.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>商家資料異動</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/mer_product.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>商品審核</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/mer_payment.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>金流報表</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/mer_ad.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>商家廣告審核</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/mer_news.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>給商家最新消息</p>
                  </a>
                </li>

              </ul>
            </li>
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-edit"></i>
                <p>
                  大樓資料管理
                  <i class="fas fa-angle-left right"></i>
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="./pages/bldg_query.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>大樓資料查詢</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/bldg_create.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>建立新大樓</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/bldg_modify.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>修改/刪除大樓資訊</p>
                  </a>
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-table"></i>
                <p>
                  訂單管理
                  <i class="fas fa-angle-left right"></i>
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="./pages/ord_query.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>訂單查詢</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/ord_review.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>評論管理</p>
                  </a>
                </li>

              </ul>
            </li>

            <li class="nav-item">
              <a href="#" class="nav-link">
                <!-- <i class="nav-icon fas fa-book"></i> -->
                <i class="nav-icon fas fa-comment"></i>
                <p>
                  客服系統
                  <i class="fas fa-angle-left right"></i>
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="./pages/FAQ_Page.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>FAQ頁面管理</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/svr_mem_msg.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>系統留言管理</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="./pages/svr_cust.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>線上客服訊息管理</p>
                  </a>
                </li>
              </ul>
              <ul class="nav nav-pills nav-sidebar " data-accordion="false" style="justify-content: flex-end">
                <li class="col-sm-5">
                  <button id="logoutButton" type="button" class="btn btn-block btn-outline-warning btn-sm"><i
                      class="fa fa-sign-out-alt  nav-icon"></i>登出</button>
                </li>
              </ul>
            </li>
        </nav>
        <!-- /.sidebar-menu -->
      </div>
      <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <!-- <h1 class="m-0">首頁 Content Header 這裡好多字測試字測試字測試字測試字</h1> -->
            </div>
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <!-- /.content-header -->

      <!-- Main content -->

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
  <script src="plugins/jquery/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- AdminLTE -->
  <script src="dist/js/adminlte.js"></script>

  <!-- OPTIONAL SCRIPTS -->
  <script src="plugins/chart.js/Chart.min.js"></script>
  <!-- AdminLTE for demo purposes -->
  <!-- <script src="dist/js/demo.js"></script> -->
  <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
  <script src="dist/js/pages/dashboard3.js"></script>

  <!-- 縮小時,登出按鈕消失 -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
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

</body>

</html>