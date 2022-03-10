package algorithm.programmers.level1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 3진법 뒤집기
public class ThreeJinBub {
	public static void main(String[] args) {
		int n = 29;
		int answer = solution(n);
		System.out.println("answer = " + answer);

//		for (int i = 1; i < 100; i++) {
//			System.out.println("solution(" + i + ") = " + solution(i));
//		}
	}

	public static int solution(int n) {
		int answer = 0;
		List<Integer> an = new ArrayList<>();
		boolean start = false;
		for (int i = 16; i >= 0; i--) {
			int divider = (int) Math.pow(3, i);
			int a = n / divider;
			if (a != 0) {
				an.add(a);
				n = n % divider;
				start = true;
			} else if (start) an.add(a);
		}

		int count = 0;
		System.out.println("an = " + an);
		for (Integer a : an) {
			int mul = (int) Math.pow(3, count++);
			answer += a * mul;
		}
		return answer;
	}
}
