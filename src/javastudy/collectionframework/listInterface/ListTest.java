package javastudy.collectionframework.listInterface;

import java.util.ArrayList;
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

		System.out.print("ArrayList : (By ListIterator)");
		ListIterator<Integer> integerListIterator = arrayList.listIterator();
		while (integerListIterator.hasNext()) System.out.print(integerListIterator.next() + " ");

		System.out.println("\n==== ==== ====");

		System.out.print("SubList of ArrayList [1]~[2] : ");
		List<Integer> subList = arrayList.subList(1, 3);
		for (Integer sub : subList) {
			System.out.print(sub + " ");
		}
		System.out.println();
	}
}
