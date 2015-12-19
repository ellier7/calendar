
package GroupProject;

import java.awt.Color;
import javax.swing.*;

@SuppressWarnings("serial")
public class Day extends JScrollPane{
	
	JTextArea display = new JTextArea();
	int dayOfTheMonth;
	boolean isActive;
	
	public Day(){
		init();
	}
	
	public void init(){
	
		// add borders to each day
		setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		setActive(false);
		display.setEditable(false);
		
		display.setLineWrap(true);
		display.setWrapStyleWord(true);
		
		getViewport().setView(display);
		
		dayOfTheMonth = 0;
	}
	
	/**
	 * highlights boarder of current day in DateBook highlightCurrentDay()
	 */
	public void highlight(){
		setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
	}
	
	/**
	 * set text for labels in DateBook
	 */
	public void setText(String text){
		
		display.setText(text);
	}
	
	/**
	 * gets event title text to display on GUI calendar
	 */
	public void addEvent(Event e){
			
		display.setText(display.getText() + "\n- " + e.getTitle());
	}
	
	/**
	 * used in Month to set days
	 */
	public void setDay(int d){
		dayOfTheMonth = d;
		setText("" + d);
	}
	
	public int getDayOfTheMonth(){
		return dayOfTheMonth;
	}
	
	/**
	 * if day is set active then it appears in the month on the GUI
	 */
	public void setActive(boolean b){
		isActive = b;
		setVisible(b);
	}
	
	public boolean isActive(){
		return isActive;
	}
}
