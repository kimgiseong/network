package com.bit2015.multiThread;

public class Alphabet {
	
	public void print(){
		for (char c = 'A'; c <= 'Z' ; c++) {
			System.out.print( c );
			try {
				Thread.sleep(1000); // 1초 정지
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
