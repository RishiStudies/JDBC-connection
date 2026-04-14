package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Employee {
	
	static final String url="jdbc:mysql://localhost:3306/EmployeeDB";
	static final String uname="root";
    static final String pass="root";
	static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("1. Add Employee");
			System.out.println("2. View All Employee");
			System.out.println("3. Update View");
			System.out.println("4. Delete Employee");
			System.out.println("5. Exit");
			System.out.println("Enter your choice: ");
			int ch=sc.nextInt();
		
			switch(ch) {
			case 1:
				addEmployee();
				break;
			case 2:
				viewEmployee();
				break;
			case 3:
				updateEmployee();
				break;
			case 4:
				deleteEmployee();
				break;
			default:
				System.out.println("Invalid Input!");
				
			
			}
		}
			
		
	}

	private static void deleteEmployee() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = DriverManager.getConnection(url, uname, pass);
		System.out.println("Enter the id of employee: ");
		int id = sc.nextInt();
		String sql = "delete from employee where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int rows = ps.executeUpdate();
		if(rows >0) {
			System.out.println("Data added!");
		}
		else {
			System.out.println("Data failed!");
		}
	}

	private static void updateEmployee() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = DriverManager.getConnection(url, uname, pass);
		System.out.println("Enter the employee id to update: ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter updated name ");
		String name = sc.nextLine();
		System.out.println("Enter udpated dept: ");
		String dept = sc.nextLine();
		System.out.println("Enter updated salary");
		double sal = sc.nextDouble();
		
		String sql = "update employee set name = ?, dept = ?, salary = ? where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, dept);
		ps.setDouble(3, sal);
		ps.setInt(4, id);
		int rows = ps.executeUpdate();
		if(rows >0) {
			System.out.println("Data added!");
		}
		else {
			System.out.println("Data failed!");
		}
		
	}

	private static void viewEmployee() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = DriverManager.getConnection(url, uname, pass);
		Statement stmt = con.createStatement();
		String query = "select * from Employee";
		ResultSet rs =  stmt.executeQuery(query);
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String dept = rs.getString("dept");
			double sal = rs.getDouble("salary");
			
			System.out.println("ID: "+id + " NAME: "+name + " DEPT: "+dept + " SALARY: "+sal);
//			System.out.println("NAME: "+name);
//			System.out.println("DEPT: "+dept);
//			System.out.println("SALARY: "+sal);
		}
			
			
	}

	private static void addEmployee() throws SQLException {
		// TODO Auto-generated method stub
		sc.nextLine();
		System.out.println("Enter name:");
		String name = sc.nextLine();
		System.out.println("Enter department:");
		String dept = sc.nextLine();
		System.out.println("Enter salary:");
		double sal = sc.nextDouble();
		
		String sql = "insert into Employee(name, dept, salary)"+"values(?,?,?)";
		Connection con = DriverManager.getConnection(url,uname,pass);
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, dept);
		ps.setDouble(3, sal);
		
		int rows = ps.executeUpdate();
		if(rows >0) {
			System.out.println("Data added!");
		}
		else {
			System.out.println("Data failed!");
		}
		
	}

}