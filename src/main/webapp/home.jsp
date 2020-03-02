<%@page import="model.Quiz"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<jsp:include page="nav.jsp" />
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
  
</body>
<c:choose>
    <c:when test="${sessionScope.user != null}">
    </c:when>    
    <c:otherwise>
    <div class="centered">
        <h4 id="pleaseLogIn">Please Log in to do a quiz</h4>
        <br />
        </div>
    </c:otherwise>
</c:choose>

<section id="boxes">
<c:forEach items="${requestScope.quizList}" var="quiz">
	<div class="quiz">
		<div class="box">
        <img height="300" width="400" src="${pageContext.request.contextPath}/images/${quiz.getPictureURL()}"><br>
        <a href="QuizResultSerlvet?test=${quiz.getId()}&quizName=${quiz.getName()}">
        ${quiz.getName()}
        </a>
        </div>
   </div>
</c:forEach>

</section>
</html>