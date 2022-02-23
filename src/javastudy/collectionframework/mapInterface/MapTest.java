package javastudy.collectionframework.mapInterface;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		map.put("D", 4);
		Map<String, Integer> map2 = new LinkedHashMap<>();
		map2.put("A", 1);
		map2.put("D", 4);
		map2.put("B", 2);
		map2.put("C", 3);

		System.out.println("=== Map Equals Test ===");
		System.out.println("map = " + map);
		System.out.println("map2 = " + map2);
		System.out.println("is equals? : " + map.equals(map2));
		/**
		 * 같은 key-value pair 구성을 가졌다면 True.
		 */


		System.out.println("\n=== Map putAll Test ===");
		Map<String, Integer> mapA = new HashMap<>();
		mapA.put("A", 340); mapA.put("c", 564); mapA.put("t", 31);
		Map<String, Integer> newMap = new HashMap<>(mapA);
		System.out.println("mapA = " + mapA);
		System.out.println("map2 = " + map2);
		System.out.println("newMap = " + newMap);
		System.out.println(" << add map2 in mapA >>  ");
		newMap.putAll(map2); // << add map2 in mapA >>
		System.out.println("newMap = " + newMap);
		/**
		 * === Map putAll Test ===
		 * mapA = {A=340, c=564, t=31}
		 * map2 = {A=1, D=4, B=2, C=3}
		 * newMap = {A=340, c=564, t=31}
		 *  << add map2 in mapA >>
		 * newMap = {A=1, B=2, c=564, C=3, t=31, D=4}
		 */
	}
}
