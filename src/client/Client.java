package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import been.MSG;
import utility.Common;

/**
 * 
 * @author Williyam
 * 
 */
public class Client extends Thread {

	private Socket client;
	private ObjectInputStream objInputStream;
	private ObjectOutputStream objOutputstream;

	public void CloseConnection() throws IOException{
		if( !this.client.isClosed())
			this.client.close();
	}

	public Client(int userId) {
		try {
			this.client = new Socket(Common.HostName, Common.Port);
			this.objInputStream = new ObjectInputStream(this.client.getInputStream());
			this.objOutputstream = new ObjectOutputStream(this.client.getOutputStream());
			
			
			writeHelloMessage(userId) ;
			
		//	this.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeHelloMessage(int userId) {
		try {
			MSG msg = new MSG(1,userId);
			
			if (client.isConnected()) {
				objOutputstream.writeObject(msg);
				objOutputstream.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeMessage(MSG msg) {
		try {
			if (client.isConnected()) {
				objOutputstream.writeObject(msg);
				objOutputstream.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ObjectInputStream getObjInputStream() {
		return objInputStream;
	}

	public ObjectOutputStream getObjOutputstream() {
		return objOutputstream;
	}
	

	public boolean isConnected() {
		return this.client == null ? false : this.client.isConnected();
	}

	/*@Override
	public void run() {
		try {
			while (this.runningFlag) {
				MSG msg = (MSG) this.objInputStream.readObject();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
