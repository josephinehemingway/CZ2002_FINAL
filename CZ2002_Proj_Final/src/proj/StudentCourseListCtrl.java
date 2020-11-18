package proj;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentCourseListCtrl {
	
	private StudentListCtrl studentListControl;
	private CourseIndexListCtrl courseIndexListControl;
	
	@SuppressWarnings("unchecked")
	public StudentCourseListCtrl(StudentListCtrl studentListControl, CourseIndexListCtrl courseIndexListControl) {
		this.studentListControl = studentListControl;
		this.courseIndexListControl = courseIndexListControl;
	}
	
	public StudentListCtrl getStudentListCtrl() {
		return this.studentListControl;
	}
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
	
	//drop registered courses
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
	
	public void changeStudentCourseIndex(String username, String courseId, int newCourseIndex) {
		for (int i=0; i<studentListControl.getStudentList().size(); i++) {
			Student stud = studentListControl.getStudent(i);
			
			if (stud.getUsername().equals(username)) {
				for (int j=0; j< stud.getCourseIndexArr().size(); j++) {
					System.out.println(stud.getCourseIndexArr());
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
	
	//print registered courses
	public boolean printRegisteredCourses(String username) {
		for (int i=0; i<studentListControl.getStudentList().size(); i++) {
			Student stud = studentListControl.getStudent(i);
			
			if (stud.getUsername().equals(username)) {
				System.out.println("\nRegistered courses for "+ username + ": \n");
				System.out.println("   CourseID	Index	  CourseName			   ");
				System.out.println("===================================================================");
				
				ArrayList<CourseIndex> registeredArr = stud.getCourseIndexArr();
				
				if (!(registeredArr.isEmpty())) {
					for (int j = 0; j < registeredArr.size(); j++) {
						CourseIndex course = registeredArr.get(j);
						System.out.println("    " + registeredArr.get(j).getCourseID()
								+ "	" + course.getIndexID()
								+ "	  " + course.getCourseName());
					}
					return true;
				} else {
					System.out.println("No course registered for this Student");
					return false;
				}
			}
		}return false;
	}
	
	
	
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
				System.out.println("===================================================================");
				System.out.println("\nTimetable for this Semester");
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
					System.out.println("Your current AU for this Semester is: " + stud.getAcadunits());
					return;
				}
			}
		}return;
		
	}
	
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
