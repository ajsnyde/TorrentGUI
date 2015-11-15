import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;

public class ConnectionCreator extends JPanel {

	/**
	 * Create the panel.
	 */
	public ConnectionCreator() {
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		add(rdbtnNewRadioButton);

	}

}
