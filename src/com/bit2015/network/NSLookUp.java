package com.bit2015.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner(System.in);
			
			while(true){
				System.out.print(">");
				String host = sc.nextLine();
				if("exit".equals(host) == true)
					break;
				InetAddress [] inetAddress = InetAddress.getAllByName(host);
				for (int i = 0; i < inetAddress.length; i++) {
					System.out.println(inetAddress[i].getHostName() + " : " + inetAddress[i].getHostAddress());
				}
			}
			sc.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("IP를 가져올 수 없습니다.");
		}	
	}
}
