<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%@ page import="com.dinerinfo.service.*"%>
<%@ page import="com.advertisement.entity.*"%>
<%@ page import="com.advertisement.service.*"%>
<%@ page import="com.advertisement.dao.*"%>
<%@ page import="java.util.*"%>

<%
DinerInfo account = (DinerInfo) session.getAttribute("account");
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>樓頂揪樓咖 商家資料管理</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<!-- <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css"> -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
	integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">
<!-- jsGrid -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/jsgrid/jsgrid.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/jsgrid/jsgrid-theme.min.css">
</head>
<body
	class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
	<div class="wrapper">


		<%@ include file="/dinerbackground/pages/Team/dinerNav.file"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>商家Q&A</h1>
						</div>
						<div class="col-sm-6"></div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->


			<section class="content">

				<div class="card">
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-8 offset-md-2"> -->
<!-- 							<form action="simple-results.html"> -->
<!-- 								<div class="input-group"> -->
<!-- 									<input type="search" id="search-box" -->
<!-- 										class="form-control form-control-lg" placeholder="搜尋您想要的問題"> -->
<!-- 									<div class="input-group-append"> -->
<!-- 										<button type="submit" class="btn btn-lg btn-default"> -->
<!-- 											<i class="fa fa-search"></i> -->
<!-- 										</button> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</form> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<!-- /.card-header -->
					<div class="card-body">
						<div id="jsGrid1"></div>
					</div>
					<!-- /.card-body -->
				</div>

				<div>

<!-- 					<p> -->
<!-- 						找不到你想要問題嗎? <span> -->
<!-- 							<button type="button" class="btn btn-light"> -->
<%-- 								<a href="${pageContext.request.contextPath}/dinerbackground/pages/Team/help/contact-form.jsp">與客服人員聯繫</a> --%>
<!-- 							</button> -->
<!-- 					</p> -->
					</span>

				</div>
				<!-- /.card -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->


	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- overlayScrollbars -->
	<script
		src="<%=request.getContextPath()%>/dinerbackground/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/adminlte.js"></script>

	<!-- PAGE PLUGINS -->
	<!-- jQuery Mapael -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/raphael/raphael.min.js"></script>
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery-mapael/jquery.mapael.min.js"></script>
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jquery-mapael/maps/usa_states.min.js"></script>
	<!-- ChartJS -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/chart.js/Chart.min.js"></script>

	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/demo.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="<%=request.getContextPath()%>/dinerbackground/dist/js/pages/dashboard2.js"></script>
	<!-- Page specific script -->

	<!-- jsGrid -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jsgrid/demos/db.js"></script>
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/jsgrid/jsgrid.min.js"></script>

	<script>
		$(function() {
			$("#jsGrid1").jsGrid({
				height : "100%",
				width : "100%",

				sorting : true,
				paging : true,

				pageSize : 10,

				data : db.question,

				fields : [ {
					name : "questionType",
					type : "text",
					width : 100,
					title : "問題類型"
				}, {
					name : "questionContent",
					type : "text",
					width : 150,
					title : "問題內容"
				}, ]

			});
		});
	</script>
	<script>
		$("#search-box").on(
				"input",
				function() {
					var searchString = $(this).val().toLowerCase(); // 獲取搜尋字串，並轉為小寫

					// 篩選出符合條件的資料
					var filteredData = db.question.filter(function(item) {
						return item.questionContent.toLowerCase().indexOf(
								searchString) >= 0
								|| item.questionType.toLowerCase().indexOf(
										searchString) >= 0;
					});

					// 更新jsGrid的資料
					$("#jsGrid1").jsGrid("option", "data", filteredData);
				});
	</script>

</body>
</html>