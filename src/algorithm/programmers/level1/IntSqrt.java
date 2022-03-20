package algorithm.programmers.level1;

public class IntSqrt {
	public static void main(String[] args) {
		long n = 2492343;
		long answer = solution(n);
		System.out.println("answer = " + answer);
	}

	public static long solution(long n) {
		Double sqrt = Math.sqrt(n);
		if (sqrt == sqrt.intValue()) {
			return (long) Math.pow(sqrt + 1, 2);
		}
		return -1;
	}
}
