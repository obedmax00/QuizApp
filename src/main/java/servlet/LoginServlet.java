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

import DAO.QuizUserDAO;
import model.QuizUser;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
		QuizUserDAO quizUserDAO = new QuizUserDAO();
		try {
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		QuizUser user = quizUserDAO.getUserByNameAndPassword(name, password);
		if(user != null) {
			HttpSession oldSession = request.getSession(false);
			if(oldSession!=null) {
				oldSession.invalidate();
			}
			HttpSession session = request.getSession(true);
			int status = user.getStatus();
			if(status==1) {
				session.setAttribute("user", user);
				response.sendRedirect("QuizServlet");
			}else if(status!=1){
				String message = "user suspended";
				request.setAttribute("message", message);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else{
			String message = "Wrong password/user combination. Please Re-enter";
			request.setAttribute("message", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("cant get writer");
		}catch(ServletException e) {
			System.out.println("Something wrong with servlet");
		}
		
	}
}
