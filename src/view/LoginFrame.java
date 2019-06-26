package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utility.Common;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnSignIn, btnCreateAccount;
	private JLabel lblUserName, lblPassword, lblHeader;

	private JTextField txtUserName;
	private JPasswordField txtPassword;
	
	
	
	public LoginFrame() {
		super(Common.APP_NAME);

		this.setSize(Common.Login_window_X, Common.Login_window_Y);
		this.setLayout(null);
		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(Common.APP_NAME);
		this.setLocationRelativeTo(null);

		initComponent();
		attachCommoponent();

		actionListeners();
	}

	private void initComponent() {

		lblHeader = new JLabel(Common.APP_NAME);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHeader.setBounds((getWidth() / 2) - 50, 0, 100, 50);

		lblUserName = new JLabel(Common.UserName);
		lblUserName.setBounds(25, 75, 120, 30);

		lblPassword = new JLabel(Common.Password);
		lblPassword.setBounds(25, 125, 120, 30);

		txtUserName = new JTextField();
		txtUserName.setBounds(120, 75, 150, 30);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(120, 125, 150, 30);

		btnSignIn = new JButton(Common.SignIn);
		btnSignIn.setBounds(200, 185, 70, 30);

		btnCreateAccount = new JButton(Common.Craete_Account);
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
	}

	private void actionListeners() {
		
		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				goTo("Messanger");
                
			}
		});
		
		btnCreateAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				goTo("Create");
			}
		});

	}
	
	private void goTo(String whichScreen){
		if(whichScreen.equals("Messanger")){

			ClientMessengerFrame serverClientMessanger = new ClientMessengerFrame();
			serverClientMessanger.setVisible(true);
			this.dispose();
		}else if(whichScreen.equals("Create")){
			
		}
	}

}
