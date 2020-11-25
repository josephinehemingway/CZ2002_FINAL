package proj;


import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Admin sub console that contains methods to edit course which inherits EditCourseIndexUI.
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-20
 */
public class EditCourseUI extends EditCourseIndexUI{
	/**
	 * Contains the course ID
	 */
	public static String courseID;
	/**
	 * Choice of admin 
	 */
	private static int choice;
	/**
	 * Scanner to read admin user input
	 */
	public static final Scanner sc= new Scanner(System.in);
	/**
	 * Subclass constructor that inherits EditCourseIndexUI
	 */
	public EditCourseUI() {
		super();	
	}
	/**
	 * Method that allows the admin to add a new course
	 * 
	 * @param courseListControl CourseList control object that holds the course list and their details.
	 * @return -1 if course already exist, 0 if course was added successfully 
	 */
	public static int addCourse(CourseListCtrl courseListControl) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course Name: ");
		String newCourseName= sc.nextLine().toUpperCase();
		EditCourseIndexUI.courseName = newCourseName;
		
		System.out.println("Enter Course ID: ");
		String newCourseID= sc.next().toUpperCase();
		EditCourseIndexUI.courseID = newCourseID;
		
		return courseListControl.addNewCourse(newCourseName, newCourseID);
		
	}
	/**
	 * Method that allows the admin to edit an existing course
	 * 
	 * @param courseIndexListControl CourseIndexList control object that holds the courseIndex list and their details.
	 * @param courseListControl CourseList control object that holds the course list and their details.
	 * @param currentCourse The course choice that the admin wishes to edit 
	 */
	public static void editCourse(CourseIndexListCtrl courseIndexListControl, CourseListCtrl courseListControl, int currentCourse) {
		Scanner scan = new Scanner(System.in);
		System.out.println("===============================================");
		System.out.println("   #	Course ID    AU	   Course Name");
		System.out.println("===============================================");
		CourseListCtrl.printCourseDetails(currentCourse-1);

		System.out.println("\nEnter your choice: ");
		System.out.println("1. Edit Current Course Name");
		System.out.println("2. Edit Current Course ID");
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
		switch (choice) {
		case 1:
			Scanner scan2 = new Scanner(System.in);
			System.out.println("\nEditing Course Name ------------------");
			System.out.println("Enter new Course Name: ");
			boolean k = true;
			String editedName = null;
			while (k) {
					int count = 1;
					editedName = scan2.nextLine().toUpperCase();
					for (int i = 0; i < courseListControl.getCourseList().size(); i++) {
						if(courseListControl.getCourseList().get(i).getCourseName().equals(editedName)) {
							System.out.println("Course Name already exist! Please enter another Course Name.");
							break;
						}
						else if((count) == courseListControl.getCourseList().size()) {
							k = false;
						} 
						(count)++;
					}
			}
			String initialCourseName = courseListControl.editCourse(choice, currentCourse, editedName);
			courseListControl.editCourse(choice, currentCourse, editedName);
			System.out.println("Changes Made!");
			courseIndexListControl.editCourseName(editedName, initialCourseName);	
			break;
		case 2:
			System.out.println("\nEditing Course ID ------------------");
			System.out.println("Enter new Course ID: ");
			boolean j = true;
			String editedID = null;
			while (j) {
				int count = 1;
				editedID = scan.next().toUpperCase();
				for (int i = 0; i < courseListControl.getCourseList().size(); i++) {
					if(courseListControl.getCourseList().get(i).getCourseID().equals(editedID)) {
						System.out.println("Course ID already exist! Please enter another Course ID.");
						break;
					}
					else if((count) == courseListControl.getCourseList().size()) {
						j = false;
					} 
					(count)++;
				}
			}
			String initialCourseID = courseListControl.editCourse(choice, currentCourse, editedID);
			courseListControl.editCourse(choice, currentCourse, editedID);
			System.out.println("Changes Made!");
			courseIndexListControl.editCourseID(editedID, initialCourseID);
			break;
		default:
			System.out.println("Invalid Choice!");
		}
	}
	
	/**
	 * Method that allows the admin to delete an existing course
	 * 
	 * @param courseListControl CourseList control object that holds the course list and their details.
	 */
	public static void deleteCourse(CourseListCtrl courseListControl) {
		System.out.println("Enter the course # you wish to delete: ");
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				while (true) {
					choice = sc.nextInt();
					if( choice <1 || choice > courseListControl.getCourseSize()) {
						System.out.println("Please re-enter a valid choice");
					}
					else {
						break;
					}
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please re-enter a valid choice");
				sc = new Scanner(System.in);
			}
		}
		System.out.println("Course deleted successfully!");
		courseListControl.deleteCourse(choice-1);
		
	}
}