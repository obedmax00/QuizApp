package servlet;

import HibernateDAO.HibernateQuizUserDAO;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import model.QuizUser;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("in profileServlet do get");
        HibernateQuizUserDAO hibernateQuizUserDAO = new HibernateQuizUserDAO();
        QuizUser user = hibernateQuizUserDAO.getUserById(Long.valueOf(request.getParameter("id")));
        user.setStatus(Integer.valueOf(request.getParameter("value")));
        hibernateQuizUserDAO.mergeQuizUser(user);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("in profile dopost");
        HibernateQuizUserDAO hibernateQuizUserDAO = new HibernateQuizUserDAO();
        String index = request.getParameter("index");
        List<QuizUser> users;
        users = hibernateQuizUserDAO.getQuizUsers(Integer.valueOf(index));
        try {
            PrintWriter out = response.getWriter();




            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String usersString = gson.toJson(users);
//            ObjectMapper objectMapper = new ObjectMapper();
//            String usersString = objectMapper.writeValueAsString(users);
            out.append(usersString);


        }catch (IOException e) {
            // TODO: handle exception
            System.out.println("cant get writer");
        }

    }
}
