package com.lti.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccServ
 */
public class AccServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	public AccServ() throws SQLException {
		connection = DriverManager.getConnection("jdbc:oracle:thin:comp13:1158:ORCL", "hr", "admin");
		statement = connection.createStatement();
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNumber = Integer.parseInt(request.getParameter("accNo"));
		PrintWriter writer = response.getWriter();
		
		try {
			resultSet = statement.executeQuery("select * from accounts where accountno="+accountNumber);
			while (resultSet.next()) {
				writer.println("<table><tr><td>Account Number</td><td>"+resultSet.getInt(1)+"</td></tr><tr><td>Account Balance</td><td>"+resultSet.getInt(2)+"</td></tr></table>");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
