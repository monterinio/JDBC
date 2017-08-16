package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Student;

public class StudentDaoImpl implements StudentDao {

	
	@Override
	public void addStudent(Student student) {
		PreparedStatement pStmt = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			pStmt = conn.prepareStatement("INSERT INTO students.student (firstname, lastname, city, birthday) VALUES(?,?,?,?);");
			pStmt.setString(1, student.getFirstName());
			pStmt.setString(2, student.getLastName());
			pStmt.setString(3, student.getCity());
			pStmt.setDate(4, student.getBirthDate());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(null, pStmt, conn);
		}
	}

	@Override
	public Student readStudent(int rollNo) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;
		Student student = null;
		try {
			conn = DBConnection.getConnection();
			pStmt = conn.prepareStatement("SELECT firstname, lastname, city, birthday FROM students.student WHERE roll_no=?;");
			pStmt.setInt(1, rollNo);
			rSet = pStmt.executeQuery();
			rSet.next();
			String firstName = rSet.getString("firstname");
			String lastName = rSet.getString("lastname");
			String city = rSet.getString("city");
			java.sql.Date birthDay = rSet.getDate("birthday");
			student = new Student(rollNo, firstName, lastName, city, birthDay);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(rSet, pStmt, conn);
		}
		return student;
	}

	@Override
	public List<Student> readStudents() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rSet = null;
		List<Student> students = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			rSet = stmt.executeQuery("SELECT roll_no, firstname, lastname, city, birthday FROM students.student");
			while(rSet.next()) {
				int rollNo = rSet.getInt("roll_no");
				String firstName = rSet.getString("firstname");
				String lastName = rSet.getString("lastname");
				String city = rSet.getString("city");
				java.sql.Date birthday = rSet.getDate("birthday");
				students.add(new Student(rollNo, firstName, lastName, city, birthday));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(rSet, stmt, conn);
		}
		return students;
	}

	@Override
	public int updateStudent(Student student) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;
		int result = 0;
		try {
			conn = DBConnection.getConnection();
			pStmt = conn.prepareStatement("UPDATE students.student SET firstname=?, lastname=?, city=?, birthday=? WHERE roll_no=?;");
			pStmt.setString(1, student.getFirstName());
			pStmt.setString(2, student.getLastName());
			pStmt.setString(3, student.getCity());
			pStmt.setDate(4, student.getBirthDate());
			pStmt.setInt(5, student.getRollNo());
			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(rSet, pStmt, conn);
		}
		return result;
	}

	@Override
	public int deleteStudent(Student student) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;
		int result = 0;
		try {
			conn = DBConnection.getConnection();
			pStmt = conn.prepareStatement("DELETE FROM students.student WHERE roll_no=? AND firstname=? AND lastname=? AND city=? AND birthday=?;");
			pStmt.setInt(1,student.getRollNo());
			pStmt.setString(2, student.getFirstName());
			pStmt.setString(3, student.getLastName());
			pStmt.setString(4, student.getCity());
			pStmt.setDate(5, student.getBirthDate());
			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(rSet, pStmt, conn);
		}
		
		return result;
	}
	
	private void releaseResources(ResultSet rSet, Statement stmt, Connection conn) {
		try {
			if(rSet != null && !rSet.isClosed()) {
				rSet.close();
			}
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
