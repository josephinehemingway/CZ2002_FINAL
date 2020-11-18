package proj;

import java.io.Serializable;
import java.util.Date;

public class Lesson implements Serializable{
	private Date starttime;
	private Date endtime;
	private String venue;
	private int indexID;
	private int day;
	
	public Lesson(int day, Date start, Date end, String venue, int indexID) {
		this.day = day;
		this.starttime = start;
		this.endtime = end;
		this.venue = venue;
		this.indexID = indexID;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public int getIndexID() {
		return indexID;
	}

	public void setIndexID(int indexID) {
		this.indexID = indexID;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	
}
