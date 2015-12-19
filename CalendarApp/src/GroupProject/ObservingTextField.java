/**
 * Third party file - The HowTo Tutorial
 */

package GroupProject;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class ObservingTextField extends JTextField implements Observer {
 
	private static final long serialVersionUID = 1L;

	public void update(Observable o, Object arg) {
        Calendar calendar = (Calendar) arg;
        DatePicker dp = (DatePicker) o;
        setText(dp.formatDate(calendar));
    }
}
