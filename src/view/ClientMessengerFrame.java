package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
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



public class ClientMessengerFrame extends JFrame  {	

/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	Thread t ;
	
	public ClientMessengerFrame() {
		this.setSize(300, 385);
		this.setLayout(null);
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle(" Server Client Messenger");
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
        saveChatMenuItem = new JMenuItem();
        exitMenuItem = new JMenuItem();
        
        connectionsMenu = new JMenu();
        serverMenuItem = new JMenuItem();
        clientMenuItem = new JMenuItem();
        closeConnectionMenuItem = new JMenuItem();
        
        helpMenu = new JMenu();
        myIpMenuItem = new JMenuItem();
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
        saveChatMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/save.png"))); 
        saveChatMenuItem.setText("Save Chat");
        fileMenu.add(saveChatMenuItem);
        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/exit.png"))); 
        exitMenuItem.setText("Exit");
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        
        ////
        // Connection Menu
        ////
        
        connectionsMenu.setText("Connections");
        serverMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/server.png"))); 
        serverMenuItem.setText("Server");
        connectionsMenu.add(serverMenuItem);
        clientMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/client.png"))); 
        clientMenuItem.setText("Client");
        connectionsMenu.add(clientMenuItem);
        closeConnectionMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/disconnect.png"))); 
        closeConnectionMenuItem.setText("Close Connection");
        connectionsMenu.add(closeConnectionMenuItem);
        menuBar.add(connectionsMenu);
        
        ////
        //Help Menu
        ////
        
        helpMenu.setText("Help");
        myIpMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ip.png"))); 
        myIpMenuItem.setText("My IP");       
        helpMenu.add(myIpMenuItem);
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
//						  new Thread(new Runnable() {
//							
//							@Override
//							public void run() {
//								try {
//									chatTextArea.append("\n" + "Server : "+dataInputStreamClient.readUTF());
//									
//								} catch (IOException e) {
//									e.printStackTrace();
//								}								
//							}
//						}).start();
//						 clientClass.sendMessege();
							
					}
					
					 if (isServer)
					 {
						 
						 chatTextArea.append("\n" + userServerName + ": " + userInputTextField.getText());
						 chat = userInputTextField.getText();
						 dataOutputStreamServer.writeUTF(chat);
						 dataOutputStreamServer.flush();
						 
						 if(t.isInterrupted())
						 {
							 t.start();
						 }
//						 new Thread(new Runnable() {
//								
//								@Override
//								public void run() {
//									try {
//										chatTextArea.append("\n" + "Client : "+dataInputStreamServer.readUTF());
//										System.out.println(dataInputStreamClient.readUTF());
//									} catch (IOException e) {
//										e.printStackTrace();									
//									}
//								}
//							}).start();
						 
	//					 serverClass.sendMessege();
					 }  
					
					 
					
					
			        } catch (NullPointerException ex) {
			        	ex.printStackTrace();
			            JOptionPane.showMessageDialog(rootPane, "Could not Send Message!\nNo Connection!", "Send Error", JOptionPane.ERROR_MESSAGE);
			        } catch (Exception ex) {
			        	ex.printStackTrace();
			            JOptionPane.showMessageDialog(rootPane, "Could not Send Message!\nUnknown Exception:\n" + ex, "Send Error", JOptionPane.ERROR_MESSAGE);
			            Logger.getLogger(ClientMessengerFrame.class.getName()).log(Level.SEVERE, null, ex);
			        }
			        finally {
			            userInputTextField.setText("");
			        }
				
			}
			

			
		});
        
        ////
        // Exit Menu Item action Listener
        ////
        
        exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
        
        ////
        //Save Chat menu Item Action Listener
        ////
        saveChatMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					@SuppressWarnings("resource")
					FileOutputStream fileOutputStream = new FileOutputStream("properties/Save.txt");
					
					String chatLog = "******* JLM Chat Log *******\n"
	                        // set the program startup time
	                        + "Chat Started: " +  "\n"
	                        // set the chat save time
	                        + "Chat Saved: "
	                        + "Client User Name: " + userNameClient + "\n"
	                        + "Serner User Name: " + userServerName + "\n"
	                        + "******* Chat Message *******\n"
	                        // set the chat message
	                        + chatTextArea.getText();
					
					try {
						fileOutputStream.write(chatLog.getBytes());
						fileOutputStream.flush();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
				
			}
		});
        
        
        ////
        // server Menu Item Action Listener
        //// 
        
        serverMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				portIdString = JOptionPane.showInputDialog(rootPane, "Enter Server Port ID to set", "2222");
				try {
					isServer=true;
					isCilent = false;
					clientMenuItem.setVisible(false);
					if (portIdString == null) {
	                    throw new NullPointerException();
	                }
					else
					{					 
						host = InetAddress.getLocalHost();					
						JOptionPane.showMessageDialog(
								rootPane,
								"\nServer IP: " + host.getHostAddress()
								+ "\nPort : " + portIdString,
								"Server Connection",
								JOptionPane.INFORMATION_MESSAGE,
								new javax.swing.ImageIcon(getClass().getResource("/resources/chat_32x32.png")));
					
						System.out.println(ipString+"   "+Integer.valueOf(portIdString));
				
						userServerName = JOptionPane.showInputDialog(rootPane, "Enter your chat name", "Server");
						serverSocket = new ServerSocket(Integer.valueOf(portIdString));
						socketServer =serverSocket.accept();
						dataInputStreamServer = new DataInputStream(socketServer.getInputStream());
						dataOutputStreamServer = new DataOutputStream(socketServer.getOutputStream());
						
			            dataOutputStreamServer.writeUTF("Request excepted -----");
			            dataOutputStreamServer.flush();
			            GetMessegeThread();
						
	                }
	                if (userServerName == null) {
	                	userServerName = "Server";
	                }
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
        
        ////
        //client Menu Item action Listener
        ////
        clientMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					isCilent =true;
					isServer = false;
					serverMenuItem.setVisible(false);
					
	                ipString = JOptionPane.showInputDialog(rootPane, "Enter Server IP", "localhost");
	                if (ipString == null) {
	                    throw new NullPointerException();
	                }

	                portIdString = JOptionPane.showInputDialog(rootPane, "Enter Server Port ID", "2222");
	              
	                if (portIdString == null) {
	                    throw new NullPointerException();
	                }
	                
	                JOptionPane.showMessageDialog(rootPane, "Chat Client connected to Chat Server with \nIP: " + ipString + " and \nPort Id: " + portIdString, "Client Connected", JOptionPane.INFORMATION_MESSAGE);
	                 userNameClient = JOptionPane.showInputDialog(rootPane, "Enter your chat name", "Client");
	                 if (userNameClient == null) {
	                    userNameClient = "Client";
	                }
	                socketClient = new Socket(ipString,Integer.valueOf(portIdString));
						
		            dataOutputStreamClient = new DataOutputStream(socketClient.getOutputStream()); 
		            dataInputStreamClient = new DataInputStream(socketClient.getInputStream());
		            dataOutputStreamClient.writeUTF("Client Request -----");
		            dataOutputStreamClient.flush();
		            GetMessegeThread();
	                System.out.println(ipString+"   "+Integer.valueOf(portIdString));
				

	            } catch (NullPointerException ex) {
	            	ex.printStackTrace();
	            	
	            } catch (NumberFormatException ex) {
	            	ex.printStackTrace();
	                JOptionPane.showMessageDialog(rootPane, "Please Enter a valid Port Id!", "Port ID Error", JOptionPane.ERROR_MESSAGE);
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(rootPane, "Could not connect to Server with IP: " + ipString + " and Port Id: " + portIdString + "\nUnknown Exception:\n" + ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
	                Logger.getLogger(ClientMessengerFrame.class.getName()).log(Level.SEVERE, null, ex);
	            }
				
			}
		});
        
        ////
        //Closed connection Action Listener
        ////
        
        closeConnectionMenuItem.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				t.stop();
				try {
					serverMenuItem.setVisible(true);
					clientMenuItem.setVisible(true);
					isCilent = false;
					isServer = false;
					socketClient.close();
					dataOutputStreamClient.close();
					dataOutputStreamServer.close();
		            if (socketServer != null && socketServer.isConnected()) {
		                
		                
		                serverSocket.close();
		               
		                JOptionPane.showMessageDialog(rootPane, "Connection Disconnected", "Connection", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		               
		                JOptionPane.showMessageDialog(rootPane, "No Connections Found!", "Connection", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SocketException ex) {
		        	ex.printStackTrace();
		            JOptionPane.showMessageDialog(rootPane, "Connection Termination Failed!\nSocket Exception:\n" + ex, "Disconnection Error", JOptionPane.ERROR_MESSAGE);
		            Logger.getLogger(ClientMessengerFrame.class.getName()).log(Level.SEVERE, null, ex);
		        } catch (IOException ex) {
		        	ex.printStackTrace();
		            JOptionPane.showMessageDialog(rootPane, "Connection Termination Failed!\nIO Exception:\n" + ex, "Disconnection Error", JOptionPane.ERROR_MESSAGE);
		            Logger.getLogger(ClientMessengerFrame.class.getName()).log(Level.SEVERE, null, ex);
		        } catch (NullPointerException ex) {
		        	ex.printStackTrace();
		            // on null pointer exception do nothing
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		        	
		            JOptionPane.showMessageDialog(rootPane, "Connection Termination Failed!\nUnknown Exception:\n" + ex, "Disconnection Error", JOptionPane.ERROR_MESSAGE);
		            Logger.getLogger(ClientMessengerFrame.class.getName()).log(Level.SEVERE, null, ex);
		        }
				
			}
		});
        
        
        
        
        
        
        ////
        //myIpMenuItem Action Listener
        ////
        
        myIpMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					host = InetAddress.getLocalHost();					
					JOptionPane.showMessageDialog(
		                    rootPane,
		                    "Your IP: " + host.getHostAddress()
		                    + "\nHost Name: " + host.getHostName(),
		                    "MyIP",
		                    JOptionPane.INFORMATION_MESSAGE,
		                    new javax.swing.ImageIcon(getClass().getResource("/resources/chat_32x32.png")));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
	            
				
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
				while(isServer || isCilent)
					{
						if (isCilent){
							try {
								chatTextArea.append("\n" + "Server : "+dataInputStreamClient.readUTF());
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (isServer){
							try {
								chatTextArea.append("\n" + "Client : "+dataInputStreamServer.readUTF());
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
			}
		});t.start();
	}

	

	
	boolean isServer=false,isCilent=false;
	private InetAddress host;
	private String userServerName=null,userNameClient= null; 
	private String chat;
	
	private DataInputStream dataInputStreamClient;
	private DataOutputStream dataOutputStreamClient;
	
	private DataInputStream dataInputStreamServer;
	private DataOutputStream dataOutputStreamServer;
	
	private ServerSocket serverSocket;
	private Socket socketServer,socketClient;
	
	private String portIdString=null;
	private String ipString=null;
	
	private JPanel panel, chatPanel;
	private JScrollPane jScrollPane1;
	private JTextArea chatTextArea;
	
	private JPanel userInputPanel;
	private JTextField userInputTextField;
	private JButton userInputSendButton;
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	private JMenuItem saveChatMenuItem;
	private JMenuItem exitMenuItem;
	
	private JMenu connectionsMenu;
	private JMenuItem serverMenuItem;
	private JMenuItem clientMenuItem;
	private JMenuItem closeConnectionMenuItem;
	
	private JMenu helpMenu;
	private JMenuItem myIpMenuItem;
	private JMenuItem aboutMenuItem;
	private JMenuItem helpMenuItem;


}
