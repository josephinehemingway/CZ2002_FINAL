package proj;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SchoolListCtrl {
	private ArrayList<School> schoolList;
	// scanner object for reading user input
	public static final Scanner sc = new Scanner(System.in);
	private String filename = "SchoolList.txt"; // file storing all the user acc created

	@SuppressWarnings("unchecked")
	public SchoolListCtrl() {
		try {
			schoolList = (ArrayList<School>) SerializeDB.readSerializedObject(filename);

		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}

	public ArrayList<School> getSchoolList() {
		if (schoolList == null) {
			schoolList = new ArrayList<School>();
		}
		return schoolList;
	}


	public School getSchool(int i) {
		return (School) schoolList.get(i);
	}

	// check if sch exists
	public boolean checkSchool(String schoolID) {
		for (int i = 0; i < schoolList.size(); i++) {
			School sch = schoolList.get(i);

			if (sch.getSchoolID().equals(schoolID)) {
				return true;
			}
		}
		return false;
	}

	public int getSchoolSize() {
		return schoolList.size();
	}

	public String chooseSchool() {
		String schoolChoice = null;
		boolean k = true;

		while (k) {
			try {
				int count = 1;
				schoolChoice = sc.nextLine().toUpperCase();
				for (int i = 0; i < schoolList.size(); i++) {
					if (schoolList.get(i).getSchoolID().equals(schoolChoice)) {
						k = false;
						break;
					} else if ((count) == schoolList.size()) {
						System.out.println("Please re-enter valid schoolID");

					} else {
						(count)++;
					}
				}
			} catch (InputMismatchException e) {
				System.out.print("Please re-enter valid schoolID");
			}
		}

		return schoolChoice;
	}

	public void getCurrentAccessPeriod(String schoolID) {
		for (int i = 0; i < schoolList.size(); i++) {
			School sch = schoolList.get(i);

			if (sch.getSchoolID().equals(schoolID)) {
				if (sch.getStartDate() != null && sch.getStartDate() != null) {
					System.out.println("\nCurrent Access Period for: " + sch.getName());
					System.out.println("Start Date: " + sch.getStartDate());
					System.out.println("End Date: " + sch.getEndDate());
				} else
					System.out.println("Access Period not yet set.");
				return;
			}
		}
		System.out.println("School not found.");
		return;
	}

	public void editAccessPeriod(String schoolID, Date startDate, Date endDate) throws ParseException {

		for (int i = 0; i < schoolList.size(); i++) {
			School sch = schoolList.get(i);
			if (sch.getSchoolID().equals(schoolID)) {

				sch.setStartDate(startDate);
				sch.setEndDate(endDate);

				System.out.println("\nAccess Period edited. ");
				System.out.println("\nNew Access Period for: " + sch.getName());
				System.out.println("Start Date: " + sch.getStartDate());
				System.out.println("End Date: " + sch.getEndDate());
				return;
			}
		}
		System.out.println("School not found.");
	}

	

	public void addCourseToSchool(String schoolID, Course selectedCourse) {
		// check if course is already in that school
		// add course into school
		for (int j = 0; j < schoolList.size(); j++) {
			if (schoolList.get(j).getSchoolID().equals(schoolID)) {
				if (schoolList.get(j).getCourse().contains(selectedCourse)) {
					System.out.println("Course already belongs to this school");
					break;
				} else {
					schoolList.get(j).addCourse(selectedCourse);
					System.out.println("Course is now in " + schoolList.get(j));
				}
			}

		}

	}

	public void removeCourseFromSchool(String schoolID, Course selectedCourse) {
		for (int j = 0; j < schoolList.size(); j++) {
			if (schoolList.get(j).getSchoolID().equals(schoolID)) {
				if (schoolList.get(j).getCourse().contains(selectedCourse)) {
					schoolList.get(j).getCourse().remove(selectedCourse);
					System.out.println("Course is removed from " + schoolList.get(j).getSchoolID());
					break;
				}

			}

		}

	}
	public String getSchoolFromCourse(String courseID) {
		for (int i=0; i<schoolList.size();i++) {
			if ( schoolList.get(i).checkCourseUnderSch(courseID)==1) {
				return schoolList.get(i).getSchoolID();
			}
		}
		return null;
	}
	
	public void printSchoolList() {
		System.out.println("List of Schools:");
		for (int i = 0; i < schoolList.size(); i++) {
			System.out.println(schoolList.get(i).getSchoolID());
		}
	}
	
	

	public void save() { // save the user list back into the file storing student data
		try {
			SerializeDB.writeSerializedObject(filename, schoolList);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}

}
