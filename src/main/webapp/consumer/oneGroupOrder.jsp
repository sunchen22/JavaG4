<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.userinfo.entity.UserInfo"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate value="${groupOrderData.groupOrderSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" var="groupOrderSubmitTimeFormatted" />
<%
List<Map<String,Object>> cartData = (List<Map<String,Object>>) request.getAttribute("cartData");
%>
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
				<div class="col-4 h-100">
					<img
						src="${pageContext.request.contextPath}/GroupOrderDinerImage?entity=GroupOrder&id=${groupOrderData.groupOrderID}"
						class="card-img" alt="...">
				</div>
				<div class="col-8">
					<div class="card-body">
						<h5 class="card-title">${groupOrderData.buildingName}</h5>
						<ul class="list-unstyled card-text">
							<li>大樓地址：${groupOrderData.buildingAddress}</li>
							<li>商家：<a href="${pageContext.request.contextPath}/consumer/EachDinerInfo.jsp?dinerID=${groupOrderData.dinerID}">${groupOrderData.dinerName}</a></li>
							<li class="list-inline-item"><span
								class="badge fs-6 rounded-pill bg-secondary">
									${groupOrderData.dinerType == 'M' ? '<i class="fa-solid fa-utensils"></i>' : (groupOrderData.dinerType=='D' ? '<i class="fa-solid fa-mug-saucer"></i>' : '<i class="fa-solid fa-utensils"></i><i class="fa-solid fa-mug-saucer"></i>')}</span>
							</li>
							<li class="list-inline-item"><span
								class="badge fs-6 rounded-pill bg-secondary"><i
									class="fa-solid fa-star"></i>${groupOrderData.dinerRating}</span></li>
							<li>主揪：${groupOrderData.userNickName}</li>
							<li class="list-inline-item">目前總金額：${groupOrderData.groupTotalPrice}元
							</li>
							<li class="list-inline-item">成團條件：${groupOrderData.dinerOrderThreshold}元
							</li>
<%-- 							<li class="list-inline-item">成團狀態：${groupOrderData.orderStatus=='1'? '未達成團條件' : '已達成團條件'}</li> --%>
							<li class="list-inline-item">成團狀態：<c:choose><c:when test="${groupOrderData.groupTotalPrice >= groupOrderData.dinerOrderThreshold}">已達成團條件</c:when><c:otherwise>未達成團條件</c:otherwise></c:choose></li>
							<li>付款截止時間：${groupOrderSubmitTimeFormatted}</li>
<!-- 							<li> -->
<!-- 								<div class="progress"> -->
<!-- 									<div class="progress-bar bg-dark" role="progressbar" -->
<!-- 										style="width: 25%;" aria-valuenow="25" aria-valuemin="0" -->
<!-- 										aria-valuemax="100">25%</div> -->
<!-- 								</div> -->
<!-- 							</li> -->
						</ul>
						<div class="d-grid gap-2 d-flex justify-content-end">
							<c:choose>
							    <c:when test="${groupOrderData.orderStatus == 3 or groupOrderData.orderStatus == 4 or groupOrderData.orderStatus == 5 
						or groupOrderData.orderStatus == 6 or groupOrderData.orderStatus == 7}">
							        <!-- Group order is timeupped -->
							        <span>此大樓揪團付款期限已過，無法加入</span>
							    </c:when>
							    <c:when test="${empty sessionScope.loginUserInfo}">
							        <!-- User is not logged in -->
							        <span>登入並加入此大樓揪團後才可點餐</span>
							        <a class="btn btn-dark" href="${pageContext.request.contextPath}/GroupOrder.do?action=join&groupOrderID=${groupOrderData.groupOrderID}">登入</a>
							    </c:when>
							    <c:otherwise>
							        <c:choose>
							            <c:when test="${not sessionScope.userIsGroupMember}">
							                <!-- User is logged in but doesn't belong to the group -->
							                <span>加入此大樓揪團後才可點餐</span><a class="btn btn-dark" href="${pageContext.request.contextPath}/GroupOrder.do?action=join&groupOrderID=${groupOrderData.groupOrderID}">加入此大樓揪團</a>
							            </c:when>
							            <c:otherwise>
							                <!-- User is logged in and belongs to the group -->
							                <a class="btn btn-dark disabled">已加入此大樓揪團</a>
							                <a class="btn btn-dark" id="open_cart_btn"
									 data-grouporderid="${groupOrderData.groupOrderID}" data-dinerid="${groupOrderData.dinerID}" aria-controls="shopping_cart"
									href="#">
									<i class="bi-cart-fill me-1"></i> 購物車 
<!-- 									<span class="badge bg-dark text-white ms-1 rounded-pill">1</span> -->
								</a>
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

	<section class="container mt-1">
		<!-- stepper start -->
		<div class="mdl-card mdl-shadow--2dp">

			<div class="mdl-card__supporting-text">

				<div class="mdl-stepper-horizontal-alternative">
					<c:choose>
						<c:when test="${(groupOrderData.orderStatus == 3 or groupOrderData.orderStatus == 4 or groupOrderData.orderStatus == 5 
						or groupOrderData.orderStatus == 6 or groupOrderData.orderStatus == 7) and not empty userOrderDetailData}">
							<div class="mdl-stepper-step">
								<div class="mdl-stepper-circle">
									<span>1</span>
								</div>
								<div class="mdl-stepper-title">已加入此大樓揪團</div>						
						</c:when>
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
					
					<c:choose>
						<c:when test="${empty userOrderDetailData}">
					<div class="mdl-stepper-step">
						<div class="mdl-stepper-circle">
							<span>2</span>
						</div>
						<div class="mdl-stepper-title">付款</div>
						</c:when>
						<c:otherwise>
					<div class="mdl-stepper-step active-step">
						<div class="mdl-stepper-circle">
							<span>2</span>
						</div>
						<div class="mdl-stepper-title">已付款</div>
						</c:otherwise>
					</c:choose>	
						<div class="mdl-stepper-optional"></div>
						<div class="mdl-stepper-bar-left"></div>
						<div class="mdl-stepper-bar-right"></div>
					</div>
					<div class="mdl-stepper-step <c:if test="${groupOrderData.orderStatus == 3 or groupOrderData.orderStatus == 5 
						or groupOrderData.orderStatus == 6 or groupOrderData.orderStatus == 7}">active-step</c:if>">
						<div class="mdl-stepper-circle <c:if test="${groupOrderData.orderStatus == 4}">bg-danger</c:if>">
							<span>3</span>
						</div>
						<div class="mdl-stepper-title <c:if test="${groupOrderData.orderStatus == 4}">text-danger</c:if>">
							<c:choose>
								<c:when test="${groupOrderData.orderStatus == 1 or groupOrderData.orderStatus == 2}">
							當付款截止時間到<br>若成團條件有達成<br>將自動送出揪團訂單
								</c:when>
								<c:when test="${groupOrderData.orderStatus == 3 or groupOrderData.orderStatus == 5 
								or groupOrderData.orderStatus == 6 or groupOrderData.orderStatus == 7}">
								付款截止時間到<br>成團條件達成，揪團成功！<br>已自動送出揪團訂單
								</c:when>
								<c:otherwise>
									付款截止時間到<br>成團條件未達成，揪團失敗！<br>已退款
								</c:otherwise>
							</c:choose>
						</div>
						<div class="mdl-stepper-optional"></div>
						<div class="mdl-stepper-bar-left"></div>
						<div class="mdl-stepper-bar-right"></div>
					</div>
				</div>

			</div>

		</div>
		<!-- stepper end -->
	</section>
	
	<section class="container mt-1">
	<c:choose>
		<c:when test="${not empty userOrderDetailData}">
		<!-- Accordion start -->
		<div class="accordion w-50" id="userOrder">
	    	<div class="accordion-item">
	        	<!-- Accordion head start -->
	        	<h2 class="accordion-header" id="headingOne">
	          		<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
	            		<div class="col-8">您已付款的訂購明細</div><div class="col-3 fw-bold text-end">總計：
	            		<% 
	            			List<Map<String, Object>> userOrderDetailData = (List<Map<String, Object>>) request.getAttribute("userOrderDetailData");
		            		int totalPrice = 0;
		            		for (Object userItem : userOrderDetailData) {
		            			int userItemPrice = (Integer) ((Map) userItem).get("userItemPrice");
		            			totalPrice += userItemPrice;
		            		}
	            		%>
	            		<%=totalPrice%>元</div>
	          		</button>
	        	</h2>
	        	<!-- Accordion head end -->
	        	<div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#userOrder" style="">
	          		<!-- Accordion body start -->
		          	<div class="accordion-body">
			            <ul class="list-group">
							<!-- Column names start -->
							<li class="list-group-item d-flex justify-content-between align-items-start">
				                <div class="ms-2 me-auto col-7"><div class="fw-bold">品名</div></div>
				                <div class="col-3"><span class="fw-bold">數量</span></div>
				                <div class="col-2"><span class="fw-bold">小計</span></div>
			                </li>
			                <!-- Column names end -->
			                <!-- User items start -->
			                <c:forEach var="userItem" items="${userOrderDetailData}">
			                <li class="list-group-item d-flex justify-content-between align-items-start">
			                  	<div class="ms-2 me-auto col-7"><div class="fw-bold">${userItem.productName}</div>
									<c:forEach var="productVary" items="${userItem.productVaryList}">
									<div class="ms-2">${productVary}</div>
									</c:forEach>
								</div>
			                	<div class="col-3"><span class="badge bg-dark rounded-pill">${userItem.productQuantity}</span></div>
			                	<div class="col-2"><span class="">${userItem.userItemPrice}元</span></div>
			                </li>
			                </c:forEach>
			                <!-- User items end -->
			            </ul>
					</div>
					<!-- Accordion body end -->
	        	</div>
			</div>
		</div>
		<!-- Accordion end -->   
		</c:when>
		<c:when test="${not empty sessionScope.loginUserInfo and sessionScope.userIsGroupMember and empty userOrderDetailData}">
			<!-- Accordion start -->
		<div class="accordion w-50" id="userOrder">
	    	<div class="accordion-item">
	        	<!-- Accordion head start -->
	        	<h2 class="accordion-header" id="headingOne">
	          		<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
	            		<div class="col-8">您已付款的訂購明細</div><div class="col-3 fw-bold text-end">總計：0元</div>
	          		</button>
	        	</h2>
	        	<!-- Accordion head end -->
	        	<div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#userOrder" style="">
	          		<!-- Accordion body start -->
		          	<div class="accordion-body">
			            <ul class="list-group">
							<!-- Column names start -->
							<li class="list-group-item d-flex justify-content-between align-items-start">
				                <div class="ms-2 me-auto col-7"><div class="fw-bold">品名</div></div>
				                <div class="col-3"><span class="fw-bold">數量</span></div>
				                <div class="col-2"><span class="fw-bold">小計</span></div>
			                </li>
			                <!-- Column names end -->
			            </ul>
					</div>
					<!-- Accordion body end -->
	        	</div>
			</div>
		</div>
		</c:when>
	</c:choose>
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
				<form id="modal_form">
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
						<input type="hidden" id="modal_group_orderID" name="groupOrderID" value="${groupOrderData.groupOrderID}"></input>
						<input type="hidden" id="modal_dinerID" name="dinerID" value="${groupOrderData.dinerID}"></input>
						<input type="hidden" id="modal_productID" name="productID" value="0" data-productid="0"></input>
						<h2 id="modal_product_name"></h2>
	
						<!-- 餐點簡介段落 -->
						<p id="modal_product_remark"></p>
	
						<!--  -->
						<div class="d-flex justify-content-between">
							<span>價格:</span> <span> <span id="modal_product_price"></span> <span>元</span>
							</span>
						</div>
	
						<!-- 餐點選項區塊 -->
						<div class="my-2">
							<div id="modal_vary_types">
								<%-- HTML inside this div will be generated by AJAX request --%>
							</div>
							
						</div>
		
						<!-- 份數:下拉選單 -->
						<div class="my-2 modal_product_quantity">
							<div class="form-outline">
								<span>份數:</span> 
							    <input type="number" id="modal_product_quantity" class="form-control" name="quantity" value="1" step="1" min="1" max="99">
							</div>
						</div>
			
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
							<a class="btn btn-dark" href="${pageContext.request.contextPath}/GroupOrder.do?action=join&groupOrderID=${groupOrderData.groupOrderID}">登入</a>
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
									<button type="button" class="btn btn-dark" id="add_to_cart">加到購物車</button>
									</div>
					            </c:otherwise>
					        </c:choose>
					    </c:otherwise>
					</c:choose>

				</div>
			</form>
			</div>
		</div>
	</div>
	
	<!-- Shopping cart offcanvas start -->
<div class="offcanvas offcanvas-end" data-bs-scroll="true" tabindex="-1" id="shopping_cart">
	<!--Offcanvas header start -->
	<div class="offcanvas-header">
		<h4 id="">購物車</h4>
		<button type="button" class="btn-close text-reset"
			data-bs-dismiss="offcanvas" aria-label="Close"></button>
	</div>
	<!-- Offcanvas header end -->
	<!-- Offcanvas body start -->
	<div class="offcanvas-body">
		<div class="px-1 py-0 my-0">
			<h5>${groupOrderData.buildingName}</h5>
			<span class="fs-6">大樓地址：${groupOrderData.buildingAddress}<br>
				商家：${groupOrderData.dinerName}
			</span>
			<ul class="list-group px-0">
				
				<c:forEach items="${cartData}" var="item">
				  <li class="list-group-item px-1">
				    <div class="row">
				      <div class="col-5">
				        <h6 class="mb-0">${item.productName}</h6>
				        <p class="mb-0 ms-2 text-muted">
				          <c:forEach items="${item.productVaryDess}" var="productVaryDes">
				            ${productVaryDes}<br/>
				          </c:forEach>
					        </p>
					      </div>
					      <div class="col-4">
					        <div class="input-group">
					          <input type="number" class="form-control form-control-sm disabled" min="1" value="${item.quantity}" disabled>
					          
					    	    </div>
					      </div>
					    	  <div class="col-3">
					   	     <span class="fs-6">${item.productAndVaryPrice}元</span>
					   	   </div>
					  	  </div>
					 	 </li>
				</c:forEach>
			
<!-- 				<li class="list-group-item px-1"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-5"> -->
<!-- 							<h6 class="mb-0">蒜味香腸烤肉飯</h6> -->
<!-- 							<p class="mb-0 ms-2 text-muted">加荷包蛋</p> -->
<!-- 						</div> -->
<!-- 						<div class="col-4"> -->
<!-- 							<div class="input-group"> -->
<!-- 								<input type="number" class="form-control form-control-sm" -->
<!-- 									min="1" value="1" disabled> -->
<!-- 								<button class="btn btn-outline-secondary disabled" type="button"> -->
<!-- 									<i class="fas fa-trash"></i> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-3"> -->
<!-- 							<span class="fs-6">150元</span> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</li> -->

<!-- 				<li class="list-group-item px-1"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-5"> -->
<!-- 							<h6 class="mb-0">蒜味香腸烤肉飯</h6> -->
<!-- 							<p class="mb-0 ms-2 text-muted"></p> -->
<!-- 						</div> -->
<!-- 						<div class="col-4"> -->
<!-- 							<div class="input-group"> -->
<!-- 								<input type="number" class="form-control form-control-sm" -->
<!-- 									min="1" value="1" disabled> -->
<!-- 								<button class="btn btn-outline-secondary disabled" type="button"> -->
<!-- 									<i class="fas fa-trash"></i> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-3"> -->
<!-- 							<span class="fs-6">140元</span> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</li> -->
			</ul>
		</div>

		<div class="mt-2 text-end">
			<p class="fw-bold" id="cart_total">總計元</p>
		</div>

		<div class="mt-4 col-10 mx-auto">
			<div class="my-2">
				<a href="${pageContext.request.contextPath}/GroupOrder.do?action=checkout&groupOrderID=${groupOrderData.groupOrderID}&dinerID=${groupOrderData.dinerID}" class="btn btn-dark btn-lg w-100">付款</a>
			</div>
			<div class="my-2">
				<a href="#" class="btn btn-outline-dark btn-lg w-100" data-bs-dismiss="offcanvas">繼續點餐</a>
			</div>
			<div class="my-2">
				<a href="${pageContext.request.contextPath}/GroupOrder.do?action=clearCart&groupOrderID=${groupOrderData.groupOrderID}&dinerID=${groupOrderData.dinerID}" class="btn btn-outline-dark btn-lg w-100">清空購物車</a>
			</div>
		</div>
	</div>
	<!-- Offcanvas body end -->
</div>
<!-- Shopping cart offcanvas end -->
	
	<%-- Page content end --%>


	<%-- The footer --%>
	<jsp:include page="./components/footer.jsp"></jsp:include>


	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>
	<script src="${pageContext.request.contextPath}/consumer/js/orderDetailModal.js"></script>

</body>
</html>