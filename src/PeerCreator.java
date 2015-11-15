import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PeerCreator extends JPanel {
	
		final DefaultListModel<Peer> peerModel = new DefaultListModel<Peer>();
		final JList<Peer> peerList = new JList<Peer>(peerModel);	
		
	public PeerCreator() {
		
		//Overall attributes
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane Split = new JSplitPane();
		Split.setContinuousLayout(true);
		add(Split);
		//List on the left	

		peerModel.addElement(new Peer("Test/Spacer", new Color(1,1,1)));
		for(Peer peer: Sim.peers)
			peerModel.addElement(peer);		


		Dimension minimumSize = new Dimension(50, 100);
		peerList.setMinimumSize(minimumSize);
		Split.setLeftComponent(peerList);
		final JTextField peerID = new JTextField();
		
		//Panel on the right
		JPanel peerCreatorPanel = new JPanel();
		Split.setRightComponent(peerCreatorPanel);
		peerCreatorPanel.setBackground(new Color(240, 240, 240));
		peerCreatorPanel.setLayout(null);	//Absolute
		
		peerID.setText("ID: null");
		peerID.setEditable(false);
		peerID.setEnabled(false);
		peerID.setBounds(105, 5, 48, 20);
		peerCreatorPanel.add(peerID);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(165, 7, 39, 16);
		peerCreatorPanel.add(lblName);		
		
		final JTextField peerNameField = new JTextField();
		peerNameField.setToolTipText("When multiple peers are made, a suffix number will be attached. (i.e. example2)");
		peerNameField.setBounds(213, 5, 114, 20);
		peerCreatorPanel.add(peerNameField);
		
		JLabel lblR = new JLabel("R");
		lblR.setBounds(388, 20, 16, 16);
		peerCreatorPanel.add(lblR);
		
		JLabel lblG = new JLabel("G");
		lblG.setBounds(388, 35, 16, 16);
		peerCreatorPanel.add(lblG);
		
		JLabel lblB = new JLabel("B");
		lblB.setBounds(388, 51, 16, 16);
		peerCreatorPanel.add(lblB);
		
		final JSlider redSlider = new JSlider();
		redSlider.setValue(0);
		redSlider.setMaximum(255);
		redSlider.setBounds(404, 20, 98, 16);
		peerCreatorPanel.add(redSlider);
		
		final JSlider greenSlider = new JSlider();
		greenSlider.setValue(0);
		greenSlider.setMaximum(255);
		greenSlider.setBounds(404, 35, 98, 16);
		peerCreatorPanel.add(greenSlider);
		
		final JSlider blueSlider = new JSlider();
		blueSlider.setMajorTickSpacing(255);
		blueSlider.setValue(0);
		blueSlider.setMaximum(255);
		blueSlider.setBounds(404, 51, 98, 16);
		peerCreatorPanel.add(blueSlider);
		
		JLabel lblConnections_1 = new JLabel("Connections");
		lblConnections_1.setBounds(105, 35, 77, 16);
		peerCreatorPanel.add(lblConnections_1);
		
		JPanel connectionsPanel = new JPanel();
		connectionsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		connectionsPanel.setBackground(Color.WHITE);
		connectionsPanel.setForeground(Color.BLACK);
		connectionsPanel.setBounds(12, 51, 276, 215);
		peerCreatorPanel.add(connectionsPanel);
		connectionsPanel.setLayout(null);
		
		JCheckBox connectAllBox = new JCheckBox("Connect to all peers");
		connectAllBox.setBounds(8, 183, 190, 24);
		connectionsPanel.add(connectAllBox);
		
		JLabel lblOutwardSpeed = new JLabel("Outgoing Speed");
		lblOutwardSpeed.setBounds(107, 16, 91, 16);
		connectionsPanel.add(lblOutwardSpeed);
		
		JLabel lblIncomingSpeed = new JLabel("Incoming Speed");
		lblIncomingSpeed.setBounds(107, 52, 91, 16);
		connectionsPanel.add(lblIncomingSpeed);
		
		JLabel lblCombinedLimit = new JLabel("Combined Limit");
		lblCombinedLimit.setBounds(107, 88, 91, 16);
		connectionsPanel.add(lblCombinedLimit);
		
		JSpinner outgoingSpinner = new JSpinner();
		outgoingSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1024)));
		outgoingSpinner.setBounds(8, 12, 92, 24);
		connectionsPanel.add(outgoingSpinner);
		
		JSpinner incomingSpinner = new JSpinner();
		incomingSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1024)));
		incomingSpinner.setBounds(8, 48, 92, 24);
		connectionsPanel.add(incomingSpinner);
		
		JSpinner combinedSpinner = new JSpinner();
		combinedSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1024)));
		combinedSpinner.setBounds(8, 86, 92, 24);
		connectionsPanel.add(combinedSpinner);
		
		final JButton peerCreate = new JButton("Create!");
		peerCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Peer in = new Peer(peerNameField.getText(), new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));
				Sim.peers.add(in);
				updateList();
			}
		});		
		peerCreate.setBounds(165, 278, 98, 26);
		peerCreatorPanel.add(peerCreate);
		
		final JButton peerDelete = new JButton("Delete");
		peerDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Peer> hitList = peerList.getSelectedValuesList();
				for(Peer kill: hitList)
					Sim.peers.remove(kill);
				updateList();
			}
		});
		peerDelete.setBounds(275, 278, 98, 26);
		peerCreatorPanel.add(peerDelete);
		
		JButton peerChange = new JButton("Apply Changes");
		peerChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				peerList.getSelectedValue().name = peerNameField.getText();
				peerList.getSelectedValue().color = new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue());
			}
		});
		peerChange.setBounds(48, 278, 105, 26);
		peerCreatorPanel.add(peerChange);
		
		peerNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					peerCreate.doClick();
			}
		});
		peerList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_DELETE)
					peerDelete.doClick();
			}
		});
		
		peerList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        @SuppressWarnings("rawtypes")
				JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            Peer peer = peerModel.getElementAt(index);
		            peerNameField.setText(peer.name);
		            peerID.setText("ID: " + peer.ID);
		            redSlider.setValue(peer.color.getRed());
		            blueSlider.setValue(peer.color.getBlue());
		            greenSlider.setValue(peer.color.getGreen());
		        }
		    }
		});
	}
	
	public void updateList(){
		peerModel.clear();
		for(Peer peer: Sim.peers)
			peerModel.addElement(peer);
	}
}
