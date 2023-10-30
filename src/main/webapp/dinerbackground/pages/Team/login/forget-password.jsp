<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.dinerinfo.entity.*"%>

<%
DinerInfo dinerInfo = (DinerInfo) request.getAttribute("dinerInfo");
%>

<html lang="zh-Hant">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>樓頂揪樓咖 | 忘記密碼</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">
</head>

<body class="hold-transition login-page">
  <div class="login-box">
    <div class="login-logo">
      <a href="<%=request.getContextPath()%>/consumer/index.jsp"><b>樓頂揪樓咖</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="card">
      <div class="card-body login-card-body">
        <p class="login-box-msg">忘記密碼</p>

          	<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				
        <form action="${pageContext.request.contextPath}/RecoverPasswordServlet" method="post">
          <div class="input-group mb-3">
          
          
            <input type="text" class="form-control" placeholder="請輸入帳號"
							name="dinerTaxID"
							value="<%=(dinerInfo != null) ? dinerInfo.getDinerTaxID() : ""%>">

            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fas fa-user"></span>
              </div>
            </div>
          </div>
          <div class="input-group mb-3">
          
             <input type="email" class="form-control" placeholder="請輸入email"
							name="dinerEmail"
							value="<%=(dinerInfo != null) ? dinerInfo.getDinerEmail() : ""%>">
            
            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fas fa-envelope"></span>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col-12">
              <button type="submit" class="btn btn-primary btn-block">申請新密碼</button>
              <input type="hidden" name="action" value="resetPassword">
            </div>
            <!-- /.col -->
          </div>
        </form>

        <p class="mt-3 mb-1">
          <a href="${pageContext.request.contextPath}/dinerbackground/pages/Team/login/login-form.jsp">登入</a>
        </p>
        <p class="mb-0">
          <a href="${pageContext.request.contextPath}/dinerbackground/pages/Team/register/register-form.jsp" class="text-center">還沒有帳號?立即註冊</a>
        </p>
      </div>
      <!-- /.login-card-body -->
    </div>
  </div>
  <!-- /.login-box -->

  <!-- jQuery -->
  <script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery/jquery.min.js"></script>
  <!-- Bootstrap 4 -->
  <script src="<%=request.getContextPath()%>/dinerbackground/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- AdminLTE App -->
  <script src="<%=request.getContextPath()%>/dinerbackground/dist/js/adminlte.min.js"></script>
</body>

</html>