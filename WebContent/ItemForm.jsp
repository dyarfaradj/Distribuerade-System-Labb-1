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
    <h1><c:if test="${item != null}">
                        Edit Item
                    </c:if>
                    <c:if test="${item == null}">
                        Add New Item
                    </c:if></h1>
     <c:if test="${item != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${item == null}">
            <form action="insert" method="post">
     </c:if>
     
     <c:if test="${item != null}">
           <input type="hidden" name="product_id" value="<c:out value='${item.product_id}' />" />
    </c:if> 
    
    <label class="testLabel">Title</label>
	<span class="testSpan"><input  type="text" placeholder="Product name" name="product_name" size="45" value="<c:out value='${item.product_name}' />"/></span>
	
	<label class="testLabel">Category</label>
	<span class="testSpan"><input type="text" placeholder="Category" name="cat_id" size="45" value="<c:out value='${item.cat_id}' />"/></span>
	
	<label class="testLabel">Stock</label>
	<span class="testSpan"><input type="text" placeholder="Stock" name="stock" size="5" value="<c:out value='${item.stock}' />"/></span>
	
	<label class="testLabel">Price</label>
	<span class="testSpan"><input type="text" placeholder="Price" name="price" size="5" value="<c:out value='${item.price}' />"/></span>
	
    <button>Add</button>
    </form>
</div> 
    </div> 
</body>
</html>