

package GroupProject;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EventViewer extends JFrame implements ActionListener {
	// property bundle keys
	private static String windowTitleText;
	private static String todayLblText;
	private static String monthLblText;
	private static String dayLblText;
	private static String yearLblText;
	private static String titleLblText;
	private static String colorLblText;
	private static String[] colorNames;
	private static String allColors;
	private static String searchBtnText;
	private static String editLbl;
	private static String errorMessage;
	private static String eventDate;
	private static String eventTime;
	private static String eventDescript;
	private static String eventLocation;
	private static String eventPriority;
	private static String eventColor;
	private static String eventAllDay;
	private static String eventRepeatWeek;
	private static String eventRepeatMonth;
	private static String eventRepeatYear;

	private JPanel panel = new JPanel();
	private JScrollPane scroll;
	private JTextField monthInput;
	private JTextField dayInput;
	private JTextField yearInput;
	private JLabel monthLabel;
	private JLabel dayLabel;
	private JLabel yearLabel;
	private JLabel colorLabel;
	private JTextField titleSearchField;
	private JLabel titleLabel;
	private JCheckBox chckbxAll;
	private JCheckBox[] colorCheckBoxes; // R,G,B,BL,OR
	private static JButton searchBtn;
	
	private List<Event> selectedEvents = new ArrayList<Event>();
	private List<JScrollPane> scrollList = new ArrayList<JScrollPane>();
	private List<JButton> buttonList = new ArrayList<JButton>();

	static {
		initFromPropBundle();
	}

	/** 
	 * Property files include German, Korean, Spanish, and English translations
	 */
	private static void initFromPropBundle() {

		ResourceBundle bundle;
			// German
		if (Locale.getDefault().equals(new Locale("de_DE"))) {
			bundle = ResourceBundle.getBundle("EventViewerProp_de");
			// Spanish
		} else if (Locale.getDefault().equals(new Locale("es_ES"))) {
			bundle = ResourceBundle.getBundle("EventViewerProp_es");
			// Korean 
		} else if (Locale.getDefault().equals(new Locale("ko_KO"))) {
			bundle = ResourceBundle.getBundle("EventViewerProp_ko");
			// English default
		} else {
			bundle = ResourceBundle.getBundle("EventViewerProp_en");
		}

		if (bundle.containsKey("WINDOW_TITLE")) {
			windowTitleText = bundle.getString("WINDOW_TITLE");
		}
		if (bundle.containsKey("TODAY")) {
			todayLblText = bundle.getString("TODAY");
		}
		if (bundle.containsKey("MONTH")) {
			monthLblText = bundle.getString("MONTH");
		}
		if (bundle.containsKey("DAY")) {
			dayLblText = bundle.getString("DAY");
		}
		if (bundle.containsKey("YEAR")) {
			yearLblText = bundle.getString("YEAR");
		}
		if (bundle.containsKey("TITLE")) {
			titleLblText = bundle.getString("TITLE");
		}
		if (bundle.containsKey("COLOR_LBL")) {
			colorLblText = bundle.getString("COLOR_LBL");
		}
		if (bundle.containsKey("COLORS")) {
			String colorArr = bundle.getString("COLORS");
			colorNames = colorArr.split(" ");
		}
		if (bundle.containsKey("ALL")) {
			allColors = bundle.getString("ALL");
		}
		if (bundle.containsKey("SEARCH")) {
			searchBtnText = bundle.getString("SEARCH");
		}
		if (bundle.containsKey("DATE")) {
			eventDate = bundle.getString("DATE");
		}
		if (bundle.containsKey("TIME")) {
			eventTime = bundle.getString("TIME");
		}
		if (bundle.containsKey("DESCRIPTION")) {
			eventDescript = bundle.getString("DESCRIPTION");
		}
		if (bundle.containsKey("LOCATION")) {
			eventLocation = bundle.getString("LOCATION");
		}
		if (bundle.containsKey("EVENT_PRIORITY")) {
			eventPriority = bundle.getString("EVENT_PRIORITY");
		}
		if (bundle.containsKey("EVENT_COLOR")) {
			eventColor = bundle.getString("EVENT_COLOR");
		}
		if (bundle.containsKey("ALL_DAY")) {
			eventAllDay = bundle.getString("ALL_DAY");
		}
		if (bundle.containsKey("REPEAT_WEEKLY")) {

			eventRepeatWeek = bundle.getString("REPEAT_WEEKLY");
		}
		if (bundle.containsKey("REPEAT_MONTHLY")) {

			eventRepeatMonth = bundle.getString("REPEAT_MONTHLY");
		}
		if (bundle.containsKey("REPEAT_YEARLY")) {

			eventRepeatYear = bundle.getString("REPEAT_YEARLY");
		}
		if (bundle.containsKey("EDIT")) {

			editLbl = bundle.getString("EDIT");
		}
		if (bundle.containsKey("ERROR")) {

			errorMessage = bundle.getString("ERROR");
		}
	}
	
	
	/**
	 * Method to set all labels from property files
	 */
	private void setLabels() {
		setTitle(windowTitleText);

		Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("MM" + "/" + "dd" + "/" + "YYYY");
		JLabel todaysDate = new JLabel(todayLblText + " " + sd.format(d));
		todaysDate.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		todaysDate.setBounds(286, 5, 142, 23);
		panel.add(todaysDate);

		monthLabel = new JLabel(monthLblText);
		monthLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		monthLabel.setBounds(146, 42, 46, 14);
		panel.add(monthLabel);

		dayLabel = new JLabel(dayLblText);
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dayLabel.setBounds(323, 42, 46, 14);
		panel.add(dayLabel);

		yearLabel = new JLabel(yearLblText);
		yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		yearLabel.setBounds(480, 42, 46, 14);
		panel.add(yearLabel);

		titleLabel = new JLabel(titleLblText);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		titleLabel.setBounds(45, 104, 36, 14);
		panel.add(titleLabel);

		colorLabel = new JLabel(colorLblText);
		colorLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		colorLabel.setBounds(20, 154, 50, 14);
		panel.add(colorLabel);

		searchBtn = new JButton(searchBtnText);
		searchBtn.setBounds(300, 193, 80, 23);
		
		// exception handling for searching events
		searchBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					isValidInput();
					search();
					displaySelectedEvents();
				}

				catch (EventViewerException ex) {
				}
			}
		});
		panel.add(searchBtn);

	}
	
	
	public EventViewer() {
		initFromPropBundle();
		
		selectedEvents = DateBook.getEventList(); //all events

		panel.setPreferredSize(new Dimension(3000, 3000));
		panel.setLayout(null);
		setBounds(100, 100, 685, 500);
		setResizable(false);
		add(panel);
		scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);

		monthInput = new JTextField();
		monthInput.setBounds(120, 58, 86, 23);
		panel.add(monthInput);
		monthInput.setColumns(10);

		dayInput = new JTextField();
		dayInput.setBounds(290, 58, 86, 23);
		panel.add(dayInput);
		dayInput.setColumns(10);

		yearInput = new JTextField();
		yearInput.setBounds(450, 58, 86, 23);
		panel.add(yearInput);
		yearInput.setColumns(10);

		titleSearchField = new JTextField();
		titleSearchField.setBounds(100, 101, 490, 23);
		panel.add(titleSearchField);

		colorCheckBoxes = new JCheckBox[5];
		for (int i = 0; i < 5; i++) {
			colorCheckBoxes[i] = new JCheckBox(); 
			colorCheckBoxes[i].setText(colorNames[i]);
		}
		for (int i = 0; i < 5; i++) {
			colorCheckBoxes[i].setBounds(135 + i * 103, 150, 105, 23);
			colorCheckBoxes[i].setFont(new Font("Calibri",Font.PLAIN, 13));
			colorCheckBoxes[i].addActionListener(this);
		}
		for (int i = 0; i < 5; i++) {
			panel.add(colorCheckBoxes[i]);
		}

		chckbxAll = new JCheckBox(allColors);
		chckbxAll.setBounds(60, 150, 75, 23);
		chckbxAll.setFont(new Font("Calibri", Font.PLAIN, 13));
		chckbxAll.addActionListener(this);
		panel.add(chckbxAll);
		
		
		setVisible(true);
		setLabels();
		displaySelectedEvents();

	}
	
	
	/**
	 * Method to remove currently displayed events.
	 * EventViewer initially displays all events;
	 * Events must be removed before displaying the searched events
	 */
	private void removeDisplayedEvents() {
		Iterator<JScrollPane> it = scrollList.iterator();
		Iterator<JButton> it2 = buttonList.iterator();
		while (it.hasNext()) {
			panel.remove(it.next());
		}
		while (it2.hasNext()) {
			panel.remove(it2.next());
		}
	}
	
	
	/** 
	 * Method that displays events.
	 * First, currently displayed events are removed,
	 * then iterates through list and displays events.
	 * Adds buttons for Event Editor next to each event
	 */
	public void displaySelectedEvents() {
		removeDisplayedEvents();

		buttonList = new ArrayList<JButton>();

		Iterator<Event> it = selectedEvents.iterator();
		int count = 0;
		Event ev;
		while (it.hasNext()) {
			JButton editBtn = new JButton(editLbl);
			editBtn.addActionListener(this);
			
			//add scroll pane if text is larger than text area
			JScrollPane scrollPaneText = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			ev = it.next();
			JTextArea eventDisplay = new JTextArea();
			eventDisplay.setEditable(false);
			eventDisplay.setLineWrap(true);
			eventDisplay.setBounds(200, 200 + 210 * count++, 300, 200);
			editBtn.setBounds(515, 25 + 210 * count, 83, 20);
			scrollPaneText.setBounds(200, 25 + 210 * count, 300, 200);
			eventDisplay.append(titleLblText + ": " + ev.getTitle());
			eventDisplay.append("\n" + eventDate + ": " + ev.getMonth() + "/" + ev.getDay() + "/" + ev.getYear());
			eventDisplay.append("\n" + eventTime + ": " + ev.getStartTime() + " - " + ev.getEndTime());
			eventDisplay.append("\n" + eventDescript + ": " + ev.getDescription());
			eventDisplay.append("\n" + eventLocation + ": " + ev.getLocation());
			eventDisplay.append("\n" + eventPriority + ": " + ev.getPriority());
			eventDisplay.append("\n" + eventColor + ": " + ev.getColorText());
			eventDisplay.append("\n" + eventAllDay + ": " + ev.getIsAllDay());
			eventDisplay.append("\n" + eventRepeatWeek + ": " + ev.getRepeatController().getIsRepeatedWeekly());
			eventDisplay.append("\n" + eventRepeatMonth + ": " + ev.getRepeatController().getIsRepeatedMonthly());
			eventDisplay.append("\n" + eventRepeatYear + ": " + ev.getRepeatController().getIsRepeatedYearly());
			scrollPaneText.setBorder(BorderFactory.createLineBorder(ev.getColor(), 2));


			panel.add(eventDisplay);
			scrollPaneText.setViewportView(eventDisplay);
			panel.add(scrollPaneText);
			panel.add(editBtn);
			buttonList.add(editBtn);
			scrollList.add(scrollPaneText);
		}
	}

	/**
	 * Method to search for events
	 */
	public void search() {

		selectedEvents = searchByColor(
				searchByTitle(searchByDay(searchByMonth(searchByYear(DateBook.getEventList())))));
	}
	
	
	/**
	 * Method to search for events by year. 
	 * @param list all events
	 * If year input field is null, method returns full list of events. 
	 * Otherwise, searches through list and returns events with year equal to user input
	 */
	private List<Event> searchByYear(List<Event> list) {
		List<Event> yearList = new ArrayList<Event>();

		if (yearInput.getText().equals("")) {
			return list;
		}

		Iterator<Event> it = list.iterator();

		while (it.hasNext()) {
			Event ev = it.next();

			if (ev.getYear() == Integer.parseInt(yearInput.getText())) {
				yearList.add(ev);
			}
		}

		return yearList;

	}
	
	
	/**
	 * Method to search for events by month. 
	 * @param list all events
	 * If month input field is null, method returns full list of events. 
	 * Otherwise, searches through list and returns events with month equal to user input
	 */
	private List<Event> searchByMonth(List<Event> list) {
		List<Event> monthList = new ArrayList<Event>();

		if (monthInput.getText().equals("")) {
			return list;
		}

		Iterator<Event> it = list.iterator();

		while (it.hasNext()) {
			Event ev = it.next();

			if (ev.getMonth() == Integer.parseInt(monthInput.getText())) {
				monthList.add(ev);
			}
		}

		return monthList;

	}
	

	/**
	 * Method to search for events by day. 
	 * @param list all events
	 * If day input field is null, method returns full list of events. 
	 * Otherwise, searches through list and returns events with day equal to user input
	 */

	private List<Event> searchByDay(List<Event> list) {
		List<Event> dayList = new ArrayList<Event>();

		if (dayInput.getText().equals("")) {
			return list;
		}

		Iterator<Event> it = list.iterator();

		while (it.hasNext()) {
			Event ev = it.next();

			if (ev.getDay() == Integer.parseInt(dayInput.getText())) {
				dayList.add(ev);
			}
		}

		return dayList;

	}


	/**
	 * Method to search for events by title. 
	 * @param list all events
	 * If title input field is null, method returns full list of events. 
	 * Otherwise, searches through list and returns events with title equal to user input
	 */
	
	private List<Event> searchByTitle(List<Event> list) {
		List<Event> titleList = new ArrayList<Event>();

		if (titleSearchField.getText().equals("")) {
			return list;
		}

		Iterator<Event> it = list.iterator();

		while (it.hasNext()) {
			Event ev = it.next();

			if (ev.getTitle().equals(titleSearchField.getText())) {
				titleList.add(ev);
			}
		}

		return titleList;

	}
	
	
	/**
	 * Method to search for events by color. 
	 * @param list all events
	 * If no color is checked, method returns full list of events. 
	 * Otherwise, searches through list and returns events with color equal to user input
	 */
	private List<Event> searchByColor(List<Event> list) {

		List<Event> colorList = new ArrayList<Event>();
		StringBuilder s = new StringBuilder();
		boolean noColor = true;

		for (int i = 0; i < 5; i++) {
			if (colorCheckBoxes[i].isSelected())
				noColor = false;
		}

		if (noColor) {
			return list;
		}

		for (int i = 0; i < 5; i++) {
			if (colorCheckBoxes[i].isSelected()) {
				s.append(Event.colors[i]); // the colors in eng from Event
											// Class
			}
		}

		Iterator<Event> it = list.iterator();

		while (it.hasNext()) {
			Event ev = it.next();

			if (s.toString().contains(ev.getColorText())) {
				colorList.add(ev);
			}
		}
		return colorList;

	}

	// check all colors if chckbxAll is selected
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();
		if (src.equals(chckbxAll)) {
			for (int i = 0; i < 5; i++) {
				colorCheckBoxes[i].setSelected(true);
			}
		} else {
			Iterator<JButton> it = buttonList.iterator();
			int count = 0;

			while (it.hasNext()) {

				if (src.equals(it.next())) {
					new EventEditor(selectedEvents.get(count));
					break;
				}
				count++;
			}
		}
	}
	
	/**
	 * Method to validate user input
	 * @throws EventViewerException if user input contains any non digit characters
	 * Uses Java expressions
	 */

	public void isValidInput() throws EventViewerException {
		String s = monthInput.getText();
		boolean pattern = Pattern.compile((".*\\D.*")).matcher(s).matches();
		if (pattern) {
			throw new EventViewerException(searchBtn, errorMessage);
		}

		s = dayInput.getText();
		pattern = Pattern.compile(".*\\D.*").matcher(s).matches();
		if (pattern) {
			throw new EventViewerException(searchBtn, errorMessage);
		}

		s = yearInput.getText();
		pattern = Pattern.compile(".*\\D.*").matcher(s).matches();
		if (pattern) {
			throw new EventViewerException(searchBtn, errorMessage);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventViewer viewer = new EventViewer();

	}
}
