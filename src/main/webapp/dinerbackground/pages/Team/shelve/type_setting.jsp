<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.varytype.dao.*"%>
<%@ page import="com.varytype.entity.*"%>
<%@ page import="com.varytype.service.*"%>
<%@ page import="com.productvary.dao.*"%>
<%@ page import="com.productvary.entity.*"%>
<%@ page import="com.productvary.service.*"%>
<%
    VaryTypeService VTSvc = new VaryTypeService();
    List <VaryType> VTList= VTSvc.getAll();
    pageContext.setAttribute("VTlist",VTList);
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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">
  <link rel="stylesheet" href="type_setting.css">
  <script src="../../../plugins/jquery/jquery.min.js"></script>
  
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




                              
                              
                              
                              
                          <br>    
                              
                              

                              <!-- ==============頁籤標頭===================== -->
                              <div class="nav nav-tabs" id="product-tab" role="tablist">
                              
                            <c:forEach var="varyTypeVO" items="${VTlist}">

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
                            
                         <c:forEach var="varyTypeVO" items="${VTlist}">  
                            <div class="tab-pane fade" id="product-${varyTypeVO.varyTypeID}" role="tabpanel" aria-labelledby="product-volume-tab">
                              <div class="card-body table-responsive p-0" style="height: 50px;">
                                    <td>
									  <FORM METHOD="post" ACTION="varytype.do" style="margin-bottom: 0px;">
									     <input type="submit" value="刪除${varyTypeVO.varyType}分類">
									     <input type="hidden" name="varyTypeID" value="${varyTypeVO.varyTypeID}">
									     <input type="hidden" name="action" value="delete"></FORM>
									</td>
 
                              </div>
                            </div>                          
       					</c:forEach>
                         
        

                              </div>  
                             </div>  
                          </div>                  
                          <!-- /.card-body -->
                        </div>
                        <!-- /.card -->

                <table id="example1" class="table table-bordered table-striped">
   
        <thead>
        <tr  align="center">
       	<th>商品名稱</th>
       	<th>客製類型</th>  
        <th>客製名稱</th>
        <th>客製價格</th>           
   		<th>編輯</th>
   		<th>刪除</th>
        
        </tr>
        </thead>
        <tbody>


	
<%
	ProductVary ProductVary = (ProductVary) request.getAttribute("ProductVary");
	ProductVaryService PVSvc = new ProductVaryService();
	List<ProductVary> PVlist = PVSvc.getByType(1);
	pageContext.setAttribute("PVlist", PVlist);
%>

	<c:forEach var="productVaryVO" items="${PVlist}" >

		<tr align="center">
		
			<td>${productVaryVO.getProductVO().getProductName()}</td>
			<td>${productVaryVO.getVaryTypeVO().getVaryType()}</td>
<%-- 			<td>${productVaryVO.varyTypeID}</td> --%>
			<td>${productVaryVO.productVaryDes}</td>
			<td>${productVaryVO.productVaryPrice}</td>			
		
			


			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/shelve/productVary.do" style="margin-bottom: 0px;">
			     <input type="submit" value="編輯">
			     <input type="hidden" name="productVaryID"  value="${productVaryVO.productVaryID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>


			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/shelve/productVary.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"	name="productVaryID" value="${productVaryVO.productVaryID}">
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>

			
		</tr>
	</c:forEach>

        </tbody>
       
        </table>
                          
               
               
     


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