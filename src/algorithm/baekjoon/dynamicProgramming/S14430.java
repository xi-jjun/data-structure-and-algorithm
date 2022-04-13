package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 14430 silver2 - 자원캐기
public class S14430 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; st.hasMoreTokens(); j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N][M];
		dp[0][0] = map[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == 0 & j == 0) continue;
				if (i == 0) {
					dp[0][j] = map[0][j] + dp[0][j - 1];
				} else if (j == 0) {
					dp[i][0] = map[i][0] + dp[i - 1][0];
				} else {
					dp[i][j] = map[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		System.out.println(dp[N - 1][M - 1]);
	}
}
