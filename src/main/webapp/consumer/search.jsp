<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouporder.dao.GroupOrderDAO"%>
<%@ page import="com.grouporder.dao.GroupOrderDAOHibernateImpl"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.grouporder.entity.GroupOrder"%>
<%@ page import="com.userorderdetail.entity.UserOrderDetail"%>
<%@ page import="com.userorderdetailvary.entity.UserOrderDetailVary"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>

<%-- Remember to edit the page title --%>
<title>樓頂揪樓咖-大樓揪團列表</title>
</head>

<body>

	<%-- The navigation bar --%>
	<jsp:include page="./components/nav.jsp"></jsp:include>

	<%-- Page content start --%>
	<section class="container mt-5">
		<!-- 1. Search bar start -->
<!-- 		<div class="search_bar"> -->
<%-- 			<form action="${pageContext.request.contextPath}/GroupOrder.do?action=searchGroupOrder" method="post"> --%>
<!-- 				<div class="row col-11 mx-auto mb-3"> -->
<!-- 					<div class="col-3"> -->

<!-- 						<input type="text" class="form-control input_keyword" -->
<!-- 							name="keyword" placeholder="輸入商家名稱關鍵字"> -->

<!-- 					</div> -->
<!-- 					<div class="col-8"> -->
<!-- 						<div class="input-group"> -->
<!-- 							<input type="text" class="form-control input_address" -->
<!-- 								name="address" placeholder="輸入大樓地址關鍵字">  -->
<!-- 								<a class="btn btn-outline-secondary" title="清除"><i -->
<!-- 								class="fa-solid fa-xmark"></i></a> <a -->
<!-- 								class="btn btn-outline-secondary" title="使用我的定位"><i -->
<!-- 								class="fa-solid fa-location-dot"></i></a> <a -->
<!-- 								class="btn btn-outline-secondary" title="使用我的常用大樓"><i -->
<!-- 								class="fa-solid fa-building"></i></a> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<button class="col-1 btn btn-dark" type="submit" id="btn_search">搜尋</button> -->
<!-- 				</div> -->
<!-- 			</form> -->
<!-- 		</div> -->
		<!-- 1. Search bar end -->

		<!-- 2. Tabs and contents start -->
		<div class="container mt-5">

			<!-- 2.1 Tabs start -->
<!-- 			<nav> -->
<!-- 				<div class="nav nav-tabs" id="nav_tab" role="tablist"> -->
<!-- 					<button class="nav-link active" id="nav_searchgrouporder_tab" -->
<!-- 						data-bs-toggle="tab" data-bs-target="#nav_searchgrouporder" -->
<!-- 						type="button" role="tab" aria-controls="nav_searchgrouporder" -->
<!-- 						aria-selected="true">搜尋大樓揪團</button> -->
<!-- 					<button class="nav-link" id="nav_searchdiner_tab" -->
<!-- 						data-bs-toggle="tab" data-bs-target="#nav_searchdiner" -->
<!-- 						type="button" role="tab" aria-controls="nav_searchdiner" -->
<!-- 						aria-selected="false" tabindex="-1">搜尋商家</button> -->

<!-- 				</div> -->
<!-- 			</nav> -->
			<!-- 2.1 Tabs end -->

			<!-- 2.2 Contents start -->
<!-- 			<div class="tab-content" id="nav_tab_content"> -->
				<!-- 2.2.1.1 Search result area start -->
				大樓揪團列表
				<div class="row">
					<!-- 2.2.1.1.1 Left content start -->
<!-- 					<div class="col-3"> -->
<!-- 						<ul class="list-group filter_by"> -->
<!-- 							<li class="list-group-item d-flex justify-content-center"> -->
<!-- 								<a class="btn btn-dark"><i -->
<!-- 									class="fa-solid fa-map-location-dot"></i>在地圖中顯示</a> -->
<!-- 							</li> -->
<!-- 							<li class="list-group-item"> -->
<!-- 								<h5>篩選</h5> -->
<!-- 								<div class="form-check"> -->
<!-- 									<input class="form-check-input my_building_only" -->
<!-- 										type="checkbox" id="g_my_building_only"> <label -->
<!-- 										class="form-check-label" for="g_my_building_only">只顯示我的常用大樓</label> -->
<!-- 								</div> -->
<!-- 								<div class="form-check"> -->
<!-- 									<input class="form-check-input achived_only" type="checkbox" -->
<!-- 										value="" id="achived_only"> <label -->
<!-- 										class="form-check-label" for="achived_only">只顯示成團條件已達成</label> -->
<!-- 								</div> -->
<!-- 							</li> -->
<!-- 							<li class="list-group-item"> -->
<!-- 								<h5>商家分類</h5> -->
<!-- 								<div class="form-check"> -->
<!-- 									<input class="form-check-input type_food" type="checkbox" -->
<!-- 										id="g_type_food"> <label class="form-check-label" -->
<!-- 										for="g_type_food"> <i class="fa-solid fa-utensils"></i>餐點 -->
<!-- 									</label> -->
<!-- 								</div> -->
<!-- 								<div class="form-check"> -->
<!-- 									<input class="form-check-input type_drinks" type="checkbox" -->
<!-- 										id="g_type_drinks"> <label class="form-check-label" -->
<!-- 										for="g_type_drinks"> <i -->
<!-- 										class="fa-solid fa-mug-saucer"></i>飲料 -->
<!-- 									</label> -->
<!-- 								</div> -->
<!-- 								<div class="form-check"> -->
<!-- 									<input class="form-check-input type_mixed" type="checkbox" -->
<!-- 										id="g_type_mixed"> <label class="form-check-label" -->
<!-- 										for="g_type_mixed"> <i class="fa-solid fa-utensils"></i><i -->
<!-- 										class="fa-solid fa-mug-saucer"></i>複合式 -->
<!-- 									</label> -->
<!-- 								</div> -->
<!-- 							</li> -->
<!-- 						</ul> -->
<!-- 					</div> -->
					<!-- 2.2.1.1.1 Left content end -->

					<!-- 2.2.1.1.2 Right content start-->
					<div class="col-8">

						<!-- Count and sort start -->
						<div class="d-flex align-items-center justify-content-between">

							<!-- Count of results start -->
<!-- 							<div class="num_of_results"> -->
<!-- 								<span class="d-inline-block">1382筆結果</span> -->
<!-- 							</div> -->
							<!-- Count of results end -->

							<!-- Sort start -->
<!-- 							<div class="order_by"> -->
<!-- 								<span class="d-inline-block">排序依：</span> -->
<!-- 								<div class="d-inline-block"> -->
<!-- 									<select name="group_order_order_by" class="form-select"> -->
<!-- 										<option value="distance">距離</option> -->
<!-- 										<option value="rating">評分</option> -->
<!-- 										<option value="threshold">成團條件金額</option> -->
<!-- 										<option value="progress">成團條件達成進度</option> -->
<!-- 										<option value="deadline">付款截止時間</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<!-- Sort end -->

						</div>
						<!-- Count and sort end -->

						<!-- Cards start -->
						<div class="mt-3" id="group_order_results">
							<!-- Card start -->

							<c:forEach var="groupOrder" items="${groupOrderList}">
								<div class="card">
									<div class="row g-0 align-items-center">
										<div class="col-4 ">
											<img src="${pageContext.request.contextPath}/GroupOrderDinerImage?entity=GroupOrder&id=${groupOrder.groupOrderID}" class="card-img" alt="...">
										</div>
										<div class="col-8">
											<div class="card-body">
												<h5 class="card-title">${groupOrder.buildingName}</h5>
												<ul class="list-unstyled card-text">
													<li>大樓地址：${groupOrder.buildingAddress}</li>
													<li>商家：${groupOrder.dinerName}</li>
													<li class="list-inline-item"><span class="badge fs-6 rounded-pill bg-secondary">
				
													${groupOrder.dinerType == 'M' ? '<i class="fa-solid fa-utensils"></i>' : (groupOrder.dinerType=='D' ? '<i class="fa-solid fa-mug-saucer"></i>' : '<i class="fa-solid fa-utensils"></i><i class="fa-solid fa-mug-saucer"></i>')}
													</span>
													</li>
													<li class="list-inline-item"><span class="badge fs-6 rounded-pill bg-secondary"><i class="fa-solid fa-star"></i>${groupOrder.dinerRating}</span>
													</li><li>主揪：${groupOrder.userNickName}</li>
													<li class="list-inline-item">目前總金額：${groupOrder.groupTotalPrice}元 </li>
													<li class="list-inline-item">成團條件：${groupOrder.dinerOrderThreshold}元 </li>
													<li class="list-inline-item">成團狀態：${groupOrder.orderStatus=='1'? '未達成團條件' : '已達成團條件'}</li>
													<fmt:formatDate value="${groupOrder.groupOrderSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" var="groupOrderSubmitTimeFormatted" />
													<li>付款截止時間：${groupOrderSubmitTimeFormatted}</li>
													<div class="d-flex justify-content-end"><a class="btn btn-dark" href="${pageContext.request.contextPath}/GroupOrder.do?action=getOne&groupOrderID=${groupOrder.groupOrderID}">查看大樓揪團詳情</a>
													</div>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							<!-- Card end -->

						</div>
						<!-- Cards end -->

					</div>
					<!-- 2.2.1.1.2 Right content end -->

				</div>
				<!-- 2.2.1.1 Search result area end -->

				<!-- 2.2.1.2 Pagination Start  -->
<!-- 				<div class="mt-3 d-flex justify-content-center"> -->
<!-- 					<nav aria-label="Page navigation example"> -->
<!-- 						<ul class="pagination justify-content-start"> -->
<!-- 							<li class="page-item disabled"><a class="page-link" -->
<!-- 								href="#"><i class="fa-solid fa-angle-left"></i></a></li> -->
<!-- 							<li class="page-item active"><a class="page-link" href="#">01</a></li> -->
<!-- 							<li class="page-item"><a class="page-link" href="#">02</a></li> -->
<!-- 							<li class="page-item"><a class="page-link" href="#">03</a></li> -->
<!-- 							<li class="page-item"><a class="page-link" href="#"><i -->
<!-- 									class="fa-solid fa-angle-right"></i></a></li> -->
<!-- 						</ul> -->
<!-- 					</nav> -->
<!-- 				</div> -->
				<!-- 2.2.1.2 Pagination End  -->
			<!-- 2.2.1 Group order end -->

<!-- 			</div> -->
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