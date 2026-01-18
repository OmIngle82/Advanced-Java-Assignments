package com.rs;

import java.sql.*;
import java.util.Scanner;

public class StudentDAO {
	public void studentOperations() {
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("Student Menu");
			System.out.println("1.Add new student\n2.Update student data\n3.Delete student data\n4.View all students\n5.View students with courses\n0.Back to main menu");
			System.out.println("Enter choice");
			choice = sc.nextInt();
			sc.nextLine();
			
			try(Connection con = DBConnection.getDBConnection()){
				switch(choice) {
				case 1:
					System.out.println("Enter student Id:");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter name:");
					String name = sc.nextLine();
					System.out.println("Enter email:");
					String email = sc.nextLine();
					System.out.println("Enter phone no:");
					String phone = sc.nextLine();
					System.out.println("Enter age:");
					int age = sc.nextInt();
					System.out.println("Enter course ID:");
					int courseId = sc.nextInt();
					
					Student st = new Student(id, name, email, phone, age, courseId);
					
					String insertQuery = "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(insertQuery);
					ps.setInt(1, st.getStudentId());
					ps.setString(2, st.getName());
					ps.setString(3, st.getEmail());
					ps.setString(4, st.getPhone());
					ps.setInt(5, st.getAge());
					ps.setInt(6, st.getCourseId());
					ps.executeUpdate();
					System.out.println("Student added successfully");
					break;
					
				case 2:
					System.out.println("Enter student Id to update:");
					int updateId = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter new email:");
					String newEmail = sc.nextLine();
					
					String updateQuery = "UPDATE students SET email = ? WHERE student_id = ?";
					PreparedStatement ps2 = con.prepareStatement(updateQuery);
					ps2.setString(1, newEmail);
					ps2.setInt(2, updateId);
					int rowsUpdated = ps2.executeUpdate();
					if(rowsUpdated > 0) {
						System.out.println("Student updated");
					} else {
						System.out.println("Student not found");
					}
					break;
					
				case 3:
					System.out.println("Enter student Id to delete:");
					int deleteId = sc.nextInt();
					
					String deleteQuery = "DELETE FROM students WHERE student_id = ?";
					PreparedStatement ps3 = con.prepareStatement(deleteQuery);
					ps3.setInt(1, deleteId);
					int rowsDeleted = ps3.executeUpdate();
					if(rowsDeleted > 0) {
						System.out.println("Student deleted");
					} else {
						System.out.println("Student not found");
					}
					break;
					
				case 4:
					String selectQuery = "SELECT * FROM students";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(selectQuery);
					while(rs.next()) {
						Student s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
						System.out.println(s.getStudentId() + " " + s.getName() + " " + s.getEmail());
					}
					break;
					
				case 5:
					String joinQuery = "SELECT s.student_id, s.name, s.email, c.course_name FROM students s INNER JOIN courses c ON s.course_id = c.course_id";
					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery(joinQuery);
					System.out.println("Id | Name |  EmailId  | Course");
					while(rs2.next()) {
						System.out.println(rs2.getInt(1) + " | " + rs2.getString(2) + " | " + rs2.getString(3) + " | " + rs2.getString(4));
					}
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} while(choice != 0);
		
	}

}