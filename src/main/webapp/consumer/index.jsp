<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="components/head.jsp"%>
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
				<div class="carousel-item active">
					<img
						src="https://images.pexels.com/photos/15792419/pexels-photo-15792419/free-photo-of-variety-of-food-on-wooden-table.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img
						src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img
						src="https://upload.wikimedia.org/wikipedia/en/c/c4/Food_Platter_at_Bar_One_Cafe_Chenab_2022.jpg"
						class="d-block w-100" alt="...">
				</div>
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
				class="row gx-4 gx-lg-5 row-cols-1 row-cols-md-1 row-cols-xl-1 justify-content-center">
				<div class="col mb-1">
					<div class="card">
						<div class="card-body">
							<div class="text-left">最新消息：訴後已，交因最重個工受內異力間局上文、天都進境界食現林半，對我投特資得維質到的太。不了前滿員以產談半毛不管，也的小。</div>
						</div>
					</div>
				</div>
				<div class="col mb-1">
					<div class="card">
						<div class="card-body">
							<div class="text-left">最新消息：訴後已，交因最重個工受內異力間局上文、天都進境界食現林半，對我投特資得。</div>
						</div>
					</div>
				</div>
				<div class="col mb-1">
					<div class="card">
						<div class="card-body">
							<div class="text-left">最新消息：訴後已，交因最重個工受內異力</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Section-->
	<section class="">
		<div class="container px-4 px-md-5">

			<h1>現在揪團中</h1>

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
		</div>


	</section>

	<%-- Page content end --%>

	<jsp:include page="./components/footer.jsp"></jsp:include>
	<%-- The footer --%>


	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>

	

</body>
</html>
