package javastudy.collectionframework.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {
	public static void main(String[] args) {
		List<Person> people = new ArrayList<>();
		people.add(new Person(2341, "AAA"));
		people.add(new Person(51, "Java"));
		people.add(new Person(624642, "C++"));
		people.add(new Person(43435, "C lang"));
		people.add(new Person(43435, "CSS"));
		people.add(new Person(8753, "OOP"));
		people.add(new Person(34156, "ELSE"));
		people.add(new Person(4636, "IFIFIF"));

		Comparator<Person> comp = new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.id - o2.id;
			}
		};
		showPeople(people);
		Collections.sort(people, comp);
		System.out.println("=== After Sorting ===");
		showPeople(people);
	}

	public static void showPeople(List<Person> people) {
		for (Person person : people) {
			System.out.println("person = " + person);
		}
	}

	static class Person {
		int id;
		String name;

		public Person(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			return "Person{" +
					"id=" + id +
					", name='" + name + '\'' +
					'}';
		}
	}
}
