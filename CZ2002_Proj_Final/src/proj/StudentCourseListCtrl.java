package proj;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Represents the control for studentList and courseList combined which holds an ArrayList of Students where each student holds an ArrayList of CourseIndexes.
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-20
 */
public class StudentCourseListCtrl {
	/**
	 * ArrayList that stores list of Students
	 */
	private StudentListCtrl studentListControl;
	/**
	 * ArrayLst that stores list of courseIndexes
	 */
	private CourseIndexListCtrl courseIndexListControl;
	/**
	 * Reads in the list of students from studentListControl and courseIndexes from courseIndexListControl
	 * @param studentListControl studentList control object that holds the student list and their details
	 * @param courseIndexListControl courseIndexList control object that holds the courseIndex list and their details
	 */
	@SuppressWarnings("unchecked")
	public StudentCourseListCtrl(StudentListCtrl studentListControl, CourseIndexListCtrl courseIndexListControl) {
		this.studentListControl = studentListControl;
		this.courseIndexListControl = courseIndexListControl;
	}
	/**
	 * Gets the studentList control object that holds the list of students and their details
	 * 
	 * @return StudentList control object holding list of students
	 */
	public StudentListCtrl getStudentListCtrl() {
		return this.studentListControl;
	}
	/**
	 * Method that adds courseIndex object into CourseIndex Arraylist of a student when they register
	 * 
	 * @param username The username of the student whose CourseIndex ArrayList we are adding the courseIndex object to
	 * @param courseId The courseID of the courseIndex object
	 * @param courseIndex The courseIndex of of the courseIndex Object
	 * @param courseIndexList control object that holds list of CourseIndexes and their details
	 */
	public void addStudentCourse(String username, String courseId, int courseIndex, 
			CourseIndexListCtrl courseIndexListControl) {
		
		// add courses to courselist
		for (int i=0; i<studentListControl.getStudentList().size(); i++) {
			
			if (studentListControl.getStudent(i).getUsername().equals(username)) {
				
				for (int j=0; j<courseIndexListControl.getCourseIndexSize(); j++) {
					CourseIndex e = courseIndexListControl.getCourseIndexList().get(j);
					
					if (e.getIndexID() == courseIndex && e.getCourseID().equals(courseId)) {
						studentListControl.getStudent(i).getCourseIndexArr().add(e);
					}
				}
			}
		}
	}
	/**
	 * Method that drops a courseIndex from the CourseIndex ArrayList of a student that he/she has registered for, identifying the student by his username, and courseIndex by its courseId
	 * 
	 * @param username The username of student that is dropping a course
	 * @param courseId The courseID of the course that the student wants to drop
	 */
	public void dropStudentCourse(String username, String courseId) {
		for (int i=0; i<studentListControl.getStudentList().size(); i++) {
			Student stud = studentListControl.getStudent(i);
			
			if (stud.getUsername().equals(username)) {
				for (int j=0; j< stud.getCourseIndexArr().size(); j++) {
					CourseIndex c = stud.getCourseIndexArr().get(j);
					if (c.getCourseID().equals(courseId)) {
						stud.getCourseIndexArr().remove(j);
					}
				
				}
			}
		}
	}
	/**
	 * Method that allows student user to change his currently registered CourseIndex to another courseIndex of the same course
	 * @param username The username of student who is changing his/her courseIndex
	 * @param courseId The courseID of the courseIndex that the student wants to change
	 * @param newCourseIndex The IndexID of the courseIndex that the student wishes to change to
	 */
	public void changeStudentCourseIndex(String username, String courseId, int newCourseIndex) {
		for (int i=0; i<studentListControl.getStudentList().size(); i++) {
			Student stud = studentListControl.getStudent(i);
			
			if (stud.getUsername().equals(username)) {
				for (int j=0; j< stud.getCourseIndexArr().size(); j++) {
//					System.out.println(stud.getCourseIndexArr());
					if (stud.getCourseIndexArr().get(j).getCourseID().equals(courseId)) {
						stud.getCourseIndexArr().remove(j);
						
						for (int k = 0; k<courseIndexListControl.getCourseIndexList().size(); k++) {
							if (newCourseIndex == courseIndexListControl.getCourseIndexList().get(k).getIndexID()) {
								CourseIndex c = courseIndexListControl.getCourseIndexList().get(k);
								stud.getCourseIndexArr().add(c);
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Prints a list of details of all the courseIndexes registered by the student
	 * @param username The username of the student of which we want to print his/her registered Courses
	 * @return true if the student has courses registered, false if the student's CourseIndex ArrayList is empty, meaning he has not registered for any courses.
	 */
	public boolean printRegisteredCourses(String username) {
		for (int i=0; i<studentListControl.getStudentList().size(); i++) {
			Student stud = studentListControl.getStudent(i);
			
			if (stud.getUsername().equals(username)) {
				System.out.println("\nRegistered courses for "+ username + ":");
				System.out.println("=======================================================================");
				System.out.println("   CourseID	Index	  AU	CourseName			   ");
				System.out.println("=======================================================================");
				
				ArrayList<CourseIndex> registeredArr = stud.getCourseIndexArr();
				
				if (!(registeredArr.isEmpty())) {
					for (int j = 0; j < registeredArr.size(); j++) {
						CourseIndex course = registeredArr.get(j);
						int maxCourseLength = 45;
					
						System.out.println("    " + registeredArr.get(j).getCourseID() + "	" + course.getIndexID() 
						+ "	  " + CourseIndex.ACADEMIC_UNITS + "     " + course.getCourseName() );
					
					}
					System.out.println("=======================================================================");
					System.out.println("\nYour current AU for this Semester is: " + stud.getAcadunits());
					return true;
				} else {
					System.out.println("No course registered for this Student");
					return false;
				}
			}
			
		}return false;
	}
	/**
	 * Prints the timetable of the student whose username is input and the Student's accumulated AU of the semester
	 * 
	 * @param username The username of the Student whose timetable would be printed
	 */
	public void printTimetable(String username) {
		DateFormat df= new SimpleDateFormat("HH:mm");
		Student stud = null;
		CourseIndex course = null;
		Schedule sch = null;
		String courseID = null;
		
		String lessonType = null;

		// list of lessons for each day of the week
		ArrayList<Lesson> monList = new ArrayList<Lesson>();
		ArrayList<Lesson> tueList = new ArrayList<Lesson>();
		ArrayList<Lesson> wedList = new ArrayList<Lesson>();
		ArrayList<Lesson> thuList = new ArrayList<Lesson>();
		ArrayList<Lesson> friList = new ArrayList<Lesson>();
		
		for (int i=0; i<studentListControl.getStudentList().size(); i++) {
			stud = studentListControl.getStudent(i);
			
			if (stud.getUsername().equals(username)) {
				ArrayList<CourseIndex> registeredArr = stud.getCourseIndexArr();
				
				if (!(registeredArr.isEmpty())) {
					for (int j = 0; j < registeredArr.size(); j++) {
						course = registeredArr.get(j);
						sch = course.getSchedule();
						
						
						switch(sch.getLab().getDay()) {
						case 1:
							monList.add(sch.getLab());
							break;
						
						case 2:
							tueList.add(sch.getLab());
							break;
						
						case 3:
							wedList.add(sch.getLab());
							break;
						
						case 4:
							thuList.add(sch.getLab());
							break;
							
						case 5:
							friList.add(sch.getLab());
							break;
							
						default:
							System.out.println("Invalid day");
						}
						
						switch(sch.getLect().getDay()) {
						case 1:
							monList.add(sch.getLect());
							break;
						
						case 2:
							tueList.add(sch.getLect());
							break;
						
						case 3:
							wedList.add(sch.getLect());
							break;
						
						case 4:
							thuList.add(sch.getLect());
							break;
							
						case 5:
							friList.add(sch.getLect());
							break;
							
						default:
							System.out.println("Invalid day");
						}
						
						switch(sch.getTut().getDay()) {
						case 1:
							monList.add(sch.getTut());
							break;
						
						case 2:
							tueList.add(sch.getTut());
							break;
						
						case 3:
							wedList.add(sch.getTut());
							break;
						
						case 4:
							thuList.add(sch.getTut());
							break;
							
						case 5:
							friList.add(sch.getTut());
							break;
							
						default:
							System.out.println("Invalid day");
						}
					}
					
				sortLesson(monList);
				sortLesson(tueList);
				sortLesson(wedList);
				sortLesson(thuList);
				sortLesson(friList);
				
				int index = 0;
				System.out.println("=======================================================================");
				System.out.println("\nTimetable for Semester 1 AY20/21");
				System.out.println("--------------------------------------------------------");
				
				System.out.println("  Day		Time		Course		Venue");
				System.out.println("========================================================");
				System.out.print("Monday   " );
				
				for (index = 0 ; index < monList.size() ; index ++) {
					
					for(CourseIndex c : registeredArr) {
						if (c.getIndexID() == monList.get(index).getIndexID()) {
							courseID = c.getCourseID();
						}
					}
					
					lessonType = getLessonType(monList.get(index));
					if (index == 0){
						System.out.println(" " + df.format(monList.get(index).getStarttime()) + " - " 
								+ df.format(monList.get(index).getEndtime()) + "		" 
								+ courseID + " (" + lessonType + ")	" + monList.get(index).getVenue()); 
					}
					else
						System.out.println("	  " + df.format(monList.get(index).getStarttime()) + " - " 
								+ df.format(monList.get(index).getEndtime()) + "		" 
								+ courseID + " (" + lessonType + ")	" + monList.get(index).getVenue());
					
				}
			
				System.out.println("\n--------------------------------------------------------");
				
				System.out.print("Tuesday  ");
				for (index = 0 ; index < tueList.size() ; index ++) {
					for(CourseIndex c : registeredArr) {
						if (c.getIndexID() == tueList.get(index).getIndexID()) {
							courseID = c.getCourseID();
						}
					}
					
					lessonType = getLessonType(tueList.get(index));
					if (index == 0){
						System.out.println(" " + df.format(tueList.get(index).getStarttime()) + " - " 
								+ df.format(tueList.get(index).getEndtime()) + "		" 
								+ courseID + " (" + lessonType + ")	" + tueList.get(index).getVenue());
					}
					else
						System.out.println("	  " + df.format(tueList.get(index).getStarttime()) + " - " 
								+ df.format(tueList.get(index).getEndtime()) + "		" 
								+ courseID + " (" + lessonType + ")	" + tueList.get(index).getVenue());
				}
				
				System.out.println("\n--------------------------------------------------------");
				
				System.out.print("Wednesday");
				for (index = 0 ; index < wedList.size() ; index ++) {
					for(CourseIndex c : registeredArr) {
						if (c.getIndexID() == wedList.get(index).getIndexID()) {
							courseID = c.getCourseID();
						}
					}
					lessonType = getLessonType(wedList.get(index));
					if (index == 0){
						System.out.println(" " + df.format(wedList.get(index).getStarttime()) + " - " 
								+ df.format(wedList.get(index).getEndtime()) + "		" 
								+ courseID + " (" + lessonType + ")	" + wedList.get(index).getVenue());
					}
					else
						System.out.println("	  " + df.format(wedList.get(index).getStarttime()) + " - " 
								+ df.format(wedList.get(index).getEndtime()) + "		" 
								+ courseID + " (" + lessonType + ")	" + wedList.get(index).getVenue());
				}
				
				System.out.println("\n--------------------------------------------------------");
				
				System.out.print("Thursday ");
				for (index = 0 ; index < thuList.size() ; index ++) {
					for(CourseIndex c : registeredArr) {
						if (c.getIndexID() == thuList.get(index).getIndexID()) {
							courseID = c.getCourseID();
						}
					}
					
					lessonType = getLessonType(thuList.get(index));
					
					if (index == 0){
						System.out.println(" " + df.format(thuList.get(index).getStarttime()) + " - " 
								+ df.format(thuList.get(index).getEndtime()) + "		" 
								+ courseID + " (" + lessonType + ")	" + thuList.get(index).getVenue());
					}
					else
						System.out.println("	  " + df.format(thuList.get(index).getStarttime()) + " - " 	
								+ df.format(thuList.get(index).getEndtime()) + "		" 
								+ courseID + " (" + lessonType + ")	" + thuList.get(index).getVenue());
		}
				
				System.out.println("\n--------------------------------------------------------");
				
				System.out.print("Friday   ");
				
				for (index = 0 ; index < friList.size() ; index ++) {
					for(CourseIndex c : registeredArr) {
						if (c.getIndexID() == friList.get(index).getIndexID()) {
							courseID = c.getCourseID();
						}
					}
				
					lessonType = getLessonType(friList.get(index));
					if (index == 0) {
						System.out.println(" " + df.format(friList.get(index).getStarttime()) + " - " 
								+ df.format(friList.get(index).getEndtime()) + "		" 
								+ courseID + " (" + lessonType + ")	" + friList.get(index).getVenue());
					}
					else
						System.out.println("	  " + df.format(friList.get(index).getStarttime()) + " - " 
								+ df.format(friList.get(index).getEndtime()) + "		" 
								+ courseID + " (" + lessonType + ")	" + friList.get(index).getVenue());
		}
				System.out.println("\n");
				System.out.println("--------------------------------------------------------");
				System.out.println("Your total registered AU for this Semester is: " + stud.getAcadunits());
					return;
				} else {
					System.out.println("\nNo Timetable available.");
					return;
				}
			}
		}return;
		
	}
	/**
	 * Sorts Lesson object according to their startTime
	 * 
	 * @param LessonList The list of lessons stored in the Lessonlist ArrayList
	 */
	public void sortLesson(ArrayList<Lesson> LessonList) {
		
		Lesson temp;
		for (int i = 0; i < LessonList.size(); i++) {
			for (int j = 1; j<LessonList.size()-i; j++) {
				if (LessonList.get(j-1).getStarttime().after(LessonList.get(j).getStarttime())) 
				{
					temp = LessonList.get(j-1);
					LessonList.set(j-1, LessonList.get(j));
					LessonList.set(j,temp);
				}
			}
		}
		
	}
	
	

	/**
	 * Gets the lesson type of the lesson object input
	 * 
	 * @param les The Lesson object whose type is being checked
	 * 
	 * @return Lessontype of the lesson object (tutorial, lecture or lab)
	 */
	public String getLessonType(Lesson les) {
		String prefix_lab = "LAB";
		String prefix_tut = "TR";
		String prefix_lect = "LT";
		String lessonType = null;
		
		if (les.getVenue().startsWith(prefix_lab)) {
			lessonType = "Lab";
		}
		else if (les.getVenue().startsWith(prefix_tut)) {
			lessonType = "Tut";
		}
		else if(les.getVenue().startsWith(prefix_lect)) {
			lessonType = "Lec";
		}
		
		return lessonType;
	}
	
	/**
	 * Allows the student to choose a valid course to register
	 * 
	 * @param username The username of the student that is registering for the courses
	 * @return A valid courseID of the chosen course the student has input
	 */
	public String chooseCourse(String username) {
		Scanner sc = new Scanner(System.in);
		String courseChoice = null;
		boolean k = true;
		Student stud = null;
		
		while (k) {
			try {
				int count = 1;
				courseChoice = sc.nextLine().toUpperCase();
				
				for (int i=0; i < studentListControl.getStudentList().size(); i++) {
					stud = studentListControl.getStudent(i);
					if (stud.getUsername().equals(username)) {
						ArrayList<CourseIndex> registeredCourseList = stud.getCourseIndexArr();
						
						for (int j=0; j< registeredCourseList.size(); j++) {
							if (registeredCourseList.get(j).getCourseID().equals(courseChoice)) {
								k = false;
								break;
							} else if (courseChoice.equals("0")) {
								System.out.println("Operation cancelled. ");
								return "exit";
							} else if ((count) == registeredCourseList.size()) {
								System.out.println("Please re-enter valid course choice");
							} else {
								(count)++;
							}
						}
					}
				}
				} catch (InputMismatchException e) {
					System.out.print("Please re-enter valid course choice");
					sc.nextLine();
				}
		}
		return courseChoice;
	}
	/**
	 * Checks if the courseIndex the student wants to register for does not clash with the other registered courseIndexs timeslots 
	 * The registered CourseIndexes of the student is stored in the CourseIndex ArrayList of the student
	 * 
	 * @param courseIndex The CourseIndex object which the student wants to register for
	 * @param username The username of the student registering
	 * @return true if the timeslots clashes, else false if the timeslots does not clash
	 */
	public boolean checkNewCourseClash(CourseIndex courseIndex, String username) {
		for (int i=0; i<studentListControl.getStudentList().size(); i++) {
			Student stud = studentListControl.getStudent(i);
			
			if (stud.getUsername().equals(username)) {
				ArrayList<CourseIndex> registeredArr = stud.getCourseIndexArr();
				if (registeredArr != null) {
					for (int j=0; j< registeredArr.size(); j++) {
						CourseIndex c = registeredArr.get(j);
						Schedule s = c.getSchedule();
						
						if (s.checkClash(courseIndex.getSchedule()) == true) {
							return true;
						}
					}
				}
			}
		} return false;
				
	}
	/**
	 * Checks if the courseIndex the student wants to change to does not clash with the other registered Courses timeslot
	 * The registered CourseIndexs of the courses registered is stored in the CourseIndex ArrayList of the student
	 * @param initialIndex The CourseIndex object the student is currently registered for but wants to change
	 * @param newIndex The new CourseIndex object which the student wants to change to
	 * @param username The username of the student who is logged in
	 * @return true if the index the student wants to change to clashes with his current timetable, false if it does not.
	 */
	public boolean checkChangedCourseClash(CourseIndex initialIndex, CourseIndex newIndex, String username) {
		for (int i = 0; i < studentListControl.getStudentList().size(); i++) {
			Student stud = studentListControl.getStudent(i);

			if (stud.getUsername().equals(username)) {
				ArrayList<CourseIndex> registeredArr = stud.getCourseIndexArr();
				ArrayList<CourseIndex> newRegArr = new ArrayList<CourseIndex>();
				if (registeredArr != null) {
					for (int k = 0; k < registeredArr.size(); k++) {
						if (registeredArr.get(k).getIndexID() == initialIndex.getIndexID()) {
							newRegArr = (ArrayList<CourseIndex>) registeredArr.clone();
							newRegArr.remove(k);
						}
					}

					for (int j = 0; j < newRegArr.size(); j++) {
						CourseIndex c = newRegArr.get(j);
						Schedule s = c.getSchedule();

						if (s.checkClash(newIndex.getSchedule()) == true) {
							return true;
						}
					}
				}
			}
		}
		return false;

	}
	/**
	 * Checks if the student is already registered for that particular course by checking against the student's ArrayList of CourseIndexes which are the courseIndexes that the student has registered for
	 * @param username The username of the student that is logged in.
 	 * @param courseId The courseID that the student wants to register for
	 * @return true if the student has already registered for that particular course, false if the student has yet to be registered.
	 */
	public boolean checkIfRegistered(String username, String courseId) {
		for (int i=0; i<studentListControl.getStudentList().size(); i++) {
			Student stud = studentListControl.getStudent(i);
			
			if (stud.getUsername().equals(username)) {
				for (int j=0; j< stud.getCourseIndexArr().size(); j++) {
					CourseIndex c = stud.getCourseIndexArr().get(j);
					if (c.getCourseID().equals(courseId)) {
						System.out.println("Course already registered.");
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Gets indexID of the courseIndex that the student has registered for, which is stored in the CourseIndex ArrayList of the student
	 * 
	 * @param username The username of the student that is logged in
	 * @param courseId The courseID of the course we are getting the indexID from 
	 * @return the indexID of the courseIndex if it exists in the student's CourseIndex ArrayList, and 0 if the course doesn't exist.
	 */
	public int getIndexOfCourse(String username, String courseId) {
		for (int i=0; i<studentListControl.getStudentList().size(); i++) {
			Student stud = studentListControl.getStudent(i);
			
			if (stud.getUsername().equals(username)) {
				for (int j=0; j< stud.getCourseIndexArr().size(); j++) {
					CourseIndex c = stud.getCourseIndexArr().get(j);
					if (c.getCourseID().equals(courseId)) {
						return c.getIndexID();
					}
				}
			}
		} return 0;
	}

	
	
	
}
