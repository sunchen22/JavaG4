<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="./vendor/bootstrap-5.3.1-dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="./vendor/fontawesome-free-6.4.2-web/css/all.min.css">
<style>
.star_block {
	display: inline-block;
}

.star {
	cursor: pointer;
	display: inline-block;
	margin-right: 3px;
}

.star.-on i {
	color: rgb(229, 187, 49);
}
</style>

<title>樓頂揪樓咖-餐廳評論</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<section
		class="container col-6 border-primary shadow-lg mb-5 mt-3 rounded">
		<div class="restaurant-info mb-5">
			<!-- 餐廳資訊 -->
			<img
				src="https://pic.616pic.com/photoone/00/05/67/618e256d3d3368237.jpg!/fw/1120"
				alt="餐廳照片" class="img-fluid rounded">
			<h2 class="mt-3">餐廳名稱</h2>
			<p>地址: xxx</p>
			<p>電話: xxx-xxxx-xxxx</p>
			<p>
				平均評等: <span class="star -on" data-star="1">4<i
					class="fas fa-star"></i></span>
			</p>
		</div>
		<div class="reviews">
			<!-- 評論列表 -->
			<div class="review d-flex align-items-center mb-3">
				<!-- <img src="" alt="User 1" class="rounded-circle me-3" style="width: 50px;"> -->
				<div>
					<p>
						<strong>姓名1</strong>
					</p>
					<div class="star_block">
						<span class="star -on" data-star="1"><i class="fas fa-star"></i></span>
						<span class="star -on" data-star="2"><i class="fas fa-star"></i></span>
						<span class="star -on" data-star="3"><i class="fas fa-star"></i></span>
						<span class="star -on" data-star="4"><i class="fas fa-star"></i></span>
						<span class="star" data-star="5"><i class="fas fa-star"></i></span>
					</div>
					<p>菜好鹹。</p>
				</div>
				<button class="like-btn btn btn-outline-primary ms-auto">👍
					5</button>
			</div>
		</div>

		<!-- 新增評論 -->
		<div class="add-review">
			<input type="text" value="小劉" class="form-control mb-3" disabled>
			<textarea placeholder="寫下您的評論" class="form-control mb-3"></textarea>
			<div class="star_block">
				<span class="star -on" data-star="1"><i class="fas fa-star"></i></span>
				<span class="star -on" data-star="2"><i class="fas fa-star"></i></span>
				<span class="star -on" data-star="3"><i class="fas fa-star"></i></span>
				<span class="star -on" data-star="4"><i class="fas fa-star"></i></span>
				<span class="star" data-star="5"><i class="fas fa-star"></i></span>
			</div>
			<button id="submit-btn" class="btn btn-primary">提交</button>
		</div>
	</section>


	<%-- Page content end --%>

	<jsp:include page="./components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>
	<script src="./vendor/jquery/jquery-3.7.1.min.js"></script>
	<script src="./vendor/bootstrap-5.3.1-dist/js/bootstrap.min.js"></script>
	<script src="./vendor/bootstrap-5.3.1-dist/js/bootstrap.js"></script>
	<script>
		$(document).ready(function () {
			// 提交評論
			$('#submit-btn').on('click', function () {
				let name = $(".add-review input[type='text']").val();
				let comment = $(".add-review textarea").val();

				let rating = $(".add-review .star_block .star.-on").length;

			<%--	let reviewStars = `<div class="star_block">
             ${Array(5).fill().map((_, index) =>
				`<span class="star ${index < rating ? '-on' : ''}" data-star="${index + 1}"><i class="fas fa-star"></i></span>`
			).join('')}
         </div>`; --%>
// 初始化一個變數來存儲最後的 HTML 結果
let reviewStars = "";

// 開始創建包含星星的 div 區塊
reviewStars += '<div class="star_block">';

// 初始化一個空陣列並填充它使其長度為 5
const starArray = Array(5).fill();

// 使用 map() 函數來迭代每個元素。這裡的 "_" 是一個佔位符，因為我們不關心陣列的元素值，只關心索引（index）。
const starHTMLArray = starArray.map((_, index) => {
  // 判斷這顆星星是否應該是亮的（"-on"）或暗的（""）
  const starClass = (index < rating) ? '-on' : '';

  // 使用模板字串來創建這顆星星的 HTML
  return `<span class="star ${starClass}" data-star="${index + 1}"><i class="fas fa-star"></i></span>`;
});

// 使用 join() 將所有星星的 HTML 連接成一個字串
const starsHTML = starHTMLArray.join('');

// 將星星的 HTML 添加到最終的 div 區塊中
reviewStars += starsHTML;

// 結束 div 區塊
reviewStars += '</div>';

				let review = `<div class="review d-flex align-items-center mb-3">
                    
                    <div>
                        <p><strong>${name}</strong></p>
                        ${reviewStars}
                        <p>${comment}</p>
                    </div>
                    <button class="like-btn btn btn-outline-primary ms-auto">👍 0</button>
                </div>`;

				$('.reviews').append(review);

				// 更新平均評等
				updateAverageRating();
			});

			// 更新平均評等的函數
			function updateAverageRating() {
				let totalStars = 0;
				let totalReviews = 0;

				// 遍歷所有的評論並計算總星數和評論數
				$('.reviews .review').each(function () {
					totalStars += $(this).find('.star.-on').length;
					totalReviews++;
				});

				// 計算平均評等
				let averageRating = (totalStars / totalReviews).toFixed(1); // 保留一位小數

				// 更新顯示的平均評等
				$('.restaurant-info p:last-child span').text(averageRating);
			}

			// 點擊星星亮燈效果
			$(document).on('click', '.add-review .star_block .star', function () {
				let current_star = parseInt($(this).attr("data-star"));
				$(this).closest(".star_block").find(".star").each(function (i, item) {
					if (parseInt($(this).attr("data-star")) <= current_star) {
						$(this).addClass("-on");
					} else {
						$(this).removeClass("-on");
					}
				});
			});

			// 點讚功能
			 $(document).on('click', '.like-btn', function () {
				let likes = parseInt($(this).text().split(' ')[1]);
				$(this).text(`👍 ${likes + 1}`);
			}); 
			

		});
	</script>

</body>
</html>