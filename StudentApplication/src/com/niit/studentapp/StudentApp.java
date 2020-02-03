package com.niit.studentapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentApp {
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworkshopdb","root","");
			connection.setAutoCommit(false);
			Statement statement=connection.createStatement();
			PreparedStatement preparedStatement=null;
			String createTablequery="CREATE TABLE IF NOT EXISTS STUDENT (ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(30), PHONE_NO VARCHAR(10), EMAIL_ID VARCHAR(30))";
			//execute is used for DDL statements, for creating a table
			if(statement.execute(createTablequery)) {
				System.out.println("Table created in database");
			}
			else
				System.out.println("Table already exist in database");
			
			//inserting records in the table
			String insertQuery="insert into student (name, phone_no, email_id) values (?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(insertQuery);
			
			preparedStatement.setString(1, "Suresh");
			preparedStatement.setString(2, "989988888");
			preparedStatement.setString(3, "suresh@gmail.com");
			
			int insert=preparedStatement.executeUpdate();
			if(insert>0) {
				System.out.println("Data inserted successfully");
			}
			else
				System.out.println("Data not inserted");
			
			//displaying the records
			statement=connection.createStatement();
			//statement.executeQuery("select * from student");
			
			ResultSet resultSet=statement.executeQuery("select * from student");
			while(resultSet.next()) {
				System.out.println("Id is: "+resultSet.getInt("id") + ", Name is: "+resultSet.getString("name"));
			}
			
			//updating the records
			String updateQuery="update student set name=?, phone_no=?, email_id=? where id=?";
			
			preparedStatement=connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, "Ramesh");
			preparedStatement.setString(2, "343434");
			preparedStatement.setString(3, "r@gmail.com");
			
			preparedStatement.setInt(4, 1);
			
			int update=preparedStatement.executeUpdate();
			if(update>0)
				System.out.println("Data updated");
			else
				System.out.println("Data not updated");
			
			//deleting the records
			String deleteQuery="delete from student where id=?";
			preparedStatement=connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, 3);
			
			int delete=preparedStatement.executeUpdate();
			if(delete>0) {
				System.out.println("Data deleted");
			}
			else
				System.out.println("Data not deleted");
			//connection.commit();
			connection.rollback();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
