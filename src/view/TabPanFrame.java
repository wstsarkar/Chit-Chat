package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.AbstractListModel;

public class TabPanFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public TabPanFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 430);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/open.png")));
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFile);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		mntmProfile.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/profile.png")));
		mntmProfile.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmProfile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/exit.png")));
		mnFile.add(mntmLogout);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/help.png")));
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/about.png")));
		mnHelp.add(mntmAbout);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/help.png")));
		mnHelp.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneFriendList = new JScrollPane();
		scrollPaneFriendList.setBounds(5, 5, 220, 365);
		contentPane.add(scrollPaneFriendList);
		
		JPanel panelFriendList = new JPanel();
		scrollPaneFriendList.setViewportView(panelFriendList);
		panelFriendList.setBorder(new TitledBorder(null, "Friend List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFriendList.setLayout(null);
		
		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(10, 20, 200, 330);
		panelFriendList.add(list);
		
		JPanel panelTab = new JPanel();
		panelTab.setBounds(230, 5, 350, 260);
		contentPane.add(panelTab);
		panelTab.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 350, 260);
		panelTab.add(tabbedPane);
		
		JPanel panelTabNewTab = new JPanel();
		tabbedPane.addTab("New tab", null, panelTabNewTab, null);
		panelTabNewTab.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 345, 232);
		panelTabNewTab.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Input", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(230, 270, 350, 100);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 23, 233, 50);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/send.png")));
		btnSend.setBounds(251, 23, 89, 50);
		panel_2.add(btnSend);
	}
}
