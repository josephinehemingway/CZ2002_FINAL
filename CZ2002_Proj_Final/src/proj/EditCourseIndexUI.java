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

public class EditCourseIndexUI{
	protected static String courseName;
	protected static String courseID;
	public static final Scanner sc = new Scanner(System.in);

	public static void setAttributes(String courseID, CourseIndexListCtrl courseIndexListControl) {
		for (int i = 0; i < courseIndexListControl.getCourseIndexSize(); i++) {
			if (courseIndexListControl.getCourseIndexList().get(i).getCourseID().equals(courseID)) {
				String chosenCourseName = courseIndexListControl.getCourseIndexList().get(i).getCourseName();
				EditCourseIndexUI.courseName = chosenCourseName;
				EditCourseIndexUI.courseID = courseID;
				
			}
		}
	}
	public static void addCourseIndex(CourseIndexListCtrl courseIndexListControl) {
		System.out.println("Enter the number of Course Indices you wish to input: ");
		Scanner sc = new Scanner(System.in);
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
			System.out.println("Enter Index ID: ");
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
			
			System.out.println("Enter number of vacancies: ");
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

			// Course index for 1 course
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
//			Date date1 = new Date(93600000L);
//			Date date2 = new Date(100800000L);
//			Date date3 = new Date(108000000L);
//			Date date4 = new Date(115200000L);
//			Date date5 = new Date(122400000L);

			
			int minDay = 1;
			int maxDay = 6;

			//Date date_arr[] = { date1, date2, date3, date4, date5 };


			// generating random schedules for course
//			Schedule schedule_1 = new Schedule(r.nextInt(maxDay - minDay) + minDay, r.nextInt(maxDay - minDay) + minDay,
//					r.nextInt(maxDay - minDay) + minDay, date_arr[r.nextInt(date_arr.length - 1)],
//					date_arr[r.nextInt(date_arr.length - 1)], date_arr[r.nextInt(date_arr.length - 1)], 2011 + 0);
			Schedule schedule_1 = new Schedule (r.nextInt(maxDay-minDay) + minDay,
					r.nextInt(maxDay-minDay) + minDay,
					r.nextInt(maxDay-minDay) + minDay,
					DateTimeList.get(0), 
					DateTimeList.get(1), 
					DateTimeList.get(2),newCourseIndex);

			count += courseIndexListControl.addNewCourseIndex(courseName, courseID, newCourseIndex, schedule_1,
					newNumOfVacancies);
			courseIndexListControl.save();
		}
	}

	public static void editCourseIndex(CourseIndexListCtrl courseIndexListControl, int indexChoice) {
		// Choose index component to edit
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

	public static void deleteCourseIndex(CourseIndexListCtrl courseIndexListControl, int indexChoice1) {
		courseIndexListControl.deleteCourseIndex(indexChoice1);
		
	}
}