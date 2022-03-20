package algorithm.programmers.level1;

import java.util.Arrays;

public class Average {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4};
		double answer = solution(arr);
		System.out.println("answer = " + answer);
	}

	public static double solution(int[] arr) {
		return (double) Arrays.stream(arr).sum() / arr.length;
	}
}
