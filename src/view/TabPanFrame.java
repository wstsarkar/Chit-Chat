package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class TabPanFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TabPanFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFile);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		mntmProfile.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmProfile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mnFile.add(mntmLogout);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnHelp.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneFriendList = new JScrollPane();
		scrollPaneFriendList.setBounds(5, 5, 220, 335);
		contentPane.add(scrollPaneFriendList);
		
		JPanel panelFriendList = new JPanel();
		scrollPaneFriendList.setViewportView(panelFriendList);
		panelFriendList.setBorder(new TitledBorder(null, "Friend List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFriendList.setLayout(null);
		
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
		panel_2.setBounds(230, 270, 350, 70);
		contentPane.add(panel_2);
	}
}
