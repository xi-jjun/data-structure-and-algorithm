package algorithm.baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S10819 {
	static int[] A;
	static int[] temp = new int[9];
	static boolean[] isVisited = new boolean[9];
	static int N, answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		backTracking(0);
		System.out.println(answer);
	}

	private static void backTracking(int depth) {
		if (depth == N) {
			int sum = 0;
			for (int i = 0; i < N - 1; i++) {
				sum += Math.abs(temp[i] - temp[i + 1]);
			}
			answer = Math.max(answer, sum);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				temp[depth] = A[i];
				backTracking(depth + 1);
				isVisited[i] = false;
			}
		}
	}
}
