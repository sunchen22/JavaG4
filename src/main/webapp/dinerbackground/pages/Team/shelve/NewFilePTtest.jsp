<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.varytype.dao.*"%>
<%@ page import="com.varytype.entity.*"%>
<%@ page import="com.varytype.service.*"%>
<%@ page import="com.producttype.dao.*"%>
<%@ page import="com.producttype.entity.*"%>
<%@ page import="com.producttype.service.*"%>
<%@ page import="com.productvary.dao.*"%>
<%@ page import="com.productvary.entity.*"%>
<%@ page import="com.productvary.service.*"%>
<%
VaryTypeService VTSvc = new VaryTypeService();
List<VaryType> VTList = VTSvc.getAll();
pageContext.setAttribute("list", VTList);
%>

<%
ProductTypeService PTSvc = new ProductTypeService();
List<ProductType> PTlist = PTSvc.getAll();
pageContext.setAttribute("list", PTlist);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>




	<FORM METHOD="post" ACTION="productType.do" name="form1">
		<table>
			<tr>
				${errorMsgs.productTypeDes}
				<td><input type="TEXT" name="productTypeDes" value="${param.productTypeDes}" /></td>
				<td><input type="hidden" name="action" value="insert">
					<input type="submit" value="新增商品分類"></td>
			</tr>
		</table>
	</FORM>






	<c:forEach var="productTypeVO" items="${list}">

		<tr>
			<td>${productTypeVO.productTypeDes}</td>

			<td>
				<FORM METHOD="post" ACTION="productType.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="productTypeID" value="${productTypeVO.productTypeID}">
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>

		</tr>

	</c:forEach>







</body>
</html>