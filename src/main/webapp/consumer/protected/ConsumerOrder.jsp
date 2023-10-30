<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../components/head.jsp"%>
<%@ page import="com.userorderdetail.dao.*"%>
<%@ page import="com.userinfo.entity.*"%>
<%@ page import="java.util.*"%>
<%-- Import CSS for this page below (if any) --%>



<title>樓頂揪樓咖-消費者</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="../components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<div class="container mt-4 mb-5 col-8 shadow rounded p-2">
		<h1 class="text-center">訂單</h1>



		<!-- 現在訂單區 -->
		<h2>訂單明細</h2>

<%
UserInfo userInfo = (UserInfo) session.getAttribute("loginUserInfo");
Integer userID = userInfo.getUserID(); 
UserOrderDetailDAO_Tz dao = new UserOrderDetailDAO_Tz();
List<Integer> groupOrdersID = dao.getUserGroupOrdersID(userID);
Map<Integer, List<Object[]>> allDetails = new HashMap<>();
Object[] firstDetail;

if (groupOrdersID != null) {
	for (Integer order : groupOrdersID) {
		List<Object[]> orderDetailsList  = dao.getUserOrderDetails(userID, order);
		if(orderDetailsList != null && !orderDetailsList.isEmpty()) {
			  firstDetail = orderDetailsList.get(0);
			
%>
		<div
			class="row d-flex justify-content-around row border border-dark p-1 rounded bg-warning bg-gradient mx-1 mb-2">
			<div class="col">
				<p>
					訂單: <span><%= order %></span>
				</p>
				<p><%= firstDetail[1] %></p>
				<p><%= firstDetail[3] %></p>
			</div>

			<div class="col-4">
<% 
			for(Object[] details : orderDetailsList){
			%>
				<div class="row">
					<span class="col"><%= details[5] %></span> 
					<span class="col">$<%= details[6] %></span>
					<span class="col"><%= details[8] %>份</span> 
				</div>
				<% if(details[10]!=null){ %>
				<div class="row">
					<span class="col"><%= details[10] %></span>
					<span class="col">+ <%= details[11] %>元</span>
					<span class="col"> </span>
				</div>
				<% } %>
				
				<% if(details[13]!=null){ %>
				<div class="row">
					<span class="col"><%= details[13] %></span>
					<span class="col">+ <%= details[14] %>元</span>
					<span class="col"> </span>
				</div>
				<% } %>
				<% if(details[16]!=null){ %>
				<div class="row">
					<span class="col"><%= details[16] %></span>
					<span class="col">+ <%= details[17] %>元</span>
					<span class="col"> </span>
				</div>
				<% } %>
				<% if(details[19]!=null){ %>
				<div class="row">
					<span class="col"><%= details[19] %></span>
					<span class="col">+ <%= details[20] %>元</span>
					<span class="col"> </span>
				</div>
				<% } %>
				<div class="row">
					<span class="col"> </span>
					<span class="col"> </span>
					<span class="col"> 
<% 
					Integer val1 = (details[6] instanceof Integer) ? (Integer) details[6] : 0;
					Integer val2 = (details[11] instanceof Integer) ? (Integer) details[11] : 0;
					Integer val3 = (details[14] instanceof Integer) ? (Integer) details[14] : 0;
					Integer val4 = (details[17] instanceof Integer) ? (Integer) details[17] : 0;
					Integer val5 = (details[20] instanceof Integer) ? (Integer) details[20] : 0;
					Integer quantity = (details[8] instanceof Integer) ? (Integer) details[8] : 0;  
					Integer subtotal = (val1 + val2 + val3 + val4 + val5) * quantity;
					out.print(subtotal);
%>
				
				元</span>
				</div>
				<div class="row">----------------------------</div>
<%}%>
			</div>

			<div class="col d-flex align-items-center justify-content-center">
<%
  String statusText = "";
  int status;
  if (firstDetail[21] != null) {
	    try {
	      status = Integer.parseInt(firstDetail[21].toString());
	    } catch (NumberFormatException e) {
	      status = -1;  // 轉換失敗時設為-1或其他無效值
	    }
  switch (status) {
    case 1:
      statusText = "揪團已建立";
      break;
    case 2:
      statusText = "成團條件達成";
      break;
    case 3:
      statusText = "揪團成功";
      break;
    case 4:
      statusText = "揪團失敗";
      break;
    case 5:
      statusText = "餐點準備中";
      break;
    case 6:
      statusText = "商家拒單";
      break;
    case 7:
      statusText = "餐點送達";
      break;
    default:
      statusText = "未知狀態";
  }}
%>
				<div
					class="inline-block bg-danger text-white bg-gradient text-center rounded-pill px-5"><%= statusText %></div>
			</div>

			<div class="col-2 d-flex flex-column ">
				<button class="btn btn-outline-success mb-1"
					onclick="window.location.href='${pageContext.request.contextPath}/consumer/protected/DinerComment.jsp?dinerID=2'">評論</button>
				<button class="btn btn-outline-success mb-1"
					onclick="window.location.href='${pageContext.request.contextPath}/consumer/protected/Chatroom.jsp'">聯繫客服</button>
			</div>
		</div>
<% }}} %>



	</div>

	<%-- Page content end --%>

	<jsp:include page="../components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="../components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>
<!-- 	<script src="./vendor/jquery/jquery-3.7.1.min.js"></script> -->
<!-- 	<script src="./vendor/bootstrap-5.3.1-dist/js/bootstrap.bundle.js"></script> -->

</body>
</html>