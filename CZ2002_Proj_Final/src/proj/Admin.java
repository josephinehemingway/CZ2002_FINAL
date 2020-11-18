package proj;

import java.io.Serializable;

public class Admin implements Serializable {
	private String adminID;
	private String adminPW;
	private String adminEmail;
	
	public Admin(String aUser, String aPW, String aEmail) {
		this.adminID = aUser;
		this.adminPW = aPW;
		this.adminEmail = aEmail;
	}
	
	public String getAdminID() {
		return adminID;
	}

	public String getAdminPW() {
		return adminPW;
	}
	
	public String getAdminEmail() {
		return adminEmail;
	}
	
	
	
	
	
}
