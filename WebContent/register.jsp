<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User registration</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />
</head>
<body>
<jsp:include page="navbar.jsp"/>
<jsp:include page="sidebar.jsp" />
<div class="container">  
<div class="container-reg">
  <form id="contact" action="<%= request.getContextPath() %>/register" method="post">
    <h3>Registration</h3>
    <h4>Fill in the details below to sign up!</h4>
    <fieldset>
      <input placeholder="Your name" name="firstName" type="text" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
      <input placeholder="Your last name" name="lastName" type="text" tabindex="2" required autofocus>
    </fieldset>
     <fieldset>
      <input placeholder="Your username" name="username" type="text" tabindex="3" required autofocus>
    </fieldset>
    <fieldset>
      <input placeholder="Your password" name="password" type="password" tabindex="4" required autofocus>
    </fieldset>
    <fieldset>
      <input placeholder="Your address" name="address" type="text" tabindex="5" required autofocus>
    </fieldset>
     <fieldset>
      <input placeholder="Your Email Address" name="email" type="email" tabindex="6" required>
    </fieldset>
    <fieldset>
      <input placeholder="Your Phone Number" name="phone" type="tel" tabindex="7" required>
    </fieldset>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Submit</button>
    </fieldset>
  </form>
  </div>
</div>
</body>
</html>