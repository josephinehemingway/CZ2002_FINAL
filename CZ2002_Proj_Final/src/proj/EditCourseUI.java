package proj;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EditCourseUI extends EditCourseIndexUI{
	public static String courseID;
	private static int choice;
	public static final Scanner sc= new Scanner(System.in);
	
	public EditCourseUI() {
		super();	
	}
	
	public static int addCourse(CourseListCtrl courseListControl) {
		System.out.println("Enter Course Name: ");
		String newCourseName= sc.nextLine().toUpperCase();
		EditCourseIndexUI.courseName= newCourseName;
		System.out.println("Enter Course ID: ");
		String newCourseID= sc.next().toUpperCase();
		EditCourseIndexUI.courseID= newCourseID;
		return courseListControl.addNewCourse(newCourseName, newCourseID);
		
	}

	public static void editCourse(CourseIndexListCtrl courseIndexListControl, CourseListCtrl courseListControl, int currentCourse) {
		Scanner scan = new Scanner(System.in);
		System.out.println("  #	Course ID  Course Name");
		System.out.println("===============================================");
		CourseListCtrl.printCourseDetails(currentCourse-1);
		// Choose course component to edit
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
			System.out.println("\nEditing Course Name ------------------");
			System.out.println("Enter new Course Name: ");
			boolean k = true;
			String editedName = null;
			while (k) {
					int count = 1;
					editedName = sc.nextLine().toUpperCase();
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
				editedID = sc.next().toUpperCase();
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