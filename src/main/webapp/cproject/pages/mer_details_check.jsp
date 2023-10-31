<%@page import="org.hibernate.internal.build.AllowSysOut"%>
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
  <title>商家資料異動</title>

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

      
      <section class="content">
        <div class="container-fluid">
          <div class="row">
            <div class="col-10">
             
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
	              	}else if(dif.getDinerType().equals("D")){  //抓現在的資料是什麼做文字轉換
	              		dinerType = "單純飲料";
	              	}else if(dif.getDinerType().equals("X")){
	              		dinerType = "餐點飲料複合";
	              	}
 					
					
					JSONObject j = new JSONObject(dif.getDinerUpdate());
					
					String dinerName = j.optString("dinerName", " ");
					String dinerPassword = j.optString("dinerPassword", " ");
					String dinerTaxID = j.optString("dinerTaxID", " ");
					String dinerContact = j.optString("dinerContact", " ");
					String dinerPhone = j.optString("dinerPhone", " ");
					String dinerEmail = j.optString("dinerEmail", " ");
					String dinerAddress = j.optString("dinerAddress", " ");
					String dinerBank = j.optString("dinerBank", " ");
					String dinerAccount = j.optString("dinerAccount", " ");
					String dinerAccountName = j.optString("dinerAccountName", " ");
					String dinerTypeJ = j.optString("dinerType", " ");
					
					//如果抓不到json的name 顯示 "" 代表沒變更
					
					
	              	if(dinerTypeJ.equals("M")){  //dinerType有變更 做文字轉換的顯示
	              		dinerTypeJ = "單純餐點";
	              	}else if(dinerTypeJ.equals("D")){
	              		dinerTypeJ = "單純飲料";
	              	}else if(dinerTypeJ.equals("X")){
	              		dinerTypeJ = "餐點飲料複合";
	              	}
	              	
	              	
// 	              	if(dinerTypeJ.equals(" ")){
	              		
// 	              		dinerTypeJ = dif.getDinerType();
// 	              	}
	              	
// 					out.write(dinerTypeJ);
					
					
				%>



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
                              <input type="text" value = "<%= dinerName %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = "<%= dinerPassword %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = "<%= dinerTaxID %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = "<%= dinerContact %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = "<%= dinerPhone %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = "<%= dinerEmail %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = "<%= dinerAddress %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = "<%= dinerBank %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = "<%= dinerAccount %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = "<%= dinerAccountName %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          <span>
                              <label>&ensp;</label>
                              <input type="text" value = "<%= dinerTypeJ %>" class="border border-warning" style="width :60%">
                          </span>
                          <br>
                          
                          
                      </div>
                      
                      
                      
                      
                  </div>
					<div align="right" style=" margin-top: 20px ;margin-right: 10px ;">
                   	  
       					
                      <button type="submit" id="click" class="btn btn-warning" style="font-weight :bold">
                          審核
                      </button>
                      
						&ensp;
						
                      <button type="submit" id="click2" class="btn btn-danger" style="font-weight :bold"                          >
                      
                          拒絕
                      </button>
					
                 	 </div>
                  

  
  
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
     
      <%@ include  file="../../background/pages/pagejs.file" %>
      
      


<script>

$("#click").on("click",function(){
	
var dinerID = "<%= dif.getDinerID() %>"
var dinerName = "<%= dinerName %>";
var dinerPassword = "<%= dinerPassword %>";
var dinerTaxID = "<%= dinerTaxID %>";
var dinerContact = "<%= dinerContact %>";
var dinerPhone =  "<%= dinerPhone %>";
var dinerEmail = "<%= dinerEmail %>";
var dinerAddress = "<%= dinerAddress %>";
var dinerBank = "<%= dinerBank %>";
var dinerAccount = "<%= dinerAccount %>";
var dinerAccountName = "<%= dinerAccountName %>";
var dinerTypeJ = "<%= dinerTypeJ %>";

// console.log(dinerTypeJ);

var modifiedData = {};
	
	modifiedData.dinerID = '<%= dif.getDinerID() %>';

if(dinerName === " "){
	modifiedData.dinerName = '<%= dif.getDinerName() %>';
}	
else if(dinerName !==  '<%= dif.getDinerName() %>'){
    modifiedData.dinerName = dinerName;

}


if(dinerPassword === " "){
	modifiedData.dinerPassword = "<%= dif.getDinerPassword() %>";
}	
else if(dinerPassword !== '<%= dif.getDinerPassword() %>') {
    modifiedData.dinerPassword = dinerPassword;

}


if(dinerTaxID === " "){
	modifiedData.dinerTaxID = '<%= dif.getDinerTaxID() %>';
}
else if(dinerTaxID !== '<%= dif.getDinerTaxID() %>') {
    modifiedData.dinerTaxID = dinerTaxID;
}

if(dinerContact === " "){
	modifiedData.dinerContact = '<%= dif.getDinerContact() %>';
}
else if(dinerContact !== '<%= dif.getDinerContact() %>') {
    modifiedData.dinerContact = dinerContact;
}

if(dinerPhone === " "){
	modifiedData.dinerPhone = '<%= dif.getDinerPhone() %>';
}
else if(dinerPhone !== '<%= dif.getDinerPhone() %>') {
    modifiedData.dinerPhone = dinerPhone;
}


if(dinerEmail === " "){
	modifiedData.dinerEmail = '<%= dif.getDinerEmail() %>';
	
}
else if(dinerEmail !== '<%= dif.getDinerEmail() %>') {
    modifiedData.dinerEmail = dinerEmail;
}


if(dinerAddress === " "){
	modifiedData.dinerAddress = '<%= dif.getDinerAddress() %>';
}
else if(dinerAddress !== '<%= dif.getDinerAddress() %>') {
    modifiedData.dinerAddress = dinerAddress;
}


if(dinerBank === " "){
	modifiedData.dinerBank = '<%= dif.getDinerBank() %>';
}
else if(dinerBank !== '<%= dif.getDinerBank() %>') {
    modifiedData.dinerBank = dinerBank;
}


if(dinerAccount === " "){
	modifiedData.dinerAccount = '<%= dif.getDinerAccount() %>';
}
else if(dinerAccount !== '<%= dif.getDinerAccount() %>') {
    modifiedData.dinerAccount = dinerAccount;
}


if(dinerAccountName === " "){
	modifiedData.dinerAccountName = '<%= dif.getDinerAccountName() %>';
	
}
else if(dinerAccountName !== '<%= dif.getDinerAccountName() %>') {
    modifiedData.dinerAccountName = dinerAccountName;
}

if(dinerTypeJ === " "){
	
	if('<%= dinerType %>' == "單純餐點"){
		modifiedData.dinerTypeJ = 'M';
		
	}else if('<%= dinerType %>' === "單純飲料"){
			modifiedData.dinerTypeJ = 'D';
			console.log("CCCCCCC");
	}else if('<%= dinerType %>' === "餐點飲料複合"){
		modifiedData.dinerTypeJ = 'X';
		console.log("ddddddddd");
	}
	
	
}
else if(dinerTypeJ !== '<%= dinerType %>') {
	if(dinerTypeJ === "單純餐點"){
			modifiedData.dinerTypeJ = 'M';
		}else if(dinerTypeJ === "單純飲料"){
				modifiedData.dinerTypeJ = 'D';
		}else if(dinerTypeJ === "餐點飲料複合"){
			modifiedData.dinerTypeJ = 'X';
		}
}

console.log(modifiedData);

var go = window.location.origin;
$.ajax({
    type: "POST",
    url: go + "/JavaG4/cproject/pages/difs.do", // 
    data: JSON.stringify(modifiedData), // 將JSON轉成String
    contentType: "text/plain", 
    success: function(response) {
    	window.location.href = go + "/JavaG4/cproject/pages/mer_details.jsp";
        console.log("成功：" + response);
    },
    error: function(xhr, status, error) {
        
        console.error("錯誤：" + error);
    }
});

alert("已發送email");

});

$("#click2").on("click", function() {
	var go = window.location.origin;
    console.log("aaaa");
    var dinerID = '<%= dif.getDinerID() %>';
    var go_for_deactivatedJ = "go_for_deactivatedJ";
    $.ajax({
        type: "POST",
        url: go + "/JavaG4/cproject/pages/difs.do",
        data: {
            dinerID: dinerID,
            action: "go_for_deactivatedJ"
        },
        success: function(response) {
            window.location.href = go + "/JavaG4/cproject/pages/mer_details.jsp";
            console.log("成功：" + response);
        },
        error: function(xhr, status, error) {
            console.error("錯誤：" + error);
        }
    });
    
    alert("已發送email");
});



</script>


</body>

</html>