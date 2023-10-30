<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
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


</head>


<body class="hold-transition sidebar-mini">
  <div class="wrapper">
    
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
      
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>

        </li>

        <li class="nav-item d-none d-sm-inline-block ">
          <a href="<%=request.getContextPath()%>/index.jsp" class="nav-link">管理者管理首頁</a>
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
   
    <aside class="main-sidebar sidebar-light-warning elevation-4">
      
      <a href="<%=request.getContextPath()%>/index.jsp" class="brand-link">
        <img src="<%=request.getContextPath()%>/cproject//dist/img/Logo.png" alt="樓頂揪樓咖 Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-normal">後台管理平台</span>
      </a>

     
      <div class="sidebar">
        
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
          <div class="image">
            <img src="<%=request.getContextPath()%>/cproject/dist/img/emp01.png" class="img-circle elevation-2" alt="emp01">
          </div>
          <div class="info">
            <a href="#" class="d-block">小丸子</a>
          </div>
        </div>

       
        <nav class="mt-2">
          <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
            
<%@ include  file="./background/pages/pageaside.file" %>
              </ul>
              
              
              
            
        </nav>
       
      </div>
      
    </aside>
    
    

   
    <div class="content-wrapper">
      
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
             
            </div>
          </div>
        </div>
      </div>
      
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
  
  <%@ include  file="/background/pages/pagejs.file" %>
</body>

</html>