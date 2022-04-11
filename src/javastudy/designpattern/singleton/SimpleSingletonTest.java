package javastudy.designpattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleSingletonTest {
	public static void main(String[] args) throws InterruptedException {
//		singletonClassEqualTest(); // multi-threading 환경이 아닐 때 생성되는 instance class 는 같은지 test

		// SimpleSingleton.java class 가 thread-safe 하지 않다는 문제를 확인하기 위한 코드
		Thread thread1 = new Thread(new SingletonThread());
		Thread thread2 = new Thread(new SingletonThread());
		Thread thread3 = new Thread(new SingletonThread());
		thread1.start();
		thread2.start();
		thread3.start();
		// [thread-safe 하지 않다는 증거] - 20번 돌려서 1번 나왔다...
//		javastudy.designpattern.singleton.SimpleSingleton@6e0d1c57
//		javastudy.designpattern.singleton.SimpleSingleton@78426c1f ← singleton 으로 구현했음에도 불구하고 다른 instance 임을 볼 수 있다.
//		javastudy.designpattern.singleton.SimpleSingleton@6e0d1c57
	}

	private static void singletonClassEqualTest() {
		SimpleSingleton simple1 = SimpleSingleton.getInstance();
		SimpleSingleton simple2 = SimpleSingleton.getInstance();
		System.out.println("simple1 == simple2 : " + (simple1 == simple2)); // true, if not in multi-threading
	}

	static class SingletonThread extends Thread{
		public void printSingletonClass() {
			SimpleSingleton instance = SimpleSingleton.getInstance();
			System.out.println(instance);
		}

		@Override
		public void run() {
			printSingletonClass();
		}
	}
}
