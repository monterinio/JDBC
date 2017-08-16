package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.DBConnection;
import com.example.dao.StudentDao;
import com.example.dao.StudentDaoImpl;
import com.example.model.Student;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
	

	public void init() {
		studentDao = new StudentDaoImpl(DBConnection.getDataSource());
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = null;
		String option =  request.getParameter("action");
		int rollNo =  Integer.parseInt(request.getParameter("rollNo"));
		String firstName =  request.getParameter("firstName");
		String lastName =  request.getParameter("lastName");
		String city = request.getParameter("city");
		String bDate = request.getParameter("birthDay");
		
		if("create".equals(option)) {
			Student student = new Student(rollNo, firstName, lastName, city, bDate);
			studentDao.addStudent(student);
			message = "Do bazy dodano studenta (" + rollNo + "," + firstName + "," + lastName + "," + city + "," + bDate + ");";
			request.setAttribute("message", message);
			request.getRequestDispatcher("addResult.jsp").forward(request,response);
		} else if("read".equals(option)) {
			Student student = studentDao.readStudent(rollNo);
			request.setAttribute("student", student);
			request.getRequestDispatcher("readResult.jsp").forward(request, response);
		} else if("readAll".equals(option)) {
			List<Student> students = studentDao.readStudents();
			request.setAttribute("students", students);
			request.getRequestDispatcher("readResults.jsp").forward(request, response);
		} else if("update".equals(option)) {
			Student student = new Student(rollNo, firstName, lastName, city, bDate);
			int result = studentDao.updateStudent(student);
			request.setAttribute("result", result);
			request.getRequestDispatcher("update.jsp").forward(request, response);
		} else if("delete".equals(option)) {
			Student student = new Student(rollNo, firstName, lastName, city, bDate);
			int result = studentDao.deleteStudent(student);
			request.setAttribute("result", result);
			request.getRequestDispatcher("update.jsp").forward(request, response);
		}
	}
	
	
}
