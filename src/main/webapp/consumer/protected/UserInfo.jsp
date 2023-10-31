<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>
<%@ page import="com.userinfo.entity.*"%>
<%@ page import="com.userinfo.dao.*"%>

<!-- 先取出BuildingInfo List以供常用大樓選單使用 -->
<%@ page import="com.buildinginfo.entity.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.buildinginfo.dao.*"%>




<title>樓頂揪樓咖-消費者個人資訊</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="../components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<div class="container col-7">
		<div class="header h2 text-center mb-3 mt-5">會員中心</div>
		<div class="row">
			<div class="sidebar col-3">
				<div class="basicInfo">
					<p></p>

				</div>
				<div class="tab-List">


					<!-- tab部分 -->
					<ul class="nav flex-column nav-pills" id="v-pills-tab">

						<li class="nav-item"><a class="nav-link active"
							id="v-pills-info-tab" data-bs-toggle="pill" href="#v-pills-info">基本資訊</a>
						</li>
						<li class="nav-item"><a class="nav-link" id="v-pills-pwd-tab"
							data-bs-toggle="pill" href="#v-pills-pwd">密碼修改</a></li>
<!-- 							最愛餐廳先隱藏 -->
<!-- 						<li class="nav-item"><a class="nav-link" -->
<!-- 							id="v-pills-favor-tab" data-bs-toggle="pill" -->
<!-- 							href="#v-pills-favor">最愛餐廳</a></li> -->
					</ul>
				</div>
			</div>
			<!-- 內容面板 -->
			<div class="tab-content col-9" id="v-pills-tabContent">
				<div class="tab-pane fade show active" id="v-pills-info">
					<!--檢查是否更新成功 -->
					<%
					if (request.getAttribute("isUpdate") != null) {
					%>
					<div class="alert alert-primary" role="alert">更新成功！</div>
					<%
					}
					%>
					<form method="post" action="${pageContext.request.contextPath}/user.do" enctype="multipart/form-data">
						<div class="row mb-3">
							<label for="userAccount" class="col-form-label col-md-3">帳號：</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="userAccount"
									maxlength="20" value="${loginUserInfo.userAccount}"
									name="userAccount" readonly>
							</div>
						</div>
						<div class="row mb-3">
							<label for="userName" class="col-form-label col-md-3">姓名：</label>
							<div class="col-md-9">
								<input type="hidden" name="userID"
									value="${loginUserInfo.userID}"> 
								<input type="text"
									class="form-control" id="userName" maxlength="20"
									value="${loginUserInfo.userName}" name="userName">
							</div>
						</div>
						<div class="row mb-3">
							<label for="nickName" class="col-form-label col-md-3">暱稱：</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="nickName"
									name="userNickName" value="${loginUserInfo.userNickName}"
									maxlength="20">
							</div>
						</div>
						<div class="row mb-3">
							<label for="userphone" class="col-form-label col-md-3">手機號碼：</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="userPhone"
									id="userphone" value="${loginUserInfo.userPhone}"
									maxlength="10">
							</div>
						</div>
						<div class="row mb-3">
							<label for="building" class="col-form-label col-md-3">常用大樓：</label>
							<div class="col-md-9">

								<select class="form-control" id="buildingID" name="buildingID">
									<%
									BuildingInfoDAO buildingInfoDAO = new BuildingInfoDAOHibernateImpl();
									List<BuildingInfo> buildingList = buildingInfoDAO.getAll();
									pageContext.setAttribute("buildingList", buildingList);
									%>
									<c:forEach var="building" items="${buildingList}">
										<option value="${building.buildingID}"
											${(loginUserInfo.buildinginfo.buildingID == building.buildingID)? 'selected':''}>${building.buildingName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row mb-3">
							<label for="userBirthday" class="col-form-label col-md-3">生日：</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="userBirthday"
									value="${loginUserInfo.userBirthday}" readonly>
							</div>
						</div>
						<div class="row mb-3">
							<label for="userBlob" class="col-form-label col-md-3">個人照片：</label>
							<div class="col-md-9">
								<input type="file" class="form-control" id="userBlob"
									name="userBlob" placeholder="請上傳個人照片"
									onchange="previewImage(event)">
<%-- 								 將byte[]轉換為Base64編碼的字符串 --%>
								<% 
// 								UserInfo loginUserInfo = (UserInfo) session.getAttribute("loginUserInfo");
// 								String base64Image = "";
// 								if (loginUserInfo != null) {
// 									byte[] userBlobData = loginUserInfo.getUserBlob();
// 									if (userBlobData != null) {
// 										base64Image = Base64.getEncoder().encodeToString(userBlobData);
// 									}
// 								}
 								%> 
								<img id="previewBlob" alt="Image preview"
									style="margin-top: 10px; max-width: 200px;"
									src="<%=request.getContextPath()%>/consumer/userDBGifReader?userID=${loginUserInfo.userID}"
<%-- 									src="<%=!base64Image.isEmpty() ? "data:image/jpeg;base64," + base64Image : ""%>"  --%>
									/>

							</div>
						</div>
						<div class="d-grid">
							<input type="hidden" name="action" value="update">
							<button type="submit" class="btn btn-primary">確認修改</button>
						</div>
					</form>
				</div>


				<div class="tab-pane fade" id="v-pills-pwd">
					<form method="post" action="${pageContext.request.contextPath}/user.do">
						<div class="row mb-3">
							<label for="oldPwd" class="col-form-label col-md-4">舊密碼：</label>
							<div class="col-md-8">
								<input type="password" class="form-control" id="oldPwd"
									maxlength="20" placeholder="請輸入舊密碼" name="oldPwd">
							</div>
						</div>
						<div class="row mb-3">
							<label for="newPwd" class="col-form-label col-md-4">新密碼：</label>
							<div class="col-md-8">
								<input type="password" class="form-control" id="newPwd"
									maxlength="20" placeholder="請輸入新密碼" name="newPwd">
							</div>
						</div>
						<div class="row mb-3">
							<label for="pwdAgain" class="col-form-label col-md-4">確認新密碼：</label>
							<div class="col-md-8">
								<input type="password" class="form-control" id="newPwdAgain"
									maxlength="20" placeholder="請再次輸入新密碼" name="newPwdAgain">
							</div>
						</div>

						<div class="d-grid">
							<input type="hidden" name="action" value="resetPwd">
							<button type="submit" class="btn btn-primary">確認修改</button>
						</div>
					</form>
				</div>


				<div class="tab-pane fade" id="v-pills-favor">
					<section class="container">

						<div class="row row-cols-1 row-cols-1 g-4">
							<div class="col">

								<div class="card">
									<div class="row g-0 align-items-center">
										<div class="col-5 ">
											<img
												src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
												class="card-img" alt="...">
										</div>
										<div class="col-7">
											<div class="card-body">
												<h5 class="card-title">ONE GOOD烤肉飯</h5>
												<ul class="list-unstyled card-text">
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary">11:00~13:00</span>
													</li>
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary"><i
															class="fa-solid fa-utensils"></i></span></li>
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary"><i
															class="fa-solid fa-star"></i>4.5</span></li>
													<li>外送大樓：宏春、揚昇金融</li>
													<li>成團條件：1500元</li>
													<li>營業時間內接單後1小時內送達</li>
												</ul>
												<div class="d-grid gap-2 d-flex justify-content-end">
													<a class="btn btn-dark fs-6"><i
														class="fa-solid fa-magnifying-glass"></i>現有揪團</a><a
														class="btn btn-dark fs-6"><i class="fa-solid fa-users"></i>發起揪團</a>
												</div>
												<i type="button"
													class="heartBtn fa-solid fa-heart fs-4 position-absolute top-0 end-0 m-3"></i>
											</div>
										</div>
									</div>
								</div>

							</div>

							<div class="col">

								<div class="card">
									<div class="row g-0 align-items-center">
										<div class="col-5 ">
											<img
												src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
												class="card-img" alt="...">
										</div>
										<div class="col-7">
											<div class="card-body">
												<h5 class="card-title">ONE GOOD烤肉飯</h5>
												<ul class="list-unstyled card-text">
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary">11:00~13:00</span>
													</li>
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary"><i
															class="fa-solid fa-utensils"></i></span></li>
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary"><i
															class="fa-solid fa-star"></i>4.5</span></li>
													<li>外送大樓：宏春、揚昇金融</li>
													<li>成團條件：1500元</li>
													<li>營業時間內接單後1小時內送達</li>
												</ul>
												<div class="d-grid gap-2 d-flex justify-content-end">
													<a class="btn btn-dark fs-6"><i
														class="fa-solid fa-magnifying-glass"></i>現有揪團</a><a
														class="btn btn-dark fs-6"><i class="fa-solid fa-users"></i>發起揪團</a>
												</div>
												<i type="button"
													class="heartBtn fa-solid fa-heart fs-4 position-absolute top-0 end-0 m-3"></i>
											</div>
										</div>
									</div>
								</div>

							</div>
							<div class="col">

								<div class="card">
									<div class="row g-0 align-items-center">
										<div class="col-5 ">
											<img
												src="https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
												class="card-img" alt="...">
										</div>
										<div class="col-7">
											<div class="card-body">
												<h5 class="card-title">ONE GOOD烤肉飯</h5>
												<ul class="list-unstyled card-text">
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary">11:00~13:00</span>
													</li>
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary"><i
															class="fa-solid fa-utensils"></i></span></li>
													<li class="list-inline-item"><span
														class="badge fs-6 rounded-pill bg-secondary"><i
															class="fa-solid fa-star"></i>4.5</span></li>
													<li>外送大樓：宏春、揚昇金融</li>
													<li>成團條件：1500元</li>
													<li>營業時間內接單後1小時內送達</li>
												</ul>
												<div class="d-grid gap-2 d-flex justify-content-end">
													<a class="btn btn-dark fs-6"><i
														class="fa-solid fa-magnifying-glass"></i>現有揪團</a><a
														class="btn btn-dark fs-6"><i class="fa-solid fa-users"></i>發起揪團</a>
												</div>
												<i type="button"
													class="heartBtn fa-solid fa-heart fs-4 position-absolute top-0 end-0 m-3"></i>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</section>
				</div>
			</div>
		</div>
	</div>


	<%-- Page content end --%>

	<jsp:include page="../components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="../components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>
	
	<script>
		function previewImage(event) {
			var reader = new FileReader();
			reader.onload = function() {
				var output = document.getElementById('previewBlob');
				output.src = reader.result;
			};
			reader.readAsDataURL(event.target.files[0]);
		}
	</script>

</body>
</html>