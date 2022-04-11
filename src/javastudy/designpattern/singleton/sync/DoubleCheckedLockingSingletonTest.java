package javastudy.designpattern.singleton.sync;

import javastudy.designpattern.singleton.eager.EagerSingleton;

public class DoubleCheckedLockingSingletonTest {
	public static void main(String[] args) {
		Thread thread1 = new Thread(new DoubleCheckedLockingSingletonThread());
		Thread thread2 = new Thread(new DoubleCheckedLockingSingletonThread());
		Thread thread3 = new Thread(new DoubleCheckedLockingSingletonThread());
		thread1.start();
		thread2.start();
		thread3.start();
	}

	static class DoubleCheckedLockingSingletonThread extends Thread {
		public void printInstanceClass() {
			EagerSingleton instance = EagerSingleton.getInstance();
			System.out.println("instance = " + instance);
		}

		@Override
		public void run() {
			printInstanceClass();
		}
	}
}
