package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 9465 silver1 스티커
public class S9465 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[][] sticker = new int[2][n];
			int[][] dp = new int[n][2];

			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}


			/**
			 * dp[stage][select]
			 * sticker[select][stage]
			 */
			dp[0][0] = sticker[0][0];
			dp[0][1] = sticker[1][0];

			if (n == 1) {
				bw.write(Math.max(dp[n - 1][0], dp[n - 1][1]) + "\n");
				continue;
			}

			dp[1][0] = dp[0][1] + sticker[0][1];
			dp[1][1] = dp[0][0] + sticker[1][1];

			for (int i = 2; i < n; i++) {
				dp[i][0] = Math.max(dp[i - 1][1], Math.max(dp[i - 2][0], dp[i - 2][1])) + sticker[0][i];
				dp[i][1] = Math.max(dp[i - 1][0], Math.max(dp[i - 2][0], dp[i - 2][1])) + sticker[1][i];
			}

			bw.write(Math.max(dp[n - 1][0], dp[n - 1][1]) + "\n");
		}

		bw.flush();
		bw.close();
	}
}
