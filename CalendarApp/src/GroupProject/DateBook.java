package GroupProject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;

@SuppressWarnings("serial")
public class DateBook extends JFrame implements ActionListener {
	
	// holds all events 
	private static List<Event> eventList = new ArrayList<Event>(10);
	
	// text keys
	protected static String[] DAYS_OF_THE_WEEK;
	protected static String[] MONTHS_OF_THE_YEAR;
	private static String windowTitleText;
	protected static String todayLblText;
	private static String newEventBtnText;
	private static String eventViewerBtnText;
	private static String optionsMenuText;
	private static String langMenuText;
	private static String[] langNames;		// get from prop file

	static {
		initFromPropBundle(); 
		
		// object used to read in events from text file 
		EventReader eventReader = new EventReader();
		eventList.addAll(eventReader.readAll());	// add all saved events to the list of events
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
			bundle = ResourceBundle.getBundle("DateBookProp_de");
			
		}else if(Locale.getDefault().equals(new Locale("es_ES"))){
			bundle = ResourceBundle.getBundle("DateBookProp_es");
			
		}else if(Locale.getDefault().equals(new Locale("ko_KO"))){
			bundle = ResourceBundle.getBundle("DateBookProp_ko");
			
		}else{
			// use English by default
			bundle = ResourceBundle.getBundle("DateBookProp_en");
		}
		
		// window title
		if(bundle.containsKey("TITLE")){
			windowTitleText = bundle.getString("TITLE");
		}
		
		// months of the year 
		if ( bundle.containsKey("MONTHS") ) {
			
			String monthString = bundle.getString("MONTHS");
			MONTHS_OF_THE_YEAR = monthString.split(" ");
		}
		
		// days of the week
		if(bundle.containsKey("WEEK_DAYS")){
			String dayString = bundle.getString("WEEK_DAYS");
			DAYS_OF_THE_WEEK = dayString.split(" ");
		}
		
		// 'today is' label
		if(bundle.containsKey("TODAY_LABEL")){
			todayLblText = bundle.getString("TODAY_LABEL");
		}
		
		// new event button
		if(bundle.containsKey("NEW_EVENT")){
			newEventBtnText = bundle.getString("NEW_EVENT");
		}
		
		// event viewer button
		if(bundle.containsKey("EVENT_VIEWER")){
			eventViewerBtnText = bundle.getString("EVENT_VIEWER");
		}
		
		// options menu
		if (bundle.containsKey("OPTIONS")) {

			optionsMenuText =  bundle.getString("OPTIONS");
		}
		
		// Languages sub menu
		if (bundle.containsKey("LANGUAGES")) {

			langMenuText =  bundle.getString("LANGUAGES");
		}
		
		// languages names
		if (bundle.containsKey("LANG_NAMES")) {

			String langNamesString = bundle.getString("LANG_NAMES");
			langNames = langNamesString.split(" ");
		}
	}
	
	// menu components 
	private JMenuBar mBar;	
	private JMenu optsMenu;	
	private JMenu langMenu;
	private JMenuItem[] langs;	// holds the language menu items 
	
	// panels
	private JPanel panel;		// main panel, will hold all other panels
	private Month monthPanel;	// the object that presents and holds the days of the month
	private JPanel up;			// will hold all other 'up'-panels
	private JPanel down;		// will hold the new event button, event viewer button and todayLbl
	private JPanel upButtons;   // will hold month buttons
	private JPanel upYearButtons;	// will hold year inc/dec buttons and yearMonthLbl
	private JPanel upLabels;		// will hold weekday labels
	
	// labels
	private JLabel todayLabel;		// will display todays date 
	private JLabel yearMonthLbl;	// displays the month and year being displayed
	private static JLabel weekDayLabels[] = new JLabel[7];  // will hold the names of the days of the week

	// buttons
	private JButton yearInc;	// button that inc selectedYear
	private JButton yearDec;	// button that dec selectedYear
	private JButton viewerButton;	// button that opens the event viewer
	private JButton monthButtons[] = new JButton[12];	// array of month buttons
	private JButton newEventButton;	// button that opens the new event window
	
	// ints
	private int selectedMonth;
	private int selectedYear;
	protected final int todaysDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);	// get todays date
	protected final int todaysMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
	protected final int todaysYear = Calendar.getInstance().get(Calendar.YEAR);
	
	/**
	 * Default constructor for DateBook class. Invokes init().
	 */
	public DateBook(){
		
		super(windowTitleText);
		setSize(1300,700);
		setResizable(false);
		
		// terminate program upon closing the window 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		init();
		
		// open the calendar to todays date
		setDate(todaysMonth,todaysYear);
		panel.setVisible(true);
		add(panel);
		setVisible(true);
		
		// menu components 
		optsMenu.add(langMenu);
		mBar.add(optsMenu);
		setJMenuBar(mBar);
	}
	
	public void init(){
		
		selectedMonth = todaysMonth;
		selectedYear = todaysYear;
		
		mBar = new JMenuBar();
		optsMenu = new JMenu(optionsMenuText);
		langMenu = new JMenu(langMenuText);
		langs = new JMenuItem[4];	// 0 = English, 1 = German, 2 = Korean, 3 = Spanish
		
		// create language menu items 
		for(int i = 0; i < 4; i++){
			langs[i] = new JMenuItem(langNames[i]);
			langs[i].addActionListener(this);
			langMenu.add(langs[i]);
		}
		
		panel = new JPanel();  
		up = new JPanel();
		down = new JPanel();
		upButtons = new JPanel();
		upYearButtons = new JPanel();
		upLabels = new JPanel();
		monthPanel = new Month(selectedMonth,selectedYear);
		
		yearMonthLbl = new JLabel();
		todayLabel = new JLabel(todayLblText + " " + MONTHS_OF_THE_YEAR[todaysMonth - 1] 
				+ " " + todaysDay +  ", " + todaysYear);
		
		yearInc = new JButton("" + (todaysYear + 1));
		yearDec = new JButton("" + (todaysYear - 1));
		viewerButton = new JButton(eventViewerBtnText);
		newEventButton = new JButton(newEventBtnText);

		upButtons.setLayout(new GridLayout(1,12,5,0));
		upLabels.setLayout(new GridLayout(1,7,5,0));
		monthPanel.setLayout(new GridLayout(6,7,5,6));
		up.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		
		// add month buttons to upButtons panel
		for(int i = 0; i < 12; i++){
			monthButtons[i] = new JButton(MONTHS_OF_THE_YEAR[i]);
			monthButtons[i].addActionListener(this);	// add action listener
			upButtons.add(monthButtons[i]);
		}
		monthButtons[todaysMonth - 1].setForeground(Color.BLUE);	// highlight current month button
		
		// add the days of the week to upLabels
		for(int i = 0; i < 7; i++){
			weekDayLabels[i] = new JLabel(DAYS_OF_THE_WEEK[i]);
			upLabels.add(weekDayLabels[i]);
		}
		
		// add actions listeners to buttons
		yearInc.addActionListener(this);
		yearDec.addActionListener(this);
		viewerButton.addActionListener(this);
		newEventButton.addActionListener(this);
		
		// add mouseListener to todayLbl
		todayLabel.addMouseListener(new MouseListener(){
			// on mouse click...
			public void mouseClicked(MouseEvent arg0) {
				//...set display to show todays date
				selectedYear = todaysYear;
				selectedMonth = todaysMonth;
				setDate(todaysMonth,todaysYear);
			}
			public void mouseEntered(MouseEvent arg0) {
				todayLabel.setForeground(Color.BLUE);
			}
			public void mouseExited(MouseEvent arg0) {
				todayLabel.setForeground(Color.BLACK);
				
			}
			public void mousePressed(MouseEvent arg0) {
				todayLabel.setForeground(new Color(100, 200, 200));
				
			}
			public void mouseReleased(MouseEvent arg0) {
				todayLabel.setForeground(Color.BLUE);				
			}
		});
		
		upYearButtons.add(yearDec);
		upYearButtons.add(yearMonthLbl);
		upYearButtons.add(yearInc);
		
		up.add(upButtons, BorderLayout.CENTER);
		up.add(upYearButtons, BorderLayout.NORTH);
		up.add(upLabels, BorderLayout.SOUTH);
		
		down.add(newEventButton);
		down.add(todayLabel);
		down.add(viewerButton);
		
		panel.add(monthPanel,BorderLayout.CENTER);
		panel.add(up, BorderLayout.NORTH);
		panel.add(down,BorderLayout.SOUTH);
	}
	
	/**
	 * Returns the 1 - 12 index of the current selected month.
	 * @return The current selected month.
	 */
	public int getMonth(){
		return selectedMonth;
	}
	
	/**
	 * Returns the year of the current selected year.
	 * @return The current selected year.
	 */
	public int getYear(){
		return selectedYear;
	}
	
	/**
	 * Takes a month index and a year, and changes the current display to show the that date.
	 * 
	 * Invokes highlightCurrentDay(), displayAllEvents() and highlightCurrentMonth().
	 * Note that this method does not assess the arguments' validity.
	 * @param month = 1,2,3,...,12 where 1 = January, 2 = February, etc.
	 * @param year = a year in standard form (e.g. 1992).
	 */
	public void setDate(int month, int year){
		selectedMonth = month;
		selectedYear = year;
	
		// change yearMonthLbl to show newly selected date
		yearMonthLbl.setText(MONTHS_OF_THE_YEAR[selectedMonth - 1] + ", " + selectedYear);
		
		// remove the Month object, and create and add a new one that corresponds to the new selected date
		panel.remove(monthPanel);
		monthPanel = new Month(selectedMonth,selectedYear);
		panel.add(monthPanel,BorderLayout.CENTER);
		validate();
		
		// update the year inc/dec buttons
		yearInc.setText("" + (selectedYear + 1));
		yearDec.setText("" + (selectedYear - 1));
		
		highlightCurrentDay();	// adds a blue border to todays date
		displayAllEvents();		
		highlightCurrentMonth();	
	}
	
	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();
		
		// new event button
		if(src.equals(newEventButton)){
			new EventCreator();
		}
		// viewer button
		else if(src.equals(viewerButton)){
			new EventViewer();
			//EventViewerTest.getInstance();		// if singleton class
		}
		// January button
		else if(src.equals(monthButtons[0])){
			setDate(1,selectedYear);
		}
		// February button
		else if(src.equals(monthButtons[1])){
			setDate(2,selectedYear);
		}
		// March button
		else if(src.equals(monthButtons[2])){
			setDate(3,selectedYear);
		}
		// April button
		else if(src.equals(monthButtons[3])){
			setDate(4,selectedYear);
		}
		// May button
		else if(src.equals(monthButtons[4])){
			setDate(5,selectedYear);
		}
		// June button
		else if(src.equals(monthButtons[5])){
			setDate(6,selectedYear);
		}
		// July button
		else if(src.equals(monthButtons[6])){
			setDate(7,selectedYear);
		}
		// August button
		else if(src.equals(monthButtons[7])){
			setDate(8,selectedYear);
		}
		// September button
		else if(src.equals(monthButtons[8])){
			setDate(9,selectedYear);
		}
		// October button
		else if(src.equals(monthButtons[9])){
			setDate(10,selectedYear);
		}
		// November button
		else if(src.equals(monthButtons[10])){
			setDate(11,selectedYear);
		}
		// December button
		else if(src.equals(monthButtons[11])){
			setDate(12,selectedYear);
		} 
		// inc yea button
		else if(src.equals(yearInc)){
			selectedYear++;
			setDate(selectedMonth,selectedYear);
		}
		// dec year button
		else if(src.equals(yearDec)){
			selectedYear--;
			setDate(selectedMonth,selectedYear);
		}
		// change language to English
		else if(src.equals(langs[0])){	
			Locale.setDefault(new Locale("us_EN"));
			DateBook.initFromPropBundle();
			setLabels();
		} 
		// change language to German
		else if(src.equals(langs[1])){	
			Locale.setDefault(new Locale("de_DE"));
			DateBook.initFromPropBundle();
			setLabels();
		} 
		// change language to Korean
		else if(src.equals(langs[2])){	
			Locale.setDefault(new Locale("ko_KO"));
			DateBook.initFromPropBundle();
			setLabels();
		}
		// change language to Spanish
		else if(src.equals(langs[3])){	
			Locale.setDefault(new Locale("es_ES"));
			DateBook.initFromPropBundle();
			setLabels();
		}
	}
	
	/**
	 * Resets the values of the displayed labels and text to the values most recently
	 * retrieved from one of the property files.
	 */
	private void setLabels(){
		setTitle(windowTitleText);
		
		todayLabel.setText(todayLblText + " " + MONTHS_OF_THE_YEAR[todaysMonth - 1] + " " + todaysDay +  ", " + todaysYear);
		yearMonthLbl.setText(MONTHS_OF_THE_YEAR[selectedMonth - 1] + ", " + selectedYear);
		
		newEventButton.setText(newEventBtnText);
		viewerButton.setText(eventViewerBtnText);
		optsMenu.setText(optionsMenuText);
		langMenu.setText(langMenuText);
		
		optsMenu.setText(optionsMenuText);
		
		// set text for language menu items
		for(int i = 0; i < 4; i++){
			langs[i].setText(langNames[i]);
		}
		
		// set text for month buttons
		for(int i = 0; i < 12; i++){
			monthButtons[i].setText(MONTHS_OF_THE_YEAR[i]);
		}
		
		// set text for weekDay labels
		for(int i = 0; i < 7; i++){
			weekDayLabels[i].setText(DAYS_OF_THE_WEEK[i]);
		}
		
	}
	
	/**
	 * Sets the font color of the month button currently selected to blue, 
	 * and all unselected month buttons to black.
	 */
	private void highlightCurrentMonth(){
		
		for(int i = 0; i < 12; i++){
			monthButtons[i].setForeground(Color.BLACK);
		}
		monthButtons[selectedMonth - 1].setForeground(Color.BLUE);
	}
	
	/**
	 * Cycles through all Day objects associated with the currently selected Month, 
	 * finds todays day and changes the color and size of its text area.
	 */
	private void highlightCurrentDay(){
		for (int i = 0; i < 42; i++) {
			if (isToday(monthPanel.getMonth(), monthPanel.getDays()[i].getDayOfTheMonth(), monthPanel.getYear())){
				monthPanel.getDays()[i].highlight();
			}
		}
	}

	/**
	 * Returns true if the day, month and year passed in correspond to todays date.
	 * Note this method does not assess the validity of the parameters.
	 * @param month
	 * @param day
	 * @param year
	 * @return true - if the date is today,
	 *		   false - if the date is not today
	 */
	private boolean isToday(int month, int day, int year){
		
		if(month == todaysMonth && day == todaysDay && year == todaysYear){
				return true;
		}
		return false;
	}
	
	/** Displays all the events that correspond to the active days.
	 * Invokes displayYearly(Event ev), displayMonthly(Event ev), DisplayOnDay(Event ev).
	 * 
	 * Note that this method does not display events that are repeated daily or weekly.
	 */
	private void displayAllEvents(){ // Doesn't show repeated weekly and daily
		Iterator<Event> it = eventList.iterator();
		
		// for each event... 
		while(it.hasNext()){
			Event ev = it.next();

			// ...if the event is repeated yearly
			if (ev.getRepeatController().getIsRepeatedYearly()) {
				displayYearly(ev);
				
			// ...if the event repeats monthly
			} else if (ev.getRepeatController().getIsRepeatedMonthly()) {
				displayMonthly(ev);
				
			// ...if the event is not repeated
			} else if (ev.getYear() == selectedYear && ev.getMonth() == selectedMonth){
				monthPanel.displayOnDay(ev);
				
			}
		}
	}
	
	/**
	 * Displays the event to it's respective day if the event's year is less than or equal-to 
	 * the selected year AND if the events month is equal to the selected month. Invokes displayOnDay(Event ev).
	 * @param ev
	 */
	private void displayYearly(Event ev){
		if(ev.getYear() <= selectedYear && ev.getMonth() == selectedMonth){
			monthPanel.displayOnDay(ev);
		}
	}
	
	/**
	 * Displays the event to it's respective day if the event's year is 
	 * less than or equal-to the selected year. Invokes displayOnDay(Event ev).
	 * @param ev
	 */
	private void displayMonthly(Event ev){
		if(ev.getYear() <= selectedYear){
			monthPanel.displayOnDay(ev);
		}
	}
	
	@Override
	public String toString(){
		return "selectedMonth=" + selectedMonth + ", selectedYear" + selectedYear 
				+ ", todaysMonth=" + todaysMonth + ", todaysDay=" + todaysDay 
				+ ", todaysYear="+ todaysYear + ", EventList=" + eventList;
	}
	
	/**
	 * Prints all events to the console.
	 */
	@SuppressWarnings("unused")
	private static void displayEvents(){
		Iterator<Event> it = eventList.iterator();
		
		System.out.println("DateBook has " + eventList.size() + " events.");
		
		while(it.hasNext()){
			System.out.println(it.next());
			System.out.println("\n");
		}
	}
	
	/**
	 * Returns the list of all events.
	 * @return the list of events
	 */
	public static List<Event> getEventList(){
		return eventList;
	}
	
	public static void main(String[] args){
		
		new DateBook();

	}

}
