package javastudy.concurrency;

public class MemoryConsistencyTest {
	private static boolean sayHello = false;

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			while (!sayHello) {
			}

			System.out.println("Hello World!!");

			while (sayHello) {
			}

			System.out.println("Bye World!!");
		});

		thread.start();

//		Thread.sleep(1000);
		System.out.println("Make Say Hello = True");
		sayHello = true;

//		Thread.sleep(1000);
		System.out.println("Make Say Hello = False");
		sayHello = false;
		/**
		 * 예상 시나리오
		 * 1. Make Say Hello = True 화면에 출력됨
		 * 2. Hello World!! 화면에 출력. (sayHello 변수가 True 가 되었기에)
		 * 3. Make Say Hello = False 화면에 출력됨 ( Thread 안에서는 sayHello=true 이기에 while loop 에 멈춰있는 중)
		 * 4. sayHello=false 가 되자마자 Bye World!! 화면에 출력.
		 *
		 *
		 * 실제 출력
		 * [무한루프에 걸릴 때]
		 * Make Say Hello = True
		 * Make Say Hello = False
		 * (무한 루프에 걸림)
		 * or
		 * [무한루프에는 안걸리지만 우리가 예상한대로는 안나왔을 때]
		 * Make Say Hello = True
		 * Make Say Hello = False
		 * Hello World!!
		 * Bye World!!
		 * or
		 * [우리가 예상한 대로 나왔을 때]
		 * Make Say Hello = True
		 * Hello World!!
		 * Make Say Hello = False
		 * Bye World!!
		 *
		 *
		 * why?? 코드 원문 참조 : https://www.callicoder.com/java-concurrency-issues-and-thread-synchronization/
		 * (위 사이트의 코드에서 Thread.sleep 부분을 없앴습니다)
		 * 이게 바로 memory consistency error 이다.
		 * main thread 가 sayHello 변수에 대한 변경을 했다는 사실을 thread 가 인식을 못했기 때문에 그렇다.
		 * [무한루프에 걸릴 때]
		 * 1. 처음에 thread 가 시작할 때 sayHello 는 false 이기에 while loop 에 걸려있다. - line 19
		 * 2. Make Say Hello = True 화면에 출력 - line 23
		 * 3. sayHello = true 로 변경 - line 24
		 * 4. Make Say Hello = False 화면에 출력 - line 26
		 * 5. sayHello = false 로 변경 - line 27
		 * 6. thread 의 while loop 의 조건을 확인하려고 했더니 여전히 sayHello=false 이기에 계속 무한루프.
		 *
		 * [무한루프에는 안걸리지만 우리가 예상한대로는 안나왔을 때]
		 * 1. 처음에 thread 가 시작할 때 sayHello 는 false 이기에 while loop 에 걸려있다. - line 19
		 * 2. Make Say Hello = True 화면에 출력 - line 23
		 * 3. sayHello = true 로 변경 - line 24
		 * 4. Make Say Hello = False 화면에 출력 - line 26
		 * 5. thread 에서 sayHello=true 이기에 while loop 탈출. - thread line 8
		 * 6. thread 에서 Hello World!! 화면에 출력 - thread line 11
		 * 7. sayHello = false 로 변경 - line 27
		 * 8. thread 에서 sayHello=false 이기에 while loop 탈출. - thread line 13
		 * 9. thread 에서 Bye World!! 화면에 출력 - thread line 16
		 * 10. 종료
		 *
		 *
		 * 100번 실행을 해본 결과,
		 * [무한루프에는 안걸리지만 우리가 예상한대로는 안나왔을 때] 의 케이스가 92번으로 가장 많이 나왔고,
		 * 가끔 [무한루프에 걸릴 때] 경우가 5번 발생.
		 * [우리가 예상한 대로 나왔을 때]는 딱 3번 발생.
		 */
	}
}
