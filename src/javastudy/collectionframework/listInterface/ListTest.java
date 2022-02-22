package javastudy.collectionframework.listInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListTest {
	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<>();
		List<Integer> linkedList = new LinkedList<>();
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(1);
		arrayList.add(4);
		arrayList.add(3);
		arrayList.add(7);
		linkedList.add(7);
		linkedList.add(3);
		linkedList.add(1);
		linkedList.add(4);
		linkedList.add(3);
		linkedList.add(7);

		System.out.println("arrayList get(index = 2) : " + arrayList.get(2));
		System.out.println("arrayList indexOf(3) : " + arrayList.indexOf(3));
		System.out.println("arrayList lastIndexOf(3) : " + arrayList.lastIndexOf(3));

		System.out.print("ArrayList : (By ListIterator) ");
		ListIterator<Integer> integerListIterator = arrayList.listIterator();
		while (integerListIterator.hasNext()) System.out.print(integerListIterator.next() + " ");
		/**
		 * arrayList get(index = 2) : 1
		 * arrayList indexOf(3) : 1
		 * arrayList lastIndexOf(3) : 4
		 * ArrayList : (By ListIterator) 2 3 1 4 3 7
		 */

		System.out.println("\n==== ==== ====");



		System.out.print("SubList of ArrayList [1]~[2] : ");
		List<Integer> subList = arrayList.subList(1, 3);
		for (Integer sub : subList) {
			System.out.print(sub + " ");
		}
		/**
		 * SubList of ArrayList [1]~[2] : 3 1
		 */
		System.out.println();



		System.out.println("=== List Equals Test ===");
		List<Integer> list1 = new ArrayList<>(Arrays.asList(41, 29, 37, 45, 5));
		List<Integer> list2 = new ArrayList<>(Arrays.asList(41, 29, 37, 45, 5));
		System.out.println("list 1 : " + list1);
		System.out.println("list 2 : " + list2);
		System.out.println("Is equals? : " + (list1.equals(list2)));
		/**
		 * === List Equals Test ===
		 * list 1 : [41, 29, 37, 45, 5]
		 * list 2 : [41, 29, 37, 45, 5]
		 * Is equals? : true
		 */


		System.out.println("=== List set Test ===");
		List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 9, 4, 5));
		System.out.println("list3 = " + list3);
		Integer setValue = list3.set(2, 1000); // return old value of index 2
		System.out.println("setValue = " + setValue);
		System.out.println("list3 = " + list3);
		/**
		 * === List set Test ===
		 * list3 = [1, 2, 9, 4, 5]
		 * setValue = 9
		 * list3 = [1, 2, 1000, 4, 5]
		 */


		System.out.println("=== Arrays.asList Test ===");
		List<Integer> asList = Arrays.asList(1, 2, 3 ,4);
//		asList.add(3000); // 오류 발생.
		System.out.println("asList = " + asList);
		/**
		 * === Arrays.asList Test ===
		 * Exception in thread "main" java.lang.UnsupportedOperationException
		 * 	at java.base/java.util.AbstractList.add(AbstractList.java:153)
		 * 	at java.base/java.util.AbstractList.add(AbstractList.java:111)
		 * 	at javastudy.collectionframework.listInterface.ListTest.main(ListTest.java:85)
		 */
	}
}
