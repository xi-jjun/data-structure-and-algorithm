package javastudy.designpattern.singleton.brokepattern;

import java.io.Serializable;

/**
 * Singleton 부수기 테스트 singleton class
 */
public class SingletonClass implements Serializable {
	private SingletonClass() {}

	private static class Holder {
		private static final SingletonClass INSTANCE = new SingletonClass();
	}

	public static SingletonClass getInstance() {
		return SingletonClass.Holder.INSTANCE;
	}
}
