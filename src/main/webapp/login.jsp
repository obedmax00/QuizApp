<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<jsp:include page="nav.jsp" /> 
<head>
<meta charset="ISO-8859-1">
<title>Log in</title>
</head>
<body>
 


<div id="login">
<div class="centered">
<h1>Log in</h1>
</div>
<form action="LoginServlet" method="post">
  <div class="form-group">
    <label for="name">Account Name</label>
    <input type="text" class="form-control" id="name" name="name" placeholder="Enter name" required>
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
  </div>
  <div class="centered">
  <button type="submit" class="btn btn-primary">Log in</button>
  </div>
</form>
</div>

<h4 style="color:blue;text-align:center"><c:out value = "${requestScope.message}"/></h4>
</body>
</html>