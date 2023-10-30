<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="components/head.jsp" %> 
<%-- Import CSS for this page below (if any) --%>


<title>樓頂揪樓咖-登入</title>  <%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="./components/nav.jsp"></jsp:include>  <%-- The navigation bar --%>
	
	<%-- Page content start --%>
	<section class="container mt-3 mb-5">
    <div class="card mx-auto col-4 ">
      <div class="card-header text-center">
        <p class="h1"><b>登入</b></p>
      </div>
      <div class="card-body">
        <p>進入後開始點餐吧！</p>
        <form action="${pageContext.request.contextPath}/user.do" method="post">
          <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="請輸入帳號Email" name="userAccount">
            <div class="input-group-append">
              <div class="input-group-text h-100">
                <span class="fas fa-envelope"></span>
              </div>
            </div>
          </div>
          <div class="input-group mb-3">
            <input type="password" class="form-control" placeholder="請輸入登入密碼" name="userPassword">

            <div class="input-group-append">
              <div class="input-group-text h-100">
                <span class="fas fa-lock"></span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-8">
<!--               <div class="icheck-primary"> -->
<!--                 <input type="checkbox" id="remember"> -->
<!--                 <label for="remember"> -->
<!--                   記住我的登入資訊 -->
<!--                 </label> -->
<!--               </div> -->
            </div>

            <div class="col-4">
            	<input type="hidden" name="action" value="login">
              <button type="submit" class="btn btn-primary btn-block">登入</button>
            </div>

          </div>
          <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請重新輸入</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
        </form>

<!--         <p class="mb-1"> -->
<%--           <a href="${pageContext.request.contextPath}/consumer/ForgetPwd.jsp">忘記密碼</a> --%>
<!--         </p> -->
        <p class="mb-0">
          <a href="${pageContext.request.contextPath}/consumer/Registration.jsp" class="registration_link">我要註冊</a>
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