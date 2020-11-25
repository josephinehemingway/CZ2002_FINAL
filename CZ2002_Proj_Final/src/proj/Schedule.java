package proj;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Schedule Entity Class
 * 
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 */

public class Schedule implements Serializable {
	/**
	 * Venue for different type of lessons.
	 */
	private String venuelec, venuetut, venuelab;

	/**
	 * Index ID of Schedule object.
	 */
	private String indexID;

	/**
	 * Start timing of lab lesson.
	 */
	private Date startlab;

	/**
	 * Start timing of lecture lesson.
	 */
	private Date startlect;

	/**
	 * Start timing of tutorial lesson.
	 */
	private Date starttut;

	/**
	 * End timing of lab lesson.
	 */
	private Date endlab;

	/**
	 * End timing of lecture lesson.
	 */
	private Date endlect;

	/**
	 * End timing of tutorial lesson.
	 */
	private Date endtut;

	/**
	 * Different types of lessons for this schedule.
	 */
	private Lesson lab, tut, lect;

	/**
	 * Course type of the course 1: Courses with lab, lecture and tutorial. 2:
	 * Courses with lecture and tutorial only. 3: Courses with lecture only
	 */
	private int coursetype;
	/**
	 * Days of the different lesson types. 1: Monday 2: Tuesday 3: Wednesday 4:
	 * Thursday 5: Friday
	 */
	private int labday, lectday, tutday;

	/**
	 * Randomize the tutorial room numbers.
	 */
	private static int venuetutcounter = 10;

	/**
	 * Randomize the lab room numbers.
	 */
	private static int venuelabcounter = 15;

	/**
	 * Randomize the lecture theater numbers.
	 */
	private static int venuelectcounter = 20;

	/**
	 * Get start timing of the lab.
	 * 
	 * @return start timing of lab.
	 */
	public Date getStartlab() {
		return startlab;
	}

	/**
	 * Get start timing of the lecture.
	 * 
	 * @return start timing of lecture.
	 */
	public Date getStartlect() {
		return startlect;
	}

	/**
	 * Get start timing of the tutorial.
	 * 
	 * @return start timing of tutorial.
	 */
	public Date getStarttut() {
		return starttut;
	}

	// Constructor to add schedule to an index
	/**
	 * Schedule Constructor to create new schedule object to a course index This
	 * constructor is for courses with all 3 types of courses: - Lab - Lecture -
	 * Tutorial
	 * 
	 * @param labday  The day of lab lesson.
	 * @param lectday The day of lecture lesson.
	 * @param tutday  The day of tutorial lesson.
	 * @param _lab    Start timing of lab lesson.
	 * @param _lect   Start timing of lecture lesson.
	 * @param _tut    Start timing of tutorial lesson.
	 * @param indexID This schedule's CourseIndex ID.
	 */
	public Schedule(int labday, int lectday, int tutday, Date _lab, Date _lect, Date _tut, int indexID) {
		this.labday = labday;
		this.lectday = lectday;
		this.tutday = tutday;
		this.startlab = _lab;
		this.startlect = _lect;
		this.starttut = _tut;
		this.coursetype = 1;

		// lab is 2h long, end timing of lab is 2h later
		endlab = new Date(startlab.getTime() + 7200000);

		// lecture is 1h long, end timing of lecture is 1h later
		endlect = new Date(startlect.getTime() + 3600000);

		// tutorial is 1h long, end timing of tutorial is 1h later
		endtut = new Date(starttut.getTime() + 3600000);

		venuetut = "TR" + venuetutcounter++ + "";
		venuelab = "LAB" + venuelabcounter++;
		venuelec = "LT" + venuelectcounter++ + "";

		lab = new Lesson(labday, startlab, endlab, venuelab, indexID);
		tut = new Lesson(tutday, starttut, endtut, venuetut, indexID);
		lect = new Lesson(lectday, startlect, endlect, venuelec, indexID);
	}

	/**
	 * Schedule Constructor to create new schedule object to a course index This
	 * constructor is for courses with only: - Lecture - Tutorial
	 * 
	 * @param lectday The day of lecture lesson.
	 * @param tutday  The day of tutorial lesson.
	 * @param _lect   Start timing of lecture lesson.
	 * @param _tut    Start timing of tutorial lesson.
	 * @param indexID This schedule's CourseIndex ID.
	 */
	public Schedule(int lectday, int tutday, Date _lect, Date _tut, int indexID) {
		this.lectday = lectday;
		this.tutday = tutday;
		this.startlect = _lect;
		this.starttut = _tut;
		this.coursetype = 2;

		endlect = new Date(startlect.getTime() + 3600000);
		endtut = new Date(starttut.getTime() + 3600000);

		venuetut = "TR" + venuetutcounter++ + "";
		venuelec = "LT" + venuelectcounter++ + "";

		tut = new Lesson(tutday, starttut, endtut, venuetut, indexID);
		lect = new Lesson(lectday, startlect, endlect, venuelec, indexID);
	}

	/**
	 * Schedule Constructor to create new schedule object to a course index This
	 * constructor is for courses with only: - Lecture
	 *
	 * @param lectday The day of lecture lesson.
	 * @param _lect   Start timing of lecture lesson.
	 * @param indexID This schedule's CourseIndex ID.
	 */
	public Schedule(int lectday, Date _lect, int indexID) {
		this.lectday = lectday;
		this.startlect = _lect;
		this.coursetype = 3;

		endlect = new Date(startlect.getTime() + 3600000);
		venuelec = "LT" + venuelectcounter++ + "";
		lect = new Lesson(lectday, startlect, endlect, venuelec, indexID);
	}

	/**
	 * Gets the lab lesson of this schedule.
	 * 
	 * @return The lab lesson that this schedule belongs to.
	 */
	public Lesson getLab() {
		return lab;
	}

	/**
	 * Sets the Lab lesson of this schedule.
	 * 
	 * @param lab The lab lesson that we want to set.
	 */
	public void setLab(Lesson lab) {
		this.lab = lab;
	}

	/**
	 * Gets the tutorial lesson of this schedule.
	 * 
	 * @return The tutorial lesson that this schedule belongs to.
	 */
	public Lesson getTut() {
		return tut;
	}

	/**
	 * Sets the tutorial lesson of this schedule.
	 * 
	 * @param tut The tutorial lesson that we want to set.
	 */
	public void setTut(Lesson tut) {
		this.tut = tut;
	}

	/**
	 * Gets the lecture lesson of this schedule.
	 * 
	 * @return The lecture lesson that this schedule belongs to.
	 */
	public Lesson getLect() {
		return lect;
	}

	/**
	 * Sets the lecture lesson of this schedule.
	 * 
	 * @param lect The lecture lesson that we want to set.
	 */
	public void setLect(Lesson lect) {
		this.lect = lect;
	}

	/**
	 * Gets the Index ID of this schedule.
	 * 
	 * @return The Index ID of the course that this schedule belongs to.
	 */
	public String getIndexID() {
		return indexID;
	}

	/**
	 * Gets the venue of the tutorial in this schedule.
	 * 
	 * @return The venue of the tutorial of this schedule.
	 */
	public String getVenueTut() {
		return this.venuetut;
	}

	/**
	 * Gets the venue of the lecture in this schedule.
	 * 
	 * @return The venue of the lecture of this schedule.
	 */
	public String getVenueLect() {
		return this.venuelec;
	}

	/**
	 * Gets the venue of the lab in this schedule.
	 * 
	 * @return The venue of the lab of this schedule.
	 */
	public String getVenueLab() {
		return this.venuelab;
	}

	/**
	 * Gets the course type of this schedule.
	 * 
	 * @return The course type of this schedule.
	 */
	public int getCoursetype() {
		return coursetype;
	}

	/**
	 * Sets the course type of this schedule.
	 * 
	 * @param coursetype The coursetype of this schedule that we want to set
	 */
	public void setCoursetype(int coursetype) {
		this.coursetype = coursetype;
	}

	/**
	 * Method to check if timing of new lessons added clash with existing registered
	 * lessons
	 * 
	 * @param checkDay The lesson day of the incoming course.
	 * @param existDay The lesson day of existing registered courses.
	 * @param start1   The start timing of the incoming lessons.
	 * @param end1     The end timing of the incoming lessons.
	 * @param start2   The start timing of the existing registered lessons.
	 * @param end2     The end timing of the existing registered lessons.
	 * @return a boolean: true if timing clash, false if there are no clashes
	 */
	public boolean timeClash(int checkDay, int existDay, Date start1, Date end1, Date start2, Date end2) {

		if (checkDay != existDay) {
			return false;
		} else {
			if (start1.getTime() < end2.getTime() && start2.getTime() < end1.getTime()) {
				return true;
			} else
				return false;
		}

	}

	/**
	 * Uses timeClash method to check conflict of timing of the different types of
	 * lessons of the new course that is to be added with the different types of
	 * lessons of the existing registered courses.
	 * 
	 * Informs user which lessons clash with which existing type of lesson.
	 * 
	 * @param otherSch Takes in the schedule of the incoming course.
	 * @return true if it clashes, false if it does not clash.
	 * 
	 */
	public boolean checkClash(Schedule otherSch) {
		// try to find a combination of clashes

		// check lab clash with lec of this schedule
		if (timeClash(otherSch.labday, lectday, otherSch.startlab, otherSch.endlab, startlect, endlect) == true) {
			System.out.println("Lab timeslot of this index clashes with lecture of existing schedule");
			return true;
		}

		// check lec clash with lec of this schedule
		if (timeClash(otherSch.lectday, lectday, otherSch.startlect, otherSch.endlect, startlect, endlect) == true) {
			System.out.println("Lecture timeslot of this index clashes with lecture of existing schedule");
			return true;
		}

		// check tut clash with lec of this schedule
		if (timeClash(otherSch.tutday, lectday, otherSch.starttut, otherSch.endtut, startlect, endlect) == true) {
			System.out.println("Tutorial timeslot of this index clashes with lecture of existing schedule");
			return true;
		}
		if (this.coursetype == 2) {
			// check lab clash with tut of this schedule
			if (timeClash(otherSch.labday, tutday, otherSch.startlab, otherSch.endlab, starttut, endtut) == true) {
				System.out.println("Lab timeslot of this index clashes with tutorial of existing schedule");
				return true;
			}

			// check lec clash with tut of this schedule
			if (timeClash(otherSch.lectday, tutday, otherSch.startlect, otherSch.endlect, starttut, endtut) == true) {
				System.out.println("Lecture timeslot of this index clashes with tutorial of existing schedule");
				return true;
			}

			// check tut clash with tut of this schedule
			if (timeClash(otherSch.tutday, tutday, otherSch.starttut, otherSch.endtut, starttut, endtut) == true) {
				System.out.println("Tutorial timeslot of this index clashes with tutorial of existing schedule");
				return true;
			}
			if (this.coursetype == 1) {
				// check lab clash with lab of this schedule
				if (timeClash(otherSch.labday, labday, otherSch.startlab, otherSch.endlab, startlab, endlab) == true) {
					System.out.println("Lab timeslot of this index clashes with lab of existing schedule");
					return true;
				}
				// check lec clash with lab of this schedule
				if (timeClash(otherSch.lectday, labday, otherSch.startlect, otherSch.endlect, startlab,
						endlab) == true) {
					System.out.println("Lecture timeslot of this index clashes with lab of existing schedule");
					return true;
				}
				// check tut clash with lab of this schedule
				if (timeClash(otherSch.tutday, labday, otherSch.starttut, otherSch.endtut, startlab, endlab) == true) {
					System.out.println("Tutorial timeslot of this index clashes with lab of existing schedule");
					return true;
				}
			}

		}
		return false;

	}

	/**
	 * Print schedule information for course type that has all lecture, tutorial and lab.
	 * 
	 * @return The schedule information in a table format.
	 */
	public String printInfoLabTutLec() {
		DateFormat df = new SimpleDateFormat("HH:mm");
		String strlabday = "";
		String strtutday = "";
		String strlectday = "";
		switch (labday) {
		case 1:
			strlabday = "MON";
			break;
		case 2:
			strlabday = "TUE";
			break;
		case 3:
			strlabday = "WED";
			break;
		case 4:
			strlabday = "THU";
			break;
		case 5:
			strlabday = "FRI";
			break;
		default:
			break;
		}

		switch (tutday) {
		case 1:
			strtutday = "MON";
			break;
		case 2:
			strtutday = "TUE";
			break;
		case 3:
			strtutday = "WED";
			break;
		case 4:
			strtutday = "THU";
			break;
		case 5:
			strtutday = "FRI";
			break;
		default:
			break;
		}

		switch (lectday) {
		case 1:
			strlectday = "MON";
			break;
		case 2:
			strlectday = "TUE";
			break;
		case 3:
			strlectday = "WED";
			break;
		case 4:
			strlectday = "THU";
			break;
		case 5:
			strlectday = "FRI";
			break;
		default:
			break;
		}

		return "====================================================\n" + "  ClassType	Day   	   Time	 	Venue   "
				+ "\n====================================================" + "\n     Lab	" + strlabday + "	"
				+ df.format(startlab) + "-" + df.format(endlab) + "	" + venuelab + "\n" + "\n     Lec	" + strlectday
				+ "	" + df.format(startlect) + "-" + df.format(endlect) + "	" + venuelec + "\n" + "\n     Tut	"
				+ strtutday + "	" + df.format(starttut) + "-" + df.format(endtut) + "	" + venuetut + "\n"
				+ "====================================================";
	}

	/**
	 * Print schedule information for course type that has lectures and tutorials only.
	 * 
	 * @return The schedule information in a table format.
	 */
	public String printInfoTutLec() {
		DateFormat df = new SimpleDateFormat("HH:mm");
		String strtutday = "";
		String strlectday = "";

		switch (tutday) {
		case 1:
			strtutday = "MON";
			break;
		case 2:
			strtutday = "TUE";
			break;
		case 3:
			strtutday = "WED";
			break;
		case 4:
			strtutday = "THU";
			break;
		case 5:
			strtutday = "FRI";
			break;
		default:
			break;
		}

		switch (lectday) {
		case 1:
			strlectday = "MON";
			break;
		case 2:
			strlectday = "TUE";
			break;
		case 3:
			strlectday = "WED";
			break;
		case 4:
			strlectday = "THU";
			break;
		case 5:
			strlectday = "FRI";
			break;
		default:
			break;
		}

		return "====================================================\n" + "  ClassType	Day   	   Time	 	Venue   "
				+ "\n====================================================" + "\n     Lec	" + strlectday + "	"
				+ df.format(startlect) + "-" + df.format(endlect) + "	" + venuelec + "\n" + "\n     Tut	"
				+ strtutday + "	" + df.format(starttut) + "-" + df.format(endtut) + "	" + venuetut + "\n"
				+ "====================================================";
	}

	/**
	 * Print schedule information for course type that has lectures only.
	 * 
	 * @return The schedule information in a table format.
	 */
	public String printInfoLec() {
		DateFormat df = new SimpleDateFormat("HH:mm");
		String strlectday = "";

		switch (lectday) {
		case 1:
			strlectday = "MON";
			break;
		case 2:
			strlectday = "TUE";
			break;
		case 3:
			strlectday = "WED";
			break;
		case 4:
			strlectday = "THU";
			break;
		case 5:
			strlectday = "FRI";
			break;
		default:
			break;
		}

		return "====================================================\n" + "  ClassType	Day   	   Time	 	Venue   "
				+ "\n====================================================" + "\n     Lec	" + strlectday + "	"
				+ df.format(startlect) + "-" + df.format(endlect) + "	" + venuelec + "\n"
				+ "====================================================";
	}

	@Override
	/**
	 * toString method that overrides the default toString() function Prints the
	 * schedule information in desired format
	 * 
	 * Since we have 3 different types of schedules, we will select which respective
	 * printing methods to print for courses with: 
	 * - Lab, lecture and tutorial 
	 * - Lecture and tutorial only 
	 * - Lecture only
	 * 
	 */
	public String toString() {
		if (this.coursetype == 1) {
			return printInfoLabTutLec();
		} else if (this.coursetype == 2) {
			return printInfoTutLec();
		} else if (this.coursetype == 3) {
			return printInfoLec();
		}
		return "Invalid Schedule";
	}

}