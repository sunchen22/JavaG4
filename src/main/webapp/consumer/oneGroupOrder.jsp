<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate value="${groupOrderData.groupOrderSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" var="groupOrderSubmitTimeFormatted" />


<%@ include file="components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>


<%-- Remember to edit the page title --%>
<title>樓頂揪樓咖-大樓揪團：${groupOrderData.buildingName} x
	${groupOrderData.dinerName}</title>
</head>

<body>
	<%-- The navigation bar --%>
	<jsp:include page="./components/nav.jsp"></jsp:include>

	<%-- Page content start --%>
	<div class="container">
		<div class="card">
			<div class="row g-0 align-items-center">
				<div class="col-5 h-100">
					<img
						src="${pageContext.request.contextPath}/GroupOrderDinerImage?entity=GroupOrder&id=${groupOrderData.groupOrderID}"
						class="card-img" alt="...">
				</div>
				<div class="col-7">
					<div class="card-body">
						<h5 class="card-title">${groupOrderData.buildingName}</h5>
						<ul class="list-unstyled card-text">
							<li>大樓地址：${groupOrderData.buildingAddress}</li>
							<li>${groupOrderData.dinerName}</li>
							<li class="list-inline-item"><span
								class="badge fs-6 rounded-pill bg-secondary">
									${groupOrderData.dinerType == 'M' ? '<i class="fa-solid fa-utensils"></i>' : (groupOrderData.dinerType=='D' ? '<i class="fa-solid fa-mug-saucer"></i>' : '<i class="fa-solid fa-utensils"></i><i class="fa-solid fa-mug-saucer"></i>')}</span>
							</li>
							<li class="list-inline-item"><span
								class="badge fs-6 rounded-pill bg-secondary"><i
									class="fa-solid fa-star"></i>${groupOrderData.dinerRating}</span></li>
							<li>主揪：${groupOrderData.userNickName}</li>
							<li class="list-inline-item">成團條件：${groupOrderData.dinerOrderThreshold}元
							</li>
							<li class="list-inline-item">成團狀態：${groupOrderData.orderStatus=='1'? '未達成團條件' : '已達成團條件'}</li>
							<li>付款截止時間：${groupOrderSubmitTimeFormatted}</li>
							<li>
								<div class="progress">
									<div class="progress-bar bg-dark" role="progressbar"
										style="width: 25%;" aria-valuenow="25" aria-valuemin="0"
										aria-valuemax="100">25%</div>
								</div>
							</li>
						</ul>
						<div class="d-grid gap-2 d-flex justify-content-end">
							<c:choose>
							    <c:when test="${empty sessionScope.loginUserInfo}">
							        <!-- User is not logged in -->
							        <span>登入並加入此大樓揪團後才可點餐</span><a class="btn btn-dark">登入</a>
							    </c:when>
							    <c:otherwise>
							        <c:choose>
							            <c:when test="${not sessionScope.userIsGroupMember}">
							                <!-- User is logged in but doesn't belong to the group -->
							                <span>加入此大樓揪團後才可點餐</span><a class="btn btn-dark" href="${pageContext.request.contextPath}/GroupOrder.do?action=join&groupOrderID=${groupOrderData.groupOrderID}&dinerName=${groupOrderData.dinerName}">加入此大樓揪團</a>
							            </c:when>
							            <c:otherwise>
							                <!-- User is logged in and belongs to the group -->
							                <a class="btn btn-outline-dark disabled">已加入此大樓揪團</a>
							            </c:otherwise>
							        </c:choose>
							    </c:otherwise>
							</c:choose>
						</div>
						<a href="#" class="btn position-absolute top-0 end-0 m-3" id="share_link_button"><i
							class="fa-solid fa-share-from-square fs-4"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<section class="container mt-5">
		<!-- stepper start -->
		<div class="mdl-card mdl-shadow--2dp">

			<div class="mdl-card__supporting-text">

				<div class="mdl-stepper-horizontal-alternative">
					<c:choose>
						<c:when test="${not sessionScope.userIsGroupMember}">
							<div class="mdl-stepper-step">
								<div class="mdl-stepper-circle">
									<span>1</span>
								</div>
								<div class="mdl-stepper-title">加入此大樓揪團</div>						
						</c:when>
						<c:otherwise>
							<div class="mdl-stepper-step active-step">
								<div class="mdl-stepper-circle">
									<span>1</span>
								</div>
								<div class="mdl-stepper-title">已加入此大樓揪團</div>	
						</c:otherwise>
					</c:choose>
						
						<div class="mdl-stepper-bar-left"></div>
						<div class="mdl-stepper-bar-right"></div>
					</div>
					<div class="mdl-stepper-step">
						<div class="mdl-stepper-circle">
							<span>2</span>
						</div>
						<div class="mdl-stepper-title">點餐</div>
						<div class="mdl-stepper-optional"></div>
						<div class="mdl-stepper-bar-left"></div>
						<div class="mdl-stepper-bar-right"></div>
					</div>
					<div class="mdl-stepper-step">
						<div class="mdl-stepper-circle">
							<span>3</span>
						</div>
						<div class="mdl-stepper-title">付款</div>
						<div class="mdl-stepper-optional"></div>
						<div class="mdl-stepper-bar-left"></div>
						<div class="mdl-stepper-bar-right"></div>
					</div>
					<div class="mdl-stepper-step">
						<div class="mdl-stepper-circle">
							<span>4</span>
						</div>
						<div class="mdl-stepper-title">成團條件達成</div>
						<div class="mdl-stepper-optional"></div>
						<div class="mdl-stepper-bar-left"></div>
						<div class="mdl-stepper-bar-right"></div>
					</div>
					<div class="mdl-stepper-step">
						<div class="mdl-stepper-circle">
							<span>5</span>
						</div>
						<div class="mdl-stepper-title">
							付款期限到<br>自動送出訂單
						</div>
						<div class="mdl-stepper-optional"></div>
						<div class="mdl-stepper-bar-left"></div>
						<div class="mdl-stepper-bar-right"></div>
					</div>
					<div class="mdl-stepper-step">
						<div class="mdl-stepper-circle">
							<span>6</span>
						</div>
						<div class="mdl-stepper-title">餐點送達</div>
						<div class="mdl-stepper-optional"></div>
						<div class="mdl-stepper-bar-left"></div>
						<div class="mdl-stepper-bar-right"></div>
					</div>
				</div>

			</div>

		</div>
		<!-- stepper end -->
	</section>

	<section class="container mt-4">
		<h2>菜單</h2>
		<c:forEach var="productType" items="${menuData}">
			<div class="mt-5">
				<h4>${productType.key}</h4>
				<div class="row mt-2">
					<c:forEach var="product" items="${productType.value}"
						varStatus="loopStatus">
						<c:if test="${loopStatus.index % 3 == 0}">
						<!-- Close the previous row and start a new one -->
				</div>
				<div class="row mt-2">
						</c:if>
					<div class="col-4">
						<div class="card product">
							<div>
								<img
									src="${pageContext.request.contextPath}/GroupOrderDinerImage?entity=Product&id=${product.productID}&no=1"
									class="card-img-top" alt="...">
							</div>
							<div>
								<div class="card-body">
									<h5 class="mealName">${product.productName}</h5>
									<ul class="list-unstyled card-text my-0">
										<li>價格：NT$<span class="mealPrice">${product.productPrice}</span></li>
									</ul>
									<a class="stretched-link .invisible" href="#" data-bs-toggle="modal" data-bs-target="#order_detail_modal" data-productid="${product.productID}"></a>
								</div>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</section>

	<div class="modal modal-sm fade" id="order_detail_modal" tabindex="-1"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body pt-0 px-0">
					<!-- 餐點圖片 可輪播 -->
					<div id="carouselExampleIndicators" class="carousel carousel-orderdetail slide my-3 mx-auto" data-bs-ride="carousel">
						<div class="carousel-indicators">
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="0" class="active"></button>
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="1" class="" aria-current="true"></button>
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="2" class=""></button>
						</div>
						<div class="carousel-inner mx-auto">
							<div class="carousel-item carousel-item-orderdetail active" id="modal_product_img_1">
								<img
									src=""
									class="d-block w-100" alt="餐點圖">
							</div>
							<div class="carousel-item carousel-item-orderdetail" id="modal_product_img_2">
								<img
									src=""
									class="d-block w-100" alt="餐點圖">
							</div>
							<div class="carousel-item carousel-item-orderdetail" id="modal_product_img_3">
								<img
									src=""
									class="d-block w-100" alt="餐點圖">
							</div>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
						</button>
					</div>
					<div class="px-4" id="modal_product_varies">
						<!-- 餐點名稱 -->
						<span class=".invisible" id="modal_productID" data-productid="0"></span>
						<h2 id="modal_product_name"></h2>
		
						<!-- 餐點簡介段落 -->
						<p id="modal_product_remark"></p>
		
						<!--  -->
						<div class="d-flex justify-content-between">
							<span>價格:</span> <span> <span id="modal_product_price"></span> <span>元</span>
							</span>
						</div>
		
						<!-- 餐點選項區塊 -->
						<form id="modal_form">
							<div class="my-2">
								<div id="modal_vary_types">
									<%-- HTML inside this div will be generated by AJAX request --%>
								</div>
								
							</div>
			
							<!-- 份數:下拉選單 -->
							<div class="my-2 modal_product_quantity">
								<div class="form-outline">
									<span>份數:</span> 
								    <input type="number" id="modal_product_quantity" class="form-control" value="1" step="1" min="1" max="99">
								</div>
							</div>
						</form>
			
						<div class="d-flex justify-content-between mt-3">
							<span>小計:</span> 
							<div>
								<span id="modal_subtotal"><%-- Text inside this span will be generated by AJAX request --%></span>
								<span>元</span>
							</div>
						</div>
						
					</div>
				</div>
			
				<div class="modal-footer">
					<!-- 取消 加到購物車 -->
					<c:choose>
					    <c:when test="${empty sessionScope.loginUserInfo}">
					        <!-- User is not logged in -->
					        <p>登入並加入此大樓揪團後才可點餐</p>
					        <div>
					        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">取消</button>
							<button type="button" class="btn btn-dark">登入</button>
							</div>
					    </c:when>
					    <c:otherwise>
					        <c:choose>
					            <c:when test="${not sessionScope.userIsGroupMember}">
					                <!-- User is logged in but doesn't belong to the group -->
					                <p>加入此大樓揪團後才可點餐</p>
					                <div>
					                <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">取消</button>
					                <a class="btn btn-dark" href="${pageContext.request.contextPath}/GroupOrder.do?action=join&groupOrderID=${groupOrderData.groupOrderID}">加入此大樓揪團</a>
					                </div>
					            </c:when>
					            <c:otherwise>
					                <!-- User is logged in and belongs to the group -->
					                <div>
									<button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">取消</button>
									<button type="button" class="btn btn-dark">加到購物車</button>
									</div>
					            </c:otherwise>
					        </c:choose>
					    </c:otherwise>
					</c:choose>
					

				</div>
			</div>
		</div>
	</div>

	<%-- Page content end --%>


	<%-- The footer --%>
	<jsp:include page="./components/footer.jsp"></jsp:include>


	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>
	<script src="${pageContext.request.contextPath}/consumer/js/orderDetailModal.js"></script>

</body>
</html>