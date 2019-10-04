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
<div style="color: white; text-align: center">
    </div>
    <div align="center">
        <c:if test="${category != null}">
            <form action="updatecategory" method="post">
        </c:if>
        <c:if test="${category == null}">
            <form action="addcategory" method="post">
        </c:if>
        <table class="table-fill" border="1" cellpadding="5">
            <caption>
            <div class="table-title">
                <h3>
                    <c:if test="${category != null}">
                        Edit Item
                    </c:if>
                    <c:if test="${category == null}">
                        Add New Category
                    </c:if>
                </h3>
               </div>
            </caption>
            <tbody class="table-hover">
                <c:if test="${category != null}">
                    <input type="hidden" name="cat_id" value="<c:out value='${category.cat_id}' />" />
                </c:if>           
            <tr>
                <th class="text-left">Name: </th>
                <td>
                    <input  type="text" name="cat_name" size="45"
                            value="<c:out value='${category.cat_name}' />"
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
    </div> 
</body>
</html>