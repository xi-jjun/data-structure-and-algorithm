package javastudy.collectionframework.listInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVsLinkedList {
	static final int DATA_NUM = 100000;

	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<>();
		List<Integer> linkedList = new LinkedList<>();

		System.out.println("Data Sequence Adding Time (ns)");
		long arrayListAddingTime = addListElement(arrayList);
		long linkedListAddingTime = addListElement(linkedList);
		System.out.println("arrayListAddingTime = " + arrayListAddingTime);
		System.out.println("linkedListAddingTime = " + linkedListAddingTime);

		reset(arrayList, linkedList);
		System.out.println("=== === === === ===");

		System.out.println("Data UnSequence Adding Time (ns)");
		long arrayListAddingAtZeroIndexTime = addListElementAtZero(arrayList);
		long linkedListAddingAtZeroIndexTime = addListElementAtZero(linkedList);
		System.out.println("arrayListAddingAtZeroIndexTime = " + arrayListAddingAtZeroIndexTime);
		System.out.println("linkedListAddingAtZeroIndexTime = " + linkedListAddingAtZeroIndexTime);


		System.out.println("=== === === === ===");
		System.out.println("Data Select");
		long arrayListSelectAll = selectAll(arrayList);
		long linkedListSelectAll = selectAll(linkedList);
		System.out.println("arrayListSelectAll = " + arrayListSelectAll);
		System.out.println("linkedListSelectAll = " + linkedListSelectAll);
	}

	private static long selectAll(List<Integer> list) {
		long start = System.nanoTime();
		for (int i = 0; i < DATA_NUM; i++) {
			list.get(i);
		}
		long end = System.nanoTime();
		return end - start;
	}

	private static void reset(List<Integer> arrayList, List<Integer> linkedList) {
		arrayList.clear();
		linkedList.clear();
	}

	private static long addListElement(List<Integer> list) {
		long start = System.nanoTime();
		for (int i = 0; i < DATA_NUM; i++) {
			list.add(i);
		}
		long end = System.nanoTime();
		return end - start;
	}

	private static long addListElementAtZero(List<Integer> list) {
		int addedIndex = 0;
		long start = System.nanoTime();
		for (int i = 0; i < DATA_NUM; i++) {
			list.add(addedIndex, i);
		}
		long end = System.nanoTime();
		return end - start;
	}
}
