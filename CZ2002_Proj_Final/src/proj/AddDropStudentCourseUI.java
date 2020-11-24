package proj;

import java.util.Scanner;
/**
 * Represents the interface for students to add, drop courses and change or swap courseIndexes with a peer.
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 */
public class AddDropStudentCourseUI {

	/**
	 * Method in the StudentUI for student to choose and register for courses
	 * Student will input their course choice and index choices here
	 * 
	 * @param username Username of student who logged into the console via mainConsole
	 * @param courseListControl courseList control object that holds the course list and their details
	 * @param courseIndexListControl courseIndexList control object that holds the courseIndex list and their details
	 * @param studentCourseListControl studentCourseList control object that holds the student's registered courses details 
	 * and the list of students in each course 
	 * @param studentListControl studentList control object that holds the student list and their details
	 */
	public static void addCourse(String username, CourseListCtrl courseListControl,
			CourseIndexListCtrl courseIndexListControl, StudentCourseListCtrl studentCourseListControl,
			StudentListCtrl studentListControl) {
		Scanner scan = new Scanner(System.in);
		
		//Print list of courses
		courseListControl.printAllCourseDetails();

		// enter course id
		System.out.println("\nEnter choice of course (eg CZ2004): ");
		String course = courseListControl.chooseCourse();

		if (studentCourseListControl.checkIfRegistered(username, course) == false) {
			// print index in course
			System.out.println("\nCourse Indexes for Course " + course + " ------------------");
			System.out.println("Course Index/Vacancy/Waiting List ");
			System.out.println("===============================================================");
			courseIndexListControl.printIndexesInfoUnderCourse(course);

			// enter course index
			System.out.println("\nEnter Course Index:");
			int courseIndex = courseIndexListControl.chooseCourseIndex(course);

			// print course index details (schedule)
			courseIndexListControl.printCourseIndexInfo(course, courseIndex);

			System.out.println("\nConfirm to add course? Enter your choice: ");
			System.out.println("1. Yes");
			System.out.println("2. No");
			int choice_confirm;

			while (true) {
				try {
					while (true) {
						choice_confirm = scan.nextInt();
						if (choice_confirm >= 1 && choice_confirm <= 2) {
							break;
						} else {
							System.out.println("Invalid choice, please enter integer 1 or 2. ");
						}
					}
					break;
				} catch (Exception e) {
					System.out.println("Invalid choice, please enter integer 1 or 2. ");
					scan = new Scanner(System.in);
				}
			}

			if (choice_confirm == 1) {
				for (int i = 0; i < courseIndexListControl.getCourseIndexSize(); i++) {
					if (courseIndexListControl.getCourseIndexList().get(i).getIndexID() == courseIndex) {
						CourseIndex c = courseIndexListControl.getCourseIndexList().get(i);

						if (studentCourseListControl.checkNewCourseClash(c, username) == false) {
							if (c.getCurrentVacancy() == 0) {
								System.out.println("\nCourse is currently fully registered----");

								for (int j = 0; j < studentListControl.getStudentListSize(); j++) {
									if (studentListControl.getStudentList().get(j).getUsername().equals(username)) {
										Student s = studentListControl.getStudentList().get(j);
										if (s.getAcadunits() > 20) {
											System.out.println("You have exceeded the Max of 21AU");
										} else {
											c.getWaitingList().add(s);
											System.out.println("You are added to the waitList for " + courseIndex);
										}
									}
								}
							}

							else {

								System.out.println("\nRegistering course " + course + "------------------");
								for (int k = 0; k < studentListControl.getStudentListSize(); k++) {
									if (studentListControl.getStudentList().get(k).getUsername().equals(username)) {
										Student s1 = studentListControl.getStudentList().get(k);
										if (s1.getAcadunits() > 20) {
											System.out.println("You have exceeded 21AUs");
											System.out.println(
													"Your current AU for this semester is : " + s1.getAcadunits());
										} else {
											c.getStudent().add(s1);
											SendMailSSLCtrl.SendRegisteredNoti(c.getCourseName(), c.getCourseID(),
													courseIndex, s1.getEmail());
											s1.addAcadunits();
											studentCourseListControl.addStudentCourse(username, course, courseIndex,
													courseIndexListControl);
											courseIndexListControl.subtractVacancy(course, courseIndex);
											System.out.println("You have successfully registered course " + course
													+ " under index " + courseIndex);
											System.out.println(
													"Your current AU for this semester is: " + s1.getAcadunits());
										}
									}

								}
							}
						}
					}
				}
			}

			else {
				System.out.println("\nCancelling addition of course ------------------");
				return;
			}
		} else
			return;
		courseIndexListControl.save();

	}

	/**
	 * Method in the StudentUI for student to drop registered courses
	 * Student will input their course choice to drop here
	 * 
	 * @param username Username of student who logged into the console via mainConsole
	 * @param courseListControl courseList control object that holds the course list and their details
	 * @param courseIndexListControl courseIndexList control object that holds the courseIndex list and their details
	 * @param studentCourseListControl studentCourseList control object that holds the student's registered courses details 
	 * and the list of students in each course 
	 */
	public static void dropCourse(String username, CourseListCtrl courseListControl,
			CourseIndexListCtrl courseIndexListControl, StudentCourseListCtrl studentCourseListControl) {

		Scanner scan = new Scanner(System.in);

		// enter choice of course from list of registered courses
		System.out.println("--------------------------------------------------------");
		System.out.println("Enter choice of course: ");
		String course = studentCourseListControl.chooseCourse(username);
		int courseIndex = studentCourseListControl.getIndexOfCourse(username, course);

		System.out.println("\nConfirm to drop course? Enter your choice: ");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int choice_confirm;

		while (true) {
			try {
				while (true) {
					choice_confirm = scan.nextInt();
					if (choice_confirm >= 1 && choice_confirm <= 2) {
						break;
					} else {
						System.out.println("Invalid choice, please enter integer 1 or 2. ");
					}
				}
				break;
			} catch (Exception e) {
				System.out.println("Invalid choice, please enter integer 1 or 2. ");
				scan = new Scanner(System.in);
			}
		}

		if (choice_confirm == 1) {
			System.out.println("\nDropping course " + course + " ------------------");
			for (int k = 0; k < studentCourseListControl.getStudentListCtrl().getStudentListSize(); k++) {
				if (studentCourseListControl.getStudentListCtrl().getStudentList().get(k).getUsername().equals(username)) {
					Student s2 = studentCourseListControl.getStudentListCtrl().getStudent(k);
					studentCourseListControl.dropStudentCourse(username, course);
					s2.dropAcadunits();
					System.out.println("You have successfully dropped course " + course);
					System.out.println("Your current AU for this Semester is: " + s2.getAcadunits());
					courseIndexListControl.addToVacancy(course, courseIndex);

					///// auto registering Next-in-Line student in waiting list
					for (int l = 0; l < courseIndexListControl.getCourseIndexSize(); l++) {
						if (courseIndexListControl.getCourseIndexList().get(l).getIndexID() == courseIndex) {
							CourseIndex c = courseIndexListControl.getCourseIndexList().get(l);
							for (int t = 0; t < c.getStudent().size(); t++) {
								if (c.getStudent().get(t).getUsername().equals(username)) {
									Student removeStud = c.getStudent().get(t);
									c.getStudent().remove(removeStud); 
								}

							}

							if (c.getWaitingList().size() > 0) {
								Student nextStud = c.getWaitingList().get(0);
								studentCourseListControl.addStudentCourse(nextStud.getUsername(), course, courseIndex,
										courseIndexListControl);
								for (int m = 0; m < studentCourseListControl.getStudentListCtrl()
										.getStudentListSize(); m++) {
									if (studentCourseListControl.getStudentListCtrl().getStudentList().get(m)
											.getUsername().equals(nextStud.getUsername())) {
										Student s3 = studentCourseListControl.getStudentListCtrl().getStudent(m);
										System.out.println(s3.getAcadunits());
										s3.addAcadunits();
										SendMailSSLCtrl.SendRegisteredNoti(c.getCourseName(), c.getCourseID(), courseIndex,
												s3.getEmail());
										System.out.println(s3.getAcadunits());
										studentCourseListControl.getStudentListCtrl().save();
									}
								}
								c.getWaitingList().remove(0);

								courseIndexListControl.subtractVacancy(course, courseIndex);
								return;
							}

						}
					}
				}
			}
		} else {
			System.out.println("\nCancelling dropping of course ------------------");
			return;
		}

	}

	/**
	 * Method for students to change courseIndex with another index
	 * Student will input choice of course and choice of new index here
	 * 
	 * @param username Username of student who logged into the console via mainConsole
	 * @param courseListControl courseList control object that holds the course list and their details
	 * @param courseIndexListControl courseIndexList control object that holds the courseIndex list and their details
	 * @param studentCourseListControl studentCourseList control object that holds the student's registered courses details
	 * and the list of students in each course  
	 */
	public static void changeCourseIndex(String username, CourseListCtrl courseListControl,
			CourseIndexListCtrl courseIndexListControl, StudentCourseListCtrl studentCourseListControl) {

		Scanner scan = new Scanner(System.in);

		// enter choice of course from list of registered courses
		System.out.println("--------------------------------------------------------");
		System.out.println("Enter choice of course: ");
		String course = studentCourseListControl.chooseCourse(username);
		int initialIndex = studentCourseListControl.getIndexOfCourse(username, course);

		System.out.println("\nYour current Course Index: " + initialIndex);
		System.out.println("Course Indexes for Course " + course + ": ");
		courseIndexListControl.printIndexesUnderCourse(course);

		System.out.println("Enter new Index No. of course: ");
		int courseIndex = courseIndexListControl.chooseCourseIndex(course);

		if (courseIndex != initialIndex) {

			boolean courseClash = false;
			CourseIndex newIndex = null, initial = null;

			for (int i = 0; i < courseIndexListControl.getCourseIndexSize(); i++) {
				if (courseIndexListControl.getCourseIndexList().get(i).getIndexID() == courseIndex) {
					newIndex = courseIndexListControl.getCourseIndexList().get(i);
					// print course index details (schedule)
					courseIndexListControl.printCourseIndexInfo(course, courseIndex);
				}

				if (courseIndexListControl.getCourseIndexList().get(i).getIndexID() == initialIndex) {
					initial = courseIndexListControl.getCourseIndexList().get(i);
					System.out.println("\nInitial Schedule:");
					courseIndexListControl.printCourseIndexInfo(course, initialIndex);
				}
			}

			courseClash = studentCourseListControl.checkChangedCourseClash(initial, newIndex, username);

			System.out.println("\nConfirm to change Index No.? Enter your choice: ");
			System.out.println("1. Yes");
			System.out.println("2. No");
			int choice_confirm;

			while (true) {
				try {
					while (true) {
						choice_confirm = scan.nextInt();
						if (choice_confirm >= 1 && choice_confirm <= 2) {
							break;
						} else {
							System.out.println("Invalid choice, please enter integer 1 or 2. ");
						}
					}
					break;
				} catch (Exception e) {
					System.out.println("Invalid choice, please enter integer 1 or 2. ");
					scan = new Scanner(System.in);
				}
			}
			if (courseClash == false) {
				if (choice_confirm == 1) {
					System.out.println("\nChanging course Index No. of " + course + " ------------------");
					studentCourseListControl.changeStudentCourseIndex(username, course, courseIndex);
					System.out.println("Successfully changed Index No from " + initialIndex + " to " + courseIndex + ".");
				} else {
					System.out.println("\nCancelling changing of course index ------------------");
					return;
				}
			} else {
				System.out.println("\nCannot change index as there is a clash.");
			}
		} else {
			System.out.println("\nYou are already registered under this index.");
			System.out.println("Cancelling changing of course index ------------------");
		}

	}

	/**	 
	 * Method in the StudentUI for student to swap their courseIndex with a peer's courseIndex
	 * Student will input their course choice and peer details here
	 * 
	 * @param username Username of student who logged into the console via mainConsole
	 * @param courseListControl  courseList control object that holds the course list and their details
	 * @param courseIndexListControl courseIndexList control object that holds the courseIndex list and their details
	 * @param studentCourseListControl studentCourseList control object that holds the student's registered courses details 
	 * and the list of students in each course 
	 * @param studentListControl studentList control object that holds the student list and their details
	 */
	public static void swapCourseIndex(String username, CourseListCtrl courseListControl,
			CourseIndexListCtrl courseIndexListControl, StudentCourseListCtrl studentCourseListControl,
			StudentListCtrl studentListControl) {

		Scanner scan = new Scanner(System.in);

		// enter choice of course from list of registered courses
		System.out.println("--------------------------------------------------------");

		System.out.println("Enter choice of course: ");
		String course = studentCourseListControl.chooseCourse(username);
		int initialIndex = studentCourseListControl.getIndexOfCourse(username, course);

		System.out.println("Your Course Index No. for Course " + course + ": " + initialIndex);

		System.out.println("\nEntering details for Student #2  ------------------");
		String peerUsername, peerPassword, peerName = null;
		int initialIndex_peer = 0;
		byte[][] SaltArray = PasswordHash.ReturnByteArray();
		String[] HashedPasswords = PasswordHash.ReturnHashedPasswordsArray();
		
		boolean exit = true;
		int count = 0;
		while (true) { // student login
			System.out.println("\nEnter your Peer's Username: ");
			peerUsername = scan.next().toUpperCase();
			System.out.println("Enter your Peer's Password:  ");
			peerPassword = scan.next();

			// validation
			if (UserValidation.loginStudent(peerUsername, peerPassword, SaltArray, HashedPasswords) == false) {
				System.out.println("Incorrect username or password! Please try again. ");
				count++;
				if (count == 3) {
					exit = false;
					break;
				}
			}
			else {
				System.out.println("Student account is verified. ");

				peerName = studentListControl.getName(peerUsername);
				
				if (studentCourseListControl.checkIfRegistered(peerUsername, course) == true) {
					initialIndex_peer = studentCourseListControl.getIndexOfCourse(peerUsername, course);
					System.out.println("Your Peer's Index No. for Course " + course + ": " + initialIndex_peer);
				}
				else {
					System.out.println("Peer is not registered under this course.");
					return;
				}
				
				break;
			}
			
		}
		if (exit) {

			if (initialIndex_peer != initialIndex) {
				
				// print course index details (schedule)
				System.out.println("\nLesson schedule for current index " + initialIndex);
				courseIndexListControl.printCourseIndexInfo(course, initialIndex);
				
				System.out.println("\nLesson schedule for Peer's index " + initialIndex_peer);
				courseIndexListControl.printCourseIndexInfo(course, initialIndex_peer);

				boolean courseClash = false, courseClash_peer = false;
				CourseIndex initial_peer = null, initial = null;

				for (int i = 0; i < courseIndexListControl.getCourseIndexSize(); i++) {
					if (courseIndexListControl.getCourseIndexList().get(i).getIndexID() == initialIndex_peer) {
						initial_peer = courseIndexListControl.getCourseIndexList().get(i);
					}

					if (courseIndexListControl.getCourseIndexList().get(i).getIndexID() == initialIndex) {
						initial = courseIndexListControl.getCourseIndexList().get(i);
					}
				}
				courseClash = studentCourseListControl.checkChangedCourseClash(initial, initial_peer, username);
				courseClash_peer = studentCourseListControl.checkChangedCourseClash(initial_peer, initial,
						peerUsername);

				System.out.println("\nConfirm to swap Index No.? Enter your choice: ");
				System.out.println("1. Yes");
				System.out.println("2. No");
				int choice_confirm;

				while (true) {
					try {
						while (true) {
							choice_confirm = scan.nextInt();
							if (choice_confirm >= 1 && choice_confirm <= 2) {
								break;
							} else {
								System.out.println("Invalid choice, please enter integer 1 or 2. ");
							}
						}
						break;
					} catch (Exception e) {
						System.out.println("Invalid choice, please enter integer 1 or 2. ");
						scan = new Scanner(System.in);
					}
				}

				if (courseClash == false && courseClash_peer == false) {
					if (choice_confirm == 1) {
						System.out.println("\nSwapping course Index No. of " + course + " ------------------");
						studentCourseListControl.changeStudentCourseIndex(username, course, initialIndex_peer);
						studentCourseListControl.changeStudentCourseIndex(peerUsername, course, initialIndex);
						System.out.println("Successfully changed Index No from " + initialIndex + " to "
								+ initialIndex_peer + " with " + peerName);

						for (int k = 0; k < studentListControl.getStudentListSize(); k++) {
							if (studentListControl.getStudent(k).getUsername().equals(username)) {
								Student curStud = studentListControl.getStudent(k);
								SendMailSSLCtrl.SendSwapNoti(course, initialIndex, initialIndex_peer, peerName,
										curStud.getEmail());

								// add student to peer's Index
								for (CourseIndex c2 : courseIndexListControl.getCourseIndexList()) {
									if (c2.getIndexID() == initialIndex_peer) {
										c2.getStudent().add(curStud);
										courseIndexListControl.save();
									}
								}
							}
						}

						// remove student from initialIndex
						for (CourseIndex c : courseIndexListControl.getCourseIndexList()) {
							if (c.getIndexID() == initialIndex) {
								for (int i = 0; i < c.getStudent().size(); i++) {
									if (c.getStudent().get(i).getUsername().equals(username)) {
										c.getStudent().remove(i);
										courseIndexListControl.save();
									}
								}
							}
						}

						// remove peer from peer's initialIndex
						for (CourseIndex c1 : courseIndexListControl.getCourseIndexList()) {
							if (c1.getIndexID() == initialIndex_peer) {
								for (int k1 = 0; k1 < c1.getStudent().size(); k1++) {
									if (c1.getStudent().get(k1).getUsername().equals(peerUsername)) {
										c1.getStudent().remove(k1);
										courseIndexListControl.save();
									}
								}

							}
						}
						// add peer to student's initialIndex
						for (int k2 = 0; k2 < studentListControl.getStudentListSize(); k2++) {
							if (studentListControl.getStudent(k2).getUsername().equals(peerUsername)) {
								Student peerStud = studentListControl.getStudent(k2);
								for (CourseIndex c3 : courseIndexListControl.getCourseIndexList()) {
									if (c3.getIndexID() == initialIndex) {
										c3.getStudent().add(peerStud);
										courseIndexListControl.save();
									}

								}
							}
						}

					} else {
						if (courseClash == true) {
							System.out.println(
									"\nCannot change course as there is a clash with user's existing timetable.");
						}

						if (courseClash_peer == true) {
							System.out.println(
									"\nCannot change course as there is a clash with peer's existing timetable.");
						}

						return;
					}
				}

				else {
					System.out.println("\nCancelling changing of course index ------------------");
					return;

				}
			} else {
				System.out.println("You are registered under the same Index as your peer.");
				return;
			}
		}

	}

}
