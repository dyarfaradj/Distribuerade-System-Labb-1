<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<div style="color: white; text-align: center">
        <h1>Items Management</h1>
        <h2>
            <a href="./new">Add New Item</a>
            &nbsp;&nbsp;&nbsp;
            <a href="./list">List All Items</a>
             
        </h2>
    </div>
<div class="table-title">
<h3>List of all items</h3>
</div>
<table class="table-fill">
<thead>
<tr>
<th class="text-left">ID</th>
<th class="text-left">Title</th>
<th class="text-left">Description</th>
<th class="text-left">Quantity</th>
<th class="text-left">Price</th>
<th class="text-left">Actions</th>
</tr>
</thead>
<tbody class="table-hover">
 <c:forEach var="item" items="${listItem}">
                <tr>
                    <td><c:out value="${item.id}" /></td>
                    <td><c:out value="${item.title}" /></td>
                    <td><c:out value="${item.description}" /></td>
                    <td><c:out value="${item.quantity}" /></td>
                    <td><c:out value="${item.price}" /></td>
                    <td>
                        <a href="./edit?id=<c:out value='${item.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="./delete?id=<c:out value='${item.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
</tbody>
</table>
</body>
</html>