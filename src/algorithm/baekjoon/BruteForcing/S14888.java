package algorithm.baekjoon.BruteForcing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// baekJoon 14888 silver1 - 연산자 끼워넣기
public class S14888 {
	static int N;
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] A;
	static char[] op;
	static int[] opCount = new int[4];
	static List<Character> opcodes = new ArrayList<>(); // [+, +, -, -, -, *, /, /]
	static boolean[] visited;
	static final char[] OPERATIONS = {'+', '-', '*', '/'};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		op = new char[N - 1];
		A = new int[N];
		visited = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; st.hasMoreTokens(); i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; st.hasMoreTokens(); i++) {
			opCount[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < opCount[i]; j++) {
				opcodes.add(OPERATIONS[i]);
			}
		}

		backTracking(0);
		System.out.println(max + "\n" + min);
	}

	private static void backTracking(int depth) {
		if (depth == N - 1) {
			// op array + A array 로 계산할 수 있게 배열을 다시 만들어야 함
			// op : char[], A : int[]
			int num = A[0];
			num = getNum(num);
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}

		for (int i = 0; i < opcodes.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				op[depth] = opcodes.get(i);
				backTracking(depth + 1);
				visited[i] = false;
			}
		}
	}

	private static int getNum(int num) {
		for (int i = 1; i < N; i++) {
			int num2 = A[i];
			switch (op[i - 1]) {
				case '+':
					num = num + num2;
					break;
				case '-':
					num -= num2;
					break;
				case '*':
					num *= num2;
					break;
				case '/':
					num /= num2;
					break;
				default:
					break;
			}
		}
		return num;
	}
}
