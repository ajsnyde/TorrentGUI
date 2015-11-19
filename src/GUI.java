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
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {
	
	private JFrame TorrentSimulator;

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
		
		final JSpinner peerWidth = new JSpinner();
		peerWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				VisSim.peerWidth = (int) peerWidth.getValue();
			}
		});
		peerWidth.setModel(new SpinnerNumberModel(new Integer(50), null, null, new Integer(1)));
		peerWidth.setBounds(12, 105, 121, 32);
		panel_4.add(peerWidth);
		
		final JSpinner peerHeight = new JSpinner();
		peerHeight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				VisSim.peerHeight = (int) peerHeight.getValue();
			}
		});
		peerHeight.setModel(new SpinnerNumberModel(new Integer(20), null, null, new Integer(1)));
		peerHeight.setBounds(12, 148, 121, 32);
		panel_4.add(peerHeight);
		
		JLabel lblPeerWidth = new JLabel("Peer Width");
		lblPeerWidth.setBounds(143, 114, 81, 14);
		panel_4.add(lblPeerWidth);
		
		JLabel lblPeerHeight = new JLabel("Peer Height");
		lblPeerHeight.setBounds(143, 157, 81, 14);
		panel_4.add(lblPeerHeight);
		
		final PeerCreator peerCreator = new PeerCreator();
		tabbedPane.addTab("Peer Creator", null, peerCreator, null);
		
		final TorrentCreator torrentCreator = new TorrentCreator();
		tabbedPane.addTab("Torrent Creator", null, torrentCreator, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Events", null, panel_5, null);
		
		JMenuBar menuBar = new JMenuBar();
		TorrentSimulator.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Test");
		menuBar.add(menu);
		
		JMenuItem mntmUpdateConnections = new JMenuItem("Update Connections");
		mntmUpdateConnections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sim.generateConnections(32);
			}
		});
		menu.add(mntmUpdateConnections);
		
		JMenuItem Tick = new JMenuItem("Tick");
		Tick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sim.tick();
			}
		});
		menu.add(Tick);
		
		JCheckBoxMenuItem Run = new JCheckBoxMenuItem("Run");
		Run.setSelected(true);
		Run.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				VisSim.run = !(VisSim.run);
			}
		});
		
		tabbedPane.addChangeListener(new ChangeListener() {	// TODO: Optimize (or remove) this code so that updates only occur on specific tabs being selected
			public void stateChanged(ChangeEvent arg0) {
				peerCreator.updateList();
			}
		});
		
		menu.add(Run);
		
		JCheckBoxMenuItem checkBoxMenuItem_1 = new JCheckBoxMenuItem("New check item");
		menu.add(checkBoxMenuItem_1);
	}
}
