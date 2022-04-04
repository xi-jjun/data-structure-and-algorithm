package algorithm.baekjoon.divide_and_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class S14606 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int A = 0;
		int B = 0;
		int answer = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(N);

		while (!stack.isEmpty()) {
			N = stack.pop();
			A = N % 2 == 0 ? N / 2 : N / 2 + 1;
			B = N / 2;
			answer += A * B;

			if (A != 1) stack.push(A);
			if (B != 1) stack.push(B);
			if (A == 0 || B == 0) break;
		}

		System.out.println(answer);
	}
}
