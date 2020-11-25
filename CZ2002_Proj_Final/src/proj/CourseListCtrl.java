package proj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Represents the control class for courseList which holds an array list of Courses.
 * @author DSAI/1 Group 5
 * @version 1.0
 * @vsince 2020-11-19
 *
 */
public class CourseListCtrl {
	/**
	 * ArrayList that stores list of Courses.
	 */
	private static ArrayList<Course> courseList;
	/**
	 * Filename of course list to be read from SerializeDB
	 */
	private String filename = "CourseList.txt";
	/**
	 * Scanner to scan user's input.
	 */
	public static final Scanner sc = new Scanner(System.in);
	/**
	 * Reads in the list of courses from SerializeDB
	 */
	@SuppressWarnings("unchecked")
	public CourseListCtrl() {
		try {
			courseList = (ArrayList<Course>) SerializeDB.readSerializedObject(filename);
		} catch (Exception e) {
			System.out.println("Exception >>" + e.getMessage());
		}
	}
	/**
	 * Gets the course list stored in this courseListControl class
	 * 
	 * @return ArrayList of courses
	 */
	public ArrayList<Course> getCourseList() {
		if (courseList == null) {
			courseList = new ArrayList<Course>();
		}
		return courseList;
	}
	/**
	 * Sets the courseList
	 * 
	 * @param CourseList ArrayList of Courses
	 */
	public void setCourseList(ArrayList<Course> CourseList) {
		Collections.copy(CourseListCtrl.courseList, courseList);
	}
	/**
	 * Gets course object from courseList ArrayList by parameter index.
	 * 
	 * @param index The parameter index
	 * @return The course object of the selected index.
	 */
	public Course getCourse(int index) {
		return (Course) courseList.get(index);
	}
	/**
	 * Allows admin user to add new courses to courseListControl ArrayList.
	 * 
	 * @param courseName The course Name of the new course.
	 * @param courseID The course ID of the new course.
	 * @return 0 when the course is successfully added, -1 when its not.
	 */
	public int addNewCourse(String courseName, String courseID) {
		for (int i = 0; i < courseList.size(); i++) {
			if (courseList.get(i).getCourseName().equals(courseName)) {
				System.out.println("Course already exists.");
				return -1;
			} else if (courseList.get(i).getCourseID().equals(courseID)) {
				System.out.println("Course ID already exists.");
				return -1;
			}
		}
		Course c1 = new Course(courseName, courseID);
		courseList.add(c1);
		return 0;
	}
	/**
	 * Allows admin user to edit Course stored in the courseList ArrayList
	 * 
	 * @param choice 1= editing course Name , 2= editing course ID
	 * @param courseIdChoice The index parameters admin have chosen.
	 * @param edited The new courseName/courseID.
	 * @return the initial course Name/ course ID of the course.
	 */
	public String editCourse(int choice, int courseIdChoice, String edited) {
		switch (choice) {
		case 1:
			String initialCourseName = courseList.get(courseIdChoice - 1).getCourseName();
			courseList.get(courseIdChoice - 1).setCourseName(edited);
			return initialCourseName;
		case 2:
			String initialCourseID = courseList.get(courseIdChoice - 1).getCourseID();
			courseList.get(courseIdChoice - 1).setCourseID(edited);
			return initialCourseID;
		default:
			return "Error";
		}
	}
	/**
	 * Allows users to choose a valid Course in the courseList.
	 * 
	 * @return The courseID of the valid course.
	 */
	public String chooseCourse() {
		Scanner sc = new Scanner(System.in);
		String courseChoice = null;
		boolean k = true;

		while (k) {
			try {
				int count = 1;
				courseChoice = sc.nextLine().toUpperCase();
				for (int i = 0; i < courseList.size(); i++) {
					if (courseList.get(i).getCourseID().equals(courseChoice)) {
						k = false;
						break;
					} else if ((count) == courseList.size()) {
						System.out.println("Please re-enter valid course choice");
					} else {
						(count)++;
					}
				}
			} catch (InputMismatchException e) {
				System.out.print("Please re-enter valid course choice");
				sc.nextLine();
			}
		}

		return courseChoice;
	}
	/**
	 * Allows admin to delete course from courseList.
	 * 
	 * @param choice The index parameter of courseList.
	 */
	public void deleteCourse(int choice) {
		courseList.remove(choice);
		return;
	}
	/**
	 * Gets the number of courses in courseList ArrayList.
	 * 
	 * @return The number of courses.
	 */
	public int getCourseSize() {
		return courseList.size();
	}
	/**
	 * Prints a course in course List and their details.
	 * @param i index of the course in courseList ArrayList.
	 */
	public static void printCourseDetails(int i) {
		if (courseList == null) {
			courseList = new ArrayList<Course>();
		} else if (courseList.isEmpty() == true) {
			System.out.println("There are no courses available." );
			return;
		}
		else {
				Course c = courseList.get(i);
				System.out.println("   " + (i + 1)+ "	" + c.getCourseID()
				+ "       " + c.getCourseName());
			}
	}
	/**
	 * Prints all courses in course List ArrayList and their details
	 */
	public void printAllCourseDetails() {
		if (courseList == null) {
			courseList = new ArrayList<Course>();
			
		} else if (courseList.isEmpty() == true) {
			System.out.println("There are no courses available." );
			return;
		}
		else {
			System.out.println("\nList of Courses ------------------");
			System.out.println("\n   #	Course ID    Course Name ");
			System.out.println("===============================================================");
			for (int i=0; i<courseList.size(); i++) {
				printCourseDetails(i);
			}
		}
	}
	/**
	 * When called, save the current edited curseList object to SerializeDB.
	 */
	public void save() {
		try {
			SerializeDB.writeSerializedObject(filename, courseList);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}

	}

}
