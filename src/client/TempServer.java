package client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import other.MyDiyReadSocket;

public class TempServer extends ServerSocket{

	public TempServer() throws IOException {
		super(0);
	}
	
	
	public Socket waitConnect(String friendname,String myname) throws IOException {
		while(true) {
		Socket conversation=this.accept();
		System.out.println("my tempserver port is "+this.getLocalPort());
		MyDiyReadSocket receive=new MyDiyReadSocket(conversation);
		System.out.println("I am want to connect "+friendname+" I port is "+conversation.getLocalPort());
		String temname=receive.readUTF();
		System.out.println("I RECEIVE FRIEND NAME is "+temname);
		if(temname.equals(myname)) {
			System.out.println("connect success friend");
			return conversation;
		}else {
			conversation.close();
			receive.close();
		}
		}
	}
	
}
