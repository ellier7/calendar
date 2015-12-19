
package GroupProject;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Calendar;

public class Event {
	
		// private members
		private static int numOfEvents = 0;
		protected static String[] colors = {"Red", "Green","Blue", "Black", "Orange"};
		private int priority;
		private int year;
		private int month;
		private int day;
		private Time startTime;
		private Time endTime;
		private String color;
		private String title;
		private String description;
		private String location;
		private boolean isAllDay;
		private RepeatController repeatController;
		
		// static constants
		protected final static int DEFAULT_PRIORITY = 1;
		protected final static String DEFAULT_COLOR = "Blue";
		protected final static boolean DEFAULT_ISALLDAY = false;
		
		// get todays date
		protected final static int DEFAULT_DAY = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		protected final static int DEFAULT_MONTH = Calendar.getInstance().get(Calendar.MONTH);
		protected final static int DEFAULT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
		
		
		public Event(){
			
			numOfEvents++;

			init();
		}
		
		public void init(){
			
			// the date equals todays date by default
			startTime = new Time();
			endTime = new Time();
			day = DEFAULT_DAY;
			month = DEFAULT_MONTH;
			year = DEFAULT_YEAR;
					
			priority = DEFAULT_PRIORITY;
			color = DEFAULT_COLOR;
			title = "";
			description = "";
			location = "";
			isAllDay = DEFAULT_ISALLDAY;
					
			repeatController = new RepeatController();
		}

		public String toString() {
			return "Event [totalNumberOfEvents=" + numOfEvents + ", startTime=" + startTime + ",endTime" + endTime + ",title=" + title + ", priority=" + priority 
					+ ", year=" + year + ", month=" + month + ", day=" + day + ", color="
					+ color + ", description=" + description + ", location=" 
					+ location + ", isAllDay=" + isAllDay + " " + repeatController
					+ "]\n";
		}

		// decrements the instance counter when garbage collector is called
		protected void finalize(){
			numOfEvents--;
		}
		
		/* sets the priority of the event, events are prioritized on a 
		 * 1 through 5 scale.
		 * uses DEFAULT_PRIORITY if argument is out of accepted range
		 */
		public void setPriority(int p){
			this.priority = (p >= 0 && p <= 5) ?  p : DEFAULT_PRIORITY;
		}
		
		public void setDate(int day, int month, int year){
			this.day = day;
			this.month = month;
			this.year = year;
		}
		
		public void setDay(int d){
			this.day = d;
		}
		
		public void setMonth(int m){
			this.month = m;
		}
		
		public void setYear(int y){
			this.year = y;
		}
		
		// 5 colors/groups: Red, Green, Blue, Black, Orange
		public void setColor(String c){
			if(c.equals("Green") || c.equals("Grün") || c.equals("Noksaek") || c.equals("Verde")){
				this.color = colors[0];
				
			}else if(c.equals("Red") || c.equals("Rot") || c.equals("Bbalgan") || c.equals("Rojo")){
				this.color = colors[1];
				
			}else if(c.equals("Blue") || c.equals("Blau") || c.equals("Palang") || c.equals("Azul")){
				this.color = colors[2];
				
			}
			else if(c.equals("Black") || c.equals("Schwarz") || c.equals("Geom-eun") || c.equals("Negro")){
				this.color = colors[3];
			}
			else if(c.equals("Orange") || c.equals("Orange") || c.equals("Juhwangsaek") || c.equals("Anaranjado")){
				this.color = colors[4];	
			}
			else {
				this.color = DEFAULT_COLOR;
			}
		}
		
		public void setTitle(String t){
			this.title = t;
		}
		
		public void setDescription(String d){
			this.description = d;
		}
		
		public void setLoaction(String l){
			this.location = l;
		}
		
		public void setIsAllDay(boolean b){
			this.isAllDay = b;
			//if isAllDay is true sets start/end time to 24
			if(b){
				this.startTime.setTime(24, 0);
				this.endTime.setTime(24, 0);
			}
		}
		
		public int getNumOfEvents(){
			return numOfEvents;
		}
		
		public int getPriority(){
			return priority;
		}
		
		public String getDate(){
			String d;
			String m;
			
			if(month < 10){
				m = "0" + month;
			}
			else 
				m = "" + month;
			if(day < 10){
				d = "0" + day;
			}
			else 
				d = "" + day;
			
			return m + "/" + d + "/" + year;
		}
		
		public int getDay(){
			return day;
		}
		
		public Time getStartTime(){
			return startTime;
		}
		
		public Time getEndTime(){
			return endTime;
		}
		
		public int getMonth(){
			return month;
		}
		
		public int getYear(){
			return year;
		}
		
		public String getColorText(){
			return color;
		}
		
		/**
		 * Used in EventViewer to get the color of the event and set boarder in EventViewer
		 * @return
		 */
		public Color getColor(){
			Color c;
			try {
				Field field = Class.forName("java.awt.Color").getField(color.toLowerCase());
				c = (Color) field.get(null);
			} catch (Exception e) {
				c = null;
			}
			
			return c;
		}
		
		public String getTitle(){
			return title;
		}
		
		public String getDescription(){
			return description;
		}
		
		public String getLocation(){
			return location;
		}
		
		public boolean getIsAllDay(){
			return isAllDay;
		}
		
		public RepeatController getRepeatController(){
			return repeatController;
		}

		public static void main(String args[]){
			
			Event e1 = new Event();
			
			for(int i = 0; i < 7; i++){
				e1.getRepeatController().setIsRepeatedDaily(i, true);
			}
			System.out.println(e1);
		}
}
