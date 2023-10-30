<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.webempadmin.entity.*" %>
<%@ page import="com.background.*" %>

<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>樓頂揪樓咖後台管理</title>


  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/background/plugins/fontawesome-free/css/all.min.css">
  <!-- IonIcons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/background/dist/css/adminlte.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/background/plugins/fontawesome-free\css\fontawesome.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/background/plugins/fontawesome-free\css\fontawesome.css">

<style>
li::marker {
 list-style-type:none;
}

#frow {
	float: left;
	width: 100%;
	padding-left:10px;
	padding-top:10px;
}
.contact-list{
	overflow: hidden;
    border-bottom: 1px solid #ccc;
    padding: 0 0 20px;
}

.friend{
  position: relative;
  display: online;
  clear: both;
  padding: 5px;
  border-radius: 20px;
  margin-bottom: 20px;
  font-family: Helvetica, Arial, sans-serif;
  background: #eee;
  float: left;
  list-style-type:none;
}

.me{
  position: relative;
  display: online;
  clear: both;
  padding: 5px;
  border-radius: 20px;
  margin-bottom: 20px;
  font-family: Helvetica, Arial, sans-serif;
  float: right;
  background: #ffc107;
  color: #fff;
}

.timedivfriend{
  position: absolute;
  display: online-block;
  clear: both;
  padding: 5px;
  font-family: Helvetica, Arial, sans-serif;
  left:0;
  top: 100%;
  color: #92959E; 
  font-size: 9px;
  white-space:nowrap;
  list-style-type:none;
}

.timedivme{
  position: absolute;
  display: online-block;
  clear: both;
  padding: 5px;
  font-family: Helvetica, Arial, sans-serif;
  right:0;
  top: 100%;
  color: #92959E; 
  font-size: 9px;
  white-space:nowrap;
}

</style>

</head>

<body class="hold-transition sidebar-mini" onload="connect();" onunload="disconnect();">
  <div class="wrapper">
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
      <!-- Left navbar links -->
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" data-widget="pushmenu" href="#" role="button" onclick="toggleLogoutButton()"><i class="fas fa-bars"></i></a>
        </li>

        <li class="nav-item d-none d-sm-inline-block ">
          <a href="svr_cust.jsp" class="nav-link">線上客服訊息管理</a>
        </li>
      </ul>

      <!-- Right navbar links -->
      <ul class="navbar-nav ml-auto">

        <li class="nav-item">
          <a class="nav-link"  href="${pageContext.request.contextPath}/background/pages/index3.jsp" role="button">
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
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->

 <!-- 引入側邊欄 -->
<%@ include  file="pageaside.file" %>

        </nav>
        <!-- /.sidebar-menu -->
      </div>
      <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <h1> </h1>
            </div>

          </div>
        </div>
      </section>

      <!-- Main content -->


      <!-- 線上聊天列表 style="margin-left: 12px-->
      <section >
        <div class="card">
          <!-- <div class="card-header">

          </div> -->
          <!-- /.card-header end -->

          <div class="card-body">
            <div class="row">

              <div class="col-12 col-md-6 col-lg-6 order-1 order-md-1" ;>
				<!-- 客服列表頭 -->
                  		<div class="card-header" >
                   			 <h3 class="card-title"  ><i class="fa fa-list"></i>&nbsp;目前線上會員列表</h3>
                  		</div>
 <!-- ===========客服列表=========== -->
                <div class="direct-chat-contacts-open" >
                 	<div class="contacts-list" >
                		<ul id="frow">

						</ul>
 					</div>
 				
                </div> 
				<!--  direct-chat-end -->
              </div>
              <!-- <col12-end> -->


              <div class="col-12 col-md-6 col-lg-6 order-2 order-md-2">
              
<!-- ===========DIRECT CHAT WARNING /一對一的談話框 ===========-->
                <div class="card card-warning direct-chat" >
                  <div class="card-header" >
                    <h3 class="card-title"  ><i class="nav-icon fas fa-comment"></i>&nbsp;聊天室</h3>
                    <br>
                    <h3 class="card-title"  id="statusOutput" ></h3>   	
                  </div>
                  
                  <div class="card-body" >
                    <div class="direct-chat-messages" id="messagesArea">
                    </div>
                    
					<!--  訊息發送按鈕、輸入文字等 -->
                    <div class="card-footer">
                        <div class="input-group">
                          <input type="text" name="message" id="message" placeholder="Aa..." class="form-control" onkeydown="if (event.keyCode == 13) sendMessage();">
                          <input type="submit" id="sendMessage" class="btn btn-warning" value="send" onclick="sendMessage();">
<!-- 							<input type="button" id="disconnect" class="btn btn-warning" value="Disconnect" onclick="disconnect();" /> -->
                        </div>
                    </div>
                    <!--/.direct-chat -->
                  </div>
                  <!-- /.col -->

                </div>
                <!-- row-end -->



              </div>
              <!-- card-body-end -->

            </div>
            <!-- /.card end -->

      </section>
    </div>
    <!-- /.content-wrapper -->






    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-warning">
      <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->

    <!-- Main Footer -->
    <footer class="main-footer">
      <strong>Copyright &copy; 2023</strong>
      樓頂揪樓咖團隊 All rights reserved.
      <div class="float-right d-none d-sm-inline-block">
        <b>Version</b> 1.1.0
      </div>
    </footer>
  </div>
  <!-- ./wrapper -->

  <!-- REQUIRED SCRIPTS -->

  <!-- jQuery -->
  <script src="${pageContext.request.contextPath}/background/plugins/jquery/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="${pageContext.request.contextPath}/background/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- AdminLTE -->
  <script src="${pageContext.request.contextPath}/background/dist/js/adminlte.js"></script>
  <!-- OPTIONAL SCRIPTS -->
  <script src="${pageContext.request.contextPath}/background/plugins/chart.js/Chart.min.js"></script>


<!-- ===========聊天室=========== -->
<%
    String cs = (String)session.getAttribute("account");
    if (cs != null) {
    	System.out.println("this is name = "+cs);
    	System.out.println("我在JSP 有拿到cs的名字");
    }
%>

<script>
// 	var MyPoint = "/FriendWS/${userName}"; 
	var MyPoint = "/FriendWS/cs";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
// 	var self = '${userName}'; 
	var self = 'cs';  //test
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};
		
		//接收傳入的資料
		webSocket.onmessage = function(event) {  
			var jsonObj = JSON.parse(event.data); 
			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;  //這裡連前面的歷史訊息都改變???
					var li = document.createElement('li');
							var div = document.createElement('div');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me' : li.className += 'friend';
					historyData.sender === self ? div.className += 'timedivme' : div.className += 'timedivfriend';
// 							div.className +='timediv';
							div.textContent = historyData.timestamp;
							li.appendChild(div);
					li.innerHTML = showMsg;
					ul.appendChild(li); //ul來結尾

				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {       //預設
				//判斷收件是否是自己、對方是自己
// 				if( jsonObj.sender === friend && jsonObj.receiver === self){
// 					if( jsonObj.sender === friend ||  jsonObj.sender === self){
						var li = document.createElement('li');
								var div = document.createElement('div');
						jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
						jsonObj.sender  === self ? div.className += 'timedivme' : div.className += 'timedivfriend';
						li.innerHTML = jsonObj.message;
						console.log(li);
		// 						div.className +='timediv';
								div.textContent = jsonObj.timestamp;
								li.appendChild(div);
						document.getElementById("area").appendChild(li);
						messagesArea.scrollTop = messagesArea.scrollHeight;
// 				}
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	//按下送出按鈕發動 
	function sendMessage() {    
		var inputMessage = document.getElementById("message");   
		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();
		var time = new Date();
		console.log(time);  //Sat Oct 21 2023 09:51:52 GMT+0800 (台北標準時間)
		var timestamp = time.toLocaleString();
		console.log(timestamp); //2023/10/21 上午9:51:52

		if (message === "") {
			alert("請輸入訊息");
			inputMessage.focus();
		} else if (friend === "") {
			alert("回覆訊息請先選擇一位會員");
		} else {
			var jsonObj = {                               //傳送這包訊息
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message,
				"timestamp": timestamp    //在前端拿現在的時間?
			};
			webSocket.send(JSON.stringify(jsonObj));  //傳送至websocket OnMessage
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) { 
		var friends = jsonObj.users;
		var frow = document.getElementById("frow");
		frow.innerHTML = '';
		for (var i = 0; i < friends.length; i++) {
			if (friends[i] === self) { continue; }
			frow.innerHTML +='<div class="contact-list" id=' + i + ' name="friendName" value=' + friends[i] + ' ><h3 class="contacts-list-name" style="color: black;">' + friends[i] + '</h3></div>';
		}
		addListener();
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("frow");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			var jsonObj = {           //這個去抓資料庫內的東西,但一加上"timestamp": timestamp就壞掉
					"type" : "history",
					"sender" : self,
					"receiver" : friend,
					"message" : "",
					"timestamp": "",
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}
	
	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}
	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>


</body>

</html>