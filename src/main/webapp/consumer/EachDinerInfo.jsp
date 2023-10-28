<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="components/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.businesshours.dao.*"%>
<%@ page import="com.businesshours.entity.*"%>
<%@ page import="com.dinerinfo.dao.*"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%@ page import="com.dinerratingcomment.entity.*"%>
<%@ page import="com.dinerratingcomment.dao.*"%>
<%@ page import="com.grouporder.dao.*"%>
<%@ page import="com.grouporder.entity.*"%>
<%@ page import="com.product.dao.*"%>
<%@ page import="com.product.entity.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%-- Import CSS for this page below (if any) --%>


<title>樓頂揪樓咖-個別商家資訊</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<!-- 個別商家資訊 -->

	<%
	Integer dinerID = Integer.parseInt(request.getParameter("dinerID"));
	String dayOfWeek = "Monday";
	DinerInfo dinerInfo = new DinerInfo();
	dinerInfo = new DinerInfoDAOImplC().findByPK(dinerID);
	if (dinerInfo != null) {
		pageContext.setAttribute("dinerInfo", dinerInfo);
	}

	// 	營業時間
	BusinessHours_Tz businessHours = new BusinessHoursDAOImpl_Tz().getTimeByDinerIDDayWeek(dinerID, dayOfWeek);
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	String formattedOpenTime = sdf.format(businessHours.getOpenTime());
	String formattedCloseTime = sdf.format(businessHours.getCloseTime());
	

	//平均評分
	Double averageRating = new DinerRatingCommentDAO().getAverageRatingByDinerIDWithCriteria(dinerID);
	String formattedRating = String.format("%.1f", averageRating);
	//依據dinerID取得groupOrder List
	GroupOrderDAOHibernateImpl_Tz groupDao = new GroupOrderDAOHibernateImpl_Tz();
	List<GroupOrder> groupOrders = groupDao.getAllbyDinerID(dinerID);
	request.setAttribute("groupOrders", groupOrders);

	//依據DinerID找到餐點List
	ProductDAOImpl_Tz productDao = new ProductDAOImpl_Tz();
	List<Product_Tz> productList = productDao.getAll(dinerID);
	request.setAttribute("productList", productList);
// 	String productName = productList.get(0).getProductName();
// 	System.out.println("第一個產品的名稱是：" + productName);
	%>

	<section class="container mt-3">
		<h1 class="text-center">餐廳介紹</h1>
		<div class="card">
			<div class="row g-0 align-items-center">
				<div class="col-4">
					<img
						src="<%=request.getContextPath()%>/consumer/dinerDBGifReader?dinerID=<%= dinerID %>"
						class="card-img" alt="...">
				</div>
				<div class="col-8">
					<div class="card-body">
						<h5 class="card-title">${dinerInfo.dinerName}</h5>
						<ul class="list-unstyled card-text">
							<li class="list-inline-item"><span
								class="badge fs-6 rounded-pill bg-secondary"><%=formattedOpenTime%>~<%=formattedCloseTime%></span></li>
							<li class="list-inline-item"><span
								class="badge fs-6 rounded-pill bg-secondary"><i
									class="fa-solid fa-utensils"></i></span></li>
							<li class="list-inline-item"><span
								class="badge fs-6 rounded-pill bg-secondary"><i
									class="fa-solid fa-star"></i><%= formattedRating %></span></li>
							<li><span>可外送大樓：</span><span>宏春、揚昇金融</span></li>
							<li><span>成團條件：</span><span>${dinerInfo.dinerOrderThreshold}</span></li>
							<li>配送時間：營業時間內接單後1小時內送達</li>
						</ul>
						<div class="d-grid gap-2 d-flex justify-content-end">
							<a class="btn btn-dark fs-6"><i class="fa-solid fa-magnifying-glass"></i>現有揪團</a>
							<a class="btn btn-dark fs-6"><i class="fa-solid fa-users"></i>發起揪團</a>
							<a class="btn btn-dark fs-6" href="<%=request.getContextPath()%>/consumer/protected/DinerComment.jsp?dinerID=<%= dinerID %>"><i class="fa-solid fa-comment"></i>觀看評論</a>
								
						</div>
<!-- 						<i type="button" -->
<!-- 							class="heartBtn fs-4 position-absolute top-0 end-0 m-3 fa-regular fa-heart"></i> -->
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- 該商家目前揪團 -->
	<section class="container mt-5">
<c:choose>
	<c:when test="${not empty groupOrders}">
		<h2 class="text-center">現在揪團中的大樓</h2>
	</c:when>
	<c:otherwise>
		<h2 class="text-center">目前沒有揪團中的大樓，你要揪一個嗎?</h2>
	</c:otherwise>
</c:choose>
		<div class="row row-cols-2">
			<c:forEach var="groupOrder" items="${groupOrders}">
				<div class="col mb-2">
					<div class="card">
						<div class="row g-0 align-items-center">
							<div class="col-4 ">
								<img
									src="<%=request.getContextPath()%>/consumer/dinerDBGifReader?dinerID=<%= dinerID %>"
									class="card-img" alt="...">
							</div>
							<div class="col-8">
								<div class="card-body">
									<h5 class="card-title">${groupOrder.buildingInfo.buildingName}</h5>
									<ul class="list-unstyled card-text">
										<li><span>大樓地址：</span><span>${groupOrder.buildingInfo.buildingAddress}</span></li>
										<li>${dinerInfo.dinerName}</li>
										<li>團主：${groupOrder.userInfo.userNickName}</li>
										<li class="list-inline-item">成團條件：${dinerInfo.dinerOrderThreshold}</li>
<c:set var="statusText" value="未知狀態"/>
<c:set var="status" value="-1"/>
<!-- 檢查 groupOrder.orderStatus 是否為 null，並轉換為整數 -->
<c:if test="${not empty groupOrder.orderStatus}">
    <c:catch var="numberFormatError">
        <c:set var="status" value="${groupOrder.orderStatus}"/>
    </c:catch>
    <c:if test="${not empty numberFormatError}">
        <c:set var="status" value="-1"/>
    </c:if>
</c:if>
<c:choose>
    <c:when test="${status == 1}">
        <c:set var="statusText" value="揪團已建立"/>
    </c:when>
    <c:when test="${status == 2}">
        <c:set var="statusText" value="成團條件達成"/>
    </c:when>
    <c:when test="${status == 3}">
        <c:set var="statusText" value="揪團成功"/>
    </c:when>
    <c:when test="${status == 4}">
        <c:set var="statusText" value="揪團失敗"/>
    </c:when>
    <c:when test="${status == 5}">
        <c:set var="statusText" value="餐點準備中"/>
    </c:when>
    <c:when test="${status == 6}">
        <c:set var="statusText" value="商家拒單"/>
    </c:when>
    <c:when test="${status == 7}">
        <c:set var="statusText" value="餐點送達"/>
    </c:when>
    <c:otherwise>
        <c:set var="statusText" value="未知狀態"/>
    </c:otherwise>
</c:choose>
										<li class="list-inline-item">狀態：${statusText}</li>
										<li>付款截止時間：${groupOrder.groupOrderSubmitTime}</li>
										<div class="d-flex justify-content-end">
											<a class="btn btn-dark">加入此大樓揪團</a>
										</div>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>

	<section class="container mt-4">
		<h2 class="text-center">菜單</h2>
		<h4>餐點</h4>
		<div class="row mb-5 row-cols-3">
			<c:forEach var="productL" items="${productList}">
				<div class="col">
					<div class="card">
						<div>
							<img
								src="<%=request.getContextPath()%>/consumer/productDBGifReader?productID=${productL.productID}"
								class="card-img" alt="...">
						</div>
						<div>
							<div class="card-body">
								<h5 class="mealName">${productL.productName}</h5>
								<ul class="list-unstyled card-text">
									<li>價格：NT$<span class="mealPrice">${productL.productPrice}</span></li></ul>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>

	<%-- Page content end --%>

	<jsp:include page="./components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>

	<script>
		//愛心切換加入最愛商家
// 		$(document).ready(function() {
// 			$('.heartBtn').click(function() {
// 				if ($(this).hasClass('fa-regular')) {
// 					$(this).removeClass('fa-regular');
// 					$(this).addClass('fa-solid');
// 					alert('已加入最愛商家');
// 				} else if ($(this).hasClass('fa-solid')) {
// 					$(this).removeClass('fa-solid');
// 					$(this).addClass('fa-regular');
// 					alert('已取消最愛商家');
// 				}
// 			});
// 		});
	</script>

</body>
</html>