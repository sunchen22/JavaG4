<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="components/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.userinfo.entity.*"%>


<link href="./css/style.css" rel="stylesheet">


<title>樓頂揪樓咖-消費者註冊</title>

</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<div class="container mt-5">

		<div class="card p-4 mx-auto" style="width: 500px;">
			<h2 class="mb-4 text-center">請填寫以下註冊資訊</h2>
			
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<form method="post" action="user.do" enctype="multipart/form-data">
				<div class="row mb-3">
					<label for="userAccount_input" class="col-form-label col-md-3">帳號Mail：</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="userAccount_input"
							placeholder="請輸入email" maxlength="50" name="userAccount">
					</div>
				</div>
				<div class="row mb-3">
					<label for="pwd_input" class="col-form-label col-md-3">設定密碼：</label>
					<div class="col-md-9">
						<input type="password" class="form-control pwd" id="pwd_input"
							placeholder="請設定6-10位密碼" maxlength="10" name="userPassword">
					</div>
				</div>
				<div class="row mb-3">
					<label for="pwd_inputAgain" class="col-form-label col-md-3">密碼確認：</label>
					<div class="col-md-9">
						<input type="password" class="form-control pwd"
							name="userPasswordAgain" id="pwd_inputAgain"
							placeholder="請再輸入一次密碼" maxlength="10">
						<div class="form-check mt-2">
							<input class="form-check-input" type="checkbox"
								id="pwdmask" onchange="togglePwdDisplay()"> <label
								class="form-check-label" for="pwdmask">顯示密碼</label>
						</div>
					</div>
				</div>
				<div class="row mb-3">
					<label for="userName_input" class="col-form-label col-md-3">姓名：</label>
					<div class="col-9">
						<input type="text" class="form-control" id="userName_input"
							name="userName" placeholder="真實中文姓名:劉德華" maxlength="20">
					</div>
				</div>
				<div class="row mb-3">
					<label for="nickName_input" class="col-form-label col-md-3">暱稱：</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="nickName_input"
							name="userNickName" placeholder="老劉" maxlength="20">
					</div>
				</div>
				<div class="row mb-3">
					<label for="userBirthday_input" class="col-form-label col-md-3">生日：</label>
					<div class="col-md-9">
						<input type="date" class="form-control" id="userBirthday_input"
							name="userBirthday">
					</div>
				</div>
				<div class="row mb-3">
					<label for="userphone_input" class="col-form-label col-md-3">手機號碼：</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="userphone_input"
							name="userPhone" placeholder="請輸入手機號碼" maxlength="10">
					</div>
				</div>
				<div class="row mb-3">
					<label for="buildingID" class="col-form-label col-md-3">常用大樓：</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="buildingID"
							name="buildingID" placeholder="請選擇常用大樓" maxlength="10">
					</div>
				</div>
				<div class="row mb-3">
					<label for="userBlob" class="col-form-label col-md-3">個人照片：</label>
					<div class="col-md-9">
						<input type="file" class="form-control" id="userBlob"
							name="userBlob" placeholder="請上傳個人照片">
					</div>
				</div>
				<div class="d-grid">
					<input type="hidden" name="action" value="registration">
					<input type="submit" class="btn btn-primary" value="註冊">
				</div>
			</form>
			<div class="mt-3 text-center">
				<a href="Login.jsp">我已經有帳號了</a>
			</div>
		</div>
	</div>


	<%-- Page content end --%>

	<jsp:include page="./components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>
	<script>
		var script = document.createElement('script');
		script.src = './vendor/bootstrap-5.3.1-dist/js/bootstrap.bundle.min.js';
		document.head.appendChild(script);
	</script>
	<script src="./js/all.js"></script>
	<script src="./vendor/jquery/jquery-3.7.1.min.js"></script>
	<script>
		function togglePwdDisplay() {
			$(".pwd").each(function() {
				if ($(this).attr("type") === "password") {
					$(this).attr("type", "text");
				} else {
					$(this).attr("type", "password");
				}
			});
		}
	</script>

</body>
</html>