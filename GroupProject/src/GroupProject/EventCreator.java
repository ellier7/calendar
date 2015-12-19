

package GroupProject;

@SuppressWarnings("serial")
public class EventCreator extends EventWindow{

	/**
	 * Default constructor for EventCreator class. Invokes the one parameter constructor for the 
	 * abstract EventWindow class. 
	 */
	public EventCreator() {
		super("Creator");
	}

	/**
	 * Creates a new event object from the input fields in the abstract EventWindow class, 
	 * and adds that event to the static list of all events. 
	 */
	@Override
	protected void saveEvent() {
		String [] dateArr;
		Event ev = new Event();
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
		
	}
	
	public static void main(String[] args) {
		EventCreator e = new EventCreator();
		e.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
