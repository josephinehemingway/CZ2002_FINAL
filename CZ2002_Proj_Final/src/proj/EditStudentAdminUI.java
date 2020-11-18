package proj;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EditStudentAdminUI {
	private static String studentName;
	private static String studentUsername;
	private static int choice;
	public static final Scanner sc = new Scanner(System.in);
	
	public static void addStudent(StudentListCtrl studentListControl) {
		//Add Student Name
		System.out.println("Enter Student Name:");
		String newStudent = sc.nextLine();
		// Add Student Username
		String username;
		while (true) {
			System.out.println("Enter Student Username:");
			username = sc.nextLine().toUpperCase();
			for (int i = 0; i < studentListControl.getStudentList().size(); i++) {
				if (studentListControl.getStudentList().get(i).getUsername().equals(username)) {
					System.out.println("Username already exist! Please re-enter valid Username");
					username = null;
					System.out.println("");
					break;
				}
			}
			if (username != null) {
				break;
			}
		}

		// Add Student Password	
		System.out.println("Enter Student Password:");
		String password = sc.nextLine();
		
		// Add Student Email
		String email = username + "@e.ntu.edu.sg";

		// Add Student MatricID
		String matricID;
		while (true) {
			System.out.println("Enter Student MatricID:");
			matricID = sc.nextLine().toUpperCase();
			for (int i = 0; i < studentListControl.getStudentList().size(); i++) {
				if (studentListControl.getStudentList().get(i).getMatricID().equals(matricID)) {
					System.out.println("Username already exist! Please re-enter valid Username");
					matricID = null;
					System.out.println("");
					break;
				}
			}
			if (matricID != null) {
				break;
			}
		}

		// Add Student Gender
		char gender;
		while(true) {
			System.out.println("Enter Student Gender (F/M):");
			gender = sc.nextLine().toUpperCase().charAt(0);
			if(gender == 'F' || gender == 'M') {
				break;
			}
			System.out.println("Please input a valid character!");
			System.out.println("");
		}
		
		//Add Student Nationality
		System.out.println("Enter Student Nationality");
		String nationality = sc.nextLine();
		
		// Add Student School
		String schoolID = null;
		SchoolListCtrl schoolListControl = new SchoolListCtrl();
		int count = 1;
		while (true) {
			System.out.println("Enter Student School:");
			schoolID = sc.nextLine().toUpperCase();
			for (int i = 0; i < schoolListControl.getSchoolList().size(); i++) {
				if (schoolListControl.getSchoolList().get(i).getSchoolID().equals(schoolID)) {
					count = 0;
					break;
				}
			}
			if (count == 1) {
				System.out.println("Invalid SchoolID! Please re-enter valid SchoolID");
				System.out.println("");
			} else if (count == 0) {
				break;
			}
		}

		// Add Student into StudentList
		
		studentListControl.addStudent(newStudent, username, password, gender, nationality, matricID, email, schoolID);
		studentListControl.printStudentListByUsername(username);
		
	}
	public static void editStudent(StudentListCtrl studentListControl) {
		ArrayList<Student> studList = studentListControl.getStudentList();
		
		while (true) {
			System.out.println("Enter student username to edit: ");
			studentUsername = sc.nextLine();
			
			if (UserValidation.checkValidUsername(studentUsername, studList) == false)
				System.out.println("Invalid username. Please re-enter valid username: ");
			else {
				System.out.println("Current particulars: ");
				studentListControl.printStudentListByUsername(studentUsername);
				break;
			}
		}

		System.out.println("\nEnter your choice: ");
		System.out.println("1. Edit Name");
		System.out.println("2. Edit Username");
		System.out.println("3. Edit Password");
		System.out.println("4. Edit MatricID");
		System.out.println("5. Edit Email");
		System.out.println("6. Edit Gender");
		System.out.println("7. Edit Nationality");
		System.out.println("8. Edit School");
		
		System.out.println("9. Exit");

		while (true) {
			try {
				choice = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please re-enter a valid choice");
			}
		}
		studentListControl.editStudent(studentUsername, choice);

	}
	//s
	public static void deleteStudent(StudentListCtrl studentListControl) {
		System.out.println("Enter username of student to be deleted: ");
		String student = sc.nextLine();
		
		studentListControl.deleteStudent(student);
	}
}
