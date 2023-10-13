<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>


<title>樓頂揪樓咖-重設密碼</title>
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
					<b>重設密碼</b>
				</p>
			</div>
			<div class="card-body">
				<form action="#" method="post">
					<div class="row mb-3">
						<label for="oldPwd" class="col-form-label col-md-4">舊密碼：</label>
						<div class="col-md-8">
							<input type="password" class="form-control" id="oldPwd"
								maxlength="20" placeholder="請輸入舊密碼">
						</div>
					</div>
					<div class="row mb-3">
						<label for="newPwd" class="col-form-label col-md-4">新密碼：</label>
						<div class="col-md-8">
							<input type="password" class="form-control" id="newPwd"
								maxlength="20" placeholder="請輸入新密碼">
						</div>
					</div>
					<div class="row mb-3">
						<label for="pwdAgain" class="col-form-label col-md-4">確認新密碼：</label>
						<div class="col-md-8">
							<input type="password" class="form-control" id="pwdAgain"
								maxlength="20" placeholder="請再次輸入新密碼">
						</div>
					</div>

					<div class="d-grid">
						<button type="submit" class="btn btn-primary">確認修改</button>
					</div>
				</form>

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