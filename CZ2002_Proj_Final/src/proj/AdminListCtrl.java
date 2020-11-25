package proj;

import java.util.ArrayList;
/**
 * Represents the control class for adminList which holds an array list of admin
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 */
public class AdminListCtrl {
	
	/**
	 * Arraylist that stores list of Admin
	 */
	private ArrayList<Admin> adminList; // user list containing all the user acc created in this app
	
	/**
	 * Filename of admin list to be read from SerializeDB
	 */
	private String filename = "AdminList.txt"; // file storing all the user acc created
	
	/**
	 * Reads in list of admin from SerializeDB
	 */
	@SuppressWarnings("unchecked")
	public AdminListCtrl() {
		try {
			adminList = (ArrayList<Admin>) SerializeDB.readSerializedObject(filename);
			
		}catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
	
	/**
	 * Gets the admin list stored  in this adminListControl class
	 * 
	 * @return ArrayList of admin
	 */
	public ArrayList<Admin> getAdminList() {
		if (adminList ==null) {
			adminList= new ArrayList<Admin>();
		}
		return adminList;
	}

	/**
	 * Gets an admin from this control class' adminList indexed by the parameter i
	 * @param i index of the admin in adminList
	 * @return an Admin object
	 */
	public Admin getAdmin(int i) {
		return (Admin) adminList.get(i);
	}
	
	/**
	 * When called, save the current edited adminList object to SerializeDB
	 */
	public void save() { // save the user list back into the file storing student data
		try {
			SerializeDB.writeSerializedObject(filename, adminList);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}