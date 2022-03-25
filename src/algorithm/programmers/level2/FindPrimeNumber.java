package algorithm.programmers.level2;

import java.util.HashSet;
import java.util.Set;

// 소수찾기
public class FindPrimeNumber {
	static char[] data = new char[7];
	static char[] nums;
	static int answer = 0;
	static boolean[] isVisited = new boolean[7];
	static Set<Integer> isCalculated = new HashSet<>();

	public static void main(String[] args) {
		String numbers = "011";
		int answer = solution(numbers);
		System.out.println("answer = " + answer);
	}

	public static int solution(String numbers) {
		nums = numbers.toCharArray();
		for (int i = 1; i <= numbers.length(); i++) {
			backTracking(0, i);
		}
		return answer;
	}

	public static void backTracking(int depth, int limit) {
		if (depth == limit) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < limit; i++) {
				sb.append(data[i]);
			}
			int number = Integer.parseInt(sb.toString());
			if (isPrime(number) && isCalculated.add(number)) answer++;
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				data[depth] = nums[i];
				backTracking(depth + 1, limit);
				isVisited[i] = false;
			}
		}
	}

	private static boolean isPrime(int num) {
		if (num <= 1) return false;
		if (num == 2) return true;
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) return false;
		}
		return true;
	}
}
