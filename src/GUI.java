import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {
	
	private JFrame TorrentSimulator;
	private JTextField peerNameField;
	private JTextField textField;

	public static void main(String[] args) {
		 try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.TorrentSimulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		
		TorrentSimulator = new JFrame();
		TorrentSimulator.setTitle("Torrent Simulator");
		TorrentSimulator.setSize(625, 400);
		TorrentSimulator.setMinimumSize(new Dimension(625, 400));
		TorrentSimulator.setBackground(Color.LIGHT_GRAY);
		TorrentSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TorrentSimulator.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		TorrentSimulator.getContentPane().add(tabbedPane);
		
		JPanel Simulator = new JPanel();
		tabbedPane.addTab("Visual Simulator", null, Simulator, null);
		Simulator.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		Simulator.add(splitPane, BorderLayout.CENTER);
		VisSim connect = new VisSim();
		
		JPanel visualSimulator = VisSim.displayPanel;
		FlowLayout fl_visualSimulator = (FlowLayout) visualSimulator.getLayout();
		fl_visualSimulator.setVgap(100);
		fl_visualSimulator.setHgap(50);
		splitPane.setLeftComponent(visualSimulator);
		
		Canvas canvas = new Canvas();
		visualSimulator.add(canvas);
		
		JPanel panel_3 = new JPanel();
		splitPane.setRightComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		panel_3.add(textArea, BorderLayout.CENTER);
		
		JPanel Stats = new JPanel();
		tabbedPane.addTab("Stats", null, Stats, null);
		Stats.setLayout(new BorderLayout(0, 0));
		
		Panel panel_4 = new Panel();
		Stats.add(panel_4, BorderLayout.CENTER);
	
		final JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Dialog", Font.BOLD, 24));
		final JSlider slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setToolTipText("Speed of animation; higher value corresponds to slower speeds.");
		slider.setBorder(new LineBorder(Color.GRAY));
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setBounds(72, 35, 495, 59);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				VisSim.sleepTime = (int) Math.sqrt(slider.getValue());
				spinner.setValue(slider.getValue());
			}
		});
		panel_4.setLayout(null);
		panel_4.add(slider);	
		

		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				slider.setValue((int) spinner.getValue());
			}
		});
		spinner.setModel(new SpinnerNumberModel(50, 0, 100, 5));
		spinner.setBounds(12, 35, 60, 59);
		panel_4.add(spinner);
		
		JLabel lblAnimationSpeed = new JLabel("Animation Speed");
		lblAnimationSpeed.setBounds(270, 12, 135, 16);
		panel_4.add(lblAnimationSpeed);
		
		
		final DefaultListModel<Peer> peerModel = new DefaultListModel<Peer>();
		peerModel.addElement(new Peer("Test/Spacer", new Color(1,1,1)));
		for(Peer peer: Sim.peers)
			peerModel.addElement(peer);
		
		JSplitPane PeerCreator = new JSplitPane();	
		PeerCreator.setContinuousLayout(true);
		tabbedPane.addTab("Peer Creator", null, PeerCreator, null);

		final JList<Peer> peerList = new JList<Peer>(peerModel);
		Dimension minimumSize = new Dimension(50, 100);
		peerList.setMinimumSize(minimumSize);
		PeerCreator.setLeftComponent(peerList);

		
		JPanel peerCreatorPanel = new JPanel();
		PeerCreator.setRightComponent(peerCreatorPanel);
		peerCreatorPanel.setLayout(null);
		
		peerNameField = new JTextField();
		peerNameField.setToolTipText("When multiple peers are made, a suffix number will be attached. (i.e. example2)");
		peerNameField.setBounds(213, 5, 114, 20);
		peerCreatorPanel.add(peerNameField);
		peerNameField.setColumns(10);
		
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
		
		
		// LABELS FOR PEER CREATOR
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(165, 7, 39, 16);
		peerCreatorPanel.add(lblName);
		
		JLabel lblOutwardSpeed = new JLabel("Outgoing Speed");
		lblOutwardSpeed.setBounds(107, 16, 91, 16);
		connectionsPanel.add(lblOutwardSpeed);
		
		JLabel lblIncomingSpeed = new JLabel("Incoming Speed");
		lblIncomingSpeed.setBounds(107, 52, 91, 16);
		connectionsPanel.add(lblIncomingSpeed);
		
		JLabel lblCombinedLimit = new JLabel("Combined Limit");
		lblCombinedLimit.setBounds(107, 88, 91, 16);
		connectionsPanel.add(lblCombinedLimit);
		
		JLabel lblConnections_1 = new JLabel("Connections");
		lblConnections_1.setBounds(105, 35, 77, 16);
		peerCreatorPanel.add(lblConnections_1);
		
		JLabel lblR = new JLabel("R");
		lblR.setBounds(388, 20, 16, 16);
		peerCreatorPanel.add(lblR);
		
		JLabel lblG = new JLabel("G");
		lblG.setBounds(388, 35, 16, 16);
		peerCreatorPanel.add(lblG);
		
		JLabel lblB = new JLabel("B");
		lblB.setBounds(388, 51, 16, 16);
		peerCreatorPanel.add(lblB);
		
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
		
		final JButton peerCreate = new JButton("Create!");
		peerCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Peer in = new Peer(peerNameField.getText(), new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));
				Sim.peers.add(in);
				peerModel.clear();
				for(Peer peer: Sim.peers)
					peerModel.addElement(peer);
			}
		});		
		
		final JButton peerDelete = new JButton("Delete");
		peerDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Peer> hitList = peerList.getSelectedValuesList();
				for(Peer kill: hitList)
					Sim.peers.remove(kill);
				peerModel.clear();
				for(Peer peer: Sim.peers)
					peerModel.addElement(peer);
			}
		});
		
		peerCreate.setBounds(165, 278, 98, 26);
		peerCreatorPanel.add(peerCreate);
		peerDelete.setBounds(275, 278, 98, 26);
		peerCreatorPanel.add(peerDelete);
		
		//Torrent List + Model
		
		final DefaultListModel<Torrent> torrentModel = new DefaultListModel<Torrent>();
		
		JSplitPane TorrentCreator = new JSplitPane();
		tabbedPane.addTab("Torrent Creator", null, TorrentCreator, null);
		
		
		JList<Torrent> torrentList = new JList<Torrent>(torrentModel);
		for(Torrent torrent: Sim.torrents)
			torrentModel.addElement(torrent);
		torrentList.setMinimumSize(new Dimension(50, 100));
		TorrentCreator.setLeftComponent(torrentList);
		
		JPanel torrentCreatorPanel = new JPanel();
		torrentCreatorPanel.setLayout(null);
		TorrentCreator.setRightComponent(torrentCreatorPanel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(204, 5, 114, 20);
		torrentCreatorPanel.add(textField);
		
		JLabel label = new JLabel("Name:");
		label.setBounds(150, 7, 36, 16);
		torrentCreatorPanel.add(label);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(12, 36, 136, 41);
		torrentCreatorPanel.add(spinner_1);
		
		JLabel label_1 = new JLabel("Total Size");
		label_1.setBounds(166, 48, 55, 16);
		torrentCreatorPanel.add(label_1);
		
		JRadioButton radioButton = new JRadioButton("# Sections");
		radioButton.setBounds(22, 85, 121, 24);
		torrentCreatorPanel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Section Size");
		radioButton_1.setSelected(true);
		radioButton_1.setBounds(150, 85, 121, 24);
		torrentCreatorPanel.add(radioButton_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setEnabled(false);
		spinner_2.setBounds(32, 117, 76, 20);
		torrentCreatorPanel.add(spinner_2);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(160, 117, 76, 20);
		torrentCreatorPanel.add(spinner_3);
		
		JButton torrentCreate = new JButton("Create!");
		torrentCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Torrent in = new Torrent();
				Sim.addTorrent(in);
				
			}
		});
		torrentCreate.setBounds(138, 270, 98, 26);
		torrentCreatorPanel.add(torrentCreate);
		
		JButton torrentDelete = new JButton("Delete");
		torrentDelete.setBounds(248, 270, 98, 26);
		torrentCreatorPanel.add(torrentDelete);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Events", null, panel_5, null);
		
		JMenuBar menuBar = new JMenuBar();
		TorrentSimulator.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Test");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("New menu item");
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("New menu item");
		menu.add(menuItem_1);
		
		JCheckBoxMenuItem Run = new JCheckBoxMenuItem("Run");
		Run.setSelected(true);
		Run.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				VisSim.run = !(VisSim.run);
			}
		});
		
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
		
		menu.add(Run);
		
		JCheckBoxMenuItem checkBoxMenuItem_1 = new JCheckBoxMenuItem("New check item");
		menu.add(checkBoxMenuItem_1);
	}
}
