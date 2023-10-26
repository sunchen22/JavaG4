<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.producttype.dao.*"%>
<%@ page import="com.producttype.entity.*"%>
<%@ page import="com.producttype.service.*"%>
<%@ page import="com.varytype.dao.*"%>
<%@ page import="com.varytype.entity.*"%>
<%@ page import="com.varytype.service.*"%>
<%@ page import="com.product.entity.*"%>
<%@ page import="com.product.service.*"%>
<%
    ProductTypeService PTSvc = new ProductTypeService();
    List<ProductType> PTlist = PTSvc.getAll();
    pageContext.setAttribute("list",PTlist);
%>
<%
VaryTypeService VTSvc = new VaryTypeService();
List<VaryType> VTList = VTSvc.getAll();
pageContext.setAttribute("VTlist", VTList);
%>
<%
    ProductService PSvc = new ProductService();
    List <Product> PList= PSvc.getAll();
    pageContext.setAttribute("Plist",PList);
%>
<!DOCTYPE html>
<html lang="zh-Hant">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>樓頂揪樓咖 商品上架</title>

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
  <link rel="stylesheet" href="shelve.css">

  
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
    <section>
    
      <!-- Content Header (Page header) -->
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <h1 class="m-0">客製選項設定</h1>
            </div>
            <div class="col-sm-6">
              <!-- <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item active">商家資料管理 v2</li>
              </ol> -->
            </div><!-- /.col -->
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
   
     

      
  
    <div class="col-12">
      <!-- interactive chart -->
      <div class="card card-warning card-outline">
        <div class="card-header">
          <h3 class="card-title">
            
          </h3>
          <div class="card-tools">
            <button type="button" class="btn btn-tool" data-card-widget="collapse">
            </button>
          </div>
        </div>
        

        <!-- <div class="col-md-3"> -->

      <div class="shelve">

  
		<FORM METHOD="post" ACTION="productVary.do" name="form1" >
	
			<div class="col-sm-10">
				<label>商品名稱:</label>
				<br>
				<select name="productID" class="form-control" style="width:200px;display:inline">
				<c:forEach var="productVO" items="${Plist}">
					<option value="${productVO.productID}"  ${(param.productID==productVO.productID)? 'selected':'' } >${productVO.productName}
				</c:forEach>
				</select>
			</div>
			測試商品id=
	${param.productID}
	
	
			<div class="col-sm-10">
				<label>客製分類:</label>
				<br>
				<select name="varyTypeID" class="form-control" style="width:200px;display:inline">
				<c:forEach var="varyTypeVO" items="${VTlist}">
					<option value="${varyTypeVO.varyTypeID}"  ${(param.varyTypeID==varyTypeVO.varyTypeID)? 'selected':'' } >${varyTypeVO.varyType}
				</c:forEach>
				</select>
			</div>
				
						
			<div class="col-sm-10">
				<label>客製選項:</label>
				<br>
				<input type="TEXT" name="productVaryDes"  style="width:200px;display:inline" value="${param.productVaryDes}" class="form-control"  />
				<span style="color:red;">${errorMsgs.productVaryDes}</span>
			
			</div>


			<div class="col-sm-10">
				<label>客製金額:</label>
				<br>
				<input type="TEXT" name="productVaryPrice" autocomplete="off"style="width:200px;display:inline"value="${param.productVaryPrice}" class="form-control" />
				<span style="color:red;" >${errorMsgs.productVaryPrice}</span>
			</div>


             測試 ${param.productVaryID}
			<div>
				<input type="hidden" name="productVaryID" value="${param.productVaryID}">			
				<input type="hidden" name="action" value="update">
				<input type="submit"  id="submit" value="確認修改">
			</div>
			
			
			
			
		
	</FORM>
 
  
    </div>
     </div>
     <script type="text/javascript">
//清除提示信息
function hideContent(d) {
     document.getElementById(d).style.display = "none";
}

//照片上傳-預覽用
var filereader_support = typeof FileReader != 'undefined';
if (!filereader_support) {
	alert("No FileReader support");
}
acceptedTypes = {
		'image/png' : true,
		'image/jpeg' : true,
		'image/gif' : true
};
function previewImage() {
	var upfile1 = document.getElementById("p_file");
	upfile1.addEventListener("change", function(event) {
		var files = event.target.files || event.dataTransfer.files;
		for (var i = 0; i < files.length; i++) {
			previewfile(files[i])
		}
	}, false);
}
function previewfile(file) {
	if (filereader_support === true && acceptedTypes[file.type] === true) {
		var reader = new FileReader();
		reader.onload = function(event) {
			var image = new Image();
			image.src = event.target.result;
			image.width = 100;
			image.height = 75;
			image.border = 2;
			if (blob_holder.hasChildNodes()) {
				blob_holder.removeChild(blob_holder.childNodes[0]);
			}
			blob_holder.appendChild(image);
		};
		reader.readAsDataURL(file);
		document.getElementById('submit').disabled = false;
	} else {
		blob_holder.innerHTML = "<div  style='text-align: left;'>" + "● filename: " + file.name
				+ "<br>" + "● ContentTyp: " + file.type
				+ "<br>" + "● size: " + file.size + "bytes"
				+ "<br>" + "● 上傳ContentType限制: <b> <font color=red>image/png、image/jpeg、image/gif </font></b></div>";
		document.getElementById('submit').disabled = true;
	}
}
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


</body>

</html>