package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import been.User;
import client.Client;
import controller.UserController;
import utility.Common;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnSignIn, btnCreateAccount;
	private JLabel lblUserName, lblPassword, lblHeader, lblErrorMessage;

	private JTextField txtUserName;
	private JPasswordField txtPassword;

	private UserController userController;
	private User me;

	public LoginFrame() {
		super(Common.APP_NAME);

		this.setSize(Common.Login_wnd_X, Common.Login_wnd_Y);
		this.setLayout(null);
		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(Common.APP_NAME);
		this.setLocationRelativeTo(null);

		initComponent();
		attachCommoponent();

		actionListeners();
		this.userController = new UserController();
	}

	private void initComponent() {

		lblHeader = new JLabel(Common.APP_NAME);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHeader.setBounds((getWidth() / 2) - 50, 0, 100, 50);

		lblUserName = new JLabel(Common.UserName);
		lblUserName.setBounds(25, 75, Common.TextBox_X_180, Common.TextBox_Y_30);

		lblPassword = new JLabel(Common.Password);
		lblPassword.setBounds(25, 125, Common.TextBox_X_180, Common.TextBox_Y_30);

		lblErrorMessage = new JLabel();
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(25, 235, Common.TextBox_X_250, Common.TextBox_Y_30);

		txtUserName = new JTextField();
		txtUserName.setBounds(150, 75, Common.TextBox_X_180, Common.TextBox_Y_30);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(150, 125, Common.TextBox_X_180, Common.TextBox_Y_30);

		btnSignIn = new JButton(Common.SignIn);
		btnSignIn.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/login.png")));
		btnSignIn.setBounds(230, 185, 100, 30);

		btnCreateAccount = new JButton(Common.Craete_Account);
		btnCreateAccount.setIcon(new ImageIcon(TabPanFrame.class.getResource("/resources/sign-up.png")));
		btnCreateAccount.setBounds(25, 185, 125, 30);

	}

	private void attachCommoponent() {

		getContentPane().add(lblHeader);
		getContentPane().add(lblUserName);
		getContentPane().add(txtUserName);
		getContentPane().add(lblPassword);
		getContentPane().add(txtPassword);
		getContentPane().add(btnCreateAccount);
		getContentPane().add(btnSignIn);

		getContentPane().add(lblErrorMessage);
	}

	private void actionListeners() {

		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userName = txtUserName.getText();
				if (userName.equals("")) {
					lblErrorMessage.setText(Common.UserNameEmpty);
					return;
				}
				String passText = new String(txtPassword.getPassword());
				if (passText.equals("")) {
					lblErrorMessage.setText(Common.PasswordEmpty);
					return;
				}

				me = userController.getUser(userName, passText);
				if (me != null) {
					me.setIs_logged_in(1);
					userController.updateUser(me);
					goTo("Messanger");
				} else {
					lblErrorMessage.setText(Common.LoginFailed);
					return;
				}
			}
		});

		btnCreateAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				goTo("Create");
			}
		});

	}
	
	public void setUserNamepassword(String userName, String pass){
		this.txtUserName.setText(userName);
		this.txtPassword.setText(pass);
	}

	private void goTo(String whichScreen) {
		if (whichScreen.equals("Messanger")) {
			if (me != null) {
				Client client = new Client(me.getUser_id());
				if(client.isConnected()){
					TabPanFrame frame = new TabPanFrame(me,client);
					frame.setVisible(true);
					this.dispose();
				}else{
					me = null;
					JOptionPane.showMessageDialog(rootPane, Common.ServerDown, Common.Error,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (whichScreen.equals("Create")) {

			CreateAccountFrame frame = new CreateAccountFrame(null, this, null);
			frame.setVisible(true);
			this.setVisible(false);

		}
	}

}
