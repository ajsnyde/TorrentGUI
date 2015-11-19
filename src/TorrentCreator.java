import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.BorderLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;

public class TorrentCreator extends JPanel {
	
	final DefaultListModel<Torrent> torrentModel = new DefaultListModel<Torrent>();
	JList<Torrent> torrentList = new JList<Torrent>(torrentModel);
	final JSpinner torrentTotalSizeSpinner = new JSpinner();
	final JSpinner torrentSectionSizeSpinner = new JSpinner();
	private JTextField torrentSections;
	private JTextField torrentID;
	
	public TorrentCreator() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane TorrentCreator = new JSplitPane();
		add(TorrentCreator);
		
		torrentList.setMinimumSize(new Dimension(100, 100));
		TorrentCreator.setLeftComponent(torrentList);
		
		JPanel torrentCreatorPanel = new JPanel();
		torrentCreatorPanel.setLayout(null);
		TorrentCreator.setRightComponent(torrentCreatorPanel);
		
		JLabel label = new JLabel("Name:");
		label.setBounds(150, 7, 36, 16);
		torrentCreatorPanel.add(label);	
		
		final JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(204, 5, 114, 20);
		torrentCreatorPanel.add(textField);
		

		JLabel lblTotalSizekb = new JLabel("Total Size (KB)");
		lblTotalSizekb.setBounds(166, 48, 70, 16);
		torrentCreatorPanel.add(lblTotalSizekb);
		
		torrentTotalSizeSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateSections();
			}
		});
		torrentTotalSizeSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1024)));
		torrentTotalSizeSpinner.setBounds(12, 36, 136, 41);
		torrentCreatorPanel.add(torrentTotalSizeSpinner);
		torrentSectionSizeSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		torrentSectionSizeSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateSections();
			}
		});
		torrentSectionSizeSpinner.setBounds(12, 86, 76, 20);
		torrentCreatorPanel.add(torrentSectionSizeSpinner);
		
		final JButton torrentCreate = new JButton("Create!");	
		torrentCreate.setBounds(138, 270, 98, 26);
		torrentCreatorPanel.add(torrentCreate);
		
		final JButton torrentDelete = new JButton("Delete");
		torrentDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Torrent> hitList = torrentList.getSelectedValuesList();
				for(Torrent kill: hitList)
					Sim.torrents.remove(kill);
				updateList();
			}
		});
		torrentDelete.setBounds(248, 270, 98, 26);
		torrentCreatorPanel.add(torrentDelete);
		
		JLabel lblSectionSize = new JLabel("Section Size (KB)");
		lblSectionSize.setBounds(98, 88, 88, 14);
		torrentCreatorPanel.add(lblSectionSize);
		
		torrentSections = new JTextField();
		torrentSections.setEnabled(false);
		torrentSections.setEditable(false);
		torrentSections.setBounds(12, 117, 76, 20);
		torrentCreatorPanel.add(torrentSections);
		torrentSections.setColumns(10);
		
		JLabel lblSections = new JLabel("# Sections");
		lblSections.setBounds(98, 120, 55, 14);
		torrentCreatorPanel.add(lblSections);
		
		torrentID = new JTextField();
		torrentID.setEnabled(false);
		torrentID.setEditable(false);
		torrentID.setText("ID: null");
		torrentID.setBounds(98, 5, 42, 20);
		torrentCreatorPanel.add(torrentID);
		torrentID.setColumns(10);
		
		final JCheckBox AddToAllPeers = new JCheckBox("Add to all Peers");
		AddToAllPeers.setBounds(12, 141, 108, 23);
		torrentCreatorPanel.add(AddToAllPeers);
		
		torrentCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Torrent in = new Torrent((int)torrentTotalSizeSpinner.getValue(), (int)torrentSectionSizeSpinner.getValue(), textField.getText());
				in.initializeFalse();
				Sim.addTorrent(in);
				if(AddToAllPeers.isSelected())
					for(Peer peer: Sim.peers){
						peer.torrents.add(in);
					}
				updateList();
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					torrentCreate.doClick();
			}
		});
		torrentList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_DELETE)
					torrentDelete.doClick();
			}
		});
		
		torrentList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        @SuppressWarnings("rawtypes")
				JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            Torrent torrent = torrentModel.getElementAt(index);
		            textField.setText(torrent.name);
		            torrentTotalSizeSpinner.setValue(torrent.totalSize);
		            torrentSectionSizeSpinner.setValue(torrent.sectionSize);
		            torrentSections.setText(""+torrent.sections.size());
		            torrentID.setText("ID: " + torrent.ID);
		        }
		    }
		});
		
		
	}
	public void updateList(){
		torrentModel.clear();
		for(Torrent torrent: Sim.torrents)
			torrentModel.addElement(torrent);
	}
	public void updateSections(){
		if((int)torrentSectionSizeSpinner.getValue() != 0)
			torrentSections.setText(""+(((int)torrentTotalSizeSpinner.getValue()/(int)torrentSectionSizeSpinner.getValue())+1));
		else
			torrentSections.setText("ERROR");
	}
}


