package algorithm.programmers.level1;

import java.util.ArrayList;
import java.util.List;

public class NoSameNumber {
	public static void main(String[] args) {

	}

	public Integer[] solution(int []arr) {
		List<Integer> answer = new ArrayList<>();

		int ex = -1;
		for (int data : arr) {
			if (ex != data) {
				ex = data;
				answer.add(data);
			}
		}

		return answer.toArray(new Integer[0]);
	}
}
