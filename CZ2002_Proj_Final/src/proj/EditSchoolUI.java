package proj;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Admin sub console that contains methods to edit the courses under a school
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 */
public class EditSchoolUI {
	/**
	 * Scanner to scan user's input.
	 */
	public static final Scanner sc= new Scanner(System.in);
	/**
	 * Allows admin to add an existing course object into the school's Course ArrayList.
	 * 
	 * @param schoolListControl schoolListControl object that holds the school list and their details.
	 * @param chosenSchoolID The schoolID of the school admin has chosen.
	 * @param courseListControl courseListControl object that holds the course list and their deatils
	 * @param courseID The courseId of the course which we want to add into the school.
	 */
	public static void addCourseToSchool(SchoolListCtrl schoolListControl,String chosenSchoolID,CourseListCtrl courseListControl, String courseID) {
		//get the course object
		System.out.println("\nAdding Course to school ------------------");
		for(int i=0; i< courseListControl.getCourseSize();i++) {
			if (courseListControl.getCourse(i).getCourseID().equals(courseID)) {
				Course selectedCourse= courseListControl.getCourse(i);
				schoolListControl.addCourseToSchool(chosenSchoolID, selectedCourse);
			}
		}	
	}
	/**
	 * Allows admin to remove course object from school's ArrayList of Courses.
	 * 
	 * @param schoolListControl schoolListControl object that holds the school list and their details.
	 * @param courseListControl courseListControl object that olds the course list and their details.
	 * @param courseID The courseId of the course Object to which admin is trying to remove from te school.
	 */
	public static void removeCourseFromSchool(SchoolListCtrl schoolListControl,CourseListCtrl courseListControl, String courseID) {
		System.out.println("\nRemoving Course from school ------------------");
		for(int i=0; i< courseListControl.getCourseSize();i++) {
			if (courseListControl.getCourse(i).getCourseID().equals(courseID)) {
				Course selectedCourse= courseListControl.getCourse(i);
				String chosenSchoolID= schoolListControl.getSchoolFromCourse(courseID);
				System.out.println("Current School:" + chosenSchoolID);
				schoolListControl.removeCourseFromSchool(chosenSchoolID, selectedCourse);
			}
		}
	}
}
	
