package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 1912 silver2 연속합
public class S1912 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		int answer = Integer.MIN_VALUE;
		int[] dp = new int[n];
		dp[0] = data[0];
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(dp[i - 1] + data[i], data[i]);
		}

		for (int result : dp) {
			if (answer < result) answer = result;
		}

		bw.write(answer + "");

		bw.flush();
		bw.close();
	}
}
