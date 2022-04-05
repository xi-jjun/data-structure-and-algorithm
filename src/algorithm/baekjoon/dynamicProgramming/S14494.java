package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S14494 {
	static final long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		long[][] dp = new long[n + 1][m + 1];
		for (int i = 1; i < n + 1; i++) dp[i][1] = 1;
		for (int i = 1; i < m + 1; i++) dp[1][i] = 1;

		for (int i = 2; i < n + 1; i++) {
			for (int j = 2; j < m + 1; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1] + dp[i - 1][j - 1]) % MOD;
			}
		}

		System.out.println(dp[n][m]);
	}
}
