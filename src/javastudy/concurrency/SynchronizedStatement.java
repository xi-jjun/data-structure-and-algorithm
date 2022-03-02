package javastudy.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedStatement {
	static final int TIMES = 10000;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("=== Test Synchronized Statement Increment C1, C2 ===");
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		Counter counter = new Counter(0, 0);
		System.out.println("init c1, c2 = 0, 0");
		for (int i = 0; i < TIMES; i++) {
			executorService.submit(() -> counter.synStatementIncrementC1());
			executorService.submit(() -> counter.synStatementIncrementC2());

			// synchronized method Test code
//			executorService.submit(() -> counter.synMethodIncrementC1());
//			executorService.submit(() -> counter.synMethodIncrementC2());
		}
		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);

		// wait
		while (counter.c1 + counter.c2 != 2 * TIMES) {
		}

		System.out.println("after c1, c2 = " + counter.c1 + " " + counter.c2);
	}

	static class Counter {
		private final Object lockC1 = new Object();
		private final Object lockC2 = new Object();
		private int c1;
		private int c2;

		public Counter(int c1, int c2) {
			this.c1 = c1;
			this.c2 = c2;
		}

		public synchronized void synMethodIncrementC1() {
			c1++;
			System.out.println("c1 = " + c1 + ", 이 때 c2 = " + c2);
		}

		public synchronized void synMethodIncrementC2() {
			c2++;
			System.out.println("c2 = " + c2 + ", 이 때 c1 = " + c1);

		}

		public void synStatementIncrementC1() {
			synchronized (lockC1) {
				c1++;
				System.out.println("c1 = " + c1 + ", 이 때 c2 = " + c2);
			}
		}

		public void synStatementIncrementC2() {
			synchronized (lockC2) {
				c2++;
				System.out.println("c2 = " + c2 + ", 이 때 c1 = " + c1);
			}
		}
	}
}
