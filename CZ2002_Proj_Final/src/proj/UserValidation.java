package proj;

import java.util.ArrayList;

public class UserValidation {
    
	// matching login credentials to the database
	// if match is found, return true -> login successfully
	// else false, retry login & show error msg on console

	private static StudentListCtrl studentList = new StudentListCtrl();
	private static AdminListCtrl adminList = new AdminListCtrl();
	private static SchoolListCtrl schList = new SchoolListCtrl();

	public static StudentListCtrl getStudentList() {
		return studentList;
	}

	public static void setStudentList(StudentListCtrl studentList) {
		UserValidation.studentList = studentList;
	}

	public static AdminListCtrl getAdminList() {
		return adminList;
	}

	public static void setAdminList(AdminListCtrl adminList) {
		UserValidation.adminList = adminList;
	}

	public static SchoolListCtrl getSchList() {
		return schList;
	}

	public static void setSchList(SchoolListCtrl schList) {
		UserValidation.schList = schList;
	}

	public static Boolean loginStudent(String username, String password, byte[][] SaltArray, String[] HashedPasswords) {

		for (int i = 0; i < studentList.getStudentList().size(); i++) {
			if (studentList.getStudent(i).getUsername().equals(username)
					&& HashedPasswords[i].equals(PasswordHash.ReturnHashedPassword(password, SaltArray[i]))) {
				return true;
			}
		}
		return false;
	}
	
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
	 * @param username User name of the user who logged in.
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
	
	public static boolean checkValidUsername(String username, ArrayList<Student> studArray) {
		
		for(int i = 0; i < studArray.size(); i++) {
			if(studArray.get(i).getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
}

