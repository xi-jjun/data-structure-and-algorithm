package javastudy.collectionframework.listInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListAlgorithmTest {
	public static void main(String[] args) {
		System.out.println("=== Collections.fill Test ===");
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		System.out.println("list = " + list);
		Collections.fill(list, 100);
		System.out.println("list = " + list);
		/**
		 * === Collections.fill Test ===
		 * list = [1, 2, 3, 4, 5, 6, 7]
		 * list = [100, 100, 100, 100, 100, 100, 100]
		 */


		System.out.println("\n=== Collections.copy Test ===");
		List<Integer> listA = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		List<Integer> srcList = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
		System.out.println("listA = " + listA);
		System.out.println("srcList = " + srcList);
		Collections.copy(listA, srcList);
		System.out.println("listA = " + listA);
		System.out.println("srcList = " + srcList);
		/**
		 * === Collections.copy Test ===
		 * listA = [1, 2, 3, 4, 5, 6, 7]
		 * srcList = [10, 20, 30, 40, 50]
		 * listA = [10, 20, 30, 40, 50, 6, 7]
		 * srcList = [10, 20, 30, 40, 50]
		 */


		System.out.println("\n=== Collections.binarySearch Test ===");
		List<Integer> orderedList = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 6, 8, 9, 20, 30, 39, 45, 90, 100, 121, 140));
		int findOne = Collections.binarySearch(orderedList, 5);
		System.out.println("findOne = " + findOne);
		/**
		 * 탐색한 값의 index 반환
		 * === Collections.binarySearch Test ===
		 * findOne = 3
		 */


		System.out.println("=== Collections.indexOfSubList Test ===");
		List<Integer> src = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		List<Integer> targetSubList = new ArrayList<>(Arrays.asList(4, 5, 6));
		int indexOfSubList = Collections.indexOfSubList(src, targetSubList);
		System.out.println("indexOfSubList = " + indexOfSubList);
		/**
		 * === Collections.indexOfSubList Test ===
		 * indexOfSubList = 3
		 */
	}
}
