import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginSystem {
    String userName;
    String passWord;
    String sql;
    Scanner sc = new Scanner(System.in);

    void login() {
        System.out.println("Welcome\nPlease enter your username and password to LOGIN:");
        System.out.println("USERNAME:");
        userName = sc.nextLine();
        System.out.println("PASSWORD:");
        passWord = sc.nextLine();
        sql = "SELECT * FROM users WHERE username = ? AND password = ?";
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test_db";
        String username = "root";
        String password = "om@ingle.";

        LoginSystem ls = new LoginSystem();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            ls.login();
            PreparedStatement stmt = con.prepareStatement(ls.sql);
            stmt.setString(1, ls.userName);
            stmt.setString(2, ls.passWord);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login Successful!");
            } else {
                System.out.println("Login Failed.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}