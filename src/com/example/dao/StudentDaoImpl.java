package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.model.Student;

public class StudentDaoImpl implements StudentDao {

	private final static String CREATE = "INSERT INTO students.student (firstname, lastname, city, birthday) "
			+ "VALUES(:firstName, :lastName, :city, :birthDay);";
	private final static String READ = "SELECT firstname, lastname, city, birthday FROM students.student "
			+ "WHERE roll_no=1;";
	private final static String UPDATE = "UPDATE students.student "
			+ "SET firstname=:firstName, lastname=:lastName, city=:city, birthday=:birthDay WHERE roll_no=:rollNo;";
	private final static String DELETE = "DELETE FROM students.student "
			+ "WHERE roll_no=:rollNo AND firstname=:firstName AND lastname=:lastName AND city=:city AND birthday=:birthDay;";
	private final static String READ_STUDENTS = "SELECT roll_no, firstname, lastname, city, birthday FROM students.student";
	
	private NamedParameterJdbcTemplate template;
	
	public StudentDaoImpl(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public int addStudent(Student student) {
		BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(student);
		return template.update(CREATE, beanParamSource);
	}

	@Override
	public Student readStudent(int rollNo) {
		SqlParameterSource parameters = new MapSqlParameterSource("rollNumber", rollNo);
		Student student = template.queryForObject(READ, parameters, new RowMapper<Student>() {
		    @Override
		    public Student mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		    	return new Student(
		    			1,
		    			resultSet.getString("firstname"),
		    			resultSet.getString("lastname"),
		    			resultSet.getString("city"),
		    			resultSet.getDate("birthday"));
		    }
		});
		return student;
	}
	

	@Override
	public List<Student> readStudents() {
		List<Student> students = template.query(READ_STUDENTS, BeanPropertyRowMapper.newInstance(Student.class));
		return students;
	}

	@Override
	public int updateStudent(Student student) {
		BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(student);
		int result = template.update(UPDATE, beanParamSource);
		return result;
	}

	@Override
	public int deleteStudent(Student student) {
		BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(student);
		int result = template.update(DELETE, beanParamSource);
		return result;
	}
}
