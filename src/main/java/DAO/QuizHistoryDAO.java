package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.QuizHistory;
import util.DBConnection;

public class QuizHistoryDAO {
    private DBConnection dbConnection;
    
    public QuizHistoryDAO() {
    	dbConnection = new DBConnection();
    }
    
   
   
    public List<QuizHistory> getHistoryByUserId(long id) {
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        List<QuizHistory> list = new ArrayList<QuizHistory>();
        try {
            String query = "select * from quizHistory where userId=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
            	QuizHistory history = new QuizHistory();
            	history.setId(rs.getLong(1));
            	history.setUserId(rs.getLong(2));
            	history.setQuizId(rs.getLong(3));
            	history.setPass(rs.getBoolean(4));
            	history.setStartTime(rs.getString(5));
            	history.setEndTime(rs.getString(6));

            	list.add(history);
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
    
    
    public boolean setHistory(QuizHistory history) {
		boolean flag = false;
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        try {
            String query = "insert into quizHistory values (?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
	            statement.setLong(1, history.getUserId());
	            statement.setLong(2, history.getQuizId());
	            statement.setBoolean(3, history.isPass());

	            //here
	            statement.setString(4, history.getStartTime()); ;
	            statement.setString(5, history.getEndTime());
	            statement.setInt(6,history.getScore());
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
    
    public boolean setTimeTakenById(double time, long id) {
		boolean flag = false;
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        try {
            String query = "update quizHistory set timeTaken=? where id?";
            PreparedStatement statement = con.prepareStatement(query);
	            statement.setDouble(1, time);
	            statement.setLong(2, id);
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
    
    public long getHistoryID(QuizHistory history) {
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        long id = -1;
        try {
            String query = "select id from quizHistory where userId=? and quizId=? and startTime=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, history.getUserId());
            statement.setLong(2,history.getQuizId());

            //here
            statement.setString(3,history.getStartTime());
            
            boolean flag = statement.execute();
            if(flag) {
            	ResultSet rs = statement.getResultSet();
            while(rs.next()) {	
            	id = rs.getLong(1);
            }
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
        return id;
    }
    
    
    
}
