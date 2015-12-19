
package GroupProject;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

@SuppressWarnings("serial")
public class Month extends JPanel{

	private static final int[] NUM_OF_DAYS = {31,28,31,30,31,30,31,31,30,31,30,31};
	private Day days[] = new Day[42];
	
	private int numOfDays;			// the number of days in this month
	private int monthOfTheYear;		// the month of the year 
	private int year;				// the year of this month 

	/**
	 * The two parameter constructor for the Month class. 
	 * @param m
	 * @param y
	 */
	public Month(int m, int y){
		
		init(m,y);
	}
	
	public void init(int m, int y){
		int dayShift;
		int count;
		
		year = y;
		monthOfTheYear = m;
		setLayout(new GridLayout(6,7,5,6));
		
		setNumOfDays();
		
		for(int i = 0; i < 42;i++){
			days[i] = new Day();
		}
		
		// find the day of the week the month starts on 
		dayShift = startsOn(1,m,y);
			
		//set active days
		for(int i = dayShift;i <numOfDays + dayShift;i++){
			days[i].setActive(true);
		}
			
		// Enumerate days of the month
		count = 1;
		for(int i = 0; i < 42; i++){
				
			if(days[i].isActive()){
				days[i].setDay(count);
				count++;
			}
		}
		addDays();
	}
	
	/**
	 * Based on an algorithm derived by the mathematician Carl Friedrich Gauss. Performs an 
	 * algorithm to determine the day of the week of a given Gergorian calendar date. 
	 * Note this method does not assess the validity of the date passed in.
	 * @param day
	 * @param month
	 * @param year
	 * @return The 0 - 6 index of the day of the week that the passed in date corresponds to.
	 * (0 refers to sunday, 1 to monday,... etc.)
	 */
	public int startsOn(int day, int month, int year){
		/*
		 * Let year = Y, m = month - 2 mod 12 (March = 1,..., January = - 1 mod 12 = 11 
		 * and February = 12) and d = days of the month
		 * 
		 * w = d + [2.6m - 0.2] + 5R(Y,4) + 4R(Y,100) + 6R(Y,400) mod 7
		 * where R(y,m) is the remainder after division of y by m, or y modulo m.
		 */
		
		// if month is January or February, dec year
		if(month == 1 || month == 2){
			year = year - 1;
		}
		
		// month = month - 2 mod 12
		month = (month + 10) % 12;		
		
		if(month == 0){
			month = 12;
		}
		
		int w = (int) (day + (2.6 * month - .2) + 5*(year % 4) + 4*(year % 100) + 6*(year % 400));
		
		return w % 7;
	}
	
	/**
	 * Returns the number of active days this month instance contains.
	 * @return The number of days in this month.
	 */
	public int getNumOfDays(){
		return numOfDays;
	}
	
	private  void setNumOfDays(){
		
		// if leap year and February
		if(year % 4 == 0 && monthOfTheYear == 2){
			numOfDays = NUM_OF_DAYS[monthOfTheYear - 1] + 1;
		}
		else 
			numOfDays = NUM_OF_DAYS[monthOfTheYear - 1];
	}
	
	/**
	 * Adds all Day objects to the Month.
	 */
	private void addDays(){
		for(int i = 0; i < 42; i++){
			add(days[i]);
		}
	}
	
	/**
	 * Returns the Day objects associated with this instance of Month as an array.
	 * @return An array of Days.
	 */
	public Day[] getDays(){
		return days;
	}
	
	/**
	 * Returns the active days associated with this instance of Month as a list.
	 * @return A list of Days. 
	 */
	private List<Day> getActiveDays(){
		List<Day> list = new ArrayList<Day> (28);
		
		// for each day...
		for(int i = 0; i < days.length; i++){
			// ... if the day is active
			if(days[i].isActive()){
				list.add(days[i]);
			}
		}
		
		return list;
	}
	
	/**
	 * Returns the 1 - 12 index of the month of year.
	 * @return The index of the month associated with this Month instance.
	 */
	public int getMonth(){
		return monthOfTheYear;
	}
	
	/**
	 * Returns the year.
	 * @return The year associated with this Month instance.
	 */
	public int getYear(){
		return year;
	}

	@Override
	public String toString() {
		return "Month [numOfDays=" + numOfDays + ", monthOfTheYear=" + monthOfTheYear 
				+ ", year=" + year + "]";
	}
	
	/**
	 * Iterates through the list of days for the current month, finds the day corresponding to the 
	 * day of the passed in event, and adds the title of the event to the day's text area.
	 * @param ev
	 */
	protected void displayOnDay(Event ev){
		Iterator<Day> itD = getActiveDays().iterator();
		// for each active day...
		while (itD.hasNext()) {
			Day day = itD.next();
			
			// ...if the day corresponds to the event's day
			if (day.dayOfTheMonth == ev.getDay()) {
				day.addEvent(ev);	
			}
		}
	}
	
}