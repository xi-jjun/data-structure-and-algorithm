package algorithm.programmers.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntegerDescend {
	public static void main(String[] args) {
		long n = 4955395;
		long answer = solution(n);
		System.out.println("answer = " + answer);
	}

	public static long solution(long n) {
		long answer = 0;
		List<Integer> numbers = new ArrayList<>();
		while (n != 0) {
			numbers.add((int) (n % 10));
			n /= 10;
		}
		Collections.sort(numbers);
		int seq = 0;
		for (Integer number : numbers) {
			answer += (long) number * (int) Math.pow(10, seq++);
		}
		return answer;
	}
}
