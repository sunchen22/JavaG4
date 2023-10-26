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
<c:forEach var="productVO" items="${list}">

		<tr>
			<td>${productVO.productName}</td>

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







</body>
</html>