package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon silver1 15486 - 퇴사2
public class S15486 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N + 1];
		int[] P = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			String[] line = br.readLine().split(" ");
			T[i] = Integer.parseInt(line[0]); // T
			P[i] = Integer.parseInt(line[1]); // P
		}

		int max = 0;
		int[] dp = new int[N + 2];
		for (int i = 1; i < N + 1; i++) {
			max = Math.max(max, dp[i]);
			if (i + T[i] <= N + 1) {
				dp[i + T[i]] = Math.max(dp[i + T[i]], max + P[i]);
			}
		}

		int answer = getMax(dp);
		System.out.println(answer);
	}

	private static int getMax(int[] arr) {
		int ret = 0;
		for (int i = 0; i < arr.length; i++) {
			ret = Math.max(arr[i], ret);
		}
		return ret;
	}
}
