package proj;

import java.util.ArrayList;
/**
 * Represents the vertification class for STARS admin and student users
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 */
public class UserValidation {
    
	/**
	 * studentList is a student list control object that holds the list of students and their login details
	 * adminList is a admin list control object that holds the list of admins and their login details
	 * schoolList is a school list control object that holds the specific school's access period for students
	 *  
	 */
	private static StudentListCtrl studentList = new StudentListCtrl();
	private static AdminListCtrl adminList = new AdminListCtrl();
	private static SchoolListCtrl schList = new SchoolListCtrl();
	/**
	 * Static method that is called whenver student logins. The user's inputted login credentials will be matched with the studentList's student login credentials
	 * @param username The username that the user has input.
	 * @param password	The password that the user has input.
	 * @param SaltArray Returns the byte array that stores salt string of the Student's password 	
	 * @param HashedPasswords Returns the hashed password of the Student 
	 * @return true if login credientials matched with studentList, else value of false will be returned.
	 * 
	 */
	public static Boolean loginStudent(String username, String password, byte[][] SaltArray, String[] HashedPasswords) {

		for (int i = 0; i < studentList.getStudentList().size(); i++) {
			if (studentList.getStudent(i).getUsername().equals(username)
					&& HashedPasswords[i].equals(PasswordHash.ReturnHashedPassword(password, SaltArray[i]))) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Static method that is used to check if the student who logged in is within the access period or not. If he/she is, 
	 * then the student will be able to access Stars, else acess will be denied, a denied access message will be shown in the console.
	 * @param username The username of the student who logged in.
	 * @return true if the accessperiod of the student, determined by the school is within the current time period, else value of false will be returned.
	 */
	public static boolean studentCanAccess(String username) {
		for(int i = 0; i < studentList.getStudentList().size(); i++) {
			Student student = studentList.getStudent(i);
			
			if(student.getUsername().equals(username)) {
				for (int j=0; j<schList.getSchoolList().size(); j++) {
					School sch = schList.getSchool(j);
					
					if (student.getSchool().equals(sch.getSchoolID())) {
						return sch.canAccess();
					}
				}
			}
		}
		return false;
			
	}
	
	/**
	 * Static method that is used to check if the user who logged in is an admin or not. If he/she is, then the admin console will be launched. Else, 
	 * the movie-goer console will be launched.
	 * @param username Username of admin who logged in.
	 * @return true if user is indeed an admin, else returns false.
	 */
	public static boolean loginAdmin(String username, String password) {
		for(int i = 0; i < adminList.getAdminList().size(); i++) {
			if(adminList.getAdmin(i).getAdminID().equals(username) && adminList.getAdmin(i).getAdminPW().equals(password)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Static method that is used to check if username inputted is valid
	 * @param username Username of student inputted
	 * @param studArray ArrayList of Students
	 * @return true if username of this student is found in the ArrayList, else returns false.
	 */
	public static boolean checkValidUsername(String username, ArrayList<Student> studArray) {
		
		for(int i = 0; i < studArray.size(); i++) {
			if(studArray.get(i).getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
}

