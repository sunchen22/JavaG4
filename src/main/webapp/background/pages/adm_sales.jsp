<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
					href="adm_sales.jsp" class="nav-link">平台銷售狀況</a></li>
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
		
<!-- 引入側邊欄 -->
<%@ include file="pageaside.file"%>

				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid"></div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
					</div>
				</div>

				<!-- 圖表展示 -->
				<div class="row">
					<div class="col-md-6" style="padding: 20px;">
						<span> 
							<label>今日店家總營收額：</label> 
							<input type="text"  id="admtotalprice" name="totalincome"  class="border border-warning" placeholder="總營收額整理中" >元
						</span>
						<br>
						<span>
							<label>目前總店家數：</label>
							<input type="text" id="admtotal" name="totaldiner"  class="border border-warning" placeholder="總店家數整理中" >間
						</span>
						<br>

					</div>
				</div>
				
<!-- 圖表呈現 -->
<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-6" style="padding: 20px;"> -->
<!-- 						Line chart -->
<!-- 						<div class="card card-warning card-outline"> -->
<!-- 							<div class="card-header"> -->
<!-- 								<h3 class="card-title"> -->
<!-- 									<i class="far fa-chart-bar"></i>&ensp;七日內平台營收狀況 -->
<!-- 								</h3> -->

<!-- 								<div class="card-tools"> -->
<!-- 									<button type="button" class="btn btn-tool" -->
<!-- 										data-card-widget="collapse"> -->
<!-- 										<i class="fas fa-minus"></i> -->
<!-- 									</button> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="card-body"> -->
<!-- 								<div class="chart"> -->
<%-- 									<canvas id="lineChart" --%>
<%-- 										style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							/.card-body -->
<!-- 						</div> -->

<!-- 						/.card -->


<!-- 					</div> -->
<!-- 					/.col -->

<!-- 				</div> -->
				<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
		</section>
		<!-- /.content -->

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
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/background/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE -->
	<script src="${pageContext.request.contextPath}/background/dist/js/adminlte.js"></script>
	<!-- OPTIONAL SCRIPTS -->
	<script src="${pageContext.request.contextPath}/background/plugins/chart.js/Chart.min.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="${pageContext.request.contextPath}/background/dist/js/pages/dashboard3.js"></script>
	<!-- ChartJS -->
	<script src="${pageContext.request.contextPath}/background/plugins/chart.js/Chart.min.js"></script>

	<!-- 引入selfjs -->
	<%@ include file="pagejs.file"%>

<!-- 直接呈現數據:今日總營收額 -->
<script>
    $(document).ready(function() {
        $.ajax({
            url: "BgsalesServlet",
            type: "POST",
            dataType: "json",
            success: function(responseData) {
                $('#admtotalprice').val(responseData.groupordertotalprice);
            },
            error: function(xhr, ajaxOptions, thrownError) {
                alert("資料有誤，請重新輸入！");
            }
        });
    });
</script>

<!-- 直接呈現數據:總店家數 -->
<script>
    $(document).ready(function() {
        $.ajax({
            url: "BgsalesServlet",
            type: "POST",
            dataType: "json",
            success: function(responseData) {
                $('#admtotal').val(responseData.dinercount);
            },
            error: function(xhr, ajaxOptions, thrownError) {
                alert("資料有誤，請重新輸入！");
            }
        });
    });
</script>


<!--   圖表 -->
	<script>
		$(function() {
			/* ChartJS
			 * -------
			 * Here we will create a few charts using ChartJS
			 */

			//--------------
			//- AREA CHART -
			//--------------
			// Get context with jQuery - using jQuery's .get() method.
			var lineChartData = {
				labels : [ 'January', 'February', 'March', 'April', 'May', 'June', 'July' ],
				datasets : [ {
					label : 'Digital Goods',
					backgroundColor : 'rgba(60,141,188,0.9)',
					borderColor : 'rgba(60,141,188,0.8)',
					pointRadius : false,
					pointColor : '#3b8bba',
					pointStrokeColor : 'rgba(60,141,188,1)',
					pointHighlightFill : '#fff',
					pointHighlightStroke : 'rgba(60,141,188,1)',
					data : [ 28, 48, 40, 19, 86, 27, 90 ]
				// 前七天數據要放這
				}, {
					label : 'Electronics',
					backgroundColor : 'rgba(210, 214, 222, 1)',
					borderColor : 'rgba(210, 214, 222, 1)',
					pointRadius : false,
					pointColor : 'rgba(210, 214, 222, 1)',
					pointStrokeColor : '#c1c7d1',
					pointHighlightFill : '#fff',
					pointHighlightStroke : 'rgba(220,220,220,1)',
					data : [ 65, 59, 80, 81, 56, 55, 40 ]
				}, ]
			}

			var areaChartOptions = {
				maintainAspectRatio : false,
				responsive : true,
				legend : {
					display : false
				},
				scales : {
					xAxes : [ {
						gridLines : {
							display : false,
						}
					} ],
					yAxes : [ {
						gridLines : {
							display : false,
						}
					} ]
				}
			}

			//-------------
			//- LINE CHART -
			//--------------
			var lineChartCanvas = $('#lineChart').get(0).getContext('2d')
			var lineChartOptions = $.extend(true, {}, areaChartOptions)
			// var lineChartData = $.extend(true, {}, areaChartData)  //為何多這行跑不動
			lineChartData.datasets[0].fill = false;
			lineChartData.datasets[1].fill = false;
			lineChartOptions.datasetFill = false

			var lineChart = new Chart(lineChartCanvas, {
				type : 'line',
				data : lineChartData,
				options : lineChartOptions
			})

		})
	</script>

</body>

</html>