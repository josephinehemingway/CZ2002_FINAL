package proj;

import java.util.ArrayList;
import java.util.Scanner;

public class EditSchoolUI {
	public static final Scanner sc= new Scanner(System.in);
	
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
	
