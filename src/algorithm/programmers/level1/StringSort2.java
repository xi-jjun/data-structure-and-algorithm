package algorithm.programmers.level1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringSort2 {
	public static void main(String[] args) {
		int n = 1;
		String str = "Zbacdefg";
		String answer = solution(str);
		System.out.println("answer = " + answer);

	}

	public static String solution(String s) {
		// A-Z 65-90
		// a-z 97-122
		// 대문자는 소문자보다 작은 것으로 간주. 큰 것부터 정렬
//		List<String> string = Arrays.stream(s.split("")).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
//		return string.stream().map(b -> String.valueOf(b)).collect(Collectors.joining());

		return Stream.of(s.split(""))
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.joining());
	}
}
