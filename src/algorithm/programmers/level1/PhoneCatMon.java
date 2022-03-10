package algorithm.programmers.level1;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PhoneCatMon {
	public static void main(String[] args) {
		Integer[] nums = new Integer[]{3, 3, 3, 2, 2, 4};
//		int answer = solution2(nums);
//		System.out.println("answer = " + answer);
		double pow = Math.pow(3, 16);
		System.out.println("pow = " + (pow / 1000000));
	}

	public static int solution(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}

		return Math.min(set.size(), nums.length / 2);
	}

	/**
	 * stream 연습
	 */
	public static int solution2(Integer[] nums) {
//		Set<Integer> set = new HashSet<>();
//		Collections.addAll(set, nums); // int array 말고 Integer array 가 와야 한다.

		Set<Integer> set = Arrays.stream(nums).collect(Collectors.toSet());

		return Math.min(set.size(), nums.length / 2);
	}
}
