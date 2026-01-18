package com.rs;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StudentDAO sd = new StudentDAO();
		CourseDAO cd = new CourseDAO();
		
		int userChoice;
		
		do {
			System.out.println("STUDENT MANAGEMENT SYSTEM");
			System.out.println("1.Manage Students\n2.Manage Courses\n0.Exit");
			
			userChoice = sc.nextInt();
			
		switch(userChoice) {
		case 1:
			sd.studentOperations();
			break;
		case 2:
			cd.courseOperations();
			break;
		case 0:
			System.out.println("Thank you for using the system\nBYE");
			break;
		default:
			System.out.println("Please enter valid choice");
		}
	} while(userChoice!=0);
		sc.close();

	}
}
