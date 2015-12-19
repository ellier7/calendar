
package GroupProject;

import java.io.*;
import java.util.*;

public class EventWriter {
	
	protected final static String DEFAULT_FILE_NAME = "C://tmp//DateBookEventFile.txt";

	protected static void writeAll(){
		try {
	    	  FileOutputStream fileOut = new FileOutputStream(DEFAULT_FILE_NAME, false);
	    	  BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOut); 
		      PrintStream printStream = new PrintStream(bufferedOut);
		   
		      Iterator<Event> it = DateBook.getEventList().iterator();
		      while(it.hasNext()){	//writes events created into a text file
		    	  Event ev = it.next();
		    	  printStream.println(ev.getPriority());
		    	  printStream.println(ev.getYear());
		    	  printStream.println(ev.getMonth());
		    	  printStream.println(ev.getDay());
		    	  printStream.println(ev.getStartTime().getTimeAs24()); 
		    	  printStream.println(ev.getEndTime().getTimeAs24());
		    	  printStream.println(ev.getColorText());
		    	  printStream.println(ev.getTitle());
		    	  printStream.println(ev.getDescription());
		    	  printStream.println(ev.getLocation());
		    	  printStream.println(ev.getIsAllDay());
		    	  printStream.println(ev.getRepeatController().getIsRepeatedWeekly());
		    	  printStream.println(ev.getRepeatController().getIsRepeatedMonthly());
		    	  printStream.println(ev.getRepeatController().getIsRepeatedYearly());
		    	  
		    	  StringBuilder build = new StringBuilder();	//String builder to add Sunday-Monday if true
		    	  for(int i = 0; i < 7; i++){
		    		  if(ev.getRepeatController().getIsRepeatedDaily()[i]){
		    			  build.append(i);
		    		  }
		    	  }
		    	  printStream.println(build);
		      }
		      printStream.close();
	    } 
	    catch (Exception ex) {
		      System.err.println(ex );
	    }
	}
	  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*EventWriter x = new EventWriter();
		x.writeAll();*/
	}
	




}
