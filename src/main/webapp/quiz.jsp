<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="service.DoQuiz"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

    <jsp:include page="nav.jsp" />
<!DOCTYPE html>
<html>
 
<script  type="text/javascript" >
function getCookie(name) {
	  var value = "; " + document.cookie;
	  var parts = value.split("; " + name + "=");
	  if (parts.length == 2) return parts.pop().split(";").shift();
	};



function startCount(){var counting = setInterval(function(){	
var time = getCookie("startTime");
var now = new Date();
var dif = now.getTime()-time;
var minuts = Math.floor(dif%1000)
document.getElementById("timer").innerHTML = millisToMinutesAndSeconds(dif);

if((dif / 60000)>1.5){
	clearInterval(counting);
	alert("Time Is Up")
	document.getElementById("quizQuestion").submit();

}
},100);};





function millisToMinutesAndSeconds(millis) {
	  var minutes = Math.floor(millis / 60000);
	  var seconds = ((millis % 60000) / 1000).toFixed(0);
	  return minutes + ":" + (seconds < 10 ? '0' : '') + seconds;
	};

	
function check(){
	var questionDone= getCookie("questionDone")
	if(questionDone<9){
		  if (confirm("Not All the questions are answered. Do you want to submit?")) {
			  document.getElementById("quizQuestion").submit();
			  } else {
			  }
	}else{
		  document.getElementById("quizQuestion").submit();
	}
}
	
	
	

</script>
<head>

<meta charset="ISO-8859-1">
<title>Quiz</title>

</head>
<body onload="startCount()" class="background">

<div class="perQuestion">
<h1 id="title"><c:out value="${sessionScope.index+1}" />. <c:out value="${sessionScope.curQuestion.getQuestion()}"/></h1>
<form action="QuizResultSerlvet?next=2" id="quizQuestion" method="post">


    <c:forEach items="${sessionScope.curQuestion.getChoices()}" var="current">
    <div class="item">
    <input type="radio" name="selector" value="<c:out value="${current.getChoiceNumber()}"/>" <c:if test="${current.getChoiceNumber().equals(sessionScope.userAnswer.getUserAns())}">checked</c:if> >
    <label><c:out value="${current.getChoiceName()}"/></label>
    </div>
     </c:forEach>
    
    <div class="QuizButton">
    <c:if test="${sessionScope.index>0}">
	<button formaction="QuizResultSerlvet?next=-1" type="submit" class="draw">Previous</button>
	</c:if>
	<c:if test="${sessionScope.index<9}">
     <button formaction="QuizResultSerlvet?next=1" type="submit"  class="draw">Next</button>
     </c:if>
     <button id="submitButton" type="button" class="draw" onclick="check();" >Submit</button>
      <div class="right">
      	<h1 id="timer"></h1>
      	</div>
     </div>
     <div>
     <c:forEach items="1,2,3,4,5,6,7,8,9,10" var="current1">
     <button class="<c:if test='${sessionScope.doQuiz.answered(current1)}'>submitedQuestion</c:if> draw" formaction="QuizResultSerlvet?whichQuestion=<c:out value="${current1}"/>&next=-2"><c:out value="${current1}"/></button>
     
     </c:forEach>
     </div>
 </form>

 </div>


	
</body>
</html>







