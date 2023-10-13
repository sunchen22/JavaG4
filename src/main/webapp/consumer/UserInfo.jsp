<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- <% 
                       // 從 session內取出 (key) account的值
     if (session.getAttribute("loginUserInfo") == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
       session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
       response.sendRedirect(request.getContextPath()+"/jo/consumer/Login.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
       return;
     }
%> --%>
<%@ include file="components/head.jsp" %> 
<%-- Import CSS for this page below (if any) --%>
<link href="./css/style.css" rel="stylesheet">
<%@ page import="com.userinfo.entity.*"%>
<%@ page import="java.util.Base64" %>



<title>樓頂揪樓咖-消費者個人資訊</title>  <%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>  <%-- The navigation bar --%>
	
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
          <ul class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <li class="nav-item">
              <a class="nav-link active" id="v-pills-info-tab" data-bs-toggle="pill" href="#v-pills-info" role="tab"
                aria-controls="v-pills-info" aria-selected="true">基本資訊</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" id="v-pills-pwd-tab" data-bs-toggle="pill" href="#v-pills-pwd" role="tab"
                aria-controls="v-pills-pwd" aria-selected="false">密碼修改</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" id="v-pills-favor-tab" data-bs-toggle="pill" href="#v-pills-favor" role="tab"
                aria-controls="v-pills-favor" aria-selected="false">最愛餐廳</a>
            </li>
          </ul>
        </div>
      </div>

      <!-- 內容面板 -->
      <div class="tab-content col-9" id="v-pills-tabContent">
        <div class="tab-pane fade show active" id="v-pills-info" role="tabpanel" aria-labelledby="v-pills-info-tab">
          <form>
            <div class="row mb-3">
              <label for="userAccount" class="col-form-label col-md-3">帳號：</label>
              <div class="col-md-9">
                <input type="text" class="form-control" id="userAccount" maxlength="20" 
                value="${loginUserInfo.userAccount}" name="userAccount">
              </div>
            </div>
            <div class="row mb-3">
              <label for="userName" class="col-form-label col-md-3">姓名：</label>
              <div class="col-md-9">
              	<input type="hidden" name="userID" value="${loginUserInfo.userID}">
                <input type="text" class="form-control" id="userName" maxlength="20" 
                value="${loginUserInfo.userName}" name="userName">
              </div>
            </div>
            <div class="row mb-3">
              <label for="nickName" class="col-form-label col-md-3">暱稱：</label>
              <div class="col-md-9">
                <input type="text" class="form-control" id="nickName" name="userNickName"
                value="${loginUserInfo.userNickName}" maxlength="20">
              </div>
            </div>
            <div class="row mb-3">
              <label for="userphone" class="col-form-label col-md-3">手機號碼：</label>
              <div class="col-md-9">
                <input type="text" class="form-control" name="userPhone"
                id="userphone" value="${loginUserInfo.userPhone}" maxlength="10">
              </div>
            </div>
            <div class="row mb-3">
              <label for="building" class="col-form-label col-md-3">常用大樓：</label>
              <div class="col-md-9">
                <input type="text" class="form-control" id="building" value="${loginUserInfo.buildingInfo.buildingName}" maxlength="10">
              </div>
            </div>
            <div class="row mb-3">
              <label for="userBirthday" class="col-form-label col-md-3">生日：</label>
              <div class="col-md-9">
                <input type="text" class="form-control" id="userBirthday" value="${loginUserInfo.userBirthday}" disabled>
              </div>
            </div>
            <div class="d-grid">
            <input type="hidden" name="action" value="update">
              <button type="submit" class="btn btn-primary">確認修改</button>
            </div>
          </form>
        </div>
        <div class="tab-pane fade" id="v-pills-pwd" role="tabpanel" aria-labelledby="v-pills-pwd-tab">
          <form>
            <div class="row mb-3">
              <label for="oldPwd" class="col-form-label col-md-4">舊密碼：</label>
              <div class="col-md-8">
                <input type="password" class="form-control" id="oldPwd" maxlength="20" placeholder="請輸入舊密碼">
              </div>
            </div>
            <div class="row mb-3">
              <label for="newPwd" class="col-form-label col-md-4">新密碼：</label>
              <div class="col-md-8">
                <input type="password" class="form-control" id="newPwd" maxlength="20" placeholder="請輸入新密碼">
              </div>
            </div>
            <div class="row mb-3">
              <label for="pwdAgain" class="col-form-label col-md-4">確認新密碼：</label>
              <div class="col-md-8">
                <input type="password" class="form-control" id="pwdAgain" maxlength="20" placeholder="請再次輸入新密碼">
              </div>
            </div>

            <div class="d-grid">
              <button type="submit" class="btn btn-primary">確認修改</button>
            </div>
          </form>
        </div>
        <div class="tab-pane fade" id="v-pills-favor" role="tabpanel" aria-labelledby="v-pills-favor-tab">
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
	
	<jsp:include page="./components/footer.jsp"></jsp:include>  <%-- The footer --%>	
	
	
	
	
<%@ include file="./components/tail.jsp" %>
<%-- Import JS for this page below (if any) --%>
<script src="./vendor/bootstrap-5.3.1-dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>