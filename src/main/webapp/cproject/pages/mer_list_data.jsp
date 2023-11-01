<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.dinerinfo.dao.*"%>
<%@ page import="com.dinerinfo.entity.*"%>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>商家列表</title>

  
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
              <h1> </h1>
            </div>

          </div>
        </div>
      </section>

   
		
      <div class="container-fluid">
        <div class="row">
          <div class="col-10">
            <!-- interactive chart -->
            <div class="card card-warning card-outline">
              <div class="card-header">
                <h3 class="card-title">
                  商家資料
                </h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                  </button>
                </div>
              </div>
              <div id="interactive" style="height: 10px;"></div>

              
              
              <%
				

				DinerInfo dif =  (DinerInfo)request.getAttribute("dif");
              	String dinerType = dif.getDinerType();
              	if(dif.getDinerType().equals("M")){
              		dinerType = "單純餐點";
              	}else if(dif.getDinerType().equals("D")){
              		dinerType = "單純飲料";
              	}else{
              		dinerType = "複合";
              	}
		
 			%>
              
              
              
              
              
              
 			
				<span>
                  <label>&nbsp;品牌名稱：</label>
                  <input type="text" value ="<%= dif.getDinerName() %>" class="border border-warning" style="width :50%">
                </span>
                
                <span>
                  <label>&nbsp;密碼：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="text" value ="<%= dif.getDinerPassword() %>" class="border border-warning" style="width :50%">
                </span>
				
				<span>
                  <label>&nbsp;註冊時間：</label>
                  <input type="text" value ="<%= dif.getDinerRegisterTime() %>" class="border border-warning" style="width :50%">
                </span>
				
				<span>
                  <label>&nbsp;商家帳號：</label>
                  <input type="text" value ="<%= dif.getDinerTaxID() %>" class="border border-warning" style="width :50%">
                </span>
				
				<span>
                  <label>&nbsp;聯絡人：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="text" value ="<%= dif.getDinerContact() %>" class="border border-warning" style="width :50%">
                </span>
				
                <span>
                  <label>&nbsp;電話：&ensp;&ensp;&ensp;&ensp;</label>
                  <input type="text" value ="<%= dif.getDinerPhone() %>" class="border border-warning" style="width :50%">
                </span>
                
                
                <span>
                  <label>&nbsp;e-mail ：&emsp;</label>
                  <input type="text" value ="<%= dif.getDinerEmail() %>" class="border border-warning" style="width :50%">
                </span>
                
                <span>
                  <label>&nbsp;地址：&ensp;&ensp;&ensp;&ensp;</label>
                  <input type="text" value ="<%= dif.getDinerAddress() %>" class="border border-warning" style="width :50%">
                </span>
                
                <span>
                  <label>&nbsp;銀行代號：</label>
                  <input type="text" value ="<%= dif.getDinerBank() %>" class="border border-warning" style="width :50%">
                </span>
                
                <span>
                  <label>&nbsp;銀行帳號：</label>
                  <input type="text" value ="<%= dif.getDinerAccount() %>" class="border border-warning" style="width :50%">
                </span>
                
                <span>
                  <label>&nbsp;戶名：&ensp;&ensp;&ensp;&ensp;</label>
                  <input type="text" value ="<%= dif.getDinerAccountName() %>" class="border border-warning" style="width :50%">
                </span>
                
                <span>
                  <label>&nbsp;販賣類型：</label>
                  <input type="text" value ="<%= dinerType  %>" class="border border-warning" style="width :50%">
                </span>
                <br>



  <div align="right" style="margin-right: 10px; margin-bottom: 15px; display: flex; justify-content: flex-end;">
 	<form method="post" action ="<%=request.getContextPath()%>/cproject/pages/difs.do" style="margin-left: 5px;">
  <button id = "click" type="submit" class="btn btn-danger" style="font-weight: bold; margin-left: 5px;">
    停權</button>
  	<input type="hidden" name="action" value="go_for_deactivated">
    <input type="hidden" name="dinerID" value="<%= dif.getDinerID()%>">
  </form>
  
  
  <form method="post" action="<%=request.getContextPath()%>/cproject/pages/pdsc.do" style="margin-left: 5px;">
    <button type="submit" class="btn btn-warning" style="font-weight: bold;">
    商品審核</button>
    <input type="hidden" name="action" value="go_for_product_check">
    <input type="hidden" name="dinerID" value="<%= dif.getDinerID()%>">
  </form>
  
  
  <form  method="post" action ="<%=request.getContextPath()%>/cproject/pages/difs.do">
  <button type="submit" id="payment" class="btn btn-warning" style="font-weight: bold; margin-left: 5px;">
    金流圖表</button>
  	<input type="hidden" name="action" value="go_for_payment">
    <input type="hidden" name="dinerID" value="<%= dif.getDinerID()%>">
  </form>
</div>

              
  
       
          </div>
          
        </div>
        

      </div>
      
    </div>
    
    
    

  </div>


  

<div>
  
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
</div>


  
  <script src="<%=request.getContextPath()%>/cproject/plugins/jquery/jquery.min.js"></script>
  
  <script src="<%=request.getContextPath()%>/cproject/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>


  
  <script src="<%=request.getContextPath()%>/cproject/dist/js/adminlte.js"></script>
  

  <%@ include  file="../../background/pages/pagejs.file" %>
  



 <script>
	$("#click").on("click",function(){
		alert("已發送email");
	});

</script>

</body>

</html>