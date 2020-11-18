package proj;

import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  

public class AccessPeriod {
	
	private Date startDate;
	private Date endDate;
	private Date now;
	
	private static Scanner scan = new Scanner(System.in);
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public AccessPeriod(String start, String end) {
		try {
			startDate = df.parse(start);
			endDate = df.parse(end);
			now = new Date();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Error!! Please Enter the Correct Date Format with /");
			System.out.println();
			startDate = null;
			endDate = null;
		}
		
	}
	
	public boolean canAccess() {
		    return !(now.before(startDate) || now.after(endDate));
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(String start) throws ParseException {
		startDate = df.parse(start);
	}

	public void setEndDate(String end) throws ParseException {
		endDate = df.parse(end);
	}

	public Date getNow() {
		return now;
	}
	
	public String toString() {
		return ("Access Period: " + startDate + " to " + endDate + ".");
	}
	
	
	
	
	

}
