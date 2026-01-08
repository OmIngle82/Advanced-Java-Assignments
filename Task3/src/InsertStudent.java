import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class InsertStudent {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test_db";
        String username = "root";
        String password = "om@ingle."; 

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student ID:");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.println("Enter Student Name:");
        String name = sc.nextLine();

        System.out.println("Enter Student Age:");
        int age = sc.nextInt();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            
            String sql = "INSERT INTO students VALUES (" + id + ", '" + name + "', " + age + ")";
            
            int rowsAffected = stmt.executeUpdate(sql);
            
            if (rowsAffected > 0) {
                System.out.println("Success! Student inserted.");
            } else {
                System.out.println("Insertion failed.");
            }

            stmt.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}