<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../components/head.jsp"%>
<%@ page import="com.dinerinfo.dao.*"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%@ page import="com.dinerratingcomment.entity.*"%>
<%@ page import="com.dinerratingcomment.dao.*"%>
<%@ page import="com.userinfo.entity.*"%>

<%@ page import="java.util.*"%>
<%-- Import CSS for this page below (if any) --%>


<style>
.star_block {
	display: inline-block;
}

.star {
	cursor: pointer;
	display: inline-block;
	margin-right: 3px;
	color: grey;
}

.star_ed {
	display: inline-block;
	margin-right: 3px;
	color: gold;
}

.star.-on {
	cursor: pointer;
	display: inline-block;
	margin-right: 3px;
	color: gold;
}
</style>

<title>樓頂揪樓咖-餐廳評論</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="../components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<section
		class="container col-6 border-primary shadow-lg mb-5 mt-3 p-2 rounded">
		<div class="restaurant-info mb-5">
			<!-- 餐廳資訊 -->
			<%
			Integer dinerID = Integer.parseInt(request.getParameter("dinerID"));
			
			DinerInfo dinerInfo = new DinerInfoDAOImplC().findByPK(dinerID);
			if (dinerInfo != null) {
				pageContext.setAttribute("dinerInfo", dinerInfo);
			}

			//平均評分
			Double averageRating = new DinerRatingCommentDAO().getAverageRatingByDinerIDWithCriteria(dinerID);
			String formattedRating = String.format("%.1f", averageRating);
			pageContext.setAttribute("averageRating", averageRating);
			%>
			<img
				src="<%=request.getContextPath()%>/consumer/dinerDBGifReader?dinerID=<%= dinerID %>"
				alt="餐廳照片" class="img-fluid rounded">
			<h2 class="mt-3">${dinerInfo.dinerName}</h2>
			<p>地址: ${dinerInfo.dinerAddress}</p>
			<p>電話: ${dinerInfo.dinerPhone}</p>
			<p>
				平均評等: <span class="star_ed"><i class="fas fa-star"></i></span> <span><%= formattedRating %></span>
			</p>
		</div>
		<div class="reviews">
			<!-- 評論列表 -->
			<%
			List<DinerRatingComment> comments = new DinerRatingCommentDAO().getAllbyDinerID(dinerID);
			if(comments != null)
			for (DinerRatingComment comment : comments) {
			%>
			<div class="review align-items-center mb-3 border p-3">
				<div class="border-bottom mb-2 row">
					<div class="comment col-10">
						<p>
							<strong><%=comment.getUserInfo().getUserName()%></strong>
						</p>
						<div class="star_block">
							<span class="star_ed"><i class="fas fa-star"></i></span> <span><%=comment.getDinerRating()%></span>
						</div>
						<p><%=comment.getUserCommentContent()%></p>
					</div>
					<div class="col-2">
<!-- 					<button class="like-btn btn btn-outline-primary ms-auto"> -->
<!-- 						<span>👍</span> <span>4</span> -->
<%-- 						這個"4"應該是從後端取得的，目前先寫死 --%>
<!-- 					</button> -->
					</div>
				</div>
				<%-- 如果有商家回覆，顯示在這裡 --%>
				<%
				if (comment.getDinerReplyContent() != null) {
				%>
				<div class="diner-reply">
					<p>
						<strong>商家回覆:</strong>
					</p>
					<p><%=comment.getDinerReplyContent()%></p>
				</div>
				<%
				}
				%>
			</div>
			<%
			}
			%>
		</div>

		<!-- 新增評論 -->
		<form action="${pageContext.request.contextPath}/comment.do" method="POST">
			<div class="add-review">
				<input type="text" value="${loginUserInfo.userName}" class="form-control mb-3" disabled>
				<textarea name="userCommentContent" placeholder="寫下您的評論"
					class="form-control mb-3"></textarea>
				<div class="star_block">
					<!-- 我們使用data-star屬性來設定每顆星的值 -->
					<span class="star -on" data-star="1"><i class="fas fa-star"></i></span>
					<span class="star -on" data-star="2"><i class="fas fa-star"></i></span>
					<span class="star -on" data-star="3"><i class="fas fa-star"></i></span>
					<span class="star -on" data-star="4"><i class="fas fa-star"></i></span>
					<span class="star -on" data-star="5"><i class="fas fa-star"></i></span>
				</div>
				
				<input type="hidden" id="star-rating" name="dinerRating" value="5">
				<input type="hidden" name="userID" value="${loginUserInfo.userID}">
				<input type="hidden" name="dinerID" value= <%= dinerID %>>
				<input type="hidden" name="action" value="insertComment">
				<button id="submit-btn" class="btn btn-primary" type="submit">提交</button>
			</div>
		</form>

	</section>


	<%-- Page content end --%>

	<jsp:include page="../components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="../components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>

	<script>
		$(document).ready(function() {
			$('.star').click(function() {
				// 取得被點擊的星星的數值
				const rating = $(this).data('star');

				// 將所有星星的樣式重設為默認
				$('.star').removeClass('-on');

				// 為被點擊的星星以及它之前的星星添加-on類
				$('.star').each(function() {
					if ($(this).data('star') <= rating) {
						$(this).addClass('-on');
					}
				});

				// 將選擇的星數儲存到隱藏的input中
				$('#star-rating').val(rating);
			});
		});
	</script>

</body>
</html>