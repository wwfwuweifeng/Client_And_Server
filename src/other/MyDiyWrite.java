package other;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MyDiyWrite extends DataOutputStream {
	public MyDiyWrite(Socket socket) throws IOException{
		// TODO Auto-generated constructor stub
		super(socket.getOutputStream());
	}
}
