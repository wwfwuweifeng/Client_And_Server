package client;

import java.io.IOException;
import java.net.Socket;

import other.MyDiyReadSocket;
import other.MyDiyWrite;

public class OneConnectSocket extends Socket {
	protected String name;
	private MyDiyWrite send=null;
	private MyDiyReadSocket receive=null;
	public OneConnectSocket(String name,String host,int port) throws IOException {
		// TODO Auto-generated constructor stub
		super(host,port);
		System.out.println(host+"====="+port);
		this.name=name;
		send=new MyDiyWrite(this);
		receive=new MyDiyReadSocket(this);
		System.out.println("i am tall to "+this.name);
		send.writeUTF(this.name);
	}

	public OneConnectSocket(String name,Socket socket) throws IOException {
		
		this.name=name;
		send=new MyDiyWrite(socket);
		receive=new MyDiyReadSocket(socket);
	}
	

	
	public void sendMessage(String message) throws IOException{
		send.writeUTF(message);
	}
	
	public String receiveMessage() throws IOException{
		String message=receive.readUTF();
		return message;
	}
	
	public synchronized void showMessage(String message) {
		
		System.out.println(message);
	}
	
	public void connectToFriend(String friendname,String ip,String port) {
		System.out.println("执行了父亲的");
	}
	
	public void closeAll() {
		try {
		send.close();
		receive.close();
		this.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally {
			System.out.println("socket close ok");
		}
	}
}
