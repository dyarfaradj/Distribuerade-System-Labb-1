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
<h3>Category of all items</h3>
</div>
<table class="table-fill">
<thead>
<tr>
<th class="text-left">ID</th>
<th class="text-left">Name</th>
<th class="text-left">Actions</th>
</tr>
</thead>
<tbody class="table-hover">
 <c:forEach var="category" items="${listCategories}">
                <tr>
                    <td><c:out value="${category.cat_id}" /></td>
                    <td><c:out value="${category.cat_name}" /></td>
                    <td>
                        <a href="./editcategory?cat_id=<c:out value='${category.cat_id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="./removecategory?cat_id=<c:out value='${category.cat_id}' />">Delete</a>                        
                    </td>
                </tr>
            </c:forEach>
</tbody>
</table>
</div>
</body>
</html>