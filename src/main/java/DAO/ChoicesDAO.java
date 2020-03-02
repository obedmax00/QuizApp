package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Choices;
import util.DBConnection;

public class ChoicesDAO {
    private DBConnection dbConnection;
    
    public ChoicesDAO() {
    	dbConnection = new DBConnection();
    }
    
    public Set<Choices> getChoicesByQuestionId(long id) {
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        Set<Choices> choices= new HashSet<>();
        try {
            String query = "Select * from choices where questionId=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
            	Choices choice = new Choices();
            	choice.setChoiceNumber(rs.getString(1));
            	choice.setQuestionId(rs.getLong(2));
            	choice.setChoiceName(rs.getString(3));
            	choices.add(choice);
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
    	return choices;
    }
   
}
