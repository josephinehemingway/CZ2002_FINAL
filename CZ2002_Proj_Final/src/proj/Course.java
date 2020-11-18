package proj;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable{
	private String courseName;
	private String courseID;
	
//Create new course object with below attributes
	public Course(String courseName, String courseID) {
		this.courseName = courseName;
		this.courseID = courseID;
		}

// Get ID of course
	public String getCourseID() {
		return courseID;
	}
	
// Get Name of course
	public String getCourseName() {
		return courseName;
	}
	
// Changes ID of course
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
// Changes Name of course
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String toString() {
		return courseName + " " + courseID;
	}

	
}