package proj;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminUI {

	private ArrayList<Admin> adminList; // user list containing all the user acc created in this app
	private String filename = "AdminList.txt"; // file storing all the user acc created

	public static Scanner sc = new Scanner(System.in);
	

	public static void console() throws ParseException {
		int choice = 0;

		do {
			StudentListCtrl studentListControl = new StudentListCtrl();
			SchoolListCtrl schoolListControl = new SchoolListCtrl();
			CourseListCtrl courseListControl = new CourseListCtrl();
			CourseIndexListCtrl courseIndexListControl = new CourseIndexListCtrl();
			UserValidation userValidation = new UserValidation();

			System.out.println("-------------------------------------------------------------------");
			System.out.println("\n- ADMIN CONSOLE -");
			System.out.println("Enter your choice: ");
			System.out.println("1. Edit student access period by School ");
			System.out.println("2. Add/Edit/Remove a student");
			System.out.println("3. Add/Update a course");
			System.out.println("4. Check available slot for an index number");
			System.out.println("5. Print student list");
			System.out.println("6. Logout");

			while (true) {
				try {
					while (true) {
						choice = sc.nextInt();
						if (choice >= 1 && choice <= 6) {
							break;
						} else {
							System.out.println("Invalid choice, please enter integer between 1 and 6. ");
						}
					}
					break;
				} catch (Exception e) {
					System.out.println("Invalid choice, please enter integer between 1 and 6. ");
					sc = new Scanner(System.in);
				}
			}

			switch (choice) {
			case 1:
				System.out.println("\nEditing Access Period ------------------");
				EditAccessPeriodUI.editAccessPeriod(schoolListControl);
				schoolListControl.save();
				break;

			case 2:
				System.out.println("\nAdding/Editing/Remove a Student ------------------");
				System.out.println("Enter your choice: ");
				System.out.println("1. Add a student");
				System.out.println("2. Edit a student");
				System.out.println("3. Delete a student");
				System.out.println("4. Return to Main Admin Console");
				int choice1;
				while (true) {
					try {
						while (true) {
							choice1 = sc.nextInt();
							if (choice1 >= 1 && choice1 <= 4) {
								break;
							} else {
								System.out.println("Invalid choice, please enter integer between 1 and 4. ");
							}
						}
						break;
					} catch (Exception e) {
						System.out.println("Invalid choice, please enter integer between 1 and 4. ");
						sc = new Scanner(System.in);
					}
				}

				switch (choice1) {
				case 1:
					System.out.println("\nAdding new student ------------------");
					EditStudentAdminUI.addStudent(studentListControl);
					studentListControl.save();
					break;

				case 2:
					System.out.println("\nEditing existing student ------------------");
					EditStudentAdminUI.editStudent(studentListControl, userValidation);
					studentListControl.save();
					break;

				case 3:
					System.out.println("\nDeleting existing student ------------------");
					EditStudentAdminUI.deleteStudent(studentListControl);
					studentListControl.save();
					break;
				default:
					break;
				}
				break;

			case 3:
				System.out.println("\nAdding/Editing/Remove a Course ------------------");
				System.out.println("Enter your choice: ");
				System.out.println("1. Add a course");
				System.out.println("2. Edit a course");
				System.out.println("3. Delete a course");
				System.out.println("4. Return to Main Admin Console");
				int choice2;

				while (true) {
					try {
						while (true) {
							choice2 = sc.nextInt();
							if (choice2 >= 1 && choice2 <= 4) {
								break;
							} else {
								System.out.println("Invalid choice, please enter integer between 1 and 4. ");
							}
						}
						break;
					} catch (Exception e) {
						System.out.println("Invalid choice, please enter integer between 1 and 4. ");
						sc = new Scanner(System.in);
					}
				}
				switch (choice2) {
				case 1:

					System.out.println("\nAdding Course ------------------");
					int counter = EditCourseUI.addCourse(courseListControl);
					if (counter == 0) {
						EditCourseIndexUI.addCourseIndex(courseIndexListControl);
						schoolListControl.printSchoolList();
						System.out.println("\nEnter what school the course is under: ");
						String chosenSchoolID = schoolListControl.chooseSchool();
						EditSchoolUI.addCourseToSchool(schoolListControl, chosenSchoolID, courseListControl,EditCourseIndexUI.courseID);
						schoolListControl.save();
						courseListControl.save();
						courseIndexListControl.save();
					} else {
						break;
					}
					courseIndexListControl.printAllCourseInfo();
					break;
				case 2:
					System.out.println("\nEditing Course ------------------");
					courseListControl.printAllCourseDetails();
					System.out.println("Enter Course # to edit: ");
					int courseChoice;
					while (true) {
						try {
							courseChoice = sc.nextInt();
							if (courseChoice < 1 || courseChoice > courseListControl.getCourseList().size()) {
								System.out.println("Please re-enter valid course #");
							} else {
								break;
							}
						} catch (InputMismatchException e) {
							System.out.print("Please re-enter valid course #");
							sc.nextLine();
						}
					}
					String chosenCourseID = courseListControl.getCourseList().get(courseChoice - 1).getCourseID();
					System.out.println("\nChoose what you want to edit");
					System.out.println("1. Edit current Course Name/ID");
					System.out.println("2. Edit a course index in current course");
					System.out.println("3. Change School of the current course");
					System.out.println("4. Quit");
					int userchoice;
					while (true) {
						try {
							while (true) {
								userchoice = sc.nextInt();
								if (userchoice >= 1 && userchoice <= 4) {
									break;
								} else {
									System.out.println("Invalid choice, please enter integer 1 or 4. ");
								}
							}
							break;
						} catch (Exception e) {
							System.out.println("Invalid choice, please enter integer 1 or 4. ");
							sc = new Scanner(System.in);
						}
					}
					

					switch (userchoice) {
					case 1:
						System.out.println("\nEditing current Course Name/ID ------------------");
						EditCourseUI.editCourse(courseIndexListControl, courseListControl, courseChoice); // changing name
						courseListControl.save();
						courseIndexListControl.save();
						courseListControl.printAllCourseDetails();
						break;
					case 2:
						System.out.println("\nEditing Course Index For Current Course ------------------");
						System.out.println("\nChoose what you want to edit for Course " + chosenCourseID);
						System.out.println("1. Add Index");
						System.out.println("2. Update Index");
						System.out.println("3. Delete Index");
						System.out.println("4. Quit");

						EditCourseIndexUI.setAttributes(chosenCourseID, courseIndexListControl);
						int indexEditChoice;
						while (true) {
							try {
								while (true) {
									indexEditChoice = sc.nextInt();
									if (indexEditChoice >= 1 && indexEditChoice <= 4) {
										break;
									} else {
										System.out.println("Invalid choice, please enter integer 1 or 4. ");
									}
								}
								break;
							} catch (Exception e) {
								System.out.println("Invalid choice, please enter integer 1 or 4. ");
								sc = new Scanner(System.in);
							}
						}
						

						int indexChoice = 0;
						switch (indexEditChoice) {
						case 1:
							System.out.println("\nAdding Course Index ------------------");

							EditCourseIndexUI.addCourseIndex(courseIndexListControl);
							courseIndexListControl.save();
							courseIndexListControl.printIndexesInfoUnderCourse(chosenCourseID);
							break;
						case 2:
							System.out.println("\nEditing Course Index ------------------");
//							courseIndexListControl.printAllCourseInfo();
							
							courseIndexListControl.printIndexesInfoUnderCourse(chosenCourseID);
							
							System.out.println("Enter Index Number to edit: ");
							indexChoice = courseIndexListControl.chooseCourseIndex(chosenCourseID);
							EditCourseIndexUI.editCourseIndex(courseIndexListControl, indexChoice);
							courseIndexListControl.save();
							courseIndexListControl.printIndexesInfoUnderCourse(chosenCourseID);
							break;

						case 3:
							int indexChoice1 = 0;
							System.out.println("\nDeleting Course Index ------------------");
							courseIndexListControl.printIndexesInfoUnderCourse(chosenCourseID);
							System.out.println("Enter Index Number to delete: ");
							indexChoice = courseIndexListControl.chooseCourseIndex(chosenCourseID);
							indexChoice1 = indexChoice;
							EditCourseIndexUI.deleteCourseIndex(courseIndexListControl, indexChoice1);
							courseIndexListControl.save();
							courseIndexListControl.printIndexesInfoUnderCourse(chosenCourseID);
							break;

						default:
							break;
						}
						break;

					case 3:
						EditSchoolUI.removeCourseFromSchool(schoolListControl, courseListControl, chosenCourseID);
						schoolListControl.printSchoolList();
						System.out.println("Enter new School: ");
						String chosenSchoolID = schoolListControl.chooseSchool();
						EditSchoolUI.addCourseToSchool(schoolListControl, chosenSchoolID, courseListControl,
								chosenCourseID);
						schoolListControl.save();
						courseListControl.save();
						break;
						
					default: 
						break;
					}
					break;

				case 3:
					System.out.println("\nDeleting Course ------------------");
					courseListControl.printAllCourseDetails();
					EditCourseUI.deleteCourse(courseListControl);
					courseListControl.save();
					courseIndexListControl.save();
					courseListControl.printAllCourseDetails();
					break;
				}
				break;

			case 4:

				System.out.println("\nChecking Available slot for Course Index---------");
				courseIndexListControl.printAllCourseInfo();
				System.out.println("\nEnter Index Number: ");
				int chosenIndex = courseIndexListControl.checkCourseIndex();
				courseIndexListControl.printAvailableVacancyforIndex(chosenIndex);
				studentListControl.save();

				break;
			case 5:
				System.out.println("\nPrint student list ------------------");
				System.out.println("1. Print student list by Course Index");
				System.out.println("2. Print student list by Course");;
				System.out.println("3. Print student details by username");
				System.out.println("4. Print all student details");
			
				System.out.println("5. Exit");

				int choice3;
				while (true) {
					try {
						while (true) {
							choice3 = sc.nextInt();
							if (choice3 >= 1 && choice3 <= 5) {
								break;
							} else {
								System.out.println("Invalid choice, please enter integer between 1 and 5. ");
							}
						}
						break;
					} catch (Exception e) {
						System.out.println("Invalid choice, please enter integer between 1 and 5. ");
						sc = new Scanner(System.in);
					}
				}
				switch (choice3) {
				case 1:
					courseIndexListControl.printAllCourseInfo();
					System.out.println("\nEnter Index Number: ");
					int indexChoice3=courseIndexListControl.checkCourseIndex();
					courseIndexListControl.printStudentsUnderIndex(indexChoice3);
					break;
				case 2:
					courseListControl.printAllCourseDetails();
					System.out.println("\nEnter the Course # :");
					int courseChoice=0;
					while (true) {
						try {
							courseChoice = sc.nextInt();
							if (courseChoice < 1 || courseChoice > courseListControl.getCourseList().size()) {
								System.out.println("Please re-enter valid course #");
							} else {
								break;
							}
						} catch (InputMismatchException e) {
							System.out.print("Please re-enter valid course #");
							sc.nextLine();
						}
					}
					String chosenCourseID1 = courseListControl.getCourseList().get(courseChoice - 1).getCourseID();
					courseIndexListControl.printStudentsUnderCourse(chosenCourseID1);
					
					break;
				case 3:
					System.out.println("\nEnter student username: ");
					String user = sc.next().toUpperCase();
					studentListControl.printStudentListByUsername(user);
					break;
				case 4:
					System.out.println("\nPrinting all student details ------------");
					studentListControl.printAllStudentDetails();
				case 5:
				}

				break;
			case 6:
				System.out.println("Logging out of admin console.");
				System.out.println("");
				studentListControl.save();
				schoolListControl.save();
				break;
			default:
				System.out.println("Invalid input!");
				break;
			}


		} while (choice!=6);

	}

}

