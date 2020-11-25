package proj;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
/**
 * Control class which performs the reading and writing of data from external files in to ArrayLists.
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-20
 */

public class SerializeDB{
	/**
	 * Reads the data from external file and returns it as an ArrayList.
	 * 
	 * @param filename The file name for the retrieval of required data.
	 * @return The retrieved data initialized as ArrayList.
	 */
	public static List readSerializedObject(String filename) {
		List pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
	}
	/**
	 * Writes the data from an ArrayList back into the external file.
	 * 
	 * @param filename The file name for the writing of required data.
	 * @param list	The updated data stored as an ArrayList.
	 */
	public static void writeSerializedObject(String filename, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Pre populated database of the students, admins, schools and courses.
	 *  
	 * @param args An array of command line arguments for the pre populated database.
	 */
	public static void main(String[] args) {
		List list;
		try	{	
				List<Student> arrayStudentList = new ArrayList<Student>();
				List<Admin> arrayAdminList = new ArrayList<Admin>();
				ArrayList<Course> arrayCourseList= new ArrayList<Course>();
				List<School> arraySchoolList = new ArrayList<School>();
				ArrayList<CourseIndex> arrayStudentCourseList = new ArrayList<CourseIndex>();

		
				// Create 15 students
				Student s1 = new Student("JOEY0070", "passcode123", "Joey", 'F', "Singaporean", "U1234567E", "JOEY0070@e.ntu.edu.sg", "SCSE");
				Student s2 = new Student("SHAN0061", "passcode456", "Shannon Wong", 'F', "Singaporean", "U6789237F", "SHAN0061@e.ntu.edu.sg", "SCSE");
				Student s3 = new Student("JOSE0011", "passcode678", "Josephine", 'F', "Singaporean", "U1254562E", "JOSE0011@e.ntu.edu.sg", "SCSE");
				Student s4 = new Student("KENN2349", "passcode789", "Kenn", 'M', "Singaporean", "U7834564E", "KENN2349@e.ntu.edu.sg", "NBS");
				Student s5 = new Student("BENW3284", "passcode890", "Ben", 'M', "Singaporean", "U1435763E", "BENW3284@e.ntu.edu.sg", "NBS");
				Student s6 = new Student("JAME0034", "passcode555", "Jame", 'M', "Singaporean", "U1246436D", "JAME0034@e.ntu.edu.sg", "NBS");
				Student s7 = new Student("NICO5563", "passcode345", "Nicole", 'F', "Singaporean", "U1435762E", "NICO5563@e.ntu.edu.sg", "NBS");
				Student s8 = new Student("PETE3423", "passcode468", "Peter", 'M', "Malaysian", "U4676914A", "PETE3423@e.ntu.edu.sg", "SPMS");
				Student s9 = new Student("DERR2343", "passcode697", "Derryl", 'M', "Singaporean", "U3865836B", "DERR234@e.ntu.edu.sg", "SPMS");
				Student s10 = new Student("SAMW2341", "passcode579", "Sam Wong", 'M', "Singaporean", "U7934562C", "SAMW2341@e.ntu.edu.sg", "SPMS");
				Student s11 = new Student("KIML4352", "passcode679", "Kim Lim", 'F', "Singaporean", "U7946472A", "KIML4352@e.ntu.edu.sg", "NBS");
				Student s12 = new Student("CHLO2375", "passcode870", "Chloe", 'F', "Singaporean", "U8057456G", "CHLO2375@e.ntu.edu.sg", "SPMS");
				Student s13 = new Student("MADE2341", "passcode386", "Madeline", 'F', "Singaporean", "U4572472Y", "MADE2341@e.ntu.edu.sg", "SPMS");
				Student s14 = new Student("EILO0485", "passcode578", "Eilot", 'M', "Singaporean", "U1334461", "EILO0485@e.ntu.edu.sg", "SCSE");
				Student s15 = new Student("QIYI2451", "passcode564", "Qiyi", 'F', "Singaporean", "U1435376E", "QIYI2451@e.ntu.edu.sg", "SCSE");
				
				// add student to arrayStudentList
				arrayStudentList.add(s1);
				arrayStudentList.add(s2);
				arrayStudentList.add(s3);
				arrayStudentList.add(s4);
				arrayStudentList.add(s5);
				arrayStudentList.add(s6);
				arrayStudentList.add(s7);
				arrayStudentList.add(s8);
				arrayStudentList.add(s9);
				arrayStudentList.add(s10);
				arrayStudentList.add(s11);
				arrayStudentList.add(s12);
				arrayStudentList.add(s13);
				arrayStudentList.add(s14);
				arrayStudentList.add(s15);

				SerializeDB.writeSerializedObject("StudentList.txt", arrayStudentList);
				
				//Create 2 admin
				Admin a1 = new Admin("ADMIN1", "passcode888", "admin1@e.ntu.edu.sg");
				Admin a2 = new Admin("ADMIN2", "passcode999", "admin2@e.ntu.edu.sg");
				
				//Add admin into arrayAdminList
				arrayAdminList.add(a1);
				arrayAdminList.add(a2);
				
				SerializeDB.writeSerializedObject("AdminList.txt", arrayAdminList);
				
				//Create 15 Courses
				Course c1 = new Course("ALGORITHMS","CZ2001");
				Course c2 = new Course("OBJECT ORIENTED DESIGN & PROGRAMMING","CZ2002");
				Course c3 = new Course("COMPUTER GRAPHICS & VISUALISATION","CZ2003");
				Course c4 = new Course("HUMAN COMPUTER INTERACTION","CZ2004");
				Course c5 = new Course("OPERATING SYSTEMS","CZ2005");
				Course c6 = new Course("LINEAR ALGEBRA FOR SCIENTISTS","MH2802");
				Course c7 = new Course("DISCRETE MATHEMATICS","MH1812");
				Course c8 = new Course("CALCULUS III","MH2100");
				Course c9 = new Course("PROBABILITY & INTRODUCTION TO STATISTICS","MH2500");
				Course c10 = new Course("COMMUNICATION MANAGEMENT STRATEGIES", "AB0602");
				Course c11 = new Course("FINANCIAL MANAGEMENT","AB1201");
				Course c12 = new Course("STATISTICS & ANALYSIS","AB1202");
				Course c13 = new Course("BUSINESS LAW","AB1301");
				Course c14 = new Course("MARKETING","AB1501");
				Course c15= new Course("ORGANISATIONAL BEHAVIOUR & DESIGN","AB1601");
				
				//Add courses into arrayCourseList
				arrayCourseList.add(c1);
				arrayCourseList.add(c2);
				arrayCourseList.add(c3);
				arrayCourseList.add(c4);
				arrayCourseList.add(c5);
				arrayCourseList.add(c6);
				arrayCourseList.add(c7);
				arrayCourseList.add(c8);
				arrayCourseList.add(c9);
				arrayCourseList.add(c10);
				arrayCourseList.add(c11);
				arrayCourseList.add(c12);
				arrayCourseList.add(c13);
				arrayCourseList.add(c14);
				arrayCourseList.add(c15);
				
				SerializeDB.writeSerializedObject("CourseList.txt", arrayCourseList);
		
				
				//Create 3 Schools
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				School sch1 = new School("SCSE", "School of Computer Science and Engineering", arrayCourseList, df.parse("23/11/2020"), df.parse("24/11/2020"));
				School sch2 = new School("SPMS", "School of Physical and Mathematical Sciences", arrayCourseList, df.parse("19/11/2020"), df.parse("24/11/2020"));
				School sch3 = new School("NBS", "Nanyang Business School", arrayCourseList, df.parse("26/11/2020"), df.parse("28/11/2020"));

				//Add course to respective schools
				sch1.addCourse(c1);
				sch1.addCourse(c2);
				sch1.addCourse(c3);
				sch1.addCourse(c4);
				sch1.addCourse(c5);
				sch2.addCourse(c6);
				sch2.addCourse(c7);
				sch2.addCourse(c8);
				sch2.addCourse(c9);
				sch2.addCourse(c10);
				sch3.addCourse(c11);
				sch3.addCourse(c12);
				sch3.addCourse(c13);
				sch3.addCourse(c14);
				sch3.addCourse(c15);
				
				//Add School to arraySchoolList
				arraySchoolList.add(sch1);
				arraySchoolList.add(sch2);
				arraySchoolList.add(sch3);

				SerializeDB.writeSerializedObject("SchoolList.txt", arraySchoolList);
				

				//Create 5 Course Index for each course
				List<CourseIndex>arrayCourseIndexList= new ArrayList<CourseIndex>();
				Random r = new Random();
				DateFormat df2 = new SimpleDateFormat("HH:mm");
				ArrayList<Date> DateTimeList = new ArrayList<Date>();
				Calendar cal = (Calendar)Calendar.getInstance();
				int hourarr[] = { 9, 11, 13, 15, 17 };
				for (int i = 0; i < 5; i++) {
					cal.set(Calendar.HOUR_OF_DAY, hourarr[i]);
					cal.set(Calendar.MINUTE, 30);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					DateTimeList.add(cal.getTime());
				}
				
				//day id spans from 1-5
				int minDay = 1;
				int maxDay = 6;
				String courseID;
				String courseName;
				
				// create 5 index with lectures, tutorials and labs for course in SCSE, some indexes with 1 vacancy for test case
				for (int j = 0; j < 5; j++) {
					courseID = arrayCourseList.get(j).getCourseID();
					courseName = arrayCourseList.get(j).getCourseName();
					int vacancy[] = {10,1,25,14,16}; 
					int k = 2000 + (j * 20);
					for (int i = 0; i < 5; i++) {
						Collections.shuffle(DateTimeList);
						Schedule schedule_lectutlab = new Schedule (r.nextInt(maxDay-minDay) + minDay,
									r.nextInt(maxDay-minDay) + minDay,
									r.nextInt(maxDay-minDay) + minDay,
									DateTimeList.get(0), 
									DateTimeList.get(1), 
									DateTimeList.get(2),2011 + k + i);
							CourseIndex courseid = new CourseIndex (courseName, courseID, 2011 + k + i, schedule_lectutlab, vacancy[i]);
							arrayCourseIndexList.add(courseid);
					}
				}
				// r.nextInt excludes 5, so we add 1
				// To obtain variables for random number of vacancies
				int minVac = 1; 
				int maxVac = 30;
				
				//create 5 indexes with lectures for courses in NBS, randomise the number of vacancies in each index
				for (int j = 5; j < 10; j++) {
					courseID = arrayCourseList.get(j).getCourseID();
					courseName = arrayCourseList.get(j).getCourseName();
					int k = 2000 + (j * 20);
					for (int i = 0; i < 5; i++) {
						Collections.shuffle(DateTimeList);
						Schedule schedule_lectut = new Schedule (r.nextInt(maxDay-minDay) + minDay,
								r.nextInt(maxDay-minDay) + minDay,
								DateTimeList.get(0), 
								DateTimeList.get(1), 2011 + k + i);
						CourseIndex courseid = new CourseIndex (courseName, courseID, 2011 + k + i, schedule_lectut, r.nextInt(maxVac-minVac) + minVac);
						arrayCourseIndexList.add(courseid);
					}
				}
			
				// create 5 indexes with lectures and tutorials for courses in SPMS, randomise the number of vacancies in each index
				for (int j = 10; j < 15; j++) {
					courseID = arrayCourseList.get(j).getCourseID();
					courseName = arrayCourseList.get(j).getCourseName();
					int k = 2000 + (j * 20);
					for (int i = 10; i < 15; i++) {
						Collections.shuffle(DateTimeList);
						Schedule schedule_lec = new Schedule (r.nextInt(maxDay-minDay) + minDay,
								DateTimeList.get(0), 2011 + k + i);
						CourseIndex courseid = new CourseIndex (courseName, courseID, 2011 + k + i, schedule_lec, r.nextInt(maxVac-minVac) + minVac);
							arrayCourseIndexList.add(courseid);
					}
				}

				SerializeDB.writeSerializedObject("CourseIndexList.txt", arrayCourseIndexList);
				
				System.out.println(arrayCourseIndexList);
				
				System.out.println("Database has been pre populated!");
				
		}  catch ( Exception e ) {
					System.out.println( "Exception >> " + e.getMessage() );
					e.printStackTrace();
		}
	}
}