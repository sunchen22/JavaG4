<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>

<%
	ArrayList<HashMap<String, Object>> navbarJoinedGroupOrders = (ArrayList<HashMap<String, Object>>) session
		.getAttribute("navbarJoinedGroupOrders");
%>

<%
if (session.getAttribute("loginUserInfo") == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
%>


<nav class="navbar navbar-expand-md sticky-top navbar-light bg-light">
	<div class="container-fluid">
		<!-- 品牌 -->
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/consumer/index.jsp">樓頂揪樓咖</a>

		<!-- 導覽列項目 -->
		<div class="navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-0 ms-2">
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="${pageContext.request.contextPath}/consumer/search.jsp">搜尋大樓揪團</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/consumer/search_diner.jsp">搜尋商家</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">協助</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/consumer/FAQ.jsp">常見問題</a></li>
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/consumer/protected/DinerComment.jsp">意見反饋</a></li>
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/consumer/protected/Chatroom.jsp">聯繫客服</a></li>
					</ul></li>
			</ul>
			<!-- 登入相關 -->
			<a class="btn btn-dark"
				href="${pageContext.request.contextPath}/consumer/Login.jsp">登入/註冊</a>

		</div>
	</div>
</nav>
<%
} else {
%>

<nav class="navbar navbar-expand-md sticky-top navbar-light bg-light">
	<div class="container-fluid">
		<!-- 品牌 -->
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/consumer/index.jsp">樓頂揪樓咖</a>

		<!-- 導覽列項目 -->
		<div class="navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-0 ms-2">
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="${pageContext.request.contextPath}/consumer/search.jsp">搜尋大樓揪團</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/consumer/search_diner.jsp">搜尋商家</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">協助</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/consumer/FAQ.jsp">常見問題</a></li>
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/consumer/protected/Feedback.jsp">意見反饋</a></li>
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/consumer/protected/Chatroom.jsp">聯繫客服</a></li>
					</ul></li>
			</ul>
			<!-- 登入相關 -->
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown"><a class="nav-link dropdown"
					id="groupCartDropdown" href="#" data-bs-toggle="dropdown"
					aria-expanded="false"> <i class="fa-solid fa-users"></i> <span
						class="badge badge-danger navbar-badge"><%=navbarJoinedGroupOrders == null? 0 : navbarJoinedGroupOrders.size()%></span> <i
						class="fa-solid fa-cart-shopping"></i> <span
						class="badge badge-danger navbar-badge">2</span>
				</a>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="groupCartDropdown">
						<%
						if (navbarJoinedGroupOrders != null) {
							for (HashMap groupOrder : navbarJoinedGroupOrders) {
						%>
						<li><a class="dropdown-item d-inline" href="#!"><%=groupOrder.get("dinerName")%>
								|
								<button class="btn btn-outline-dark" data-bs-toggle="offcanvas"
									data-bs-target="#shopping_cart" aria-controls="shopping_cart">
									<i class="bi-cart-fill me-1"></i> 購物車 <span
										class="badge bg-dark text-white ms-1 rounded-pill">1</span>
								</button> </a></li>
						<%
						}
						}
						%>

						<li><a class="dropdown-item d-inline" href="#!">餐廳1 |
								<button class="btn btn-outline-dark" data-bs-toggle="offcanvas"
									data-bs-target="#shopping_cart" aria-controls="shopping_cart">
									<i class="bi-cart-fill me-1"></i> 購物車 <span
										class="badge bg-dark text-white ms-1 rounded-pill">1</span>
								</button>
						</a></li>
						<li><a class="dropdown-item d-inline" href="#!">餐廳2 |
								<button class="btn btn-outline-dark" disabled
									data-bs-toggle="offcanvas" data-bs-target="#shopping_cart"
									aria-controls="shopping_cart" disabled>
									<i class="bi-cart-fill me-1"></i> 購物車 <span
										class="badge bg-dark text-white ms-1 rounded-pill">0</span>
								</button>
						</a></li>
						<li><a class="dropdown-item d-inline" href="#!">餐廳3 |
								<button class="btn btn-outline-dark" data-bs-toggle="offcanvas"
									data-bs-target="#shopping_cart" aria-controls="shopping_cart">
									<i class="bi-cart-fill me-1"></i> 購物車 <span
										class="badge bg-dark text-white ms-1 rounded-pill">1</span>
								</button>
						</a></li>
					</ul></li>

				<li class="nav-item dropdown"><a class="nav-link dropdown"
					id="notifyDropdown" href="#" data-bs-toggle="dropdown"
					aria-expanded="false"> <i class="fa-solid fa-bell"></i> <span
						class="badge badge-danger navbar-badge">4</span>
				</a>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="notifyDropdown">
						<li><a class="dropdown-item" href="#!">通知1</a></li>
						<li><a class="dropdown-item" href="#!">通知2</a></li>
						<li><a class="dropdown-item" href="#!">通知3</a></li>
						<li><a class="dropdown-item" href="#!">通知4</a></li>
					</ul></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="userDropdown" href="#"
					data-bs-toggle="dropdown" aria-expanded="false"> <i
						class="fa-solid fa-circle-user"></i>
				</a>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="userDropdown">
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/consumer/protected/UserInfo.jsp">會員中心</a></li>
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/consumer/protected/UserInfo.jsp">最愛餐廳</a></li>
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/consumer/protected/ConsumerOrder.jsp">訂單</a></li>
						<li><form action="${pageContext.request.contextPath}/user.do"
								method="post">
								<input type="hidden" name="action" value="logout" readonly>
								<button type="submit" class="dropdown-item">登出</button>
							</form></li>
					</ul></li>


			</ul>
		</div>
	</div>
</nav>


<!-- Shopping cart offcanvas start -->
<div class="offcanvas offcanvas-end" tabindex="-1" id="shopping_cart">
	<!--Offcanvas header start -->
	<div class="offcanvas-header">
		<h5 id="">ONE GOOD烤肉飯購物車</h5>
		<button type="button" class="btn-close text-reset"
			data-bs-dismiss="offcanvas" aria-label="Close"></button>
	</div>
	<!-- Offcanvas header end -->
	<!-- Offcanvas body start -->
	<div class="offcanvas-body">
		<div class="px-0">
			<ul class="list-group px-0">
				<li class="list-group-item px-1">
					<div class="row">
						<div class="col-5">
							<h6 class="mb-0">蒜味香腸烤肉飯蒜味香腸烤</h6>
							<p class="mb-0 ms-2 text-muted">加荷包蛋</p>
							<p class="mb-0 ms-2 text-muted">加荷包蛋</p>
						</div>
						<div class="col-4">
							<div class="input-group">
								<input type="number" class="form-control form-control-sm"
									min="1" value="1">
								<button class="btn btn-outline-secondary" type="button">
									<i class="fas fa-trash"></i>
								</button>
							</div>
						</div>
						<div class="col-3">
							<span class="fs-6">149元</span>
						</div>
					</div>
				</li>

				<li class="list-group-item px-1">
					<div class="row">
						<div class="col-5">
							<h6 class="mb-0">蒜味香腸烤肉飯</h6>
							<p class="mb-0 ms-2 text-muted"></p>
						</div>
						<div class="col-4">
							<div class="input-group">
								<input type="number" class="form-control form-control-sm"
									min="1" value="1">
								<button class="btn btn-outline-secondary" type="button">
									<i class="fas fa-trash"></i>
								</button>
							</div>
						</div>
						<div class="col-3">
							<span class="fs-6">139元</span>
						</div>
					</div>
				</li>
			</ul>
		</div>

		<div class="mt-2 text-end">
			<h6>總計149元</h6>
		</div>

		<div class="mt-4 col-10 mx-auto">
			<div class="my-2">
				<a href="#" class="btn btn-dark btn-lg w-100">付款</a>
			</div>
			<div class="my-2">
				<a href="#" class="btn btn-dark btn-lg w-100">繼續點餐</a>
			</div>
		</div>
	</div>
	<!-- Offcanvas body end -->
</div>
<!-- Shopping cart offcanvas end -->

<%
}
%>