package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.QuizDAO;
import model.Quiz;
@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession(false);
			if(session!=null) {
				session.invalidate();
			}
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}catch(ServletException e) {
			System.out.println("something went wrong with servlet...");
		}catch(IOException e) {
			System.out.println("something went wrong with input and output stream...");
		}
	}
}
