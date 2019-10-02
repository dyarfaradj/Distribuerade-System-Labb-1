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
 <% for(Cart cart : result) { %>
		
		<tr>
				<td><%= cart.getProductId() %></td>
				<td><%= cart.getProductName() %></td>
				<td><%= cart.getQuantity() %></td>
				<td><%= cart.getPrice() %></td>
		</tr>
		
		<% } %>
</tbody>
</table>
</body>
</html>