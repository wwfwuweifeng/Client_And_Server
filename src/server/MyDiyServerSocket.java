package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import client.OneUserSocket;
import other.MyDiyReadSocket;
import other.MyDiyWrite;

public class MyDiyServerSocket extends ServerSocket{
	private HashMap<String,Socket> onlineuser=new HashMap();
	private MyDiyReadSocket receive=null;
	private MyDiyWrite write=null;
	public MyDiyServerSocket(int port) throws IOException {
		// TODO Auto-generated constructor stub
		super(port);
	}
	
	public UserSocket userRegister() throws IOException {
		Socket usersocket=this.accept();
		receive=new MyDiyReadSocket(usersocket);
		String username=receive.readUTF();
		synchronized (onlineuser){
			onlineuser.put(username, usersocket);
		}
		return new UserSocket(usersocket, username);
	}
	
	public synchronized void showMessage(String message) {
		
		System.out.println(message);
	}
	
	public synchronized void speakToFriend(String information) {
		String[] messages=information.split("-");
		Socket friendsocket=onlineuser.get(messages[1]);
		System.out.println("i am ready to tell your friend");
		try {
			MyDiyWrite send=new MyDiyWrite(friendsocket);
			send.writeUTF("request connect");
			send.writeUTF(messages[0]);
			send.writeUTF(messages[2]);
			send.writeUTF(messages[3]);
			System.out.println("speak to friend is ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean connectUser(String username) {
		
		
		
		return true;
	}	
}

class UserSocket{
	public Socket socket=null;
	public String username=null;
	public UserSocket(Socket socket,String username) {
		// TODO Auto-generated constructor stub
		this.socket=socket;
		this.username=username;
	}
}
