package proj;

import java.io.Serializable;
import java.util.Date;

/**
 * Lesson Entity Class
 * 
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 */

public class Lesson implements Serializable {

	/**
	 * Start time of lesson object.
	 */
	private Date starttime;

	/**
	 * End time of lesson object.
	 */
	private Date endtime;

	/**
	 * Venue of the lesson being held.
	 */
	private String venue;

	/**
	 * The indexID of courseIndex of which the lesson is from.
	 */
	private int indexID;

	/**
	 * The day of the lesson.
	 */
	private int day;

	/**
	 * Constructor to create new lesson object
	 * 
	 * @param day     The day the lesson is held.
	 * @param start   The start time of the lesson.
	 * @param end     The end time of the lesson.
	 * @param venue   The venue of a lesson.
	 * @param indexID The indexID of a CourseIndex the lesson belongs to.
	 */
	public Lesson(int day, Date start, Date end, String venue, int indexID) {
		this.day = day;
		this.starttime = start;
		this.endtime = end;
		this.venue = venue;
		this.indexID = indexID;
	}

	/**
	 * Gets the start time of a lesson
	 * 
	 * @return The start time of a lesson.
	 */
	public Date getStarttime() {
		return starttime;
	}

	/**
	 * Sets the start time of a lesson
	 * 
	 * @param starttime The start time of a lesson.
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	/**
	 * Gets the end time of a lesson
	 * 
	 * @return The end time of a lesson.
	 */
	public Date getEndtime() {
		return endtime;
	}

	/**
	 * Sets the end time of the lesson
	 * 
	 * @param endtime The end time of lesson.
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	/**
	 * Gets the venue of the lesson
	 * 
	 * @return The venue of the lesson.
	 */
	public String getVenue() {
		return venue;
	}

	/**
	 * Sets the venue of the lesson
	 * 
	 * @param venue The venue of the lesson we want to set.
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}

	/**
	 * Gets the indexID of the courseIndex the lesson belongs to.
	 * 
	 * @return The indexID of the courseIndex the lesson belongs to.
	 */
	public int getIndexID() {
		return indexID;
	}

	/**
	 * Sets the indexId of courseIndex the lesson belongs to
	 * 
	 * @param indexID The indexID of the courseIndex.
	 */
	public void setIndexID(int indexID) {
		this.indexID = indexID;
	}

	/**
	 * Gets the day the lesson is being held
	 * 
	 * @return The day the lesson is being held.
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Sets the day the lesson is being held
	 * 
	 * @param day The day the lesson is being held.
	 */
	public void setDay(int day) {
		this.day = day;
	}

}
