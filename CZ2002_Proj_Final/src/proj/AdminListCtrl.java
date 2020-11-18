package proj;

import java.util.ArrayList;

public class AdminListCtrl {
	
	private ArrayList<Admin> adminList; // user list containing all the user acc created in this app
	private String filename = "AdminList.txt"; // file storing all the user acc created
	
	@SuppressWarnings("unchecked")
	public AdminListCtrl() {
		try {
			adminList = (ArrayList<Admin>) SerializeDB.readSerializedObject(filename);
			
		}catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
	
	public ArrayList<Admin> getAdminList() {
		if (adminList ==null) {
			adminList= new ArrayList<Admin>();
		}
		return adminList;
	}

	public Admin getAdmin(int i) {
		return (Admin) adminList.get(i);
	}

	public void save() { // save the user list back into the file storing student data
		try {
			SerializeDB.writeSerializedObject(filename, adminList);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}