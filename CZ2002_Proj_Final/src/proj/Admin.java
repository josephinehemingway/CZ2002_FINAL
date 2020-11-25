package proj;

import java.io.Serializable;
/**
 * Admin entity class
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 */

public class Admin implements Serializable {
	/**
	 * Account Username of the admin in the database
	 */
	private String adminID;
	
	/**
	 * Account Password for admin
	 */
	private String adminPW;
	
	/** 
	 * Email of admin
	 */
	private String adminEmail;
	
	/**
	 * Creates a new admin object with the following attributes
	 * @param aUser This admin's username
	 * @param aPW This admin's password
	 * @param aEmail This admin's email
	 */
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
