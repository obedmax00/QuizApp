package servlet;

import java.io.IOException;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.QuizDAO;
import model.QuestionPool;
import model.Quiz;
import model.QuizUser;
import service.DoQuiz;
@WebServlet("/QuizResultSerlvet")
public class QuizResultSerlvet extends HttpServlet{
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		DoQuiz doquiz = (DoQuiz)session.getAttribute("doQuiz");
		String next = request.getParameter("next");
		System.out.println("next: "+next);
		String input = request.getParameter("selector");
		System.out.println(input);
		QuestionPool question = (QuestionPool)session.getAttribute("curQuestion");
		QuestionPool curQuestion;
		if(next.equals("1")) {
			curQuestion=doquiz.nextQuestion(input);
			session.setAttribute("curQuestion", curQuestion);
			session.setAttribute("index", doquiz.getCurQuestion());
			session.setAttribute("userAnswer", doquiz.getCurAnswer(curQuestion.getId()));
			
			Cookie cookie = getCookie(request, "questionDone");
			if(cookie!=null) {
				cookie.setValue(String.valueOf(String.valueOf(doquiz.getAnswerSize())));
			}else {
				cookie = new Cookie("questionDone",String.valueOf(doquiz.getAnswerSize()));
			}
			
			response.addCookie(cookie);

			try {
			request.getRequestDispatcher("quiz.jsp").forward(request, response);
			}catch(IOException e) {
				System.out.println("Something went wrong with the input output...");
			}catch(ServletException e) {
				System.out.println("Something went wrong with servlet...");
			}
		}else if(next.equals("2")) {		
			System.out.println("in submit");
			String quizNumber = (String)session.getAttribute("quizNumber");
			QuizUser user = (QuizUser)session.getAttribute("user");
			
			
			doquiz.saveCurQuestion(input);
			
			
			   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now();  
			   System.out.println(dtf.format(now));
			   session.setAttribute("endTime", dtf.format(now));
			   
			   doquiz.setEndTime(dtf.format(now));
			   doquiz.submit(user.getId(), Long.valueOf(quizNumber));
			
			try {
				request.getRequestDispatcher("quizResult.jsp").forward(request, response);
			}catch(IOException e) {
				System.out.println("Something went wrong with the input output...");
			}catch(ServletException e) {
				System.out.println("Something went wrong with servlet in quiz result...");
			}
		}else if(next.equals("-2")) {
			String questionNumber = request.getParameter("whichQuestion");
			
			curQuestion=doquiz.chooseQuestion(input,Integer.valueOf(questionNumber));
			
			Cookie cookie = getCookie(request, "questionDone");
			if(cookie!=null) {
				cookie.setValue(String.valueOf(String.valueOf(doquiz.getAnswerSize())));
			}else {
				cookie = new Cookie("questionDone",String.valueOf(doquiz.getAnswerSize()));
			}
			response.addCookie(cookie);
			
			session.setAttribute("curQuestion", curQuestion);
			session.setAttribute("index", doquiz.getCurQuestion());
			session.setAttribute("userAnswer", doquiz.getCurAnswer(curQuestion.getId()));
			try {
			request.getRequestDispatcher("quiz.jsp").forward(request, response);
			}catch(IOException e) {
				System.out.println("Something went wrong with the input output...");
			}catch(ServletException e) {
				System.out.println("Something went wrong with servlet...");
			}
			
		}else{
			
			curQuestion=doquiz.previousQuestion(input);
			session.setAttribute("curQuestion", curQuestion);
			session.setAttribute("index", doquiz.getCurQuestion());
			session.setAttribute("userAnswer", doquiz.getCurAnswer(curQuestion.getId()));
			Cookie cookie = getCookie(request, "questionDone");
			if(cookie!=null) {
				cookie.setValue(String.valueOf(String.valueOf(doquiz.getAnswerSize())));
			}else {
				cookie = new Cookie("questionDone",String.valueOf(doquiz.getAnswerSize()));
			}
			response.addCookie(cookie);
			
			try {
			request.getRequestDispatcher("quiz.jsp").forward(request, response);
			}catch(IOException e) {
				System.out.println("Something went wrong with the input output...");
			}catch(ServletException e) {
				System.out.println("Something went wrong with servlet...");
			}
		}

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession(false);
			QuizUser user = (QuizUser)session.getAttribute("user");
			if(user!=null) {
				
				
				System.out.println(user.getName());
				
				DoQuiz doQuiz = new DoQuiz();
				String quizNumber= request.getParameter("test");
				String quizName = request.getParameter("quizName");




			   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			   LocalDateTime now = LocalDateTime.now();
			   System.out.println(dtf.format(now));  
				
				doQuiz.setStartTime(dtf.format(now));
				doQuiz.setUp(Long.parseLong(quizNumber));
				System.out.println(quizName);
				Calendar calendar = Calendar.getInstance();
				long time = calendar.getTimeInMillis();
				
				Cookie cookie = getCookie(request, "startTime");
				if(cookie!=null) {
					cookie.setValue(String.valueOf(time));
				}else {
				cookie = new Cookie("startTime",String.valueOf(time));
				}
				
				
				response.addCookie(cookie);
				session.setAttribute("startTime", dtf.format(now));
				session.setAttribute("quizNumber", quizNumber);
				session.setAttribute("doQuiz", doQuiz);
				session.setAttribute("quizName", quizName);
				
				session.setAttribute("curQuestion", doQuiz.currentQuestion());
				session.setAttribute("index", doQuiz.getCurQuestion());
				
				request.getRequestDispatcher("quiz.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}catch(ServletException e) {
			System.out.println("something went wrong with servlet...");
		}catch(IOException e) {
			System.out.println("something went wrong with input and output stream...");
		}
	}
	
	
    public static Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

}

	


