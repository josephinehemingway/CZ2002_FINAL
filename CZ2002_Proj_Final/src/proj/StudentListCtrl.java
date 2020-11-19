package proj;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentListCtrl {
	private ArrayList<Student> studentList; // user list containing all the user acc created in this app
	
	private String filename = "StudentList.txt"; // file storing all the user acc created
	
	@SuppressWarnings("unchecked")
	public StudentListCtrl() {
		try {
			studentList = (ArrayList<Student>) SerializeDB.readSerializedObject(filename);
			
		}catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
	
	public ArrayList<Student> getStudentList() {
		if (studentList==null) {
			studentList= new ArrayList<Student>();
		}
		return studentList;
	}

	public Student getStudent(int i) {
		return (Student) studentList.get(i);
	}
	
	public int getStudentListSize() {
		return studentList.size();
	}
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
	
	public Student chooseStudent(String username) {
		for (int j = 0; j < studentList.size(); j++) {
			if (studentList.get(j).getUsername() == username) {
				Student curStudent = studentList.get(j);
				return curStudent;}
		}
		return null;
	}
	
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
							editedUsername = sc.nextLine();
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
	
	public void printStudentListByUsername(String username) {
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < studentList.size() ; i++) {
			Student stud = studentList.get(i);
			
			if (stud.getUsername().equals(username)){
				System.out.println("Username: " + username);
				System.out.println("Student Name: "+ stud.getName());
				System.out.println("Matriculation ID: "+ stud.getMatricID());
				System.out.println("Email: "+ stud.getEmail());
				System.out.println("Gender: " + stud.getGender());
				System.out.println("Nationality: "+ stud.getNationality());
				System.out.println("School: "+ stud.getSchool());

				return;
				
			}
		}
		System.out.println("Student not found.");
		
	}
	
	public void printStudentDetailsByIndex(int index) { //not sorted
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		Student stud = studentList.get(index);
		System.out.println("Student Username: "+ stud.getUsername());
		System.out.println("Student Name: "+ stud.getName());
		System.out.println("Matriculation ID: "+ stud.getMatricID());
		System.out.println("School: " + stud.getSchool());

		if (index == studentList.size()-1) {
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
		
	}
	
	public void printAllStudentDetails() {
		System.out.println("List of all students ------------------ ");
		for(int i=0; i< studentList.size(); i++) {
			printStudentDetailsByIndex(i);
		}
	}

	public Student getStudentFromUsername(String username) {
		for (int i=0; i< studentList.size();i++) {
			if( studentList.get(i).getUsername().equals(username)) {
				Student s= studentList.get(i);
				return s;
			}
		}
		return null;
	}
	
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
	
	public void save() { // save the user list back into the file storing student data
		try {
			SerializeDB.writeSerializedObject(filename, studentList);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}
