package Stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

// baekJoon 17298 Gold4 오큰수
public class G17298 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Stack<Integer> stack = new Stack<>();
		Stack<String> answers = new Stack<>();

		for (int i = N; i > 0; i--) {
			if (i == N) {
				stack.push(A[i]);
				answers.push("-1 ");
			} else if (stack.peek() > A[i]) {
				answers.push(stack.peek() + " ");
				stack.push(A[i]);
			} else {
				boolean ok = false;

				while (!stack.isEmpty()) {
					if (stack.peek() > A[i]) {
						answers.push(stack.peek() + " ");
						stack.push(A[i]);
						ok = true;
						break;
					} else {
						stack.pop();
					}
				}

				if (!ok) {
					stack.push(A[i]);
					answers.push("-1 ");
				}
			}
		}

		while (!answers.isEmpty()) {
			bw.write(answers.pop());
		}

		bw.flush();
		bw.close();
	}
}
