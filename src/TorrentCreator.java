import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.BorderLayout;

public class TorrentCreator extends JPanel {
	
	final DefaultListModel<Torrent> torrentModel = new DefaultListModel<Torrent>();
	JList<Torrent> torrentList = new JList<Torrent>(torrentModel);
	
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
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(204, 5, 114, 20);
		torrentCreatorPanel.add(textField);
		

		JLabel label_1 = new JLabel("Total Size");
		label_1.setBounds(166, 48, 55, 16);
		torrentCreatorPanel.add(label_1);
		
		final JSpinner torrentTotalSizeSpinner = new JSpinner();
		torrentTotalSizeSpinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1024)));
		torrentTotalSizeSpinner.setBounds(12, 36, 136, 41);
		torrentCreatorPanel.add(torrentTotalSizeSpinner);
		
		final JRadioButton torrentSectionSizeButton = new JRadioButton("Section Size");
		torrentSectionSizeButton.setSelected(true);
		torrentSectionSizeButton.setBounds(150, 85, 121, 24);
		torrentCreatorPanel.add(torrentSectionSizeButton);		
		
		final JSpinner torrentSectionSizeSpinner = new JSpinner();
		torrentSectionSizeSpinner.setBounds(160, 117, 76, 20);
		torrentCreatorPanel.add(torrentSectionSizeSpinner);		
		
		final JRadioButton torrentSectionsButton = new JRadioButton("# Sections");
		torrentSectionsButton.setBounds(22, 85, 121, 24);
		torrentCreatorPanel.add(torrentSectionsButton);
		
		final JSpinner torrentSectionsSpinner = new JSpinner();
		torrentSectionsSpinner.setEnabled(false);
		torrentSectionsSpinner.setBounds(32, 117, 76, 20);
		torrentCreatorPanel.add(torrentSectionsSpinner);
		
		JButton torrentCreate = new JButton("Create!");	
		torrentCreate.setBounds(138, 270, 98, 26);
		torrentCreatorPanel.add(torrentCreate);
		
		JButton torrentDelete = new JButton("Delete");
		torrentDelete.setBounds(248, 270, 98, 26);
		torrentCreatorPanel.add(torrentDelete);

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
		
		torrentCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Torrent in = new Torrent();
				Sim.addTorrent(in);
			}
		});
		
	}
	public void updateList(){
		torrentModel.clear();
		for(Torrent torrent: Sim.torrents)
			torrentModel.addElement(torrent);
	}
}


