package algorithm.programmers.level1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PickAdd {
	static final int N = 2;
	static int[] results = new int[2];
	static Set<Integer> answers = new TreeSet<>();
	static int[] arr;
	static boolean[] isVisited;

	public static void main(String[] args) {
		int[] nums = new int[]{2, 1, 3, 4, 1};
		PickAdd main = new PickAdd();
		int[] answers = main.solution(nums);
//		System.out.println(answers);
		for (int answer : answers) {
			System.out.print(answer + " ");
		}
	}

	public static void backTracking(int depth, int at) {
		if (depth == N) {
			answers.add(results[0] + results[1]);
			return;
		}

		for (int i = at; i < arr.length; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				results[depth] = arr[i];
				backTracking(depth + 1, i);
				isVisited[i] = false;
			}
		}
	}


	public int[] solution(int[] numbers) {
		int[] answer = {};
		arr = numbers;
		isVisited = new boolean[arr.length];
		backTracking(0, 0);

//		int idx = 0;
//		answer = new int[answers.size()];
//		for (Integer ans : answers) {
//			answer[idx++] = ans;
//		}
//		return answer;
		return answers.stream().mapToInt(Integer::intValue).toArray();
	}
}
