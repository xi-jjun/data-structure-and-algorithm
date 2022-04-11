package javastudy.designpattern.singleton.sync;

public class SynchronizedSingleton {
	private static SynchronizedSingleton instance;

	private SynchronizedSingleton() {}

	/**
	 * multi-threading 환경에서도 하나의 instance 만 생성됨을 보장할 수 있다.
	 * 그러나 getInstance method 를 호출할 때 마다 동기화 처리하는 작업 때문에 성능에 영향을 미칠 수 있다.
	 */
	public static synchronized SynchronizedSingleton getInstance() {
		if (instance == null) {
			instance = new SynchronizedSingleton();
		}
		return instance;
	}
}
