package proj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseListCtrl {
	// holds an arrayList of courses
	private static ArrayList<Course> courseList;
	// Filename of course list to be read from fileIO

	private String filename = "CourseList.txt";
	public static final Scanner sc = new Scanner(System.in);

	@SuppressWarnings("unchecked")
	public CourseListCtrl() {
		try {
			courseList = (ArrayList<Course>) SerializeDB.readSerializedObject(filename);
		} catch (Exception e) {
			System.out.println("Exception >>" + e.getMessage());
		}
	}

	// Get course list stored in CourseListControl class

	public ArrayList<Course> getCourseList() {
		if (courseList == null) {
			courseList = new ArrayList<Course>();
		}
		return courseList;
	}
	// Change this control class's courseList to new courseList

	public void setCourseList(ArrayList<Course> CourseList) {
		Collections.copy(CourseListCtrl.courseList, courseList);
	}

	// Get course from courseList, indexed by parameter
	public Course getCourse(int index) {
		return (Course) courseList.get(index);
	}

	// Adding new course to this control class's courseList
	public int addNewCourse(String courseName, String courseID) {
		for (int i = 0; i < courseList.size(); i++) {
			if (courseList.get(i).getCourseName().equals(courseName)) {
				System.out.println("Course already exists.");
				return -1;
			} else if (courseList.get(i).getCourseID().equals(courseID)) {
				System.out.println("Course ID already exists.");
				return -1;
			}
		}
		Course c1 = new Course(courseName, courseID);
		courseList.add(c1);
		return 0;
	}

	// Allow admin user to edit course stored in courseList
	public String editCourse(int choice, int courseIdChoice, String edited) {
		switch (choice) {
		case 1:
			String initialCourseName = courseList.get(courseIdChoice - 1).getCourseName();
			courseList.get(courseIdChoice - 1).setCourseName(edited);
			return initialCourseName;
		case 2:
			String initialCourseID = courseList.get(courseIdChoice - 1).getCourseID();
			courseList.get(courseIdChoice - 1).setCourseID(edited);
			return initialCourseID;
		default:
			return "Error";
		}
	}
	
	public String chooseCourse() {
		Scanner sc = new Scanner(System.in);
		String courseChoice = null;
		boolean k = true;

		while (k) {
			try {
				int count = 1;
				courseChoice = sc.nextLine().toUpperCase();
				for (int i = 0; i < courseList.size(); i++) {
					if (courseList.get(i).getCourseID().equals(courseChoice)) {
						k = false;
						break;
					} else if ((count) == courseList.size()) {
						System.out.println("Please re-enter valid course choice");
					} else {
						(count)++;
					}
				}
			} catch (InputMismatchException e) {
				System.out.print("Please re-enter valid course choice");
				sc.nextLine();
			}
		}

		return courseChoice;
	}

	public void deleteCourse(int choice) {
		courseList.remove(choice);
		return;
	}

	public int getCourseSize() {
		return courseList.size();
	}

	public static void printCourseDetails(int i) {
		if (courseList == null) {
			courseList = new ArrayList<Course>();
		} else if (courseList.isEmpty() == true) {
			System.out.println("There are no courses available." );
			return;
		}
		else {
				Course c = courseList.get(i);
				System.out.println("   " + (i + 1)+ "	" + c.getCourseID()
				+ "       " + c.getCourseName());
			}
	}
	
	public void printAllCourseDetails() {
		if (courseList == null) {
			courseList = new ArrayList<Course>();
			
		} else if (courseList.isEmpty() == true) {
			System.out.println("There are no courses available." );
			return;
		}
		else {
			System.out.println("\nList of Courses ------------------");
			System.out.println("\n   #	Course ID    Course Name ");
			System.out.println("===============================================================");
			for (int i=0; i<courseList.size(); i++) {
				printCourseDetails(i);
			}
		}
	}

	public void save() {
		try {
			SerializeDB.writeSerializedObject(filename, courseList);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}

	}

}
