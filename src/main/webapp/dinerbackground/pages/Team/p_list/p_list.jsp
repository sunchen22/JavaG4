<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品列表</title>
</head>
<body>


<a href="${pageContext.request.contextPath}/product.do?action=getAll">查詢所有</a>
   
   
        <tr>
        <th>商品名稱</th>
        <th>商品狀態</th>
        <th>每日庫存</th>
        <th>金額</th>
        <th>編輯商品</th>
        <th>下架商品</th>
        <th>刪除商品</th>
        <th>修改時間</th>
        </tr>
   
   
   <c:forEach var="product" items="${productList}">
			<tr>
				<td>${product.productName}</td>
				<td>${product.productStatus}</td>
				<td>${product.productDailyStock}</td>
				<td>${product.productPrice}</td>
				<td>編輯</td>
				<td>下架</td>
				<td>刪除</td>
				<td>${product.productReleaseTime}</td>
			</tr>
		</c:forEach>
        
          <li>
    <FORM METHOD="post" ACTION="product.do" >
        <b>輸入員工編號 (如7001):</b>
        <input type="text" name="productID" value="${param.productID}"><font color=red>${errorMsgs.productID}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

 
   
  <li>
     <FORM METHOD="post" ACTION=product.do >
       <b>選擇員工編號:</b>
       <select size="1" name="productID">
         <c:forEach var="productVO" items="${empSvc.all}" > 
          <option value="${productVO.productID}">${productVO.productID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
        
        
        
        
        
        
        
</body>
</html>