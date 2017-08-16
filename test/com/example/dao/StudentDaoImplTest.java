package com.example.dao;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	@Before
	public void setUp() throws SQLException {
		StudentDao dao = new StudentDaoImpl();
	}
	
	@Test
	public void TestAddStudentWithNoExceptions() throws SQLException {
		
		Student validStudent = new Student(0,"testName","testLastName","testCity","2000-01-01");
		
		dao.addStudent(validStudent);
		
	}

}
