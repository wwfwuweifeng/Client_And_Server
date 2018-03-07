package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import other.MyDiyReadSocket;
import other.MyDiyWrite;

public class OneUserSocket extends OneConnectSocket {
	public OneUserSocket(String name,String host,int port) throws IOException {
		// TODO Auto-generated constructor stub
		super(name,host,port);
	}
	
	public OneUserSocket(Socket socket,String name) throws IOException {
		// TODO Auto-generated constructor stub
		super(name,"127.0.0.1",socket.getPort());	
	}
	
	@Override
	public void connectToFriend(String friendname, String ip, String port) {
		try {
			Socket session=new Socket(ip, Integer.parseInt(port));
			System.out.println("I am bei connect "+name);
			new ReadMessage(session, friendname);
			new WriteMessage(session, friendname);
			System.out.println("connect to "+friendname+" is success,please start tall");
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connectFriend(String friendname) throws IOException {
		TempServer tempServer=new TempServer();
		this.sendMessage("connect to friend");
		this.sendMessage(name);
		this.sendMessage(friendname);
		this.sendMessage("127.0.0.1");
		this.sendMessage(tempServer.getLocalPort()+"");
		Socket session=tempServer.waitConnect(friendname,name);
		tempServer.close();
		new ReadMessage(session, friendname);
		new WriteMessage(session, friendname);
		System.out.println("和"+friendname+"的连接以建立，可以开始通话了");
	}
}
