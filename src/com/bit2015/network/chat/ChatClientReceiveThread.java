package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ChatClientReceiveThread extends Thread {
	private static BufferedReader bufferedReader;
	
	public ChatClientReceiveThread(BufferedReader bufferedReader){
		this.bufferedReader = bufferedReader;
	}
	
	public void run(){
		try {	
			while( true ){
				String request = bufferedReader.readLine();
				if(request == null){
					ChatClient.log("프로그램 종료.");
					break;
				}
				
				System.out.println(request);
			} 
			//bufferedReader는 main에서 닫음
		}catch (IOException e) {
				ChatClient.log("error" + e);
		}		
		
	}
}
