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
import java.awt.event.ActionEvent;

public class GUI {
	
	private JFrame TorrentSimulator;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField;
	private JTextField textField_2;

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
		TorrentSimulator.setSize(600, 400);
		TorrentSimulator.setMinimumSize(new Dimension(600, 400));
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
		
		JSplitPane PeerEditor = new JSplitPane();
		tabbedPane.addTab("Peer Editor", null, PeerEditor, null);
		
		JPanel panel_1 = new JPanel();
		PeerEditor.setRightComponent(panel_1);
		panel_1.setLayout(null);
		
		JCheckBox chckbxEnabled = new JCheckBox("Enabled?");
		chckbxEnabled.setBounds(8, 8, 112, 24);
		panel_1.add(chckbxEnabled);
		
		JLabel lblConnections = new JLabel("Connections");
		lblConnections.setBounds(237, 123, 71, 16);
		panel_1.add(lblConnections);
		
		textField_1 = new JTextField();
		textField_1.setBounds(8, 40, 114, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPeerName = new JLabel("Peer Name");
		lblPeerName.setBounds(128, 42, 85, 16);
		panel_1.add(lblPeerName);
		
		JSpinner spinner_7 = new JSpinner();
		spinner_7.setBounds(8, 72, 50, 20);
		panel_1.add(spinner_7);
		
		JLabel lblPeerSumMax = new JLabel("Peer Sum Max Speed");
		lblPeerSumMax.setBounds(72, 72, 155, 16);
		panel_1.add(lblPeerSumMax);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(8, 147, 442, 149);
		panel_1.add(table);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PeerEditor.setLeftComponent(list);
		
		JSplitPane PeerCreator = new JSplitPane();
		PeerCreator.setContinuousLayout(true);
		tabbedPane.addTab("Peer Creator", null, PeerCreator, null);
		
		JPanel panel = new JPanel();
		PeerCreator.setRightComponent(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setToolTipText("When multiple peers are made, a suffix number will be attached. (i.e. example2)");
		textField.setBounds(213, 5, 114, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(165, 7, 39, 16);
		panel.add(lblName);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBounds(12, 51, 276, 215);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Connect to all peers");
		chckbxNewCheckBox.setBounds(8, 183, 190, 24);
		panel_2.add(chckbxNewCheckBox);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1024)));
		spinner_1.setBounds(8, 12, 92, 24);
		panel_2.add(spinner_1);
		
		JLabel lblOutwardSpeed = new JLabel("Outgoing Speed");
		lblOutwardSpeed.setBounds(107, 16, 91, 16);
		panel_2.add(lblOutwardSpeed);
		
		JLabel lblIncomingSpeed = new JLabel("Incoming Speed");
		lblIncomingSpeed.setBounds(107, 52, 91, 16);
		panel_2.add(lblIncomingSpeed);
		
		JLabel lblCombinedLimit = new JLabel("Combined Limit");
		lblCombinedLimit.setBounds(107, 88, 91, 16);
		panel_2.add(lblCombinedLimit);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1024)));
		spinner_2.setBounds(8, 48, 92, 24);
		panel_2.add(spinner_2);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1024)));
		spinner_3.setBounds(8, 86, 92, 24);
		panel_2.add(spinner_3);
		
		JLabel lblConnections_1 = new JLabel("Connections");
		lblConnections_1.setBounds(105, 35, 77, 16);
		panel.add(lblConnections_1);
		
		JButton btnCreate = new JButton("Create!");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sim.addPeer(new Peer("TEST2", new Color(1,1,1)));
				//list.ad(new Peer("TEST2", new Color(1,1,1)));
			}
		});
		btnCreate.setBounds(199, 278, 98, 26);
		panel.add(btnCreate);
		
		JSlider slider_1 = new JSlider();
		slider_1.setValue(0);
		slider_1.setMaximum(255);
		slider_1.setBounds(404, 20, 98, 16);
		panel.add(slider_1);
		
		JSlider slider_2 = new JSlider();
		slider_2.setValue(0);
		slider_2.setMaximum(255);
		slider_2.setBounds(404, 35, 98, 16);
		panel.add(slider_2);
		
		JSlider slider_3 = new JSlider();
		slider_3.setMajorTickSpacing(255);
		slider_3.setValue(0);
		slider_3.setMaximum(255);
		slider_3.setBounds(404, 51, 98, 16);
		panel.add(slider_3);
		
		JLabel lblR = new JLabel("R");
		lblR.setBounds(388, 20, 16, 16);
		panel.add(lblR);
		
		JLabel lblG = new JLabel("G");
		lblG.setBounds(388, 35, 16, 16);
		panel.add(lblG);
		
		JLabel lblB = new JLabel("B");
		lblB.setBounds(388, 51, 16, 16);
		panel.add(lblB);
		
		JList list_1 = new JList();
		list_1.setModel(new DefaultListModel() {
			//ArrayList<Peer> values = Sim.peers;
			public int getSize() {
				return Sim.peers.size();
			}
			public Object getElementAt(int index) {
				return Sim.peers.get(index);
			}
		    public void update() {
		        this.fireContentsChanged(this, 0, Sim.peers.size() - 1);
		    }
		});
		
		PeerCreator.setLeftComponent(list_1);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Torrent Creator", null, panel_6, null);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_2 = new JSplitPane();
		panel_6.add(splitPane_2, BorderLayout.CENTER);
		
		JList list_2 = new JList();
		list_2.setModel(new AbstractListModel() {
			String[] values = new String[] {"Torrent"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		splitPane_2.setLeftComponent(list_2);
		
		JPanel panel_7 = new JPanel();
		splitPane_2.setRightComponent(panel_7);
		panel_7.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(204, 5, 114, 20);
		panel_7.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setBounds(150, 7, 36, 16);
		panel_7.add(lblName_1);
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1024)));
		spinner_4.setBounds(12, 36, 136, 41);
		panel_7.add(spinner_4);
		
		JLabel lblTotalSize = new JLabel("Total Size");
		lblTotalSize.setBounds(166, 48, 55, 16);
		panel_7.add(lblTotalSize);
		
		JRadioButton rdbtnSections = new JRadioButton("# Sections");
		rdbtnSections.setBounds(22, 85, 121, 24);
		panel_7.add(rdbtnSections);
		
		JRadioButton rdbtnSectionSize = new JRadioButton("Section Size");
		rdbtnSectionSize.setSelected(true);
		rdbtnSectionSize.setBounds(150, 85, 121, 24);
		panel_7.add(rdbtnSectionSize);
		
		JSpinner spinner_5 = new JSpinner();
		spinner_5.setEnabled(false);
		spinner_5.setBounds(32, 117, 76, 20);
		panel_7.add(spinner_5);
		
		JSpinner spinner_6 = new JSpinner();
		spinner_6.setBounds(160, 117, 76, 20);
		panel_7.add(spinner_6);
		
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
		
		menu.add(Run);
		
		JCheckBoxMenuItem checkBoxMenuItem_1 = new JCheckBoxMenuItem("New check item");
		menu.add(checkBoxMenuItem_1);
	}
}
