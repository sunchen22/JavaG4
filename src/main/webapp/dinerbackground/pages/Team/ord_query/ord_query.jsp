<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.grouporder.service.*"%>
<%@ page import="com.grouporder.entity.*"%>
<%@ page import="com.grouporder.dao.*"%>

<%
    GroupOrderServiceN GOSvc = new GroupOrderServiceN();
    List<GroupOrderVO> GOlist = GOSvc.getAll(3);
    pageContext.setAttribute("GOlist",GOlist);
%>
<%
    GroupOrderServiceN GOSvc4 = new GroupOrderServiceN();
    List<GroupOrderVO> GOlist4 = GOSvc4.getAll(4);
    pageContext.setAttribute("GOlist4",GOlist4);
%>
<%
    GroupOrderServiceN GOSvc5 = new GroupOrderServiceN();
    List<GroupOrderVO> GOlist5 = GOSvc5.getAll(5);
    pageContext.setAttribute("GOlist5",GOlist5);
%>
<%
    GroupOrderServiceN GOSvc6 = new GroupOrderServiceN();
    List<GroupOrderVO> GOlist6 = GOSvc6.getAll(6);
    pageContext.setAttribute("GOlist6",GOlist6);
%>
<%
    GroupOrderServiceN GOSvc7 = new GroupOrderServiceN();
    List<GroupOrderVO> GOlist7 = GOSvc7.getAll(7);
    pageContext.setAttribute("GOlist7",GOlist7);
%>
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
                          訂單查詢
                        </a>

                        <a class="nav-item nav-link " id="tab2" data-toggle="tab" 
                          href="#ord-preparation" role="tab" aria-controls="ord-preparation" 
                          aria-selected="false">
                          待確認訂單
                        </a>

                        <a class="nav-item nav-link" id="tab3" data-toggle="tab" 
                          href="#ord-finish" role="tab" aria-controls="ord-finish" 
                          aria-selected="false">
                          準備中訂單
                        </a>

                        <a class="nav-item nav-link" id="tab4" data-toggle="tab"
                          href="#ord-cancel" role="tab" aria-controls="ord-cancel"
                          aria-selected="false">
                          已完成訂單
                        </a>
                        
						<a class="nav-item nav-link" id="tab5" data-toggle="tab"
                          href="#ord-query" role="tab" aria-controls="ord-query"
                          aria-selected="false">
                          已取消訂單
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
                            <div class="card-header">
                            <h3 class="card-title" >訂單查詢</h3>
                            
                            </div>
                            <div class="col-12">
                               <!-- interactive chart -->                                                                             
                                                                    
                                 </div>
                                         
                                 <!-- <div class="col-md-3"> -->
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/ord_query/orderquery.do"
									style="margin-bottom: 0px;">
									
									<span class="col-10">
                                     <label style=" margin: 0;">訂單編號查詢：</label>
                                     <input type="TEXT" name="groupOrderID" placeholder="請輸入訂單編號"  value="${param.groupOrderID}">
                                     </span><font color=red>${errorMsgs.groupOrderID}</font>
                                      <br>
									<input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit"  value="查詢" class="btn btn-warning"style="font-weight:bold"> 
                                    
								</FORM>
                                 
                                 
                                 
                                 
                
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
                             
             
<%
GroupOrderVO grouporder = (GroupOrderVO) request.getAttribute("grouporder"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>                   	         

                           
                           
                              <tbody>

                              
                      
                                <tr data-widget="expandable-table" aria-expanded="false">
                                  <td>${grouporder.groupOrderID}</td>
                                  <td>${grouporder.orderStatus}</td>
                                  <td>${grouporder.groupOrderCreateTime}</td>
                                  <td>${grouporder.buildingID}</td>
                                  <td>${grouporder.groupTotalPrice}</td>
                                  <td>${grouporder.groupOrderSubmitTime}</td>
                                  </tr>
                                  <tr class="expandable-body">
                                  <td colspan="6">
                                  <p>
                                    訂單內容<br>
                                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                   <br>訂單內容      
                                  </p>

                                	
                             
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
                    <!-- 標籤 ======商品 銷售額==== 內容 -->
                <div class="tab-pane fade" id="ord-preparation" role="tabpanel"
                aria-labelledby="ord-preparation">
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

                              
                          <c:forEach var="groupOrder" items="${GOlist}"> 
                                <tr data-widget="expandable-table" aria-expanded="false">
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
                                    訂單內容<br>
                                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                   <br>訂單內容      
                                  </p>
                  				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/ord_query/orderquery.do"
									style="margin-bottom: 0px;">
									<input type="submit"  value="確認訂單"> 
									<input type="hidden"name="groupOrderID" value="${groupOrder.groupOrderID}">
									<input type="hidden"name="orderStatus" value="5">
									<input type="hidden" name="action" value="status">
								</FORM>
								
				 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/ord_query/orderquery.do"
									style="margin-bottom: 0px;">
									<input type="submit"  value="拒絕訂單"> 
									<input type="hidden"name="groupOrderID" value="${groupOrder.groupOrderID}">
									<input type="hidden"name="orderStatus" value="6">
									<input type="hidden" name="action" value="status">
								</FORM>    
                                	
                            </c:forEach>             
                 			   </tbody>
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
                             <c:forEach var="groupOrder" items="${GOlist5}"> 
                              <tr data-widget="expandable-table" aria-expanded="false">
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
                                  訂單內容<br>
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                 <br>訂單內容      
                                </p>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/ord_query/orderquery.do"
									style="margin-bottom: 0px;">
									<input type="submit"  value="訂單已送達"> 
									<input type="hidden"name="groupOrderID" value="${groupOrder.groupOrderID}">
									<input type="hidden"name="orderStatus" value="7">
									<input type="hidden" name="action" value="status">
								</FORM>
                                                         
                  				</c:forEach>  
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
                    <!-- 標籤 ======商品種類 銷售額==== 內容 -->
                    <div class="tab-pane fade" id="ord-cancel" role="tabpanel"
                      aria-labelledby="ord-cancel">
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
                            <c:forEach var="groupOrder" items="${GOlist7}"> 
                             <tr data-widget="expandable-table" aria-expanded="false">
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
                                 訂單內容<br>
                               Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                             <br> 訂單內容
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
                    <!-- 標籤 ======商品種類 銷售量==== 內容 -->
                    <div class="tab-pane fade" id="ord-query" role="tabpanel"
                      aria-labelledby="ord-query">
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
                            <c:forEach var="groupOrder" items="${GOlist4}"> 
                             <tr data-widget="expandable-table" aria-expanded="false">
                                  <td>${groupOrder.groupOrderID}</td>
                                  <td>${groupOrder.orderStatus}</td>
                                  <td>${groupOrder.groupOrderCreateTime}</td>
                                  <td>${groupOrder.buildingID}</td>
                                  <td>${groupOrder.groupTotalPrice}</td>
                                  <td>${groupOrder.groupOrderSubmitTime}</td>
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
                             </c:forEach>   
                             
                            <c:forEach var="groupOrder" items="${GOlist6}"> 
                             <tr data-widget="expandable-table" aria-expanded="false">
                                  <td>${groupOrder.groupOrderID}</td>
                                  <td>${groupOrder.orderStatus}</td>
                                  <td>${groupOrder.groupOrderCreateTime}</td>
                                  <td>${groupOrder.buildingID}</td>
                                  <td>${groupOrder.groupTotalPrice}</td>
                                  <td>${groupOrder.groupOrderSubmitTime}</td>
                               </th>
                               </tr>
                               <tr class="expandable-body">
                               <td colspan="6">
                               <p>
                                 訂單內容<br>
                                 Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                         
                               <br>訂單內容
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