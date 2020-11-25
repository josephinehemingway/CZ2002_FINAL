package proj;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Represents the control for courseIndexList which holds an ArrayList of CourseIndexes
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-25
 */
public class CourseIndexListCtrl {
	/**
	 * ArrayList that stores list of Students
	 */
	private ArrayList<CourseIndex> courseIndexList;
	/**
	 * CourseID of a CourseIndex
	 */
	private String courseID;
	/**
	 * Filename of courseIndex list to be read from SerializeDB
	 */
	private String filename = "CourseIndexList.txt";
	/**
	 * Scanner to read user's input
	 */
	public static final Scanner sc = new Scanner(System.in);
	/**
	 * Reads in the list of courseIndexes from SerializeDB
	 */
	@SuppressWarnings("unchecked")
	public CourseIndexListCtrl() {
		try {
			courseIndexList = (ArrayList<CourseIndex>) SerializeDB.readSerializedObject(filename);
		} catch (Exception e) {
			System.out.println("Exception >>" + e.getMessage());
		}
	}
	/**
	 * Gets the courseIndex list stored in this CourseIndexListControl class
	 * 
	 * @return ArrayList of courseIndexes
	 */
	public ArrayList<CourseIndex> getCourseIndexList() {
		if (courseIndexList == null) {
			courseIndexList = new ArrayList<CourseIndex>();
		}
		return courseIndexList;
	}
	/**
	 * Gets the number of courseIndexes from this control class's list of courseIndexes
	 * @return number of course indexes in the courseIndexList
	 */
	public int getCourseIndexSize() {
		return courseIndexList.size();
	}
	/**
	 * Gets the coursename associated with the course Index by using its IndexID
	 * 
	 * @param index indexID of the courseIndex
	 * 
	 * @return The Course Name of the course Index
	 */
	public String getCourseNameFromIndex(int index) {
		for(CourseIndex c : courseIndexList) {
			if (c.getIndexID()==index) {
				return c.getCourseName();
			}
		}
		return null;
	}
	/**
	 * Gets an arraylist of course Indexes registered under a certain Course
	 * 
	 * @param courseID CourseID of the course
	 * 
	 * @return Course Indexes available in the current course
	 */
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
	/**
	 * Allows users to choose a valid courseIndex to register/edit
	 * 
	 * @param courseID The CourseID of the course to which the indexes are being chosen.
	 * 
	 * @return A valid IndexId of CourseIndex.
	 */
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
					} else if (indexChoice == 0) {
						System.out.println("Operation cancelled. ");
						return -1;
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
	/**
	 * Checks if the courseIndex chosen is within the courseIndexList.
	 * 
	 * @return IndexID of a valid course Index.
	 */
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
	/**
	 * Allows admin user to add new courseIndex under a certain Course.
	 * @param courseName CourseName of the course we are trying to add indexes to.
	 * @param courseID CourseId ot the course.
	 * @param indexID The new Index of the course we are trying to add.
	 * @param schedule The schedule of the new Index.
	 * @param numOfVacancies The number of vacancy of the new Index.
	 * 
	 * @return 1 if the new Index is successfully added, 0 if the index already exist.
	 */
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
	/**
	 * Allows admin to edit the details of a courseIndex.
	 * 
	 * @param choice 1 = editing indexID 2= editing Vacancy
	 * @param indexIdChoice The index which the admin wishes to edit.
	 */
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
	/**
	 * Allows admin user to edit CourseName of course Indexes stored in the courseIndexList.
	 * @param editedCourseName The new CourseName
	 * @param initialCourseName The initial courseName we wish to change.
	 */
	public void editCourseName(String editedCourseName, String initialCourseName) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getCourseName().equals(initialCourseName)) {
				courseIndexList.get(i).setCourseName(editedCourseName);
			}
		}
	}
	/**
	 *Allows admin user to edit CourseID of course Indexes stored in the courseIndexList.
	 * @param editedCourseID The new courseID.
	 * @param initialCourseID the initial courseID we wish to edit.
	 */
	public void editCourseID(String editedCourseID, String initialCourseID) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getCourseID().equals(initialCourseID)) {
				courseIndexList.get(i).setCourseID(editedCourseID);
			}
		}
	}
	/**
	 * Allows admin user to delete a courseIndex from the courseIndexList.
	 * @param index The index of the courseIndex object we wish to remove.
	 */
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
	/**
	 * Method that subtracts vacancy of a course Index when called.
	 * @param courseID The courseId of the course Index.
	 * @param courseIndexID The courseIndexID of the course Index.
	 */
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
					//System.out.println("Course vacancy is now: " + c.getCurrentVacancy());
					return;
				}
			}
		}
	}
	/**
	 * Method to add vacancies to a courseIndex object in courseIndexList.
	 * @param courseID The courseId of the courseIndex.
	 * @param courseIndexID The courseIndexID of the courseIndex.
	 */
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
					//System.out.println("Course vacancy is now: " + c.getCurrentVacancy());
					return;
				}
			}
		}
	}
	/**
	 * Prints all course Indexes in courseIndex list as well as their ID, name, index, Vacancy and number of AUs.
	 */
	public void printAllCourseInfo() {
		if (courseIndexList == null) {
			courseIndexList = new ArrayList<CourseIndex>();

		} else if (courseIndexList.isEmpty() == true) {
			System.out.println("There is no course index for " + courseID);
			return;
		} else {
			System.out.println("\nAll General Course Information ------------------");
			System.out.println("====================================================================================");
			System.out.println("  CourseID      CourseName  	 			      Index    Vacancy	AU");
			System.out.println("====================================================================================");
			//max coursename length = 45
			int maxCourseLength = 45;
			for (CourseIndex i : this.courseIndexList) {
				System.out.print("   " + i.getCourseID() + "       " + i.getCourseName());
			    for(int j=0;j <= maxCourseLength - i.getCourseName().length(); j++)
			    {
			    	   System.out.print(" ");
			    }
			    System.out.print(i.getIndexID()+ "	" + i.getCurrentVacancyOverInitial()
			    							   + "	" + CourseIndex.ACADEMIC_UNITS +"\n");
			}
		}
	}
	/**
	 * Prints the information of a CourseIndex object
	 * 
	 * @param courseID The courseID of the courseIndex.
	 * @param courseIndexID THe indexID of the courseIndex.
	 */
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
					System.out.println("Index Vacancies: " + c.getCurrentVacancyOverInitial()); 
			}
		}
		}
	}
	/**
	 * Prints the vacancies available in a courseIndex object.
	 * 
	 * @param courseID The courseId of the courseIndex.
	 * @param courseIndexID The indexID of the courseIndex.
	 */
	public void printVacanciesForCourseIndex(String courseID, int courseIndexID) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getIndexID() == courseIndexID) {
				System.out.println("Current Vacancy for " + courseIndexID + " is: "
						+ courseIndexList.get(i).getCurrentVacancyOverInitial());
			}
		}

	}
	/**
	 * Prints all the courseIndexes and their information under a certain course.
	 * 
	 * @param courseID The courseId of the course.
	 */
	public void printIndexesInfoUnderCourse(String courseID) {
		int count = 0;
		System.out.println("\n============================================");
		System.out.println("  Index No     Vacancy	  Waiting List Size");
		System.out.println("============================================");
		
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getCourseID().equals(courseID)) {
				CourseIndex s = courseIndexList.get(i);
				System.out.println("    " + s.getIndexID() + "	" + s.getCurrentVacancyOverInitial() + "		" + s.getWaitingList().size());
			} else {
				count++;
			}
		}
		if (count == courseIndexList.size() - 1) {
			System.out.println("There are no indexes in this course");
		}
	}
	/**
	 * Prints ArrayList of students registered under a courseIndex from its ArrayList of Students.
	 * 
	 * @param courseIndex The indexID the courseIndex.
	 */
	public void printStudentsUnderIndex(int courseIndex) {
		System.out.println("\nStudents registered in Course Index: " + courseIndex + " ------------");
		System.out.println("\n===============================================");
		System.out.println(" Student Name       Gender	Nationality");
		System.out.println("===============================================");
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getIndexID() == courseIndex) {
				CourseIndex curIndex = courseIndexList.get(i);
				ArrayList<Student> studArr = curIndex.getStudent();
				
				int count = 0;
				int maxNameLength = 20;
				for (Student s : studArr) {
					System.out.print(" " + s.getName());
				    for(int j=0;j <= maxNameLength - s.getName().length(); j++)
				    {
				    	   System.out.print(" ");
				    }
				    System.out.print(s.getGender()+ "		" + s.getNationality() +"\n");
				    count++;
				}
				
				if (count == curIndex.getStudent().size() - 1) {
					System.out.println("There are no students under in this course index!");
				}
			}
		}
	}
	/**
	 * Prints all students registered under a certain Course.
	 * 
	 * @param courseID The courseID of the Course.
	 */
	public void printStudentsUnderCourse(String courseID) {
		System.out.println("\nStudents registered in Course: " + courseID + " ------------");
		System.out.println("\n=============================================================");
		System.out.println(" CourseIndex	  Student Name       Gender	Nationality");
		System.out.println("=============================================================");
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getCourseID().equals(courseID)) {
				CourseIndex curIndex = courseIndexList.get(i);
				ArrayList<Student> studArr = curIndex.getStudent();
				
				int count = 0;
				int maxNameLength = 20;
				for (Student s : studArr) {
					System.out.print("     " + curIndex.getIndexID() + "	  " + s.getName());
				    for(int j=0;j <= maxNameLength - s.getName().length(); j++)
				    {
				    	   System.out.print(" ");
				    }
				    System.out.print(s.getGender()+ "	" + s.getNationality() +"\n");
				    count++;
				}
				
				if (count == curIndex.getStudent().size() - 1) {
					System.out.println("There are no students under in this course index!");
				}
			}
		}
	}
	/**
	 * Prints vacancy of a courseIndex in current vacancy/max capacity.
	 * 
	 * @param indexID The indexID of  courseIndex.
	 */
	public void printAvailableVacancyforIndex(int indexID) {
		for (int i = 0; i < courseIndexList.size(); i++) {
			if (courseIndexList.get(i).getIndexID() == indexID) {
				System.out.println("Available Vacancy for " + indexID + " : "
						+ courseIndexList.get(i).getCurrentVacancyOverInitial());
			}
		}
	}
	/**
	 * When called, save the current edited courseIndexList object to SerializeDB.
	 */
	public void save() {
		try {
			SerializeDB.writeSerializedObject(filename, courseIndexList);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}
