package com.rs;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TaxDAO {
    BigDecimal salary;

    Scanner sc = new Scanner(System.in);
    Connection con = DBConnection.getDBConnection();

    public void calculateTax() {
        System.out.println("Enter Employee ID to calculate Tax");
        int employeeId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter TaxId:");
        int tax_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Financial year:");
        String Fyear = sc.nextLine();

        String sql = "SELECT salary FROM employees WHERE emp_id = ?";

        try {
            PreparedStatement psSelect = con.prepareStatement(sql);
            psSelect.setInt(1, employeeId);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                salary = rs.getBigDecimal("salary");
            } else {
                System.out.println("The employee does not have any salary.");
                return;
            }

            BigDecimal tax = BigDecimal.ZERO;

            if (salary.compareTo(new BigDecimal("250000")) <= 0) {
                tax = BigDecimal.ZERO;
            } else if (salary.compareTo(new BigDecimal("500000")) <= 0) {
                tax = salary.multiply(new BigDecimal("0.05"));
            } else if (salary.compareTo(new BigDecimal("1000000")) <= 0) {
                tax = salary.multiply(new BigDecimal("0.20"));
            } else {
                tax = salary.multiply(new BigDecimal("0.30"));
            }

            String sqlInsert = "INSERT INTO taxes(tax_id, emp_id, salary, tax_amount, financial_year) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setInt(1, tax_id);
            ps.setInt(2, employeeId);
            ps.setBigDecimal(3, salary);
            ps.setBigDecimal(4, tax);
            ps.setString(5, Fyear);

            ps.executeUpdate();

            System.out.println("Tax calculated and stored successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showTaxes() {
        String sql = "SELECT e.emp_id, e.name, d.dept_name, e.salary, t.tax_amount, t.financial_year "
                   + "FROM employees e "
                   + "INNER JOIN departments d ON e.dept_id = d.dept_id "
                   + "INNER JOIN taxes t ON e.emp_id = t.emp_id";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Emp_ID  Name  Department  Salary  Tax_Amount  Year");
            System.out.println("---------------------------------------------------");

            while (rs.next()) {
                int empId = rs.getInt("emp_id");
                String name = rs.getString("name");
                String dept = rs.getString("dept_name");
                BigDecimal sal = rs.getBigDecimal("salary");
                BigDecimal taxAmt = rs.getBigDecimal("tax_amount");
                String year = rs.getString("financial_year");

                System.out.println(empId + "  " + name + "  " + dept + "  " + sal + "  " + taxAmt + "  " + year);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}