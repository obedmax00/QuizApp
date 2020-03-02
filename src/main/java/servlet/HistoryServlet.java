package servlet;

import HibernateDAO.HibernateQuizHistoryDAO;
import HibernateDAO.HibernateQuizUserDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Quiz;
import model.QuizHistory;
import model.QuizUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        String whichHistory = request.getParameter("whichHistory");
        HttpSession session = request.getSession(false);

        List<QuizHistory> list = (List<QuizHistory>) session.getAttribute("HistoryList");
        HibernateQuizHistoryDAO hibernateQuizHistoryDAO = new HibernateQuizHistoryDAO();
        QuizHistory history = hibernateQuizHistoryDAO.getHistoryandRelationById(list.get(Integer.valueOf(whichHistory)).getId());

        session.setAttribute("history",history);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("in history dopost");
        HibernateQuizHistoryDAO hibernateQuizHistoryDAO = new HibernateQuizHistoryDAO();
        String index = request.getParameter("index");
        String name = request.getParameter("name");
        List<QuizHistory> list =null;
        if(name.equals("none")) {
            list = hibernateQuizHistoryDAO.getHistoryOrderedByDate(Integer.valueOf(index));
        }else if(name.equals("name")){
            list = hibernateQuizHistoryDAO.getHistoryOrderedByName(Integer.valueOf(index));
        }else if(name.equals("cat")){
            list = hibernateQuizHistoryDAO.getHistoryOrderedByQuiz(Integer.valueOf(index));
        }
        HttpSession session = request.getSession(false);
        session.setAttribute("HistoryList",list);
        try {
            PrintWriter out = response.getWriter();




            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            String usersString = gson.toJson(list);
//            ObjectMapper objectMapper = new ObjectMapper();
//            String usersString = objectMapper.writeValueAsString(users);
            out.append(usersString);


        }catch (IOException e) {
            // TODO: handle exception
            System.out.println("cant get writer");
        }

    }
}
