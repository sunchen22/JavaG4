<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>

<title>樓頂揪樓咖-忘記密碼</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<section class="container mt-4">
		<div class="card mx-auto col-4 ">
			<div class="card-header text-center">
				<p class="h1">
					<b>忘記密碼</b>
				</p>
			</div>
			<div class="card-body">
				<form action="#" method="post">
					<div class="input-group mb-3">
						<input type="email" class="form-control" placeholder="請輸入帳號Email">
						<div class="input-group-append">
							<div class="input-group-text h-100">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="請輸入手機號碼">
						<div class="input-group-append">
							<div class="input-group-text h-100">
								<span class="fa-solid fa-phone"></span>
							</div>
						</div>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary btn-block">取得驗證碼</button>
						<p class="mt-3">驗證碼將由簡訊發出</p>
					</div>
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="請輸入驗證碼">
						<div class="input-group-append"></div>
					</div>
					<div class="text-center">
						<button type="button" onclick="location.href='${pageContext.request.contextPath}/consumer/ResetPwd.jsp';"
							class="btn btn-primary btn-block">送出驗證碼</button>
					</div>
				</form>
			</div>
		</div>

	</section>


	<%-- Page content end --%>

	<jsp:include page="./components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="./components/tail.jsp"%>
	

</body>
</html>