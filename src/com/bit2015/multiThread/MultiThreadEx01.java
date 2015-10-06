package com.bit2015.multiThread;

public class MultiThreadEx01 {

	public static void main(String[] args) {	
		
		Thread thread = new DigitThread();
		thread.start();
		
		try {
			Thread.sleep(1000); // 1초간 정지
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
