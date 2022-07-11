package javastudy.designpattern.singleton.brokepattern;

import javastudy.designpattern.singleton.innerclass.StaticInnerClassSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 만약에 제공하는 사람은 singleton 으로 잘 제공했는데 쓰는 사람이 이상하게 쓴다면...?
 * singleton 을 깨트리는 방법에 대해 알아보자
 */
public class ReflectionConstructor {
	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		SingletonClass singleton = SingletonClass.getInstance();

		Constructor<SingletonClass> constructor = SingletonClass.class.getDeclaredConstructor();
		constructor.setAccessible(true); // private constructor 에 접근 true 하게 만들기
		SingletonClass instance = constructor.newInstance();

		System.out.println("singleton == reflection instance : " + (singleton == instance));
		// singleton == reflection instance : false
	}
}
