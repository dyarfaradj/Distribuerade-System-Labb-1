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
</tr>
</thead>
<tbody class="table-hover">
 <c:forEach var="cat" items="${listCat}">
                <tr>
                    <td><c:out value="${cat.cat_id}" /></td>
                    <td><c:out value="${cat.cat_name}" /></td>
                    <td>
                        <a href="./edit<c:out value='${cat.cat_id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="./delete<c:out value='${cat.cat_id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
</tbody>
</table>
</body>
</html>