<%@page import="model.QuizUser"%>
<%@page import="servlet.QuizServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="https://fonts.googleapis.com/css?family=Playfair+Display&display=swap" rel="stylesheet">
<link rel="stylesheet" href="./CSS/style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Navigation</title>
</head>
<body>
<div class="nav_bar">
<nav class="nav">
  <a class="nav-link" href="QuizServlet">Home</a>
  <a class="nav-link" href="login.jsp">Log in</a>
  <a class="nav-link" href="registration.jsp">Registration</a>
  <a class="nav-link" href="FeedbackServlet">Feedback</a>
  <a class="nav-link" href="contact.jsp">Contact Us</a>
  <c:if test="${sessionScope.user.getRole().equals('u')}">
  <a class="nav-link" href="user.jsp">User History</a>
  </c:if>
  <c:if test="${sessionScope.user.getRole().equals('a')}">
  <a class="nav-link" href="admin.jsp">Admin</a>
  </c:if>


  <c:if test="${sessionScope.user != null}">
  	<div class="navbar-nav ml-auto" style="flex-direction: row;">
  		<a class="nav-link" style="margin-right:10px; color:orange;">Hi, <c:out value = "${sessionScope.user.getName()}"/></a>
  		<a class="nav-link" style="margin-right:10px;" href="LogOutServlet">Log Out</a>
    </div>
  </c:if>

  </nav>
</div>


</body>
</html>
