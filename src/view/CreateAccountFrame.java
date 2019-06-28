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

public class CreateAccountFrame extends JFrame {

	private static final long serialVersionUID = 2L;

	private JButton btnSave, btnCancel;
	private JLabel lblHeader, lblUserName, lblPassword,lblConfirmPassword,lblName,lblEmail,lblMobile ;

	private JTextField txtUserName,txtName,txtEmail,txtMobile;
	private JPasswordField txtPassword,txtConfirmPassword;
	
	
	
	public CreateAccountFrame() {
		super(Common.APP_NAME);

		this.setSize(Common.Create_Acc_wnd_X, Common.Create_Acc_wnd_Y);
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
	}

	private void actionListeners() {
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				goTo("Saved");
                
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				goTo("Cancel");
			}
		});

	}
	
	private void goTo(String whichScreen){
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		this.dispose();
	}

}
