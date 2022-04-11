package javastudy.designpattern.singleton.sync;

public class SynchronizedSingletonTest {
	public static void main(String[] args) {
		Thread thread1 = new Thread(new SyncSingletonThread());
		Thread thread2 = new Thread(new SyncSingletonThread());
		Thread thread3 = new Thread(new SyncSingletonThread());
		thread1.start();
		thread2.start();
		thread3.start();
	}

	static class SyncSingletonThread extends Thread {
		public void printInstance() {
			SynchronizedSingleton instance = SynchronizedSingleton.getInstance();
			System.out.println("instance = " + instance);
		}

		@Override
		public void run() {
			printInstance();
		}
	}

}
