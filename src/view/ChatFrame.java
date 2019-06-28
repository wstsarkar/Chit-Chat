package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import been.MSG;
import been.User;
import utility.Common;

/**
 * 
 * @author Williyam
 * 
 */
public class ChatFrame extends JFrame {

	private static final long serialVersionUID = 3L;
	Thread t;
	private User me, otherClient;
	private MSG msg;

	public ChatFrame(User me, User otherClient) {
		this.setSize(Common.Chat_wnd_X, Common.Chat_wnd_Y);
		this.setLayout(null);
		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle(Common.APP_NAME);
		this.setLocationRelativeTo(null);
		this.me = me;
		this.otherClient = otherClient;
		try {
			initComponents();
			itemActionListener();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initComponents() throws Exception {

		chatPanel = new JPanel();
		jScrollPane1 = new JScrollPane(chatTextArea);
		chatTextArea = new JTextArea();
		panel = new JPanel();

		userInputPanel = new JPanel();
		userInputTextField = new JTextField();
		userInputSendButton = new JButton();

		////
		// textArea
		////
		chatTextArea.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		chatTextArea.setBounds(5, 25, getWidth() - 40, getHeight() - 220);
		chatTextArea.setEditable(false);
		////
		// Auto scrol to show last Line
		////
		DefaultCaret caret = (DefaultCaret) chatTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		////
		// ScrollBar add to text area
		////

		jScrollPane1 = new JScrollPane(chatTextArea);
		jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		////
		// Chat Panel
		////

		chatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(otherClient.getName()));
		chatPanel.setBounds(0, 0, getWidth() - 8, getHeight() - 180);
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
		// User Input panel
		////

		userInputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Input"));
		userInputPanel.setBounds(0, 205, getWidth() - 8, 120);
		userInputPanel.setLayout(null);

		userInputPanel.add(userInputTextField);
		userInputPanel.add(userInputSendButton);

		////
		// Panel
		////
		panel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));
		panel.setBounds(0, 25, getWidth() - 6, getHeight() - 55);
		panel.setLayout(null);
		panel.add(chatPanel);
		panel.add(userInputPanel);

		this.add(panel);
	}

	public void itemActionListener() {
		////////
		/// Save Button Action Listener not done
		////////

		userInputSendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (isCilent) {

						msg = getMsg();

						objOutputStreamClient.writeObject(msg);
						objOutputStreamClient.flush();
						if (t.isInterrupted()) {
							t.start();
						}
					}

				} catch (NullPointerException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Could not Send Message!\nNo Connection!", "Send Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Could not Send Message!\nUnknown Exception:\n" + ex,
							"Send Error", JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
				} finally {
					userInputTextField.setText("");
				}

			}

		});

	}
	private MSG getMsg(){
		chat = userInputTextField.getText();
		
		MSG msg = new MSG();
		
		msg.setMessage_id(0);
		msg.setMessage_type("String");
		msg.setMessage(chat);
		msg.setReceiver_user_id(otherClient.getUser_id());
		msg.setReceiving_date(null);
		msg.setIs_received(0);
		msg.setSender_user_id(me.getUser_id());
		msg.setSending_date(Calendar.getInstance().getTime());
		msg.setIs_send(0);
		msg.setIs_seen(0);
		return msg;
	}

	public void GetMessegeThread() {
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (isCilent) {
					/*try {
						chatTextArea.append("\n" + "Server : " + objInputStreamClient.readObject());
					} catch (IOException e) {
						e.printStackTrace();
					}*/
				}
			}
		});
		t.start();
	}

	boolean isCilent = true;
	private String chat;

	private ObjectInputStream objInputStreamClient;
	private ObjectOutputStream objOutputStreamClient;

	private JPanel panel, chatPanel;
	private JScrollPane jScrollPane1;
	private JTextArea chatTextArea;

	private JPanel userInputPanel;
	private JTextField userInputTextField;
	private JButton userInputSendButton;

}
