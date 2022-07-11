package javastudy.designpattern.singleton.brokepattern.serializationsolution;

import java.io.Serializable;

public class SerializationSolutionSingleton implements Serializable {
	private SerializationSolutionSingleton() {}

	private static class Holder {
		private static final SerializationSolutionSingleton INSTANCE = new SerializationSolutionSingleton();
	}

	public static SerializationSolutionSingleton getInstance() {
		return SerializationSolutionSingleton.Holder.INSTANCE;
	}

	/**
	 * 해당 method 를 정의해주게 되면, deserialization 할 때에 읽어서 instance 를 새로 생성하는게 아니라
	 * getInstance() 로 주기 때문에 같은 instance 를 반환받을 수 있게 된다.
	 */
	protected Object readResolve() {
		return getInstance();
	}
}
