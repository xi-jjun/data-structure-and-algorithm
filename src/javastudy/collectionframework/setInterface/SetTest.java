package javastudy.collectionframework.setInterface;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetTest {
	public static void main(String[] args) {
		Set<Integer> hashSet = new HashSet<>();
		hashSet.add(1);
		hashSet.add(3);
		hashSet.add(1);
		hashSet.add(2);
		hashSet.add(-4);
		hashSet.add(10);
		hashSet.add(0);
		hashSet.add(-1);
		for (Integer hashValue : hashSet) {
			System.out.print(hashValue + " ");
		}

		/**
		 * 같은 정보를 넣어줬으나 HashSet 은 순서를 고려하지 않기 때문에 다른 순서로 출력된 것을 볼 수 있다.
		 */
		System.out.println("\n== == == ==");
		LinkedHashSet<Object> linkedHashSet = new LinkedHashSet<>();
		linkedHashSet.add(1);
		linkedHashSet.add(3);
		linkedHashSet.add(1);
		linkedHashSet.add(2);
		linkedHashSet.add(-4);
		linkedHashSet.add(10);
		linkedHashSet.add(0);
		linkedHashSet.add(-1);
		for (Object value : linkedHashSet) {
			System.out.print(value + " ");
		}

		/**
		 * "같은 element 를 포함하는 Set 은 동일하다"
		 * 라는 말을 확인하기 위한 코드. equals method 로 확인 => 동일하다.
		 */
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		set1.add(1);
		set1.add(3);
		set1.add(2);
		set1.add(10);

		set2.add(1);
		set2.add(3);
		set2.add(2);
		set2.add(10);

		System.out.println(set1);
		System.out.println(set2);
		System.out.println(set1.equals(set2));
	}
}
