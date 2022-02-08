package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// baekJoon 1932 silver1 정수삼각형
public class S1932 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());

		int[][] dp = new int[n + 1][n];

		for (int floor = 1; floor <= n; floor++) {
			String[] numbers = br.readLine().split(" ");
			for (int i = 0; i < floor; i++) {
				dp[floor][i] = Integer.parseInt(numbers[i]);
			}
		}

		for (int floor = 2; floor <= n; floor++) {
			for (int i = 0; i < floor; i++) {
				if (i == 0) { // 맨 왼쪽
					dp[floor][0] += dp[floor - 1][0];
				} else if (i == floor - 1) { // 맨 오른쪽
					dp[floor][floor - 1] += dp[floor - 1][floor - 2];
				} else // 중간 요소들
					dp[floor][i] += Math.max(dp[floor - 1][i - 1], dp[floor - 1][i]);
			}
		}

		int answer = Arrays.stream(dp[n]).max().getAsInt();

		bw.write(answer + "");

		bw.flush();
		bw.close();
	}
}
