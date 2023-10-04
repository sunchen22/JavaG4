<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="components/head.jsp" %> 
<%-- Import CSS for this page below (if any) --%>


<title>樓頂揪樓咖-登入</title>  <%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>  <%-- The navigation bar --%>
	
	<%-- Page content start --%>
	<section class="container ">
    <div class="card mx-auto col-4 ">
      <div class="card-header text-center">
        <p class="h1"><b>登入</b></p>
      </div>
      <div class="card-body">
        <p>進入後開始點餐吧！</p>
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
            <input type="password" class="form-control" placeholder="請輸入登入密碼">

            <div class="input-group-append">
              <div class="input-group-text h-100">
                <span class="fas fa-lock"></span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-8">
              <div class="icheck-primary">
                <input type="checkbox" id="remember">
                <label for="remember">
                  記住我的登入資訊
                </label>
              </div>
            </div>

            <div class="col-4">
              <button type="submit" class="btn btn-primary btn-block">登入</button>
            </div>

          </div>
        </form>

        <p class="mb-1">
          <a href="ForgetPwd.html">忘記密碼</a>
        </p>
        <p class="mb-0">
          <a href="registration.html" class="registration_link">我要註冊</a>
        </p>
      </div>

    </div>



  </section>
	
	
	<%-- Page content end --%>
	
	<jsp:include page="./components/footer.jsp"></jsp:include>  <%-- The footer --%>	
	
	
	
	
<%@ include file="./components/tail.jsp" %>
<%-- Import JS for this page below (if any) --%>

</body>
</html>