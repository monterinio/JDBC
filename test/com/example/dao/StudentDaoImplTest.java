package com.example.dao;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyObject;
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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.model.Student;
import com.mysql.jdbc.PreparedStatement;

@RunWith(MockitoJUnitRunner.class)
public class StudentDaoImplTest {

	@Mock NamedParameterJdbcTemplate mockTemplate;
	@Mock DataSource mockDataSource;
	@Mock BeanPropertySqlParameterSource sqlParameterSource;
	
	private Student testStudent;
	private StudentDaoImpl dao;
	private static int ONE = 1;
	
	private final static String CREATE = "INSERT INTO students.student (firstname, lastname, city, birthday) "
			+ "VALUES(:firstName, :lastName, :city, :birthDate);";
	
	@Before
	public void setUp() throws SQLException {
		//mockDataSource must be not null; otherwise test fails
		assertNotNull(mockDataSource);
		testStudent = new Student(1,"testName","testLastName","testCity","2000-01-01");
		dao = new StudentDaoImpl(mockDataSource);
		
		when(mockTemplate.update(CREATE, sqlParameterSource));
		
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
