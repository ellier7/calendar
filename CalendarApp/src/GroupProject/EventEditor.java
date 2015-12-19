

package GroupProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class EventEditor extends EventWindow{

	// the event being edited
	private Event event;
	
	/**
	 * One parameter constructor for the EventEditor class. Invokes the one parameter 
	 * constructor from the abstract EventEditor class. 
	 * @param ev
	 */
	public EventEditor(Event ev){
		super("Editor");
		event = ev;
		initialize();
	}

	/**
	 * Takes the values of each data member of the event to be edited, and sets the initial values 
	 * of the window's input fields to match.
	 */
	protected void initialize() {
		
		titleInputField.setText(event.getTitle());				// title
		locationInputField.setText(event.getLocation());		// location
		descriptionInputField.setText(event.getDescription());	// description
		displayDate.setText(event.getDate());					// date
		
		fromHour.setSelectedItem("" + event.getStartTime().getHoursAs12());		// start time hour
		fromMin.setSelectedItem("" + event.getStartTime().getMins());			// start time min
		
		if(event.getStartTime().toString().endsWith("am")){		// am
			fromAmPm.setSelectedItem("AM");
		}
		else{
			fromAmPm.setSelectedItem("PM");						// pm
		}
		
		toHour.setSelectedItem("" + event.getEndTime().getHoursAs12());			// end time hour
		toMin.setSelectedItem("" + event.getEndTime().getMins());				// end time min
		
		if(event.getStartTime().toString().endsWith("am")){
			toAmPm.setSelectedItem("AM");						// am
		}
		else{
			toAmPm.setSelectedItem("PM");						// pm
		}
		
		colorDropDown.setSelectedItem(event.getColorText());		// color
		priorityDropDown.setSelectedIndex(event.getPriority());		// priority
		
		allDayCheckBox.setSelected(event.getIsAllDay());			// all day
		setTimeDisplay();	// grays out start and end time inputs if event is all day
		
		weeklyCheckBox.setSelected(event.getRepeatController().getIsRepeatedWeekly());	// repeated weekly
		
		if (event.getRepeatController().getIsRepeatedWeekly()) {
			/* gets the action listener from the weekly check box and triggers an action
			 * to correctly gray out other repeat check boxes  */
			for (ActionListener a : weeklyCheckBox.getActionListeners()) {
				ActionEvent e = new ActionEvent(weeklyCheckBox, ActionEvent.ACTION_PERFORMED, null);
				a.actionPerformed(e);
			}
		}
		
		monthlyCheckBox.setSelected(event.getRepeatController().getIsRepeatedMonthly());	// repeated monthly
		
		if (event.getRepeatController().getIsRepeatedMonthly()) {					
			/* gets the action listener from the monthly check box and triggers an action
			 * to correctly gray out other repeat check boxes  */
			for (ActionListener a : monthlyCheckBox.getActionListeners()) {
				ActionEvent e = new ActionEvent(monthlyCheckBox, ActionEvent.ACTION_PERFORMED, null);
				a.actionPerformed(e);
			}
		}
		
		yearlyCheckBox.setSelected(event.getRepeatController().getIsRepeatedYearly());		// repeat yearly
		
		if (event.getRepeatController().getIsRepeatedYearly()) {
			/* gets the action listener from the yearly check box and triggers an action
			 * to correctly gray out other repeat check boxes  */
			for (ActionListener a : yearlyCheckBox.getActionListeners()) {
				ActionEvent e = new ActionEvent(yearlyCheckBox, ActionEvent.ACTION_PERFORMED, null);
				a.actionPerformed(e);
			}
		}
		
		for(int i = 0; i < 7; i++){					// repeat daily
			dailyArr[i].setSelected(event.getRepeatController().getIsRepeatedDaily()[i]);
		}
		setWMYEnabled();		// gray out weekly, monthly , and yearly check boxes if necessary 
	}

	@Override
	protected void saveEvent() {
		// first removes the old event from the list of events 
		DateBook.getEventList().remove(event);
		
		String [] dateArr;
		Event ev = new Event();		// crates new event...
	
		// set prio
		ev.setPriority(Integer.parseInt((String)priorityDropDown.getSelectedItem()));
		
		// set date
		dateArr = displayDate.getText().split("/");
		ev.setDate(Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[2]));
		
		// set start time
		ev.getStartTime().setTime(Integer.parseInt(hourArr[fromHour.getSelectedIndex()]), 
				Integer.parseInt(minArr[fromMin.getSelectedIndex()]), amPmArr[fromAmPm.getSelectedIndex()]);
	
		// set end time
		ev.getEndTime().setTime(Integer.parseInt(hourArr[toHour.getSelectedIndex()]), 
				Integer.parseInt(minArr[toMin.getSelectedIndex()]), amPmArr[toAmPm.getSelectedIndex()]);
		
		// set color
		ev.setColor((String) colorDropDown.getSelectedItem()); 
		
		// set title
		ev.setTitle(titleInputField.getText());	
		
		// set description
		ev.setDescription(descriptionInputField.getText());
		
		// set location
		ev.setLoaction(locationInputField.getText());
		
		// set isAllDay
		ev.setIsAllDay(allDayCheckBox.isSelected());
	
		// set IsRepeatedWeekly
		ev.getRepeatController().setIsRepeatedWeekly(weeklyCheckBox.isSelected());
		
		// set IsRepeatedMonthly
		ev.getRepeatController().setIsRepeatedMonthly(monthlyCheckBox.isSelected());
		
		// set IsRepeatedYearly
		ev.getRepeatController().setIsRepeatedYearly(yearlyCheckBox.isSelected());
	
		// set IsRepeatedDaily
		for(int i = 0; i < 7; i++){
			ev.getRepeatController().setIsRepeatedDaily(i, dailyArr[i].isSelected());
		}
		
		// add new event to list 
		DateBook.getEventList().add(ev);
	}
	
	@Override
	protected void deleteEvent() {
		// remove the event from the list 
		DateBook.getEventList().remove(event);		
		
	}
	
	public static void main(String[] args) {
		Event e = new Event();
		e.setDate(3, 11, 2015);
		e.setDescription("dfghjklfjhrfnmckiuhrnfkc");
		e.setLoaction("here");
		e.setColor("Orange");
		e.setTitle("the title");
		e.setPriority(5);
		e.getStartTime().setTime(13, 30);
		e.getEndTime().setTime(14, 30);
		e.setIsAllDay(true);
		e.getRepeatController().setIsRepeatedDaily(1, true);
		e.getRepeatController().setIsRepeatedDaily(2, true);
		
		EventEditor editor = new EventEditor(e);
		editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
