package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 1788 silver3 - 피보나치 수의 확장
public class S1788 {
	public static void main(String[] args) throws IOException {
		final long MOD = 1_000_000_000L;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N == 0) {
			System.out.println("0\n0");
			return;
		}

		int max = Math.abs(N);
		long[] dp = new long[max + 1];
		dp[0] = 0L;
		dp[1] = 1L;
		for (int i = 2; i <= max; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
		}

		long answer = dp[max] % MOD;
		if (N < 0 && N % 2 == 0) {
			System.out.println("-1\n" + answer);
		} else {
			System.out.println("1\n" + answer);
		}
	}
}
