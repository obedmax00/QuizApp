package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.QuestionPool;
import util.DBConnection;

public class QuestionPoolDAO {
    private DBConnection dbConnection;
    
    public QuestionPoolDAO() {
    	dbConnection = new DBConnection();
    }
    
   
   
    public List<QuestionPool> getQuestionPoolByQuizId(long id) {
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        List<QuestionPool> list = new ArrayList<QuestionPool>();
        try {
            String query = "select * from questionPool where quizId=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
            	QuestionPool question = new QuestionPool();
            	question.setId(rs.getLong(1));
            	question.setQuizId(rs.getLong(2));
            	question.setQuestion(rs.getString(3));
            	question.setQuestionAns(rs.getString(4));
            	question.setIfShort(rs.getBoolean(5));
            	list.add(question);
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
    
    
    public List<QuestionPool> getTenQuestionByQuizId(long id,int number) {
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        List<QuestionPool> list = new ArrayList<QuestionPool>();
        try {
            //here
            String query = "SELECT TOP (?) * FROM questionPool where quizId=? and status=1 ORDER BY NEWID()";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, number);
            statement.setLong(2, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
            	QuestionPool question = new QuestionPool();
            	question.setId(rs.getLong(1));
            	question.setQuizId(rs.getLong(2));
            	question.setQuestion(rs.getString(3));
            	question.setQuestionAns(rs.getString(4));
            	question.setIfShort(rs.getBoolean(5));
            	list.add(question);
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
