package algorithm.programmers.level2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Tuple {
	public static void main(String[] args) {
		String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		Integer[] answer = solution(s);
		for (int ans : answer) {
			System.out.println("ans = " + ans);
		}
	}

	public static Integer[] solution(String s) {
		int index = 0;
		List<List<Integer>> list = new ArrayList<>();
		char[] chars = s.toCharArray();
		for (int i = 1; i < chars.length - 1; i++) {
			if (chars[i] == '{') {
				int start = i;
				list.add(new ArrayList<>());
				while (chars[++i] != '}') {
				}
				StringBuilder sb = new StringBuilder();
				for (int j = start + 1; j < i; j++) {
					sb.append(chars[j]);
				}

				String[] added = sb.toString().split(",");
				for (String data : added) {
					list.get(index).add(Integer.parseInt(data));
				}
				index++;
			}
		}

		list.sort(Comparator.comparingInt(List::size));

		Set<Integer> set = new LinkedHashSet<>();
		for (List<Integer> integerList : list) {
			set.addAll(integerList);
		}

		return set.toArray(new Integer[0]);
	}
}
