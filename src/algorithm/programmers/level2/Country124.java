package algorithm.programmers.level2;

// https://yabmoons.tistory.com/692 참고해서 품
// 3진법으로 푸는 것을 알았는가?
// 1,2,4 로 3진법 처럼 규칙을 찾을 수 있었는가
public class Country124 {
	public static void main(String[] args) {
		int n = 10;
		String answer = solution(n);
		System.out.println("answer = " + answer);
		String answer1 = otherAnswer(n);
		System.out.println("answer1 = " + answer1);
	}

	public static String solution(int n) {
		StringBuilder sb = new StringBuilder();
		while (n != 0) {
			if (n % 3 == 0) {
				sb.append("4");
				n--; // 3으로 나눠 떨어질 때에는 이전 값에서 1칸 올라와야 하는 것이기 때문이다. 그냥 3진법에서는 10 2개로 표현됨
			} else {
				sb.append(n % 3);
			}
			n /= 3;
		}
		return sb.reverse().toString();
	}

	public static String otherAnswer(int n) {
		int[] number = {4, 1, 2};
		StringBuilder answer = new StringBuilder();
		while (n > 0) {
			answer.insert(0, number[n % 3]);
			n = (n - 1) / 3;
		}
		return answer.toString();
	}
}
