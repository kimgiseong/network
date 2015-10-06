package com.bit2015.multiThread;

public class MultiThreadEx03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread1 = new DigitThread(); // 스레드 상속받아서 스레드 만들기
		Thread thread2 = new Thread( new AlphabetRunnableImpl() ); // runnable 사용하여 스레드 만들기
		// runnable은 특정클래스를 스레드로 돌릴 상황이 생길겨우 사용 MultiThreadEx04참고 / 안드로이드에서 많이 씀
		
		thread1.start();
		thread2.start();
	}

}
