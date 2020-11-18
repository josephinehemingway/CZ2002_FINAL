package proj;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseIndexListCtrl {
// holds arrayList of course indexes
	private ArrayList<CourseIndex> courseIndexList;
	// file name of index list to be read from fileIO

	// course code of this courseIndexList
	private String courseID;

	private String filename = "CourseIndexList.txt";

	// scanner object for reading user input
	public static final Scanner sc = new Scanner(System.in);

	// Reads in list of course indexes from fileIO.

	@SuppressWarnings("unchecked")
	public CourseIndexListCtrl() {
		try {
			courseIndexList = (ArrayList<CourseIndex>) SerializeDB.readSerializedObject(filename);
		} catch (Exception e) {
			System.out.println("Exception >>" + e.getMessage());
		}
	}
	
	
	public ArrayList<CourseIndex> getCourseIndexList() {
		if (courseIndexList == null) {
			courseIndexList = new ArrayList<CourseIndex>();
		}
		return courseIndexList;
	}
	
	public ArrayList<CourseIndex> getStaticCourseIndexList() {
		if (courseIndexList == null) {
			courseIndexList = new ArrayList<CourseIndex>();
		}
		return courseIndexList;
	}
	
	public int getCourseIndexSize() {
		return courseIndexList.size();
	}
	
	public String getCourseNameFromIndex(int index) {
		for(CourseIndex c : courseIndexList) {
			if (c.getIndexID()==index) {
				return c.getCourseName();
			}
		}
		return null;
	}
	
	public ArrayList<Integer> arrayIndexUnderCourse(String courseID) {
		int count = 0;
		ArrayList<Integer> indexUnderCourse = new ArrayList<Integer>();
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getCourseID().equals(courseID)) {
				indexUnderCourse.add(courseIndexList.get(i).getIndexID());
			} else {
				count++;
			}
		}
		if (count == courseIndexList.size() - 1) {
			System.out.println("There are no indexes in this course.");
		}
		return indexUnderCourse;
	}

	
	public int chooseCourseIndex(String courseID) {
		int indexChoice = 0;
		boolean k = true;

		while (k) {
			try {
				int count = 1;
				indexChoice = sc.nextInt();
				for (int i = 0; i < arrayIndexUnderCourse(courseID).size(); i++) {
					if (arrayIndexUnderCourse(courseID).get(i) == indexChoice) {
						k = false;
						break;
					} else if ((count) == arrayIndexUnderCourse(courseID).size()) {
						System.out.println("Please re-enter valid index choice:");

					} else {
						(count)++;
					}
				}
			} catch (InputMismatchException e) {
				System.out.print("Please re-enter valid index choice: ");
				sc.nextLine();
			}
		}

		return indexChoice;
	}

	public int checkCourseIndex() {
		int indexChoice1 = 0;
		boolean k = true;
		while (k) {
			try {
				int count = 1;
				indexChoice1 = sc.nextInt();
				for (int i = 0; i < courseIndexList.size(); i++) {
					if (courseIndexList.get(i).getIndexID() == indexChoice1) {
						k = false;
						break;
					} else if ((count) == courseIndexList.size()) {
						System.out.println("Please re-enter valid index choice");
					} else {
						(count)++;
					}
				}
			} catch (InputMismatchException e) {
				System.out.print("Please re-enter valid index choice");
				sc.nextLine();
			}
		}

		return indexChoice1;
	}
	
	public int addNewCourseIndex(String courseName, String courseID, int indexID, Schedule schedule,
			int numOfVacancies) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getIndexID() == indexID) {
				System.out.println("Course Index already exist.");
				return 0;
			}
		}
		CourseIndex c1 = new CourseIndex(courseName, courseID, indexID, schedule, numOfVacancies);
		courseIndexList.add(c1);
		return 1;
	}

	public void editCourseIndex(int choice, int indexIdChoice) {
		switch (choice) {
		case 1:
			System.out.println("\nEditing Index ID ------------------");
			System.out.println("Enter new Index ID: ");
			int editedId = sc.nextInt();
			for (int i = 0; i < courseIndexList.size(); i++) {
				if (courseIndexList.get(i).getIndexID() == indexIdChoice) {
					courseIndexList.get(i).setIndexID(editedId);
				}
			}

			break;
		case 2:
			System.out.println("\nEditing Index Vacancies ------------------");
			for (int j = 0; j < courseIndexList.size(); j++) {
				if (courseIndexList.get(j).getIndexID() == indexIdChoice) {
					System.out
							.println("Current Max Vacancy of course is: " + courseIndexList.get(j).getNumOfVacancies());
				}
			}
			System.out.println("Enter new Index Vacancy: ");
			int editedVacancies = sc.nextInt();
			for (int i = 0; i < courseIndexList.size(); i++) {
				if (courseIndexList.get(i).getIndexID() == indexIdChoice) {
					int initialVacancy = courseIndexList.get(i).getNumOfVacancies();
					courseIndexList.get(i).setNumOfVacancies(editedVacancies);
					int initialCurVacancy = courseIndexList.get(i).getCurrentVacancy();
					int differenceinVacancy = editedVacancies - initialVacancy;
					courseIndexList.get(i).setCurrentVacancy(initialCurVacancy + differenceinVacancy);
					System.out.println("Available vacancy to course is : " + courseIndexList.get(i).getCurrentVacancy());
					System.out.println("Edited Max Vacancy of course to: " + courseIndexList.get(i).getNumOfVacancies());
				}
			}
			break;
		default:
		}
		System.out.println("Changes Made!");
	}
	
	public void editCourseName(String editedCourseName, String initialCourseName) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getCourseName().equals(initialCourseName)) {
				courseIndexList.get(i).setCourseName(editedCourseName);
			}
		}
	}

	public void editCourseID(String editedCourseID, String initialCourseID) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getCourseID().equals(initialCourseID)) {
				courseIndexList.get(i).setCourseID(editedCourseID);
			}
		}
	}
	
	public void deleteCourseIndex(int index) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getIndexID() == index) {
				courseIndexList.remove(i);
				System.out.println("Changes Made!");
				return;
			}
		}
		System.out.println("Course Index not found.");
	}

	public void subtractVacancy(String courseID, int courseIndexID) {
		if (courseIndexList.isEmpty() == true) {
			System.out.println("There is no index for " + courseID + ".");
			return;
		}
		for (int i = 0; i < courseIndexList.size(); i++) {
			CourseIndex c = courseIndexList.get(i);
			if (c.getCourseID().equals(courseID)) {
				if (c.getIndexID() == courseIndexID) {
					c.minusVacancy();
					System.out.println("Course vacancy is now: " + c.getCurrentVacancy());
					return;
				}
			}
		}
	}

	public void addToVacancy(String courseID, int courseIndexID) {
		if (courseIndexList.isEmpty() == true) {
			System.out.println("There is no index for " + courseID + ".");
			return;
		}
		for (int i = 0; i < courseIndexList.size(); i++) {
			CourseIndex c = courseIndexList.get(i);
			if (c.getCourseID().equals(courseID)) {
				if (c.getIndexID() == courseIndexID) {
					c.addToVacancy();
					System.out.println("Course vacancy is now: " + c.getCurrentVacancy());
					return;
				}
			}
		}
	}
	

	public void printAllCourseGeneralInfo() {
		if (courseIndexList == null) {
			courseIndexList = new ArrayList<CourseIndex>();
		} else if (courseIndexList.isEmpty() == true) {
			System.out.println("There is no course index for " + courseID);
			return;
		}

		else {
			for (CourseIndex i : this.courseIndexList) {
				System.out.println();
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("CourseName: " + i.getCourseName());
				System.out.println("CourseID: " + i.getCourseID());
				System.out.println("Course Index: " + i.getIndexID());
				System.out.println("Index Vacancies: " + i.getCurrentVacancyOverInitial());
			}

		}
	}
	public void printAllCourseInfo() {
		if (courseIndexList == null) {
			courseIndexList = new ArrayList<CourseIndex>();

		} else if (courseIndexList.isEmpty() == true) {
			System.out.println("There is no course index for " + courseID);
			return;
		} else {
			System.out.println("\nAll General Course Information ------------------");
			System.out.println("\n   CourseID   CourseName  	 Index  Vacancy ");
			System.out.println("===============================================");
			for (CourseIndex i : this.courseIndexList) {
				System.out.println("   " + i.getCourseID() + "       " + i.getCourseName() + "		" + i.getIndexID()
						+ "	" + i.getCurrentVacancyOverInitial());
			}
		}
	}
	
	public void printCourseIndexInfo(String courseID, int courseIndexID) {
		if (courseIndexList.isEmpty() == true) {
			System.out.println("There is no course index for " + courseID + ".");
			return;
		}
		for (int i = 0; i < courseIndexList.size(); i++) {
			CourseIndex c = courseIndexList.get(i);
			if (c.getCourseID().equals(courseID)) {
				if (c.getIndexID() == courseIndexID) {
					System.out.println();
					System.out.println("CourseID: " + courseID);
					System.out.println("Course Index: " + courseIndexID);
					System.out.println("Index Schedule: \n" + c.getSchedule());
					System.out.println("Index Vacancies: " + c.getCurrentVacancyOverInitial()); // was getNumOfVacancies
//					System.out.println("Students Enrolled in Index: " + c.getStudent());
//					System.out.println("Students on waitlist for Index: " + c.getWaitingList());
				}
			}
		}
	}

	public void printVacanciesForCourseIndex(String courseID, int courseIndexID) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getIndexID() == courseIndexID) {
				System.out.println("Current Vacancy for " + courseIndexID + " is: "
						+ courseIndexList.get(i).getCurrentVacancyOverInitial());
			}
		}

	}

	public void printIndexesUnderCourse(String courseID) {
		int count = 0;

		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getCourseID().equals(courseID)) {
				System.out.println(courseIndexList.get(i).getIndexID());
			} else {
				count++;
			}
		}
		if (count == courseIndexList.size() - 1) {
			System.out.println("There are no indexes in this course");
		}
	}
	
	public void printStudentsUnderIndex(int courseIndex) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getIndexID() == courseIndex) {
				CourseIndex curIndex = courseIndexList.get(i);
				System.out.println(curIndex.getStudent());
			}
		}
	}

	public void printStudentsUnderCourse(String courseID) {
		System.out.println("\n Students registered in Course " + courseID + "------------");
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getCourseID().equals(courseID)) {
				CourseIndex curIndex = courseIndexList.get(i);
				System.out.println(curIndex.getStudent());
			}
		}
	}

	public void printAvailableVacancyforIndex(int indexID) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getIndexID() == indexID) {
				System.out.println("Available Vacancy for " + indexID + " : "
						+ courseIndexList.get(i).getCurrentVacancyOverInitial());
			}
		}
	}

	public void save() {
		try {
			SerializeDB.writeSerializedObject(filename, courseIndexList);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}
