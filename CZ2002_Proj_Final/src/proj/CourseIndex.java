package proj;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class CourseIndex extends Course implements Serializable {
	// index no.
	@SuppressWarnings("unused")
	private String courseName;
	private int indexID;
	private String courseID;
	private Schedule schedule;
	private int numOfVacancies;
	private ArrayList<Student> student;
	private ArrayList<Student> waitingList;
	private int currentVacancy;
	final static int ACADEMIC_UNITS= 3;
	// Array of strings that stores type of classes ///

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
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName= courseName;
	}

	public int getIndexID() {
		return indexID;
	}

	public void setIndexID(int indexID) {
		this.indexID = indexID;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getNumOfVacancies() {
		return numOfVacancies;
	}

	public void setNumOfVacancies(int numOfVacancies) {
		this.numOfVacancies = numOfVacancies;
	}

	public int getCurrentVacancy(){
		return currentVacancy;
	}
	
	public void setCurrentVacancy(int currentVacancy) {
		this.currentVacancy = currentVacancy;
	}
	
	public void minusVacancy(){
		this.currentVacancy -= 1;
	}
	
	public void addToVacancy() {
		this.currentVacancy += 1;
	}
	
	public String getCurrentVacancyOverInitial() {
		return this.currentVacancy + "/" + this.numOfVacancies;
	}
	
	public ArrayList<Student> getStudent() {
		if (this.student == null) {
			this.student = new ArrayList<Student>();
		}
		else if ( this.student.isEmpty()) {
			System.out.println("There are no students registered under this index");
		}
		return this.student;
	}

	public void setStudent(ArrayList<Student> student) {
		this.student = student;
	}

	public ArrayList<Student> getWaitingList() {
		if (this.waitingList == null) {
			this.waitingList = new ArrayList<Student>();
		}
		return this.waitingList;
	}

	public void setWaitingList(ArrayList<Student> waitingList) {
		this.waitingList = waitingList;
	}
	
	
	
	public String toString() {
		return "CourseID: " + courseID + "\nIndexID: " + indexID + "\nNumberofVacancies: " + numOfVacancies + "\n"
				+ schedule;
	}


}
