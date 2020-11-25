package proj;

import java.util.ArrayList;
/**
 * Represents the verification class for STARS admin and student users
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
	private StudentListCtrl studentlist;
	private SchoolListCtrl schoollist;
	private AdminListCtrl adminlist;
	
	/**
	 * constructor to initilise studentlist, schoollist and adminlist.
	 */
	public UserValidation() {
		StudentListCtrl studentList = new StudentListCtrl();
		 AdminListCtrl adminList = new AdminListCtrl();
		 SchoolListCtrl schList = new SchoolListCtrl();
		 this.studentlist = studentList;
		 this.adminlist= adminList;
		 this.schoollist= schList;
		
	}

	/**
	 * method that is called whenever student logins. The user's input login credentials will be matched 
	 * with the studentList's student login credentials
	 * 
	 * @param username The username that the user has input.
	 * @param password	The password that the user has input.
	 * @param SaltArray Returns the byte array that stores salt string of the Student's password 	
	 * @param HashedPasswords Returns the hashed password of the Student 
	 * @return true if login credentials matched with studentList, else value of false will be returned.
	 * 
	 */
	public boolean loginStudent(String username, String password, byte[][] SaltArray, String[] HashedPasswords) {
		for (int i = 0; i < this.studentlist.getStudentListSize(); i++) {
			if (this.studentlist.getStudent(i).getUsername().equals(username)
					&& HashedPasswords[i].equals(PasswordHash.ReturnHashedPassword(password, SaltArray[i]))) {
				return true;
			}
		}
		return false;
	}
	/**
	 * method that is used to check if the student who logged in is within the access period or not. If he/she is, 
	 * then the student will be able to access Stars, else access will be denied, a denied access message will be shown in the console.
	 * 
	 * @param username The username of the student who logged in.
	 * @return true if the accessperiod of the student, determined by the school is within the current time period, else value of false will be returned.
	 */
	public boolean studentCanAccess(String username) {
		String schString = null;
		for(int i = 0; i < this.studentlist.getStudentList().size(); i++) {
			Student student = this.studentlist.getStudent(i);
			
			if(student.getUsername().equals(username)) {
				for (int j=0; j<this.schoollist.getSchoolList().size(); j++) {
					School sch = this.schoollist.getSchool(j);
					schString = sch.getSchoolID();
					if (student.getSchool().equals(sch.getSchoolID())) {
						if(sch.canAccess() == false) {
							System.out.println("\nUnable to login as current date is not within Student Access period.");
							schoollist.getCurrentAccessPeriod(schString);
							System.out.println("\n");
						}
						return sch.canAccess();
					}
				}
			}
		}
		return false;
			
	}
	
	/**
	 * If user logged in as admin, if login is successful, admin console will be launched.
	 * 
	 * @param username Username of admin who logged in.
	 * @return true if user is indeed an admin, else returns false.
	 */
	public boolean loginAdmin(String username, String password) {
		for(int i = 0; i < this.adminlist.getAdminList().size(); i++) {
			if(this.adminlist.getAdmin(i).getAdminID().equals(username) && this.adminlist.getAdmin(i).getAdminPW().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * method that is used to check if username input is valid
	 * @param username Username of student input
	 * @param studArray ArrayList of Students
	 * @return true if username of this student is found in the ArrayList, else returns false.
	 */
	public boolean checkValidUsername(String username, ArrayList<Student> studArray) {
		
		for(int i = 0; i < studArray.size(); i++) {
			if(studArray.get(i).getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
}

