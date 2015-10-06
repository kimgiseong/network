package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_ADDRESS = "192.168.1.96";
	private static final int SERVER_PORT = 10001;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = null;
		Socket socket = null;

		try {
			// 1. 키보드 연결
			scanner = new Scanner(System.in);
			// 2. socket 생성
			socket = new Socket();
			// 3. 연결
			socket.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
			// 4. reader/ writer 생성
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			// 5. join 프로토콜
			System.out.print("닉네임>>");
			String nickname = scanner.nextLine();
			printWriter.println("join:" + nickname);
			printWriter.flush();
			// 6. ChatClientRecevieThread 시작
			// 수신만 수행하는 thread
			Thread thread = new ChatClientReceiveThread(bufferedReader);
			thread.start();
			// 7. 키보드 입력 처리
			// 입력만 수행
			while (true) {
				// System.out.print( ">>" );
				String input = scanner.nextLine();

				if ("quit".equals(input) == true) {
					// 8. quit 프로토콜 처리
					printWriter.println("quit");
					printWriter.flush();
					break;
				} else {
					// 9. 메시지 처리
					printWriter.println("message:" + input);
					printWriter.flush();
				}
			}
			// 10. 자원정리
			bufferedReader.close();
			printWriter.close();
			if (socket.isClosed() == false) {
				socket.close();
			}
		} catch (IOException ex) {
			log("error:" + ex);
		}

	}

	public static void log(String log) {
		System.out.println("[chat-client]" + log);
	}
}
