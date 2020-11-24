package proj;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 
 * @author DSAI/1 GROUP 5
 * @version 1.0
 * @since 2020-11-19
 *
 */
public class School implements Serializable {
	/**
	 * ArrayList of Courses in a school
	 */
	private ArrayList<Course> course;
	/**
	 * 
	 */
	private ArrayList<Student> student;
	private String schoolID;
	private String name;
	private Date startDate;
	private Date endDate;
	private Date now;

//	private AccessPeriod accessPeriod;

	public School(String schoolID, String name, ArrayList<Course> course, Date startDate, Date endDate) {
		this.schoolID = schoolID;
		this.name = name;
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
		this.course = new ArrayList<Course>();
		this.student = new ArrayList<Student>();
	}

	// method to add course to the school
	public void addCourse(Course cs) {
		if (this.course == null) {
			this.course = new ArrayList<Course>();
			this.course.add(cs);
		} else {
			this.course.add(cs);
		}
	}

	public int checkCourseUnderSch(String courseID) {
		for (int i = 0; i < course.size(); i++) {
			if (course.get(i).getCourseID().equals(courseID)) {
				return 1;
			}
		}
		return 0;
	}

	public ArrayList<Course> getCourse() {
		if (course == null) {
			course = new ArrayList<Course>();
		}
		return course;
	}

	public ArrayList<Student> getStudentList() {
		if (student == null) {
			student = new ArrayList<Student>();
		}
		return student;
	}

	public void setCourse(ArrayList<Course> course) {
		this.course = course;
	}

	public String getSchoolID() {
		return this.schoolID;
	}

	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean canAccess() {
		now = new Date();
		return !(now.before(startDate) || now.after(endDate));
	}

	public Date getNow() {
		return now;
	}

	public String toString() { // overriding the toString() method
		return schoolID + " " + name + "\nCourses: " + course + "\nAccess Period: " + startDate + " to " + endDate
				+ "\n";
	}

}
