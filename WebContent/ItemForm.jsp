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
<div style="color: white; text-align: center">
    </div>
    <div align="center">
        <c:if test="${item != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${item == null}">
            <form action="insert" method="post">
        </c:if>
        <table class="table-fill" border="1" cellpadding="5">
            <caption>
            <div class="table-title">
                <h3>
                    <c:if test="${item != null}">
                        Edit Item
                    </c:if>
                    <c:if test="${item == null}">
                        Add New Item
                    </c:if>
                </h3>
               </div>
            </caption>
            <tbody class="table-hover">
                <c:if test="${item != null}">
                    <input type="hidden" name="product_id" value="<c:out value='${item.product_id}' />" />
                </c:if>           
            <tr>
                <th class="text-left">Title: </th>
                <td>
                    <input  type="text" name="product_name" size="45"
                            value="<c:out value='${item.product_name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th class="text-left">Description: </th>
                <td>
                    <input type="text" name="cat_id" size="45"
                            value="<c:out value='${item.cat_id}' />"
                    />
                </td>
            </tr>
            <tr>
                <th class="text-left">Quantity: </th>
                <td>
                    <input type="text" name="stock" size="5"
                            value="<c:out value='${item.stock}' />"
                    />
                </td>
            </tr>
            <tr>
                <th class="text-left">Price: </th>
                <td>
                    <input type="text" name="price" size="5"
                            value="<c:out value='${item.price}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button>Save</button>
                </td>
            </tr>
            </tbody>
        </table>
        </form>
    </div>   
</body>
</html>