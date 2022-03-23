package algorithm.programmers.level2;

import java.util.ArrayDeque;
import java.util.Queue;

// 타겟넘버
public class TargetNumber {
	public static void main(String[] args) {
		int[] numbers = {4, 1, 2, 1};
		int target = 4;
		int answer = solution(numbers, target);
		System.out.println("answer = " + answer);
	}

	public static int bfs(int[] numbers, int target) {
		int ret = 0;

		Queue<Number> q = new ArrayDeque<>();
		q.offer(new Number(-1, 0));

		while (!q.isEmpty()) {
			Number now = q.poll();
			int idx = now.index;
			int acc = now.acc;

			for (int op = 0; op < 2; op++) {
				if (idx + 1 < numbers.length) {
					if (idx + 1 == numbers.length - 1 && calculate(acc, op, numbers[idx + 1]) == target) {
						ret++;
					} else {
						q.offer(new Number(idx + 1, calculate(acc, op, numbers[idx + 1])));
					}
				}
			}
		}
		return ret;
	}

	private static int calculate(int acc, int op, int number) {
		return op == 0 ? acc + number : acc - number;
	}

	public static int solution(int[] numbers, int target) {
		int answer = bfs(numbers, target);
		return answer;
	}

	static class Number {
		int index;
		int acc;

		public Number(int index, int acc) {
			this.index = index;
			this.acc = acc;
		}
	}
}
