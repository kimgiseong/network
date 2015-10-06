package com.bit2015.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {
	
	private static final int BUFFER_SIZE = 1024;
	private static final String SERVER_IP = "192.168.1.96";
	private static final int SERVER_PORT = 50000;
	
	public static void main(String[] args){
		DatagramSocket datagramSocket = null;
		Scanner sc = new Scanner(System.in);
		
		try{
			// 1. UDP client socket 생성
			datagramSocket = new DatagramSocket();
			
			// 2. packet 보내기
			String message = null;
			while(true){
				System.out.print(">>");
				message = sc.nextLine();
				byte[] data = message.getBytes();	
				DatagramPacket sendPacket = new DatagramPacket(data, data.length, new InetSocketAddress(SERVER_IP, SERVER_PORT));
				datagramSocket.send(sendPacket);
				if(message.equals("end") == true){
					break;
				}
				
				// 3. packet 받기
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				datagramSocket.receive(receivePacket);
				
				// 4. 수신 데이터 출력
				message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				System.out.println("<<" + message);
			}
			
			// 5. 자원 정리
			datagramSocket.close();
			sc.close();
		}catch(IOException e){
			log("error : " + e);
		}
	}
	
	public static void log(String log){
		System.out.println("[UDP-echo-client]" + log);
	}
}
