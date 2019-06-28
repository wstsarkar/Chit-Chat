package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import utility.Common;



public class ChatFrame extends JFrame  {	

/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	Thread t ;
	
	public ChatFrame() {
		this.setSize(Common.Chat_wnd_X, Common.Chat_wnd_Y);
		this.setLayout(null);
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle(Common.APP_NAME);
	    this.setLocationRelativeTo(null);
	    
		try {
			initComponents();
			itemActionListener();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initComponents() throws Exception{

        chatPanel = new JPanel();
        jScrollPane1 = new JScrollPane (chatTextArea);
        chatTextArea = new JTextArea();
        panel = new JPanel();
        
        userInputPanel = new JPanel();
        userInputTextField = new JTextField();
        userInputSendButton = new JButton();
        
        menuBar = new JMenuBar();
        menuBar.setBounds(0,0,getWidth()-6,25);
        
        fileMenu = new JMenu();
        signOutMenuItem = new JMenuItem();
        
        helpMenu = new JMenu();
        aboutMenuItem = new JMenuItem();
        helpMenuItem = new JMenuItem();
        
        ////
        //textArea
        ////
        chatTextArea.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        chatTextArea.setBounds(5, 25, getWidth()-40, getHeight()-220);    
        chatTextArea.setEditable(false);
        ////
        // Auto scrol to show last Line
        ////
        DefaultCaret caret = (DefaultCaret) chatTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);    
        
        
        ////
        //ScrollBar add to text area
        ////
        
        jScrollPane1 = new JScrollPane (chatTextArea);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        ////
        // Chat Panel
        ////
        
        chatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));
        chatPanel.setBounds(0, 0, getWidth()-8, getHeight()-180);
        chatPanel.setLayout(new GridLayout(1,1,0,0));
        chatPanel.add(jScrollPane1,BorderLayout.CENTER);
        ////
        //userTextField 
        ///
        userInputTextField.setBounds(5, 25, getWidth()-20, 35);        
        
        ////
        //userInputSendButton 
        ////
        
        userInputSendButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/send.png")));
        userInputSendButton.setText("Send");
        userInputSendButton.setBounds(getWidth()-115, 70, 100, 30);
        ////
        // User Input panel
        ////
        
        userInputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Input"));
        userInputPanel.setBounds(0, 205, getWidth()-8, 120);
        userInputPanel.setLayout(null);
        
        userInputPanel.add(userInputTextField);
        userInputPanel.add(userInputSendButton);
        
        
        ////
        //Panel
        ////
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));
        panel.setBounds(0, 25, getWidth()-6, getHeight()-55);
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
        //Help Menu
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
	
	public void itemActionListener()
	{
	////////
        /// Save Button  Action Listener not done
        ////////
        
        userInputSendButton.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
								
					
				 try {
					 if(isCilent)
					 {
						 chatTextArea.append("\n" + userNameClient + ": " + userInputTextField.getText());
							
						 chat = userInputTextField.getText();
							
						 dataOutputStreamClient.writeUTF(chat);
						 dataOutputStreamClient.flush();
						 if(t.isInterrupted())
						 {
							 t.start();
						 }
					}
					
			        } catch (NullPointerException ex) {
			        	ex.printStackTrace();
			            JOptionPane.showMessageDialog(rootPane, "Could not Send Message!\nNo Connection!", "Send Error", JOptionPane.ERROR_MESSAGE);
			        } catch (Exception ex) {
			        	ex.printStackTrace();
			            JOptionPane.showMessageDialog(rootPane, "Could not Send Message!\nUnknown Exception:\n" + ex, "Send Error", JOptionPane.ERROR_MESSAGE);
			            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
			        }
			        finally {
			            userInputTextField.setText("");
			        }
				
			}
			

			
		});
        
        ////
        // Exit Menu Item action Listener
        ////
        
        signOutMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
        
          
        
        ////
        // aboutMenuItem Action listener
        ////
        
        aboutMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(
	                    rootPane,"Server Client Messenger"+"\n"+
	                    "Developer By: " + "Williyam Sarkar"
	                    + "\nEmail: " + "williyam.sarkar@gmail.com",
	                    "About",
	                    JOptionPane.INFORMATION_MESSAGE,
	                    new javax.swing.ImageIcon(getClass().getResource("/resources/about.png")));
				 
			}
		});
        
        ////
        // helpMenuitem Action Listener
        ////
        helpMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(
	                    rootPane,"AppName = Server Client Messanger\nDevelopedBy = Williyam Sarkar\n"
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
	                    "About",
	                    JOptionPane.INFORMATION_MESSAGE,
	                    new javax.swing.ImageIcon(getClass().getResource("/resources/about.png")));	
			}
		});  
		
	}
	
	
	public void GetMessegeThread()
	{
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(isCilent)
					{
						if (isCilent){
							try {
								chatTextArea.append("\n" + "Server : "+dataInputStreamClient.readUTF());
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						
					}
			}
		});t.start();
	}

	

	
	boolean isCilent=false;
	private String userNameClient= null; 
	private String chat;
	
	private DataInputStream dataInputStreamClient;
	private DataOutputStream dataOutputStreamClient;
	
	
	private JPanel panel, chatPanel;
	private JScrollPane jScrollPane1;
	private JTextArea chatTextArea;
	
	private JPanel userInputPanel;
	private JTextField userInputTextField;
	private JButton userInputSendButton;
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	private JMenuItem signOutMenuItem;
	
	
	private JMenu helpMenu;
	private JMenuItem aboutMenuItem;
	private JMenuItem helpMenuItem;


}
