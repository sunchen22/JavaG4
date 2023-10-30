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
// int dinerID = account.getDinerID();
// String dinerStatus = account.getDinerStatus();
//這是login之後傳進來的account，代表已登錄後才能看到的資料

// 以下是既有資料的引入
// AdvertisementDAOHibernateImpl ad = new AdvertisementDAOHibernateImpl();

// List<Advertisement> listSubmitted = ad.getSubmitted(dinerID);
// List<Advertisement> listDeactivated = ad.getDeactivated(dinerID);
// List<Advertisement> listActive = ad.getActive(dinerID);
// pageContext.setAttribute("listSubmitted", listSubmitted);
// pageContext.setAttribute("listDeactivated", listDeactivated);
// pageContext.setAttribute("listActive", listActive);

// 以下是使用者輸入資料的暫存點
Advertisement advertisement = (Advertisement) request.getAttribute("advertisement");
%>
<html lang="zh-Hant">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>樓頂揪樓咖 商家資料管理</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/fontawesome-free/css/all.min.css">
<!-- Ekko Lightbox -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/ekko-lightbox/ekko-lightbox.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/bs-stepper/css/bs-stepper.min.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Select2 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/bs-stepper/css/bs-stepper.min.css">
<!-- dropzonejs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dinerbackground/plugins/dropzone/min/dropzone.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/dinerbackground/dist/css/adminlte.min.css">


<style>
#imagePreview {
	width: 800px; /* 你可以根據需求調整這個寬度 */
	height: 300px; /* 你可以根據需求調整這個高度 */
	overflow: hidden; /* 這會確保超出預覽區域的內容不會被顯示 */
	position: relative; /* 這讓預覽區域的子元素可以使用絕對定位 */
	border: 1px solid #ccc; /* 添加邊框 */
}

#imagePreview img {
	max-width: 100%;
	max-height: 100%;
	display: block;
	margin: auto; /* 這會將圖片置中於預覽區域 */
	position: absolute; /* 使用絕對定位來確保圖片居中 */
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
}

#container-for-button {
	position: relative;
	/* 設置為相對定位 */
	height: 100%;
	/* 或其他適當的值，以確保容器有足夠的高度 */
}
</style>
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
							<h1>申請廣告上架</h1>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
			
			<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<form action="<%=request.getContextPath()%>/advertisement"
					method="post" enctype="multipart/form-data"
					id="requestADplacement">
					<div class="row" id="container-for-button">
						<div class="col mr-4">
							<div class="form-group">
								<!-- ==================廣告名稱(卡片抬頭) ============================================-->
								<label>廣告名稱</label>
								<div class="input-group" style="width: 250px;">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="far fa-lightbulb"></i>
										</span>
									</div>
									<input type="text" class="form-control float-right cardName"
										name="advertisementName"
										value="<%=(advertisement != null) ? advertisement.getAdvertisementName() : ""%>">
								</div>
								<br>
								<!-- ==================廣告上架區間 ============================================-->
								
<!-- 								這裡是一個時間轉換器，因為後端記錄錯誤的Advertisement對象我想要重新記錄到這兩個欄位中 -->
<!-- 								但是紀錄的是time，需要轉換回日期，才能再放入這兩個前端的欄位 -->
								<%@ page import="java.text.SimpleDateFormat"%>
								<%@ page import="java.util.Date"%>
								<%
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String formattedUpTime = (advertisement != null) ? sdf.format(advertisement.getAdvertisementUpTime()) : "";
								String formattedDownTime = (advertisement != null) ? sdf.format(advertisement.getAdvertisementDownTime()) : "";
								
								%>

								<div class="col-sm-10">
									<label for="startTime">上架時間</label> <input type="date"
										class="form-control" id="startTime" name="UpTime"
										required
										value="<%=(advertisement != null) ? formattedUpTime : ""%>">
								</div>
								<br>
								<div class="col-sm-10">
									<label for="endTime">下架時間</label> <input type="date"
										class="form-control" id="endTime" name="DownTime" 
										required
										value="<%=(advertisement != null) ? formattedDownTime : ""%>">
								</div>
								<br>
								<!-- =============廣告天數(自動跳轉) ============================================-->
								<label for="advertisementDuringTime">廣告天數</label>
								<div class="input-group" style="width: 250px;">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="far fa-clock"></i>
										</span>
									</div>
									<!-- 天數自動顯示 -->
									<input type="text" id="advertisementDuringTime"
										class="form-control float-right" readonly
										name="advertisementDuringTime" 
										value="<%=(advertisement != null) ? advertisement.getAdvertisementDuringTime() : ""%>">
								</div>
								<br>
								<!-- ================上傳廣告橫幅 ============================================-->
								<label for="ADInputFile">上傳廣告橫幅</label>
								<div class="input-group" style="width: 250px;">
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="ADInputFile"
										    name="advertisementBlob" 
											>
										<label class="custom-file-label" for="ADInputFile">選擇檔案</label>
									</div>
									<div class="input-group-append">
										<span class="input-group-text">上傳</span>
									</div>
								</div>
								<br>
								<button class="btn btn-primary" id="form-submit"  type="button" 
									style="width: 100px;">送出申請</button>
									
							</div>
						</div>
						<div class="col mr-4">
							<!-- ===============預覽圖片 ============================================-->
							<label for="imagePreview">預覽圖片</label>
							<div>
								<div id="imagePreview" class="col-12"></div>
							</div>
						</div>
					</div>

				<!-- ==================================確認送出後的彈出視窗  (頭)  =============================== -->
				<!-- Modal -->
				<div class="modal fade" id="confirmationModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">確認資料</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">請確認你的資料無誤。</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary"
									id="confirm-submit"
									>確認</button>
								<input type="hidden" name="action" value="requestAD">
								<input type="hidden" name="dinerID" value="${account.dinerID}">
								<input type="hidden" name="dinerTaxID" value="${account.dinerTaxID}">
								
							</div>
						</div>
					</div>
				</div>
				</form>
				<!-- ==================================確認送出後的彈出視窗  (尾)  =============================== -->
				<!--/. container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->


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
    // 獲取上架和下架的日期input元素和廣告天數的input元素
    const startTimeInput = document.getElementById('startTime');
    const endTimeInput = document.getElementById('endTime');
    const advertisementDuringTimeInput = document.getElementById('advertisementDuringTime');

    // 設定事件監聽器來檢查和計算日期
    startTimeInput.addEventListener('change', calculateDays);
    endTimeInput.addEventListener('change', calculateDays);

    function calculateDays() {
        // 獲取選擇的日期
        const startDate = new Date(startTimeInput.value);
        const endDate = new Date(endTimeInput.value);

        // 如果上架時間晚於下架時間，則顯示警告並重設日期
        if (startDate >= endDate) {
            alert('上架時間不能晚於或等於下架時間！');
            startTimeInput.value = '';
            endTimeInput.value = '';
            advertisementDuringTimeInput.value = '';
            return;
        }

        // 計算日期差異，並更新到廣告天數的input元素
        const diffTime = Math.abs(endDate - startDate);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); // 轉換為天數
        advertisementDuringTimeInput.value = diffDays;
    }
    
 // 用於檢查是否所有必要的欄位都已填寫
    function checkFields() {
        // 檢查廣告名稱
        if (document.querySelector('.cardName').value.trim() === '') {
            alert('請填寫廣告名稱！');
            return false;
        }

        // 檢查上架時間和下架時間
        if (document.getElementById('startTime').value === '' || document.getElementById('endTime').value === '') {
            alert('請選擇上架和下架的時間！');
            return false;
        }

        // 檢查廣告橫幅是否已上傳
        if (document.getElementById('ADInputFile').files.length === 0) {
            alert('請上傳廣告橫幅！');
            return false;
        }

        // 如果所有的檢查都通過，返回true
        return true;
    }

    // 申請表單提交控制
    document.getElementById('form-submit').addEventListener('click', function() {
//         event.preventDefault();
        // 如果檢查未通過，阻止提交
         // 檢查是否所有必要的欄位都已填寫
    if (checkFields()) {
        // 如果所有的檢查都通過，提交表單
        $('#confirmationModal').modal('show');

    }
    });
    
    document.getElementById('confirm-submit').addEventListener('click', function(event) {
//         event.preventDefault();
        // 提交表單
//         document.forms[1].submit();
         document.getElementById('requestADplacement').submit();

        // 清空預覽區的圖片
        document.getElementById('imagePreview').innerHTML = '';
    });

    

			// =============== 預覽圖片 ==========
			var imagefile = document.getElementById('ADInputFile');
			imagefile.addEventListener('change', function() {
				var reader = new FileReader();
				reader.onload = function(e) {
					// 清空預覽區域的內容
					document.getElementById('imagePreview').innerHTML = '';

					var ADimg = new Image();
					ADimg.src = e.target.result;
					document.getElementById('imagePreview').appendChild(ADimg);
				};
				reader.readAsDataURL(imagefile.files[0]);
			});
</script>






</body>
</html>