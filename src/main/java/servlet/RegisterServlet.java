package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuizUserDAO;
import model.QuizUser;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
		QuizUserDAO userDAO = new QuizUserDAO();
		QuizUser user = new QuizUser();
		try {
			PrintWriter out = response.getWriter();
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			user.setStatus(1);
			user.setPhoneNumber(request.getParameter("phoneNumber"));
			user.setFirstName(request.getParameter("firstName"));

			user.setLastName(request.getParameter("lastName"));

			user.setAddress(request.getParameter("Address"));
			user.setPhoneNumber(request.getParameter("Number"));


			boolean flag = userDAO.setUser(user);
		request.getRequestDispatcher("registration.jsp").include(request, response);
		if(flag) {
			out.println("Registered user");
		}else {
			out.println("Not Registerd");
		}
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("cant get writer");
		}catch(ServletException e) {
			System.out.println("something is wrong with the servlet");
		}
		
	}
}
