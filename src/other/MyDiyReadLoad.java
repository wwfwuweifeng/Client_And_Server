package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyDiyReadLoad extends BufferedReader{
	
	public MyDiyReadLoad() throws IOException {
		// TODO Auto-generated constructor stub
		super(new InputStreamReader(System.in));
	}
}
