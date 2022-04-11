package javastudy.designpattern.singleton;

public class SimpleSingleton {
	private static SimpleSingleton instance; // SimpleSingleton class 에서 instance 를 미리 안만들기로 함.

	private SimpleSingleton() {}

	/**
	 * 다음과 같은 singleton method 는 문제가 있다. 바로 thread-safe 하지 않다는 것!!!
	 * 1. thread1, thread2 가 if 에 동시에 접근하고, 현재 instance field 는 null 이기에 둘 다 접근!!
	 * 2. thread1 이 new keyword 로 SimpleSingleton class 생성. 이제 instance field 는 null 이 아니다
	 * 3. thread2 도 new keyword 로 SimpleSingleton class 생성. 이제 ...? 아까 instance 는 null 아니었는데...?
	 * '이미 if 안에 들어온 시점에서 instance == null' 이었기에 thread2 도 instance 를 생성하게 된 것.
	 */
	public static SimpleSingleton getInstance() {
		if (instance == null) {
			instance = new SimpleSingleton();
		}
		return instance;
	}
}
