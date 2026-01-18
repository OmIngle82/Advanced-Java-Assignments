import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CRUDOperations {
    static int userChoice;
    static int student_id;
    static String student_name;
    static String student_course;
    static int student_age;
    static String sql;
    static Scanner sc = new Scanner(System.in);

    void choice(int userChoice) {
        this.userChoice = userChoice;
        switch (userChoice) {
            case 1:
                System.out.println("Enter student id:");
                student_id = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter student name:");
                student_name = sc.nextLine();

                System.out.println("Enter course:");
                student_course = sc.nextLine();

                System.out.println("Enter student age:");
                student_age = sc.nextInt();

                sql = "INSERT INTO students_rs VALUES (" + student_id + ", '" + student_name + "', '" + student_course + "', " + student_age + ")";
                break;

            case 2:
                System.out.println("Enter student ID to update Student course:");
                student_id = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter new course:");
                student_course = sc.nextLine();
                sql = "UPDATE students_rs SET course = '" + student_course + "' WHERE student_id = " + student_id;
                break;

            case 3:
                System.out.println("Enter student Id to DELETE student record:");
                student_id = sc.nextInt();
                sc.nextLine();
                sql = "DELETE FROM students_rs WHERE student_id = " + student_id;
                break;

            case 4:
                System.out.println("Enter Student ID to display student information:");
                student_id = sc.nextInt();
                sc.nextLine();
                sql = "SELECT * FROM students_rs WHERE student_id = " + student_id;
                break;
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test_db";
        String username = "root";
        String password = "om@ingle.";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            System.out.println("Enter your Choice:\n1:INSERT new student data\n2:Update student data\n3:Delete student data\n4:Display student information");
            userChoice = sc.nextInt();
            CRUDOperations co = new CRUDOperations();
            co.choice(userChoice);

            if (userChoice == 4) {
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    System.out.println("Student ID: " + rs.getInt("student_id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Course: " + rs.getString("course"));
                    System.out.println("Age: " + rs.getInt("age"));
                } else {
                    System.out.println("No student found with ID " + student_id);
                }
            } else {
                int rowsAffected = stmt.executeUpdate(sql);
                if (rowsAffected > 0) {
                    System.out.println("Success!");
                } else {
                    System.out.println("Failed.");
                }
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}