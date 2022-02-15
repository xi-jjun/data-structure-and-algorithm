//package javastudy.jvm.classloader;
//package classloader;
/**
 * https://tecoble.techcourse.co.kr/post/2021-07-15-jvm-classloader/
 * java -verbose:class Main 명령어 테스트를 위한 코드
 *
 * java -verbose:class ClassLoaderTest
 */

import java.util.LinkedList;
import java.util.List;

public class ClassLoaderTest {
	public static void main(String[] args) {
		UserClass userClass = new UserClass(100);
		int number = 1;
		List<Integer> numbers = new LinkedList<>();

		System.out.println("Application ClassLoader: " + UserClass.class.getClassLoader());
		System.out.println("Extension ClassLoader: " + UserClass.class.getClassLoader().getParent());
		System.out.println("Bootstrap ClassLoader: " + UserClass.class.getClassLoader().getParent().getParent());
		/**
		 * java zulu-8.jdk 에서 실행시킨 결과
		 *
		 * Application ClassLoader: sun.misc.Launcher$AppClassLoader@4e0e2f2a
		 * Extension ClassLoader: sun.misc.Launcher$ExtClassLoader@2a139a55
		 * Bootstrap ClassLoader: null
		 */
	}
}

/**
 * https://tecoble.techcourse.co.kr/post/2021-07-15-jvm-classloader/
 * 를 참고하여 Class Loader 테스트를 위한 코드입니다.
 */
class UserClass {
	private int num;

	public UserClass(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}
}
