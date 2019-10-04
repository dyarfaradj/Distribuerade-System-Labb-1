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
<h3>List of all orders</h3> 
</div>
<table class="table-fill">
<thead>
<tr>
<th class="text-left">Order ID</th>
<th class="text-left">User ID</th>
<th class="text-left">Total amount</th>
<th class="text-left">Shipped</th>
<th class="text-left">Pack</th>
</tr>
</thead>
<tbody class="table-hover">
 <c:forEach var="order" items="${orderList}">
                <tr>
                    <td><c:out value="${order.bill_no}" /></td>
                    <td><c:out value="${order.uid}" /></td>
                    <td><c:out value="${order.total_amt}" /></td>
                    <td><c:out value="${order.packed}" /></td>
                    <td><a class="nostyle" href="./vieworder?bill_no=<c:out value='${order.bill_no}' />"><button>Pack</button></a>
                    </td>
                </tr>
            </c:forEach>
</tbody>
</table>

</div>
</body>
</html>