package com.bit2015.network.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_ADDRESS = "192.168.1.95";
	private static final int SERVER_PORT = 10001;
	
	public static void main(String[] args) {
		
		Socket socket = null;
		Scanner sc = null;
		
		try{
			sc = new Scanner(System.in);
			socket = new Socket();
			
			socket.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
			
			//쓰고 / 받기
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			
			while( true ){
				System.out.print(">>");
				String data = sc.nextLine();
				if( "exit".equals(data) == true )
					break;
				
				data += "\n";
				os.write(data.getBytes());
				
				byte [] buffer = new byte [128];
				int readByteCount = is.read(buffer);
				data = new String (buffer, 0, readByteCount, "UTF-8");
				System.out.print("<< " + data);
			}
			os.close();
			is.close();
			if( socket.isClosed() == false ){
				socket.close();
			}
		}catch(IOException e){
			System.out.println("<<에러 : " + e);
		}
	}
}
