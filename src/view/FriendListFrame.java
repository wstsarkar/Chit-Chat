package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import been.User;
import controller.UserController;
import utility.Common;

/**
 * 
 * @author Williyam
 * 
 */
public class FriendListFrame extends JFrame {

	private static final long serialVersionUID = 3L;
	Thread t;

	private JPanel panel, chatPanel;
	private JScrollPane jScrollPane1;
	private JList<User> friedList;

	private JPanel userInputPanel;
	private JTextField userInputTextField;
	private JButton userInputSendButton;

	private JMenuBar menuBar;

	private JMenu fileMenu;
	private JMenuItem signOutMenuItem;

	private JMenu helpMenu;
	private JMenuItem aboutMenuItem;
	private JMenuItem helpMenuItem;

	private List<User> onlineUserList;
	private UserController userController;
	private boolean isLogedIn = false;
	
	private User me;

	public FriendListFrame() {
		this.setSize(Common.Chat_wnd_X, Common.Chat_wnd_Y);
		this.setLayout(null);
		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle(Common.APP_NAME);
		this.setLocationRelativeTo(null);

		this.isLogedIn = true;
		this.userController = new UserController();
		this.me = new User();
		try {
			initComponents();
			attachCommoponent();
			itemActionListener();
			refteshFriendList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void attachCommoponent() {
		////
		// textArea
		////
		friedList.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		friedList.setBounds(5, 25, getWidth() - 40, getHeight() - 220);

		////
		// ScrollBar add to text area
		////

		jScrollPane1 = new JScrollPane(friedList);
		jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		////
		// Chat Panel
		////

		chatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Friend List"));
		chatPanel.setBounds(0, 0, getWidth() - 8, getHeight() - 50);
		chatPanel.setLayout(new GridLayout(1, 1, 0, 0));
		chatPanel.add(jScrollPane1, BorderLayout.CENTER);
		////
		// userTextField
		///
		userInputTextField.setBounds(5, 25, getWidth() - 20, 35);

		////
		// userInputSendButton
		////

		userInputSendButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/send.png")));
		userInputSendButton.setText("Send");
		userInputSendButton.setBounds(getWidth() - 115, 70, 100, 30);

		////
		// Panel
		////
		panel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));
		panel.setBounds(0, 25, getWidth() - 6, getHeight() - 55);
		panel.setLayout(null);
		panel.add(chatPanel);
		panel.add(userInputPanel);

		////
		// File Menu
		////

		fileMenu.setText("File");

		signOutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/exit.png")));
		signOutMenuItem.setText(Common.SignOut);
		fileMenu.add(signOutMenuItem);
		menuBar.add(fileMenu);

		////
		// Help Menu
		////

		helpMenu.setText("Help");
		aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/about.png")));
		aboutMenuItem.setText("About");
		helpMenu.add(aboutMenuItem);
		helpMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/help.png")));
		helpMenuItem.setText("Help");
		helpMenu.add(helpMenuItem);
		menuBar.add(helpMenu);

		this.add(menuBar);
		this.add(panel);
	}

	private void initComponents() throws Exception {

		chatPanel = new JPanel();
		jScrollPane1 = new JScrollPane(friedList);
		friedList = new JList<User>();
		panel = new JPanel();

		userInputPanel = new JPanel();
		userInputTextField = new JTextField();
		userInputSendButton = new JButton();

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, getWidth() - 6, 25);

		fileMenu = new JMenu();
		signOutMenuItem = new JMenuItem();

		helpMenu = new JMenu();
		aboutMenuItem = new JMenuItem();
		helpMenuItem = new JMenuItem();

	}

	public void refteshFriendList() {
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (isLogedIn) {

					try {
						
						onlineUserList = userController.getAllOnlineUserExcept("");
						User[] array = onlineUserList.toArray(new User[onlineUserList.size()]);
						friedList.setListData(array);
						
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public void itemActionListener() {

		////
		// Sign Out Menu Item action Listener
		////

		signOutMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (t != null) {
					isLogedIn = false;
				}
				System.exit(0);
			}
		});

		////
		// aboutMenuItem Action listener
		////

		aboutMenuItem.addActionListener(new ActionListener() {

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
		helpMenuItem.addActionListener(new ActionListener() {

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

}
