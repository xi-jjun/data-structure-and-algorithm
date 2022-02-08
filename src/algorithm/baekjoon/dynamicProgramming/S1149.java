package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 1149 silver1 RGB 거리
public class S1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int[][] rgb = new int[N][3]; // N 번째 집의 RGB 3개 색상
		int[][] dp = new int[N][3]; // N 번째 집의 RGB 3개 색상

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			rgb[i][0] = Integer.parseInt(st.nextToken()); // R
			rgb[i][1] = Integer.parseInt(st.nextToken()); // G
			rgb[i][2] = Integer.parseInt(st.nextToken()); // B
		}

		dp[0][0] = rgb[0][0];
		dp[0][1] = rgb[0][1];
		dp[0][2] = rgb[0][2];

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				dp[i][j] = rgb[i][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
			}
		}

		bw.write(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])) + "\n");
		bw.flush();
		bw.close();
	}
}
