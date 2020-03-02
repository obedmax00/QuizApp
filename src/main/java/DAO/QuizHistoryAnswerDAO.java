package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.QuizHistoryAnswer;
import util.DBConnection;

public class QuizHistoryAnswerDAO {
    private DBConnection dbConnection;
    
    public QuizHistoryAnswerDAO() {
    	dbConnection = new DBConnection();
    }
    
   
   
    public List<QuizHistoryAnswer> getAnswersByQuizHistoryId(long id) {
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        List<QuizHistoryAnswer> list = new ArrayList<QuizHistoryAnswer>();
        try {
            String query = "select * from quizHistoryAnswer where quizHistoryId=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
            	QuizHistoryAnswer qAnswer = new QuizHistoryAnswer();
            	qAnswer.setId(rs.getLong(1));
            	qAnswer.setQuizHistoryId(rs.getLong(2));
            	qAnswer.setQuestionId(rs.getLong(3));
            	qAnswer.setUserAns(rs.getString(4));
            	qAnswer.setCorrect(rs.getBoolean(5));
            	list.add(qAnswer);
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
    
    
    public boolean setAnswers(List<QuizHistoryAnswer> answer, long quizHistoryId) {
		boolean flag = false;
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        try {
            String query = "insert into quizHistoryAnswer values (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            for(QuizHistoryAnswer x: answer) {
	            statement.setLong(1, quizHistoryId);
	            statement.setLong(2, x.getQuestionId());
	            statement.setString(3, x.getUserAns());
	            statement.setBoolean(4, x.isCorrect());
	            statement.addBatch();
            }
            int[] rowsAffected = statement.executeBatch();
            if (rowsAffected[0] > 0) {
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
    
    

}
