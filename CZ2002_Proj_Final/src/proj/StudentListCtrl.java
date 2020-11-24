package proj;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Represents the control class for studentList which holds an array list of Students
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 */
public class StudentListCtrl {
	/**
	 * ArrayList that stores list of Students
	 */
	private ArrayList<Student> studentList; // user list containing all the user acc created in this app
	/**
	 * Filename of student list to be read from SerializeDB
	 */
	private String filename = "StudentList.txt"; // file storing all the user acc created
	/**
	 * Reads in the list of students from SerializeDB
	 */
	@SuppressWarnings("unchecked")
	public StudentListCtrl() {
		try {
			studentList = (ArrayList<Student>) SerializeDB.readSerializedObject(filename);
			
		}catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
	/**
	 * Gets the student list stored in this studentListControl class
	 * 
	 * @return ArrayList of students
	 */
	public ArrayList<Student> getStudentList() {
		if (studentList==null) {
			studentList= new ArrayList<Student>();
		}
		return studentList;
	}
	/**
	 * Gets a student from this control class's studentList indexed by the parameter i
	 * @param i index of the movie in the studentList
	 * @return	a Student object
	 */
	public Student getStudent(int i) {
		return (Student) studentList.get(i);
	}
	/**
	 * Gets the number of students from this control class's studentList
	 * @return number of Students in the studentList
	 */
	public int getStudentListSize() {
		return studentList.size();
	}
	/**
	 * Adds a new Student to this control class's studentList
	 * @param name	Name of the student to be added.
	 * @param username	Username of the student to be added.
	 * @param pw	Password of the student to be added.
	 * @param gender	Gender of the student to be added.
	 * @param nationality	Nationality of the student to be added.
	 * @param matricID	Matriculation ID of the student to be added.
	 * @param email	Email of the student to be added.
	 * @param schoolID	School of the Student to be added.
	 */
	public void addStudent(String name, String username, String pw, char gender, String nationality, String matricID, String email, String schoolID) {
		for(int i = 0; i < studentList.size(); i++) {
			if(studentList.get(i).getUsername().equals(username)) {
				System.out.println("Student already exists.");
				return;
			}
		}
		Student s1 = new Student(username, pw, name, gender, nationality, matricID, email, schoolID);
		studentList.add(s1);
	}

	/**
	 * Allows admin user to edit Student stored in the studentList ArrayList
	 * @param s Name of the Student to be edited
	 * @param choice integer to be passed in to determine which option of the student details to edit
	 *  1 for student name, 2 for username, 3 for password, 4 for matric ID, 5 for email, 6 for gender, 7 for nationality, 8 for school, 9 to exit
	 */
	public void editStudent(String s, int choice) {
		final Scanner sc = new Scanner(System.in);
		for(int i = 0; i < studentList.size(); i++) {
			Student stud = studentList.get(i);
			if(stud.getUsername().equals(s)) {
				switch(choice) {
				case 1://name
					System.out.println("\nEditing Student Name ------------------");
					System.out.println("Enter Edited Student Name: ");
					String editedName;
					while (true) {
						try {
							editedName = sc.nextLine();
							break;
						} catch (InputMismatchException e) {
							System.out.print("Please re-enter a valid value: ");
							sc.nextLine();
						}
					}
					stud.setName(editedName);
					System.out.println("Edited successfully! \n");
					System.out.println("Edited student particulars ------------------ \n" + stud.toString());
					System.out.println();
					return;
					
				case 2://username
					System.out.println("\nEditing Student Username ------------------");
					System.out.println("Enter Edited Student Username: ");
					String editedUsername;
					while (true) {
						try {
							editedUsername = sc.nextLine().toUpperCase();
							break;
						} catch (InputMismatchException e) {
							System.out.print("Please re-enter a valid value: ");
							sc.nextLine();
						}
					}
					stud.setUsername(editedUsername);
					System.out.println("Edited successfully! \n");
					System.out.println("Edited student particulars ------------------ \n" + stud.toString());
					System.out.println();
					return;
					
				case 3://password
					System.out.println("\nEditing Student Password ------------------");
					System.out.println("Enter Edited Student Password: ");
					String editedPW;
					while (true) {
						try {
							editedPW = sc.nextLine();
							break;
						} catch (InputMismatchException e) {
							System.out.print("Please re-enter a valid value: ");
							sc.nextLine();
						}
					}
					stud.setPassword(editedPW);
					System.out.println("Edited successfully! \n");
					System.out.println("Edited student particulars ------------------ \n" + stud.toString());
					System.out.println();
					return;
					
				case 4://matricid
					System.out.println("\nEditing Student Matriculation ID ------------------");
					System.out.println("Enter Edited Student Matriculation ID: ");
					String editedMatric;
					while (true) {
						try {
							editedMatric = sc.nextLine();
							break;
						} catch (InputMismatchException e) {
							System.out.print("Please re-enter a valid value: ");
							sc.nextLine();
						}
					}
					stud.setMatricID(editedMatric);
					System.out.println("Edited successfully! \n");
					System.out.println("Edited student particulars ------------------ \n" + stud.toString());
					System.out.println();
					return;
					
				case 5://email
					System.out.println("\nEditing Student Email ------------------");
					System.out.println("Enter Edited Student Email: ");
					String editedEmail;
					while (true) {
						try {
							editedEmail = sc.nextLine();
							break;
						} catch (InputMismatchException e) {
							System.out.print("Please re-enter a valid value: ");
							sc.nextLine();
						}
					}
					stud.setEmail(editedEmail);
					System.out.println("Edited successfully! \n");
					System.out.println("Edited student particulars ------------------ \n" + stud.toString());
					System.out.println();
					return;
					
				case 6://gender
					System.out.println("\nEditing Student Gender ------------------");
					System.out.println("Enter Edited Student Gender: ");
					char editedGender;
					while (true) {
						try {
							editedGender = sc.next().charAt(0);
							break;
						} catch (InputMismatchException e) {
							System.out.print("Please re-enter a valid value: ");
							sc.nextLine();
						}
					}
					stud.setGender(editedGender);
					System.out.println("Edited successfully! \n");
					System.out.println("Edited student particulars ------------------ \n" + stud.toString());
					System.out.println();
					return;
					
				case 7://nationality
					System.out.println("\nEditing Student Nationality ------------------");
					System.out.println("Enter Edited Student Nationality: ");
					String editedNationality;
					while (true) {
						try {
							editedNationality = sc.nextLine();
							break;
						} catch (InputMismatchException e) {
							System.out.print("Please re-enter a valid value: ");
							sc.nextLine();
						}
					}
					stud.setNationality(editedNationality);
					System.out.println("Edited successfully! \n");
					System.out.println("Edited student particulars ------------------ \n" + stud.toString());
					System.out.println();
					return;
					
				case 8://school
					System.out.println("\nEditing Student School ------------------");
					System.out.println("Enter Edited Student School: ");
					String editedSchool;
					while (true) {
						try {
							editedSchool = sc.nextLine();
							break;
						} catch (InputMismatchException e) {
							System.out.print("Please re-enter a valid value: ");
							sc.nextLine();
						}
					}
					stud.setSchool(editedSchool);
					System.out.println("Edited successfully! \n");
					System.out.println("Edited student particulars ------------------ \n" + stud.toString());
					System.out.println();
					return;
					
				case 9:
					System.out.println("Exiting.. ");
					break;
				default:
					break;
					
					
				}
			}
		}
	}
	/**
	 * Allows admin user to remove a Student from the studentList ArrayList
	 * 
	 * @param s The username of the student to be deleted
	 */
	public void deleteStudent(String s) {
		System.out.println("Initial size of Student List:  " + studentList.size());
		for(int i = 0; i < studentList.size(); i++) {
			if(studentList.get(i).getUsername().equals(s)) {
				studentList.remove(i);
				System.out.println("Removed successfully! \n");
				System.out.println("Updated size of Student List: " + studentList.size());
				return;
			}
		}
		System.out.println("Student does not exist.");
	}
	/**
	 * Prints student detail of a student in the studentList ArrayList, along with username, name, ID, Email, Nationality and School
	 * 
	 * @param username The username of the student whose details would be printed
	 */
	public void printStudentListByUsername(String username) {
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < studentList.size() ; i++) {
			Student stud = studentList.get(i);
			
			if (stud.getUsername().equals(username)){
				System.out.println("Username: " + username);
				System.out.println("Student Name: "+ stud.getName());
//				System.out.println("Matriculation ID: "+ stud.getMatricID());
//				System.out.println("Email: "+ stud.getEmail());
				System.out.println("Gender: " + stud.getGender());
				System.out.println("Nationality: "+ stud.getNationality());
//				System.out.println("School: "+ stud.getSchool());

				return;
				
			}
		}
		System.out.println("Student not found.");
		
	}
	/**
	 * Prints details of a indexed Student in the studentList
	 * 
	 * @param index Index of the Student to be printed in studentList
	 */
	public void printStudentDetailsByIndex(int index) { //not sorted		
		Student s = studentList.get(index);
		int maxNameLength = 20;
		
		System.out.print(" " + s.getName());
	    for(int j=0;j <= maxNameLength - s.getName().length(); j++)
	    {
	    	   System.out.print(" ");
	    }
	    System.out.print(s.getGender()+ "		" + s.getNationality() +"\n");
	    
		}
//		System.out.println(
//				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		Student stud = studentList.get(index);
//		System.out.println("Student Username: "+ stud.getUsername());
//		System.out.println("Student Name: "+ stud.getName());
//		System.out.println("Matriculation ID: "+ stud.getMatricID());
//		System.out.println("School: " + stud.getSchool());
//
//		if (index == studentList.size()-1) {
//			System.out.println(
//					"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		}
	
	/**
	 * Prints details of all students in the studentList
	 */
	public void printAllStudentDetails() {
		System.out.println("List of all students ------------------ ");
		System.out.println("\n===============================================");
		System.out.println(" Student Name       Gender	Nationality");
		System.out.println("===============================================");
		
		for(int i=0; i< studentList.size(); i++) {
			if (studentList.size() != 0) {
				printStudentDetailsByIndex(i);
			}
			else {
				System.out.println("No students found!");
			}
			
		}
	}
	
/**
 *  Gets student object from studentList Array using the student's username
 * 
 * @param username The username of the student object to be retrieved
 * 
 * @return the Student object that has the username inputted
 */
	public Student getStudentFromUsername(String username) {
		for (int i=0; i< studentList.size();i++) {
			if( studentList.get(i).getUsername().equals(username)) {
				Student s= studentList.get(i);
				return s;
			}
		}
		return null;
	}
	/**
	 * Gets the student's MatricID from Student in the studentList ArrayList with the username that is inputted
	 * 
	 * @param username The username of the student that we are getting MatricID from
	 * 
	 * @return MatriID of student from the studentList ArrayList of the same username inputted
	 */
	public String getMatricNo(String username) {
		for(int i = 0; i < studentList.size(); i++) {
			Student stud = studentList.get(i);
			if (stud.getUsername().equals(username)) {
				String matricNo = stud.getMatricID();
				return matricNo;
			}
		}
		System.out.println("Student not found.");
		return null;
	}
	/**
	 * Gets the name of the student from the studentList ArrayList with username that is inputted
	 * 
	 * @param username The username of the student that we want to get the name from
	 * 
	 * @return Name of the student of the associated username from studentList ArrayList
	 */
	public String getName(String username) {
		for(int i = 0; i < studentList.size(); i++) {
			Student stud = studentList.get(i);
			if (stud.getUsername().equals(username)) {
				String name = stud.getName();
				return name;
			}
		}
		System.out.println("Student not found.");
		return null;
	}
	/**
	 * When called, save the current edited studentList object to SerializeDB
	 */
	public void save() { // save the user list back into the file storing student data
		try {
			SerializeDB.writeSerializedObject(filename, studentList);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}
