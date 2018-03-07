package other;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class MyDiyReadSocket extends DataInputStream {

	public MyDiyReadSocket(Socket socket) throws IOException {
		super(socket.getInputStream());
		// TODO Auto-generated constructor stub
	}

}
