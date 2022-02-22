package javastudy.collectionframework.listInterface;

import java.util.ArrayList;
import java.util.List;

public class RemoveTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(245);
		list.add(5);
		list.add(3);

		System.out.println(list);
		System.out.println("remove element : " + list.remove(1));

		System.out.println("=== After remove ===");
		System.out.println(list);
	}
}
