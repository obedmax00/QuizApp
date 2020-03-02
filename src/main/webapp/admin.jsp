<%@page import="model.Quiz"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<jsp:include page="nav.jsp" />
<head>
    <link rel="stylesheet" href="./CSS/style.css">

    <meta charset="ISO-8859-1">
    <title>Home</title>
</head>
<body>

<div id="adminPage">
    <h3 id="select">Please Select</h3>
    <div style="padding-left: 60px">
        <a href="profile.jsp" ><button type="button" class="btn btn-outline-primary">User Profile</button></a>

    </div>
    <div style="padding-left: 60px">
        <a href="quizHistory.jsp" ><button type="button" class="btn btn-outline-primary">Quiz History</button></a>
    </div>
</div>



</body>
</html>