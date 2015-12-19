
package GroupProject;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Exception handling for user input fields
 * If input is invalid, message dialogue displays error message 
 */
@SuppressWarnings("serial")
public class EventViewerException extends Exception {

	public EventViewerException(JButton searchButton, String error) {
		super();
		JOptionPane.showMessageDialog(searchButton, error);
	}
}
