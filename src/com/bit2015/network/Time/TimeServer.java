package com.bit2015.network.Time;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {

	private static final int PORT = 50000;
	private static final int BUFFER_SIZE = 1024; // send와 receive의 size는 동일해야함
	
	public static void main(String[] args) {
		DatagramSocket datagramSocket = null;
		
		try{
			// 1. UDP server socket 생성
			datagramSocket = new DatagramSocket(PORT);
			
			// 2. 수신 대기
			log("packet 수신 대기");
			DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			while(true){
				datagramSocket.receive(receivePacket);
				
				// 3. 수신 데이터 출력
				String message = new String(receivePacket.getData(),0, receivePacket.getLength(), "UTF-8");
				if(message.equals("") == false){
					break;
				}
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss a" );
				String data = format.format( new Date() );
				byte [] message2 = data.getBytes();
				
				// 4. 시간 데이터 보내기
				DatagramPacket sendPacket = new DatagramPacket(message2, message2.length, receivePacket.getAddress(), receivePacket.getPort());
				datagramSocket.send(sendPacket);
			}
			// 5. 자원정리
			datagramSocket.close();
		}catch(IOException e){
			log("error : " + e);
		}
	}
	
	public static void log(String log){
		System.out.println("[UDP-echo-server]" + log);
	}
}