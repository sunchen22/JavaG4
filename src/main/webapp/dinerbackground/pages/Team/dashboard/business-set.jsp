<%@page import="org.hibernate.internal.build.AllowSysOut"%>
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
String dinerStatus = account.getDinerStatus();
//這是login之後傳進來的account，代表已登錄後才能看到的資料
Integer dinerID = account.getDinerID();
BusinessHoursServiceImpl bh = new BusinessHoursServiceImpl();
List<BusinessHours> businessHours = bh.getBusinessHoursByDinerID(dinerID);

session.setAttribute("businessHours", businessHours);
// pageContext.setAttribute("businessHours", businessHours);

System.out.println("businessHours"+businessHours);

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
	href="<%=request.getContextPath()%>/dinerbackground/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">

<!-- Select2 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
</head>

<body
	class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
	<div class="wrapper">

	
		<%@ include file="/dinerbackground/pages/Team/dinerNav.file"%>
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
							
							
								<label>
								 <%
 									// 檢查 businessHours 是否為空，並根據情況顯示適當的訊息
 									if (businessHours == null || businessHours.isEmpty()) {
 									out.print("您已成功設定營業時間");
									 } else {
 									out.print("營業時間 :");
									 }
								 
								 
										 %>
								</label>
							</div>

<P></P>

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
										if (hours != null && hours.getDayOfWeek() != null && hours.getDayOfWeek().equalsIgnoreCase(dayInEnglish)) { // 確認該 BusinessHours 物件的營業日是否與當前迭代的 day 相匹配
								%>
<%-- 								<input type="checkbox" name="dayOfWeek[<%=dayInEnglish%>]" --%>
<%-- 									id="open<%=dayInEnglish%>"  --%>
<!-- 									data-bootstrap-switch -->
<!-- 									data-rendered="true" -->
<%-- 									<%="Open".equals(hours.getOpenStatus()) ? "checked" : ""%> --%>
<%-- 									onchange="confirmStatusChange('<%=dayInEnglish%>', '<%=dayInChinese%>', this, this.form);"> --%>
								<label for="open<%=dayInEnglish%>" class="inline"><h5><%=dayInChinese%></h5></label>&ensp;&ensp;&ensp;

								<!-- 隱藏的 input 用於儲存狀態 -->
<%-- 								<input type="hidden" name="dayOfWeekStatus[<%=dayInEnglish%>]" --%>
<%-- 									id="hiddenInput<%=dayInEnglish%>" --%>
<%-- 									value="<%=hours.getOpenStatus()%>"> --%>

									<input type="hidden" name="action" value="dayOfWeekStatus[<%=dayInEnglish%>]">
<%-- 									<input type="hidden" name="openStatus" value="<%=hours.getOpenStatus()%>"> --%>

								<input type="hidden" name="openStatus"
									id="hiddenInput<%=dayInEnglish%>"
									value="<%=hours.getOpenStatus()%>">
									
									<input type="hidden" name="dinerID" value="${account.dinerID}">
								
								<span>開店時間 :<%=hours.getOpenTime()%></span>&ensp;&ensp;&ensp;
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





<!-- 							這裡是營業時間更改的部分 -->
<!-- 							<div class="card-tools"> -->
<!-- 								Collapse Button -->
<!-- 								<button type="button" class="btn btn-tool" -->
<!-- 									data-card-widget="collapse"> -->
<!-- 									<i class="fas fa-plus"></i> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 							/.card-tools -->
<!-- 						</div> -->
<!-- 						/.card-header -->
<!-- 						<div class="card-body"> -->
<!-- 							<label for="openTimeSetting" class="inline"> -->
<!-- 								<h5>營業時間設定</h5> -->
<!-- 							</label> <br> -->

<%-- 							<form action="<%=request.getContextPath()%>/businessHours.do" --%>
<!-- 								method="post"> -->

<!-- 								<div class="form-group row form-inline"> -->
<!-- 									<div class="col"> -->
<!-- 										<span>營業日 :</span>  -->
<!-- 										<select name="dayOfWeek" id="dayOfWeek" -->
<!-- 											class="form-control"> -->
<!-- 											<option value="Monday">星期一</option> -->
<!-- 											<option value="Tuesday">星期二</option> -->
<!-- 											<option value="Wednesday">星期三</option> -->
<!-- 											<option value="Thursday">星期四</option> -->
<!-- 											<option value="Friday">星期五</option> -->
											
<!-- 										</select> -->
<!-- 									</div> -->
<!-- 									<div class="col"> -->
<!-- 										開店時間： -->
<!-- 										<span>開店時間 :</span> <select id="openTime" -->
<!-- 											name="openTime" class="form-control"> -->
<!-- 											<option selected="selected">9:00</option> -->
<!-- 											<option value="0930">9:30</option> -->
<!-- 											<option value="1000">10:00</option> -->
<!-- 											<option  value="1030">10:30</option> -->
<!-- 											<option  value="1100">11:00</option> -->
<!-- 											<option  value="1130">11:30</option> -->
<!-- 											<option value="1200">12:00</option> -->
<!-- 											<option  value="1230">12:30</option> -->
<!-- 											<option  value="1300">13:00</option> -->
<!-- 											<option  value="1330">13:30</option> -->
<!-- 											<option  value="1400">14:00</option> -->
<!-- 											<option  value="1430">14:30</option> -->
<!-- 											<option  value="1500">15:00</option> -->
<!-- 											<option  value="1530">15:30</option> -->
<!-- 											<option  value="1600">16:00</option> -->
<!-- 											<option value="1630">16:30</option> -->
<!-- 											<option  value="1700">17:00</option> -->
<!-- 											<option  value="1730">17:30</option> -->
<!-- 											<option  value="1800">18:00</option> -->
<!-- 											<option  value="1830">18:30</option> -->
<!-- 											<option  value="1900">19:00</option> -->
<!-- 											<option  value="1930">19:30</option> -->
<!-- 											<option  value="2000">20:00</option> -->
<!-- 											<option  value="2030">20:30</option> -->
<!-- 										</select> -->
<!-- 									</div> -->

<!-- 									<div class="col"> -->
<!-- 										關店時間： -->
<!-- 										<span>關店時間 :</span> <select id="closeTime" -->
<!-- 											name="closeTime[dayOfWeek]" class="form-control"> -->
<!-- 											<option selected="selected">9:30</option> -->
<!-- 											<option>10:00</option> -->
<!-- 											<option>10:30</option> -->
<!-- 											<option>11:00</option> -->
<!-- 											<option>10:30</option> -->
<!-- 											<option>12:00</option> -->
<!-- 											<option>10:30</option> -->
<!-- 											<option>13:00</option> -->
<!-- 											<option>13:30</option> -->
<!-- 											<option>14:00</option> -->
<!-- 											<option>14:30</option> -->
<!-- 											<option>15:00</option> -->
<!-- 											<option>15:30</option> -->
<!-- 											<option>16:00</option> -->
<!-- 											<option>16:30</option> -->
<!-- 											<option>17:00</option> -->
<!-- 											<option>17:30</option> -->
<!-- 											<option>18:00</option> -->
<!-- 											<option>18:30</option> -->
<!-- 											<option>19:00</option> -->
<!-- 											<option>19:30</option> -->
<!-- 											<option>20:00</option> -->
<!-- 											<option>20:30</option> -->
<!-- 											<option>21:00</option> -->
<!-- 										</select> -->
<!-- 									</div> -->
<!-- 									<div class="col"> -->
<!-- 										<input type="hidden" name="action" value="businessChange"> -->
<!-- 										<button class="btn btn-primary w-80">變更</button> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 						</div> -->
<!-- 						/.card-body -->
<!-- 					</div> -->
<!-- 					/.card -->
<!-- 					</form> -->
					
					<br>
					<br>

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


	<!-- Bootstrap Switch -->
	<script
		src="<%=request.getContextPath()%>/dinerbackground/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>

	<!-- Select2 -->
	<script src="<%=request.getContextPath()%>/dinerbackground/plugins/select2/js/select2.full.min.js"></script>

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