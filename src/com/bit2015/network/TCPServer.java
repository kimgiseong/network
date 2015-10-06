package com.bit2015.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	private static final int PORT = 10002;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
			System.out.println("[서버] 연결 기다림");
			Socket socket = serverSocket.accept();
			
			
			
			
			
			
			// 데이터 송수신 부분
			// 연결 요청한 곳의 이름과 포트 확인
			InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			System.out.println("[서버] 연결됨 from : "+inetSocketAddress.getHostName() + ":" + inetSocketAddress.getPort());
			
			// 4. 데이터 읽고 쓰기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			while(true){
				byte [] buffer = new byte[128];
				int readByteCount = is.read(buffer);
				
				if( readByteCount == -1){ // 클라이언트가 정상적으로 종료
					System.out.println("[서버] 클라이언트로부터 연결끊김");
					break;
				}
				
				String data = new String(buffer, 0, readByteCount, "UTF-8");
				System.out.print("[서버] 데이터 수신 : " + data);
				os.write(data.getBytes("UTF-8"));
				os.flush();
			}
			
			// 스트림 닫기
			os.close();
			is.close();
			//데이터 소켓 닫기
			if( socket.isClosed() == false ){
				socket.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
