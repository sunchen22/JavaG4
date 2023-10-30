<%@page import="com.buildinginfo.entity.BuildingInfo"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.buildinginfo.dao.*"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>樓頂揪樓咖後台管理</title>

  
  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  
  <link rel="stylesheet" href="<%=request.getContextPath()%>/cproject/plugins/fontawesome-free/css/all.min.css">
  
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
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
              
              <div class="card card-warning card-outline">
                <div class="card-header">
                   
                </div>
                <div id="interactive" style="height: 10px;"></div>
             
                <form method ="post" action="<%=request.getContextPath()%>/cproject/pages/bis.do" style="padding:10px;">
					
                <!--錯誤表列 -->
				<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
	   			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
				</c:forEach>
				</ul>
				</c:if>
					
				
                  <span>
                    <label>大樓編號：</label>
                    <input type="text"  name = "bdgname" class="border border-warning">
                    &ensp;
                  </span>

				  <input type="hidden" name="action" value="get_Data_Display">
                  <span>
                    <button type="submit" value = "送出" style="font-weight:bold" class="btn btn-warning">查詢</button>
                  </span>
                </form>
 
        </div>
        </div>
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
      <script src="<%=request.getContextPath()%>/cproject/dist/js/pages/dashboard3.js"></script>
      
      
 <!--      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" -->
<!--     integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" -->
<!--     crossorigin="anonymous"></script> -->
    
<!--       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<!--       <link rel="stylesheet" -->
<!--         href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext"> -->


	<%@ include  file="../../background/pages/pagejs.file" %>
      <script>
        

        
  </script>

</body>

</html>