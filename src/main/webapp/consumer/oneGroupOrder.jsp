<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%
Map<String, Object> groupOrderData = (Map<String, Object>) request.getAttribute("groupOrderData");

%>
<%@ include file="components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>


<title>樓頂揪樓咖-大樓揪團：${groupOrderData.buildingName} x
	${groupOrderData.dinerName}</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<div class="container">
		<div class="card">
			<div class="row g-0 align-items-center">
				<div class="col-5 h-100">
					<img
						src="/JavaG4/GroupOrderDinerImage?id=${groupOrderData.groupOrderID}"
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

<!-- service做兩個DAO -->
<!-- 第一個DAO是上面的GroupOrder相關 -->
<!-- 第二個DAO是下面的菜單需要的Product相關	 -->
<!-- service方法getOneJoinGroupOrder的回傳值裡面繼續增加 -->
<!-- 	LinkedMap<商品類別, LinkedMap<productID, array>> -->

<!-- 類別1 -->
<!--   品名1  價格1 -->
<!--   品名2  價格2 -->

<!-- 類別2 -->
<!--   品名3  價格3 -->
<!--   品名4  價格4 -->


<!-- 一個row放三個商品 -->
<!-- index % 3 == 0 ==> new row -->
	
	
	<section class="container mt-4">
		<h2>菜單</h2>
		<div>
			<h4>便當</h4>
			<div class="row">
				<div class="col-4">
					<div class="card">
						<div>
							<img
								src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&amp;cs=tinysrgb&amp;w=1260&amp;h=750&amp;dpr=1"
								class="card-img" alt="...">
						</div>
						<div>
							<div class="card-body">
								<h5 class="mealName">雞腿飯</h5>
								<ul class="list-unstyled card-text">
									<li>價格：NT$<span class="mealPrice">80</span></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<%-- Page content end --%>


	<%-- The footer --%>
	<jsp:include page="./components/footer.jsp"></jsp:include>


	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>

</body>
</html>