package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FeedbackDAO;
import DAO.QuizDAO;
import DAO.QuizUserDAO;
import DAO.WebsiteFeedbackDAO;
import model.Feedback;
import model.Quiz;
import model.QuizUser;
import model.WebsiteFeedback;


@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
		


		QuizDAO quizDAO = new QuizDAO();
		List<Quiz> list = quizDAO.getQuiz();
		
		try {
			HttpSession session = request.getSession(false);
			String message;
			if(session.getAttribute("user")!=null) {
			PrintWriter out = response.getWriter();
			request.setAttribute("quizList", list);
			boolean flag;

			String whichQuiz = request.getParameter("whichQuiz");

			if(!whichQuiz.equals("-1")) {
				Feedback feedback = new Feedback();

				feedback.setFeedback(request.getParameter("feedback"));
				feedback.setQuizId(Long.valueOf(whichQuiz));
				feedback.setRating(request.getParameter("rating"));
				FeedbackDAO feedbackDAO = new FeedbackDAO();

				flag = feedbackDAO.setFeedback(feedback);


			}else{
				WebsiteFeedback websiteFeedback = new WebsiteFeedback();
				websiteFeedback.setFeedback(request.getParameter("feedback"));
				websiteFeedback.setRating(request.getParameter("rating"));
				WebsiteFeedbackDAO websiteFeedbackDAO = new WebsiteFeedbackDAO();
				flag = websiteFeedbackDAO.setFeedback(websiteFeedback);

			}



			

			
		request.getRequestDispatcher("feedback.jsp").include(request, response);
		if(flag) {
			message ="Feedback submited";
		}else {
			message="Feedback not submited";
		}
		}else {
			message="Please log in first";
		}
			request.setAttribute("message", message);
			request.getRequestDispatcher("feedback.jsp").forward(request, response);
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("cant get writer");
		}catch(ServletException e) {
			System.out.println("something is wrong with the servlet");
		}
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		QuizDAO quizDAO = new QuizDAO();
		List<Quiz> list = quizDAO.getQuiz();
		try {
		request.setAttribute("quizList", list);
		request.getRequestDispatcher("feedback.jsp").forward(request, response);
		}catch(ServletException e) {
			System.out.println("something went wrong with servlet...");
		}catch(IOException e) {
			System.out.println("something went wrong with input and output stream...");
		}
	}
}
