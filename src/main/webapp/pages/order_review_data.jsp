<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
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
                    <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>

                </li>

                <!-- <li class="nav-item d-none d-sm-inline-block ">
          <a href="adm_sales.html" class="nav-link">銷售狀況</a>
        </li> -->
            </ul>

            <!-- Right navbar links -->
            <ul class="navbar-nav ml-auto">

                <li class="nav-item">
                    <a class="nav-link" href="../index.jsp" role="button">
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
            <a href="../index.jsp" class="brand-link">
                <img src="../dist/img/Logo.png" alt="樓頂揪樓咖 Logo" class="brand-image img-circle elevation-3"
                    style="opacity: .8">
                <span class="brand-text font-weight-normal">後台管理平台</span>
            </a>

            <!-- Sidebar -->
            <div class="sidebar">
                <!-- Sidebar user panel (optional) -->
                <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                    <div class="image">
                        <img src="../dist/img/emp01.png" class="img-circle elevation-2" alt="emp01">
                    </div>
                    <div class="info">
                        <a href="#" class="d-block">小丸子</a>
                    </div>
                </div>

                <!-- Sidebar Menu -->
                <nav class="mt-2">
                    <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                        data-accordion="false">
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
                                    <a href="./adm_sales.html" class="nav-link">
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
                                    <a href="./adm_men.html" class="nav-link">
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
                                    <a href="./mem_account.html" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>會員帳號查詢</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./mem_profile.html" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>會員資料與權限變更</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./mem_news.html" class="nav-link">
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
                                    <a href="./mer_list.jsp" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>商家列表查詢</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./mer_application.jsp" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>商家申請審核</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./mer_details.jsp" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>商家資料異動</p>
                                    </a>
                                </li>
                               
                                <li class="nav-item">
                                    <a href="./mer_product.jsp" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>商品審核</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./mer_payment.jsp" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>金流報表</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./mer_ad.jsp" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>商家廣告審核</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./mer_news.jsp" class="nav-link">
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
                                    <a href="./bldg_query.jsp" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>大樓資料查詢</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./bldg_create.jsp" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>建立新大樓</p>
                                    </a>
                                </li>
                                </ul>
                        <li class="nav-item  menu-open">
                            <a href="#" class="nav-link active">
                                <i class="nav-icon fas fa-table"></i>
                                <p>
                                    訂單管理
                                    <i class="fas fa-angle-left right"></i>
                                </p>
                            </a>
                            <ul class="nav nav-treeview">
                                <li class="nav-item">
                                    <a href="./ord_query.jsp" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>訂單查詢</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./ord_review.jsp" class="nav-link active">
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
                                    <a href="./FAQ_Page.html" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>FAQ頁面管理</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./svr_cust.html" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>訊息管理</p>
                                    </a>
                                </li>

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
                            <h1> </h1>
                        </div>

                    </div>
                </div><!-- /.container-fluid -->
            </section>

            <!-- Main content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-10">
                            <!-- interactive chart -->
                            <div class="card card-warning card-outline">
                                <div class="card-header">

                                    <div class="card-tools">

                                    </div>
                                </div>


                                <!-- <div class="col-md-3"> -->

                                <!-- </div> -->



                                <div class="table-responsive">
                                    <table class="table card-table table-vcenter text-nowrap">
                                        <thead>
                                            <tr>
                                                <th class="w-1">商家編號</th>
                                                <th>商家帳號</th>
                                                <th>商家名稱</th>
                                                <th>商家電話</th>
                                                <th>e-mail</th>
                                                <th>地址</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><span class="text-muted">1</span></td>
                                                <td><a href="invoice.html" class="text-inherit">54321</a></td>
                                                <td>
                                                    吉野家烤肉飯
                                                </td>
                                                <td>
                                                    88880000
                                                </td>
                                                <td>
                                                    xxxxxxxxxxxxxx
                                                </td>

                                                <td>
                                                    xxxxxxxxxxxxxxXXXXXXXXXXXX
                                                </td>

                                            </tr>

                                        </tbody>
                                    </table>


                                </div>




                                <!-- /.card-body-->
                            </div>
                            <!-- /.card -->

                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->

                    <div class="table-responsive">
                        <table class="table card-table table-vcenter text-nowrap">
                            <thead>
                                <tr>
                                    <th class="w-1">訂單編號</th>
                                    <th>訂單明細</th>
                                    <th>評論者</th>
                                    <th>評論內容</th>
                                    <th>星數</th>
                                    <th>商家回覆內容</th>
                                    <th>商家回覆時間</th>
                                    <th>時間</th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><span class="text-muted">1</span></td>
                                    <td>XXXXXXXX</td>
                                    <td>XXX</td>
                                    <td>XXXXXXXX</td>
                                    <td>5</td>
                                    <td>XXXXXX</td>
                                    <td>XXXXXX</td>
                                    <td>XXXXXXX</td>
                                    <td>
                                        <button type="button" id="stop" class="btn btn-warning" style="font-weight :bold"
                                        data-bs-toggle="modal" data-bs-target="#exampleModal">
                                        刪除
                                      </button>

                                    </td>
                                    

                                        

                            

                                    



                                </tr>
                                


                            </tbody>
                        </table>

                         <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">提示</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>

        <div class="modal-body">
          <p style="text-align: center; font-size: 20px;">確定要刪除嗎</p>
          
            <div align="center">
                <input type="text" placeholder="欲拒絕請輸入原因:" id="msg"
                    style="width: 80%">
            
        </div>

        <div class="modal-body">
            
           
        <div class="modal-footer">
          <button type="button" id = "stop1" class="btn btn-warning" data-bs-dismiss="modal">確定</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          
        </div>
      </div>
    </div>
  </div>
                    </div>
            </section>

        </div>

       








        <!-- </section> 這裡結束-->





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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
    <!-- AdminLTE -->
    <script src="../dist/js/adminlte.js"></script>
    <!-- OPTIONAL SCRIPTS -->
    <script src="../plugins/chart.js/Chart.min.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="../dist/js/pages/dashboard3.js"></script>
    <!-- AdminLTE App -->
    <!-- <script src="../dist/js/adminlte.min.js"></script> -->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext">
    <script src="./assets/js/require.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    <script>

        
    </script>



    

</body>

</html>