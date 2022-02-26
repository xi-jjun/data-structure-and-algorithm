package javastudy.concurrency;

public class ThreadBasicRunnable implements Runnable {
	/**
	 * Runnable Interface 가 사용되는 이유
	 * Which of these idioms should you use? The first idiom, which employs a Runnable object,
	 * is more general, because the Runnable object can subclass a class other than Thread.
	 *
	 * Thread class 를 사용하게 되면, extends 로 상속을 받아야 한다.
	 * 따라서 자연스럽게 다른 class 를 상속받는 것이 불가능해지기 때문에 이런 점이 싫을 때 Runnable 을 주로 사용한다고 한다.
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Main Thread Start");
		Thread thread = new Thread(new ThreadBasicRunnable());
		thread.start(); // thread 실행 시작위한 method 호출
		thread.join();
		System.out.println("Main Thread End");
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
