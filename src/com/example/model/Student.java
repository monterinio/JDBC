package com.example.model;

import java.sql.Date;

public class Student {
	private int rollNo;
	private String firstName;
	private String lastName;
	private String city;
	private Date birthDate;
	
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Student() { }
	public Student(int rollNo, String firstName, String lastName, String city, String birthDate) {
		super();
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.birthDate = java.sql.Date.valueOf(birthDate);
	}
	public Student(int rollNo, String firstName, String lastName, String city, java.sql.Date birthDate) {
		super();
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.birthDate = birthDate;
	}
}
