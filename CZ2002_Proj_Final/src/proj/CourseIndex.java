package proj;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * 
 * @author DSAI/1 GROUP 5
 * @version 1.0
 * @since 2020-11-19
 *
 */

public class CourseIndex extends Course implements Serializable {
	@SuppressWarnings("unused")
	/**
	 * Course name of a course
	 */
	private String courseName;
	/**
	 * Index ID of a course
	 */
	private int indexID;
	/**
	 * Course ID of a course
	 */
	private String courseID;
	/**
	 * Schedule of a course
	 */
	private Schedule schedule;
	/**
	 * Number of Vacancies in a course
	 */
	private int numOfVacancies;
	/**
	 * ArrayList of Students in a course
	 */
	private ArrayList<Student> student;
	/**
	 * ArrayList of students in a waiting list of a course
	 */
	private ArrayList<Student> waitingList;
	/**
	 * Current Vacancy of a course
	 */
	private int currentVacancy;
	/**
	 * Academic units for a course
	 */
	final static int ACADEMIC_UNITS= 3;
	/** 
	 * Creates a new course object with the below attributes
	 * 
	 * @param courseName This course's name
	 * @param courseID This course's ID
	 * @param indexID This course's indexID
	 * @param schedule This course's schedule
	 * @param numOfVacancies This course's number of vacancies
	 */

	public CourseIndex(String courseName, String courseID, int indexID, Schedule schedule, int numOfVacancies) {
		super(courseName, courseID);
		this.courseName = courseName;
		this.indexID = indexID;
		this.courseID = courseID;
		this.schedule = schedule;
		this.numOfVacancies = numOfVacancies;
		this.student =null;
		this.waitingList = null;
		this.currentVacancy= numOfVacancies;
		
	}
	/**
	 * Gets course name of this Course
	 * 
	 * @return this Course's Name
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * Sets this Course's Name
	 * 
	 * @param courseName This Course's new course name.
	 */
	public void setCourseName(String courseName) {
		this.courseName= courseName;
	}
	/**
	 * Gets Index ID of this Course
	 * 
	 * @return this Course's Index ID.
	 */
	public int getIndexID() {
		return indexID;
	}
	/**
	 * Sets the Index ID
	 * 
	 * @param indexID This Course's new index ID.
	 */
	public void setIndexID(int indexID) {
		this.indexID = indexID;
	}
	/**
	 * Gets Course ID of this Course
	 * 
	 * @return this Course's Index ID.
	 */
	public String getCourseID() {
		return courseID;
	}
	/**
	 * Sets the Course ID
	 * 
	 * @param courseID This Course's new course ID
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	/**
	 * Gets Schedule of this Course
	 * 
	 * @return this Course's Schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	/**
	 * Sets the Course Schedule
	 * 
	 * @param schedule This Course's new course schedule
	 */
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	/**
	 * Gets Number of Vacancies of this Course
	 * 
	 * @return this Course's Number of Vacancies
	 */
	public int getNumOfVacancies() {
		return numOfVacancies;
	}
	/**
	 * Sets the Number of Vacancies
	 * 
	 * @param numOfVacancies This Course's new number of vacancies
	 */
	public void setNumOfVacancies(int numOfVacancies) {
		this.numOfVacancies = numOfVacancies;
	}
	/**
	 * Gets Current Number of Vacancies of this Course
	 * 
	 * @return this Course's Current Number of Vacancies
	 */
	public int getCurrentVacancy(){
		return currentVacancy;
	}
	/**
	 * Sets the Current Number of Vacancies
	 * 
	 * @param currentVacancy This Course's new current number of vacancies
	 */
	public void setCurrentVacancy(int currentVacancy) {
		this.currentVacancy = currentVacancy;
	}
	/**
	 * Minus the number of vacancies of this course.
	 */
	public void minusVacancy(){
		this.currentVacancy -= 1;
	}
	/**
	 * Add the number of vacancies of this course.
	 */
	public void addToVacancy() {
		this.currentVacancy += 1;
	}
	/**
	 * Gets Current Vacancy over the Initial Vacancy of this Course.
	 * 
	 * @return this Course' vacancy in the format of Current Vacancy / Total Number of Vacancies
	 */
	public String getCurrentVacancyOverInitial() {
		return this.currentVacancy + "/" + this.numOfVacancies;
	}
	/**
	 * Gets ArrayList of Students 
	 * 
	 * @return This course's ArrayList of Students
	 */
	public ArrayList<Student> getStudent() {
		if (this.student == null) {
			this.student = new ArrayList<Student>();
		}
		return this.student;
	}
	/**
	 * Sets this course's ArrayList of Students
	 * 
	 * @param student This Course's ArrayList of students. 
	 */
	public void setStudent(ArrayList<Student> student) {
		this.student = student;
	}
	/**
	 * Gets ArrayList of Students in this Course
	 * 
	 * @return This course's ArrayList of Students in a waiting list
	 */
	public ArrayList<Student> getWaitingList() {
		if (this.waitingList == null) {
			this.waitingList = new ArrayList<Student>();
		}
		return this.waitingList;
	}
	/**
	 * Sets this course's ArrayList of Students 
	 * 
	 * @param waitingList This Course's ArrayList of students in a waiting list
	 */
	public void setWaitingList(ArrayList<Student> waitingList) {
		this.waitingList = waitingList;
	}
	/**
	 * Returns the string representation of course and its details
	 */
	public String toString() {
		return "CourseID: " + courseID + "\nIndexID: " + indexID + "\nNumberofVacancies: " + numOfVacancies + "\n"
				+ schedule;
	}


}
