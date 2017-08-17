package com.example.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.example.model.Student;

public class StudentDaoImpl implements StudentDao {

	private final static String CREATE = "INSERT INTO students.student (firstname, lastname, city, birthday) "
			+ "VALUES(:firstName, :lastName, :city, :birthDate);";
	private final static String READ = "SELECT firstname, lastname, city, birthday FROM students.student "
			+ "WHERE roll_no=:rollNo;";
	private final static String UPDATE = "UPDATE students.student "
			+ "SET firstname=:firstName, lastname=:lastName, city=:city, birthday=:birthDate WHERE roll_no=:rollNo;";
	private final static String DELETE = "DELETE FROM students.student "
			+ "WHERE roll_no=:rollNo AND firstname=:firstName AND lastname=:lastName AND city=:city AND birthday=:birthDate;";
	private final static String READ_STUDENTS = "SELECT roll_no, firstname, lastname, city, birthday FROM students.student";
	
	private NamedParameterJdbcTemplate template;
	
	public StudentDaoImpl(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public void addStudent(Student student) {
		BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(student);
		template.update(CREATE, beanParamSource);
	}

	@Override
	public Student readStudent(int rollNo) {
		Student student = null;
		List<Student> students = template.query(READ, BeanPropertyRowMapper.newInstance(Student.class));
		if(students.get(0) != null) {
			student = students.get(0); 
		}
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
