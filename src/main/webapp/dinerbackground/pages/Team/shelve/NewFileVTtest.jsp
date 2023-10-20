<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.varytype.dao.*"%>
<%@ page import="com.varytype.entity.*"%>
<%@ page import="com.varytype.service.*"%>
<%
    VaryTypeService VTSvc = new VaryTypeService();
    List <VaryType> VTList= VTSvc.getAll();
    pageContext.setAttribute("list",VTList);
%>

<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
				
	<c:forEach var="varyTypeVO" items="${list}">

		<tr>
		<td>${varyTypeVO.varyType}</td>									
	
			<td>
			  <FORM METHOD="post" ACTION="varytype.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="varyTypeID" value="${varyTypeVO.varyTypeID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		
		</tr>
		
	</c:forEach>
	
	
	
	
	
		
	
</body>
</html>