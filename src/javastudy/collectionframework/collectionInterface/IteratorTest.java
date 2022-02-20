package javastudy.collectionframework.collectionInterface;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class IteratorTest {
	public static void main(String[] args) {
		/**
		 * < 오류났던 코드 >
		 * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		 *
		 * 이유 : Arrays 내부의 ArrayList static class 는 iterator remove method 를 지원하지 않는다.
		 *
		 * === 출력 결과 ===
		 * 1
		 * 삭제할 element : 2
		 * Exception in thread "main" java.lang.UnsupportedOperationException: remove
		 * 	at java.base/java.util.Iterator.remove(Iterator.java:102)
		 * 	at javastudy.collectionframework.collectionInterface.IteratorTest.main(IteratorTest.java:23)
		 */
		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		Iterator<Integer> iterator = numbers.iterator();

		while (iterator.hasNext()) {
			Integer next = iterator.next();
			if (next == 2) {
				System.out.println("\n삭제할 element : " + next);
				iterator.remove(); // delete
				break;
			}
			System.out.print(next + " ");
		}

		System.out.println("\n=== 나머지 출력 ===");
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}

		/**
		 * iterator 에 담은 list 가 수정이 됐음을 알 수 있다.
		 *
		 * 수정된 이유 : ArrayList 의 iterator 구현체의 remove method 를 확인해보면,
		 * ArrayList.this.remove(lastRet);
		 * 라는 코드가 있다. 따라서 현재 instance 의 remove method 에 접근하여 직접 지우기에
		 * 수정이 될 수 밖에 없다.
		 */
		System.out.println("\n=== 원래 리스트 출력 ===");
		for (Integer number : numbers) {
			System.out.print(number + " ");
		}
	}
}
