<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dinerinfo.entity.*"%>
<%@ page import="com.dinerinfo.service.*"%>
<%@ page import="com.businesshours.entity.*"%>
<%@ page import="com.businesshours.service.*"%>
<%@ page import="java.util.*"%>


<%
// 	DinerInfoServiceImpl dinerSvc = new DinerInfoServiceImpl();
// 	DinerInfo account = dinerSvc.getDinerInfoByDinerID(1);
//測試用，之後順利連結頁面後要改以下這種

DinerInfo account = (DinerInfo) session.getAttribute("account");
// String dinerStatus = account.getDinerStatus();
//這是login之後傳進來的account，代表已登錄後才能看到的資料
Integer dinerID = account.getDinerID();
BuisnessHoursServiceImpl bh = new BuisnessHoursServiceImpl();
List<BusinessHours> businessHours = bh.getBusinessHoursByDinerID(dinerID);
%>

<html lang="zh-Hant">

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
	href="../../../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../../../dist/css/adminlte.min.css">

<!-- Select2 -->
<link rel="stylesheet"
	href="../../../plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="../../../plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
</head>

<body
	class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<!-- <div class="preloader flex-column justify-content-center align-items-center">
      <img class="animation__wobble" src="dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
    </div> -->

		<!-- 基本版面 上方懸浮欄 -->
		<!-- Navbar -->
		<nav class="main-header navbar navbar-expand navbar-dark">
			<!-- 上方懸浮欄 左方按鈕 -->
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a
					href="./dashboard-index" class="nav-link">首頁</a></li>

			</ul>

			<!-- 彈出右側下拉欄 -->
			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">


				<!-- 全螢幕控制鈕 -->
				<li class="nav-item"><a class="nav-link"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>

			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- 主要功能側邊欄(左側) -->
		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">

			<!-- 品牌logo -->
			<!-- Brand Logo -->
			<a href="index3.html" class="brand-link"> <img
				src="../../../dist/img/joLOGO.png" alt="joLOGO"
				class="brand-image img-circle elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-light">樓頂揪樓咖</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- 商家頭像 -->
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<img src="../../../dist/img/sara lance.png"
							class="img-circle elevation-2" alt="User Image">
					</div>
					<div class="info">
						<a href="#" class="d-block">Sara Lance</a>
					</div>
				</div>

				<!-- SidebarSearch Form -->

				<!-- 功能項目列 -->
				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
						<li class="nav-item  menu-open"><a href="#" class="nav-link">
								<i class="nav-icon fas fa-cogs"></i>
								<p>
									商家資料管理 <i class="right fas fa-angle-left"></i>
								</p>
						</a> <!-- 商家資料管理 子選項 -->
							<ul class="nav nav-treeview">

								<!-- 公司資料變更 -->
								<li class="nav-item"><a href="./info-change.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>公司資料變更</p>
								</a></li>

								<!-- 店面設定 -->
								<li class="nav-item"><a href="./business-set.html"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>店面設定</p>
								</a></li>

							</ul></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-file-upload"></i>
								<p>
									商品上架 <i class="right fas fa-angle-left"></i>
									<!-- <span class="badge badge-info right"></span> 這裡可以用來設定右邊顯示的訊息提醒小數字 -->
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="pages/layout/top-nav.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>商品群組設定</p>
								</a></li>
								<li class="nav-item"><a
									href="pages/layout/top-nav-sidebar.html" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>單獨上架</p>
								</a></li>
								<li class="nav-item"><a href="pages/layout/boxed.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>批次上架</p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-hammer"></i>
								<p>
									商品管理 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="pages/charts/chartjs.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>商品列表</p>
								</a></li>
								<li class="nav-item"><a href="pages/charts/flot.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>單獨商品頁面</p>
								</a></li>

							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-newspaper"></i>
								<p>
									訂單管理 <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="pages/UI/general.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>揪團成功訂單</p>
								</a></li>
								<li class="nav-item"><a href="pages/UI/icons.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>已完成訂單</p>
								</a></li>
								<li class="nav-item"><a href="pages/UI/buttons.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>已取消訂單</p>
								</a></li>
								<li class="nav-item"><a href="pages/UI/sliders.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>訂單查詢</p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-handshake"></i>
								<p>
									幫助中心 <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a
									href="../help/Guide-to-Adding-Products-list.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>上架教學</p>
								</a></li>
								<li class="nav-item"><a href="../help/monthly-report.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>每月報表</p>
								</a></li>
								<li class="nav-item"><a href="../help/sales-data.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>銷售數據</p>
								</a></li>
								<li class="nav-item"><a
									href="../help/request-Ad-placement.html" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>申請廣告上架</p>
								</a></li>
								<li class="nav-item"><a href="../help/comment-reply.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>評論區回覆</p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a
							href="../frontpage/frontpage-index.html" class="nav-link"> <i
								class="nav-icon fas fa-sign-out-alt"></i>
								<p>登出</p>
						</a></li>
				</nav>
				<!-- /.sidebar-menu -->
			</div>

			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-6">
							<h1>店面設定</h1>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">

					<!-- 營業時間設定 -->

					<div class="card collapsed-card">

						<div class="card-header">
							<div>
								<label> <%
 									// 檢查 businessHours 是否為空，並根據情況顯示適當的訊息
 									if (businessHours == null || businessHours.isEmpty()) {
 									out.print("營業時間 : 您目前還沒有設定");
									 } else {
 									out.print("營業時間 :");
									 }
										 %>
								</label>
							</div>



							<form action="<%=request.getContextPath()%>/businessHours.do"
								method="post">
								<%
								Map<String, String> dayMap = new HashMap<>();
								dayMap.put("Monday", "星期一");
								dayMap.put("Tuesday", "星期二");
								dayMap.put("Wednesday", "星期三");
								dayMap.put("Thursday", "星期四");
								dayMap.put("Friday", "星期五");
								dayMap.put("Saturday", "星期六");
								dayMap.put("Sunday", "星期日");

								String[] daysInEnglish = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
								for (String dayInEnglish : daysInEnglish) {
									String dayInChinese = dayMap.get(dayInEnglish);
									boolean found = false; // 用於追踪是否找到了對應的營業日
									for (BusinessHours hours : businessHours) {
										if (hours.getDayOfWeek().equals(dayInEnglish)) { // 確認該 BusinessHours 物件的營業日是否與當前迭代的 day 相匹配
								%>
								<input type="checkbox" name="dayOfWeek[<%=dayInEnglish%>]"
									id="open<%=dayInEnglish%>" data-bootstrap-switch
									data-rendered="true"
									<%="Open".equals(hours.getOpenStatus()) ? "checked" : ""%>
									onchange="confirmStatusChange('<%=dayInEnglish%>', '<%=dayInChinese%>', this, this.form);">
								<label for="open<%=dayInEnglish%>" class="inline"><h5><%=dayInChinese%></h5></label>&ensp;&ensp;&ensp;

								<!-- 隱藏的 input 用於儲存狀態 -->
								<input type="hidden" name="dayOfWeekStatus[<%=dayInEnglish%>]"
									id="hiddenInput<%=dayInEnglish%>"
									value="<%=hours.getOpenStatus()%>"> <span>開店時間 :<%=hours.getOpenTime()%></span>&ensp;&ensp;&ensp;
								<span>關店時間 : <%=hours.getCloseTime()%></span> <br>
								<%
								found = true; // 標記已找到匹配的營業日
								break; // 找到匹配的 BusinessHours 物件後，終止內部迴圈
								}
								}

								if (!found) { // 如果沒有找到匹配的營業日，則顯示預設的消息或空白
								%>
								<span></span>
								<%
								}
								}
								%>
							</form>






							<div class="card-tools">
								<!-- Collapse Button -->
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-plus"></i>
								</button>
							</div>
							<!-- /.card-tools -->
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<label for="openTimeSetting" class="inline">
								<h5>營業時間設定</h5>
							</label> <br>

							<form action="<%=request.getContextPath()%>/businessHours.do"
								method="post">

								<div class="form-group row form-inline">
									<div class="col">
										<span>營業日 :</span> <select name="dayOfWeek" id="dayOfWeek"
											class="form-control">
											<option>星期一</option>
											<option>星期二</option>
											<option>星期三</option>
											<option>星期四</option>
											<option>星期五</option>
											<option>星期六</option>
											<option>星期日</option>
										</select>
									</div>
									<div class="col">
										<!-- 開店時間： -->
										<span>開店時間 :</span> <select id="openTime"
											name="openTime[dayOfWeek]" class="form-control">
											<option selected="selected">9:00</option>
											<option>9:30</option>
											<option>10:00</option>
											<option>10:30</option>
											<option>11:00</option>
											<option>10:30</option>
											<option>12:00</option>
											<option>10:30</option>
											<option>13:00</option>
											<option>13:30</option>
											<option>14:00</option>
											<option>14:30</option>
											<option>15:00</option>
											<option>15:30</option>
											<option>16:00</option>
											<option>16:30</option>
											<option>17:00</option>
											<option>17:30</option>
											<option>18:00</option>
											<option>18:30</option>
											<option>19:00</option>
											<option>19:30</option>
											<option>20:00</option>
											<option>20:30</option>
										</select>
									</div>

									<div class="col">
										<!-- 關店時間： -->
										<span>關店時間 :</span> <select id="closeTime"
											name="closeTime[dayOfWeek]" class="form-control">
											<option selected="selected">9:30</option>
											<option>10:00</option>
											<option>10:30</option>
											<option>11:00</option>
											<option>10:30</option>
											<option>12:00</option>
											<option>10:30</option>
											<option>13:00</option>
											<option>13:30</option>
											<option>14:00</option>
											<option>14:30</option>
											<option>15:00</option>
											<option>15:30</option>
											<option>16:00</option>
											<option>16:30</option>
											<option>17:00</option>
											<option>17:30</option>
											<option>18:00</option>
											<option>18:30</option>
											<option>19:00</option>
											<option>19:30</option>
											<option>20:00</option>
											<option>20:30</option>
											<option>21:00</option>
										</select>
									</div>
									<div class="col">
										<input type="hidden" name="action" value="businessChange">
										<button class="btn btn-primary w-80">變更</button>
									</div>
								</div>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
					</form>

					<form
						action="<%=request.getContextPath()%>/dinerbackground/pages/Team/dashboard/dinerInfo.do"
						method="post">



						<h6>成團訂單金額 :</h6>
						<div class="input-group mb-3 col-6">
							<input type="text" id="uniqueOrderAmount"
								name="dinerOrderThreshold" class="form-control"
								placeholder="請填入大於零的數字" value="${account.dinerOrderThreshold}">&ensp;
							<h6>元</h6>
							&ensp;&ensp;
							<button type="button" class="btn btn-primary" data-toggle="modal"
								data-target="#uniqueConfirmModal">確認訂單金額</button>

						</div>

						<%-- 成團訂單金額   錯誤表列 --%>
						<c:if test="${not empty orderThresholdErrorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${orderThresholdErrorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<!-- Modal -->
						<div class="modal fade" id="uniqueConfirmModal" tabindex="-1"
							role="dialog" aria-labelledby="uniqueModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="uniqueModalLabel">確認更動</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										是否確認將成團訂單金額更動為<span id="uniqueConfirmAmount">xxx</span>元?



									</div>
									<div class="modal-footer">
										<input type="hidden" name="action"
											value="dinerOrderThresholdChange"> <input
											type="hidden" name="dinerID" value="${account.dinerID}">
										<button type="submit" class="btn btn-primary">確認</button>
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</form>


					<form id="uploadForm"
						action="<%=request.getContextPath()%>/dinerbackground/pages/Team/dashboard/dinerInfo.do"
						method="post" enctype="multipart/form-data">
						<!-- 餐廳照片上傳 -->
						<br>
						<div class="form-group">
							<label for="exampleInputFile">餐廳封面照片上傳</label>
							<div class="input-group">
								<div class="custom-file">
									<input type="file" name="dinerBlob" class="custom-file-input"
										id="exampleInputFile" onchange="previewImage(this)"> <label
										class="custom-file-label" for="exampleInputFile">選擇檔案</label>
								</div>

							</div>
						</div>


						<div style="display: flex; gap: 10px;">


							<!-- 預覽圖 -->
							<div
								style="border: 3px dashed #ccc; width: 500px; height: 300px; display: flex; align-items: center; justify-content: center;">
								<img id="imagePreview" src="" alt="圖片預覽"
									style="max-width: 100%; max-height: 100%; display: none;">
								<span class="text"></span>
							</div>


						</div>


						<br> <br>
						<!-- 確認上傳按鈕和模態框 -->
						<!-- 						<button type="button" class="btn btn-primary" data-toggle="modal" -->
						<!-- 							data-target="#confirmModal">上傳圖片</button> -->

						<button type="button" class="btn btn-primary" id="uploadBtn"
							onclick="checkAndOpenModal()">上傳圖片</button>

						<%-- 商家圖片   錯誤表列 --%>
						<c:if test="${not empty dinerBloberrorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${dinerBloberrorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>

								<div id="errorMsgSection" style="display: none;">
									<ul id="errorList"></ul>
								</div>


							</ul>
						</c:if>

						<div class="modal" id="confirmModal">
							<div class="modal-dialog">
								<div class="modal-content">

									<div class="modal-header">
										<h4 class="modal-title">確認上傳</h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>

									<div class="modal-body">是否確認上傳圖片？</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											onclick="confirmUpload()">確認</button>
										<input type="hidden" name="action" value="addDinerBlob">
										<input type="hidden" name="dinerID" value="${account.dinerID}">
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">取消</button>
									</div>

								</div>
							</div>
						</div>
					</form>

					<!-- 							舊圖區 -->
					<div
						style="border: 3px dashed #ddd; width: 500px; height: 300px; display: flex; align-items: center; justify-content: center;">
						<img id="oldImage"
							src="${pageContext.request.contextPath}/DinerInfoImg?id=${account.dinerID}"
							alt="舊圖片" style="max-width: 100%; max-height: 100%;">
					</div>

				</div>
		</div>
		<!--/. container-fluid -->
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
	<script src="../../../plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../../../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- overlayScrollbars -->
	<script
		src="../../../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../../../dist/js/adminlte.js"></script>

	<!-- PAGE PLUGINS -->
	<!-- jQuery Mapael -->
	<script src="../../../plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
	<script src="../../../plugins/raphael/raphael.min.js"></script>
	<script src="../../../plugins/jquery-mapael/jquery.mapael.min.js"></script>
	<script src="../../../plugins/jquery-mapael/maps/usa_states.min.js"></script>
	<!-- ChartJS -->
	<script src="../../../plugins/chart.js/Chart.min.js"></script>

	<!-- AdminLTE for demo purposes -->
	<script src="../../../dist/js/demo.js"></script>


	<!-- Bootstrap Switch -->
	<script
		src="../../../plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>

	<!-- Select2 -->
	<script src="../../../plugins/select2/js/select2.full.min.js"></script>

	<script>
		//控制開關店按鈕

		function confirmStatusChange(dayInEnglish, dayInChinese,
				checkboxElement, formElement) {
			// 檢查此input是否是渲染的資料
			if (checkboxElement.getAttribute('data-rendered')) {
				checkboxElement.removeAttribute('data-rendered');
				return; // 不觸發確認框
			}

			var action = checkboxElement.checked ? "開啟" : "關閉";
			var confirmMessage = "您確定要" + action + dayInChinese + "的營業日嗎?";

			if (confirm(confirmMessage)) {
				updateHiddenInput(dayInEnglish, checkboxElement, formElement);
			} else {
				// 使用者選擇"取消"，所以我們需要還原checkbox的狀態
				checkboxElement.checked = !checkboxElement.checked;
			}
		}

		function updateHiddenInput(day, checkboxElement, formElement) {
			var hiddenInput = document.getElementById('hiddenInput' + day);
			if (checkboxElement.checked) {
				hiddenInput.value = 'Open';
			} else {
				hiddenInput.value = 'Close';
			}
			formElement.submit(); // 新增的程式碼：提交表單
		}

		// 確保在點擊確認訂單金額的按鈕時，會從輸入框中取得金額，然後更新到模態框中
		$(document).ready(
				function() {
					$('button[data-target="#uniqueConfirmModal"]').on('click',
							function() {
								var amount = $('#uniqueOrderAmount').val();
								$('#uniqueConfirmAmount').text(amount);
							});
				});

		// 		圖片上傳區
		function previewImage(input) {
			// 定義一個名為previewImage的函數，並將選擇文件的input元素作為參數
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#imagePreview').attr('src', e.target.result).show();
				}
				reader.readAsDataURL(input.files[0]);
			}
		}

		function confirmUpload() {
			// 在此處添加上傳的邏輯代碼（如上傳到伺服器）
			document.getElementById("uploadForm").submit();
			// 上傳成功後更新畫面
			document.getElementById('oldImage').src = "new_path_to_updated_image_from_database";
			document.getElementById('imagePreview').style.display = 'none';
			document.getElementById('imagePreview').src = '';

			$('#confirmModal').modal('hide'); // 關閉模態框
		}

		function checkAndOpenModal() {
			var fileInput = document.querySelector("#exampleInputFile");
			var hasFile = fileInput && fileInput.files
					&& fileInput.files.length > 0;

			if (!hasFile) {
				// 如果用戶沒有選擇圖片，添加錯誤消息並阻止模態框彈出
				// 				var errorList = document.createElement("ul");
				// 				errorList.innerHTML = "<li style='color: red'>您還沒增加任何圖片</li>";
				var errorList = document.getElementById("errorList");
				errorList.innerHTML = "<li style='color: red'>您還沒增加任何圖片</li>";
				errorMsgSection.style.display = "block"; // 讓錯誤信息可見
				var errorMsgSection = document
						.querySelector("#errorMsgSection");
				if (errorMsgSection) {
					errorMsgSection.appendChild(errorList);
				} else {
					// 如果不存在錯誤消息部分，則先創建它
					var newErrorSection = document.createElement("div");
					newErrorSection.setAttribute("dinerBloberrorMsgs", "");
					newErrorSection.appendChild(errorList);
					document.body.appendChild(newErrorSection);
				}
			} else {
				// 如果用戶選擇了圖片，則彈出模態框
				$('#confirmModal').modal('show');
			}
		}
	</script>



	<script>
		$(function() {
			//Initialize Select2 Elements
			$('.select2').select2()
			//Initialize Select2 Elements
			$('.select2bs4').select2({
				theme : 'bootstrap4'
			})

			$("input[data-bootstrap-switch]").each(function() {
				$(this).bootstrapSwitch('state', $(this).prop('checked'));
			})

		})
	</script>

</body>

</html>