import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test_db";
        String username = "root";
        String password = "om@ingle."; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            if (con != null) {
                System.out.println("Connection Successful!");
            }
            con.close();
            System.out.println("Connection closed.");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection Failed.");
            e.printStackTrace();
        }
    }
}