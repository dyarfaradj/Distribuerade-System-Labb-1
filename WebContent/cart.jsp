<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Business.Cart" %>
<%@ page import="DataAccess.CartDao" %>
<%@ page import="java.util.*" %>   
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<div style="color: white; text-align: center">
    </div>
<div class="table-title">
<h3>List of all items</h3>
</div>
<%
	
	
	List<Cart> result = new ArrayList<Cart>();
	
	result = CartDao.getProducts(1);
	
%>
<table class="table-fill">
<thead>
<tr>
<th class="text-left">Product_Id</th>
<th class="text-left">Product_Name</th>
<th class="text-left">Quantity</th>
<th class="text-left">Price</th>
</tr>
</thead>
<tbody class="table-hover">
 <c:forEach var="item" items="${listItem}">
                <tr>
                    <td><c:out value="${item.product_id}" /></td>
                    <td><c:out value="${item.product_name}" /></td>
                    <td><c:out value="${item.quantity}" /></td>
                    <td><c:out value="${item.price}" /></td>
                </tr>
            </c:forEach>
</tbody>
</table>
</body>
</html>