<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../components/head.jsp" %>
<%-- Import CSS for this page below (if any) --%>
<%@ page import="com.userinfo.controller.*" %>
<%@ page import="com.userinfo.entity.*" %>

<style>
/* 	.messages--received>.message { */
/* 		background-color: #ddd; */
/* 	} */
	
/* 	.messages--sent>.message { */
/* 		background-color: #1998e6; */
/* 		color: white; */
/* 	} */
	li::marker {
	 list-style-type:none;
	}
	
	#row {
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
  background: #ddd;
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
  background: #1998e6;
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


<title>樓頂揪樓咖-線上客服</title>
<%-- Remember to edit the page title --%>
</head>

<body onload="connect();" onunload="disconnect();">
	<jsp:include page="../components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<div class="container col-4">
	<div class="card card-warning direct-chat" >
	
		<div class="card-header" >
                    <h3 class="card-title"  ><i class="nav-icon fas fa-comment"></i>&nbsp;聊天室</h3>
                    <h3 class="card-title"  id="statusOutput" ></h3> 	
                    <div id="row">點擊後開始聊天</div>
         </div>
         
		 <div class="card-body"  style="height: 300px; overflow-y: scroll;">
			<div id="messagesArea" class="direct-chat-messages" ></div>
		 </div>

			<div class="card-footer ">
		        <div class="input-group">
					<input id="message" class="form-control"  type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
					<input type="submit"  id="sendMessage" class="btn btn-primary"  value="Send" onclick="sendMessage();" /> 
			<!-- 		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" />  -->
			<!-- 		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" /> -->
				</div>
			</div>
 	 </div>
    </div>



	<%-- Page content end --%>

	<jsp:include page="../components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="../components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>


  <!-- ===========消費者聊天室=========== -->
<%
    UserInfo loginUserInfo = (UserInfo)session.getAttribute("loginUserInfo");
	String userName = null;
    if (loginUserInfo != null) {
    	userName = loginUserInfo.getUserName();
    	System.out.println("this is name = "+userName);
    	System.out.println("我在JSP 有拿到loginUserInfo");
    }
%>

<script>
	var userName = '<%= userName %>';
	var MyPoint = "/FriendWS/"+userName;
	console.log(MyPoint);
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
// 	var self = '${userName}';
	var self = userName;
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
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}

	
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
				"timestamp": timestamp
			};
			webSocket.send(JSON.stringify(jsonObj));  //傳送至websocket OnMessage
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var row = document.getElementById("row");
		row.innerHTML = 'cs';
// 		for (var i = 0; i < friends.length; i++) {
// 			if (friends[i] === self) { continue; }  //跳過自己
// 			row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
// 		}
		addListener();
	}
	// 註冊列表點擊事件,並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent; //抓取點擊對象
			updateFriendName(friend);
			var jsonObj = {           //這個去抓資料庫內的東西
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