<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<jsp:include page="sidebar.jsp" />
<div class="logo"></div>
<div class="login-block">
    <h1>Login</h1>
    <form action="<%=request.getContextPath()%>/login" method="post">
    <input type="text" name="username" value="" placeholder="Username" id="username" />
    <input type="password" name="password" value="" placeholder="Password" id="password" />
    <button>Submit</button>
    </form>
</div>
</body>
</html>