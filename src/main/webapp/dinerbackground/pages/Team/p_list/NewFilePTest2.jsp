<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.varytype.dao.*"%>
<%@ page import="com.product.entity.*"%>
<%@ page import="com.product.service.*"%>
<%
    ProductService PSvc = new ProductService();
    List <Product> PList= PSvc.getAll();
    pageContext.setAttribute("list",PList);
%>
 <%
  Product product = (Product) request.getAttribute("product"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>

	
	<table>
	<tr>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>商品類型</th>
		<th>每日商品庫存</th>
		<th>商品上架時間</th>
		<th>商品介紹</th>
		<th>修改</th>
		<th>刪除</th>

	</tr>

	<c:forEach var="productVO" items="${list}" >
		
		<tr>
			<td>${productVO.productName}</td>
			<td>${productVO.productPrice}</td>
			<td>${productVO.productTypeID}</td>
			<td>${productVO.productDailyStock}</td>
			<td>${productVO.productReleaseTime}</td>
			<td>${productVO.productRemark}</td>
			<td>
			  <FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="productID"  value="${productVO.productID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

			<td>
				<FORM METHOD="post" ACTION="product.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="productID" value="${productVO.productID}">
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>