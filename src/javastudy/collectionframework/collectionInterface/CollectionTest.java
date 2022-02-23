package javastudy.collectionframework.collectionInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionTest {
	public static void main(String[] args) {
		/**
		 * 오류 나는 코드
		 * 이유 : Arrays 내부의 ArrayList static class 는 iterator remove 를 지원하지 않는다.
		 * Collection<String> collection = Arrays.asList("Hello", "Java", "Collections", "Frameworks");
		 * List<String> list = new ArrayList<>(collection);
		 *
		 * for (String element : list) {
		 * 		System.out.print(element + " ");
		 * }
		 */

		Collection<String> collection = new ArrayList<>(Arrays.asList("Hello", "Java", "Collections", "Frameworks"));
		List<String> list = new ArrayList<>(collection);

		for (String element : list) {
			System.out.print(element + " ");
		}
	}
}
