package javastudy.collectionframework.setInterface;

import java.util.HashSet;
import java.util.Set;

public class BulkOperationTest {
	public static void main(String[] args) {
		Set<Integer> s1 = new HashSet<>();
		Set<Integer> s2 = new HashSet<>();
		addingElement(s1, s2);
		System.out.println("=== s1, s2 ===");
		System.out.println("s1 : " + s1);
		System.out.println("s2 : " + s2);

		/**
		 * s1.addAll(s2)
		 * => S1 = S1 ∪ S2
		 */
		System.out.println("=== addAll Test ===");
		System.out.println("s1 : " + s1);
		s1.addAll(s2);
		System.out.println("after s1.addAll(s2) s1 : " + s1);

		s1 = new HashSet<>();
		s2 = new HashSet<>();
		addingElement(s1, s2);

		/**
		 * s1.retainAll(s2)
		 * => S1 = S1 ∩ S2
		 */
		System.out.println("=== retainAll Test ===");
		System.out.println("s1 : " + s1);
		s1.retainAll(s2);
		System.out.println("after s1.retainAll(s2) s1 : " + s1);

		s1 = new HashSet<>();
		s2 = new HashSet<>();
		addingElement(s1, s2);

		/**
		 * s1.removeAll(s2)
		 * => S1 = S1 - S2
		 */
		System.out.println("=== removeAll Test ===");
		System.out.println("s1 : " + s1);
		s1.removeAll(s2);
		System.out.println("after s1.remove(s2) s1 : " + s1);
	}

	private static void addingElement(Set<Integer> s1, Set<Integer> s2) {
		s1.add(1);
		s1.add(2);
		s1.add(-1);
		s1.add(-2);
		s1.add(3);
		s1.add(4);
		s1.add(5);
		s1.add(6);

		s2.add(1);
		s2.add(2);
		s2.add(7);
		s2.add(8);
		s2.add(9);
		s2.add(10);
		s2.add(100);
	}
}
