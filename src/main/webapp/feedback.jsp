<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="nav.jsp" /> 
<head>
<meta charset="ISO-8859-1">
<title>Feedback</title>
</head>
<body>


<div id="feedback">
<div class="centered">
<h1>Feedback</h1>
</div>
<form action="FeedbackServlet" method="post">
  <div class="form-group">

    <label for="whichQuiz">Feedback Option</label>
  
  <select class="form-control" id="whichQuiz" name="whichQuiz" required>
  <option value="">Select your option...</option>
  <option value="-1">Website</option>
  
  <c:forEach items="${requestScope.quizList}" var="quiz">
  <option value="${quiz.getId()}"><c:out value="${quiz.getName()}" /></option>
</c:forEach>
	
  </select>
    <div class="form-group">
    
    <label for="rating">Give Your Rating</label>
    <select class="form-control" name="rating" required>
    	<option value="">Select Rating...</option>
      <option value="1">1 = Poor</option>
        <option value="2">2 = Fair</option>
          <option value="3">3 = Good</option>
        
          <option value="4">4 = Very Good</option>
          <option value="5">5 = Excellent</option>
          
        
      
    
    </select>
</div>
</div>
  <div class="form-group">
    <label for="feedback">Your feedback</label>
    <textarea type="text" class="form-control" id="feedback" name="feedback" rows="4" placeholder="Type your feedback..." required></textarea>
  </div>
  <div class="centered">
  <button type="submit" class="btn btn-primary">Submit</button>
  </div>
</form>
</div>
<h4 style="color:blue;text-align:center"><c:out value = "${requestScope.message}"/></h4>
</body>
</html>