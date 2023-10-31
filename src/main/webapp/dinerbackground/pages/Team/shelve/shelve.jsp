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
<%@ page import="com.product.service.*"%>
<%@ page import="com.dinerinfo.service.*"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%

	DinerInfo account = (DinerInfo) session.getAttribute("account");

%>
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
    List <ProductVO> PList= PSvc.getAll();
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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">
  <link rel="stylesheet" href="shelve.css">

  
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
  </head>

  <body class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
    <div class="wrapper">

  <%@ include file="/dinerbackground/pages/Team/dinerNav.file"%>
  



    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
    <section>
    
      <!-- Content Header (Page header) -->
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <h1 class="m-0">商品上架</h1>
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

  
		<FORM METHOD="post" ACTION="product.do" name="form1" enctype="multipart/form-data">
	

			
			<div class="col-sm-10">
				<label>商品名稱:</label>
				<br>
				<input type="TEXT" name="productName"  style="width:200px;display:inline" value="${param.productName}" class="form-control"  />
				<span style="color:red;">${errorMsgs.productName}</span>
			
			</div>


			<div class="col-sm-10">
				<label>商品金額:</label>
				<br>
				<input type="TEXT" name="productPrice" style="width:200px;display:inline"value="${param.productPrice}" class="form-control" />
				<span style="color:red;" >${errorMsgs.productPrice}</span>
			</div>

			
			<div class="col-sm-10">
				<label>商品類型:</label>
				<br>
				<select name="productTypeID" class="form-control" style="width:200px;display:inline">
				<c:forEach var="productTypeVO" items="${list}">
					<option value="${productTypeVO.productTypeID}" ${(param.productTypeID==productTypeVO.productTypeID)? 'selected':'' } >${productTypeVO.productTypeDes}
				</c:forEach>
				</select>
			</div>
			
						
			<div class="col-sm-10">
				<label>每日庫存:</label>
				<br>
				<input type="TEXT" name="productDailyStock" style="width:200px;display:inline"value="${param.productDailyStock}" class="form-control" />
				<span style="color:red;">${errorMsgs.productDailyStock}</span>
			</div>
			
			

		  <div class="col-sm-10">
            <label >商品圖片:</label> 
        	<br>
            <input type="file" id="p_file" name="productBlob1" onclick="previewImage()"/>                     
            <br>
            <input type="file" id="p_file2" name="productBlob2" onclick="previewImage()"/>
            <br>
            <input type="file" id="p_file3" name="productBlob3" onclick="previewImage()"/>
            
            
            <br>
            <br>

           <div id="preview" class="col-sm-6">
              <span class="text">預覽圖</span>
            </div>
        		
            <div id="preview2" class="col-sm-6">
              <span class="text">預覽圖2</span>
            </div>
      			
            <div id="preview3" class="col-sm-6">
              <span class="text">預覽圖3</span>
            </div>
          </div>
    
                 
	        <div class="col-sm-10">
	            <label >商品詳情：</label><span style="color:red;">${errorMsgs.productRemark}</span>
	            <textarea name="productRemark" id="productRemark" class="form-control" rows="10" style="width: 60%;">${param.productRemark}</textarea>
	        </div>
           
           
			<div>
				<input type="hidden" name="dinerID"	value="${account.dinerID}"/>
				<input type="hidden" name="action" value="insert">				
				<input type="submit"  id="submit" value="新增商品">
			</div>
			
	</FORM>

    <script>

      window.addEventListener("load", function(e){

      
        var preview_el = document.getElementById("preview");
        var p_file_el = document.getElementById("p_file");
        
        var preview_img = function(file){

          var reader = new FileReader(); // 用來讀取檔案
          reader.readAsDataURL(file); // 讀取檔案
          reader.addEventListener("load", function () {
       

            let img_str = '<img src="' + reader.result + '" class="preview_img">';
            preview_el.innerHTML = img_str;
          });
        };


        p_file_el.addEventListener("change", function(e){
          if(this.files.length > 0){
            preview_img(this.files[0]);
          }else{
            preview_el.innerHTML = '<span class="text">預覽圖</span>';
          }
        });
        
        
        var preview_el2 = document.getElementById("preview2");
        var p_file_el2 = document.getElementById("p_file2");
        
        var preview_img2 = function(file){

          var reader2 = new FileReader(); // 用來讀取檔案
          reader2.readAsDataURL(file); // 讀取檔案
          reader2.addEventListener("load", function () {
       

            let img_str2 = '<img src="' + reader2.result + '" class="preview_img">';
            preview_el2.innerHTML = img_str2;
          });
        };


        p_file_el2.addEventListener("change", function(e){
          if(this.files.length > 0){
            preview_img2(this.files[0]);
          }else{
            preview_el2.innerHTML = '<span class="text">預覽圖2</span>';
          }
        });
        
        
        
        var preview_el3 = document.getElementById("preview3");
        var p_file_el3 = document.getElementById("p_file3");
        
        var preview_img3 = function(file){

          var reader3 = new FileReader(); // 用來讀取檔案
          reader3.readAsDataURL(file); // 讀取檔案
          reader3.addEventListener("load", function () {
       

            let img_str3 = '<img src="' + reader3.result + '" class="preview_img">';
            preview_el3.innerHTML = img_str3;
          });
        };


        p_file_el3.addEventListener("change", function(e){
          if(this.files.length > 0){
            preview_img3(this.files[0]);
          }else{
            preview_el3.innerHTML = '<span class="text">預覽圖3</span>';
          }
        });
        
        
        

      });
      
      
     </script>
  
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


</body>

</html>