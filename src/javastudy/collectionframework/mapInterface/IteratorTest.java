package javastudy.collectionframework.mapInterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IteratorTest {
	public static void main(String[] args) {
		System.out.println("=== Map Collection view remove Test ===");
		Map<String, Integer> mapA = new HashMap<>();
		mapA.put("A", 1);
		mapA.put("H", 2);
		mapA.put("E", 3);
		mapA.put("G", 4);
		mapA.put("F", 5);
		System.out.println("mapA = " + mapA);

		Iterator<Map.Entry<String, Integer>> entryIterator = mapA.entrySet().iterator();
		while (entryIterator.hasNext()) {
			System.out.println(entryIterator.next());
			entryIterator.remove();
		}

		System.out.println("mapA = " + mapA);
		/**
		 * === Map Collection view remove Test ===
		 * mapA = {A=1, E=3, F=5, G=4, H=2}
		 * A=1
		 * E=3
		 * F=5
		 * G=4
		 * H=2
		 * mapA = {}
		 */

		mapA.clear();

		System.out.println("=== Map EntrySet view remove Test ===");
		mapA = new HashMap<>();
		mapA.put("A", 1);
		mapA.put("H", 2);
		mapA.put("E", 3);
		mapA.put("G", 4);
		mapA.put("F", 5);
		System.out.println("mapA = " + mapA);

		Set<Map.Entry<String, Integer>> entries = mapA.entrySet();
		for (Map.Entry<String, Integer> entry : entries) {
			entry.setValue(100);
		}
		System.out.println("mapA = " + mapA);
	}
}
