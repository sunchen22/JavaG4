<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>



<title>樓頂揪樓咖-訂單明細</title>
</head>

<body>
	<jsp:include page="../components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<div class="container mt-4 col-8 shadow rounded p-2">
		<h1 class="text-center">訂單明細</h1>

		<!-- 歷史訂單區 -->

		<div
			class="row d-flex justify-content-around row border border-dark p-1 rounded bg-warning bg-gradient mx-1">
			<div class="col">
				<p>
					訂單: <span>12345</span>
				</p>
				<p>2023-09-27</p>
				<p>美味餐廳</p>
			</div>

			<div class="col-4">
				<div class="row">
					<span class="col">漢堡</span> <span class="col">2份</span> <span
						class="col">$200</span>
				</div>
				<div class="row">
					<span class="col">漢堡ABCD</span> <span class="col">2份</span> <span
						class="col">$200</span>
				</div>
				<div class="row">
					<span class="col">漢堡22</span> <span class="col">2份</span> <span
						class="col">$200</span>
				</div>

			</div>

			<div class="col d-flex align-items-center justify-content-center">
				<div
					class="inline-block bg-secondary bg-gradient text-center rounded-pill px-5">已送達</div>
			</div>

			<div class="col d-flex flex-column">
				<button class="btn btn-outline-success mb-1"
					onclick="window.location.href='ConsumerOrder.html'">回到訂單</button>
				<button class="btn btn-outline-success mb-1"
					onclick="window.location.href='DinerComment.html'">評論</button>
				<button class="btn btn-outline-success mb-1"
					onclick="window.location.href='Feedback.html'">意見反饋</button>
				<button class="btn btn-outline-success mb-1"
					onclick="window.location.href='ChatRoom.html'">聯繫客服</button>
			</div>

		</div>
		<h3 class="text-center">
			<br>已送達照片
			</h6>
			<div class="d-flex justify-content-center">
				<img src="https://pic.pimg.tw/evonneshih/1382864699-1593795877.jpg"
					alt="送達照片">
			</div>
	</div>

	<%-- Page content end --%>

	<jsp:include page="../components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="../components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>


</body>
</html>