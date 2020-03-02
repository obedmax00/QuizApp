package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection con;

    public void createConnection() {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost:50806;" +
                        "databaseName=quizApp;user=sa;password=reallyStrongPwd123;";
                con = DriverManager.getConnection(connectionUrl);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public Connection getConnection() {
        if (con != null) {
            return con;
        }else{
            createConnection();
        }
        return con;
    }

}
