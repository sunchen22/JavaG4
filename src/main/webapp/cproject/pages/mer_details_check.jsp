<%@page import="org.json.JSONArray"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.dinerinfo.dao.*"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%@ page import="org.json.JSONObject" %>
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
   
    <aside class="main-sidebar sidebar-light-warning elevation-4">
      
      <a href="<%=request.getContextPath()%>/index.jsp" class="brand-link">
        <img src="<%=request.getContextPath()%>/cproject/dist/img/Logo.png" alt="樓頂揪樓咖 Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
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
            
<%@ include  file="pageaside.file" %>
              </ul>
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

      
      <section class="content">
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



                    
                  </div>
                </div>
                

				<%
					DinerInfo dif = (DinerInfo)request.getAttribute("dif");
				
					String dinerType = dif.getDinerType();
	              	if(dif.getDinerType().equals("M")){
	              		dinerType = "單純餐點";
	              	}else if(dif.getDinerType().equals("D")){
	              		dinerType = "單純飲料";
	              	}else{
	              		dinerType = "複合";
	              	}
 					
					
// 					JSONObject j = new JSONObject(dif.getDinerUpdate());
					
// 					j.get("a").toString() 下面的value
					
					
					
				%>



                <form  method = "post" action="" style="padding:10px;">


                  <div style="display: flex">

                      <div style="width: 50%;">

                          <span>
                              <label>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;舊資料</label>

                          </span>
                          <br>

                          <span>
                              <label>品牌名稱：</label>
                              <input type="text" value = "<%= dif.getDinerName() %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
							
							<span>
                              <label>密碼：&ensp;&ensp;&ensp;&ensp;</label>
                              <input type="text" value = "<%= dif.getDinerPassword() %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                          <span>
                              <label>商家帳號：</label>
                              <input type="text" value = "<%= dif.getDinerTaxID() %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                          <span>
                              <label>聯絡人：&ensp;&ensp;</label>
                              <input type="text" value = "<%= dif.getDinerContact() %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                          <span>
                              <label>電話：&ensp;&ensp;&ensp;&ensp;</label>
                              <input type="text" value = "<%= dif.getDinerPhone() %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>e-mail ：&emsp;</label>
                              <input type="text" value = "<%= dif.getDinerEmail() %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                          <span>
                              <label>地址：&ensp;&ensp;&ensp;&ensp;</label>
                              <input type="text" value = "<%= dif.getDinerAddress() %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                          
                          <span>
                              <label>銀行代號：</label>
                              <input type="text" value = "<%= dif.getDinerBank() %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                          <span>
                           <label>銀行帳號：</label>
                           <input type="text" value = "<%= dif.getDinerAccount() %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                          <span>
                              <label>戶名：&ensp;&ensp;&ensp;&ensp;</label>
                              <input type="text" value = "<%= dif.getDinerAccountName() %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                          <span>
                              <label>販賣類型：</label>
                              <input type="text" value = "<%= dinerType %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                          
                          
                          
                          
                          
                          <br>
                          <br>

                      </div>

                      <div style="width: 50%;">
                          <span>
                              <label>&ensp;欲修改資料</label>
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = ""class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                      </div>
                  </div>

                  <div align="right" style="margin-right: 20px;">
                   





                     
                      <button type="button" id="stop" class="btn btn-warning" style="font-weight :bold"
                          data-bs-toggle="modal" data-bs-target="#exampleModal">
                          審核
                      </button>

                      <button type="button" id="product" class="btn btn-warning" style="font-weight :bold"
                          data-bs-toggle="modal" data-bs-target="#exampleModal">
                          拒絕
                      </button>

                  </div>







                 
                  <div class="modal fade" id="exampleModal" tabindex="-1"
                      aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog">
                          <div class="modal-content">
                              <div class="modal-header">
                                  <h5 class="modal-title" id="exampleModalLabel">提示</h5>
                                  <button type="button" class="btn-close" data-bs-dismiss="modal"
                                      aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                                  <p style="text-align: center; font-size: 20px;">確定要審核/拒絕嗎</p>
                                  <div align="center">
                                      <input type="text" placeholder="欲拒絕請輸入原因:" id="msg"
                                          style="width: 80%">
                                  </div>
                              </div>


                              <div class="modal-footer">
                                  <button type="button" id="stop1" class="btn btn-warning"
                                      data-bs-dismiss="modal">確定</button>
                                  <button type="button" class="btn btn-secondary"
                                      data-bs-dismiss="modal">取消</button>

                              </div>
                          </div>
                      </div>
                  </div>


              </form>
               
  
  
  
                  <div id="interactive" style="height: 20px;"></div>
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
      
      <script src="<%=request.getContextPath()%>/cproject/plugins/chart.js/Chart.min.js"></script>
     
      <script src="<%=request.getContextPath()%>/cproject/dist/js/pages/dashboard3.js"></script>
     
      <%@ include  file="pagejs.file" %>
      
      
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
    
      
    
<!--       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<!--       <link rel="stylesheet" -->
<!--         href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext"> -->
<!--       <script src="./assets/js/require.min.js"></script> -->

    

</body>

</html>