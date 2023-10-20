<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.varytype.dao.*"%>
<%@ page import="com.varytype.entity.*"%>
<%@ page import="com.varytype.service.*"%>
<%
    VaryTypeService VTSvc = new VaryTypeService();
    List <VaryType> VTList= VTSvc.getAll();
    pageContext.setAttribute("list",VTList);
%>
<!DOCTYPE html>
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
  <link rel="stylesheet" href="type_setting.css">
  <script src="../../../plugins/jquery/jquery.min.js"></script>
  
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
          <a href="dashboard-index" class="nav-link">首頁</a>
        </li>

      </ul>

      <!-- 彈出右側下拉欄 -->
      <!-- Right navbar links -->
      <ul class="navbar-nav ml-auto">

        <!-- 搜尋控制鈕 -->
        <!-- Navbar Search -->
        <!-- <li class="nav-item">
          <a class="nav-link" data-widget="navbar-search" href="#" role="button">
            <i class="fas fa-search"></i>
          </a>
          <div class="navbar-search-block">
            <form class="form-inline">
              <div class="input-group input-group-sm">
                <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
                <div class="input-group-append">
                  <button class="btn btn-navbar" type="submit">
                    <i class="fas fa-search"></i>
                  </button>
                  <button class="btn btn-navbar" type="button" data-widget="navbar-search">
                    <i class="fas fa-times"></i>
                  </button>
                </div>
              </div>
            </form>
          </div>
        </li> -->

        <!-- 訊息控制鈕 -->
        <!-- Messages Dropdown Menu -->
        <!-- <li class="nav-item dropdown">
          <a class="nav-link" data-toggle="dropdown" href="#">
            <i class="far fa-comments"></i>
            <span class="badge badge-danger navbar-badge">3</span>
          </a>
          <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
            <a href="#" class="dropdown-item">
              <!-- Message Start -->
        <!-- <div class="media">
                <img src="dist/img/user1-128x128.jpg" alt="User Avatar" class="img-size-50 mr-3 img-circle">
                <div class="media-body">
                  <h3 class="dropdown-item-title">
                    Brad Diesel
                    <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
                  </h3>
                  <p class="text-sm">Call me whenever you can...</p>
                  <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                </div>
              </div> -->
        <!-- Message End -->
        <!-- </a>
            <div class="dropdown-divider"></div>
            <a href="#" class="dropdown-item"> -->
        <!-- Message Start -->
        <!-- <div class="media">
                <img src="dist/img/user8-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
                <div class="media-body">
                  <h3 class="dropdown-item-title">
                    John Pierce
                    <span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>
                  </h3>
                  <p class="text-sm">I got your message bro</p>
                  <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                </div>
              </div> -->
        <!-- Message End -->
        <!-- </a>
            <div class="dropdown-divider"></div>
            <a href="#" class="dropdown-item"> -->
        <!-- Message Start -->
        <!-- <div class="media">
                <img src="dist/img/user3-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
                <div class="media-body">
                  <h3 class="dropdown-item-title">
                    Nora Silvester
                    <span class="float-right text-sm text-warning"><i class="fas fa-star"></i></span>
                  </h3>
                  <p class="text-sm">The subject goes here</p>
                  <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                </div>
              </div> -->
        <!-- Message End -->
        <!-- </a>
            <div class="dropdown-divider"></div>
            <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
          </div>
        </li>  -->

        <!-- 提醒控制鈕 -->
        <!-- Notifications Dropdown Menu -->
        <!-- <li class="nav-item dropdown">
          <a class="nav-link" data-toggle="dropdown" href="#">
            <i class="far fa-bell"></i>
            <span class="badge badge-warning navbar-badge">15</span>
          </a>
          <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
            <span class="dropdown-item dropdown-header">15 Notifications</span>
            <div class="dropdown-divider"></div>
            <a href="#" class="dropdown-item">
              <i class="fas fa-envelope mr-2"></i> 4 new messages
              <span class="float-right text-muted text-sm">3 mins</span>
            </a>
            <div class="dropdown-divider"></div>
            <a href="#" class="dropdown-item">
              <i class="fas fa-users mr-2"></i> 8 friend requests
              <span class="float-right text-muted text-sm">12 hours</span>
            </a>
            <div class="dropdown-divider"></div>
            <a href="#" class="dropdown-item">
              <i class="fas fa-file mr-2"></i> 3 new reports
              <span class="float-right text-muted text-sm">2 days</span>
            </a>
            <div class="dropdown-divider"></div>
            <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
          </div>
        </li> -->

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
                  <a href="./info-change.html" class="nav-link">
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
                  <a href="..\shelve\type_setting.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>商品群組設定</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="..\shelve\shelve.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>單獨上架</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="..\shelve\shelve_batch.html" class="nav-link">
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
                  <a href="..\p_list\p_list.html" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>商品列表</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="..\shelve\p_preview.html" class="nav-link">
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
                  <a href="../ord_query/ord_query.html#tab1" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>揪團成功訂單</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="../ord_query/ord_query.html#tab2" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>已完成訂單</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="../ord_query/ord_query.html#tab3" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>已取消訂單</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="../ord_query/ord_query.html#tab4" class="nav-link">
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
              <h1>商品客製化設定</h1>
            </div>
           
              <!-- <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item active">商家資料管理 v2</li>
              </ol> -->
            </div><!-- /.col -->
          </div><!-- /.row -->
       
    
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
                              

                             <FORM METHOD="post" ACTION="varytype.do" name="form1">
								<table>
										
									<tr>
										<td>客製選項分類:</td>
										<td><input type="TEXT" name="varyType" value="${param.varyType}" placeholder="辣度"/>${errorMsgs.varyType}</td> 
										<td>
										    <input type="hidden" name="action" value="insert">
									        <input type="submit" value="新增分類"> 
										</td>
									</tr>
								
								
								</table>
							
							</FORM>




                              
                              
                              
                              
                              
                              
                              

                              <!-- ==============頁籤標頭===================== -->
                              <div class="nav nav-tabs" id="product-tab" role="tablist">
                              
                            <c:forEach var="varyTypeVO" items="${list}">

							  <a class="nav-item nav-link " id="product-revenue-tab" data-toggle="tab"
                                  href="#product-${varyTypeVO.varyTypeID}" role="tab" aria-controls="product-revenue" aria-selected="true">
                                  ${varyTypeVO.varyType}
                               </a>
							</c:forEach>
                              

                              </div>
                            </nav>
        
                          </div>
                        </div>
        
                        <!-- /.card-header -->
                        <!-- ==============頁籤內容===================== -->
                        <!-- Tab panes -->
                        <div class="tab-content">
                          <div class="tab-content p-3" id="nav-tabContent">
 
        
                            <!-- 標籤 ======商品 銷售量==== 內容 -->
                            
                         <c:forEach var="varyTypeVO" items="${list}">  
                            <div class="tab-pane fade" id="product-${varyTypeVO.varyTypeID}" role="tabpanel" aria-labelledby="product-volume-tab">
                              <div class="card-body table-responsive p-0" style="height: 300px;">
                                    <td>
									  <FORM METHOD="post" ACTION="varytype.do" style="margin-bottom: 0px;">
									     <input type="submit" value="刪除${varyTypeVO.varyType}分類">
									     <input type="hidden" name="varyTypeID" value="${varyTypeVO.varyTypeID}">
									     <input type="hidden" name="action" value="delete"></FORM>
									</td>

	        
                                <table class="table table-head-fixed text-nowrap">
                               

                                    <article class="task_container">
                                      
                                      <button type="button" class="btn_empty">清空</button>
                                      <h1 class="title1">${varyTypeVO.varyType}</h1>
                                
                                     
                                        <input type="text" class="task_name" placeholder="大辣" >
                                       
                                        <font size="5.8">+</font>
                                        <input type="number" class="task_name2" placeholder="0" >  
                                        <font size="4">元</font>          
                                        <button type="button" class="task_add">新增</button>
                                      
                                
                                      <div class="task_list_parent">
                                        <ul class="task_list">
                                        </ul>
                                      </div>
                                    </article>
                                   
                                   
                                   
                                   
                                </table>
                              </div>
                            </div>
                          
       					</c:forEach>
                         
                          
        

                              </div>  
                             </div>  
                          </div>                  
                          <!-- /.card-body -->
                        </div>
                        <!-- /.card -->

               
               
               
        
        
        
        
        
        

          
        
        <script>
        
        
        var user_id = "jo15";
        function init(){
        
          $("ul.task_list").html('<li style="text-align: center;"><i class="fas fa-spinner fa-spin fa-3x"></i></li>');
          fetch('https://notes.webmix.cc/ajax/teach/api/list.php?user_id=' + user_id).then(function(response) {
            //console.log(response);
            return response.json();
          }).then(function(data) {
         
            let list_html = '';
            $.each(data, function(index, item){
        
              list_html += '<li data-id="' + item.item_id + '" data-star="' + item.star + '" data-sort="' + item.sort + '">';
              list_html +=   '<div class="item_flex">';
              list_html +=     '<div class="left_block">';
              list_html +=       '<div class="btn_flex">';
              list_html +=         '<button type="button" class="btn_up">∧</button>';
              list_html +=         '<button type="button" class="btn_down">∨</button>';
              list_html +=       '</div>';
              list_html +=     '</div>';
              list_html +=     '<div class="middle_block">';
              list_html +=       '<p class="para">' + data.name +'+'+data.name2 +'元'+ '</p>';
              list_html +=       '<input type="text" class="task_name_update -none" placeholder="客製化內容" value="' + item.name + '">';
              list_html +=         '<font size="5.8" class="task_name3_update -none" >+</font>'
              list_html +=       '<input type="text" class="task_name2_update -none" placeholder="金額" value="' + item.name2 + '">';
              list_html +=         '<font size="5.8" class="task_name3_update -none" >元</font>'
              list_html +=     '</div>';
              list_html +=     '<div class="right_block">';
              list_html +=       '<div class="btn_flex">';
              list_html +=         '<button type="button" class="btn_update">更新</button>';
              list_html +=         '<button type="button" class="btn_delete">移除</button>';
              list_html +=       '</div>';
              list_html +=     '</div>';
              list_html +=   '</div>';
              list_html += '</li>';
            });
            $("ul.task_list").html(list_html);
          });
        
        }
        
        // 更新整體的排序
        function reload_sort(){
          let formData = new FormData();
          let sort_item = [];
          $("ul.task_list").children("li").each(function(i, item){
            $(this).attr("data-sort", (i + 1));
        
        
            formData.append("data["+i+"][item_id]", $(this).attr("data-id"))
            formData.append("data["+i+"][sort]", $(this).attr("data-sort"))
          });

        
          formData.append("user_id", user_id);
        
          $("article.task_container").append("<div class='temp_loading'><span><i class='fas fa-spinner fa-spin fa-5x'></i></span></div>");
        
          fetch("https://notes.webmix.cc/ajax/teach/api/patch_sort.php", {
            method: "PATCH",
            body: new URLSearchParams(formData)
          }).then(function(response){
            return response.json();
          }).then(function(data){
            console.log(data);
            $("article.task_container").children("div.temp_loading").remove();
          });
        
        
        }
        
        $(function(){
        
          init();
        
          
        
          // ==== text 欄位新增待辦事項 ===== //
          $("input.task_name").on("keyup", function(e){
            if(e.which == 13){ // 按下 Enter 鍵
              $("button.task_add").click();
            }
          });
          // 按下新增按鈕
          $("button.task_add").on("click", function(){
            let task_text = ($("input.task_name").val()).trim();
            let task_text2 = ($("input.task_name2").val()).trim();
            if(task_text != ""&task_text2 != ""){
        
              let form_data = new FormData();
              form_data.append("user_id", user_id);
              form_data.append("name", task_text);
              form_data.append("name2", task_text2);
        
              /*
              let form_data = {
                "user_id": user_id,
                "name": task_text
              };
              */
              $("button.task_add").addClass("-disabled");
        
              fetch("https://notes.webmix.cc/ajax/teach/api/add.php", {
                method: "POST",
                //body: new URLSearchParams(form_data)
                body: form_data
              }).then(function(response){
                console.log(response);
                return response.json();
              }).then(function(data){
                console.log(data);
        
                let list_html = "";
        
                list_html += '<li data-id="' + data.item_id + '" data-star="' + data.star + '" data-sort="' + data.sort + '">';
                list_html +=   '<div class="item_flex">';
                list_html +=     '<div class="left_block">';
                list_html +=       '<div class="btn_flex">';
                list_html +=         '<button type="button" class="btn_up">∧</button>';
                list_html +=         '<button type="button" class="btn_down">∨</button>';
                list_html +=       '</div>';
                list_html +=     '</div>';
                list_html +=     '<div class="middle_block">';
                list_html +=       '<p class="para">' + data.name +'+'+data.name2 +'元'+ '</p>';
                list_html +=       '<input type="text" class="task_name_update -none" placeholder="更新客製選項" value="' + data.name + '">';
                list_html +=         '<font size="5.8" class="task_name3_update -none" >+</font>'
                list_html +=       '<input type="text" class="task_name2_update -none" placeholder="更新金額" value="' + data.name2 + '">';
                list_html +=         '<font size="5.8" class="task_name3_update -none" >元</font>'
                list_html +=     '</div>';
                list_html +=     '<div class="right_block">';
                list_html +=       '<div class="btn_flex">';
                list_html +=         '<button type="button" class="btn_update">更新</button>';
                list_html +=         '<button type="button" class="btn_delete">移除</button>';
                list_html +=       '</div>';
                list_html +=     '</div>';
                list_html +=   '</div>';
                list_html += '</li>';
        
                $("ul.task_list").prepend(list_html);
                $("input.task_name").val("");
                $("input.task_name2").val("");
                reload_sort();
              });
        
        
            }
          });
        });
        
        // ==== 移除待辦事項 ===== //
        $("ul.task_list").on("click", "button.btn_delete", function(){
          let r = confirm("確認移除？")
          if (r){
            let item_id = $(this).closest("li").attr("data-id");
            let that = this;
        
            let form_data = new FormData();
            form_data.append("user_id", user_id);
            form_data.append("item_id", item_id);
            fetch("https://notes.webmix.cc/ajax/teach/api/delete_item.php", {
              method: "DELETE",
              body: new URLSearchParams(form_data)
            }).then(function(response){
              console.log(response);
              if(response.ok){
                return response.json();
              }
            }).then(function(data){
              console.log(data);
              $(that).closest("li").animate({
                "opacity": 0
              }, 1000, "swing", function(){
                $(this).remove();
                reload_sort();
              });
            });
        
          }
        });
        $("button.btn_empty").on("click", function(){
          let r = confirm("確認清空？")
          if (r){
        
            let form_data = new FormData();
            form_data.append("user_id", user_id);
            fetch("https://notes.webmix.cc/ajax/teach/api/delete_all.php", {
              method: "DELETE",
              body: new URLSearchParams(form_data)
            }).then(function(response){
              if(response.ok){
                return response.json();
              }
            }).then(function(data){
              console.log(data);
        
              if(data.msg == "delete all success"){
                $("ul.task_list").children("li").animate({
                  "opacity": 0
                }, 1000, "swing", function(){
                  $(this).remove();
                });
              }
            }).catch(function(error){
              console.log("error");
              console.log(error);
            });
        
        
          }
        });
        
        // ==== 更新待辦事項 ===== //
        $("ul.task_list").on("click", "button.btn_update", function(){
          //console.log($(this).attr("data-edit"));
          if($(this).attr("data-updating") == "true"){ // 有 data-updating 就代表 ajax 正在傳輸中，資料正在更新中
            alert("資料更新中");
            return;
          }
        
          if($(this).attr("data-edit") == undefined){ // 進入編輯狀態
            $(this).attr("data-edit", true);
            $(this).closest("li").find("p.para").toggleClass("-none");
            $(this).closest("li").find("input.task_name_update").toggleClass("-none");
            $(this).closest("li").find("input.task_name2_update").toggleClass("-none");
            $(this).closest("li").find("font.task_name3_update").toggleClass("-none");
          }else{ // 進入檢視狀態
            let update_task_name = ($(this).closest("li").find("input.task_name_update").val()).trim();
            let update_task_name2 = ($(this).closest("li").find("input.task_name2_update").val()).trim();
            if(update_task_name == ""){
              alert("請輸入待辦事項");
            }else{
        
              $(this).attr("data-updating", true).html('<i class="fas fa-spinner fa-spin"></i>');
              let closest_li = $(this).closest("li");
              let that = this;
        
              let form_data = new FormData();
              form_data.append("user_id", user_id);
              form_data.append("item_id", $(closest_li).attr("data-id"));
              form_data.append("name", update_task_name);
              form_data.append("name2", update_task_name2);
              form_data.append("star", $(closest_li).attr("data-star"));
              form_data.append("sort", $(closest_li).attr("data-sort"));
        
              fetch("https://notes.webmix.cc/ajax/teach/api/update_item.php", {
                method: "PUT",
                body: new URLSearchParams(form_data)
              }).then(function(response){
                if(response.ok){
                  return response.json();
                }
              }).then(function(data){
                console.log(data);
        
                if(data.msg == "item update success"){
                  $(closest_li).find("p.para").html(update_task_name).toggleClass("-none");
                  $(closest_li).find("p.para").html(update_task_name2).toggleClass("-none");
                  $(closest_li).find("input.task_name_update").val(update_task_name).toggleClass("-none");
                  $(closest_li).find("input.task_name2_update").val(update_task_name2).toggleClass("-none");
                  $(that).removeAttr("data-updating").removeAttr("data-edit").html("更新");
                  alert("更新成功");
                }else{
                  alert(data.msg);
                }
              }).catch(function(error){
                console.log(error);
              });
        
            }
          }
        
        });
        
        // ==== 排序 ===== //
        $("ul.task_list").on("click", "button.btn_up, button.btn_down", function(){
        
          // 往上
          if($(this).hasClass("btn_up") && !$(this).closest("li").is(':first-child')){
            let clone_html = $(this).closest("li").clone();
            $(this).closest("li").prev().before(clone_html);
            $(this).closest("li").remove();
            reload_sort();
          }
        
          // 往下
          if($(this).hasClass("btn_down") && !$(this).closest("li").is(':last-child')){
            let clone_html = $(this).closest("li").clone();
            $(this).closest("li").next().after(clone_html);
            $(this).closest("li").remove();
            reload_sort();
          }
        
        
        });
        
        
        
        </script>








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