import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import javax.swing.JLabel;

public class VisStats extends JPanel {

	final JSpinner spinner = new JSpinner();
	final JSlider slider = new JSlider();
	final JSpinner peerWidth = new JSpinner();
	final JSpinner peerHeight = new JSpinner();
	
	public VisStats() {
		
		setLayout(null);
		
		spinner.setModel(new SpinnerNumberModel(50, 0, 100, 5));
		spinner.setBounds(12, 35, 60, 59);
		add(spinner);
		
		spinner.setFont(new Font("Dialog", Font.BOLD, 24));
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setToolTipText("Speed of animation; higher value corresponds to slower speeds.");
		slider.setBorder(new LineBorder(Color.GRAY));
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setBounds(72, 35, 495, 59);
		add(slider);		
		
		JLabel lblAnimationSpeed = new JLabel("Animation Speed");
		lblAnimationSpeed.setBounds(270, 12, 135, 16);
		add(lblAnimationSpeed);


		JLabel lblPeerWidth = new JLabel("Peer Width");
		lblPeerWidth.setBounds(143, 114, 81, 14);
		add(lblPeerWidth);
		
		peerWidth.setModel(new SpinnerNumberModel(new Integer(50), null, null, new Integer(1)));
		peerWidth.setBounds(12, 105, 121, 32);
		add(peerWidth);
		
		JLabel lblPeerHeight = new JLabel("Peer Height");
		lblPeerHeight.setBounds(143, 157, 81, 14);
		add(lblPeerHeight);
		
		peerHeight.setModel(new SpinnerNumberModel(new Integer(20), null, null, new Integer(1)));
		peerHeight.setBounds(12, 148, 121, 32);
		add(peerHeight);
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				VisSim.sleepTime = (int) Math.sqrt(slider.getValue());
				spinner.setValue(slider.getValue());
			}
		});
		
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				slider.setValue((int) spinner.getValue());
			}
		});
		
		peerWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				VisSim.peerWidth = (int) peerWidth.getValue();
			}
		});
		
		peerHeight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				VisSim.peerHeight = (int) peerHeight.getValue();
			}
		});
		
	}
}
