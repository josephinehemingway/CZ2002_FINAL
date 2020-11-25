package proj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * Admin sub console that contains methods to edit course index of a course
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 */
public class EditCourseIndexUI{
	/**
	 * Contains the course name
	 */
	protected static String courseName;
	/**
	 * Contains the course ID
	 */
	protected static String courseID;
	/**
	 * Scanner to read admin user input
	 */
	public static final Scanner sc = new Scanner(System.in);
	/**
	 * Method to set course name and course ID
	 * 
	 * @param courseID The courseID of the courseIndex object
	 * @param courseIndexListControl CourseIndexList control object that holds the courseIndex list and their details.
	 */
	public static void setAttributes(String courseID, CourseIndexListCtrl courseIndexListControl) {
		for (int i = 0; i < courseIndexListControl.getCourseIndexSize(); i++) {
			if (courseIndexListControl.getCourseIndexList().get(i).getCourseID().equals(courseID)) {
				String chosenCourseName = courseIndexListControl.getCourseIndexList().get(i).getCourseName();
				EditCourseIndexUI.courseName = chosenCourseName;
				EditCourseIndexUI.courseID = courseID;
				
			}
		}
	}
	/**
	 * Method that allows admin to add a new course index to a course.
	 * 
	 * @param courseIndexListControl CourseIndexList control object that holds the courseIndex list and their details.
	 */
	public static void addCourseIndex(CourseIndexListCtrl courseIndexListControl) {
		System.out.println("\nEnter the course type (1-3): ");
		System.out.println("1. Lecture, Tutorial, Lab ");
		System.out.println("2. Lecture, Tutorial ");
		System.out.println("3. Lecture ");
		int courseType;
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				courseType = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please enter an integer");
				sc = new Scanner(System.in);
			}
		}
		
		System.out.println("\nEnter the number of Course Indices you wish to input: ");
		int numOfIndexInput;
		while (true) {
			try {
				numOfIndexInput = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please enter an integer");
				sc = new Scanner(System.in);
			}
		}
		
		int count = 0;
		while (count < numOfIndexInput) {
			System.out.println("\nEnter Index ID of index " + (count+1) + ":");
			int newCourseIndex;
			while (true) {
				try {
					newCourseIndex = sc.nextInt();
					break;
				} catch (InputMismatchException e) {
					System.out.println("Please enter an integer");
					sc = new Scanner(System.in);
				}
			}
			
			System.out.println("\nEnter number of vacancies: ");
			int newNumOfVacancies;
			
			while (true) {
				try {
					newNumOfVacancies = sc.nextInt();
					break;
				} catch (InputMismatchException e) {
					System.out.println("Please enter an integer");
					sc = new Scanner(System.in);
				}
			}

			Random r = new Random();
			DateFormat df2 = new SimpleDateFormat("HH:mm");
			ArrayList<Date> DateTimeList = new ArrayList<Date>();
			Calendar cal = (Calendar)Calendar.getInstance();
			int hourarr[] = { 9, 11, 13, 15, 17 };
			for (int i = 0; i < hourarr.length; i++) {
				cal.set(Calendar.HOUR_OF_DAY, hourarr[i]);
				cal.set(Calendar.MINUTE, 30);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				DateTimeList.add(cal.getTime());
			}

			int minDay = 1;
			int maxDay = 6;
			if (courseType == 1) {
				Schedule schedule_1 = new Schedule (r.nextInt(maxDay-minDay) + minDay,
						r.nextInt(maxDay-minDay) + minDay,
						r.nextInt(maxDay-minDay) + minDay,
						DateTimeList.get(0), 
						DateTimeList.get(1), 
						DateTimeList.get(2), newCourseIndex);	
				count += courseIndexListControl.addNewCourseIndex(courseName, courseID, newCourseIndex, schedule_1,
						newNumOfVacancies);
				
			}
			else if (courseType == 2) {
				Schedule schedule_2 = new Schedule (r.nextInt(maxDay-minDay) + minDay,
						r.nextInt(maxDay-minDay) + minDay,
						DateTimeList.get(0), 
						DateTimeList.get(1), newCourseIndex);
				count += courseIndexListControl.addNewCourseIndex(courseName, courseID, newCourseIndex, schedule_2,
						newNumOfVacancies);
			}
			else if (courseType == 3) {
				Schedule schedule_3 = new Schedule (r.nextInt(maxDay-minDay) + minDay,
						DateTimeList.get(0),newCourseIndex);
				count += courseIndexListControl.addNewCourseIndex(courseName, courseID, newCourseIndex, schedule_3,
						newNumOfVacancies);
			}
			
			courseIndexListControl.save();
		}
	}
	/**
	 * Method that allows admin to edit an existing course index of a course.
	 * 
	 * @param courseIndexListControl CourseIndexList control object that holds the courseIndex list and their details.
	 * @param indexChoice The index ID that the admin wishes to edit in a course
	 */
	public static void editCourseIndex(CourseIndexListCtrl courseIndexListControl, int indexChoice) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nEnter your choice: ");
		System.out.println("1. Edit Current Index ID");
		System.out.println("2. Edit Current Index Vacancies");
		int choice;
		while (true) {
			try {
				while (true) {
					choice = scan.nextInt();
					if (choice >= 1 && choice <= 2) {
						break;
					} else {
						System.out.println("Invalid choice, please enter integer 1 or 2. ");
					}
				}
				break;
			} catch (Exception e) {
				System.out.println("Invalid choice, please enter integer 1 or 2. ");
				scan = new Scanner(System.in);
			}
		}
		
		courseIndexListControl.editCourseIndex(choice, indexChoice);
	}
	/**
	 * Method that allows the admin to delete a course index of a course.
	 * 
	 * @param courseIndexListControl CourseIndexList control object that holds the courseIndex list and their details.
	 * @param indexChoice1 The index ID that the admin wishes to delete from a course.
	 */
	public static void deleteCourseIndex(CourseIndexListCtrl courseIndexListControl, int indexChoice1) {
		courseIndexListControl.deleteCourseIndex(indexChoice1);
		
	}
}