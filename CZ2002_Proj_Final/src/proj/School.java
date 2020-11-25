package proj;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 *
 */
public class School implements Serializable {
	/**
	 * ArrayList of Courses in school
	 */
	private ArrayList<Course> course;
	/**
	 * School ID of school
	 */
	private String schoolID;
	/**
	 * Name of school
	 */
	private String name;
	/**
	 * Start Date of school's access period
	 */
	private Date startDate;
	/**
	 * End Date of school's access period
	 */
	private Date endDate;
	/**
	 * Current date
	 */
	private Date now;
	/**
	 * 
	 * @param schoolID This school's School ID
	 * @param name This school's Name
	 * @param course This school's courses
	 * @param startDate This school's start date for access period
	 * @param endDate This school's end date for access period
	 */
	public School(String schoolID, String name, ArrayList<Course> course, Date startDate, Date endDate) {
		this.schoolID = schoolID;
		this.name = name;
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
		this.course = new ArrayList<Course>();
	}
	/**
	 * Add course to this school
	 * 
	 * @param cs The course object which the admin wants to add.
	 */
	public void addCourse(Course cs) {
		if (this.course == null) {
			this.course = new ArrayList<Course>();
			this.course.add(cs);
		} else {
			this.course.add(cs);
		}
	}
	/**
	 * Check if the course exists in this school
	 * 
	 * @return 1 if the course exist, 0 if course does not exist.
	 */

	public int checkCourseUnderSch(String courseID) {
		for (int i = 0; i < course.size(); i++) {
			if (course.get(i).getCourseID().equals(courseID)) {
				return 1;
			}
		}
		return 0;
	}
	/**
	 * Gets the ArrayList of Courses in this school
	 * 
	 * @return This school's ArrayList of Courses 
	 */
	public ArrayList<Course> getCourse() {
		if (course == null) {
			course = new ArrayList<Course>();
		}
		return course;
	}
	/**
	 * Sets this school's ArrayList of Courses
	 * 
	 * @param course This School's ArrayList of Courses
	 */
	public void setCourse(ArrayList<Course> course) {
		this.course = course;
	}
	/**
	 * Gets the School ID of this school
	 * 
	 * @return this School's School ID
	 */
	public String getSchoolID() {
		return this.schoolID;
	}
	/**
	 * Sets this School's school ID
	 * 
	 * @param schoolID This School's new school ID
	 */
	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}
	/**
	 * Gets Name of this school
	 * 
	 * @return this School's Name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Sets this School's name
	 * 
	 * @param name This School's new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets Start Date of this school's access period
	 * 
	 * @return this School's Start Date of access period
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	/**
	 * Sets this School's start date of access period
	 * 
	 * @param startDate This School's new end date of access period
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Gets End Date of this school's access period 
	 * 
	 * @return this School's End Date of access period
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	/**
	 * Sets this School's end date of access period
	 * 
	 * @param endDate This School's new start date of access period
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * Checks if the current date is within the access period
	 * 
	 * @return true if the current date is within the access period, false if it is not in the access period
	 */
	public boolean canAccess() {
		now = new Date();
		return !(now.before(startDate) || now.after(endDate));
	}
	/**
	 * Gets current date of this school's access period
	 * 
	 * @return this School's current date 
	 */
	public Date getNow() {
		return now;
	}
	/**
	 * Returns the string representation of school and its details.
	 * @Override
	 */
	public String toString() { // overriding the toString() method
		return schoolID + " " + name + "\nCourses: " + course + "\nAccess Period: " + startDate + " to " + endDate
				+ "\n";
	}

}
