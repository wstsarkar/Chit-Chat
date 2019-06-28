package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import been.User;
import controller.UserController;
import utility.Common;

public class CreateAccountFrame extends JFrame {

	private static final long serialVersionUID = 2L;

	private JButton btnSave, btnCancel;
	private JLabel lblHeader, lblUserName, lblPassword,lblConfirmPassword,lblName,lblEmail,lblMobile;
	private JLabel lblErrorMessage;

	private JTextField txtUserName,txtName,txtEmail,txtMobile;
	private JPasswordField txtPassword,txtConfirmPassword;
	

	private UserController userController;
	User me;
	
	public CreateAccountFrame(User me) {
		super(Common.APP_NAME);
		this.me = me;

		this.setSize(Common.Create_Acc_wnd_X, Common.Create_Acc_wnd_Y);
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

		lblName = new JLabel(Common.Name);
		lblName.setBounds(25, 75, Common.TextBox_X_180, Common.TextBox_Y_30);
		
		lblEmail = new JLabel(Common.Email);
		lblEmail.setBounds(25, 125, Common.TextBox_X_180, Common.TextBox_Y_30);
		
		lblMobile = new JLabel(Common.Mobile);
		lblMobile.setBounds(25, 175, Common.TextBox_X_180, Common.TextBox_Y_30);

		lblUserName = new JLabel(Common.UserName);
		lblUserName.setBounds(25, 225, Common.TextBox_X_180, Common.TextBox_Y_30);

		lblPassword = new JLabel(Common.Password);
		lblPassword.setBounds(25, 275, Common.TextBox_X_180, Common.TextBox_Y_30);

		lblConfirmPassword = new JLabel(Common.Confirm_Password);
		lblConfirmPassword.setBounds(25, 325, Common.TextBox_X_180, Common.TextBox_Y_30);
		

		lblErrorMessage = new JLabel();
		lblErrorMessage.setForeground(Color.RED);		
		lblErrorMessage.setBounds(25, 425, Common.TextBox_X_180, Common.TextBox_Y_30);

		txtName = new JTextField();
		txtName.setBounds(150, 75, Common.TextBox_X_180, Common.TextBox_Y_30);

		txtEmail = new JTextField();
		txtEmail.setBounds(150, 125, Common.TextBox_X_180, Common.TextBox_Y_30);
		
		txtMobile = new JTextField();
		txtMobile.setBounds(150, 175, Common.TextBox_X_180, Common.TextBox_Y_30);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(150, 225, Common.TextBox_X_180, Common.TextBox_Y_30);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(150, 275, Common.TextBox_X_180, Common.TextBox_Y_30);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(150, 325, Common.TextBox_X_180, Common.TextBox_Y_30);

		btnSave = new JButton(Common.Save);
		btnSave.setBounds(230, 385, 100, 30);

		btnCancel = new JButton(Common.Cancel);
		btnCancel.setBounds(25, 385, 125, 30);

	}

	private void attachCommoponent() {

		getContentPane().add(lblHeader);
		getContentPane().add(lblName);
		getContentPane().add(lblEmail);
		getContentPane().add(lblMobile);		
		getContentPane().add(lblUserName);
		getContentPane().add(lblPassword);
		getContentPane().add(lblConfirmPassword);

		getContentPane().add(txtName);
		getContentPane().add(txtEmail);
		getContentPane().add(txtMobile);
		getContentPane().add(txtUserName);
		getContentPane().add(txtPassword);
		getContentPane().add(txtConfirmPassword);
		
		getContentPane().add(btnCancel);
		getContentPane().add(btnSave);
		getContentPane().add(lblErrorMessage);
	}

	private void actionListeners() {
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				String name = txtName.getText();
				if(name.equals("")){
					lblErrorMessage.setText(Common.NameEmpty);
					return;
				}
				
				String userName = txtUserName.getText();
				if(userName.equals("")){
					lblErrorMessage.setText(Common.UserNameEmpty);
					return;
				}
				String passText = new String(txtPassword.getPassword());
				if(passText.equals("")){
					lblErrorMessage.setText(Common.PasswordEmpty);
					return;
				}
				String confirmPassText = new String(txtConfirmPassword.getPassword());
				if(confirmPassText.equals("")){
					lblErrorMessage.setText(Common.ConfirmPasswordEmpty);
					return;
				}
				if(!confirmPassText.equals(passText)){
					lblErrorMessage.setText(Common.PasswordNotMatch);
					return;
				}
				

				String email = txtEmail.getText();
				String mobile_no = txtMobile.getText();
				
				User user = userController.getUser(userName);
				if(user != null){
					lblErrorMessage.setText(Common.DuplicateUserName);
					return;					
				}
				
				user = new User(0, userName, passText, name, email, mobile_no, 0);
				
				int id = userController.createUser(user);
				if(id > 0){ 
					JOptionPane.showMessageDialog(rootPane, "Account created", "Success", JOptionPane.INFORMATION_MESSAGE);
                
					goTo("Login");
				}else{
					lblErrorMessage.setText(Common.CreatUserFailed);
					return;
				}

				
                
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				goTo("Login");
			}
		});

	}
	
	private void goTo(String whichScreen){
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		this.dispose();
	}

}
