package com.nichat.webJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {
	
	private DataSource dataSource;

	public StudentDBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		List <Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;
		
		try {
			//Step 1 : get a connection
			myConn = dataSource.getConnection();
			
			//Step 2:create a SQL statement
			String sql = "select * from student order by last_name";
			myStmt = myConn.createStatement();
			
			//Step 3:execute sql query
			myRes = myStmt.executeQuery(sql);
			
			//Step 4:process the result set
			while(myRes.next()) {
				//retrieve data from result set row
				int id = myRes.getInt("id");
				String first_name = myRes.getString("first_name");
				String last_name = myRes.getString("last_name");
				String email = myRes.getString("email");
				
				Student tempStudent = new Student(id,first_name,last_name,email);
				students.add(tempStudent);
			}
			
			//Step 5:Close jdbc objects 
			close(myConn,myStmt,myRes);
			return students;
		}
		finally {
			
		}
		
		
	}
	
	public void addStudent(Student theStudent) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		System.out.print(theStudent.getFirst_name());
		System.out.print(theStudent.getLast_name());
		System.out.print(theStudent.getEmail());
		
		try {
			//Step 1 : get a connection
			myConn = dataSource.getConnection();
			
			//Step 2:create a SQL statement
			String sql = "insert into student(first_name, last_name, email) values (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			//set param values 
			myStmt.setString(1,theStudent.getFirst_name());
			myStmt.setString(2,theStudent.getLast_name());
			myStmt.setString(3,theStudent.getEmail());
			
			//Step 3:execute sql query
			myStmt.execute();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			close(myConn,myStmt,null);
		}
		
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRes) {
		// TODO Auto-generated method stub
		try {
			if(myRes != null) {
				myRes.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myConn != null) {
				myConn.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Student getStudent(String theStudentId) {
		// TODO Auto-generated method stub
		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;
		int studentId;
		try {
			//convert student id to integer
			studentId = Integer.parseInt(theStudentId);
			
			//get connection to database
			myConn = dataSource.getConnection();
			
			//create sql to get selected student
			String sql = "select * from student where id = ?";
			
			//create prepared statment
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, studentId);
			
			//execute statement
			myRes = myStmt.executeQuery();
			
			//retrieve data from result set
			if(myRes.next()) {
				
				String firstName = myRes.getString("first_name");
				String lastName = myRes.getString("last_name");
				String email = myRes.getString("email");
				
				//use the student id during consturction
				theStudent = new Student(studentId, firstName, lastName, email);
				
				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			close(myConn,myStmt,myRes);
		}
		
		return theStudent;

	}
	
	public void updateStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			
			//get connection to database
			myConn = dataSource.getConnection();
			
			//create sql to get selected student
			String sql = "update student set first_name=?, last_name=?, email=? where id = ?";
			
			//create prepared statment
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, theStudent.getFirst_name());
			myStmt.setString(2, theStudent.getLast_name());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			//execute statement
			myStmt.execute();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			close(myConn,myStmt,null);
		}		
	}

	public void deleteStudent(int theStudentId) {
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			
			//get connection to database
			myConn = dataSource.getConnection();
			
			//create sql to delete selected student
			String sql = "delete from student where id = ?";
			
			//create prepared statment
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, theStudentId);
			
			//execute statement
			myStmt.execute();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			close(myConn,myStmt,null);
		}		
	}

}
