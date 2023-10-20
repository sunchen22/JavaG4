<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.varytype.dao.*"%>
<%@ page import="com.varytype.entity.*"%>
<%@ page import="com.producttype.dao.*"%>
<%@ page import="com.producttype.entity.*"%>
<%@ page import="com.producttype.service.*"%>
<%@ page import="com.productvary.dao.*"%>
<%@ page import="com.productvary.entity.*"%>
<%@ page import="com.productvary.service.*"%>




<%
ProductVaryService PVSvc = new ProductVaryService();
List<ProductVary> PVlist = PVSvc.getAll();
pageContext.setAttribute("list", PVlist);
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

                 ==================新增====================

	<FORM METHOD="post" ACTION="productVary.do" name="form1">
		<table>
			<tr>
			<tr>
			<tr>
				<td>商品編號:</td>
				<td><input type="TEXT" name="productID"
					value="${param.productID}" size="45" /></td>
				<td>${errorMsgs.productID}</td>
			</tr>
			<tr>
				<td>客製項目:</td>
				<td><input type="TEXT" name="productVaryDes"
					value="${param.productVaryDes}" size="45" /></td>
				<td>${errorMsgs.productVaryDes}</td>
			</tr>

			<tr>
				<td>客製金額:</td>
				<td><input type="TEXT" name="productVaryPrice"
					value="${param.productVaryPrice}" size="45" /></td>
				<td>${errorMsgs.productVaryPrice}</td>
			</tr>
			<tr>
				<td>客制分類:</td>
				<td><input type="TEXT" name="varyTypeID"
					value="${param.varyTypeID}" size="45" /></td>
				<td>${errorMsgs.varyTypeID}</td>
			</tr>

				<td><input type="hidden" name="action" value="insert">
					<input type="submit" value="新增客製選項"></td>
			</tr>
		</table>
	</FORM>


                 ==================修改====================
                 
	<FORM METHOD="post" ACTION="productVary.do" name="form1">
		<table>


			<tr>
			<tr>
				<td>商品編號:</td>
				<td><input type="TEXT" name="productID"
					value="${param.productID}" size="45" /></td>
				<td>${errorMsgs.productID}</td>
			</tr>
			<tr>
				<td>客製項目:</td>
				<td><input type="TEXT" name="productVaryDes"
					value="${param.productVaryDes}" size="45" /></td>
				<td>${errorMsgs.productVaryDes}</td>
			</tr>

			<tr>
				<td>客製金額:</td>
				<td><input type="TEXT" name="productVaryPrice"
					value="${param.productVaryPrice}" size="45" /></td>
				<td>${errorMsgs.productVaryPrice}</td>
			</tr>
			<tr>
				<td>客制分類:</td>
				<td><input type="TEXT" name="varyTypeID"
					value="${param.varyTypeID}" size="45" /></td>
				<td>${errorMsgs.varyTypeID}</td>
			</tr>



		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="productVaryID" value="6"> <input
			type="submit" value="送出修改">
	</FORM>






==================刪除====================


	<c:forEach var="productVaryVO" items="${list}">

		<tr>
			<td>${productVaryVO.productVaryDes}</td>

			<td>
				<FORM METHOD="post" ACTION="productVary.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="productVaryID" value="${productVaryVO.productVaryID}">
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>

		</tr>

	</c:forEach>







</body>
</html>