package softech.com;

import java.net.*;
import java.io.*;

public class Server {
public static void main(String[] args) throws IOException{
	ServerSocket ss=new ServerSocket(8835);
	
	while(true){
		try{
			Socket client=ss.accept();
			RequestHandler rh=new RequestHandler(client);
			rh.start();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
}
