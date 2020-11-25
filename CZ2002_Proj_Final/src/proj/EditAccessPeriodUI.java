package proj;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EditAccessPeriodUI {
	public static final Scanner scan = new Scanner(System.in);
	public static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void editAccessPeriod(SchoolListCtrl schoolListControl) throws ParseException {
		System.out.println("Please Enter School ID (enter '0' to exit): ");
		String schoolID = scan.nextLine().toUpperCase();
		
		// checks if school exists, if not it will exit
		if (schoolListControl.checkSchool(schoolID) == false) {
			if (schoolID.equals("0")) {
				System.out.println("Operation cancelled.\nExiting back to home..");
				return;
			}
			System.out.println("School does not exist.");
			return;
		}
		
		//returns current access period of sch
		schoolListControl.getCurrentAccessPeriod(schoolID);
		
		
		// need to check if date entered is in valid format
		Date sd, ed;
		while(true) {
			try {
				System.out.println("\nPlease Enter Start Date in the format dd/MM/yyyy (enter '0' to exit): ");
				String stringsd = scan.nextLine();
				if (stringsd.equals("0")) {
					System.out.println("Operation cancelled.\nExiting back to home..");
					return;
				}
				sd = df.parse(stringsd);
				break;
			}
			catch(Exception e) {
				System.out.println("Invalid date, please try again.\n");
			}
		}
		
		while(true) {
			try {
				while (true) {
					System.out.println("Please Enter End Date in the format dd/MM/yyyy: ");
					String stringed = scan.nextLine();
					if (stringed.equals("0")) {
						System.out.println("Operation cancelled.\nExiting back to home..");
						return;
					}
					ed = df.parse(stringed);
					
					// check if end date is later than start date
					if (ed.after(sd)) {
						break;
					}
					else {
						System.out.println("Please enter a date later than start date.\n");
					}
				}
				break;
			}
			catch(Exception e) {
				System.out.println("Invalid date, please try again.\n");
			}
		}
		
		schoolListControl.editAccessPeriod(schoolID, sd, ed);
		schoolListControl.save();
		return;
	
	}
	
}