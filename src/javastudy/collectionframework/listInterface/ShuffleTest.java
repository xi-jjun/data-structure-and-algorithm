package javastudy.collectionframework.listInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println("list = " + list);
		Collections.shuffle(list);
		System.out.println("list = " + list);

		/**
		 * Arrays.asList 는 Array 를 List 로 보여주는 것이다.
		 * > The Arrays class has a static factory method called asList, which allows an array to be viewed as a List.
		 *
		 * 일반적인 List impl 과는 다르다. Array 에는 add, remove 동작이 없기 때문.
		 * > This method does not copy the array. Changes in the List write through to the array and vice versa.
		 * > The resulting List is not a general-purpose List implementation,
		 * > because it doesn't implement the (optional) add and remove operations
		 */
		List<Integer> asList = Arrays.asList(1, 2, 3, 4, 5);
		System.out.println("asList = " + asList);
		Collections.shuffle(asList, new Random());
		System.out.println("asList = " + asList);
	}
}
