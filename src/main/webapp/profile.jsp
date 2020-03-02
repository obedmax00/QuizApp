<%@page import="model.Quiz"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<jsp:include page="nav.jsp" />
<head>
    <script type="text/javascript" src="javaScript/index.js"></script>
    <meta charset="ISO-8859-1">
    <title>Home</title>
</head>
<body onload="profileOnLoad();">
<h1 id="profileTitle">User Profile</h1>

<table class="table" id="myTable">
    <thead>
    <tr>
        <th scope="col">FirstName</th>
        <th scope="col">LastName</th>
        <th scope="col">Address</th>
        <th scope="col">Email</th>
        <th scope="col">PhoneNumber</th>
        <th scope="col">Status</th>
        <th scope="col">activate/suspend</th>
    </tr>
    </thead>
    <tbody >
    </tbody>
</table>

<button onclick="preProfile()" class="btn btn-outline-primary">Previous</button>

<button onclick="nextProfile()" class="btn btn-outline-primary">Next</button>


</body>
</html>