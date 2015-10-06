package com.bit2015.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			System.out.println(inetAddress.getHostName());
			System.out.println(inetAddress.getHostAddress());
			
			byte [] address = inetAddress.getAddress();
			for (int j = 0; j < address.length; j++) {
				int addr = address[j] & 0xff;
				System.out.println(addr);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
