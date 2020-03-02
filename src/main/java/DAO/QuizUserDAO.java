package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.QuizHistory;
import model.QuizUser;
import util.DBConnection;

public class QuizUserDAO {
    private DBConnection dbConnection;
    
    public QuizUserDAO() {
    	dbConnection = new DBConnection();
    }
    
   
   
    public QuizUser getUserByNameAndPassword(String name, String password) {
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        QuizUser user = null;
        try {
            String query = "select * from quizUser where name=? and password=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            boolean flag = statement.execute();
           
            	
            	ResultSet rs = statement.getResultSet();
            while(rs.next()) {
            	user = new QuizUser();
            	user.setId(rs.getLong(1));
            	user.setName(rs.getString(2));
            	user.setRole(rs.getString(4));
            	user.setStatus(rs.getInt("status"));
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
        return user;
    }
    
    
    public boolean setUser(QuizUser user) {
		boolean flag = false;
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        try {
            String query = "insert into quizUser values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
	            statement.setString(1, user.getName());
	            statement.setString(2, user.getPassword());
	            statement.setString(3, "u");
	            statement.setString(4, user.getEmail());
	            statement.setInt(5,user.getStatus());
	            statement.setString(6,user.getPhoneNumber());
	            statement.setString(7,user.getFirstName());
	            statement.setString(8,user.getLastName());
	            statement.setString(9,user.getAddress());
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
    
}
