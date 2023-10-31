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
	href="${pageContext.request.contextPath}/background/plugins/fontawesome-free/css/all.min.css">
<!-- IonIcons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/background/dist/css/adminlte.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/background/plugins/daterangepicker/daterangepicker.css">


<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/background/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/background/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/background/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Select2 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/background/plugins/select2/css/select2.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/background/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">

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

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/background/pages/index3.jsp" 
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
						
 <!-- 引入側邊欄 -->
<%@ include  file="pageaside.file" %>

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
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
							<form METHOD="post"
								ACTION="emp.do"
								NAME="form_admadd"
								ENCTYPE="multipart/form-data">
								<div class="card-body" style="padding-bottom: 5px;">
									<div class="form-group">

										<%
										String empName;
										%>
										<label>員工姓名 </label>
										<input type="text" class="form-control "
											name="empName" id="empName"
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
								
									<div style="text-align: center;  background: transparent ">
										<input type="hidden" name="action" value="insert">
										<input type="submit" id="submitbtn" name="action" value="確定" class="btn btn-warning">
										</div>
								</form>
								<form METHOD="post" ACTION="emp.do" NAME="form_admadd" ENCTYPE="multipart/form-data">	
								<div style="text-align: center;  background: transparent ">
									<button type="submit" class="btn btn-warning">取消</button>
									<input type="hidden" name="action" value="cancel">
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
	<script src="${pageContext.request.contextPath}/background/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="${pageContext.request.contextPath}/background/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Select2 -->
	<script src="${pageContext.request.contextPath}/background/plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<script
		src="${pageContext.request.contextPath}/background/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
	<!-- InputMask -->
	<script src="${pageContext.request.contextPath}/background/plugins/moment/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/background/plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- date-range-picker -->
	<script src="${pageContext.request.contextPath}/background/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="${pageContext.request.contextPath}/background/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="${pageContext.request.contextPath}/background/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Bootstrap Switch -->
	<script src="${pageContext.request.contextPath}/background/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${pageContext.request.contextPath}/background/dist/js/adminlte.min.js"></script>
	
 <!-- 引入selfjs -->
<%@ include  file="pagejs.file" %>





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
	
	<!-- 姓名重複驗證 -->
	<script>
		$(document).ready(function() {
			$('#empName').on('focusout', function() {
				var empName = $(this).val();
				$.ajax({
					url : "EmpNameServlet",
					type : "POST",
					data : {
						"empName" : empName
					},
					dataType : "json",
					success: function (repeatName) {
	                    if (repeatName === "true") {
	                        alert("重複姓名，請確認！");
	                    }
	                },
					error : function(xhr, ajaxOptions, thrownError) {
						
					}
				});
			});
		});
	</script>

</body>

</html>