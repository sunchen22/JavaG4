<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dinerinfo.entity.*"%>

<%
//������J�榡�����~�ɪ�dinerInfo����
DinerInfo dinerInfo = (DinerInfo) request.getAttribute("dinerInfo");
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>�ӳ����ө@ | �Ӯa�|�����U</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/fontawesome-free/css/all.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">

</head>
<body class="hold-transition register-page">
	<div class="register-box w-100">
		<div class="register-logo">
			<a href="<%=request.getContextPath()%>/consumer/index.jsp"><b>�ӳ����ө@</b>�Ӯa���U</a>
		</div>

		<div class="card col-12">
			<div class="card-body register-card-body">
				<p class="login-box-msg">�Яd�U�z����T�A�ڭ̱N�ѱM�H�P�z�pô</p>

				<%-- ���~��C --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">�Эץ��H�U���~:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<form METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/register/dinerInfo.do" name="form1">
					<div class="row">
						<!-- �ө��W�� -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerName" class="form-control" placeholder="�ө��W��"
								value="<%=(dinerInfo != null) ? dinerInfo.getDinerName() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-store"></span>
								</div>
							</div>
						</div>

						<!-- �Ȧ�b�� �Ȧ�N�X -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerBank" class="form-control"
								placeholder="�Ȧ�b�� �Ȧ�N�X " value="<%=(dinerInfo != null) ? dinerInfo.getDinerBank() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-user"></span>
								</div>
							</div>
						</div>

						<!-- �ө��a�} -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerAddress" class="form-control"
								placeholder="�ө��a�}" value="<%=(dinerInfo != null) ? dinerInfo.getDinerAddress() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-map-marker-alt"></span>

								</div>
							</div>
						</div>

						<!-- �Ȧ�b�� �b�ḹ�X -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerAccount" class="form-control"
								placeholder="�Ȧ�b�� �b�ḹ�X" value="<%=(dinerInfo != null) ? dinerInfo.getDinerAccount() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-university"></span>
								</div>
							</div>
						</div>

						<!-- �p���H�m�W -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerContact" class="form-control"
								placeholder="�p���H�m�W" value="<%=(dinerInfo != null) ? dinerInfo.getDinerContact() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-user"></span>
								</div>
							</div>
						</div>

						<!-- �Ȧ�b�� ��W -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerAccountName" class="form-control"
								placeholder="�Ȧ�b�� ��W" value="<%=(dinerInfo != null) ? dinerInfo.getDinerAccountName() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-money-check-alt"></span>

								</div>
							</div>
						</div>

						<!-- �p���q��  �Яd������X-->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerPhone" class="form-control"
								placeholder="�p���q�� (�ж�g���)" value="<%=(dinerInfo != null) ? dinerInfo.getDinerPhone() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-phone"></span>
								</div>
							</div>
						</div>

						<!-- �p���Hemail  -->
						<div class="input-group mb-3 col-6">
							<input type="email" name="dinerEmail" class="form-control"
								placeholder="�p���Hemail" value="<%=(dinerInfo != null) ? dinerInfo.getDinerEmail() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-envelope"></span>
								</div>
							</div>
						</div>
						<!-- �νs  (�N�ӷ|�@���n�J�b���ϥ�) -->
						<div class="input-group mb-3 col-6">
							<input type="text" name="dinerTaxID" class="form-control"
								placeholder="�νs " value="<%=(dinerInfo != null) ? dinerInfo.getDinerTaxID() : "" %>">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-signature"></span>
								</div>
							</div>
						</div>

						<!-- �аݱz�c�⪺�ӫ~�O�ƻ�����? * -->
						<div class="form-group form-inline mb-3 col-6 row">
							<div class="col-4" style="text-align: right;">
								<label for="dinerType">�z�c�⪺�ӫ~����</label>
							</div>
							<div class="col-8">
								<select name="dinerType" class="form-control w-100">
									<option value="M">����\�I</option>
									<option value="D">��¶���</option>
									<option value="X">�ƦX�\��</option>
								</select>
							</div>
						</div>



					</div>


					<div class="row">
						<div class="col-8">
							<div class="icheck-primary">
								<input type="checkbox" id="agreeTerms" name="terms"
									value="agree"> <label for="agreeTerms"> �ڦP�N<a
									href="#">�ϥΪ̱���</a>��<a href="#">���p�v����</a>
								</label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-4">
							<input type="hidden" name="action" value="insert">
							<button type="submit" class="btn btn-primary btn-block">�e�X���U�ӽ�</button>
						</div>
						<!-- /.col -->
					</div>
				</form>



				<a href="${pageContext.request.contextPath}/dinerbackground/pages/Team/login/login-form.jsp" class="text-center">�w�O�Ӯa�|��?�Ыe���n�J</a>
			</div>
			<!-- /.form-box -->
		</div>
		<!-- /.card -->
	</div>
	<!-- /.register-box -->

	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/adminlte.min.js"></script>
</body>
</html>