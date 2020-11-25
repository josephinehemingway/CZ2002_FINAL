package proj;

import java.io.Serializable;
import java.util.*;
/**
 * 
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19 
 * 
 */
public class Course implements Serializable{
	/**
	 * Course name of a course
	 */
	private String courseName;
	
	/**
	 * Course ID of a course
	 */
	private String courseID;
	

	/**
	 * Create a new course object with the below attributes.
	 * 
	 * @param courseName This course's name.
	 * @param courseID	This course's ID.
	 */
	public Course(String courseName, String courseID) {
		this.courseName = courseName;
		this.courseID = courseID;
		}
	
	/**
	 * Gets the course ID of this course.
	 * 
	 * @return the course ID of this course.
	 */
	public String getCourseID() {
		return courseID;
	}
	
	/**
	 * Gets the course name of this course.
	 * 
	 * @return the course name of this course.
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Sets this Course's ID.
	 * 
	 * @param courseID This Course's ID.
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	/**
	 * Sets this Course Name.
	 * 
	 * @param courseName This Course's name.
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Returns the string representation of course and its details.
	 */
	public String toString() {
		return courseName + " " + courseID;
	}

	
}