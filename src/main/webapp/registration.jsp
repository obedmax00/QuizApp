<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<jsp:include page="nav.jsp" /> 
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>



<div id="register">
<div class="centered">
<h1>Registration</h1>
</div>
<form action="RegisterServlet" method="post">
  <div class="form-group">
    <label for="name">Account Name</label>
    <input type="text" class="form-control" id="name" name="name" placeholder="Enter name" required>
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
  </div>
  <div class="form-group">
  <label for="email">Email address</label>
  <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" required>
  </div>
  <div class="form-group">
    <label for="firstName">First Name</label>
    <input type="firstName" class="form-control" id="firstName" name="firstName" placeholder="Enter firstName" required>
  </div>
  <div class="form-group">
    <label for="lastName">Last Name</label>
    <input type="lastName" class="form-control" id="lastName" name="lastName" placeholder="Enter lastName" required>
  </div>
  <div class="form-group">
    <label for="Address">Address</label>
    <input type="Address" class="form-control" id="Address" name="Address" placeholder="Enter email" required>
  </div>
  <div class="form-group">
    <label for="Number">Phone Number</label>
    <input type="text" class="form-control" id="Number" name="Number" placeholder="Enter Phone Number" pattern="[1-9]{1}[0-9]{9}" required>
  </div>
  <div class="centered">
  <button type="submit" class="btn btn-primary">Register</button>
  </div>
</form>
</div>
</body>
</html>