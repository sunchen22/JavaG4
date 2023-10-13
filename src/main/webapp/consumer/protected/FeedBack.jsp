<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="components/head.jsp" %> 
<%-- Import CSS for this page below (if any) --%>
<!-- Bootstrap CSS -->
  <link rel="stylesheet" href="./vendor/bootstrap-5.3.1-dist/css/bootstrap.css">
  <!-- jQuery -->
  <script src="./vendor/jquery/jquery-3.7.1.min.js"></script>
  <!-- Bootstrap JS -->
  <script src="./vendor/bootstrap-5.3.1-dist/js/bootstrap.bundle.js"></script>

<title>樓頂揪樓咖-意見回饋</title>  <%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>  <%-- The navigation bar --%>
	
	<%-- Page content start --%>
	<div class="container mb-4 col-6 shadow rounded">

    <h1 class="text-center mb-3 mt-5">意見回饋</h1>
    <form class="form" id="contact-form">
      <div class="controls">
        <div>
          <div class="mb-4">
            <label class="form-label" for="name">姓名</label>
            <input class="form-control" type="text" name="name" id="name" value="周杰倫" required="required">
          </div>
        </div>
        <div class="mb-4">
          <label class="form-label" for="email">Email</label>
          <input class="form-control" type="email" name="email" id="email" value="123@gmail.com" required="required">
        </div>
        <div class="mb-4">
          <label class="form-label" for="phone">手機</label>
          <input class="form-control" type="text" name="phone" id="phone" value="0958123456">
        </div>
        <div class="mb-4">
          <label class="form-label" for="questionType">問題類型</label>
          <select class="form-select" name="questionType" id="questionType" required="required">
            <option value="1">餐點品質</option>
            <option value="2">商品未到</option>
            <option value="3">付款問題</option>
            <option value="4">其他意見</option>
          </select>
        </div>
        <div class="mb-4">
          <label class="form-label" for="message">您的意見</label>
          <textarea class="form-control" rows="4" name="message" id="message" placeholder="請輸入您的意見"
            required="required"></textarea>
        </div>
        <div id="success-message" class="alert alert-success d-none" role="alert">
          感謝您的意見! 我們會盡快回復您。
        </div>
        <button class="btn btn-outline-dark" type="submit" id="submitButton">送出</button>

      </div>
    </form>
  </div>
	
	
	<%-- Page content end --%>
	
	<jsp:include page="./components/footer.jsp"></jsp:include>  <%-- The footer --%>	
	
	
	
	
<%@ include file="./components/tail.jsp" %>
<%-- Import JS for this page below (if any) --%>
<script>
    $(function () {
      $('#contact-form').submit(function (e) {
        e.preventDefault();
        $('#success-message').removeClass('d-none');
        //等候3秒網頁轉移
        setTimeout(function () {
          window.location.href = 'https://tw.yahoo.com/';
        }, 3000);
      });
    });
  </script>

</body>
</html>