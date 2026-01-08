import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateStudent {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test_db";
        String username = "root";
        String password = "om@ingle."; 

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student ID to Update:");
        int id = sc.nextInt();

        System.out.println("Enter New Marks:");
        int marks = sc.nextInt();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            
            String sql = "UPDATE students SET marks = " + marks + " WHERE id = " + id;
            
            int rowsAffected = stmt.executeUpdate(sql);
            
            if (rowsAffected > 0) {
                System.out.println("Success! Marks updated.");
            } else {
                System.out.println("Student ID not found.");
            }

            stmt.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}