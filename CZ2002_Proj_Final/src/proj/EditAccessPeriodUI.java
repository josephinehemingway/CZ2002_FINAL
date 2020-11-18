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
		System.out.println("Please Enter School ID: ");
		String schoolID = scan.nextLine().toUpperCase();
		
		// checks if school exists, if not it will exit
		if (schoolListControl.checkSchool(schoolID) == false) {
			System.out.println("School not found");
			return;
		}
		
		//returns current access period of sch
		schoolListControl.getCurrentAccessPeriod(schoolID);
		
		
		// need to check if date entered is in valid format
		Date sd, ed;
		while(true) {
			try {
				System.out.println("\nPlease Enter Start Date in the format dd/MM/yyyy: ");
				String stringsd = scan.nextLine();
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
		return;
	
	}
	
}