package com.rs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static String url = "jdbc:mysql://localhost:3306/sms_db";
    static String username = "root";
    static String password = "om@ingle.";
    
    public static Connection getDBConnection() {
    	Connection con = null;
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	
		return con;
    }
    
}
