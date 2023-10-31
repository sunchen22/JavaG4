<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouporder.dao.GroupOrderDAO"%>
<%@ page import="com.grouporder.dao.GroupOrderDAOHibernateImpl"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouporder.entity.GroupOrder"%>
<%@ page import="com.userorderdetail.entity.UserOrderDetail"%>
<%@ page import="com.userorderdetailvary.entity.UserOrderDetailVary"%>


<%@ include file="components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>

<%-- Remember to edit the page title --%>
<title>樓頂揪樓咖-搜尋商家</title>
</head>

<body>

	<%-- The navigation bar --%>
	<jsp:include page="./components/nav.jsp"></jsp:include>

	<%-- Page content start --%>
	<section class="container mt-5">
		<!-- 1. Search bar start -->
		<div class="search_bar">
			<form action="${pageContext.request.contextPath}/diner.search" method="get">
				<div class="row col-11 mx-auto mb-3">
					<div class="col-3">

						<input type="text" class="form-control input_keyword"
							name="keyword" placeholder="輸入店名關鍵字">

					</div>
					<div class="col-8">
						<div class="input-group">
							<input type="text" class="form-control input_address"
								name="address" placeholder="輸入地址"> 
						</div>
					</div>
					<button class="col-1 btn btn-dark" type="submit" id="btn_search">搜尋</button>
				</div>
			</form>
		</div>
		<!-- 1. Search bar end -->

		<!-- 2. Tabs and contents start -->
		<div class="container mt-5">
					<!-- 2.2.2.1 Search result area start -->
					搜尋商家
					<div class="row">
						<!-- 2.2.2.1.1 Left content start -->
<!-- 						<div class="col-3"> -->
<!-- 							<ul class="list-group filter_by"> -->
<!-- 								<li class="list-group-item d-flex justify-content-center"> -->
<!-- 									<a class="btn btn-dark"><i -->
<!-- 										class="fa-solid fa-map-location-dot"></i>在地圖中顯示</a> -->
<!-- 								</li> -->
<!-- 								<li class="list-group-item"> -->
<!-- 									<h5>篩選</h5> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input my_building_only" -->
<!-- 											type="checkbox" id="d_my_building_only"> <label -->
<!-- 											class="form-check-label" for="d_my_building_only">只顯示我的常用大樓</label> -->
<!-- 									</div> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input has_group_only" type="checkbox" -->
<!-- 											id="has_group_only"> <label class="form-check-label" -->
<!-- 											for="has_group_only">只顯示目前有團</label> -->
<!-- 									</div> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input now_open_only" type="checkbox" -->
<!-- 											id="now_open_only"> <label class="form-check-label" -->
<!-- 											for="now_open_only">只顯示營業中</label> -->
<!-- 									</div> -->
<!-- 								</li> -->
<!-- 								<li class="list-group-item"> -->
<!-- 									<h5>商家分類</h5> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input type_food" type="checkbox" -->
<!-- 											id="d_type_food"> <label class="form-check-label" -->
<!-- 											for="d_type_food"> <i class="fa-solid fa-utensils"></i>餐點 -->
<!-- 										</label> -->
<!-- 									</div> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input type_drinks" type="checkbox" -->
<!-- 											id="d_type_drinks"> <label class="form-check-label" -->
<!-- 											for="d_type_drinks"> <i -->
<!-- 											class="fa-solid fa-mug-saucer"></i>飲料 -->
<!-- 										</label> -->
<!-- 									</div> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input type_mixed" type="checkbox" -->
<!-- 											id="d_type_mixed"> <label class="form-check-label" -->
<!-- 											for="d_type_mixed"> <i class="fa-solid fa-utensils"></i><i -->
<!-- 											class="fa-solid fa-mug-saucer"></i>複合式 -->
<!-- 										</label> -->
<!-- 									</div> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
						<!-- 2.2.2.1.1 Left content end -->

						<!-- 2.2.2.1.2 Right content start-->
						<div class="col-9">

							<!-- Count and sort start -->
							<div class="d-flex align-items-center justify-content-between">

								<!-- Count of results start -->
								<div class="num_of_results">
<!-- 									<span class="d-inline-block">677筆結果</span> -->
								</div>
								<!-- Count of results end -->

								<!-- Sort start -->
								<div class="order_by">
<!-- 									<span class="d-inline-block">排序依：</span> -->
<!-- 									<div class="d-inline-block"> -->
<!-- 										<select name="diner_order_by" class="form-select"> -->
<!-- 											<option value="distance">距離</option> -->
<!-- 											<option value="rating">評分</option> -->
<!-- 											<option value="threshold">成團條件金額</option> -->
<!-- 										</select> -->
<!-- 									</div> -->
								</div>
								<!-- Sort end -->

							</div>
							<!-- Count and sort end -->

							<!-- Cards start -->
							<div class="mt-3">
								<!-- Card start -->
<%@ page import="com.dinerinfo.dao.*"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%@ page import="com.dinerratingcomment.entity.*"%>
<%@ page import="com.dinerratingcomment.dao.*"%>
<%@ page import="com.businesshours.dao.*"%>
<%@ page import="com.businesshours.entity.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
								<% 
								//平均評分
								Double averageRating;
								BusinessHours_Tz businessHours;
								SimpleDateFormat sdf;
								String formattedOpenTime;
								String formattedCloseTime;
								String formattedRating;
								if (request.getAttribute("diners") != null) { 
									List<DinerInfo> diners = (List<DinerInfo>) request.getAttribute("diners");
									for (DinerInfo diner : diners) {
									averageRating = new DinerRatingCommentDAO().getAverageRatingByDinerIDWithCriteria(diner.getDinerID());
									formattedRating = String.format("%.1f", averageRating);
									businessHours = new BusinessHoursDAOImpl_Tz().getTimeByDinerIDDayWeek(diner.getDinerID(), "Monday");
							    	sdf = new SimpleDateFormat("HH:mm");
							    	formattedOpenTime = sdf.format(businessHours.getOpenTime());
							    	formattedCloseTime = sdf.format(businessHours.getCloseTime());
								%> 
								<div class="card">
									<div class="row g-0 align-items-center">
										<div class="col-5 ">
											<img
												src="<%=request.getContextPath()%>/consumer/dinerDBGifReader?dinerID=<%= diner.getDinerID() %>"
												class="card-img" alt="...">
										</div>
										<div class="col-7">
											<div class="card-body">
												<h5 class="card-title"><a href="${pageContext.request.contextPath}/consumer/EachDinerInfo.jsp?dinerID=<%= diner.getDinerID() %>">
												<%= diner.getDinerName() %> </a>
												</h5>
												<ul class="list-unstyled card-text">
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary"><%= formattedOpenTime %>~<%= formattedCloseTime %></span>
													</li>
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary"><i
															class="fa-solid fa-utensils"></i></span></li>
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary"><i
															class="fa-solid fa-star"></i><%= formattedRating %></span></li>
													<li>外送大樓：宏春、揚昇金融</li>
													<li>成團條件：<%= diner.getDinerOrderThreshold() %>元</li>
													<li>營業時間內接單後1小時內送達</li>
													<li>地址：<%= diner.getDinerAddress() %></li>
												</ul>
												<div class="d-grid gap-2 d-flex justify-content-end">
<!-- 													<a class="btn btn-dark fs-6"><i -->
<!-- 														class="fa-solid fa-magnifying-glass"></i>現有揪團</a><a -->
<!-- 														class="btn btn-dark fs-6"><i class="fa-solid fa-users"></i>發起揪團</a> -->
												</div>
<!-- 												<i -->
<!-- 													class="fa-regular fa-heart fs-4 position-absolute top-0 end-0 m-3"></i> -->
											</div>
										</div>
									</div>
								</div>
								<% 
									}
								} 
								%>
								<!-- Card end -->
								
							</div>
							<!-- Cards end -->

						</div>
						<!-- 2.2.2.1.2 Right content end -->

					</div>
					<!-- 2.2.2.1 Search result area end -->

					<!-- 2.2.2.2 Pagination Start  -->
<!-- 					<div class="mt-3 d-flex justify-content-center"> -->
<!-- 						<nav aria-label="Page navigation example"> -->
<!-- 							<ul class="pagination justify-content-start"> -->
<!-- 								<li class="page-item disabled"><a class="page-link" -->
<!-- 									href="#"><i class="fa-solid fa-angle-left"></i></a></li> -->
<!-- 								<li class="page-item active"><a class="page-link" href="#">01</a></li> -->
<!-- 								<li class="page-item"><a class="page-link" href="#">02</a></li> -->
<!-- 								<li class="page-item"><a class="page-link" href="#">03</a></li> -->
<!-- 								<li class="page-item"><a class="page-link" href="#"><i -->
<!-- 										class="fa-solid fa-angle-right"></i></a></li> -->
<!-- 							</ul> -->
<!-- 						</nav> -->
<!-- 					</div> -->
					<!-- 2.2.2.2 Pagination End  -->
				</div>
				<!-- 2.2.2 diner end -->


			<!-- 2.2 Contents end -->
		</div>
		<!-- 2. Tabs end -->
	</section>

	<%-- Page content end --%>

	<jsp:include page="./components/footer.jsp"></jsp:include>
	<%-- The footer --%>



	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>

</body>
</html>