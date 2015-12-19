
package GroupProject;

public class Time {
	
	private int hours;	// 1 - 12 - 24 : 1am - 12pm - 12am
	private int mins;	// 0 - 59
	
	public Time(){
		
	}
	
	public Time(int h, int m){
		hours = h;
		mins = m;
	}

	public int getMins(){
		return mins;
	}
	
	public void setMins(int m){
		mins = m;
	}
	
	public int getHours(){
		return hours;
	}
	
	public void setHours(int h){
		hours = h;
	}
	
	/**
	 * Method sets hours and minutes based on a 24-hour time
	 */
	public void setTime(int h, int m){
		mins = m;
		hours = h;
	}
	
	/**
	 * Method sets hours and minutes based on a 12-hour time
	 */
	public void setTime(int hrs, int mns, String isAm){
			
		if((isAm.equalsIgnoreCase("am") && hrs == 12) || (hrs < 12 && isAm.equalsIgnoreCase("pm"))){
			hrs += 12;
		}
			
		hours = hrs;
		mins = mns;
	}
	
	
	/**
	 * Method converts 24-hour to 12-hour
	 */
	public int getHoursAs12(){
		
		if(hours > 12){
			return hours - 12;
		}
		return hours;
	}

	/**
	 * Method returns time as 24-hour
	 */
	public String getTimeAs24(){
		String hrs = "" + hours;
		String mns = "" + mins;
		
		// pad with 0 if mins/hours is less than 10
		if(mins < 10){
			mns = "0" + mins;
		}
		if(hours < 10){
			hrs = "0" + hours;
		}
		
		return hrs + mns;
	}
	
	public String toString(){
		
		String hrs;
		String mns;
		String amPm;
		
		// determine if time is AM or PM
		if(hours >= 12 && hours < 24){
			amPm = "pm";
		}
		else 
			amPm = "am";
		// if hours is 12(12pm) or 24(12am), set hrs to 12
		if(hours == 12 || hours == 24){
			hrs = "" + 12;
		} 
		else{
			hrs = "" + hours % 12;
		}
		
		// pad with 0 if mins is less than 10
		if(mins < 10){
			mns = "0" + mins;
		}
		else
			mns = "" + mins;

		return hrs + ":" + mns + " " + amPm;
	}
	
	public static void main(String[] args){
		Time t = new Time();
		t.setHours(23);
		t.setMins(5);
		
		System.out.println(t);
		
		// test
		t.setTime(3, 20, "am");
		System.out.println(t);
		System.out.println(t.getHours());
		System.out.println(t.getMins());
	}
}
