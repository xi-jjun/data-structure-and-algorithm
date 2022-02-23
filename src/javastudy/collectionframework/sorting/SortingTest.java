package javastudy.collectionframework.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingTest {
	public static void main(String[] args) {
		System.out.println("=== Is String implements of Comparable Test ===");
		Comparable<String> str = new String("Hello");
		System.out.println("str = " + str);
		for (String s : ((String) str).split("")) {
			System.out.print(s + " ");
		}
		/**
		 * Comparable 에는 split method 를 지원하지 않기 때문에 String 으로
		 * down casting 하고 써야 했다.
		 */


		System.out.println("=== Class Cast Exception Test ===");
		List<Person> people = new ArrayList<>();
		people.add(new Person("Hello", 124903L));
		people.add(new Person("Java", 2424L));
		people.add(new Person("JPA", 1122L));
		Collections.sort(people);
	}

	static class Person implements Comparable<Person> {
		static int SERIAL = 0;
		String name;
		int seq;
		Long id;

		public Person(String name, Long id) {
			this.name = name;
			this.id = id;
			seq = (++SERIAL);
		}

		@Override
		public int compareTo(Person o) {
			return o.seq;
		}
	}
}
