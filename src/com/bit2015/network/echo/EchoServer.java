package com.bit2015.network.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	private static final int PORT = 10002;
	
	public static void main(String[] args) {
		Thread thread = null;
		ServerSocket serverSocket = null;
		
		try {
			//accept부분
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩, 포트는 5000이상 아무거나
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			System.out.println("[서버] 바인딩  " + hostAddress + " : " + PORT);
			
			// 3. 연결 요청 대기
			while( true ){
				System.out.println("[서버] 연결 기다림");
				Socket socket = serverSocket.accept();
				
				thread = new EchoReceiveThread(socket);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[서버] 에러 ");
		}finally{
			if(serverSocket != null && serverSocket.isClosed() == false){ // serverSocket이 null이거나 닫혀있지 않으면
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
