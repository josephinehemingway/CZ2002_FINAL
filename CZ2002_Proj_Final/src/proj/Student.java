package proj;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Student entity class
 * 
 * @author DSAI/1 GROUP 5
 * @version 1.0
 * @since 2020-11-19
 *
 */
public class Student implements Serializable {
	/**
	 * Account username of a student in the school
	 */
	private String username;
	/**
	 * Account Password of student
	 */
	private String password;
	/**
	 * Name of student
	 */
	private String name;
	/**
	 * Gender of student
	 */
	private char gender;
	/**
	 * Nationality of student
	 */
	private String nationality;
	/**
	 * Matriculation ID of student
	 */
	private String matricID;
	/**
	 * Email of student
	 */
	private String email;
	/**
	 * School ID of the school the student belongs to
	 */
	private String schoolID;
	/**
	 * ArrayList of CourseIndexes student has registered for
	 */
	private ArrayList<CourseIndex> courseIndexArr;
	/**
	 * Total Academic Units Student has accumulated in this semester
	 */
	private int AcadUnits;

	/**
	 * Creates a new student object with the below attributes
	 * 
	 * @param sUser        This student's username
	 * @param sPW          This student's password
	 * @param sName        This student's Name
	 * @param sGender      This student's gender
	 * @param sNationality This student's Nationality
	 * @param sMatricID    This student's Matriculation ID
	 * @param sEmail       This student's email
	 * @param sSchoolID    This student's School's ID
	 */

	public Student(String sUser, String sPW, String sName, char sGender, String sNationality, String sMatricID,
			String sEmail, String sSchoolID) {
		this.username = sUser;
		this.password = sPW;
		this.name = sName;
		this.gender = sGender;
		this.nationality = sNationality;
		this.matricID = sMatricID;
		this.email = sEmail;
		this.schoolID = sSchoolID;
		courseIndexArr = null;
		this.AcadUnits = 0;

	}

	/**
	 * Gets the total accumulated AU of this student
	 * 
	 * @return the total accumulated AU of this student
	 */
	public int getAcadunits() {
		return this.AcadUnits;
	}

	/**
	 * Adds 3 AU to the student when he successfully registers for a course
	 */
	public void addAcadunits() {
		this.AcadUnits += 3;
	}

	/**
	 * Subtract 3 AU from the student's accumulated when he drops a course
	 */
	public void dropAcadunits() {
		this.AcadUnits -= 3;
	}

	/**
	 * Gets the ArrayList of CourseIndexes this student has registered for
	 * 
	 * @return This student's ArrayList of CourseIndexes
	 */
	public ArrayList<CourseIndex> getCourseIndexArr() {
		if (courseIndexArr == null) {
			courseIndexArr = new ArrayList<CourseIndex>();
		}
		return courseIndexArr;
	}

	/**
	 * Sets this student's ArrayList of CourseIndexes
	 * 
	 * @param courseIndexArr This Student's ArrayList of CourseIndexes
	 */
	public void setCourseIndexArr(ArrayList<CourseIndex> courseIndexArr) {
		this.courseIndexArr = courseIndexArr;
	}

	/**
	 * Gets username of this Student
	 * 
	 * @return this Student's Username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets password of this Student
	 * 
	 * @return this Student's Password
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * Gets Name of this Student
	 * 
	 * @return this Student's Name
	 */

	public String getName() {
		return name;
	}

	/**
	 * Gets gender of this Student
	 * 
	 * @return this Student's Gender
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * Gets nationality of this Student
	 * 
	 * @return this Student's Nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Gets Matriculation ID of this Student
	 * 
	 * @return this Student's Matriculation ID
	 */

	public String getMatricID() {
		return matricID;
	}

	/**
	 * Gets Email of this Student
	 * 
	 * @return this Student's Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets School ID of this Student's School
	 * 
	 * @return SchoolID of this Student
	 */
	public String getSchool() {
		return schoolID;
	}

	/**
	 * Sets this Student's Username
	 * 
	 * @param username This Student's new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets this Student's Password
	 * 
	 * @param password This Student's new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets this Student's name
	 * 
	 * @param name This Student's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets this Student's gender
	 * 
	 * @param gender This Student's new gender
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * Sets this Student's nationality
	 * 
	 * @param nationality This Student's new nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Sets this Student's matriculation ID
	 * 
	 * @param matricID This Student's new matriculation ID
	 */
	public void setMatricID(String matricID) {
		this.matricID = matricID;
	}

	/**
	 * Sets this Student's email
	 * 
	 * @param email This Student's new Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets this Student's school
	 * 
	 * @param school this Student's new school
	 */
	public void setSchool(String school) {
		this.schoolID = school;
	}

	/**
	 * Returns the string representation of student and his details.
	 */
	public String toString() {
		return "Student Username: " + username + "\nStudent Name: " + name + "\nMatriculation ID: " + matricID
				+ "\nEmail: " + email + "\nGender: " + gender + "\nNationality: " + nationality + "\nSchool: "
				+ schoolID + "\n";
	}

}
