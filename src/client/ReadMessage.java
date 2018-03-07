package client;

import java.io.IOException;
import java.net.Socket;

import other.MyDiyReadSocket;
import other.MyDiyWrite;

public class ReadMessage extends Thread{
	private boolean exit=false;
	private Socket socket=null;
	private String friendname=null;
	private MyDiyReadSocket receive=null;
	
	
	
	public ReadMessage(Socket socket,String frinedname) throws IOException {
		this.friendname=frinedname;
		this.socket=socket;
		receive=new MyDiyReadSocket(socket);
		start();
		
	}
	
	@Override
	public void run() {
		int num=0;
		String connectname=null;
		String friendIp=null;
		String friendPort=null;
		OneUserSocket socket_user=null;
		String message = null;
		while(!exit) {
			try {
				message = receive.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			String sentence="from "+friendname+"'s message: "+message;
			System.out.println(sentence);
			if(message.equals("request connect")&&(socket instanceof OneUserSocket)) {
				num++;
				socket_user=(OneUserSocket)socket;
			}else if(num>0) {
				switch(num) {
				case 1:
					connectname=message;
					socket_user.showMessage("receive friend "+connectname+" connect request");
					num++;
					break;
				case 2:
					friendIp=message;
					num++;
					break;
				case 3:
					friendPort=message;
					socket_user.connectToFriend(connectname, friendIp, friendPort);
					num=0;
					break;
				}
			}
			
		}
	}
	
//	public String receiveMessage(){
//		try {
//			return receive.readUTF();
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e);
//		}
//		return null;
//	}
	
	public void shutDown() {
		try {
			exit=true;
			interrupt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("读取线程关闭");
		}
	}
}
