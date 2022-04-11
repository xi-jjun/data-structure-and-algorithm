package javastudy.designpattern.singleton.innerclass;

public class StaticInnerClassSingleton {
	private StaticInnerClassSingleton() {}

	/**
	 * 장점
	 * 1. multi-threading 환경에서도 안전하다.
	 * 		왜? : JVM 에서는 class loading 할 때 안전한 동기화 환경을 제공하기 때문이다.
	 * 		따라서 Holder 라는 inner class 가 loading 될 때, 그 과정 자체가 JVM 에서 동기화 환경을 제공하니깐 thread-safe 하게
	 * 		INSTANCE 를 생성할 수 있는 것.
	 * 2. Lazy loading 이 가능하다.
	 * 		StaticInnerClassSingleton class 가 로딩되는 시점이 아닌, getInstance() method 를 호출할 때에
	 * 		INSTANCE 객체가 생성되므로 우리가 원하는 시점에 생성핳 수 있다는 장점이 있다.
	 *
	 * Double Checked Locking 과 같이 복잡한 이론이나, 코드가 필요없이 간단하게 만들 수 있다.
	 */

	// 2. Holder class loading.
	private static class Holder {
		// 3. class loading 되면서 static field 가 초기화 되는데 그 때 JVM 에서 동기화 환경을 제공한다.
		// 		→ 따라서 thread-safe 하다고 할 수 있다.
		private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
	}

	public StaticInnerClassSingleton getInstance() {
		return Holder.INSTANCE; // 1. Holder class 호출.
	}
}
