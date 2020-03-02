package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuizDAO;
import model.Quiz;
@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		QuizDAO quizDAO = new QuizDAO();
		List<Quiz> list = quizDAO.getQuiz();
		try {
		request.setAttribute("quizList", list);
		request.getRequestDispatcher("home.jsp").forward(request, response);
		}catch(ServletException e) {
			System.out.println("something went wrong with servlet...");
		}catch(IOException e) {
			System.out.println("something went wrong with input and output stream...");
		}
	}
}
