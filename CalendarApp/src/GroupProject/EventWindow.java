
package GroupProject;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This class will be the abstract super class for the EventCreator and EventEditor classes.
 *
 */
@SuppressWarnings("serial")
public abstract class EventWindow extends JFrame implements ActionListener{
	
	// text keys
	private static String[] windowTitleText;
	private static String selectDateBtnText;
	private static String titleLblText;
	private static String startTimeLblText;
	private static String endTimeLblText;
	private static String hourLblText; 
	private static String minLblText;
	private static String amPmLblText;
	private static String locationLblText;
	private static String allDayLblText;
	private static String descriptionLblText;
	private static String priorityLblText;
	private static String colorLblText;
	private static String repeatOptionsLblText;
	private static String weeklyLblText;
	private static String monthlyLblText;
	private static String yearlyLblText;
	private static String saveBtnText;
	protected static String deleteBtnText;

	static {
		initFromPropBundle(); 
	}
	
	/**
	 * Looks at the current default locale, and uses the appropriate property file 
	 * to set the values of all displayed labels and text.
	 * 
	 * Supported languages include English, German, Spanish and Korean
	 */
	private static void initFromPropBundle() {
		ResourceBundle bundle;
		
		if(Locale.getDefault().equals(new Locale("de_DE"))){
			bundle = ResourceBundle.getBundle("EventWindowProp_de");
			
		}else if(Locale.getDefault().equals(new Locale("es_ES"))){
			bundle = ResourceBundle.getBundle("EventWindowProp_es");
			
		}else if(Locale.getDefault().equals(new Locale("ko_KO"))){
			bundle = ResourceBundle.getBundle("EventWindowProp_ko");
			
		}else{
			bundle = ResourceBundle.getBundle("EventWindowProp_en");
		}
		
		// window title, includes both the title for the EventCreator and the EventEditor 
		if(bundle.containsKey("WINDOW_TITLE")){
			String windowTitleString = bundle.getString("WINDOW_TITLE");
			windowTitleText = windowTitleString.split(" ");
		}
		// select date button
		if (bundle.containsKey("SELECT_DATE")) {
			selectDateBtnText = bundle.getString("SELECT_DATE");
		}
		// title label
		if(bundle.containsKey("TITLE")){
			titleLblText = bundle.getString("TITLE");
		}
		// start time label
		if (bundle.containsKey("START_TIME")) {
			startTimeLblText = bundle.getString("START_TIME");
		}
		// end time label
		if (bundle.containsKey("END_TIME")) {
			endTimeLblText = bundle.getString("END_TIME");
		}
		// hours label
		if (bundle.containsKey("HOURS_LBL")) {
			hourLblText = bundle.getString("HOURS_LBL");
		}
		// mins label 
		if (bundle.containsKey("MINS_LBL")) {
			minLblText = bundle.getString("MINS_LBL");
		}
		// ampm label
		if (bundle.containsKey("AMPM_LBL")) {
			amPmLblText = bundle.getString("AMPM_LBL");
		}
		// all day label
		if (bundle.containsKey("ALL_DAY")) {
			allDayLblText = bundle.getString("ALL_DAY");
		}
		// description label
		if (bundle.containsKey("DESCRIPTION")) {
			descriptionLblText = bundle.getString("DESCRIPTION");
		}
		// priority label
		if (bundle.containsKey("PRIORITY_LBL")) {
			priorityLblText = bundle.getString("PRIORITY_LBL");
		}
		// color label
		if (bundle.containsKey("COLOR_LBL")) {
			colorLblText = bundle.getString("COLOR_LBL");
		}
		// repeat options label
		if (bundle.containsKey("REPEAT_OPTIONS")) {
			repeatOptionsLblText = bundle.getString("REPEAT_OPTIONS");
		}
		//weekly label
		if (bundle.containsKey("WEEKLY")) {
			weeklyLblText = bundle.getString("WEEKLY");
		}
		// monthly label
		if (bundle.containsKey("MONTHLY")) {
			monthlyLblText = bundle.getString("MONTHLY");
		}
		// yearly label
		if (bundle.containsKey("YEARLY")) {
			yearlyLblText = bundle.getString("YEARLY");
		}
		// save button
		if (bundle.containsKey("SAVE")) {
			saveBtnText = bundle.getString("SAVE");
		}
		// array of priorities 
		if (bundle.containsKey("PRIORITES")){
			String priString = bundle.getString("PRIORITES");
			prioArr = priString.split(" ");
		}
		// array of colors
		if(bundle.containsKey("COLORS")){
			String clrString = bundle.getString("COLORS");
			colorArr = clrString.split(" ");
		}
		// array of colors
		if(bundle.containsKey("HOURS")){
			String hrsString = bundle.getString("HOURS");
			hourArr = hrsString.split(" ");
		}
		// array of mins
		if(bundle.containsKey("MINS")){
			String minString = bundle.getString("MINS");
			minArr = minString.split(" ");
		}
		// array of am/pm
		if(bundle.containsKey("AMPM")){
			String ampmString = bundle.getString("AMPM");
			amPmArr = ampmString.split(" ");
		}
		// location label
		if(bundle.containsKey("LOCATION")){
			locationLblText = bundle.getString("LOCATION");
		}
		// delete button
		if(bundle.containsKey("DELETE")){
			deleteBtnText = bundle.getString("DELETE");
		}
	}
	
	// string arrays 
	protected static String prioArr[];		// holds the priority strings
	protected static String colorArr[];		// holds the color strings
	protected static String hourArr[];		// holds the hour strings
	protected static String minArr[];		// holds the min strings
	protected static String amPmArr[];		// holds the am/pm strings
	
	// text fields and areas
	protected JTextField titleInputField;		// will serve as the input field for title 
	protected JTextField locationInputField;	// will serve as the input field for location 
	protected JTextArea descriptionInputField;	// will serve as the input field for description 
	protected ObservingTextField displayDate;	// will receive and display input from the DatePicker class 
	
	// start time combo boxs
	protected JComboBox<String> fromHour;	// drop down for the start time hour
	protected JComboBox<String> fromMin;	// drop down for the start time min
	protected JComboBox<String> fromAmPm;	// drop down for the start time am/pm
	// end time combo boxs
	protected JComboBox<String> toHour;		// drop down for the end time hour 
	protected JComboBox<String> toMin;		// drop down for the end time min 
	protected JComboBox<String> toAmPm;		// drop down for the end time am/pm 
	
	protected JComboBox<String> colorDropDown;		// drop down for colors
	protected JComboBox<String> priorityDropDown;	// drop down for the priorities

	// check boxs
	protected JCheckBox allDayCheckBox;		// marks an event that is all day
	protected JCheckBox weeklyCheckBox;		// marks an event that repeats every week
	protected JCheckBox monthlyCheckBox;	// marks an event that repeats every month
	protected JCheckBox yearlyCheckBox;		// marks an event that repeats every year
	protected JCheckBox dailyArr[];			// will mark the days an event repeats
	
	// buttons
	protected JButton dateInput;			// will open the DatePicker
	protected JButton btnSave;				// will invoke the abstract method saveEvent()
	protected JButton deleteBtn;			// will invoke the abstract method deleteEvent()
	
	// window labels 
	private JLabel lblTitle;
	private JLabel lblStartTime;
	private JLabel lblHour;
	private JLabel lblMinutes;
	private JLabel lblAmpm;
	private JLabel lblEndtime;
	private JLabel lblLocation;
	private JLabel lblDescription;
	private JLabel lblColor;
	private JLabel lblPriority;
	private JLabel lblRepeatManager;
	
	// will hold the description input text area
	private JScrollPane desScrollPane;

	/**
	 * One parameter constructor for EventWindow class. Takes a string that designates whether
	 * the child class is to be an EventCreator or an EventEditor.
	 * @param name
	 */
	public EventWindow(String name) {
		initFromPropBundle(); 
		
		init();
		// if EventCreator 
		if(name.equals("Creator")){
			setTitle(windowTitleText[0]);
		}
		// if not EventCrator
		else{
			setTitle(windowTitleText[1]);
			// adds a button for deletion if the child class is an EventEditor
			add(deleteBtn);		
		}
	}
	
	private void init(){

		// window uses an abstract layout
		setLayout(null);	
		
		// text fields
		titleInputField = new JTextField();
		locationInputField = new JTextField();
		descriptionInputField = new JTextArea();
		displayDate = new ObservingTextField();
		
		// combo boxs
		fromHour = new JComboBox<String>(hourArr);
		fromMin = new JComboBox<String>(minArr);
		fromAmPm = new JComboBox<String>(amPmArr);
		toHour = new JComboBox<String>(hourArr);
		toMin = new JComboBox<String>(minArr);
		toAmPm = new JComboBox<String>(amPmArr);
		colorDropDown = new JComboBox<String>(colorArr);
		priorityDropDown = new JComboBox<String>(prioArr);
		
		// check boxs
		allDayCheckBox = new JCheckBox(allDayLblText);
		weeklyCheckBox = new JCheckBox(weeklyLblText);
		monthlyCheckBox = new JCheckBox(monthlyLblText);
		yearlyCheckBox = new JCheckBox(yearlyLblText);
		dailyArr = new JCheckBox[7];
		
		// Instantiate daily repeat checkboxs, and set their labels
		for(int i = 0; i < 7; i++){
			dailyArr[i] = new JCheckBox();
			dailyArr[i].setText(DateBook.DAYS_OF_THE_WEEK[i].substring(0, 1));
		}
		
		// buttons
		dateInput = new JButton(selectDateBtnText);
		btnSave = new JButton(saveBtnText);
		deleteBtn = new JButton(deleteBtnText);
		
		// labels
		lblTitle = new JLabel(titleLblText);
		lblStartTime = new JLabel(startTimeLblText);
		lblHour = new JLabel(hourLblText);
		lblMinutes = new JLabel(minLblText);
		lblAmpm = new JLabel(amPmLblText);
		lblEndtime = new JLabel(endTimeLblText);
		lblLocation = new JLabel(locationLblText);
		lblDescription = new JLabel(descriptionLblText);
		lblColor = new JLabel(colorLblText);
		lblPriority = new JLabel(priorityLblText);
		lblRepeatManager = new JLabel(repeatOptionsLblText);

		setBounds(100, 100, 580, 620);
		titleInputField.setBounds(130, 70, 300, 20);
		locationInputField.setBounds(130, 229, 300, 20);
		descriptionInputField.setBounds(130, 260, 380, 92);
		descriptionInputField.setLineWrap(true);
		displayDate.setBounds(300, 20, 100, 20);
		
		fromHour.setBounds(126, 117, 100, 20);
		fromMin.setBounds(270, 117, 100, 20);
		fromAmPm.setBounds(411, 117, 100, 20);
		
		toHour.setBounds(126, 177, 100, 20);
		toMin.setBounds(270, 177, 100, 20);
		toAmPm.setBounds(411, 177, 100, 20);
		colorDropDown.setBounds(320, 400, 100, 20);
		priorityDropDown.setBounds(150, 400, 55, 20);

		allDayCheckBox.setBounds(445, 226, 100, 23);
		weeklyCheckBox.setBounds(150, 470, 105, 23);
		monthlyCheckBox.setBounds(270, 470, 100, 23);
		yearlyCheckBox.setBounds(390, 470, 100, 23);
		for(int i = 0; i < 7; i++){
			dailyArr[i].setBounds(150 + i*40, 500, 37, 23);
			dailyArr[i].addActionListener(this);
		}
		
		dateInput.setBounds(140, 20, 140, 20);
		btnSave.setBounds(230, 540, 100, 20);
		deleteBtn.setBounds(450,20,80,20);
		
		lblTitle.setBounds(10, 71, 46, 14);
		lblStartTime.setBounds(10, 118, 100, 14);
		lblHour.setBounds(160, 101, 55, 14);
		lblMinutes.setBounds(295, 101, 55, 14);
		lblAmpm.setBounds(438, 101, 46, 14);
		lblEndtime.setBounds(10, 178, 120, 14);
		lblLocation.setBounds(10, 232, 100, 14);
		lblDescription.setBounds(10, 297, 100, 18);
		lblColor.setBounds(330, 380, 100, 14);
		lblColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriority.setBounds(130, 380, 100, 14);
		lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepeatManager.setBounds(130, 440, 300, 16);
		lblRepeatManager.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHour.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMinutes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmpm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEndtime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPriority.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRepeatManager.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		displayDate.setColumns(10);
		displayDate.setEnabled(false);
		displayDate.setDisabledTextColor(Color.BLACK);
		deleteBtn.setBackground(Color.RED);
		
		// adds actions listeners to checkBoxes
		allDayCheckBox.addActionListener(this);
		weeklyCheckBox.addActionListener(this);
		monthlyCheckBox.addActionListener(this);
		yearlyCheckBox.addActionListener(this);
		// adds actionListeners to buttons
		dateInput.addActionListener(this);
		btnSave.addActionListener(this);
		deleteBtn.addActionListener(this);
		
		add(dateInput);
		add(btnSave);
		
		add(titleInputField);
		add(locationInputField);
		add(displayDate);
		
		add(fromHour);
		add(fromMin);
		add(fromAmPm);
		add(toHour);
		add(toMin);
		add(toAmPm);
		
		add(colorDropDown);
		add(priorityDropDown);
		
		add(allDayCheckBox);
		add(weeklyCheckBox);
		add(monthlyCheckBox);
		add(yearlyCheckBox);
		for(int i = 0;i < 7;i++){
			add(dailyArr[i]);
		}
		
		add(lblTitle);
		add(lblStartTime);
		add(lblHour);
		add(lblMinutes);
		add(lblAmpm);
		add(lblEndtime);
		add(lblLocation);
		add(lblDescription);
		add(lblColor);
		add(lblPriority);
		add(lblRepeatManager);
		
		desScrollPane = new JScrollPane(descriptionInputField);
		desScrollPane.setBounds(130, 260, 380, 92);
		add(desScrollPane);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();
		
		// if save button
		if(src.equals(btnSave)){
			
			saveEvent();
			EventWriter.writeAll();			// re-writes the newly updated list of event to a text file 
			dispose();						// closes window
		}
		// if DatePicker button
		else if(src.equals(dateInput)){
			
			// create new DatePicker
			final Locale locale = Locale.getDefault();
			DatePicker dp = new DatePicker(displayDate, locale);
			// previously selected date
			Date selectedDate = dp.parseDate(displayDate.getText());
			dp.setSelectedDate(selectedDate);
			dp.start(displayDate);
			
		} 
		// weekly check box
		else if(src.equals(weeklyCheckBox)){
			// toggles the daily check boxes
			for (int i = 0; i < 7; i++) {
				dailyArr[i].setEnabled(!dailyArr[i].isEnabled());
			}

			// toggles monthly and yearly checkBoxes
			monthlyCheckBox.setEnabled(!monthlyCheckBox.isEnabled());
			yearlyCheckBox.setEnabled(!yearlyCheckBox.isEnabled());

		}
		// monthly check box
		else if(src.equals(monthlyCheckBox)){
			// toggles the daily check boxes
			for (int i = 0; i < 7; i++) {
				dailyArr[i].setEnabled(!dailyArr[i].isEnabled());
			}

			// toggles weekly and yearly check boxes
			weeklyCheckBox.setEnabled(!weeklyCheckBox.isEnabled());
			yearlyCheckBox.setEnabled(!yearlyCheckBox.isEnabled());
			
		}
		// yearly check box
		else if(src.equals(yearlyCheckBox)){
			// toggles the daily check boxes
			for (int i = 0; i < 7; i++) {
				dailyArr[i].setEnabled(!dailyArr[i].isEnabled());
			}

			// toggles weekly and monthly check boxes
			weeklyCheckBox.setEnabled(!weeklyCheckBox.isEnabled());
			monthlyCheckBox.setEnabled(!monthlyCheckBox.isEnabled());
			
		}
		// all day check box
		else if(src.equals(allDayCheckBox)){
			
			setTimeDisplay();
			
		}
		// delete button
		else if(src.equals(deleteBtn)){
			deleteEvent();
			EventWriter.writeAll();		// re-writes the newly updated list of event to a text file 
			dispose();					// closes window 
		
		}
		// any of the repeat daily buttons
		else{
			for(int i = 0; i < 7; i++){
				if(src.equals(dailyArr[i])){
					setWMYEnabled();
				}
			}
		}
		
	}
	
	/**
	 * If allDay checkBox is checked, grays out start and end time drop downs, 
	 * else sets start and end time drop downs active.
	 */
	protected void setTimeDisplay() {
		Boolean b = !allDayCheckBox.isSelected();
		
		fromHour.setEnabled(b);
		fromMin.setEnabled(b);
		fromAmPm.setEnabled(b);
		toHour.setEnabled(b);
		toMin.setEnabled(b);
		toAmPm.setEnabled(b);
	}
	
	/**
	 * Looks to see if any daily repeat check boxes are checked, if so grays 
	 * out weekly, monthly and yearly check boxes
	 */
	protected void setWMYEnabled(){
		// ...for each daily repeat check box
		for(int i = 0; i < 7; i++){
			
			//...if is selected
			if(dailyArr[i].isSelected()){
				
				// gray out weekly, monthly and yearly check boxes
				weeklyCheckBox.setEnabled(false);
				monthlyCheckBox.setEnabled(false);
				yearlyCheckBox.setEnabled(false);
				return;
			}
		}
		// if none are selected
		weeklyCheckBox.setEnabled(true);
		monthlyCheckBox.setEnabled(true);
		yearlyCheckBox.setEnabled(true);
	}
	
	/** 
	 *  Will describe what is performed when the saveEvent button is pressed.
	 */
	protected abstract void saveEvent();
	
	/**
	 * Will describe what is performed when the delete event button is pressed.
	 */
	protected abstract void deleteEvent();

}
