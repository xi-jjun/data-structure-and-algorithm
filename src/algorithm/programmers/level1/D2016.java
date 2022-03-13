package algorithm.programmers.level1;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class D2016 {
	public static void main(String[] args) {
		int a = 5;
		int b = 24;
		String answer = solution(a, b);
		System.out.println("answer = " + answer);
	}

	public static String solution(int a, int b) {
		String answer = "";
		LocalDate localDate = LocalDate.of(2016, a, b);
		String dayOfWeek = localDate.getDayOfWeek().toString().substring(0, 3);
		System.out.println("dayOfWeek = " + dayOfWeek);
		return answer;
	}
}
