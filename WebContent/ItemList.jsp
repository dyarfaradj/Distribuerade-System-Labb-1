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
<div class="container900">
<div class="table-title">
<h3>List of all items</h3>
</div>
<table class="table-fill">
<thead>
<tr>
<th class="text-left">ID</th>
<th class="text-left">Title</th>
<th class="text-left">Category</th>
<th class="text-left">Stock</th>
<th class="text-left">Price</th>
<th class="text-left">Actions</th>
</tr>
</thead>
<tbody class="table-hover">
 <c:forEach var="item" items="${listItem}">
                <tr>
                    <td><c:out value="${item.product_id}" /></td>
                    <td><c:out value="${item.product_name}" /></td>
                    <td><c:out value="${item.cat_id}" /></td>
                    <td><c:out value="${item.stock}" /></td>
                    <td><c:out value="${item.price}" /></td>
                    <td>
                   		<a class="nostyle" href="./edit?product_id=<c:out value='${item.product_id}' />"><button class="edit-button40">Edit</button></a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="nostyle" href="./delete?product_id=<c:out value='${item.product_id}' />"><button class="edit-button40">Delete</button></a>                   
                    </td>
                </tr>
            </c:forEach>
</tbody>
</table>
</div>
</body>
</html>