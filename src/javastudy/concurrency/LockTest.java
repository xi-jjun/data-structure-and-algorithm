package javastudy.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	static final int TIMES = 10000;
	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();
		Thread th0 = new Thread(() -> threadRun(counter));
		Thread th1 = new Thread(() -> threadRun(counter));
		Thread th2 = new Thread(() -> threadRun(counter));
		Thread th3 = new Thread(() -> threadRun(counter));

		th0.start();
		th1.start();
		th2.start();
		th3.start();

		th0.join();
		th1.join();
		th2.join();
		th3.join();

		System.out.println(counter.count);
	}

	private static void threadRun(Counter counter) {
		for (int i = 0; i < TIMES; i++) {
			counter.inc();
		}
	}

	static class Counter {

		private Lock lock = new ReentrantLock();
		private int count = 0;

		public int inc() {
			lock.lock();
			int newCount = ++count;
			lock.unlock();
			return newCount;
		}
	}
}
