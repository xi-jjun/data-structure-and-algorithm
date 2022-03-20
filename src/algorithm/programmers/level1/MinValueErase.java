package algorithm.programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinValueErase {
	public static void main(String[] args) {
		int[] arr = new int[]{4, 3, 2, 1};
		Integer[] answer = solution(arr);
		for (Integer integer : answer) {
			System.out.print(integer + " ");
		}
		System.out.println();

		int[] answer2 = solution2(arr);
		for (int i : answer2) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static int[] solution2(int[] arr) {
		if (arr.length == 1) return new int[]{-1};
		int min = Arrays.stream(arr).min().getAsInt();
		return Arrays.stream(arr).filter(e -> e != min).toArray();
	}

	public static Integer[] solution(int[] arr) {
		List<Integer> answer = new ArrayList<>();
		if (arr.length == 1) return new Integer[]{-1};
		int index = -1;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
				index = i;
			}
		}

		for (int data : arr) {
			if (data == min) continue;
			answer.add(data);
		}

		return answer.toArray(new Integer[0]);
	}
}
