package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 14501 silver3 - 퇴사
public class S14501 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N + 1];
		int[] P = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		int max = 0;
		int[] dp = new int[N + 2];
		for (int i = 1; i < N + 1; i++) {
			max = Math.max(max, dp[i]);
			if (i + T[i] <= N + 1) {
				dp[i + T[i]] = Math.max(dp[i + T[i]], max + P[i]);
			}
		}

		System.out.println(getMax(dp));
	}

	private static int getMax(int[] dp) {
		int ret = 0;
		for (int num : dp) {
			ret = Math.max(ret, num);
		}
		return ret;
	}
}
