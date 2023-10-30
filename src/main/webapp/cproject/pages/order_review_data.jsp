<%@page import="com.dinerratingcomment.entity.DinerRatingComment"%>
<%@page import="com.dinerratingcomment.dao.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.dinerratingcomment.dao.*"%>
<%@page import="com.dinerinfo.entity.DinerInfo"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>樓頂揪樓咖後台管理</title>

    
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/cproject/plugins/fontawesome-free/css/all.min.css">
   
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/cproject/dist/css/adminlte.min.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


</head>




<body class="hold-transition sidebar-mini">
    <div class="wrapper">
        
        <nav class="main-header navbar navbar-expand navbar-white navbar-light">
           
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>

                </li>
        
            </ul>

           
            <ul class="navbar-nav ml-auto">

                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp" role="button">
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
       
        <%@ include file="../../background/pages/pageaside.file" %>


</nav> 
 
      </div>
      
    </aside>


        <div class="content-wrapper">
           
            <section class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-sm-6">
                            
                        </div>

                    </div>
                </div>
            </section>

            
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
                         
                                
<%
DinerInfo dif = (DinerInfo)request.getAttribute("dif");


DinerRatingCommentDAOImplC drcdi = new DinerRatingCommentDAOImplC();

List<DinerRatingComment> list = drcdi.getAll(dif.getDinerID());
pageContext.setAttribute("list", list);

%>



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
                                                <td><span class="text-muted"><%=dif.getDinerID() %></span></td>
                                                <td><%=dif.getDinerTaxID() %></td>
                                                <td>
                                                 <%=dif.getDinerName() %>
                                                </td>
                                                <td>
                                                  <%=dif.getDinerPhone() %>
                                                </td>
                                                <td>
                                                   <%=dif.getDinerEmail() %> 
                                                </td>

                                                <td>
                                                    <%=dif.getDinerAddress() %>
                                                </td>

                                            </tr>

                                        </tbody>
                                    </table>


                                </div>



                             
                            </div>
                       

                        </div>
                        
                    </div>
                   

                    <div class="table-responsive">
                        <table id = "table" class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="w-1">評論編號</th>
                                    
                                    <th>評論者</th>
                                    <th>評論內容</th>
                                    <th>評論時間</th>
                                    <th>評分</th>
                                    
                                    <th></th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var ="dinerratingcomment" items="${list}">
                                <tr>
                                    <td><span class="text-muted">${dinerratingcomment.commentID}</span></td>
                                    
                                    <td>${dinerratingcomment.userInfo.userName}</td>
                                    <td>${dinerratingcomment.userCommentContent}</td>
                                    <td>
                                    <fmt:formatDate value="${dinerratingcomment.userCommentTime}" pattern="yyyy-MM-dd HH:mm:ss" />                                    
                                    </td>
                                                                       
                                    <td>


									<c:forEach var="i" begin="1" end="5">
								    <c:choose>
								      <c:when test="${i <= dinerratingcomment.dinerRating}">
								        <span class="star">&#9733;</span>
								      </c:when>
								      <c:otherwise>
								        <span class="star">&#9734;</span>
								      </c:otherwise>
								    </c:choose>
								  </c:forEach>

                             
                                    </td>
                                    
                                    
                                    
                                    
                                    <td>
                                    	<form method = post action = "<%=request.getContextPath()%>/cproject/pages/drcs.do">
                                    	<input type="hidden" name="commentID"  value="${dinerratingcomment.commentID}">
                                    	<input type="hidden" name="dinerID"  value="${dinerratingcomment.dinerInfo.dinerID}">    
					      		  		<input type="hidden" name="action" value="go_for_delete">
                                        <button id = "click" type="submit" class="btn btn-warning" style="font-weight :bold">
                                        刪除
                                      </button>
										</form>
                                    </td>
  

                                </tr>
                                </c:forEach>


                            </tbody>
                        </table>


                    </div>
                    </div>
            </section>

        </div>

       




        <aside class="control-sidebar control-sidebar-warning">
           
        </aside>
      
        <footer class="main-footer">
            <strong>Copyright &copy; 2023</strong>
            樓頂揪樓咖團隊 All rights reserved.
            <div class="float-right d-none d-sm-inline-block">
                <b>Version</b> 1.1.0
            </div>
        </footer>
    </div>
   
    <script src="<%=request.getContextPath()%>/cproject/plugins/jquery/jquery.min.js"></script>
    
    <script src="<%=request.getContextPath()%>/cproject/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

   
    
    <script src="<%=request.getContextPath()%>/cproject/dist/js/adminlte.js"></script>
    
    <script src="<%=request.getContextPath()%>/cproject/plugins/chart.js/Chart.min.js"></script>
    
    <script src="<%=request.getContextPath()%>/cproject/dist/js/pages/dashboard3.js"></script>
    
	<%@ include  file="../../background/pages/pagejs.file" %>
	

    <%@ include file="included-fragment.file" %>
		<script src="https://code.jquery.com/jquery-3.5.1.js"></script>                                    <!-- ●●js  for jquery datatables 用 -->
		<script	src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>              <!-- ●●js  for jquery datatables 用 -->
		<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/dataTables.jqueryui.min.css" /> <!-- ●●css for jquery datatables 用 -->
		
      <script>
      $(document).ready(function() {
  		$('#table').DataTable({
  			"lengthMenu": [5],
  			"searching": true,  //搜尋功能, 預設是開啟
  		    "paging": true,     //分頁功能, 預設是開啟
  		    "ordering": true,   //排序功能, 預設是開啟
  		    "language": {
  		        "processing": "處理中...",
  		        "loadingRecords": "載入中...",
  		        "lengthMenu": "顯示 _MENU_ 筆結果",
  		        "zeroRecords": "沒有符合的結果",
  		        "info": "顯示第 _START_ 至 _END_ 筆結果，共<font color=red> _TOTAL_ </font>筆",
  		        "infoEmpty": "顯示第 0 至 0 筆結果，共 0 筆",
  		        "infoFiltered": "(從 _MAX_ 筆結果中過濾)",
  		        "infoPostFix": "",
  		        "search": "搜尋:",
  		        "paginate": {
  		            "first": "第一頁",
  		            "previous": "上一頁",
  		            "next": "下一頁",
  		            "last": "最後一頁"
  		        },
  		        "aria": {
  		            "sortAscending":  ": 升冪排列",
  		            "sortDescending": ": 降冪排列"
  		        }
  		    }
  		});
  		
  		$("button[id^='click']").click(function() {
  	        alert("已發送email");
  	    });
  		
  	});
        

        
  </script>



    

</body>

</html>