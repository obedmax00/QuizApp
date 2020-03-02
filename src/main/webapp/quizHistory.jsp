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
<body onload="historyOnLoad();">

<h1 id="quizHistoryTitle">Quiz Result</h1>

<button onclick="sortByCategory()" class="btn btn-outline-primary">Sort by Category</button>

<button onclick="sortByName()" class="btn btn-outline-primary">Sort by Name</button>
<br>

<label for="search" style="color: black">Filter by Category or User Name</label>
<input type="text" class="form-control" id="search" name="search" placeholder="insert here...">


<table class="table" id="historyTable">
    <thead>
    <tr>
        <th scope="col">Taken Date</th>
        <th scope="col">Category</th>
        <th scope="col">User Name</th>
        <th scope="col">Score</th>
        <th scope="col">View Detail</th>
    </tr>
    </thead>
    <tbody >
    </tbody>
</table>

<button onclick="preHistory()" class="btn btn-outline-primary">Previous</button>

<button onclick="nextHistory()" class="btn btn-outline-primary">Next</button>
</body>
</html>
