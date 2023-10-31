<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.grouporder.service.*"%>
<%@ page import="com.grouporder.entity.*"%>
<%@ page import="com.grouporder.dao.*"%>
<%@ page import="com.dinerinfo.service.*"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% 

	DinerInfo account3 = (DinerInfo) session.getAttribute("account");
    GroupOrderDAOHibernateImplC godhi3 = new GroupOrderDAOHibernateImplC();
    int dinerID = account3.getDinerID();
	List<Object[]> list3 = godhi3.getOrderDetail2(dinerID,"3");                          	
    pageContext.setAttribute("list3", list3);	

%>

<%

 	DinerInfo account5 = (DinerInfo) session.getAttribute("account");
     GroupOrderDAOHibernateImplC godhi5 = new GroupOrderDAOHibernateImplC();
 	List<Object[]> list5 = godhi5.getOrderDetail2(account5.getDinerID(),"5");                          	
     pageContext.setAttribute("list5", list5);	
    
%>
<%

 	DinerInfo account6 = (DinerInfo) session.getAttribute("account");
     GroupOrderDAOHibernateImplC godhi6 = new GroupOrderDAOHibernateImplC();
 	List<Object[]> list6 = godhi6.getOrderDetail2(account6.getDinerID(),"6");                          	
     pageContext.setAttribute("list6", list6);	
    
%>
<%

 	DinerInfo account7 = (DinerInfo) session.getAttribute("account");
     GroupOrderDAOHibernateImplC godhi7 = new GroupOrderDAOHibernateImplC();
 	List<Object[]> list7 = godhi7.getOrderDetail2(account7.getDinerID(),"7");                          	
     pageContext.setAttribute("list7", list7);	
    
%>
<%

 	DinerInfo account4 = (DinerInfo) session.getAttribute("account");
     GroupOrderDAOHibernateImplC godhi4 = new GroupOrderDAOHibernateImplC();
 	List<Object[]> list4 = godhi4.getOrderDetail2(account4.getDinerID(),"4");                          	
     pageContext.setAttribute("list4", list4);	
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

<%@ include file="/dinerbackground/pages/Team/dinerNav.file"%>


   

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
                             <th>訂單地址</th>
                             <th>金額</th>
                             <th>成立時間</th>
                             </tr>
                             </thead>
                             
             
<%
GroupOrderVO grouporder = (GroupOrderVO) request.getAttribute("grouporder"); 
%>                   	         

                           
                           
                              <tbody>

                              
                      
                                <tr data-widget="expandable-table" aria-expanded="false">
                                  <td>${grouporder.groupOrderID}</td>
                                  <td>
                                    ${grouporder.orderStatus eq '1' ? '揪團已建立' : 
									  grouporder.orderStatus eq '2' ? '成團條件達成':
									  grouporder.orderStatus eq '3' ? '待商家確認':
									  grouporder.orderStatus eq '4' ? '揪團失敗':
									  grouporder.orderStatus eq '5' ? '餐點準備中':
									  grouporder.orderStatus eq '6' ? '商家拒單':
									  grouporder.orderStatus eq '7' ? '餐點送達' : ''}                                  
                                  </td>
                                  
                                  <td>${grouporder.buildingID}</td>
                                  <td>${grouporder.groupTotalPrice}</td>
                                  <td>${grouporder.groupOrderSubmitTime}</td>
                                  
                                  </tr>
                                  <tr class="expandable-body">
                                  <td colspan="6">
                              
                 
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
                            <th>訂單地址</th>
                            <th>金額</th>
                            <th>成立時間</th>
                            </tr>
                            </thead>
                            <tbody>
                          <c:forEach var="row" items="${list3}"> 
                              <tr data-widget="expandable-table" aria-expanded="false">
                                  <td>${row[0]}</td>
                                  <td>
                                    ${row[5] eq '1' ? '揪團已建立' : 
									  row[5] eq '2' ? '成團條件達成':
									  row[5] eq '3' ? '待商家確認':
									  row[5] eq '4' ? '揪團失敗':
									  row[5] eq '5' ? '餐點準備中':
									  row[5] eq '6' ? '商家拒單':
									  row[5] eq '7' ? '餐點送達' : ''}                                  
                                  </td>
                                  <td>${row[6]}</td>
                                  <td>${row[7]}</td>
                                  <td>${row[8]}</td>
                                </tr>
                                <tr class="expandable-body">
                                <td colspan="6">
                                <p>
                                <br>訂單內容<br> 
            <c:forEach var="value1" items="${fn:split(row[1], ',')}" varStatus="status1">
                ${value1}*
                <c:set var="value2" value="${fn:split(row[2], ',')[status1.index]}" />
                ${value2}
                ${fn:replace(fn:split(row[7], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[8], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[9], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[10], ',')[status1.index], 'NA', '')}
                 ,&ensp; 訂購者 :
                ${fn:replace(fn:split(row[11], ',')[status1.index], 'NA', '')}
                
                <br><br>
            </c:forEach>
                                       
                                </p>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/ord_query/orderquery.do"
									style="margin-bottom: 0px;">
									<input type="submit"  value="訂單已送達"> 
									<input type="hidden"name="groupOrderID" value="${row[0]}">
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
                            <th>訂單地址</th>
                            <th>金額</th>
                            <th>成立時間</th>
                            </tr>
                            </thead>
                            <tbody>
                          <c:forEach var="row" items="${list5}"> 
                              <tr data-widget="expandable-table" aria-expanded="false">
                                  <td>${row[0]}</td>
                                  <td>
                                    ${row[3] eq '1' ? '揪團已建立' : 
									  row[3] eq '2' ? '成團條件達成':
									  row[3] eq '3' ? '待商家確認':
									  row[3] eq '4' ? '揪團失敗':
									  row[3] eq '5' ? '餐點準備中':
									  row[3] eq '6' ? '商家拒單':
									  row[3] eq '7' ? '餐點送達' : ''}                                  
                                  </td>
                                  <td>${row[4]}</td>
                                  <td>${row[5]}</td>
                                  <td>${row[7]}</td>
                                </tr>
                                <tr class="expandable-body">
                                <td colspan="6">
                                <p>
                                  
            <c:forEach var="value1" items="${fn:split(row[1], ',')}" varStatus="status1">
                ${value1}*
                <c:set var="value2" value="${fn:split(row[2], ',')[status1.index]}" />
                ${value2}
                ${fn:replace(fn:split(row[7], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[8], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[9], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[10], ',')[status1.index], 'NA', '')}
                 ,&ensp; 訂購者 :
                ${fn:replace(fn:split(row[11], ',')[status1.index], 'NA', '')}
                
                <br><br>
            </c:forEach>
                                   <br>訂單內容      
                                </p>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/ord_query/orderquery.do"
									style="margin-bottom: 0px;">
									<input type="submit"  value="訂單已送達"> 
									<input type="hidden"name="groupOrderID" value="${row[0]}">
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
                            <th>訂單地址</th>
                            <th>金額</th>
                            <th>成立時間</th>
                            </tr>
                            </thead>
                            <tbody>
                          <c:forEach var="row" items="${list7}"> 
                              <tr data-widget="expandable-table" aria-expanded="false">
                                  <td>${row[0]}</td>
                                  <td>
                                    ${row[5] eq '1' ? '揪團已建立' : 
									  row[5] eq '2' ? '成團條件達成':
									  row[5] eq '3' ? '待商家確認':
									  row[5] eq '4' ? '揪團失敗':
									  row[5] eq '5' ? '餐點準備中':
									  row[5] eq '6' ? '商家拒單':
									  row[5] eq '7' ? '餐點送達' : ''}                                  
                                  </td>
                                  <td>${row[6]}</td>
                                  <td>${row[7]}</td>
                                  <td>${row[8]}</td>
                                </tr>
                                <tr class="expandable-body">
                                <td colspan="6">
                                <p>
                                <br>訂單內容<br> 
            <c:forEach var="value1" items="${fn:split(row[1], ',')}" varStatus="status1">
                ${value1}*
                <c:set var="value2" value="${fn:split(row[2], ',')[status1.index]}" />
                ${value2}
                ${fn:replace(fn:split(row[7], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[8], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[9], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[10], ',')[status1.index], 'NA', '')}
                 ,&ensp; 訂購者 :
                ${fn:replace(fn:split(row[11], ',')[status1.index], 'NA', '')}
                
                <br><br>
            </c:forEach>
                                       
                                </p>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/ord_query/orderquery.do"
									style="margin-bottom: 0px;">
									<input type="submit"  value="訂單已送達"> 
									<input type="hidden"name="groupOrderID" value="${row[0]}">
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
                            <th>訂單地址</th>
                            <th>金額</th>
                            <th>成立時間</th>
                            </tr>
                            </thead>
                            <tbody>
                          <c:forEach var="row" items="${list5}"> 
                              <tr data-widget="expandable-table" aria-expanded="false">
                                  <td>${row[0]}</td>
                                  <td>
                                    ${row[5] eq '1' ? '揪團已建立' : 
									  row[5] eq '2' ? '成團條件達成':
									  row[5] eq '3' ? '待商家確認':
									  row[5] eq '4' ? '揪團失敗':
									  row[5] eq '5' ? '餐點準備中':
									  row[5] eq '6' ? '商家拒單':
									  row[5] eq '7' ? '餐點送達' : ''}                                  
                                  </td>
                                  <td>${row[6]}</td>
                                  <td>${row[7]}</td>
                                  <td>${row[8]}</td>
                                </tr>
                                <tr class="expandable-body">
                                <td colspan="6">
                                <p>
                                <br>訂單內容<br> 
            <c:forEach var="value1" items="${fn:split(row[1], ',')}" varStatus="status1">
                ${value1}*
                <c:set var="value2" value="${fn:split(row[2], ',')[status1.index]}" />
                ${value2}
                ${fn:replace(fn:split(row[7], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[8], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[9], ',')[status1.index], 'NA', '')}
                ${fn:replace(fn:split(row[10], ',')[status1.index], 'NA', '')}
                 ,&ensp; 訂購者 :
                ${fn:replace(fn:split(row[11], ',')[status1.index], 'NA', '')}
                
                <br><br>
            </c:forEach>
                                       
                                </p>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/ord_query/orderquery.do"
									style="margin-bottom: 0px;">
									<input type="submit"  value="訂單已送達"> 
									<input type="hidden"name="groupOrderID" value="${row[0]}">
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