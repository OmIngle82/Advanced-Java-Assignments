package com.rs;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeDAO {
    int employee_id;
    String name;
    String email;
    BigDecimal salary;
    int dept_id;
    String sql;

    Connection con = DBConnection.getDBConnection();
    Scanner sc = new Scanner(System.in);

    // Add Employee
    public void addEmployee() {
        System.out.println("Enter Employee ID:");
        employee_id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Name:");
        name = sc.nextLine();

        System.out.println("Enter Email:");
        email = sc.nextLine();

        System.out.println("Enter Salary:");
        salary = sc.nextBigDecimal();

        System.out.println("Enter Department Id:");
        dept_id = sc.nextInt();
        sc.nextLine();

        sql = "INSERT INTO employees VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, employee_id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setBigDecimal(4, salary);
            ps.setInt(5, dept_id);
            ps.executeUpdate();
            System.out.println("Employee added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Employees
    public void viewEmployees() {
        sql = "SELECT * FROM employees";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(
                    rs.getInt("emp_id") + " "
                    + rs.getString("name") + " "
                    + rs.getString("email") + " "
                    + rs.getBigDecimal("salary") + " "
                    + rs.getInt("dept_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Employee
    public void updateEmployee() {
        System.out.println("Enter Employee ID to update employee details:");
        employee_id = sc.nextInt();
        System.out.println("What do you want to update\n1.Name\n2.Email\n3.Salary");
        int userChoice = sc.nextInt();
        sc.nextLine();

        switch (userChoice) {
            case 1:
                System.out.println("Enter new Name:");
                name = sc.nextLine();
                sql = "UPDATE employees SET name = ? WHERE emp_id = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, name);
                    ps.setInt(2, employee_id);
                    ps.executeUpdate();
                    System.out.println("Employee Name updated successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case 2:
                System.out.println("Enter new Email:");
                email = sc.nextLine();
                sql = "UPDATE employees SET email = ? WHERE emp_id = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, email);
                    ps.setInt(2, employee_id);
                    ps.executeUpdate();
                    System.out.println("Employee Email updated successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case 3:
                System.out.println("Enter new Salary:");
                salary = sc.nextBigDecimal();
                sql = "UPDATE employees SET salary = ? WHERE emp_id = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setBigDecimal(1, salary);
                    ps.setInt(2, employee_id);
                    ps.executeUpdate();
                    System.out.println("Employee Salary updated successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // Delete Employee
    public void deleteEmployee() {
        System.out.println("Enter Employee Id to delete Employee data:");
        employee_id = sc.nextInt();
        sc.nextLine();

        sql = "DELETE FROM employees WHERE emp_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, employee_id);
            ps.executeUpdate();
            System.out.println("Employee data deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}