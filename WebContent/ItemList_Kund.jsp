<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
    <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />
</head>
<body>
<jsp:include page="navbar.jsp" />
<jsp:include page="sidebar.jsp" />
<div class="container">
<div class="table-title">
<h3>List of all items</h3>
</div>

<div style="display: flex;"> 
<div>
<c:forEach var="category" items="${listCategories}">
 <a class="nostyle" href="./ItemList_Kund?cat_id=<c:out value='${category.cat_id}' />"><button><c:out value="${category.cat_name}"/></button></a>
 </c:forEach> 
</div>

<div id="p-grid">
<c:forEach var="item" items="${listItem1}">
      <div class="p-grid">
	      <div class="p-grid-in">
	        <img class="p-img" src="https://code-boxx.com/wp-content/uploads/2018/07/5.jpg"/>
	        <div class="p-name"><c:out value="${item.product_name}" /></div>
	        <div class="p-price">$<c:out value="${item.price}" /></div>
	        <div class="p-desc"><c:out value="${item.product_id}" /></div>
	        <a class="nostyle" href="./addtocart?product_id=<c:out value='${item.product_id}' />"><button>Add to cart</button></a>
	      </div>
      </div>
 </c:forEach>  
</div>
</div>
</div>
</body>
</html>