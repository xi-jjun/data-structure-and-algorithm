package javastudy.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadInterferenceTest {
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
		 * 우리의 의도는 1000번의 increment() method 호출의 결과가 value = 1000 의 결과를 가져올 것이라 생각.
		 * 그러나 그렇게 되지 않고 995, 1000, 996, 999, 999... 등 1000이 아닌 수들이 나오는 것을 발견.
		 * → 일관되지 못한 결과를 보여주고 있다.
		 *
		 * 왜 그런지에 대해 알아보도록 해보자. (참고 : https://www.callicoder.com/java-concurrency-issues-and-thread-synchronization/)
		 * increment() method 의 동작에 대해 보면,
		 * 	1. count instance 의 value 변수 값을 가져온다.
		 * 	2. 가져온 value 변수에 1 추가.
		 * 	3. 추가된 value 변수를 저장.
		 * 의 과정을 거치게 된다. 근데 이걸 여러개의 thread 가 동시에 하게 된다면...? 을 생각해보자.
		 *
		 * 당장 A, B 2개의 thread 만 간단하게 생각해봐도,
		 * 	1. A Thread 가 count instance 의 value 변수 값을 가져온다. (value = 0)
		 * 	2. B Thread 가 count instance 의 value 변수 값을 가져온다. (value = 0)
		 * 	3. A Thread 가 가져온 변수 값에 1 추가. (value = 1)
		 * 	4. B Thread 가 가져온 변수 값에 1 추가. (value = 1)
		 * 	5. A, B 둘 다 가져온 값 저장.=> 둘 다 1을 저장하게 된다...
		 *
		 * 원래 예상대로라면, A Thread 가 value++을 하고 난 뒤에 B Thread 가 value++을 해서
		 * value=2 가 될 것이라 생각했지만, Thread 로 실행된 두 연산이 서로 interleaving 됐기 때문에
		 * A Thread 가 한 일이 없던 일로 되어 버린 것이다.
		 */
	}

	static class Counter {
		int value = 0;

		public void increment() {
			value++;
		}

		public int getValue() {
			return value;
		}
	}
}
