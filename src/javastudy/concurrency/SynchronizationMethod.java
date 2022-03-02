package javastudy.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizationMethod {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10); // Thread 10개 생성

		Counter counter = new Counter();

		for (int i = 0; i < 1000; i++) {
			executorService.submit(() -> counter.increment()); // counter.increment() 라는 행위를 multi-threading 으로 처리하겠다는 코드
		}

		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);

		System.out.println("counted value = " + counter.getValue());
		/**
		 * Counter class 의 increment() method 는 thread 가 공통으로 접근하는 critical-section 이다.
		 * 따라서 해당 method 에 synchronized keyword 를 추가하여 선언해주면,
		 * 해당 method 에는 한번에 1개의 thread 만 접근이 가능해진다. => interleaving 제거
		 */
	}

	static class Counter {
		int value = 0;

		/**
		 * critical-section
		 */
		public synchronized void increment() {
			value++;
		}

		public int getValue() {
			return value;
		}
	}
}
