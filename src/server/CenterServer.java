package server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

import client.OneConnectSocket;
import client.OneUserSocket;
import other.MyDiyReadSocket;
import other.MyDiyWrite;

public class CenterServer {

	public static void main(String[] args) throws IOException {
		MyDiyServerSocket server=new MyDiyServerSocket(8000);
		while(true) {
			UserSocket user=server.userRegister();
			System.out.println(user.username+"is online");
			new ServerReceive(server, user.socket, user.username);
		}
	}
}

class ServerReceive extends Thread{
	private Socket user=null;
	private String username=null;
	private MyDiyServerSocket server=null;
	public ServerReceive(MyDiyServerSocket server,Socket user,String username) {
		// TODO Auto-generated constructor stub
		super();
		this.user=user;
		this.username=username;
		this.server=server;
		start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			int num=0;
			String message=null;
			MyDiyReadSocket receive=new MyDiyReadSocket(user);
			MyDiyWrite send=new MyDiyWrite(user);
			String information="";
			while(true) {
				message=receive.readUTF();
				server.showMessage(message);
				if(message.equals("connect to friend")) {
					num++;
					System.out.println("i am ready connect");
				}else {
				if(num>0) {
					information=information+message+"-";
					num++;
					System.out.println(num);
					if(num==5) {
						System.out.println("I am ready to speak");
						server.speakToFriend(information);
						num=0;
						information="";
					}
				}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
