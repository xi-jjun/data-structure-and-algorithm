package javastudy.designpattern.singleton.eager;

public class EagerSingleton {
	/**
	 * 동기화 keyword 를 사용하는건 성능적으로 안좋을 것 같아서 multi-thread 환경에서도 안전한 '미리선언하는 방법' 을 사용.
	 * INSTANCE 는 class loading 할 때에 초기화되어 미리 생성된다.
	 * 이 방법의 단점은 '미리 생성' 한다는 것이다.
	 * 만약 EagerSingleton class 가 생성하는데 많은 비용이 든다면,
	 * 필요하지도 않을 때 큰 비용을 들여가며 instance 를 생성할 필요가 있는지 생각을 해봐야 한다.
	 */
	private static final EagerSingleton INSTANCE = new EagerSingleton(); // EagerSingleton class 에서 instance 를 미리 만들기로 함.

	private EagerSingleton() {}

	public static EagerSingleton getInstance() {
		return INSTANCE;
	}
}
