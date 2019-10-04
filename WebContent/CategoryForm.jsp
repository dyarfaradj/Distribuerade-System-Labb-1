<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Item Store Application</title>
    <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
</head>
<body>
<jsp:include page="navbar.jsp" />
<jsp:include page="sidebar.jsp" />
<div class="container">
    <div class="login-block">
    <h1><c:if test="${category != null}">
                        Edit Category
                    </c:if>
                    <c:if test="${category == null}">
                        Add New Category
                    </c:if></h1>
     <c:if test="${category != null}">
            <form action="updatecategory" method="post">
        </c:if>
        <c:if test="${category == null}">
            <form action="addcategory" method="post">
        </c:if>
     
     <c:if test="${category != null}">
                    <input type="hidden" name="cat_id" value="<c:out value='${category.cat_id}' />" />
       </c:if>
    
	<label class="testLabel">Name</label>
	<span class="testSpan"><input  type="text" placeholder="Category name" name="cat_name" size="45" value="<c:out value='${category.cat_name}' />"/></span>
	
    <button>Add</button>
    </form>
</div> 
     
    </div> 
</body>
</html>