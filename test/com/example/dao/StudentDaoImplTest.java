package com.example.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.example.model.Student;
import com.mysql.jdbc.PreparedStatement;

@RunWith(MockitoJUnitRunner.class)
public class StudentDaoImplTest {

	@Mock DataSource mockDataSource;
	@Mock Connection mockConn;
	@Mock PreparedStatement mockPreparedStmt;
	@Mock ResultSet mockResultSet;
	@Mock Statement mockStatement;
	
	private Student testStudent;
	private StudentDaoImpl dao;
	private static int ONE = 1;
	
	@Before
	public void setUp() throws SQLException {
		//mockDataSource must be not null; otherwise test fails
		assertNotNull(mockDataSource);
		testStudent = new Student(1,"testName","testLastName","testCity","2000-01-01");
		dao = new StudentDaoImpl(mockDataSource);
		
		when(mockDataSource.getConnection()).thenReturn(mockConn);
		when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmt);
		when(mockConn.createStatement()).thenReturn(mockStatement);
		when(mockPreparedStmt.executeQuery()).thenReturn(mockResultSet);
		when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
		when(mockPreparedStmt.executeUpdate()).thenReturn(ONE);
		
		//Chained because of ReadStudents() method
		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
		when(mockResultSet.getInt("roll_no")).thenReturn(1);
		when(mockResultSet.getString("firstname")).thenReturn(testStudent.getFirstName());
		when(mockResultSet.getString("lastname")).thenReturn(testStudent.getLastName());
		when(mockResultSet.getString("city")).thenReturn(testStudent.getCity());
		when(mockResultSet.getDate("birthday")).thenReturn(testStudent.getBirthDay());
		
	}
	
	@Test
	public void TestAddStudent() throws SQLException {
		dao.addStudent(testStudent);
	}
	
	@Test
	public void TestReadStudent() {
		Student readStudent = dao.readStudent(1);
		assertEquals(testStudent, readStudent);
	}
	
	@Test
	public void TestCreateAndReadAllStudents() {
		List<Student> readStudents = dao.readStudents();
		List<Student> expectedStudents = new ArrayList<>();
		expectedStudents.add(testStudent);
		assertEquals(expectedStudents, readStudents);
	}
	
	@Test
	public void TestUpdateStudent() {
		int updatedStatus = dao.updateStudent(testStudent);
		assertEquals(ONE, updatedStatus);
	}
	
	@Test
	public void TestDeleteStudent() {
		int deletedStatus = dao.deleteStudent(testStudent);
		assertEquals(ONE, deletedStatus);
	}

}
