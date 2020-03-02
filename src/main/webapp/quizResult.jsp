<%@page import="service.DoQuiz"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

    <jsp:include page="nav.jsp" />
<!DOCTYPE html>
<html>
 
<head>

<meta charset="ISO-8859-1">
<title>Quiz</title>

</head>
<body>
<div class="background">
<div id="result">
<div class="centered">
<h1 id="resultTitle"><c:out value="${sessionScope.quizName}" /></h1>
<h2>User: <c:out value="${sessionScope.user.getName()}" /></h2>
<h3>Start Time: <c:out  value="${sessionScope.startTime}"  /></h3>
<h3>End Time: <c:out  value="${sessionScope.endTime}" /></h3>
<h3><c:out value='${sessionScope.doQuiz.ifPass()?"Passed":"Not Pass"}' /></h3>
<h1>______________________________________________________________________________________________________________________________</h1>
</div>
<c:forEach items="${sessionScope.doQuiz.getQuestionList()}" var="current">

<div>


<h1>
<c:choose>
    <c:when test="${current.getAnswer().getUserAns().equals(current.getQuestionAns())}">
       &#10004
    </c:when>    
    <c:otherwise>
       &#10060
    </c:otherwise>
</c:choose>  <c:out value="${current.getQuestion()}" /></h1>
<c:forEach items="${current.getChoices()}" var="choice">
  <h4 class="answers" style="
      <c:if test='${choice.getChoiceNumber().equals(current.getQuestionAns())}'>
       color:#0DFF92;
    </c:if>
    <c:if test='${!current.getAnswer().getUserAns().equals(current.getQuestionAns()) && choice.getChoiceNumber().equals(current.getAnswer().getUserAns()) }'>
    color:#DC143C;
    </c:if>
    "><c:out value="${choice.getChoiceNumber()}"/>. <c:out value="${choice.getChoiceName()}"/></h4>
</c:forEach>
<h4 class="answers">User Answer: <c:out value="${current.getAnswer().getUserAns()}" /></h4>
<h4 class="answers">Correct Answer: <c:out value="${current.getQuestionAns()}" /></h4>
<h1 style="text-align: center;">______________________________________________________________________________________________________________________________</h1>
<br>
<br>
</div>
</c:forEach>

<div class="centered">
<a href="QuizServlet"><button id="feedbackButton"type="submit" class="draw">Take Another Quiz</button></a>
</div>
</div>

</div>


</body>
</html>






