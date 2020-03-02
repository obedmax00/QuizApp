package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Quiz;
import util.DBConnection;

public class QuizDAO {
    private DBConnection dbConnection;
    
    public QuizDAO() {
    	dbConnection = new DBConnection();
    }
    
   
   
    public List<Quiz> getQuiz() {
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        List<Quiz> list = new ArrayList<Quiz>();
        try {
            String query = "select * from quiz";
            PreparedStatement statement = con.prepareStatement(query);;
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
            	Quiz quiz = new Quiz();
            	quiz.setId(rs.getLong(1));
            	quiz.setName(rs.getString(2));
            	quiz.setPictureURL(rs.getString(3));
            	list.add(quiz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    
}
