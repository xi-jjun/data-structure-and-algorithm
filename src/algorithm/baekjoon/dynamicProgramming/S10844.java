package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 10844 silver1 쉬운 계단 수
public class S10844 {
	static final int MOD = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long[][] dp = new long[N + 1][10];
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j - 1 > -1 && j + 1 < 10) {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
				} else if (j == 9) {
					dp[i][9] = dp[i - 1][8] % MOD;
				} else if (j == 0) {
					dp[i][0] = dp[i - 1][1] % MOD;
				}
			}
		}

		long answer = 0L;
		for (int i = 1; i < 10; i++) {
			answer = (answer + dp[N][i]) % MOD;
		}

		System.out.println(answer);
	}
}
