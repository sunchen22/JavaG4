<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.varytype.dao.*"%>
<%@ page import="com.product.entity.*"%>
<%@ page import="com.product.service.*"%>
<%
    ProductService PSvc = new ProductService();
    List <Product> PList= PSvc.getAll();
    pageContext.setAttribute("Plist",PList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>

  <li>
    <FORM METHOD="post" ACTION="product.do" name="form1" >
        <b>輸入商品類型</b>
        <input type="text" name="productType" value="${param.productTypeID}"><font color=red>${errorMsgs.productID}</font>
        <input type="hidden" name="action" value="getByType_Display">
        <input type="submit" value="查詢">
        
        
        
        
    </FORM>
  </li>
====================新增========================

<FORM METHOD="post" ACTION="product.do" name="form1">
		<table>
			<tr>
			<tr>
			<tr>
				<td>商家編號:</td>
				<td><input type="TEXT" name="dinerID"
					value="${param.dinerID}" size="45" /></td>
				<td>${errorMsgs.dinerID}</td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="productName"
					value="${param.productName}" size="45" /></td>
				<td>${errorMsgs.productName}</td>
			</tr>

			<tr>
				<td>商品金額:</td>
				<td><input type="TEXT" name="productPrice"
					value="${param.productPrice}" size="45" /></td>
				<td>${errorMsgs.productPrice}</td>
			</tr>
			<tr>
				<td>商品類型ID:</td>
				<td><input type="TEXT" name="productTypeID"
					value="${param.productTypeID}" size="45" /></td>
				<td>${errorMsgs.productTypeID}</td>
			</tr>
			
			<tr>
				<td>每日庫存:</td>
				<td><input type="TEXT" name="productDailyStock"
					value="${param.productDailyStock}" size="45" /></td>
				<td>${errorMsgs.productDailyStock}</td>
			</tr>
			
			<tr>
				<td>商品介紹:</td>
				<td><input type="TEXT" name="productRemark"
					value="${param.productRemark}" size="45" /></td>
				<td>${errorMsgs.productRemark}</td>
			</tr>
			

			<tr>
				<td><input type="hidden" name="action" value="insert">
					<input type="submit" value="新增商品"></td>
			</tr>
		</table>
	</FORM>


=====================修改========================
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dinerbackground/pages/Team/shelve/product.do" name="form1">
		<table>
			<tr>
			<tr>
			<tr>
				<td>商品編號:</td>
				<td><input type="TEXT" name="productID"	value="${param.productID}" size="45" /></td>
				<td>${errorMsgs.dinerID}</td>
			</tr>
			
			<tr>
				<td>商家編號:</td>
				<td><input type="TEXT" name="dinerID"	value="${param.dinerID}" size="45" /></td>
				<td>${errorMsgs.dinerID}</td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="productName" value="${param.productName}" size="45" /></td>
				<td>${errorMsgs.productName}</td>
			</tr>

			<tr>
				<td>商品金額:</td>
				<td><input type="TEXT" name="productPrice" value="${param.productPrice}" size="45" /></td>
				<td>${errorMsgs.productPrice}</td>
			</tr>
			<tr>
				<td>商品類型ID:</td>
				<td><input type="TEXT" name="productTypeID"	value="${param.productTypeID}" size="45" /></td>
				<td>${errorMsgs.productTypeID}</td>
			</tr>
			
			<tr>
				<td>每日庫存:</td>
				<td><input type="TEXT" name="productDailyStock"	value="${param.productDailyStock}" size="45" /></td>
				<td>${errorMsgs.productDailyStock}</td>
			</tr>
			
			<tr>
				<td>商品介紹:</td>
				<td><input type="TEXT" name="productRemark"	value="${param.productRemark}" size="45" /></td>
				<td>${errorMsgs.productRemark}</td>
			</tr>
			
			<tr>
				<td>商品狀態:</td>
				<td><input type="TEXT" name="productStatus"	value="${param.productStatus}" size="45" /></td>
				<td>${errorMsgs.productStatus}</td>
			</tr>
			


				<td><input type="hidden" name="action" value="update">
					<input type="submit" value="修改商品"></td>
			</tr>
		</table>
	</FORM>



=====================刪除========================
<br>
	<c:forEach var="productVO" items="${Plist}">

		<tr>
			<td>${productVO.productName}</td>

			<td>
				<FORM METHOD="post" ACTION="product.do"	style="margin-bottom: 0px;">
					<input type="submit" value="刪除">
					<input type="hidden" name="productID" value="${productVO.productID}">
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>

		</tr>

	</c:forEach>


</body>
</html>