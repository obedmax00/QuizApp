package DAO;

import model.Feedback;
import model.WebsiteFeedback;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WebsiteFeedbackDAO {

    private DBConnection dbConnection;

    public WebsiteFeedbackDAO() {
        dbConnection = new DBConnection();
    }


    public boolean setFeedback(WebsiteFeedback feedback) {
        boolean flag = false;
        dbConnection.createConnection();
        Connection con = dbConnection.getConnection();
        try {
            String query = "insert into WebsiteFeedback values (?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, feedback.getFeedback());
            statement.setString(2, feedback.getRating());
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
