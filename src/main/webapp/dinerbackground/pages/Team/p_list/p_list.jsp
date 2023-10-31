<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.varytype.dao.*"%>
<%@ page import="com.product.entity.*"%>
<%@ page import="com.product.service.*"%>
<%@ page import="com.dinerinfo.service.*"%>
<%@ page import="com.dinerinfo.entity.*"%>



<%

	DinerInfo account = (DinerInfo) session.getAttribute("account");
	ProductService PSvc2 = new ProductService();
	List <ProductVO> PList2= PSvc2.getByDID(account.getDinerID());
	pageContext.setAttribute("Plist2",PList2);
%>



<!DOCTYPE html>
<html lang="zh-Hant">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>樓頂揪樓咖 商品列表</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <!-- <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css"> -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
    integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">
</head>

<body class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
  <div class="wrapper">
<%@ include file="/dinerbackground/pages/Team/dinerNav.file"%>

   

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <h1 class="m-0">商品列表</h1>
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

      <div class="card">
        <div class="card-header">
        <h3 class="card-title">商品列表</h3>
        </div>
        
        <div class="card-body">
        <table id="example1" class="table table-bordered table-striped">
        <thead>
        <tr  align="center">
<!--         <th>商品編號</th> -->
		
        <th>商品名稱</th>
        <th>商品價格</th>
        <th>商品類型</th>
        <th>每日庫存</th>
        <th>修改時間</th>
        <th>商品圖片</th>
        <th>編輯商品</th>
        <th>商品狀態</th>
        <th>商品上架</th>
        
        
        </tr>
        </thead>
        <tbody>


	


	<c:forEach var="productVO" items="${Plist2}" >
		
		<tr align="center">
<%-- 			<td>${productVO.productID}</td> --%>

			<td>${productVO.productName}</td>
			<td>${productVO.productPrice}</td>
						
			<td>${productVO.getProductType().getProductTypeDes()}</td>	
<%-- 			<td>${productVO.productTypeID}</td> --%>
					
			<td width="12%">${productVO.productDailyStock}</td>
			
			<td width="12%">${productVO.productReleaseTime}</td>
			
			<td><img style="width:80px" src="<%= request.getContextPath()%>/dinerbackground/pages/Team/shelve/productPhoto.do?productID=${productVO.productID}"></td>			
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/shelve/product.do" style="margin-bottom: 0px;">
			     <input type="submit" value="編輯商品">
			     <input type="hidden" name="productID"  value="${productVO.productID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>


			<td>
			${productVO.productStatus}
<%-- 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/shelve/product.do"	style="margin-bottom: 0px;"> --%>
<!-- 					<input type="submit" value="刪除商品">  -->
<%-- 					<input type="hidden"name="productID" value="${productVO.productID}"> --%>
<!-- 					<input type="hidden" name="action" value="delete"> -->
<!-- 				</FORM> -->
			</td>

			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/shelve/product.do"
					style="margin-bottom: 0px;">
					<input type="submit"  value="下架商品"> 
					<input type="hidden"name="productID" value="${productVO.productID}">
					<input type="hidden"name="productStatus" value="已下架">
					<input type="hidden" name="action" value="off_shelve">
				</FORM>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/shelve/product.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="重新上架"> 
					<input type="hidden"name="productID" value="${productVO.productID}">
					<input type="hidden"name="productStatus" value="上架中">
					<input type="hidden" name="action" value="off_shelve">
				</FORM>
				
			</td>
			
		</tr>
	</c:forEach>

       
    
 
    

        </tbody>
       
        </table>
        </div>
        
        </div>
        

        

   







          

        </div><!--/. container-fluid -->
  
      <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
      <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->


 
  <!-- ./wrapper -->

  <!-- REQUIRED SCRIPTS -->
 <!-- jQuery -->
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery/jquery.min.js"></script>
			<!-- Bootstrap -->
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
			<!-- overlayScrollbars -->
			<script
				src="<%=request.getContextPath()%>/dinerbackground/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
			<!-- AdminLTE App -->
			<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/adminlte.js"></script>

			<!-- PAGE PLUGINS -->
			<!-- jQuery Mapael -->
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/raphael/raphael.min.js"></script>
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery-mapael/jquery.mapael.min.js"></script>
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery-mapael/maps/usa_states.min.js"></script>
			<!-- ChartJS -->
			<script src="<%=request.getContextPath()%>/dinerbackground/plugins/chart.js/Chart.min.js"></script>

			<!-- AdminLTE for demo purposes -->
			<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/demo.js"></script>
			<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
			<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/pages/dashboard2.js"></script>
</body>

</html>