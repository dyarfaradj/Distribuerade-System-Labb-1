<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<jsp:include page="sidebar.jsp" />
<div class="container">
<div style="color: white; text-align: center">
    </div>
<div class="table-title">
<h3>Din varukorg!</h3> <a href="./placeorder" style="font: bold 11px Arial;
  text-decoration: none;
  background-color: #EEEEEE;
  color: #333333;
  padding: 2px 6px 2px 6px;
  border-top: 1px solid #CCCCCC;
  border-right: 1px solid #333333;
  border-bottom: 1px solid #333333;
  border-left: 1px solid #CCCCCC;">Place order!</a>
</div>
<table class="table-fill">
<thead>
<tr>
<th class="text-left">Order ID</th>
<th class="text-left">product ID</th>
<th class="text-left">product name</th>
<th class="text-left">Quantity</th>
<th class="text-left">In stock</th>
</tr>
</thead>
<tbody class="table-hover">
 <c:forEach var="order" items="${viewOrder}">
                <tr>
                    <td><c:out value="${order.bill_no}" /></td>
                    <td><c:out value="${order.product_id}" /></td>
                    <td><c:out value="${order.product_name}" /></td>
                    <td><c:out value="${order.quantity}" /></td>
                    <td><c:out value="${order.stock}" /></td>
                </tr>
            </c:forEach>
</tbody>
</table>
</div>
</body>
</html>