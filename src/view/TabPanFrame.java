package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import been.User;
import client.Client;
import controller.UserController;
import utility.Common;

public class TabPanFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUserMsg;

	private JMenuBar menuBar;
	private JMenu mnFile, mnHelp;
	private JMenuItem mntmProfile, mntmSignOut, mntmAbout, mntmHelp;

	private JScrollPane scrollPaneFriendList;

	private JPanel panelFriendList;

	private JPanel panelTabpan;
	private JTabbedPane tabbedPane;

	private JPanel panelInputText;
	private JButton btnSend;

	private JList<String> jFriendList;

	private List<User> onlineUserList;
	private UserController userController;
	private boolean isLogedIn = false;

	private Thread t;
	private User me;
	private Client client;
	private JPanel panel;
	private JScrollPane scrollPane_1;
	private JPanel panel_1;

	/**
	 * Create the frame.
	 */
	public TabPanFrame(User me, Client client) {

		this.isLogedIn = true;
		this.userController = new UserController();
		this.me = me;
		this.client = client;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 600, 430);

		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle(Common.APP_NAME);
		this.setLocationRelativeTo(null);

		try {
			initComponents();
			itemActionListener();
			refteshFriendList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		addTabPan("First");

	}

	private void initComponents() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		mnFile.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/open.png")));
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFile);

		mntmProfile = new JMenuItem("Profile");
		mntmProfile.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/profile.png")));
		mntmProfile.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmProfile);

		mntmSignOut = new JMenuItem("Sign Out");
		mntmSignOut.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/exit.png")));
		mnFile.add(mntmSignOut);

		mnHelp = new JMenu("Help");
		mnHelp.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/help.png")));
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");
		mntmAbout.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/about.png")));
		mnHelp.add(mntmAbout);

		mntmHelp = new JMenuItem("Help");
		mntmHelp.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/help.png")));
		mnHelp.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPaneFriendList = new JScrollPane();
		scrollPaneFriendList.setBounds(5, 5, 220, 365);
		contentPane.add(scrollPaneFriendList);

		panelFriendList = new JPanel();
		scrollPaneFriendList.setViewportView(panelFriendList);
		panelFriendList
				.setBorder(new TitledBorder(null, "Friend List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFriendList.setLayout(null);

		jFriendList = new JList<String>();
		jFriendList.setBounds(10, 20, 200, 330);
		panelFriendList.add(jFriendList);

		panelTabpan = new JPanel();
		panelTabpan.setBounds(230, 5, 350, 260);
		contentPane.add(panelTabpan);
		panelTabpan.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 0, 350, 260);
		panelTabpan.add(tabbedPane);

		panelInputText = new JPanel();
		panelInputText.setBorder(new TitledBorder(null, "Input", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInputText.setBounds(230, 270, 350, 100);
		contentPane.add(panelInputText);
		panelInputText.setLayout(null);

		textFieldUserMsg = new JTextField();
		textFieldUserMsg.setBounds(10, 23, 233, 50);
		panelInputText.add(textFieldUserMsg);
		textFieldUserMsg.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/send.png")));
		btnSend.setBounds(251, 23, 89, 50);
		panelInputText.add(btnSend);

	}

	private void itemActionListener() {
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addTabPan("thahta ioh att");

			}
		});
		
		this.jFriendList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					User otherClient = onlineUserList.get(index);
					addTabPan(otherClient.getName());
				}
			}
		});

		////
		// Sign Out Menu Item action Listener
		////

		mntmSignOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (t != null) {
					isLogedIn = false;
				}
				me.setIs_logged_in(0);
				try {
					client.CloseConnection();
					userController.updateUser(me);
					goTo("Login");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		////
		// Sign Out Menu Item action Listener
		////

		mntmProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				goTo("EditProfile");
			}
		});

		////
		// aboutMenuItem Action listener
		////

		mntmAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(rootPane,
						"Server Client Messenger" + "\n" + "Developer By: " + "Williyam Sarkar" + "\nEmail: "
								+ "williyam.sarkar@gmail.com",
						"About", JOptionPane.INFORMATION_MESSAGE,
						new javax.swing.ImageIcon(getClass().getResource("/resources/about.png")));

			}
		});

		////
		// helpMenuitem Action Listener
		////
		mntmHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(rootPane,
						"AppName = Server Client Messanger\nDevelopedBy = Williyam Sarkar\n"
								+ "						DeveloperEmail = williyam.sarkar@gmail.com \n"
								+ "						AppHelp =\n"
								+ "						- If you are initiating the chat then select Server mode.\n"
								+ "						- If you want to join a Server select Client mode.\n"
								+ "						- In Server mode you have to provide the Port Id.\n"
								+ "						- You can see your IP address from the MyIP menu.\n"
								+ "						- A default Port Id has been already provided.\n"
								+ "						- In Client mode you have to enter the Server Ip and Server Port Id.\n"
								+ "						- Use the input text field to enter your chat message.\n"
								+ "						- Select the white board from the white board menu.\n"
								+ "						- Save your chat using the save chat option from the file menu.\n"
								+ "						- Before exiting the application close the connection.",
						"About", JOptionPane.INFORMATION_MESSAGE,
						new javax.swing.ImageIcon(getClass().getResource("/resources/about.png")));
			}
		});


	}

	public void refteshFriendList() {
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (isLogedIn) {

					try {

						onlineUserList = userController.getAllOnlineUserExcept(me.getUser_name());
						String[] array = new String[onlineUserList.size()];
						for (int i = 0; i < onlineUserList.size(); i++) {
							array[i] = onlineUserList.get(i).getName();
						}
						jFriendList.setListData(array);

						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	private void addTabPan(String tabName) {

		JPanel panelTabNewTab = new JPanel();
		tabbedPane.addTab(tabName, new ImageIcon(TabPanFrame.class.getResource("/resources/chat_16x16.png")),
				panelTabNewTab, null);
		tabbedPane.setEnabledAt(0, true);
		panelTabNewTab.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 345, 232);
		panelTabNewTab.add(scrollPane);

		JPanel panelChat = new JPanel();
		panelChat.setBounds(0, 0, 10, 10);
		scrollPane.setViewportView(panelChat);
		panelChat.setLayout(null);

	}
	
	private void goTo(String whichScreen) {
		if (whichScreen.equals("Login")) {
			LoginFrame loginFrame = new LoginFrame();
			loginFrame.setVisible(true);
			this.dispose();
		}
		else if (whichScreen.equals("EditProfile")) {
//			CreateAccountFrame frame = new CreateAccountFrame(me, null, this);
//			frame.setVisible(true);
		}
	}

	private void goToChatFrame(User otherClient) {
		ChatFrame frame = new ChatFrame(me, otherClient,client);
		frame.setVisible(true);
	}

	public void setMe(User me2) {
		this.me = me2;
		
	}
}
