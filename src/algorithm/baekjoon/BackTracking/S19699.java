package algorithm.baekjoon.BackTracking;

import java.io.*;
import java.util.*;

public class S19699 {
	static int N, M;
	static int[] W;
	static int[] weights;
	static boolean[] visited;
	static Set<Integer> answer = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		W = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; st.hasMoreTokens(); i++) {
			W[i] = Integer.parseInt(st.nextToken());
		}

		weights = new int[M];
		visited = new boolean[N];

		backTracking(0);
		if (answer.isEmpty()) {
			bw.write("-1");
		} else {
			List<Integer> answers = new ArrayList<>(answer);
			answers.sort(Comparator.comparingInt(o -> o));
			for (Integer number : answers) {
				bw.write(number + " ");
			}
		}

		bw.flush();
		bw.close();
	}

	private static void backTracking(int depth) {
		if (depth == M) {
			int sum = sum(weights);
			if (isPrime(sum)) {
				answer.add(sum);
			}
			return;
		}

		for (int i = 0; i < W.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				weights[depth] = W[i];
				backTracking(depth + 1);
				visited[i] = false;
			}
		}
	}

	private static int sum(int[] arr) {
		int ret = 0;
		for (int num : arr) {
			ret += num;
		}
		return ret;
	}

	private static boolean isPrime(int num) {
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) return false;
		}
		return true;
	}
}
