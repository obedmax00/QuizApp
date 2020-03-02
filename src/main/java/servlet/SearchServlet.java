package servlet;

import HibernateDAO.HibernateQuizHistoryDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Quiz;
import model.QuizHistory;
import model.QuizUser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        String category = request.getParameter("category");
        String value = request.getParameter("label");
        String index = request.getParameter("index");
        System.out.println(category);
        HibernateQuizHistoryDAO hibernateQuizHistoryDAO =new HibernateQuizHistoryDAO();
        List<QuizHistory> histories=null;
        if(category.equals("User Name")){
            histories = hibernateQuizHistoryDAO.getHistoryByUserName(value,Integer.valueOf(index));
        }else if(category.equals("Category")){
            histories = hibernateQuizHistoryDAO.getHistoryByQuizCat(value,Integer.valueOf(index));
        }

        Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String studentString = gson.toJson(histories);


        try{
            PrintWriter writer = response.getWriter();
            writer.append(studentString);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("in search do post");
        HibernateQuizHistoryDAO hibernateQuizHistoryDAO = new HibernateQuizHistoryDAO();
        List<Map<String,String>> stringHolders = new ArrayList<>();
        String regex = request.getParameter("search");

        List<QuizHistory> catList = hibernateQuizHistoryDAO.getHistoryByCatRegex(regex);
        Set<Quiz> quizzes = new HashSet<>();
        for(QuizHistory x: catList){
            quizzes.add(x.getQuiz());
        }
        for(Quiz x: quizzes){
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("label",x.getName());
            stringMap.put("category","Category");
            stringHolders.add(stringMap);
        }
        List<QuizHistory> nameList = hibernateQuizHistoryDAO.getHistoryByNameRegex(regex);
        Set<QuizUser> users =new HashSet<>();
        for(QuizHistory x: nameList){
            users.add(x.getQuizUser());
        }
        for(QuizUser x: users){
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("label",x.getFirstName()+" "+x.getLastName());
            stringMap.put("category","User Name");
            stringHolders.add(stringMap);
        }
        try {
            PrintWriter writer = response.getWriter();
            Gson gson = new Gson();
            String holder = gson.toJson(stringHolders);

            writer.append(holder);
        }catch(IOException e){
            e.printStackTrace();
        }

    }


}
