<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="components/head.jsp"%>
<%-- Import CSS for this page below (if any) --%>


<title>樓頂揪樓咖-FAQ</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<div class="container mt-5 w-50">
		<div class="text-center mb-4">
			<h2>常見問題</h2>
		</div>
		<div class="accordion accordion-flush" id="accordionFlushExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="flush-headingOne">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#flush-collapseOne"
						aria-expanded="false" aria-controls="flush-collapseOne">
						如何註冊成為會員</button>
				</h2>
				<div id="flush-collapseOne" class="accordion-collapse collapse"
					aria-labelledby="flush-headingOne"
					data-bs-parent="#accordionFlushExample">
					<div class="accordion-body">
						請點選右上角的<a href="${pageContext.request.contextPath}/consumer/Registration.jsp">註冊</a>，填寫資料即可成為會員。<br>
					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="flush-headingTwo">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo"
						aria-expanded="false" aria-controls="flush-collapseTwo">
						收到的商品有誤</button>
				</h2>
				<div id="flush-collapseTwo" class="accordion-collapse collapse"
					aria-labelledby="flush-headingTwo"
					data-bs-parent="#accordionFlushExample">
					<div class="accordion-body">如有任何商品內容的錯誤，最即時的方式請直接聯絡門市，也可在訂單下評價或給予回饋。
					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#flush-collapseThree"
						aria-expanded="false" aria-controls="flush-collapseThree">
						我是商家，想加入你們</button>
				</h2>
				<div id="flush-collapseThree" class="accordion-collapse collapse"
					aria-labelledby="flush-headingThree"
					data-bs-parent="#accordionFlushExample">
					<div class="accordion-body">如果想成為商家，請點選加入<a href="${pageContext.request.contextPath}/dinerbackground/pages/Team/login/login-form.jsp">商家</a>。</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#flush-collapseFour"
						aria-expanded="false" aria-controls="flush-collapseFour">
						無法使用電子載具</button>
				</h2>
				<div id="flush-collapseFour" class="accordion-collapse collapse"
					aria-labelledby="flush-headingFour"
					data-bs-parent="#accordionFlushExample">
					<div class="accordion-body">很抱歉，各店家有不同的開立發票設定，將會由各商家外送時附上實體發票
					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#flush-collapseFive"
						aria-expanded="false" aria-controls="flush-collapseFive">
						收不到手機認證碼</button>
				</h2>
				<div id="flush-collapseFive" class="accordion-collapse collapse"
					aria-labelledby="flush-headingFive"
					data-bs-parent="#accordionFlushExample">
					<div class="accordion-body">
						可能會導致收不到簡訊驗證碼的情況有以下幾種，建議您嘗試以下方法：<br> 1.
						請您再次確認於手機號碼欄位所選擇的國碼及輸入的手機號碼是否正確。<br> 2.
						訊號不佳或者網路壅塞皆會導致無法收取驗證碼，建議可至訊號通順良好的地方再次獲取驗證碼，或者稍後重新獲取即可。<br> 3.
						請確認是否有設定來電攔截APP (e.g.
						Whoscall)，如果有設定到黑名單，由於驗證簡訊通常為系統大量發送訊息，很可能被手機判為廣告，請解除攔截或封鎖，避免簡訊接收失敗。<br>
						4. 若您使用iOS手機，訊息裡的設定請不要使用 3rd party 程式來封鎖/過濾黑名單聯絡人，避免簡訊接收失敗。<br>
						5.
						部分電信業者有提供的＂拒收企業簡訊＂功能，該功能可能導致無法正常接受驗證碼簡訊。解除流程可能因業者系統升級而有所不同，請見電信業者官網為主，若需查詢、關閉此功能請自行洽詢所屬電信商。<br>
						<br>若上述仍無法排除，請您加入官方客服，由客服人員協助驗證作業。
					</div>
				</div>
			</div>
		</div>
	</div>


	<%-- Page content end --%>

	<jsp:include page="./components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="./components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>

</body>
</html>