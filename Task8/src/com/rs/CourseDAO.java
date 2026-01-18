package com.rs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CourseDAO {

	public void courseOperations() {
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("Course Menu");
			System.out.println("1.Add new course\n2.View all courses\n3.Update course\n4.Delete course\n0.Back to main menu");
			System.out.println("Enter choice:");
			
			choice = sc.nextInt();
			sc.nextLine();
			
			try(Connection con = DBConnection.getDBConnection()){
				switch(choice) {
				case 1:
					System.out.println("Enter course ID:");
					int id = sc.nextInt();
					sc.nextLine();
					
					System.out.println("Enter course name:");
					String name = sc.nextLine();
					
					System.out.println("Enter duration in months:");
					String duration = sc.nextLine();
					
					Course course = new Course(id, name, duration);
					
					String insertQuery = "INSERT INTO courses VALUES(?,?,?)";
					PreparedStatement ps = con.prepareStatement(insertQuery);
					
					ps.setInt(1, course.getCourseId());
					ps.setString(2, course.getCourseName());
					ps.setString(3, course.getDuration());
					
					int rowsAffected = ps.executeUpdate();
					System.out.println(rowsAffected+" course added successfully");
					break;
					
				case 2:
					String viewQuery = "SELECT * FROM courses";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(viewQuery);
					
					System.out.println("ID | Name | Duration");
					System.out.println("---------------------");
					
					while(rs.next()) {
						Course c = new Course(rs.getInt("course_id"), rs.getString("course_name"), rs.getString("duration"));
						System.out.println(c.getCourseId() + " | "+ c.getCourseName()+" | "+c.getDuration());
					}
					break;
					
				case 3:
					System.out.println("Enter course ID to update:");
					int updateId = sc.nextInt();
					sc.nextLine();
					
					System.out.println("Enter new course name:");
					String newName = sc.nextLine();
					
					System.out.println("Enter new duration:");
					String newDuration = sc.nextLine();
					
					String updateQuery = "UPDATE courses SET course_name = ?, duration = ? WHERE course_id = ?";
					PreparedStatement ps2 = con.prepareStatement(updateQuery);
					ps2.setString(1, newName);
					ps2.setString(2, newDuration);
					ps2.setInt(3, updateId);
					
					int rowsUpdated = ps2.executeUpdate();
					if(rowsUpdated > 0) {
						System.out.println("Course updated successfully");
					} else {
						System.out.println("Course ID not found");
					}
					break;
					
				case 4:
					System.out.println("Enter course ID to delete:");
					int deleteId = sc.nextInt();
					
					String deleteQuery = "DELETE FROM courses WHERE course_id = ?";
					PreparedStatement ps3 = con.prepareStatement(deleteQuery);
					ps3.setInt(1, deleteId);
					
					try {
						int rowsDeleted = ps3.executeUpdate();
						if(rowsDeleted > 0) {
							System.out.println("Course deleted successfully");
						} else {
							System.out.println("Course ID not found");
						}
					} catch (SQLException e) {
						System.out.println("Cannot delete this course. Students are enrolled in it.");
					}
					break;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}while(choice!=0);
		
	}

}