package algorithm.programmers.level2;

import java.util.ArrayList;
import java.util.List;

// 기능 개발 level2
public class FunctionDevelop {
	public static void main(String[] args) {
		int[] progress = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		Integer[] answer = solution(progress, speeds);
		for (int i : answer) {
			System.out.print(i + " ");
		}
	}

	public static Integer[] solution(int[] progresses, int[] speeds) {
		int[] needs = new int[progresses.length];
		for (int i = 0; i < progresses.length; i++) {
			needs[i] = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
		}
		List<Integer> answer = new ArrayList<>();
		int count = 0;
		int max = needs[0];
		for (int need : needs) {
			if (max < need) {
				answer.add(count);
				max = need;
				count = 1;
			} else {
				count++;
			}
		}
		answer.add(count);
		return answer.toArray(new Integer[0]);
	}
}
