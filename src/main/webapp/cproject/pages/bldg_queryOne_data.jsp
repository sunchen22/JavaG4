<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.buildinginfo.dao.*"%>
<%@ page import="com.buildinginfo.entity.BuildingInfo"%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>樓頂揪樓咖後台管理</title>


<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/cproject/plugins/fontawesome-free/css/all.min.css">

<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/cproject/dist/css/adminlte.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>


<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>

			</ul>

			
			<ul class="navbar-nav ml-auto">

				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/cproject/index.jsp"
					role="button"> <i class="fas fa-home"></i>
				</a></li>

				<li class="nav-item"><a class="nav-link"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>

			</ul>
		</nav>
		
		<%@ include file="../../background/pages/pageaside.file" %>


</nav> 
 
      </div>
      
    </aside>



		<div class="content-wrapper">
			
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1></h1>
						</div>

					</div>
				</div>
				
			</section>

			
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-10">
							<!-- interactive chart -->
							<div class="card card-warning card-outline">
								<div class="card-header">
									<h3 class="card-title">
										<i class="fa fa-search fa-sm"></i>
									</h3>
									<div class="card-tools">
										<button type="button" class="btn btn-tool"
											data-card-widget="collapse"></button>
									</div>
								</div>
								<div id="interactive" style="height: 10px;"></div>



								
								<form method="post" action="<%=request.getContextPath()%>/cproject/pages/bis.do" style="padding: 10px;">

									<%-- 錯誤表列 --%>
									<c:if test="${not empty errorMsgs}">
										<font style="color: red">請修正以下錯誤:</font>
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>




									<span> <label>大樓編號：</label> <input type="text"
										name="bdgname" class="border border-warning">

									</span>
								 
									<input type="hidden" name="action" value="get_Data_Display">
									<span>
										<button type="submit" value="送出" style="font-weight: bold"
											class="btn btn-warning">查詢</button>
									</span>
								</form>


								<div id="interactive" style="height: 20px;"></div>
							</div>


						
						</div>
				

					</div>
				
				</div>
			



				<%
				BuildingInfo bif = (BuildingInfo) request.getAttribute("bif");
				
				%>

				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th class="w-1">大樓編號</th>
								<th>大樓名稱</th>
								<th>地址</th>								
								<th></th>

							</tr>
						</thead>
						<tbody>
							<tr>
								<td><span class="text-muted"><%=bif.getBuildingID() %></span></td>
								<td><%=bif.getBuildingName() %></td>
								<td><%=bif.getBuildingAddress() %></td>
								
								
								<td>
														 
										<form METHOD="post" ACTION="<%=request.getContextPath()%>/cproject/pages/bis.do">                
					                  <input type="hidden" name="buildingID"  value="<%=bif.getBuildingID() %>">    
								      <input type="hidden" name="action" value="delete">
								      <button type="submit" value ="送出" class ="btn btn-danger" style = "font-weight :bold ">刪除</button>
					                  </form>																												
								</td>





							</tr>

						</tbody>
					</table>

				

				</div>
			</section>

		</div>



		
		<aside class="control-sidebar control-sidebar-warning">
			
		</aside>
		
		<footer class="main-footer">
			<strong>Copyright &copy; 2023</strong> 樓頂揪樓咖團隊 All rights reserved.
			<div class="float-right d-none d-sm-inline-block">
				<b>Version</b> 1.1.0
			</div>
		</footer>
	</div>

	<script src="<%=request.getContextPath()%>/cproject/plugins/jquery/jquery.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/cproject/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

	<script src="<%=request.getContextPath()%>/cproject/dist/js/adminlte.js"></script>

	<script src="<%=request.getContextPath()%>/cproject/dist/js/pages/dashboard3.js"></script>
	
	<%@ include  file="../../background/pages/pagejs.file" %>
	
	
	
	

</body>

</html>