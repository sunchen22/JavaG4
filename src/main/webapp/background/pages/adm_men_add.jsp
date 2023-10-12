<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.webempadmin.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
WebempadminVO empVO = (WebempadminVO)request.getAttribute("empVO");
%>

<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>樓頂揪樓咖後台管理</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="../plugins/fontawesome-free/css/all.min.css">
<!-- IonIcons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../dist/css/adminlte.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="../plugins/daterangepicker/daterangepicker.css">


<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="../plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="../plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="../plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Select2 -->
<link rel="stylesheet" href="../plugins/select2/css/select2.min.css">
<!-- <link rel="stylesheet" href="../plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css"> -->
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="../plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<!-- <link rel="stylesheet" href="../plugins/bs-stepper/css/bs-stepper.min.css"> -->

<style>
#preview {
	border: 1px solid lightgray;
	display: inline-block;
	width: 100px;
	min-height: 100px;
	position: relative;
}

#preview span.text {
	position: absolute;
	display: inline-block;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: -1;
	color: lightgray;
}

#preview img.preview_img {
	width: 100%;
}
</style>

</head>
<!--
`body` tag options:

  Apply one or more of the following classes to to the body tag
  to get the desired effect

  * sidebar-collapse
  * sidebar-mini
-->

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button" onclick="toggleLogoutButton()"><i
						class="fas fa-bars"></i></a></li>

				<li class="nav-item d-none d-sm-inline-block "><a
					href="adm_men.jsp" class="nav-link">管理者帳號管理</a></li>
			</ul>

			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">

				<li class="nav-item"><a class="nav-link" href="../index3.html"
					role="button"> <i class="fas fa-home"></i>
				</a></li>

				<li class="nav-item"><a class="nav-link"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>

			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<!-- 有修改顏色 原本sidebar-dark-primary -->
		<aside class="main-sidebar sidebar-light-warning elevation-4">
			<!-- Brand Logo -->
			<a href="../index3.html" class="brand-link"> <img
				src="../dist/img/Logo.png" alt="樓頂揪樓咖 Logo"
				class="brand-image img-circle elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-normal">後台管理平台</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<img src="../dist/empimg/emp02.png" class="img-circle elevation-2"
							alt="emp01">
					</div>
					<div class="info">
						<a href="#" class="d-block">小丸子</a>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->

						<!-- 側邊欄主標要打開用class="nav-item menu-open" -->
						<li class="nav-item menu-open">
							<!-- 若要自動打開設定<a href="#" class="nav-link active"></a> --> <a
							href="#" class="nav-link active"> <i
								class="nav-icon fas fa-book"></i>
								<p>
									管理者管理 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="./adm_sales.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>銷售狀況</p>
								</a></li>
								<li class="nav-item"><a href="./adm_people.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>線上會員人數</p>
								</a></li>
								<!-- 側邊欄次標要打開用class="nav-link active" -->
								<li class="nav-item"><a href="./adm_men.jsp"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>管理者帳號管理</p>
								</a></li>
							</ul>
						</li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-user"></i>
								<p>
									會員管理 <i class="fas fa-angle-left right"></i>
									<!-- 可省略/側邊欄的綠色標示提醒 -->
									<!-- <span class="badge badge-info right">6</span> -->
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="./mem_account.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>會員資料查詢</p>
								</a></li>
								<li class="nav-item"><a href="./mem_profile.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>會員資料與權限變更</p>
								</a></li>
								<li class="nav-item"><a href="./mem_news.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>會員最新消息</p>
								</a></li>

							</ul></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-briefcase"></i>
								<p>
									商家管理 <i class="fas fa-angle-left right"></i>
								</p>
						</a>

							<ul class="nav nav-treeview">
								<!-- <li class="nav-header">商家列表查詢</li> -->
								<li class="nav-item"><a href="./pages/mer_list.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>商家列表查詢</p>
								</a></li>
								<li class="nav-item"><a href="./pages/mer_application.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>商家申請審核</p>
								</a></li>
								<li class="nav-item"><a href="./pages/mer_details.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>商家資料異動</p>
								</a></li>

								<li class="nav-item"><a href="./pages/mer_product.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>商品審核</p>
								</a></li>
								<li class="nav-item"><a href="./pages/mer_payment.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>金流報表</p>
								</a></li>
								<li class="nav-item"><a href="./pages/mer_ad.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>商家廣告審核</p>
								</a></li>
								<li class="nav-item"><a href="./pages/mer_news.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>給商家最新消息</p>
								</a></li>

							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-edit"></i>
								<p>
									大樓資料管理 <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="./bldg_query.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>大樓資料查詢</p>
								</a></li>
								<li class="nav-item"><a href="./bldg_create.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>建立新大樓</p>
								</a></li>
								<li class="nav-item"><a href="./bldg_modify.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>修改/刪除大樓資訊</p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-table"></i>
								<p>
									訂單管理 <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="./pages/ord_query.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>訂單查詢</p>
								</a></li>
								<li class="nav-item"><a href="./pages/ord_review.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>評論管理</p>
								</a></li>

							</ul></li>

						<li class="nav-item"><a href="#" class="nav-link"> <!-- <i class="nav-icon fas fa-book"></i> -->
								<i class="nav-icon fas fa-comment"></i>
								<p>
									客服系統 <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="./FAQ_Page.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>FAQ頁面管理</p>
								</a></li>
								<li class="nav-item"><a href="./svr_mem_msg.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>系統留言管理</p>
								</a></li>
								<li class="nav-item"><a href="./svr_cust.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>線上客服訊息管理</p>
								</a></li>
							</ul>
							<ul class="nav nav-pills nav-sidebar " data-accordion="false"
								style="justify-content: flex-end">
								<li class="col-sm-5">
									<button id="logoutButton" type="button"
										class="btn btn-block btn-outline-warning btn-sm">
										<i class="fa fa-sign-out-alt  nav-icon"></i>登出
									</button>
								</li>
							</ul>
					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper" style="height: AUTO;">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1></h1>
						</div>

					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->

			<!-- general form elements -->
			<div class="content">
				<div class="container-fluid">
					<div class="col-md-6">
						<div class="card card-warning">
							<div class="card-header">
								<h3 class="card-title">
									<i class="fa fa-plus-circle"></i>&nbsp;建立新管理者帳號
								</h3>
							</div>
							<!-- /.card-header -->

							<form METHOD="post"
<%-- 								ACTION="<%=request.getContextPath()%>/pages/emp.do" --%>
								ACTION="emp.do"
								NAME="form_admadd"
								ENCTYPE="multipart/form-data">
								<div class="card-body" style="padding-bottom: 5px;">
									<div class="form-group">

										<%
										String empName;
										%>
										<label>員工姓名 </label> <input type="text" class="form-control "
											name="empName"
											value="<%if (empVO == null) {
	empName = "請輸入員工姓名";
} else {
	empName = empVO.getEmpName();
}%>"
											placeholder="請輸入員工姓名">
									</div>

									<%
									String passwordValue;
									%>
									<div class="form-group">
										<label>密碼 </label> <input type="password"
											class="form-control " name="empPassword" id="password"
											value="<% if (empVO == null) {
														passwordValue = "請輸入密碼";
													} else {
														passwordValue = empVO.getEmpPassword();
													}%>"
											placeholder="請輸入密碼">
									</div>

									<div class="form-group">
										<label>再次輸入密碼 </label> <input type="password"
											class="form-control" id="confirmPassword"
											placeholder="請再次輸入密碼">
									</div>
									<div id="passwordError" style="color: red;"></div>

									<div class="form-group">
										<label>員工大頭照 </label> <br> 
										<input type="file" id="p_file" name="empBlob">
										<div id="preview">
											<span class="text">預覽圖</span>
										</div>
									</div>

									<!-- Date -->
<% 
  java.sql.Date empArriveDate = null;
  try {
	  empArriveDate = empVO.getEmpArriveDate();
   } catch (Exception e) {
	   empArriveDate = new java.sql.Date(System.currentTimeMillis());
   }
%>
									<div class="form-group">
										<label>到職時間 </label>
										<div class="input-group date" id="reservationdate" data-target-input="nearest">
											<input type="text" class="form-control datetimepicker-input"
												data-target="#reservationdate" name="empArriveDate" />
											<div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">
												<div class="input-group-text">
													<i class="fa fa-calendar"></i>
												</div>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label for="exampleSelectBorder">權限等級</label> 
										<select class="custom-select form-control-border" name="empAdminAuthorization">
											<option value="staff" ${(empVO.empAdminAuthorization == 'staff') ? 'selected' : ''}>職員</option>
       									 	<option value="manager" ${(empVO.empAdminAuthorization == 'manager') ? 'selected' : ''}>經理</option>
										</select>
									</div>

								</div>
								<!-- /.card-body -->
								<div class="card-footer" style="text-align: center; padding-top: 5px; background: transparent">
									
										<input type="hidden" name="action" value="insert">
										<input type="submit" id="submitbtn" name="action" value="確定" class="btn btn-warning">
									
									<button type="submit" class="btn btn-warning">取消</button>
									
								</div>
							</form>
						</div>
						<!-- /.card -->
					</div>
				</div>
				<!-- container-fluid end -->
			</div>


		</div>
		<!-- /.content-wrapper -->


		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-warning">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<strong>Copyright &copy; 2023</strong> 樓頂揪樓咖團隊 All rights reserved.
			<div class="float-right d-none d-sm-inline-block">
				<b>Version</b> 1.1.0
			</div>
		</footer>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->
	<!-- jQuery -->
	<script src="../plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Select2 -->
	<script src="../plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<script
		src="../plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
	<!-- InputMask -->
	<script src="../plugins/moment/moment.min.js"></script>
	<script src="../plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- date-range-picker -->
	<script src="../plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="../plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="../plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Bootstrap Switch -->
	<script src="../plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../dist/js/adminlte.min.js"></script>
	<!-- 縮小時,登出按鈕消失 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<!-- 縮小時,登出按鈕消失 -->
	<script>
		function toggleLogoutButton() {
			var logoutButton = document.getElementById("logoutButton");
			if (logoutButton.style.display === "none") {
				logoutButton.style.display = "block";
			} else {
				logoutButton.style.display = "none";
			}
		}
	</script>





	<!-- Page specific script -->
	<script>
		$(function() {
			//Initialize Select2 Elements
			$('.select2').select2()

			//Initialize Select2 Elements
			$('.select2bs4').select2({
				theme : 'bootstrap4'
			})

			//Datemask dd/mm/yyyy
			$('#datemask').inputmask('dd/mm/yyyy', {
				'placeholder' : 'dd/mm/yyyy'
			})
			//Datemask2 mm/dd/yyyy
			$('#datemask2').inputmask('mm/dd/yyyy', {
				'placeholder' : 'mm/dd/yyyy'
			})
			//Money Euro
			$('[data-mask]').inputmask()

			//Date picker
			$('#reservationdate').datetimepicker({
				format : 'L'
			});

			//Date and time picker
			$('#reservationdatetime').datetimepicker({
				icons : {
					time : 'far fa-clock'
				}
			});

			//Date range picker
			$('#reservation').daterangepicker()
			//Date range picker with time picker
			$('#reservationtime').daterangepicker({
				timePicker : true,
				timePickerIncrement : 30,
				locale : {
					format : 'MM/DD/YYYY hh:mm A'
				}
			})
			//Date range as a button
			$('#daterange-btn').daterangepicker(
					{
						ranges : {
							'Today' : [ moment(), moment() ],
							'Yesterday' : [ moment().subtract(1, 'days'),
									moment().subtract(1, 'days') ],
							'Last 7 Days' : [ moment().subtract(6, 'days'),
									moment() ],
							'Last 30 Days' : [ moment().subtract(29, 'days'),
									moment() ],
							'This Month' : [ moment().startOf('month'),
									moment().endOf('month') ],
							'Last Month' : [
									moment().subtract(1, 'month').startOf(
											'month'),
									moment().subtract(1, 'month')
											.endOf('month') ]
						},
						startDate : moment().subtract(29, 'days'),
						endDate : moment()
					},
					function(start, end) {
						$('#reportrange span').html(
								start.format('MMMM D, YYYY') + ' - '
										+ end.format('MMMM D, YYYY'))
					})

			//Timepicker
			$('#timepicker').datetimepicker({
				format : 'LT'
			})

			//Bootstrap Duallistbox
			$('.duallistbox').bootstrapDualListbox()

			//Colorpicker
			$('.my-colorpicker1').colorpicker()
			//color picker with addon
			$('.my-colorpicker2').colorpicker()

			$('.my-colorpicker2').on(
					'colorpickerChange',
					function(event) {
						$('.my-colorpicker2 .fa-square').css('color',
								event.color.toString());
					})

			$("input[data-bootstrap-switch]").each(function() {
				$(this).bootstrapSwitch('state', $(this).prop('checked'));
			})

		})
		// BS-Stepper Init
		document.addEventListener('DOMContentLoaded', function() {
			window.stepper = new Stepper(document.querySelector('.bs-stepper'))
		})

		// DropzoneJS Demo Code Start
		Dropzone.autoDiscover = false

		// Get the template HTML and remove it from the doumenthe template HTML and remove it from the doument
		var previewNode = document.querySelector("#template")
		previewNode.id = ""
		var previewTemplate = previewNode.parentNode.innerHTML
		previewNode.parentNode.removeChild(previewNode)

		var myDropzone = new Dropzone(document.body, { // Make the whole body a dropzone
			url : "/target-url", // Set the url
			thumbnailWidth : 80,
			thumbnailHeight : 80,
			parallelUploads : 20,
			previewTemplate : previewTemplate,
			autoQueue : false, // Make sure the files aren't queued until manually added
			previewsContainer : "#previews", // Define the container to display the previews
			clickable : ".fileinput-button" // Define the element that should be used as click trigger to select files.
		})

		myDropzone.on("addedfile", function(file) {
			// Hookup the start button
			file.previewElement.querySelector(".start").onclick = function() {
				myDropzone.enqueueFile(file)
			}
		})

		// Update the total progress bar
		myDropzone
				.on(
						"totaluploadprogress",
						function(progress) {
							document
									.querySelector("#total-progress .progress-bar").style.width = progress
									+ "%"
						})

		myDropzone.on("sending", function(file) {
			// Show the total progress bar when upload starts
			document.querySelector("#total-progress").style.opacity = "1"
			// And disable the start button
			file.previewElement.querySelector(".start").setAttribute(
					"disabled", "disabled")
		})

		// Hide the total progress bar when nothing's uploading anymore
		myDropzone.on("queuecomplete", function(progress) {
			document.querySelector("#total-progress").style.opacity = "0"
		})

		// Setup the buttons for all transfers
		// The "add files" button doesn't need to be setup because the config
		// `clickable` has already been specified.
		document.querySelector("#actions .start").onclick = function() {
			myDropzone.enqueueFiles(myDropzone
					.getFilesWithStatus(Dropzone.ADDED))
		}
		document.querySelector("#actions .cancel").onclick = function() {
			myDropzone.removeAllFiles(true)
		}
		// DropzoneJS Demo Code End
	</script>

	<!-- 兩次輸入密碼比對 -->
	<script>
		const passwordInput = document.getElementById('password');
		const confirmPasswordInput = document.getElementById('confirmPassword');
		const passwordError = document.getElementById('passwordError');
		const submitbtn_c = document.getElementById('submitbtn');
		console.log(passwordInput);
		console.log(confirmPasswordInput);

		window.onload = function() {
			confirmPassword.addEventListener('blur', function(e) {
				let cpvalue = confirmPasswordInput.value;
				let pvalue = passwordInput.value;
				console.log(cpvalue);
				console.log(pvalue);

				if (cpvalue !== pvalue) {
					console.log('NO MATCH')
					document.getElementById("confirmPassword").value = ""; //清空輸入框
					passwordError.textContent = '密碼匹配不正確，請再次輸入';
					submitbtn_c.disabled = true;
				} else {
					console.log('OK');
					passwordError.textContent = '';
					submitbtn_c.disabled = false;
				}
			});
		};
	</script>

	<!-- 員工圖片預覽圖 -->
	<script>
	var preview_el = document.getElementById("preview");
	var p_file_el = document.getElementById("p_file");
// 	preview_img(e.dataTransfer.files[0]);
//     p_file_el.value = ""; // 將 type="file" 那個清空
//   });

		var preview_img = function(file) {
			var reader = new FileReader(); // 用來讀取檔案
			reader.readAsDataURL(file); // 讀取檔案
			reader.addEventListener("load",function() {
// 			console.log(reader.result);
								
// 			let img_node = document.createElement("img"); // <img>
// 			img_node.setAttribute("src", reader.result); // <img src="base64">
// 			img_node.setAttribute("class", "preview_img"); // <img src="base64" class="preview_img">
// 			preview_el.innerHTML = '';
// 			preview_el.append(img_node);
								

			let img_str = '<img src="' + reader.result + '" class="preview_img">';
			preview_el.innerHTML = img_str;
			});
		};

		p_file_el.addEventListener("change", function(e) {
			if (this.files.length > 0) {
				preview_img(this.files[0]);
			} else {
				preview_el.innerHTML = 'span class="text">預覽圖</span>';
			}
		});
	</script>


</body>

</html>