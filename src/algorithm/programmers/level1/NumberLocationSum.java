package algorithm.programmers.level1;

import java.util.Arrays;

public class NumberLocationSum {
	public static void main(String[] args) {
		int n = 1244;

	}

	public static int solution(int n) {
		int answer = 0;

		String nums = n + "";
		for (String s : nums.split("")) {
			answer += Integer.parseInt(s);
		}

		return answer;
	}
}
