package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S11055 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int[] arr = new int[A + 1];
		int[] dp = new int[A + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < A + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp[1] = arr[1];

		int answer = dp[1];
		for (int i = 2; i < A + 1; i++) {
			for (int j = 1; j < i; j++) {
				if (arr[i - j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[i - j] + arr[i]);
				}
			}
			dp[i] = Math.max(dp[i], arr[i]);
		}

		for (int i = 2; i < A + 1; i++) {
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}
}
