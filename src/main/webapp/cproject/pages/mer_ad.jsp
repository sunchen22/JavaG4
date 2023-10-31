<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.dinerinfo.dao.*"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%@ page import="com.advertisement.entity.*"%>
<%@ page import="com.advertisement.dao.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>商家廣告審核</title>

  
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
    
    <%
				
		
				AdvertisementDAOHibernateImplC ac = new AdvertisementDAOHibernateImplC();
				List<Advertisement> list = ac.getAllApprovedAD();
				pageContext.setAttribute("list", list);
				
				List<Advertisement> list1 = ac.getAllSubmittedAD();
				pageContext.setAttribute("list1", list1);
 			%>
    


	
    <div class="content-wrapper">
   
      <section class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              
            </div>

          </div>
        </div>
        
        
        
        
        <p style="color: red">已審核</p>
        <div class="">
          <table id = "table" class="table table-bordered">
            <thead>
            
              <tr>
              
              	<th class="w-1">商家編號</th>
                <th>商家帳號</th>
                <th>商家名稱</th>                      				                
                <th>審核狀態</th>
                <th>廣告名稱</th>
                <th>上架時間</th>
                <th>下架時間</th>
                <th>圖片</th>
                

              </tr>
            </thead>
            
            <tbody>
            <c:forEach var="advertisement" items="${list}">
              <tr>
                <td><span class="text-muted">${advertisement.dinerid.dinerID}</span></td>
                <td>${advertisement.dinerid.dinerTaxID}</td>
                <td>
                  ${advertisement.dinerid.dinerName}
                </td>
                
                <td>
                  ${advertisement.advertisementStatus eq 'Approved' ? '已審核' : '申請中'}
                  
                </td>
                
				<td>
				  ${advertisement.advertisementName}
				</td>
				
				<td>
				<fmt:formatDate value="${advertisement.advertisementUpTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				  
				</td>
				<td>
				<fmt:formatDate value="${advertisement.advertisementDownTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				  
				</td>
				
				<td>
				<form method = "post" action = "<%=request.getContextPath()%>/cproject/pages/adc.do">
                <input type="hidden" name="advertisementID" value = "${advertisement.advertisementID}">
				<button type = "submit" class="btn btn-warning" style="font-weight :bold">查詢</button>
                </form>
				
				</td>
				
    

              </tr>
			  </c:forEach>
			  
            </tbody>
          </table>
          </div>
          
          <br>
          
          
          <p style="color: red">待審核</p>
          <div class="">
          <table id = "table1" class="table table-bordered">
            <thead>
            
              <tr>
              
              	<th class="w-1">商家編號</th>
                <th>商家帳號</th>
                <th>商家名稱</th>                      				                
                <th>審核狀態</th>
                <th>廣告名稱</th>
                <th>上架時間</th>
                <th>下架時間</th>
                <th>圖片</th>
                
				<th></th>
				<th></th>
              </tr>
            </thead>
            
            <tbody>
            <c:forEach var="advertisement" items="${list1}">
              <tr>
                <td><span class="text-muted">${advertisement.dinerid.dinerID}</span></td>
                <td>${advertisement.dinerid.dinerTaxID}</td>
                <td>
                  ${advertisement.dinerid.dinerName}
                </td>
                
                <td>
                  ${advertisement.advertisementStatus eq 'Approved' ? '已審核' : '申請中'}
                </td>
                <td>
                	${advertisement.advertisementName}
                </td>
				 <td>
				 <fmt:formatDate value="${advertisement.advertisementUpTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				 
                </td>
                 <td>
                 <fmt:formatDate value="${advertisement.advertisementDownTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                 
                </td>
                <td>
                <form method = "post" action = "<%=request.getContextPath()%>/cproject/pages/adc.do">
                <input type="hidden" name="advertisementID" value = "${advertisement.advertisementID}">
				<button type = "submit" class="btn btn-warning" style="font-weight :bold">查詢</button>
                </form>
                </td>
                
                
				
				<td>
				<form method = "post" action = "<%=request.getContextPath()%>/cproject/pages/ads.do">
				<input type="hidden" name="action" value = "go_for_check">
				<input type="hidden" name="check" value = "${advertisement.advertisementID}">
				<button id = "click1" type = "submit" class="btn btn-warning" style="font-weight :bold">審核</button>
				</form>
				</td>
				
				<td>
				<form method = "post" action = "<%=request.getContextPath()%>/cproject/pages/ads.do">
				<input type="hidden" name="action" value = "go_for_rejected">
				<input type="hidden" name="check" value = "${advertisement.dinerid.dinerID}">
				<button id = "click2 " type = "submit" class="btn btn-danger" style="font-weight :bold">拒絕</button>
				</form>
				</td>
    

              </tr>
			  </c:forEach>
			  
            </tbody>
          </table>
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
  
  <script src="<%=request.getContextPath()%>/cproject/dist/js/pages/dashboard3.js"></script>
  

	<%@ include  file="../../background/pages/pagejs.file" %>

<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<!--   <link rel="stylesheet" -->
<!--     href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext"> -->
  
  <%@ include file="included-fragment.file" %>
		<script src="https://code.jquery.com/jquery-3.5.1.js"></script>                                    <!-- ●●js  for jquery datatables 用 -->
		<script	src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>              <!-- ●●js  for jquery datatables 用 -->
		<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/dataTables.jqueryui.min.css" /> <!-- ●●css for jquery datatables 用 -->
		
      <script>
             
       
      
	  
     
      
      
      
      $(document).ready(function() {   	  
    	  
  		$('#table').DataTable({
  			"lengthMenu": [3],
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
  		
  		
  		
  		
  		$('#table1').DataTable({
			"lengthMenu": [3],
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
  		
  		$("button[id^='click1']").click(function() {
  	        alert("已發送email");
  	    });
  		
  		$("button[id^='click2']").click(function() {
  	        alert("已發送email");
  	    });
  		

  		
  		
  	});
      
    
      
      </script>

</body>

</html>