<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="components/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.advertisement.dao.*"%>
<%@ page import="com.advertisement.entity.*"%>
<%@ page import="com.usernews.service.*"%>
<%@ page import="com.usernews.entity.*"%>
<%@ page import="com.grouporder.dao.*"%>
<%@ page import="com.grouporder.entity.*"%>
<%-- Import CSS for this page below (if any) --%>


<title>樓頂揪樓咖-首頁</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<!-- Header-->
	<header class="bg-light">
		<!-- 輪播 carousel -->
		<div id="carouselExampleIndicators" class="carousel slide"
			data-bs-ride="carousel">
<!-- 			<div class="carousel-indicators"> -->
<!-- 				<button type="button" data-bs-target="#carouselExampleIndicators" -->
<!-- 					data-bs-slide-to="0" class="active" aria-current="true" -->
<!-- 					aria-label="Slide 1"></button> -->
<!-- 				<button type="button" data-bs-target="#carouselExampleIndicators" -->
<!-- 					data-bs-slide-to="1" aria-label="Slide 2"></button> -->
<!-- 				<button type="button" data-bs-target="#carouselExampleIndicators" -->
<!-- 					data-bs-slide-to="2" aria-label="Slide 3"></button> -->
<!-- 			</div> -->

			<div class="carousel-inner">
  <% 
  AdvertisementDAO_Tz dao = new AdvertisementDAO_Tz();
  List<Advertisement> adList = dao.getAllApprovedAD();
  if (adList != null) {
	  for (Advertisement ad : adList) { 
		Integer adID = ad.getAdvertisementID(); 
  %>
				<div class="carousel-item <%=(adID.equals(adList.get(0).getAdvertisementID()) ? "active" : "")%>">
					<img
						src="<%=request.getContextPath()%>/consumer/adDBGifReader?adID=<%= adID %>"
						class="d-block w-100" alt="...">
				</div>
  <% 
  	}
  } 
  %>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	</header>

	<!-- 最新消息 -->
	<section class="">
		<div class="container px-4 px-lg-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-1 row-cols-md-1 row-cols-xl-1 justify-content-center mb-3">
<% 
UserNewsService newsServ = new UserNewsServiceImpl();
List<UserNews> newsList = newsServ.getAllUserNewsByStatus();
for(UserNews news : newsList){
%>
				<div class="col mb-1">
					<div class="card">
						<div class="card-body">
							<div class="text-left"><%= news.getUserNewsContent() %></div>
						</div>
					</div>
				</div>
<%} %>
			</div>
		</div>
	</section>

	<!-- Section-->
	<section class="">
		<div class="container px-4 px-md-5">
<% 
	//依據dinerID取得groupOrder List
	GroupOrderDAOHibernateImpl_Tz groupDao = new GroupOrderDAOHibernateImpl_Tz();
	List<GroupOrder> groupOrders = groupDao.getAll();
%>
<% if(groupOrders != null){ %>
			<h1>現在揪團中</h1>
<%
	} else {
%>	
			<h1>現在揪團中: 沒有正在揪團中~趕快揪一個吧</h1>
<%
	}
%>
			<div class="row">
<% 
			for(GroupOrder order : groupOrders){
%>
				<div class="col">
					<div class="card">
						<div class="row g-0 align-items-center">
							<div class="col-4 ">
								<img
									src="<%=request.getContextPath()%>/consumer/dinerDBGifReader?dinerID=<%= order.getDinerInfo().getDinerID() %>"
									class="card-img" alt="...">
							</div>
							<div class="col-8">
								<div class="card-body">
									<h5 class="card-title"><%= order.getBuildingInfo().getBuildingName() %></h5>
									<ul class="list-unstyled card-text">
										<li><span>大樓地址：</span><span><%= order.getBuildingInfo().getBuildingAddress() %></span></li>
										<li><%= order.getDinerInfo().getDinerName() %></li>
										<li>團主：<%= order.getUserInfo().getUserName() %></li>
										<li class="list-inline-item">成團條件：<%= order.getDinerInfo().getDinerOrderThreshold() %>元</li>
<%
  String statusText = "";
  int status;
  if (order.getOrderStatus() != null) {
	    try {
	      status = Integer.parseInt(order.getOrderStatus().toString());
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
										<li class="list-inline-item">成團狀態：<%= statusText%></li>
<%
  java.util.Date submitTime = order.getGroupOrderSubmitTime();
  java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
  String formattedSubmitTime = sdf.format(submitTime);
%>
										<li>付款截止時間：<%= formattedSubmitTime %></li>
										<div class="d-flex justify-content-end">
											<a class="btn btn-dark" href="${pageContext.request.contextPath}/GroupOrder.do?action=getOne&groupOrderID=<%=order.getGroupOrderID() %>">查看大樓揪團詳情</a>
										</div>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
<% 
			}
%>
				
			</div>
		</div>


	</section>

	<%-- Page content end --%>

	<jsp:include page="./components/footer.jsp"></jsp:include>
	<%-- The footer --%>


	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>

	

</body>
</html>
