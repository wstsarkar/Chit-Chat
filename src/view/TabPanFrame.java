package view;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import been.MSG;
import been.User;
import client.Client;
import controller.MsgController;
import controller.UserController;
import renderer.FriendListRenderer;
import renderer.MsgListRenderer;
import utility.Common;

/**
 * 
 * @author Williyam
 * 
 */
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

	private JList<User> jFriendList;
	private DefaultListModel<User> modelFriendList;

	private List<User> onlineUserList;
	private UserController userController;
	private MsgController msgController;
	private boolean isLogedIn = false;

	private Thread t;
	private User me;
	private Client client;

	private List<User> chatingList;
	private List<DefaultListModel<MSG>> chatingMSGList;

	/**
	 * Create the frame.
	 */
	public TabPanFrame(User me, Client client) {

		this.isLogedIn = true;
		this.userController = new UserController();
		this.msgController = new MsgController();
		this.me = me;
		this.client = client;
		this.chatingList = new ArrayList<User>();
		this.chatingMSGList = new ArrayList<DefaultListModel<MSG>>();
		this.modelFriendList = new DefaultListModel<User>();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 600, 430);

		this.setResizable(false);

		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setTitle(Common.APP_NAME);
		this.setLocationRelativeTo(null);

		try {
			initComponents();
			itemActionListener();
			refteshFriendList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		GetMessegeThread();

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

		jFriendList = new JList<User>(modelFriendList);
		jFriendList.setBounds(10, 20, 200, 330);
		jFriendList.setCellRenderer(new FriendListRenderer());

		panelFriendList.add(jFriendList);

		panelTabpan = new JPanel();
		panelTabpan.setBounds(230, 5, 350, 260);
		contentPane.add(panelTabpan);
		panelTabpan.setLayout(null);

		tabbedPane = new JTabbedPane(SwingConstants.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);
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
		
		textFieldUserMsg.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	            	int selectedIndex = tabbedPane.getSelectedIndex();
					if (selectedIndex > -1) {
						MSG msg = getMsg();
						send(msg);
					} else {
						textFieldUserMsg.setText("");
						JOptionPane.showMessageDialog(rootPane, "No one selected for chat!", Common.Error,
								JOptionPane.ERROR_MESSAGE);
					}
	            }
	        }

	    });

		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedIndex = tabbedPane.getSelectedIndex();
				if (selectedIndex > -1) {
					MSG msg = getMsg();
					send(msg);
				} else {
					textFieldUserMsg.setText("");
					JOptionPane.showMessageDialog(rootPane, "No one selected for chat!", Common.Error,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.jFriendList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					User otherClient = onlineUserList.get(index);

					boolean flag = true;
					for (int i = 0; i < chatingList.size(); i++) {
						if (chatingList.get(i).getUser_id() == otherClient.getUser_id()) {
							flag = false;
							break;
						}
					}

					if (flag) {
						chatingList.add(otherClient);
						addTabPan(otherClient);
					}
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

	protected void send(MSG msg) {
		try {

			client.writeMessage(msg);

			AddMsgToView(msg);
			this.textFieldUserMsg.setText("");

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "Could not Send Message!\nNo Connection!", Common.Error,
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "Could not Send Message!\nUnknown Exception:\n" + ex, Common.Error,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void refteshFriendList() {
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (isLogedIn) {

					try {

						onlineUserList = userController.getAllUserExcept(me.getUser_name());
						for (int i = 0; i < onlineUserList.size(); i++) {
							User user = onlineUserList.get(i);
							if (modelFriendList.getSize() <= i
									|| modelFriendList.get(i).getUser_id() != user.getUser_id()) {
								modelFriendList.addElement(user);
								i -= 1;
							} else if (modelFriendList.get(i).getUser_id() == user.getUser_id()
									&& modelFriendList.get(i).getIs_logged_in() != user.getIs_logged_in()) {
								modelFriendList.remove(i);
								modelFriendList.add(i,user);
								i -= 1;
							}

						}

						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	private void addTabPan(User chatingUser) {
		/*
		 * JPanel panelTabNewTab = new JPanel(); tabbedPane.addTab(tabName, new
		 * ImageIcon(TabPanFrame.class.getResource("/resources/chat_16x16.png"))
		 * ,panelTabNewTab, null); tabbedPane.setEnabledAt(0, true);
		 * panelTabNewTab.setLayout(null);
		 */
		int tabCount = tabbedPane.getTabCount();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 345, 232);
		tabbedPane.add(scrollPane);

		tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(scrollPane),
				getTitlePanel(tabbedPane, scrollPane, chatingUser.getName()));

		// JPanel panelChat = new JPanel();
		// panelChat.setBounds(0, 0, 0, 0);
		// panelChat.setLayout(null);

		DefaultListModel<MSG> msgList = new DefaultListModel<MSG>();
		chatingMSGList.add(msgList);
		JList<MSG> list = new JList<MSG>(msgList);
		list.setVisibleRowCount(10);
		list.setCellRenderer(new MsgListRenderer(me));
		list.setBounds(5, 5, 315, 220);
		list.setAutoscrolls(true);
		// panelChat.add(list);

		scrollPane.setViewportView(list);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		tabbedPane.setSelectedIndex(tabCount);
		GetOldMessege(chatingUser);
	}

	private JPanel getTitlePanel(final JTabbedPane tabbedPane, final JScrollPane panel, String title) {
		JPanel titlePanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT, 0, 0));
		titlePanel.setOpaque(false);
		JLabel titleLbl = new JLabel(title);
		titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		titlePanel.add(titleLbl);
		JButton closeButton = new JButton();
		closeButton.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/Close.png")));

		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selectedIndex = tabbedPane.getSelectedIndex();
				if (chatingList.size() > selectedIndex) {
					chatingList.remove(selectedIndex);
					chatingMSGList.remove(selectedIndex);
					tabbedPane.remove(selectedIndex);
				}

			}
		});
		titlePanel.add(closeButton);

		return titlePanel;
	}

	public void GetMessegeThread() {
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (client.isConnected()) {
						MSG msg = (MSG) client.getObjInputStream().readObject();
						boolean isChatting = false;
						for (int i = 0; i < chatingList.size(); i++) {
							User user = chatingList.get(i);
							if (user.getUser_id() == msg.getSender_user_id()) {

								int selectedIndex = tabbedPane.getSelectedIndex();
								if (i != selectedIndex) {
									tabbedPane.setSelectedIndex(i);
								}

								AddMsgToView(msg);
								isChatting = true;
								break;
							}
						}
						if (!isChatting) {
							for (int i = 0; i < onlineUserList.size(); i++) {
								User user = onlineUserList.get(i);
								if (user.getUser_id() == msg.getSender_user_id()) {
									chatingList.add(user);
									addTabPan(user);
									break;
								}
							}
						}

					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	public void GetOldMessege(User chatingUser) {
		List<MSG> msgs = msgController.getAllMyMsg(me.getUser_id(), chatingUser.getUser_id());
		for (int i = 0; i < msgs.size(); i++) {
			MSG msg = msgs.get(i);

			AddMsgToView(msg);
		}
	}

	private void AddMsgToView(MSG msg) {
		int selectedIndex = tabbedPane.getSelectedIndex();
		chatingMSGList.get(selectedIndex).addElement(msg);

	}

	private MSG getMsg() {
		String chat = textFieldUserMsg.getText();

		int selectedIndex = tabbedPane.getSelectedIndex();
		if (chatingList.size() <= selectedIndex) {
			throw new RuntimeException("Some thing went wrong");
		}

		MSG msg = new MSG();

		msg.setMessage_id(0);
		msg.setMessage_type("String");
		msg.setMessage(chat);
		msg.setReceiver_user_id(chatingList.get(selectedIndex).getUser_id());
		msg.setReceiving_date(null);
		msg.setIs_received(0);
		msg.setSender_user_id(me.getUser_id());
		msg.setSending_date(Calendar.getInstance().getTime());
		msg.setIs_send(0);
		msg.setIs_seen(0);
		return msg;
	}

	private void goTo(String whichScreen) {
		if (whichScreen.equals("Login")) {
			LoginFrame loginFrame = new LoginFrame();
			loginFrame.setVisible(true);
			this.dispose();
		} else if (whichScreen.equals("EditProfile")) {
			CreateAccountFrame frame = new CreateAccountFrame(me, null, this);
			frame.setVisible(true);
		}
	}

	public void setMe(User me2) {
		this.me = me2;

	}
}
