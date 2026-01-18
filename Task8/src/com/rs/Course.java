package com.rs;

public class Course {
	private int courseId;
	private String courseName;
	private String duration;
	
	public Course(int courseId,String courseName, String duration ) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.duration = duration;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
}
