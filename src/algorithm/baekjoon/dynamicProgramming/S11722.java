package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon silver2 11722 - 가장 긴 감소하는 수열
public class S11722 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int idx = 0;
		int[] A = new int[N];
		int[] dp = new int[N];
		while (st.hasMoreTokens()) {
			A[idx++] = Integer.parseInt(st.nextToken());
		}

		dp[0] = 1;
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (A[j] > A[i]) dp[i] = Math.max(dp[j] + 1, dp[i]);
			}
			dp[i] = Math.max(dp[i], 1);
			answer = Math.max(answer, dp[i]);
		}

		System.out.println(answer);
	}
}
