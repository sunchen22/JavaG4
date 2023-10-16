<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%
HashMap<String, Object> groupOrderData = (HashMap<String, Object>) request.getAttribute("groupOrderData");
LinkedHashMap<String, List<HashMap<String, Object>>> menuData = (LinkedHashMap<String, List<HashMap<String, Object>>>) request
		.getAttribute("menuData");
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
							<li>付款截止時間：${groupOrderData.groupOrderSubmitTime}</li>
							<li>
								<div class="progress">
									<div class="progress-bar bg-dark" role="progressbar"
										style="width: 25%;" aria-valuenow="25" aria-valuemin="0"
										aria-valuemax="100">25%</div>
								</div>
							</li>
						</ul>
						<div class="d-grid gap-2 d-flex justify-content-end">
							<a class="btn btn-dark">加入此大樓揪團</a><a class="btn btn-dark">點餐</a><a
								class="btn btn-dark">付款</a>
						</div>
						<i
							class="fa-solid fa-share-from-square fs-4 position-absolute top-0 end-0 m-3"></i>
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
					<div class="mdl-stepper-step">
						<div class="mdl-stepper-circle">
							<span>1</span>
						</div>
						<div class="mdl-stepper-title">加入此大樓揪團</div>
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
						<div class="card">
							<div>
								<img
									src="${pageContext.request.contextPath}/GroupOrderDinerImage?entity=Product&id=${product.productID}"
									class="card-img-top" alt="...">
							</div>
							<div>
								<div class="card-body">
									<h5 class="mealName">${product.productName}</h5>
									<ul class="list-unstyled card-text my-0">
										<li>價格：NT$<span class="mealPrice">${product.productPrice}</span></li>
									</ul>
									<a class="stretched-link .invisible" href="#" data-bs-toggle="modal" data-bs-target="#order_detail_modal"></a>
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
				<div class="modal-header p-0">
					<!-- 餐點圖片 可輪播 -->
					<div id="carouselExampleIndicators" class="carousel carousel-orderdetail slide my-3 mx-auto" data-bs-ride="carousel">
						<div class="carousel-indicators">
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="0" class=""></button>
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="1" class="active" aria-current="true"></button>
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="2" class=""></button>
						</div>
						<div class="carousel-inner mx-auto">
							<div class="carousel-item carousel-item-orderdetail">
								<img
									src="https://images.pexels.com/photos/5692293/pexels-photo-5692293.jpeg"
									class="d-block w-100" alt="餐點圖">
							</div>
							<div class="carousel-item carousel-item-orderdetail active">
								<img
									src="https://pic.616pic.com/ys_bnew_img/00/28/05/7NPYpgndQS.jpg"
									class="d-block w-100" alt="餐點圖">
							</div>
							<div class="carousel-item carousel-item-orderdetail">
								<img
									src="https://pic.616pic.com/ys_bnew_img/00/27/71/Nc5vgPgVuL.jpg"
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
				</div>
				<div class="modal-body mx-2">
					<!-- 餐點名稱 -->
					<h2 class="mealName">排骨飯</h2>
	
					<!-- 餐點簡介段落 -->
					<p class="mealDes">溫體豬製成</p>
	
					<!--  -->
					<div class="d-flex justify-content-between">
						<span>價格:</span> <span> <span id="price">150</span> <span>元</span>
						</span>
					</div>
	
					<!-- 餐點選項區塊 -->
					<div class="my-2">
						<input type="checkbox" class="form-check-input" id="rice">
						<label class="form-check-label" for="rice">加白飯 0元</label>
					</div>
					<div class="my-2">
						<input type="checkbox" class="form-check-input" id="egg"> <label
							class="form-check-label" for="egg">加荷包蛋 10元</label>
					</div>
	
					<!-- 份數:下拉選單 -->
					<div class="my-2">
						<label for="quantity">份數:</label> <select class="form-select"
							id="quantity">
							<option selected="" value="1">1份</option>
							<option value="2">2份</option>
							<option value="3">3份</option>
							<option value="4">4份</option>
						</select>
					</div>
	
	
					<!-- 後續補充js -->
	
					<div class="d-flex justify-content-between mt-3">
						<label for="subtotal">小計:</label> <span id="subtotal">160</span>
					</div>
				</div>
			
				<div class="modal-footer">
					<!-- 取消 加到購物車 -->
					<div class="d-flex justify-content-between mt-3">
						<button type="button" class="btn btn-outline-danger">取消</button>
						<button type="button" class="btn btn-primary">加到購物車</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%-- Page content end --%>


	<%-- The footer --%>
	<jsp:include page="./components/footer.jsp"></jsp:include>


	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>

</body>
</html>