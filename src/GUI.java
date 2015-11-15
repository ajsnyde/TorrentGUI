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
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GUI {
	
	private JFrame TorrentSimulator;
	private JTextField textField;

	public static void main(String[] args) {
		 try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
	public GUI() {
		initialize();
	}
	private void initialize() {
		
		TorrentSimulator = new JFrame();
		TorrentSimulator.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/Pictures/p2p.png")));
		TorrentSimulator.setTitle("Torrent Simulator");
		TorrentSimulator.setSize(625, 400);
		TorrentSimulator.setMinimumSize(new Dimension(625, 400));
		TorrentSimulator.setBackground(Color.LIGHT_GRAY);
		TorrentSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TorrentSimulator.getContentPane().setLayout(new BorderLayout(0, 0));
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

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
		
		final PeerCreator in_1 = new PeerCreator();
		tabbedPane.addTab("Peer Creator", null, in_1, null);

		Dimension minimumSize = new Dimension(50, 100);
		
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
		
		final JSpinner torrentTotalSizeSpinner = new JSpinner();
		torrentTotalSizeSpinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1024)));
		torrentTotalSizeSpinner.setBounds(12, 36, 136, 41);
		torrentCreatorPanel.add(torrentTotalSizeSpinner);
		
		JLabel label_1 = new JLabel("Total Size");
		label_1.setBounds(166, 48, 55, 16);
		torrentCreatorPanel.add(label_1);
		final JRadioButton torrentSectionSizeButton = new JRadioButton("Section Size");
		final JRadioButton torrentSectionsButton = new JRadioButton("# Sections");
		torrentSectionSizeButton.setSelected(true);
		torrentSectionSizeButton.setBounds(150, 85, 121, 24);
		torrentCreatorPanel.add(torrentSectionSizeButton);
		torrentSectionsButton.setBounds(22, 85, 121, 24);
		torrentCreatorPanel.add(torrentSectionsButton);
		final JSpinner torrentSectionsSpinner = new JSpinner();
		
		torrentSectionsSpinner.setEnabled(false);
		torrentSectionsSpinner.setBounds(32, 117, 76, 20);
		torrentCreatorPanel.add(torrentSectionsSpinner);
		
		final JSpinner torrentSectionSizeSpinner = new JSpinner();
		torrentSectionSizeSpinner.setBounds(160, 117, 76, 20);
		torrentCreatorPanel.add(torrentSectionSizeSpinner);
		
		
		
		torrentSectionSizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				torrentSectionsButton.setSelected(false);
				torrentSectionsSpinner.setEnabled(false);
				torrentSectionSizeSpinner.setEnabled(true);
				if((int)torrentSectionSizeSpinner.getValue()!=0)
				torrentSectionsSpinner.setValue((int)torrentTotalSizeSpinner.getValue()/(int)torrentSectionSizeSpinner.getValue());
			}
		});
		torrentSectionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				torrentSectionSizeButton.setSelected(false);
				torrentSectionSizeSpinner.setEnabled(false);
				torrentSectionsSpinner.setEnabled(true);
				if((int)torrentSectionsSpinner.getValue()!=0)
					torrentSectionSizeSpinner.setValue((int)torrentTotalSizeSpinner.getValue()/(int)torrentSectionsSpinner.getValue());
			}
		});
		
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
		
		tabbedPane.addChangeListener(new ChangeListener() {	// TODO: Optimize this code so that updates only occur on specific tabs being selected
			public void stateChanged(ChangeEvent arg0) {
				in_1.updateList();
			}
		});
		
		menu.add(Run);
		
		JCheckBoxMenuItem checkBoxMenuItem_1 = new JCheckBoxMenuItem("New check item");
		menu.add(checkBoxMenuItem_1);
	}
}
