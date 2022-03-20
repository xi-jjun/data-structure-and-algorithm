package algorithm.programmers.level1;

public class GcdLCM {
	public static void main(String[] args) {
		int a = 10;
		int b = 4;
		int[] answer = solution(a, b);
		System.out.println(answer[0] + " " + answer[1]);
	}

	public static int gcd(int a, int b) {
		if (a % b == 0) return b;
		return gcd(b, a % b);
	}

	public static int[] solution(int n, int m) {
		return new int[]{gcd(n, m), n * m / gcd(n, m)};
	}
}
