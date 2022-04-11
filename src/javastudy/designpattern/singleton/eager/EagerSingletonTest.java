package javastudy.designpattern.singleton.eager;

public class EagerSingletonTest {
	public static void main(String[] args) {
		Thread thread1 = new Thread(new EagerSingletonThread());
		Thread thread2 = new Thread(new EagerSingletonThread());
		Thread thread3 = new Thread(new EagerSingletonThread());
		thread1.start();
		thread2.start();
		thread3.start();
	}

	static class EagerSingletonThread extends Thread {
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
