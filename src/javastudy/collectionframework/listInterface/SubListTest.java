package javastudy.collectionframework.listInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubListTest {
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println("=== subList Test ===");
		System.out.println("list1 = " + list1);
		list1.subList(1, 3).clear();
		System.out.println("list1 = " + list1);
		/**
		 * === subList Test ===
		 * list1 = [1, 2, 3, 4, 5]
		 * list1 = [1, 4, 5]
		 */


		System.out.println("=== subList modify Test ===");
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		List<Integer> subListOfList = list.subList(3, 7);
		System.out.println("list = " + list);
		System.out.println("subListOfList = " + subListOfList);
		list.set(4, 1000);
		System.out.println(" << modify list's index 4th (5=>1000) >> ");
		System.out.println("list = " + list);
		System.out.println("subListOfList = " + subListOfList);
		/**
		 * === subList modify Test ===
		 * list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
		 * subListOfList = [4, 5, 6, 7]
		 *  << modify list's index 4th (5=>1000) >>
		 * list = [1, 2, 3, 4, 1000, 6, 7, 8, 9, 10]
		 * subListOfList = [4, 1000, 6, 7]
		 */


		System.out.println("=== subList indexOf Test ===");
		list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3, 2, 1, 0, 5, 7, 2, 4, 10));
		System.out.println("list1 = " + list1);
		int indexOf2_in3to6 = list1.subList(3, 6).indexOf(2);
		System.out.println("indexOf2_in3to6 = " + indexOf2_in3to6);
		/**
		 * subList(3, 6) 에서의 index 위치를 반환하기에 2를 반환.
		 * === subList indexOf Test ===
		 * list1 = [1, 2, 3, 4, 3, 2, 1, 0, 5, 7, 2, 4, 10]
		 * indexOf2_in3to6 = 2
		 */


		System.out.println("=== subList reference Test ===");
		List<Integer> deck = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		List<Integer> subList = deck.subList(3, 7);
		System.out.println("deck = " + deck);
		System.out.println("subList = " + subList);
		subList.clear();
		System.out.println("After clearing the subList : deck = " + deck);
		subList.add(1000);
		System.out.println("After add the subList : deck = " + deck);
		/**
		 * === subList reference Test ===
		 * deck = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
		 * subList = [4, 5, 6, 7]
		 * After clearing the subList : deck = [1, 2, 3, 8, 9, 10]
		 * After add the subList : deck = [1, 2, 3, 1000, 8, 9, 10]
		 */


		System.out.println("=== subList adding Test ===");
		List<Integer> listA = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		List<Integer> subListA = listA.subList(3, 7);
		System.out.println("listA = " + listA);
		System.out.println("subListA = " + subListA);
//		listA.add(1000); // 에러 발생 원인
		System.out.println("listA = " + listA);
		System.out.println("subListA = " + subListA); // 여기서 에러 발생!
		/**
		 * The semantics of the List returned by subList become undefined
		 * if elements are added to or removed from the backing List in any way other than via the returned List.
		 */
		/**
		 * Exception in thread "main" java.util.ConcurrentModificationException
		 * 	at java.base/java.util.ArrayList$SubList.checkForComodification(ArrayList.java:1445)
		 * 	at java.base/java.util.ArrayList$SubList.listIterator(ArrayList.java:1314)
		 * 	at java.base/java.util.AbstractList.listIterator(AbstractList.java:311)
		 * 	at java.base/java.util.ArrayList$SubList.iterator(ArrayList.java:1310)
		 * 	at java.base/java.util.AbstractCollection.toString(AbstractCollection.java:465)
		 * 	at java.base/java.lang.String.valueOf(String.java:2951)
		 * 	at javastudy.collectionframework.listInterface.SubListTest.main(SubListTest.java:59)
		 */


		System.out.println("=== subList of subList Test ===");
		List<Integer> listC = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
		List<Integer> subListOfListC = listC.subList(3, 10);
		List<Integer> subListOfSubListOfListC = subListOfListC.subList(2, 6);
		System.out.println("listC = " + listC);
		System.out.println("subListOfListC = " + subListOfListC);
		System.out.println("subListOfSubListOfListC = " + subListOfSubListOfListC);
		System.out.println(" << add 3000 in subList of subList of listC...>> ");
		subListOfSubListOfListC.add(3000);
		System.out.println("listC = " + listC);
		System.out.println("subListOfListC = " + subListOfListC);
		System.out.println("subListOfSubListOfListC = " + subListOfSubListOfListC);
	}
}
