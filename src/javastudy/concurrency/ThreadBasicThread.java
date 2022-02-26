package javastudy.concurrency;

public class ThreadBasicThread extends Thread {
	/**
	 * Thread class 가 사용되는 이유
	 * The second idiom is easier to use in simple applications,
	 * but is limited by the fact that your task class must be a descendant of Thread.
	 *
	 * 간단한 프로그램을 만들 때 쉽게 사용할 수 있기 때문이다. 그러나 상속받는 class 는 다른 클래스를 상속받을 수 없다는 단점이 있다.
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Main Thread Start");
		ThreadBasicThread thread = new ThreadBasicThread();
		thread.start();
		thread.join();
		System.out.println("Main Thread End");
		/**
		 * <<thread.join() 을 없애고 실행했을 때>>
		 * Main Thread Start
		 * Main Thread End
		 * Thread Start
		 * Thread End
		 *
		 * <<thread.join() 이 있을 때 출력>>
		 * Main Thread Start
		 * Thread Start
		 * Thread End
		 * Main Thread End
		 */
	}

	@Override
	public void run() {
		System.out.println("Thread Start");
		// Logic...
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		// ...logic
		System.out.println("Thread End");
	}
}
