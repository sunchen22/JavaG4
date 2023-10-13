<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-Hant">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>樓頂揪樓咖 訂單查詢</title>

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
  <link rel="stylesheet" href="ord_query.css">
</head>

<body class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
  <div class="wrapper">



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
          <a href="dashboard-index" class="nav-link">首頁</a>
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
            <li class="nav-item">
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
                  <a href="../dashboard/info-change.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>公司資料變更</p>
                  </a>
                </li>

                <!-- 店面設定 -->
                <li class="nav-item">
                  <a href="../dashboard/business-set.html" class="nav-link">
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
            <li class="nav-item menu-open">
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
                  <a href="pages/UI/sliders.html" class="nav-link active">
                    <i class="far fa-circle nav-icon"></i>
                    <p>訂單查詢</p>
                  </a>
                </li>
              </ul>

            </li>
            <li class="nav-item ">
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
                  <a href="../help/sales-data.html" class="nav-link ">
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
              <h1 class="m-0">訂單查詢</h1>
            </div>
            <div class="col-sm-6">

            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <!-- /.content-header -->

      <!-- Main content -->
      <section class="content">
        <div class="container-fluid">
          <div class="row">
            <div class="col-12">
              <div class="card">
                <div class="card-header">
                  <div class="row mt-4">
                    <nav class="w-100">

                      <!-- ==============頁籤標頭===================== -->
                      <div class="nav nav-tabs" id="ord-tab" role="tablist" >

                        <a class="nav-item nav-link active" id="tab1" data-toggle="tab" 
                          href="#ord-tbd" role="tab" aria-controls="ord-tbd" 
                          aria-selected="true">
                          待確認訂單
                        </a>

                        <a class="nav-item nav-link " id="tab2" data-toggle="tab" 
                          href="#ord-preparation" role="tab" aria-controls="ord-preparation" 
                          aria-selected="false">
                          準備中訂單
                        </a>

                        <a class="nav-item nav-link" id="tab3" data-toggle="tab" 
                          href="#ord-finish" role="tab" aria-controls="ord-finish" 
                          aria-selected="false">
                          已完成訂單
                        </a>

                        <a class="nav-item nav-link" id="tab4" data-toggle="tab"
                          href="#ord-cancel" role="tab" aria-controls="ord-cancel"
                          aria-selected="false">
                          已取消訂單
                        </a>

                        <a class="nav-item nav-link" id="tab5" data-toggle="tab"
                          href="#ord-query" role="tab" aria-controls="ord-query"
                          aria-selected="false">
                          訂單查詢
                        </a>
                      </div>
                    </nav>
                  </div>
                </div>

                <!-- /.card-header -->
                <!-- ==============頁籤內容===================== -->
                <!-- Tab panes -->
                <div class="tab-content">
                  <div class="tab-content p-3" id="nav-tabContent">
                    <!-- 標籤 ======商品 銷售額==== 內容 -->
                    <div class="tab-pane fade show active" id="ord-tbd" role="tabpanel" aria-labelledby="ord-tbd">
                      <div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="card-body table-responsive p-0" style="height: auto;">
                          <table class="table table-head-fixed text-nowrap">
                            <!-- 表格標題 -->
                            
                            <div class="col-12">
                              <div class="card">
                              <div class="card-header" >
                              <h3 class="card-title" >待確認訂單</h3>
                              </div>
                              
                              
                              <table class="table table-bordered table-hover" >
                              <thead >
                              <tr>
                              <th>訂單編號</th>
                              <th>訂單狀態</th>
                              <th>成立時間</th>
                              <th>訂單地址</th>
                              <th>金額</th>
                              <th>完成時間</th>
                              </tr>
                              </thead>
                              <tbody>
                              
                                <tr data-widget="expandable-table" aria-expanded="false">
                                  <td>S2232802896	</td>
                                  <td><font color='blue'>待確認</font></td>
                                  <td><b>04-16</b>
                                      <br>17:00</td>
                                  <td>台北市中正區濟南路一段321號<br />
                                    <a href='/s2232802896.html' target=blank>apple大樓</a>
                                  </td>
                                  <td><font color="#009900">$ 8,000</font></td>
                                  <th><b>2023-04-16</b>
                                      <br>20:16<br/>
                                    </th>
                                  </tr>
                                  <tr class="expandable-body">
                                  <td colspan="6">
                                  <p>
                                    訂單內容<br>
                                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                   <br>訂單內容      
                                  </p>
                  
                                    <button type="submit" style="font-weight:bold" class="print btn-warning">確認訂單</button>
   
                                    <button type="submit" style="font-weight:bold" class="print btn-warning">               
                                    <p style="display:none;">開始時間：<span id="start_pad"></span><br>
                                      结束時間：<span id="end_pad"></span><br></p>
                                    <div >拒絕訂單(<span id="pad"></span>後拒絕)</div>
                                   </button>                       
                    
                                  </td>
                                  </tr>                    
                                </div>
                              </div>
                             </div>                      
                           </div>
                            <!-- 結束 -->
                          </table>
                        </div>
                      </div>
                    </div>
                    <!-- 標籤 ======商品 銷售額==== 內容 -->
                <div class="tab-pane fade" id="ord-preparation" role="tabpanel"
                aria-labelledby="ord-preparation">
                  <div class="card-body table-responsive p-0" style="height: auto;">
                    <table class="table table-head-fixed text-nowrap">
                          <!-- 表格標題 -->
                          
                          <div class="col-12">
                            <div class="card">
                            <div class="card-header" >
                            <h3 class="card-title" >準備中訂單</h3>
                            </div>
                            
                            
                            <table class="table table-bordered table-hover" >
                            <thead >
                            <tr>
                            <th>訂單編號</th>
                            <th>訂單狀態</th>
                            <th>成立時間</th>
                            <th>訂單地址</th>
                            <th>金額</th>
                            <th>完成時間</th>
                            </tr>
                            </thead>
                            <tbody>
                            
                              <tr data-widget="expandable-table" aria-expanded="false">
                                <td>S2232802896	</td>
                                <td><font color='blue'>待確認</font></td>
                                <td><b>04-16</b>
                                    <br>17:00</td>
                                <td>台北市中正區濟南路一段321號<br />
                                  <a href='/s2232802896.html' target=blank>apple大樓</a>
                                </td>
                                <td><font color="#009900">$ 8,000</font></td>
                                <th><b>2023-04-16</b>
                                    <br>20:16<br/>
                                  </th>
                                </tr>
                                <tr class="expandable-body">
                                <td colspan="6">
                                <p>
                                  訂單內容<br>
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                 <br>訂單內容      
                                </p>
                
                                  <button type="submit" style="font-weight:bold" class="print btn-warning">確認訂單</button>
     
                                  <button type="submit" style="font-weight:bold" class="print btn-warning">               
                                  <p style="display:none;">開始時間：<span id="start_pad"></span><br>
                                    结束時間：<span id="end_pad"></span><br></p>
                                  <div >拒絕訂單(<span id="pad"></span>後拒絕)</div>
                                 </button>                       
                  
                                </td>
                                </tr>                    
                              </div>
                            </div>
                           </div>                      
                         </div>
                          <!-- 結束 -->
                        </table>
                      </div>
                    </div>
                    <!-- 標籤 ======商品 銷售量==== 內容 -->
                    <div class="tab-pane fade" id="ord-finish" role="tabpanel" aria-labelledby="ord-finish">
                      <div class="card-body table-responsive p-0" style="height: auto;">
                        <table class="table table-head-fixed text-nowrap">
                          <!-- 表格標題 -->
                          
                          <div class="col-12">
                            <div class="card">
                            <div class="card-header">
                            <h3 class="card-title" >已完成訂單</h3>
                            </div>                          
                           
                            <table class="table table-bordered table-hover" >
                            <thead >
                            <tr>
                            <th>訂單編號</th>
                            <th>訂單狀態</th>
                            <th>成立時間</th>
                            <th>訂單地址</th>
                            <th>金額</th>
                            <th>完成時間</th>
                            </tr>
                            </thead>
                            <tbody>
                           
                             <tr data-widget="expandable-table" aria-expanded="false">
                               <td>S2232802896	</td>
                               <td><font color='blue'>已完成</font></td>
                               <td><b>04-16</b>
                                 <br>17:00</td>
                               <td>台北市中正區濟南路一段321號<br />
                                 <a href='/s2232802896.html' target=blank>apple大樓</a>
                               </td>
                               <td><font color="#009900">$ 8,000</font></td>
                               <th><b>2023-04-16</b>
                                 <br>20:16<br/>
                                 </th>
                               </tr>
                               <tr class="expandable-body">
                               <td colspan="6">
                               <p>
                                 訂單內容<br>
                               Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                               <button type="submit" style="font-weight:bold" class="print btn-warning">列印</button>              
                             <br> 訂單內容
                             </p>                
                               </td>
                               </tr>
                           </tbody>                          
                         </div>                          
                       </div>                          
                     </div>
                   </div> 
                          <!-- 結束 -->     
                        </table>
                      </div>
                    </div>
                    <!-- 標籤 ======商品種類 銷售額==== 內容 -->
                    <div class="tab-pane fade" id="ord-cancel" role="tabpanel"
                      aria-labelledby="ord-cancel">
                      <div class="card-body table-responsive p-0" style="height: auto;">
                        <table class="table table-head-fixed text-nowrap">
                          <!-- 表格標題 -->
                          
                          <div class="col-12">
                            <div class="card">
                            <div class="card-header">
                            <h3 class="card-title" >已取消訂單</h3>
                            </div>
                             
                            
                            <table class="table table-bordered table-hover" >
                            <thead >
                            <tr>
                            <th>訂單編號</th>
                            <th>訂單狀態</th>
                            <th>成立時間</th>
                            <th>訂單地址</th>
                            <th>金額</th>
                            <th>完成時間</th>
                            </tr>
                            </thead>
                            <tbody>
                             
                             <tr data-widget="expandable-table" aria-expanded="false">
                               <td>S2232802896	</td>
                               <td><font color='blue'>已取消</font></td>
                               <td><b>04-16</b>
                                   <br>17:00</td>
                               <td>台北市中正區濟南路一段321號<br />
                                   <a href='/s2232802896.html' target=blank>apple大樓</a>
                               </td>
                               <td><font color="#009900">$ 8,000</font></td>
                               <th><b>2023-04-16</b>
                                   <br>20:16<br/>
                               </th>
                               </tr>
                               <tr class="expandable-body">
                               <td colspan="6">
                               <p>
                                 訂單內容<br>
                                 Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                               <button type="submit" style="font-weight:bold" class="print btn-warning">列印</button>              
                               <br>訂單內容
                               </p>                  
                               </td>
                             </tr>                
                           </tbody>
                         </div>            
                       </div>                        
                     </div>
                   </div>  
                          <!-- 結束 -->
                        </table>
                      </div>
                    </div>
                    <!-- 標籤 ======商品種類 銷售量==== 內容 -->
                    <div class="tab-pane fade" id="ord-query" role="tabpanel"
                      aria-labelledby="ord-query">
                      <div class="card-body table-responsive p-0" style="height: auto;">
                        <table class="table table-head-fixed text-nowrap">
                          <!-- 表格標題 -->
                          
                          <div class="col-12">
                            <div class="card">
                            <div class="card-header">
                            <h3 class="card-title" >訂單查詢</h3>
                            
                            </div>
                            <div class="col-12">
                               <!-- interactive chart -->                                                                             
                                                                    
                                 </div>
                                                     
                                 <!-- <div class="col-md-3"> -->
                                 <form action="simple-results.html" style="padding:10px;">
                     
                                   <span class="col-10">
                                     <label style=" margin: 0;">訂單編號查詢：</label>
                                     <input type="number" placeholder="請輸入訂單編號" class="border border-warning" >
                                   </span>
                                   <br>
                     
                                   <span class="col-10">
                                     <label style=" margin: 0;">訂單日期查詢：</label>
                                     <input type="date" placeholder="2023" class="border border-warning" size="4" > 
                                   </span>                              
                                   <br>
                     
                                   <span class="col-10">
                                     <label style=" margin: 0;">訂單地址查詢：</label>
                                     <input type="search" placeholder="請輸入大樓名稱" class="border border-warning" list="test" >
                                     <datalist id="test">
                                       <option value="apple大樓"></option>
                                       <option value="ant大樓"></option>
                                       <option value="banana大樓"></option>
                                       <option value="bird大樓"></option>
                                       <option value="orange大樓"></option>
                                       <option value="ox大樓"></option>
                                     </datalist>                                  
                                   </span>
                                   <br>
                                   <button type="submit" style="font-weight:bold" class="btn btn-warning">查詢</button>
                                 </form>                  
                               </div>                        
                             <table class="table table-bordered table-hover" >
                             <thead >
                             <tr>
                             <th>訂單編號</th>
                             
                               <td  align="center" class="tableTitle"><input type="search" placeholder="訂單狀態"  list="orderStatus" size="12">
                                 <datalist id="orderStatus">
                                   <option value="揪團已建立"></option>
                                   <option value="成團條件達成 "></option>
                                   <option value="揪團成功"></option>
                                   <option value="揪團失敗"></option>
                                   <option value="餐點準備中"></option>
                                   <option value="商家拒單"></option>
                                   <option value="餐點送達"></option>
                                 </datalist>
                               </td>
                             
                             <th>成立時間</th>
                             <th>訂單地址</th>
                             <th>金額</th>
                             <th>完成時間</th>
                             </tr>
                             </thead>
                             
             
                   	         
                             <tbody>
<c:forEach var="groupOrder" items="${groupOrder}"> 
                             <tr>
                             <td>${groupOrder.groupOrderID}</td>
                             <td>${groupOrder.orderStatus}</td>
                             <td>${groupOrder.groupOrderCreateTime}</td>
                             <td>${groupOrder.buildingID}</td>
                             <td>${groupOrder.groupTotalPrice}</td>
                             <td>${groupOrder.groupOrderSubmitTime}</td>
                             </tr>
                             <tr class="expandable-body">
                             <td colspan="6">
                             <p>
                             Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                             <button type="submit" style="font-weight:bold" class="print btn-warning">列印</button>    
                                       
                             </p>              
                             </td>
                             </tr>  
</c:forEach>          
                           </tbody>
                           
               
                           
                         </div>          
                       </div>                      
                     </div>
                   </div> 
                          <!-- 結束 -->
                        </table>
                      </div>
                    </div>    
                  </div>   
                  <!-- /.card-body -->
                </div>
                <!-- /.card -->
                <SCRIPT> 
                  var startDate = new Date();
                  var endDate = new Date(2023,8,27,23,59);
                  var spantime = (endDate - startDate)/1000;
                   
                  function getString(dt){
                      return dt.getFullYear() + "年" + (dt.getMonth()+1) + "月" +    dt.getDate() + "日" + dt.getHours() + "時" + dt.getMinutes() + "分";
                  }
                  function cal(){
                      spantime --;
                      
                      var m = Math.floor((spantime % 3600)/(60));
                      var s = Math.floor(spantime%60);
                      str =  m + "分 " + s + "秒 ";
                      document.getElementById("pad").innerHTML = str;
                  }
                   
                  window.onload = function(){
                      document.getElementById("start_pad").innerHTML = getString(startDate);
                      document.getElementById("end_pad").innerHTML = getString(endDate);
                      setInterval(cal, 1000);
                  }
                  </SCRIPT>  

              </div>
            </div>

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
  <script src="../../../plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
  <script src="../../../plugins/raphael/raphael.min.js"></script>
  <script src="../../../plugins/jquery-mapael/jquery.mapael.min.js"></script>
  <script src="../../../plugins/jquery-mapael/maps/usa_states.min.js"></script>
  <!-- ChartJS -->
  <script src="../../../plugins/chart.js/Chart.min.js"></script>

  <!-- AdminLTE for demo purposes -->
  <script src="../../../dist/js/demo.js"></script>

</body>

</html>