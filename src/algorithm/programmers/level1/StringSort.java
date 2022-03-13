package algorithm.programmers.level1;

import java.util.Arrays;
import java.util.Comparator;

public class StringSort {
	public static void main(String[] args) {
		int n = 1;
		String[] arr = new String[]{"sun", "bed", "car"};
		String[] answer = solution(arr, n);
		for (String s : answer) {
			System.out.println(s);
		}
	}

	public static String[] solution(String[] strings, int n) {
		// A-Z 65-90
		// a-z 97-122
		// 대문자는 소문자보다 작은 것으로 간주. 큰 것부터 정렬
		Arrays.sort(strings, (o1, o2) -> {
			if (o1.charAt(n) == o2.charAt(n)) return o1.compareTo(o2);
			else return o1.charAt(n) - o2.charAt(n);
		});
		return strings;
	}
}
