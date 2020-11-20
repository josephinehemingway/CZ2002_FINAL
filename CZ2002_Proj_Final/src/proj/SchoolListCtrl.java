package proj;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Represents the control for schoolList which holds an array list of Schools.
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 */
public class SchoolListCtrl {
	/**
	 * ArrayList that stores list of Students.
	 */
	private ArrayList<School> schoolList;
	/**
	 * Scanner to read admin user input
	 */
	public static final Scanner sc = new Scanner(System.in);
	/**
	 * Filename of the school list to be read from SerializeDB
	 */
	private String filename = "SchoolList.txt"; 
	/**
	 * Reads in the list of schools from serializeDB.
	 */
	@SuppressWarnings("unchecked")
	public SchoolListCtrl() {
		try {
			schoolList = (ArrayList<School>) SerializeDB.readSerializedObject(filename);

		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
	/**
	 * Gets the school list stored in this schoolListControl Class.
	 * 
	 * @return ArrayList of schools
	 */
	public ArrayList<School> getSchoolList() {
		if (schoolList == null) {
			schoolList = new ArrayList<School>();
		}
		return schoolList;
	}

	/**
	 * Gets a school from this control class's studentList indexed by the parameter i.
	 * @param i index of the school in the schoolList
	 * @return a School object
	 */
	public School getSchool(int i) {
		return (School) schoolList.get(i);
	}
	/**
	 * Checks if the schoolID the admin has inputted is valid by checking the schoolList ArrayList. 
	 * @param schoolID The schoolID of the school.
	 * @return true if the school exists, false if it does not exist.
	 */
	public boolean checkSchool(String schoolID) {
		for (int i = 0; i < schoolList.size(); i++) {
			School sch = schoolList.get(i);

			if (sch.getSchoolID().equals(schoolID)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Gets the number of schools stored in the schoolList ArrayList.
	 * 
	 * @return The size of the schoolList ArrayList which is the number of schools.
	 */
	public int getSchoolSize() {
		return schoolList.size();
	}
	/**
	 * Allows admin to choose a valid school to set or change.
	 * 
	 * @return A valid schoolID of the chosen school the admin user has inputted.
	 */
	public String chooseSchool() {
		String schoolChoice = null;
		boolean k = true;

		while (k) {
			try {
				int count = 1;
				schoolChoice = sc.nextLine().toUpperCase();
				for (int i = 0; i < schoolList.size(); i++) {
					if (schoolList.get(i).getSchoolID().equals(schoolChoice)) {
						k = false;
						break;
					} else if ((count) == schoolList.size()) {
						System.out.println("Please re-enter valid schoolID");

					} else {
						(count)++;
					}
				}
			} catch (InputMismatchException e) {
				System.out.print("Please re-enter valid schoolID");
			}
		}

		return schoolChoice;
	}
	/**
	 *Gets the AccessPeriod of a school.
	 *
	 * @param schoolID The schoolID of the school we are getting AccessPeriod from.
	 */
	public void getCurrentAccessPeriod(String schoolID) {
		for (int i = 0; i < schoolList.size(); i++) {
			School sch = schoolList.get(i);

			if (sch.getSchoolID().equals(schoolID)) {
				if (sch.getStartDate() != null && sch.getStartDate() != null) {
					System.out.println("\nCurrent Access Period for: " + sch.getName());
					System.out.println("Start Date: " + sch.getStartDate());
					System.out.println("End Date: " + sch.getEndDate());
				} else
					System.out.println("Access Period not yet set.");
				return;
			}
		}
		System.out.println("School not found.");
		return;
	}
	/**
	 * Allows the admin to edit the AccessPeriod of a school by inputting the schoolID of the school and the respect start and end dates.
	 * 
	 * @param schoolID The schoolID of the school whose AccessPeriod is to be edited.
	 * @param startDate The start Date of the new AccessPeriod.
	 * @param endDate The end Date of the new AccessPeriod.
	 * @throws ParseException 
	 */
	public void editAccessPeriod(String schoolID, Date startDate, Date endDate) throws ParseException {

		for (int i = 0; i < schoolList.size(); i++) {
			School sch = schoolList.get(i);
			if (sch.getSchoolID().equals(schoolID)) {

				sch.setStartDate(startDate);
				sch.setEndDate(endDate);

				System.out.println("\nAccess Period edited. ");
				System.out.println("\nNew Access Period for: " + sch.getName());
				System.out.println("Start Date: " + sch.getStartDate());
				System.out.println("End Date: " + sch.getEndDate());
				save();
				return;
			}
		}
		System.out.println("School not found.");
	}
	/**
	 * Allows admin to add a course to a selected School.
	 * 
	 * @param schoolID The schoolID of the school the admin wants to put the course in.
	 * @param selectedCourse The course object the admin is assigning the school to.
	 */
	public void addCourseToSchool(String schoolID, Course selectedCourse) {
		// check if course is already in that school
		// add course into school
		for (int j = 0; j < schoolList.size(); j++) {
			if (schoolList.get(j).getSchoolID().equals(schoolID)) {
				if (schoolList.get(j).getCourse().contains(selectedCourse)) {
					System.out.println("Course already belongs to this school");
					break;
				} else {
					schoolList.get(j).addCourse(selectedCourse);
					System.out.println("Course is now in " + schoolList.get(j));
				}
			}

		}

	}
	/**
	 * Allows admin to remove the selected Course from its school.
	 * 
	 * @param schoolID The schoolID of the school
	 * @param selectedCourse The course object that we wish to remove from school.
	 */
	public void removeCourseFromSchool(String schoolID, Course selectedCourse) {
		for (int j = 0; j < schoolList.size(); j++) {
			if (schoolList.get(j).getSchoolID().equals(schoolID)) {
				if (schoolList.get(j).getCourse().contains(selectedCourse)) {
					schoolList.get(j).getCourse().remove(selectedCourse);
					System.out.println("Course is removed from " + schoolList.get(j).getSchoolID());
					break;
				}

			}

		}

	}
	/**
	 * Gets the school that the course belongs to.
	 * @param courseID The courseID of the course.
	 * @return The schoolID the course belongs to.
	 */
	public String getSchoolFromCourse(String courseID) {
		for (int i=0; i<schoolList.size();i++) {
			if ( schoolList.get(i).checkCourseUnderSch(courseID)==1) {
				return schoolList.get(i).getSchoolID();
			}
		}
		return null;
	}
	/**
	 * Prints the list of schools under schoolList ArrayList.
	 */
	public void printSchoolList() {
		System.out.println("List of Schools:");
		for (int i = 0; i < schoolList.size(); i++) {
			System.out.println(schoolList.get(i).getSchoolID());
		}
	}
	/**
	 * When called, save the current edited schoolList object to SerializeDB.
	 */
	public void save() { // save the user list back into the file storing student data
		try {
			SerializeDB.writeSerializedObject(filename, schoolList);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}

}
