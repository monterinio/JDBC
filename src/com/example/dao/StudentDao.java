package com.example.dao;

import java.util.List;

import com.example.model.Student;

public interface StudentDao {
	public void addStudent(Student student);
	public Student readStudent(int rollNo);
	public List<Student> readStudents();
	public int updateStudent(Student student);
	public int deleteStudent(Student student);
}
