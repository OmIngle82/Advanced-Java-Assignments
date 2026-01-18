package com.rs;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        EmployeeDAO ed = new EmployeeDAO();
        TaxDAO td = new TaxDAO();

        int userChoice;

        do {
        	System.out.println("--------------------------------------------------");
            System.out.println("Welcome to Employee Tax Calculation System");
            System.out.println("Please select an option to continue");
            System.out.println("1.Add Employee");
            System.out.println("2.View Employees");
            System.out.println("3.Update Employee Details");
            System.out.println("4.Delete Employee");
            System.out.println("5.Calculate and Store tax");
            System.out.println("6.Display Employees with Calculated taxes");
            System.out.println("7.Exit");
            System.out.println("--------------------------------------------------");
            userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    ed.addEmployee();
                    break;
                case 2:
                    ed.viewEmployees();
                    break;
                case 3:
                    ed.updateEmployee();
                    break;
                case 4:
                    ed.deleteEmployee();
                    break;
                case 5:
                    td.calculateTax();
                    break;
                case 6:
                    td.showTaxes();
                    break;
                case 7:
                    System.out.println("Thank you for using the system.\nBYE");
                    break;
                default:
                    System.out.println("Please enter valid choice");
            }
        } while (userChoice != 7);

        sc.close();
    }
}