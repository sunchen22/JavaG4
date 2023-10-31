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
<%@ page import="com.productvary.dao.*"%>
<%@ page import="com.productvary.entity.*"%>
<%@ page import="com.productvary.service.*"%>
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
<%
	ProductVO product = (ProductVO) session.getAttribute("product");
	ProductVaryService PVSvc = new ProductVaryService();
	List<ProductVary> PVlist = PVSvc.getByPID(product.getProductID());
	pageContext.setAttribute("PVlist", PVlist);
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

  
  

  <!-- PAGE PLUGINS -->
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
              <h1 class="m-0">客製選項編輯</h1>
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
					<option value="${productVO.productID}"${(param.productID==productVO.productID)? 'selected':'' } >${productVO.productName}
				</c:forEach>
				</select>
			</div>
			

			<div class="col-sm-10">
				<label>客製分類:</label>
				<br>
				<select name="varyTypeID" id="mySelect" class="form-control" style="width:200px;display:inline" onchange="getValue()">
				<c:forEach var="varyTypeVO" items="${VTlist}">
					<option value="${varyTypeVO.varyTypeID}" >${varyTypeVO.varyType}
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


           
			<div>
				<input type="hidden" name="action" value="insert2">
				<input type="submit"  id="submit" value="新增客製選項">
			</div>
			
	
		
	</FORM>

  <table id="example1" class="table table-bordered table-striped">
                                        
                                        
                                        
                                        
        <thead>
        <tr  align="center">
       	<th>商品名稱</th>
       	<th>客製類型</th>  
        <th>客製名稱</th>
        <th>客製價格</th>           
   		<th>編輯</th>
   	
        
        </tr>
        </thead>
        <tbody>


	


	<c:forEach var="productVaryVO" items="${PVlist}" >
		
		<tr align="center">
		
			<td>${productVaryVO.getProductVO().getProductName()}</td>
			<td>${productVaryVO.getVaryTypeVO().getVaryType()}</td>
			<td>${productVaryVO.productVaryDes}</td>
			<td>${productVaryVO.productVaryPrice}</td>			
		
			


			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/shelve/productVary.do" style="margin-bottom: 0px;">
			  	 
			     <input type="submit" value="編輯">
			     <input type="hidden" name="productVaryID"  value="${productVaryVO.productVaryID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>


<!-- 			<td> -->
<%-- 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/shelve/productVary.do" --%>
<!-- 					style="margin-bottom: 0px;"> -->
<!-- 					<input type="submit" value="刪除">  -->
<%-- 					<input type="hidden" name="productVaryID" value="${productVaryVO.productVaryID}"> --%>
<!-- 					<input type="hidden" name="action" value="delete"> -->
<!-- 				</FORM> -->
<!-- 			</td> -->

			
		</tr>
	</c:forEach>

       
    
 
    

        </tbody>
       
        </table>
  
    </div>
     </div>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/p_list/p_list.jsp" name="form1">
					<div class="col-sm-4" >
						<div class="form-group">							
							<input type="submit" value="返回商品列表" >						
						</div>
					</div>
				</FORM>
     
     
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