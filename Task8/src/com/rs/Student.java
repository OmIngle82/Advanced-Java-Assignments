package com.rs;

public class Student {
	private int studentId;
	private String name;
	private String email;
	private String phone;
	private int age;
	private int courseId;
	
	public Student(int studentId,String name,String email,String phone,int age,int courseId) {
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	
}
