package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Feedback;
import util.DBConnection;

public class FeedbackDAO {
	
    private DBConnection dbConnection;
    
    public FeedbackDAO() {
    	dbConnection = new DBConnection();
    }
    
    
    public boolean setFeedback(Feedback feedback) {
		boolean flag = false;
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        try {
            String query = "insert into feedback values (?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, feedback.getQuizId());
            statement.setString(2, feedback.getFeedback());
            statement.setString(3, feedback.getRating());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected>0) {
                flag = true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	if(con != null) {
        		try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }	
        	}
            
        }
    	return flag;
    }
    
   
    public List<Feedback> getFeedbackByQuizId(long id) {
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        List<Feedback> list = new ArrayList<Feedback>();
        try {
            String query = "select * from feedback where quizId=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
            	Feedback feedback = new Feedback();
            	feedback.setId(rs.getLong(1));
            	feedback.setQuizId(rs.getLong(2));
            	feedback.setFeedback(rs.getString(3));
            	feedback.setRating(rs.getString(4));
            	list.add(feedback);
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
    
    
    public List<Feedback> getPageFeedback() {
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        List<Feedback> list = new ArrayList<Feedback>();
        try {
            String query = "select * from feedback where quizId is null";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
            	Feedback feedback = new Feedback();
            	feedback.setId(rs.getLong(1));
            	feedback.setQuizId(rs.getLong(2));
            	feedback.setFeedback(rs.getString(3));
            	feedback.setRating(rs.getString(4));
            	list.add(feedback);
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
