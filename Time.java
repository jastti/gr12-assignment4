package tv.show;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A Time object has three variables, and that is the time in number of hours, minutes, and seconds.
 * (Constructor: Should have 1 parameter – String format of hh:mm:ss.)
 */
public class Time {
	private int numOfSeconds;
	private int numOfMinutes;
	private int numOfHours;
	/**
	 * Constructor to Initialize Time class Object
	 * @param time input string format "hh:mm:ss"
	 */
	public Time(String time) {
		/*
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		
		try {
			Date date = dateFormat.parse(time);
			Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
			calendar.setTime(date);   // assigns calendar to given date 
			
			setNumOfHours(calendar.get(Calendar.HOUR)); // gets hour in 24h format
			setNumOfMinutes(calendar.get(Calendar.MINUTE));
			setNumOfSeconds(calendar.get(Calendar.SECOND));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		String []timeParts = time.split(":");
		setNumOfHours(Integer.parseInt(timeParts[0]));
		setNumOfMinutes(Integer.parseInt(timeParts[1]));
		setNumOfSeconds(Integer.parseInt(timeParts[2]));
	}
	
	public Time(int h,int m, int s) {
		numOfHours = h;
		numOfMinutes = m;
		numOfSeconds = s;
	}
	/**
	 * @return the num Of Seconds
	 */
	public int getNumOfSeconds() {
		return numOfSeconds;
	}
	/**
	 * @param numOfSeconds the number Of Seconds to set
	 */
	public void setNumOfSeconds(int numOfSeconds) {
		this.numOfSeconds = numOfSeconds;
	}
	/**
	 * @return the number Of Minutes
	 */
	public int getNumOfMinutes() {
		return numOfMinutes;
	}
	/**
	 * @param numOfMinutes the number Of Minutes to set
	 */
	public void setNumOfMinutes(int numOfMinutes) {
		this.numOfMinutes = numOfMinutes;
	}
	/**
	 * @return the number Of Hours
	 */
	public int getNumOfHours() {
		return numOfHours;
	}
	/**
	 * @param numOfHours the number Of Hours to set
	 */
	public void setNumOfHours(int numOfHours) {
		this.numOfHours = numOfHours;
	}
	
	@Override
	public String toString() {
		return numOfHours + ":" + numOfMinutes  + ":" +numOfSeconds;
		
	}
}
