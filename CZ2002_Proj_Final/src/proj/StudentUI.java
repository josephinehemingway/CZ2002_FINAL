package proj;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Student main console for non-admin users
 * StudentUI contains one single method which allows user to perform student's STARS functions
 * @author Joey
 * @since 2020-11-19 
 */
public class StudentUI {
	/**
	 * Scanner to read student user input
	 */
	public static Scanner sc = new Scanner(System.in);
	/**
	 * Calls the console method for the user to perform student Stars functions
	 * @param username Username of student who logged into the console via mainConsole
	 */
	public static void console(String username) {
		StudentListCtrl studentListControl = new StudentListCtrl();
		CourseListCtrl courseListControl = new CourseListCtrl();
		CourseIndexListCtrl courseIndexListControl = new CourseIndexListCtrl();
		StudentCourseListCtrl studentCourseListControl = new StudentCourseListCtrl(studentListControl, courseIndexListControl);

		
		int choice = 0;
		for(int i = 0; i < studentListControl.getStudentList().size(); i++) {
			Student stud = studentListControl.getStudent(i);
			if (stud.getUsername().equals(username)) {
				System.out.println("");
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Welcome " + stud.getName() + "!");
				System.out.println("Matriculation No.: " + stud.getMatricID());
				System.out.println("Course: " + stud.getSchool());
			}
		}
		do {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("- STUDENT CONSOLE -");
			System.out.println("");
			System.out.println("Enter your choice:");;
			System.out.println("1. Add Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check/Print Course Registered");
			System.out.println("4. Check Vacancies Available");
			System.out.println("5. Change Index Number of Course");
			System.out.println("6. Swap Index Number with Another Student");
			System.out.println("7. Exit");
			
			while (true) {
				try {
					while (true) {
						choice = sc.nextInt();
						if (choice >= 1 && choice <= 7) {
							break;
						} else {
							System.out.println("Invalid choice, please enter integer between 1 and 7. ");
						}
					}
					break;
				} catch (Exception e) {
					System.out.println("Invalid choice, please enter integer between 1 and 7. ");
					sc = new Scanner(System.in);
				}
			}
			
			switch(choice) {
			case 1: 
				System.out.println("\nAdding Course ------------------");
				studentCourseListControl.printRegisteredCourses(username);
				AddDropStudentCourseUI.addCourse(username, courseListControl, courseIndexListControl, studentCourseListControl, studentListControl);
				studentCourseListControl.printRegisteredCourses(username);
				courseIndexListControl.save();
				studentListControl.save();
				break;
				
			case 2:
				System.out.println("\nDropping Course ------------------");
				boolean check_notempty = studentCourseListControl.printRegisteredCourses(username);
				if (check_notempty) {
					AddDropStudentCourseUI.dropCourse(username, courseListControl, courseIndexListControl, studentCourseListControl);
					studentCourseListControl.printRegisteredCourses(username);
					courseIndexListControl.save();
					studentListControl.save();
				}
				break;
				
			case 3:
				System.out.println("\nPrint Course Registered ------------------");
				studentCourseListControl.printRegisteredCourses(username);
				studentCourseListControl.printTimetable(username);
				studentListControl.save();
				break;
				
			case 4:
				System.out.println("\nCheck Vacancies Available ------------------");
				courseIndexListControl.printAllCourseInfo();
				System.out.println("\nEnter Course ID: ");
				String courseID = courseListControl.chooseCourse();
				System.out.println("Enter Course Index: ");
				int courseIndexID = courseIndexListControl.chooseCourseIndex(courseID);
				courseIndexListControl.printVacanciesForCourseIndex(courseID, courseIndexID);
				studentListControl.save();
				break;
				
			case 5:
				System.out.println("\nChange Index Number of Course ------------------");
				boolean check_notempty2 = studentCourseListControl.printRegisteredCourses(username);
				if (check_notempty2) {
					AddDropStudentCourseUI.changeCourseIndex(username, courseListControl, courseIndexListControl, studentCourseListControl);
					studentCourseListControl.printRegisteredCourses(username);
					studentListControl.save();
				}
				break;
				
			case 6:
				System.out.println("\nSwap Index Number with Another Student ------------------");
				System.out.println("Student #1: ");
				System.out.println("Your Username: " + username);
				System.out.println("Your Matriculation No.: " + studentListControl.getMatricNo(username));
				if(studentCourseListControl.printRegisteredCourses(username) == false) {
					System.out.println("No courses available to swap!");
					break;
				}
				AddDropStudentCourseUI.swapCourseIndex(username, courseListControl, courseIndexListControl, studentCourseListControl, studentListControl);
				studentCourseListControl.printRegisteredCourses(username);
				studentListControl.save();
				break;
				
			case 7:
				System.out.println("Logging out of student console.");
				System.out.println("");
				studentListControl.save();
				break;
				
			default:
				System.out.println("Invalid input!");
				break;
			}
		}while(choice != 7);
	}
}

