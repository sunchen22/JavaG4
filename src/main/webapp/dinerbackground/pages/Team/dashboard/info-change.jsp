<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%@ page import="com.dinerinfo.service.*"%>

<% 
	DinerInfoServiceImpl dinerSvc = new DinerInfoServiceImpl();
	DinerInfo dinerInfoBc = dinerSvc.getDinerInfoByDinerID(1);
	//測試用，之後順利連結頁面後要改以下這種
	
	//DinerInfo dinerInfo = (DinerInfo)request.getAttribute("dinerInfo");
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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
    integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="../../../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../../../dist/css/adminlte.min.css">
</head>

<body class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
  <div class="wrapper">

    <!-- Preloader -->
    <!-- <div class="preloader flex-column justify-content-center align-items-center">
      <img class="animation__wobble" src="dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
    </div> -->

    <!-- 基本版面 上方懸浮欄 -->
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-dark">
      <!-- 上方懸浮欄 左方按鈕 -->
      <!-- Left navbar links -->
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
          <a href="./dashboard-index" class="nav-link">首頁</a>
        </li>

      </ul>

      <!-- 彈出右側下拉欄 -->
      <!-- Right navbar links -->
      <ul class="navbar-nav ml-auto">


        <!-- 全螢幕控制鈕 -->
        <li class="nav-item">
          <a class="nav-link" data-widget="fullscreen" href="#" role="button">
            <i class="fas fa-expand-arrows-alt"></i>
          </a>
        </li>

      </ul>
    </nav>
    <!-- /.navbar -->

    <!-- 主要功能側邊欄(左側) -->
    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">

      <!-- 品牌logo -->
      <!-- Brand Logo -->
      <a href="index3.html" class="brand-link">
        <img src="../../../dist/img/joLOGO.png" alt="joLOGO" class="brand-image img-circle elevation-3"
          style="opacity: .8">
        <span class="brand-text font-weight-light">樓頂揪樓咖</span>
      </a>

      <!-- Sidebar -->
      <div class="sidebar">
        <!-- 商家頭像 -->
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
          <div class="image">
            <img src="../../../dist/img/sara lance.png" class="img-circle elevation-2" alt="User Image">
          </div>
          <div class="info">
            <a href="#" class="d-block">Sara Lance</a>
          </div>
        </div>

        <!-- SidebarSearch Form -->

        <!-- 功能項目列 -->
        <!-- Sidebar Menu -->
        <nav class="mt-2">
          <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
            <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
            <li class="nav-item menu-open">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-cogs"></i>
                <p>
                  商家資料管理
                  <i class="right fas fa-angle-left"></i>
                </p>
              </a>
              <!-- 商家資料管理 子選項 -->
              <ul class="nav nav-treeview">

                <!-- 公司資料變更 -->
                <li class="nav-item">
                  <a href="./info-change.html" class="nav-link active">
                    <i class="far fa-circle nav-icon"></i>
                    <p>公司資料變更</p>
                  </a>
                </li>

                <!-- 店面設定 -->
                <li class="nav-item">
                  <a href="./business-set.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>店面設定</p>
                  </a>
                </li>

              </ul>

            </li>

            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-file-upload"></i>
                <p>
                  商品上架
                  <i class="right fas fa-angle-left"></i>
                  <!-- <span class="badge badge-info right"></span> 這裡可以用來設定右邊顯示的訊息提醒小數字 -->
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="pages/layout/top-nav.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>商品群組設定</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="pages/layout/top-nav-sidebar.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>單獨上架</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="pages/layout/boxed.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>批次上架</p>
                  </a>
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-hammer"></i>
                <p>
                  商品管理
                  <i class="right fas fa-angle-left"></i>
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="pages/charts/chartjs.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>商品列表</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="pages/charts/flot.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>單獨商品頁面</p>
                  </a>
                </li>

              </ul>
            </li>
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-newspaper"></i>
                <p>
                  訂單管理
                  <i class="fas fa-angle-left right"></i>
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="pages/UI/general.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>揪團成功訂單</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="pages/UI/icons.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>已完成訂單</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="pages/UI/buttons.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>已取消訂單</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="pages/UI/sliders.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>訂單查詢</p>
                  </a>
                </li>
              </ul>

            </li>
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-handshake"></i>
                <p>
                  幫助中心
                  <i class="fas fa-angle-left right"></i>
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="../help/Guide-to-Adding-Products-list.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>上架教學</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="../help/monthly-report.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>每月報表</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="../help/sales-data.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>銷售數據</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="../help/request-Ad-placement.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>申請廣告上架</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="../help/comment-reply.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>評論區回覆</p>
                  </a>
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a href="../frontpage/frontpage-index.html" class="nav-link">

                <i class="nav-icon fas fa-sign-out-alt"></i>
                <p>
                  登出

                </p>
              </a>

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
              <h1 class="m-0">公司資料變更</h1>
            </div>
            <div class="card col-12">
              <div class="card-body register-card-body">
                <p class="login-box-msg">送出需要修改的資訊後，將有專人與您聯絡</p>
                <p class="login-box-msg">確認資訊無誤後，將為您更新資訊</p>


                <form action="<%=request.getContextPath()%>/dinerbackground/pages/Team/dashboard/dinerInfo.do"
                 method="post">
                 
                  <div class="row">
                    <!-- 商店名稱 -->
                    <div class="input-group mb-3 col-6">
                      <input type="text" class="form-control" value="<%=dinerInfoBc.getDinerName()%>">
                      <div class="input-group-append">
                        <div class="input-group-text">
                          <span class="fas fa-store"></span>
                        </div>
                      </div>
                    </div>

                    <!-- 銀行帳戶 銀行代碼 -->
                    <div class="input-group mb-3 col-6">
                      <input type="text" class="form-control" value="<%=dinerInfoBc.getDinerBank()%>">
                      <div class="input-group-append">
                        <div class="input-group-text">
                          <span class="fas fa-user"></span>
                        </div>
                      </div>
                    </div>

                    <!-- 商店地址 -->
                    <div class="input-group mb-3 col-6">
                      <input type="text" class="form-control" value="<%=dinerInfoBc.getDinerAddress()%>">
                      <div class="input-group-append">
                        <div class="input-group-text">
                          <span class="fas fa-map-marker-alt"></span>

                        </div>
                      </div>
                    </div>

                    <!-- 銀行帳戶 帳戶號碼 -->
                    <div class="input-group mb-3 col-6">
                      <input type="text" class="form-control" value="<%=dinerInfoBc.getDinerAccount()%>">
                      <div class="input-group-append">
                        <div class="input-group-text">
                          <span class="fas fa-university"></span>
                        </div>
                      </div>
                    </div>

                    <!-- 聯絡人姓名 -->
                    <div class="input-group mb-3 col-6">
                      <input type="text" class="form-control" value="<%=dinerInfoBc.getDinerContact()%>">
                      <div class="input-group-append">
                        <div class="input-group-text">
                          <span class="fas fa-user"></span>
                        </div>
                      </div>
                    </div>

                    <!-- 銀行帳戶 戶名 -->
                    <div class="input-group mb-3 col-6">
                      <input type="text" class="form-control" value="<%=dinerInfoBc.getDinerAccountName()%>">
                      <div class="input-group-append">
                        <div class="input-group-text">
                          <span class="fas fa-money-check-alt"></span>

                        </div>
                      </div>
                    </div>

                    <!-- 聯絡電話  請留手機號碼-->
                    <div class="input-group mb-3 col-6">
                      <input type="text" class="form-control" value="<%=dinerInfoBc.getDinerPhone()%>">
                      <div class="input-group-append">
                        <div class="input-group-text">
                          <span class="fas fa-phone"></span>
                        </div>
                      </div>
                    </div>

                    <!-- 聯絡人email  -->
                    <div class="input-group mb-3 col-6">
                      <input type="email" class="form-control" value="<%=dinerInfoBc.getDinerEmail()%>">
                      <div class="input-group-append">
                        <div class="input-group-text">
                          <span class="fas fa-envelope"></span>
                        </div>
                      </div>
                    </div>
                    <!-- 統編  (將來會作為登入帳號使用) -->
                    <div class="input-group mb-3 col-6">
                      <input type="text" class="form-control" value="<%=dinerInfoBc.getDinerTaxID()%>">
                      <div class="input-group-append">
                        <div class="input-group-text">
                          <span class="fas fa-signature"></span>
                        </div>
                      </div>
                    </div>
                    <!-- 請問您販售的商品是甚麼類型? * -->
                    <!-- <div class="form-group mb-3 col-6">
                  <select name="dinerType" class="form-control" value="">
                    <option value="" disabled>您販售的商品類型</option>
                    <option value="M">單純餐點</option>
                    <option value="D">單純飲料</option>
                    <option value="X">複合餐飲</option>
                  </select>
                </div> -->
                    <!-- 請問您販售的商品是甚麼類型? * -->
                    <div class="form-group form-inline mb-3 col-6 row">
                      <div class="col-4" style="text-align: right;">
                        <label for="dinerType">您販售的商品類型</label>
                      </div>
                      <div class="col-8">
                        <select name="dinerType" class="form-control w-100">
                          <option value="M">單純餐點</option>
                          <option value="D">單純飲料</option>
                          <option value="X">複合餐飲</option>
                        </select>
                      </div>
                    </div>



                  </div>


                  <div class="row">
                   <div class="input-group mb-3 col-6">
                      <input type="text" class="form-control" value="<%=dinerInfoBc.getDinerTaxID()%>">
                      <div class="input-group-append">
                        <div class="input-group-text">
                          <span class="fas fa-signature"></span>
                        </div>
                      </div>
                    </div>
                    <!-- /.col -->
                    <div class="col-4 mr-1">
                      <button type="submit" class="btn btn-primary btn-block">確認修改</button>
                    </div>
                    <!-- /.col -->
                  </div>
                </form>
              </div><!-- /.row -->
            </div><!-- /.container-fluid -->
          </div>
          <!-- /.content-header -->

          <!-- Main content -->
          <section class="content">
            <div class="container-fluid">


            </div><!--/. container-fluid -->
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
      <script src="../../../plugins/jquery/jquery.min.js"></script>
      <!-- Bootstrap -->
      <script src="../../../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
      <!-- overlayScrollbars -->
      <script src="../../../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
      <!-- AdminLTE App -->
      <script src="../../../dist/js/adminlte.js"></script>

      <!-- PAGE PLUGINS -->
      <!-- jQuery Mapael -->
      <script src="../../../../plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
      <script src="../../../../plugins/raphael/raphael.min.js"></script>
      <script src="../../../../plugins/jquery-mapael/jquery.mapael.min.js"></script>
      <script src="../../../../plugins/jquery-mapael/maps/usa_states.min.js"></script>
      <!-- ChartJS -->
      <script src="../../../../plugins/chart.js/Chart.min.js"></script>

      <!-- AdminLTE for demo purposes -->
      <script src="../../../../dist/js/demo.js"></script>
      <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
      <script src="../../../../dist/js/pages/dashboard2.js"></script>
</body>

</html>