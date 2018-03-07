package client;

import java.io.IOException;
import java.net.Socket;

import other.MyDiyReadLoad;
import other.MyDiyReadSocket;
import other.MyDiyWrite;

public class WriteMessage extends Thread{
	private boolean exit=false;
	private Socket socket=null;
	private MyDiyReadLoad myinput=new MyDiyReadLoad();
	private String friendname=null;
	private MyDiyWrite send=null;
	public WriteMessage(Socket socket,String name) throws IOException {
		// TODO Auto-generated constructor stub
		this.socket=socket;
		friendname=name;
		send=new MyDiyWrite(socket);
		start();
	}

	public void run() {
		try {
			sendMessage(friendname);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!exit) {
			try {
				String message=myinput.readLine();
				sendMessage(message);
				String sentence="to "+friendname+"'s message: "+message;
				System.out.println(sentence);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(String message) throws IOException{
		send.writeUTF(message);
	}
	
	public void shutDown() {
		try {
			
			exit=true;
			interrupt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("发送线程关闭");
		}
	}
}
