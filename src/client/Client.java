package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import been.MSG;

/**
 * 
 * @author Williyam
 * 
 */
public class Client extends Thread {
	private List<MSG> messages;
	protected Socket client;
	protected ObjectInputStream streamFromServer;
	private boolean runningFlag = false;

	public List<MSG> getMessages() {
		return this.messages;
	}
	
	public void CloseConnection() throws IOException{
		this.runningFlag = false;
		if( !this.client.isClosed())
			this.client.close();
	}

	public Client(String hostName, int ip) {
		try {
			this.client = new Socket(hostName, ip);
			this.runningFlag = true;
			this.streamFromServer = new ObjectInputStream(this.client.getInputStream());
			this.messages = Collections.synchronizedList(new ArrayList<MSG>());
			this.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (this.runningFlag) {
				MSG m = (MSG) this.streamFromServer.readObject();
				this.messages.add(m);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
