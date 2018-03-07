package client;

import java.io.IOException;

import other.MyDiyReadLoad;

public class ClientMain {
	public static void main(String[] args) throws IOException {
		MyDiyReadLoad readload=new MyDiyReadLoad();
		System.out.println("请输入用户名：");
		String username=readload.readLine();
		OneUserSocket oneuser=new OneUserSocket(username,"127.0.0.1",8000);
		System.out.println("i am "+username+" port is "+oneuser.getPort());
		
		new ReadMessage(oneuser, "server");
		System.out.println("1、I want to speak to someone");
		System.out.println("2、I just want to online");
		System.out.println("0、close online");
		switch (readload.readLine()) {
		case "1":
			System.out.println("please input your friend name");
			String friendname=readload.readLine();
			oneuser.connectFriend(friendname);
			break;
		case "2":
			System.out.println("you are online");
			break;
		default:
			System.out.println("you are close connect");
			oneuser.closeAll();
			break;
		}
		
		
	}
}
