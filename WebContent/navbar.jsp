<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/navbar.js"></script>
</head>
<body>
<section class="navigation">
  <div class="nav-container">
    <div class="brand">
      <a href="./">DYKA ONLINE SHOPPING!</a>
    </div>
    <nav>
      <div class="nav-mobile"><a id="nav-toggle" href="#!"><span></span></a></div>
      <ul class="nav-list">
        <li>
          <a href="./">Home</a>
        </li>
        <li>
          <a href="#!">About</a>
        </li>
        <li>
          <a href="#!">Sidor</a>
          <ul class="nav-dropdown">
            <li>
              <a href="./list">ADMIN Product List</a>
            </li>
            <li>
              <a href="./new"> ADMIN Add new product</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="./register.jsp">Register</a>
        </li>
        <li>
          <a href="./login.jsp">Login</a>
        </li>
        <li>
          <a href="#!">Portfolio</a>
          <ul class="nav-dropdown">
            <li>
              <a href="#!">Web Design</a>
            </li>
            <li>
              <a href="#!">Web Development</a>
            </li>
            <li>
              <a href="#!">Graphic Design</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#!">Contact</a>
        </li>
      </ul>
    </nav>
  </div>
</section>
</body>
</html>