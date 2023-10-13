<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>

<link rel="stylesheet"
	href="./vendor/bootstrap-5.3.1-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="./vendor/fontawesome-free-6.4.2-web/css/all.min.css">

<title>樓頂揪樓咖-個別商家資訊</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<!-- 個別商家資訊 -->
	<section class="container mt-3">
		<h1 class="text-center">餐廳介紹</h1>
		<div class="card">
			<div class="row g-0 align-items-center">
				<div class="col-4">
					<img
						src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
						class="card-img" alt="...">
				</div>
				<div class="col-8">
					<div class="card-body">
						<h5 class="card-title">ONE GOOD烤肉飯</h5>
						<ul class="list-unstyled card-text">
							<li class="list-inline-item"><span
								class="badge fs-6 rounded-pill bg-secondary">11:00~13:00</span></li>
							<li class="list-inline-item"><span
								class="badge fs-6 rounded-pill bg-secondary"><i
									class="fa-solid fa-utensils"></i></span></li>
							<li class="list-inline-item"><span
								class="badge fs-6 rounded-pill bg-secondary"><i
									class="fa-solid fa-star"></i>4.5</span></li>
							<li><span>可外送大樓：</span><span>宏春、揚昇金融</span></li>
							<li><span>成團條件：</span><span>1500</span></li>
							<li>配送時間：營業時間內接單後1小時內送達</li>
						</ul>
						<div class="d-grid gap-2 d-flex justify-content-end">
							<a class="btn btn-dark fs-6"><i
								class="fa-solid fa-magnifying-glass"></i>現有揪團</a><a
								class="btn btn-dark fs-6"><i class="fa-solid fa-users"></i>發起揪團</a>
						</div>
						<i type="button"
							class="heartBtn fs-4 position-absolute top-0 end-0 m-3 fa-regular fa-heart"></i>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- 該商家目前揪團 -->
	<section class="container mt-5">
		<h2 class="text-center">現在揪團中的大樓</h2>
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="row g-0 align-items-center">
						<div class="col-4 ">
							<img
								src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
								class="card-img" alt="...">
						</div>
						<div class="col-8">
							<div class="card-body">
								<h5 class="card-title">宏春大樓</h5>
								<ul class="list-unstyled card-text">
									<li><span>大樓地址：</span><span>台北市中山區南京東路三段219號</span></li>
									<li>ONE GOOD烤肉飯</li>
									<li>團主：會員暱稱</li>
									<li class="list-inline-item">成團條件：1500元</li>
									<li class="list-inline-item">成團狀態：尚未達成</li>
									<li>付款截止時間：今日11:30</li>
									<div class="d-flex justify-content-end">
										<a class="btn btn-dark">加入此大樓揪團</a>
									</div>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<div class="row g-0 align-items-center">
						<div class="col-4 ">
							<img
								src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
								class="card-img" alt="...">
						</div>
						<div class="col-8">
							<div class="card-body">
								<h5 class="card-title">宏春大樓</h5>
								<ul class="list-unstyled card-text">
									<li>大樓地址：台北市中山區南京東路三段219號</li>
									<li>ONE GOOD烤肉飯</li>
									<li>團主：會員暱稱</li>
									<li class="list-inline-item">成團條件：1500元</li>
									<li class="list-inline-item">成團狀態：尚未達成</li>
									<li>付款截止時間：今日11:30</li>
									<div class="d-flex justify-content-end">
										<a class="btn btn-dark">加入此大樓揪團</a>
									</div>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="container mt-4">
		<h2 class="text-center">菜單</h2>
		<h4>便當</h4>
		<div class="row mb-5">
			<div class="col">
				<div class="card">
					<div>
						<img
							src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
							class="card-img" alt="...">
					</div>
					<div>
						<div class="card-body">
							<h5 class="mealName">雞腿飯</h5>
							<ul class="list-unstyled card-text">
								<li>價格：NT$<span class="mealPrice">80</span></li>
						</div>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<div>
						<img
							src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
							class="card-img" alt="...">
					</div>
					<div>
						<div class="card-body">
							<h5 class="mealName">雞腿飯</h5>
							<ul class="list-unstyled card-text">
								<li>價格：NT$<span class="mealPrice">80</span></li>
						</div>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<div>
						<img
							src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
							class="card-img" alt="...">
					</div>
					<div>
						<div class="card-body">
							<h5 class="mealName">雞腿飯</h5>
							<ul class="list-unstyled card-text">
								<li>價格：NT$<span class="mealPrice">80</span></li>
						</div>
					</div>
				</div>
			</div>
		</div>

		<h4>飲料</h4>
		<div class="row mb-2">
			<div class="col">
				<div class="card">
					<div>
						<img
							src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
							class="card-img" alt="...">
					</div>
					<div>
						<div class="card-body">
							<h5 class="mealName">雞腿飯</h5>
							<ul class="list-unstyled card-text">
								<li>價格：NT$<span class="mealPrice">80</span></li>
						</div>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<div>
						<img
							src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
							class="card-img" alt="...">
					</div>
					<div>
						<div class="card-body">
							<h5 class="mealName">雞腿飯</h5>
							<ul class="list-unstyled card-text">
								<li>價格：NT$<span class="mealPrice">80</span></li>
						</div>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<div>
						<img
							src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
							class="card-img" alt="...">
					</div>
					<div>
						<div class="card-body">
							<h5 class="mealName">雞腿飯</h5>
							<ul class="list-unstyled card-text">
								<li>價格：NT$<span class="mealPrice">80</span></li>
						</div>
					</div>
				</div>
			</div>
		</div>


	</section>

	<%-- Page content end --%>

	<jsp:include page="./components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>
	<script src="./vendor/jquery/jquery-3.7.1.min.js"></script>
	<!-- <script src="./vendor/bootstrap-5.3.1-dist/js/bootstrap.bundle.js"></script> -->

	<script>
		//愛心切換加入最愛商家
		$(function() {
			$('.heartBtn').click(function() {
				if ($(this).hasClass('fa-regular')) {
					$(this).removeClass('fa-regular').addClass('fa-solid');
					$(this).removeClass('fa-heart').addClass('fa-heart');
					alert('已加入最愛商家');
				} else if ($(this).hasClass('fa-solid')) {
					$(this).removeClass('fa-solid').addClass('fa-regular');
					$(this).removeClass('fa-heart').addClass('fa-heart');
					alert('已取消最愛商家');
				}
			});
		});
	</script>

</body>
</html>