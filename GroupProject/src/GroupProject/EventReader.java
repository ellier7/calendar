
package GroupProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventReader {

	protected final static String DEFAULT_FILE_NAME = "C://tmp//DateBookEventFile.txt";
	private String fileName;
	private List<Event> fileEvents = new ArrayList<Event>();
	
	//make dir and file if not already there
	static{
		File dir = new File("C://tmp//");
		dir.mkdirs();
		try {
		      File file = new File("C://tmp//DateBookEventFile.txt");
		      
		      if (file.createNewFile()){
		        System.out.println("File is created!");
		      }else{
		        System.out.println("File already exists.");
		      }
		      
	    	} catch (IOException e) {
		      e.printStackTrace();
		}
	}
	public EventReader() {
		fileName = DEFAULT_FILE_NAME; 
	}
	
	public EventReader(String fileName) {
		this.fileName = fileName; 
	}
	
	public String getFileName() {
		return fileName;
	}
	
	@Override
	public String toString() {
		return "EventReader [fileName=" + fileName + "]";
	}
	
	/**
	 * Reads in all field info from text file to ArrayList of events
	 */
	public List<Event> readAll(){
		 try {
			 // create a bufferedReader to read line from 
		    	java.io.FileReader fileReader = new java.io.FileReader(this.fileName);  
		    	BufferedReader bufferedReader = new BufferedReader(fileReader);
			    String line; 
			    
			    /* for each event, read the next line of the file, while 
			     * there is another line of text: */
			    while( ( line = bufferedReader.readLine() ) != null ) {
			    	
			    	// ignore lines that start with '*'
			    	if(line.startsWith("*")){
			    		continue;
			    	}
			    	
			    	Event ev = new Event();
			    	
			    	// set prio 
					ev.setPriority(Integer.parseInt(line));
					
					// read next line and set year
					line = bufferedReader.readLine();
					ev.setYear(Integer.parseInt(line));
					
					// read next line and set month
					line = bufferedReader.readLine();
					ev.setMonth(Integer.parseInt(line));
					
					// read next line and set day
					line = bufferedReader.readLine();
					ev.setDay(Integer.parseInt(line));
					
					// read next line and set startTime
					line = bufferedReader.readLine();
					ev.getStartTime().setHours(Integer.parseInt(line.substring(0,2)));
					ev.getStartTime().setMins(Integer.parseInt(line.substring(2,4)));
					
					// read next line and set endTime
					line = bufferedReader.readLine();
					ev.getEndTime().setHours(Integer.parseInt(line.substring(0,2)));
					ev.getEndTime().setMins(Integer.parseInt(line.substring(2,4)));
					
					// read next line and set color
					line = bufferedReader.readLine();
					ev.setColor(line);
					
					// read next line and set title
					line = bufferedReader.readLine();
					ev.setTitle(line);
					
					// read next line and set description
					line = bufferedReader.readLine();
					ev.setDescription(line);
					
					// read next line and set location
					line = bufferedReader.readLine();
					ev.setLoaction(line);
					
					// read next line and set isAllDay
					line = bufferedReader.readLine();
					ev.setIsAllDay(Boolean.parseBoolean(line));
	
					// repeat options
					
					// read next line and set isRepeatedWeekly
					line = bufferedReader.readLine();
					ev.getRepeatController().setIsRepeatedWeekly(Boolean.parseBoolean(line));
	
					// read next line and set isRepeatedMonthly
					line = bufferedReader.readLine();
					ev.getRepeatController().setIsRepeatedMonthly(Boolean.parseBoolean(line));
					
					// read next line and set isRepeatedYearly
					line = bufferedReader.readLine();
					ev.getRepeatController().setIsRepeatedYearly(Boolean.parseBoolean(line));
					
					// read next line and set isRepeatedDaily[i] if i is contained by line	
					line = bufferedReader.readLine();
					for(int i = 0; i < 7; i++){
						if(line.contains("" + i)){
							ev.getRepeatController().setIsRepeatedDaily(i, true);
						}
					}
					
					// add event to the list 
					fileEvents.add(ev);
					
			    }
		    	
		    	fileReader.close();
	
		    } 
		    catch (Exception ex) {
			      System.err.println(ex );
		    }
		
		return fileEvents;
		
	}

	public static void main(String[] args) {
		EventReader er = new EventReader();
		
		List<Event> list = er.readAll();
		
		Iterator<Event> it = list.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}