package proj;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Student implements Serializable{
	private String username;
	private String password;
	private String name;
	private char gender;
	private String nationality;
	private String matricID;
	private String email;
	private String schoolID;
	private ArrayList<CourseIndex> courseIndexArr;
	private int AcadUnits;

	public Student(String sUser, String sPW, String sName, char sGender, String sNationality, 
			String sMatricID, String sEmail, String sSchoolID) {
		this.username = sUser;
		this.password = sPW;
		this.name = sName;
		this.gender = sGender;
		this.nationality = sNationality;
		this.matricID = sMatricID;
		this.email = sEmail;
		this.schoolID = sSchoolID;
		courseIndexArr = null;
		this.AcadUnits=0;

	}
	
	
	public int getAcadunits() {
		return this.AcadUnits;
	}
	public void addAcadunits() {
		this.AcadUnits +=3;
	}
	public void dropAcadunits() {
		this.AcadUnits -=3;
	}
	public ArrayList<CourseIndex> getCourseIndexArr() {
		if (courseIndexArr == null) {
			courseIndexArr = new ArrayList<CourseIndex>();
		}
		return courseIndexArr;
	}

	public void setCourseIndexArr(ArrayList<CourseIndex> courseIndexArr) {
		this.courseIndexArr = courseIndexArr;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public char getGender() {
		return gender;
	}
	
	public String getNationality() {
		return nationality;
	}

	public String getMatricID() {
		return matricID;
	}

	public String getEmail() {
		return email;
	}
	
	public String getSchool() {
		return schoolID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setMatricID(String matricID) {
		this.matricID = matricID;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSchool(String school) {
		this.schoolID = school;
	}

	public String toString() {
		return "Student Username: " + username + "\nStudent Name: " + name 
				+ "\nMatriculation ID: " + matricID + "\nEmail: " + email 
				+ "\nGender: " + gender + "\nNationality: " + nationality
				+ "\nSchool: " + schoolID;
	}

}
